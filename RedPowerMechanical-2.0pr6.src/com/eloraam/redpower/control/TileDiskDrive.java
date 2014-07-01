/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerBase;
/*   6:    */ import com.eloraam.redpower.RedPowerControl;
/*   7:    */ import com.eloraam.redpower.base.ItemScrewdriver;
/*   8:    */ import com.eloraam.redpower.core.BlockExtended;
/*   9:    */ import com.eloraam.redpower.core.CoreLib;
/*  10:    */ import com.eloraam.redpower.core.DiskLib;
/*  11:    */ import com.eloraam.redpower.core.IFrameSupport;
/*  12:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  13:    */ import com.eloraam.redpower.core.IRedbusConnectable;
/*  14:    */ import com.eloraam.redpower.core.MachineLib;
/*  15:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  16:    */ import com.eloraam.redpower.core.TileExtended;
/*  17:    */ import com.eloraam.redpower.core.WorldCoord;
/*  18:    */ import ef;
/*  19:    */ import java.io.ByteArrayOutputStream;
/*  20:    */ import java.io.File;
/*  21:    */ import java.io.IOException;
/*  22:    */ import java.io.InputStream;
/*  23:    */ import java.io.RandomAccessFile;
/*  24:    */ import java.io.UnsupportedEncodingException;
/*  25:    */ import java.util.Arrays;
/*  26:    */ import la;
/*  27:    */ import md;
/*  28:    */ import qw;
/*  29:    */ import qx;
/*  30:    */ import ur;
/*  31:    */ import yc;
/*  32:    */ import ym;
/*  33:    */ 
/*  34:    */ public class TileDiskDrive
/*  35:    */   extends TileExtended
/*  36:    */   implements IRedbusConnectable, la, IHandlePackets, IFrameSupport
/*  37:    */ {
/*  38:    */   public TileDiskDrive()
/*  39:    */   {
/*  40: 35 */     this.contents = new ur[1];
/*  41: 36 */     this.databuf = new byte[''];
/*  42:    */   }
/*  43:    */   
/*  44:    */   public int rbGetAddr()
/*  45:    */   {
/*  46: 42 */     return this.rbaddr;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void rbSetAddr(int addr)
/*  50:    */   {
/*  51: 46 */     this.rbaddr = addr;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public int rbRead(int reg)
/*  55:    */   {
/*  56: 50 */     if (reg < 128) {
/*  57: 50 */       return this.databuf[reg] & 0xFF;
/*  58:    */     }
/*  59: 52 */     switch (reg)
/*  60:    */     {
/*  61:    */     case 128: 
/*  62: 53 */       return this.sector & 0xFF;
/*  63:    */     case 129: 
/*  64: 54 */       return this.sector >> 8;
/*  65:    */     case 130: 
/*  66: 55 */       return this.cmdreg & 0xFF;
/*  67:    */     }
/*  68: 57 */     return 0;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void rbWrite(int reg, int dat)
/*  72:    */   {
/*  73: 61 */     dirtyBlock();
/*  74: 62 */     if (reg < 128)
/*  75:    */     {
/*  76: 63 */       this.databuf[reg] = ((byte)dat);
/*  77: 64 */       return;
/*  78:    */     }
/*  79: 67 */     switch (reg)
/*  80:    */     {
/*  81:    */     case 128: 
/*  82: 68 */       this.sector = (this.sector & 0xFF00 | dat); break;
/*  83:    */     case 129: 
/*  84: 69 */       this.sector = (this.sector & 0xFF | dat << 8); break;
/*  85:    */     case 130: 
/*  86: 70 */       this.cmdreg = dat;
/*  87:    */     }
/*  88:    */   }
/*  89:    */   
/*  90:    */   public int getConnectableMask()
/*  91:    */   {
/*  92: 77 */     return 16777215;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public int getConnectClass(int side)
/*  96:    */   {
/*  97: 81 */     return 66;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public int getCornerPowerMode()
/* 101:    */   {
/* 102: 84 */     return 0;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 106:    */   {
/* 107: 90 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) + 1 & 0x3);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public boolean onBlockActivated(qx player)
/* 111:    */   {
/* 112: 94 */     if (player.ah())
/* 113:    */     {
/* 114: 95 */       if (CoreLib.isClient(this.k)) {
/* 115: 96 */         return false;
/* 116:    */       }
/* 117: 97 */       ur ist = player.bJ.g();
/* 118: 98 */       if (ist == null) {
/* 119: 98 */         return false;
/* 120:    */       }
/* 121: 99 */       if (!(ist.b() instanceof ItemScrewdriver)) {
/* 122:100 */         return false;
/* 123:    */       }
/* 124:101 */       player.openGui(RedPowerBase.instance, 3, this.k, this.l, this.m, this.n);
/* 125:    */       
/* 126:103 */       return true;
/* 127:    */     }
/* 128:105 */     if (CoreLib.isClient(this.k)) {
/* 129:106 */       return true;
/* 130:    */     }
/* 131:107 */     if (!this.hasDisk) {
/* 132:107 */       return false;
/* 133:    */     }
/* 134:108 */     if (this.contents[0] == null) {
/* 135:108 */       return false;
/* 136:    */     }
/* 137:109 */     if (this.Active) {
/* 138:109 */       return false;
/* 139:    */     }
/* 140:110 */     ejectDisk();
/* 141:111 */     return true;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public int getBlockID()
/* 145:    */   {
/* 146:115 */     return RedPowerControl.blockPeripheral.cm;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public int getExtendedID()
/* 150:    */   {
/* 151:119 */     return 2;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void onBlockRemoval()
/* 155:    */   {
/* 156:123 */     for (int i = 0; i < 1; i++)
/* 157:    */     {
/* 158:124 */       ur ist = this.contents[i];
/* 159:125 */       if ((ist != null) && (ist.a > 0)) {
/* 160:126 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 161:    */       }
/* 162:    */     }
/* 163:    */   }
/* 164:    */   
/* 165:    */   boolean setDisk(ur ist)
/* 166:    */   {
/* 167:135 */     if (this.contents[0] != null) {
/* 168:135 */       return false;
/* 169:    */     }
/* 170:136 */     a(0, ist);
/* 171:137 */     return true;
/* 172:    */   }
/* 173:    */   
/* 174:    */   private bq getDiskTags()
/* 175:    */   {
/* 176:141 */     bq tags = this.contents[0].d;
/* 177:142 */     if (tags == null)
/* 178:    */     {
/* 179:143 */       this.contents[0].d(new bq());
/* 180:144 */       tags = this.contents[0].d;
/* 181:    */     }
/* 182:146 */     return tags;
/* 183:    */   }
/* 184:    */   
/* 185:    */   private File startDisk()
/* 186:    */   {
/* 187:150 */     if (this.contents[0].j() > 0) {
/* 188:151 */       return null;
/* 189:    */     }
/* 190:169 */     bq tags = getDiskTags();
/* 191:170 */     File savedir = DiskLib.getSaveDir(this.k);
/* 192:172 */     if (tags.b("serno")) {
/* 193:173 */       return DiskLib.getDiskFile(savedir, tags.i("serno"));
/* 194:    */     }
/* 195:176 */     String serno = null;
/* 196:    */     for (;;)
/* 197:    */     {
/* 198:179 */       serno = DiskLib.generateSerialNumber(this.k);
/* 199:180 */       File diskFile = DiskLib.getDiskFile(savedir, serno);
/* 200:    */       try
/* 201:    */       {
/* 202:182 */         if (diskFile.createNewFile())
/* 203:    */         {
/* 204:183 */           tags.a("serno", serno);
/* 205:184 */           return diskFile;
/* 206:    */         }
/* 207:    */       }
/* 208:    */       catch (IOException e)
/* 209:    */       {
/* 210:187 */         e.printStackTrace();
/* 211:188 */         return null;
/* 212:    */       }
/* 213:    */     }
/* 214:    */   }
/* 215:    */   
/* 216:    */   private void runCmd1()
/* 217:    */   {
/* 218:194 */     Arrays.fill(this.databuf, (byte)0);
/* 219:195 */     String nm = "";
/* 220:197 */     if (this.contents[0].j() > 0)
/* 221:    */     {
/* 222:198 */       nm = "System Disk";
/* 223:    */     }
/* 224:    */     else
/* 225:    */     {
/* 226:200 */       bq tags = this.contents[0].d;
/* 227:201 */       if (tags == null) {
/* 228:201 */         return;
/* 229:    */       }
/* 230:202 */       nm = tags.i("label");
/* 231:    */     }
/* 232:    */     try
/* 233:    */     {
/* 234:205 */       byte[] bytes = nm.getBytes("US-ASCII");
/* 235:206 */       System.arraycopy(bytes, 0, this.databuf, 0, Math.min(bytes.length, 128));
/* 236:    */     }
/* 237:    */     catch (UnsupportedEncodingException e) {}
/* 238:    */   }
/* 239:    */   
/* 240:    */   private void runCmd2()
/* 241:    */   {
/* 242:212 */     if (this.contents[0].j() > 0)
/* 243:    */     {
/* 244:213 */       this.cmdreg = -1;
/* 245:214 */       return;
/* 246:    */     }
/* 247:217 */     bq tags = getDiskTags();
/* 248:218 */     int len = 0;
/* 249:219 */     while ((this.databuf[len] != 0) && (len < 64)) {
/* 250:219 */       len++;
/* 251:    */     }
/* 252:220 */     this.cmdreg = 0;
/* 253:    */     try
/* 254:    */     {
/* 255:222 */       String nm = new String(this.databuf, 0, len, "US-ASCII");
/* 256:223 */       tags.a("label", nm);
/* 257:    */     }
/* 258:    */     catch (UnsupportedEncodingException e) {}
/* 259:    */   }
/* 260:    */   
/* 261:    */   private void runCmd3()
/* 262:    */   {
/* 263:229 */     Arrays.fill(this.databuf, (byte)0);
/* 264:230 */     String nm = "";
/* 265:231 */     if (this.contents[0].j() > 0)
/* 266:    */     {
/* 267:232 */       nm = String.format("%016d", new Object[] { Integer.valueOf(this.contents[0].j()) });
/* 268:    */     }
/* 269:    */     else
/* 270:    */     {
/* 271:234 */       bq tags = getDiskTags();
/* 272:235 */       startDisk();
/* 273:236 */       if (tags == null) {
/* 274:236 */         return;
/* 275:    */       }
/* 276:237 */       nm = tags.i("serno");
/* 277:    */     }
/* 278:    */     try
/* 279:    */     {
/* 280:240 */       byte[] bytes = nm.getBytes("US-ASCII");
/* 281:241 */       System.arraycopy(bytes, 0, this.databuf, 0, Math.min(bytes.length, 128));
/* 282:    */     }
/* 283:    */     catch (UnsupportedEncodingException e) {}
/* 284:    */   }
/* 285:    */   
/* 286:    */   private void runCmd4()
/* 287:    */   {
/* 288:248 */     if (this.sector > 2048)
/* 289:    */     {
/* 290:248 */       this.cmdreg = -1;return;
/* 291:    */     }
/* 292:249 */     long l = this.sector * 128;
/* 293:    */     
/* 294:251 */     InputStream inf = null;
/* 295:253 */     if (this.contents[0].j() > 0)
/* 296:    */     {
/* 297:254 */       InputStream ins = null;
/* 298:255 */       switch (this.contents[0].j())
/* 299:    */       {
/* 300:    */       case 1: 
/* 301:257 */         ins = RedPowerControl.class.getResourceAsStream("/eloraam/control/redforth.img");
/* 302:    */         
/* 303:    */ 
/* 304:260 */         break;
/* 305:    */       case 2: 
/* 306:262 */         ins = RedPowerControl.class.getResourceAsStream("/eloraam/control/redforthxp.img");
/* 307:    */       }
/* 308:    */       try
/* 309:    */       {
/* 310:268 */         if (ins.skip(l) != l)
/* 311:    */         {
/* 312:268 */           this.cmdreg = -1; return;
/* 313:    */         }
/* 314:269 */         if (ins.read(this.databuf) != 128)
/* 315:    */         {
/* 316:269 */           this.cmdreg = -1; return;
/* 317:    */         }
/* 318:270 */         this.cmdreg = 0;
/* 319:    */       }
/* 320:    */       catch (IOException e)
/* 321:    */       {
/* 322:272 */         e.printStackTrace();
/* 323:273 */         this.cmdreg = -1;
/* 324:    */       }
/* 325:    */       finally
/* 326:    */       {
/* 327:    */         try
/* 328:    */         {
/* 329:276 */           if (ins != null) {
/* 330:276 */             ins.close();
/* 331:    */           }
/* 332:    */         }
/* 333:    */         catch (IOException e) {}
/* 334:    */       }
/* 335:    */     }
/* 336:    */     else
/* 337:    */     {
/* 338:280 */       File file = startDisk();
/* 339:281 */       if (file == null)
/* 340:    */       {
/* 341:281 */         this.cmdreg = -1;return;
/* 342:    */       }
/* 343:282 */       RandomAccessFile raf = null;
/* 344:    */       try
/* 345:    */       {
/* 346:284 */         raf = new RandomAccessFile(file, "r");
/* 347:285 */         raf.seek(l);
/* 348:286 */         if (raf.read(this.databuf) != 128) {
/* 349:286 */           this.cmdreg = -1;
/* 350:    */         } else {
/* 351:287 */           this.cmdreg = 0;
/* 352:    */         }
/* 353:    */       }
/* 354:    */       catch (IOException e)
/* 355:    */       {
/* 356:289 */         e.printStackTrace();
/* 357:290 */         this.cmdreg = -1;
/* 358:    */       }
/* 359:    */       finally
/* 360:    */       {
/* 361:    */         try
/* 362:    */         {
/* 363:293 */           if (raf != null) {
/* 364:293 */             raf.close();
/* 365:    */           }
/* 366:    */         }
/* 367:    */         catch (IOException e) {}
/* 368:    */       }
/* 369:    */     }
/* 370:    */   }
/* 371:    */   
/* 372:    */   private void runCmd5()
/* 373:    */   {
/* 374:300 */     if (this.contents[0].j() > 0)
/* 375:    */     {
/* 376:300 */       this.cmdreg = -1;return;
/* 377:    */     }
/* 378:301 */     if (this.sector > 2048)
/* 379:    */     {
/* 380:301 */       this.cmdreg = -1;return;
/* 381:    */     }
/* 382:302 */     long l = this.sector * 128;
/* 383:    */     
/* 384:304 */     File file = startDisk();
/* 385:305 */     if (file == null)
/* 386:    */     {
/* 387:305 */       this.cmdreg = -1;return;
/* 388:    */     }
/* 389:306 */     RandomAccessFile raf = null;
/* 390:    */     try
/* 391:    */     {
/* 392:308 */       raf = new RandomAccessFile(file, "rw");
/* 393:309 */       raf.seek(l);
/* 394:310 */       raf.write(this.databuf);
/* 395:311 */       raf.close();
/* 396:312 */       raf = null;
/* 397:313 */       this.cmdreg = 0; return;
/* 398:    */     }
/* 399:    */     catch (IOException e)
/* 400:    */     {
/* 401:315 */       this.cmdreg = -1;
/* 402:    */     }
/* 403:    */     finally
/* 404:    */     {
/* 405:    */       try
/* 406:    */       {
/* 407:318 */         if (raf != null) {
/* 408:318 */           raf.close();
/* 409:    */         }
/* 410:    */       }
/* 411:    */       catch (IOException e) {}
/* 412:    */     }
/* 413:    */   }
/* 414:    */   
/* 415:    */   private void runDiskCmd()
/* 416:    */   {
/* 417:324 */     dirtyBlock();
/* 418:325 */     if (this.contents[0] == null)
/* 419:    */     {
/* 420:325 */       this.cmdreg = -1;return;
/* 421:    */     }
/* 422:326 */     if (!(this.contents[0].b() instanceof ItemDisk))
/* 423:    */     {
/* 424:327 */       this.cmdreg = -1;return;
/* 425:    */     }
/* 426:329 */     switch (this.cmdreg)
/* 427:    */     {
/* 428:    */     case 1: 
/* 429:331 */       runCmd1();
/* 430:332 */       this.cmdreg = 0;
/* 431:333 */       break;
/* 432:    */     case 2: 
/* 433:335 */       runCmd2();
/* 434:336 */       break;
/* 435:    */     case 3: 
/* 436:338 */       runCmd3();
/* 437:339 */       this.cmdreg = 0;
/* 438:340 */       break;
/* 439:    */     case 4: 
/* 440:342 */       runCmd4();
/* 441:343 */       break;
/* 442:    */     case 5: 
/* 443:345 */       runCmd5();
/* 444:346 */       break;
/* 445:    */     default: 
/* 446:349 */       this.cmdreg = -1;
/* 447:    */     }
/* 448:353 */     this.accessTime = 5;
/* 449:354 */     if (!this.Active)
/* 450:    */     {
/* 451:355 */       this.Active = true;
/* 452:356 */       updateBlock();
/* 453:    */     }
/* 454:    */   }
/* 455:    */   
/* 456:    */   private void ejectDisk()
/* 457:    */   {
/* 458:361 */     if (this.contents[0] == null) {
/* 459:361 */       return;
/* 460:    */     }
/* 461:362 */     MachineLib.ejectItem(this.k, new WorldCoord(this), this.contents[0], CoreLib.rotToSide(this.Rotation) ^ 0x1);
/* 462:    */     
/* 463:364 */     this.contents[0] = null;
/* 464:365 */     this.hasDisk = false;
/* 465:366 */     updateBlock();
/* 466:    */   }
/* 467:    */   
/* 468:    */   public void d()
/* 469:    */   {
/* 470:370 */     super.d();
/* 471:372 */     if ((this.contents[0] != null) && (!(this.contents[0].b() instanceof ItemDisk))) {
/* 472:374 */       ejectDisk();
/* 473:    */     }
/* 474:    */   }
/* 475:    */   
/* 476:    */   public void g()
/* 477:    */   {
/* 478:379 */     if ((this.cmdreg != 0) && (this.cmdreg != -1)) {
/* 479:379 */       runDiskCmd();
/* 480:    */     }
/* 481:381 */     if ((this.accessTime > 0) && 
/* 482:382 */       (--this.accessTime == 0))
/* 483:    */     {
/* 484:383 */       this.Active = false;
/* 485:384 */       updateBlock();
/* 486:    */     }
/* 487:    */   }
/* 488:    */   
/* 489:    */   public int k_()
/* 490:    */   {
/* 491:392 */     return 1;
/* 492:    */   }
/* 493:    */   
/* 494:    */   public ur a(int i)
/* 495:    */   {
/* 496:396 */     return this.contents[i];
/* 497:    */   }
/* 498:    */   
/* 499:    */   public ur a(int i, int j)
/* 500:    */   {
/* 501:401 */     if (this.contents[i] == null) {
/* 502:401 */       return null;
/* 503:    */     }
/* 504:403 */     if (this.contents[i].a <= j)
/* 505:    */     {
/* 506:404 */       ur tr = this.contents[i];
/* 507:405 */       this.contents[i] = null;
/* 508:406 */       d();
/* 509:407 */       return tr;
/* 510:    */     }
/* 511:409 */     ur tr = this.contents[i].a(j);
/* 512:410 */     if (this.contents[i].a == 0) {
/* 513:411 */       this.contents[i] = null;
/* 514:    */     }
/* 515:412 */     d();
/* 516:413 */     return tr;
/* 517:    */   }
/* 518:    */   
/* 519:    */   public ur a_(int i)
/* 520:    */   {
/* 521:417 */     if (this.contents[i] == null) {
/* 522:417 */       return null;
/* 523:    */     }
/* 524:418 */     ur ist = this.contents[i];
/* 525:419 */     this.contents[i] = null;
/* 526:420 */     return ist;
/* 527:    */   }
/* 528:    */   
/* 529:    */   public void a(int i, ur ist)
/* 530:    */   {
/* 531:424 */     this.contents[i] = ist;
/* 532:425 */     if ((ist != null) && (ist.a > c())) {
/* 533:426 */       ist.a = c();
/* 534:    */     }
/* 535:427 */     d();
/* 536:    */     
/* 537:429 */     this.hasDisk = (this.contents[i] != null);
/* 538:430 */     updateBlock();
/* 539:    */   }
/* 540:    */   
/* 541:    */   public String b()
/* 542:    */   {
/* 543:434 */     return "Disk Drive";
/* 544:    */   }
/* 545:    */   
/* 546:    */   public int c()
/* 547:    */   {
/* 548:438 */     return 64;
/* 549:    */   }
/* 550:    */   
/* 551:    */   public boolean a_(qx player)
/* 552:    */   {
/* 553:442 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 554:443 */       return false;
/* 555:    */     }
/* 556:444 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 557:    */   }
/* 558:    */   
/* 559:    */   public void f() {}
/* 560:    */   
/* 561:    */   public void l_() {}
/* 562:    */   
/* 563:    */   public byte[] getFramePacket()
/* 564:    */   {
/* 565:455 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 566:456 */     pkt.subId = 7;
/* 567:457 */     writeToPacket(pkt);
/* 568:458 */     pkt.headout.write(pkt.subId);
/* 569:459 */     return pkt.toByteArray();
/* 570:    */   }
/* 571:    */   
/* 572:    */   public void handleFramePacket(byte[] ba)
/* 573:    */     throws IOException
/* 574:    */   {
/* 575:463 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 576:464 */     pkt.subId = pkt.getByte();
/* 577:465 */     readFromPacket(pkt);
/* 578:    */   }
/* 579:    */   
/* 580:    */   public void onFrameRefresh(ym iba) {}
/* 581:    */   
/* 582:    */   public void onFramePickup(ym iba) {}
/* 583:    */   
/* 584:    */   public void onFrameDrop() {}
/* 585:    */   
/* 586:    */   public void a(bq tag)
/* 587:    */   {
/* 588:475 */     super.a(tag);
/* 589:476 */     this.Rotation = tag.c("rot");
/* 590:477 */     this.accessTime = tag.c("actime");
/* 591:478 */     this.sector = (tag.d("sect") & 0xFFFF);
/* 592:479 */     this.cmdreg = (tag.c("cmd") & 0xFF);
/* 593:480 */     this.rbaddr = (tag.c("rbaddr") & 0xFF);
/* 594:    */     
/* 595:482 */     int fl = tag.c("fl");
/* 596:483 */     this.hasDisk = ((fl & 0x1) > 0);
/* 597:484 */     this.Active = ((fl & 0x2) > 0);
/* 598:    */     
/* 599:486 */     this.databuf = tag.j("dbuf");
/* 600:487 */     if (this.databuf.length != 128) {
/* 601:488 */       this.databuf = new byte[''];
/* 602:    */     }
/* 603:490 */     by items = tag.m("Items");
/* 604:491 */     this.contents = new ur[k_()];
/* 605:492 */     for (int i = 0; i < items.c(); i++)
/* 606:    */     {
/* 607:493 */       bq item = (bq)items.b(i);
/* 608:    */       
/* 609:495 */       int j = item.c("Slot") & 0xFF;
/* 610:496 */       if ((j >= 0) && (j < this.contents.length)) {
/* 611:497 */         this.contents[j] = ur.a(item);
/* 612:    */       }
/* 613:    */     }
/* 614:500 */     this.hasDisk = (this.contents[0] != null);
/* 615:    */   }
/* 616:    */   
/* 617:    */   public void b(bq tag)
/* 618:    */   {
/* 619:504 */     super.b(tag);
/* 620:505 */     tag.a("rot", (byte)this.Rotation);
/* 621:    */     
/* 622:507 */     int fl = (this.hasDisk ? 1 : 0) | (this.Active ? 2 : 0);
/* 623:508 */     tag.a("fl", (byte)fl);
/* 624:    */     
/* 625:510 */     tag.a("actime", (byte)this.accessTime);
/* 626:511 */     tag.a("dbuf", this.databuf);
/* 627:512 */     tag.a("sect", (short)this.sector);
/* 628:513 */     tag.a("cmd", (byte)this.cmdreg);
/* 629:514 */     tag.a("rbaddr", (byte)this.rbaddr);
/* 630:    */     
/* 631:516 */     by items = new by();
/* 632:517 */     for (int i = 0; i < this.contents.length; i++) {
/* 633:518 */       if (this.contents[i] != null)
/* 634:    */       {
/* 635:519 */         bq item = new bq();
/* 636:520 */         item.a("Slot", (byte)i);
/* 637:521 */         this.contents[i].b(item);
/* 638:522 */         items.a(item);
/* 639:    */       }
/* 640:    */     }
/* 641:525 */     tag.a("Items", items);
/* 642:    */   }
/* 643:    */   
/* 644:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 645:    */     throws IOException
/* 646:    */   {
/* 647:530 */     this.Rotation = pkt.getByte();
/* 648:    */     
/* 649:532 */     int fl = pkt.getByte();
/* 650:533 */     this.hasDisk = ((fl & 0x1) > 0);
/* 651:534 */     this.Active = ((fl & 0x2) > 0);
/* 652:    */   }
/* 653:    */   
/* 654:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 655:    */   {
/* 656:538 */     pkt.addByte(this.Rotation);
/* 657:    */     
/* 658:540 */     int fl = (this.hasDisk ? 1 : 0) | (this.Active ? 2 : 0);
/* 659:541 */     pkt.addByte(fl);
/* 660:    */   }
/* 661:    */   
/* 662:    */   public ef l()
/* 663:    */   {
/* 664:545 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 665:546 */     packet.subId = 7;
/* 666:547 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 667:548 */     packet.zCoord = this.n;
/* 668:549 */     writeToPacket(packet);
/* 669:550 */     packet.encode();
/* 670:551 */     return packet;
/* 671:    */   }
/* 672:    */   
/* 673:    */   public void handlePacket(Packet211TileDesc packet)
/* 674:    */   {
/* 675:    */     try
/* 676:    */     {
/* 677:556 */       if (packet.subId != 7) {
/* 678:556 */         return;
/* 679:    */       }
/* 680:557 */       readFromPacket(packet);
/* 681:    */     }
/* 682:    */     catch (IOException e) {}
/* 683:559 */     this.k.i(this.l, this.m, this.n);
/* 684:    */   }
/* 685:    */   
/* 686:562 */   public int Rotation = 0;
/* 687:563 */   public boolean hasDisk = false;
/* 688:563 */   public boolean Active = false;
/* 689:    */   private ur[] contents;
/* 690:566 */   private int accessTime = 0;
/* 691:    */   private byte[] databuf;
/* 692:568 */   private int sector = 0;
/* 693:569 */   private int cmdreg = 0;
/* 694:571 */   private int rbaddr = 2;
/* 695:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.TileDiskDrive
 * JD-Core Version:    0.7.0.1
 */