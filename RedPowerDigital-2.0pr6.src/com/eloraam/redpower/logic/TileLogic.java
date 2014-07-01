/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerLogic;
/*   5:    */ import com.eloraam.redpower.core.BlockExtended;
/*   6:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.CoverLib;
/*   9:    */ import com.eloraam.redpower.core.IFrameSupport;
/*  10:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  11:    */ import com.eloraam.redpower.core.IRedPowerConnectable;
/*  12:    */ import com.eloraam.redpower.core.IRotatable;
/*  13:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  14:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  15:    */ import com.eloraam.redpower.core.TileCoverable;
/*  16:    */ import ef;
/*  17:    */ import java.io.ByteArrayOutputStream;
/*  18:    */ import java.io.IOException;
/*  19:    */ import java.util.ArrayList;
/*  20:    */ import qx;
/*  21:    */ import ur;
/*  22:    */ import yc;
/*  23:    */ import ym;
/*  24:    */ 
/*  25:    */ public class TileLogic
/*  26:    */   extends TileCoverable
/*  27:    */   implements IHandlePackets, IRedPowerConnectable, IRotatable, IFrameSupport
/*  28:    */ {
/*  29:    */   public int getPartMaxRotation(int part, boolean sec)
/*  30:    */   {
/*  31: 29 */     if (sec) {
/*  32: 29 */       return 0;
/*  33:    */     }
/*  34: 30 */     if (part != this.Rotation >> 2) {
/*  35: 30 */       return 0;
/*  36:    */     }
/*  37: 31 */     return 3;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getPartRotation(int part, boolean sec)
/*  41:    */   {
/*  42: 35 */     if (sec) {
/*  43: 35 */       return 0;
/*  44:    */     }
/*  45: 36 */     if (part != this.Rotation >> 2) {
/*  46: 36 */       return 0;
/*  47:    */     }
/*  48: 37 */     return this.Rotation & 0x3;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void setPartRotation(int part, boolean sec, int rot)
/*  52:    */   {
/*  53: 41 */     if (sec) {
/*  54: 41 */       return;
/*  55:    */     }
/*  56: 42 */     if (part != this.Rotation >> 2) {
/*  57: 42 */       return;
/*  58:    */     }
/*  59: 43 */     this.Rotation = (rot & 0x3 | this.Rotation & 0xFFFFFFFC);
/*  60: 44 */     updateBlockChange();
/*  61:    */   }
/*  62:    */   
/*  63:    */   public int getConnectableMask()
/*  64:    */   {
/*  65: 50 */     return 15 << (this.Rotation & 0xFFFFFFFC);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public int getConnectClass(int side)
/*  69:    */   {
/*  70: 54 */     return 0;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public int getCornerPowerMode()
/*  74:    */   {
/*  75: 58 */     return 0;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public int getPoweringMask(int ch)
/*  79:    */   {
/*  80: 62 */     if (ch != 0) {
/*  81: 62 */       return 0;
/*  82:    */     }
/*  83: 63 */     if (this.Powered) {
/*  84: 64 */       return RedPowerLib.mapRotToCon(8, this.Rotation);
/*  85:    */     }
/*  86: 66 */     return 0;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public boolean canAddCover(int side, int cover)
/*  90:    */   {
/*  91: 72 */     if (this.Cover != 255) {
/*  92: 72 */       return false;
/*  93:    */     }
/*  94: 73 */     if ((side ^ 0x1) != this.Rotation >> 2) {
/*  95: 73 */       return false;
/*  96:    */     }
/*  97: 74 */     if (cover > 254) {
/*  98: 74 */       return false;
/*  99:    */     }
/* 100: 75 */     return true;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public boolean tryAddCover(int side, int cover)
/* 104:    */   {
/* 105: 79 */     if (!canAddCover(side, cover)) {
/* 106: 80 */       return false;
/* 107:    */     }
/* 108: 81 */     this.Cover = cover;
/* 109: 82 */     updateBlock();
/* 110: 83 */     return true;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public int tryRemoveCover(int side)
/* 114:    */   {
/* 115: 88 */     if (this.Cover == 255) {
/* 116: 88 */       return -1;
/* 117:    */     }
/* 118: 89 */     if ((side ^ 0x1) != this.Rotation >> 2) {
/* 119: 89 */       return -1;
/* 120:    */     }
/* 121: 90 */     int tr = this.Cover;this.Cover = 255;
/* 122: 91 */     updateBlock();
/* 123: 92 */     return tr;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public int getCover(int side)
/* 127:    */   {
/* 128: 96 */     if (this.Cover == 255) {
/* 129: 96 */       return -1;
/* 130:    */     }
/* 131: 97 */     if ((side ^ 0x1) != this.Rotation >> 2) {
/* 132: 97 */       return -1;
/* 133:    */     }
/* 134: 98 */     return this.Cover;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public int getCoverMask()
/* 138:    */   {
/* 139:102 */     if (this.Cover == 255) {
/* 140:102 */       return 0;
/* 141:    */     }
/* 142:103 */     return 1 << (this.Rotation >> 2 ^ 0x1);
/* 143:    */   }
/* 144:    */   
/* 145:    */   public boolean blockEmpty()
/* 146:    */   {
/* 147:109 */     return false;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public void addHarvestContents(ArrayList ist)
/* 151:    */   {
/* 152:113 */     super.addHarvestContents(ist);
/* 153:    */     
/* 154:115 */     ist.add(new ur(getBlockID(), 1, getExtendedID() * 256 + this.SubId));
/* 155:    */   }
/* 156:    */   
/* 157:    */   private void replaceWithCovers()
/* 158:    */   {
/* 159:120 */     if (this.Cover != 255)
/* 160:    */     {
/* 161:121 */       short[] t = new short[26];
/* 162:122 */       t[(this.Rotation >> 2 ^ 0x1)] = ((short)this.Cover);
/* 163:123 */       CoverLib.replaceWithCovers(this.k, this.l, this.m, this.n, 1 << (this.Rotation >> 2 ^ 0x1), t);
/* 164:    */       
/* 165:    */ 
/* 166:126 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(getBlockID(), 1, getExtendedID() * 256 + this.SubId));
/* 167:    */     }
/* 168:    */     else
/* 169:    */     {
/* 170:130 */       breakBlock();
/* 171:131 */       RedPowerLib.updateIndirectNeighbors(this.k, this.l, this.m, this.n, getBlockID());
/* 172:    */     }
/* 173:    */   }
/* 174:    */   
/* 175:    */   public boolean tryDropBlock()
/* 176:    */   {
/* 177:137 */     if (RedPowerLib.canSupportWire(this.k, this.l, this.m, this.n, this.Rotation >> 2)) {
/* 178:139 */       return false;
/* 179:    */     }
/* 180:140 */     replaceWithCovers();
/* 181:141 */     return true;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void onHarvestPart(qx player, int part)
/* 185:    */   {
/* 186:145 */     if (part == this.Rotation >> 2)
/* 187:    */     {
/* 188:146 */       replaceWithCovers();
/* 189:147 */       return;
/* 190:    */     }
/* 191:149 */     super.onHarvestPart(player, part);
/* 192:    */   }
/* 193:    */   
/* 194:    */   public float getPartStrength(qx player, int part)
/* 195:    */   {
/* 196:153 */     BlockExtended bl = RedPowerLogic.blockLogic;
/* 197:154 */     if (part == this.Rotation >> 2) {
/* 198:155 */       return player.getCurrentPlayerStrVsBlock(bl, 0) / (bl.getHardness() * 30.0F);
/* 199:    */     }
/* 200:158 */     return super.getPartStrength(player, part);
/* 201:    */   }
/* 202:    */   
/* 203:    */   public void setPartBounds(BlockMultipart bl, int part)
/* 204:    */   {
/* 205:162 */     if (part != this.Rotation >> 2)
/* 206:    */     {
/* 207:163 */       super.setPartBounds(bl, part);
/* 208:164 */       return;
/* 209:    */     }
/* 210:166 */     switch (part)
/* 211:    */     {
/* 212:    */     case 0: 
/* 213:168 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/* 214:169 */       break;
/* 215:    */     case 1: 
/* 216:171 */       bl.a(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 217:172 */       break;
/* 218:    */     case 2: 
/* 219:174 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
/* 220:175 */       break;
/* 221:    */     case 3: 
/* 222:177 */       bl.a(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
/* 223:178 */       break;
/* 224:    */     case 4: 
/* 225:180 */       bl.a(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 1.0F);
/* 226:181 */       break;
/* 227:    */     case 5: 
/* 228:183 */       bl.a(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 229:    */     }
/* 230:    */   }
/* 231:    */   
/* 232:    */   public int getPartsMask()
/* 233:    */   {
/* 234:189 */     int pm = 1 << (this.Rotation >> 2);
/* 235:190 */     if (this.Cover != 255) {
/* 236:190 */       pm |= 1 << (this.Rotation >> 2 ^ 0x1);
/* 237:    */     }
/* 238:191 */     return pm;
/* 239:    */   }
/* 240:    */   
/* 241:    */   public int getSolidPartsMask()
/* 242:    */   {
/* 243:195 */     return getPartsMask();
/* 244:    */   }
/* 245:    */   
/* 246:    */   public boolean isBlockStrongPoweringTo(int l)
/* 247:    */   {
/* 248:201 */     return (getPoweringMask(0) & RedPowerLib.getConDirMask(l ^ 0x1)) > 0;
/* 249:    */   }
/* 250:    */   
/* 251:    */   public boolean isBlockWeakPoweringTo(int l)
/* 252:    */   {
/* 253:206 */     return (getPoweringMask(0) & RedPowerLib.getConDirMask(l ^ 0x1)) > 0;
/* 254:    */   }
/* 255:    */   
/* 256:    */   public int getBlockID()
/* 257:    */   {
/* 258:211 */     return RedPowerLogic.blockLogic.cm;
/* 259:    */   }
/* 260:    */   
/* 261:    */   public int getExtendedMetadata()
/* 262:    */   {
/* 263:215 */     return this.SubId;
/* 264:    */   }
/* 265:    */   
/* 266:    */   public void setExtendedMetadata(int md)
/* 267:    */   {
/* 268:219 */     this.SubId = md;
/* 269:    */   }
/* 270:    */   
/* 271:    */   public void playSound(String name, float f, float f2, boolean always)
/* 272:    */   {
/* 273:225 */     if ((!always) && (!RedPowerLogic.EnableSounds)) {
/* 274:226 */       return;
/* 275:    */     }
/* 276:227 */     this.k.a(this.l + 0.5F, this.m + 0.5F, this.n + 0.5F, name, f, f2);
/* 277:    */   }
/* 278:    */   
/* 279:    */   public void initSubType(int st)
/* 280:    */   {
/* 281:235 */     this.SubId = st;
/* 282:237 */     if (CoreLib.isClient(this.k)) {
/* 283:237 */       return;
/* 284:    */     }
/* 285:238 */     if (getLightValue() != 9) {
/* 286:239 */       this.k.z(this.l, this.m, this.n);
/* 287:    */     }
/* 288:    */   }
/* 289:    */   
/* 290:    */   public int getLightValue()
/* 291:    */   {
/* 292:243 */     return 9;
/* 293:    */   }
/* 294:    */   
/* 295:    */   public byte[] getFramePacket()
/* 296:    */   {
/* 297:249 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 298:250 */     pkt.subId = 7;
/* 299:251 */     writeToPacket(pkt);
/* 300:252 */     pkt.headout.write(pkt.subId);
/* 301:253 */     return pkt.toByteArray();
/* 302:    */   }
/* 303:    */   
/* 304:    */   public void handleFramePacket(byte[] ba)
/* 305:    */     throws IOException
/* 306:    */   {
/* 307:257 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 308:258 */     pkt.subId = pkt.getByte();
/* 309:259 */     readFromPacket(pkt);
/* 310:    */   }
/* 311:    */   
/* 312:    */   public void onFrameRefresh(ym iba) {}
/* 313:    */   
/* 314:    */   public void onFramePickup(ym iba) {}
/* 315:    */   
/* 316:    */   public void onFrameDrop() {}
/* 317:    */   
/* 318:    */   public void a(bq tag)
/* 319:    */   {
/* 320:269 */     super.a(tag);
/* 321:    */     
/* 322:271 */     this.SubId = (tag.c("sid") & 0xFF);
/* 323:272 */     this.Rotation = (tag.c("rot") & 0xFF);
/* 324:273 */     int ps = tag.c("ps") & 0xFF;
/* 325:274 */     this.Deadmap = (tag.c("dm") & 0xFF);
/* 326:275 */     this.Cover = (tag.c("cov") & 0xFF);
/* 327:276 */     this.PowerState = (ps & 0xF);
/* 328:277 */     this.Powered = ((ps & 0x10) > 0);
/* 329:278 */     this.Disabled = ((ps & 0x20) > 0);
/* 330:279 */     this.Active = ((ps & 0x40) > 0);
/* 331:    */   }
/* 332:    */   
/* 333:    */   public void b(bq tag)
/* 334:    */   {
/* 335:283 */     super.b(tag);
/* 336:284 */     tag.a("sid", (byte)this.SubId);
/* 337:285 */     tag.a("rot", (byte)this.Rotation);
/* 338:286 */     int ps = this.PowerState | (this.Powered ? 16 : 0) | (this.Disabled ? 32 : 0) | (this.Active ? 64 : 0);
/* 339:    */     
/* 340:288 */     tag.a("ps", (byte)ps);
/* 341:289 */     tag.a("dm", (byte)this.Deadmap);
/* 342:290 */     tag.a("cov", (byte)this.Cover);
/* 343:    */   }
/* 344:    */   
/* 345:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 346:    */     throws IOException
/* 347:    */   {
/* 348:295 */     this.SubId = pkt.getByte();
/* 349:296 */     this.Rotation = pkt.getByte();
/* 350:297 */     int ps = pkt.getByte();
/* 351:300 */     if (CoreLib.isClient(this.k))
/* 352:    */     {
/* 353:301 */       this.PowerState = (ps & 0xF);
/* 354:302 */       this.Powered = ((ps & 0x10) > 0);
/* 355:303 */       this.Disabled = ((ps & 0x20) > 0);
/* 356:304 */       this.Active = ((ps & 0x40) > 0);
/* 357:    */     }
/* 358:306 */     if ((ps & 0x80) > 0) {
/* 359:307 */       this.Deadmap = pkt.getByte();
/* 360:    */     } else {
/* 361:308 */       this.Deadmap = 0;
/* 362:    */     }
/* 363:309 */     this.Cover = pkt.getByte();
/* 364:    */   }
/* 365:    */   
/* 366:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 367:    */   {
/* 368:313 */     pkt.addByte(this.SubId);
/* 369:314 */     pkt.addByte(this.Rotation);
/* 370:315 */     int ps = this.PowerState | (this.Powered ? 16 : 0) | (this.Disabled ? 32 : 0) | (this.Active ? 64 : 0) | (this.Deadmap > 0 ? 128 : 0);
/* 371:    */     
/* 372:317 */     pkt.addByte(ps);
/* 373:318 */     if (this.Deadmap > 0) {
/* 374:318 */       pkt.addByte(this.Deadmap);
/* 375:    */     }
/* 376:319 */     pkt.addByte(this.Cover);
/* 377:    */   }
/* 378:    */   
/* 379:    */   public ef l()
/* 380:    */   {
/* 381:323 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 382:324 */     packet.subId = 1;
/* 383:325 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 384:326 */     packet.zCoord = this.n;
/* 385:327 */     writeToPacket(packet);
/* 386:328 */     packet.encode();
/* 387:329 */     return packet;
/* 388:    */   }
/* 389:    */   
/* 390:    */   public void handlePacket(Packet211TileDesc packet)
/* 391:    */   {
/* 392:    */     try
/* 393:    */     {
/* 394:334 */       readFromPacket(packet);
/* 395:    */     }
/* 396:    */     catch (IOException e) {}
/* 397:336 */     this.k.i(this.l, this.m, this.n);
/* 398:    */   }
/* 399:    */   
/* 400:339 */   public int SubId = 0;
/* 401:340 */   public int Rotation = 0;
/* 402:341 */   public boolean Powered = false;
/* 403:341 */   public boolean Disabled = false;
/* 404:341 */   public boolean Active = false;
/* 405:342 */   public int PowerState = 0;
/* 406:343 */   public int Deadmap = 0;
/* 407:344 */   public int Cover = 255;
/* 408:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.TileLogic
 * JD-Core Version:    0.7.0.1
 */