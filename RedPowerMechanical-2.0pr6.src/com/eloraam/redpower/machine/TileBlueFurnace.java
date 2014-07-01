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
/*  11:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  12:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  13:    */ import la;
/*  14:    */ import md;
/*  15:    */ import net.minecraftforge.common.ForgeDirection;
/*  16:    */ import net.minecraftforge.common.ISidedInventory;
/*  17:    */ import qx;
/*  18:    */ import up;
/*  19:    */ import ur;
/*  20:    */ import wj;
/*  21:    */ import yc;
/*  22:    */ 
/*  23:    */ public class TileBlueFurnace
/*  24:    */   extends TileAppliance
/*  25:    */   implements la, ISidedInventory, IBluePowerConnectable
/*  26:    */ {
/*  27:    */   public TileBlueFurnace()
/*  28:    */   {
/*  29: 28 */     this.contents = new ur[2];
/*  30:    */   }
/*  31:    */   
/*  32:    */   public int getConnectableMask()
/*  33:    */   {
/*  34: 34 */     return 1073741823;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public int getConnectClass(int side)
/*  38:    */   {
/*  39: 38 */     return 64;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public int getCornerPowerMode()
/*  43:    */   {
/*  44: 41 */     return 0;
/*  45:    */   }
/*  46:    */   
/*  47: 45 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  48:    */   {
/*  49:    */     public any getParent()
/*  50:    */     {
/*  51: 47 */       return TileBlueFurnace.this;
/*  52:    */     }
/*  53:    */   };
/*  54:    */   private ur[] contents;
/*  55:    */   
/*  56:    */   public BluePowerConductor getBlueConductor(int side)
/*  57:    */   {
/*  58: 52 */     return this.cond;
/*  59:    */   }
/*  60:    */   
/*  61:    */   void updateLight()
/*  62:    */   {
/*  63: 58 */     this.k.z(this.l, this.m, this.n);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public int getExtendedID()
/*  67:    */   {
/*  68: 62 */     return 1;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void g()
/*  72:    */   {
/*  73: 66 */     super.g();
/*  74: 67 */     if (CoreLib.isClient(this.k)) {
/*  75: 67 */       return;
/*  76:    */     }
/*  77: 69 */     if (this.ConMask < 0)
/*  78:    */     {
/*  79: 70 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/*  80:    */       
/*  81: 72 */       this.cond.recache(this.ConMask, 0);
/*  82:    */     }
/*  83: 74 */     this.cond.iterate();
/*  84: 75 */     dirtyBlock();
/*  85: 77 */     if (this.cond.getVoltage() < 60.0D)
/*  86:    */     {
/*  87: 78 */       if ((this.Active) && (this.cond.Flow == 0))
/*  88:    */       {
/*  89: 79 */         this.Active = false;
/*  90: 80 */         updateBlock();
/*  91: 81 */         updateLight();
/*  92:    */       }
/*  93: 83 */       return;
/*  94:    */     }
/*  95: 85 */     boolean cs = canSmelt();
/*  96: 86 */     if (cs)
/*  97:    */     {
/*  98: 87 */       if (!this.Active)
/*  99:    */       {
/* 100: 88 */         this.Active = true;
/* 101: 89 */         updateBlock();
/* 102: 90 */         updateLight();
/* 103:    */       }
/* 104: 92 */       this.cooktime += 1;
/* 105: 93 */       this.cond.drawPower(1000.0D);
/* 106: 94 */       if (this.cooktime >= 100)
/* 107:    */       {
/* 108: 95 */         this.cooktime = 0;
/* 109: 96 */         smeltItem();
/* 110: 97 */         d();
/* 111:    */       }
/* 112:    */     }
/* 113:    */     else
/* 114:    */     {
/* 115:100 */       if (this.Active)
/* 116:    */       {
/* 117:101 */         this.Active = false;
/* 118:102 */         updateBlock();
/* 119:103 */         updateLight();
/* 120:    */       }
/* 121:105 */       this.cooktime = 0;
/* 122:    */     }
/* 123:    */   }
/* 124:    */   
/* 125:    */   boolean canSmelt()
/* 126:    */   {
/* 127:110 */     if (this.contents[0] == null) {
/* 128:110 */       return false;
/* 129:    */     }
/* 130:111 */     ur ist = wj.a().getSmeltingResult(this.contents[0]);
/* 131:113 */     if (ist == null) {
/* 132:113 */       return false;
/* 133:    */     }
/* 134:115 */     if (this.contents[1] == null) {
/* 135:115 */       return true;
/* 136:    */     }
/* 137:116 */     if (!this.contents[1].a(ist)) {
/* 138:116 */       return false;
/* 139:    */     }
/* 140:118 */     int st = this.contents[1].a + ist.a;
/* 141:119 */     return (st <= c()) && (st <= ist.d());
/* 142:    */   }
/* 143:    */   
/* 144:    */   void smeltItem()
/* 145:    */   {
/* 146:124 */     if (!canSmelt()) {
/* 147:124 */       return;
/* 148:    */     }
/* 149:125 */     ur ist = wj.a().getSmeltingResult(this.contents[0]);
/* 150:127 */     if (this.contents[1] == null) {
/* 151:128 */       this.contents[1] = ist.l();
/* 152:129 */     } else if (this.contents[1].a(ist)) {
/* 153:130 */       this.contents[1].a += ist.a;
/* 154:    */     }
/* 155:132 */     if (this.contents[0].b().s()) {
/* 156:133 */       this.contents[0] = new ur(this.contents[0].b().r());
/* 157:    */     } else {
/* 158:136 */       this.contents[0].a -= 1;
/* 159:    */     }
/* 160:138 */     if (this.contents[0].a <= 0) {
/* 161:139 */       this.contents[0] = null;
/* 162:    */     }
/* 163:    */   }
/* 164:    */   
/* 165:    */   int getCookScaled(int i)
/* 166:    */   {
/* 167:143 */     return this.cooktime * i / 100;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public boolean onBlockActivated(qx player)
/* 171:    */   {
/* 172:149 */     if (player.ah()) {
/* 173:149 */       return false;
/* 174:    */     }
/* 175:150 */     if (CoreLib.isClient(this.k)) {
/* 176:151 */       return true;
/* 177:    */     }
/* 178:152 */     player.openGui(RedPowerMachine.instance, 3, this.k, this.l, this.m, this.n);
/* 179:    */     
/* 180:154 */     return true;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 184:    */   {
/* 185:159 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3);
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void onBlockRemoval()
/* 189:    */   {
/* 190:163 */     for (int i = 0; i < 2; i++)
/* 191:    */     {
/* 192:164 */       ur ist = this.contents[i];
/* 193:165 */       if ((ist != null) && (ist.a > 0)) {
/* 194:166 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 195:    */       }
/* 196:    */     }
/* 197:    */   }
/* 198:    */   
/* 199:    */   public void onBlockNeighborChange(int l)
/* 200:    */   {
/* 201:173 */     this.ConMask = -1;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public int k_()
/* 205:    */   {
/* 206:179 */     return 2;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public ur a(int i)
/* 210:    */   {
/* 211:183 */     return this.contents[i];
/* 212:    */   }
/* 213:    */   
/* 214:    */   public ur a(int i, int j)
/* 215:    */   {
/* 216:188 */     if (this.contents[i] == null) {
/* 217:188 */       return null;
/* 218:    */     }
/* 219:190 */     if (this.contents[i].a <= j)
/* 220:    */     {
/* 221:191 */       ur tr = this.contents[i];
/* 222:192 */       this.contents[i] = null;
/* 223:193 */       d();
/* 224:194 */       return tr;
/* 225:    */     }
/* 226:196 */     ur tr = this.contents[i].a(j);
/* 227:197 */     if (this.contents[i].a == 0) {
/* 228:198 */       this.contents[i] = null;
/* 229:    */     }
/* 230:199 */     d();
/* 231:200 */     return tr;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public ur a_(int i)
/* 235:    */   {
/* 236:204 */     if (this.contents[i] == null) {
/* 237:204 */       return null;
/* 238:    */     }
/* 239:205 */     ur ist = this.contents[i];
/* 240:206 */     this.contents[i] = null;
/* 241:207 */     return ist;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void a(int i, ur ist)
/* 245:    */   {
/* 246:211 */     this.contents[i] = ist;
/* 247:212 */     if ((ist != null) && (ist.a > c())) {
/* 248:213 */       ist.a = c();
/* 249:    */     }
/* 250:214 */     d();
/* 251:    */   }
/* 252:    */   
/* 253:    */   public String b()
/* 254:    */   {
/* 255:218 */     return "Blulectric Furnace";
/* 256:    */   }
/* 257:    */   
/* 258:    */   public int c()
/* 259:    */   {
/* 260:222 */     return 64;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public boolean a_(qx player)
/* 264:    */   {
/* 265:226 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 266:227 */       return false;
/* 267:    */     }
/* 268:228 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 269:    */   }
/* 270:    */   
/* 271:    */   public void f() {}
/* 272:    */   
/* 273:    */   public void l_() {}
/* 274:    */   
/* 275:    */   public int getStartInventorySide(ForgeDirection fd)
/* 276:    */   {
/* 277:238 */     int side = fd.ordinal();
/* 278:239 */     int s = CoreLib.rotToSide(this.Rotation);
/* 279:240 */     if (side == s) {
/* 280:240 */       return 0;
/* 281:    */     }
/* 282:241 */     if (side == (s ^ 0x1)) {
/* 283:241 */       return 1;
/* 284:    */     }
/* 285:242 */     return 0;
/* 286:    */   }
/* 287:    */   
/* 288:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 289:    */   {
/* 290:246 */     int side = fd.ordinal();
/* 291:247 */     int s = CoreLib.rotToSide(this.Rotation);
/* 292:248 */     if (side == s) {
/* 293:248 */       return 1;
/* 294:    */     }
/* 295:249 */     if (side == (s ^ 0x1)) {
/* 296:249 */       return 1;
/* 297:    */     }
/* 298:250 */     return 0;
/* 299:    */   }
/* 300:    */   
/* 301:    */   public void a(bq nbttagcompound)
/* 302:    */   {
/* 303:256 */     super.a(nbttagcompound);
/* 304:    */     
/* 305:258 */     by items = nbttagcompound.m("Items");
/* 306:259 */     this.contents = new ur[k_()];
/* 307:260 */     for (int i = 0; i < items.c(); i++)
/* 308:    */     {
/* 309:261 */       bq item = (bq)items.b(i);
/* 310:    */       
/* 311:263 */       int j = item.c("Slot") & 0xFF;
/* 312:264 */       if ((j >= 0) && (j < this.contents.length)) {
/* 313:265 */         this.contents[j] = ur.a(item);
/* 314:    */       }
/* 315:    */     }
/* 316:268 */     this.cooktime = nbttagcompound.d("CookTime");
/* 317:    */     
/* 318:270 */     this.cond.readFromNBT(nbttagcompound);
/* 319:    */   }
/* 320:    */   
/* 321:    */   public void b(bq nbttagcompound)
/* 322:    */   {
/* 323:274 */     super.b(nbttagcompound);
/* 324:    */     
/* 325:276 */     by items = new by();
/* 326:277 */     for (int i = 0; i < this.contents.length; i++) {
/* 327:278 */       if (this.contents[i] != null)
/* 328:    */       {
/* 329:279 */         bq item = new bq();
/* 330:280 */         item.a("Slot", (byte)i);
/* 331:281 */         this.contents[i].b(item);
/* 332:282 */         items.a(item);
/* 333:    */       }
/* 334:    */     }
/* 335:285 */     nbttagcompound.a("Items", items);
/* 336:286 */     nbttagcompound.a("CookTime", (short)this.cooktime);
/* 337:    */     
/* 338:288 */     this.cond.writeToNBT(nbttagcompound);
/* 339:    */   }
/* 340:    */   
/* 341:292 */   public int cooktime = 0;
/* 342:293 */   public int ConMask = -1;
/* 343:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileBlueFurnace
 * JD-Core Version:    0.7.0.1
 */