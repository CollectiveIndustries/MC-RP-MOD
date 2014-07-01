/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerLogic;
/*   5:    */ import com.eloraam.redpower.core.CoreLib;
/*   6:    */ import com.eloraam.redpower.core.MathLib;
/*   7:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   8:    */ import com.eloraam.redpower.core.Quat;
/*   9:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  10:    */ import com.eloraam.redpower.core.Vector3;
/*  11:    */ import java.io.IOException;
/*  12:    */ import qx;
/*  13:    */ import yc;
/*  14:    */ 
/*  15:    */ public class TileLogicPointer
/*  16:    */   extends TileLogic
/*  17:    */   implements IPointerTile
/*  18:    */ {
/*  19:    */   public void initSubType(int st)
/*  20:    */   {
/*  21: 22 */     super.initSubType(st);
/*  22: 24 */     switch (st)
/*  23:    */     {
/*  24:    */     case 0: 
/*  25: 26 */       this.interval = 38L;
/*  26: 27 */       break;
/*  27:    */     case 2: 
/*  28: 29 */       this.Disabled = true;
/*  29:    */     }
/*  30:    */   }
/*  31:    */   
/*  32:    */   public int getPartMaxRotation(int part, boolean sec)
/*  33:    */   {
/*  34: 37 */     if ((sec) && (
/*  35: 38 */       (this.SubId == 1) || (this.SubId == 2))) {
/*  36: 38 */       return 1;
/*  37:    */     }
/*  38: 40 */     return super.getPartMaxRotation(part, sec);
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getPartRotation(int part, boolean sec)
/*  42:    */   {
/*  43: 44 */     if ((sec) && (
/*  44: 45 */       (this.SubId == 1) || (this.SubId == 2))) {
/*  45: 45 */       return this.Deadmap;
/*  46:    */     }
/*  47: 47 */     return super.getPartRotation(part, sec);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void setPartRotation(int part, boolean sec, int rot)
/*  51:    */   {
/*  52: 51 */     if ((sec) && (
/*  53: 52 */       (this.SubId == 1) || (this.SubId == 2)))
/*  54:    */     {
/*  55: 53 */       this.Deadmap = rot;
/*  56: 54 */       updateBlockChange();
/*  57: 55 */       return;
/*  58:    */     }
/*  59: 58 */     super.setPartRotation(part, sec, rot);
/*  60:    */   }
/*  61:    */   
/*  62:    */   private void timerChange()
/*  63:    */   {
/*  64: 65 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 7, this.Rotation, 0);
/*  65: 67 */     if (ps != this.PowerState) {
/*  66: 67 */       updateBlock();
/*  67:    */     }
/*  68: 68 */     this.PowerState = ps;
/*  69: 70 */     if (this.Powered)
/*  70:    */     {
/*  71: 71 */       if (!this.Disabled) {
/*  72: 71 */         return;
/*  73:    */       }
/*  74: 72 */       if (ps > 0) {
/*  75: 72 */         return;
/*  76:    */       }
/*  77: 73 */       this.Powered = false;
/*  78: 74 */       this.Disabled = false;
/*  79: 75 */       this.timestart = this.k.G();
/*  80: 76 */       updateBlock();
/*  81:    */     }
/*  82: 77 */     else if (this.Disabled)
/*  83:    */     {
/*  84: 78 */       if (ps > 0) {
/*  85: 78 */         return;
/*  86:    */       }
/*  87: 79 */       this.timestart = this.k.G();
/*  88: 80 */       this.Disabled = false;
/*  89: 81 */       updateBlock();
/*  90:    */     }
/*  91:    */     else
/*  92:    */     {
/*  93: 83 */       if (ps == 0) {
/*  94: 83 */         return;
/*  95:    */       }
/*  96: 84 */       this.Disabled = true;
/*  97: 85 */       updateBlock();
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   private void timerTick()
/* 102:    */   {
/* 103: 91 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 7, this.Rotation, 0);
/* 104: 93 */     if (ps != this.PowerState) {
/* 105: 93 */       updateBlock();
/* 106:    */     }
/* 107: 94 */     this.PowerState = ps;
/* 108: 96 */     if (this.Powered)
/* 109:    */     {
/* 110: 97 */       if (this.Disabled)
/* 111:    */       {
/* 112: 98 */         if (ps > 0)
/* 113:    */         {
/* 114: 99 */           this.Powered = false;
/* 115:100 */           updateBlock();
/* 116:101 */           return;
/* 117:    */         }
/* 118:103 */         this.Disabled = false;this.Powered = false;
/* 119:104 */         this.timestart = this.k.G();
/* 120:105 */         updateBlock();
/* 121:106 */         return;
/* 122:    */       }
/* 123:108 */       if (ps == 0)
/* 124:    */       {
/* 125:109 */         this.Powered = false;
/* 126:    */       }
/* 127:    */       else
/* 128:    */       {
/* 129:111 */         this.Disabled = true;
/* 130:112 */         scheduleTick(2);
/* 131:    */       }
/* 132:114 */       this.timestart = this.k.G();
/* 133:115 */       updateBlockChange();
/* 134:    */     }
/* 135:116 */     else if (this.Disabled)
/* 136:    */     {
/* 137:117 */       if (ps > 0) {
/* 138:117 */         return;
/* 139:    */       }
/* 140:118 */       this.timestart = this.k.G();
/* 141:119 */       this.Disabled = false;
/* 142:120 */       updateBlock();
/* 143:    */     }
/* 144:    */     else
/* 145:    */     {
/* 146:122 */       if (ps == 0) {
/* 147:122 */         return;
/* 148:    */       }
/* 149:123 */       this.Disabled = true;
/* 150:124 */       updateBlock();
/* 151:    */     }
/* 152:    */   }
/* 153:    */   
/* 154:    */   private void timerUpdate()
/* 155:    */   {
/* 156:129 */     if (CoreLib.isClient(this.k)) {
/* 157:129 */       return;
/* 158:    */     }
/* 159:130 */     if ((this.Powered) || (this.Disabled)) {
/* 160:130 */       return;
/* 161:    */     }
/* 162:132 */     long wt = this.k.G();
/* 163:133 */     if (this.interval < 2L) {
/* 164:133 */       this.interval = 2L;
/* 165:    */     }
/* 166:134 */     if (this.timestart > wt) {
/* 167:134 */       this.timestart = wt;
/* 168:    */     }
/* 169:136 */     if (this.timestart + this.interval <= wt)
/* 170:    */     {
/* 171:137 */       playSound("random.click", 0.3F, 0.5F, false);
/* 172:138 */       this.Powered = true;
/* 173:139 */       scheduleTick(2);
/* 174:140 */       updateBlockChange();
/* 175:    */     }
/* 176:    */   }
/* 177:    */   
/* 178:    */   private void sequencerUpdate()
/* 179:    */   {
/* 180:147 */     long wt = this.k.G() + 6000L;
/* 181:148 */     float f = (float)wt / (float)(this.interval * 4L);
/* 182:149 */     int i = (int)Math.floor(f * 4.0F);
/* 183:150 */     if (this.Deadmap == 1) {
/* 184:150 */       i = 3 - i & 0x3;
/* 185:    */     } else {
/* 186:151 */       i = i + 3 & 0x3;
/* 187:    */     }
/* 188:152 */     if ((this.PowerState != i) && (!CoreLib.isClient(this.k)))
/* 189:    */     {
/* 190:153 */       playSound("random.click", 0.3F, 0.5F, false);
/* 191:154 */       this.PowerState = i;
/* 192:155 */       updateBlockChange();
/* 193:    */     }
/* 194:    */   }
/* 195:    */   
/* 196:    */   private void stateCellChange()
/* 197:    */   {
/* 198:164 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 7, this.Rotation, 0);
/* 199:166 */     if (ps != this.PowerState) {
/* 200:166 */       updateBlock();
/* 201:    */     }
/* 202:167 */     this.PowerState = ps;
/* 203:    */     
/* 204:169 */     boolean ps3 = (ps & 0x3) > 0;
/* 205:170 */     if ((this.Disabled) && (!ps3))
/* 206:    */     {
/* 207:171 */       this.Disabled = false;
/* 208:172 */       this.timestart = this.k.G();
/* 209:173 */       updateBlock();
/* 210:    */     }
/* 211:174 */     else if ((!this.Disabled) && (ps3))
/* 212:    */     {
/* 213:175 */       this.Disabled = true;
/* 214:176 */       updateBlock();
/* 215:    */     }
/* 216:178 */     if ((!this.Active) && (!this.Powered) && ((ps & 0x2) > 0))
/* 217:    */     {
/* 218:179 */       this.Powered = true;
/* 219:180 */       updateBlock();
/* 220:181 */       scheduleTick(2);
/* 221:    */     }
/* 222:    */   }
/* 223:    */   
/* 224:    */   private void stateCellTick()
/* 225:    */   {
/* 226:186 */     if ((!this.Active) && (this.Powered))
/* 227:    */     {
/* 228:187 */       this.Powered = false;this.Active = true;
/* 229:188 */       this.timestart = this.k.G();
/* 230:189 */       updateBlockChange();
/* 231:    */     }
/* 232:190 */     else if ((this.Active) && (this.Powered))
/* 233:    */     {
/* 234:191 */       this.Powered = false;this.Active = false;
/* 235:192 */       updateBlockChange();
/* 236:    */     }
/* 237:    */   }
/* 238:    */   
/* 239:    */   private void stateCellUpdate()
/* 240:    */   {
/* 241:197 */     if (CoreLib.isClient(this.k)) {
/* 242:197 */       return;
/* 243:    */     }
/* 244:199 */     if ((!this.Active) || (this.Powered) || (this.Disabled)) {
/* 245:199 */       return;
/* 246:    */     }
/* 247:201 */     long wt = this.k.G();
/* 248:202 */     if (this.interval < 2L) {
/* 249:202 */       this.interval = 2L;
/* 250:    */     }
/* 251:203 */     if (this.timestart > wt) {
/* 252:203 */       this.timestart = wt;
/* 253:    */     }
/* 254:204 */     if (this.timestart + this.interval <= wt)
/* 255:    */     {
/* 256:205 */       playSound("random.click", 0.3F, 0.5F, false);
/* 257:    */       
/* 258:207 */       this.Powered = true;
/* 259:208 */       scheduleTick(2);
/* 260:209 */       updateBlockChange();
/* 261:    */     }
/* 262:    */   }
/* 263:    */   
/* 264:    */   public void onBlockNeighborChange(int l)
/* 265:    */   {
/* 266:216 */     if (tryDropBlock()) {
/* 267:216 */       return;
/* 268:    */     }
/* 269:217 */     switch (this.SubId)
/* 270:    */     {
/* 271:    */     case 0: 
/* 272:219 */       timerChange();
/* 273:220 */       break;
/* 274:    */     case 2: 
/* 275:222 */       stateCellChange();
/* 276:    */     }
/* 277:    */   }
/* 278:    */   
/* 279:    */   public void onTileTick()
/* 280:    */   {
/* 281:228 */     switch (this.SubId)
/* 282:    */     {
/* 283:    */     case 0: 
/* 284:230 */       timerTick();
/* 285:231 */       break;
/* 286:    */     case 2: 
/* 287:233 */       stateCellTick();
/* 288:    */     }
/* 289:    */   }
/* 290:    */   
/* 291:    */   public int getPoweringMask(int ch)
/* 292:    */   {
/* 293:239 */     if (ch != 0) {
/* 294:239 */       return 0;
/* 295:    */     }
/* 296:242 */     switch (this.SubId)
/* 297:    */     {
/* 298:    */     case 0: 
/* 299:244 */       if ((this.Disabled) || (!this.Powered)) {
/* 300:244 */         return 0;
/* 301:    */       }
/* 302:245 */       return RedPowerLib.mapRotToCon(13, this.Rotation);
/* 303:    */     case 1: 
/* 304:248 */       return RedPowerLib.mapRotToCon(1 << this.PowerState, this.Rotation);
/* 305:    */     case 2: 
/* 306:251 */       int ps = ((this.Active) && (this.Powered) ? 8 : 0) | ((this.Active) && (!this.Powered) ? 1 : this.Deadmap == 0 ? 4 : 0);
/* 307:    */       
/* 308:253 */       return RedPowerLib.mapRotToCon(ps, this.Rotation);
/* 309:    */     }
/* 310:256 */     return 0;
/* 311:    */   }
/* 312:    */   
/* 313:    */   public boolean onPartActivateSide(qx player, int part, int side)
/* 314:    */   {
/* 315:261 */     if (part != this.Rotation >> 2) {
/* 316:261 */       return false;
/* 317:    */     }
/* 318:262 */     if (player.ah()) {
/* 319:262 */       return false;
/* 320:    */     }
/* 321:263 */     if (CoreLib.isClient(this.k)) {
/* 322:264 */       return false;
/* 323:    */     }
/* 324:265 */     player.openGui(RedPowerLogic.instance, 2, this.k, this.l, this.m, this.n);
/* 325:    */     
/* 326:267 */     return true;
/* 327:    */   }
/* 328:    */   
/* 329:    */   public void g()
/* 330:    */   {
/* 331:271 */     super.g();
/* 332:272 */     switch (this.SubId)
/* 333:    */     {
/* 334:    */     case 0: 
/* 335:274 */       timerUpdate();
/* 336:275 */       break;
/* 337:    */     case 1: 
/* 338:277 */       sequencerUpdate();
/* 339:278 */       break;
/* 340:    */     case 2: 
/* 341:280 */       stateCellUpdate();
/* 342:    */     }
/* 343:    */   }
/* 344:    */   
/* 345:    */   public float getPointerDirection(float f)
/* 346:    */   {
/* 347:287 */     if (this.SubId == 0)
/* 348:    */     {
/* 349:288 */       if ((this.Powered) || (this.Disabled)) {
/* 350:288 */         return 0.75F;
/* 351:    */       }
/* 352:289 */       long wt = this.k.G();
/* 353:290 */       float ivt = ((float)wt + f - (float)this.timestart) / (float)this.interval;
/* 354:292 */       if (ivt > 1.0F) {
/* 355:292 */         ivt = 1.0F;
/* 356:    */       }
/* 357:293 */       return ivt + 0.75F;
/* 358:    */     }
/* 359:295 */     if (this.SubId == 1)
/* 360:    */     {
/* 361:296 */       long wt = this.k.G() + 6000L;
/* 362:297 */       float pp = ((float)wt + f) / (float)(this.interval * 4L);
/* 363:298 */       if (this.Deadmap == 1) {
/* 364:298 */         pp = 0.75F - pp;
/* 365:    */       } else {
/* 366:299 */         pp += 0.75F;
/* 367:    */       }
/* 368:300 */       return pp;
/* 369:    */     }
/* 370:302 */     if (this.SubId == 2)
/* 371:    */     {
/* 372:303 */       if (this.Deadmap > 0)
/* 373:    */       {
/* 374:304 */         if ((!this.Active) || (this.Disabled)) {
/* 375:304 */           return 1.0F;
/* 376:    */         }
/* 377:305 */         if ((this.Active) && (this.Powered)) {
/* 378:305 */           return 0.8F;
/* 379:    */         }
/* 380:    */       }
/* 381:    */       else
/* 382:    */       {
/* 383:307 */         if ((!this.Active) || (this.Disabled)) {
/* 384:307 */           return 0.5F;
/* 385:    */         }
/* 386:308 */         if ((this.Active) && (this.Powered)) {
/* 387:308 */           return 0.7F;
/* 388:    */         }
/* 389:    */       }
/* 390:310 */       long wt = this.k.G();
/* 391:311 */       float ivt = ((float)wt + f - (float)this.timestart) / (float)this.interval;
/* 392:312 */       if (this.Deadmap > 0) {
/* 393:312 */         return 1.0F - 0.2F * ivt;
/* 394:    */       }
/* 395:313 */       return 0.5F + 0.2F * ivt;
/* 396:    */     }
/* 397:315 */     return 0.0F;
/* 398:    */   }
/* 399:    */   
/* 400:    */   public Quat getOrientationBasis()
/* 401:    */   {
/* 402:320 */     return MathLib.orientQuat(this.Rotation >> 2, this.Rotation & 0x3);
/* 403:    */   }
/* 404:    */   
/* 405:    */   public Vector3 getPointerOrigin()
/* 406:    */   {
/* 407:324 */     if (this.SubId == 2)
/* 408:    */     {
/* 409:325 */       if (this.Deadmap > 0) {
/* 410:326 */         return new Vector3(0.0D, -0.1D, -0.25D);
/* 411:    */       }
/* 412:327 */       return new Vector3(0.0D, -0.1D, 0.25D);
/* 413:    */     }
/* 414:329 */     return new Vector3(0.0D, -0.1D, 0.0D);
/* 415:    */   }
/* 416:    */   
/* 417:    */   public void setInterval(long iv)
/* 418:    */   {
/* 419:334 */     if (this.SubId == 0) {
/* 420:334 */       this.interval = (iv - 2L);
/* 421:    */     } else {
/* 422:335 */       this.interval = iv;
/* 423:    */     }
/* 424:    */   }
/* 425:    */   
/* 426:    */   public long getInterval()
/* 427:    */   {
/* 428:339 */     if (this.SubId == 0) {
/* 429:339 */       return this.interval + 2L;
/* 430:    */     }
/* 431:340 */     return this.interval;
/* 432:    */   }
/* 433:    */   
/* 434:    */   public boolean isUseableByPlayer(qx player)
/* 435:    */   {
/* 436:344 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 437:345 */       return false;
/* 438:    */     }
/* 439:346 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 440:    */   }
/* 441:    */   
/* 442:    */   public int getExtendedID()
/* 443:    */   {
/* 444:352 */     return 0;
/* 445:    */   }
/* 446:    */   
/* 447:    */   public void a(bq tag)
/* 448:    */   {
/* 449:356 */     super.a(tag);
/* 450:357 */     this.interval = tag.f("iv");
/* 451:359 */     if ((this.SubId == 0) || (this.SubId == 2)) {
/* 452:359 */       this.timestart = tag.f("ts");
/* 453:    */     }
/* 454:    */   }
/* 455:    */   
/* 456:    */   public void b(bq tag)
/* 457:    */   {
/* 458:363 */     super.b(tag);
/* 459:364 */     tag.a("iv", this.interval);
/* 460:366 */     if ((this.SubId == 0) || (this.SubId == 2)) {
/* 461:366 */       tag.a("ts", this.timestart);
/* 462:    */     }
/* 463:    */   }
/* 464:    */   
/* 465:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 466:    */     throws IOException
/* 467:    */   {
/* 468:371 */     super.readFromPacket(pkt);
/* 469:372 */     if (pkt.subId != 2) {
/* 470:372 */       return;
/* 471:    */     }
/* 472:373 */     this.interval = pkt.getUVLC();
/* 473:374 */     if ((this.SubId == 0) || (this.SubId == 2)) {
/* 474:374 */       this.timestart = pkt.getVLC();
/* 475:    */     }
/* 476:    */   }
/* 477:    */   
/* 478:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 479:    */   {
/* 480:378 */     super.writeToPacket(pkt);
/* 481:379 */     pkt.subId = 2;
/* 482:380 */     pkt.addUVLC(this.interval);
/* 483:381 */     if ((this.SubId == 0) || (this.SubId == 2)) {
/* 484:381 */       pkt.addVLC(this.timestart);
/* 485:    */     }
/* 486:    */   }
/* 487:    */   
/* 488:384 */   private long timestart = 0L;
/* 489:385 */   public long interval = 40L;
/* 490:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.TileLogicPointer
 * JD-Core Version:    0.7.0.1
 */