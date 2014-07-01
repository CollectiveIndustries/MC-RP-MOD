/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import alr;
/*   4:    */ import amq;
/*   5:    */ import any;
/*   6:    */ import aoe;
/*   7:    */ import bq;
/*   8:    */ import com.eloraam.redpower.core.CoreLib;
/*   9:    */ import com.eloraam.redpower.core.ITubeConnectable;
/*  10:    */ import com.eloraam.redpower.core.MachineLib;
/*  11:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  12:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  13:    */ import com.eloraam.redpower.core.TubeItem;
/*  14:    */ import com.eloraam.redpower.core.WorldCoord;
/*  15:    */ import java.util.List;
/*  16:    */ import la;
/*  17:    */ import lq;
/*  18:    */ import net.minecraftforge.common.ForgeDirection;
/*  19:    */ import net.minecraftforge.common.ISidedInventory;
/*  20:    */ import px;
/*  21:    */ import py;
/*  22:    */ import ur;
/*  23:    */ import yc;
/*  24:    */ 
/*  25:    */ public class TileTranspose
/*  26:    */   extends TileMachine
/*  27:    */   implements ITubeConnectable
/*  28:    */ {
/*  29:    */   public int getTubeConnectableSides()
/*  30:    */   {
/*  31: 30 */     return 3 << (this.Rotation & 0xFFFFFFFE);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public int getTubeConClass()
/*  35:    */   {
/*  36: 34 */     return 0;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public boolean canRouteItems()
/*  40:    */   {
/*  41: 38 */     return false;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  45:    */   {
/*  46: 42 */     if ((side == this.Rotation) && (state == 2))
/*  47:    */     {
/*  48: 43 */       this.buffer.addBounce(ti);
/*  49: 44 */       this.Active = true;
/*  50: 45 */       updateBlock();
/*  51: 46 */       scheduleTick(5);
/*  52: 47 */       return true;
/*  53:    */     }
/*  54: 48 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1))
/*  55:    */     {
/*  56: 49 */       if (this.Powered) {
/*  57: 49 */         return false;
/*  58:    */       }
/*  59: 50 */       if (!this.buffer.isEmpty()) {
/*  60: 50 */         return false;
/*  61:    */       }
/*  62: 51 */       addToBuffer(ti.item);
/*  63: 52 */       this.Active = true;
/*  64: 53 */       updateBlock();
/*  65: 54 */       scheduleTick(5);
/*  66: 55 */       drainBuffer();
/*  67: 56 */       return true;
/*  68:    */     }
/*  69: 58 */     return false;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  73:    */   {
/*  74: 62 */     if ((side == this.Rotation) && (state == 2)) {
/*  75: 63 */       return true;
/*  76:    */     }
/*  77: 64 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1)) {
/*  78: 65 */       return (this.buffer.isEmpty()) && (!this.Powered);
/*  79:    */     }
/*  80: 66 */     return false;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public int tubeWeight(int side, int state)
/*  84:    */   {
/*  85: 70 */     if ((side == this.Rotation) && (state == 2)) {
/*  86: 71 */       return this.buffer.size();
/*  87:    */     }
/*  88: 72 */     return 0;
/*  89:    */   }
/*  90:    */   
/*  91:    */   protected void addToBuffer(ur ist)
/*  92:    */   {
/*  93: 76 */     this.buffer.addNew(ist);
/*  94:    */   }
/*  95:    */   
/*  96:    */   public boolean canSuck(int i, int j, int k)
/*  97:    */   {
/*  98: 82 */     if (this.k.isBlockSolidOnSide(i, j, k, ForgeDirection.getOrientation(this.Rotation))) {
/*  99: 84 */       return false;
/* 100:    */     }
/* 101: 85 */     any te = this.k.q(i, j, k);
/* 102: 86 */     if (te == null) {
/* 103: 86 */       return true;
/* 104:    */     }
/* 105: 87 */     if (((te instanceof la)) || ((te instanceof ITubeConnectable))) {
/* 106: 88 */       return false;
/* 107:    */     }
/* 108: 89 */     return true;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void onBlockNeighborChange(int l)
/* 112:    */   {
/* 113: 93 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, 16777215, 63))
/* 114:    */     {
/* 115: 95 */       if (this.Powered) {
/* 116: 95 */         return;
/* 117:    */       }
/* 118: 96 */       this.Powered = true;
/* 119: 97 */       dirtyBlock();
/* 120: 98 */       if (this.Active) {
/* 121: 98 */         return;
/* 122:    */       }
/* 123: 99 */       this.Active = true;
/* 124:    */     }
/* 125:    */     else
/* 126:    */     {
/* 127:101 */       if ((this.Active) && (!isTickScheduled())) {
/* 128:102 */         scheduleTick(5);
/* 129:    */       }
/* 130:103 */       this.Powered = false;
/* 131:104 */       dirtyBlock();
/* 132:105 */       return;
/* 133:    */     }
/* 134:107 */     WorldCoord wc = new WorldCoord(this.l, this.m, this.n);
/* 135:108 */     wc.step(this.Rotation ^ 0x1);
/* 136:109 */     if (canSuck(wc.x, wc.y, wc.z))
/* 137:    */     {
/* 138:110 */       doSuck();
/* 139:111 */       updateBlock();
/* 140:112 */       return;
/* 141:    */     }
/* 142:114 */     if (handleExtract(wc)) {
/* 143:114 */       updateBlock();
/* 144:    */     }
/* 145:    */   }
/* 146:    */   
/* 147:    */   protected la getConnectedInventory(boolean push)
/* 148:    */   {
/* 149:120 */     WorldCoord pos = new WorldCoord(this);
/* 150:121 */     pos.step(this.Rotation ^ 0x1);
/* 151:122 */     return MachineLib.getSideInventory(this.k, pos, this.Rotation, push);
/* 152:    */   }
/* 153:    */   
/* 154:    */   protected boolean handleExtract(WorldCoord wc)
/* 155:    */   {
/* 156:128 */     la inv = MachineLib.getInventory(this.k, wc);
/* 157:129 */     if (inv == null) {
/* 158:129 */       return false;
/* 159:    */     }
/* 160:130 */     int start = 0;
/* 161:131 */     int len = inv.k_();
/* 162:133 */     if ((inv instanceof ISidedInventory))
/* 163:    */     {
/* 164:134 */       ISidedInventory isi = (ISidedInventory)inv;
/* 165:135 */       start = isi.getStartInventorySide(ForgeDirection.getOrientation(this.Rotation));
/* 166:    */       
/* 167:137 */       len = isi.getSizeInventorySide(ForgeDirection.getOrientation(this.Rotation));
/* 168:    */     }
/* 169:141 */     return handleExtract(inv, start, len);
/* 170:    */   }
/* 171:    */   
/* 172:    */   protected boolean handleExtract(la inv, int start, int len)
/* 173:    */   {
/* 174:145 */     for (int n = start; n < start + len; n++)
/* 175:    */     {
/* 176:146 */       ur ist = inv.a(n);
/* 177:147 */       if ((ist != null) && (ist.a != 0))
/* 178:    */       {
/* 179:149 */         addToBuffer(inv.a(n, 1));
/* 180:150 */         drainBuffer();
/* 181:151 */         return true;
/* 182:    */       }
/* 183:    */     }
/* 184:153 */     return false;
/* 185:    */   }
/* 186:    */   
/* 187:    */   protected aoe getSizeBox(double bw, double bf, double bb)
/* 188:    */   {
/* 189:157 */     double fx = this.l + 0.5D;double fy = this.m + 0.5D;double fz = this.n + 0.5D;
/* 190:158 */     switch (this.Rotation)
/* 191:    */     {
/* 192:    */     case 0: 
/* 193:159 */       return aoe.a(fx - bw, this.m - bb, fz - bw, fx + bw, this.m + bf, fz + bw);
/* 194:    */     case 1: 
/* 195:161 */       return aoe.a(fx - bw, this.m + 1 - bf, fz - bw, fx + bw, this.m + 1 + bb, fz + bw);
/* 196:    */     case 2: 
/* 197:163 */       return aoe.a(fx - bw, fy - bw, this.n - bb, fx + bw, fy + bw, this.n + bf);
/* 198:    */     case 3: 
/* 199:165 */       return aoe.a(fx - bw, fy - bw, this.n + 1 - bf, fx + bw, fy + bw, this.n + 1 + bb);
/* 200:    */     case 4: 
/* 201:167 */       return aoe.a(this.l - bb, fy - bw, fz - bw, this.l + bf, fy + bw, fz + bw);
/* 202:    */     }
/* 203:169 */     return aoe.a(this.l + 1 - bf, fy - bw, fz - bw, this.l + 1 + bb, fy + bw, fz + bw);
/* 204:    */   }
/* 205:    */   
/* 206:    */   protected void doSuck()
/* 207:    */   {
/* 208:175 */     suckEntities(getSizeBox(1.55D, 3.05D, -0.95D));
/* 209:    */   }
/* 210:    */   
/* 211:    */   protected boolean suckFilter(ur ist)
/* 212:    */   {
/* 213:179 */     return true;
/* 214:    */   }
/* 215:    */   
/* 216:    */   protected int suckEntity(Object ent)
/* 217:    */   {
/* 218:183 */     if ((ent instanceof px))
/* 219:    */     {
/* 220:184 */       px ei = (px)ent;
/* 221:185 */       ur ist = ei.d();
/* 222:186 */       if ((ist.a == 0) || (ei.L)) {
/* 223:187 */         return 0;
/* 224:    */       }
/* 225:188 */       if (!suckFilter(ist)) {
/* 226:188 */         return 0;
/* 227:    */       }
/* 228:189 */       addToBuffer(ist);
/* 229:190 */       ei.x();
/* 230:191 */       return 1;
/* 231:    */     }
/* 232:192 */     if ((ent instanceof py))
/* 233:    */     {
/* 234:193 */       if (this.Active) {
/* 235:193 */         return 0;
/* 236:    */       }
/* 237:194 */       py em = (py)ent;
/* 238:195 */       if (handleExtract(em, 0, em.k_())) {
/* 239:196 */         return 2;
/* 240:    */       }
/* 241:    */     }
/* 242:198 */     return 0;
/* 243:    */   }
/* 244:    */   
/* 245:    */   protected void suckEntities(aoe bb)
/* 246:    */   {
/* 247:202 */     boolean trig = false;
/* 248:203 */     List el = this.k.a(lq.class, bb);
/* 249:204 */     for (Object ent : el)
/* 250:    */     {
/* 251:205 */       int i = suckEntity(ent);
/* 252:206 */       if (i != 0)
/* 253:    */       {
/* 254:207 */         trig = true;
/* 255:208 */         if (i == 2) {
/* 256:    */           break;
/* 257:    */         }
/* 258:    */       }
/* 259:    */     }
/* 260:210 */     if (trig)
/* 261:    */     {
/* 262:211 */       if (!this.Active)
/* 263:    */       {
/* 264:212 */         this.Active = true;
/* 265:213 */         updateBlock();
/* 266:    */       }
/* 267:215 */       drainBuffer();
/* 268:216 */       scheduleTick(5);
/* 269:    */     }
/* 270:    */   }
/* 271:    */   
/* 272:    */   public boolean stuffCart(ur ist)
/* 273:    */   {
/* 274:221 */     WorldCoord wc = new WorldCoord(this);
/* 275:222 */     wc.step(this.Rotation);
/* 276:    */     
/* 277:224 */     amq bl = amq.p[this.k.a(wc.x, wc.y, wc.z)];
/* 278:225 */     if (!(bl instanceof alr)) {
/* 279:225 */       return false;
/* 280:    */     }
/* 281:227 */     List el = this.k.a(py.class, getSizeBox(0.8D, 0.05D, 1.05D));
/* 282:229 */     for (Object ent : el) {
/* 283:230 */       if ((ent instanceof py))
/* 284:    */       {
/* 285:231 */         py em = (py)ent;
/* 286:232 */         if (MachineLib.addToInventoryCore(em, ist, 0, em.k_(), true)) {
/* 287:234 */           return true;
/* 288:    */         }
/* 289:    */       }
/* 290:    */     }
/* 291:237 */     return false;
/* 292:    */   }
/* 293:    */   
/* 294:    */   public void drainBuffer()
/* 295:    */   {
/* 296:241 */     while (!this.buffer.isEmpty())
/* 297:    */     {
/* 298:242 */       TubeItem ti = this.buffer.getLast();
/* 299:243 */       if (stuffCart(ti.item))
/* 300:    */       {
/* 301:244 */         this.buffer.pop();
/* 302:    */       }
/* 303:    */       else
/* 304:    */       {
/* 305:247 */         if (!handleItem(ti))
/* 306:    */         {
/* 307:248 */           this.buffer.plugged = true;
/* 308:249 */           return;
/* 309:    */         }
/* 310:251 */         this.buffer.pop();
/* 311:252 */         if (this.buffer.plugged) {
/* 312:252 */           return;
/* 313:    */         }
/* 314:    */       }
/* 315:    */     }
/* 316:    */   }
/* 317:    */   
/* 318:    */   public aoe getCollisionBoundingBox()
/* 319:    */   {
/* 320:257 */     return getSizeBox(0.5D, 0.95D, 0.0D);
/* 321:    */   }
/* 322:    */   
/* 323:    */   public void onEntityCollidedWithBlock(lq ent)
/* 324:    */   {
/* 325:261 */     if (CoreLib.isClient(this.k)) {
/* 326:262 */       return;
/* 327:    */     }
/* 328:263 */     if (this.Powered) {
/* 329:263 */       return;
/* 330:    */     }
/* 331:264 */     if (this.buffer.isEmpty()) {
/* 332:265 */       suckEntities(getSizeBox(0.55D, 1.05D, -0.95D));
/* 333:    */     }
/* 334:    */   }
/* 335:    */   
/* 336:    */   public void onBlockRemoval()
/* 337:    */   {
/* 338:269 */     this.buffer.onRemove(this);
/* 339:    */   }
/* 340:    */   
/* 341:    */   public void onTileTick()
/* 342:    */   {
/* 343:273 */     if (CoreLib.isClient(this.k)) {
/* 344:274 */       return;
/* 345:    */     }
/* 346:275 */     if (!this.buffer.isEmpty())
/* 347:    */     {
/* 348:276 */       drainBuffer();
/* 349:277 */       if (!this.buffer.isEmpty()) {
/* 350:277 */         scheduleTick(10);
/* 351:    */       } else {
/* 352:278 */         scheduleTick(5);
/* 353:    */       }
/* 354:279 */       return;
/* 355:    */     }
/* 356:281 */     if (!this.Powered)
/* 357:    */     {
/* 358:282 */       this.Active = false;
/* 359:283 */       updateBlock();
/* 360:    */     }
/* 361:    */   }
/* 362:    */   
/* 363:    */   public int getExtendedID()
/* 364:    */   {
/* 365:288 */     return 2;
/* 366:    */   }
/* 367:    */   
/* 368:    */   public void a(bq nbttagcompound)
/* 369:    */   {
/* 370:294 */     super.a(nbttagcompound);
/* 371:295 */     this.buffer.readFromNBT(nbttagcompound);
/* 372:    */   }
/* 373:    */   
/* 374:    */   public void b(bq nbttagcompound)
/* 375:    */   {
/* 376:299 */     super.b(nbttagcompound);
/* 377:300 */     this.buffer.writeToNBT(nbttagcompound);
/* 378:    */   }
/* 379:    */   
/* 380:303 */   TubeBuffer buffer = new TubeBuffer();
/* 381:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileTranspose
 * JD-Core Version:    0.7.0.1
 */