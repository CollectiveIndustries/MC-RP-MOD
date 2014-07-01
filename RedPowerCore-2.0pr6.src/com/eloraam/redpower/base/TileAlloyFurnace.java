/*   1:    */ package com.eloraam.redpower.base;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerBase;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.CraftLib;
/*   8:    */ import la;
/*   9:    */ import md;
/*  10:    */ import net.minecraftforge.common.ForgeDirection;
/*  11:    */ import net.minecraftforge.common.ISidedInventory;
/*  12:    */ import qx;
/*  13:    */ import up;
/*  14:    */ import ur;
/*  15:    */ import yc;
/*  16:    */ 
/*  17:    */ public class TileAlloyFurnace
/*  18:    */   extends TileAppliance
/*  19:    */   implements la, ISidedInventory
/*  20:    */ {
/*  21:    */   public TileAlloyFurnace()
/*  22:    */   {
/*  23: 26 */     this.contents = new ur[11];
/*  24:    */   }
/*  25:    */   
/*  26:    */   void updateLight()
/*  27:    */   {
/*  28: 32 */     this.k.z(this.l, this.m, this.n);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public int getExtendedID()
/*  32:    */   {
/*  33: 36 */     return 0;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void g()
/*  37:    */   {
/*  38: 40 */     super.g();
/*  39: 41 */     boolean btu = false;
/*  40: 43 */     if (this.burntime > 0)
/*  41:    */     {
/*  42: 44 */       this.burntime -= 1;
/*  43: 45 */       if (this.burntime == 0)
/*  44:    */       {
/*  45: 46 */         btu = true;
/*  46: 47 */         this.Active = false;
/*  47:    */       }
/*  48:    */     }
/*  49: 50 */     if (CoreLib.isClient(this.k)) {
/*  50: 50 */       return;
/*  51:    */     }
/*  52: 52 */     boolean cs = canSmelt();
/*  53: 53 */     if ((this.burntime == 0) && (cs) && (this.contents[9] != null))
/*  54:    */     {
/*  55: 54 */       this.burntime = (this.totalburn = CoreLib.getBurnTime(this.contents[9]));
/*  56: 55 */       if (this.burntime > 0)
/*  57:    */       {
/*  58: 56 */         this.Active = true;
/*  59: 57 */         if (this.contents[9].b().s()) {
/*  60: 58 */           this.contents[9] = new ur(this.contents[9].b().r());
/*  61:    */         } else {
/*  62: 59 */           this.contents[9].a -= 1;
/*  63:    */         }
/*  64: 60 */         if (this.contents[9].a == 0) {
/*  65: 61 */           this.contents[9] = null;
/*  66:    */         }
/*  67: 62 */         if (!btu)
/*  68:    */         {
/*  69: 63 */           d();
/*  70: 64 */           updateBlock();
/*  71: 65 */           updateLight();
/*  72:    */         }
/*  73:    */       }
/*  74:    */     }
/*  75: 69 */     if ((this.burntime > 0) && (cs))
/*  76:    */     {
/*  77: 70 */       this.cooktime += 1;
/*  78: 71 */       if (this.cooktime == 200)
/*  79:    */       {
/*  80: 72 */         this.cooktime = 0;
/*  81: 73 */         smeltItem();
/*  82: 74 */         d();
/*  83:    */       }
/*  84:    */     }
/*  85:    */     else
/*  86:    */     {
/*  87: 76 */       this.cooktime = 0;
/*  88:    */     }
/*  89: 77 */     if (btu)
/*  90:    */     {
/*  91: 78 */       updateBlock();
/*  92: 79 */       updateLight();
/*  93:    */     }
/*  94:    */   }
/*  95:    */   
/*  96:    */   boolean canSmelt()
/*  97:    */   {
/*  98: 84 */     ur ist = CraftLib.getAlloyResult(this.contents, 0, 9, false);
/*  99: 85 */     if (ist == null) {
/* 100: 85 */       return false;
/* 101:    */     }
/* 102: 86 */     if (this.contents[10] == null) {
/* 103: 86 */       return true;
/* 104:    */     }
/* 105: 87 */     if (!this.contents[10].a(ist)) {
/* 106: 87 */       return false;
/* 107:    */     }
/* 108: 89 */     int st = this.contents[10].a + ist.a;
/* 109: 90 */     return (st <= c()) && (st <= ist.d());
/* 110:    */   }
/* 111:    */   
/* 112:    */   void smeltItem()
/* 113:    */   {
/* 114: 95 */     if (!canSmelt()) {
/* 115: 95 */       return;
/* 116:    */     }
/* 117: 97 */     ur ist = CraftLib.getAlloyResult(this.contents, 0, 9, true);
/* 118: 98 */     if (this.contents[10] == null) {
/* 119: 99 */       this.contents[10] = ist.l();
/* 120:    */     } else {
/* 121:101 */       this.contents[10].a += ist.a;
/* 122:    */     }
/* 123:    */   }
/* 124:    */   
/* 125:    */   int getCookScaled(int i)
/* 126:    */   {
/* 127:106 */     return this.cooktime * i / 200;
/* 128:    */   }
/* 129:    */   
/* 130:    */   int getBurnScaled(int i)
/* 131:    */   {
/* 132:110 */     if (this.totalburn == 0) {
/* 133:110 */       return 0;
/* 134:    */     }
/* 135:111 */     return this.burntime * i / this.totalburn;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public boolean onBlockActivated(qx player)
/* 139:    */   {
/* 140:117 */     if (player.ah()) {
/* 141:117 */       return false;
/* 142:    */     }
/* 143:118 */     if (CoreLib.isClient(this.k)) {
/* 144:119 */       return true;
/* 145:    */     }
/* 146:120 */     player.openGui(RedPowerBase.instance, 1, this.k, this.l, this.m, this.n);
/* 147:    */     
/* 148:122 */     return true;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 152:    */   {
/* 153:127 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3);
/* 154:    */   }
/* 155:    */   
/* 156:    */   public void onBlockRemoval()
/* 157:    */   {
/* 158:131 */     for (int i = 0; i < 11; i++)
/* 159:    */     {
/* 160:132 */       ur ist = this.contents[i];
/* 161:133 */       if ((ist != null) && (ist.a > 0)) {
/* 162:134 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 163:    */       }
/* 164:    */     }
/* 165:    */   }
/* 166:    */   
/* 167:    */   public int k_()
/* 168:    */   {
/* 169:143 */     return 11;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public ur a(int i)
/* 173:    */   {
/* 174:147 */     return this.contents[i];
/* 175:    */   }
/* 176:    */   
/* 177:    */   public ur a(int i, int j)
/* 178:    */   {
/* 179:152 */     if (this.contents[i] == null) {
/* 180:152 */       return null;
/* 181:    */     }
/* 182:154 */     if (this.contents[i].a <= j)
/* 183:    */     {
/* 184:155 */       ur tr = this.contents[i];
/* 185:156 */       this.contents[i] = null;
/* 186:157 */       d();
/* 187:158 */       return tr;
/* 188:    */     }
/* 189:160 */     ur tr = this.contents[i].a(j);
/* 190:161 */     if (this.contents[i].a == 0) {
/* 191:162 */       this.contents[i] = null;
/* 192:    */     }
/* 193:163 */     d();
/* 194:164 */     return tr;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public ur a_(int i)
/* 198:    */   {
/* 199:168 */     if (this.contents[i] == null) {
/* 200:168 */       return null;
/* 201:    */     }
/* 202:169 */     ur ist = this.contents[i];
/* 203:170 */     this.contents[i] = null;
/* 204:171 */     return ist;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public void a(int i, ur ist)
/* 208:    */   {
/* 209:175 */     this.contents[i] = ist;
/* 210:176 */     if ((ist != null) && (ist.a > c())) {
/* 211:177 */       ist.a = c();
/* 212:    */     }
/* 213:178 */     d();
/* 214:    */   }
/* 215:    */   
/* 216:    */   public String b()
/* 217:    */   {
/* 218:182 */     return "AlloyFurnace";
/* 219:    */   }
/* 220:    */   
/* 221:    */   public int c()
/* 222:    */   {
/* 223:186 */     return 64;
/* 224:    */   }
/* 225:    */   
/* 226:    */   public boolean a_(qx player)
/* 227:    */   {
/* 228:190 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 229:191 */       return false;
/* 230:    */     }
/* 231:192 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void f() {}
/* 235:    */   
/* 236:    */   public void l_() {}
/* 237:    */   
/* 238:    */   public int getStartInventorySide(ForgeDirection fd)
/* 239:    */   {
/* 240:202 */     int side = fd.ordinal();
/* 241:204 */     if (side == 1) {
/* 242:204 */       return 0;
/* 243:    */     }
/* 244:205 */     int s = CoreLib.rotToSide(this.Rotation);
/* 245:206 */     if (side == s) {
/* 246:206 */       return 9;
/* 247:    */     }
/* 248:207 */     if (side == (s ^ 0x1)) {
/* 249:207 */       return 10;
/* 250:    */     }
/* 251:208 */     return 0;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 255:    */   {
/* 256:212 */     int side = fd.ordinal();
/* 257:214 */     if (side == 1) {
/* 258:214 */       return 9;
/* 259:    */     }
/* 260:215 */     int s = CoreLib.rotToSide(this.Rotation);
/* 261:216 */     if (side == s) {
/* 262:216 */       return 1;
/* 263:    */     }
/* 264:217 */     if (side == (s ^ 0x1)) {
/* 265:217 */       return 1;
/* 266:    */     }
/* 267:218 */     return 0;
/* 268:    */   }
/* 269:    */   
/* 270:    */   public void a(bq nbttagcompound)
/* 271:    */   {
/* 272:224 */     super.a(nbttagcompound);
/* 273:    */     
/* 274:226 */     by items = nbttagcompound.m("Items");
/* 275:227 */     this.contents = new ur[k_()];
/* 276:228 */     for (int i = 0; i < items.c(); i++)
/* 277:    */     {
/* 278:229 */       bq item = (bq)items.b(i);
/* 279:    */       
/* 280:231 */       int j = item.c("Slot") & 0xFF;
/* 281:232 */       if ((j >= 0) && (j < this.contents.length)) {
/* 282:233 */         this.contents[j] = ur.a(item);
/* 283:    */       }
/* 284:    */     }
/* 285:236 */     this.totalburn = nbttagcompound.d("TotalBurn");
/* 286:237 */     this.burntime = nbttagcompound.d("BurnTime");
/* 287:238 */     this.cooktime = nbttagcompound.d("CookTime");
/* 288:    */   }
/* 289:    */   
/* 290:    */   public void b(bq nbttagcompound)
/* 291:    */   {
/* 292:242 */     super.b(nbttagcompound);
/* 293:    */     
/* 294:244 */     by items = new by();
/* 295:245 */     for (int i = 0; i < this.contents.length; i++) {
/* 296:246 */       if (this.contents[i] != null)
/* 297:    */       {
/* 298:247 */         bq item = new bq();
/* 299:248 */         item.a("Slot", (byte)i);
/* 300:249 */         this.contents[i].b(item);
/* 301:250 */         items.a(item);
/* 302:    */       }
/* 303:    */     }
/* 304:253 */     nbttagcompound.a("Items", items);
/* 305:254 */     nbttagcompound.a("TotalBurn", (short)this.totalburn);
/* 306:255 */     nbttagcompound.a("BurnTime", (short)this.burntime);
/* 307:256 */     nbttagcompound.a("CookTime", (short)this.cooktime);
/* 308:    */   }
/* 309:    */   
/* 310:260 */   public int cooktime = 0;
/* 311:260 */   public int burntime = 0;
/* 312:260 */   public int totalburn = 0;
/* 313:    */   private ur[] contents;
/* 314:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.TileAlloyFurnace
 * JD-Core Version:    0.7.0.1
 */