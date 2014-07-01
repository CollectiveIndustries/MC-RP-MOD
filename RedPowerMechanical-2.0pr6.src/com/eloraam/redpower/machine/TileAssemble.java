/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.IRedPowerWiring;
/*   8:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   9:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  10:    */ import com.eloraam.redpower.core.WorldCoord;
/*  11:    */ import java.io.IOException;
/*  12:    */ import la;
/*  13:    */ import net.minecraftforge.common.ForgeDirection;
/*  14:    */ import net.minecraftforge.common.ISidedInventory;
/*  15:    */ import qw;
/*  16:    */ import qx;
/*  17:    */ import ur;
/*  18:    */ import yc;
/*  19:    */ 
/*  20:    */ public class TileAssemble
/*  21:    */   extends TileDeployBase
/*  22:    */   implements la, ISidedInventory, IRedPowerWiring
/*  23:    */ {
/*  24:    */   public TileAssemble()
/*  25:    */   {
/*  26: 20 */     this.contents = new ur[34];
/*  27:    */   }
/*  28:    */   
/*  29:    */   public int getExtendedID()
/*  30:    */   {
/*  31: 26 */     return 13;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public boolean onBlockActivated(qx player)
/*  35:    */   {
/*  36: 30 */     if (player.ah()) {
/*  37: 30 */       return false;
/*  38:    */     }
/*  39: 31 */     if (CoreLib.isClient(this.k)) {
/*  40: 32 */       return true;
/*  41:    */     }
/*  42: 33 */     player.openGui(RedPowerMachine.instance, 11, this.k, this.l, this.m, this.n);
/*  43:    */     
/*  44: 35 */     return true;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void onBlockRemoval()
/*  48:    */   {
/*  49: 39 */     for (int i = 0; i < 34; i++)
/*  50:    */     {
/*  51: 40 */       ur ist = this.contents[i];
/*  52: 41 */       if ((ist != null) && (ist.a > 0)) {
/*  53: 42 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/*  54:    */       }
/*  55:    */     }
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void onBlockNeighborChange(int l)
/*  59:    */   {
/*  60: 49 */     this.ConMask = -1;
/*  61: 50 */     if (this.mode == 0) {
/*  62: 50 */       super.onBlockNeighborChange(l);
/*  63:    */     }
/*  64: 51 */     RedPowerLib.updateCurrent(this.k, this.l, this.m, this.n);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public int getConnectionMask()
/*  68:    */   {
/*  69: 57 */     if (this.ConMask >= 0) {
/*  70: 57 */       return this.ConMask;
/*  71:    */     }
/*  72: 58 */     this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/*  73:    */     
/*  74: 60 */     return this.ConMask;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public int getExtConnectionMask()
/*  78:    */   {
/*  79: 64 */     return 0;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public int getPoweringMask(int ch)
/*  83:    */   {
/*  84: 70 */     return 0;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public int scanPoweringStrength(int cons, int ch)
/*  88:    */   {
/*  89: 76 */     return 0;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public int getCurrentStrength(int cons, int ch)
/*  93:    */   {
/*  94: 80 */     return -1;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void updateCurrentStrength()
/*  98:    */   {
/*  99: 90 */     if (this.mode != 1) {
/* 100: 90 */       return;
/* 101:    */     }
/* 102: 91 */     for (int ch = 0; ch < 16; ch++)
/* 103:    */     {
/* 104: 92 */       int ps = (short)RedPowerLib.getMaxCurrentStrength(this.k, this.l, this.m, this.n, 1073741823, 0, ch + 1);
/* 105: 96 */       if (ps > 0) {
/* 106: 96 */         this.PowerState |= 1 << ch;
/* 107:    */       } else {
/* 108: 97 */         this.PowerState &= (1 << ch ^ 0xFFFFFFFF);
/* 109:    */       }
/* 110:    */     }
/* 111: 99 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/* 112:101 */     if (this.PowerState == 0)
/* 113:    */     {
/* 114:102 */       if (!this.Active) {
/* 115:102 */         return;
/* 116:    */       }
/* 117:103 */       scheduleTick(5);
/* 118:104 */       return;
/* 119:    */     }
/* 120:106 */     if (this.Active) {
/* 121:106 */       return;
/* 122:    */     }
/* 123:107 */     this.Active = true;
/* 124:108 */     updateBlock();
/* 125:    */     
/* 126:110 */     int slot = Integer.numberOfTrailingZeros(this.PowerState);
/* 127:111 */     if (this.contents[slot] != null)
/* 128:    */     {
/* 129:112 */       WorldCoord wc = new WorldCoord(this);
/* 130:113 */       wc.step(this.Rotation ^ 0x1);
/* 131:    */       
/* 132:115 */       int ms = getMatchingStack(slot);
/* 133:116 */       if (ms >= 0) {
/* 134:116 */         enableTowardsActive(wc, ms);
/* 135:    */       }
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   public int getConnectClass(int side)
/* 140:    */   {
/* 141:123 */     if (this.mode == 0) {
/* 142:123 */       return 0;
/* 143:    */     }
/* 144:124 */     return 18;
/* 145:    */   }
/* 146:    */   
/* 147:    */   protected void packInv(ur[] bkup, int act)
/* 148:    */   {
/* 149:130 */     for (int i = 0; i < 36; i++)
/* 150:    */     {
/* 151:131 */       bkup[i] = fakePlayer.bJ.a(i);
/* 152:132 */       fakePlayer.bJ.a(i, null);
/* 153:    */     }
/* 154:134 */     for (int i = 0; i < 18; i++) {
/* 155:135 */       if (act == i) {
/* 156:136 */         fakePlayer.bJ.a(0, this.contents[(16 + i)]);
/* 157:    */       } else {
/* 158:140 */         fakePlayer.bJ.a(i + 9, this.contents[(16 + i)]);
/* 159:    */       }
/* 160:    */     }
/* 161:    */   }
/* 162:    */   
/* 163:    */   protected void unpackInv(ur[] bkup, int act)
/* 164:    */   {
/* 165:148 */     for (int i = 0; i < 18; i++) {
/* 166:149 */       if (act == i) {
/* 167:150 */         this.contents[(16 + i)] = fakePlayer.bJ.a(0);
/* 168:    */       } else {
/* 169:153 */         this.contents[(16 + i)] = fakePlayer.bJ.a(i + 9);
/* 170:    */       }
/* 171:    */     }
/* 172:157 */     for (int i = 0; i < 36; i++) {
/* 173:158 */       fakePlayer.bJ.a(i, bkup[i]);
/* 174:    */     }
/* 175:    */   }
/* 176:    */   
/* 177:    */   protected int getMatchingStack(int stack)
/* 178:    */   {
/* 179:164 */     for (int i = 0; i < 18; i++)
/* 180:    */     {
/* 181:165 */       ur ist = this.contents[(16 + i)];
/* 182:166 */       if ((this.contents[(16 + i)] != null) && (CoreLib.compareItemStack(this.contents[(16 + i)], this.contents[stack]) == 0)) {
/* 183:168 */         return i;
/* 184:    */       }
/* 185:    */     }
/* 186:171 */     return -1;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public void enableTowards(WorldCoord wc)
/* 190:    */   {
/* 191:175 */     if (this.contents[this.select] != null)
/* 192:    */     {
/* 193:176 */       int ms = getMatchingStack(this.select);
/* 194:177 */       if (ms >= 0) {
/* 195:177 */         enableTowardsActive(wc, ms);
/* 196:    */       }
/* 197:    */     }
/* 198:179 */     for (int i = 0; i < 16; i++)
/* 199:    */     {
/* 200:180 */       this.select = ((byte)(this.select + 1 & 0xF));
/* 201:181 */       if (((this.skipSlots & 1 << this.select) == 0) || (this.select == 0)) {
/* 202:    */         break;
/* 203:    */       }
/* 204:    */     }
/* 205:    */   }
/* 206:    */   
/* 207:    */   protected void enableTowardsActive(WorldCoord wc, int act)
/* 208:    */   {
/* 209:187 */     ur[] bkup = new ur[36];
/* 210:188 */     initPlayer();
/* 211:189 */     packInv(bkup, act);
/* 212:    */     
/* 213:191 */     ur ist = this.contents[(16 + act)];
/* 214:192 */     if ((ist != null) && (ist.a > 0) && 
/* 215:193 */       (tryUseItemStack(ist, wc.x, wc.y, wc.z, 0)))
/* 216:    */     {
/* 217:194 */       if (fakePlayer.bM()) {
/* 218:195 */         fakePlayer.bO();
/* 219:    */       }
/* 220:196 */       unpackInv(bkup, act);
/* 221:198 */       if (this.contents[(16 + act)].a == 0) {
/* 222:199 */         this.contents[(16 + act)] = null;
/* 223:    */       }
/* 224:200 */       d();
/* 225:201 */       return;
/* 226:    */     }
/* 227:204 */     unpackInv(bkup, act);
/* 228:    */   }
/* 229:    */   
/* 230:    */   public int k_()
/* 231:    */   {
/* 232:210 */     return 34;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public ur a(int i)
/* 236:    */   {
/* 237:214 */     return this.contents[i];
/* 238:    */   }
/* 239:    */   
/* 240:    */   public ur a(int i, int j)
/* 241:    */   {
/* 242:219 */     if (this.contents[i] == null) {
/* 243:219 */       return null;
/* 244:    */     }
/* 245:221 */     if (this.contents[i].a <= j)
/* 246:    */     {
/* 247:222 */       ur tr = this.contents[i];
/* 248:223 */       this.contents[i] = null;
/* 249:224 */       d();
/* 250:225 */       return tr;
/* 251:    */     }
/* 252:227 */     ur tr = this.contents[i].a(j);
/* 253:228 */     if (this.contents[i].a == 0) {
/* 254:229 */       this.contents[i] = null;
/* 255:    */     }
/* 256:230 */     d();
/* 257:231 */     return tr;
/* 258:    */   }
/* 259:    */   
/* 260:    */   public ur a_(int i)
/* 261:    */   {
/* 262:235 */     if (this.contents[i] == null) {
/* 263:235 */       return null;
/* 264:    */     }
/* 265:236 */     ur ist = this.contents[i];
/* 266:237 */     this.contents[i] = null;
/* 267:238 */     return ist;
/* 268:    */   }
/* 269:    */   
/* 270:    */   public void a(int i, ur ist)
/* 271:    */   {
/* 272:242 */     this.contents[i] = ist;
/* 273:243 */     if ((ist != null) && (ist.a > c())) {
/* 274:244 */       ist.a = c();
/* 275:    */     }
/* 276:245 */     if ((ist != null) && (i < 16)) {
/* 277:246 */       this.skipSlots &= (1 << i ^ 0xFFFFFFFF);
/* 278:    */     }
/* 279:248 */     d();
/* 280:    */   }
/* 281:    */   
/* 282:    */   public String b()
/* 283:    */   {
/* 284:252 */     return "Assembler";
/* 285:    */   }
/* 286:    */   
/* 287:    */   public int c()
/* 288:    */   {
/* 289:256 */     return 64;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public boolean a_(qx player)
/* 293:    */   {
/* 294:260 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 295:261 */       return false;
/* 296:    */     }
/* 297:262 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 298:    */   }
/* 299:    */   
/* 300:    */   public void f() {}
/* 301:    */   
/* 302:    */   public void l_() {}
/* 303:    */   
/* 304:    */   public int getStartInventorySide(ForgeDirection fd)
/* 305:    */   {
/* 306:274 */     int side = fd.ordinal();
/* 307:275 */     if ((side ^ 0x1) == this.Rotation) {
/* 308:275 */       return 0;
/* 309:    */     }
/* 310:276 */     return 16;
/* 311:    */   }
/* 312:    */   
/* 313:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 314:    */   {
/* 315:281 */     int side = fd.ordinal();
/* 316:282 */     if ((side ^ 0x1) == this.Rotation) {
/* 317:282 */       return 0;
/* 318:    */     }
/* 319:283 */     return 18;
/* 320:    */   }
/* 321:    */   
/* 322:    */   public void a(bq tag)
/* 323:    */   {
/* 324:289 */     super.a(tag);
/* 325:    */     
/* 326:291 */     by items = tag.m("Items");
/* 327:292 */     this.contents = new ur[k_()];
/* 328:293 */     for (int i = 0; i < items.c(); i++)
/* 329:    */     {
/* 330:294 */       bq item = (bq)items.b(i);
/* 331:    */       
/* 332:296 */       int j = item.c("Slot") & 0xFF;
/* 333:297 */       if ((j >= 0) && (j < this.contents.length)) {
/* 334:298 */         this.contents[j] = ur.a(item);
/* 335:    */       }
/* 336:    */     }
/* 337:301 */     this.mode = tag.c("mode");
/* 338:302 */     this.select = tag.c("sel");
/* 339:303 */     this.skipSlots = (tag.d("ssl") & 0xFFFF);
/* 340:304 */     this.PowerState = tag.e("psex");
/* 341:    */   }
/* 342:    */   
/* 343:    */   public void b(bq tag)
/* 344:    */   {
/* 345:308 */     super.b(tag);
/* 346:    */     
/* 347:310 */     by items = new by();
/* 348:311 */     for (int i = 0; i < this.contents.length; i++) {
/* 349:312 */       if (this.contents[i] != null)
/* 350:    */       {
/* 351:313 */         bq item = new bq();
/* 352:314 */         item.a("Slot", (byte)i);
/* 353:315 */         this.contents[i].b(item);
/* 354:316 */         items.a(item);
/* 355:    */       }
/* 356:    */     }
/* 357:319 */     tag.a("Items", items);
/* 358:320 */     tag.a("mode", this.mode);
/* 359:321 */     tag.a("sel", this.select);
/* 360:322 */     tag.a("ssl", (short)this.skipSlots);
/* 361:323 */     tag.a("psex", this.PowerState);
/* 362:    */   }
/* 363:    */   
/* 364:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 365:    */     throws IOException
/* 366:    */   {
/* 367:328 */     super.readFromPacket(pkt);
/* 368:329 */     this.mode = ((byte)pkt.getByte());
/* 369:    */   }
/* 370:    */   
/* 371:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 372:    */   {
/* 373:333 */     super.writeToPacket(pkt);
/* 374:334 */     pkt.addByte(this.mode);
/* 375:    */   }
/* 376:    */   
/* 377:339 */   public byte select = 0;
/* 378:339 */   public byte mode = 0;
/* 379:340 */   public int skipSlots = 65534;
/* 380:341 */   public int ConMask = -1;
/* 381:342 */   public int PowerState = 0;
/* 382:    */   private ur[] contents;
/* 383:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileAssemble
 * JD-Core Version:    0.7.0.1
 */