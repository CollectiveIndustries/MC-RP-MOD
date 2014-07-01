/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import by;
/*   6:    */ import com.eloraam.redpower.RedPowerMachine;
/*   7:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   8:    */ import com.eloraam.redpower.core.CoreLib;
/*   9:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  10:    */ import com.eloraam.redpower.core.IFrameSupport;
/*  11:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  12:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  13:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  14:    */ import com.eloraam.redpower.core.TileExtended;
/*  15:    */ import ef;
/*  16:    */ import java.io.ByteArrayOutputStream;
/*  17:    */ import java.io.IOException;
/*  18:    */ import java.util.ArrayList;
/*  19:    */ import la;
/*  20:    */ import md;
/*  21:    */ import net.minecraftforge.common.ForgeDirection;
/*  22:    */ import net.minecraftforge.common.ISidedInventory;
/*  23:    */ import qx;
/*  24:    */ import up;
/*  25:    */ import ur;
/*  26:    */ import yc;
/*  27:    */ import ym;
/*  28:    */ 
/*  29:    */ public class TileBatteryBox
/*  30:    */   extends TileExtended
/*  31:    */   implements IHandlePackets, la, IBluePowerConnectable, ISidedInventory, IFrameSupport
/*  32:    */ {
/*  33:    */   public TileBatteryBox()
/*  34:    */   {
/*  35: 27 */     this.contents = new ur[2];
/*  36:    */   }
/*  37:    */   
/*  38:    */   public int getConnectableMask()
/*  39:    */   {
/*  40: 33 */     return 1073741823;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public int getConnectClass(int side)
/*  44:    */   {
/*  45: 37 */     return 65;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public int getCornerPowerMode()
/*  49:    */   {
/*  50: 40 */     return 0;
/*  51:    */   }
/*  52:    */   
/*  53: 44 */   BluePowerConductor cond = new BluePowerConductor()
/*  54:    */   {
/*  55:    */     public any getParent()
/*  56:    */     {
/*  57: 46 */       return TileBatteryBox.this;
/*  58:    */     }
/*  59:    */     
/*  60:    */     public double getInvCap()
/*  61:    */     {
/*  62: 50 */       return 0.25D;
/*  63:    */     }
/*  64:    */   };
/*  65:    */   protected ur[] contents;
/*  66:    */   
/*  67:    */   public BluePowerConductor getBlueConductor(int side)
/*  68:    */   {
/*  69: 55 */     return this.cond;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public int getStartInventorySide(ForgeDirection fd)
/*  73:    */   {
/*  74: 62 */     int side = fd.ordinal();
/*  75: 63 */     if (side == 0) {
/*  76: 63 */       return 1;
/*  77:    */     }
/*  78: 64 */     return 0;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public int getSizeInventorySide(ForgeDirection fd)
/*  82:    */   {
/*  83: 69 */     int side = fd.ordinal();
/*  84: 70 */     if (side >= 2) {
/*  85: 70 */       return 0;
/*  86:    */     }
/*  87: 71 */     return 1;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void addHarvestContents(ArrayList ist)
/*  91:    */   {
/*  92: 77 */     ur is = new ur(getBlockID(), 1, getExtendedID());
/*  93: 78 */     if (this.Storage > 0)
/*  94:    */     {
/*  95: 79 */       is.d(new bq());
/*  96: 80 */       is.d.a("batLevel", (short)this.Storage);
/*  97:    */     }
/*  98: 82 */     ist.add(is);
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 102:    */   {
/* 103: 86 */     if (ist.d != null) {
/* 104: 87 */       this.Storage = ist.d.d("batLevel");
/* 105:    */     }
/* 106:    */   }
/* 107:    */   
/* 108:    */   public int getExtendedID()
/* 109:    */   {
/* 110: 94 */     return 6;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public int getBlockID()
/* 114:    */   {
/* 115: 98 */     return RedPowerMachine.blockMachine.cm;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public int getMaxStorage()
/* 119:    */   {
/* 120:103 */     return 6000;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public int getStorageForRender()
/* 124:    */   {
/* 125:107 */     return this.Storage * 8 / getMaxStorage();
/* 126:    */   }
/* 127:    */   
/* 128:    */   public int getChargeScaled(int i)
/* 129:    */   {
/* 130:111 */     return Math.min(i, i * this.Charge / 1000);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public int getStorageScaled(int i)
/* 134:    */   {
/* 135:115 */     return Math.min(i, i * this.Storage / getMaxStorage());
/* 136:    */   }
/* 137:    */   
/* 138:    */   public void g()
/* 139:    */   {
/* 140:119 */     super.g();
/* 141:120 */     if (CoreLib.isClient(this.k)) {
/* 142:120 */       return;
/* 143:    */     }
/* 144:122 */     if (this.ConMask < 0)
/* 145:    */     {
/* 146:123 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 147:    */       
/* 148:125 */       this.cond.recache(this.ConMask, 0);
/* 149:    */     }
/* 150:127 */     this.cond.iterate();
/* 151:128 */     dirtyBlock();
/* 152:129 */     this.Charge = ((int)(this.cond.getVoltage() * 10.0D));
/* 153:    */     
/* 154:131 */     int rs = getStorageForRender();
/* 155:132 */     if ((this.contents[0] != null) && (this.Storage > 0))
/* 156:    */     {
/* 157:133 */       if (this.contents[0].b() == RedPowerMachine.itemBatteryEmpty)
/* 158:    */       {
/* 159:135 */         this.contents[0] = new ur(RedPowerMachine.itemBatteryPowered, 1, RedPowerMachine.itemBatteryPowered.m());
/* 160:    */         
/* 161:    */ 
/* 162:    */ 
/* 163:139 */         d();
/* 164:    */       }
/* 165:141 */       if (this.contents[0].b() == RedPowerMachine.itemBatteryPowered)
/* 166:    */       {
/* 167:143 */         int n = Math.min(this.contents[0].j() - 1, this.Storage);
/* 168:    */         
/* 169:145 */         n = Math.min(n, 25);
/* 170:146 */         this.Storage -= n;
/* 171:147 */         this.contents[0].b(this.contents[0].j() - n);
/* 172:    */         
/* 173:149 */         d();
/* 174:    */       }
/* 175:    */     }
/* 176:153 */     if ((this.contents[1] != null) && 
/* 177:154 */       (this.contents[1].b() == RedPowerMachine.itemBatteryPowered))
/* 178:    */     {
/* 179:156 */       int n = Math.min(this.contents[1].k() - this.contents[1].j(), getMaxStorage() - this.Storage);
/* 180:    */       
/* 181:    */ 
/* 182:159 */       n = Math.min(n, 25);
/* 183:160 */       this.Storage += n;
/* 184:161 */       this.contents[1].b(this.contents[1].j() + n);
/* 185:163 */       if (this.contents[1].j() == this.contents[1].k()) {
/* 186:165 */         this.contents[1] = new ur(RedPowerMachine.itemBatteryEmpty, 1);
/* 187:    */       }
/* 188:169 */       d();
/* 189:    */     }
/* 190:172 */     if ((this.Charge > 900) && (this.Storage < getMaxStorage()))
/* 191:    */     {
/* 192:173 */       int n = Math.min((this.Charge - 900) / 10, 10);
/* 193:174 */       n = Math.min(n, getMaxStorage() - this.Storage);
/* 194:175 */       this.cond.drawPower(n * 1000);
/* 195:176 */       this.Storage += n;
/* 196:    */     }
/* 197:177 */     else if ((this.Charge < 800) && (this.Storage > 0) && (!this.Powered))
/* 198:    */     {
/* 199:178 */       int n = Math.min((800 - this.Charge) / 10, 10);
/* 200:179 */       n = Math.min(n, this.Storage);
/* 201:180 */       this.cond.applyPower(n * 1000);
/* 202:181 */       this.Storage -= n;
/* 203:    */     }
/* 204:183 */     if (rs != getStorageForRender()) {
/* 205:184 */       updateBlock();
/* 206:    */     }
/* 207:    */   }
/* 208:    */   
/* 209:    */   public int k_()
/* 210:    */   {
/* 211:190 */     return 2;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public ur a(int i)
/* 215:    */   {
/* 216:194 */     return this.contents[i];
/* 217:    */   }
/* 218:    */   
/* 219:    */   public ur a(int i, int j)
/* 220:    */   {
/* 221:199 */     if (this.contents[i] == null) {
/* 222:199 */       return null;
/* 223:    */     }
/* 224:201 */     if (this.contents[i].a <= j)
/* 225:    */     {
/* 226:202 */       ur tr = this.contents[i];
/* 227:203 */       this.contents[i] = null;
/* 228:204 */       d();
/* 229:205 */       return tr;
/* 230:    */     }
/* 231:207 */     ur tr = this.contents[i].a(j);
/* 232:208 */     if (this.contents[i].a == 0) {
/* 233:209 */       this.contents[i] = null;
/* 234:    */     }
/* 235:210 */     d();
/* 236:211 */     return tr;
/* 237:    */   }
/* 238:    */   
/* 239:    */   public ur a_(int i)
/* 240:    */   {
/* 241:215 */     if (this.contents[i] == null) {
/* 242:215 */       return null;
/* 243:    */     }
/* 244:216 */     ur ist = this.contents[i];
/* 245:217 */     this.contents[i] = null;
/* 246:218 */     return ist;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public void a(int i, ur ist)
/* 250:    */   {
/* 251:222 */     this.contents[i] = ist;
/* 252:223 */     if ((ist != null) && (ist.a > c())) {
/* 253:224 */       ist.a = c();
/* 254:    */     }
/* 255:225 */     d();
/* 256:    */   }
/* 257:    */   
/* 258:    */   public String b()
/* 259:    */   {
/* 260:229 */     return "Battery Box";
/* 261:    */   }
/* 262:    */   
/* 263:    */   public int c()
/* 264:    */   {
/* 265:233 */     return 1;
/* 266:    */   }
/* 267:    */   
/* 268:    */   public boolean a_(qx player)
/* 269:    */   {
/* 270:237 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 271:238 */       return false;
/* 272:    */     }
/* 273:239 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 274:    */   }
/* 275:    */   
/* 276:    */   public void f() {}
/* 277:    */   
/* 278:    */   public void l_() {}
/* 279:    */   
/* 280:    */   public void onBlockNeighborChange(int l)
/* 281:    */   {
/* 282:250 */     this.ConMask = -1;
/* 283:251 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, 16777215, 63))
/* 284:    */     {
/* 285:253 */       if (this.Powered) {
/* 286:253 */         return;
/* 287:    */       }
/* 288:254 */       this.Powered = true;
/* 289:255 */       dirtyBlock();
/* 290:    */     }
/* 291:    */     else
/* 292:    */     {
/* 293:257 */       if (!this.Powered) {
/* 294:257 */         return;
/* 295:    */       }
/* 296:258 */       this.Powered = false;
/* 297:259 */       dirtyBlock();
/* 298:260 */       return;
/* 299:    */     }
/* 300:    */   }
/* 301:    */   
/* 302:    */   public boolean onBlockActivated(qx player)
/* 303:    */   {
/* 304:265 */     if (player.ah()) {
/* 305:265 */       return false;
/* 306:    */     }
/* 307:266 */     if (CoreLib.isClient(this.k)) {
/* 308:267 */       return true;
/* 309:    */     }
/* 310:268 */     player.openGui(RedPowerMachine.instance, 8, this.k, this.l, this.m, this.n);
/* 311:    */     
/* 312:270 */     return true;
/* 313:    */   }
/* 314:    */   
/* 315:    */   public void onBlockRemoval()
/* 316:    */   {
/* 317:274 */     super.onBlockRemoval();
/* 318:276 */     for (int i = 0; i < 2; i++)
/* 319:    */     {
/* 320:277 */       ur ist = this.contents[i];
/* 321:278 */       if ((ist != null) && (ist.a > 0)) {
/* 322:279 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 323:    */       }
/* 324:    */     }
/* 325:    */   }
/* 326:    */   
/* 327:    */   public byte[] getFramePacket()
/* 328:    */   {
/* 329:288 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 330:289 */     pkt.subId = 7;
/* 331:290 */     writeToPacket(pkt);
/* 332:291 */     pkt.headout.write(pkt.subId);
/* 333:292 */     return pkt.toByteArray();
/* 334:    */   }
/* 335:    */   
/* 336:    */   public void handleFramePacket(byte[] ba)
/* 337:    */     throws IOException
/* 338:    */   {
/* 339:296 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 340:297 */     pkt.subId = pkt.getByte();
/* 341:298 */     readFromPacket(pkt);
/* 342:    */   }
/* 343:    */   
/* 344:    */   public void onFrameRefresh(ym iba) {}
/* 345:    */   
/* 346:    */   public void onFramePickup(ym iba) {}
/* 347:    */   
/* 348:    */   public void onFrameDrop() {}
/* 349:    */   
/* 350:    */   public void a(bq tag)
/* 351:    */   {
/* 352:308 */     super.a(tag);
/* 353:    */     
/* 354:310 */     by items = tag.m("Items");
/* 355:311 */     this.contents = new ur[k_()];
/* 356:312 */     for (int i = 0; i < items.c(); i++)
/* 357:    */     {
/* 358:313 */       bq item = (bq)items.b(i);
/* 359:    */       
/* 360:315 */       int j = item.c("Slot") & 0xFF;
/* 361:316 */       if ((j >= 0) && (j < this.contents.length)) {
/* 362:317 */         this.contents[j] = ur.a(item);
/* 363:    */       }
/* 364:    */     }
/* 365:321 */     this.cond.readFromNBT(tag);
/* 366:322 */     this.Charge = tag.d("chg");
/* 367:323 */     this.Storage = tag.d("stor");
/* 368:    */     
/* 369:325 */     int k = tag.c("ps");
/* 370:326 */     this.Powered = ((k & 0x1) > 0);
/* 371:    */   }
/* 372:    */   
/* 373:    */   public void b(bq tag)
/* 374:    */   {
/* 375:330 */     super.b(tag);
/* 376:    */     
/* 377:332 */     by items = new by();
/* 378:333 */     for (int i = 0; i < this.contents.length; i++) {
/* 379:334 */       if (this.contents[i] != null)
/* 380:    */       {
/* 381:335 */         bq item = new bq();
/* 382:336 */         item.a("Slot", (byte)i);
/* 383:337 */         this.contents[i].b(item);
/* 384:338 */         items.a(item);
/* 385:    */       }
/* 386:    */     }
/* 387:341 */     tag.a("Items", items);
/* 388:    */     
/* 389:343 */     this.cond.writeToNBT(tag);
/* 390:344 */     tag.a("chg", (short)this.Charge);
/* 391:345 */     tag.a("stor", (short)this.Storage);
/* 392:    */     
/* 393:347 */     int ps = this.Powered ? 1 : 0;
/* 394:348 */     tag.a("ps", (byte)ps);
/* 395:    */   }
/* 396:    */   
/* 397:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 398:    */     throws IOException
/* 399:    */   {
/* 400:353 */     this.Storage = ((int)pkt.getUVLC());
/* 401:    */   }
/* 402:    */   
/* 403:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 404:    */   {
/* 405:357 */     pkt.addUVLC(this.Storage);
/* 406:    */   }
/* 407:    */   
/* 408:    */   public ef l()
/* 409:    */   {
/* 410:361 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 411:362 */     packet.subId = 7;
/* 412:363 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 413:364 */     packet.zCoord = this.n;
/* 414:365 */     writeToPacket(packet);
/* 415:366 */     packet.encode();
/* 416:367 */     return packet;
/* 417:    */   }
/* 418:    */   
/* 419:    */   public void handlePacket(Packet211TileDesc packet)
/* 420:    */   {
/* 421:    */     try
/* 422:    */     {
/* 423:372 */       if (packet.subId != 7) {
/* 424:372 */         return;
/* 425:    */       }
/* 426:373 */       readFromPacket(packet);
/* 427:    */     }
/* 428:    */     catch (IOException e) {}
/* 429:375 */     updateBlock();
/* 430:    */   }
/* 431:    */   
/* 432:379 */   public int Charge = 0;
/* 433:379 */   public int Storage = 0;
/* 434:380 */   public int ConMask = -1;
/* 435:381 */   public boolean Powered = false;
/* 436:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileBatteryBox
 * JD-Core Version:    0.7.0.1
 */