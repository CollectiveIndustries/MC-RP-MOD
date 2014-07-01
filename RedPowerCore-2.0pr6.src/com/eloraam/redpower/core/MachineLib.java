/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import anm;
/*   4:    */ import any;
/*   5:    */ import java.util.ArrayList;
/*   6:    */ import java.util.Comparator;
/*   7:    */ import java.util.TreeMap;
/*   8:    */ import kz;
/*   9:    */ import la;
/*  10:    */ import net.minecraftforge.common.ForgeDirection;
/*  11:    */ import net.minecraftforge.common.ISidedInventory;
/*  12:    */ import px;
/*  13:    */ import qx;
/*  14:    */ import up;
/*  15:    */ import ur;
/*  16:    */ import yc;
/*  17:    */ 
/*  18:    */ public class MachineLib
/*  19:    */ {
/*  20:    */   public static class SubInventory
/*  21:    */     implements la
/*  22:    */   {
/*  23:    */     la parent;
/*  24:    */     int start;
/*  25:    */     int size;
/*  26:    */     
/*  27:    */     SubInventory(la par, int st, int sz)
/*  28:    */     {
/*  29: 23 */       this.parent = par;this.start = st;this.size = sz;
/*  30:    */     }
/*  31:    */     
/*  32:    */     public int k_()
/*  33:    */     {
/*  34: 26 */       return this.size;
/*  35:    */     }
/*  36:    */     
/*  37:    */     public ur a(int idx)
/*  38:    */     {
/*  39: 29 */       return this.parent.a(idx + this.start);
/*  40:    */     }
/*  41:    */     
/*  42:    */     public ur a(int idx, int num)
/*  43:    */     {
/*  44: 33 */       return this.parent.a(idx + this.start, num);
/*  45:    */     }
/*  46:    */     
/*  47:    */     public ur a_(int idx)
/*  48:    */     {
/*  49: 37 */       return this.parent.a_(idx + this.start);
/*  50:    */     }
/*  51:    */     
/*  52:    */     public void a(int idx, ur ist)
/*  53:    */     {
/*  54: 41 */       this.parent.a(idx + this.start, ist);
/*  55:    */     }
/*  56:    */     
/*  57:    */     public String b()
/*  58:    */     {
/*  59: 43 */       return this.parent.b();
/*  60:    */     }
/*  61:    */     
/*  62:    */     public int c()
/*  63:    */     {
/*  64: 46 */       return this.parent.c();
/*  65:    */     }
/*  66:    */     
/*  67:    */     public void d()
/*  68:    */     {
/*  69: 49 */       this.parent.d();
/*  70:    */     }
/*  71:    */     
/*  72:    */     public boolean a_(qx var1)
/*  73:    */     {
/*  74: 52 */       return this.parent.a_(var1);
/*  75:    */     }
/*  76:    */     
/*  77:    */     public void l_() {}
/*  78:    */     
/*  79:    */     public void f() {}
/*  80:    */   }
/*  81:    */   
/*  82:    */   public static la getInventory(yc world, WorldCoord wc)
/*  83:    */   {
/*  84: 61 */     la inv = (la)CoreLib.getTileEntity(world, wc, la.class);
/*  85: 63 */     if (!(inv instanceof anm)) {
/*  86: 63 */       return inv;
/*  87:    */     }
/*  88: 66 */     anm tec = (anm)CoreLib.getTileEntity(world, wc.x - 1, wc.y, wc.z, anm.class);
/*  89: 68 */     if (tec != null) {
/*  90: 69 */       return new kz("Large chest", tec, inv);
/*  91:    */     }
/*  92: 70 */     tec = (anm)CoreLib.getTileEntity(world, wc.x + 1, wc.y, wc.z, anm.class);
/*  93: 72 */     if (tec != null) {
/*  94: 73 */       return new kz("Large chest", inv, tec);
/*  95:    */     }
/*  96: 74 */     tec = (anm)CoreLib.getTileEntity(world, wc.x, wc.y, wc.z - 1, anm.class);
/*  97: 76 */     if (tec != null) {
/*  98: 77 */       return new kz("Large chest", tec, inv);
/*  99:    */     }
/* 100: 78 */     tec = (anm)CoreLib.getTileEntity(world, wc.x, wc.y, wc.z + 1, anm.class);
/* 101: 80 */     if (tec != null) {
/* 102: 81 */       return new kz("Large chest", inv, tec);
/* 103:    */     }
/* 104: 82 */     return inv;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public static la getSideInventory(yc world, WorldCoord wc, int side, boolean push)
/* 108:    */   {
/* 109: 88 */     la inv = getInventory(world, wc);
/* 110: 89 */     if (inv == null) {
/* 111: 89 */       return null;
/* 112:    */     }
/* 113: 91 */     if ((inv instanceof ISidedInventory))
/* 114:    */     {
/* 115: 92 */       ISidedInventory isi = (ISidedInventory)inv;
/* 116: 93 */       int start = isi.getStartInventorySide(ForgeDirection.getOrientation(side));
/* 117:    */       
/* 118: 95 */       int len = isi.getSizeInventorySide(ForgeDirection.getOrientation(side));
/* 119:    */       
/* 120: 97 */       return new SubInventory(inv, start, len);
/* 121:    */     }
/* 122: 99 */     return inv;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public static boolean addToInventoryCore(yc world, ur ist, WorldCoord wc, int side, boolean act)
/* 126:    */   {
/* 127:106 */     la inv = getInventory(world, wc);
/* 128:107 */     if (inv == null) {
/* 129:107 */       return false;
/* 130:    */     }
/* 131:108 */     int start = 0;
/* 132:109 */     int len = inv.k_();
/* 133:110 */     if ((inv instanceof ISidedInventory))
/* 134:    */     {
/* 135:111 */       ISidedInventory isi = (ISidedInventory)inv;
/* 136:112 */       start = isi.getStartInventorySide(ForgeDirection.getOrientation(side));
/* 137:    */       
/* 138:114 */       len = isi.getSizeInventorySide(ForgeDirection.getOrientation(side));
/* 139:    */     }
/* 140:117 */     return addToInventoryCore(inv, ist, start, len, act);
/* 141:    */   }
/* 142:    */   
/* 143:    */   public static boolean addToInventoryCore(la inv, ur ist, int start, int len, boolean act)
/* 144:    */   {
/* 145:123 */     for (int n = start; n < start + len; n++)
/* 146:    */     {
/* 147:124 */       ur invst = inv.a(n);
/* 148:125 */       if (invst == null)
/* 149:    */       {
/* 150:126 */         if (!act) {
/* 151:126 */           return true;
/* 152:    */         }
/* 153:    */       }
/* 154:129 */       else if ((ist.a(invst)) && 
/* 155:130 */         (ur.a(ist, invst)))
/* 156:    */       {
/* 157:132 */         int dfs = Math.min(invst.d(), inv.c());
/* 158:    */         
/* 159:134 */         dfs -= invst.a;
/* 160:135 */         if (dfs > 0)
/* 161:    */         {
/* 162:136 */           int si = Math.min(dfs, ist.a);
/* 163:137 */           if (!act) {
/* 164:137 */             return true;
/* 165:    */           }
/* 166:139 */           invst.a += si;
/* 167:140 */           inv.a(n, invst);
/* 168:141 */           ist.a -= si;
/* 169:142 */           if (ist.a == 0) {
/* 170:143 */             return true;
/* 171:    */           }
/* 172:    */         }
/* 173:    */       }
/* 174:    */     }
/* 175:146 */     if (!act) {
/* 176:146 */       return false;
/* 177:    */     }
/* 178:147 */     for (int n = start; n < start + len; n++)
/* 179:    */     {
/* 180:148 */       ur invst = inv.a(n);
/* 181:149 */       if (invst == null)
/* 182:    */       {
/* 183:150 */         if (inv.c() >= ist.a)
/* 184:    */         {
/* 185:152 */           inv.a(n, ist);
/* 186:153 */           return true;
/* 187:    */         }
/* 188:155 */         inv.a(n, ist.a(inv.c()));
/* 189:    */       }
/* 190:    */     }
/* 191:159 */     return false;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public static boolean addToInventory(yc world, ur ist, WorldCoord wc, int side)
/* 195:    */   {
/* 196:164 */     return addToInventoryCore(world, ist, wc, side, true);
/* 197:    */   }
/* 198:    */   
/* 199:    */   public static boolean canAddToInventory(yc world, ur ist, WorldCoord wc, int side)
/* 200:    */   {
/* 201:169 */     return addToInventoryCore(world, ist, wc, side, false);
/* 202:    */   }
/* 203:    */   
/* 204:    */   public static void ejectItem(yc world, WorldCoord wc, ur ist, int dir)
/* 205:    */   {
/* 206:174 */     wc = wc.copy();wc.step(dir);
/* 207:    */     
/* 208:176 */     px item = new px(world, wc.x + 0.5D, wc.y + 0.5D, wc.z + 0.5D, ist);
/* 209:    */     
/* 210:178 */     item.w = 0.0D;item.x = 0.0D;item.y = 0.0D;
/* 211:179 */     switch (dir)
/* 212:    */     {
/* 213:    */     case 0: 
/* 214:180 */       item.x = -0.3D; break;
/* 215:    */     case 1: 
/* 216:181 */       item.x = 0.3D; break;
/* 217:    */     case 2: 
/* 218:182 */       item.y = -0.3D; break;
/* 219:    */     case 3: 
/* 220:183 */       item.y = 0.3D; break;
/* 221:    */     case 4: 
/* 222:184 */       item.w = -0.3D; break;
/* 223:    */     default: 
/* 224:185 */       item.w = 0.3D;
/* 225:    */     }
/* 226:187 */     item.b = 10;
/* 227:188 */     world.d(item);
/* 228:    */   }
/* 229:    */   
/* 230:    */   public static boolean handleItem(yc world, ur ist, WorldCoord wc, int side)
/* 231:    */   {
/* 232:194 */     WorldCoord dest = wc.copy();
/* 233:195 */     dest.step(side);
/* 234:196 */     if (ist.a == 0) {
/* 235:196 */       return true;
/* 236:    */     }
/* 237:198 */     if (TubeLib.addToTubeRoute(world, ist, wc, dest, side ^ 0x1)) {
/* 238:199 */       return true;
/* 239:    */     }
/* 240:201 */     if (addToInventory(world, ist, dest, (side ^ 0x1) & 0x3F)) {
/* 241:201 */       return true;
/* 242:    */     }
/* 243:203 */     any te = (any)CoreLib.getTileEntity(world, dest, any.class);
/* 244:205 */     if (((te instanceof la)) || ((te instanceof ITubeConnectable))) {
/* 245:206 */       return false;
/* 246:    */     }
/* 247:207 */     if (world.isBlockSolidOnSide(dest.x, dest.y, dest.z, ForgeDirection.getOrientation(side ^ 0x1))) {
/* 248:209 */       return false;
/* 249:    */     }
/* 250:210 */     ejectItem(world, wc, ist, side);
/* 251:211 */     return true;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public static boolean handleItem(yc world, TubeItem ti, WorldCoord wc, int side)
/* 255:    */   {
/* 256:216 */     WorldCoord dest = wc.copy();
/* 257:217 */     dest.step(side);
/* 258:218 */     if (ti.item.a == 0) {
/* 259:218 */       return true;
/* 260:    */     }
/* 261:220 */     if (TubeLib.addToTubeRoute(world, ti, wc, dest, side ^ 0x1)) {
/* 262:221 */       return true;
/* 263:    */     }
/* 264:223 */     if (addToInventory(world, ti.item, dest, (side ^ 0x1) & 0x3F)) {
/* 265:223 */       return true;
/* 266:    */     }
/* 267:225 */     any te = (any)CoreLib.getTileEntity(world, dest, any.class);
/* 268:227 */     if (((te instanceof la)) || ((te instanceof ITubeConnectable))) {
/* 269:228 */       return false;
/* 270:    */     }
/* 271:229 */     if (world.isBlockSolidOnSide(dest.x, dest.y, dest.z, ForgeDirection.getOrientation(side ^ 0x1))) {
/* 272:231 */       return false;
/* 273:    */     }
/* 274:232 */     ejectItem(world, wc, ti.item, side);
/* 275:233 */     return true;
/* 276:    */   }
/* 277:    */   
/* 278:    */   public static boolean addToRandomInventory(yc world, ur ist, int i, int j, int k)
/* 279:    */   {
/* 280:259 */     return false;
/* 281:    */   }
/* 282:    */   
/* 283:    */   public static int compareItem(ur a, ur b)
/* 284:    */   {
/* 285:265 */     if (a.c != b.c) {
/* 286:266 */       return a.c - b.c;
/* 287:    */     }
/* 288:267 */     if (a.j() == b.j()) {
/* 289:268 */       return 0;
/* 290:    */     }
/* 291:269 */     if (a.b().l()) {
/* 292:270 */       return a.j() - b.j();
/* 293:    */     }
/* 294:272 */     int d1 = a.j() == a.k() - 1 ? 1 : a.j() <= 1 ? -1 : 0;
/* 295:    */     
/* 296:274 */     int d2 = b.j() == b.k() - 1 ? 1 : b.j() <= 1 ? -1 : 0;
/* 297:    */     
/* 298:276 */     return d1 - d2;
/* 299:    */   }
/* 300:    */   
/* 301:    */   public static class FilterMap
/* 302:    */   {
/* 303:    */     protected TreeMap map;
/* 304:    */     protected ur[] filter;
/* 305:    */     
/* 306:    */     public FilterMap(ur[] filt)
/* 307:    */     {
/* 308:281 */       this.filter = filt;
/* 309:282 */       this.map = new TreeMap(new Comparator()
/* 310:    */       {
/* 311:    */         public int compare(ur o1, ur o2)
/* 312:    */         {
/* 313:285 */           return MachineLib.compareItem(o1, o2);
/* 314:    */         }
/* 315:    */       });
/* 316:298 */       for (int i = 0; i < filt.length; i++) {
/* 317:299 */         if ((filt[i] != null) && (filt[i].a != 0))
/* 318:    */         {
/* 319:301 */           ArrayList arl = (ArrayList)this.map.get(filt[i]);
/* 320:302 */           if (arl == null)
/* 321:    */           {
/* 322:303 */             arl = new ArrayList();
/* 323:304 */             this.map.put(filt[i], arl);
/* 324:    */           }
/* 325:306 */           arl.add(Integer.valueOf(i));
/* 326:    */         }
/* 327:    */       }
/* 328:    */     }
/* 329:    */     
/* 330:    */     public int size()
/* 331:    */     {
/* 332:309 */       return this.map.size();
/* 333:    */     }
/* 334:    */     
/* 335:    */     public boolean containsKey(ur ist)
/* 336:    */     {
/* 337:311 */       return this.map.containsKey(ist);
/* 338:    */     }
/* 339:    */     
/* 340:    */     public int firstMatch(ur ist)
/* 341:    */     {
/* 342:315 */       ArrayList arl = (ArrayList)this.map.get(ist);
/* 343:316 */       if (arl == null) {
/* 344:316 */         return -1;
/* 345:    */       }
/* 346:317 */       return ((Integer)arl.get(0)).intValue();
/* 347:    */     }
/* 348:    */   }
/* 349:    */   
/* 350:    */   public static FilterMap makeFilterMap(ur[] ist)
/* 351:    */   {
/* 352:333 */     return new FilterMap(ist);
/* 353:    */   }
/* 354:    */   
/* 355:    */   public static FilterMap makeFilterMap(ur[] ist, int st, int ln)
/* 356:    */   {
/* 357:337 */     ur[] it = new ur[ln];
/* 358:338 */     System.arraycopy(ist, st, it, 0, ln);
/* 359:339 */     return new FilterMap(it);
/* 360:    */   }
/* 361:    */   
/* 362:    */   public static int[] genMatchCounts(FilterMap map)
/* 363:    */   {
/* 364:343 */     int[] tr = new int[map.filter.length];
/* 365:344 */     for (int n = 0; n < map.filter.length; n++)
/* 366:    */     {
/* 367:345 */       ur ist = map.filter[n];
/* 368:346 */       if ((ist != null) && (ist.a != 0))
/* 369:    */       {
/* 370:348 */         ArrayList arl = (ArrayList)map.map.get(ist);
/* 371:349 */         if (arl != null) {
/* 372:351 */           if (((Integer)arl.get(0)).intValue() == n) {
/* 373:352 */             for (Integer m : arl) {
/* 374:353 */               tr[n] += map.filter[m.intValue()].a;
/* 375:    */             }
/* 376:    */           }
/* 377:    */         }
/* 378:    */       }
/* 379:    */     }
/* 380:356 */     return tr;
/* 381:    */   }
/* 382:    */   
/* 383:    */   public static int decMatchCount(FilterMap map, int[] mc, ur ist)
/* 384:    */   {
/* 385:361 */     ArrayList arl = (ArrayList)map.map.get(ist);
/* 386:362 */     if (arl == null) {
/* 387:362 */       return 0;
/* 388:    */     }
/* 389:363 */     int n = ((Integer)arl.get(0)).intValue();
/* 390:364 */     int tr = Math.min(mc[n], ist.a);
/* 391:365 */     mc[n] -= tr;
/* 392:366 */     return tr;
/* 393:    */   }
/* 394:    */   
/* 395:    */   public static int getMatchCount(FilterMap map, int[] mc, ur ist)
/* 396:    */   {
/* 397:371 */     ArrayList arl = (ArrayList)map.map.get(ist);
/* 398:372 */     if (arl == null) {
/* 399:372 */       return 0;
/* 400:    */     }
/* 401:373 */     int n = ((Integer)arl.get(0)).intValue();
/* 402:374 */     int tr = Math.min(mc[n], ist.a);
/* 403:375 */     return tr;
/* 404:    */   }
/* 405:    */   
/* 406:    */   public static boolean isMatchEmpty(int[] mc)
/* 407:    */   {
/* 408:379 */     for (int i = 0; i < mc.length; i++) {
/* 409:380 */       if (mc[i] > 0) {
/* 410:380 */         return false;
/* 411:    */       }
/* 412:    */     }
/* 413:381 */     return true;
/* 414:    */   }
/* 415:    */   
/* 416:    */   public static void decMatchCounts(FilterMap map, int[] mc, la inv, int start, int len)
/* 417:    */   {
/* 418:386 */     for (int n = start; n < start + len; n++)
/* 419:    */     {
/* 420:387 */       ur ist = inv.a(n);
/* 421:388 */       if ((ist != null) && (ist.a != 0)) {
/* 422:389 */         decMatchCount(map, mc, ist);
/* 423:    */       }
/* 424:    */     }
/* 425:    */   }
/* 426:    */   
/* 427:    */   public static boolean matchOneStack(FilterMap map, la inv, int start, int len, int pos)
/* 428:    */   {
/* 429:395 */     ur match = map.filter[pos];
/* 430:396 */     int fc = match == null ? 1 : match.a;
/* 431:397 */     for (int n = start; n < start + len; n++)
/* 432:    */     {
/* 433:398 */       ur ist = inv.a(n);
/* 434:399 */       if ((ist != null) && (ist.a != 0))
/* 435:    */       {
/* 436:400 */         if (match == null) {
/* 437:400 */           return true;
/* 438:    */         }
/* 439:402 */         if (compareItem(match, ist) == 0)
/* 440:    */         {
/* 441:404 */           int m = Math.min(ist.a, fc);
/* 442:405 */           fc -= m;
/* 443:406 */           if (fc <= 0) {
/* 444:406 */             return true;
/* 445:    */           }
/* 446:    */         }
/* 447:    */       }
/* 448:    */     }
/* 449:408 */     return false;
/* 450:    */   }
/* 451:    */   
/* 452:    */   public static int matchAnyStack(FilterMap map, la inv, int start, int len)
/* 453:    */   {
/* 454:413 */     int[] mc = new int[map.filter.length];
/* 455:    */     ur ist;
/* 456:415 */     for (int n = start; n < start + len; n++)
/* 457:    */     {
/* 458:416 */       ist = inv.a(n);
/* 459:417 */       if ((ist != null) && (ist.a != 0))
/* 460:    */       {
/* 461:419 */         ArrayList arl = (ArrayList)map.map.get(ist);
/* 462:420 */         if (arl != null) {
/* 463:422 */           for (Integer m : arl)
/* 464:    */           {
/* 465:423 */             mc[m.intValue()] += ist.a;
/* 466:424 */             if (mc[m.intValue()] >= map.filter[m.intValue()].a) {
/* 467:425 */               return m.intValue();
/* 468:    */             }
/* 469:    */           }
/* 470:    */         }
/* 471:    */       }
/* 472:    */     }
/* 473:428 */     return -1;
/* 474:    */   }
/* 475:    */   
/* 476:    */   public static int matchAnyStackCol(FilterMap map, la inv, int start, int len, int col)
/* 477:    */   {
/* 478:433 */     int[] mc = new int[5];
/* 479:    */     ur ist;
/* 480:435 */     for (int n = start; n < start + len; n++)
/* 481:    */     {
/* 482:436 */       ist = inv.a(n);
/* 483:437 */       if ((ist != null) && (ist.a != 0))
/* 484:    */       {
/* 485:439 */         ArrayList arl = (ArrayList)map.map.get(ist);
/* 486:440 */         if (arl != null) {
/* 487:442 */           for (Integer m : arl) {
/* 488:443 */             if ((m.intValue() & 0x7) == col)
/* 489:    */             {
/* 490:444 */               int s = m.intValue() >> 3;
/* 491:445 */               mc[s] += ist.a;
/* 492:446 */               if (mc[s] >= map.filter[m.intValue()].a) {
/* 493:447 */                 return m.intValue();
/* 494:    */               }
/* 495:    */             }
/* 496:    */           }
/* 497:    */         }
/* 498:    */       }
/* 499:    */     }
/* 500:450 */     return -1;
/* 501:    */   }
/* 502:    */   
/* 503:    */   public static boolean matchAllCol(FilterMap map, la inv, int start, int len, int col)
/* 504:    */   {
/* 505:455 */     int[] mc = new int[5];
/* 506:    */     int ss;
/* 507:457 */     for (int n = start; n < start + len; n++)
/* 508:    */     {
/* 509:458 */       ur ist = inv.a(n);
/* 510:459 */       if ((ist != null) && (ist.a != 0))
/* 511:    */       {
/* 512:461 */         ArrayList arl = (ArrayList)map.map.get(ist);
/* 513:462 */         if (arl != null)
/* 514:    */         {
/* 515:464 */           ss = ist.a;
/* 516:465 */           for (Integer m : arl) {
/* 517:466 */             if ((m.intValue() & 0x7) == col)
/* 518:    */             {
/* 519:467 */               int c = m.intValue() >> 3;
/* 520:468 */               int s1 = Math.min(ss, map.filter[m.intValue()].a - mc[c]);
/* 521:    */               
/* 522:470 */               mc[c] += s1;ss -= s1;
/* 523:471 */               if (ss == 0) {
/* 524:    */                 break;
/* 525:    */               }
/* 526:    */             }
/* 527:    */           }
/* 528:    */         }
/* 529:    */       }
/* 530:    */     }
/* 531:474 */     boolean any = false;
/* 532:475 */     for (int n = 0; n < 5; n++)
/* 533:    */     {
/* 534:476 */       ur ct = map.filter[(n * 8 + col)];
/* 535:477 */       if ((ct != null) && (ct.a != 0))
/* 536:    */       {
/* 537:478 */         any = true;
/* 538:479 */         if (ct.a > mc[n]) {
/* 539:479 */           return false;
/* 540:    */         }
/* 541:    */       }
/* 542:    */     }
/* 543:481 */     return any;
/* 544:    */   }
/* 545:    */   
/* 546:    */   public static boolean emptyInventory(la inv, int start, int len)
/* 547:    */   {
/* 548:486 */     for (int n = start; n < start + len; n++)
/* 549:    */     {
/* 550:487 */       ur ist = inv.a(n);
/* 551:488 */       if ((ist != null) && (ist.a != 0)) {
/* 552:489 */         return false;
/* 553:    */       }
/* 554:    */     }
/* 555:491 */     return true;
/* 556:    */   }
/* 557:    */   
/* 558:    */   public static ur collectOneStack(la inv, int start, int len, ur match)
/* 559:    */   {
/* 560:496 */     ur tr = null;
/* 561:497 */     int fc = match == null ? 1 : match.a;
/* 562:498 */     for (int n = start; n < start + len; n++)
/* 563:    */     {
/* 564:499 */       ur ist = inv.a(n);
/* 565:500 */       if ((ist != null) && (ist.a != 0))
/* 566:    */       {
/* 567:501 */         if (match == null)
/* 568:    */         {
/* 569:502 */           inv.a(n, null);
/* 570:503 */           return ist;
/* 571:    */         }
/* 572:505 */         if (compareItem(match, ist) == 0)
/* 573:    */         {
/* 574:507 */           int m = Math.min(ist.a, fc);
/* 575:508 */           if (tr == null)
/* 576:    */           {
/* 577:509 */             tr = inv.a(n, m);
/* 578:    */           }
/* 579:    */           else
/* 580:    */           {
/* 581:511 */             inv.a(n, m);
/* 582:512 */             tr.a += m;
/* 583:    */           }
/* 584:514 */           fc -= m;
/* 585:515 */           if (fc <= 0) {
/* 586:    */             break;
/* 587:    */           }
/* 588:    */         }
/* 589:    */       }
/* 590:    */     }
/* 591:517 */     return tr;
/* 592:    */   }
/* 593:    */   
/* 594:    */   public static ur collectOneStackFuzzy(la inv, int start, int len, ur match)
/* 595:    */   {
/* 596:522 */     ur tr = null;
/* 597:523 */     int fc = match == null ? 1 : match.b().k();
/* 598:524 */     for (int n = start; n < start + len; n++)
/* 599:    */     {
/* 600:525 */       ur ist = inv.a(n);
/* 601:526 */       if ((ist != null) && (ist.a != 0))
/* 602:    */       {
/* 603:527 */         if (match == null)
/* 604:    */         {
/* 605:528 */           inv.a(n, null);
/* 606:529 */           return ist;
/* 607:    */         }
/* 608:531 */         if (compareItem(match, ist) == 0)
/* 609:    */         {
/* 610:533 */           int m = Math.min(ist.a, fc);
/* 611:534 */           if (tr == null)
/* 612:    */           {
/* 613:535 */             tr = inv.a(n, m);
/* 614:    */           }
/* 615:    */           else
/* 616:    */           {
/* 617:537 */             inv.a(n, m);
/* 618:538 */             tr.a += m;
/* 619:    */           }
/* 620:540 */           fc -= m;
/* 621:541 */           if (fc <= 0) {
/* 622:    */             break;
/* 623:    */           }
/* 624:    */         }
/* 625:    */       }
/* 626:    */     }
/* 627:543 */     return tr;
/* 628:    */   }
/* 629:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.MachineLib
 * JD-Core Version:    0.7.0.1
 */