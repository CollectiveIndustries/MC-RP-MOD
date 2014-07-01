/*   1:    */ package com.eloraam.redpower.world;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import bq;
/*   5:    */ import by;
/*   6:    */ import com.eloraam.redpower.RedPowerWorld;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.WorldCoord;
/*   9:    */ import cpw.mods.fml.relauncher.Side;
/*  10:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  11:    */ import java.util.List;
/*  12:    */ import la;
/*  13:    */ import net.minecraftforge.common.ForgeDirection;
/*  14:    */ import net.minecraftforge.common.IPlantable;
/*  15:    */ import qv;
/*  16:    */ import qx;
/*  17:    */ import tj;
/*  18:    */ import up;
/*  19:    */ import ur;
/*  20:    */ import yc;
/*  21:    */ 
/*  22:    */ public class ItemSeedBag
/*  23:    */   extends up
/*  24:    */ {
/*  25:    */   public ItemSeedBag(int i)
/*  26:    */   {
/*  27: 24 */     super(i);
/*  28: 25 */     e(576);
/*  29: 26 */     d(1);
/*  30: 27 */     setTextureFile("/eloraam/world/worlditems1.png");
/*  31: 28 */     b("rpSeedBag");
/*  32: 29 */     a(tj.f);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public static class InventorySeedBag
/*  36:    */     implements la
/*  37:    */   {
/*  38:    */     ur bagitem;
/*  39:    */     ur[] items;
/*  40:    */     
/*  41:    */     InventorySeedBag(ur ist)
/*  42:    */     {
/*  43: 37 */       this.bagitem = ist;
/*  44: 38 */       unpackInventory();
/*  45:    */     }
/*  46:    */     
/*  47:    */     void unpackInventory()
/*  48:    */     {
/*  49: 42 */       this.items = new ur[9];
/*  50: 43 */       if (this.bagitem.d == null) {
/*  51: 44 */         return;
/*  52:    */       }
/*  53: 45 */       by list = this.bagitem.d.m("contents");
/*  54: 47 */       for (int i = 0; i < list.c(); i++)
/*  55:    */       {
/*  56: 48 */         bq item = (bq)list.b(i);
/*  57:    */         
/*  58: 50 */         int slt = item.c("Slot");
/*  59: 51 */         if (slt < 9) {
/*  60: 52 */           this.items[slt] = ur.a(item);
/*  61:    */         }
/*  62:    */       }
/*  63:    */     }
/*  64:    */     
/*  65:    */     void packInventory()
/*  66:    */     {
/*  67: 57 */       if (this.bagitem.d == null) {
/*  68: 58 */         this.bagitem.d(new bq());
/*  69:    */       }
/*  70: 61 */       int itc = 0;
/*  71: 62 */       by contents = new by();
/*  72: 63 */       for (int i = 0; i < 9; i++) {
/*  73: 64 */         if (this.items[i] != null)
/*  74:    */         {
/*  75: 65 */           itc += this.items[i].a;
/*  76: 66 */           bq cpd = new bq();
/*  77: 67 */           this.items[i].b(cpd);
/*  78: 68 */           cpd.a("Slot", (byte)i);
/*  79: 69 */           contents.a(cpd);
/*  80:    */         }
/*  81:    */       }
/*  82: 71 */       this.bagitem.d.a("contents", contents);
/*  83: 72 */       this.bagitem.b(itc == 0 ? 0 : 577 - itc);
/*  84:    */     }
/*  85:    */     
/*  86:    */     public int k_()
/*  87:    */     {
/*  88: 75 */       return 9;
/*  89:    */     }
/*  90:    */     
/*  91:    */     public ur a(int slot)
/*  92:    */     {
/*  93: 78 */       return this.items[slot];
/*  94:    */     }
/*  95:    */     
/*  96:    */     public ur a(int slot, int num)
/*  97:    */     {
/*  98: 83 */       if (this.items[slot] == null) {
/*  99: 83 */         return null;
/* 100:    */       }
/* 101: 84 */       if (this.items[slot].a <= num)
/* 102:    */       {
/* 103: 85 */         ur tr = this.items[slot];this.items[slot] = null;
/* 104: 86 */         d();
/* 105: 87 */         return tr;
/* 106:    */       }
/* 107: 89 */       ur tr = this.items[slot].a(num);
/* 108: 90 */       if (this.items[slot].a == 0) {
/* 109: 91 */         this.items[slot] = null;
/* 110:    */       }
/* 111: 92 */       d();
/* 112: 93 */       return tr;
/* 113:    */     }
/* 114:    */     
/* 115:    */     public ur a_(int slot)
/* 116:    */     {
/* 117: 97 */       if (this.items[slot] == null) {
/* 118: 97 */         return null;
/* 119:    */       }
/* 120: 98 */       ur tr = this.items[slot];
/* 121: 99 */       this.items[slot] = null;
/* 122:100 */       return tr;
/* 123:    */     }
/* 124:    */     
/* 125:    */     public void a(int slot, ur ist)
/* 126:    */     {
/* 127:104 */       this.items[slot] = ist;
/* 128:105 */       if ((ist != null) && (ist.a > c())) {
/* 129:106 */         ist.a = c();
/* 130:    */       }
/* 131:107 */       d();
/* 132:    */     }
/* 133:    */     
/* 134:    */     public String b()
/* 135:    */     {
/* 136:109 */       return "Seed Bag";
/* 137:    */     }
/* 138:    */     
/* 139:    */     public int c()
/* 140:    */     {
/* 141:110 */       return 64;
/* 142:    */     }
/* 143:    */     
/* 144:    */     public void d()
/* 145:    */     {
/* 146:112 */       packInventory();
/* 147:    */     }
/* 148:    */     
/* 149:    */     public boolean a_(qx pl)
/* 150:    */     {
/* 151:114 */       return true;
/* 152:    */     }
/* 153:    */     
/* 154:    */     public void l_() {}
/* 155:    */     
/* 156:    */     public void f() {}
/* 157:    */   }
/* 158:    */   
/* 159:    */   public static la getBagInventory(ur ist)
/* 160:    */   {
/* 161:121 */     if (!(ist.b() instanceof ItemSeedBag)) {
/* 162:122 */       return null;
/* 163:    */     }
/* 164:123 */     return new InventorySeedBag(ist);
/* 165:    */   }
/* 166:    */   
/* 167:    */   public static boolean canAdd(la inv, ur ist)
/* 168:    */   {
/* 169:127 */     if (!(ist.b() instanceof IPlantable)) {
/* 170:128 */       return false;
/* 171:    */     }
/* 172:129 */     for (int i = 0; i < inv.k_(); i++)
/* 173:    */     {
/* 174:130 */       ur is2 = inv.a(i);
/* 175:131 */       if ((is2 != null) && (is2.a != 0) && 
/* 176:132 */         (CoreLib.compareItemStack(is2, ist) != 0)) {
/* 177:133 */         return false;
/* 178:    */       }
/* 179:    */     }
/* 180:135 */     return true;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public static ur getPlant(la inv)
/* 184:    */   {
/* 185:139 */     for (int i = 0; i < inv.k_(); i++)
/* 186:    */     {
/* 187:140 */       ur is2 = inv.a(i);
/* 188:141 */       if ((is2 != null) && (is2.a != 0)) {
/* 189:142 */         return is2;
/* 190:    */       }
/* 191:    */     }
/* 192:144 */     return null;
/* 193:    */   }
/* 194:    */   
/* 195:    */   private static void decrPlant(la inv)
/* 196:    */   {
/* 197:148 */     for (int i = 0; i < inv.k_(); i++)
/* 198:    */     {
/* 199:149 */       ur is2 = inv.a(i);
/* 200:150 */       if ((is2 != null) && (is2.a != 0))
/* 201:    */       {
/* 202:151 */         inv.a(i, 1);
/* 203:152 */         break;
/* 204:    */       }
/* 205:    */     }
/* 206:    */   }
/* 207:    */   
/* 208:    */   public int c_(ur par1ItemStack)
/* 209:    */   {
/* 210:160 */     return 1;
/* 211:    */   }
/* 212:    */   
/* 213:    */   @SideOnly(Side.CLIENT)
/* 214:    */   public int b(int par1)
/* 215:    */   {
/* 216:166 */     return par1 > 0 ? 129 : 128;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public ur a(ur ist, yc world, qx player)
/* 220:    */   {
/* 221:172 */     if (CoreLib.isClient(world)) {
/* 222:172 */       return ist;
/* 223:    */     }
/* 224:173 */     if (!player.ah()) {
/* 225:174 */       return ist;
/* 226:    */     }
/* 227:175 */     player.openGui(RedPowerWorld.instance, 1, world, 0, 0, 0);
/* 228:176 */     return ist;
/* 229:    */   }
/* 230:    */   
/* 231:    */   public static class SpiralSearch
/* 232:    */   {
/* 233:    */     int curs;
/* 234:    */     int rem;
/* 235:    */     int ln;
/* 236:    */     int steps;
/* 237:    */     public WorldCoord point;
/* 238:    */     
/* 239:    */     public SpiralSearch(WorldCoord start, int size)
/* 240:    */     {
/* 241:181 */       this.point = start;
/* 242:182 */       this.curs = 0;this.rem = 1;
/* 243:183 */       this.ln = 1;
/* 244:184 */       this.steps = (size * size);
/* 245:    */     }
/* 246:    */     
/* 247:    */     public boolean again()
/* 248:    */     {
/* 249:187 */       return this.steps > 0;
/* 250:    */     }
/* 251:    */     
/* 252:    */     public boolean step()
/* 253:    */     {
/* 254:191 */       if (--this.steps == 0) {
/* 255:191 */         return false;
/* 256:    */       }
/* 257:193 */       this.rem -= 1;
/* 258:194 */       switch (this.curs)
/* 259:    */       {
/* 260:    */       case 0: 
/* 261:195 */         this.point.step(2); break;
/* 262:    */       case 1: 
/* 263:196 */         this.point.step(4); break;
/* 264:    */       case 2: 
/* 265:197 */         this.point.step(3); break;
/* 266:    */       default: 
/* 267:198 */         this.point.step(5);
/* 268:    */       }
/* 269:200 */       if (this.rem > 0) {
/* 270:200 */         return true;
/* 271:    */       }
/* 272:202 */       this.curs = (this.curs + 1 & 0x3);
/* 273:203 */       this.rem = this.ln;
/* 274:204 */       if ((this.curs & 0x1) > 0) {
/* 275:205 */         this.ln += 1;
/* 276:    */       }
/* 277:206 */       return true;
/* 278:    */     }
/* 279:    */   }
/* 280:    */   
/* 281:    */   public boolean a(ur ist, qx player, yc world, int x, int y, int z, int side, float par8, float par9, float par10)
/* 282:    */   {
/* 283:218 */     if (side != 1) {
/* 284:218 */       return false;
/* 285:    */     }
/* 286:219 */     if (CoreLib.isClient(world)) {
/* 287:219 */       return false;
/* 288:    */     }
/* 289:220 */     if (player.ah()) {
/* 290:220 */       return false;
/* 291:    */     }
/* 292:222 */     la baginv = getBagInventory(ist);
/* 293:    */     
/* 294:224 */     SpiralSearch search = new SpiralSearch(new WorldCoord(x, y, z), 5);
/* 295:    */     
/* 296:226 */     boolean st = false;
/* 297:227 */     for (; search.again(); search.step())
/* 298:    */     {
/* 299:228 */       int wp = world.a(search.point.x, search.point.y, search.point.z);
/* 300:    */       
/* 301:230 */       amq soil = amq.p[wp];
/* 302:231 */       if (soil == null)
/* 303:    */       {
/* 304:232 */         if (!st) {
/* 305:    */           break;
/* 306:    */         }
/* 307:    */       }
/* 308:    */       else
/* 309:    */       {
/* 310:235 */         ur plantstk = getPlant(baginv);
/* 311:236 */         if ((plantstk == null) || 
/* 312:237 */           (!(plantstk.b() instanceof IPlantable))) {
/* 313:    */           break;
/* 314:    */         }
/* 315:238 */         IPlantable plant = (IPlantable)plantstk.b();
/* 316:240 */         if ((soil == null) || (!soil.canSustainPlant(world, search.point.x, search.point.y, search.point.z, ForgeDirection.UP, plant)))
/* 317:    */         {
/* 318:244 */           if (!st) {
/* 319:    */             break;
/* 320:    */           }
/* 321:    */         }
/* 322:    */         else
/* 323:    */         {
/* 324:248 */           int wp2 = world.a(search.point.x, search.point.y + 1, search.point.z);
/* 325:250 */           if ((wp2 != 0) && (!amq.p[wp].isAirBlock(world, search.point.x, search.point.y + 1, search.point.z)))
/* 326:    */           {
/* 327:253 */             if (!st) {
/* 328:    */               break;
/* 329:    */             }
/* 330:    */           }
/* 331:    */           else
/* 332:    */           {
/* 333:256 */             st = true;
/* 334:    */             
/* 335:258 */             world.c(search.point.x, search.point.y + 1, search.point.z, plant.getPlantID(world, search.point.x, search.point.y + 1, search.point.z), plant.getPlantMetadata(world, search.point.x, search.point.y + 1, search.point.z));
/* 336:265 */             if (!player.cd.d) {
/* 337:266 */               decrPlant(baginv);
/* 338:    */             }
/* 339:    */           }
/* 340:    */         }
/* 341:    */       }
/* 342:    */     }
/* 343:269 */     return true;
/* 344:    */   }
/* 345:    */   
/* 346:    */   public void a(ur ist, qx player, List lines, boolean par4)
/* 347:    */   {
/* 348:276 */     if ((ist.d == null) || (ist.j() == 0)) {
/* 349:277 */       return;
/* 350:    */     }
/* 351:279 */     la baginv = getBagInventory(ist);
/* 352:280 */     for (int i = 0; i < baginv.k_(); i++)
/* 353:    */     {
/* 354:281 */       ur is2 = baginv.a(i);
/* 355:282 */       if ((is2 != null) && (is2.a != 0))
/* 356:    */       {
/* 357:284 */         lines.add(is2.b().l(is2));
/* 358:285 */         return;
/* 359:    */       }
/* 360:    */     }
/* 361:    */   }
/* 362:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemSeedBag
 * JD-Core Version:    0.7.0.1
 */