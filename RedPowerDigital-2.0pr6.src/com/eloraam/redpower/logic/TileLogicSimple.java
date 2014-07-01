/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   4:    */ import java.util.Random;
/*   5:    */ import qx;
/*   6:    */ import yc;
/*   7:    */ 
/*   8:    */ public class TileLogicSimple
/*   9:    */   extends TileLogic
/*  10:    */ {
/*  11:    */   public void initSubType(int st)
/*  12:    */   {
/*  13: 20 */     super.initSubType(st);
/*  14:    */   }
/*  15:    */   
/*  16:    */   public int getPartMaxRotation(int part, boolean sec)
/*  17:    */   {
/*  18: 26 */     if (sec) {
/*  19: 27 */       switch (this.SubId)
/*  20:    */       {
/*  21:    */       case 0: 
/*  22: 29 */         return 3;
/*  23:    */       case 1: 
/*  24:    */       case 2: 
/*  25:    */       case 3: 
/*  26:    */       case 4: 
/*  27:    */       case 9: 
/*  28: 35 */         return 6;
/*  29:    */       case 10: 
/*  30: 37 */         return 3;
/*  31:    */       case 11: 
/*  32:    */       case 15: 
/*  33: 40 */         return 1;
/*  34:    */       case 16: 
/*  35: 42 */         return 3;
/*  36:    */       }
/*  37:    */     }
/*  38: 45 */     return super.getPartMaxRotation(part, sec);
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getPartRotation(int part, boolean sec)
/*  42:    */   {
/*  43: 49 */     if (sec) {
/*  44: 50 */       switch (this.SubId)
/*  45:    */       {
/*  46:    */       case 0: 
/*  47: 52 */         return this.Deadmap;
/*  48:    */       case 1: 
/*  49:    */       case 2: 
/*  50:    */       case 3: 
/*  51:    */       case 4: 
/*  52: 57 */         return fromDead[this.Deadmap];
/*  53:    */       case 9: 
/*  54: 59 */         return fromDeadNot[this.Deadmap];
/*  55:    */       case 10: 
/*  56: 61 */         return fromDeadBuf[this.Deadmap];
/*  57:    */       case 11: 
/*  58:    */       case 15: 
/*  59:    */       case 16: 
/*  60: 65 */         return this.Deadmap;
/*  61:    */       }
/*  62:    */     }
/*  63: 68 */     return super.getPartRotation(part, sec);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void setPartRotation(int part, boolean sec, int rot)
/*  67:    */   {
/*  68: 72 */     if (sec) {
/*  69: 73 */       switch (this.SubId)
/*  70:    */       {
/*  71:    */       case 0: 
/*  72: 75 */         this.Deadmap = rot;
/*  73: 76 */         this.PowerState = 0;
/*  74: 77 */         this.Active = false;this.Powered = false;
/*  75: 78 */         updateBlockChange();
/*  76: 79 */         return;
/*  77:    */       case 1: 
/*  78:    */       case 2: 
/*  79:    */       case 3: 
/*  80:    */       case 4: 
/*  81: 84 */         this.Deadmap = toDead[rot];
/*  82: 85 */         updateBlockChange();
/*  83: 86 */         return;
/*  84:    */       case 9: 
/*  85: 88 */         this.Deadmap = toDeadNot[rot];
/*  86: 89 */         updateBlockChange();
/*  87: 90 */         return;
/*  88:    */       case 10: 
/*  89: 92 */         this.Deadmap = toDeadBuf[rot];
/*  90: 93 */         updateBlockChange();
/*  91: 94 */         return;
/*  92:    */       case 11: 
/*  93:    */       case 15: 
/*  94:    */       case 16: 
/*  95: 98 */         this.Deadmap = rot;
/*  96: 99 */         updateBlockChange();
/*  97:100 */         return;
/*  98:    */       }
/*  99:    */     }
/* 100:103 */     super.setPartRotation(part, sec, rot);
/* 101:    */   }
/* 102:    */   
/* 103:106 */   private static final int[] toDead = { 0, 1, 2, 4, 6, 5, 3 };
/* 104:107 */   private static final int[] fromDead = { 0, 1, 2, 6, 3, 5, 4 };
/* 105:108 */   private static final int[] toDeadNot = { 0, 1, 8, 4, 12, 5, 9 };
/* 106:109 */   private static final int[] fromDeadNot = { 0, 1, 0, 0, 3, 5, 0, 0, 2, 6, 0, 0, 4 };
/* 107:110 */   private static final int[] toDeadBuf = { 0, 1, 4, 5 };
/* 108:111 */   private static final int[] fromDeadBuf = { 0, 1, 0, 0, 2, 3 };
/* 109:    */   
/* 110:    */   private void latchUpdatePowerState()
/* 111:    */   {
/* 112:116 */     if ((this.Disabled) && (!this.Active)) {
/* 113:116 */       return;
/* 114:    */     }
/* 115:118 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 5, this.Rotation, 0);
/* 116:120 */     if (ps != this.PowerState) {
/* 117:120 */       updateBlock();
/* 118:    */     }
/* 119:121 */     this.PowerState = ps;
/* 120:123 */     if (isTickRunnable()) {
/* 121:123 */       return;
/* 122:    */     }
/* 123:124 */     if (this.Active)
/* 124:    */     {
/* 125:125 */       if (ps == 5) {
/* 126:125 */         this.Disabled = true;
/* 127:    */       } else {
/* 128:126 */         this.Disabled = false;
/* 129:    */       }
/* 130:    */     }
/* 131:127 */     else if (((ps == 1) && (this.Powered)) || ((ps == 4) && (!this.Powered)))
/* 132:    */     {
/* 133:128 */       this.Powered = (!this.Powered);
/* 134:129 */       this.Active = true;
/* 135:130 */       playSound("random.click", 0.3F, 0.5F, false);
/* 136:131 */       scheduleTick(2);
/* 137:132 */       updateBlockChange();
/* 138:    */     }
/* 139:133 */     else if (ps == 5)
/* 140:    */     {
/* 141:134 */       this.Active = true;this.Disabled = true;
/* 142:135 */       this.Powered = (!this.Powered);
/* 143:136 */       scheduleTick(2);
/* 144:137 */       updateBlockChange();
/* 145:    */     }
/* 146:    */   }
/* 147:    */   
/* 148:    */   private void latchTick()
/* 149:    */   {
/* 150:142 */     if (this.Active)
/* 151:    */     {
/* 152:143 */       this.Active = false;
/* 153:144 */       if (this.Disabled)
/* 154:    */       {
/* 155:145 */         updateBlockChange();
/* 156:146 */         scheduleTick(2);
/* 157:147 */         return;
/* 158:    */       }
/* 159:149 */       updateBlockChange();
/* 160:150 */       return;
/* 161:    */     }
/* 162:152 */     if (!this.Disabled) {
/* 163:152 */       return;
/* 164:    */     }
/* 165:154 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 5, this.Rotation, 0);
/* 166:156 */     if (ps != this.PowerState) {
/* 167:156 */       updateBlock();
/* 168:    */     }
/* 169:157 */     this.PowerState = ps;
/* 170:159 */     switch (ps)
/* 171:    */     {
/* 172:    */     case 0: 
/* 173:161 */       this.Disabled = false;
/* 174:162 */       this.Powered = (this.k.t.nextInt(2) == 0);
/* 175:163 */       updateBlockChange();
/* 176:164 */       break;
/* 177:    */     case 1: 
/* 178:166 */       this.Disabled = false;
/* 179:167 */       this.Powered = false;
/* 180:168 */       updateBlockChange();
/* 181:169 */       playSound("random.click", 0.3F, 0.5F, false);
/* 182:170 */       break;
/* 183:    */     case 4: 
/* 184:172 */       this.Disabled = false;
/* 185:173 */       this.Powered = true;
/* 186:174 */       updateBlockChange();
/* 187:175 */       playSound("random.click", 0.3F, 0.5F, false);
/* 188:176 */       break;
/* 189:    */     case 5: 
/* 190:178 */       scheduleTick(4);
/* 191:    */     }
/* 192:    */   }
/* 193:    */   
/* 194:    */   private int latch2NextState()
/* 195:    */   {
/* 196:184 */     if ((this.PowerState & 0x5) == 0) {
/* 197:185 */       return this.PowerState;
/* 198:    */     }
/* 199:186 */     int ps = this.PowerState & 0x5 | 0xA;
/* 200:187 */     if (this.Deadmap == 2)
/* 201:    */     {
/* 202:188 */       if ((ps & 0x1) > 0) {
/* 203:188 */         ps &= 0xFFFFFFF7;
/* 204:    */       }
/* 205:189 */       if ((ps & 0x4) > 0) {
/* 206:189 */         ps &= 0xFFFFFFFD;
/* 207:    */       }
/* 208:    */     }
/* 209:    */     else
/* 210:    */     {
/* 211:191 */       if ((ps & 0x1) > 0) {
/* 212:191 */         ps &= 0xFFFFFFFD;
/* 213:    */       }
/* 214:192 */       if ((ps & 0x4) > 0) {
/* 215:192 */         ps &= 0xFFFFFFF7;
/* 216:    */       }
/* 217:    */     }
/* 218:194 */     return ps;
/* 219:    */   }
/* 220:    */   
/* 221:    */   private void latch2UpdatePowerState()
/* 222:    */   {
/* 223:198 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 5, this.Rotation, 0);
/* 224:    */     
/* 225:    */ 
/* 226:201 */     boolean upd = false;
/* 227:202 */     if (ps != (this.PowerState & 0x5))
/* 228:    */     {
/* 229:203 */       this.PowerState = (this.PowerState & 0xA | ps);
/* 230:204 */       upd = true;
/* 231:    */     }
/* 232:206 */     int p2 = latch2NextState();
/* 233:207 */     if ((p2 != this.PowerState) || ((this.PowerState & 0x5) == 0))
/* 234:    */     {
/* 235:208 */       scheduleTick(2);
/* 236:209 */       upd = true;
/* 237:    */     }
/* 238:211 */     if (upd) {
/* 239:211 */       updateBlock();
/* 240:    */     }
/* 241:    */   }
/* 242:    */   
/* 243:    */   private void latchChange()
/* 244:    */   {
/* 245:215 */     if (this.Deadmap < 2)
/* 246:    */     {
/* 247:216 */       latchUpdatePowerState();
/* 248:    */     }
/* 249:    */     else
/* 250:    */     {
/* 251:218 */       if (isTickRunnable()) {
/* 252:218 */         return;
/* 253:    */       }
/* 254:219 */       latch2UpdatePowerState();
/* 255:    */     }
/* 256:    */   }
/* 257:    */   
/* 258:    */   private void latch2Tick()
/* 259:    */   {
/* 260:224 */     boolean upd = false;
/* 261:226 */     if (this.PowerState == 0)
/* 262:    */     {
/* 263:227 */       this.PowerState |= (this.k.t.nextInt(2) == 0 ? 1 : 4);
/* 264:228 */       upd = true;
/* 265:    */     }
/* 266:230 */     int ps = latch2NextState();
/* 267:231 */     if (ps != this.PowerState)
/* 268:    */     {
/* 269:232 */       this.PowerState = ps;
/* 270:233 */       upd = true;
/* 271:    */     }
/* 272:235 */     if (upd) {
/* 273:235 */       updateBlockChange();
/* 274:    */     }
/* 275:236 */     latch2UpdatePowerState();
/* 276:    */   }
/* 277:    */   
/* 278:    */   private void pulseChange()
/* 279:    */   {
/* 280:243 */     if (this.Active)
/* 281:    */     {
/* 282:244 */       int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 2, this.Rotation, 0);
/* 283:246 */       if (ps == 0)
/* 284:    */       {
/* 285:247 */         this.Active = false;
/* 286:248 */         updateBlock();
/* 287:    */       }
/* 288:    */     }
/* 289:250 */     else if (!this.Powered)
/* 290:    */     {
/* 291:251 */       int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 2, this.Rotation, 0);
/* 292:253 */       if (ps > 0)
/* 293:    */       {
/* 294:254 */         this.Powered = true;
/* 295:255 */         updateBlockChange();
/* 296:256 */         scheduleTick(2);
/* 297:    */       }
/* 298:    */     }
/* 299:    */   }
/* 300:    */   
/* 301:    */   private void pulseTick()
/* 302:    */   {
/* 303:263 */     if (this.Powered)
/* 304:    */     {
/* 305:264 */       this.Powered = false;
/* 306:265 */       int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 2, this.Rotation, 0);
/* 307:267 */       if (ps > 0) {
/* 308:267 */         this.Active = true;
/* 309:    */       }
/* 310:268 */       updateBlockChange();
/* 311:    */     }
/* 312:    */   }
/* 313:    */   
/* 314:    */   private void toggleUpdatePowerState()
/* 315:    */   {
/* 316:276 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 5, this.Rotation, 0);
/* 317:278 */     if (ps != this.PowerState)
/* 318:    */     {
/* 319:279 */       int t = 0x5 & ps & (this.PowerState ^ 0xFFFFFFFF);
/* 320:280 */       if ((t == 1) || (t == 4)) {
/* 321:280 */         this.Active = true;
/* 322:    */       }
/* 323:281 */       this.PowerState = ps;
/* 324:282 */       updateBlock();
/* 325:284 */       if (this.Active) {
/* 326:284 */         scheduleTick(2);
/* 327:    */       }
/* 328:    */     }
/* 329:    */   }
/* 330:    */   
/* 331:    */   private void toggleTick()
/* 332:    */   {
/* 333:289 */     if (this.Active)
/* 334:    */     {
/* 335:290 */       playSound("random.click", 0.3F, 0.5F, false);
/* 336:291 */       this.Powered = (!this.Powered);
/* 337:292 */       this.Active = false;
/* 338:293 */       updateBlockChange();
/* 339:    */     }
/* 340:295 */     toggleUpdatePowerState();
/* 341:    */   }
/* 342:    */   
/* 343:300 */   private static int[] tickSchedule = { 2, 4, 6, 8, 16, 32, 64, 128, 256 };
/* 344:    */   
/* 345:    */   private void repUpdatePowerState()
/* 346:    */   {
/* 347:304 */     if (this.Active) {
/* 348:304 */       return;
/* 349:    */     }
/* 350:305 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 2, this.Rotation, 0);
/* 351:307 */     if (ps != this.PowerState) {
/* 352:307 */       updateBlock();
/* 353:    */     }
/* 354:308 */     this.PowerState = ps;
/* 355:    */     
/* 356:310 */     boolean pwr = this.PowerState > 0;
/* 357:311 */     if (pwr != this.Powered)
/* 358:    */     {
/* 359:312 */       this.Active = true;
/* 360:313 */       scheduleTick(tickSchedule[this.Deadmap]);
/* 361:    */     }
/* 362:    */   }
/* 363:    */   
/* 364:    */   private void repTick()
/* 365:    */   {
/* 366:318 */     boolean pwr = simpleWantsPower();
/* 367:320 */     if (!this.Active) {
/* 368:320 */       return;
/* 369:    */     }
/* 370:321 */     this.Powered = (!this.Powered);
/* 371:322 */     this.Active = false;
/* 372:    */     
/* 373:324 */     updateBlockChange();
/* 374:325 */     repUpdatePowerState();
/* 375:    */   }
/* 376:    */   
/* 377:    */   private void syncChange()
/* 378:    */   {
/* 379:332 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 7, this.Rotation, 0);
/* 380:    */     
/* 381:334 */     int psc = ps & (this.PowerState ^ 0xFFFFFFFF);
/* 382:335 */     if (ps != this.PowerState) {
/* 383:335 */       updateBlock();
/* 384:    */     }
/* 385:336 */     this.PowerState = ps;
/* 386:    */     
/* 387:338 */     boolean upd = false;
/* 388:339 */     if ((ps & 0x2) == 2)
/* 389:    */     {
/* 390:340 */       if ((!this.Powered) && ((this.Active) || (this.Disabled)))
/* 391:    */       {
/* 392:341 */         this.Active = false;this.Disabled = false;
/* 393:342 */         upd = true;
/* 394:    */       }
/* 395:    */     }
/* 396:    */     else
/* 397:    */     {
/* 398:345 */       if (((psc & 0x1) > 0) && (!this.Active))
/* 399:    */       {
/* 400:346 */         this.Active = true;
/* 401:347 */         upd = true;
/* 402:    */       }
/* 403:349 */       if (((psc & 0x4) > 0) && (!this.Disabled))
/* 404:    */       {
/* 405:350 */         this.Disabled = true;
/* 406:351 */         upd = true;
/* 407:    */       }
/* 408:    */     }
/* 409:354 */     if (upd)
/* 410:    */     {
/* 411:355 */       updateBlock();
/* 412:356 */       scheduleTick(2);
/* 413:    */     }
/* 414:    */   }
/* 415:    */   
/* 416:    */   private void syncTick()
/* 417:    */   {
/* 418:361 */     if ((this.Active) && (this.Disabled) && (!this.Powered))
/* 419:    */     {
/* 420:362 */       this.Powered = true;
/* 421:363 */       this.Active = false;
/* 422:364 */       this.Disabled = false;
/* 423:365 */       scheduleTick(2);
/* 424:366 */       updateBlockChange();
/* 425:    */     }
/* 426:367 */     else if (this.Powered)
/* 427:    */     {
/* 428:368 */       this.Powered = false;
/* 429:369 */       updateBlockChange();
/* 430:    */     }
/* 431:    */   }
/* 432:    */   
/* 433:    */   private void randUpdatePowerState()
/* 434:    */   {
/* 435:377 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 15, this.Rotation, 0);
/* 436:    */     
/* 437:379 */     int psc = ps & (this.PowerState ^ 0xFFFFFFFF);
/* 438:380 */     if (ps != this.PowerState) {
/* 439:380 */       updateBlock();
/* 440:    */     }
/* 441:381 */     this.PowerState = ps;
/* 442:382 */     if ((psc & 0x2) > 0) {
/* 443:383 */       scheduleTick(2);
/* 444:    */     }
/* 445:    */   }
/* 446:    */   
/* 447:    */   private void randTick()
/* 448:    */   {
/* 449:388 */     if ((this.PowerState & 0x2) == 0) {
/* 450:388 */       return;
/* 451:    */     }
/* 452:390 */     int rv = this.k.t.nextInt(8);
/* 453:391 */     this.Disabled = ((rv & 0x1) > 0);
/* 454:392 */     this.Active = ((rv & 0x2) > 0);
/* 455:393 */     this.Powered = ((rv & 0x4) > 0);
/* 456:    */     
/* 457:395 */     updateBlockChange();
/* 458:    */     
/* 459:397 */     randUpdatePowerState();
/* 460:398 */     if ((this.PowerState & 0x2) > 0) {
/* 461:399 */       scheduleTick(4);
/* 462:    */     }
/* 463:    */   }
/* 464:    */   
/* 465:    */   private void lightTick()
/* 466:    */   {
/* 467:405 */     int lb = this.k.m(this.l, this.m, this.n);
/* 468:406 */     this.Active = (lb > this.Deadmap * 4);
/* 469:407 */     if ((this.Cover != 7) && (this.Cover != 255)) {
/* 470:407 */       this.Active = false;
/* 471:    */     }
/* 472:408 */     if (this.Active != this.Powered) {
/* 473:408 */       scheduleTick(2);
/* 474:    */     }
/* 475:410 */     simpleTick();
/* 476:    */   }
/* 477:    */   
/* 478:    */   private boolean simpleWantsPower()
/* 479:    */   {
/* 480:417 */     switch (this.SubId)
/* 481:    */     {
/* 482:    */     case 1: 
/* 483:419 */       return (this.PowerState & 0x7 & (this.Deadmap ^ 0xFFFFFFFF)) == 0;
/* 484:    */     case 2: 
/* 485:421 */       return (this.PowerState & (this.Deadmap ^ 0xFFFFFFFF)) > 0;
/* 486:    */     case 3: 
/* 487:423 */       return (this.PowerState & 0x7 | this.Deadmap) < 7;
/* 488:    */     case 4: 
/* 489:425 */       return (this.PowerState | this.Deadmap) == 7;
/* 490:    */     case 5: 
/* 491:427 */       return (this.PowerState == 5) || (this.PowerState == 0);
/* 492:    */     case 6: 
/* 493:429 */       int t = this.PowerState & 0x5;
/* 494:430 */       return (t == 4) || (t == 1);
/* 495:    */     case 9: 
/* 496:432 */       return (this.PowerState & 0x2) == 0;
/* 497:    */     case 10: 
/* 498:434 */       return (this.PowerState & 0x2) > 0;
/* 499:    */     case 11: 
/* 500:436 */       if (this.Deadmap == 0) {
/* 501:437 */         return ((this.PowerState & 0x3) == 1) || ((this.PowerState & 0x6) == 6);
/* 502:    */       }
/* 503:439 */       return ((this.PowerState & 0x3) == 3) || ((this.PowerState & 0x6) == 4);
/* 504:    */     case 15: 
/* 505:442 */       if ((this.PowerState & 0x2) == 0) {
/* 506:443 */         return this.Powered;
/* 507:    */       }
/* 508:444 */       if (this.Deadmap == 0) {
/* 509:445 */         return (this.PowerState & 0x4) == 4;
/* 510:    */       }
/* 511:446 */       return (this.PowerState & 0x1) == 1;
/* 512:    */     case 16: 
/* 513:448 */       return this.Active;
/* 514:    */     }
/* 515:450 */     return false;
/* 516:    */   }
/* 517:    */   
/* 518:    */   private void simpleUpdatePowerState()
/* 519:    */   {
/* 520:455 */     int sides = 15;
/* 521:457 */     switch (this.SubId)
/* 522:    */     {
/* 523:    */     case 2: 
/* 524:458 */       sides = 7; break;
/* 525:    */     case 4: 
/* 526:459 */       sides = 7; break;
/* 527:    */     case 5: 
/* 528:460 */       sides = 5; break;
/* 529:    */     case 6: 
/* 530:461 */       sides = 13; break;
/* 531:    */     case 10: 
/* 532:462 */       sides = 7; break;
/* 533:    */     case 11: 
/* 534:463 */       sides = 7; break;
/* 535:    */     case 12: 
/* 536:464 */       sides = 2; break;
/* 537:    */     case 15: 
/* 538:465 */       sides = this.Deadmap == 0 ? 6 : 3; break;
/* 539:    */     case 16: 
/* 540:466 */       sides = 8;
/* 541:    */     }
/* 542:469 */     int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, sides, this.Rotation, 0);
/* 543:471 */     if (ps != this.PowerState) {
/* 544:471 */       updateBlock();
/* 545:    */     }
/* 546:472 */     this.PowerState = ps;
/* 547:    */     
/* 548:474 */     boolean pwr = simpleWantsPower();
/* 549:475 */     if (pwr != this.Powered) {
/* 550:476 */       scheduleTick(2);
/* 551:    */     }
/* 552:    */   }
/* 553:    */   
/* 554:    */   private void simpleTick()
/* 555:    */   {
/* 556:481 */     boolean pwr = simpleWantsPower();
/* 557:483 */     if ((this.Powered) && (!pwr))
/* 558:    */     {
/* 559:484 */       this.Powered = false;
/* 560:485 */       updateBlockChange();
/* 561:    */     }
/* 562:486 */     else if ((!this.Powered) && (pwr))
/* 563:    */     {
/* 564:487 */       this.Powered = true;
/* 565:488 */       updateBlockChange();
/* 566:    */     }
/* 567:490 */     simpleUpdatePowerState();
/* 568:    */   }
/* 569:    */   
/* 570:    */   public void onBlockNeighborChange(int l)
/* 571:    */   {
/* 572:496 */     if (tryDropBlock()) {
/* 573:496 */       return;
/* 574:    */     }
/* 575:497 */     switch (this.SubId)
/* 576:    */     {
/* 577:    */     case 0: 
/* 578:499 */       latchChange();
/* 579:500 */       break;
/* 580:    */     case 1: 
/* 581:    */     case 2: 
/* 582:    */     case 3: 
/* 583:    */     case 4: 
/* 584:    */     case 5: 
/* 585:    */     case 6: 
/* 586:    */     case 9: 
/* 587:    */     case 10: 
/* 588:    */     case 11: 
/* 589:    */     case 15: 
/* 590:    */     case 16: 
/* 591:512 */       if (!isTickRunnable()) {
/* 592:513 */         simpleUpdatePowerState();
/* 593:    */       }
/* 594:514 */       break;
/* 595:    */     case 7: 
/* 596:516 */       pulseChange();
/* 597:517 */       break;
/* 598:    */     case 8: 
/* 599:519 */       if (!isTickRunnable()) {
/* 600:520 */         toggleUpdatePowerState();
/* 601:    */       }
/* 602:521 */       break;
/* 603:    */     case 12: 
/* 604:523 */       if (!isTickRunnable()) {
/* 605:524 */         repUpdatePowerState();
/* 606:    */       }
/* 607:525 */       break;
/* 608:    */     case 13: 
/* 609:527 */       syncChange();
/* 610:528 */       break;
/* 611:    */     case 14: 
/* 612:530 */       if (!isTickRunnable()) {
/* 613:531 */         randUpdatePowerState();
/* 614:    */       }
/* 615:    */       break;
/* 616:    */     }
/* 617:    */   }
/* 618:    */   
/* 619:    */   public void onTileTick()
/* 620:    */   {
/* 621:537 */     switch (this.SubId)
/* 622:    */     {
/* 623:    */     case 0: 
/* 624:539 */       if (this.Deadmap < 2) {
/* 625:539 */         latchTick();
/* 626:    */       } else {
/* 627:540 */         latch2Tick();
/* 628:    */       }
/* 629:541 */       break;
/* 630:    */     case 1: 
/* 631:    */     case 2: 
/* 632:    */     case 3: 
/* 633:    */     case 4: 
/* 634:    */     case 5: 
/* 635:    */     case 6: 
/* 636:    */     case 9: 
/* 637:    */     case 10: 
/* 638:    */     case 11: 
/* 639:    */     case 15: 
/* 640:552 */       simpleTick();
/* 641:553 */       break;
/* 642:    */     case 7: 
/* 643:555 */       pulseTick();
/* 644:556 */       break;
/* 645:    */     case 8: 
/* 646:558 */       toggleTick();
/* 647:559 */       break;
/* 648:    */     case 12: 
/* 649:561 */       repTick();
/* 650:562 */       break;
/* 651:    */     case 13: 
/* 652:564 */       syncTick();
/* 653:565 */       break;
/* 654:    */     case 14: 
/* 655:567 */       randTick();
/* 656:568 */       break;
/* 657:    */     case 16: 
/* 658:570 */       lightTick();
/* 659:    */     }
/* 660:    */   }
/* 661:    */   
/* 662:    */   public int getPoweringMask(int ch)
/* 663:    */   {
/* 664:576 */     if (ch != 0) {
/* 665:576 */       return 0;
/* 666:    */     }
/* 667:579 */     switch (this.SubId)
/* 668:    */     {
/* 669:    */     case 0: 
/* 670:    */       int ps;
/* 671:    */       int ps;
/* 672:581 */       if (this.Deadmap > 1)
/* 673:    */       {
/* 674:582 */         ps = this.PowerState & 0xA;
/* 675:    */       }
/* 676:    */       else
/* 677:    */       {
/* 678:    */         int ps;
/* 679:584 */         if ((this.Disabled) && (!this.Active))
/* 680:    */         {
/* 681:585 */           ps = 0;
/* 682:    */         }
/* 683:    */         else
/* 684:    */         {
/* 685:    */           int ps;
/* 686:587 */           if (this.Active)
/* 687:    */           {
/* 688:588 */             ps = this.Powered ? 4 : 1;
/* 689:    */           }
/* 690:    */           else
/* 691:    */           {
/* 692:    */             int ps;
/* 693:589 */             if (this.Deadmap == 1) {
/* 694:590 */               ps = this.Powered ? 6 : 9;
/* 695:    */             } else {
/* 696:592 */               ps = this.Powered ? 12 : 3;
/* 697:    */             }
/* 698:    */           }
/* 699:    */         }
/* 700:    */       }
/* 701:596 */       return RedPowerLib.mapRotToCon(ps, this.Rotation);
/* 702:    */     case 8: 
/* 703:598 */       if (this.Powered) {
/* 704:598 */         return RedPowerLib.mapRotToCon(2, this.Rotation);
/* 705:    */       }
/* 706:599 */       return RedPowerLib.mapRotToCon(8, this.Rotation);
/* 707:    */     case 9: 
/* 708:    */     case 10: 
/* 709:602 */       if (this.Powered) {
/* 710:603 */         return RedPowerLib.mapRotToCon(0xD & (this.Deadmap ^ 0xFFFFFFFF), this.Rotation);
/* 711:    */       }
/* 712:606 */       return 0;
/* 713:    */     case 14: 
/* 714:608 */       return RedPowerLib.mapRotToCon((this.Active ? 1 : 0) | (this.Disabled ? 4 : 0) | (this.Powered ? 8 : 0), this.Rotation);
/* 715:    */     case 15: 
/* 716:612 */       return RedPowerLib.mapRotToCon(this.Powered ? 12 : this.Deadmap == 0 ? 0 : this.Powered ? 9 : 0, this.Rotation);
/* 717:    */     }
/* 718:616 */     return super.getPoweringMask(ch);
/* 719:    */   }
/* 720:    */   
/* 721:    */   public boolean onPartActivateSide(qx player, int part, int side)
/* 722:    */   {
/* 723:621 */     switch (this.SubId)
/* 724:    */     {
/* 725:    */     case 8: 
/* 726:623 */       if (part != this.Rotation >> 2) {
/* 727:623 */         return false;
/* 728:    */       }
/* 729:624 */       playSound("random.click", 0.3F, 0.5F, false);
/* 730:625 */       if (this.Powered) {
/* 731:625 */         this.Powered = false;
/* 732:    */       } else {
/* 733:625 */         this.Powered = true;
/* 734:    */       }
/* 735:626 */       updateBlockChange();
/* 736:627 */       return true;
/* 737:    */     case 12: 
/* 738:629 */       if (part != this.Rotation >> 2) {
/* 739:629 */         return false;
/* 740:    */       }
/* 741:630 */       this.Deadmap += 1;
/* 742:631 */       if (this.Deadmap > 8) {
/* 743:631 */         this.Deadmap = 0;
/* 744:    */       }
/* 745:632 */       updateBlockChange();
/* 746:633 */       return true;
/* 747:    */     }
/* 748:635 */     return false;
/* 749:    */   }
/* 750:    */   
/* 751:    */   public int getConnectableMask()
/* 752:    */   {
/* 753:640 */     switch (this.SubId)
/* 754:    */     {
/* 755:    */     case 1: 
/* 756:    */     case 2: 
/* 757:    */     case 3: 
/* 758:    */     case 4: 
/* 759:645 */       return RedPowerLib.mapRotToCon(0x8 | 0x7 & (this.Deadmap ^ 0xFFFFFFFF), this.Rotation);
/* 760:    */     case 5: 
/* 761:    */     case 6: 
/* 762:648 */       return RedPowerLib.mapRotToCon(13, this.Rotation);
/* 763:    */     case 7: 
/* 764:650 */       return RedPowerLib.mapRotToCon(10, this.Rotation);
/* 765:    */     case 9: 
/* 766:652 */       return RedPowerLib.mapRotToCon(0x2 | 0xD & (this.Deadmap ^ 0xFFFFFFFF), this.Rotation);
/* 767:    */     case 10: 
/* 768:655 */       return RedPowerLib.mapRotToCon(0xA | 0x5 & (this.Deadmap ^ 0xFFFFFFFF), this.Rotation);
/* 769:    */     case 12: 
/* 770:658 */       return RedPowerLib.mapRotToCon(10, this.Rotation);
/* 771:    */     }
/* 772:660 */     return super.getConnectableMask();
/* 773:    */   }
/* 774:    */   
/* 775:    */   public void g()
/* 776:    */   {
/* 777:664 */     super.g();
/* 778:665 */     if ((this.SubId == 16) && 
/* 779:666 */       (!isTickScheduled())) {
/* 780:667 */       scheduleTick(8);
/* 781:    */     }
/* 782:    */   }
/* 783:    */   
/* 784:    */   public int getLightValue()
/* 785:    */   {
/* 786:672 */     if (this.SubId == 16) {
/* 787:673 */       return 0;
/* 788:    */     }
/* 789:675 */     return super.getLightValue();
/* 790:    */   }
/* 791:    */   
/* 792:    */   public int getExtendedID()
/* 793:    */   {
/* 794:680 */     return 1;
/* 795:    */   }
/* 796:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.TileLogicSimple
 * JD-Core Version:    0.7.0.1
 */