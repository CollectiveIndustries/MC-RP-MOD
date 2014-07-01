/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.RedPowerBase;
/*   6:    */ import com.eloraam.redpower.RedPowerMachine;
/*   7:    */ import com.eloraam.redpower.base.ItemScrewdriver;
/*   8:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   9:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*  10:    */ import com.eloraam.redpower.core.CoreLib;
/*  11:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  12:    */ import com.eloraam.redpower.core.IRedbusConnectable;
/*  13:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  14:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  15:    */ import com.eloraam.redpower.core.TubeItem;
/*  16:    */ import la;
/*  17:    */ import px;
/*  18:    */ import qw;
/*  19:    */ import qx;
/*  20:    */ import up;
/*  21:    */ import ur;
/*  22:    */ 
/*  23:    */ public class TileSortron
/*  24:    */   extends TileTranspose
/*  25:    */   implements IBluePowerConnectable, IRedbusConnectable
/*  26:    */ {
/*  27:    */   public int getConnectableMask()
/*  28:    */   {
/*  29: 37 */     return 1073741823;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public int getConnectClass(int side)
/*  33:    */   {
/*  34: 41 */     return 67;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public int getCornerPowerMode()
/*  38:    */   {
/*  39: 44 */     return 0;
/*  40:    */   }
/*  41:    */   
/*  42: 48 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  43:    */   {
/*  44:    */     public any getParent()
/*  45:    */     {
/*  46: 50 */       return TileSortron.this;
/*  47:    */     }
/*  48:    */   };
/*  49:    */   
/*  50:    */   public BluePowerConductor getBlueConductor(int side)
/*  51:    */   {
/*  52: 55 */     return this.cond;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public int rbGetAddr()
/*  56:    */   {
/*  57: 61 */     return this.rbaddr;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void rbSetAddr(int addr)
/*  61:    */   {
/*  62: 65 */     this.rbaddr = addr;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public int rbRead(int reg)
/*  66:    */   {
/*  67: 69 */     switch (reg)
/*  68:    */     {
/*  69:    */     case 0: 
/*  70: 70 */       return this.command & 0xFF;
/*  71:    */     case 1: 
/*  72: 71 */       return this.itemQty & 0xFF;
/*  73:    */     case 2: 
/*  74: 72 */       return this.itemSlot & 0xFF;
/*  75:    */     case 3: 
/*  76: 73 */       return this.itemSlot >> 8 & 0xFF;
/*  77:    */     case 4: 
/*  78: 75 */       return this.itemType & 0xFF;
/*  79:    */     case 5: 
/*  80: 76 */       return this.itemType >> 8 & 0xFF;
/*  81:    */     case 6: 
/*  82: 77 */       return this.itemType >> 16 & 0xFF;
/*  83:    */     case 7: 
/*  84: 78 */       return this.itemType >> 24 & 0xFF;
/*  85:    */     case 8: 
/*  86: 80 */       return this.itemDamage & 0xFF;
/*  87:    */     case 9: 
/*  88: 81 */       return this.itemDamage >> 8 & 0xFF;
/*  89:    */     case 10: 
/*  90: 82 */       return this.itemDamageMax & 0xFF;
/*  91:    */     case 11: 
/*  92: 83 */       return this.itemDamageMax >> 8 & 0xFF;
/*  93:    */     case 12: 
/*  94: 85 */       return this.itemColor & 0xFF;
/*  95:    */     case 13: 
/*  96: 86 */       return this.itemInColor & 0xFF;
/*  97:    */     }
/*  98: 88 */     return 0;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void rbWrite(int reg, int dat)
/* 102:    */   {
/* 103: 93 */     dirtyBlock();
/* 104: 94 */     switch (reg)
/* 105:    */     {
/* 106:    */     case 0: 
/* 107: 95 */       this.command = dat;this.cmdDelay = 2; break;
/* 108:    */     case 1: 
/* 109: 96 */       this.itemQty = dat; break;
/* 110:    */     case 2: 
/* 111: 97 */       this.itemSlot = (this.itemSlot & 0xFF00 | dat); break;
/* 112:    */     case 3: 
/* 113: 98 */       this.itemSlot = (this.itemSlot & 0xFF | dat << 8); break;
/* 114:    */     case 4: 
/* 115:100 */       this.itemType = (this.itemType & 0xFFFFFF00 | dat); break;
/* 116:    */     case 5: 
/* 117:101 */       this.itemType = (this.itemType & 0xFFFF00FF | dat << 8); break;
/* 118:    */     case 6: 
/* 119:102 */       this.itemType = (this.itemType & 0xFF00FFFF | dat << 16); break;
/* 120:    */     case 7: 
/* 121:103 */       this.itemType = (this.itemType & 0xFFFFFF | dat << 24); break;
/* 122:    */     case 8: 
/* 123:105 */       this.itemDamage = (this.itemDamage & 0xFF00 | dat); break;
/* 124:    */     case 9: 
/* 125:106 */       this.itemDamage = (this.itemDamage & 0xFF | dat << 8); break;
/* 126:    */     case 10: 
/* 127:107 */       this.itemDamageMax = (this.itemDamageMax & 0xFF00 | dat); break;
/* 128:    */     case 11: 
/* 129:108 */       this.itemDamageMax = (this.itemDamageMax & 0xFF | dat << 8); break;
/* 130:    */     case 12: 
/* 131:110 */       this.itemColor = dat; break;
/* 132:    */     case 13: 
/* 133:111 */       this.itemInColor = dat;
/* 134:    */     }
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void g()
/* 138:    */   {
/* 139:119 */     super.g();
/* 140:120 */     if (CoreLib.isClient(this.k)) {
/* 141:120 */       return;
/* 142:    */     }
/* 143:122 */     if (this.ConMask < 0)
/* 144:    */     {
/* 145:123 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 146:    */       
/* 147:125 */       this.cond.recache(this.ConMask, 0);
/* 148:    */     }
/* 149:127 */     this.cond.iterate();
/* 150:128 */     dirtyBlock();
/* 151:130 */     if ((this.cmdDelay > 0) && 
/* 152:131 */       (--this.cmdDelay == 0)) {
/* 153:132 */       processCommand();
/* 154:    */     }
/* 155:135 */     if (this.cond.Flow == 0)
/* 156:    */     {
/* 157:136 */       if (this.Charged)
/* 158:    */       {
/* 159:137 */         this.Charged = false;
/* 160:138 */         updateBlock();
/* 161:    */       }
/* 162:    */     }
/* 163:140 */     else if (!this.Charged)
/* 164:    */     {
/* 165:141 */       this.Charged = true;
/* 166:142 */       updateBlock();
/* 167:    */     }
/* 168:    */   }
/* 169:    */   
/* 170:    */   public int getBlockID()
/* 171:    */   {
/* 172:149 */     return RedPowerMachine.blockMachine2.cm;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public int getExtendedID()
/* 176:    */   {
/* 177:153 */     return 0;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public boolean onBlockActivated(qx player)
/* 181:    */   {
/* 182:157 */     if (player.ah())
/* 183:    */     {
/* 184:158 */       if (CoreLib.isClient(this.k)) {
/* 185:159 */         return false;
/* 186:    */       }
/* 187:160 */       ur ist = player.bJ.g();
/* 188:161 */       if (ist == null) {
/* 189:161 */         return false;
/* 190:    */       }
/* 191:162 */       if (!(ist.b() instanceof ItemScrewdriver)) {
/* 192:163 */         return false;
/* 193:    */       }
/* 194:164 */       player.openGui(RedPowerBase.instance, 3, this.k, this.l, this.m, this.n);
/* 195:    */       
/* 196:166 */       return true;
/* 197:    */     }
/* 198:168 */     return false;
/* 199:    */   }
/* 200:    */   
/* 201:    */   public void onBlockNeighborChange(int l)
/* 202:    */   {
/* 203:172 */     this.ConMask = -1;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public void onTileTick()
/* 207:    */   {
/* 208:176 */     if (CoreLib.isClient(this.k)) {
/* 209:177 */       return;
/* 210:    */     }
/* 211:179 */     if (!this.Active) {
/* 212:179 */       return;
/* 213:    */     }
/* 214:180 */     if (!this.buffer.isEmpty())
/* 215:    */     {
/* 216:181 */       drainBuffer();
/* 217:182 */       if (!this.buffer.isEmpty()) {
/* 218:182 */         scheduleTick(10);
/* 219:    */       } else {
/* 220:183 */         scheduleTick(5);
/* 221:    */       }
/* 222:184 */       return;
/* 223:    */     }
/* 224:186 */     this.Active = false;
/* 225:187 */     updateBlock();
/* 226:    */   }
/* 227:    */   
/* 228:    */   public static int hashItem(ur ist)
/* 229:    */   {
/* 230:193 */     String in = ist.b().a();
/* 231:    */     int hc;
/* 232:    */     int hc;
/* 233:198 */     if (in == null) {
/* 234:199 */       hc = Integer.valueOf(ist.c).hashCode();
/* 235:    */     } else {
/* 236:201 */       hc = in.hashCode();
/* 237:    */     }
/* 238:203 */     if (ist.g()) {
/* 239:204 */       hc = Integer.valueOf(Integer.valueOf(hc).hashCode() ^ ist.j()).intValue();
/* 240:    */     }
/* 241:208 */     return hc;
/* 242:    */   }
/* 243:    */   
/* 244:    */   void processCommand()
/* 245:    */   {
/* 246:216 */     if (this.cond.getVoltage() < 60.0D)
/* 247:    */     {
/* 248:216 */       this.cmdDelay = 20; return;
/* 249:    */     }
/* 250:    */     la inv;
/* 251:    */     ur ist;
/* 252:217 */     switch (this.command)
/* 253:    */     {
/* 254:    */     case 0: 
/* 255:    */       break;
/* 256:    */     case 1: 
/* 257:222 */       inv = getConnectedInventory(false);
/* 258:223 */       if (inv == null)
/* 259:    */       {
/* 260:223 */         this.command = 255;
/* 261:    */       }
/* 262:    */       else
/* 263:    */       {
/* 264:224 */         this.itemSlot = inv.k_();
/* 265:225 */         this.command = 0;
/* 266:    */       }
/* 267:226 */       break;
/* 268:    */     case 2: 
/* 269:229 */       inv = getConnectedInventory(false);
/* 270:230 */       if (inv == null)
/* 271:    */       {
/* 272:230 */         this.command = 255;
/* 273:    */       }
/* 274:231 */       else if (this.itemSlot >= inv.k_())
/* 275:    */       {
/* 276:232 */         this.command = 255;
/* 277:    */       }
/* 278:    */       else
/* 279:    */       {
/* 280:234 */         ist = inv.a(this.itemSlot);
/* 281:235 */         if ((ist == null) || (ist.a == 0))
/* 282:    */         {
/* 283:236 */           this.itemQty = 0;
/* 284:237 */           this.itemType = 0;
/* 285:238 */           this.itemDamage = 0;
/* 286:239 */           this.itemDamageMax = 0;
/* 287:240 */           this.command = 0;
/* 288:    */         }
/* 289:    */         else
/* 290:    */         {
/* 291:243 */           this.itemQty = ist.a;
/* 292:244 */           this.itemType = hashItem(ist);
/* 293:245 */           if (ist.f())
/* 294:    */           {
/* 295:246 */             this.itemDamage = ist.j();
/* 296:247 */             this.itemDamageMax = ist.k();
/* 297:    */           }
/* 298:    */           else
/* 299:    */           {
/* 300:249 */             this.itemDamage = 0;
/* 301:250 */             this.itemDamageMax = 0;
/* 302:    */           }
/* 303:252 */           this.command = 0;
/* 304:    */         }
/* 305:    */       }
/* 306:253 */       break;
/* 307:    */     case 3: 
/* 308:256 */       if (this.Active)
/* 309:    */       {
/* 310:256 */         this.cmdDelay = 2;return;
/* 311:    */       }
/* 312:257 */       inv = getConnectedInventory(false);
/* 313:258 */       if (inv == null)
/* 314:    */       {
/* 315:258 */         this.command = 255;
/* 316:    */       }
/* 317:259 */       else if (this.itemSlot >= inv.k_())
/* 318:    */       {
/* 319:260 */         this.command = 255;
/* 320:    */       }
/* 321:    */       else
/* 322:    */       {
/* 323:262 */         ist = inv.a(this.itemSlot);
/* 324:263 */         if ((ist == null) || (ist.a == 0))
/* 325:    */         {
/* 326:264 */           this.itemQty = 0;this.command = 0;
/* 327:    */         }
/* 328:    */         else
/* 329:    */         {
/* 330:266 */           int i = Math.min(this.itemQty, ist.a);
/* 331:267 */           this.itemQty = i;
/* 332:269 */           if (this.itemColor > 16) {
/* 333:269 */             this.itemColor = 0;
/* 334:    */           }
/* 335:270 */           this.buffer.addNewColor(inv.a(this.itemSlot, i), this.itemColor);
/* 336:    */           
/* 337:272 */           this.cond.drawPower(50 * ist.a);
/* 338:273 */           drainBuffer();
/* 339:274 */           this.Active = true;
/* 340:275 */           this.command = 0;
/* 341:276 */           updateBlock();
/* 342:277 */           scheduleTick(5);
/* 343:    */         }
/* 344:    */       }
/* 345:278 */       break;
/* 346:    */     case 4: 
/* 347:281 */       if (this.itemQty == 0) {
/* 348:282 */         this.command = 0;
/* 349:    */       }
/* 350:    */       break;
/* 351:    */     default: 
/* 352:285 */       this.command = 255;
/* 353:    */     }
/* 354:    */   }
/* 355:    */   
/* 356:    */   protected boolean handleExtract(la inv, int start, int len)
/* 357:    */   {
/* 358:290 */     return false;
/* 359:    */   }
/* 360:    */   
/* 361:    */   protected void addToBuffer(ur ist)
/* 362:    */   {
/* 363:294 */     if (this.itemColor > 16) {
/* 364:294 */       this.itemColor = 0;
/* 365:    */     }
/* 366:295 */     this.buffer.addNewColor(ist, this.itemColor);
/* 367:    */   }
/* 368:    */   
/* 369:    */   protected int suckEntity(Object ent)
/* 370:    */   {
/* 371:299 */     if ((ent instanceof px))
/* 372:    */     {
/* 373:300 */       px ei = (px)ent;
/* 374:301 */       ur ist = ei.d();
/* 375:302 */       if ((ist.a == 0) || (ei.L)) {
/* 376:303 */         return 0;
/* 377:    */       }
/* 378:304 */       int st = ist.a;
/* 379:305 */       if (!suckFilter(ist)) {
/* 380:306 */         return st == ist.a ? 0 : 2;
/* 381:    */       }
/* 382:307 */       addToBuffer(ist);
/* 383:308 */       ei.x();
/* 384:309 */       return 1;
/* 385:    */     }
/* 386:311 */     return 0;
/* 387:    */   }
/* 388:    */   
/* 389:    */   protected boolean suckFilter(ur ist)
/* 390:    */   {
/* 391:315 */     if (this.command != 4) {
/* 392:315 */       return false;
/* 393:    */     }
/* 394:316 */     if (this.cond.getVoltage() < 60.0D) {
/* 395:316 */       return false;
/* 396:    */     }
/* 397:317 */     if ((this.itemType != 0) && (this.itemType != hashItem(ist))) {
/* 398:318 */       return false;
/* 399:    */     }
/* 400:320 */     boolean tr = true;
/* 401:321 */     if (this.itemQty < ist.a)
/* 402:    */     {
/* 403:322 */       tr = false;
/* 404:323 */       ist = ist.a(this.itemQty);
/* 405:324 */       if (this.itemColor > 16) {
/* 406:324 */         this.itemColor = 0;
/* 407:    */       }
/* 408:325 */       this.buffer.addNewColor(ist, this.itemColor);
/* 409:    */     }
/* 410:327 */     this.itemQty -= ist.a;
/* 411:328 */     if (this.itemQty == 0) {
/* 412:328 */       this.command = 0;
/* 413:    */     }
/* 414:329 */     this.cond.drawPower(50 * ist.a);
/* 415:330 */     return tr;
/* 416:    */   }
/* 417:    */   
/* 418:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/* 419:    */   {
/* 420:336 */     if ((side == this.Rotation) && (state == 2)) {
/* 421:337 */       return super.tubeItemEnter(side, state, ti);
/* 422:    */     }
/* 423:338 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1))
/* 424:    */     {
/* 425:339 */       if (this.command != 4) {
/* 426:339 */         return false;
/* 427:    */       }
/* 428:340 */       if (this.cond.getVoltage() < 60.0D) {
/* 429:340 */         return false;
/* 430:    */       }
/* 431:341 */       if ((this.itemType != 0) && (this.itemType != hashItem(ti.item))) {
/* 432:342 */         return false;
/* 433:    */       }
/* 434:343 */       if ((this.itemInColor != 0) && (this.itemInColor != ti.color)) {
/* 435:344 */         return false;
/* 436:    */       }
/* 437:346 */       boolean tr = true;
/* 438:347 */       ur ist = ti.item;
/* 439:348 */       if (this.itemQty < ist.a)
/* 440:    */       {
/* 441:349 */         tr = false;
/* 442:350 */         ist = ist.a(this.itemQty);
/* 443:    */       }
/* 444:352 */       this.itemQty -= ist.a;
/* 445:353 */       if (this.itemQty == 0) {
/* 446:353 */         this.command = 0;
/* 447:    */       }
/* 448:355 */       if (this.itemColor > 16) {
/* 449:355 */         this.itemColor = 0;
/* 450:    */       }
/* 451:356 */       this.buffer.addNewColor(ist, this.itemColor);
/* 452:357 */       this.cond.drawPower(50 * ist.a);
/* 453:358 */       drainBuffer();
/* 454:359 */       this.Active = true;
/* 455:360 */       updateBlock();
/* 456:361 */       scheduleTick(5);
/* 457:362 */       return tr;
/* 458:    */     }
/* 459:364 */     return false;
/* 460:    */   }
/* 461:    */   
/* 462:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/* 463:    */   {
/* 464:368 */     if ((side == this.Rotation) && (state == 2)) {
/* 465:369 */       return true;
/* 466:    */     }
/* 467:370 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1))
/* 468:    */     {
/* 469:371 */       if (this.command != 4) {
/* 470:371 */         return false;
/* 471:    */       }
/* 472:372 */       if (this.cond.getVoltage() < 60.0D) {
/* 473:372 */         return false;
/* 474:    */       }
/* 475:374 */       if ((this.itemType != 0) && (this.itemType != hashItem(ti.item))) {
/* 476:375 */         return false;
/* 477:    */       }
/* 478:376 */       if ((this.itemInColor != 0) && (this.itemInColor != ti.color)) {
/* 479:377 */         return false;
/* 480:    */       }
/* 481:378 */       return true;
/* 482:    */     }
/* 483:380 */     return false;
/* 484:    */   }
/* 485:    */   
/* 486:    */   public void a(bq tag)
/* 487:    */   {
/* 488:386 */     super.a(tag);
/* 489:    */     
/* 490:388 */     this.cond.readFromNBT(tag);
/* 491:389 */     this.rbaddr = (tag.c("rbaddr") & 0xFF);
/* 492:    */     
/* 493:391 */     this.cmdDelay = (tag.c("cmddelay") & 0xFF);
/* 494:    */     
/* 495:393 */     this.command = (tag.c("cmd") & 0xFF);
/* 496:394 */     this.itemSlot = (tag.d("itemslot") & 0xFFFF);
/* 497:395 */     this.itemType = tag.e("itemtype");
/* 498:396 */     this.itemDamage = (tag.d("itemdmg") & 0xFFFF);
/* 499:397 */     this.itemDamageMax = (tag.d("itemdmgmax") & 0xFFFF);
/* 500:398 */     this.itemQty = (tag.c("itemqty") & 0xFF);
/* 501:399 */     this.itemInColor = (tag.c("itemincolor") & 0xFF);
/* 502:    */   }
/* 503:    */   
/* 504:    */   public void b(bq tag)
/* 505:    */   {
/* 506:403 */     super.b(tag);
/* 507:    */     
/* 508:405 */     this.cond.writeToNBT(tag);
/* 509:406 */     tag.a("rbaddr", (byte)this.rbaddr);
/* 510:    */     
/* 511:408 */     tag.a("cmddelay", (byte)this.cmdDelay);
/* 512:    */     
/* 513:410 */     tag.a("cmd", (byte)this.command);
/* 514:411 */     tag.a("itemslot", (short)this.itemSlot);
/* 515:412 */     tag.a("itemtype", this.itemType);
/* 516:413 */     tag.a("itemdmg", (short)this.itemDamage);
/* 517:414 */     tag.a("itemdmgmax", (short)this.itemDamageMax);
/* 518:415 */     tag.a("itemqty", (byte)this.itemQty);
/* 519:416 */     tag.a("itemcolor", (byte)this.itemColor);
/* 520:417 */     tag.a("itemincolor", (byte)this.itemInColor);
/* 521:    */   }
/* 522:    */   
/* 523:420 */   public int ConMask = -1;
/* 524:421 */   int rbaddr = 4;
/* 525:424 */   private int cmdDelay = 0;
/* 526:427 */   private int command = 0;
/* 527:428 */   private int itemSlot = 0;
/* 528:429 */   private int itemType = 0;
/* 529:430 */   private int itemDamage = 0;
/* 530:431 */   private int itemDamageMax = 0;
/* 531:432 */   private int itemQty = 0;
/* 532:433 */   private int itemColor = 0;
/* 533:434 */   private int itemInColor = 0;
/* 534:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileSortron
 * JD-Core Version:    0.7.0.1
 */