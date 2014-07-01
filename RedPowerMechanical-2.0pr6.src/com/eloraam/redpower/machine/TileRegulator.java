/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.ITubeConnectable;
/*   8:    */ import com.eloraam.redpower.core.MachineLib;
/*   9:    */ import com.eloraam.redpower.core.MachineLib.FilterMap;
/*  10:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  11:    */ import com.eloraam.redpower.core.TubeItem;
/*  12:    */ import com.eloraam.redpower.core.WorldCoord;
/*  13:    */ import la;
/*  14:    */ import net.minecraftforge.common.ForgeDirection;
/*  15:    */ import net.minecraftforge.common.ISidedInventory;
/*  16:    */ import qx;
/*  17:    */ import ur;
/*  18:    */ import yc;
/*  19:    */ 
/*  20:    */ public class TileRegulator
/*  21:    */   extends TileMachine
/*  22:    */   implements ITubeConnectable, la, ISidedInventory
/*  23:    */ {
/*  24:    */   public TileRegulator()
/*  25:    */   {
/*  26: 19 */     this.contents = new ur[27];
/*  27:    */   }
/*  28:    */   
/*  29:    */   void regenFilterMap()
/*  30:    */   {
/*  31: 25 */     this.inputMap = MachineLib.makeFilterMap(this.contents, 0, 9);
/*  32: 26 */     this.outputMap = MachineLib.makeFilterMap(this.contents, 18, 9);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public int getTubeConnectableSides()
/*  36:    */   {
/*  37: 32 */     return 3 << (this.Rotation & 0xFFFFFFFE);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getTubeConClass()
/*  41:    */   {
/*  42: 36 */     return 0;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public boolean canRouteItems()
/*  46:    */   {
/*  47: 40 */     return false;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  51:    */   {
/*  52: 44 */     if ((side == this.Rotation) && (state == 2))
/*  53:    */     {
/*  54: 45 */       this.buffer.addBounce(ti);
/*  55: 46 */       this.Active = true;
/*  56: 47 */       updateBlock();
/*  57: 48 */       scheduleTick(5);
/*  58: 49 */       return true;
/*  59:    */     }
/*  60: 50 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1))
/*  61:    */     {
/*  62: 51 */       int ic = inCount(ti.item);
/*  63: 52 */       if (ic == 0) {
/*  64: 52 */         return false;
/*  65:    */       }
/*  66: 54 */       boolean tr = true;
/*  67: 55 */       ur ist = ti.item;
/*  68: 56 */       if (ic < ist.a)
/*  69:    */       {
/*  70: 57 */         tr = false;
/*  71: 58 */         ist = ist.a(ic);
/*  72:    */       }
/*  73: 60 */       if (MachineLib.addToInventoryCore(this, ist, 9, 9, true))
/*  74:    */       {
/*  75: 61 */         d();
/*  76: 62 */         scheduleTick(2);
/*  77: 63 */         dirtyBlock();
/*  78: 64 */         return tr;
/*  79:    */       }
/*  80: 66 */       dirtyBlock();
/*  81: 67 */       return false;
/*  82:    */     }
/*  83: 69 */     return false;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  87:    */   {
/*  88: 73 */     if ((side == this.Rotation) && (state == 2)) {
/*  89: 74 */       return true;
/*  90:    */     }
/*  91: 75 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1))
/*  92:    */     {
/*  93: 76 */       if (inCount(ti.item) == 0) {
/*  94: 76 */         return false;
/*  95:    */       }
/*  96: 77 */       return MachineLib.addToInventoryCore(this, ti.item, 9, 9, false);
/*  97:    */     }
/*  98: 80 */     return false;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public int tubeWeight(int side, int state)
/* 102:    */   {
/* 103: 84 */     if ((side == this.Rotation) && (state == 2)) {
/* 104: 85 */       return this.buffer.size();
/* 105:    */     }
/* 106: 86 */     return 0;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public int getStartInventorySide(ForgeDirection side)
/* 110:    */   {
/* 111: 93 */     return 9;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 115:    */   {
/* 116: 98 */     int side = fd.ordinal();
/* 117: 99 */     if ((side == this.Rotation) || (side == (this.Rotation ^ 0x1))) {
/* 118:100 */       return 0;
/* 119:    */     }
/* 120:101 */     return 9;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void drainBuffer()
/* 124:    */   {
/* 125:107 */     while (!this.buffer.isEmpty())
/* 126:    */     {
/* 127:108 */       TubeItem ti = this.buffer.getLast();
/* 128:109 */       if (!handleItem(ti))
/* 129:    */       {
/* 130:110 */         this.buffer.plugged = true;
/* 131:111 */         return;
/* 132:    */       }
/* 133:113 */       this.buffer.pop();
/* 134:114 */       if (this.buffer.plugged) {
/* 135:115 */         return;
/* 136:    */       }
/* 137:    */     }
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void g()
/* 141:    */   {
/* 142:121 */     super.g();
/* 143:122 */     if (CoreLib.isClient(this.k)) {
/* 144:122 */       return;
/* 145:    */     }
/* 146:123 */     if (!isTickScheduled()) {
/* 147:124 */       scheduleTick(10);
/* 148:    */     }
/* 149:    */   }
/* 150:    */   
/* 151:    */   public boolean isPoweringTo(int side)
/* 152:    */   {
/* 153:129 */     if (side == (this.Rotation ^ 0x1)) {
/* 154:129 */       return false;
/* 155:    */     }
/* 156:130 */     return this.Powered;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public boolean onBlockActivated(qx player)
/* 160:    */   {
/* 161:136 */     if (player.ah()) {
/* 162:136 */       return false;
/* 163:    */     }
/* 164:137 */     if (CoreLib.isClient(this.k)) {
/* 165:138 */       return true;
/* 166:    */     }
/* 167:139 */     player.openGui(RedPowerMachine.instance, 9, this.k, this.l, this.m, this.n);
/* 168:    */     
/* 169:141 */     return true;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public int getExtendedID()
/* 173:    */   {
/* 174:145 */     return 10;
/* 175:    */   }
/* 176:    */   
/* 177:    */   public void onBlockRemoval()
/* 178:    */   {
/* 179:149 */     this.buffer.onRemove(this);
/* 180:151 */     for (int i = 0; i < 27; i++)
/* 181:    */     {
/* 182:152 */       ur ist = this.contents[i];
/* 183:153 */       if ((ist != null) && (ist.a > 0)) {
/* 184:154 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 185:    */       }
/* 186:    */     }
/* 187:    */   }
/* 188:    */   
/* 189:    */   private int[] scanInput()
/* 190:    */   {
/* 191:161 */     if (this.inputMap == null) {
/* 192:161 */       regenFilterMap();
/* 193:    */     }
/* 194:162 */     if (this.inputMap.size() == 0) {
/* 195:162 */       return null;
/* 196:    */     }
/* 197:164 */     int[] mc = MachineLib.genMatchCounts(this.inputMap);
/* 198:165 */     MachineLib.decMatchCounts(this.inputMap, mc, this, 9, 9);
/* 199:166 */     return mc;
/* 200:    */   }
/* 201:    */   
/* 202:    */   private int inCount(ur ist)
/* 203:    */   {
/* 204:170 */     if (this.inputMap == null) {
/* 205:170 */       regenFilterMap();
/* 206:    */     }
/* 207:171 */     if (this.inputMap.size() == 0) {
/* 208:171 */       return 0;
/* 209:    */     }
/* 210:172 */     if (!this.inputMap.containsKey(ist)) {
/* 211:172 */       return 0;
/* 212:    */     }
/* 213:173 */     int[] mc = MachineLib.genMatchCounts(this.inputMap);
/* 214:174 */     MachineLib.decMatchCounts(this.inputMap, mc, this, 9, 9);
/* 215:175 */     return MachineLib.decMatchCount(this.inputMap, mc, ist);
/* 216:    */   }
/* 217:    */   
/* 218:    */   private int[] scanOutput()
/* 219:    */   {
/* 220:179 */     WorldCoord wc = new WorldCoord(this);
/* 221:180 */     wc.step(this.Rotation);
/* 222:181 */     la inv = MachineLib.getInventory(this.k, wc);
/* 223:182 */     if (inv == null) {
/* 224:182 */       return null;
/* 225:    */     }
/* 226:183 */     int start = 0;
/* 227:184 */     int len = inv.k_();
/* 228:185 */     if ((inv instanceof ISidedInventory))
/* 229:    */     {
/* 230:186 */       ISidedInventory isi = (ISidedInventory)inv;
/* 231:187 */       ForgeDirection r1 = ForgeDirection.getOrientation((this.Rotation ^ 0x1) & 0xFF);
/* 232:188 */       start = isi.getStartInventorySide(r1);
/* 233:189 */       len = isi.getSizeInventorySide(r1);
/* 234:    */     }
/* 235:192 */     if (this.outputMap == null) {
/* 236:192 */       regenFilterMap();
/* 237:    */     }
/* 238:193 */     if (this.outputMap.size() == 0) {
/* 239:193 */       return null;
/* 240:    */     }
/* 241:195 */     int[] mc = MachineLib.genMatchCounts(this.outputMap);
/* 242:196 */     MachineLib.decMatchCounts(this.outputMap, mc, inv, start, len);
/* 243:197 */     return mc;
/* 244:    */   }
/* 245:    */   
/* 246:    */   private void handleTransfer(int[] omc)
/* 247:    */   {
/* 248:201 */     if ((this.mode == 0) || (omc == null))
/* 249:    */     {
/* 250:202 */       for (int i = 0; i < 9; i++)
/* 251:    */       {
/* 252:203 */         ur ist = this.contents[(9 + i)];
/* 253:204 */         if ((ist != null) && (ist.a != 0))
/* 254:    */         {
/* 255:205 */           this.buffer.addNewColor(ist, this.color);
/* 256:206 */           this.contents[(9 + i)] = null;
/* 257:    */         }
/* 258:    */       }
/* 259:    */     }
/* 260:    */     else
/* 261:    */     {
/* 262:209 */       boolean ch = false;
/* 263:210 */       for (int i = 0; i < 9; i++) {
/* 264:210 */         while (omc[i] > 0)
/* 265:    */         {
/* 266:211 */           ur ist = this.contents[(18 + i)].l();
/* 267:212 */           int ss = Math.min(ist.a, omc[i]);
/* 268:213 */           omc[i] -= ss;
/* 269:214 */           ist.a = ss;
/* 270:215 */           ur is2 = MachineLib.collectOneStack(this, 9, 9, ist);
/* 271:217 */           if (is2 != null)
/* 272:    */           {
/* 273:218 */             this.buffer.addNewColor(is2, this.color);
/* 274:219 */             ch = true;
/* 275:    */           }
/* 276:    */         }
/* 277:    */       }
/* 278:222 */       if (!ch) {
/* 279:222 */         return;
/* 280:    */       }
/* 281:    */     }
/* 282:224 */     d();
/* 283:225 */     this.Powered = true;this.Active = true;
/* 284:226 */     updateBlockChange();
/* 285:227 */     drainBuffer();
/* 286:228 */     if (!this.buffer.isEmpty()) {
/* 287:228 */       scheduleTick(10);
/* 288:    */     } else {
/* 289:229 */       scheduleTick(5);
/* 290:    */     }
/* 291:    */   }
/* 292:    */   
/* 293:    */   public void onTileTick()
/* 294:    */   {
/* 295:234 */     if (CoreLib.isClient(this.k)) {
/* 296:235 */       return;
/* 297:    */     }
/* 298:236 */     if (this.Active)
/* 299:    */     {
/* 300:237 */       if (!this.buffer.isEmpty())
/* 301:    */       {
/* 302:238 */         this.Powered = true;
/* 303:239 */         drainBuffer();
/* 304:240 */         updateBlockChange();
/* 305:242 */         if (!this.buffer.isEmpty()) {
/* 306:242 */           scheduleTick(10);
/* 307:    */         }
/* 308:243 */         return;
/* 309:    */       }
/* 310:245 */       this.Active = false;
/* 311:246 */       updateBlock();
/* 312:    */     }
/* 313:248 */     if (this.Powered)
/* 314:    */     {
/* 315:249 */       int[] omc = scanOutput();
/* 316:250 */       if (omc == null)
/* 317:    */       {
/* 318:251 */         this.Powered = false;
/* 319:252 */         updateBlockChange();
/* 320:253 */         return;
/* 321:    */       }
/* 322:255 */       if (MachineLib.isMatchEmpty(omc)) {
/* 323:255 */         return;
/* 324:    */       }
/* 325:257 */       int[] imc = scanInput();
/* 326:258 */       if ((imc == null) || (!MachineLib.isMatchEmpty(imc)))
/* 327:    */       {
/* 328:259 */         this.Powered = false;
/* 329:260 */         updateBlockChange();
/* 330:261 */         return;
/* 331:    */       }
/* 332:263 */       handleTransfer(omc);
/* 333:264 */       return;
/* 334:    */     }
/* 335:266 */     int[] omc = scanOutput();
/* 336:267 */     if ((omc != null) && (MachineLib.isMatchEmpty(omc)))
/* 337:    */     {
/* 338:268 */       this.Powered = true;
/* 339:269 */       updateBlockChange();
/* 340:270 */       return;
/* 341:    */     }
/* 342:272 */     int[] imc = scanInput();
/* 343:273 */     if ((imc != null) && (MachineLib.isMatchEmpty(imc)))
/* 344:    */     {
/* 345:274 */       handleTransfer(omc);
/* 346:275 */       return;
/* 347:    */     }
/* 348:277 */     if ((omc != null) && (this.mode == 1))
/* 349:    */     {
/* 350:278 */       handleTransfer(omc);
/* 351:279 */       return;
/* 352:    */     }
/* 353:    */   }
/* 354:    */   
/* 355:    */   public int k_()
/* 356:    */   {
/* 357:286 */     return 27;
/* 358:    */   }
/* 359:    */   
/* 360:    */   public ur a(int i)
/* 361:    */   {
/* 362:290 */     return this.contents[i];
/* 363:    */   }
/* 364:    */   
/* 365:    */   public ur a(int i, int j)
/* 366:    */   {
/* 367:295 */     if (this.contents[i] == null) {
/* 368:295 */       return null;
/* 369:    */     }
/* 370:297 */     if (this.contents[i].a <= j)
/* 371:    */     {
/* 372:298 */       ur tr = this.contents[i];
/* 373:299 */       this.contents[i] = null;
/* 374:300 */       d();
/* 375:301 */       return tr;
/* 376:    */     }
/* 377:303 */     ur tr = this.contents[i].a(j);
/* 378:304 */     if (this.contents[i].a == 0) {
/* 379:305 */       this.contents[i] = null;
/* 380:    */     }
/* 381:306 */     d();
/* 382:307 */     return tr;
/* 383:    */   }
/* 384:    */   
/* 385:    */   public ur a_(int i)
/* 386:    */   {
/* 387:311 */     if (this.contents[i] == null) {
/* 388:311 */       return null;
/* 389:    */     }
/* 390:312 */     ur ist = this.contents[i];
/* 391:313 */     this.contents[i] = null;
/* 392:314 */     return ist;
/* 393:    */   }
/* 394:    */   
/* 395:    */   public void a(int i, ur ist)
/* 396:    */   {
/* 397:318 */     this.contents[i] = ist;
/* 398:319 */     if ((ist != null) && (ist.a > c())) {
/* 399:320 */       ist.a = c();
/* 400:    */     }
/* 401:321 */     d();
/* 402:    */   }
/* 403:    */   
/* 404:    */   public String b()
/* 405:    */   {
/* 406:325 */     return "Regulator";
/* 407:    */   }
/* 408:    */   
/* 409:    */   public int c()
/* 410:    */   {
/* 411:329 */     return 64;
/* 412:    */   }
/* 413:    */   
/* 414:    */   public boolean a_(qx player)
/* 415:    */   {
/* 416:333 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 417:334 */       return false;
/* 418:    */     }
/* 419:335 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 420:    */   }
/* 421:    */   
/* 422:    */   public void d()
/* 423:    */   {
/* 424:340 */     this.inputMap = null;
/* 425:341 */     this.outputMap = null;
/* 426:342 */     super.d();
/* 427:    */   }
/* 428:    */   
/* 429:    */   public void f() {}
/* 430:    */   
/* 431:    */   public void l_() {}
/* 432:    */   
/* 433:    */   public void a(bq tag)
/* 434:    */   {
/* 435:352 */     super.a(tag);
/* 436:    */     
/* 437:354 */     by items = tag.m("Items");
/* 438:355 */     this.contents = new ur[k_()];
/* 439:356 */     for (int i = 0; i < items.c(); i++)
/* 440:    */     {
/* 441:357 */       bq item = (bq)items.b(i);
/* 442:    */       
/* 443:359 */       int j = item.c("Slot") & 0xFF;
/* 444:360 */       if ((j >= 0) && (j < this.contents.length)) {
/* 445:361 */         this.contents[j] = ur.a(item);
/* 446:    */       }
/* 447:    */     }
/* 448:365 */     this.buffer.readFromNBT(tag);
/* 449:366 */     this.mode = tag.c("mode");
/* 450:367 */     this.color = tag.c("col");
/* 451:    */   }
/* 452:    */   
/* 453:    */   public void b(bq tag)
/* 454:    */   {
/* 455:371 */     super.b(tag);
/* 456:    */     
/* 457:373 */     by items = new by();
/* 458:374 */     for (int i = 0; i < this.contents.length; i++) {
/* 459:375 */       if (this.contents[i] != null)
/* 460:    */       {
/* 461:376 */         bq item = new bq();
/* 462:377 */         item.a("Slot", (byte)i);
/* 463:378 */         this.contents[i].b(item);
/* 464:379 */         items.a(item);
/* 465:    */       }
/* 466:    */     }
/* 467:382 */     tag.a("Items", items);
/* 468:383 */     this.buffer.writeToNBT(tag);
/* 469:384 */     tag.a("mode", this.mode);
/* 470:385 */     tag.a("col", (byte)this.color);
/* 471:    */   }
/* 472:    */   
/* 473:388 */   TubeBuffer buffer = new TubeBuffer();
/* 474:389 */   public byte mode = 0;
/* 475:    */   protected ur[] contents;
/* 476:391 */   protected MachineLib.FilterMap inputMap = null;
/* 477:391 */   protected MachineLib.FilterMap outputMap = null;
/* 478:392 */   public int color = 0;
/* 479:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileRegulator
 * JD-Core Version:    0.7.0.1
 */