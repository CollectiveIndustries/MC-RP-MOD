/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import by;
/*   6:    */ import com.eloraam.redpower.RedPowerMachine;
/*   7:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   8:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   9:    */ import com.eloraam.redpower.core.CoreLib;
/*  10:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  11:    */ import com.eloraam.redpower.core.MachineLib;
/*  12:    */ import com.eloraam.redpower.core.MachineLib.FilterMap;
/*  13:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  14:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  15:    */ import com.eloraam.redpower.core.TubeItem;
/*  16:    */ import com.eloraam.redpower.core.WorldCoord;
/*  17:    */ import la;
/*  18:    */ import net.minecraftforge.common.ForgeDirection;
/*  19:    */ import net.minecraftforge.common.ISidedInventory;
/*  20:    */ import qx;
/*  21:    */ import ur;
/*  22:    */ import yc;
/*  23:    */ 
/*  24:    */ public class TileSorter
/*  25:    */   extends TileTranspose
/*  26:    */   implements la, ISidedInventory, IBluePowerConnectable
/*  27:    */ {
/*  28:    */   public TileSorter()
/*  29:    */   {
/*  30: 20 */     this.contents = new ur[40];
/*  31: 21 */     this.colors = new byte[8];
/*  32: 22 */     this.channelBuffers = new TubeBuffer[8];
/*  33: 23 */     for (int i = 0; i < 8; i++) {
/*  34: 24 */       this.channelBuffers[i] = new TubeBuffer();
/*  35:    */     }
/*  36:    */   }
/*  37:    */   
/*  38:    */   void regenFilterMap()
/*  39:    */   {
/*  40: 30 */     this.filterMap = MachineLib.makeFilterMap(this.contents);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public int getConnectableMask()
/*  44:    */   {
/*  45: 36 */     return 1073741823;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public int getConnectClass(int side)
/*  49:    */   {
/*  50: 40 */     return 65;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public int getCornerPowerMode()
/*  54:    */   {
/*  55: 43 */     return 0;
/*  56:    */   }
/*  57:    */   
/*  58: 47 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  59:    */   {
/*  60:    */     public any getParent()
/*  61:    */     {
/*  62: 49 */       return TileSorter.this;
/*  63:    */     }
/*  64:    */   };
/*  65:    */   
/*  66:    */   public BluePowerConductor getBlueConductor(int side)
/*  67:    */   {
/*  68: 54 */     return this.cond;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public int getStartInventorySide(ForgeDirection side)
/*  72:    */   {
/*  73: 60 */     return 0;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public int getSizeInventorySide(ForgeDirection side)
/*  77:    */   {
/*  78: 64 */     return 0;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void g()
/*  82:    */   {
/*  83: 73 */     super.g();
/*  84: 74 */     if (CoreLib.isClient(this.k)) {
/*  85: 74 */       return;
/*  86:    */     }
/*  87: 76 */     if (!this.Powered) {
/*  88: 76 */       this.Delay = false;
/*  89:    */     }
/*  90: 77 */     if (this.ConMask < 0)
/*  91:    */     {
/*  92: 78 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/*  93:    */       
/*  94: 80 */       this.cond.recache(this.ConMask, 0);
/*  95:    */     }
/*  96: 82 */     this.cond.iterate();
/*  97: 83 */     dirtyBlock();
/*  98: 84 */     if (this.cond.Flow == 0)
/*  99:    */     {
/* 100: 85 */       if (this.Charged)
/* 101:    */       {
/* 102: 86 */         this.Charged = false;
/* 103: 87 */         updateBlock();
/* 104:    */       }
/* 105:    */     }
/* 106: 89 */     else if (!this.Charged)
/* 107:    */     {
/* 108: 90 */       this.Charged = true;
/* 109: 91 */       updateBlock();
/* 110:    */     }
/* 111: 93 */     if (((this.automode == 1) || ((this.automode == 2) && (this.pulses > 0))) && 
/* 112: 94 */       (!isTickScheduled())) {
/* 113: 95 */       scheduleTick(10);
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   public boolean onBlockActivated(qx player)
/* 118:    */   {
/* 119:102 */     if (player.ah()) {
/* 120:102 */       return false;
/* 121:    */     }
/* 122:103 */     if (CoreLib.isClient(this.k)) {
/* 123:104 */       return true;
/* 124:    */     }
/* 125:105 */     player.openGui(RedPowerMachine.instance, 5, this.k, this.l, this.m, this.n);
/* 126:    */     
/* 127:107 */     return true;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public int getExtendedID()
/* 131:    */   {
/* 132:111 */     return 5;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void onBlockRemoval()
/* 136:    */   {
/* 137:115 */     super.onBlockRemoval();
/* 138:117 */     for (int i = 0; i < 8; i++) {
/* 139:118 */       this.channelBuffers[i].onRemove(this);
/* 140:    */     }
/* 141:120 */     for (int i = 0; i < 40; i++)
/* 142:    */     {
/* 143:121 */       ur ist = this.contents[i];
/* 144:122 */       if ((ist != null) && (ist.a > 0)) {
/* 145:123 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 146:    */       }
/* 147:    */     }
/* 148:    */   }
/* 149:    */   
/* 150:    */   public void onBlockNeighborChange(int l)
/* 151:    */   {
/* 152:130 */     this.ConMask = -1;
/* 153:131 */     if (this.automode == 0) {
/* 154:132 */       super.onBlockNeighborChange(l);
/* 155:    */     }
/* 156:133 */     if (this.automode == 2) {
/* 157:134 */       if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, 16777215, 63))
/* 158:    */       {
/* 159:136 */         if (this.Powered) {
/* 160:136 */           return;
/* 161:    */         }
/* 162:137 */         this.Powered = true;
/* 163:138 */         dirtyBlock();
/* 164:139 */         if (this.Delay) {
/* 165:139 */           return;
/* 166:    */         }
/* 167:140 */         this.Delay = true;
/* 168:141 */         this.pulses += 1;
/* 169:    */       }
/* 170:    */       else
/* 171:    */       {
/* 172:143 */         this.Powered = false;
/* 173:144 */         dirtyBlock();
/* 174:145 */         return;
/* 175:    */       }
/* 176:    */     }
/* 177:    */   }
/* 178:    */   
/* 179:    */   protected int getColumnMatch(ur ist)
/* 180:    */   {
/* 181:153 */     if (this.filterMap == null) {
/* 182:153 */       regenFilterMap();
/* 183:    */     }
/* 184:154 */     if (this.filterMap.size() == 0) {
/* 185:155 */       return -2;
/* 186:    */     }
/* 187:157 */     int i = this.filterMap.firstMatch(ist);
/* 188:158 */     if (i < 0) {
/* 189:158 */       return i;
/* 190:    */     }
/* 191:159 */     return i & 0x7;
/* 192:    */   }
/* 193:    */   
/* 194:    */   protected void fireMatch()
/* 195:    */   {
/* 196:164 */     this.Active = true;
/* 197:165 */     updateBlock();
/* 198:166 */     scheduleTick(5);
/* 199:    */   }
/* 200:    */   
/* 201:    */   protected boolean tryDrainBuffer(TubeBuffer buf)
/* 202:    */   {
/* 203:170 */     if (buf.isEmpty()) {
/* 204:170 */       return false;
/* 205:    */     }
/* 206:171 */     while (!buf.isEmpty())
/* 207:    */     {
/* 208:172 */       TubeItem ti = buf.getLast();
/* 209:173 */       if (stuffCart(ti.item))
/* 210:    */       {
/* 211:174 */         buf.pop();
/* 212:    */       }
/* 213:    */       else
/* 214:    */       {
/* 215:177 */         if (!handleItem(ti))
/* 216:    */         {
/* 217:178 */           buf.plugged = true;
/* 218:179 */           return true;
/* 219:    */         }
/* 220:181 */         buf.pop();
/* 221:182 */         if (buf.plugged) {
/* 222:182 */           return true;
/* 223:    */         }
/* 224:    */       }
/* 225:    */     }
/* 226:184 */     return true;
/* 227:    */   }
/* 228:    */   
/* 229:    */   protected boolean tryDrainBuffer()
/* 230:    */   {
/* 231:188 */     for (int i = 0; i < 9; i++)
/* 232:    */     {
/* 233:190 */       this.draining = ((byte)(this.draining + 1));
/* 234:    */       TubeBuffer buf;
/* 235:    */       TubeBuffer buf;
/* 236:191 */       if (this.draining > 7)
/* 237:    */       {
/* 238:192 */         this.draining = -1;
/* 239:193 */         buf = this.buffer;
/* 240:    */       }
/* 241:    */       else
/* 242:    */       {
/* 243:195 */         buf = this.channelBuffers[this.draining];
/* 244:    */       }
/* 245:197 */       if (tryDrainBuffer(buf)) {
/* 246:197 */         return false;
/* 247:    */       }
/* 248:    */     }
/* 249:199 */     return true;
/* 250:    */   }
/* 251:    */   
/* 252:    */   protected boolean isBufferEmpty()
/* 253:    */   {
/* 254:203 */     if (!this.buffer.isEmpty()) {
/* 255:203 */       return false;
/* 256:    */     }
/* 257:204 */     for (int i = 0; i < 8; i++) {
/* 258:205 */       if (!this.channelBuffers[i].isEmpty()) {
/* 259:205 */         return false;
/* 260:    */       }
/* 261:    */     }
/* 262:206 */     return true;
/* 263:    */   }
/* 264:    */   
/* 265:    */   public void drainBuffer()
/* 266:    */   {
/* 267:211 */     tryDrainBuffer();
/* 268:    */   }
/* 269:    */   
/* 270:    */   private boolean autoTick()
/* 271:    */   {
/* 272:215 */     if (this.Active) {
/* 273:215 */       return false;
/* 274:    */     }
/* 275:216 */     if ((this.automode == 2) && (this.pulses == 0)) {
/* 276:216 */       return false;
/* 277:    */     }
/* 278:218 */     WorldCoord wc = new WorldCoord(this);
/* 279:219 */     wc.step(this.Rotation ^ 0x1);
/* 280:220 */     if (handleExtract(wc))
/* 281:    */     {
/* 282:221 */       this.Active = true;
/* 283:222 */       updateBlock();
/* 284:223 */       scheduleTick(5);
/* 285:    */     }
/* 286:    */     else
/* 287:    */     {
/* 288:225 */       scheduleTick(10);
/* 289:    */     }
/* 290:227 */     return true;
/* 291:    */   }
/* 292:    */   
/* 293:    */   public void onTileTick()
/* 294:    */   {
/* 295:231 */     if (CoreLib.isClient(this.k)) {
/* 296:232 */       return;
/* 297:    */     }
/* 298:233 */     if ((this.automode == 1) && (this.Powered))
/* 299:    */     {
/* 300:234 */       this.Powered = false;
/* 301:235 */       updateBlock();
/* 302:    */     }
/* 303:237 */     if ((this.automode > 0) && 
/* 304:238 */       (autoTick())) {
/* 305:238 */       return;
/* 306:    */     }
/* 307:241 */     if (!this.Active) {
/* 308:241 */       return;
/* 309:    */     }
/* 310:242 */     if (!tryDrainBuffer())
/* 311:    */     {
/* 312:243 */       if (isBufferEmpty()) {
/* 313:243 */         scheduleTick(5);
/* 314:    */       } else {
/* 315:244 */         scheduleTick(10);
/* 316:    */       }
/* 317:245 */       return;
/* 318:    */     }
/* 319:247 */     if ((!this.Powered) || (this.automode == 2))
/* 320:    */     {
/* 321:248 */       this.Active = false;
/* 322:249 */       updateBlock();
/* 323:    */     }
/* 324:251 */     if ((this.automode == 1) || ((this.automode == 2) && (this.pulses > 0))) {
/* 325:252 */       scheduleTick(5);
/* 326:    */     }
/* 327:    */   }
/* 328:    */   
/* 329:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/* 330:    */   {
/* 331:258 */     if ((side == this.Rotation) && (state == 2))
/* 332:    */     {
/* 333:259 */       int cm = getColumnMatch(ti.item);
/* 334:260 */       TubeBuffer buf = this.buffer;
/* 335:261 */       if ((cm >= 0) && (this.mode > 1)) {
/* 336:262 */         buf = this.channelBuffers[cm];
/* 337:    */       }
/* 338:263 */       buf.addBounce(ti);
/* 339:264 */       fireMatch();
/* 340:265 */       return true;
/* 341:    */     }
/* 342:266 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1))
/* 343:    */     {
/* 344:267 */       if (ti.priority > 0) {
/* 345:267 */         return false;
/* 346:    */       }
/* 347:268 */       if ((this.automode == 0) && (this.Powered)) {
/* 348:268 */         return false;
/* 349:    */       }
/* 350:269 */       if (this.cond.getVoltage() < 60.0D) {
/* 351:269 */         return false;
/* 352:    */       }
/* 353:272 */       int cm = getColumnMatch(ti.item);
/* 354:273 */       TubeBuffer buf = this.buffer;
/* 355:274 */       if ((cm >= 0) && (this.mode > 1)) {
/* 356:275 */         buf = this.channelBuffers[cm];
/* 357:    */       }
/* 358:277 */       if (!buf.isEmpty()) {
/* 359:277 */         return false;
/* 360:    */       }
/* 361:278 */       if (cm < 0)
/* 362:    */       {
/* 363:279 */         if ((this.mode == 4) || (this.mode == 6))
/* 364:    */         {
/* 365:280 */           this.cond.drawPower(25 * ti.item.a);
/* 366:281 */           buf.addNewColor(ti.item, this.defcolor);
/* 367:282 */           fireMatch();
/* 368:283 */           tryDrainBuffer(buf);
/* 369:284 */           return true;
/* 370:    */         }
/* 371:286 */         if (cm == -2)
/* 372:    */         {
/* 373:287 */           this.cond.drawPower(25 * ti.item.a);
/* 374:288 */           buf.addNewColor(ti.item, 0);
/* 375:289 */           fireMatch();
/* 376:290 */           tryDrainBuffer(buf);
/* 377:291 */           return true;
/* 378:    */         }
/* 379:293 */         return false;
/* 380:    */       }
/* 381:295 */       this.cond.drawPower(25 * ti.item.a);
/* 382:296 */       buf.addNewColor(ti.item, this.colors[cm]);
/* 383:297 */       fireMatch();
/* 384:298 */       tryDrainBuffer(buf);
/* 385:299 */       return true;
/* 386:    */     }
/* 387:301 */     return false;
/* 388:    */   }
/* 389:    */   
/* 390:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/* 391:    */   {
/* 392:305 */     if ((side == this.Rotation) && (state == 2)) {
/* 393:306 */       return true;
/* 394:    */     }
/* 395:307 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1))
/* 396:    */     {
/* 397:308 */       if (ti.priority > 0) {
/* 398:308 */         return false;
/* 399:    */       }
/* 400:309 */       if ((this.automode == 0) && (this.Powered)) {
/* 401:309 */         return false;
/* 402:    */       }
/* 403:310 */       if (this.cond.getVoltage() < 60.0D) {
/* 404:310 */         return false;
/* 405:    */       }
/* 406:312 */       int cm = getColumnMatch(ti.item);
/* 407:313 */       TubeBuffer buf = this.buffer;
/* 408:314 */       if ((cm >= 0) && (this.mode > 1)) {
/* 409:315 */         buf = this.channelBuffers[cm];
/* 410:    */       }
/* 411:316 */       if (!buf.isEmpty()) {
/* 412:316 */         return false;
/* 413:    */       }
/* 414:317 */       if (cm < 0)
/* 415:    */       {
/* 416:318 */         if ((this.mode == 4) || (this.mode == 6)) {
/* 417:318 */           return true;
/* 418:    */         }
/* 419:319 */         if (cm == -2) {
/* 420:319 */           return true;
/* 421:    */         }
/* 422:320 */         return false;
/* 423:    */       }
/* 424:322 */       return true;
/* 425:    */     }
/* 426:324 */     return false;
/* 427:    */   }
/* 428:    */   
/* 429:    */   protected void addToBuffer(ur ist)
/* 430:    */   {
/* 431:328 */     int cm = getColumnMatch(ist);
/* 432:329 */     TubeBuffer buf = this.buffer;
/* 433:330 */     if ((cm >= 0) && (this.mode > 1)) {
/* 434:331 */       buf = this.channelBuffers[cm];
/* 435:    */     }
/* 436:332 */     if (cm < 0)
/* 437:    */     {
/* 438:333 */       if ((this.mode == 4) || (this.mode == 6))
/* 439:    */       {
/* 440:334 */         buf.addNewColor(ist, this.defcolor);
/* 441:335 */         return;
/* 442:    */       }
/* 443:337 */       buf.addNewColor(ist, 0);
/* 444:338 */       return;
/* 445:    */     }
/* 446:340 */     buf.addNewColor(ist, this.colors[cm]);
/* 447:    */   }
/* 448:    */   
/* 449:    */   private void stepColumn()
/* 450:    */   {
/* 451:346 */     for (int i = 0; i < 8; i++)
/* 452:    */     {
/* 453:347 */       this.column = ((byte)(this.column + 1));
/* 454:348 */       if (this.column > 7)
/* 455:    */       {
/* 456:349 */         if (this.pulses > 0) {
/* 457:349 */           this.pulses -= 1;
/* 458:    */         }
/* 459:350 */         this.column = 0;
/* 460:    */       }
/* 461:352 */       for (int a = 0; a < 5; a++)
/* 462:    */       {
/* 463:353 */         ur ct = this.contents[(a * 8 + this.column)];
/* 464:354 */         if ((ct != null) && (ct.a != 0)) {
/* 465:355 */           return;
/* 466:    */         }
/* 467:    */       }
/* 468:    */     }
/* 469:358 */     this.column = 0;
/* 470:    */   }
/* 471:    */   
/* 472:    */   private void checkColumn()
/* 473:    */   {
/* 474:362 */     for (int a = 0; a < 5; a++)
/* 475:    */     {
/* 476:363 */       ur ct = this.contents[(a * 8 + this.column)];
/* 477:364 */       if ((ct != null) && (ct.a != 0)) {
/* 478:365 */         return;
/* 479:    */       }
/* 480:    */     }
/* 481:367 */     stepColumn();
/* 482:368 */     dirtyBlock();
/* 483:    */   }
/* 484:    */   
/* 485:    */   protected boolean handleExtract(la inv, int start, int len)
/* 486:    */   {
/* 487:373 */     if (this.cond.getVoltage() < 60.0D) {
/* 488:373 */       return false;
/* 489:    */     }
/* 490:375 */     if (this.filterMap == null) {
/* 491:375 */       regenFilterMap();
/* 492:    */     }
/* 493:376 */     if (this.filterMap.size() == 0)
/* 494:    */     {
/* 495:377 */       ur ist = MachineLib.collectOneStack(inv, start, len, null);
/* 496:379 */       if (ist == null) {
/* 497:379 */         return false;
/* 498:    */       }
/* 499:381 */       if ((this.mode == 4) || (this.mode == 6)) {
/* 500:381 */         this.buffer.addNewColor(ist, this.defcolor);
/* 501:    */       } else {
/* 502:382 */         this.buffer.addNew(ist);
/* 503:    */       }
/* 504:384 */       this.cond.drawPower(25 * ist.a);
/* 505:385 */       drainBuffer();
/* 506:386 */       return true;
/* 507:    */     }
/* 508:    */     int sm;
/* 509:    */     ur coll;
/* 510:    */     ur coll;
/* 511:    */     ur coll;
/* 512:392 */     switch (this.mode)
/* 513:    */     {
/* 514:    */     case 0: 
/* 515:394 */       checkColumn();
/* 516:395 */       sm = MachineLib.matchAnyStackCol(this.filterMap, inv, start, len, this.column);
/* 517:397 */       if (sm < 0) {
/* 518:397 */         return false;
/* 519:    */       }
/* 520:398 */       coll = MachineLib.collectOneStack(inv, start, len, this.contents[sm]);
/* 521:    */       
/* 522:400 */       this.buffer.addNewColor(coll, this.colors[(sm & 0x7)]);
/* 523:401 */       this.cond.drawPower(25 * coll.a);
/* 524:402 */       stepColumn();
/* 525:403 */       drainBuffer();
/* 526:404 */       return true;
/* 527:    */     case 1: 
/* 528:407 */       checkColumn();
/* 529:408 */       if (!MachineLib.matchAllCol(this.filterMap, inv, start, len, this.column)) {
/* 530:410 */         return false;
/* 531:    */       }
/* 532:411 */       for (int n = 0; n < 5; n++)
/* 533:    */       {
/* 534:412 */         ur match = this.contents[(n * 8 + this.column)];
/* 535:413 */         if ((match != null) && (match.a != 0))
/* 536:    */         {
/* 537:414 */           coll = MachineLib.collectOneStack(inv, start, len, match);
/* 538:    */           
/* 539:416 */           this.buffer.addNewColor(coll, this.colors[this.column]);
/* 540:417 */           this.cond.drawPower(25 * coll.a);
/* 541:    */         }
/* 542:    */       }
/* 543:419 */       stepColumn();
/* 544:420 */       drainBuffer();
/* 545:421 */       return true;
/* 546:    */     case 2: 
/* 547:424 */       for (sm = 0; sm < 8; sm++) {
/* 548:425 */         if (MachineLib.matchAllCol(this.filterMap, inv, start, len, sm)) {
/* 549:    */           break;
/* 550:    */         }
/* 551:    */       }
/* 552:429 */       if (sm == 8) {
/* 553:429 */         return false;
/* 554:    */       }
/* 555:430 */       for (int n = 0; n < 5; n++)
/* 556:    */       {
/* 557:431 */         ur match = this.contents[(n * 8 + sm)];
/* 558:432 */         if ((match != null) && (match.a != 0))
/* 559:    */         {
/* 560:433 */           ur coll = MachineLib.collectOneStack(inv, start, len, match);
/* 561:    */           
/* 562:435 */           this.channelBuffers[sm].addNewColor(coll, this.colors[sm]);
/* 563:436 */           this.cond.drawPower(25 * coll.a);
/* 564:    */         }
/* 565:    */       }
/* 566:438 */       if (this.pulses > 0) {
/* 567:438 */         this.pulses -= 1;
/* 568:    */       }
/* 569:439 */       drainBuffer();
/* 570:440 */       return true;
/* 571:    */     case 3: 
/* 572:443 */       sm = MachineLib.matchAnyStack(this.filterMap, inv, start, len);
/* 573:444 */       if (sm < 0) {
/* 574:444 */         return false;
/* 575:    */       }
/* 576:445 */       coll = MachineLib.collectOneStack(inv, start, len, this.contents[sm]);
/* 577:    */       
/* 578:447 */       this.channelBuffers[(sm & 0x7)].addNewColor(coll, this.colors[(sm & 0x7)]);
/* 579:448 */       this.cond.drawPower(25 * coll.a);
/* 580:449 */       if (this.pulses > 0) {
/* 581:449 */         this.pulses -= 1;
/* 582:    */       }
/* 583:450 */       drainBuffer();
/* 584:451 */       return true;
/* 585:    */     case 4: 
/* 586:453 */       sm = MachineLib.matchAnyStack(this.filterMap, inv, start, len);
/* 587:454 */       if (sm < 0)
/* 588:    */       {
/* 589:455 */         coll = MachineLib.collectOneStack(inv, start, len, null);
/* 590:457 */         if (coll == null) {
/* 591:457 */           return false;
/* 592:    */         }
/* 593:458 */         this.buffer.addNewColor(coll, this.defcolor);
/* 594:    */       }
/* 595:    */       else
/* 596:    */       {
/* 597:460 */         coll = MachineLib.collectOneStack(inv, start, len, this.contents[sm]);
/* 598:    */         
/* 599:462 */         this.channelBuffers[(sm & 0x7)].addNewColor(coll, this.colors[(sm & 0x7)]);
/* 600:    */       }
/* 601:465 */       this.cond.drawPower(25 * coll.a);
/* 602:466 */       if (this.pulses > 0) {
/* 603:466 */         this.pulses -= 1;
/* 604:    */       }
/* 605:467 */       drainBuffer();
/* 606:468 */       return true;
/* 607:    */     case 5: 
/* 608:470 */       sm = MachineLib.matchAnyStack(this.filterMap, inv, start, len);
/* 609:471 */       if (sm < 0) {
/* 610:471 */         return false;
/* 611:    */       }
/* 612:472 */       coll = MachineLib.collectOneStackFuzzy(inv, start, len, this.contents[sm]);
/* 613:    */       
/* 614:474 */       this.channelBuffers[(sm & 0x7)].addNewColor(coll, this.colors[(sm & 0x7)]);
/* 615:475 */       this.cond.drawPower(25 * coll.a);
/* 616:476 */       if (this.pulses > 0) {
/* 617:476 */         this.pulses -= 1;
/* 618:    */       }
/* 619:477 */       drainBuffer();
/* 620:478 */       return true;
/* 621:    */     case 6: 
/* 622:480 */       sm = MachineLib.matchAnyStack(this.filterMap, inv, start, len);
/* 623:    */       ur coll;
/* 624:481 */       if (sm < 0)
/* 625:    */       {
/* 626:482 */         coll = MachineLib.collectOneStack(inv, start, len, null);
/* 627:484 */         if (coll == null) {
/* 628:484 */           return false;
/* 629:    */         }
/* 630:485 */         this.buffer.addNewColor(coll, this.defcolor);
/* 631:    */       }
/* 632:    */       else
/* 633:    */       {
/* 634:487 */         coll = MachineLib.collectOneStackFuzzy(inv, start, len, this.contents[sm]);
/* 635:    */         
/* 636:489 */         this.channelBuffers[(sm & 0x7)].addNewColor(coll, this.colors[(sm & 0x7)]);
/* 637:    */       }
/* 638:492 */       this.cond.drawPower(25 * coll.a);
/* 639:493 */       if (this.pulses > 0) {
/* 640:493 */         this.pulses -= 1;
/* 641:    */       }
/* 642:494 */       drainBuffer();
/* 643:495 */       return true;
/* 644:    */     }
/* 645:497 */     return false;
/* 646:    */   }
/* 647:    */   
/* 648:    */   protected boolean suckFilter(ur ist)
/* 649:    */   {
/* 650:502 */     if (this.cond.getVoltage() < 60.0D) {
/* 651:502 */       return false;
/* 652:    */     }
/* 653:503 */     if (this.filterMap == null) {
/* 654:503 */       regenFilterMap();
/* 655:    */     }
/* 656:505 */     int cm = getColumnMatch(ist);
/* 657:506 */     TubeBuffer buf = this.buffer;
/* 658:507 */     if ((cm >= 0) && (this.mode > 1)) {
/* 659:508 */       buf = this.channelBuffers[cm];
/* 660:    */     }
/* 661:509 */     if (buf.plugged) {
/* 662:509 */       return false;
/* 663:    */     }
/* 664:510 */     if (cm < 0)
/* 665:    */     {
/* 666:511 */       if ((this.mode == 4) || (this.mode == 6) || (cm == -2))
/* 667:    */       {
/* 668:512 */         this.cond.drawPower(25 * ist.a);
/* 669:513 */         return true;
/* 670:    */       }
/* 671:515 */       return false;
/* 672:    */     }
/* 673:518 */     this.cond.drawPower(25 * ist.a);
/* 674:519 */     return true;
/* 675:    */   }
/* 676:    */   
/* 677:    */   public int k_()
/* 678:    */   {
/* 679:525 */     return 40;
/* 680:    */   }
/* 681:    */   
/* 682:    */   public ur a(int i)
/* 683:    */   {
/* 684:529 */     return this.contents[i];
/* 685:    */   }
/* 686:    */   
/* 687:    */   public ur a(int i, int j)
/* 688:    */   {
/* 689:534 */     if (this.contents[i] == null) {
/* 690:534 */       return null;
/* 691:    */     }
/* 692:536 */     if (this.contents[i].a <= j)
/* 693:    */     {
/* 694:537 */       ur tr = this.contents[i];
/* 695:538 */       this.contents[i] = null;
/* 696:539 */       d();
/* 697:540 */       return tr;
/* 698:    */     }
/* 699:542 */     ur tr = this.contents[i].a(j);
/* 700:543 */     if (this.contents[i].a == 0) {
/* 701:544 */       this.contents[i] = null;
/* 702:    */     }
/* 703:545 */     d();
/* 704:546 */     return tr;
/* 705:    */   }
/* 706:    */   
/* 707:    */   public ur a_(int i)
/* 708:    */   {
/* 709:550 */     if (this.contents[i] == null) {
/* 710:550 */       return null;
/* 711:    */     }
/* 712:551 */     ur ist = this.contents[i];
/* 713:552 */     this.contents[i] = null;
/* 714:553 */     return ist;
/* 715:    */   }
/* 716:    */   
/* 717:    */   public void a(int i, ur ist)
/* 718:    */   {
/* 719:557 */     this.contents[i] = ist;
/* 720:558 */     if ((ist != null) && (ist.a > c())) {
/* 721:559 */       ist.a = c();
/* 722:    */     }
/* 723:560 */     d();
/* 724:    */   }
/* 725:    */   
/* 726:    */   public String b()
/* 727:    */   {
/* 728:564 */     return "Sorter";
/* 729:    */   }
/* 730:    */   
/* 731:    */   public int c()
/* 732:    */   {
/* 733:568 */     return 64;
/* 734:    */   }
/* 735:    */   
/* 736:    */   public boolean a_(qx player)
/* 737:    */   {
/* 738:572 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 739:573 */       return false;
/* 740:    */     }
/* 741:574 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 742:    */   }
/* 743:    */   
/* 744:    */   public void d()
/* 745:    */   {
/* 746:579 */     this.filterMap = null;
/* 747:580 */     super.d();
/* 748:    */   }
/* 749:    */   
/* 750:    */   public void f() {}
/* 751:    */   
/* 752:    */   public void l_() {}
/* 753:    */   
/* 754:    */   public void a(bq tag)
/* 755:    */   {
/* 756:590 */     super.a(tag);
/* 757:    */     
/* 758:592 */     by items = tag.m("Items");
/* 759:593 */     this.contents = new ur[k_()];
/* 760:594 */     for (int i = 0; i < items.c(); i++)
/* 761:    */     {
/* 762:595 */       bq item = (bq)items.b(i);
/* 763:    */       
/* 764:597 */       int j = item.c("Slot") & 0xFF;
/* 765:598 */       if ((j >= 0) && (j < this.contents.length)) {
/* 766:599 */         this.contents[j] = ur.a(item);
/* 767:    */       }
/* 768:    */     }
/* 769:602 */     this.column = tag.c("coln");
/* 770:603 */     byte[] cols = tag.j("cols");
/* 771:604 */     if (cols.length >= 8) {
/* 772:605 */       for (int i = 0; i < 8; i++) {
/* 773:606 */         this.colors[i] = cols[i];
/* 774:    */       }
/* 775:    */     }
/* 776:608 */     this.mode = tag.c("mode");
/* 777:609 */     this.automode = tag.c("amode");
/* 778:610 */     this.draining = tag.c("drain");
/* 779:611 */     if ((this.mode == 4) || (this.mode == 6)) {
/* 780:611 */       this.defcolor = tag.c("defc");
/* 781:    */     }
/* 782:612 */     this.pulses = tag.e("pulses");
/* 783:    */     
/* 784:614 */     this.cond.readFromNBT(tag);
/* 785:    */     
/* 786:616 */     by bufs = tag.m("buffers");
/* 787:617 */     for (int i = 0; i < bufs.c(); i++)
/* 788:    */     {
/* 789:618 */       bq buf = (bq)bufs.b(i);
/* 790:619 */       this.channelBuffers[i].readFromNBT(buf);
/* 791:    */     }
/* 792:    */   }
/* 793:    */   
/* 794:    */   public void b(bq tag)
/* 795:    */   {
/* 796:624 */     super.b(tag);
/* 797:    */     
/* 798:626 */     by items = new by();
/* 799:627 */     for (int i = 0; i < this.contents.length; i++) {
/* 800:628 */       if (this.contents[i] != null)
/* 801:    */       {
/* 802:629 */         bq item = new bq();
/* 803:630 */         item.a("Slot", (byte)i);
/* 804:631 */         this.contents[i].b(item);
/* 805:632 */         items.a(item);
/* 806:    */       }
/* 807:    */     }
/* 808:635 */     tag.a("coln", this.column);
/* 809:636 */     tag.a("Items", items);
/* 810:637 */     tag.a("cols", this.colors);
/* 811:638 */     tag.a("mode", this.mode);
/* 812:639 */     tag.a("amode", this.automode);
/* 813:640 */     tag.a("drain", this.draining);
/* 814:641 */     tag.a("pulses", this.pulses);
/* 815:642 */     if ((this.mode == 4) || (this.mode == 6)) {
/* 816:642 */       tag.a("defc", this.defcolor);
/* 817:    */     }
/* 818:644 */     this.cond.writeToNBT(tag);
/* 819:    */     
/* 820:646 */     by bufs = new by();
/* 821:647 */     for (int i = 0; i < 8; i++)
/* 822:    */     {
/* 823:648 */       bq buf = new bq();
/* 824:649 */       this.channelBuffers[i].writeToNBT(buf);
/* 825:650 */       bufs.a(buf);
/* 826:    */     }
/* 827:652 */     tag.a("buffers", bufs);
/* 828:    */   }
/* 829:    */   
/* 830:655 */   public int ConMask = -1;
/* 831:    */   private ur[] contents;
/* 832:    */   public byte[] colors;
/* 833:659 */   public byte mode = 0;
/* 834:659 */   public byte automode = 0;
/* 835:659 */   public byte defcolor = 0;
/* 836:659 */   public byte draining = -1;
/* 837:660 */   public byte column = 0;
/* 838:661 */   public int pulses = 0;
/* 839:663 */   protected MachineLib.FilterMap filterMap = null;
/* 840:    */   TubeBuffer[] channelBuffers;
/* 841:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileSorter
 * JD-Core Version:    0.7.0.1
 */