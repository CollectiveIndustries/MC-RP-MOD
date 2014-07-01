/*    1:     */ package com.eloraam.redpower.core;
/*    2:     */ 
/*    3:     */ import amq;
/*    4:     */ import aoh;
/*    5:     */ import aoj;
/*    6:     */ import java.util.ArrayList;
/*    7:     */ import java.util.Arrays;
/*    8:     */ import java.util.HashMap;
/*    9:     */ import ur;
/*   10:     */ import yc;
/*   11:     */ 
/*   12:     */ public class CoverLib
/*   13:     */ {
/*   14:     */   public static void addMaterialHandler(IMaterialHandler handler)
/*   15:     */   {
/*   16:  20 */     for (int i = 0; i < 256; i++) {
/*   17:  21 */       if (materials[i] != null) {
/*   18:  22 */         handler.addMaterial(i);
/*   19:     */       }
/*   20:     */     }
/*   21:  25 */     materialHandlers.add(handler);
/*   22:     */   }
/*   23:     */   
/*   24:     */   public static Integer getMaterial(ur ist)
/*   25:     */   {
/*   26:  29 */     return (Integer)coverIndex.get(Arrays.asList(new Integer[] { Integer.valueOf(ist.c), Integer.valueOf(ist.j()) }));
/*   27:     */   }
/*   28:     */   
/*   29:     */   public static void addMaterial(int n, int hard, amq bl, String name, String desc)
/*   30:     */   {
/*   31:  35 */     addMaterial(n, hard, false, bl, 0, name, desc);
/*   32:     */   }
/*   33:     */   
/*   34:     */   public static void addMaterial(int n, int hard, amq bl, int md, String name, String desc)
/*   35:     */   {
/*   36:  40 */     addMaterial(n, hard, false, bl, md, name, desc);
/*   37:     */   }
/*   38:     */   
/*   39:     */   public static void addMaterial(int n, int hard, boolean tpar, amq bl, String name, String desc)
/*   40:     */   {
/*   41:  45 */     addMaterial(n, hard, tpar, bl, 0, name, desc);
/*   42:     */   }
/*   43:     */   
/*   44:     */   public static void addMaterial(int n, int hard, boolean tpar, amq bl, int md, String name, String desc)
/*   45:     */   {
/*   46:  51 */     ur ist = new ur(bl, 1, md);
/*   47:     */     
/*   48:  53 */     coverTextures[n] = new int[6];
/*   49:  54 */     for (int i = 0; i < 6; i++) {
/*   50:  55 */       coverTextures[n][i] = bl.a(i, md);
/*   51:     */     }
/*   52:  58 */     if (!bl.isDefaultTexture) {
/*   53:  59 */       coverTextureFiles[n] = bl.getTextureFile();
/*   54:     */     }
/*   55:  61 */     if ((bl instanceof IBlockHardness)) {
/*   56:  62 */       miningHardness[n] = ((IBlockHardness)bl).getPrototypicalHardness(md);
/*   57:     */     } else {
/*   58:  65 */       miningHardness[n] = bl.m(null, 0, 0, 0);
/*   59:     */     }
/*   60:  68 */     materials[n] = ist;
/*   61:  69 */     names[n] = name;
/*   62:  70 */     descs[n] = desc;
/*   63:  71 */     hardness[n] = hard;
/*   64:  72 */     transparency[n] = tpar;
/*   65:     */     
/*   66:  74 */     coverIndex.put(Arrays.asList(new Integer[] { Integer.valueOf(bl.cm), Integer.valueOf(md) }), Integer.valueOf(n));
/*   67:  76 */     for (IMaterialHandler imh : materialHandlers) {
/*   68:  77 */       imh.addMaterial(n);
/*   69:     */     }
/*   70:  79 */     Config.addName("tile.rpcover." + name + ".name", desc + " Cover");
/*   71:  80 */     Config.addName("tile.rppanel." + name + ".name", desc + " Panel");
/*   72:  81 */     Config.addName("tile.rpslab." + name + ".name", desc + " Slab");
/*   73:     */     
/*   74:  83 */     Config.addName("tile.rphcover." + name + ".name", "Hollow " + desc + " Cover");
/*   75:     */     
/*   76:  85 */     Config.addName("tile.rphpanel." + name + ".name", "Hollow " + desc + " Panel");
/*   77:     */     
/*   78:  87 */     Config.addName("tile.rphslab." + name + ".name", "Hollow " + desc + " Slab");
/*   79:     */     
/*   80:  89 */     Config.addName("tile.rpcovc." + name + ".name", desc + " Cover Corner");
/*   81:     */     
/*   82:  91 */     Config.addName("tile.rppanc." + name + ".name", desc + " Panel Corner");
/*   83:     */     
/*   84:  93 */     Config.addName("tile.rpslabc." + name + ".name", desc + " Slab Corner");
/*   85:     */     
/*   86:  95 */     Config.addName("tile.rpcovs." + name + ".name", desc + " Cover Strip");
/*   87:     */     
/*   88:  97 */     Config.addName("tile.rppans." + name + ".name", desc + " Panel Strip");
/*   89:     */     
/*   90:  99 */     Config.addName("tile.rpslabs." + name + ".name", desc + " Slab Strip");
/*   91:     */     
/*   92:     */ 
/*   93:     */ 
/*   94: 103 */     Config.addName("tile.rpcov3." + name + ".name", desc + " Triple Cover");
/*   95: 104 */     Config.addName("tile.rpcov5." + name + ".name", desc + " Cover Slab");
/*   96: 105 */     Config.addName("tile.rpcov6." + name + ".name", desc + " Triple Panel");
/*   97: 106 */     Config.addName("tile.rpcov7." + name + ".name", desc + " Anticover");
/*   98:     */     
/*   99:     */ 
/*  100: 109 */     Config.addName("tile.rphcov3." + name + ".name", desc + " Hollow Triple Cover");
/*  101: 110 */     Config.addName("tile.rphcov5." + name + ".name", desc + " Hollow Cover Slab");
/*  102: 111 */     Config.addName("tile.rphcov6." + name + ".name", desc + " Hollow Triple Panel");
/*  103: 112 */     Config.addName("tile.rphcov7." + name + ".name", desc + " Hollow Anticover");
/*  104:     */     
/*  105: 114 */     Config.addName("tile.rpcov3c." + name + ".name", desc + " Triple Cover Corner");
/*  106: 115 */     Config.addName("tile.rpcov5c." + name + ".name", desc + " Cover Slab Corner");
/*  107: 116 */     Config.addName("tile.rpcov6c." + name + ".name", desc + " Triple Panel Corner");
/*  108: 117 */     Config.addName("tile.rpcov7c." + name + ".name", desc + " Anticover Corner");
/*  109:     */     
/*  110: 119 */     Config.addName("tile.rpcov3s." + name + ".name", desc + " Triple Cover Strip");
/*  111: 120 */     Config.addName("tile.rpcov5s." + name + ".name", desc + " Cover Slab Strip");
/*  112: 121 */     Config.addName("tile.rpcov6s." + name + ".name", desc + " Triple Panel Strip");
/*  113: 122 */     Config.addName("tile.rpcov7s." + name + ".name", desc + " Anticover Strip");
/*  114:     */     
/*  115:     */ 
/*  116: 125 */     Config.addName("tile.rppole1." + name + ".name", desc + " Post");
/*  117: 126 */     Config.addName("tile.rppole2." + name + ".name", desc + " Pillar");
/*  118: 127 */     Config.addName("tile.rppole3." + name + ".name", desc + " Column");
/*  119:     */   }
/*  120:     */   
/*  121:     */   public static int damageToCoverData(int dmg)
/*  122:     */   {
/*  123: 132 */     int hd = dmg >> 8;
/*  124: 133 */     int cn = dmg & 0xFF;
/*  125: 135 */     switch (hd)
/*  126:     */     {
/*  127:     */     case 0: 
/*  128: 137 */       cn |= 0x10000; break;
/*  129:     */     case 16: 
/*  130: 138 */       cn |= 0x20100; break;
/*  131:     */     case 17: 
/*  132: 139 */       cn |= 0x40200; break;
/*  133:     */     case 24: 
/*  134: 140 */       cn |= 0x110300; break;
/*  135:     */     case 25: 
/*  136: 141 */       cn |= 0x120400; break;
/*  137:     */     case 26: 
/*  138: 142 */       cn |= 0x140500; break;
/*  139:     */     case 27: 
/*  140: 144 */       cn |= 0x30600; break;
/*  141:     */     case 28: 
/*  142: 145 */       cn |= 0x50700; break;
/*  143:     */     case 29: 
/*  144: 146 */       cn |= 0x60800; break;
/*  145:     */     case 30: 
/*  146: 147 */       cn |= 0x70900; break;
/*  147:     */     case 31: 
/*  148: 148 */       cn |= 0x130A00; break;
/*  149:     */     case 32: 
/*  150: 149 */       cn |= 0x150B00; break;
/*  151:     */     case 33: 
/*  152: 150 */       cn |= 0x160C00; break;
/*  153:     */     case 34: 
/*  154: 151 */       cn |= 0x170D00; break;
/*  155:     */     case 18: 
/*  156: 154 */       cn |= 0x2010000; break;
/*  157:     */     case 19: 
/*  158: 155 */       cn |= 0x2020100; break;
/*  159:     */     case 20: 
/*  160: 156 */       cn |= 0x2040200; break;
/*  161:     */     case 35: 
/*  162: 158 */       cn |= 0x2030300; break;
/*  163:     */     case 36: 
/*  164: 159 */       cn |= 0x2050400; break;
/*  165:     */     case 37: 
/*  166: 160 */       cn |= 0x2060500; break;
/*  167:     */     case 38: 
/*  168: 161 */       cn |= 0x2070600; break;
/*  169:     */     case 21: 
/*  170: 164 */       cn |= 0x1010000; break;
/*  171:     */     case 22: 
/*  172: 165 */       cn |= 0x1020100; break;
/*  173:     */     case 23: 
/*  174: 166 */       cn |= 0x1040200; break;
/*  175:     */     case 39: 
/*  176: 168 */       cn |= 0x1030300; break;
/*  177:     */     case 40: 
/*  178: 169 */       cn |= 0x1050400; break;
/*  179:     */     case 41: 
/*  180: 170 */       cn |= 0x1060500; break;
/*  181:     */     case 42: 
/*  182: 171 */       cn |= 0x1070600; break;
/*  183:     */     case 43: 
/*  184: 174 */       cn |= 0x3020000; break;
/*  185:     */     case 44: 
/*  186: 175 */       cn |= 0x3040100; break;
/*  187:     */     case 45: 
/*  188: 176 */       cn |= 0x3060200;
/*  189:     */     }
/*  190: 178 */     return cn;
/*  191:     */   }
/*  192:     */   
/*  193:     */   public static int damageToCoverValue(int dmg)
/*  194:     */   {
/*  195: 182 */     return damageToCoverData(dmg) & 0xFFFF;
/*  196:     */   }
/*  197:     */   
/*  198:     */   public static int coverValueToDamage(int side, int cov)
/*  199:     */   {
/*  200: 235 */     int hd = cov >> 8;
/*  201: 236 */     int cn = cov & 0xFF;
/*  202: 237 */     if (side < 6) {
/*  203: 238 */       switch (hd)
/*  204:     */       {
/*  205:     */       case 1: 
/*  206: 239 */         cn |= 0x1000; break;
/*  207:     */       case 2: 
/*  208: 240 */         cn |= 0x1100; break;
/*  209:     */       case 3: 
/*  210: 241 */         cn |= 0x1800; break;
/*  211:     */       case 4: 
/*  212: 242 */         cn |= 0x1900; break;
/*  213:     */       case 5: 
/*  214: 243 */         cn |= 0x1A00; break;
/*  215:     */       case 6: 
/*  216: 244 */         cn |= 0x1B00; break;
/*  217:     */       case 7: 
/*  218: 245 */         cn |= 0x1C00; break;
/*  219:     */       case 8: 
/*  220: 246 */         cn |= 0x1D00; break;
/*  221:     */       case 9: 
/*  222: 247 */         cn |= 0x1E00; break;
/*  223:     */       case 10: 
/*  224: 248 */         cn |= 0x1F00; break;
/*  225:     */       case 11: 
/*  226: 249 */         cn |= 0x2000; break;
/*  227:     */       case 12: 
/*  228: 250 */         cn |= 0x2100; break;
/*  229:     */       case 13: 
/*  230: 251 */         cn |= 0x2200;
/*  231:     */       }
/*  232: 253 */     } else if (side < 14) {
/*  233: 254 */       switch (hd)
/*  234:     */       {
/*  235:     */       case 0: 
/*  236: 255 */         cn |= 0x1200; break;
/*  237:     */       case 1: 
/*  238: 256 */         cn |= 0x1300; break;
/*  239:     */       case 2: 
/*  240: 257 */         cn |= 0x1400; break;
/*  241:     */       case 3: 
/*  242: 259 */         cn |= 0x2300; break;
/*  243:     */       case 4: 
/*  244: 260 */         cn |= 0x2400; break;
/*  245:     */       case 5: 
/*  246: 261 */         cn |= 0x2500; break;
/*  247:     */       case 6: 
/*  248: 262 */         cn |= 0x2600;
/*  249:     */       }
/*  250: 264 */     } else if (side < 26) {
/*  251: 265 */       switch (hd)
/*  252:     */       {
/*  253:     */       case 0: 
/*  254: 266 */         cn |= 0x1500; break;
/*  255:     */       case 1: 
/*  256: 267 */         cn |= 0x1600; break;
/*  257:     */       case 2: 
/*  258: 268 */         cn |= 0x1700; break;
/*  259:     */       case 3: 
/*  260: 270 */         cn |= 0x2700; break;
/*  261:     */       case 4: 
/*  262: 271 */         cn |= 0x2800; break;
/*  263:     */       case 5: 
/*  264: 272 */         cn |= 0x2900; break;
/*  265:     */       case 6: 
/*  266: 273 */         cn |= 0x2A00;
/*  267:     */       }
/*  268: 275 */     } else if (side < 29) {
/*  269: 276 */       switch (hd)
/*  270:     */       {
/*  271:     */       case 0: 
/*  272: 277 */         cn |= 0x2B00; break;
/*  273:     */       case 1: 
/*  274: 278 */         cn |= 0x2C00; break;
/*  275:     */       case 2: 
/*  276: 279 */         cn |= 0x2D00;
/*  277:     */       }
/*  278:     */     }
/*  279: 282 */     return cn;
/*  280:     */   }
/*  281:     */   
/*  282:     */   public static ur convertCoverPlate(int side, int cov)
/*  283:     */   {
/*  284: 286 */     if (blockCoverPlate == null) {
/*  285: 286 */       return null;
/*  286:     */     }
/*  287: 287 */     return new ur(blockCoverPlate, 1, coverValueToDamage(side, cov));
/*  288:     */   }
/*  289:     */   
/*  290:     */   public static int cornerToCoverMask(int cn)
/*  291:     */   {
/*  292: 292 */     switch (cn)
/*  293:     */     {
/*  294:     */     case 0: 
/*  295: 293 */       return 21;
/*  296:     */     case 1: 
/*  297: 294 */       return 25;
/*  298:     */     case 2: 
/*  299: 295 */       return 37;
/*  300:     */     case 3: 
/*  301: 296 */       return 41;
/*  302:     */     case 4: 
/*  303: 297 */       return 22;
/*  304:     */     case 5: 
/*  305: 298 */       return 26;
/*  306:     */     case 6: 
/*  307: 299 */       return 38;
/*  308:     */     }
/*  309: 300 */     return 42;
/*  310:     */   }
/*  311:     */   
/*  312:     */   public static int coverToCornerMask(int cn)
/*  313:     */   {
/*  314: 305 */     switch (cn)
/*  315:     */     {
/*  316:     */     case 0: 
/*  317: 306 */       return 15;
/*  318:     */     case 1: 
/*  319: 307 */       return 240;
/*  320:     */     case 2: 
/*  321: 308 */       return 85;
/*  322:     */     case 3: 
/*  323: 309 */       return 170;
/*  324:     */     case 4: 
/*  325: 310 */       return 51;
/*  326:     */     }
/*  327: 311 */     return 204;
/*  328:     */   }
/*  329:     */   
/*  330:     */   public static int coverToStripMask(int cn)
/*  331:     */   {
/*  332: 316 */     switch (cn)
/*  333:     */     {
/*  334:     */     case 0: 
/*  335: 317 */       return 15;
/*  336:     */     case 1: 
/*  337: 318 */       return 3840;
/*  338:     */     case 2: 
/*  339: 319 */       return 337;
/*  340:     */     case 3: 
/*  341: 320 */       return 674;
/*  342:     */     case 4: 
/*  343: 321 */       return 1076;
/*  344:     */     }
/*  345: 322 */     return 2248;
/*  346:     */   }
/*  347:     */   
/*  348:     */   public static int stripToCornerMask(int sn)
/*  349:     */   {
/*  350: 327 */     switch (sn)
/*  351:     */     {
/*  352:     */     case 0: 
/*  353: 328 */       return 5;
/*  354:     */     case 1: 
/*  355: 329 */       return 10;
/*  356:     */     case 2: 
/*  357: 330 */       return 3;
/*  358:     */     case 3: 
/*  359: 331 */       return 12;
/*  360:     */     case 4: 
/*  361: 332 */       return 17;
/*  362:     */     case 5: 
/*  363: 333 */       return 34;
/*  364:     */     case 6: 
/*  365: 334 */       return 68;
/*  366:     */     case 7: 
/*  367: 335 */       return 136;
/*  368:     */     case 8: 
/*  369: 336 */       return 80;
/*  370:     */     case 9: 
/*  371: 337 */       return 160;
/*  372:     */     case 10: 
/*  373: 338 */       return 48;
/*  374:     */     }
/*  375: 339 */     return 192;
/*  376:     */   }
/*  377:     */   
/*  378:     */   public static int stripToCoverMask(int sn)
/*  379:     */   {
/*  380: 344 */     switch (sn)
/*  381:     */     {
/*  382:     */     case 0: 
/*  383: 345 */       return 5;
/*  384:     */     case 1: 
/*  385: 346 */       return 9;
/*  386:     */     case 2: 
/*  387: 347 */       return 17;
/*  388:     */     case 3: 
/*  389: 348 */       return 33;
/*  390:     */     case 4: 
/*  391: 349 */       return 20;
/*  392:     */     case 5: 
/*  393: 350 */       return 24;
/*  394:     */     case 6: 
/*  395: 351 */       return 36;
/*  396:     */     case 7: 
/*  397: 352 */       return 40;
/*  398:     */     case 8: 
/*  399: 353 */       return 6;
/*  400:     */     case 9: 
/*  401: 354 */       return 10;
/*  402:     */     case 10: 
/*  403: 355 */       return 18;
/*  404:     */     }
/*  405: 356 */     return 34;
/*  406:     */   }
/*  407:     */   
/*  408:     */   public static float getThickness(int side, int cov)
/*  409:     */   {
/*  410: 361 */     if (side < 6)
/*  411:     */     {
/*  412: 362 */       switch (cov >> 8)
/*  413:     */       {
/*  414:     */       case 0: 
/*  415: 363 */         return 0.125F;
/*  416:     */       case 1: 
/*  417: 364 */         return 0.25F;
/*  418:     */       case 2: 
/*  419: 365 */         return 0.5F;
/*  420:     */       case 3: 
/*  421: 366 */         return 0.125F;
/*  422:     */       case 4: 
/*  423: 367 */         return 0.25F;
/*  424:     */       case 5: 
/*  425: 368 */         return 0.5F;
/*  426:     */       case 6: 
/*  427: 370 */         return 0.375F;
/*  428:     */       case 7: 
/*  429: 371 */         return 0.625F;
/*  430:     */       case 8: 
/*  431: 372 */         return 0.75F;
/*  432:     */       case 9: 
/*  433: 373 */         return 0.875F;
/*  434:     */       case 10: 
/*  435: 374 */         return 0.375F;
/*  436:     */       case 11: 
/*  437: 375 */         return 0.625F;
/*  438:     */       case 12: 
/*  439: 376 */         return 0.75F;
/*  440:     */       case 13: 
/*  441: 377 */         return 0.875F;
/*  442:     */       }
/*  443: 379 */       return 1.0F;
/*  444:     */     }
/*  445: 381 */     if ((side >= 26) && (side < 29)) {
/*  446: 382 */       switch (cov >> 8)
/*  447:     */       {
/*  448:     */       case 0: 
/*  449: 383 */         return 0.125F;
/*  450:     */       case 1: 
/*  451: 384 */         return 0.25F;
/*  452:     */       case 2: 
/*  453: 385 */         return 0.375F;
/*  454:     */       }
/*  455:     */     }
/*  456: 388 */     switch (cov >> 8)
/*  457:     */     {
/*  458:     */     case 0: 
/*  459: 389 */       return 0.125F;
/*  460:     */     case 1: 
/*  461: 390 */       return 0.25F;
/*  462:     */     case 2: 
/*  463: 391 */       return 0.5F;
/*  464:     */     case 3: 
/*  465: 392 */       return 0.375F;
/*  466:     */     case 4: 
/*  467: 393 */       return 0.625F;
/*  468:     */     case 5: 
/*  469: 394 */       return 0.75F;
/*  470:     */     case 6: 
/*  471: 395 */       return 0.875F;
/*  472:     */     }
/*  473: 397 */     return 1.0F;
/*  474:     */   }
/*  475:     */   
/*  476:     */   public static int getThicknessQuanta(int side, int cov)
/*  477:     */   {
/*  478: 401 */     if (side < 6)
/*  479:     */     {
/*  480: 402 */       switch (cov >> 8)
/*  481:     */       {
/*  482:     */       case 0: 
/*  483: 403 */         return 1;
/*  484:     */       case 1: 
/*  485: 404 */         return 2;
/*  486:     */       case 2: 
/*  487: 405 */         return 4;
/*  488:     */       case 3: 
/*  489: 406 */         return 1;
/*  490:     */       case 4: 
/*  491: 407 */         return 2;
/*  492:     */       case 5: 
/*  493: 408 */         return 4;
/*  494:     */       case 6: 
/*  495: 410 */         return 3;
/*  496:     */       case 7: 
/*  497: 411 */         return 5;
/*  498:     */       case 8: 
/*  499: 412 */         return 6;
/*  500:     */       case 9: 
/*  501: 413 */         return 7;
/*  502:     */       case 10: 
/*  503: 414 */         return 3;
/*  504:     */       case 11: 
/*  505: 415 */         return 5;
/*  506:     */       case 12: 
/*  507: 416 */         return 6;
/*  508:     */       case 13: 
/*  509: 417 */         return 7;
/*  510:     */       }
/*  511: 419 */       return 0;
/*  512:     */     }
/*  513: 421 */     if ((side >= 26) && (side < 29)) {
/*  514: 422 */       switch (cov >> 8)
/*  515:     */       {
/*  516:     */       case 0: 
/*  517: 423 */         return 1;
/*  518:     */       case 1: 
/*  519: 424 */         return 2;
/*  520:     */       case 2: 
/*  521: 425 */         return 3;
/*  522:     */       }
/*  523:     */     }
/*  524: 428 */     switch (cov >> 8)
/*  525:     */     {
/*  526:     */     case 0: 
/*  527: 429 */       return 1;
/*  528:     */     case 1: 
/*  529: 430 */       return 2;
/*  530:     */     case 2: 
/*  531: 431 */       return 4;
/*  532:     */     case 3: 
/*  533: 432 */       return 3;
/*  534:     */     case 4: 
/*  535: 433 */       return 5;
/*  536:     */     case 5: 
/*  537: 434 */       return 6;
/*  538:     */     case 6: 
/*  539: 435 */       return 7;
/*  540:     */     }
/*  541: 437 */     return 0;
/*  542:     */   }
/*  543:     */   
/*  544:     */   private static class PlacementValidator
/*  545:     */   {
/*  546: 441 */     public int cornermask = 0;
/*  547: 441 */     public int sidemask = 0;
/*  548: 442 */     public int hollowcornermask = 0;
/*  549: 442 */     public int fillcornermask = 0;
/*  550: 443 */     public int thickfaces = 0;
/*  551: 448 */     public int[] quanta = new int[29];
/*  552:     */     public int covm;
/*  553:     */     public short[] covs;
/*  554:     */     
/*  555:     */     public PlacementValidator(int cm, short[] cs)
/*  556:     */     {
/*  557: 450 */       this.covm = cm;this.covs = cs;
/*  558:     */     }
/*  559:     */     
/*  560:     */     public boolean checkThickFace(int type)
/*  561:     */     {
/*  562: 457 */       for (int i = 0; i < 6; i++) {
/*  563: 458 */         if (((this.covm & 1 << i) != 0) && 
/*  564: 459 */           (this.covs[i] >> 8 == type))
/*  565:     */         {
/*  566: 461 */           int t = CoverLib.coverToCornerMask(i);
/*  567: 462 */           if ((this.fillcornermask & t) > 0) {
/*  568: 462 */             return false;
/*  569:     */           }
/*  570: 463 */           this.fillcornermask |= t;
/*  571: 464 */           this.sidemask |= CoverLib.coverToStripMask(i);
/*  572:     */         }
/*  573:     */       }
/*  574: 466 */       return true;
/*  575:     */     }
/*  576:     */     
/*  577:     */     public boolean checkThickSide(int type)
/*  578:     */     {
/*  579: 471 */       for (int i = 0; i < 12; i++) {
/*  580: 472 */         if (((this.covm & 1 << i + 14) != 0) && 
/*  581: 473 */           (this.covs[(i + 14)] >> 8 == type))
/*  582:     */         {
/*  583: 475 */           int t = CoverLib.stripToCornerMask(i);
/*  584: 476 */           if ((this.fillcornermask & t) > 0) {
/*  585: 476 */             return false;
/*  586:     */           }
/*  587: 477 */           this.fillcornermask |= t;
/*  588: 478 */           this.sidemask |= 1 << i;
/*  589:     */         }
/*  590:     */       }
/*  591: 480 */       return true;
/*  592:     */     }
/*  593:     */     
/*  594:     */     public boolean checkThickCorner(int type)
/*  595:     */     {
/*  596: 485 */       for (int i = 0; i < 8; i++) {
/*  597: 486 */         if (((this.covm & 1 << i + 6) != 0) && 
/*  598: 487 */           (this.covs[(i + 6)] >> 8 == type))
/*  599:     */         {
/*  600: 488 */           int t = 1 << i;
/*  601: 489 */           if ((this.fillcornermask & t) == t) {
/*  602: 489 */             return false;
/*  603:     */           }
/*  604: 490 */           this.fillcornermask |= t;
/*  605:     */         }
/*  606:     */       }
/*  607: 492 */       return true;
/*  608:     */     }
/*  609:     */     
/*  610:     */     public boolean checkFace(int type)
/*  611:     */     {
/*  612: 497 */       for (int i = 0; i < 6; i++) {
/*  613: 498 */         if (((this.covm & 1 << i) != 0) && 
/*  614: 499 */           (this.covs[i] >> 8 == type))
/*  615:     */         {
/*  616: 501 */           int t = CoverLib.coverToCornerMask(i);
/*  617: 502 */           if ((this.fillcornermask & t) == t) {
/*  618: 502 */             return false;
/*  619:     */           }
/*  620: 503 */           this.cornermask |= t;
/*  621: 504 */           this.sidemask |= CoverLib.coverToStripMask(i);
/*  622:     */         }
/*  623:     */       }
/*  624: 506 */       return true;
/*  625:     */     }
/*  626:     */     
/*  627:     */     public boolean checkSide(int type)
/*  628:     */     {
/*  629: 511 */       for (int i = 0; i < 12; i++) {
/*  630: 512 */         if (((this.covm & 1 << i + 14) != 0) && 
/*  631: 513 */           (this.covs[(i + 14)] >> 8 == type))
/*  632:     */         {
/*  633: 515 */           int t = CoverLib.stripToCornerMask(i);
/*  634: 516 */           if ((this.fillcornermask & t) == t) {
/*  635: 516 */             return false;
/*  636:     */           }
/*  637: 517 */           if ((this.sidemask & 1 << i) > 0) {
/*  638: 517 */             return false;
/*  639:     */           }
/*  640: 518 */           this.cornermask |= t;
/*  641: 519 */           this.sidemask |= 1 << i;
/*  642:     */         }
/*  643:     */       }
/*  644: 521 */       return true;
/*  645:     */     }
/*  646:     */     
/*  647:     */     public boolean checkCorner(int type)
/*  648:     */     {
/*  649: 526 */       for (int i = 0; i < 8; i++) {
/*  650: 527 */         if (((this.covm & 1 << i + 6) != 0) && 
/*  651: 528 */           (this.covs[(i + 6)] >> 8 == type))
/*  652:     */         {
/*  653: 529 */           int t = 1 << i;
/*  654: 530 */           if ((this.cornermask & t) == t) {
/*  655: 530 */             return false;
/*  656:     */           }
/*  657: 531 */           this.cornermask |= t;
/*  658:     */         }
/*  659:     */       }
/*  660: 533 */       return true;
/*  661:     */     }
/*  662:     */     
/*  663:     */     public boolean checkHollow(int type)
/*  664:     */     {
/*  665: 538 */       for (int i = 0; i < 6; i++) {
/*  666: 539 */         if (((this.covm & 1 << i) != 0) && 
/*  667: 540 */           (this.covs[i] >> 8 == type))
/*  668:     */         {
/*  669: 542 */           int t = CoverLib.coverToCornerMask(i);
/*  670: 543 */           if ((this.cornermask & t) > 0) {
/*  671: 543 */             return false;
/*  672:     */           }
/*  673: 544 */           this.cornermask |= t;
/*  674: 545 */           this.hollowcornermask |= t;
/*  675: 546 */           t = CoverLib.coverToStripMask(i);
/*  676: 547 */           if ((this.sidemask & t) > 0) {
/*  677: 547 */             return false;
/*  678:     */           }
/*  679: 548 */           this.sidemask |= t;
/*  680:     */         }
/*  681:     */       }
/*  682: 550 */       return true;
/*  683:     */     }
/*  684:     */     
/*  685:     */     public boolean checkHollowCover(int type)
/*  686:     */     {
/*  687: 555 */       int ocm = 0;int osm = 0;
/*  688: 556 */       for (int i = 0; i < 6; i++) {
/*  689: 557 */         if (((this.covm & 1 << i) != 0) && 
/*  690: 558 */           (this.covs[i] >> 8 == type))
/*  691:     */         {
/*  692: 560 */           int t = CoverLib.coverToCornerMask(i);
/*  693: 561 */           if ((this.cornermask & t) > 0) {
/*  694: 561 */             return false;
/*  695:     */           }
/*  696: 562 */           ocm |= t;
/*  697: 563 */           t = CoverLib.coverToStripMask(i);
/*  698: 564 */           if ((this.sidemask & t) > 0) {
/*  699: 564 */             return false;
/*  700:     */           }
/*  701: 565 */           osm |= t;
/*  702:     */         }
/*  703:     */       }
/*  704: 567 */       this.cornermask |= ocm;this.sidemask |= osm;
/*  705: 568 */       return true;
/*  706:     */     }
/*  707:     */     
/*  708:     */     public void calcQuanta()
/*  709:     */     {
/*  710: 573 */       for (int i = 0; i < 29; i++) {
/*  711: 574 */         if ((this.covm & 1 << i) == 0) {
/*  712: 574 */           this.quanta[i] = 0;
/*  713:     */         } else {
/*  714: 575 */           this.quanta[i] = CoverLib.getThicknessQuanta(i, this.covs[i]);
/*  715:     */         }
/*  716:     */       }
/*  717:     */     }
/*  718:     */     
/*  719:     */     private boolean checkOverlap(int a, int b, int c, int d)
/*  720:     */     {
/*  721: 580 */       a = this.quanta[a];b = this.quanta[b];c = this.quanta[c];d = this.quanta[d];
/*  722: 581 */       return (a + b > 8) || (a + c > 8) || (a + d > 8) || (b + c > 8) || (b + d > 8) || (c + d > 8);
/*  723:     */     }
/*  724:     */     
/*  725:     */     public boolean checkImpingement()
/*  726:     */     {
/*  727: 587 */       for (int i = 0; i < 6; i += 2) {
/*  728: 588 */         if (this.quanta[i] + this.quanta[(i + 1)] > 8) {
/*  729: 589 */           return false;
/*  730:     */         }
/*  731:     */       }
/*  732: 593 */       if (checkOverlap(14, 15, 22, 23)) {
/*  733: 593 */         return false;
/*  734:     */       }
/*  735: 594 */       if (checkOverlap(16, 17, 24, 25)) {
/*  736: 594 */         return false;
/*  737:     */       }
/*  738: 595 */       if (checkOverlap(18, 19, 20, 22)) {
/*  739: 595 */         return false;
/*  740:     */       }
/*  741: 598 */       if (checkOverlap(6, 7, 8, 9)) {
/*  742: 598 */         return false;
/*  743:     */       }
/*  744: 599 */       if (checkOverlap(10, 11, 12, 13)) {
/*  745: 599 */         return false;
/*  746:     */       }
/*  747: 600 */       if (checkOverlap(6, 8, 10, 12)) {
/*  748: 600 */         return false;
/*  749:     */       }
/*  750: 601 */       if (checkOverlap(7, 9, 11, 13)) {
/*  751: 601 */         return false;
/*  752:     */       }
/*  753: 602 */       if (checkOverlap(6, 7, 10, 11)) {
/*  754: 602 */         return false;
/*  755:     */       }
/*  756: 603 */       if (checkOverlap(8, 9, 12, 13)) {
/*  757: 603 */         return false;
/*  758:     */       }
/*  759: 606 */       for (int i = 0; i < 6; i++)
/*  760:     */       {
/*  761: 607 */         int q1 = this.quanta[i];
/*  762: 608 */         if (q1 != 0)
/*  763:     */         {
/*  764: 610 */           int cm = CoverLib.coverToCornerMask(i);
/*  765: 611 */           int sm1 = CoverLib.coverToStripMask(i);
/*  766: 612 */           int sm2 = CoverLib.coverToStripMask(i ^ 0x1);
/*  767: 615 */           for (int j = 0; j < 8; j++)
/*  768:     */           {
/*  769: 616 */             int q2 = this.quanta[(6 + j)];
/*  770: 617 */             if ((cm & 1 << j) == 0)
/*  771:     */             {
/*  772: 618 */               if (q1 + q2 > 8) {
/*  773: 618 */                 return false;
/*  774:     */               }
/*  775:     */             }
/*  776: 620 */             else if ((q2 > 0) && (q2 < q1)) {
/*  777: 620 */               return false;
/*  778:     */             }
/*  779:     */           }
/*  780: 624 */           for (int j = 0; j < 12; j++)
/*  781:     */           {
/*  782: 625 */             int q2 = this.quanta[(14 + j)];
/*  783: 626 */             if ((sm2 & 1 << j) > 0)
/*  784:     */             {
/*  785: 627 */               if (q1 + q2 > 8) {
/*  786: 627 */                 return false;
/*  787:     */               }
/*  788:     */             }
/*  789: 628 */             else if (((sm1 & 1 << j) > 0) && 
/*  790: 629 */               (q2 > 0) && (q2 < q1)) {
/*  791: 629 */               return false;
/*  792:     */             }
/*  793:     */           }
/*  794:     */         }
/*  795:     */       }
/*  796: 635 */       for (int i = 0; i < 12; i++)
/*  797:     */       {
/*  798: 636 */         int q1 = this.quanta[(14 + i)];
/*  799: 637 */         if (q1 != 0)
/*  800:     */         {
/*  801: 639 */           int cm = CoverLib.stripToCornerMask(i);
/*  802: 640 */           for (int j = 0; j < 8; j++)
/*  803:     */           {
/*  804: 641 */             int q2 = this.quanta[(6 + j)];
/*  805: 642 */             if ((cm & 1 << j) == 0)
/*  806:     */             {
/*  807: 643 */               if (q1 + q2 > 8) {
/*  808: 643 */                 return false;
/*  809:     */               }
/*  810:     */             }
/*  811: 645 */             else if ((q2 > 0) && (q2 < q1)) {
/*  812: 645 */               return false;
/*  813:     */             }
/*  814:     */           }
/*  815:     */         }
/*  816:     */       }
/*  817: 651 */       for (int i = 0; i < 3; i++)
/*  818:     */       {
/*  819: 652 */         int q1 = this.quanta[(26 + i)];
/*  820: 653 */         if (q1 != 0)
/*  821:     */         {
/*  822: 656 */           for (int j = 0; j < 8; j++)
/*  823:     */           {
/*  824: 657 */             int q2 = this.quanta[(6 + j)];
/*  825: 658 */             if (q1 + q2 > 4) {
/*  826: 658 */               return false;
/*  827:     */             }
/*  828:     */           }
/*  829: 662 */           for (int j = 0; j < 12; j++)
/*  830:     */           {
/*  831: 663 */             int q2 = this.quanta[(14 + j)];
/*  832: 664 */             if (q1 + q2 > 4) {
/*  833: 664 */               return false;
/*  834:     */             }
/*  835:     */           }
/*  836: 667 */           for (int j = 0; j < 6; j++) {
/*  837: 668 */             if ((j >> 1 != i) && 
/*  838: 669 */               (this.quanta[j] + q1 > 4)) {
/*  839: 669 */               return false;
/*  840:     */             }
/*  841:     */           }
/*  842:     */         }
/*  843:     */       }
/*  844: 672 */       return true;
/*  845:     */     }
/*  846:     */     
/*  847:     */     public boolean checkPlacement(int cons, boolean jacket)
/*  848:     */     {
/*  849: 677 */       calcQuanta();
/*  850: 679 */       if (!checkImpingement()) {
/*  851: 679 */         return false;
/*  852:     */       }
/*  853: 684 */       if (!checkThickFace(9)) {
/*  854: 684 */         return false;
/*  855:     */       }
/*  856: 685 */       if (!checkThickSide(6)) {
/*  857: 685 */         return false;
/*  858:     */       }
/*  859: 686 */       if (!checkThickCorner(6)) {
/*  860: 686 */         return false;
/*  861:     */       }
/*  862: 688 */       if (!checkThickFace(8)) {
/*  863: 688 */         return false;
/*  864:     */       }
/*  865: 689 */       if (!checkThickSide(5)) {
/*  866: 689 */         return false;
/*  867:     */       }
/*  868: 690 */       if (!checkThickCorner(5)) {
/*  869: 690 */         return false;
/*  870:     */       }
/*  871: 692 */       if (!checkThickFace(7)) {
/*  872: 692 */         return false;
/*  873:     */       }
/*  874: 693 */       if (!checkThickSide(4)) {
/*  875: 693 */         return false;
/*  876:     */       }
/*  877: 694 */       if (!checkThickCorner(4)) {
/*  878: 694 */         return false;
/*  879:     */       }
/*  880: 697 */       if ((this.cornermask > 0) && (cons > 0)) {
/*  881: 697 */         return false;
/*  882:     */       }
/*  883: 700 */       if (!checkThickFace(2)) {
/*  884: 700 */         return false;
/*  885:     */       }
/*  886: 701 */       if (!checkThickSide(2)) {
/*  887: 701 */         return false;
/*  888:     */       }
/*  889: 702 */       if (!checkThickCorner(2)) {
/*  890: 702 */         return false;
/*  891:     */       }
/*  892: 705 */       this.cornermask = this.fillcornermask;
/*  893: 706 */       if (!checkFace(6)) {
/*  894: 706 */         return false;
/*  895:     */       }
/*  896: 707 */       if (!checkSide(3)) {
/*  897: 707 */         return false;
/*  898:     */       }
/*  899: 708 */       if (!checkCorner(3)) {
/*  900: 708 */         return false;
/*  901:     */       }
/*  902: 711 */       if ((this.covm & 0x1C000000) > 0)
/*  903:     */       {
/*  904: 712 */         if (jacket) {
/*  905: 712 */           return false;
/*  906:     */         }
/*  907: 713 */         if (cons > 0) {
/*  908: 713 */           return false;
/*  909:     */         }
/*  910:     */       }
/*  911: 718 */       for (int i = 0; i < 6; i++) {
/*  912: 719 */         if (((cons & 1 << i) != 0) && 
/*  913: 720 */           ((this.cornermask & CoverLib.coverToCornerMask(i)) > 0)) {
/*  914: 721 */           return false;
/*  915:     */         }
/*  916:     */       }
/*  917: 725 */       if (!checkFace(1)) {
/*  918: 725 */         return false;
/*  919:     */       }
/*  920: 726 */       if (!checkSide(1)) {
/*  921: 726 */         return false;
/*  922:     */       }
/*  923: 727 */       if (!checkCorner(1)) {
/*  924: 727 */         return false;
/*  925:     */       }
/*  926: 731 */       if ((jacket) && ((this.cornermask > 0) || (this.sidemask > 0))) {
/*  927: 732 */         return false;
/*  928:     */       }
/*  929: 734 */       if (!checkHollow(13)) {
/*  930: 734 */         return false;
/*  931:     */       }
/*  932: 735 */       if (!checkHollow(12)) {
/*  933: 735 */         return false;
/*  934:     */       }
/*  935: 736 */       if (!checkHollow(11)) {
/*  936: 736 */         return false;
/*  937:     */       }
/*  938: 737 */       if (!checkHollow(10)) {
/*  939: 737 */         return false;
/*  940:     */       }
/*  941: 738 */       if (!checkHollow(5)) {
/*  942: 738 */         return false;
/*  943:     */       }
/*  944: 741 */       for (int i = 0; i < 6; i++) {
/*  945: 742 */         if (((cons & 1 << i) != 0) && 
/*  946: 743 */           ((this.hollowcornermask & CoverLib.coverToCornerMask(i)) > 0)) {
/*  947: 744 */           return false;
/*  948:     */         }
/*  949:     */       }
/*  950: 746 */       if (!checkHollow(4)) {
/*  951: 746 */         return false;
/*  952:     */       }
/*  953: 747 */       if (!checkHollowCover(3)) {
/*  954: 747 */         return false;
/*  955:     */       }
/*  956: 750 */       if (!checkFace(0)) {
/*  957: 750 */         return false;
/*  958:     */       }
/*  959: 751 */       if (!checkSide(0)) {
/*  960: 751 */         return false;
/*  961:     */       }
/*  962: 752 */       if (!checkCorner(0)) {
/*  963: 752 */         return false;
/*  964:     */       }
/*  965: 755 */       for (int i = 0; i < 12; i++) {
/*  966: 756 */         if ((this.covm & 1 << i + 14) != 0)
/*  967:     */         {
/*  968: 757 */           int t = CoverLib.stripToCoverMask(i);
/*  969: 758 */           if ((cons & t) == t) {
/*  970: 758 */             return false;
/*  971:     */           }
/*  972:     */         }
/*  973:     */       }
/*  974: 760 */       return true;
/*  975:     */     }
/*  976:     */   }
/*  977:     */   
/*  978:     */   public static boolean checkPlacement(int covm, short[] covs, int cons, boolean jacket)
/*  979:     */   {
/*  980: 769 */     int scm = 0;int sm = 0;
/*  981:     */     
/*  982:     */ 
/*  983: 772 */     PlacementValidator pv = new PlacementValidator(covm, covs);
/*  984: 773 */     if (!pv.checkPlacement(cons, jacket)) {
/*  985: 774 */       return false;
/*  986:     */     }
/*  987: 938 */     return true;
/*  988:     */   }
/*  989:     */   
/*  990:     */   private static boolean canAddCover(yc world, aoh mop, int item)
/*  991:     */   {
/*  992: 943 */     if (world.a(blockCoverPlate.cm, mop.b, mop.c, mop.d, false, mop.e, null)) {
/*  993: 946 */       return true;
/*  994:     */     }
/*  995: 948 */     ICoverable icv = (ICoverable)CoreLib.getTileEntity(world, mop.b, mop.c, mop.d, ICoverable.class);
/*  996: 950 */     if (icv == null) {
/*  997: 950 */       return false;
/*  998:     */     }
/*  999: 951 */     if (icv.canAddCover(mop.subHit, item)) {
/* 1000: 952 */       return true;
/* 1001:     */     }
/* 1002: 953 */     return false;
/* 1003:     */   }
/* 1004:     */   
/* 1005:     */   private static int extractCoverSide(aoh src)
/* 1006:     */   {
/* 1007: 958 */     int tr = 0;
/* 1008: 959 */     double rpx = src.f.c - src.b - 0.5D;
/* 1009: 960 */     double rpy = src.f.d - src.c - 0.5D;
/* 1010: 961 */     double rpz = src.f.e - src.d - 0.5D;
/* 1011: 962 */     float sbw = 0.25F;
/* 1012: 964 */     switch (src.e)
/* 1013:     */     {
/* 1014:     */     case 0: 
/* 1015:     */     case 1: 
/* 1016: 966 */       if ((rpz > -sbw) && (rpz < sbw) && (rpx > -sbw) && (rpx < sbw)) {
/* 1017: 967 */         return src.e;
/* 1018:     */       }
/* 1019: 968 */       if (rpz > rpx)
/* 1020:     */       {
/* 1021: 969 */         if (rpz > -rpx) {
/* 1022: 969 */           return 3;
/* 1023:     */         }
/* 1024: 970 */         return 4;
/* 1025:     */       }
/* 1026: 972 */       if (rpz > -rpx) {
/* 1027: 972 */         return 5;
/* 1028:     */       }
/* 1029: 973 */       return 2;
/* 1030:     */     case 2: 
/* 1031:     */     case 3: 
/* 1032: 976 */       if ((rpy > -sbw) && (rpy < sbw) && (rpx > -sbw) && (rpx < sbw)) {
/* 1033: 977 */         return src.e;
/* 1034:     */       }
/* 1035: 978 */       if (rpy > rpx)
/* 1036:     */       {
/* 1037: 979 */         if (rpy > -rpx) {
/* 1038: 979 */           return 1;
/* 1039:     */         }
/* 1040: 980 */         return 4;
/* 1041:     */       }
/* 1042: 982 */       if (rpy > -rpx) {
/* 1043: 982 */         return 5;
/* 1044:     */       }
/* 1045: 983 */       return 0;
/* 1046:     */     case 4: 
/* 1047:     */     case 5: 
/* 1048: 986 */       if ((rpy > -sbw) && (rpy < sbw) && (rpz > -sbw) && (rpz < sbw)) {
/* 1049: 987 */         return src.e;
/* 1050:     */       }
/* 1051: 988 */       if (rpy > rpz)
/* 1052:     */       {
/* 1053: 989 */         if (rpy > -rpz) {
/* 1054: 989 */           return 1;
/* 1055:     */         }
/* 1056: 990 */         return 2;
/* 1057:     */       }
/* 1058: 992 */       if (rpy > -rpz) {
/* 1059: 992 */         return 3;
/* 1060:     */       }
/* 1061: 993 */       return 0;
/* 1062:     */     }
/* 1063: 995 */     return tr;
/* 1064:     */   }
/* 1065:     */   
/* 1066:     */   private static int extractCoverAxis(aoh src)
/* 1067:     */   {
/* 1068: 999 */     switch (src.e)
/* 1069:     */     {
/* 1070:     */     case 0: 
/* 1071:     */     case 1: 
/* 1072:1001 */       return src.f.d - src.c > 0.5D ? 1 : 0;
/* 1073:     */     case 2: 
/* 1074:     */     case 3: 
/* 1075:1003 */       return src.f.e - src.d > 0.5D ? 1 : 0;
/* 1076:     */     }
/* 1077:1005 */     return src.f.c - src.b > 0.5D ? 1 : 0;
/* 1078:     */   }
/* 1079:     */   
/* 1080:     */   private static void stepDir(aoh mop)
/* 1081:     */   {
/* 1082:1010 */     switch (mop.e)
/* 1083:     */     {
/* 1084:     */     case 0: 
/* 1085:1011 */       mop.c -= 1; break;
/* 1086:     */     case 1: 
/* 1087:1012 */       mop.c += 1; break;
/* 1088:     */     case 2: 
/* 1089:1013 */       mop.d -= 1; break;
/* 1090:     */     case 3: 
/* 1091:1014 */       mop.d += 1; break;
/* 1092:     */     case 4: 
/* 1093:1015 */       mop.b -= 1; break;
/* 1094:     */     default: 
/* 1095:1016 */       mop.b += 1;
/* 1096:     */     }
/* 1097:     */   }
/* 1098:     */   
/* 1099:     */   private static boolean isClickOutside(aoh mop)
/* 1100:     */   {
/* 1101:1021 */     if (mop.subHit < 0) {
/* 1102:1021 */       return true;
/* 1103:     */     }
/* 1104:1022 */     if (mop.subHit < 6) {
/* 1105:1023 */       return mop.e != (mop.subHit ^ 0x1);
/* 1106:     */     }
/* 1107:1024 */     if (mop.subHit < 14)
/* 1108:     */     {
/* 1109:1025 */       int fc = mop.subHit - 6;
/* 1110:1026 */       fc = fc >> 2 | (fc & 0x3) << 1;
/* 1111:1027 */       if (((mop.e ^ fc >> (mop.e >> 1)) & 0x1) == 0) {
/* 1112:1028 */         return true;
/* 1113:     */       }
/* 1114:1029 */       return false;
/* 1115:     */     }
/* 1116:1031 */     if (mop.subHit < 26)
/* 1117:     */     {
/* 1118:1032 */       int fc = mop.subHit - 14;
/* 1119:1033 */       fc = stripToCoverMask(fc);
/* 1120:1034 */       if ((fc & 1 << (mop.e ^ 0x1)) > 0) {
/* 1121:1035 */         return false;
/* 1122:     */       }
/* 1123:1036 */       return true;
/* 1124:     */     }
/* 1125:1038 */     if (mop.subHit < 29) {
/* 1126:1038 */       return true;
/* 1127:     */     }
/* 1128:1039 */     if (mop.subHit == 29) {
/* 1129:1039 */       return true;
/* 1130:     */     }
/* 1131:1040 */     return false;
/* 1132:     */   }
/* 1133:     */   
/* 1134:     */   public static aoh getPlacement(yc world, aoh src, int item)
/* 1135:     */   {
/* 1136:1046 */     aoh tr = new aoh(src.b, src.c, src.d, src.e, src.f);
/* 1137:     */     
/* 1138:     */ 
/* 1139:1049 */     int cval = damageToCoverValue(item);
/* 1140:     */     int dir;
/* 1141:1050 */     switch (item >> 8)
/* 1142:     */     {
/* 1143:     */     case 0: 
/* 1144:     */     case 16: 
/* 1145:     */     case 17: 
/* 1146:     */     case 24: 
/* 1147:     */     case 25: 
/* 1148:     */     case 26: 
/* 1149:     */     case 27: 
/* 1150:     */     case 28: 
/* 1151:     */     case 29: 
/* 1152:     */     case 30: 
/* 1153:     */     case 31: 
/* 1154:     */     case 32: 
/* 1155:     */     case 33: 
/* 1156:     */     case 34: 
/* 1157:1056 */       dir = extractCoverSide(src);
/* 1158:1057 */       if (dir != tr.e)
/* 1159:     */       {
/* 1160:1058 */         tr.subHit = dir;
/* 1161:1059 */         if ((!isClickOutside(src)) && 
/* 1162:1060 */           (canAddCover(world, tr, cval))) {
/* 1163:1061 */           return tr;
/* 1164:     */         }
/* 1165:1063 */         stepDir(tr);
/* 1166:1064 */         if (canAddCover(world, tr, cval)) {
/* 1167:1065 */           return tr;
/* 1168:     */         }
/* 1169:1066 */         return null;
/* 1170:     */       }
/* 1171:1068 */       if (!isClickOutside(src))
/* 1172:     */       {
/* 1173:1069 */         tr.subHit = (dir ^ 0x1);
/* 1174:1070 */         if (canAddCover(world, tr, cval)) {
/* 1175:1071 */           return tr;
/* 1176:     */         }
/* 1177:     */       }
/* 1178:1073 */       tr.subHit = dir;
/* 1179:1074 */       if (canAddCover(world, tr, cval)) {
/* 1180:1075 */         return tr;
/* 1181:     */       }
/* 1182:1076 */       if (!isClickOutside(src)) {
/* 1183:1077 */         return null;
/* 1184:     */       }
/* 1185:1079 */       stepDir(tr);
/* 1186:1080 */       tr.subHit = (dir ^ 0x1);
/* 1187:1081 */       if (canAddCover(world, tr, cval)) {
/* 1188:1082 */         return tr;
/* 1189:     */       }
/* 1190:1083 */       return null;
/* 1191:     */     case 21: 
/* 1192:     */     case 22: 
/* 1193:     */     case 23: 
/* 1194:     */     case 39: 
/* 1195:     */     case 40: 
/* 1196:     */     case 41: 
/* 1197:     */     case 42: 
/* 1198:1088 */       dir = extractCoverSide(src);
/* 1199:1089 */       if (dir == tr.e) {
/* 1200:1090 */         return null;
/* 1201:     */       }
/* 1202:1091 */       int csm = coverToStripMask(dir);
/* 1203:1092 */       if (!isClickOutside(src))
/* 1204:     */       {
/* 1205:1093 */         int csm2 = csm & coverToStripMask(tr.e ^ 0x1);
/* 1206:1094 */         tr.subHit = (14 + Integer.numberOfTrailingZeros(csm2));
/* 1207:1096 */         if (canAddCover(world, tr, cval)) {
/* 1208:1097 */           return tr;
/* 1209:     */         }
/* 1210:1098 */         csm2 = csm & coverToStripMask(tr.e);
/* 1211:1099 */         tr.subHit = (14 + Integer.numberOfTrailingZeros(csm2));
/* 1212:1101 */         if (canAddCover(world, tr, cval)) {
/* 1213:1102 */           return tr;
/* 1214:     */         }
/* 1215:1103 */         return null;
/* 1216:     */       }
/* 1217:1105 */       stepDir(tr);
/* 1218:1106 */       int csm2 = csm & coverToStripMask(tr.e ^ 0x1);
/* 1219:1107 */       tr.subHit = (14 + Integer.numberOfTrailingZeros(csm2));
/* 1220:1109 */       if (canAddCover(world, tr, cval)) {
/* 1221:1110 */         return tr;
/* 1222:     */       }
/* 1223:1111 */       return null;
/* 1224:     */     case 18: 
/* 1225:     */     case 19: 
/* 1226:     */     case 20: 
/* 1227:     */     case 35: 
/* 1228:     */     case 36: 
/* 1229:     */     case 37: 
/* 1230:     */     case 38: 
/* 1231:1116 */       double rpx = src.f.c - src.b;
/* 1232:1117 */       double rpy = src.f.d - src.c;
/* 1233:1118 */       double rpz = src.f.e - src.d;
/* 1234:1119 */       dir = 0;
/* 1235:1120 */       if (rpz > 0.5D) {
/* 1236:1120 */         dir++;
/* 1237:     */       }
/* 1238:1121 */       if (rpx > 0.5D) {
/* 1239:1121 */         dir += 2;
/* 1240:     */       }
/* 1241:1122 */       if (rpy > 0.5D) {
/* 1242:1122 */         dir += 4;
/* 1243:     */       }
/* 1244:1124 */       switch (src.e)
/* 1245:     */       {
/* 1246:     */       case 0: 
/* 1247:1125 */         dir &= 0x3; break;
/* 1248:     */       case 1: 
/* 1249:1126 */         dir |= 0x4; break;
/* 1250:     */       case 2: 
/* 1251:1127 */         dir &= 0x6; break;
/* 1252:     */       case 3: 
/* 1253:1128 */         dir |= 0x1; break;
/* 1254:     */       case 4: 
/* 1255:1129 */         dir &= 0x5; break;
/* 1256:     */       default: 
/* 1257:1130 */         dir |= 0x2;
/* 1258:     */       }
/* 1259:1132 */       int dir2 = dir;
/* 1260:1133 */       switch (src.e)
/* 1261:     */       {
/* 1262:     */       case 0: 
/* 1263:     */       case 1: 
/* 1264:1134 */         dir2 ^= 0x4; break;
/* 1265:     */       case 2: 
/* 1266:     */       case 3: 
/* 1267:1135 */         dir2 ^= 0x1; break;
/* 1268:     */       default: 
/* 1269:1136 */         dir2 ^= 0x2;
/* 1270:     */       }
/* 1271:1139 */       if (isClickOutside(src))
/* 1272:     */       {
/* 1273:1140 */         tr.subHit = (dir2 + 6);
/* 1274:1141 */         stepDir(tr);
/* 1275:1143 */         if (canAddCover(world, tr, cval)) {
/* 1276:1144 */           return tr;
/* 1277:     */         }
/* 1278:1145 */         return null;
/* 1279:     */       }
/* 1280:1147 */       tr.subHit = (dir2 + 6);
/* 1281:1148 */       if (canAddCover(world, tr, cval)) {
/* 1282:1149 */         return tr;
/* 1283:     */       }
/* 1284:1150 */       tr.subHit = (dir + 6);
/* 1285:1151 */       if (canAddCover(world, tr, cval)) {
/* 1286:1152 */         return tr;
/* 1287:     */       }
/* 1288:1153 */       return null;
/* 1289:     */     case 43: 
/* 1290:     */     case 44: 
/* 1291:     */     case 45: 
/* 1292:1157 */       dir = extractCoverSide(src);
/* 1293:1158 */       if ((dir != tr.e) && (dir != (tr.e ^ 0x1))) {
/* 1294:1159 */         return null;
/* 1295:     */       }
/* 1296:1160 */       if (isClickOutside(src)) {
/* 1297:1161 */         stepDir(tr);
/* 1298:     */       }
/* 1299:1162 */       tr.subHit = ((dir >> 1) + 26);
/* 1300:1163 */       if (canAddCover(world, tr, cval)) {
/* 1301:1164 */         return tr;
/* 1302:     */       }
/* 1303:1165 */       return null;
/* 1304:     */     }
/* 1305:1167 */     return null;
/* 1306:     */   }
/* 1307:     */   
/* 1308:     */   public static void replaceWithCovers(yc world, int i, int j, int k, int sides, short[] covers)
/* 1309:     */   {
/* 1310:1172 */     BlockMultipart.removeMultipart(world, i, j, k);
/* 1311:1173 */     if (blockCoverPlate == null) {
/* 1312:1173 */       return;
/* 1313:     */     }
/* 1314:1174 */     if (sides == 0) {
/* 1315:1174 */       return;
/* 1316:     */     }
/* 1317:1176 */     world.c(i, j, k, blockCoverPlate.cm, 0);
/* 1318:1177 */     TileCovered tc = (TileCovered)CoreLib.getTileEntity(world, i, j, k, TileCovered.class);
/* 1319:1179 */     if (tc == null) {
/* 1320:1179 */       return;
/* 1321:     */     }
/* 1322:1181 */     tc.CoverSides = sides;
/* 1323:1182 */     tc.Covers = covers;
/* 1324:     */     
/* 1325:1184 */     RedPowerLib.updateIndirectNeighbors(world, i, j, k, blockCoverPlate.cm);
/* 1326:     */   }
/* 1327:     */   
/* 1328:     */   public static boolean tryMakeCompatible(yc world, WorldCoord wc, int bid, int dmg)
/* 1329:     */   {
/* 1330:1190 */     TileCovered tc = (TileCovered)CoreLib.getTileEntity(world, wc, TileCovered.class);
/* 1331:1192 */     if (tc == null) {
/* 1332:1192 */       return false;
/* 1333:     */     }
/* 1334:1194 */     int hb = dmg >> 8;int lb = dmg & 0xFF;
/* 1335:1195 */     int xid = tc.getExtendedID();
/* 1336:1196 */     if (xid == hb)
/* 1337:     */     {
/* 1338:1197 */       if (tc.getExtendedMetadata() != lb) {
/* 1339:1197 */         return false;
/* 1340:     */       }
/* 1341:1198 */       return true;
/* 1342:     */     }
/* 1343:1200 */     if (xid != 0) {
/* 1344:1200 */       return false;
/* 1345:     */     }
/* 1346:1202 */     short[] covs = tc.Covers;
/* 1347:1203 */     int cs = tc.CoverSides;
/* 1348:1204 */     BlockMultipart.removeMultipart(world, wc.x, wc.y, wc.z);
/* 1349:1205 */     if (!world.c(wc.x, wc.y, wc.z, bid, hb)) {
/* 1350:1206 */       return false;
/* 1351:     */     }
/* 1352:1207 */     tc = (TileCovered)CoreLib.getTileEntity(world, wc, TileCovered.class);
/* 1353:1208 */     if (tc == null) {
/* 1354:1208 */       return true;
/* 1355:     */     }
/* 1356:1209 */     tc.Covers = covs;tc.CoverSides = cs;
/* 1357:1210 */     tc.setExtendedMetadata(lb);
/* 1358:1211 */     return true;
/* 1359:     */   }
/* 1360:     */   
/* 1361:     */   public static ur getItemStack(int n)
/* 1362:     */   {
/* 1363:1215 */     return materials[n];
/* 1364:     */   }
/* 1365:     */   
/* 1366:     */   public static amq getBlock(int n)
/* 1367:     */   {
/* 1368:1219 */     ur ist = materials[n];
/* 1369:1220 */     return amq.p[ist.c];
/* 1370:     */   }
/* 1371:     */   
/* 1372:     */   public static String getName(int n)
/* 1373:     */   {
/* 1374:1224 */     return names[n];
/* 1375:     */   }
/* 1376:     */   
/* 1377:     */   public static String getDesc(int n)
/* 1378:     */   {
/* 1379:1228 */     return descs[n];
/* 1380:     */   }
/* 1381:     */   
/* 1382:     */   public static int getHardness(int n)
/* 1383:     */   {
/* 1384:1232 */     return hardness[n];
/* 1385:     */   }
/* 1386:     */   
/* 1387:     */   public static float getMiningHardness(int n)
/* 1388:     */   {
/* 1389:1236 */     return miningHardness[n];
/* 1390:     */   }
/* 1391:     */   
/* 1392:     */   public static boolean isTransparent(int n)
/* 1393:     */   {
/* 1394:1240 */     return transparency[n];
/* 1395:     */   }
/* 1396:     */   
/* 1397:1243 */   public static amq blockCoverPlate = null;
/* 1398:1244 */   private static ur[] materials = new ur[256];
/* 1399:1245 */   private static String[] names = new String[256];
/* 1400:1246 */   private static String[] descs = new String[256];
/* 1401:1247 */   private static int[] hardness = new int[256];
/* 1402:1249 */   private static boolean[] transparency = new boolean[256];
/* 1403:1250 */   public static int[][] coverTextures = new int[256][];
/* 1404:1251 */   public static String[] coverTextureFiles = new String[256];
/* 1405:1252 */   private static float[] miningHardness = new float[256];
/* 1406:1256 */   private static ArrayList materialHandlers = new ArrayList();
/* 1407:1257 */   private static HashMap coverIndex = new HashMap();
/* 1408:     */   public static final float selectBoxWidth = 0.25F;
/* 1409:     */   
/* 1410:     */   public static abstract interface IMaterialHandler
/* 1411:     */   {
/* 1412:     */     public abstract void addMaterial(int paramInt);
/* 1413:     */   }
/* 1414:     */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CoverLib
 * JD-Core Version:    0.7.0.1
 */