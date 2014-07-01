/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import aoh;
/*   5:    */ import aoi;
/*   6:    */ import aoj;
/*   7:    */ import bav;
/*   8:    */ import baz;
/*   9:    */ import bba;
/*  10:    */ import ie;
/*  11:    */ import java.util.Map;
/*  12:    */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*  13:    */ import net.minecraftforge.event.ForgeSubscribe;
/*  14:    */ import org.lwjgl.opengl.GL11;
/*  15:    */ import qx;
/*  16:    */ import ur;
/*  17:    */ import yc;
/*  18:    */ 
/*  19:    */ public class RenderHighlight
/*  20:    */ {
/*  21:    */   RenderContext context;
/*  22:    */   CoverRenderer coverRenderer;
/*  23:    */   
/*  24:    */   public RenderHighlight()
/*  25:    */   {
/*  26: 22 */     this.context = new RenderContext();
/*  27: 23 */     this.coverRenderer = new CoverRenderer(this.context);
/*  28:    */   }
/*  29:    */   
/*  30:    */   @ForgeSubscribe
/*  31:    */   public void highlightEvent(DrawBlockHighlightEvent ev)
/*  32:    */   {
/*  33: 28 */     onBlockHighlight(ev.context, ev.player, ev.target, ev.subID, ev.currentItem, ev.partialTicks);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public boolean onBlockHighlight(bav renderglobal, qx player, aoh mop, int i, ur ist, float f)
/*  37:    */   {
/*  38: 36 */     yc world = player.p;
/*  39: 37 */     int bid = world.a(mop.b, mop.c, mop.d);
/*  40: 39 */     if (!renderglobal.E.isEmpty()) {
/*  41: 40 */       for (Object o : renderglobal.E.values())
/*  42:    */       {
/*  43: 41 */         ie drb = (ie)o;
/*  44: 42 */         if ((drb.b() == mop.b) && (drb.c() == mop.c) && (drb.d() == mop.d))
/*  45:    */         {
/*  46: 46 */           if (!(amq.p[bid] instanceof BlockExtended)) {
/*  47:    */             break;
/*  48:    */           }
/*  49: 47 */           drawBreaking(player.p, renderglobal, (BlockExtended)amq.p[bid], player, mop, f, drb.e());
/*  50:    */           
/*  51:    */ 
/*  52:    */ 
/*  53: 51 */           renderglobal.b(player, mop, i, ist, f);
/*  54:    */           
/*  55: 53 */           return true;
/*  56:    */         }
/*  57:    */       }
/*  58:    */     }
/*  59: 74 */     if ((ist != null) && (CoverLib.blockCoverPlate != null) && (ist.c == CoverLib.blockCoverPlate.cm))
/*  60:    */     {
/*  61: 76 */       if (mop.a != aoi.a) {
/*  62: 77 */         return false;
/*  63:    */       }
/*  64:    */       aoh plp;
/*  65: 79 */       switch (ist.j() >> 8)
/*  66:    */       {
/*  67:    */       case 0: 
/*  68:    */       case 16: 
/*  69:    */       case 17: 
/*  70:    */       case 21: 
/*  71:    */       case 22: 
/*  72:    */       case 23: 
/*  73:    */       case 24: 
/*  74:    */       case 25: 
/*  75:    */       case 26: 
/*  76:    */       case 27: 
/*  77:    */       case 28: 
/*  78:    */       case 29: 
/*  79:    */       case 30: 
/*  80:    */       case 31: 
/*  81:    */       case 32: 
/*  82:    */       case 33: 
/*  83:    */       case 34: 
/*  84:    */       case 39: 
/*  85:    */       case 40: 
/*  86:    */       case 41: 
/*  87:    */       case 42: 
/*  88:    */       case 43: 
/*  89:    */       case 44: 
/*  90:    */       case 45: 
/*  91: 90 */         drawSideBox(world, player, mop, f);
/*  92: 91 */         plp = CoverLib.getPlacement(world, mop, ist.j());
/*  93: 93 */         if (plp != null) {
/*  94: 94 */           drawPreview(player, plp, f, ist.j());
/*  95:    */         }
/*  96: 95 */         break;
/*  97:    */       case 18: 
/*  98:    */       case 19: 
/*  99:    */       case 20: 
/* 100:    */       case 35: 
/* 101:    */       case 36: 
/* 102:    */       case 37: 
/* 103:    */       case 38: 
/* 104: 99 */         drawCornerBox(world, player, mop, f);
/* 105:100 */         plp = CoverLib.getPlacement(world, mop, ist.j());
/* 106:102 */         if (plp != null) {
/* 107:103 */           drawPreview(player, plp, f, ist.j());
/* 108:    */         }
/* 109:104 */         break;
/* 110:    */       case 1: 
/* 111:    */       case 2: 
/* 112:    */       case 3: 
/* 113:    */       case 4: 
/* 114:    */       case 5: 
/* 115:    */       case 6: 
/* 116:    */       case 7: 
/* 117:    */       case 8: 
/* 118:    */       case 9: 
/* 119:    */       case 10: 
/* 120:    */       case 11: 
/* 121:    */       case 12: 
/* 122:    */       case 13: 
/* 123:    */       case 14: 
/* 124:    */       case 15: 
/* 125:    */       default: 
/* 126:105 */         return false;
/* 127:    */       }
/* 128:108 */       return true;
/* 129:    */     }
/* 130:110 */     return false;
/* 131:    */   }
/* 132:    */   
/* 133:    */   private void setRawPos(qx player, aoh mop, float f)
/* 134:    */   {
/* 135:115 */     double x = player.T + (player.t - player.T) * f;
/* 136:    */     
/* 137:117 */     double y = player.U + (player.u - player.U) * f;
/* 138:    */     
/* 139:119 */     double z = player.V + (player.v - player.V) * f;
/* 140:    */     
/* 141:    */ 
/* 142:122 */     this.context.setPos(mop.b - x, mop.c - y, mop.d - z);
/* 143:    */   }
/* 144:    */   
/* 145:    */   private void setCollPos(qx player, aoh mop, float f)
/* 146:    */   {
/* 147:127 */     setRawPos(player, mop, f);
/* 148:129 */     switch (mop.e)
/* 149:    */     {
/* 150:    */     case 0: 
/* 151:131 */       this.context.setRelPos(0.0D, mop.f.d - mop.c, 0.0D);
/* 152:132 */       break;
/* 153:    */     case 1: 
/* 154:134 */       this.context.setRelPos(0.0D, mop.c - mop.f.d + 1.0D, 0.0D);
/* 155:135 */       break;
/* 156:    */     case 2: 
/* 157:137 */       this.context.setRelPos(0.0D, mop.f.e - mop.d, 0.0D);
/* 158:138 */       break;
/* 159:    */     case 3: 
/* 160:140 */       this.context.setRelPos(0.0D, mop.d - mop.f.e + 1.0D, 0.0D);
/* 161:141 */       break;
/* 162:    */     case 4: 
/* 163:143 */       this.context.setRelPos(0.0D, mop.f.c - mop.b, 0.0D);
/* 164:144 */       break;
/* 165:    */     default: 
/* 166:146 */       this.context.setRelPos(0.0D, mop.b - mop.f.c + 1.0D, 0.0D);
/* 167:    */     }
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void drawCornerBox(yc world, qx player, aoh mop, float f)
/* 171:    */   {
/* 172:154 */     GL11.glEnable(3042);
/* 173:155 */     GL11.glBlendFunc(770, 771);
/* 174:156 */     GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.9F);
/* 175:157 */     GL11.glLineWidth(3.0F);
/* 176:158 */     GL11.glDisable(3553);
/* 177:159 */     GL11.glDepthMask(false);
/* 178:160 */     float sx = 0.002F;
/* 179:161 */     float sbs = 0.25F;
/* 180:    */     
/* 181:163 */     int bid = world.a(mop.b, mop.c, mop.d);
/* 182:164 */     if (bid > 0)
/* 183:    */     {
/* 184:165 */       this.context.setSize(0.0D, -sx, 0.0D, 1.0D, -sx, 1.0D);
/* 185:166 */       this.context.setupBox();
/* 186:167 */       this.context.vertexList[4].set(0.0D, -sx, 0.5D);
/* 187:168 */       this.context.vertexList[5].set(1.0D, -sx, 0.5D);
/* 188:169 */       this.context.vertexList[6].set(0.5D, -sx, 0.0D);
/* 189:170 */       this.context.vertexList[7].set(0.5D, -sx, 1.0D);
/* 190:    */       
/* 191:172 */       this.context.setOrientation(mop.e, 0);
/* 192:173 */       setCollPos(player, mop, f);
/* 193:174 */       this.context.transformRotate();
/* 194:    */       
/* 195:176 */       baz.a.b(3);
/* 196:177 */       this.context.drawPoints(new int[] { 0, 1, 2, 3, 0 });
/* 197:178 */       baz.a.a();
/* 198:179 */       baz.a.b(1);
/* 199:180 */       this.context.drawPoints(new int[] { 4, 5, 6, 7 });
/* 200:181 */       baz.a.a();
/* 201:    */     }
/* 202:183 */     GL11.glDepthMask(true);
/* 203:184 */     GL11.glEnable(3553);
/* 204:185 */     GL11.glDisable(3042);
/* 205:186 */     this.context.setRelPos(0.0D, 0.0D, 0.0D);
/* 206:    */   }
/* 207:    */   
/* 208:    */   public void drawSideBox(yc world, qx player, aoh mop, float f)
/* 209:    */   {
/* 210:191 */     GL11.glEnable(3042);
/* 211:192 */     GL11.glBlendFunc(770, 771);
/* 212:193 */     GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.9F);
/* 213:194 */     GL11.glLineWidth(3.0F);
/* 214:195 */     GL11.glDisable(3553);
/* 215:196 */     GL11.glDepthMask(false);
/* 216:197 */     float sx = 0.002F;
/* 217:198 */     float sbs = 0.25F;
/* 218:    */     
/* 219:200 */     int bid = world.a(mop.b, mop.c, mop.d);
/* 220:201 */     if (bid > 0)
/* 221:    */     {
/* 222:202 */       this.context.setSize(0.0D, -sx, 0.0D, 1.0D, -sx, 1.0D);
/* 223:203 */       this.context.setupBox();
/* 224:204 */       this.context.vertexList[4].set(1.0F - sbs, -sx, sbs);
/* 225:205 */       this.context.vertexList[5].set(sbs, -sx, sbs);
/* 226:206 */       this.context.vertexList[6].set(sbs, -sx, 1.0F - sbs);
/* 227:207 */       this.context.vertexList[7].set(1.0F - sbs, -sx, 1.0F - sbs);
/* 228:    */       
/* 229:209 */       this.context.setOrientation(mop.e, 0);
/* 230:210 */       setCollPos(player, mop, f);
/* 231:211 */       this.context.transformRotate();
/* 232:    */       
/* 233:213 */       baz.a.b(3);
/* 234:214 */       this.context.drawPoints(new int[] { 0, 1, 2, 3, 0 });
/* 235:215 */       baz.a.a();
/* 236:216 */       baz.a.b(3);
/* 237:217 */       this.context.drawPoints(new int[] { 4, 5, 6, 7, 4 });
/* 238:218 */       baz.a.a();
/* 239:219 */       baz.a.b(1);
/* 240:220 */       this.context.drawPoints(new int[] { 0, 4, 1, 5, 2, 6, 3, 7 });
/* 241:221 */       baz.a.a();
/* 242:    */     }
/* 243:223 */     GL11.glDepthMask(true);
/* 244:224 */     GL11.glEnable(3553);
/* 245:225 */     GL11.glDisable(3042);
/* 246:226 */     this.context.setRelPos(0.0D, 0.0D, 0.0D);
/* 247:    */   }
/* 248:    */   
/* 249:    */   public void drawBreaking(yc world, bav rg, BlockExtended bex, qx player, aoh mop, float f, int dmg)
/* 250:    */   {
/* 251:233 */     if ((bex instanceof BlockMultipart))
/* 252:    */     {
/* 253:234 */       BlockMultipart bmp = (BlockMultipart)bex;
/* 254:235 */       bmp.setPartBounds(world, mop.b, mop.c, mop.d, mop.subHit);
/* 255:    */     }
/* 256:238 */     GL11.glEnable(3042);
/* 257:239 */     GL11.glBlendFunc(774, 768);
/* 258:    */     
/* 259:241 */     int j = rg.i.b("/terrain.png");
/* 260:242 */     GL11.glBindTexture(3553, j);
/* 261:243 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/* 262:    */     
/* 263:245 */     GL11.glPolygonOffset(-3.0F, -3.0F);
/* 264:246 */     GL11.glEnable(32823);
/* 265:    */     
/* 266:248 */     double x = player.T + (player.t - player.T) * f;
/* 267:    */     
/* 268:250 */     double y = player.U + (player.u - player.U) * f;
/* 269:    */     
/* 270:252 */     double z = player.V + (player.v - player.V) * f;
/* 271:    */     
/* 272:254 */     GL11.glEnable(3008);
/* 273:    */     
/* 274:256 */     this.context.setPos(mop.b - x, mop.c - y, mop.d - z);
/* 275:    */     
/* 276:258 */     this.context.setTex(240 + dmg);
/* 277:    */     
/* 278:    */ 
/* 279:261 */     baz.a.b();
/* 280:    */     
/* 281:    */ 
/* 282:264 */     this.context.setSize(bex.v(), bex.x(), bex.z(), bex.w(), bex.y(), bex.A());
/* 283:    */     
/* 284:    */ 
/* 285:    */ 
/* 286:    */ 
/* 287:    */ 
/* 288:    */ 
/* 289:271 */     this.context.setupBox();
/* 290:272 */     this.context.transform();
/* 291:273 */     this.context.renderFaces(63);
/* 292:274 */     baz.a.a();
/* 293:    */     
/* 294:276 */     GL11.glPolygonOffset(0.0F, 0.0F);
/* 295:277 */     GL11.glDisable(32823);
/* 296:    */   }
/* 297:    */   
/* 298:    */   public void drawPreview(qx player, aoh mop, float f, int item)
/* 299:    */   {
/* 300:283 */     setRawPos(player, mop, f);
/* 301:284 */     this.coverRenderer.start();
/* 302:285 */     this.coverRenderer.setupCorners();
/* 303:286 */     this.coverRenderer.setSize(mop.subHit, CoverLib.getThickness(mop.subHit, CoverLib.damageToCoverValue(item)));
/* 304:    */     
/* 305:288 */     this.context.setTexFile(CoverLib.coverTextureFiles[(item & 0xFF)]);
/* 306:289 */     this.context.setTex(CoverLib.coverTextures[(item & 0xFF)]);
/* 307:    */     
/* 308:291 */     GL11.glEnable(3042);
/* 309:292 */     GL11.glBlendFunc(770, 1);
/* 310:293 */     GL11.glDepthMask(false);
/* 311:    */     
/* 312:295 */     GL11.glPolygonOffset(-3.0F, -3.0F);
/* 313:296 */     GL11.glEnable(32823);
/* 314:    */     
/* 315:298 */     baz.a.b();
/* 316:299 */     this.context.setupBox();
/* 317:300 */     this.context.transform();
/* 318:301 */     this.context.doMappingBox(63);
/* 319:302 */     this.context.doLightLocal(63);
/* 320:303 */     this.context.renderAlpha(63, 0.8F);
/* 321:304 */     baz.a.a();
/* 322:    */     
/* 323:306 */     GL11.glDisable(32823);
/* 324:307 */     GL11.glDepthMask(true);
/* 325:308 */     GL11.glDisable(3042);
/* 326:    */   }
/* 327:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.RenderHighlight
 * JD-Core Version:    0.7.0.1
 */