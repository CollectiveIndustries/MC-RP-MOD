/*   1:    */ package com.eloraam.redpower.wiring;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerBase;
/*   5:    */ import com.eloraam.redpower.base.BlockMicro;
/*   6:    */ import com.eloraam.redpower.core.BlockExtended;
/*   7:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   8:    */ import com.eloraam.redpower.core.CoreLib;
/*   9:    */ import com.eloraam.redpower.core.CoverLib;
/*  10:    */ import com.eloraam.redpower.core.IWiring;
/*  11:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  12:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  13:    */ import com.eloraam.redpower.core.TileCovered;
/*  14:    */ import java.io.IOException;
/*  15:    */ import java.util.ArrayList;
/*  16:    */ import java.util.Arrays;
/*  17:    */ import qx;
/*  18:    */ import ur;
/*  19:    */ import yc;
/*  20:    */ import ym;
/*  21:    */ 
/*  22:    */ public abstract class TileWiring
/*  23:    */   extends TileCovered
/*  24:    */   implements IWiring
/*  25:    */ {
/*  26:    */   public float getWireHeight()
/*  27:    */   {
/*  28: 27 */     return 0.125F;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void uncache0()
/*  32:    */   {
/*  33: 31 */     this.EConMask = -1;this.EConEMask = -1;this.ConMask = -1;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void uncache()
/*  37:    */   {
/*  38: 34 */     if ((this.ConaMask >= 0) || (this.EConMask >= 0) || (this.ConMask >= 0)) {
/*  39: 35 */       this.k.i(this.l, this.m, this.n);
/*  40:    */     }
/*  41: 36 */     this.ConaMask = -1;this.EConMask = -1;this.EConEMask = -1;this.ConMask = -1;
/*  42:    */   }
/*  43:    */   
/*  44:    */   private static int stripBlockConMask(int side)
/*  45:    */   {
/*  46: 42 */     switch (side)
/*  47:    */     {
/*  48:    */     case 0: 
/*  49: 43 */       return 257;
/*  50:    */     case 1: 
/*  51: 44 */       return 4098;
/*  52:    */     case 2: 
/*  53: 45 */       return 65540;
/*  54:    */     case 3: 
/*  55: 46 */       return 1048584;
/*  56:    */     case 4: 
/*  57: 47 */       return 263168;
/*  58:    */     case 5: 
/*  59: 48 */       return 540672;
/*  60:    */     case 6: 
/*  61: 49 */       return 4196352;
/*  62:    */     case 7: 
/*  63: 50 */       return 8421376;
/*  64:    */     case 8: 
/*  65: 51 */       return 528;
/*  66:    */     case 9: 
/*  67: 52 */       return 8224;
/*  68:    */     case 10: 
/*  69: 53 */       return 131136;
/*  70:    */     }
/*  71: 54 */     return 2097280;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public int getConnectableMask()
/*  75:    */   {
/*  76: 59 */     if (this.ConaMask >= 0) {
/*  77: 59 */       return this.ConaMask;
/*  78:    */     }
/*  79: 62 */     int tr = 0;
/*  80: 64 */     if ((this.ConSides & 0x1) > 0) {
/*  81: 64 */       tr |= 0xF;
/*  82:    */     }
/*  83: 65 */     if ((this.ConSides & 0x2) > 0) {
/*  84: 65 */       tr |= 0xF0;
/*  85:    */     }
/*  86: 66 */     if ((this.ConSides & 0x4) > 0) {
/*  87: 66 */       tr |= 0xF00;
/*  88:    */     }
/*  89: 67 */     if ((this.ConSides & 0x8) > 0) {
/*  90: 67 */       tr |= 0xF000;
/*  91:    */     }
/*  92: 68 */     if ((this.ConSides & 0x10) > 0) {
/*  93: 68 */       tr |= 0xF0000;
/*  94:    */     }
/*  95: 69 */     if ((this.ConSides & 0x20) > 0) {
/*  96: 69 */       tr |= 0xF00000;
/*  97:    */     }
/*  98: 71 */     if ((this.CoverSides & 0x1) > 0) {
/*  99: 71 */       tr &= 0xFFEEEEFF;
/* 100:    */     }
/* 101: 72 */     if ((this.CoverSides & 0x2) > 0) {
/* 102: 72 */       tr &= 0xFFDDDDFF;
/* 103:    */     }
/* 104: 73 */     if ((this.CoverSides & 0x4) > 0) {
/* 105: 73 */       tr &= 0xFFBBFFEE;
/* 106:    */     }
/* 107: 74 */     if ((this.CoverSides & 0x8) > 0) {
/* 108: 74 */       tr &= 0xFF77FFDD;
/* 109:    */     }
/* 110: 75 */     if ((this.CoverSides & 0x10) > 0) {
/* 111: 75 */       tr &= 0xFFFFBBBB;
/* 112:    */     }
/* 113: 76 */     if ((this.CoverSides & 0x20) > 0) {
/* 114: 76 */       tr &= 0xFFFF7777;
/* 115:    */     }
/* 116: 78 */     for (int i = 0; i < 12; i++) {
/* 117: 79 */       if ((this.CoverSides & 16384 << i) > 0) {
/* 118: 80 */         tr &= (stripBlockConMask(i) ^ 0xFFFFFFFF);
/* 119:    */       }
/* 120:    */     }
/* 121: 83 */     if ((this.ConSides & 0x40) > 0)
/* 122:    */     {
/* 123: 84 */       tr |= 0x3F000000;
/* 124: 86 */       for (i = 0; i < 6; i++) {
/* 125: 87 */         if ((this.CoverSides & 1 << i) > 0)
/* 126:    */         {
/* 127: 88 */           int j = this.Covers[i] >> 8;
/* 128: 89 */           if (j < 3) {
/* 129: 89 */             tr &= (1 << i + 24 ^ 0xFFFFFFFF);
/* 130:    */           }
/* 131: 90 */           if (j == 5) {
/* 132: 90 */             tr &= 3 << (i & 0xFFFFFFFE) + 24;
/* 133:    */           }
/* 134:    */         }
/* 135:    */       }
/* 136:    */     }
/* 137: 95 */     this.ConaMask = tr;
/* 138: 96 */     return tr;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public int getConnectionMask()
/* 142:    */   {
/* 143:100 */     if (this.ConMask >= 0) {
/* 144:100 */       return this.ConMask;
/* 145:    */     }
/* 146:101 */     this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 147:    */     
/* 148:103 */     return this.ConMask;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public int getExtConnectionMask()
/* 152:    */   {
/* 153:107 */     if (this.EConMask >= 0) {
/* 154:107 */       return this.EConMask;
/* 155:    */     }
/* 156:108 */     this.EConMask = RedPowerLib.getExtConnections(this.k, this, this.l, this.m, this.n);
/* 157:    */     
/* 158:110 */     this.EConEMask = RedPowerLib.getExtConnectionExtras(this.k, this, this.l, this.m, this.n);
/* 159:    */     
/* 160:112 */     return this.EConMask;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public int getCornerPowerMode()
/* 164:    */   {
/* 165:116 */     return 1;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public void onFrameRefresh(ym iba)
/* 169:    */   {
/* 170:122 */     if (this.ConMask < 0) {
/* 171:123 */       this.ConMask = RedPowerLib.getConnections(iba, this, this.l, this.m, this.n);
/* 172:    */     }
/* 173:126 */     if (this.EConMask < 0)
/* 174:    */     {
/* 175:127 */       this.EConMask = RedPowerLib.getExtConnections(iba, this, this.l, this.m, this.n);
/* 176:    */       
/* 177:129 */       this.EConEMask = RedPowerLib.getExtConnectionExtras(iba, this, this.l, this.m, this.n);
/* 178:    */     }
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void onBlockNeighborChange(int l)
/* 182:    */   {
/* 183:137 */     if ((this.EConMask >= 0) || (this.ConMask >= 0)) {
/* 184:138 */       this.k.i(this.l, this.m, this.n);
/* 185:    */     }
/* 186:139 */     this.ConMask = -1;this.EConMask = -1;this.EConEMask = -1;
/* 187:140 */     refreshBlockSupport();
/* 188:141 */     RedPowerLib.updateCurrent(this.k, this.l, this.m, this.n);
/* 189:    */   }
/* 190:    */   
/* 191:    */   public int getExtendedMetadata()
/* 192:    */   {
/* 193:144 */     return this.Metadata;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void setExtendedMetadata(int md)
/* 197:    */   {
/* 198:145 */     this.Metadata = md;
/* 199:    */   }
/* 200:    */   
/* 201:    */   public boolean canAddCover(int side, int cover)
/* 202:    */   {
/* 203:150 */     if ((side < 6) && ((this.ConSides & 1 << side) > 0)) {
/* 204:150 */       return false;
/* 205:    */     }
/* 206:151 */     if ((this.CoverSides & 1 << side) > 0) {
/* 207:151 */       return false;
/* 208:    */     }
/* 209:153 */     short[] test = Arrays.copyOf(this.Covers, 29);
/* 210:154 */     test[side] = ((short)cover);
/* 211:155 */     if (!CoverLib.checkPlacement(this.CoverSides | 1 << side, test, this.ConSides, (this.ConSides & 0x40) > 0)) {
/* 212:157 */       return false;
/* 213:    */     }
/* 214:158 */     return true;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public boolean tryAddCover(int side, int cover)
/* 218:    */   {
/* 219:162 */     if (!canAddCover(side, cover)) {
/* 220:163 */       return false;
/* 221:    */     }
/* 222:164 */     this.CoverSides |= 1 << side;
/* 223:165 */     this.Covers[side] = ((short)cover);
/* 224:166 */     uncache();
/* 225:167 */     updateBlockChange();
/* 226:    */     
/* 227:169 */     return true;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public int tryRemoveCover(int side)
/* 231:    */   {
/* 232:173 */     int tr = super.tryRemoveCover(side);
/* 233:174 */     if (tr < 0) {
/* 234:174 */       return -1;
/* 235:    */     }
/* 236:175 */     uncache();
/* 237:176 */     updateBlockChange();
/* 238:177 */     return tr;
/* 239:    */   }
/* 240:    */   
/* 241:    */   public boolean blockEmpty()
/* 242:    */   {
/* 243:183 */     return (this.CoverSides == 0) && (this.ConSides == 0);
/* 244:    */   }
/* 245:    */   
/* 246:    */   public void addHarvestContents(ArrayList ist)
/* 247:    */   {
/* 248:187 */     super.addHarvestContents(ist);
/* 249:189 */     for (int s = 0; s < 6; s++) {
/* 250:190 */       if ((this.ConSides & 1 << s) != 0) {
/* 251:191 */         ist.add(new ur(RedPowerBase.blockMicro.cm, 1, getExtendedID() * 256 + this.Metadata));
/* 252:    */       }
/* 253:    */     }
/* 254:195 */     if ((this.ConSides & 0x40) > 0)
/* 255:    */     {
/* 256:196 */       int td = 16384 + this.CenterPost;
/* 257:197 */       if (getExtendedID() == 3) {
/* 258:197 */         td += 256;
/* 259:    */       }
/* 260:198 */       if (getExtendedID() == 5) {
/* 261:198 */         td += 512;
/* 262:    */       }
/* 263:199 */       ist.add(new ur(RedPowerBase.blockMicro.cm, 1, td));
/* 264:    */     }
/* 265:    */   }
/* 266:    */   
/* 267:    */   public int getPartsMask()
/* 268:    */   {
/* 269:205 */     return this.CoverSides | this.ConSides & 0x3F | (this.ConSides & 0x40) << 23;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public int getSolidPartsMask()
/* 273:    */   {
/* 274:210 */     return this.CoverSides | (this.ConSides & 0x40) << 23;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public boolean refreshBlockSupport()
/* 278:    */   {
/* 279:215 */     boolean all = false;
/* 280:216 */     int s = this.ConSides & 0x3F;
/* 281:217 */     if ((s == 3) || (s == 12) || (s == 48)) {
/* 282:217 */       all = true;
/* 283:    */     }
/* 284:218 */     for (s = 0; s < 6; s++) {
/* 285:219 */       if (((this.ConSides & 1 << s) != 0) && (
/* 286:220 */         (all) || (!RedPowerLib.canSupportWire(this.k, this.l, this.m, this.n, s))))
/* 287:    */       {
/* 288:223 */         uncache();
/* 289:224 */         CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/* 290:225 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(RedPowerBase.blockMicro.cm, 1, getExtendedID() * 256 + this.Metadata));
/* 291:    */         
/* 292:    */ 
/* 293:    */ 
/* 294:229 */         this.ConSides &= (1 << s ^ 0xFFFFFFFF);
/* 295:    */       }
/* 296:    */     }
/* 297:231 */     if (this.ConSides == 0)
/* 298:    */     {
/* 299:232 */       if (this.CoverSides > 0) {
/* 300:232 */         replaceWithCovers();
/* 301:    */       } else {
/* 302:233 */         deleteBlock();
/* 303:    */       }
/* 304:234 */       return false;
/* 305:    */     }
/* 306:236 */     return true;
/* 307:    */   }
/* 308:    */   
/* 309:    */   public void onHarvestPart(qx player, int part)
/* 310:    */   {
/* 311:240 */     boolean change = false;
/* 312:241 */     if ((part == 29) && ((this.ConSides & 0x40) > 0))
/* 313:    */     {
/* 314:242 */       int td = 16384 + this.CenterPost;
/* 315:243 */       if (getExtendedID() == 3) {
/* 316:243 */         td += 256;
/* 317:    */       }
/* 318:244 */       if (getExtendedID() == 5) {
/* 319:244 */         td += 512;
/* 320:    */       }
/* 321:245 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(RedPowerBase.blockMicro.cm, 1, td));
/* 322:    */       
/* 323:    */ 
/* 324:    */ 
/* 325:249 */       this.ConSides &= 0x3F;
/* 326:    */     }
/* 327:250 */     else if ((this.ConSides & 1 << part) > 0)
/* 328:    */     {
/* 329:251 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(RedPowerBase.blockMicro.cm, 1, getExtendedID() * 256 + this.Metadata));
/* 330:    */       
/* 331:    */ 
/* 332:    */ 
/* 333:255 */       this.ConSides &= (1 << part ^ 0xFFFFFFFF);
/* 334:    */     }
/* 335:    */     else
/* 336:    */     {
/* 337:257 */       super.onHarvestPart(player, part);
/* 338:258 */       return;
/* 339:    */     }
/* 340:260 */     uncache();
/* 341:261 */     if (this.ConSides == 0) {
/* 342:262 */       if (this.CoverSides > 0) {
/* 343:262 */         replaceWithCovers();
/* 344:    */       } else {
/* 345:263 */         deleteBlock();
/* 346:    */       }
/* 347:    */     }
/* 348:265 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/* 349:266 */     RedPowerLib.updateIndirectNeighbors(this.k, this.l, this.m, this.n, RedPowerBase.blockMicro.cm);
/* 350:    */   }
/* 351:    */   
/* 352:    */   public float getPartStrength(qx player, int part)
/* 353:    */   {
/* 354:272 */     BlockExtended bl = RedPowerBase.blockMicro;
/* 355:273 */     if ((part == 29) && ((this.ConSides & 0x40) > 0)) {
/* 356:274 */       return player.getCurrentPlayerStrVsBlock(bl, 0) / (bl.getHardness() * 30.0F);
/* 357:    */     }
/* 358:277 */     if ((this.ConSides & 1 << part) > 0) {
/* 359:278 */       return player.getCurrentPlayerStrVsBlock(bl, 0) / (bl.getHardness() * 30.0F);
/* 360:    */     }
/* 361:281 */     return super.getPartStrength(player, part);
/* 362:    */   }
/* 363:    */   
/* 364:    */   public void setPartBounds(BlockMultipart bl, int part)
/* 365:    */   {
/* 366:285 */     if (part == 29)
/* 367:    */     {
/* 368:286 */       if ((this.ConSides & 0x40) == 0) {
/* 369:287 */         super.setPartBounds(bl, part);
/* 370:    */       }
/* 371:    */     }
/* 372:290 */     else if ((this.ConSides & 1 << part) == 0)
/* 373:    */     {
/* 374:291 */       super.setPartBounds(bl, part);
/* 375:292 */       return;
/* 376:    */     }
/* 377:294 */     float wh = getWireHeight();
/* 378:295 */     switch (part)
/* 379:    */     {
/* 380:    */     case 0: 
/* 381:297 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, wh, 1.0F);
/* 382:298 */       break;
/* 383:    */     case 1: 
/* 384:300 */       bl.a(0.0F, 1.0F - wh, 0.0F, 1.0F, 1.0F, 1.0F);
/* 385:301 */       break;
/* 386:    */     case 2: 
/* 387:303 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, wh);
/* 388:304 */       break;
/* 389:    */     case 3: 
/* 390:306 */       bl.a(0.0F, 0.0F, 1.0F - wh, 1.0F, 1.0F, 1.0F);
/* 391:307 */       break;
/* 392:    */     case 4: 
/* 393:309 */       bl.a(0.0F, 0.0F, 0.0F, wh, 1.0F, 1.0F);
/* 394:310 */       break;
/* 395:    */     case 5: 
/* 396:312 */       bl.a(1.0F - wh, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 397:313 */       break;
/* 398:    */     case 29: 
/* 399:315 */       bl.a(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/* 400:    */     }
/* 401:    */   }
/* 402:    */   
/* 403:    */   public void a(bq nbttagcompound)
/* 404:    */   {
/* 405:323 */     super.a(nbttagcompound);
/* 406:324 */     this.ConSides = (nbttagcompound.c("cons") & 0xFF);
/* 407:325 */     this.Metadata = (nbttagcompound.c("md") & 0xFF);
/* 408:326 */     this.CenterPost = ((short)(nbttagcompound.d("post") & 0xFF));
/* 409:    */   }
/* 410:    */   
/* 411:    */   public void b(bq nbttagcompound)
/* 412:    */   {
/* 413:330 */     super.b(nbttagcompound);
/* 414:331 */     nbttagcompound.a("cons", (byte)this.ConSides);
/* 415:332 */     nbttagcompound.a("md", (byte)this.Metadata);
/* 416:333 */     nbttagcompound.a("post", this.CenterPost);
/* 417:    */   }
/* 418:    */   
/* 419:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 420:    */     throws IOException
/* 421:    */   {
/* 422:338 */     super.readFromPacket(pkt);
/* 423:339 */     this.Metadata = pkt.getByte();
/* 424:340 */     this.ConSides = pkt.getByte();
/* 425:341 */     if ((this.ConSides & 0x40) > 0) {
/* 426:341 */       this.CenterPost = ((short)(int)pkt.getUVLC());
/* 427:    */     }
/* 428:342 */     this.ConaMask = -1;this.EConMask = -1;this.EConEMask = -1;this.ConMask = -1;
/* 429:    */   }
/* 430:    */   
/* 431:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 432:    */   {
/* 433:346 */     super.writeToPacket(pkt);
/* 434:347 */     pkt.addByte(this.Metadata);
/* 435:348 */     pkt.addByte(this.ConSides);
/* 436:349 */     if ((this.ConSides & 0x40) > 0) {
/* 437:349 */       pkt.addUVLC(this.CenterPost);
/* 438:    */     }
/* 439:    */   }
/* 440:    */   
/* 441:352 */   public int ConSides = 0;
/* 442:353 */   public int Metadata = 0;
/* 443:354 */   public short CenterPost = 0;
/* 444:355 */   public int ConMask = -1;
/* 445:355 */   public int EConMask = -1;
/* 446:355 */   public int EConEMask = -1;
/* 447:355 */   public int ConaMask = -1;
/* 448:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.TileWiring
 * JD-Core Version:    0.7.0.1
 */