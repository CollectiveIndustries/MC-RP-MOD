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
/*  11:    */ import com.eloraam.redpower.core.ITubeConnectable;
/*  12:    */ import com.eloraam.redpower.core.ITubeRequest;
/*  13:    */ import com.eloraam.redpower.core.MachineLib;
/*  14:    */ import com.eloraam.redpower.core.MachineLib.FilterMap;
/*  15:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  16:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  17:    */ import com.eloraam.redpower.core.TubeItem;
/*  18:    */ import com.eloraam.redpower.core.TubeLib.RequestRouteFinder;
/*  19:    */ import com.eloraam.redpower.core.WorldCoord;
/*  20:    */ import la;
/*  21:    */ import net.minecraftforge.common.ForgeDirection;
/*  22:    */ import net.minecraftforge.common.ISidedInventory;
/*  23:    */ import qx;
/*  24:    */ import ur;
/*  25:    */ import yc;
/*  26:    */ 
/*  27:    */ public class TileManager
/*  28:    */   extends TileMachine
/*  29:    */   implements IBluePowerConnectable, la, ISidedInventory, ITubeConnectable, ITubeRequest
/*  30:    */ {
/*  31:    */   public TileManager()
/*  32:    */   {
/*  33: 26 */     this.contents = new ur[24];
/*  34:    */   }
/*  35:    */   
/*  36:    */   public int getConnectableMask()
/*  37:    */   {
/*  38: 32 */     return 1073741823;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getConnectClass(int side)
/*  42:    */   {
/*  43: 36 */     return 65;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public int getCornerPowerMode()
/*  47:    */   {
/*  48: 39 */     return 0;
/*  49:    */   }
/*  50:    */   
/*  51: 43 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  52:    */   {
/*  53:    */     public any getParent()
/*  54:    */     {
/*  55: 45 */       return TileManager.this;
/*  56:    */     }
/*  57:    */   };
/*  58:    */   
/*  59:    */   public BluePowerConductor getBlueConductor(int side)
/*  60:    */   {
/*  61: 50 */     return this.cond;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public int getStartInventorySide(ForgeDirection side)
/*  65:    */   {
/*  66: 57 */     return 0;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public int getSizeInventorySide(ForgeDirection fd)
/*  70:    */   {
/*  71: 62 */     int side = fd.ordinal();
/*  72: 63 */     if ((side == this.Rotation) || (side == (this.Rotation ^ 0x1))) {
/*  73: 64 */       return 0;
/*  74:    */     }
/*  75: 65 */     return 24;
/*  76:    */   }
/*  77:    */   
/*  78:    */   protected la getConnectedInventory(boolean push)
/*  79:    */   {
/*  80: 71 */     WorldCoord pos = new WorldCoord(this);
/*  81: 72 */     pos.step(this.Rotation ^ 0x1);
/*  82: 73 */     return MachineLib.getSideInventory(this.k, pos, this.Rotation, push);
/*  83:    */   }
/*  84:    */   
/*  85:    */   protected void regenFilterMap()
/*  86:    */   {
/*  87: 77 */     this.filterMap = MachineLib.makeFilterMap(this.contents, 0, 24);
/*  88:    */   }
/*  89:    */   
/*  90:    */   protected int[] getAcceptCounts()
/*  91:    */   {
/*  92: 81 */     if (this.filterMap == null) {
/*  93: 81 */       regenFilterMap();
/*  94:    */     }
/*  95: 82 */     if (this.filterMap.size() == 0) {
/*  96: 82 */       return null;
/*  97:    */     }
/*  98: 84 */     la inv = getConnectedInventory(true);
/*  99: 85 */     if (inv == null) {
/* 100: 85 */       return null;
/* 101:    */     }
/* 102: 87 */     int[] tr = MachineLib.genMatchCounts(this.filterMap);
/* 103: 88 */     MachineLib.decMatchCounts(this.filterMap, tr, inv, 0, inv.k_());
/* 104:    */     
/* 105: 90 */     return tr;
/* 106:    */   }
/* 107:    */   
/* 108:    */   protected int acceptCount(ur ist)
/* 109:    */   {
/* 110: 94 */     if (this.filterMap == null) {
/* 111: 94 */       regenFilterMap();
/* 112:    */     }
/* 113: 95 */     if (this.filterMap.size() == 0) {
/* 114: 95 */       return 0;
/* 115:    */     }
/* 116: 96 */     if (!this.filterMap.containsKey(ist)) {
/* 117: 96 */       return 0;
/* 118:    */     }
/* 119: 98 */     int[] match = getAcceptCounts();
/* 120: 99 */     if (match == null) {
/* 121: 99 */       return 0;
/* 122:    */     }
/* 123:100 */     return MachineLib.getMatchCount(this.filterMap, match, ist);
/* 124:    */   }
/* 125:    */   
/* 126:    */   protected void doRequest(int slot, int num)
/* 127:    */   {
/* 128:104 */     ur rq = CoreLib.copyStack(this.contents[slot], Math.min(64, num));
/* 129:    */     
/* 130:106 */     TubeItem tir = new TubeItem(0, rq);
/* 131:107 */     tir.priority = ((short)this.priority);
/* 132:108 */     tir.color = this.color;
/* 133:    */     
/* 134:110 */     TubeLib.RequestRouteFinder rrf = new TubeLib.RequestRouteFinder(this.k, tir);
/* 135:112 */     if (rrf.find(new WorldCoord(this), 63) < 0) {
/* 136:113 */       return;
/* 137:    */     }
/* 138:114 */     WorldCoord wc = rrf.getResultPoint();
/* 139:115 */     ITubeRequest itr = (ITubeRequest)CoreLib.getTileEntity(this.k, wc, ITubeRequest.class);
/* 140:    */     
/* 141:117 */     itr.requestTubeItem(tir, true);
/* 142:    */     
/* 143:119 */     this.cond.drawPower(100.0D);
/* 144:120 */     scheduleTick(20);
/* 145:    */   }
/* 146:    */   
/* 147:    */   protected void scanInventory()
/* 148:    */   {
/* 149:124 */     la inv = getConnectedInventory(false);
/* 150:125 */     if (inv == null) {
/* 151:125 */       return;
/* 152:    */     }
/* 153:126 */     if (this.filterMap == null) {
/* 154:126 */       regenFilterMap();
/* 155:    */     }
/* 156:127 */     int[] ac = MachineLib.genMatchCounts(this.filterMap);
/* 157:128 */     if (ac == null) {
/* 158:128 */       return;
/* 159:    */     }
/* 160:130 */     for (int i = 0; i < inv.k_(); i++)
/* 161:    */     {
/* 162:131 */       ur ist = inv.a(i);
/* 163:132 */       if ((ist != null) && (ist.a != 0)) {
/* 164:134 */         if (this.mode == 0)
/* 165:    */         {
/* 166:135 */           int mc = MachineLib.decMatchCount(this.filterMap, ac, ist);
/* 167:137 */           if (mc < ist.a)
/* 168:    */           {
/* 169:138 */             ur rem = inv.a(i, ist.a - mc);
/* 170:    */             
/* 171:140 */             this.cond.drawPower(25 * ist.a);
/* 172:141 */             this.buffer.addNewColor(rem, this.color);
/* 173:    */             
/* 174:143 */             this.Active = true;
/* 175:144 */             scheduleTick(5);
/* 176:145 */             updateBlock();
/* 177:146 */             return;
/* 178:    */           }
/* 179:    */         }
/* 180:148 */         else if ((this.mode == 1) && 
/* 181:149 */           (!this.filterMap.containsKey(ist)))
/* 182:    */         {
/* 183:150 */           inv.a(i, null);
/* 184:    */           
/* 185:152 */           this.cond.drawPower(25 * ist.a);
/* 186:153 */           this.buffer.addNewColor(ist, this.color);
/* 187:154 */           this.Active = true;
/* 188:155 */           scheduleTick(5);
/* 189:156 */           updateBlock();
/* 190:157 */           return;
/* 191:    */         }
/* 192:    */       }
/* 193:    */     }
/* 194:161 */     boolean hrs = false;
/* 195:162 */     if (this.mode == 0)
/* 196:    */     {
/* 197:163 */       ac = getAcceptCounts();
/* 198:164 */       if (ac != null)
/* 199:    */       {
/* 200:165 */         hrs = true;
/* 201:166 */         this.rqnum = ((byte)(this.rqnum + 1));
/* 202:166 */         if (this.rqnum >= 24) {
/* 203:166 */           this.rqnum = 0;
/* 204:    */         }
/* 205:167 */         for (int n = this.rqnum; n < ac.length; n++) {
/* 206:168 */           if (ac[n] != 0)
/* 207:    */           {
/* 208:169 */             hrs = false;
/* 209:170 */             doRequest(n, ac[n]);
/* 210:171 */             break;
/* 211:    */           }
/* 212:    */         }
/* 213:174 */         for (int n = 0; n < this.rqnum; n++) {
/* 214:175 */           if (ac[n] != 0)
/* 215:    */           {
/* 216:176 */             hrs = false;
/* 217:177 */             doRequest(n, ac[n]);
/* 218:178 */             break;
/* 219:    */           }
/* 220:    */         }
/* 221:    */       }
/* 222:    */     }
/* 223:183 */     if (this.Powered != hrs)
/* 224:    */     {
/* 225:184 */       this.Powered = hrs;
/* 226:185 */       updateBlockChange();
/* 227:    */     }
/* 228:    */   }
/* 229:    */   
/* 230:    */   public int getTubeConnectableSides()
/* 231:    */   {
/* 232:192 */     return 1 << this.Rotation;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public int getTubeConClass()
/* 236:    */   {
/* 237:196 */     return 0;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public boolean canRouteItems()
/* 241:    */   {
/* 242:200 */     return false;
/* 243:    */   }
/* 244:    */   
/* 245:    */   private boolean handleTubeItem(TubeItem ti)
/* 246:    */   {
/* 247:204 */     if (this.cond.getVoltage() < 60.0D) {
/* 248:204 */       return false;
/* 249:    */     }
/* 250:205 */     if (ti.priority > this.priority) {
/* 251:205 */       return false;
/* 252:    */     }
/* 253:206 */     if ((ti.color != this.color) && (this.color != 0) && (ti.color != 0)) {
/* 254:206 */       return false;
/* 255:    */     }
/* 256:208 */     if (this.mode == 1)
/* 257:    */     {
/* 258:209 */       if (this.filterMap == null) {
/* 259:209 */         regenFilterMap();
/* 260:    */       }
/* 261:210 */       if (this.filterMap.size() == 0) {
/* 262:210 */         return false;
/* 263:    */       }
/* 264:211 */       if (!this.filterMap.containsKey(ti.item)) {
/* 265:211 */         return false;
/* 266:    */       }
/* 267:213 */       la dest = getConnectedInventory(true);
/* 268:214 */       if (MachineLib.addToInventoryCore(dest, ti.item, 0, dest.k_(), true))
/* 269:    */       {
/* 270:216 */         this.Delay = true;
/* 271:217 */         scheduleTick(5);
/* 272:218 */         updateBlock();
/* 273:219 */         return true;
/* 274:    */       }
/* 275:221 */       return false;
/* 276:    */     }
/* 277:224 */     int mc = acceptCount(ti.item);
/* 278:225 */     if (mc == 0) {
/* 279:225 */       return false;
/* 280:    */     }
/* 281:227 */     boolean tr = true;
/* 282:228 */     ur ist = ti.item;
/* 283:229 */     if (mc < ist.a)
/* 284:    */     {
/* 285:230 */       tr = false;
/* 286:231 */       ist = ist.a(mc);
/* 287:    */     }
/* 288:233 */     la dest = getConnectedInventory(true);
/* 289:234 */     if (MachineLib.addToInventoryCore(dest, ist, 0, dest.k_(), true))
/* 290:    */     {
/* 291:236 */       this.Delay = true;
/* 292:237 */       scheduleTick(5);
/* 293:238 */       updateBlock();
/* 294:239 */       return tr;
/* 295:    */     }
/* 296:241 */     return false;
/* 297:    */   }
/* 298:    */   
/* 299:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/* 300:    */   {
/* 301:245 */     if (side != this.Rotation) {
/* 302:245 */       return false;
/* 303:    */     }
/* 304:246 */     if (state == 2)
/* 305:    */     {
/* 306:247 */       if (handleTubeItem(ti)) {
/* 307:247 */         return true;
/* 308:    */       }
/* 309:249 */       this.buffer.addBounce(ti);
/* 310:250 */       this.Active = true;
/* 311:251 */       updateBlock();
/* 312:252 */       scheduleTick(5);
/* 313:253 */       return true;
/* 314:    */     }
/* 315:256 */     return handleTubeItem(ti);
/* 316:    */   }
/* 317:    */   
/* 318:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/* 319:    */   {
/* 320:260 */     if (side != this.Rotation) {
/* 321:260 */       return false;
/* 322:    */     }
/* 323:261 */     if (state == 2) {
/* 324:261 */       return true;
/* 325:    */     }
/* 326:263 */     if (this.cond.getVoltage() < 60.0D) {
/* 327:263 */       return false;
/* 328:    */     }
/* 329:264 */     if (ti.priority > this.priority) {
/* 330:264 */       return false;
/* 331:    */     }
/* 332:265 */     if ((ti.color != this.color) && (this.color != 0) && (ti.color != 0)) {
/* 333:265 */       return false;
/* 334:    */     }
/* 335:267 */     switch (this.mode)
/* 336:    */     {
/* 337:    */     case 0: 
/* 338:269 */       return acceptCount(ti.item) > 0;
/* 339:    */     case 1: 
/* 340:271 */       if (this.filterMap == null) {
/* 341:271 */         regenFilterMap();
/* 342:    */       }
/* 343:272 */       if (this.filterMap.size() == 0) {
/* 344:272 */         return false;
/* 345:    */       }
/* 346:273 */       return this.filterMap.containsKey(ti.item);
/* 347:    */     }
/* 348:274 */     return false;
/* 349:    */   }
/* 350:    */   
/* 351:    */   public int tubeWeight(int side, int state)
/* 352:    */   {
/* 353:279 */     if ((side == this.Rotation) && (state == 2)) {
/* 354:280 */       return this.buffer.size();
/* 355:    */     }
/* 356:281 */     return 0;
/* 357:    */   }
/* 358:    */   
/* 359:    */   public boolean requestTubeItem(TubeItem rq, boolean act)
/* 360:    */   {
/* 361:287 */     if (this.Active) {
/* 362:287 */       return false;
/* 363:    */     }
/* 364:288 */     if (this.cond.getVoltage() < 60.0D) {
/* 365:288 */       return false;
/* 366:    */     }
/* 367:290 */     if (this.filterMap == null) {
/* 368:290 */       regenFilterMap();
/* 369:    */     }
/* 370:291 */     if (this.filterMap.size() == 0) {
/* 371:291 */       return false;
/* 372:    */     }
/* 373:292 */     if (!this.filterMap.containsKey(rq.item)) {
/* 374:292 */       return false;
/* 375:    */     }
/* 376:294 */     if (rq.priority <= this.priority) {
/* 377:294 */       return false;
/* 378:    */     }
/* 379:295 */     if ((rq.color != this.color) && (this.color > 0)) {
/* 380:295 */       return false;
/* 381:    */     }
/* 382:296 */     la inv = getConnectedInventory(false);
/* 383:297 */     if (inv == null) {
/* 384:297 */       return false;
/* 385:    */     }
/* 386:299 */     for (int i = 0; i < inv.k_(); i++)
/* 387:    */     {
/* 388:300 */       ur is2 = inv.a(i);
/* 389:301 */       if ((is2 != null) && (is2.a != 0)) {
/* 390:303 */         if (CoreLib.compareItemStack(rq.item, is2) == 0)
/* 391:    */         {
/* 392:305 */           if (act)
/* 393:    */           {
/* 394:306 */             ur pull = inv.a(i, Math.min(rq.item.a, is2.a));
/* 395:    */             
/* 396:    */ 
/* 397:309 */             TubeItem ti = new TubeItem(0, pull);
/* 398:310 */             this.cond.drawPower(25 * ti.item.a);
/* 399:    */             
/* 400:312 */             ti.priority = rq.priority;
/* 401:313 */             ti.color = this.color;
/* 402:314 */             this.buffer.add(ti);
/* 403:315 */             this.Active = true;
/* 404:316 */             scheduleTick(5);
/* 405:317 */             updateBlock();
/* 406:    */           }
/* 407:319 */           return true;
/* 408:    */         }
/* 409:    */       }
/* 410:    */     }
/* 411:321 */     return false;
/* 412:    */   }
/* 413:    */   
/* 414:    */   public void g()
/* 415:    */   {
/* 416:327 */     super.g();
/* 417:328 */     if (CoreLib.isClient(this.k)) {
/* 418:328 */       return;
/* 419:    */     }
/* 420:329 */     if (!isTickScheduled()) {
/* 421:330 */       scheduleTick(10);
/* 422:    */     }
/* 423:332 */     if (this.ConMask < 0)
/* 424:    */     {
/* 425:333 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 426:    */       
/* 427:335 */       this.cond.recache(this.ConMask, 0);
/* 428:    */     }
/* 429:337 */     this.cond.iterate();
/* 430:338 */     dirtyBlock();
/* 431:340 */     if (this.cond.Flow == 0)
/* 432:    */     {
/* 433:341 */       if (this.Charged)
/* 434:    */       {
/* 435:342 */         this.Charged = false;
/* 436:343 */         updateBlock();
/* 437:    */       }
/* 438:    */     }
/* 439:345 */     else if (!this.Charged)
/* 440:    */     {
/* 441:346 */       this.Charged = true;
/* 442:347 */       updateBlock();
/* 443:    */     }
/* 444:    */   }
/* 445:    */   
/* 446:    */   public boolean isPoweringTo(int side)
/* 447:    */   {
/* 448:353 */     if (side == (this.Rotation ^ 0x1)) {
/* 449:353 */       return false;
/* 450:    */     }
/* 451:354 */     return this.Powered;
/* 452:    */   }
/* 453:    */   
/* 454:    */   public int getBlockID()
/* 455:    */   {
/* 456:360 */     return RedPowerMachine.blockMachine2.cm;
/* 457:    */   }
/* 458:    */   
/* 459:    */   public int getExtendedID()
/* 460:    */   {
/* 461:364 */     return 1;
/* 462:    */   }
/* 463:    */   
/* 464:    */   public boolean onBlockActivated(qx player)
/* 465:    */   {
/* 466:368 */     if (player.ah()) {
/* 467:368 */       return false;
/* 468:    */     }
/* 469:369 */     if (CoreLib.isClient(this.k)) {
/* 470:370 */       return true;
/* 471:    */     }
/* 472:371 */     player.openGui(RedPowerMachine.instance, 16, this.k, this.l, this.m, this.n);
/* 473:    */     
/* 474:373 */     return true;
/* 475:    */   }
/* 476:    */   
/* 477:    */   public void onBlockNeighborChange(int l)
/* 478:    */   {
/* 479:377 */     this.ConMask = -1;
/* 480:    */   }
/* 481:    */   
/* 482:    */   public void drainBuffer()
/* 483:    */   {
/* 484:381 */     while (!this.buffer.isEmpty())
/* 485:    */     {
/* 486:382 */       TubeItem ti = this.buffer.getLast();
/* 487:383 */       if (handleTubeItem(ti))
/* 488:    */       {
/* 489:384 */         this.buffer.pop();
/* 490:385 */         if (!this.buffer.plugged) {}
/* 491:    */       }
/* 492:    */       else
/* 493:    */       {
/* 494:389 */         if (!handleItem(ti))
/* 495:    */         {
/* 496:390 */           this.buffer.plugged = true;
/* 497:391 */           return;
/* 498:    */         }
/* 499:393 */         this.buffer.pop();
/* 500:394 */         if (this.buffer.plugged) {
/* 501:395 */           return;
/* 502:    */         }
/* 503:    */       }
/* 504:    */     }
/* 505:    */   }
/* 506:    */   
/* 507:    */   public void onTileTick()
/* 508:    */   {
/* 509:401 */     if (CoreLib.isClient(this.k)) {
/* 510:402 */       return;
/* 511:    */     }
/* 512:403 */     boolean r = false;
/* 513:404 */     if (this.Delay)
/* 514:    */     {
/* 515:405 */       this.Delay = false;
/* 516:406 */       r = true;
/* 517:    */     }
/* 518:408 */     if (this.Active)
/* 519:    */     {
/* 520:409 */       if (!this.buffer.isEmpty())
/* 521:    */       {
/* 522:410 */         drainBuffer();
/* 523:411 */         if (!this.buffer.isEmpty()) {
/* 524:411 */           scheduleTick(10);
/* 525:    */         } else {
/* 526:412 */           scheduleTick(5);
/* 527:    */         }
/* 528:413 */         return;
/* 529:    */       }
/* 530:415 */       this.Active = false;
/* 531:416 */       updateBlock();
/* 532:417 */       return;
/* 533:    */     }
/* 534:419 */     if (r)
/* 535:    */     {
/* 536:419 */       updateBlock();return;
/* 537:    */     }
/* 538:421 */     if (this.cond.getVoltage() < 60.0D) {
/* 539:421 */       return;
/* 540:    */     }
/* 541:422 */     scanInventory();
/* 542:    */   }
/* 543:    */   
/* 544:    */   public void onBlockRemoval()
/* 545:    */   {
/* 546:426 */     super.onBlockRemoval();
/* 547:428 */     for (int i = 0; i < this.contents.length; i++)
/* 548:    */     {
/* 549:429 */       ur ist = this.contents[i];
/* 550:430 */       if ((ist != null) && (ist.a > 0)) {
/* 551:431 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 552:    */       }
/* 553:    */     }
/* 554:    */   }
/* 555:    */   
/* 556:    */   public int k_()
/* 557:    */   {
/* 558:440 */     return 24;
/* 559:    */   }
/* 560:    */   
/* 561:    */   public ur a(int i)
/* 562:    */   {
/* 563:444 */     return this.contents[i];
/* 564:    */   }
/* 565:    */   
/* 566:    */   public ur a(int i, int j)
/* 567:    */   {
/* 568:449 */     if (this.contents[i] == null) {
/* 569:449 */       return null;
/* 570:    */     }
/* 571:451 */     if (this.contents[i].a <= j)
/* 572:    */     {
/* 573:452 */       ur tr = this.contents[i];
/* 574:453 */       this.contents[i] = null;
/* 575:454 */       d();
/* 576:455 */       return tr;
/* 577:    */     }
/* 578:457 */     ur tr = this.contents[i].a(j);
/* 579:458 */     if (this.contents[i].a == 0) {
/* 580:459 */       this.contents[i] = null;
/* 581:    */     }
/* 582:460 */     d();
/* 583:461 */     return tr;
/* 584:    */   }
/* 585:    */   
/* 586:    */   public ur a_(int i)
/* 587:    */   {
/* 588:465 */     if (this.contents[i] == null) {
/* 589:465 */       return null;
/* 590:    */     }
/* 591:466 */     ur ist = this.contents[i];
/* 592:467 */     this.contents[i] = null;
/* 593:468 */     return ist;
/* 594:    */   }
/* 595:    */   
/* 596:    */   public void a(int i, ur ist)
/* 597:    */   {
/* 598:472 */     this.contents[i] = ist;
/* 599:473 */     if ((ist != null) && (ist.a > c())) {
/* 600:474 */       ist.a = c();
/* 601:    */     }
/* 602:475 */     d();
/* 603:    */   }
/* 604:    */   
/* 605:    */   public String b()
/* 606:    */   {
/* 607:479 */     return "Manager";
/* 608:    */   }
/* 609:    */   
/* 610:    */   public int c()
/* 611:    */   {
/* 612:483 */     return 64;
/* 613:    */   }
/* 614:    */   
/* 615:    */   public boolean a_(qx player)
/* 616:    */   {
/* 617:487 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 618:488 */       return false;
/* 619:    */     }
/* 620:489 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 621:    */   }
/* 622:    */   
/* 623:    */   public void d()
/* 624:    */   {
/* 625:494 */     this.filterMap = null;
/* 626:495 */     super.d();
/* 627:    */   }
/* 628:    */   
/* 629:    */   public void f() {}
/* 630:    */   
/* 631:    */   public void l_() {}
/* 632:    */   
/* 633:    */   public void a(bq tag)
/* 634:    */   {
/* 635:505 */     super.a(tag);
/* 636:506 */     this.cond.readFromNBT(tag);
/* 637:    */     
/* 638:508 */     by items = tag.m("Items");
/* 639:509 */     this.contents = new ur[k_()];
/* 640:510 */     for (int i = 0; i < items.c(); i++)
/* 641:    */     {
/* 642:511 */       bq item = (bq)items.b(i);
/* 643:    */       
/* 644:513 */       int j = item.c("Slot") & 0xFF;
/* 645:514 */       if ((j >= 0) && (j < this.contents.length)) {
/* 646:515 */         this.contents[j] = ur.a(item);
/* 647:    */       }
/* 648:    */     }
/* 649:518 */     this.color = tag.c("color");
/* 650:519 */     this.mode = tag.c("mode");
/* 651:520 */     this.priority = tag.e("prio");
/* 652:521 */     this.rqnum = tag.c("rqnum");
/* 653:522 */     this.buffer.readFromNBT(tag);
/* 654:    */   }
/* 655:    */   
/* 656:    */   public void b(bq tag)
/* 657:    */   {
/* 658:526 */     super.b(tag);
/* 659:527 */     this.cond.writeToNBT(tag);
/* 660:    */     
/* 661:529 */     by items = new by();
/* 662:530 */     for (int i = 0; i < this.contents.length; i++) {
/* 663:531 */       if (this.contents[i] != null)
/* 664:    */       {
/* 665:532 */         bq item = new bq();
/* 666:533 */         item.a("Slot", (byte)i);
/* 667:534 */         this.contents[i].b(item);
/* 668:535 */         items.a(item);
/* 669:    */       }
/* 670:    */     }
/* 671:538 */     tag.a("Items", items);
/* 672:539 */     tag.a("color", this.color);
/* 673:540 */     tag.a("mode", this.mode);
/* 674:541 */     tag.a("prio", this.priority);
/* 675:542 */     tag.a("rqnum", this.rqnum);
/* 676:543 */     this.buffer.writeToNBT(tag);
/* 677:    */   }
/* 678:    */   
/* 679:546 */   TubeBuffer buffer = new TubeBuffer();
/* 680:    */   protected ur[] contents;
/* 681:548 */   public int ConMask = -1;
/* 682:549 */   public byte color = 0;
/* 683:549 */   public byte mode = 0;
/* 684:550 */   public int priority = 0;
/* 685:551 */   public byte rqnum = 0;
/* 686:553 */   protected MachineLib.FilterMap filterMap = null;
/* 687:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileManager
 * JD-Core Version:    0.7.0.1
 */