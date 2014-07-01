/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import any;
/*   5:    */ import aoc;
/*   6:    */ import aod;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import java.util.Arrays;
/*   9:    */ import java.util.HashSet;
/*  10:    */ import java.util.LinkedList;
/*  11:    */ import java.util.List;
/*  12:    */ import net.minecraftforge.common.ForgeDirection;
/*  13:    */ import yc;
/*  14:    */ import ym;
/*  15:    */ 
/*  16:    */ public class RedPowerLib
/*  17:    */ {
/*  18:    */   public static void notifyBlock(yc world, int i, int j, int k, int l)
/*  19:    */   {
/*  20: 38 */     amq block = amq.p[world.a(i, j, k)];
/*  21: 39 */     if (block != null) {
/*  22: 40 */       block.a(world, i, j, k, l);
/*  23:    */     }
/*  24:    */   }
/*  25:    */   
/*  26:    */   public static void updateIndirectNeighbors(yc w, int i, int j, int k, int bid)
/*  27:    */   {
/*  28: 47 */     if ((w.r) || (CoreLib.isClient(w))) {
/*  29: 48 */       return;
/*  30:    */     }
/*  31: 49 */     for (int a = -3; a <= 3; a++) {
/*  32: 49 */       for (int b = -3; b <= 3; b++) {
/*  33: 49 */         for (int c = -3; c <= 3; c++)
/*  34:    */         {
/*  35: 50 */           int md = a < 0 ? -a : a;md += (b < 0 ? -b : b);
/*  36: 51 */           md += (c < 0 ? -c : c);
/*  37: 52 */           if (md <= 3) {
/*  38: 53 */             notifyBlock(w, i + a, j + b, k + c, bid);
/*  39:    */           }
/*  40:    */         }
/*  41:    */       }
/*  42:    */     }
/*  43:    */   }
/*  44:    */   
/*  45:    */   public static boolean isBlockRedstone(ym iba, int i, int j, int k, int l)
/*  46:    */   {
/*  47: 59 */     switch (l)
/*  48:    */     {
/*  49:    */     case 0: 
/*  50: 60 */       j--; break;
/*  51:    */     case 1: 
/*  52: 61 */       j++; break;
/*  53:    */     case 2: 
/*  54: 62 */       k--; break;
/*  55:    */     case 3: 
/*  56: 63 */       k++; break;
/*  57:    */     case 4: 
/*  58: 64 */       i--; break;
/*  59:    */     case 5: 
/*  60: 65 */       i++;
/*  61:    */     }
/*  62: 67 */     int bid = iba.a(i, j, k);
/*  63: 68 */     return bid == amq.ay.cm;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public static boolean isSideNormal(ym iba, int i, int j, int k, int l)
/*  67:    */   {
/*  68: 73 */     switch (l)
/*  69:    */     {
/*  70:    */     case 0: 
/*  71: 74 */       j--; break;
/*  72:    */     case 1: 
/*  73: 75 */       j++; break;
/*  74:    */     case 2: 
/*  75: 76 */       k--; break;
/*  76:    */     case 3: 
/*  77: 77 */       k++; break;
/*  78:    */     case 4: 
/*  79: 78 */       i--; break;
/*  80:    */     case 5: 
/*  81: 79 */       i++;
/*  82:    */     }
/*  83: 81 */     l ^= 0x1;
/*  84: 82 */     if (iba.t(i, j, k)) {
/*  85: 83 */       return true;
/*  86:    */     }
/*  87: 84 */     int bid = iba.a(i, j, k);
/*  88:    */     
/*  89:    */ 
/*  90:    */ 
/*  91: 88 */     IMultipart im = (IMultipart)CoreLib.getTileEntity(iba, i, j, k, IMultipart.class);
/*  92: 89 */     if (im == null) {
/*  93: 89 */       return false;
/*  94:    */     }
/*  95: 90 */     return im.isSideNormal(l);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public static boolean canSupportWire(ym iba, int i, int j, int k, int l)
/*  99:    */   {
/* 100: 95 */     switch (l)
/* 101:    */     {
/* 102:    */     case 0: 
/* 103: 96 */       j--; break;
/* 104:    */     case 1: 
/* 105: 97 */       j++; break;
/* 106:    */     case 2: 
/* 107: 98 */       k--; break;
/* 108:    */     case 3: 
/* 109: 99 */       k++; break;
/* 110:    */     case 4: 
/* 111:100 */       i--; break;
/* 112:    */     case 5: 
/* 113:101 */       i++;
/* 114:    */     }
/* 115:103 */     l ^= 0x1;
/* 116:104 */     if ((iba instanceof yc))
/* 117:    */     {
/* 118:105 */       yc world = (yc)iba;
/* 119:106 */       if (!world.f(i, j, k)) {
/* 120:107 */         return true;
/* 121:    */       }
/* 122:108 */       if (world.isBlockSolidOnSide(i, j, k, ForgeDirection.getOrientation(l))) {
/* 123:110 */         return true;
/* 124:    */       }
/* 125:    */     }
/* 126:112 */     if (iba.t(i, j, k)) {
/* 127:113 */       return true;
/* 128:    */     }
/* 129:114 */     int bid = iba.a(i, j, k);
/* 130:115 */     if (bid == amq.af.cm) {
/* 131:115 */       return true;
/* 132:    */     }
/* 133:116 */     if ((bid == amq.Y.cm) || (bid == amq.ac.cm))
/* 134:    */     {
/* 135:118 */       int md = iba.h(i, j, k) & 0x7;
/* 136:119 */       return (i != md) && (md != 7);
/* 137:    */     }
/* 138:121 */     IMultipart im = (IMultipart)CoreLib.getTileEntity(iba, i, j, k, IMultipart.class);
/* 139:122 */     if (im == null) {
/* 140:122 */       return false;
/* 141:    */     }
/* 142:126 */     return im.isSideNormal(l);
/* 143:    */   }
/* 144:    */   
/* 145:    */   public static boolean isStrongPoweringTo(ym iba, int i, int j, int k, int l)
/* 146:    */   {
/* 147:133 */     int bid = iba.a(i, j, k);
/* 148:134 */     if (bid == 0) {
/* 149:134 */       return false;
/* 150:    */     }
/* 151:135 */     if ((searching) && (bid == amq.ay.cm)) {
/* 152:135 */       return false;
/* 153:    */     }
/* 154:136 */     if (!(iba instanceof yc)) {
/* 155:136 */       return false;
/* 156:    */     }
/* 157:137 */     yc world = (yc)iba;
/* 158:138 */     return amq.p[bid].c(world, i, j, k, l);
/* 159:    */   }
/* 160:    */   
/* 161:    */   public static boolean isStrongPowered(ym iba, int i, int j, int k, int l)
/* 162:    */   {
/* 163:143 */     if ((l != 1) && (isStrongPoweringTo(iba, i, j - 1, k, 0))) {
/* 164:143 */       return true;
/* 165:    */     }
/* 166:144 */     if ((l != 0) && (isStrongPoweringTo(iba, i, j + 1, k, 1))) {
/* 167:144 */       return true;
/* 168:    */     }
/* 169:145 */     if ((l != 3) && (isStrongPoweringTo(iba, i, j, k - 1, 2))) {
/* 170:145 */       return true;
/* 171:    */     }
/* 172:146 */     if ((l != 2) && (isStrongPoweringTo(iba, i, j, k + 1, 3))) {
/* 173:146 */       return true;
/* 174:    */     }
/* 175:147 */     if ((l != 5) && (isStrongPoweringTo(iba, i - 1, j, k, 4))) {
/* 176:147 */       return true;
/* 177:    */     }
/* 178:148 */     if ((l != 4) && (isStrongPoweringTo(iba, i + 1, j, k, 5))) {
/* 179:148 */       return true;
/* 180:    */     }
/* 181:149 */     return false;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public static boolean isWeakPoweringTo(ym iba, int i, int j, int k, int l)
/* 185:    */   {
/* 186:154 */     int bid = iba.a(i, j, k);
/* 187:155 */     if (bid == 0) {
/* 188:155 */       return false;
/* 189:    */     }
/* 190:156 */     if ((searching) && (bid == amq.ay.cm)) {
/* 191:156 */       return false;
/* 192:    */     }
/* 193:157 */     if (amq.p[bid].b(iba, i, j, k, l)) {
/* 194:158 */       return true;
/* 195:    */     }
/* 196:159 */     if ((l > 1) && (bid == amq.ay.cm) && 
/* 197:160 */       (amq.p[bid].b(iba, i, j, k, 1))) {
/* 198:161 */       return true;
/* 199:    */     }
/* 200:163 */     return false;
/* 201:    */   }
/* 202:    */   
/* 203:    */   public static boolean isPoweringTo(ym iba, int i, int j, int k, int l)
/* 204:    */   {
/* 205:168 */     int bid = iba.a(i, j, k);
/* 206:169 */     if (bid == 0) {
/* 207:169 */       return false;
/* 208:    */     }
/* 209:170 */     if (amq.p[bid].b(iba, i, j, k, l)) {
/* 210:171 */       return true;
/* 211:    */     }
/* 212:172 */     if ((iba.t(i, j, k)) && 
/* 213:173 */       (isStrongPowered(iba, i, j, k, l))) {
/* 214:174 */       return true;
/* 215:    */     }
/* 216:176 */     if ((l > 1) && (bid == amq.ay.cm))
/* 217:    */     {
/* 218:177 */       if (searching) {
/* 219:177 */         return false;
/* 220:    */       }
/* 221:178 */       if (amq.p[bid].b(iba, i, j, k, 1)) {
/* 222:179 */         return true;
/* 223:    */       }
/* 224:    */     }
/* 225:181 */     return false;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public static boolean isPowered(ym iba, int i, int j, int k, int cons, int indside)
/* 229:    */   {
/* 230:186 */     if (((cons & 0x1111100) > 0) && (isWeakPoweringTo(iba, i, j - 1, k, 0))) {
/* 231:187 */       return true;
/* 232:    */     }
/* 233:188 */     if (((cons & 0x2222200) > 0) && (isWeakPoweringTo(iba, i, j + 1, k, 1))) {
/* 234:189 */       return true;
/* 235:    */     }
/* 236:190 */     if (((cons & 0x4440011) > 0) && (isWeakPoweringTo(iba, i, j, k - 1, 2))) {
/* 237:191 */       return true;
/* 238:    */     }
/* 239:192 */     if (((cons & 0x8880022) > 0) && (isWeakPoweringTo(iba, i, j, k + 1, 3))) {
/* 240:193 */       return true;
/* 241:    */     }
/* 242:194 */     if (((cons & 0x10004444) > 0) && (isWeakPoweringTo(iba, i - 1, j, k, 4))) {
/* 243:195 */       return true;
/* 244:    */     }
/* 245:196 */     if (((cons & 0x20008888) > 0) && (isWeakPoweringTo(iba, i + 1, j, k, 5))) {
/* 246:197 */       return true;
/* 247:    */     }
/* 248:198 */     if (((indside & 0x1) > 0) && (isPoweringTo(iba, i, j - 1, k, 0))) {
/* 249:199 */       return true;
/* 250:    */     }
/* 251:200 */     if (((indside & 0x2) > 0) && (isPoweringTo(iba, i, j + 1, k, 1))) {
/* 252:201 */       return true;
/* 253:    */     }
/* 254:202 */     if (((indside & 0x4) > 0) && (isPoweringTo(iba, i, j, k - 1, 2))) {
/* 255:203 */       return true;
/* 256:    */     }
/* 257:204 */     if (((indside & 0x8) > 0) && (isPoweringTo(iba, i, j, k + 1, 3))) {
/* 258:205 */       return true;
/* 259:    */     }
/* 260:206 */     if (((indside & 0x10) > 0) && (isPoweringTo(iba, i - 1, j, k, 4))) {
/* 261:207 */       return true;
/* 262:    */     }
/* 263:208 */     if (((indside & 0x20) > 0) && (isPoweringTo(iba, i + 1, j, k, 5))) {
/* 264:209 */       return true;
/* 265:    */     }
/* 266:210 */     return false;
/* 267:    */   }
/* 268:    */   
/* 269:    */   private static int getSidePowerMask(ym iba, int i, int j, int k, int ch, int side)
/* 270:    */   {
/* 271:216 */     IRedPowerConnectable irp = (IRedPowerConnectable)CoreLib.getTileEntity(iba, i, j, k, IRedPowerConnectable.class);
/* 272:    */     
/* 273:218 */     int mask = getConDirMask(side);
/* 274:219 */     if (irp != null)
/* 275:    */     {
/* 276:220 */       int m = irp.getPoweringMask(ch);
/* 277:221 */       m = (m & 0x55555555) << 1 | (m & 0x2AAAAAAA) >> 1;
/* 278:222 */       return m & mask;
/* 279:    */     }
/* 280:224 */     if (ch != 0) {
/* 281:224 */       return 0;
/* 282:    */     }
/* 283:225 */     if (isWeakPoweringTo(iba, i, j, k, side)) {
/* 284:226 */       return mask & 0xFFFFFF;
/* 285:    */     }
/* 286:227 */     if (isPoweringTo(iba, i, j, k, side)) {
/* 287:228 */       return mask;
/* 288:    */     }
/* 289:229 */     return 0;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public static int getPowerState(ym iba, int i, int j, int k, int cons, int ch)
/* 293:    */   {
/* 294:234 */     int trs = 0;
/* 295:236 */     if ((cons & 0x1111100) > 0) {
/* 296:237 */       trs |= getSidePowerMask(iba, i, j - 1, k, ch, 0);
/* 297:    */     }
/* 298:238 */     if ((cons & 0x2222200) > 0) {
/* 299:239 */       trs |= getSidePowerMask(iba, i, j + 1, k, ch, 1);
/* 300:    */     }
/* 301:240 */     if ((cons & 0x4440011) > 0) {
/* 302:241 */       trs |= getSidePowerMask(iba, i, j, k - 1, ch, 2);
/* 303:    */     }
/* 304:242 */     if ((cons & 0x8880022) > 0) {
/* 305:243 */       trs |= getSidePowerMask(iba, i, j, k + 1, ch, 3);
/* 306:    */     }
/* 307:244 */     if ((cons & 0x10004444) > 0) {
/* 308:245 */       trs |= getSidePowerMask(iba, i - 1, j, k, ch, 4);
/* 309:    */     }
/* 310:246 */     if ((cons & 0x20008888) > 0) {
/* 311:247 */       trs |= getSidePowerMask(iba, i + 1, j, k, ch, 5);
/* 312:    */     }
/* 313:248 */     return trs & cons;
/* 314:    */   }
/* 315:    */   
/* 316:    */   public static int getRotPowerState(ym iba, int i, int j, int k, int rcon, int rot, int ch)
/* 317:    */   {
/* 318:253 */     int c1 = mapRotToCon(rcon, rot);
/* 319:254 */     int ps = getPowerState(iba, i, j, k, c1, ch);
/* 320:255 */     return mapConToRot(ps, rot);
/* 321:    */   }
/* 322:    */   
/* 323:    */   public static int getConDirMask(int dir)
/* 324:    */   {
/* 325:262 */     switch (dir)
/* 326:    */     {
/* 327:    */     case 0: 
/* 328:263 */       return 17895680;
/* 329:    */     case 1: 
/* 330:264 */       return 35791360;
/* 331:    */     case 2: 
/* 332:265 */       return 71565329;
/* 333:    */     case 3: 
/* 334:266 */       return 143130658;
/* 335:    */     case 4: 
/* 336:267 */       return 268452932;
/* 337:    */     }
/* 338:268 */     return 536905864;
/* 339:    */   }
/* 340:    */   
/* 341:    */   public static int mapConToLocal(int cons, int face)
/* 342:    */   {
/* 343:273 */     cons >>= face * 4;
/* 344:274 */     cons &= 0xF;
/* 345:275 */     switch (face)
/* 346:    */     {
/* 347:    */     case 0: 
/* 348:276 */       return cons;
/* 349:    */     case 1: 
/* 350:278 */       cons ^= ((cons ^ cons >> 1) & 0x1) * 3;
/* 351:279 */       return cons;
/* 352:    */     case 3: 
/* 353:    */     case 4: 
/* 354:282 */       cons ^= ((cons ^ cons >> 2) & 0x3) * 5;
/* 355:283 */       cons ^= ((cons ^ cons >> 1) & 0x1) * 3;
/* 356:284 */       return cons;
/* 357:    */     }
/* 358:286 */     cons ^= ((cons ^ cons >> 2) & 0x3) * 5;
/* 359:287 */     return cons;
/* 360:    */   }
/* 361:    */   
/* 362:    */   public static int mapLocalToCon(int loc, int face)
/* 363:    */   {
/* 364:292 */     switch (face)
/* 365:    */     {
/* 366:    */     case 0: 
/* 367:    */       break;
/* 368:    */     case 1: 
/* 369:295 */       loc ^= ((loc ^ loc >> 1) & 0x1) * 3;
/* 370:296 */       break;
/* 371:    */     case 3: 
/* 372:    */     case 4: 
/* 373:299 */       loc ^= ((loc ^ loc >> 1) & 0x1) * 3;
/* 374:300 */       loc ^= ((loc ^ loc >> 2) & 0x3) * 5;
/* 375:301 */       break;
/* 376:    */     case 2: 
/* 377:    */     default: 
/* 378:303 */       loc ^= ((loc ^ loc >> 2) & 0x3) * 5;
/* 379:    */     }
/* 380:306 */     return loc << face * 4;
/* 381:    */   }
/* 382:    */   
/* 383:    */   public static int mapRotToLocal(int rm, int rot)
/* 384:    */   {
/* 385:310 */     rm = rm << rot | rm >> 4 - rot;
/* 386:311 */     rm &= 0xF;
/* 387:312 */     return rm & 0x8 | (rm & 0x3) << 1 | rm >> 2 & 0x1;
/* 388:    */   }
/* 389:    */   
/* 390:    */   public static int mapLocalToRot(int rm, int rot)
/* 391:    */   {
/* 392:316 */     rm = rm & 0x8 | (rm & 0x6) >> 1 | rm << 2 & 0x4;
/* 393:317 */     rm = rm << 4 - rot | rm >> rot;
/* 394:318 */     return rm & 0xF;
/* 395:    */   }
/* 396:    */   
/* 397:    */   public static int mapConToRot(int con, int rot)
/* 398:    */   {
/* 399:322 */     return mapLocalToRot(mapConToLocal(con, rot >> 2), rot & 0x3);
/* 400:    */   }
/* 401:    */   
/* 402:    */   public static int mapRotToCon(int con, int rot)
/* 403:    */   {
/* 404:326 */     return mapLocalToCon(mapRotToLocal(con, rot & 0x3), rot >> 2);
/* 405:    */   }
/* 406:    */   
/* 407:    */   public static int getDirToRedstone(int rsd)
/* 408:    */   {
/* 409:330 */     switch (rsd)
/* 410:    */     {
/* 411:    */     case 2: 
/* 412:331 */       return 0;
/* 413:    */     case 3: 
/* 414:332 */       return 2;
/* 415:    */     case 4: 
/* 416:333 */       return 3;
/* 417:    */     case 5: 
/* 418:334 */       return 1;
/* 419:    */     }
/* 420:335 */     return 0;
/* 421:    */   }
/* 422:    */   
/* 423:    */   public static int getConSides(ym iba, int i, int j, int k, int side, int pcl)
/* 424:    */   {
/* 425:345 */     int l = iba.a(i, j, k);
/* 426:346 */     if (l == 0) {
/* 427:346 */       return 0;
/* 428:    */     }
/* 429:348 */     IConnectable rpa = (IConnectable)CoreLib.getTileEntity(iba, i, j, k, IConnectable.class);
/* 430:350 */     if (rpa != null)
/* 431:    */     {
/* 432:351 */       int pc = rpa.getConnectClass(side);
/* 433:352 */       if (isCompatible(pc, pcl)) {
/* 434:353 */         return rpa.getConnectableMask();
/* 435:    */       }
/* 436:354 */       return 0;
/* 437:    */     }
/* 438:356 */     if (!isCompatible(0, pcl)) {
/* 439:356 */       return 0;
/* 440:    */     }
/* 441:366 */     if ((l == amq.ac.cm) || (l == amq.Y.cm))
/* 442:    */     {
/* 443:368 */       int md = iba.h(i, j, k) & 0x7;
/* 444:369 */       if (md == 7) {
/* 445:369 */         return 0;
/* 446:    */       }
/* 447:370 */       return 0x3FFFFFFF ^ getConDirMask(md);
/* 448:    */     }
/* 449:372 */     if (l == amq.af.cm)
/* 450:    */     {
/* 451:373 */       any te = iba.q(i, j, k);
/* 452:374 */       if (!(te instanceof aod)) {
/* 453:374 */         return 0;
/* 454:    */       }
/* 455:375 */       aod tep = (aod)te;
/* 456:    */       
/* 457:377 */       int sid = tep.a();
/* 458:378 */       if ((sid == amq.ac.cm) || (sid == amq.Y.cm))
/* 459:    */       {
/* 460:380 */         int md = tep.p() & 0x7;
/* 461:381 */         if (md == 7) {
/* 462:381 */           return 0;
/* 463:    */         }
/* 464:382 */         return 0x3FFFFFFF ^ getConDirMask(md);
/* 465:    */       }
/* 466:384 */       return 0;
/* 467:    */     }
/* 468:387 */     if ((l == amq.S.cm) || (l == amq.aU.cm) || (l == amq.ci.cm) || (l == amq.aM.cm)) {
/* 469:389 */       return 1073741823;
/* 470:    */     }
/* 471:390 */     if ((l == amq.aT.cm) || (l == amq.aS.cm)) {
/* 472:392 */       return 1073741823;
/* 473:    */     }
/* 474:394 */     if ((l == amq.bk.cm) || (l == amq.bl.cm))
/* 475:    */     {
/* 476:396 */       int md = iba.h(i, j, k) & 0x1;
/* 477:397 */       if (md > 0) {
/* 478:397 */         return 12;
/* 479:    */       }
/* 480:398 */       return 3;
/* 481:    */     }
/* 482:408 */     if (amq.p[l].canConnectRedstone(iba, i, j, k, getDirToRedstone(side))) {
/* 483:410 */       return getConDirMask(side);
/* 484:    */     }
/* 485:411 */     return 0;
/* 486:    */   }
/* 487:    */   
/* 488:    */   private static int getES1(ym iba, int i, int j, int k, int side, int pcl, int cc)
/* 489:    */   {
/* 490:417 */     int l = iba.a(i, j, k);
/* 491:418 */     if (l == 0) {
/* 492:418 */       return 0;
/* 493:    */     }
/* 494:420 */     IConnectable rpa = (IConnectable)CoreLib.getTileEntity(iba, i, j, k, IConnectable.class);
/* 495:422 */     if (rpa != null)
/* 496:    */     {
/* 497:423 */       int cc2 = rpa.getCornerPowerMode();
/* 498:424 */       if ((cc == 0) || (cc2 == 0)) {
/* 499:424 */         return 0;
/* 500:    */       }
/* 501:425 */       if ((cc == 2) && (cc2 == 2)) {
/* 502:425 */         return 0;
/* 503:    */       }
/* 504:426 */       if ((cc == 3) && (cc2 == 1)) {
/* 505:426 */         return 0;
/* 506:    */       }
/* 507:428 */       int pc = rpa.getConnectClass(side);
/* 508:429 */       if (isCompatible(pc, pcl)) {
/* 509:430 */         return rpa.getConnectableMask();
/* 510:    */       }
/* 511:431 */       return 0;
/* 512:    */     }
/* 513:433 */     return 0;
/* 514:    */   }
/* 515:    */   
/* 516:    */   public static int getExtConSides(ym iba, IConnectable irp, int i, int j, int k, int dir, int cc)
/* 517:    */   {
/* 518:438 */     int cons = irp.getConnectableMask();
/* 519:439 */     cons &= getConDirMask(dir) & 0xFFFFFF;
/* 520:440 */     if (cons == 0) {
/* 521:440 */       return 0;
/* 522:    */     }
/* 523:442 */     int l = iba.a(i, j, k);
/* 524:443 */     if ((CoverLib.blockCoverPlate != null) && (l == CoverLib.blockCoverPlate.cm))
/* 525:    */     {
/* 526:445 */       if (iba.h(i, j, k) != 0) {
/* 527:445 */         return 0;
/* 528:    */       }
/* 529:447 */       ICoverable icv = (ICoverable)CoreLib.getTileEntity(iba, i, j, k, ICoverable.class);
/* 530:449 */       if (icv == null) {
/* 531:449 */         return 0;
/* 532:    */       }
/* 533:456 */       int m = icv.getCoverMask();
/* 534:457 */       if ((m & 1 << (dir ^ 0x1)) > 0) {
/* 535:457 */         return 0;
/* 536:    */       }
/* 537:458 */       m |= m << 12;
/* 538:459 */       m |= m << 6;m &= 0x30303;
/* 539:460 */       m |= m << 3;m &= 0x111111;
/* 540:461 */       m |= m << 2;m |= m << 1;
/* 541:462 */       cons &= (m ^ 0xFFFFFFFF);
/* 542:    */     }
/* 543:463 */     else if ((l != 0) && (l != amq.D.cm) && (l != amq.E.cm))
/* 544:    */     {
/* 545:464 */       return 0;
/* 546:    */     }
/* 547:466 */     int pcl = irp.getConnectClass(dir);
/* 548:    */     
/* 549:468 */     int isv = 0;
/* 550:469 */     if ((cons & 0xF) > 0) {
/* 551:470 */       isv |= getES1(iba, i, j - 1, k, 1, pcl, cc) & 0x222200;
/* 552:    */     }
/* 553:471 */     if ((cons & 0xF0) > 0) {
/* 554:472 */       isv |= getES1(iba, i, j + 1, k, 0, pcl, cc) & 0x111100;
/* 555:    */     }
/* 556:473 */     if ((cons & 0xF00) > 0) {
/* 557:474 */       isv |= getES1(iba, i, j, k - 1, 3, pcl, cc) & 0x880022;
/* 558:    */     }
/* 559:475 */     if ((cons & 0xF000) > 0) {
/* 560:476 */       isv |= getES1(iba, i, j, k + 1, 2, pcl, cc) & 0x440011;
/* 561:    */     }
/* 562:477 */     if ((cons & 0xF0000) > 0) {
/* 563:478 */       isv |= getES1(iba, i - 1, j, k, 5, pcl, cc) & 0x8888;
/* 564:    */     }
/* 565:479 */     if ((cons & 0xF00000) > 0) {
/* 566:480 */       isv |= getES1(iba, i + 1, j, k, 4, pcl, cc) & 0x4444;
/* 567:    */     }
/* 568:482 */     isv >>= (dir ^ 0x1) << 2;
/* 569:483 */     isv = (isv & 0xA) >> 1 | (isv & 0x5) << 1;
/* 570:484 */     isv |= isv << 6;
/* 571:485 */     isv |= isv << 3;
/* 572:486 */     isv &= 0x1111;
/* 573:487 */     isv <<= (dir & 0x1);
/* 574:489 */     switch (dir)
/* 575:    */     {
/* 576:    */     case 0: 
/* 577:    */     case 1: 
/* 578:490 */       return isv << 8;
/* 579:    */     case 2: 
/* 580:    */     case 3: 
/* 581:491 */       return isv << 10 & 0xFF0000 | isv & 0xFF;
/* 582:    */     }
/* 583:492 */     return isv << 2;
/* 584:    */   }
/* 585:    */   
/* 586:    */   public static int getConnections(ym iba, IConnectable irp, int i, int j, int k)
/* 587:    */   {
/* 588:499 */     int cons = irp.getConnectableMask();
/* 589:    */     
/* 590:501 */     int cs = 0;
/* 591:502 */     if ((cons & 0x1111100) > 0)
/* 592:    */     {
/* 593:503 */       int pcl = irp.getConnectClass(0);
/* 594:504 */       cs |= getConSides(iba, i, j - 1, k, 1, pcl) & 0x2222200;
/* 595:    */     }
/* 596:506 */     if ((cons & 0x2222200) > 0)
/* 597:    */     {
/* 598:507 */       int pcl = irp.getConnectClass(1);
/* 599:508 */       cs |= getConSides(iba, i, j + 1, k, 0, pcl) & 0x1111100;
/* 600:    */     }
/* 601:510 */     if ((cons & 0x4440011) > 0)
/* 602:    */     {
/* 603:511 */       int pcl = irp.getConnectClass(2);
/* 604:512 */       cs |= getConSides(iba, i, j, k - 1, 3, pcl) & 0x8880022;
/* 605:    */     }
/* 606:514 */     if ((cons & 0x8880022) > 0)
/* 607:    */     {
/* 608:515 */       int pcl = irp.getConnectClass(3);
/* 609:516 */       cs |= getConSides(iba, i, j, k + 1, 2, pcl) & 0x4440011;
/* 610:    */     }
/* 611:518 */     if ((cons & 0x10004444) > 0)
/* 612:    */     {
/* 613:519 */       int pcl = irp.getConnectClass(4);
/* 614:520 */       cs |= getConSides(iba, i - 1, j, k, 5, pcl) & 0x20008888;
/* 615:    */     }
/* 616:522 */     if ((cons & 0x20008888) > 0)
/* 617:    */     {
/* 618:523 */       int pcl = irp.getConnectClass(5);
/* 619:524 */       cs |= getConSides(iba, i + 1, j, k, 4, pcl) & 0x10004444;
/* 620:    */     }
/* 621:526 */     cs = cs << 1 & 0x2AAAAAAA | cs >> 1 & 0x15555555;
/* 622:527 */     cs &= cons;
/* 623:    */     
/* 624:529 */     return cs;
/* 625:    */   }
/* 626:    */   
/* 627:    */   public static int getExtConnections(ym iba, IConnectable irp, int i, int j, int k)
/* 628:    */   {
/* 629:534 */     int cs = 0;
/* 630:535 */     int cc = irp.getCornerPowerMode();
/* 631:536 */     cs |= getExtConSides(iba, irp, i, j - 1, k, 0, cc);
/* 632:537 */     cs |= getExtConSides(iba, irp, i, j + 1, k, 1, cc);
/* 633:538 */     cs |= getExtConSides(iba, irp, i, j, k - 1, 2, cc);
/* 634:539 */     cs |= getExtConSides(iba, irp, i, j, k + 1, 3, cc);
/* 635:540 */     cs |= getExtConSides(iba, irp, i - 1, j, k, 4, cc);
/* 636:541 */     cs |= getExtConSides(iba, irp, i + 1, j, k, 5, cc);
/* 637:542 */     return cs;
/* 638:    */   }
/* 639:    */   
/* 640:    */   public static int getExtConnectionExtras(ym iba, IConnectable irp, int i, int j, int k)
/* 641:    */   {
/* 642:547 */     int cs = 0;
/* 643:548 */     cs |= getExtConSides(iba, irp, i, j - 1, k, 0, 3);
/* 644:549 */     cs |= getExtConSides(iba, irp, i, j + 1, k, 1, 3);
/* 645:550 */     cs |= getExtConSides(iba, irp, i, j, k - 1, 2, 3);
/* 646:551 */     cs |= getExtConSides(iba, irp, i, j, k + 1, 3, 3);
/* 647:552 */     cs |= getExtConSides(iba, irp, i - 1, j, k, 4, 3);
/* 648:553 */     cs |= getExtConSides(iba, irp, i + 1, j, k, 5, 3);
/* 649:554 */     return cs;
/* 650:    */   }
/* 651:    */   
/* 652:    */   public static int getTileCurrentStrength(yc world, int i, int j, int k, int cons, int ch)
/* 653:    */   {
/* 654:562 */     IRedPowerConnectable irp = (IRedPowerConnectable)CoreLib.getTileEntity(world, i, j, k, IRedPowerConnectable.class);
/* 655:564 */     if (irp == null) {
/* 656:564 */       return -1;
/* 657:    */     }
/* 658:565 */     if ((irp instanceof IRedPowerWiring))
/* 659:    */     {
/* 660:566 */       IRedPowerWiring irw = (IRedPowerWiring)irp;
/* 661:567 */       return irw.getCurrentStrength(cons, ch);
/* 662:    */     }
/* 663:569 */     if ((irp.getPoweringMask(ch) & cons) > 0) {
/* 664:569 */       return 255;
/* 665:    */     }
/* 666:570 */     return -1;
/* 667:    */   }
/* 668:    */   
/* 669:    */   public static int getTileOrRedstoneCurrentStrength(yc world, int i, int j, int k, int cons, int ch)
/* 670:    */   {
/* 671:575 */     int bid = world.a(i, j, k);
/* 672:576 */     if (bid == 0) {
/* 673:576 */       return -1;
/* 674:    */     }
/* 675:577 */     if (bid == amq.ay.cm)
/* 676:    */     {
/* 677:578 */       int md = world.h(i, j, k);
/* 678:579 */       if (md > 0) {
/* 679:579 */         return md;
/* 680:    */       }
/* 681:580 */       return -1;
/* 682:    */     }
/* 683:583 */     IRedPowerConnectable irp = (IRedPowerConnectable)CoreLib.getTileEntity(world, i, j, k, IRedPowerConnectable.class);
/* 684:585 */     if (irp == null) {
/* 685:585 */       return -1;
/* 686:    */     }
/* 687:586 */     if ((irp instanceof IRedPowerWiring))
/* 688:    */     {
/* 689:587 */       IRedPowerWiring irw = (IRedPowerWiring)irp;
/* 690:588 */       return irw.getCurrentStrength(cons, ch);
/* 691:    */     }
/* 692:590 */     if ((irp.getPoweringMask(ch) & cons) > 0) {
/* 693:590 */       return 255;
/* 694:    */     }
/* 695:591 */     return -1;
/* 696:    */   }
/* 697:    */   
/* 698:    */   private static int getIndCur(yc world, int i, int j, int k, int d1, int d2, int ch)
/* 699:    */   {
/* 700:    */     int d3;
/* 701:597 */     switch (d1)
/* 702:    */     {
/* 703:    */     case 0: 
/* 704:598 */       j--;d3 = d2 + 2; break;
/* 705:    */     case 1: 
/* 706:599 */       j++;d3 = d2 + 2; break;
/* 707:    */     case 2: 
/* 708:600 */       k--;d3 = d2 + (d2 & 0x2); break;
/* 709:    */     case 3: 
/* 710:601 */       k++;d3 = d2 + (d2 & 0x2); break;
/* 711:    */     case 4: 
/* 712:602 */       i--;d3 = d2; break;
/* 713:    */     default: 
/* 714:603 */       i++;d3 = d2;
/* 715:    */     }
/* 716:    */     int d4;
/* 717:605 */     switch (d3)
/* 718:    */     {
/* 719:    */     case 0: 
/* 720:606 */       j--;d4 = d1 - 2; break;
/* 721:    */     case 1: 
/* 722:607 */       j++;d4 = d1 - 2; break;
/* 723:    */     case 2: 
/* 724:608 */       k--;d4 = d1 & 0x1 | (d1 & 0x4) >> 1; break;
/* 725:    */     case 3: 
/* 726:609 */       k++;d4 = d1 & 0x1 | (d1 & 0x4) >> 1; break;
/* 727:    */     case 4: 
/* 728:610 */       i--;d4 = d1; break;
/* 729:    */     default: 
/* 730:611 */       i++;d4 = d1;
/* 731:    */     }
/* 732:613 */     return getTileCurrentStrength(world, i, j, k, 1 << (d4 ^ 0x1) << ((d3 ^ 0x1) << 2), ch);
/* 733:    */   }
/* 734:    */   
/* 735:    */   public static int getMaxCurrentStrength(yc world, int i, int j, int k, int cons, int indcon, int ch)
/* 736:    */   {
/* 737:620 */     int mcs = -1;
/* 738:621 */     int ocon = cons << 1 & 0x2AAAAAAA | cons >> 1 & 0x15555555;
/* 739:623 */     if ((cons & 0x1111100) > 0) {
/* 740:624 */       mcs = Math.max(mcs, getTileOrRedstoneCurrentStrength(world, i, j - 1, k, ocon & 0x2222200, ch));
/* 741:    */     }
/* 742:627 */     if ((cons & 0x2222200) > 0) {
/* 743:628 */       mcs = Math.max(mcs, getTileOrRedstoneCurrentStrength(world, i, j + 1, k, ocon & 0x1111100, ch));
/* 744:    */     }
/* 745:631 */     if ((cons & 0x4440011) > 0) {
/* 746:632 */       mcs = Math.max(mcs, getTileOrRedstoneCurrentStrength(world, i, j, k - 1, ocon & 0x8880022, ch));
/* 747:    */     }
/* 748:635 */     if ((cons & 0x8880022) > 0) {
/* 749:636 */       mcs = Math.max(mcs, getTileOrRedstoneCurrentStrength(world, i, j, k + 1, ocon & 0x4440011, ch));
/* 750:    */     }
/* 751:639 */     if ((cons & 0x10004444) > 0) {
/* 752:640 */       mcs = Math.max(mcs, getTileOrRedstoneCurrentStrength(world, i - 1, j, k, ocon & 0x20008888, ch));
/* 753:    */     }
/* 754:643 */     if ((cons & 0x20008888) > 0) {
/* 755:644 */       mcs = Math.max(mcs, getTileOrRedstoneCurrentStrength(world, i + 1, j, k, ocon & 0x10004444, ch));
/* 756:    */     }
/* 757:648 */     for (int a = 0; a < 6; a++) {
/* 758:648 */       for (int b = 0; b < 4; b++) {
/* 759:649 */         if ((indcon & 1 << a * 4 + b) > 0) {
/* 760:650 */           mcs = Math.max(mcs, getIndCur(world, i, j, k, a, b, ch));
/* 761:    */         }
/* 762:    */       }
/* 763:    */     }
/* 764:654 */     return mcs;
/* 765:    */   }
/* 766:    */   
/* 767:    */   public static void addUpdateBlock(int i, int j, int k)
/* 768:    */   {
/* 769:659 */     for (int a = -3; a <= 3; a++) {
/* 770:659 */       for (int b = -3; b <= 3; b++) {
/* 771:659 */         for (int c = -3; c <= 3; c++)
/* 772:    */         {
/* 773:660 */           int md = a < 0 ? -a : a;md += (b < 0 ? -b : b);
/* 774:661 */           md += (c < 0 ? -c : c);
/* 775:662 */           if (md <= 3) {
/* 776:663 */             blockUpdates.add(Arrays.asList(new Integer[] { Integer.valueOf(i + a), Integer.valueOf(j + b), Integer.valueOf(k + c) }));
/* 777:    */           }
/* 778:    */         }
/* 779:    */       }
/* 780:    */     }
/* 781:    */   }
/* 782:    */   
/* 783:    */   public static void addStartSearchBlock(int i, int j, int k)
/* 784:    */   {
/* 785:668 */     List sb = Arrays.asList(new Integer[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) });
/* 786:669 */     if (powerSearchTest.contains(sb)) {
/* 787:669 */       return;
/* 788:    */     }
/* 789:670 */     powerSearch.addLast(sb);
/* 790:671 */     powerSearchTest.add(sb);
/* 791:    */   }
/* 792:    */   
/* 793:    */   public static void addSearchBlock(int i, int j, int k)
/* 794:    */   {
/* 795:675 */     addStartSearchBlock(i, j, k);
/* 796:676 */     blockUpdates.add(Arrays.asList(new Integer[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) }));
/* 797:    */   }
/* 798:    */   
/* 799:    */   private static void addIndBl(int i, int j, int k, int d1, int d2)
/* 800:    */   {
/* 801:    */     int d3;
/* 802:682 */     switch (d1)
/* 803:    */     {
/* 804:    */     case 0: 
/* 805:683 */       j--;d3 = d2 + 2; break;
/* 806:    */     case 1: 
/* 807:684 */       j++;d3 = d2 + 2; break;
/* 808:    */     case 2: 
/* 809:685 */       k--;d3 = d2 + (d2 & 0x2); break;
/* 810:    */     case 3: 
/* 811:686 */       k++;d3 = d2 + (d2 & 0x2); break;
/* 812:    */     case 4: 
/* 813:687 */       i--;d3 = d2; break;
/* 814:    */     default: 
/* 815:688 */       i++;d3 = d2;
/* 816:    */     }
/* 817:690 */     switch (d3)
/* 818:    */     {
/* 819:    */     case 0: 
/* 820:691 */       j--; break;
/* 821:    */     case 1: 
/* 822:692 */       j++; break;
/* 823:    */     case 2: 
/* 824:693 */       k--; break;
/* 825:    */     case 3: 
/* 826:694 */       k++; break;
/* 827:    */     case 4: 
/* 828:695 */       i--; break;
/* 829:    */     case 5: 
/* 830:696 */       i++;
/* 831:    */     }
/* 832:698 */     addSearchBlock(i, j, k);
/* 833:    */   }
/* 834:    */   
/* 835:    */   public static void addSearchBlocks(int i, int j, int k, int cons, int indcon)
/* 836:    */   {
/* 837:703 */     int ocon = cons << 1 & 0xAAAAAA | cons >> 1 & 0x555555;
/* 838:705 */     if ((cons & 0x1111100) > 0) {
/* 839:705 */       addSearchBlock(i, j - 1, k);
/* 840:    */     }
/* 841:706 */     if ((cons & 0x2222200) > 0) {
/* 842:706 */       addSearchBlock(i, j + 1, k);
/* 843:    */     }
/* 844:707 */     if ((cons & 0x4440011) > 0) {
/* 845:707 */       addSearchBlock(i, j, k - 1);
/* 846:    */     }
/* 847:708 */     if ((cons & 0x8880022) > 0) {
/* 848:708 */       addSearchBlock(i, j, k + 1);
/* 849:    */     }
/* 850:709 */     if ((cons & 0x10004444) > 0) {
/* 851:709 */       addSearchBlock(i - 1, j, k);
/* 852:    */     }
/* 853:710 */     if ((cons & 0x20008888) > 0) {
/* 854:710 */       addSearchBlock(i + 1, j, k);
/* 855:    */     }
/* 856:713 */     for (int a = 0; a < 6; a++) {
/* 857:713 */       for (int b = 0; b < 4; b++) {
/* 858:714 */         if ((indcon & 1 << a * 4 + b) > 0) {
/* 859:715 */           addIndBl(i, j, k, a, b);
/* 860:    */         }
/* 861:    */       }
/* 862:    */     }
/* 863:    */   }
/* 864:    */   
/* 865:    */   public static void updateCurrent(yc world, int i, int j, int k)
/* 866:    */   {
/* 867:721 */     addStartSearchBlock(i, j, k);
/* 868:722 */     if (searching) {
/* 869:722 */       return;
/* 870:    */     }
/* 871:723 */     searching = true;
/* 872:724 */     while (powerSearch.size() > 0)
/* 873:    */     {
/* 874:725 */       List sp1 = (List)powerSearch.removeFirst();
/* 875:726 */       powerSearchTest.remove(sp1);
/* 876:727 */       Integer[] sp = (Integer[])sp1.toArray();
/* 877:    */       
/* 878:729 */       IRedPowerWiring rp = (IRedPowerWiring)CoreLib.getTileEntity(world, sp[0].intValue(), sp[1].intValue(), sp[2].intValue(), IRedPowerWiring.class);
/* 879:733 */       if (rp != null) {
/* 880:734 */         rp.updateCurrentStrength();
/* 881:    */       }
/* 882:    */     }
/* 883:736 */     searching = false;
/* 884:    */     
/* 885:738 */     ArrayList up2 = new ArrayList(blockUpdates);
/* 886:739 */     blockUpdates.clear();
/* 887:740 */     for (int l = 0; l < up2.size(); l++)
/* 888:    */     {
/* 889:741 */       Integer[] sp = (Integer[])((List)up2.get(l)).toArray();
/* 890:742 */       notifyBlock(world, sp[0].intValue(), sp[1].intValue(), sp[2].intValue(), amq.ay.cm);
/* 891:    */       
/* 892:744 */       world.i(sp[0].intValue(), sp[1].intValue(), sp[2].intValue());
/* 893:    */     }
/* 894:    */   }
/* 895:    */   
/* 896:    */   public static int updateBlockCurrentStrength(yc world, IRedPowerWiring irp, int i, int j, int k, int conm, int chm)
/* 897:    */   {
/* 898:753 */     int cons = irp.getConnectionMask() & conm;
/* 899:754 */     int indcon = irp.getExtConnectionMask() & conm;
/* 900:    */     
/* 901:756 */     int mx = -1;int ps = 0;int cs = 0;
/* 902:757 */     int chm2 = chm;
/* 903:759 */     while (chm2 > 0)
/* 904:    */     {
/* 905:760 */       int ch = Integer.numberOfTrailingZeros(chm2);
/* 906:761 */       chm2 &= (1 << ch ^ 0xFFFFFFFF);
/* 907:762 */       cs = Math.max(cs, irp.getCurrentStrength(conm, ch));
/* 908:763 */       mx = Math.max(mx, getMaxCurrentStrength(world, i, j, k, cons, indcon, ch));
/* 909:    */       
/* 910:765 */       ps = Math.max(ps, irp.scanPoweringStrength(cons | indcon, ch));
/* 911:    */     }
/* 912:767 */     if ((ps <= cs) && ((mx == cs + 1) || ((cs == 0) && (mx == 0)))) {
/* 913:767 */       return cs;
/* 914:    */     }
/* 915:768 */     if ((ps == cs) && (mx <= cs)) {
/* 916:768 */       return cs;
/* 917:    */     }
/* 918:770 */     cs = Math.max(ps, cs);
/* 919:771 */     if (cs >= mx)
/* 920:    */     {
/* 921:772 */       if (cs > ps) {
/* 922:772 */         cs = 0;
/* 923:    */       }
/* 924:    */     }
/* 925:    */     else {
/* 926:774 */       cs = Math.max(0, mx - 1);
/* 927:    */     }
/* 928:777 */     if ((chm & 0x1) > 0) {
/* 929:777 */       addUpdateBlock(i, j, k);
/* 930:    */     }
/* 931:778 */     addSearchBlocks(i, j, k, cons, indcon);
/* 932:779 */     return cs;
/* 933:    */   }
/* 934:    */   
/* 935:    */   public static boolean isSearching()
/* 936:    */   {
/* 937:782 */     return searching;
/* 938:    */   }
/* 939:    */   
/* 940:    */   public static void addCompatibleMapping(int a, int b)
/* 941:    */   {
/* 942:785 */     powerClassMapping.add(Arrays.asList(new Integer[] { Integer.valueOf(a), Integer.valueOf(b) }));
/* 943:786 */     powerClassMapping.add(Arrays.asList(new Integer[] { Integer.valueOf(b), Integer.valueOf(a) }));
/* 944:    */   }
/* 945:    */   
/* 946:    */   public static boolean isCompatible(int a, int b)
/* 947:    */   {
/* 948:790 */     if (a != b) {}
/* 949:790 */     return powerClassMapping.contains(Arrays.asList(new Integer[] { Integer.valueOf(a), Integer.valueOf(b) }));
/* 950:    */   }
/* 951:    */   
/* 952:793 */   private static HashSet powerClassMapping = new HashSet();
/* 953:794 */   private static HashSet blockUpdates = new HashSet();
/* 954:796 */   private static LinkedList powerSearch = new LinkedList();
/* 955:797 */   private static HashSet powerSearchTest = new HashSet();
/* 956:798 */   private static boolean searching = false;
/* 957:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.RedPowerLib
 * JD-Core Version:    0.7.0.1
 */