/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import by;
/*   6:    */ import com.eloraam.redpower.RedPowerMachine;
/*   7:    */ import com.eloraam.redpower.base.TileAppliance;
/*   8:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   9:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*  10:    */ import com.eloraam.redpower.core.CoreLib;
/*  11:    */ import com.eloraam.redpower.core.CraftLib;
/*  12:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  13:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  14:    */ import la;
/*  15:    */ import md;
/*  16:    */ import net.minecraftforge.common.ForgeDirection;
/*  17:    */ import net.minecraftforge.common.ISidedInventory;
/*  18:    */ import qx;
/*  19:    */ import ur;
/*  20:    */ import yc;
/*  21:    */ 
/*  22:    */ public class TileBlueAlloyFurnace
/*  23:    */   extends TileAppliance
/*  24:    */   implements la, ISidedInventory, IBluePowerConnectable
/*  25:    */ {
/*  26:    */   public TileBlueAlloyFurnace()
/*  27:    */   {
/*  28: 28 */     this.contents = new ur[10];
/*  29:    */   }
/*  30:    */   
/*  31:    */   public int getConnectableMask()
/*  32:    */   {
/*  33: 34 */     return 1073741823;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public int getConnectClass(int side)
/*  37:    */   {
/*  38: 38 */     return 64;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getCornerPowerMode()
/*  42:    */   {
/*  43: 41 */     return 0;
/*  44:    */   }
/*  45:    */   
/*  46: 45 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  47:    */   {
/*  48:    */     public any getParent()
/*  49:    */     {
/*  50: 47 */       return TileBlueAlloyFurnace.this;
/*  51:    */     }
/*  52:    */   };
/*  53:    */   private ur[] contents;
/*  54:    */   
/*  55:    */   public BluePowerConductor getBlueConductor(int side)
/*  56:    */   {
/*  57: 52 */     return this.cond;
/*  58:    */   }
/*  59:    */   
/*  60:    */   void updateLight()
/*  61:    */   {
/*  62: 58 */     this.k.z(this.l, this.m, this.n);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public int getExtendedID()
/*  66:    */   {
/*  67: 62 */     return 4;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void g()
/*  71:    */   {
/*  72: 66 */     super.g();
/*  73: 67 */     if (CoreLib.isClient(this.k)) {
/*  74: 67 */       return;
/*  75:    */     }
/*  76: 69 */     if (this.ConMask < 0)
/*  77:    */     {
/*  78: 70 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/*  79:    */       
/*  80: 72 */       this.cond.recache(this.ConMask, 0);
/*  81:    */     }
/*  82: 74 */     this.cond.iterate();
/*  83: 75 */     dirtyBlock();
/*  84: 77 */     if (this.cond.getVoltage() < 60.0D)
/*  85:    */     {
/*  86: 78 */       if ((this.Active) && (this.cond.Flow == 0))
/*  87:    */       {
/*  88: 79 */         this.Active = false;
/*  89: 80 */         updateBlock();
/*  90: 81 */         updateLight();
/*  91:    */       }
/*  92: 83 */       return;
/*  93:    */     }
/*  94: 85 */     boolean cs = canSmelt();
/*  95: 86 */     if (cs)
/*  96:    */     {
/*  97: 87 */       if (!this.Active)
/*  98:    */       {
/*  99: 88 */         this.Active = true;
/* 100: 89 */         updateBlock();
/* 101: 90 */         updateLight();
/* 102:    */       }
/* 103: 92 */       this.cooktime += 1;
/* 104: 93 */       this.cond.drawPower(1000.0D);
/* 105: 94 */       if (this.cooktime >= 100)
/* 106:    */       {
/* 107: 95 */         this.cooktime = 0;
/* 108: 96 */         smeltItem();
/* 109: 97 */         d();
/* 110:    */       }
/* 111:    */     }
/* 112:    */     else
/* 113:    */     {
/* 114:100 */       if (this.Active)
/* 115:    */       {
/* 116:101 */         this.Active = false;
/* 117:102 */         updateBlock();
/* 118:103 */         updateLight();
/* 119:    */       }
/* 120:105 */       this.cooktime = 0;
/* 121:    */     }
/* 122:    */   }
/* 123:    */   
/* 124:    */   boolean canSmelt()
/* 125:    */   {
/* 126:110 */     ur ist = CraftLib.getAlloyResult(this.contents, 0, 9, false);
/* 127:111 */     if (ist == null) {
/* 128:111 */       return false;
/* 129:    */     }
/* 130:112 */     if (this.contents[9] == null) {
/* 131:112 */       return true;
/* 132:    */     }
/* 133:113 */     if (!this.contents[9].a(ist)) {
/* 134:113 */       return false;
/* 135:    */     }
/* 136:115 */     int st = this.contents[9].a + ist.a;
/* 137:116 */     return (st <= c()) && (st <= ist.d());
/* 138:    */   }
/* 139:    */   
/* 140:    */   void smeltItem()
/* 141:    */   {
/* 142:121 */     if (!canSmelt()) {
/* 143:121 */       return;
/* 144:    */     }
/* 145:123 */     ur ist = CraftLib.getAlloyResult(this.contents, 0, 9, true);
/* 146:124 */     if (this.contents[9] == null) {
/* 147:125 */       this.contents[9] = ist.l();
/* 148:    */     } else {
/* 149:127 */       this.contents[9].a += ist.a;
/* 150:    */     }
/* 151:    */   }
/* 152:    */   
/* 153:    */   int getCookScaled(int i)
/* 154:    */   {
/* 155:132 */     return this.cooktime * i / 100;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public boolean onBlockActivated(qx player)
/* 159:    */   {
/* 160:138 */     if (player.ah()) {
/* 161:138 */       return false;
/* 162:    */     }
/* 163:139 */     if (CoreLib.isClient(this.k)) {
/* 164:140 */       return true;
/* 165:    */     }
/* 166:141 */     player.openGui(RedPowerMachine.instance, 10, this.k, this.l, this.m, this.n);
/* 167:    */     
/* 168:143 */     return true;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 172:    */   {
/* 173:148 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3);
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void onBlockRemoval()
/* 177:    */   {
/* 178:152 */     for (int i = 0; i < 10; i++)
/* 179:    */     {
/* 180:153 */       ur ist = this.contents[i];
/* 181:154 */       if ((ist != null) && (ist.a > 0)) {
/* 182:155 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 183:    */       }
/* 184:    */     }
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void onBlockNeighborChange(int l)
/* 188:    */   {
/* 189:162 */     this.ConMask = -1;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public int k_()
/* 193:    */   {
/* 194:168 */     return 10;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public ur a(int i)
/* 198:    */   {
/* 199:172 */     return this.contents[i];
/* 200:    */   }
/* 201:    */   
/* 202:    */   public ur a(int i, int j)
/* 203:    */   {
/* 204:177 */     if (this.contents[i] == null) {
/* 205:177 */       return null;
/* 206:    */     }
/* 207:179 */     if (this.contents[i].a <= j)
/* 208:    */     {
/* 209:180 */       ur tr = this.contents[i];
/* 210:181 */       this.contents[i] = null;
/* 211:182 */       d();
/* 212:183 */       return tr;
/* 213:    */     }
/* 214:185 */     ur tr = this.contents[i].a(j);
/* 215:186 */     if (this.contents[i].a == 0) {
/* 216:187 */       this.contents[i] = null;
/* 217:    */     }
/* 218:188 */     d();
/* 219:189 */     return tr;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public ur a_(int i)
/* 223:    */   {
/* 224:193 */     if (this.contents[i] == null) {
/* 225:193 */       return null;
/* 226:    */     }
/* 227:194 */     ur ist = this.contents[i];
/* 228:195 */     this.contents[i] = null;
/* 229:196 */     return ist;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public void a(int i, ur ist)
/* 233:    */   {
/* 234:200 */     this.contents[i] = ist;
/* 235:201 */     if ((ist != null) && (ist.a > c())) {
/* 236:202 */       ist.a = c();
/* 237:    */     }
/* 238:203 */     d();
/* 239:    */   }
/* 240:    */   
/* 241:    */   public String b()
/* 242:    */   {
/* 243:207 */     return "Blulectric Alloy Furnace";
/* 244:    */   }
/* 245:    */   
/* 246:    */   public int c()
/* 247:    */   {
/* 248:211 */     return 64;
/* 249:    */   }
/* 250:    */   
/* 251:    */   public boolean a_(qx player)
/* 252:    */   {
/* 253:215 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 254:216 */       return false;
/* 255:    */     }
/* 256:217 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void f() {}
/* 260:    */   
/* 261:    */   public void l_() {}
/* 262:    */   
/* 263:    */   public int getStartInventorySide(ForgeDirection fd)
/* 264:    */   {
/* 265:228 */     int side = fd.ordinal();
/* 266:    */     
/* 267:230 */     int s = CoreLib.rotToSide(this.Rotation);
/* 268:231 */     if (side == 1) {
/* 269:231 */       return 0;
/* 270:    */     }
/* 271:232 */     if (side == (s ^ 0x1)) {
/* 272:232 */       return 9;
/* 273:    */     }
/* 274:233 */     return 0;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 278:    */   {
/* 279:238 */     int side = fd.ordinal();
/* 280:239 */     int s = CoreLib.rotToSide(this.Rotation);
/* 281:240 */     if (side == 1) {
/* 282:240 */       return 9;
/* 283:    */     }
/* 284:241 */     if (side == (s ^ 0x1)) {
/* 285:241 */       return 1;
/* 286:    */     }
/* 287:242 */     return 0;
/* 288:    */   }
/* 289:    */   
/* 290:    */   public void a(bq nbttagcompound)
/* 291:    */   {
/* 292:248 */     super.a(nbttagcompound);
/* 293:    */     
/* 294:250 */     by items = nbttagcompound.m("Items");
/* 295:251 */     this.contents = new ur[k_()];
/* 296:252 */     for (int i = 0; i < items.c(); i++)
/* 297:    */     {
/* 298:253 */       bq item = (bq)items.b(i);
/* 299:    */       
/* 300:255 */       int j = item.c("Slot") & 0xFF;
/* 301:256 */       if ((j >= 0) && (j < this.contents.length)) {
/* 302:257 */         this.contents[j] = ur.a(item);
/* 303:    */       }
/* 304:    */     }
/* 305:260 */     this.cooktime = nbttagcompound.d("CookTime");
/* 306:    */     
/* 307:262 */     this.cond.readFromNBT(nbttagcompound);
/* 308:    */   }
/* 309:    */   
/* 310:    */   public void b(bq nbttagcompound)
/* 311:    */   {
/* 312:266 */     super.b(nbttagcompound);
/* 313:    */     
/* 314:268 */     by items = new by();
/* 315:269 */     for (int i = 0; i < this.contents.length; i++) {
/* 316:270 */       if (this.contents[i] != null)
/* 317:    */       {
/* 318:271 */         bq item = new bq();
/* 319:272 */         item.a("Slot", (byte)i);
/* 320:273 */         this.contents[i].b(item);
/* 321:274 */         items.a(item);
/* 322:    */       }
/* 323:    */     }
/* 324:277 */     nbttagcompound.a("Items", items);
/* 325:278 */     nbttagcompound.a("CookTime", (short)this.cooktime);
/* 326:    */     
/* 327:280 */     this.cond.writeToNBT(nbttagcompound);
/* 328:    */   }
/* 329:    */   
/* 330:284 */   public int cooktime = 0;
/* 331:285 */   public int ConMask = -1;
/* 332:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileBlueAlloyFurnace
 * JD-Core Version:    0.7.0.1
 */