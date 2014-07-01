/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import agi;
/*   4:    */ import amq;
/*   5:    */ import any;
/*   6:    */ import aoe;
/*   7:    */ import aok;
/*   8:    */ import bq;
/*   9:    */ import com.eloraam.redpower.RedPowerMachine;
/*  10:    */ import com.eloraam.redpower.core.BlockMultipart;
/*  11:    */ import com.eloraam.redpower.core.CoreLib;
/*  12:    */ import com.eloraam.redpower.core.IFrameLink;
/*  13:    */ import com.eloraam.redpower.core.IFrameSupport;
/*  14:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  15:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  16:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  17:    */ import com.eloraam.redpower.core.TileMultipart;
/*  18:    */ import com.eloraam.redpower.core.WorldCoord;
/*  19:    */ import ef;
/*  20:    */ import java.io.IOException;
/*  21:    */ import java.util.ArrayList;
/*  22:    */ import java.util.List;
/*  23:    */ import lq;
/*  24:    */ import net.minecraftforge.common.ForgeDirection;
/*  25:    */ import qx;
/*  26:    */ import yc;
/*  27:    */ import ym;
/*  28:    */ import yy;
/*  29:    */ 
/*  30:    */ public class TileFrameMoving
/*  31:    */   extends TileMultipart
/*  32:    */   implements IFrameLink, IHandlePackets
/*  33:    */ {
/*  34:    */   public boolean isFrameMoving()
/*  35:    */   {
/*  36: 33 */     return true;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public boolean canFrameConnectIn(int dir)
/*  40:    */   {
/*  41: 37 */     return true;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public boolean canFrameConnectOut(int dir)
/*  45:    */   {
/*  46: 41 */     return true;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public WorldCoord getFrameLinkset()
/*  50:    */   {
/*  51: 45 */     return new WorldCoord(this.motorX, this.motorY, this.motorZ);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public int getExtendedID()
/*  55:    */   {
/*  56: 51 */     return 1;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void onBlockNeighborChange(int l) {}
/*  60:    */   
/*  61:    */   public int getBlockID()
/*  62:    */   {
/*  63: 58 */     return RedPowerMachine.blockFrame.cm;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public int getPartsMask()
/*  67:    */   {
/*  68: 64 */     if (this.movingBlockID == 0) {
/*  69: 64 */       return 0;
/*  70:    */     }
/*  71: 65 */     return 536870912;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public int getSolidPartsMask()
/*  75:    */   {
/*  76: 69 */     if (this.movingBlockID == 0) {
/*  77: 69 */       return 0;
/*  78:    */     }
/*  79: 70 */     return 536870912;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public boolean blockEmpty()
/*  83:    */   {
/*  84: 74 */     return false;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void onHarvestPart(qx player, int part) {}
/*  88:    */   
/*  89:    */   public void addHarvestContents(ArrayList ist)
/*  90:    */   {
/*  91: 82 */     super.addHarvestContents(ist);
/*  92:    */   }
/*  93:    */   
/*  94:    */   public float getPartStrength(qx player, int part)
/*  95:    */   {
/*  96: 86 */     amq bl = RedPowerMachine.blockMachine;
/*  97: 87 */     return 0.0F;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void setPartBounds(BlockMultipart bl, int part)
/* 101:    */   {
/* 102: 91 */     TileMotor tm = (TileMotor)CoreLib.getTileEntity(this.k, this.motorX, this.motorY, this.motorZ, TileMotor.class);
/* 103: 93 */     if (tm == null) {
/* 104: 93 */       return;
/* 105:    */     }
/* 106: 95 */     float ofs = tm.getMoveScaled();
/* 107: 96 */     switch (tm.MoveDir)
/* 108:    */     {
/* 109:    */     case 0: 
/* 110: 97 */       bl.a(0.0F, 0.0F - ofs, 0.0F, 1.0F, 1.0F - ofs, 1.0F); break;
/* 111:    */     case 1: 
/* 112: 98 */       bl.a(0.0F, 0.0F + ofs, 0.0F, 1.0F, 1.0F + ofs, 1.0F); break;
/* 113:    */     case 2: 
/* 114: 99 */       bl.a(0.0F, 0.0F, 0.0F - ofs, 1.0F, 1.0F, 1.0F - ofs); break;
/* 115:    */     case 3: 
/* 116:100 */       bl.a(0.0F, 0.0F, 0.0F + ofs, 1.0F, 1.0F, 1.0F + ofs); break;
/* 117:    */     case 4: 
/* 118:101 */       bl.a(0.0F - ofs, 0.0F, 0.0F, 1.0F - ofs, 1.0F, 1.0F); break;
/* 119:    */     case 5: 
/* 120:102 */       bl.a(0.0F + ofs, 0.0F, 0.0F, 1.0F + ofs, 1.0F, 1.0F);
/* 121:    */     }
/* 122:    */   }
/* 123:    */   
/* 124:    */   private class FrameBlockAccess
/* 125:    */     implements ym
/* 126:    */   {
/* 127:109 */     private final aok vecpool = new aok(300, 2000);
/* 128:    */     
/* 129:    */     public FrameBlockAccess() {}
/* 130:    */     
/* 131:    */     private TileFrameMoving getFrame(int i, int j, int k)
/* 132:    */     {
/* 133:114 */       TileFrameMoving tfm = (TileFrameMoving)CoreLib.getTileEntity(TileFrameMoving.this.k, i, j, k, TileFrameMoving.class);
/* 134:116 */       if (tfm == null) {
/* 135:116 */         return null;
/* 136:    */       }
/* 137:117 */       if ((tfm.motorX != TileFrameMoving.this.motorX) || (tfm.motorY != TileFrameMoving.this.motorY) || (tfm.motorZ != tfm.motorZ)) {
/* 138:119 */         return null;
/* 139:    */       }
/* 140:120 */       return tfm;
/* 141:    */     }
/* 142:    */     
/* 143:    */     public int a(int i, int j, int k)
/* 144:    */     {
/* 145:125 */       TileFrameMoving tfm = getFrame(i, j, k);
/* 146:126 */       if (tfm == null) {
/* 147:126 */         return 0;
/* 148:    */       }
/* 149:127 */       return tfm.movingBlockID;
/* 150:    */     }
/* 151:    */     
/* 152:    */     public any q(int i, int j, int k)
/* 153:    */     {
/* 154:132 */       TileFrameMoving tfm = getFrame(i, j, k);
/* 155:133 */       if (tfm == null) {
/* 156:133 */         return null;
/* 157:    */       }
/* 158:134 */       return tfm.movingTileEntity;
/* 159:    */     }
/* 160:    */     
/* 161:    */     public int i(int i, int j, int k, int l)
/* 162:    */     {
/* 163:140 */       return TileFrameMoving.this.k.i(i, j, k, l);
/* 164:    */     }
/* 165:    */     
/* 166:    */     public float j(int i, int j, int k, int l)
/* 167:    */     {
/* 168:146 */       return TileFrameMoving.this.k.j(i, j, k, l);
/* 169:    */     }
/* 170:    */     
/* 171:    */     public float p(int i, int j, int k)
/* 172:    */     {
/* 173:151 */       return TileFrameMoving.this.k.p(i, j, k);
/* 174:    */     }
/* 175:    */     
/* 176:    */     public int h(int i, int j, int k)
/* 177:    */     {
/* 178:156 */       TileFrameMoving tfm = getFrame(i, j, k);
/* 179:157 */       if (tfm == null) {
/* 180:157 */         return 0;
/* 181:    */       }
/* 182:158 */       return tfm.movingBlockMeta;
/* 183:    */     }
/* 184:    */     
/* 185:    */     public agi g(int i, int j, int k)
/* 186:    */     {
/* 187:163 */       int l = a(i, j, k);
/* 188:164 */       if (l == 0) {
/* 189:164 */         return agi.a;
/* 190:    */       }
/* 191:165 */       return amq.p[l].cB;
/* 192:    */     }
/* 193:    */     
/* 194:    */     public boolean s(int i, int j, int k)
/* 195:    */     {
/* 196:170 */       amq block = amq.p[a(i, j, k)];
/* 197:171 */       if (block == null) {
/* 198:171 */         return false;
/* 199:    */       }
/* 200:172 */       return block.c();
/* 201:    */     }
/* 202:    */     
/* 203:    */     public boolean t(int i, int j, int k)
/* 204:    */     {
/* 205:177 */       amq block = amq.p[a(i, j, k)];
/* 206:178 */       if (block == null) {
/* 207:178 */         return false;
/* 208:    */       }
/* 209:179 */       return block.isBlockNormalCube(TileFrameMoving.this.k, i, j, k);
/* 210:    */     }
/* 211:    */     
/* 212:    */     public boolean c(int i, int j, int k)
/* 213:    */     {
/* 214:184 */       int bid = a(i, j, k);
/* 215:185 */       if (bid == 0) {
/* 216:185 */         return true;
/* 217:    */       }
/* 218:186 */       return amq.p[bid].isAirBlock(TileFrameMoving.this.k, i, j, k);
/* 219:    */     }
/* 220:    */     
/* 221:    */     public int O()
/* 222:    */     {
/* 223:191 */       return TileFrameMoving.this.k.O();
/* 224:    */     }
/* 225:    */     
/* 226:    */     public boolean v(int par1, int par2, int par3)
/* 227:    */     {
/* 228:197 */       return TileFrameMoving.this.k.isBlockSolidOnSide(par1, par2, par3, ForgeDirection.UP);
/* 229:    */     }
/* 230:    */     
/* 231:    */     public boolean Q()
/* 232:    */     {
/* 233:203 */       return false;
/* 234:    */     }
/* 235:    */     
/* 236:    */     public yy a(int a, int b)
/* 237:    */     {
/* 238:208 */       return TileFrameMoving.this.k.a(a, b);
/* 239:    */     }
/* 240:    */     
/* 241:    */     public boolean k(int var1, int var2, int var3, int var4)
/* 242:    */     {
/* 243:214 */       return false;
/* 244:    */     }
/* 245:    */     
/* 246:    */     public aok S()
/* 247:    */     {
/* 248:219 */       return this.vecpool;
/* 249:    */     }
/* 250:    */   }
/* 251:    */   
/* 252:222 */   FrameBlockAccess frameblock = new FrameBlockAccess();
/* 253:    */   public int motorX;
/* 254:    */   public int motorY;
/* 255:    */   public int motorZ;
/* 256:    */   
/* 257:    */   public ym getFrameBlockAccess()
/* 258:    */   {
/* 259:224 */     return this.frameblock;
/* 260:    */   }
/* 261:    */   
/* 262:    */   public void setContents(int bid, int md, int mx, int my, int mz, any bte)
/* 263:    */   {
/* 264:231 */     this.movingBlockID = bid;this.movingBlockMeta = md;
/* 265:232 */     this.motorX = mx;this.motorY = my;this.motorZ = mz;
/* 266:233 */     this.movingTileEntity = bte;
/* 267:235 */     if (this.movingTileEntity != null)
/* 268:    */     {
/* 269:236 */       if (RedPowerMachine.FrameAlwaysCrate) {
/* 270:237 */         this.movingCrate = true;
/* 271:    */       }
/* 272:238 */       if (!(this.movingTileEntity instanceof IFrameSupport)) {
/* 273:239 */         this.movingCrate = true;
/* 274:    */       }
/* 275:    */     }
/* 276:    */   }
/* 277:    */   
/* 278:    */   public void doRefresh(ym iba)
/* 279:    */   {
/* 280:244 */     if (!(this.movingTileEntity instanceof IFrameSupport)) {
/* 281:245 */       return;
/* 282:    */     }
/* 283:246 */     IFrameSupport ifs = (IFrameSupport)this.movingTileEntity;
/* 284:247 */     ifs.onFrameRefresh(iba);
/* 285:    */   }
/* 286:    */   
/* 287:    */   public void dropBlock()
/* 288:    */   {
/* 289:251 */     this.k.c(this.l, this.m, this.n, this.movingBlockID, this.movingBlockMeta);
/* 290:253 */     if (this.movingTileEntity != null)
/* 291:    */     {
/* 292:254 */       this.movingTileEntity.l = this.l;
/* 293:255 */       this.movingTileEntity.m = this.m;
/* 294:256 */       this.movingTileEntity.n = this.n;
/* 295:257 */       this.movingTileEntity.s();
/* 296:258 */       this.k.a(this.l, this.m, this.n, this.movingTileEntity);
/* 297:    */     }
/* 298:261 */     this.k.i(this.l, this.m, this.n);
/* 299:262 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/* 300:263 */     RedPowerLib.updateIndirectNeighbors(this.k, this.l, this.m, this.n, this.movingBlockID);
/* 301:    */   }
/* 302:    */   
/* 303:    */   private aoe getAABB(int dir, float dist)
/* 304:    */   {
/* 305:269 */     aoe aabb = aoe.a(this.l, this.m, this.n, this.l + 1, this.m + 1, this.n + 1);
/* 306:272 */     switch (dir)
/* 307:    */     {
/* 308:    */     case 0: 
/* 309:273 */       aabb.b -= dist;aabb.e -= dist; break;
/* 310:    */     case 1: 
/* 311:274 */       aabb.b += dist;aabb.e += dist; break;
/* 312:    */     case 2: 
/* 313:275 */       aabb.c -= dist;aabb.f -= dist; break;
/* 314:    */     case 3: 
/* 315:276 */       aabb.c += dist;aabb.f += dist; break;
/* 316:    */     case 4: 
/* 317:277 */       aabb.a -= dist;aabb.d -= dist; break;
/* 318:    */     case 5: 
/* 319:278 */       aabb.a += dist;aabb.d += dist;
/* 320:    */     }
/* 321:280 */     return aabb;
/* 322:    */   }
/* 323:    */   
/* 324:    */   void pushEntities(TileMotor tm)
/* 325:    */   {
/* 326:284 */     float f1 = this.lastMovePos / 16.0F;
/* 327:285 */     float f2 = tm.MovePos / 16.0F;
/* 328:286 */     this.lastMovePos = ((byte)tm.MovePos);
/* 329:    */     
/* 330:288 */     float xm = 0.0F;float ym = 0.0F;float zm = 0.0F;
/* 331:289 */     switch (tm.MoveDir)
/* 332:    */     {
/* 333:    */     case 0: 
/* 334:290 */       ym -= f2 - f1; break;
/* 335:    */     case 1: 
/* 336:291 */       ym += f2 - f1; break;
/* 337:    */     case 2: 
/* 338:292 */       zm -= f2 - f1; break;
/* 339:    */     case 3: 
/* 340:293 */       zm += f2 - f1; break;
/* 341:    */     case 4: 
/* 342:294 */       xm -= f2 - f1; break;
/* 343:    */     case 5: 
/* 344:295 */       xm += f2 - f1;
/* 345:    */     }
/* 346:298 */     aoe aabb = getAABB(tm.MoveDir, f2);
/* 347:299 */     List li = this.k.b(null, aabb);
/* 348:300 */     List li2 = new ArrayList();
/* 349:301 */     li2.addAll(li);
/* 350:302 */     for (Object o : li2)
/* 351:    */     {
/* 352:303 */       lq ent = (lq)o;
/* 353:304 */       ent.d(xm, ym, zm);
/* 354:    */     }
/* 355:    */   }
/* 356:    */   
/* 357:    */   public void g()
/* 358:    */   {
/* 359:309 */     super.g();
/* 360:310 */     TileMotor tm = (TileMotor)CoreLib.getTileEntity(this.k, this.motorX, this.motorY, this.motorZ, TileMotor.class);
/* 361:312 */     if ((tm == null) || (tm.MovePos < 0))
/* 362:    */     {
/* 363:313 */       if (CoreLib.isClient(this.k)) {
/* 364:313 */         return;
/* 365:    */       }
/* 366:314 */       dropBlock();
/* 367:315 */       return;
/* 368:    */     }
/* 369:317 */     pushEntities(tm);
/* 370:    */   }
/* 371:    */   
/* 372:    */   public void s()
/* 373:    */   {
/* 374:324 */     super.s();
/* 375:325 */     if (this.movingTileEntity != null) {
/* 376:326 */       this.movingTileEntity.k = this.k;
/* 377:    */     }
/* 378:    */   }
/* 379:    */   
/* 380:    */   public void a(bq tag)
/* 381:    */   {
/* 382:332 */     super.a(tag);
/* 383:333 */     this.motorX = tag.e("mx");
/* 384:334 */     this.motorY = tag.e("my");
/* 385:335 */     this.motorZ = tag.e("mz");
/* 386:    */     
/* 387:337 */     this.movingBlockID = tag.e("mbid");
/* 388:338 */     this.movingBlockMeta = tag.e("mbmd");
/* 389:    */     
/* 390:340 */     this.lastMovePos = tag.c("lmp");
/* 391:341 */     if (tag.b("mte"))
/* 392:    */     {
/* 393:342 */       bq mte = tag.l("mte");
/* 394:343 */       this.movingTileEntity = any.c(mte);
/* 395:    */     }
/* 396:    */     else
/* 397:    */     {
/* 398:345 */       this.movingTileEntity = null;
/* 399:    */     }
/* 400:    */   }
/* 401:    */   
/* 402:    */   public void b(bq tag)
/* 403:    */   {
/* 404:350 */     super.b(tag);
/* 405:351 */     tag.a("mx", this.motorX);
/* 406:352 */     tag.a("my", this.motorY);
/* 407:353 */     tag.a("mz", this.motorZ);
/* 408:    */     
/* 409:355 */     tag.a("mbid", this.movingBlockID);
/* 410:356 */     tag.a("mbmd", this.movingBlockMeta);
/* 411:357 */     tag.a("lmp", this.lastMovePos);
/* 412:359 */     if (this.movingTileEntity != null)
/* 413:    */     {
/* 414:360 */       bq mte = new bq();
/* 415:361 */       this.movingTileEntity.b(mte);
/* 416:362 */       tag.a("mte", mte);
/* 417:    */     }
/* 418:    */   }
/* 419:    */   
/* 420:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 421:    */     throws IOException
/* 422:    */   {
/* 423:368 */     this.motorX = ((int)pkt.getVLC());
/* 424:369 */     this.motorY = ((int)pkt.getVLC());
/* 425:370 */     this.motorZ = ((int)pkt.getVLC());
/* 426:371 */     this.movingBlockID = ((int)pkt.getUVLC());
/* 427:372 */     this.movingBlockMeta = pkt.getByte();
/* 428:374 */     if (this.movingBlockID != 0)
/* 429:    */     {
/* 430:375 */       this.movingTileEntity = amq.p[this.movingBlockID].createTileEntity(this.k, this.movingBlockMeta);
/* 431:377 */       if (this.movingTileEntity != null)
/* 432:    */       {
/* 433:378 */         if (!(this.movingTileEntity instanceof IFrameSupport))
/* 434:    */         {
/* 435:380 */           this.movingCrate = true;
/* 436:381 */           return;
/* 437:    */         }
/* 438:383 */         this.movingTileEntity.k = this.k;
/* 439:384 */         this.movingTileEntity.l = this.l;
/* 440:385 */         this.movingTileEntity.m = this.m;
/* 441:386 */         this.movingTileEntity.n = this.n;
/* 442:    */         
/* 443:388 */         IFrameSupport ifs = (IFrameSupport)this.movingTileEntity;
/* 444:    */         
/* 445:390 */         ifs.handleFramePacket(pkt.getByteArray());
/* 446:    */       }
/* 447:    */     }
/* 448:    */   }
/* 449:    */   
/* 450:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 451:    */   {
/* 452:396 */     pkt.addVLC(this.motorX);
/* 453:397 */     pkt.addVLC(this.motorY);
/* 454:398 */     pkt.addVLC(this.motorZ);
/* 455:399 */     pkt.addUVLC(this.movingBlockID);
/* 456:400 */     pkt.addByte(this.movingBlockMeta);
/* 457:402 */     if ((this.movingTileEntity instanceof IFrameSupport))
/* 458:    */     {
/* 459:403 */       IFrameSupport ifs = (IFrameSupport)this.movingTileEntity;
/* 460:404 */       pkt.addByteArray(ifs.getFramePacket());
/* 461:    */     }
/* 462:    */     else
/* 463:    */     {
/* 464:406 */       pkt.addByteArray(new byte[0]);
/* 465:    */     }
/* 466:    */   }
/* 467:    */   
/* 468:    */   public ef l()
/* 469:    */   {
/* 470:411 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 471:412 */     packet.subId = 7;
/* 472:413 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 473:414 */     packet.zCoord = this.n;
/* 474:415 */     writeToPacket(packet);
/* 475:416 */     packet.encode();
/* 476:417 */     return packet;
/* 477:    */   }
/* 478:    */   
/* 479:    */   public void handlePacket(Packet211TileDesc packet)
/* 480:    */   {
/* 481:    */     try
/* 482:    */     {
/* 483:422 */       if (packet.subId != 7) {
/* 484:422 */         return;
/* 485:    */       }
/* 486:423 */       readFromPacket(packet);
/* 487:    */     }
/* 488:    */     catch (IOException e) {}
/* 489:425 */     this.k.i(this.l, this.m, this.n);
/* 490:    */   }
/* 491:    */   
/* 492:429 */   public int movingBlockID = 0;
/* 493:430 */   public int movingBlockMeta = 0;
/* 494:431 */   public boolean movingCrate = false;
/* 495:432 */   public any movingTileEntity = null;
/* 496:433 */   public byte lastMovePos = 0;
/* 497:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileFrameMoving
 * JD-Core Version:    0.7.0.1
 */