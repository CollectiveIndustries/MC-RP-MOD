/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.core.CoreLib;
/*   6:    */ import com.eloraam.redpower.core.FluidBuffer;
/*   7:    */ import com.eloraam.redpower.core.FluidClass;
/*   8:    */ import com.eloraam.redpower.core.IPipeConnectable;
/*   9:    */ import com.eloraam.redpower.core.PipeLib;
/*  10:    */ import com.eloraam.redpower.core.WorldCoord;
/*  11:    */ import java.util.Collections;
/*  12:    */ import java.util.Comparator;
/*  13:    */ import java.util.HashMap;
/*  14:    */ import java.util.PriorityQueue;
/*  15:    */ import md;
/*  16:    */ import ur;
/*  17:    */ import yc;
/*  18:    */ import ym;
/*  19:    */ 
/*  20:    */ public class TileGrate
/*  21:    */   extends TileMachinePanel
/*  22:    */   implements IPipeConnectable
/*  23:    */ {
/*  24:    */   public static class SimpleComparator
/*  25:    */     implements Comparator
/*  26:    */   {
/*  27:    */     int dir;
/*  28:    */     
/*  29:    */     public int compare(Object a, Object b)
/*  30:    */     {
/*  31: 20 */       TileGrate.FluidCoord wa = (TileGrate.FluidCoord)a;
/*  32: 21 */       TileGrate.FluidCoord wb = (TileGrate.FluidCoord)b;
/*  33:    */       
/*  34: 23 */       return wa.dist - wb.dist;
/*  35:    */     }
/*  36:    */   }
/*  37:    */   
/*  38:    */   public static class FluidCoord
/*  39:    */     implements Comparable
/*  40:    */   {
/*  41:    */     public WorldCoord wc;
/*  42:    */     public int dist;
/*  43:    */     
/*  44:    */     public FluidCoord(WorldCoord w, int d)
/*  45:    */     {
/*  46: 30 */       this.wc = w;
/*  47: 31 */       this.dist = d;
/*  48:    */     }
/*  49:    */     
/*  50:    */     public int compareTo(Object obj)
/*  51:    */     {
/*  52: 35 */       FluidCoord wr = (FluidCoord)obj;
/*  53: 36 */       if (this.wc.y == wr.wc.y) {
/*  54: 37 */         return this.dist - wr.dist;
/*  55:    */       }
/*  56: 38 */       return this.wc.y - wr.wc.y;
/*  57:    */     }
/*  58:    */   }
/*  59:    */   
/*  60:    */   public class GratePathfinder
/*  61:    */   {
/*  62:    */     WorldCoord startPos;
/*  63:    */     
/*  64:    */     public GratePathfinder(boolean checkVertical)
/*  65:    */     {
/*  66: 47 */       if (checkVertical) {
/*  67: 48 */         this.workset = new PriorityQueue();
/*  68:    */       } else {
/*  69: 50 */         this.workset = new PriorityQueue(1024, new TileGrate.SimpleComparator());
/*  70:    */       }
/*  71:    */     }
/*  72:    */     
/*  73:    */     public void start(WorldCoord wc, int tp, int sides)
/*  74:    */     {
/*  75: 56 */       this.fluidID = tp;
/*  76: 57 */       this.fluidClass = PipeLib.getLiquidClass(this.fluidID);
/*  77: 58 */       this.startPos = wc;
/*  78: 60 */       for (int i = 0; i < 6; i++) {
/*  79: 61 */         if ((sides & 1 << i) != 0)
/*  80:    */         {
/*  81: 64 */           WorldCoord wc2 = wc.coordStep(i);
/*  82: 65 */           this.backlink.put(wc2, wc);
/*  83: 66 */           this.workset.add(new TileGrate.FluidCoord(wc2, 0));
/*  84:    */         }
/*  85:    */       }
/*  86:    */     }
/*  87:    */     
/*  88:    */     public boolean startSuck(WorldCoord wc, int sides)
/*  89:    */     {
/*  90: 71 */       this.fluidID = 0;
/*  91: 72 */       this.startPos = wc;
/*  92: 74 */       for (int i = 0; i < 6; i++) {
/*  93: 75 */         if ((sides & 1 << i) != 0)
/*  94:    */         {
/*  95: 78 */           WorldCoord wc2 = wc.coordStep(i);
/*  96: 79 */           this.backlink.put(wc2, wc);
/*  97: 80 */           this.workset.add(new TileGrate.FluidCoord(wc2, 0));
/*  98:    */           
/*  99: 82 */           int fl = PipeLib.getLiquidId(TileGrate.this.k, wc2);
/* 100: 83 */           if (fl != 0) {
/* 101: 83 */             this.fluidID = fl;
/* 102:    */           }
/* 103:    */         }
/* 104:    */       }
/* 105: 85 */       if (this.fluidID == 0) {
/* 106: 85 */         return false;
/* 107:    */       }
/* 108: 86 */       this.fluidClass = PipeLib.getLiquidClass(this.fluidID);
/* 109: 87 */       return true;
/* 110:    */     }
/* 111:    */     
/* 112:    */     public boolean isConnected(WorldCoord wc)
/* 113:    */     {
/* 114: 91 */       if (wc.compareTo(this.startPos) == 0) {
/* 115: 92 */         return true;
/* 116:    */       }
/* 117:    */       do
/* 118:    */       {
/* 119: 94 */         wc = (WorldCoord)this.backlink.get(wc);
/* 120: 95 */         if (wc == null) {
/* 121: 95 */           return false;
/* 122:    */         }
/* 123: 97 */         if (wc.compareTo(this.startPos) == 0) {
/* 124: 98 */           return true;
/* 125:    */         }
/* 126: 99 */       } while (this.fluidClass.getFluidId(TileGrate.this.k, wc) == this.fluidID);
/* 127:101 */       return false;
/* 128:    */     }
/* 129:    */     
/* 130:    */     public void stepAdd(TileGrate.FluidCoord nc)
/* 131:    */     {
/* 132:106 */       for (int i = 0; i < 6; i++)
/* 133:    */       {
/* 134:107 */         WorldCoord wc2 = nc.wc.coordStep(i);
/* 135:108 */         if (!this.backlink.containsKey(wc2))
/* 136:    */         {
/* 137:111 */           this.backlink.put(wc2, nc.wc);
/* 138:112 */           this.workset.add(new TileGrate.FluidCoord(wc2, nc.dist + 1));
/* 139:    */         }
/* 140:    */       }
/* 141:    */     }
/* 142:    */     
/* 143:    */     public void stepMap(TileGrate.FluidCoord nc)
/* 144:    */     {
/* 145:117 */       for (int i = 0; i < 6; i++)
/* 146:    */       {
/* 147:118 */         WorldCoord wc2 = nc.wc.coordStep(i);
/* 148:120 */         if (this.fluidClass.getFluidId(TileGrate.this.k, wc2) == this.fluidID) {
/* 149:123 */           if (!this.backlink.containsKey(wc2))
/* 150:    */           {
/* 151:126 */             this.backlink.put(wc2, nc.wc);
/* 152:127 */             this.workset.add(new TileGrate.FluidCoord(wc2, nc.dist + 1));
/* 153:    */           }
/* 154:    */         }
/* 155:    */       }
/* 156:    */     }
/* 157:    */     
/* 158:    */     public int tryDumpFluid(int level, int tries)
/* 159:    */     {
/* 160:132 */       for (int i = 0; i < tries; i++)
/* 161:    */       {
/* 162:133 */         TileGrate.FluidCoord nc = (TileGrate.FluidCoord)this.workset.poll();
/* 163:134 */         if (nc == null)
/* 164:    */         {
/* 165:135 */           TileGrate.this.restartPath();
/* 166:136 */           return level;
/* 167:    */         }
/* 168:139 */         if (!isConnected(nc.wc))
/* 169:    */         {
/* 170:140 */           TileGrate.this.restartPath();
/* 171:141 */           return level;
/* 172:    */         }
/* 173:144 */         if (TileGrate.this.k.a(nc.wc.x, nc.wc.y, nc.wc.z) == 0)
/* 174:    */         {
/* 175:146 */           if (this.fluidClass.setFluidLevel(TileGrate.this.k, nc.wc, level))
/* 176:    */           {
/* 177:148 */             stepAdd(nc);
/* 178:149 */             return 0;
/* 179:    */           }
/* 180:    */         }
/* 181:153 */         else if (this.fluidClass.getFluidId(TileGrate.this.k, nc.wc) == this.fluidID)
/* 182:    */         {
/* 183:156 */           stepAdd(nc);
/* 184:    */           
/* 185:158 */           int lv1 = this.fluidClass.getFluidLevel(TileGrate.this.k, nc.wc);
/* 186:160 */           if (lv1 < 1000)
/* 187:    */           {
/* 188:162 */             int lv2 = Math.min(lv1 + level, 1000);
/* 189:163 */             if (this.fluidClass.setFluidLevel(TileGrate.this.k, nc.wc, lv2))
/* 190:    */             {
/* 191:165 */               level -= lv2 - lv1;
/* 192:166 */               if (level == 0) {
/* 193:166 */                 return 0;
/* 194:    */               }
/* 195:    */             }
/* 196:    */           }
/* 197:    */         }
/* 198:    */       }
/* 199:169 */       return level;
/* 200:    */     }
/* 201:    */     
/* 202:    */     public boolean tryMapFluid(int tries)
/* 203:    */     {
/* 204:173 */       if (this.allset.size() > 32768) {
/* 205:174 */         return true;
/* 206:    */       }
/* 207:177 */       for (int i = 0; i < tries; i++)
/* 208:    */       {
/* 209:178 */         TileGrate.FluidCoord nc = (TileGrate.FluidCoord)this.workset.poll();
/* 210:179 */         if (nc == null) {
/* 211:179 */           return true;
/* 212:    */         }
/* 213:181 */         if (this.fluidClass.getFluidId(TileGrate.this.k, nc.wc) == this.fluidID)
/* 214:    */         {
/* 215:184 */           stepMap(nc);
/* 216:    */           
/* 217:186 */           int lv1 = this.fluidClass.getFluidLevel(TileGrate.this.k, nc.wc);
/* 218:188 */           if (lv1 > 0) {
/* 219:188 */             this.allset.add(nc);
/* 220:    */           }
/* 221:    */         }
/* 222:    */       }
/* 223:190 */       return false;
/* 224:    */     }
/* 225:    */     
/* 226:    */     public int trySuckFluid(int level)
/* 227:    */     {
/* 228:194 */       int tr = 0;
/* 229:195 */       while (!this.allset.isEmpty())
/* 230:    */       {
/* 231:196 */         TileGrate.FluidCoord nc = (TileGrate.FluidCoord)this.allset.peek();
/* 232:197 */         if (!isConnected(nc.wc))
/* 233:    */         {
/* 234:198 */           TileGrate.this.restartPath();
/* 235:199 */           return tr;
/* 236:    */         }
/* 237:202 */         if (this.fluidClass.getFluidId(TileGrate.this.k, nc.wc) != this.fluidID)
/* 238:    */         {
/* 239:204 */           this.allset.poll();
/* 240:    */         }
/* 241:    */         else
/* 242:    */         {
/* 243:208 */           int lv1 = this.fluidClass.getFluidLevel(TileGrate.this.k, nc.wc);
/* 244:210 */           if (lv1 == 0)
/* 245:    */           {
/* 246:211 */             this.allset.poll();
/* 247:    */           }
/* 248:    */           else
/* 249:    */           {
/* 250:214 */             if (tr + lv1 <= level)
/* 251:    */             {
/* 252:215 */               tr += lv1;
/* 253:216 */               TileGrate.this.k.e(nc.wc.x, nc.wc.y, nc.wc.z, 0);
/* 254:    */               
/* 255:218 */               this.allset.poll();
/* 256:219 */               if (tr == level) {
/* 257:219 */                 return level;
/* 258:    */               }
/* 259:    */             }
/* 260:221 */             if (this.fluidClass.setFluidLevel(TileGrate.this.k, nc.wc, level - tr)) {
/* 261:223 */               return level;
/* 262:    */             }
/* 263:    */           }
/* 264:    */         }
/* 265:    */       }
/* 266:226 */       TileGrate.this.restartPath();
/* 267:227 */       return tr;
/* 268:    */     }
/* 269:    */     
/* 270:231 */     HashMap backlink = new HashMap();
/* 271:    */     PriorityQueue workset;
/* 272:236 */     PriorityQueue allset = new PriorityQueue(1024, Collections.reverseOrder());
/* 273:    */     public int fluidID;
/* 274:    */     public FluidClass fluidClass;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public int getPartMaxRotation(int part, boolean sec)
/* 278:    */   {
/* 279:247 */     if (sec) {
/* 280:247 */       return 0;
/* 281:    */     }
/* 282:248 */     return 5;
/* 283:    */   }
/* 284:    */   
/* 285:    */   public int getPipeConnectableSides()
/* 286:    */   {
/* 287:254 */     return 1 << this.Rotation;
/* 288:    */   }
/* 289:    */   
/* 290:    */   public int getPipeFlangeSides()
/* 291:    */   {
/* 292:258 */     return 1 << this.Rotation;
/* 293:    */   }
/* 294:    */   
/* 295:    */   public int getPipePressure(int side)
/* 296:    */   {
/* 297:262 */     return this.pressure;
/* 298:    */   }
/* 299:    */   
/* 300:265 */   FluidBuffer gratebuf = new FluidBuffer()
/* 301:    */   {
/* 302:    */     public any getParent()
/* 303:    */     {
/* 304:267 */       return TileGrate.this;
/* 305:    */     }
/* 306:    */     
/* 307:    */     public void onChange()
/* 308:    */     {
/* 309:270 */       TileGrate.this.dirtyBlock();
/* 310:    */     }
/* 311:    */     
/* 312:    */     public int getMaxLevel()
/* 313:    */     {
/* 314:272 */       return 1000;
/* 315:    */     }
/* 316:    */   };
/* 317:    */   
/* 318:    */   public FluidBuffer getPipeBuffer(int side)
/* 319:    */   {
/* 320:276 */     return this.gratebuf;
/* 321:    */   }
/* 322:    */   
/* 323:    */   public void onFramePickup(ym iba)
/* 324:    */   {
/* 325:282 */     restartPath();
/* 326:    */   }
/* 327:    */   
/* 328:    */   public int getExtendedID()
/* 329:    */   {
/* 330:288 */     return 3;
/* 331:    */   }
/* 332:    */   
/* 333:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 334:    */   {
/* 335:293 */     this.Rotation = getFacing(ent);
/* 336:294 */     updateBlockChange();
/* 337:    */   }
/* 338:    */   
/* 339:    */   public void onBlockNeighborChange(int l) {}
/* 340:    */   
/* 341:    */   public void g()
/* 342:    */   {
/* 343:301 */     super.g();
/* 344:302 */     if (CoreLib.isClient(this.k)) {
/* 345:303 */       return;
/* 346:    */     }
/* 347:304 */     if (!isTickScheduled()) {
/* 348:305 */       scheduleTick(5);
/* 349:    */     }
/* 350:307 */     WorldCoord wc = new WorldCoord(this);
/* 351:308 */     wc.step(this.Rotation);
/* 352:309 */     Integer pr = PipeLib.getPressure(this.k, wc, this.Rotation ^ 0x1);
/* 353:310 */     if (pr != null) {
/* 354:311 */       this.pressure = (pr.intValue() - Integer.signum(pr.intValue()));
/* 355:    */     }
/* 356:313 */     if (this.searchState == 1) {
/* 357:314 */       this.searchPath.tryMapFluid(400);
/* 358:    */     }
/* 359:315 */     PipeLib.movePipeLiquid(this.k, this, new WorldCoord(this), 1 << this.Rotation);
/* 360:    */   }
/* 361:    */   
/* 362:319 */   GratePathfinder searchPath = null;
/* 363:320 */   int searchState = 0;
/* 364:    */   int pressure;
/* 365:    */   
/* 366:    */   public void restartPath()
/* 367:    */   {
/* 368:323 */     this.searchPath = null;this.searchState = 0;
/* 369:    */   }
/* 370:    */   
/* 371:    */   public void onTileTick()
/* 372:    */   {
/* 373:327 */     if (CoreLib.isClient(this.k)) {
/* 374:328 */       return;
/* 375:    */     }
/* 376:341 */     if (this.pressure == 0)
/* 377:    */     {
/* 378:342 */       restartPath();
/* 379:    */     }
/* 380:343 */     else if (this.pressure < -100)
/* 381:    */     {
/* 382:344 */       if (this.gratebuf.getLevel() >= this.gratebuf.getMaxLevel()) {
/* 383:345 */         return;
/* 384:    */       }
/* 385:346 */       if (this.searchState == 2) {
/* 386:347 */         restartPath();
/* 387:    */       }
/* 388:348 */       if (this.searchState == 0)
/* 389:    */       {
/* 390:349 */         this.searchState = 1;
/* 391:350 */         this.searchPath = new GratePathfinder(false);
/* 392:351 */         if (this.gratebuf.Type == 0)
/* 393:    */         {
/* 394:352 */           if (!this.searchPath.startSuck(new WorldCoord(this), 0x3F ^ 1 << this.Rotation)) {
/* 395:355 */             restartPath();
/* 396:    */           }
/* 397:    */         }
/* 398:    */         else {
/* 399:359 */           this.searchPath.start(new WorldCoord(this), this.gratebuf.Type, 0x3F ^ 1 << this.Rotation);
/* 400:    */         }
/* 401:    */       }
/* 402:364 */       if (this.searchState == 1)
/* 403:    */       {
/* 404:365 */         if (!this.searchPath.tryMapFluid(400)) {
/* 405:365 */           return;
/* 406:    */         }
/* 407:366 */         int lv = this.searchPath.trySuckFluid(this.searchPath.fluidClass.getFluidQuanta());
/* 408:368 */         if (lv == 0) {
/* 409:368 */           return;
/* 410:    */         }
/* 411:369 */         this.gratebuf.addLevel(this.searchPath.fluidID, lv);
/* 412:    */       }
/* 413:    */     }
/* 414:371 */     else if (this.pressure > 100)
/* 415:    */     {
/* 416:372 */       FluidClass fluid = this.gratebuf.getFluidClass();
/* 417:373 */       if (fluid == null) {
/* 418:373 */         return;
/* 419:    */       }
/* 420:377 */       int fq = fluid.getFluidQuanta();
/* 421:378 */       if (fq == 0) {
/* 422:378 */         return;
/* 423:    */       }
/* 424:380 */       if (this.gratebuf.getLevel() < fq) {
/* 425:380 */         return;
/* 426:    */       }
/* 427:381 */       if (this.gratebuf.Type == 0) {
/* 428:381 */         return;
/* 429:    */       }
/* 430:383 */       if (this.searchState == 1) {
/* 431:384 */         restartPath();
/* 432:    */       }
/* 433:385 */       if (this.searchState == 0)
/* 434:    */       {
/* 435:386 */         this.searchState = 2;
/* 436:387 */         this.searchPath = new GratePathfinder(true);
/* 437:388 */         this.searchPath.start(new WorldCoord(this), this.gratebuf.Type, 0x3F ^ 1 << this.Rotation);
/* 438:    */       }
/* 439:391 */       if (this.searchState == 2)
/* 440:    */       {
/* 441:392 */         int fr = this.searchPath.tryDumpFluid(fq, 2000);
/* 442:393 */         if (fr != fq) {
/* 443:394 */           this.gratebuf.addLevel(this.gratebuf.Type, -fq);
/* 444:    */         }
/* 445:    */       }
/* 446:    */     }
/* 447:    */   }
/* 448:    */   
/* 449:    */   public void a(bq tag)
/* 450:    */   {
/* 451:403 */     super.a(tag);
/* 452:404 */     this.gratebuf.readFromNBT(tag, "buf");
/* 453:405 */     this.pressure = tag.d("pres");
/* 454:    */   }
/* 455:    */   
/* 456:    */   public void b(bq tag)
/* 457:    */   {
/* 458:409 */     super.b(tag);
/* 459:410 */     this.gratebuf.writeToNBT(tag, "buf");
/* 460:411 */     tag.a("pres", (short)this.pressure);
/* 461:    */   }
/* 462:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileGrate
 * JD-Core Version:    0.7.0.1
 */