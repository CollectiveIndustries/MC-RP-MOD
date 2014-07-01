/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerBase;
/*   5:    */ import com.eloraam.redpower.RedPowerControl;
/*   6:    */ import com.eloraam.redpower.base.ItemScrewdriver;
/*   7:    */ import com.eloraam.redpower.core.BlockExtended;
/*   8:    */ import com.eloraam.redpower.core.CoreLib;
/*   9:    */ import com.eloraam.redpower.core.IFrameSupport;
/*  10:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  11:    */ import com.eloraam.redpower.core.IRedbusConnectable;
/*  12:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  13:    */ import com.eloraam.redpower.core.TileExtended;
/*  14:    */ import ef;
/*  15:    */ import java.io.ByteArrayOutputStream;
/*  16:    */ import java.io.IOException;
/*  17:    */ import java.util.Arrays;
/*  18:    */ import md;
/*  19:    */ import qw;
/*  20:    */ import qx;
/*  21:    */ import ur;
/*  22:    */ import yc;
/*  23:    */ import ym;
/*  24:    */ 
/*  25:    */ public class TileDisplay
/*  26:    */   extends TileExtended
/*  27:    */   implements IRedbusConnectable, IHandlePackets, IFrameSupport
/*  28:    */ {
/*  29:    */   public byte[] screen;
/*  30:    */   
/*  31:    */   public TileDisplay()
/*  32:    */   {
/*  33: 42 */     this.screen = new byte[4000];
/*  34: 43 */     Arrays.fill(this.screen, (byte)32);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public int rbGetAddr()
/*  38:    */   {
/*  39: 49 */     return this.rbaddr;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void rbSetAddr(int addr)
/*  43:    */   {
/*  44: 53 */     this.rbaddr = addr;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public int rbRead(int reg)
/*  48:    */   {
/*  49: 57 */     if ((reg >= 16) && (reg < 96)) {
/*  50: 58 */       return this.screen[(this.memRow * 80 + reg - 16)];
/*  51:    */     }
/*  52: 59 */     switch (reg)
/*  53:    */     {
/*  54:    */     case 0: 
/*  55: 60 */       return this.memRow;
/*  56:    */     case 1: 
/*  57: 61 */       return this.cursX;
/*  58:    */     case 2: 
/*  59: 62 */       return this.cursY;
/*  60:    */     case 3: 
/*  61: 63 */       return this.cursMode;
/*  62:    */     case 4: 
/*  63: 64 */       return this.kbstart;
/*  64:    */     case 5: 
/*  65: 65 */       return this.kbpos;
/*  66:    */     case 6: 
/*  67: 66 */       return this.kbbuf[this.kbstart] & 0xFF;
/*  68:    */     case 7: 
/*  69: 68 */       return this.blitMode;
/*  70:    */     case 8: 
/*  71: 69 */       return this.blitXS;
/*  72:    */     case 9: 
/*  73: 70 */       return this.blitYS;
/*  74:    */     case 10: 
/*  75: 71 */       return this.blitXD;
/*  76:    */     case 11: 
/*  77: 72 */       return this.blitYD;
/*  78:    */     case 12: 
/*  79: 73 */       return this.blitW;
/*  80:    */     case 13: 
/*  81: 74 */       return this.blitH;
/*  82:    */     }
/*  83: 75 */     return 0;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void rbWrite(int reg, int dat)
/*  87:    */   {
/*  88: 80 */     dirtyBlock();
/*  89: 81 */     if ((reg >= 16) && (reg < 96))
/*  90:    */     {
/*  91: 82 */       this.screen[(this.memRow * 80 + reg - 16)] = ((byte)dat);
/*  92: 83 */       return;
/*  93:    */     }
/*  94: 85 */     switch (reg)
/*  95:    */     {
/*  96:    */     case 0: 
/*  97: 86 */       this.memRow = dat;
/*  98: 86 */       if (this.memRow > 49) {
/*  99: 86 */         this.memRow = 49;
/* 100:    */       }
/* 101: 86 */       return;
/* 102:    */     case 1: 
/* 103: 87 */       this.cursX = dat;return;
/* 104:    */     case 2: 
/* 105: 88 */       this.cursY = dat;return;
/* 106:    */     case 3: 
/* 107: 89 */       this.cursMode = dat;return;
/* 108:    */     case 4: 
/* 109: 90 */       this.kbstart = (dat & 0xF);return;
/* 110:    */     case 5: 
/* 111: 91 */       this.kbpos = (dat & 0xF);return;
/* 112:    */     case 6: 
/* 113: 92 */       this.kbbuf[this.kbstart] = ((byte)dat);return;
/* 114:    */     case 7: 
/* 115: 94 */       this.blitMode = dat;return;
/* 116:    */     case 8: 
/* 117: 95 */       this.blitXS = dat;return;
/* 118:    */     case 9: 
/* 119: 96 */       this.blitYS = dat;return;
/* 120:    */     case 10: 
/* 121: 97 */       this.blitXD = dat;return;
/* 122:    */     case 11: 
/* 123: 98 */       this.blitYD = dat;return;
/* 124:    */     case 12: 
/* 125: 99 */       this.blitW = dat;return;
/* 126:    */     case 13: 
/* 127:100 */       this.blitH = dat;return;
/* 128:    */     }
/* 129:    */   }
/* 130:    */   
/* 131:    */   public int getConnectableMask()
/* 132:    */   {
/* 133:107 */     return 16777215;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public int getConnectClass(int side)
/* 137:    */   {
/* 138:111 */     return 66;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public int getCornerPowerMode()
/* 142:    */   {
/* 143:114 */     return 0;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 147:    */   {
/* 148:120 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) + 1 & 0x3);
/* 149:    */   }
/* 150:    */   
/* 151:    */   public boolean onBlockActivated(qx player)
/* 152:    */   {
/* 153:124 */     if (player.ah())
/* 154:    */     {
/* 155:125 */       if (CoreLib.isClient(this.k)) {
/* 156:126 */         return false;
/* 157:    */       }
/* 158:128 */       ur ist = player.bJ.g();
/* 159:129 */       if (ist == null) {
/* 160:129 */         return false;
/* 161:    */       }
/* 162:130 */       if (!(ist.b() instanceof ItemScrewdriver)) {
/* 163:131 */         return false;
/* 164:    */       }
/* 165:132 */       player.openGui(RedPowerBase.instance, 3, this.k, this.l, this.m, this.n);
/* 166:    */       
/* 167:134 */       return true;
/* 168:    */     }
/* 169:136 */     if (CoreLib.isClient(this.k)) {
/* 170:137 */       return true;
/* 171:    */     }
/* 172:138 */     player.openGui(RedPowerControl.instance, 1, this.k, this.l, this.m, this.n);
/* 173:    */     
/* 174:    */ 
/* 175:    */ 
/* 176:    */ 
/* 177:143 */     return true;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public int getBlockID()
/* 181:    */   {
/* 182:147 */     return RedPowerControl.blockPeripheral.cm;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public int getExtendedID()
/* 186:    */   {
/* 187:151 */     return 0;
/* 188:    */   }
/* 189:    */   
/* 190:    */   public boolean isUseableByPlayer(qx player)
/* 191:    */   {
/* 192:157 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 193:158 */       return false;
/* 194:    */     }
/* 195:159 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void pushKey(byte b)
/* 199:    */   {
/* 200:166 */     int np = this.kbpos + 1 & 0xF;
/* 201:167 */     if (np == this.kbstart) {
/* 202:167 */       return;
/* 203:    */     }
/* 204:168 */     this.kbbuf[this.kbpos] = b;
/* 205:169 */     this.kbpos = np;
/* 206:    */   }
/* 207:    */   
/* 208:    */   public void g()
/* 209:    */   {
/* 210:173 */     runblitter();
/* 211:    */   }
/* 212:    */   
/* 213:    */   private void runblitter()
/* 214:    */   {
/* 215:177 */     if (this.blitMode == 0) {
/* 216:177 */       return;
/* 217:    */     }
/* 218:178 */     dirtyBlock();
/* 219:179 */     int w = this.blitW;int h = this.blitH;
/* 220:180 */     w = Math.min(w, 80 - this.blitXD);
/* 221:181 */     h = Math.min(h, 50 - this.blitYD);
/* 222:182 */     if ((w < 0) || (h < 0))
/* 223:    */     {
/* 224:182 */       this.blitMode = 0;return;
/* 225:    */     }
/* 226:184 */     int doffs = this.blitYD * 80 + this.blitXD;
/* 227:186 */     switch (this.blitMode)
/* 228:    */     {
/* 229:    */     case 1: 
/* 230:188 */       for (int j = 0; j < h; j++) {
/* 231:188 */         for (int i = 0; i < w; i++) {
/* 232:189 */           this.screen[(doffs + 80 * j + i)] = ((byte)this.blitXS);
/* 233:    */         }
/* 234:    */       }
/* 235:190 */       this.blitMode = 0;return;
/* 236:    */     case 2: 
/* 237:192 */       for (int j = 0; j < h; j++) {
/* 238:192 */         for (int i = 0; i < w; i++)
/* 239:    */         {
/* 240:193 */           int tmp192_191 = (doffs + 80 * j + i); byte[] tmp192_179 = this.screen;tmp192_179[tmp192_191] = ((byte)(tmp192_179[tmp192_191] ^ 0x80));
/* 241:    */         }
/* 242:    */       }
/* 243:194 */       this.blitMode = 0;return;
/* 244:    */     }
/* 245:196 */     w = Math.min(w, 80 - this.blitXS);
/* 246:197 */     h = Math.min(h, 50 - this.blitYS);
/* 247:198 */     if ((w < 0) || (h < 0))
/* 248:    */     {
/* 249:198 */       this.blitMode = 0;return;
/* 250:    */     }
/* 251:200 */     int soffs = this.blitYS * 80 + this.blitXS;
/* 252:202 */     switch (this.blitMode)
/* 253:    */     {
/* 254:    */     case 3: 
/* 255:204 */       for (int j = 0; j < h; j++) {
/* 256:204 */         for (int i = 0; i < w; i++) {
/* 257:205 */           this.screen[(doffs + 80 * j + i)] = this.screen[(soffs + 80 * j + i)];
/* 258:    */         }
/* 259:    */       }
/* 260:206 */       this.blitMode = 0;return;
/* 261:    */     }
/* 262:    */   }
/* 263:    */   
/* 264:    */   public byte[] getFramePacket()
/* 265:    */   {
/* 266:215 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 267:216 */     pkt.subId = 7;
/* 268:217 */     writeToPacket(pkt);
/* 269:218 */     pkt.headout.write(pkt.subId);
/* 270:219 */     return pkt.toByteArray();
/* 271:    */   }
/* 272:    */   
/* 273:    */   public void handleFramePacket(byte[] ba)
/* 274:    */     throws IOException
/* 275:    */   {
/* 276:223 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 277:224 */     pkt.subId = pkt.getByte();
/* 278:225 */     readFromPacket(pkt);
/* 279:    */   }
/* 280:    */   
/* 281:    */   public void onFrameRefresh(ym iba) {}
/* 282:    */   
/* 283:    */   public void onFramePickup(ym iba) {}
/* 284:    */   
/* 285:    */   public void onFrameDrop() {}
/* 286:    */   
/* 287:    */   public void a(bq tag)
/* 288:    */   {
/* 289:235 */     super.a(tag);
/* 290:236 */     this.Rotation = tag.c("rot");
/* 291:237 */     this.screen = tag.j("fb");
/* 292:238 */     if (this.screen.length != 4000) {
/* 293:239 */       this.screen = new byte[4000];
/* 294:    */     }
/* 295:240 */     this.memRow = (tag.c("row") & 0xFF);
/* 296:241 */     this.cursX = (tag.c("cx") & 0xFF);
/* 297:242 */     this.cursY = (tag.c("cy") & 0xFF);
/* 298:243 */     this.cursMode = (tag.c("cm") & 0xFF);
/* 299:    */     
/* 300:245 */     this.kbstart = tag.c("kbs");
/* 301:246 */     this.kbpos = tag.c("kbp");
/* 302:    */     
/* 303:248 */     this.kbbuf = tag.j("kbb");
/* 304:249 */     if (this.kbbuf.length != 16) {
/* 305:250 */       this.kbbuf = new byte[16];
/* 306:    */     }
/* 307:252 */     this.blitXS = (tag.c("blxs") & 0xFF);
/* 308:253 */     this.blitYS = (tag.c("blys") & 0xFF);
/* 309:254 */     this.blitXD = (tag.c("blxd") & 0xFF);
/* 310:255 */     this.blitYD = (tag.c("blyd") & 0xFF);
/* 311:256 */     this.blitW = (tag.c("blw") & 0xFF);
/* 312:257 */     this.blitH = (tag.c("blh") & 0xFF);
/* 313:258 */     this.blitMode = tag.c("blmd");
/* 314:    */     
/* 315:260 */     this.rbaddr = (tag.c("rbaddr") & 0xFF);
/* 316:    */   }
/* 317:    */   
/* 318:    */   public void b(bq tag)
/* 319:    */   {
/* 320:264 */     super.b(tag);
/* 321:265 */     tag.a("rot", (byte)this.Rotation);
/* 322:266 */     tag.a("fb", this.screen);
/* 323:267 */     tag.a("row", (byte)this.memRow);
/* 324:268 */     tag.a("cx", (byte)this.cursX);
/* 325:269 */     tag.a("cy", (byte)this.cursY);
/* 326:270 */     tag.a("cm", (byte)this.cursMode);
/* 327:    */     
/* 328:272 */     tag.a("kbs", (byte)this.kbstart);
/* 329:273 */     tag.a("kbp", (byte)this.kbpos);
/* 330:274 */     tag.a("kbb", this.kbbuf);
/* 331:    */     
/* 332:276 */     tag.a("blxs", (byte)this.blitXS);
/* 333:277 */     tag.a("blys", (byte)this.blitYS);
/* 334:278 */     tag.a("blxd", (byte)this.blitXD);
/* 335:279 */     tag.a("blyd", (byte)this.blitYD);
/* 336:280 */     tag.a("blw", (byte)this.blitW);
/* 337:281 */     tag.a("blh", (byte)this.blitH);
/* 338:282 */     tag.a("blmd", (byte)this.blitMode);
/* 339:    */     
/* 340:284 */     tag.a("rbaddr", (byte)this.rbaddr);
/* 341:    */   }
/* 342:    */   
/* 343:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 344:    */     throws IOException
/* 345:    */   {
/* 346:289 */     this.Rotation = pkt.getByte();
/* 347:    */   }
/* 348:    */   
/* 349:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 350:    */   {
/* 351:293 */     pkt.addByte(this.Rotation);
/* 352:    */   }
/* 353:    */   
/* 354:    */   public ef l()
/* 355:    */   {
/* 356:297 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 357:298 */     packet.subId = 7;
/* 358:299 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 359:300 */     packet.zCoord = this.n;
/* 360:301 */     writeToPacket(packet);
/* 361:302 */     packet.encode();
/* 362:303 */     return packet;
/* 363:    */   }
/* 364:    */   
/* 365:    */   public void handlePacket(Packet211TileDesc packet)
/* 366:    */   {
/* 367:    */     try
/* 368:    */     {
/* 369:308 */       if (packet.subId != 7) {
/* 370:308 */         return;
/* 371:    */       }
/* 372:309 */       readFromPacket(packet);
/* 373:    */     }
/* 374:    */     catch (IOException e) {}
/* 375:311 */     this.k.i(this.l, this.m, this.n);
/* 376:    */   }
/* 377:    */   
/* 378:315 */   public int Rotation = 0;
/* 379:316 */   public int memRow = 0;
/* 380:316 */   public int cursX = 0;
/* 381:316 */   public int cursY = 0;
/* 382:316 */   public int cursMode = 2;
/* 383:317 */   public int kbstart = 0;
/* 384:317 */   public int kbpos = 0;
/* 385:319 */   public int blitXS = 0;
/* 386:319 */   public int blitYS = 0;
/* 387:319 */   public int blitXD = 0;
/* 388:319 */   public int blitYD = 0;
/* 389:319 */   public int blitW = 0;
/* 390:319 */   public int blitH = 0;
/* 391:320 */   public int blitMode = 0;
/* 392:321 */   public byte[] kbbuf = new byte[16];
/* 393:323 */   int rbaddr = 1;
/* 394:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.TileDisplay
 * JD-Core Version:    0.7.0.1
 */