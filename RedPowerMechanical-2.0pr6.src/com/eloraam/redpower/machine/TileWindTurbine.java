/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import aoe;
/*   5:    */ import bq;
/*   6:    */ import by;
/*   7:    */ import com.eloraam.redpower.RedPowerMachine;
/*   8:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   9:    */ import com.eloraam.redpower.core.CoreLib;
/*  10:    */ import com.eloraam.redpower.core.EnvironLib;
/*  11:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  12:    */ import com.eloraam.redpower.core.IMultiblock;
/*  13:    */ import com.eloraam.redpower.core.MultiLib;
/*  14:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  15:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  16:    */ import com.eloraam.redpower.core.WorldCoord;
/*  17:    */ import cpw.mods.fml.relauncher.Side;
/*  18:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  19:    */ import java.io.IOException;
/*  20:    */ import java.util.ArrayList;
/*  21:    */ import java.util.List;
/*  22:    */ import la;
/*  23:    */ import qx;
/*  24:    */ import ur;
/*  25:    */ import yc;
/*  26:    */ 
/*  27:    */ public class TileWindTurbine
/*  28:    */   extends TileMachine
/*  29:    */   implements la, IBluePowerConnectable, IMultiblock
/*  30:    */ {
/*  31:    */   public TileWindTurbine()
/*  32:    */   {
/*  33: 28 */     this.contents = new ur[1];
/*  34:    */   }
/*  35:    */   
/*  36:    */   public int getConnectableMask()
/*  37:    */   {
/*  38: 34 */     return 1073741823;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getConnectClass(int side)
/*  42:    */   {
/*  43: 38 */     return 65;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public int getCornerPowerMode()
/*  47:    */   {
/*  48: 41 */     return 2;
/*  49:    */   }
/*  50:    */   
/*  51: 45 */   BluePowerConductor cond = new BluePowerConductor()
/*  52:    */   {
/*  53:    */     public any getParent()
/*  54:    */     {
/*  55: 47 */       return TileWindTurbine.this;
/*  56:    */     }
/*  57:    */     
/*  58:    */     public double getInvCap()
/*  59:    */     {
/*  60: 51 */       return 0.25D;
/*  61:    */     }
/*  62:    */   };
/*  63:    */   
/*  64:    */   public BluePowerConductor getBlueConductor(int side)
/*  65:    */   {
/*  66: 56 */     return this.cond;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void setPartRotation(int part, boolean sec, int rot)
/*  70:    */   {
/*  71: 60 */     teardownBlades();
/*  72: 61 */     super.setPartRotation(part, sec, rot);
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void onMultiRemoval(int subnum)
/*  76:    */   {
/*  77: 67 */     ur ist = this.contents[0];
/*  78: 68 */     if ((ist != null) && (ist.a > 0)) {
/*  79: 69 */       CoreLib.dropItem(this.k, this.l, this.m + 1, this.n, ist);
/*  80:    */     }
/*  81: 71 */     this.contents[0] = null;
/*  82: 72 */     d();
/*  83:    */     
/*  84:    */ 
/*  85: 75 */     teardownBlades();
/*  86:    */   }
/*  87:    */   
/*  88:    */   public aoe getMultiBounds(int subnum)
/*  89:    */   {
/*  90: 79 */     switch (this.windmillType)
/*  91:    */     {
/*  92:    */     case 1: 
/*  93: 81 */       return aoe.a(-2.5D, 1.3D, -2.5D, 3.5D, 9.0D, 3.5D);
/*  94:    */     case 2: 
/*  95: 83 */       WorldCoord wc = new WorldCoord(0, 0, 0);
/*  96: 84 */       int dir2 = WorldCoord.getRightDir(this.Rotation);
/*  97: 85 */       wc.step(this.Rotation ^ 0x1);
/*  98:    */       
/*  99: 87 */       WorldCoord wc2 = wc.coordStep(this.Rotation ^ 0x1);
/* 100: 88 */       wc.step(dir2, 8);wc2.step(dir2, -8);
/* 101:    */       
/* 102: 90 */       return aoe.a(Math.min(wc.x, wc2.x) + 0.5D, -7.5D, Math.min(wc.z, wc2.z + 0.5D), Math.max(wc.x, wc2.x) + 0.5D, 8.5D, Math.max(wc.z, wc2.z) + 0.5D);
/* 103:    */     }
/* 104: 96 */     return aoe.a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public float getMultiBlockStrength(int subnum, qx player)
/* 108:    */   {
/* 109:100 */     return 0.08F;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public int getExtendedID()
/* 113:    */   {
/* 114:106 */     return 9;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public int getBlockID()
/* 118:    */   {
/* 119:110 */     return RedPowerMachine.blockMachine.cm;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public List getRelayBlockList(int wmt)
/* 123:    */   {
/* 124:115 */     ArrayList tr = new ArrayList();
/* 125:116 */     int dir2 = WorldCoord.getRightDir(this.Rotation);
/* 126:118 */     switch (wmt)
/* 127:    */     {
/* 128:    */     case 1: 
/* 129:120 */       for (int x = -3; x <= 3; x++) {
/* 130:120 */         for (int z = -3; z <= 3; z++) {
/* 131:121 */           for (int y = 1; y < 8; y++) {
/* 132:122 */             tr.add(new WorldCoord(x + this.l, y + this.m, z + this.n));
/* 133:    */           }
/* 134:    */         }
/* 135:    */       }
/* 136:124 */       break;
/* 137:    */     case 2: 
/* 138:127 */       for (int x = -8; x <= 8; x++) {
/* 139:128 */         for (int y = -8; y <= 8; y++)
/* 140:    */         {
/* 141:129 */           WorldCoord nc = new WorldCoord(this);
/* 142:130 */           nc.step(this.Rotation ^ 0x1);
/* 143:131 */           nc.step(dir2, x);
/* 144:132 */           nc.y += y;
/* 145:133 */           tr.add(nc);
/* 146:    */         }
/* 147:    */       }
/* 148:    */     }
/* 149:137 */     return tr;
/* 150:    */   }
/* 151:    */   
/* 152:    */   private void teardownBlades()
/* 153:    */   {
/* 154:141 */     this.hasBlades = false;
/* 155:    */     
/* 156:143 */     this.efficiency = 0;
/* 157:144 */     this.speed = 0;
/* 158:145 */     this.rayTrace = null;
/* 159:    */     
/* 160:    */ 
/* 161:    */ 
/* 162:    */ 
/* 163:    */ 
/* 164:151 */     updateBlock();
/* 165:152 */     List rbl = getRelayBlockList(this.windmillType);
/* 166:153 */     MultiLib.removeRelays(this.k, new WorldCoord(this), rbl);
/* 167:    */   }
/* 168:    */   
/* 169:    */   public void g()
/* 170:    */   {
/* 171:157 */     super.g();
/* 172:158 */     if (CoreLib.isClient(this.k))
/* 173:    */     {
/* 174:159 */       if (this.hasBrakes) {
/* 175:159 */         this.phase = ((int)(this.phase + this.speed * 0.1D));
/* 176:    */       } else {
/* 177:160 */         this.phase += this.speed;
/* 178:    */       }
/* 179:161 */       return;
/* 180:    */     }
/* 181:163 */     if (!isTickScheduled()) {
/* 182:164 */       scheduleTick(5);
/* 183:    */     }
/* 184:166 */     if (this.ConMask < 0)
/* 185:    */     {
/* 186:167 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 187:    */       
/* 188:169 */       this.EConMask = RedPowerLib.getExtConnections(this.k, this, this.l, this.m, this.n);
/* 189:    */       
/* 190:171 */       this.cond.recache(this.ConMask, this.EConMask);
/* 191:    */     }
/* 192:173 */     this.cond.iterate();
/* 193:174 */     dirtyBlock();
/* 194:176 */     if (this.hasBlades)
/* 195:    */     {
/* 196:177 */       if ((this.contents[0] == null) || (!(this.contents[0].b() instanceof ItemWindmill)))
/* 197:    */       {
/* 198:180 */         teardownBlades();
/* 199:181 */         return;
/* 200:    */       }
/* 201:183 */       ItemWindmill iwm = (ItemWindmill)this.contents[0].b();
/* 202:184 */       if (iwm.windmillType != this.windmillType)
/* 203:    */       {
/* 204:185 */         teardownBlades();
/* 205:186 */         return;
/* 206:    */       }
/* 207:188 */       if (this.propTicks <= 0)
/* 208:    */       {
/* 209:189 */         this.contents[0].b(this.contents[0].j() + 1);
/* 210:191 */         if (this.contents[0].j() > this.contents[0].k())
/* 211:    */         {
/* 212:193 */           this.contents[0] = null;
/* 213:194 */           d();
/* 214:195 */           teardownBlades();
/* 215:196 */           this.contents[0] = iwm.getBrokenItem();
/* 216:197 */           d();
/* 217:198 */           return;
/* 218:    */         }
/* 219:200 */         d();
/* 220:201 */         this.propTicks += 6600;
/* 221:    */       }
/* 222:203 */       if (this.hasBrakes) {
/* 223:203 */         return;
/* 224:    */       }
/* 225:204 */       this.propTicks -= 1;
/* 226:211 */       if (this.cond.getVoltage() > 130.0D) {
/* 227:211 */         return;
/* 228:    */       }
/* 229:212 */       this.cond.applyPower(this.power / 5);
/* 230:    */     }
/* 231:    */   }
/* 232:    */   
/* 233:    */   private void traceAir0()
/* 234:    */   {
/* 235:217 */     int yh = this.m + 1 + this.tracer / 28;
/* 236:218 */     int xp = this.tracer % 7;
/* 237:    */     
/* 238:    */ 
/* 239:221 */     int dir = 0;
/* 240:    */     WorldCoord tp;
/* 241:222 */     switch (this.tracer / 7 % 4)
/* 242:    */     {
/* 243:    */     case 0: 
/* 244:224 */       dir = 2;
/* 245:225 */       tp = new WorldCoord(this.l - 3 + xp, yh, this.n - 4);
/* 246:226 */       break;
/* 247:    */     case 1: 
/* 248:228 */       dir = 4;
/* 249:229 */       tp = new WorldCoord(this.l - 4, yh, this.n - 3 + xp);
/* 250:230 */       break;
/* 251:    */     case 2: 
/* 252:232 */       dir = 3;
/* 253:233 */       tp = new WorldCoord(this.l - 3 + xp, yh, this.n + 4);
/* 254:234 */       break;
/* 255:    */     default: 
/* 256:236 */       dir = 5;
/* 257:237 */       tp = new WorldCoord(this.l + 4, yh, this.n - 3 + xp);
/* 258:    */     }
/* 259:241 */     int i = 0;
/* 260:242 */     while ((i < 10) && 
/* 261:243 */       (this.k.a(tp.x, tp.y, tp.z) == 0))
/* 262:    */     {
/* 263:244 */       tp.step(dir);
/* 264:245 */       i++;
/* 265:    */     }
/* 266:247 */     if (this.rayTrace == null) {
/* 267:247 */       this.rayTrace = new byte['à'];
/* 268:    */     }
/* 269:248 */     this.efficiency = (this.efficiency - this.rayTrace[this.tracer] + i);
/* 270:249 */     this.rayTrace[this.tracer] = ((byte)i);
/* 271:250 */     this.tracer += 1;
/* 272:251 */     if (this.tracer >= 224) {
/* 273:251 */       this.tracer = 0;
/* 274:    */     }
/* 275:    */   }
/* 276:    */   
/* 277:    */   private void traceAir1()
/* 278:    */   {
/* 279:255 */     int yh = this.tracer / 17;
/* 280:256 */     int xp = this.tracer % 17;
/* 281:    */     
/* 282:258 */     int dir2 = WorldCoord.getRightDir(this.Rotation);
/* 283:259 */     WorldCoord tp = new WorldCoord(this);
/* 284:260 */     tp.step(this.Rotation ^ 0x1, 2);
/* 285:261 */     tp.step(dir2, xp - 8);
/* 286:262 */     tp.y += yh;
/* 287:    */     
/* 288:264 */     int i = 0;
/* 289:265 */     while ((i < 20) && 
/* 290:266 */       (this.k.a(tp.x, tp.y, tp.z) == 0))
/* 291:    */     {
/* 292:267 */       tp.step(this.Rotation ^ 0x1);
/* 293:268 */       i++;
/* 294:    */     }
/* 295:270 */     if (this.rayTrace == null) {
/* 296:270 */       this.rayTrace = new byte[289];
/* 297:    */     }
/* 298:271 */     this.efficiency = (this.efficiency - this.rayTrace[this.tracer] + i);
/* 299:272 */     this.rayTrace[this.tracer] = ((byte)i);
/* 300:273 */     this.tracer += 1;
/* 301:274 */     if (this.tracer >= 289) {
/* 302:274 */       this.tracer = 0;
/* 303:    */     }
/* 304:    */   }
/* 305:    */   
/* 306:    */   public int getWindScaled(int i)
/* 307:    */   {
/* 308:278 */     return Math.min(i, i * this.windSpeed / 13333);
/* 309:    */   }
/* 310:    */   
/* 311:    */   private void tryDeployBlades()
/* 312:    */   {
/* 313:282 */     ItemWindmill iwm = (ItemWindmill)this.contents[0].b();
/* 314:284 */     if (!iwm.canFaceDirection(this.Rotation)) {
/* 315:285 */       return;
/* 316:    */     }
/* 317:286 */     List rbl = getRelayBlockList(iwm.windmillType);
/* 318:287 */     if (MultiLib.isClear(this.k, new WorldCoord(this), rbl))
/* 319:    */     {
/* 320:289 */       this.windmillType = ((byte)iwm.windmillType);
/* 321:290 */       this.hasBlades = true;
/* 322:291 */       MultiLib.addRelays(this.k, new WorldCoord(this), 0, rbl);
/* 323:    */       
/* 324:293 */       updateBlock();
/* 325:    */     }
/* 326:    */   }
/* 327:    */   
/* 328:    */   public void onTileTick()
/* 329:    */   {
/* 330:298 */     if ((!this.hasBlades) && (this.contents[0] != null) && ((this.contents[0].b() instanceof ItemWindmill))) {
/* 331:300 */       tryDeployBlades();
/* 332:    */     }
/* 333:302 */     if ((!this.hasBrakes) && (this.cond.getVoltage() > 110.0D)) {
/* 334:303 */       this.hasBrakes = true;
/* 335:304 */     } else if ((this.hasBrakes) && (this.cond.getVoltage() < 100.0D)) {
/* 336:305 */       this.hasBrakes = false;
/* 337:    */     }
/* 338:308 */     this.windSpeed = ((int)(10000.0D * EnvironLib.getWindSpeed(this.k, new WorldCoord(this))));
/* 339:310 */     if (this.hasBlades)
/* 340:    */     {
/* 341:311 */       switch (this.windmillType)
/* 342:    */       {
/* 343:    */       case 1: 
/* 344:313 */         this.power = (2 * this.windSpeed * this.efficiency / 2240);
/* 345:314 */         this.speed = (this.power * this.power / 20000);
/* 346:315 */         traceAir0();
/* 347:316 */         break;
/* 348:    */       case 2: 
/* 349:318 */         this.power = (this.windSpeed * this.efficiency / 5780);
/* 350:319 */         this.speed = (this.power * this.power / 5000);
/* 351:320 */         traceAir1();
/* 352:    */       }
/* 353:327 */       updateBlock();
/* 354:    */     }
/* 355:329 */     scheduleTick(20);
/* 356:    */   }
/* 357:    */   
/* 358:    */   public int k_()
/* 359:    */   {
/* 360:335 */     return 1;
/* 361:    */   }
/* 362:    */   
/* 363:    */   public ur a(int i)
/* 364:    */   {
/* 365:339 */     return this.contents[i];
/* 366:    */   }
/* 367:    */   
/* 368:    */   public ur a(int i, int j)
/* 369:    */   {
/* 370:344 */     if (this.contents[i] == null) {
/* 371:344 */       return null;
/* 372:    */     }
/* 373:346 */     if (this.contents[i].a <= j)
/* 374:    */     {
/* 375:347 */       ur tr = this.contents[i];
/* 376:348 */       this.contents[i] = null;
/* 377:349 */       d();
/* 378:350 */       return tr;
/* 379:    */     }
/* 380:352 */     ur tr = this.contents[i].a(j);
/* 381:353 */     if (this.contents[i].a == 0) {
/* 382:354 */       this.contents[i] = null;
/* 383:    */     }
/* 384:355 */     d();
/* 385:356 */     return tr;
/* 386:    */   }
/* 387:    */   
/* 388:    */   public ur a_(int i)
/* 389:    */   {
/* 390:360 */     if (this.contents[i] == null) {
/* 391:360 */       return null;
/* 392:    */     }
/* 393:361 */     ur ist = this.contents[i];
/* 394:362 */     this.contents[i] = null;
/* 395:363 */     return ist;
/* 396:    */   }
/* 397:    */   
/* 398:    */   public void a(int i, ur ist)
/* 399:    */   {
/* 400:367 */     this.contents[i] = ist;
/* 401:368 */     if ((ist != null) && (ist.a > c())) {
/* 402:369 */       ist.a = c();
/* 403:    */     }
/* 404:370 */     d();
/* 405:    */   }
/* 406:    */   
/* 407:    */   public String b()
/* 408:    */   {
/* 409:374 */     return "Wind Turbine";
/* 410:    */   }
/* 411:    */   
/* 412:    */   public int c()
/* 413:    */   {
/* 414:378 */     return 64;
/* 415:    */   }
/* 416:    */   
/* 417:    */   public boolean a_(qx player)
/* 418:    */   {
/* 419:382 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 420:383 */       return false;
/* 421:    */     }
/* 422:384 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 423:    */   }
/* 424:    */   
/* 425:    */   public void f() {}
/* 426:    */   
/* 427:    */   public void l_() {}
/* 428:    */   
/* 429:    */   public void onBlockNeighborChange(int l)
/* 430:    */   {
/* 431:395 */     this.ConMask = -1;this.EConMask = -1;
/* 432:    */   }
/* 433:    */   
/* 434:    */   public boolean onBlockActivated(qx player)
/* 435:    */   {
/* 436:399 */     if (player.ah()) {
/* 437:399 */       return false;
/* 438:    */     }
/* 439:400 */     if (CoreLib.isClient(this.k)) {
/* 440:401 */       return true;
/* 441:    */     }
/* 442:402 */     player.openGui(RedPowerMachine.instance, 15, this.k, this.l, this.m, this.n);
/* 443:    */     
/* 444:404 */     return true;
/* 445:    */   }
/* 446:    */   
/* 447:    */   public void onBlockRemoval()
/* 448:    */   {
/* 449:408 */     super.onBlockRemoval();
/* 450:410 */     if (this.hasBlades) {
/* 451:411 */       teardownBlades();
/* 452:    */     }
/* 453:413 */     ur ist = this.contents[0];
/* 454:414 */     if ((ist != null) && (ist.a > 0)) {
/* 455:415 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 456:    */     }
/* 457:    */   }
/* 458:    */   
/* 459:    */   @SideOnly(Side.CLIENT)
/* 460:    */   public double m()
/* 461:    */   {
/* 462:431 */     return 1048576.0D;
/* 463:    */   }
/* 464:    */   
/* 465:    */   public void a(bq tag)
/* 466:    */   {
/* 467:437 */     super.a(tag);
/* 468:    */     
/* 469:439 */     by items = tag.m("Items");
/* 470:440 */     this.contents = new ur[k_()];
/* 471:441 */     for (int i = 0; i < items.c(); i++)
/* 472:    */     {
/* 473:442 */       bq item = (bq)items.b(i);
/* 474:    */       
/* 475:444 */       int j = item.c("Slot") & 0xFF;
/* 476:445 */       if ((j >= 0) && (j < this.contents.length)) {
/* 477:446 */         this.contents[j] = ur.a(item);
/* 478:    */       }
/* 479:    */     }
/* 480:450 */     this.windmillType = tag.c("wmt");
/* 481:451 */     this.hasBlades = (this.windmillType > 0);
/* 482:452 */     this.efficiency = 0;
/* 483:453 */     byte[] rt = tag.j("rays");
/* 484:454 */     if (rt != null) {
/* 485:455 */       switch (this.windmillType)
/* 486:    */       {
/* 487:    */       case 1: 
/* 488:457 */         if (rt.length != 224) {
/* 489:457 */           rt = null;
/* 490:    */         }
/* 491:    */         break;
/* 492:    */       case 2: 
/* 493:460 */         if (rt.length != 289) {
/* 494:460 */           rt = null;
/* 495:    */         }
/* 496:    */         break;
/* 497:    */       default: 
/* 498:463 */         rt = null;
/* 499:    */       }
/* 500:    */     }
/* 501:467 */     this.rayTrace = rt;
/* 502:468 */     if (rt != null) {
/* 503:469 */       for (int i = 0; i < rt.length; i++) {
/* 504:470 */         this.efficiency += rt[i];
/* 505:    */       }
/* 506:    */     }
/* 507:480 */     this.tracer = tag.e("tracer");
/* 508:481 */     this.speed = tag.e("speed");
/* 509:482 */     this.power = tag.e("spdpwr");
/* 510:483 */     this.propTicks = tag.e("proptick");
/* 511:484 */     this.cond.readFromNBT(tag);
/* 512:    */   }
/* 513:    */   
/* 514:    */   public void b(bq tag)
/* 515:    */   {
/* 516:488 */     super.b(tag);
/* 517:    */     
/* 518:490 */     by items = new by();
/* 519:491 */     for (int i = 0; i < this.contents.length; i++) {
/* 520:492 */       if (this.contents[i] != null)
/* 521:    */       {
/* 522:493 */         bq item = new bq();
/* 523:494 */         item.a("Slot", (byte)i);
/* 524:495 */         this.contents[i].b(item);
/* 525:496 */         items.a(item);
/* 526:    */       }
/* 527:    */     }
/* 528:499 */     tag.a("Items", items);
/* 529:500 */     if (!this.hasBlades) {
/* 530:500 */       this.windmillType = 0;
/* 531:    */     }
/* 532:501 */     tag.a("wmt", this.windmillType);
/* 533:502 */     if (this.rayTrace != null) {
/* 534:502 */       tag.a("rays", this.rayTrace);
/* 535:    */     }
/* 536:503 */     tag.a("tracer", this.tracer);
/* 537:504 */     tag.a("speed", this.speed);
/* 538:505 */     tag.a("spdpwr", this.power);
/* 539:506 */     tag.a("proptick", this.propTicks);
/* 540:507 */     this.cond.writeToNBT(tag);
/* 541:    */   }
/* 542:    */   
/* 543:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 544:    */     throws IOException
/* 545:    */   {
/* 546:513 */     super.readFromPacket(pkt);
/* 547:    */     
/* 548:515 */     int ps = pkt.getByte();
/* 549:516 */     this.hasBlades = ((ps & 0x1) > 0);
/* 550:517 */     this.hasBrakes = ((ps & 0x2) > 0);
/* 551:518 */     this.windmillType = ((byte)pkt.getByte());
/* 552:519 */     this.speed = ((int)pkt.getUVLC());
/* 553:    */   }
/* 554:    */   
/* 555:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 556:    */   {
/* 557:523 */     super.writeToPacket(pkt);
/* 558:    */     
/* 559:525 */     int ps = (this.hasBlades ? 1 : 0) | (this.hasBrakes ? 2 : 0);
/* 560:526 */     pkt.addByte(ps);
/* 561:527 */     pkt.addByte(this.windmillType);
/* 562:528 */     pkt.addUVLC(this.speed);
/* 563:    */   }
/* 564:    */   
/* 565:531 */   private byte[] rayTrace = null;
/* 566:532 */   private int efficiency = 0;
/* 567:533 */   private int tracer = 0;
/* 568:535 */   public int windSpeed = 0;
/* 569:536 */   public int speed = 0;
/* 570:537 */   public int phase = 0;
/* 571:539 */   private int power = 0;
/* 572:540 */   private int propTicks = 0;
/* 573:542 */   public boolean hasBlades = false;
/* 574:543 */   public boolean hasBrakes = false;
/* 575:544 */   public byte windmillType = 0;
/* 576:    */   protected ur[] contents;
/* 577:547 */   public int ConMask = -1;
/* 578:547 */   public int EConMask = -1;
/* 579:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileWindTurbine
 * JD-Core Version:    0.7.0.1
 */