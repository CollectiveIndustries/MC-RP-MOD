/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.RedPowerBase;
/*   6:    */ import com.eloraam.redpower.RedPowerMachine;
/*   7:    */ import com.eloraam.redpower.base.BlockMicro;
/*   8:    */ import com.eloraam.redpower.core.BlockExtended;
/*   9:    */ import com.eloraam.redpower.core.BlockMultipart;
/*  10:    */ import com.eloraam.redpower.core.CoreLib;
/*  11:    */ import com.eloraam.redpower.core.CoreProxy;
/*  12:    */ import com.eloraam.redpower.core.IPaintable;
/*  13:    */ import com.eloraam.redpower.core.ITubeFlow;
/*  14:    */ import com.eloraam.redpower.core.MachineLib;
/*  15:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  16:    */ import com.eloraam.redpower.core.TileCovered;
/*  17:    */ import com.eloraam.redpower.core.TubeFlow;
/*  18:    */ import com.eloraam.redpower.core.TubeFlow.TubeScheduleContext;
/*  19:    */ import com.eloraam.redpower.core.TubeItem;
/*  20:    */ import com.eloraam.redpower.core.TubeLib;
/*  21:    */ import java.io.IOException;
/*  22:    */ import java.util.ArrayList;
/*  23:    */ import java.util.Iterator;
/*  24:    */ import java.util.LinkedList;
/*  25:    */ import qx;
/*  26:    */ import ur;
/*  27:    */ 
/*  28:    */ public class TileTube
/*  29:    */   extends TileCovered
/*  30:    */   implements ITubeFlow, IPaintable
/*  31:    */ {
/*  32:    */   public int getTubeConnectableSides()
/*  33:    */   {
/*  34: 25 */     int tr = 63;
/*  35: 26 */     for (int i = 0; i < 6; i++) {
/*  36: 27 */       if (((this.CoverSides & 1 << i) > 0) && 
/*  37: 28 */         (this.Covers[i] >> 8 < 3)) {
/*  38: 29 */         tr &= (1 << i ^ 0xFFFFFFFF);
/*  39:    */       }
/*  40:    */     }
/*  41: 32 */     return tr;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public int getTubeConClass()
/*  45:    */   {
/*  46: 36 */     return this.paintColor;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public boolean canRouteItems()
/*  50:    */   {
/*  51: 40 */     return true;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  55:    */   {
/*  56: 44 */     if (state != 0) {
/*  57: 44 */       return false;
/*  58:    */     }
/*  59: 45 */     if ((ti.color != 0) && (this.paintColor != 0) && (ti.color != this.paintColor)) {
/*  60: 46 */       return false;
/*  61:    */     }
/*  62: 47 */     ti.side = ((byte)side);
/*  63:    */     
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68:    */ 
/*  69: 54 */     this.flow.add(ti);
/*  70:    */     
/*  71: 56 */     this.hasChanged = true;
/*  72: 57 */     dirtyBlock();
/*  73: 58 */     return true;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  77:    */   {
/*  78: 62 */     if ((ti.color != 0) && (this.paintColor != 0) && (ti.color != this.paintColor)) {
/*  79: 63 */       return false;
/*  80:    */     }
/*  81: 64 */     if (state == 0) {
/*  82: 64 */       return true;
/*  83:    */     }
/*  84: 65 */     return false;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public int tubeWeight(int side, int state)
/*  88:    */   {
/*  89: 69 */     return 0;
/*  90:    */   }
/*  91:    */   
/*  92: 74 */   protected TubeFlow flow = new TubeFlow()
/*  93:    */   {
/*  94:    */     public any getParent()
/*  95:    */     {
/*  96: 76 */       return TileTube.this;
/*  97:    */     }
/*  98:    */     
/*  99:    */     public boolean schedule(TubeItem ti, TubeFlow.TubeScheduleContext tsc)
/* 100:    */     {
/* 101: 80 */       ti.scheduled = true;
/* 102: 81 */       ti.progress = 0;
/* 103: 82 */       int i = tsc.cons & (1 << ti.side ^ 0xFFFFFFFF);
/* 104: 83 */       if (i == 0) {
/* 105: 83 */         return true;
/* 106:    */       }
/* 107: 84 */       if (Integer.bitCount(i) == 1)
/* 108:    */       {
/* 109: 85 */         ti.side = ((byte)Integer.numberOfTrailingZeros(i));
/* 110: 86 */         return true;
/* 111:    */       }
/* 112: 89 */       if (CoreLib.isClient(TileTube.this.k)) {
/* 113: 89 */         return false;
/* 114:    */       }
/* 115: 91 */       if (ti.mode != 3) {
/* 116: 91 */         ti.mode = 1;
/* 117:    */       }
/* 118: 92 */       ti.side = ((byte)TubeLib.findRoute(tsc.world, tsc.wc, ti, i, ti.mode, TileTube.this.lastDir));
/* 119: 94 */       if (ti.side >= 0)
/* 120:    */       {
/* 121: 95 */         int m = i & ((2 << TileTube.this.lastDir) - 1 ^ 0xFFFFFFFF);
/* 122: 96 */         if (m == 0) {
/* 123: 96 */           m = i;
/* 124:    */         }
/* 125: 97 */         if (m == 0) {
/* 126: 97 */           TileTube.this.lastDir = 0;
/* 127:    */         } else {
/* 128: 98 */           TileTube.this.lastDir = ((byte)Integer.numberOfTrailingZeros(m));
/* 129:    */         }
/* 130:    */       }
/* 131:    */       else
/* 132:    */       {
/* 133:100 */         if ((ti.mode == 1) && (ti.priority > 0))
/* 134:    */         {
/* 135:101 */           ti.priority = 0;
/* 136:102 */           ti.side = ((byte)TubeLib.findRoute(tsc.world, tsc.wc, ti, tsc.cons, 1));
/* 137:104 */           if (ti.side >= 0) {
/* 138:105 */             return true;
/* 139:    */           }
/* 140:    */         }
/* 141:107 */         ti.side = ((byte)TubeLib.findRoute(tsc.world, tsc.wc, ti, tsc.cons, 2));
/* 142:109 */         if (ti.side >= 0)
/* 143:    */         {
/* 144:110 */           ti.mode = 2;
/* 145:111 */           return true;
/* 146:    */         }
/* 147:113 */         if (ti.mode == 3)
/* 148:    */         {
/* 149:114 */           ti.side = ((byte)TubeLib.findRoute(tsc.world, tsc.wc, ti, tsc.cons, 1));
/* 150:    */           
/* 151:116 */           ti.mode = 1;
/* 152:    */         }
/* 153:118 */         if (ti.side < 0)
/* 154:    */         {
/* 155:119 */           ti.side = TileTube.this.lastDir;
/* 156:    */           
/* 157:121 */           int m = i & ((2 << TileTube.this.lastDir) - 1 ^ 0xFFFFFFFF);
/* 158:122 */           if (m == 0) {
/* 159:122 */             m = i;
/* 160:    */           }
/* 161:123 */           if (m == 0) {
/* 162:123 */             TileTube.this.lastDir = 0;
/* 163:    */           } else {
/* 164:124 */             TileTube.this.lastDir = ((byte)Integer.numberOfTrailingZeros(m));
/* 165:    */           }
/* 166:    */         }
/* 167:    */       }
/* 168:127 */       return true;
/* 169:    */     }
/* 170:    */     
/* 171:    */     public boolean handleItem(TubeItem ti, TubeFlow.TubeScheduleContext tsc)
/* 172:    */     {
/* 173:131 */       return MachineLib.addToInventory(TileTube.this.k, ti.item, tsc.dest, (ti.side ^ 0x1) & 0x3F);
/* 174:    */     }
/* 175:    */   };
/* 176:    */   
/* 177:    */   public void addTubeItem(TubeItem ti)
/* 178:    */   {
/* 179:137 */     TubeItem tmp1_0 = ti;tmp1_0.side = ((byte)(tmp1_0.side ^ 0x1));
/* 180:138 */     this.flow.add(ti);
/* 181:    */     
/* 182:140 */     this.hasChanged = true;
/* 183:141 */     dirtyBlock();
/* 184:    */   }
/* 185:    */   
/* 186:    */   public TubeFlow getTubeFlow()
/* 187:    */   {
/* 188:145 */     return this.flow;
/* 189:    */   }
/* 190:    */   
/* 191:    */   public boolean tryPaint(int part, int side, int color)
/* 192:    */   {
/* 193:151 */     if (part == 29)
/* 194:    */     {
/* 195:152 */       if (this.paintColor == color) {
/* 196:152 */         return false;
/* 197:    */       }
/* 198:153 */       this.paintColor = ((byte)color);
/* 199:154 */       updateBlockChange();
/* 200:155 */       return true;
/* 201:    */     }
/* 202:157 */     return false;
/* 203:    */   }
/* 204:    */   
/* 205:    */   public boolean canUpdate()
/* 206:    */   {
/* 207:163 */     return true;
/* 208:    */   }
/* 209:    */   
/* 210:    */   public void g()
/* 211:    */   {
/* 212:167 */     if (this.flow.update()) {
/* 213:167 */       this.hasChanged = true;
/* 214:    */     }
/* 215:169 */     if (this.hasChanged)
/* 216:    */     {
/* 217:170 */       this.hasChanged = false;
/* 218:171 */       if (!CoreLib.isClient(this.k)) {
/* 219:172 */         sendItemUpdate();
/* 220:    */       }
/* 221:173 */       dirtyBlock();
/* 222:    */     }
/* 223:    */   }
/* 224:    */   
/* 225:    */   public int getBlockID()
/* 226:    */   {
/* 227:294 */     return RedPowerBase.blockMicro.cm;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public int getExtendedID()
/* 231:    */   {
/* 232:298 */     return 8;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public void onBlockNeighborChange(int l) {}
/* 236:    */   
/* 237:    */   public int getPartsMask()
/* 238:    */   {
/* 239:307 */     return this.CoverSides | 0x20000000;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public int getSolidPartsMask()
/* 243:    */   {
/* 244:311 */     return this.CoverSides | 0x20000000;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public boolean blockEmpty()
/* 248:    */   {
/* 249:315 */     return false;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public void onHarvestPart(qx player, int part)
/* 253:    */   {
/* 254:319 */     if (part == 29)
/* 255:    */     {
/* 256:320 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(RedPowerBase.blockMicro.cm, 1, getExtendedID() << 8));
/* 257:    */       
/* 258:    */ 
/* 259:    */ 
/* 260:    */ 
/* 261:    */ 
/* 262:    */ 
/* 263:    */ 
/* 264:328 */       this.flow.onRemove();
/* 265:330 */       if (this.CoverSides > 0) {
/* 266:330 */         replaceWithCovers();
/* 267:    */       } else {
/* 268:331 */         deleteBlock();
/* 269:    */       }
/* 270:    */     }
/* 271:    */     else
/* 272:    */     {
/* 273:333 */       super.onHarvestPart(player, part);
/* 274:334 */       return;
/* 275:    */     }
/* 276:    */   }
/* 277:    */   
/* 278:    */   public void addHarvestContents(ArrayList ist)
/* 279:    */   {
/* 280:339 */     super.addHarvestContents(ist);
/* 281:    */     
/* 282:341 */     ist.add(new ur(RedPowerBase.blockMicro.cm, 1, getExtendedID() << 8));
/* 283:    */   }
/* 284:    */   
/* 285:    */   public float getPartStrength(qx player, int part)
/* 286:    */   {
/* 287:346 */     BlockExtended bl = RedPowerMachine.blockMachine;
/* 288:347 */     if (part == 29) {
/* 289:348 */       return player.getCurrentPlayerStrVsBlock(bl, 0) / (bl.getHardness() * 30.0F);
/* 290:    */     }
/* 291:351 */     return super.getPartStrength(player, part);
/* 292:    */   }
/* 293:    */   
/* 294:    */   public void setPartBounds(BlockMultipart bl, int part)
/* 295:    */   {
/* 296:355 */     if (part == 29) {
/* 297:356 */       bl.a(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/* 298:    */     } else {
/* 299:358 */       super.setPartBounds(bl, part);
/* 300:    */     }
/* 301:    */   }
/* 302:    */   
/* 303:    */   public void a(bq tag)
/* 304:    */   {
/* 305:365 */     super.a(tag);
/* 306:    */     
/* 307:    */ 
/* 308:    */ 
/* 309:    */ 
/* 310:    */ 
/* 311:    */ 
/* 312:    */ 
/* 313:    */ 
/* 314:    */ 
/* 315:375 */     this.flow.readFromNBT(tag);
/* 316:    */     
/* 317:377 */     this.lastDir = tag.c("lDir");
/* 318:378 */     this.paintColor = tag.c("pCol");
/* 319:    */   }
/* 320:    */   
/* 321:    */   public void b(bq tag)
/* 322:    */   {
/* 323:382 */     super.b(tag);
/* 324:    */     
/* 325:    */ 
/* 326:    */ 
/* 327:    */ 
/* 328:    */ 
/* 329:    */ 
/* 330:    */ 
/* 331:    */ 
/* 332:    */ 
/* 333:    */ 
/* 334:393 */     this.flow.writeToNBT(tag);
/* 335:    */     
/* 336:    */ 
/* 337:396 */     tag.a("lDir", this.lastDir);
/* 338:397 */     tag.a("pCol", this.paintColor);
/* 339:    */   }
/* 340:    */   
/* 341:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 342:    */     throws IOException
/* 343:    */   {
/* 344:402 */     if (pkt.subId == 10)
/* 345:    */     {
/* 346:403 */       this.flow.contents.clear();
/* 347:404 */       int cs = (int)pkt.getUVLC();
/* 348:405 */       for (int i = 0; i < cs; i++) {
/* 349:406 */         this.flow.contents.add(TubeItem.newFromPacket(pkt));
/* 350:    */       }
/* 351:    */     }
/* 352:    */     else
/* 353:    */     {
/* 354:409 */       super.readFromPacket(pkt);
/* 355:410 */       this.paintColor = ((byte)pkt.getByte());
/* 356:411 */       updateBlock();
/* 357:    */     }
/* 358:    */   }
/* 359:    */   
/* 360:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 361:    */   {
/* 362:416 */     super.writeToPacket(pkt);
/* 363:417 */     pkt.addByte(this.paintColor);
/* 364:    */   }
/* 365:    */   
/* 366:    */   protected void sendItemUpdate()
/* 367:    */   {
/* 368:433 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 369:434 */     pkt.subId = 10;
/* 370:435 */     pkt.xCoord = this.l;pkt.yCoord = this.m;
/* 371:436 */     pkt.zCoord = this.n;
/* 372:    */     
/* 373:438 */     int cs = this.flow.contents.size();
/* 374:439 */     if (cs > 6) {
/* 375:439 */       cs = 6;
/* 376:    */     }
/* 377:440 */     pkt.addUVLC(cs);
/* 378:    */     
/* 379:442 */     Iterator tii = this.flow.contents.iterator();
/* 380:443 */     for (int i = 0; i < cs; i++)
/* 381:    */     {
/* 382:444 */       TubeItem ti = (TubeItem)tii.next();
/* 383:445 */       ti.writeToPacket(pkt);
/* 384:    */     }
/* 385:447 */     pkt.encode();
/* 386:448 */     CoreProxy.sendPacketToPosition(this.k, pkt, this.l, this.n);
/* 387:    */   }
/* 388:    */   
/* 389:    */   public void handlePacket(Packet211TileDesc packet)
/* 390:    */   {
/* 391:    */     try
/* 392:    */     {
/* 393:453 */       readFromPacket(packet);
/* 394:    */     }
/* 395:    */     catch (IOException e) {}
/* 396:    */   }
/* 397:    */   
/* 398:458 */   public byte lastDir = 0;
/* 399:459 */   public byte paintColor = 0;
/* 400:460 */   private boolean hasChanged = false;
/* 401:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileTube
 * JD-Core Version:    0.7.0.1
 */