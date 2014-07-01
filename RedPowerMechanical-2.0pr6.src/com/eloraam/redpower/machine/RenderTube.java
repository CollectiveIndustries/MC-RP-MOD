/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.RenderContext;
/*   8:    */ import com.eloraam.redpower.core.RenderCovers;
/*   9:    */ import com.eloraam.redpower.core.RenderLib;
/*  10:    */ import com.eloraam.redpower.core.TubeLib;
/*  11:    */ import java.util.Random;
/*  12:    */ import yc;
/*  13:    */ import ym;
/*  14:    */ 
/*  15:    */ public class RenderTube
/*  16:    */   extends RenderCovers
/*  17:    */ {
/*  18:    */   public RenderTube(amq bl)
/*  19:    */   {
/*  20: 16 */     super(bl);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  24:    */   
/*  25:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  26:    */   {
/*  27: 26 */     int cons = 0;
/*  28: 27 */     TileTube tt = (TileTube)CoreLib.getTileEntity(iba, i, j, k, TileTube.class);
/*  29: 29 */     if (tt == null) {
/*  30: 29 */       return;
/*  31:    */     }
/*  32: 31 */     this.context.exactTextureCoordinates = true;
/*  33: 32 */     this.context.setTexFlags(55);
/*  34: 33 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/*  35: 34 */     this.context.setPos(i, j, k);
/*  36: 35 */     if (tt.CoverSides > 0)
/*  37:    */     {
/*  38: 36 */       this.context.readGlobalLights(iba, i, j, k);
/*  39: 37 */       renderCovers(tt.CoverSides, tt.Covers);
/*  40:    */     }
/*  41: 40 */     cons = TubeLib.getConnections(iba, i, j, k);
/*  42:    */     
/*  43:    */ 
/*  44:    */ 
/*  45: 44 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/*  46:    */     
/*  47: 46 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  48: 47 */     this.context.setPos(i, j, k);
/*  49:    */     
/*  50: 49 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  51: 51 */     if (md == 10) {
/*  52: 52 */       renderCenterBlock(cons, 76, 77);
/*  53: 53 */     } else if (md == 11)
/*  54:    */     {
/*  55: 54 */       if (renderMagFins(cons)) {
/*  56: 55 */         renderCenterBlock(cons, 23, 22);
/*  57:    */       } else {
/*  58: 57 */         renderCenterBlock(cons, 21, 22);
/*  59:    */       }
/*  60:    */     }
/*  61:    */     else {
/*  62: 60 */       renderCenterBlock(cons, 64, 65);
/*  63:    */     }
/*  64: 63 */     if (tt.paintColor > 0)
/*  65:    */     {
/*  66: 64 */       int tc = this.paintColors[(tt.paintColor - 1)];
/*  67: 65 */       this.context.setTint((tc >> 16) / 255.0F, (tc >> 8 & 0xFF) / 255.0F, (tc & 0xFF) / 255.0F);
/*  68: 67 */       if (md == 10) {
/*  69: 68 */         renderBlockPaint(cons, 78);
/*  70:    */       } else {
/*  71: 70 */         renderBlockPaint(cons, 66);
/*  72:    */       }
/*  73:    */     }
/*  74: 74 */     RenderLib.unbindTexture();
/*  75:    */   }
/*  76:    */   
/*  77: 77 */   int[] paintColors = { 16777215, 16744448, 16711935, 7110911, 16776960, 65280, 16737408, 5460819, 9671571, 65535, 8388863, 255, 5187328, 32768, 16711680, 2039583 };
/*  78:    */   
/*  79:    */   public void renderInvBlock(bbb renderblocks, int md)
/*  80:    */   {
/*  81: 84 */     this.block.f();
/*  82:    */     
/*  83: 86 */     this.context.setDefaults();
/*  84: 87 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/*  85: 88 */     this.context.useNormal = true;
/*  86: 89 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  87:    */     
/*  88: 91 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  89: 92 */     baz tessellator = baz.a;
/*  90:    */     
/*  91: 94 */     tessellator.b();
/*  92: 95 */     this.context.useNormal = true;
/*  93: 97 */     if (md >> 8 == 10)
/*  94:    */     {
/*  95: 98 */       this.context.setTex(65, 65, 76, 76, 76, 76);
/*  96:    */     }
/*  97: 99 */     else if (md >> 8 == 11)
/*  98:    */     {
/*  99:100 */       renderMagFins(3);
/* 100:101 */       this.context.setTex(22, 22, 21, 21, 21, 21);
/* 101:    */     }
/* 102:    */     else
/* 103:    */     {
/* 104:103 */       this.context.setTex(65, 65, 64, 64, 64, 64);
/* 105:    */     }
/* 106:105 */     this.context.renderBox(63, 0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 107:106 */     this.context.renderBox(63, 0.7400000095367432D, 0.9900000095367432D, 0.7400000095367432D, 0.2599999904632568D, 0.009999999776482582D, 0.2599999904632568D);
/* 108:    */     
/* 109:    */ 
/* 110:    */ 
/* 111:    */ 
/* 112:    */ 
/* 113:112 */     tessellator.a();
/* 114:113 */     RenderLib.unbindTexture();
/* 115:114 */     this.context.useNormal = false;
/* 116:    */   }
/* 117:    */   
/* 118:    */   void doubleBox(int sides, float x1, float y1, float z1, float x2, float y2, float z2)
/* 119:    */   {
/* 120:119 */     int s2 = sides << 1 & 0x2A | sides >> 1 & 0x15;
/* 121:    */     
/* 122:121 */     this.context.renderBox(sides, x1, y1, z1, x2, y2, z2);
/* 123:122 */     this.context.renderBox(s2, x2, y2, z2, x1, y1, z1);
/* 124:    */   }
/* 125:    */   
/* 126:    */   public boolean renderMagFins(int cons)
/* 127:    */   {
/* 128:126 */     if (cons == 3)
/* 129:    */     {
/* 130:127 */       this.context.setTexFlags(0);
/* 131:128 */       this.context.setTex(25, 25, 24, 24, 24, 24);
/* 132:129 */       this.context.renderBox(63, 0.125D, 0.125D, 0.125D, 0.875D, 0.375D, 0.875D);
/* 133:    */       
/* 134:131 */       this.context.renderBox(63, 0.125D, 0.625D, 0.125D, 0.875D, 0.875D, 0.875D);
/* 135:    */       
/* 136:133 */       return true;
/* 137:    */     }
/* 138:134 */     if (cons == 12)
/* 139:    */     {
/* 140:135 */       this.context.setTexFlags(147492);
/* 141:136 */       this.context.setTex(24, 24, 25, 25, 24, 24);
/* 142:137 */       this.context.renderBox(63, 0.125D, 0.125D, 0.125D, 0.875D, 0.875D, 0.375D);
/* 143:    */       
/* 144:139 */       this.context.renderBox(63, 0.125D, 0.125D, 0.625D, 0.875D, 0.875D, 0.875D);
/* 145:    */       
/* 146:141 */       return true;
/* 147:    */     }
/* 148:142 */     if (cons == 48)
/* 149:    */     {
/* 150:143 */       this.context.setTexFlags(2304);
/* 151:144 */       this.context.setTex(24, 24, 24, 24, 25, 25);
/* 152:145 */       this.context.renderBox(63, 0.125D, 0.125D, 0.125D, 0.375D, 0.875D, 0.875D);
/* 153:    */       
/* 154:147 */       this.context.renderBox(63, 0.625D, 0.125D, 0.125D, 0.875D, 0.875D, 0.875D);
/* 155:    */       
/* 156:149 */       return true;
/* 157:    */     }
/* 158:151 */     return false;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void renderCenterBlock(int cons, int side, int end)
/* 162:    */   {
/* 163:155 */     if (cons == 0)
/* 164:    */     {
/* 165:156 */       this.context.setTex(end);
/* 166:157 */       doubleBox(63, 0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/* 167:158 */       return;
/* 168:    */     }
/* 169:159 */     if (cons == 3)
/* 170:    */     {
/* 171:160 */       this.context.setTexFlags(1773);
/* 172:161 */       this.context.setTex(end, end, side, side, side, side);
/* 173:162 */       doubleBox(60, 0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
/* 174:163 */       return;
/* 175:    */     }
/* 176:164 */     if (cons == 12)
/* 177:    */     {
/* 178:165 */       this.context.setTexFlags(184365);
/* 179:166 */       this.context.setTex(side, side, end, end, side, side);
/* 180:167 */       doubleBox(51, 0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
/* 181:168 */       return;
/* 182:    */     }
/* 183:169 */     if (cons == 48)
/* 184:    */     {
/* 185:170 */       this.context.setTexFlags(187200);
/* 186:171 */       this.context.setTex(side, side, side, side, end, end);
/* 187:172 */       doubleBox(15, 0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
/* 188:173 */       return;
/* 189:    */     }
/* 190:175 */     this.context.setTex(end);
/* 191:176 */     doubleBox(0x3F ^ cons, 0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/* 192:177 */     if ((cons & 0x1) > 0)
/* 193:    */     {
/* 194:178 */       this.context.setTexFlags(1773);
/* 195:179 */       this.context.setTex(end, end, side, side, side, side);
/* 196:180 */       doubleBox(60, 0.25F, 0.0F, 0.25F, 0.75F, 0.25F, 0.75F);
/* 197:    */     }
/* 198:182 */     if ((cons & 0x2) > 0)
/* 199:    */     {
/* 200:183 */       this.context.setTexFlags(1773);
/* 201:184 */       this.context.setTex(end, end, side, side, side, side);
/* 202:185 */       doubleBox(60, 0.25F, 0.75F, 0.25F, 0.75F, 1.0F, 0.75F);
/* 203:    */     }
/* 204:187 */     if ((cons & 0x4) > 0)
/* 205:    */     {
/* 206:188 */       this.context.setTexFlags(184365);
/* 207:189 */       this.context.setTex(side, side, end, end, side, side);
/* 208:190 */       doubleBox(51, 0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.25F);
/* 209:    */     }
/* 210:192 */     if ((cons & 0x8) > 0)
/* 211:    */     {
/* 212:193 */       this.context.setTexFlags(184365);
/* 213:194 */       this.context.setTex(side, side, end, end, side, side);
/* 214:195 */       doubleBox(51, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F, 1.0F);
/* 215:    */     }
/* 216:197 */     if ((cons & 0x10) > 0)
/* 217:    */     {
/* 218:198 */       this.context.setTexFlags(187200);
/* 219:199 */       this.context.setTex(side, side, side, side, end, end);
/* 220:200 */       doubleBox(15, 0.0F, 0.25F, 0.25F, 0.25F, 0.75F, 0.75F);
/* 221:    */     }
/* 222:202 */     if ((cons & 0x20) > 0)
/* 223:    */     {
/* 224:203 */       this.context.setTexFlags(187200);
/* 225:204 */       this.context.setTex(side, side, side, side, end, end);
/* 226:205 */       doubleBox(15, 0.75F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
/* 227:    */     }
/* 228:    */   }
/* 229:    */   
/* 230:    */   public void renderBlockPaint(int cons, int tx1)
/* 231:    */   {
/* 232:211 */     if (cons == 0) {
/* 233:212 */       return;
/* 234:    */     }
/* 235:213 */     if (cons == 3)
/* 236:    */     {
/* 237:214 */       this.context.setTexFlags(1773);
/* 238:215 */       this.context.setTex(0, 0, tx1 + 1, tx1 + 1, tx1 + 1, tx1 + 1);
/* 239:216 */       doubleBox(60, 0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
/* 240:217 */       return;
/* 241:    */     }
/* 242:218 */     if (cons == 12)
/* 243:    */     {
/* 244:219 */       this.context.setTexFlags(184365);
/* 245:220 */       this.context.setTex(tx1 + 1, tx1 + 1, 0, 0, tx1 + 1, tx1 + 1);
/* 246:221 */       doubleBox(51, 0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
/* 247:222 */       return;
/* 248:    */     }
/* 249:223 */     if (cons == 48)
/* 250:    */     {
/* 251:224 */       this.context.setTexFlags(187200);
/* 252:225 */       this.context.setTex(tx1 + 1, tx1 + 1, tx1 + 1, tx1 + 1, 0, 0);
/* 253:226 */       doubleBox(15, 0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
/* 254:227 */       return;
/* 255:    */     }
/* 256:229 */     this.context.setTex(tx1);
/* 257:230 */     doubleBox(0x3F ^ cons, 0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/* 258:231 */     if ((cons & 0x1) > 0)
/* 259:    */     {
/* 260:232 */       this.context.setTexFlags(1773);
/* 261:233 */       this.context.setTex(tx1, tx1, tx1 + 1, tx1 + 1, tx1 + 1, tx1 + 1);
/* 262:234 */       doubleBox(60, 0.25F, 0.0F, 0.25F, 0.75F, 0.25F, 0.75F);
/* 263:    */     }
/* 264:236 */     if ((cons & 0x2) > 0)
/* 265:    */     {
/* 266:237 */       this.context.setTexFlags(1773);
/* 267:238 */       this.context.setTex(tx1, tx1, tx1 + 1, tx1 + 1, tx1 + 1, tx1 + 1);
/* 268:239 */       doubleBox(60, 0.25F, 0.75F, 0.25F, 0.75F, 1.0F, 0.75F);
/* 269:    */     }
/* 270:241 */     if ((cons & 0x4) > 0)
/* 271:    */     {
/* 272:242 */       this.context.setTexFlags(184365);
/* 273:243 */       this.context.setTex(tx1 + 1, tx1 + 1, tx1, tx1, tx1 + 1, tx1 + 1);
/* 274:244 */       doubleBox(51, 0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.25F);
/* 275:    */     }
/* 276:246 */     if ((cons & 0x8) > 0)
/* 277:    */     {
/* 278:247 */       this.context.setTexFlags(184365);
/* 279:248 */       this.context.setTex(tx1 + 1, tx1 + 1, tx1, tx1, tx1 + 1, tx1 + 1);
/* 280:249 */       doubleBox(51, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F, 1.0F);
/* 281:    */     }
/* 282:251 */     if ((cons & 0x10) > 0)
/* 283:    */     {
/* 284:252 */       this.context.setTexFlags(187200);
/* 285:253 */       this.context.setTex(tx1 + 1, tx1 + 1, tx1 + 1, tx1 + 1, tx1, tx1);
/* 286:254 */       doubleBox(15, 0.0F, 0.25F, 0.25F, 0.25F, 0.75F, 0.75F);
/* 287:    */     }
/* 288:256 */     if ((cons & 0x20) > 0)
/* 289:    */     {
/* 290:257 */       this.context.setTexFlags(187200);
/* 291:258 */       this.context.setTex(tx1 + 1, tx1 + 1, tx1 + 1, tx1 + 1, tx1, tx1);
/* 292:259 */       doubleBox(15, 0.75F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
/* 293:    */     }
/* 294:    */   }
/* 295:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderTube
 * JD-Core Version:    0.7.0.1
 */