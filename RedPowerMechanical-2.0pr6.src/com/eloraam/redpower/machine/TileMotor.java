/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   7:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   8:    */ import com.eloraam.redpower.core.CoreLib;
/*   9:    */ import com.eloraam.redpower.core.FrameLib.FrameSolver;
/*  10:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  11:    */ import com.eloraam.redpower.core.IFrameLink;
/*  12:    */ import com.eloraam.redpower.core.IFrameSupport;
/*  13:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  14:    */ import com.eloraam.redpower.core.IRotatable;
/*  15:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  16:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  17:    */ import com.eloraam.redpower.core.TileExtended;
/*  18:    */ import com.eloraam.redpower.core.WorldCoord;
/*  19:    */ import ef;
/*  20:    */ import java.io.ByteArrayOutputStream;
/*  21:    */ import java.io.IOException;
/*  22:    */ import java.util.LinkedList;
/*  23:    */ import md;
/*  24:    */ import ur;
/*  25:    */ import yc;
/*  26:    */ import ym;
/*  27:    */ 
/*  28:    */ public class TileMotor
/*  29:    */   extends TileExtended
/*  30:    */   implements IHandlePackets, IBluePowerConnectable, IRotatable, IFrameLink, IFrameSupport
/*  31:    */ {
/*  32:    */   public int getConnectableMask()
/*  33:    */   {
/*  34: 29 */     return 0x3FFFFFFF ^ RedPowerLib.getConDirMask(this.Rotation >> 2 ^ 0x1);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public int getConnectClass(int side)
/*  38:    */   {
/*  39: 33 */     return 65;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public int getCornerPowerMode()
/*  43:    */   {
/*  44: 36 */     return 0;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public WorldCoord getFrameLinkset()
/*  48:    */   {
/*  49: 38 */     return null;
/*  50:    */   }
/*  51:    */   
/*  52: 42 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  53:    */   {
/*  54:    */     public any getParent()
/*  55:    */     {
/*  56: 44 */       return TileMotor.this;
/*  57:    */     }
/*  58:    */   };
/*  59:    */   
/*  60:    */   public BluePowerConductor getBlueConductor(int side)
/*  61:    */   {
/*  62: 49 */     return this.cond;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public int getPartMaxRotation(int part, boolean sec)
/*  66:    */   {
/*  67: 55 */     if (this.MovePos >= 0) {
/*  68: 55 */       return 0;
/*  69:    */     }
/*  70: 56 */     if (sec) {
/*  71: 56 */       return 5;
/*  72:    */     }
/*  73: 57 */     return 3;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public int getPartRotation(int part, boolean sec)
/*  77:    */   {
/*  78: 61 */     if (sec) {
/*  79: 61 */       return this.Rotation >> 2;
/*  80:    */     }
/*  81: 62 */     return this.Rotation & 0x3;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setPartRotation(int part, boolean sec, int rot)
/*  85:    */   {
/*  86: 66 */     if (this.MovePos >= 0) {
/*  87: 66 */       return;
/*  88:    */     }
/*  89: 67 */     if (sec) {
/*  90: 67 */       this.Rotation = (this.Rotation & 0x3 | rot << 2);
/*  91:    */     } else {
/*  92: 68 */       this.Rotation = (this.Rotation & 0xFFFFFFFC | rot & 0x3);
/*  93:    */     }
/*  94: 69 */     updateBlockChange();
/*  95:    */   }
/*  96:    */   
/*  97:    */   public boolean isFrameMoving()
/*  98:    */   {
/*  99: 75 */     return false;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public boolean canFrameConnectIn(int dir)
/* 103:    */   {
/* 104: 79 */     if (dir == (this.Rotation >> 2 ^ 0x1)) {
/* 105: 79 */       return false;
/* 106:    */     }
/* 107: 80 */     return true;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public boolean canFrameConnectOut(int dir)
/* 111:    */   {
/* 112: 84 */     if (dir == (this.Rotation >> 2 ^ 0x1)) {
/* 113: 84 */       return true;
/* 114:    */     }
/* 115: 85 */     return false;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public int getExtendedID()
/* 119:    */   {
/* 120: 91 */     return 7;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public int getBlockID()
/* 124:    */   {
/* 125: 95 */     return RedPowerMachine.blockMachine.cm;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void g()
/* 129:    */   {
/* 130: 99 */     super.g();
/* 131:101 */     if ((this.MovePos >= 0) && (this.MovePos < 16))
/* 132:    */     {
/* 133:101 */       this.MovePos += 1;dirtyBlock();
/* 134:    */     }
/* 135:102 */     if (CoreLib.isClient(this.k)) {
/* 136:102 */       return;
/* 137:    */     }
/* 138:104 */     if (this.MovePos >= 0) {
/* 139:105 */       this.cond.drawPower(100 + 10 * this.LinkSize);
/* 140:    */     }
/* 141:107 */     if (this.MovePos >= 16)
/* 142:    */     {
/* 143:108 */       dropFrame(true);
/* 144:109 */       this.MovePos = -1;
/* 145:110 */       this.Active = false;
/* 146:111 */       updateBlock();
/* 147:    */     }
/* 148:114 */     if (this.ConMask < 0)
/* 149:    */     {
/* 150:115 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 151:    */       
/* 152:117 */       this.cond.recache(this.ConMask, 0);
/* 153:    */     }
/* 154:119 */     this.cond.iterate();
/* 155:120 */     dirtyBlock();
/* 156:121 */     if (this.MovePos >= 0) {
/* 157:121 */       return;
/* 158:    */     }
/* 159:123 */     if (this.cond.getVoltage() < 60.0D)
/* 160:    */     {
/* 161:124 */       if ((this.Charged) && (this.cond.Flow == 0))
/* 162:    */       {
/* 163:125 */         this.Charged = false;
/* 164:126 */         updateBlock();
/* 165:    */       }
/* 166:128 */       return;
/* 167:    */     }
/* 168:129 */     if (!this.Charged)
/* 169:    */     {
/* 170:130 */       this.Charged = true;
/* 171:131 */       updateBlock();
/* 172:    */     }
/* 173:    */   }
/* 174:    */   
/* 175:    */   private int getDriveSide()
/* 176:    */   {
/* 177:    */     int n;
/* 178:138 */     switch (this.Rotation >> 2)
/* 179:    */     {
/* 180:    */     case 0: 
/* 181:139 */       n = 13604; break;
/* 182:    */     case 1: 
/* 183:140 */       n = 13349; break;
/* 184:    */     case 2: 
/* 185:141 */       n = 20800; break;
/* 186:    */     case 3: 
/* 187:142 */       n = 16720; break;
/* 188:    */     case 4: 
/* 189:143 */       n = 8496; break;
/* 190:    */     default: 
/* 191:144 */       n = 12576;
/* 192:    */     }
/* 193:146 */     n >>= (this.Rotation & 0x3) << 2;
/* 194:147 */     n &= 0x7;
/* 195:148 */     return n;
/* 196:    */   }
/* 197:    */   
/* 198:    */   void pickFrame()
/* 199:    */   {
/* 200:152 */     this.MoveDir = getDriveSide();
/* 201:    */     
/* 202:154 */     WorldCoord wc = new WorldCoord(this);
/* 203:155 */     FrameLib.FrameSolver fs = new FrameLib.FrameSolver(this.k, wc.coordStep(this.Rotation >> 2 ^ 0x1), wc, this.MoveDir);
/* 204:157 */     if (!fs.solveLimit(RedPowerMachine.FrameLinkSize)) {
/* 205:157 */       return;
/* 206:    */     }
/* 207:158 */     if (!fs.addMoved()) {
/* 208:158 */       return;
/* 209:    */     }
/* 210:160 */     this.LinkSize = fs.getFrameSet().size();
/* 211:161 */     this.MovePos = 0;
/* 212:162 */     this.Active = true;
/* 213:163 */     updateBlock();
/* 214:166 */     for (WorldCoord sp : fs.getClearSet()) {
/* 215:167 */       this.k.b(sp.x, sp.y, sp.z, 0);
/* 216:    */     }
/* 217:169 */     for (WorldCoord sp : fs.getFrameSet())
/* 218:    */     {
/* 219:170 */       int bid = this.k.a(sp.x, sp.y, sp.z);
/* 220:171 */       int md = this.k.h(sp.x, sp.y, sp.z);
/* 221:172 */       any te = this.k.q(sp.x, sp.y, sp.z);
/* 222:173 */       if (te != null) {
/* 223:174 */         this.k.r(sp.x, sp.y, sp.z);
/* 224:    */       }
/* 225:175 */       boolean ir = this.k.I;
/* 226:176 */       this.k.I = true;
/* 227:    */       
/* 228:178 */       this.k.a(sp.x, sp.y, sp.z, RedPowerMachine.blockFrame.cm, 1, false);
/* 229:    */       
/* 230:    */ 
/* 231:181 */       this.k.I = ir;
/* 232:182 */       TileFrameMoving tfm = (TileFrameMoving)CoreLib.getTileEntity(this.k, sp, TileFrameMoving.class);
/* 233:184 */       if (tfm != null) {
/* 234:185 */         tfm.setContents(bid, md, this.l, this.m, this.n, te);
/* 235:    */       }
/* 236:    */     }
/* 237:187 */     for (WorldCoord sp : fs.getFrameSet())
/* 238:    */     {
/* 239:188 */       this.k.i(sp.x, sp.y, sp.z);
/* 240:189 */       CoreLib.markBlockDirty(this.k, sp.x, sp.y, sp.z);
/* 241:    */       
/* 242:191 */       TileFrameMoving tfm = (TileFrameMoving)CoreLib.getTileEntity(this.k, sp, TileFrameMoving.class);
/* 243:193 */       if ((tfm != null) && 
/* 244:194 */         ((tfm.movingTileEntity instanceof IFrameSupport)))
/* 245:    */       {
/* 246:196 */         IFrameSupport ifs = (IFrameSupport)tfm.movingTileEntity;
/* 247:197 */         ifs.onFramePickup(tfm.getFrameBlockAccess());
/* 248:    */       }
/* 249:    */     }
/* 250:    */   }
/* 251:    */   
/* 252:    */   void dropFrame(boolean fw)
/* 253:    */   {
/* 254:202 */     WorldCoord wc = new WorldCoord(this);
/* 255:203 */     FrameLib.FrameSolver fs = new FrameLib.FrameSolver(this.k, wc.coordStep(this.Rotation >> 2 ^ 0x1), wc, -1);
/* 256:205 */     if (!fs.solve()) {
/* 257:205 */       return;
/* 258:    */     }
/* 259:206 */     this.LinkSize = 0;
/* 260:    */     
/* 261:208 */     fs.sort(this.MoveDir);
/* 262:211 */     for (WorldCoord sp : fs.getFrameSet())
/* 263:    */     {
/* 264:212 */       TileFrameMoving tfm = (TileFrameMoving)CoreLib.getTileEntity(this.k, sp, TileFrameMoving.class);
/* 265:214 */       if (tfm != null)
/* 266:    */       {
/* 267:216 */         tfm.pushEntities(this);
/* 268:    */         
/* 269:218 */         WorldCoord s2 = sp.copy();
/* 270:219 */         if (fw) {
/* 271:219 */           s2.step(this.MoveDir);
/* 272:    */         }
/* 273:220 */         if (tfm.movingBlockID != 0)
/* 274:    */         {
/* 275:221 */           boolean ir = this.k.I;
/* 276:222 */           this.k.I = true;
/* 277:223 */           this.k.a(s2.x, s2.y, s2.z, tfm.movingBlockID, tfm.movingBlockMeta, false);
/* 278:    */           
/* 279:    */ 
/* 280:    */ 
/* 281:227 */           this.k.I = ir;
/* 282:228 */           if (tfm.movingTileEntity != null)
/* 283:    */           {
/* 284:229 */             tfm.movingTileEntity.l = s2.x;
/* 285:230 */             tfm.movingTileEntity.m = s2.y;
/* 286:231 */             tfm.movingTileEntity.n = s2.z;
/* 287:232 */             tfm.movingTileEntity.s();
/* 288:233 */             this.k.a(s2.x, s2.y, s2.z, tfm.movingTileEntity);
/* 289:    */           }
/* 290:    */         }
/* 291:237 */         if (fw) {
/* 292:237 */           this.k.b(sp.x, sp.y, sp.z, 0);
/* 293:    */         }
/* 294:    */       }
/* 295:    */     }
/* 296:239 */     for (WorldCoord sp : fs.getFrameSet())
/* 297:    */     {
/* 298:240 */       IFrameSupport ifs = (IFrameSupport)CoreLib.getTileEntity(this.k, sp, IFrameSupport.class);
/* 299:242 */       if (ifs != null) {
/* 300:242 */         ifs.onFrameDrop();
/* 301:    */       }
/* 302:244 */       this.k.i(sp.x, sp.y, sp.z);
/* 303:245 */       CoreLib.markBlockDirty(this.k, sp.x, sp.y, sp.z);
/* 304:246 */       RedPowerLib.updateIndirectNeighbors(this.k, sp.x, sp.y, sp.z, this.k.a(sp.x, sp.y, sp.z));
/* 305:    */     }
/* 306:    */   }
/* 307:    */   
/* 308:    */   float getMoveScaled()
/* 309:    */   {
/* 310:253 */     return this.MovePos / 16.0F;
/* 311:    */   }
/* 312:    */   
/* 313:    */   public void onBlockRemoval()
/* 314:    */   {
/* 315:257 */     if (this.MovePos >= 0)
/* 316:    */     {
/* 317:258 */       this.Active = false;
/* 318:259 */       dropFrame(false);
/* 319:    */     }
/* 320:261 */     this.MovePos = -1;
/* 321:    */   }
/* 322:    */   
/* 323:    */   public void onBlockNeighborChange(int l)
/* 324:    */   {
/* 325:267 */     this.ConMask = -1;
/* 326:269 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, 16777215, 63))
/* 327:    */     {
/* 328:271 */       if (!this.Charged) {
/* 329:271 */         return;
/* 330:    */       }
/* 331:272 */       if (this.Powered) {
/* 332:272 */         return;
/* 333:    */       }
/* 334:273 */       if (this.MovePos >= 0) {
/* 335:273 */         return;
/* 336:    */       }
/* 337:274 */       this.Powered = true;
/* 338:275 */       updateBlockChange();
/* 339:    */     }
/* 340:    */     else
/* 341:    */     {
/* 342:277 */       if (!this.Powered) {
/* 343:277 */         return;
/* 344:    */       }
/* 345:278 */       this.Powered = false;
/* 346:279 */       updateBlockChange();
/* 347:280 */       return;
/* 348:    */     }
/* 349:282 */     if (this.Powered) {
/* 350:283 */       pickFrame();
/* 351:    */     }
/* 352:    */   }
/* 353:    */   
/* 354:    */   public int getFacing(md ent)
/* 355:    */   {
/* 356:288 */     int yawrx = (int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 357:289 */     if ((Math.abs(ent.t - this.l) < 2.0D) && (Math.abs(ent.v - this.n) < 2.0D))
/* 358:    */     {
/* 359:291 */       double p = ent.u + 1.82D - ent.M - this.m;
/* 360:292 */       if (p > 2.0D) {
/* 361:292 */         return 0;
/* 362:    */       }
/* 363:293 */       if (p < 0.0D) {
/* 364:293 */         return 1;
/* 365:    */       }
/* 366:    */     }
/* 367:295 */     switch (yawrx)
/* 368:    */     {
/* 369:    */     case 0: 
/* 370:296 */       return 3;
/* 371:    */     case 1: 
/* 372:297 */       return 4;
/* 373:    */     case 2: 
/* 374:298 */       return 2;
/* 375:    */     }
/* 376:299 */     return 5;
/* 377:    */   }
/* 378:    */   
/* 379:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 380:    */   {
/* 381:305 */     this.Rotation = (getFacing(ent) << 2);
/* 382:    */   }
/* 383:    */   
/* 384:    */   public byte[] getFramePacket()
/* 385:    */   {
/* 386:311 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 387:312 */     pkt.subId = 7;
/* 388:313 */     writeToPacket(pkt);
/* 389:314 */     pkt.headout.write(pkt.subId);
/* 390:315 */     return pkt.toByteArray();
/* 391:    */   }
/* 392:    */   
/* 393:    */   public void handleFramePacket(byte[] ba)
/* 394:    */     throws IOException
/* 395:    */   {
/* 396:319 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 397:320 */     pkt.subId = pkt.getByte();
/* 398:321 */     readFromPacket(pkt);
/* 399:    */   }
/* 400:    */   
/* 401:    */   public void onFrameRefresh(ym iba) {}
/* 402:    */   
/* 403:    */   public void onFramePickup(ym iba) {}
/* 404:    */   
/* 405:    */   public void onFrameDrop() {}
/* 406:    */   
/* 407:    */   public void a(bq tag)
/* 408:    */   {
/* 409:331 */     super.a(tag);
/* 410:332 */     this.Rotation = tag.c("rot");
/* 411:333 */     this.MoveDir = tag.c("mdir");
/* 412:334 */     this.MovePos = tag.c("mpos");
/* 413:335 */     this.LinkSize = tag.e("links");
/* 414:    */     
/* 415:337 */     this.cond.readFromNBT(tag);
/* 416:    */     
/* 417:339 */     int k = tag.c("ps");
/* 418:340 */     this.Powered = ((k & 0x1) > 0);
/* 419:341 */     this.Active = ((k & 0x2) > 0);
/* 420:342 */     this.Charged = ((k & 0x4) > 0);
/* 421:    */   }
/* 422:    */   
/* 423:    */   public void b(bq tag)
/* 424:    */   {
/* 425:346 */     super.b(tag);
/* 426:    */     
/* 427:348 */     tag.a("rot", (byte)this.Rotation);
/* 428:349 */     tag.a("mdir", (byte)this.MoveDir);
/* 429:350 */     tag.a("mpos", (byte)this.MovePos);
/* 430:351 */     tag.a("links", this.LinkSize);
/* 431:    */     
/* 432:353 */     this.cond.writeToNBT(tag);
/* 433:    */     
/* 434:355 */     int ps = (this.Powered ? 1 : 0) | (this.Active ? 2 : 0) | (this.Charged ? 4 : 0);
/* 435:356 */     tag.a("ps", (byte)ps);
/* 436:    */   }
/* 437:    */   
/* 438:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 439:    */     throws IOException
/* 440:    */   {
/* 441:361 */     this.Rotation = pkt.getByte();
/* 442:362 */     this.MoveDir = pkt.getByte();
/* 443:363 */     this.MovePos = (pkt.getByte() - 1);
/* 444:    */     
/* 445:365 */     int flags = pkt.getByte();
/* 446:366 */     this.Powered = ((flags & 0x1) > 0);
/* 447:367 */     this.Active = ((flags & 0x2) > 0);
/* 448:368 */     this.Charged = ((flags & 0x4) > 0);
/* 449:    */   }
/* 450:    */   
/* 451:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 452:    */   {
/* 453:372 */     pkt.addByte(this.Rotation);
/* 454:373 */     pkt.addByte(this.MoveDir);
/* 455:374 */     pkt.addByte(this.MovePos + 1);
/* 456:    */     
/* 457:376 */     int flags = (this.Powered ? 1 : 0) | (this.Active ? 2 : 0) | (this.Charged ? 4 : 0);
/* 458:377 */     pkt.addByte(flags);
/* 459:    */   }
/* 460:    */   
/* 461:    */   public ef l()
/* 462:    */   {
/* 463:381 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 464:382 */     packet.subId = 7;
/* 465:383 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 466:384 */     packet.zCoord = this.n;
/* 467:385 */     writeToPacket(packet);
/* 468:386 */     packet.encode();
/* 469:387 */     return packet;
/* 470:    */   }
/* 471:    */   
/* 472:    */   public void handlePacket(Packet211TileDesc packet)
/* 473:    */   {
/* 474:    */     try
/* 475:    */     {
/* 476:392 */       if (packet.subId != 7) {
/* 477:392 */         return;
/* 478:    */       }
/* 479:393 */       readFromPacket(packet);
/* 480:    */     }
/* 481:    */     catch (IOException e) {}
/* 482:395 */     this.k.i(this.l, this.m, this.n);
/* 483:    */   }
/* 484:    */   
/* 485:398 */   public int Rotation = 0;
/* 486:400 */   public int MoveDir = 4;
/* 487:401 */   public int MovePos = -1;
/* 488:402 */   public boolean Powered = false;
/* 489:402 */   public boolean Active = false;
/* 490:402 */   public boolean Charged = false;
/* 491:403 */   public int LinkSize = -1;
/* 492:405 */   public int ConMask = -1;
/* 493:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileMotor
 * JD-Core Version:    0.7.0.1
 */