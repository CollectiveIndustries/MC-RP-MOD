/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import any;
/*   5:    */ import baz;
/*   6:    */ import bbb;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.Matrix3;
/*   9:    */ import com.eloraam.redpower.core.RenderContext;
/*  10:    */ import com.eloraam.redpower.core.RenderCovers;
/*  11:    */ import com.eloraam.redpower.core.RenderLib;
/*  12:    */ import com.eloraam.redpower.core.Vector3;
/*  13:    */ import java.util.Random;
/*  14:    */ import org.lwjgl.opengl.GL11;
/*  15:    */ import yc;
/*  16:    */ import ym;
/*  17:    */ 
/*  18:    */ public abstract class RenderLogic
/*  19:    */   extends RenderCovers
/*  20:    */ {
/*  21:    */   public RenderLogic(amq bl)
/*  22:    */   {
/*  23: 15 */     super(bl);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public void renderCovers(ym iba, TileLogic tl)
/*  27:    */   {
/*  28: 19 */     if (tl.Cover == 255) {
/*  29: 19 */       return;
/*  30:    */     }
/*  31: 21 */     this.context.setPos(tl.l, tl.m, tl.n);
/*  32: 22 */     this.context.readGlobalLights(iba, tl.l, tl.m, tl.n);
/*  33:    */     
/*  34: 24 */     renderCover(tl.Rotation, tl.Cover);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public TileLogic getTileEntity(ym iba, int i, int j, int k)
/*  38:    */   {
/*  39: 30 */     any te = iba.q(i, j, k);
/*  40: 31 */     if (!(te instanceof TileLogic)) {
/*  41: 31 */       return null;
/*  42:    */     }
/*  43: 32 */     return (TileLogic)te;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void setMatrixWorld(int x, int y, int z, int rot)
/*  47:    */   {
/*  48: 36 */     this.context.setOrientation(rot >> 2, rot & 0x3);
/*  49: 37 */     this.context.setPos(x, y, z);
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void setMatrixDisplayTick(int i, int j, int k, int rot, Random random)
/*  53:    */   {
/*  54: 42 */     float x = i + 0.5F + (random.nextFloat() - 0.5F) * 0.2F;
/*  55: 43 */     float y = j + 0.7F + (random.nextFloat() - 0.5F) * 0.2F;
/*  56: 44 */     float z = k + 0.5F + (random.nextFloat() - 0.5F) * 0.2F;
/*  57:    */     
/*  58: 46 */     this.context.setOrientation(0, rot);
/*  59: 47 */     this.context.setPos(x, y, z);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void setMatrixInv()
/*  63:    */   {
/*  64: 51 */     this.context.setOrientation(0, 3);
/*  65: 52 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void renderWafer(int tx)
/*  69:    */   {
/*  70: 57 */     switch (tx >> 8)
/*  71:    */     {
/*  72:    */     case 0: 
/*  73: 59 */       this.context.bindTexture("/eloraam/logic/logic1.png");
/*  74: 60 */       break;
/*  75:    */     case 1: 
/*  76: 62 */       this.context.bindTexture("/eloraam/logic/logic2.png");
/*  77: 63 */       break;
/*  78:    */     case 2: 
/*  79: 65 */       this.context.bindTexture("/eloraam/logic/sensor1.png");
/*  80:    */     }
/*  81: 68 */     tx &= 0xFF;
/*  82:    */     
/*  83: 70 */     this.context.setRelPos(0.0D, 0.0D, 0.0D);
/*  84: 71 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/*  85: 72 */     this.context.setTexFlags(0);
/*  86: 73 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/*  87: 74 */     this.context.setTex(0, tx, 0, 0, 0, 0);
/*  88: 75 */     this.context.calcBounds();
/*  89: 76 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  90: 77 */     this.context.renderFaces(62);
/*  91:    */     
/*  92: 79 */     this.context.unbindTexture();
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void renderInvWafer(int tx)
/*  96:    */   {
/*  97: 83 */     this.context.useNormal = true;
/*  98: 84 */     switch (tx >> 8)
/*  99:    */     {
/* 100:    */     case 0: 
/* 101: 86 */       this.context.bindTexture("/eloraam/logic/logic1.png");
/* 102: 87 */       break;
/* 103:    */     case 1: 
/* 104: 89 */       this.context.bindTexture("/eloraam/logic/logic2.png");
/* 105: 90 */       break;
/* 106:    */     case 2: 
/* 107: 92 */       this.context.bindTexture("/eloraam/logic/sensor1.png");
/* 108:    */     }
/* 109: 95 */     tx &= 0xFF;
/* 110:    */     
/* 111:    */ 
/* 112: 98 */     baz tessellator = baz.a;
/* 113: 99 */     tessellator.b();
/* 114:    */     
/* 115:101 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/* 116:102 */     this.context.setTexFlags(0);
/* 117:103 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/* 118:104 */     this.context.setTex(0, tx, 0, 0, 0, 0);
/* 119:105 */     this.context.calcBounds();
/* 120:106 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 121:107 */     this.context.renderFaces(63);
/* 122:    */     
/* 123:109 */     tessellator.a();
/* 124:110 */     RenderLib.setDefaultTexture();
/* 125:    */     
/* 126:112 */     this.context.useNormal = false;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public void renderCover(int rot, int cov)
/* 130:    */   {
/* 131:116 */     if (cov == 255) {
/* 132:116 */       return;
/* 133:    */     }
/* 134:117 */     rot >>= 2;rot ^= 0x1;
/* 135:118 */     short[] rs = { 0, 0, 0, 0, 0, 0 };
/* 136:119 */     rs[rot] = ((short)cov);
/* 137:120 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/* 138:121 */     renderCovers(1 << rot, rs);
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void renderTorch(double x, double y, double z, double h, int tex)
/* 142:    */   {
/* 143:126 */     this.context.setTexFlags(0);
/* 144:127 */     this.context.setRelPos(x, y, z);
/* 145:128 */     this.context.setTex(tex);
/* 146:    */     
/* 147:130 */     this.context.setLocalLights(1.0F);
/* 148:131 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/* 149:    */     
/* 150:133 */     this.context.setSize(0.4375D, 1.0D - h, 0.0D, 0.5625D, 1.0D, 1.0D);
/* 151:134 */     this.context.calcBounds();
/* 152:135 */     this.context.renderFaces(48);
/* 153:136 */     this.context.setSize(0.0D, 1.0D - h, 0.4375D, 1.0D, 1.0D, 0.5625D);
/* 154:137 */     this.context.calcBounds();
/* 155:138 */     this.context.renderFaces(12);
/* 156:139 */     this.context.setSize(0.375D, 0.0D, 0.4375D, 0.5D, 1.0D, 0.5625D);
/* 157:140 */     this.context.setRelPos(x + 0.0625D, y - 0.375D, z);
/* 158:141 */     this.context.calcBounds();
/* 159:142 */     this.context.setTexFlags(24);
/* 160:143 */     this.context.renderFaces(2);
/* 161:    */     
/* 162:145 */     this.context.setRelPos(0.0D, 0.0D, 0.0D);
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void renderTorchPuff(yc world, String name, double x, double y, double z)
/* 166:    */   {
/* 167:151 */     Vector3 v = new Vector3(x, y, z);
/* 168:152 */     this.context.basis.rotate(v);
/* 169:153 */     v.add(this.context.globalOrigin);
/* 170:154 */     world.a(name, v.x, v.y, v.z, 0.0D, 0.0D, 0.0D);
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void renderChip(double x, double y, double z, int tex)
/* 174:    */   {
/* 175:158 */     this.context.bindTexture("/eloraam/logic/logic1.png");
/* 176:159 */     this.context.setTexFlags(0);
/* 177:160 */     this.context.setRelPos(x, y, z);
/* 178:161 */     this.context.setTex(tex);
/* 179:162 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 180:163 */     this.context.renderBox(62, 0.375D, 0.0625D, 0.375D, 0.625D, 0.1875D, 0.625D);
/* 181:164 */     this.context.unbindTexture();
/* 182:    */   }
/* 183:    */   
/* 184:    */   public static class TorchPos
/* 185:    */   {
/* 186:    */     double x;
/* 187:    */     double y;
/* 188:    */     double z;
/* 189:    */     double h;
/* 190:    */     
/* 191:    */     public TorchPos(double xi, double yi, double zi, double hi)
/* 192:    */     {
/* 193:171 */       this.x = xi;this.y = yi;this.z = zi;this.h = hi;
/* 194:    */     }
/* 195:    */   }
/* 196:    */   
/* 197:    */   protected int getTorchState(TileLogic tl)
/* 198:    */   {
/* 199:177 */     return 0;
/* 200:    */   }
/* 201:    */   
/* 202:    */   protected int getInvTorchState(int md)
/* 203:    */   {
/* 204:178 */     return 0;
/* 205:    */   }
/* 206:    */   
/* 207:    */   protected TorchPos[] getTorchVectors(TileLogic tl)
/* 208:    */   {
/* 209:179 */     return null;
/* 210:    */   }
/* 211:    */   
/* 212:    */   protected TorchPos[] getInvTorchVectors(int md)
/* 213:    */   {
/* 214:180 */     return null;
/* 215:    */   }
/* 216:    */   
/* 217:    */   protected void renderWorldPart(ym iba, TileLogic tl) {}
/* 218:    */   
/* 219:    */   protected void renderInvPart(int md) {}
/* 220:    */   
/* 221:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random)
/* 222:    */   {
/* 223:186 */     TileLogic tl = (TileLogic)CoreLib.getTileEntity(world, i, j, k, TileLogic.class);
/* 224:188 */     if (tl == null) {
/* 225:188 */       return;
/* 226:    */     }
/* 227:190 */     int ts = getTorchState(tl);
/* 228:191 */     if (ts == 0) {
/* 229:191 */       return;
/* 230:    */     }
/* 231:193 */     setMatrixDisplayTick(i, j, k, tl.Rotation, random);
/* 232:    */     
/* 233:195 */     TorchPos[] tpv = getTorchVectors(tl);
/* 234:196 */     if (tpv == null) {
/* 235:196 */       return;
/* 236:    */     }
/* 237:198 */     int rv = random.nextInt(tpv.length);
/* 238:199 */     if ((ts & 1 << rv) == 0) {
/* 239:199 */       return;
/* 240:    */     }
/* 241:201 */     renderTorchPuff(world, "reddust", tpv[rv].x, tpv[rv].y, tpv[rv].z);
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 245:    */   {
/* 246:207 */     TileLogic tl = (TileLogic)CoreLib.getTileEntity(iba, i, j, k, TileLogic.class);
/* 247:209 */     if (tl == null) {
/* 248:209 */       return;
/* 249:    */     }
/* 250:211 */     renderCovers(iba, tl);
/* 251:    */     
/* 252:213 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/* 253:    */     
/* 254:    */ 
/* 255:216 */     setMatrixWorld(i, j, k, tl.Rotation);
/* 256:    */     
/* 257:218 */     renderWorldPart(iba, tl);
/* 258:    */     
/* 259:220 */     int ts = getTorchState(tl);
/* 260:221 */     TorchPos[] tpv = getTorchVectors(tl);
/* 261:222 */     if (tpv != null) {
/* 262:223 */       for (int n = 0; n < tpv.length; n++) {
/* 263:224 */         renderTorch(tpv[n].x, tpv[n].y, tpv[n].z, tpv[n].h, (ts & 1 << n) > 0 ? 99 : 115);
/* 264:    */       }
/* 265:    */     }
/* 266:    */   }
/* 267:    */   
/* 268:    */   public void renderInvBlock(bbb renderblocks, int md)
/* 269:    */   {
/* 270:231 */     this.block.f();
/* 271:232 */     this.context.setDefaults();
/* 272:    */     
/* 273:234 */     setMatrixInv();
/* 274:235 */     renderInvPart(md);
/* 275:    */     
/* 276:237 */     GL11.glDisable(2896);
/* 277:238 */     baz tessellator = baz.a;
/* 278:239 */     tessellator.b();
/* 279:240 */     int ts = getInvTorchState(md);
/* 280:241 */     TorchPos[] tpv = getInvTorchVectors(md);
/* 281:242 */     if (tpv != null) {
/* 282:243 */       for (int n = 0; n < tpv.length; n++) {
/* 283:244 */         renderTorch(tpv[n].x, tpv[n].y, tpv[n].z, tpv[n].h, (ts & 1 << n) > 0 ? 99 : 115);
/* 284:    */       }
/* 285:    */     }
/* 286:248 */     tessellator.a();
/* 287:249 */     GL11.glEnable(2896);
/* 288:    */   }
/* 289:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.RenderLogic
 * JD-Core Version:    0.7.0.1
 */