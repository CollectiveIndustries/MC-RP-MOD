/*   1:    */ package com.eloraam.redpower.base;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerBase;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*   8:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*   9:    */ import java.util.List;
/*  10:    */ import la;
/*  11:    */ import qw;
/*  12:    */ import qx;
/*  13:    */ import rq;
/*  14:    */ import ry;
/*  15:    */ import sp;
/*  16:    */ import sr;
/*  17:    */ import up;
/*  18:    */ import ur;
/*  19:    */ import wn;
/*  20:    */ 
/*  21:    */ public class ContainerAdvBench
/*  22:    */   extends rq
/*  23:    */   implements IHandleGuiEvent
/*  24:    */ {
/*  25:    */   SlotCraftRefill slotCraft;
/*  26:    */   private TileAdvBench tileAdvBench;
/*  27:    */   public InventorySubCraft craftMatrix;
/*  28:    */   public la craftResult;
/*  29:    */   public ry fakeInv;
/*  30:    */   public int satisfyMask;
/*  31:    */   
/*  32:    */   public static class SlotPlan
/*  33:    */     extends sr
/*  34:    */   {
/*  35:    */     public SlotPlan(la inv, int i, int j, int k)
/*  36:    */     {
/*  37: 25 */       super(i, j, k);
/*  38:    */     }
/*  39:    */     
/*  40:    */     public boolean a(ur ist)
/*  41:    */     {
/*  42: 30 */       return (ist.c == RedPowerBase.itemPlanBlank.cj) || (ist.c == RedPowerBase.itemPlanFull.cj);
/*  43:    */     }
/*  44:    */     
/*  45:    */     public int a()
/*  46:    */     {
/*  47: 38 */       return 1;
/*  48:    */     }
/*  49:    */   }
/*  50:    */   
/*  51:    */   public class InventorySubUpdate
/*  52:    */     implements la
/*  53:    */   {
/*  54:    */     int size;
/*  55:    */     int start;
/*  56:    */     la parent;
/*  57:    */     
/*  58:    */     public InventorySubUpdate(la par, int st, int sz)
/*  59:    */     {
/*  60: 47 */       this.parent = par;this.start = st;this.size = sz;
/*  61:    */     }
/*  62:    */     
/*  63:    */     public int k_()
/*  64:    */     {
/*  65: 50 */       return this.size;
/*  66:    */     }
/*  67:    */     
/*  68:    */     public ur a(int idx)
/*  69:    */     {
/*  70: 53 */       return this.parent.a(idx + this.start);
/*  71:    */     }
/*  72:    */     
/*  73:    */     public ur a(int idx, int num)
/*  74:    */     {
/*  75: 57 */       ur tr = this.parent.a(idx + this.start, num);
/*  76: 58 */       if (tr != null) {
/*  77: 58 */         ContainerAdvBench.this.a(this);
/*  78:    */       }
/*  79: 59 */       return tr;
/*  80:    */     }
/*  81:    */     
/*  82:    */     public ur a_(int idx)
/*  83:    */     {
/*  84: 63 */       return this.parent.a_(idx + this.start);
/*  85:    */     }
/*  86:    */     
/*  87:    */     public void a(int idx, ur ist)
/*  88:    */     {
/*  89: 67 */       this.parent.a(idx + this.start, ist);
/*  90: 68 */       ContainerAdvBench.this.a(this);
/*  91:    */     }
/*  92:    */     
/*  93:    */     public String b()
/*  94:    */     {
/*  95: 70 */       return this.parent.b();
/*  96:    */     }
/*  97:    */     
/*  98:    */     public int c()
/*  99:    */     {
/* 100: 73 */       return this.parent.c();
/* 101:    */     }
/* 102:    */     
/* 103:    */     public void d()
/* 104:    */     {
/* 105: 76 */       ContainerAdvBench.this.a(this);
/* 106: 77 */       this.parent.d();
/* 107:    */     }
/* 108:    */     
/* 109:    */     public boolean a_(qx var1)
/* 110:    */     {
/* 111: 79 */       return false;
/* 112:    */     }
/* 113:    */     
/* 114:    */     public void l_() {}
/* 115:    */     
/* 116:    */     public void f() {}
/* 117:    */   }
/* 118:    */   
/* 119:    */   public static class ContainerNull
/* 120:    */     extends rq
/* 121:    */   {
/* 122:    */     public boolean a(qx var1)
/* 123:    */     {
/* 124: 88 */       return false;
/* 125:    */     }
/* 126:    */     
/* 127:    */     public void a(la inv) {}
/* 128:    */   }
/* 129:    */   
/* 130:    */   public ContainerAdvBench(qw inv, TileAdvBench td)
/* 131:    */   {
/* 132: 98 */     this.tileAdvBench = td;
/* 133:    */     
/* 134:100 */     this.craftMatrix = new InventorySubCraft(this, td);
/* 135:101 */     this.craftResult = new sp();
/* 136:103 */     for (int i = 0; i < 3; i++) {
/* 137:103 */       for (int j = 0; j < 3; j++) {
/* 138:104 */         a(new sr(this.craftMatrix, j + i * 3, 48 + j * 18, 18 + i * 18));
/* 139:    */       }
/* 140:    */     }
/* 141:106 */     a(new SlotPlan(new InventorySubUpdate(td, 9, 1), 0, 17, 36));
/* 142:    */     
/* 143:    */ 
/* 144:109 */     this.slotCraft = new SlotCraftRefill(inv.d, this.craftMatrix, this.craftResult, td, this, 0, 143, 36);
/* 145:    */     
/* 146:111 */     a(this.slotCraft);
/* 147:    */     
/* 148:113 */     la ingrid = new InventorySubUpdate(td, 10, 18);
/* 149:114 */     for (i = 0; i < 2; i++) {
/* 150:114 */       for (int j = 0; j < 9; j++) {
/* 151:115 */         a(new sr(ingrid, j + i * 9, 8 + j * 18, 90 + i * 18));
/* 152:    */       }
/* 153:    */     }
/* 154:117 */     for (i = 0; i < 3; i++) {
/* 155:117 */       for (int j = 0; j < 9; j++) {
/* 156:118 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
/* 157:    */       }
/* 158:    */     }
/* 159:120 */     for (i = 0; i < 9; i++) {
/* 160:121 */       a(new sr(inv, i, 8 + i * 18, 198));
/* 161:    */     }
/* 162:123 */     this.fakeInv = new ry(new ContainerNull(), 3, 3);
/* 163:124 */     a(this.craftMatrix);
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void a(int num, ur ist)
/* 167:    */   {
/* 168:129 */     super.a(num, ist);
/* 169:    */   }
/* 170:    */   
/* 171:    */   public static ur[] getShadowItems(ur ist)
/* 172:    */   {
/* 173:133 */     if (ist.d == null) {
/* 174:133 */       return null;
/* 175:    */     }
/* 176:134 */     by require = ist.d.m("requires");
/* 177:135 */     if (require == null) {
/* 178:135 */       return null;
/* 179:    */     }
/* 180:137 */     ur[] tr = new ur[9];
/* 181:139 */     for (int i = 0; i < require.c(); i++)
/* 182:    */     {
/* 183:140 */       bq item = (bq)require.b(i);
/* 184:141 */       ur is2 = ur.a(item);
/* 185:142 */       int sl = item.c("Slot");
/* 186:143 */       if ((sl >= 0) && (sl < 9)) {
/* 187:144 */         tr[sl] = is2;
/* 188:    */       }
/* 189:    */     }
/* 190:146 */     return tr;
/* 191:    */   }
/* 192:    */   
/* 193:    */   public boolean a(qx player)
/* 194:    */   {
/* 195:150 */     return this.tileAdvBench.a_(player);
/* 196:    */   }
/* 197:    */   
/* 198:    */   public ur[] getPlanItems()
/* 199:    */   {
/* 200:154 */     ur plan = this.tileAdvBench.a(9);
/* 201:155 */     if (plan == null) {
/* 202:155 */       return null;
/* 203:    */     }
/* 204:156 */     return getShadowItems(plan);
/* 205:    */   }
/* 206:    */   
/* 207:    */   public int getSatisfyMask()
/* 208:    */   {
/* 209:160 */     ur plan = this.tileAdvBench.a(9);
/* 210:161 */     ur[] items = null;
/* 211:162 */     if (plan != null) {
/* 212:162 */       items = getShadowItems(plan);
/* 213:    */     }
/* 214:163 */     int bits = 0;
/* 215:165 */     for (int i = 0; i < 9; i++)
/* 216:    */     {
/* 217:166 */       ur st = this.tileAdvBench.a(i);
/* 218:167 */       if (st != null) {
/* 219:167 */         bits |= 1 << i;
/* 220:168 */       } else if ((items == null) || (items[i] == null)) {
/* 221:169 */         bits |= 1 << i;
/* 222:    */       }
/* 223:    */     }
/* 224:172 */     if (bits == 511) {
/* 225:172 */       return 511;
/* 226:    */     }
/* 227:174 */     for (int i = 0; i < 18; i++)
/* 228:    */     {
/* 229:175 */       ur test = this.tileAdvBench.a(10 + i);
/* 230:176 */       if ((test != null) && (test.a != 0))
/* 231:    */       {
/* 232:178 */         int sc = test.a;
/* 233:179 */         for (int j = 0; j < 9; j++) {
/* 234:180 */           if ((bits & 1 << j) <= 0)
/* 235:    */           {
/* 236:182 */             ur st = this.tileAdvBench.a(j);
/* 237:183 */             if (st == null)
/* 238:    */             {
/* 239:184 */               st = items[j];
/* 240:185 */               if (st != null) {
/* 241:187 */                 if (CoreLib.matchItemStackOre(st, test))
/* 242:    */                 {
/* 243:191 */                   bits |= 1 << j;
/* 244:192 */                   sc--;
/* 245:193 */                   if (sc == 0) {
/* 246:    */                     break;
/* 247:    */                   }
/* 248:    */                 }
/* 249:    */               }
/* 250:    */             }
/* 251:    */           }
/* 252:    */         }
/* 253:    */       }
/* 254:    */     }
/* 255:196 */     return bits;
/* 256:    */   }
/* 257:    */   
/* 258:    */   private int findMatch(ur a)
/* 259:    */   {
/* 260:200 */     for (int i = 0; i < 18; i++)
/* 261:    */     {
/* 262:201 */       ur test = this.tileAdvBench.a(10 + i);
/* 263:202 */       if ((test != null) && (test.a != 0) && 
/* 264:203 */         (CoreLib.matchItemStackOre(a, test))) {
/* 265:205 */         return 10 + i;
/* 266:    */       }
/* 267:    */     }
/* 268:207 */     return -1;
/* 269:    */   }
/* 270:    */   
/* 271:    */   public void a(la iinventory)
/* 272:    */   {
/* 273:211 */     ur plan = this.tileAdvBench.a(9);
/* 274:212 */     ur[] items = null;
/* 275:213 */     if (plan != null) {
/* 276:213 */       items = getShadowItems(plan);
/* 277:    */     }
/* 278:215 */     for (int i = 0; i < 9; i++)
/* 279:    */     {
/* 280:216 */       ur tos = this.tileAdvBench.a(i);
/* 281:217 */       if ((tos == null) && (items != null) && (items[i] != null))
/* 282:    */       {
/* 283:218 */         int j = findMatch(items[i]);
/* 284:219 */         if (j > 0) {
/* 285:220 */           tos = this.tileAdvBench.a(j);
/* 286:    */         }
/* 287:    */       }
/* 288:224 */       this.fakeInv.a(i, tos);
/* 289:    */     }
/* 290:227 */     this.satisfyMask = getSatisfyMask();
/* 291:228 */     if (this.satisfyMask == 511) {
/* 292:234 */       this.craftResult.a(0, wn.a().a(this.fakeInv, this.tileAdvBench.k));
/* 293:    */     } else {
/* 294:239 */       this.craftResult.a(0, null);
/* 295:    */     }
/* 296:    */   }
/* 297:    */   
/* 298:    */   public ur b(qx player, int i)
/* 299:    */   {
/* 300:246 */     ur itemstack = null;
/* 301:247 */     sr slot = (sr)this.c.get(i);
/* 302:248 */     if ((slot != null) && (slot.d()))
/* 303:    */     {
/* 304:249 */       ur itemstack1 = slot.c();
/* 305:250 */       itemstack = itemstack1.l();
/* 306:251 */       if (i == 10)
/* 307:    */       {
/* 308:252 */         mergeCrafting(player, slot, 29, 65);
/* 309:253 */         return null;
/* 310:    */       }
/* 311:254 */       if (i < 9)
/* 312:    */       {
/* 313:255 */         if (!a(itemstack1, 11, 29, false)) {
/* 314:256 */           return null;
/* 315:    */         }
/* 316:    */       }
/* 317:257 */       else if (i < 29)
/* 318:    */       {
/* 319:258 */         if (!a(itemstack1, 29, 65, true)) {
/* 320:259 */           return null;
/* 321:    */         }
/* 322:    */       }
/* 323:261 */       else if (!a(itemstack1, 11, 29, false)) {
/* 324:262 */         return null;
/* 325:    */       }
/* 326:264 */       if (itemstack1.a == 0) {
/* 327:265 */         slot.c(null);
/* 328:    */       } else {
/* 329:267 */         slot.e();
/* 330:    */       }
/* 331:269 */       if (itemstack1.a != itemstack.a) {
/* 332:270 */         slot.a(player, itemstack1);
/* 333:    */       } else {
/* 334:272 */         return null;
/* 335:    */       }
/* 336:    */     }
/* 337:275 */     return itemstack;
/* 338:    */   }
/* 339:    */   
/* 340:    */   protected boolean canFit(ur ist, int st, int ed)
/* 341:    */   {
/* 342:279 */     int ms = 0;
/* 343:281 */     for (int i = st; i < ed; i++)
/* 344:    */     {
/* 345:282 */       sr slot = (sr)this.c.get(i);
/* 346:283 */       ur is2 = slot.c();
/* 347:284 */       if (is2 == null) {
/* 348:284 */         return true;
/* 349:    */       }
/* 350:286 */       if (CoreLib.compareItemStack(is2, ist) == 0)
/* 351:    */       {
/* 352:288 */         ms += is2.d() - is2.a;
/* 353:289 */         if (ms >= ist.a) {
/* 354:289 */           return true;
/* 355:    */         }
/* 356:    */       }
/* 357:    */     }
/* 358:291 */     return false;
/* 359:    */   }
/* 360:    */   
/* 361:    */   protected void fitItem(ur ist, int st, int ed)
/* 362:    */   {
/* 363:295 */     if (ist.e()) {
/* 364:296 */       for (int i = st; i < ed; i++)
/* 365:    */       {
/* 366:297 */         sr slot = (sr)this.c.get(i);
/* 367:298 */         ur is2 = slot.c();
/* 368:299 */         if (is2 != null) {
/* 369:301 */           if (CoreLib.compareItemStack(is2, ist) == 0)
/* 370:    */           {
/* 371:303 */             int n = Math.min(ist.a, ist.d() - is2.a);
/* 372:305 */             if (n != 0)
/* 373:    */             {
/* 374:307 */               ist.a -= n;
/* 375:308 */               is2.a += n;
/* 376:309 */               slot.e();
/* 377:310 */               if (ist.a == 0) {
/* 378:310 */                 return;
/* 379:    */               }
/* 380:    */             }
/* 381:    */           }
/* 382:    */         }
/* 383:    */       }
/* 384:    */     }
/* 385:313 */     for (int i = st; i < ed; i++)
/* 386:    */     {
/* 387:314 */       sr slot = (sr)this.c.get(i);
/* 388:315 */       ur is2 = slot.c();
/* 389:316 */       if (is2 == null)
/* 390:    */       {
/* 391:318 */         slot.c(ist);
/* 392:319 */         slot.e();
/* 393:320 */         return;
/* 394:    */       }
/* 395:    */     }
/* 396:    */   }
/* 397:    */   
/* 398:    */   protected void mergeCrafting(qx player, sr cslot, int st, int ed)
/* 399:    */   {
/* 400:326 */     int cc = 0;
/* 401:    */     
/* 402:328 */     ur ist = cslot.c();
/* 403:329 */     if ((ist == null) || (ist.a == 0)) {
/* 404:329 */       return;
/* 405:    */     }
/* 406:330 */     ur craftas = ist.l();
/* 407:331 */     int mss = craftas.d();
/* 408:332 */     if (mss == 1) {
/* 409:332 */       mss = 16;
/* 410:    */     }
/* 411:    */     do
/* 412:    */     {
/* 413:335 */       if (!canFit(ist, st, ed)) {
/* 414:335 */         return;
/* 415:    */       }
/* 416:336 */       cc += ist.a;
/* 417:337 */       fitItem(ist, st, ed);
/* 418:338 */       cslot.a(player, ist);
/* 419:339 */       if (cc >= mss) {
/* 420:339 */         return;
/* 421:    */       }
/* 422:341 */       if (this.slotCraft.isLastUse()) {
/* 423:342 */         return;
/* 424:    */       }
/* 425:344 */       ist = cslot.c();
/* 426:345 */       if ((ist == null) || (ist.a == 0)) {
/* 427:345 */         return;
/* 428:    */       }
/* 429:346 */     } while (CoreLib.compareItemStack(ist, craftas) == 0);
/* 430:    */   }
/* 431:    */   
/* 432:    */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 433:    */   {
/* 434:352 */     if ((this.tileAdvBench.k == null) || (CoreLib.isClient(this.tileAdvBench.k))) {
/* 435:354 */       return;
/* 436:    */     }
/* 437:355 */     if (packet.eventId != 1) {
/* 438:355 */       return;
/* 439:    */     }
/* 440:357 */     ur blank = this.tileAdvBench.a(9);
/* 441:358 */     if ((blank == null) || (blank.c != RedPowerBase.itemPlanBlank.cj)) {
/* 442:359 */       return;
/* 443:    */     }
/* 444:361 */     ur plan = new ur(RedPowerBase.itemPlanFull);
/* 445:    */     
/* 446:363 */     plan.d = new bq();
/* 447:    */     
/* 448:365 */     bq result = new bq();
/* 449:366 */     this.craftResult.a(0).b(result);
/* 450:367 */     plan.d.a("result", result);
/* 451:    */     
/* 452:369 */     by requires = new by();
/* 453:370 */     for (int i = 0; i < 9; i++)
/* 454:    */     {
/* 455:371 */       ur is1 = this.craftMatrix.a(i);
/* 456:372 */       if (is1 != null)
/* 457:    */       {
/* 458:373 */         ur ist = CoreLib.copyStack(is1, 1);
/* 459:374 */         bq item = new bq();
/* 460:375 */         ist.b(item);
/* 461:376 */         item.a("Slot", (byte)i);
/* 462:377 */         requires.a(item);
/* 463:    */       }
/* 464:    */     }
/* 465:379 */     plan.d.a("requires", requires);
/* 466:    */     
/* 467:381 */     this.tileAdvBench.a(9, plan);
/* 468:    */   }
/* 469:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ContainerAdvBench
 * JD-Core Version:    0.7.0.1
 */