/*   1:    */ package com.eloraam.redpower.base;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import any;
/*   5:    */ import aoh;
/*   6:    */ import aoi;
/*   7:    */ import com.eloraam.redpower.RedPowerBase;
/*   8:    */ import com.eloraam.redpower.core.CoreLib;
/*   9:    */ import com.eloraam.redpower.core.CoverLib;
/*  10:    */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  11:    */ import com.eloraam.redpower.core.ICoverable;
/*  12:    */ import com.eloraam.redpower.core.IMicroPlacement;
/*  13:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  14:    */ import com.eloraam.redpower.core.WorldCoord;
/*  15:    */ import cpw.mods.fml.relauncher.Side;
/*  16:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  17:    */ import java.util.List;
/*  18:    */ import qx;
/*  19:    */ import tj;
/*  20:    */ import ur;
/*  21:    */ import vq;
/*  22:    */ import yc;
/*  23:    */ 
/*  24:    */ public class ItemMicro
/*  25:    */   extends vq
/*  26:    */ {
/*  27:    */   public ItemMicro(int i)
/*  28:    */   {
/*  29: 25 */     super(i);
/*  30: 26 */     e(0);
/*  31: 27 */     a(true);
/*  32:    */   }
/*  33:    */   
/*  34:    */   private boolean useCover(ur ist, qx player, yc world, int i, int j, int k, int l)
/*  35:    */   {
/*  36: 41 */     aoh pos = CoreLib.retraceBlock(world, player, i, j, k);
/*  37: 43 */     if (pos == null) {
/*  38: 43 */       return false;
/*  39:    */     }
/*  40: 44 */     if (pos.a != aoi.a) {
/*  41: 44 */       return false;
/*  42:    */     }
/*  43: 46 */     pos = CoverLib.getPlacement(world, pos, ist.j());
/*  44: 47 */     if (pos == null) {
/*  45: 47 */       return false;
/*  46:    */     }
/*  47: 49 */     if (world.a(ist.c, pos.b, pos.c, pos.d, false, l, player)) {
/*  48: 51 */       world.c(pos.b, pos.c, pos.d, RedPowerBase.blockMicro.cm, 0);
/*  49:    */     }
/*  50: 55 */     any te = world.q(pos.b, pos.c, pos.d);
/*  51: 57 */     if (!(te instanceof ICoverable)) {
/*  52: 57 */       return false;
/*  53:    */     }
/*  54: 58 */     ICoverable icv = (ICoverable)te;
/*  55: 60 */     if (icv.tryAddCover(pos.subHit, CoverLib.damageToCoverValue(ist.j())))
/*  56:    */     {
/*  57: 62 */       ist.a -= 1;
/*  58: 63 */       CoreLib.placeNoise(world, pos.b, pos.c, pos.d, CoverLib.getBlock(ist.j() & 0xFF).cm);
/*  59:    */       
/*  60:    */ 
/*  61: 66 */       RedPowerLib.updateIndirectNeighbors(world, pos.b, pos.c, pos.d, RedPowerBase.blockMicro.cm);
/*  62:    */       
/*  63:    */ 
/*  64: 69 */       world.i(pos.b, pos.c, pos.d);
/*  65:    */       
/*  66: 71 */       return true;
/*  67:    */     }
/*  68: 73 */     return false;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public boolean a(yc world, int x, int y, int z, int side, qx player, ur ist)
/*  72:    */   {
/*  73: 80 */     return true;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public boolean a(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/*  77:    */   {
/*  78:139 */     if (player == null) {
/*  79:139 */       return false;
/*  80:    */     }
/*  81:140 */     if (player.ah()) {
/*  82:140 */       return false;
/*  83:    */     }
/*  84:141 */     return itemUseShared(ist, player, world, i, j, k, l);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public boolean onItemUseFirst(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/*  88:    */   {
/*  89:149 */     if (CoreLib.isClient(world)) {
/*  90:149 */       return false;
/*  91:    */     }
/*  92:150 */     if (!player.ah()) {
/*  93:150 */       return false;
/*  94:    */     }
/*  95:151 */     return itemUseShared(ist, player, world, i, j, k, l);
/*  96:    */   }
/*  97:    */   
/*  98:    */   private boolean itemUseShared(ur ist, qx player, yc world, int i, int j, int k, int l)
/*  99:    */   {
/* 100:208 */     int hb = ist.j();
/* 101:209 */     int lb = hb & 0xFF;hb >>= 8;
/* 102:211 */     if ((hb == 0) || ((hb >= 16) && (hb <= 45))) {
/* 103:212 */       return useCover(ist, player, world, i, j, k, l);
/* 104:    */     }
/* 105:213 */     if (this.placers[hb] == null) {
/* 106:213 */       return false;
/* 107:    */     }
/* 108:215 */     return this.placers[hb].onPlaceMicro(ist, player, world, new WorldCoord(i, j, k), l);
/* 109:    */   }
/* 110:    */   
/* 111:    */   private String getMicroName(int hb)
/* 112:    */   {
/* 113:281 */     switch (hb)
/* 114:    */     {
/* 115:    */     case 0: 
/* 116:282 */       return "rpcover";
/* 117:    */     case 16: 
/* 118:283 */       return "rppanel";
/* 119:    */     case 17: 
/* 120:284 */       return "rpslab";
/* 121:    */     case 18: 
/* 122:285 */       return "rpcovc";
/* 123:    */     case 19: 
/* 124:286 */       return "rppanc";
/* 125:    */     case 20: 
/* 126:287 */       return "rpslabc";
/* 127:    */     case 21: 
/* 128:288 */       return "rpcovs";
/* 129:    */     case 22: 
/* 130:289 */       return "rppans";
/* 131:    */     case 23: 
/* 132:290 */       return "rpslabs";
/* 133:    */     case 24: 
/* 134:291 */       return "rphcover";
/* 135:    */     case 25: 
/* 136:292 */       return "rphpanel";
/* 137:    */     case 26: 
/* 138:293 */       return "rphslab";
/* 139:    */     case 27: 
/* 140:295 */       return "rpcov3";
/* 141:    */     case 28: 
/* 142:296 */       return "rpcov5";
/* 143:    */     case 29: 
/* 144:297 */       return "rpcov6";
/* 145:    */     case 30: 
/* 146:298 */       return "rpcov7";
/* 147:    */     case 31: 
/* 148:300 */       return "rphcov3";
/* 149:    */     case 32: 
/* 150:301 */       return "rphcov5";
/* 151:    */     case 33: 
/* 152:302 */       return "rphcov6";
/* 153:    */     case 34: 
/* 154:303 */       return "rphcov7";
/* 155:    */     case 35: 
/* 156:305 */       return "rpcov3c";
/* 157:    */     case 36: 
/* 158:306 */       return "rpcov5c";
/* 159:    */     case 37: 
/* 160:307 */       return "rpcov6c";
/* 161:    */     case 38: 
/* 162:308 */       return "rpcov7c";
/* 163:    */     case 39: 
/* 164:310 */       return "rpcov3s";
/* 165:    */     case 40: 
/* 166:311 */       return "rpcov5s";
/* 167:    */     case 41: 
/* 168:312 */       return "rpcov6s";
/* 169:    */     case 42: 
/* 170:313 */       return "rpcov7s";
/* 171:    */     case 43: 
/* 172:315 */       return "rppole1";
/* 173:    */     case 44: 
/* 174:316 */       return "rppole2";
/* 175:    */     case 45: 
/* 176:317 */       return "rppole3";
/* 177:    */     }
/* 178:319 */     return null;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public String d(ur ist)
/* 182:    */   {
/* 183:325 */     int hb = ist.j();
/* 184:326 */     int lb = hb & 0xFF;hb >>= 8;
/* 185:    */     
/* 186:328 */     String stub = getMicroName(hb);
/* 187:329 */     if (stub != null)
/* 188:    */     {
/* 189:330 */       String name = CoverLib.getName(lb);
/* 190:331 */       if (name == null) {
/* 191:331 */         throw new IndexOutOfBoundsException();
/* 192:    */       }
/* 193:332 */       return "tile." + stub + "." + name;
/* 194:    */     }
/* 195:334 */     if (this.placers[hb] == null) {
/* 196:335 */       throw new IndexOutOfBoundsException();
/* 197:    */     }
/* 198:336 */     String name = this.placers[hb].getMicroName(hb, lb);
/* 199:337 */     if (name == null) {
/* 200:338 */       throw new IndexOutOfBoundsException();
/* 201:    */     }
/* 202:339 */     return name;
/* 203:    */   }
/* 204:    */   
/* 205:    */   public void registerPlacement(int md, IMicroPlacement imp)
/* 206:    */   {
/* 207:343 */     this.placers[md] = imp;
/* 208:    */   }
/* 209:    */   
/* 210:    */   @SideOnly(Side.CLIENT)
/* 211:    */   public void a(int id, tj tab, List list)
/* 212:    */   {
/* 213:349 */     if ((tab == CreativeExtraTabs.tabWires) || (tab == CreativeExtraTabs.tabMachine))
/* 214:    */     {
/* 215:351 */       for (int i = 0; i < 255; i++) {
/* 216:352 */         if (this.placers[i] != null) {
/* 217:353 */           this.placers[i].addCreativeItems(i, tab, list);
/* 218:    */         }
/* 219:    */       }
/* 220:    */     }
/* 221:355 */     else if (tab == CreativeExtraTabs.tabMicros)
/* 222:    */     {
/* 223:356 */       for (int i = 0; i < 255; i++)
/* 224:    */       {
/* 225:357 */         String stub = CoverLib.getName(i);
/* 226:358 */         if (stub != null) {
/* 227:359 */           list.add(new ur(RedPowerBase.blockMicro, 1, i));
/* 228:    */         }
/* 229:    */       }
/* 230:363 */       for (int i = 1; i < 255; i++)
/* 231:    */       {
/* 232:364 */         String stub = getMicroName(i);
/* 233:365 */         if (stub != null) {
/* 234:366 */           list.add(new ur(RedPowerBase.blockMicro, 1, i << 8));
/* 235:    */         }
/* 236:    */       }
/* 237:370 */       for (int i = 1; i < 255; i++)
/* 238:    */       {
/* 239:371 */         String stub = getMicroName(i);
/* 240:372 */         if (stub != null) {
/* 241:373 */           list.add(new ur(RedPowerBase.blockMicro, 1, i << 8 | 0x2));
/* 242:    */         }
/* 243:    */       }
/* 244:377 */       for (int i = 1; i < 255; i++)
/* 245:    */       {
/* 246:378 */         String stub = getMicroName(i);
/* 247:379 */         if (stub != null) {
/* 248:380 */           list.add(new ur(RedPowerBase.blockMicro, 1, i << 8 | 0x17));
/* 249:    */         }
/* 250:    */       }
/* 251:384 */       for (int i = 1; i < 255; i++)
/* 252:    */       {
/* 253:385 */         String stub = getMicroName(i);
/* 254:386 */         if (stub != null) {
/* 255:387 */           list.add(new ur(RedPowerBase.blockMicro, 1, i << 8 | 0x1A));
/* 256:    */         }
/* 257:    */       }
/* 258:    */     }
/* 259:    */   }
/* 260:    */   
/* 261:    */   public tj[] getCreativeTabs()
/* 262:    */   {
/* 263:396 */     return new tj[] { CreativeExtraTabs.tabWires, CreativeExtraTabs.tabMicros, CreativeExtraTabs.tabMachine };
/* 264:    */   }
/* 265:    */   
/* 266:404 */   IMicroPlacement[] placers = new IMicroPlacement[256];
/* 267:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ItemMicro
 * JD-Core Version:    0.7.0.1
 */