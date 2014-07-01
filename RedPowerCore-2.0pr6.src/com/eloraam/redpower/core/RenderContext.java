/*    1:     */ package com.eloraam.redpower.core;
/*    2:     */ 
/*    3:     */ import amq;
/*    4:     */ import baz;
/*    5:     */ import bbb;
/*    6:     */ import net.minecraft.client.Minecraft;
/*    7:     */ import ym;
/*    8:     */ 
/*    9:     */ public class RenderContext
/*   10:     */ {
/*   11:     */   public void setDefaults()
/*   12:     */   {
/*   13:  14 */     this.localOffset.set(0.0D, 0.0D, 0.0D);
/*   14:  15 */     setOrientation(0, 0);
/*   15:  16 */     this.texFlags = 0;
/*   16:  17 */     this.tintR = 1.0F;this.tintG = 1.0F;this.tintB = 1.0F;this.tintA = 1.0F;
/*   17:  18 */     setLocalLights(1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F);
/*   18:  19 */     setBrightness(15728880);
/*   19:     */   }
/*   20:     */   
/*   21:     */   public void setPos(double x, double y, double z)
/*   22:     */   {
/*   23:  23 */     this.globalOrigin.set(x, y, z);
/*   24:     */   }
/*   25:     */   
/*   26:     */   public void setPos(Vector3 v)
/*   27:     */   {
/*   28:  25 */     this.globalOrigin.set(v);
/*   29:     */   }
/*   30:     */   
/*   31:     */   public void setRelPos(double x, double y, double z)
/*   32:     */   {
/*   33:  28 */     this.localOffset.set(x, y, z);
/*   34:     */   }
/*   35:     */   
/*   36:     */   public void setRelPos(Vector3 v)
/*   37:     */   {
/*   38:  30 */     this.localOffset.set(v);
/*   39:     */   }
/*   40:     */   
/*   41:     */   public void setOrientation(int down, int rot)
/*   42:     */   {
/*   43:  33 */     MathLib.orientMatrix(this.basis, down, rot);
/*   44:     */   }
/*   45:     */   
/*   46:     */   public void setSize(double tx, double ty, double tz, double bx, double by, double bz)
/*   47:     */   {
/*   48:  38 */     this.boxSize1.set(tx, ty, tz);
/*   49:  39 */     this.boxSize2.set(bx, by, bz);
/*   50:     */   }
/*   51:     */   
/*   52:     */   public void clearTexFiles()
/*   53:     */   {
/*   54:  43 */     this.texBinds = null;
/*   55:  44 */     RenderLib.unbindTexture();
/*   56:     */   }
/*   57:     */   
/*   58:     */   public void setTexFiles(String[] tex)
/*   59:     */   {
/*   60:  48 */     this.texBinds = tex;
/*   61:     */   }
/*   62:     */   
/*   63:     */   public void setTexFile(String tex)
/*   64:     */   {
/*   65:  52 */     this.texBinds = null;
/*   66:  53 */     if (tex == null) {
/*   67:  54 */       RenderLib.unbindTexture();
/*   68:     */     } else {
/*   69:  55 */       RenderLib.bindTexture(tex);
/*   70:     */     }
/*   71:     */   }
/*   72:     */   
/*   73:     */   public void setTexFiles(String a, String b, String c, String d, String e, String f)
/*   74:     */   {
/*   75:  60 */     if ((a == null) && (b == null) && (c == null) && (d == null) && (e == null) && (f == null))
/*   76:     */     {
/*   77:  62 */       if (this.texBinds != null) {
/*   78:  62 */         clearTexFiles();
/*   79:     */       }
/*   80:  63 */       return;
/*   81:     */     }
/*   82:  65 */     this.texBinds = this.texBindsBox;
/*   83:  66 */     this.texBinds[0] = a;this.texBinds[1] = b;this.texBinds[2] = c;
/*   84:  67 */     this.texBinds[3] = d;this.texBinds[4] = e;this.texBinds[5] = f;
/*   85:     */   }
/*   86:     */   
/*   87:     */   public void setTexFlags(int fl)
/*   88:     */   {
/*   89:  71 */     this.texFlags = fl;
/*   90:     */   }
/*   91:     */   
/*   92:     */   public void setTex(int a, int b, int c, int d, int e, int f)
/*   93:     */   {
/*   94:  75 */     if (this.lockTexture) {
/*   95:  75 */       return;
/*   96:     */     }
/*   97:  76 */     this.texIndex = this.texIndexBox;
/*   98:  77 */     this.texIndex[0] = a;this.texIndex[1] = b;this.texIndex[2] = c;
/*   99:  78 */     this.texIndex[3] = d;this.texIndex[4] = e;this.texIndex[5] = f;
/*  100:     */   }
/*  101:     */   
/*  102:     */   public void setTex(int a)
/*  103:     */   {
/*  104:  82 */     if (this.lockTexture) {
/*  105:  82 */       return;
/*  106:     */     }
/*  107:  83 */     this.texIndex = this.texIndexBox;
/*  108:  84 */     this.texIndex[0] = a;this.texIndex[1] = a;this.texIndex[2] = a;
/*  109:  85 */     this.texIndex[3] = a;this.texIndex[4] = a;this.texIndex[5] = a;
/*  110:     */   }
/*  111:     */   
/*  112:     */   public void setTex(int[] a)
/*  113:     */   {
/*  114:  89 */     if (this.lockTexture) {
/*  115:  89 */       return;
/*  116:     */     }
/*  117:  90 */     this.texIndex = a;
/*  118:     */   }
/*  119:     */   
/*  120:     */   public void setTex(int[][] a)
/*  121:     */   {
/*  122:  94 */     if (this.lockTexture) {
/*  123:  94 */       return;
/*  124:     */     }
/*  125:  95 */     this.texIndexList = a;
/*  126:  96 */     this.texIndex = a[0];
/*  127:     */   }
/*  128:     */   
/*  129:     */   public void setTexIndex(int n)
/*  130:     */   {
/*  131: 100 */     if (this.texIndexList == null) {
/*  132: 100 */       return;
/*  133:     */     }
/*  134: 101 */     this.texIndex = this.texIndexList[n];
/*  135:     */   }
/*  136:     */   
/*  137:     */   public void setTexNum(int num, int tex)
/*  138:     */   {
/*  139: 105 */     this.texIndex[num] = tex;
/*  140:     */   }
/*  141:     */   
/*  142:     */   public void setTint(float r, float g, float b)
/*  143:     */   {
/*  144: 109 */     this.tintR = r;this.tintG = g;this.tintB = b;
/*  145:     */   }
/*  146:     */   
/*  147:     */   public void setTintHex(int tc)
/*  148:     */   {
/*  149: 113 */     this.tintR = ((tc >> 16) / 255.0F);
/*  150: 114 */     this.tintG = ((tc >> 8 & 0xFF) / 255.0F);
/*  151: 115 */     this.tintB = ((tc & 0xFF) / 255.0F);
/*  152:     */   }
/*  153:     */   
/*  154:     */   public void setAlpha(float a)
/*  155:     */   {
/*  156: 118 */     this.tintA = a;
/*  157:     */   }
/*  158:     */   
/*  159:     */   public void setLocalLights(float a, float b, float c, float d, float e, float f)
/*  160:     */   {
/*  161: 122 */     this.lightLocal = this.lightLocalBox;
/*  162: 123 */     this.lightLocal[0] = a;
/*  163: 124 */     this.lightLocal[1] = b;
/*  164: 125 */     this.lightLocal[2] = c;
/*  165: 126 */     this.lightLocal[3] = d;
/*  166: 127 */     this.lightLocal[4] = e;
/*  167: 128 */     this.lightLocal[5] = f;
/*  168:     */   }
/*  169:     */   
/*  170:     */   public void setLocalLights(float a)
/*  171:     */   {
/*  172: 132 */     this.lightLocal = this.lightLocalBox;
/*  173: 133 */     for (int i = 0; i < 6; i++) {
/*  174: 133 */       this.lightLocal[i] = a;
/*  175:     */     }
/*  176:     */   }
/*  177:     */   
/*  178:     */   public void setBrightness(int a)
/*  179:     */   {
/*  180: 137 */     this.brightLocal = this.brightLocalBox;
/*  181: 138 */     for (int i = 0; i < 6; i++) {
/*  182: 138 */       this.brightLocal[i] = a;
/*  183:     */     }
/*  184:     */   }
/*  185:     */   
/*  186:     */   public void startWorldRender(bbb rbl) {}
/*  187:     */   
/*  188:     */   public boolean endWorldRender()
/*  189:     */   {
/*  190: 154 */     return false;
/*  191:     */   }
/*  192:     */   
/*  193:     */   public void bindTexture(String name)
/*  194:     */   {
/*  195: 158 */     if (this.lockTexture) {
/*  196: 158 */       return;
/*  197:     */     }
/*  198: 159 */     RenderLib.bindTexture(name);
/*  199:     */   }
/*  200:     */   
/*  201:     */   public void unbindTexture()
/*  202:     */   {
/*  203: 163 */     if (this.lockTexture) {
/*  204: 163 */       return;
/*  205:     */     }
/*  206: 164 */     RenderLib.unbindTexture();
/*  207:     */   }
/*  208:     */   
/*  209:     */   public void setupBox()
/*  210:     */   {
/*  211: 170 */     this.vertexList = this.vertexListBox;
/*  212: 171 */     this.vertexList[0].set(this.boxSize2.x, this.boxSize2.y, this.boxSize1.z);
/*  213: 172 */     this.vertexList[1].set(this.boxSize1.x, this.boxSize2.y, this.boxSize1.z);
/*  214: 173 */     this.vertexList[2].set(this.boxSize1.x, this.boxSize2.y, this.boxSize2.z);
/*  215: 174 */     this.vertexList[3].set(this.boxSize2.x, this.boxSize2.y, this.boxSize2.z);
/*  216: 175 */     this.vertexList[4].set(this.boxSize2.x, this.boxSize1.y, this.boxSize1.z);
/*  217: 176 */     this.vertexList[5].set(this.boxSize1.x, this.boxSize1.y, this.boxSize1.z);
/*  218: 177 */     this.vertexList[6].set(this.boxSize1.x, this.boxSize1.y, this.boxSize2.z);
/*  219: 178 */     this.vertexList[7].set(this.boxSize2.x, this.boxSize1.y, this.boxSize2.z);
/*  220:     */   }
/*  221:     */   
/*  222:     */   public void transformRotate()
/*  223:     */   {
/*  224: 183 */     for (int i = 0; i < this.vertexList.length; i++)
/*  225:     */     {
/*  226: 184 */       this.vertexList[i].add(this.localOffset.x - 0.5D, this.localOffset.y - 0.5D, this.localOffset.z - 0.5D);
/*  227:     */       
/*  228: 186 */       this.basis.rotate(this.vertexList[i]);
/*  229: 187 */       this.vertexList[i].add(this.globalOrigin.x + 0.5D, this.globalOrigin.y + 0.5D, this.globalOrigin.z + 0.5D);
/*  230:     */     }
/*  231:     */   }
/*  232:     */   
/*  233:     */   public void transform()
/*  234:     */   {
/*  235: 194 */     for (int i = 0; i < this.vertexList.length; i++)
/*  236:     */     {
/*  237: 195 */       this.vertexList[i].add(this.localOffset);
/*  238: 196 */       this.vertexList[i].add(this.globalOrigin);
/*  239:     */     }
/*  240:     */   }
/*  241:     */   
/*  242:     */   public void setSideUV(int s, double u1, double u2, double v1, double v2)
/*  243:     */   {
/*  244: 202 */     if (!this.exactTextureCoordinates)
/*  245:     */     {
/*  246: 203 */       u1 += 0.001D;v1 += 0.001D;u2 -= 0.001D;v2 -= 0.001D;
/*  247:     */     }
/*  248: 205 */     u1 *= 0.0625D;u2 *= 0.0625D;v1 *= 0.0625D;v2 *= 0.0625D;
/*  249:     */     
/*  250: 207 */     int txl = this.texFlags >> s * 3;
/*  251: 208 */     if ((txl & 0x1) > 0)
/*  252:     */     {
/*  253: 208 */       u1 = 0.0625D - u1;u2 = 0.0625D - u2;
/*  254:     */     }
/*  255: 209 */     if ((txl & 0x2) > 0)
/*  256:     */     {
/*  257: 209 */       v1 = 0.0625D - v1;v2 = 0.0625D - v2;
/*  258:     */     }
/*  259: 211 */     int ti = this.texIndex[s];
/*  260: 212 */     double tx = (ti & 0xF) * 0.0625D;double ty = (ti >> 4) * 0.0625D;
/*  261: 214 */     if ((txl & 0x4) > 0)
/*  262:     */     {
/*  263: 215 */       u1 += ty;u2 += ty;v1 += tx;v2 += tx;
/*  264: 216 */       this.cornerList[s][0].setUV(v1, u1);
/*  265: 217 */       this.cornerList[s][1].setUV(v2, u1);
/*  266: 218 */       this.cornerList[s][2].setUV(v2, u2);
/*  267: 219 */       this.cornerList[s][3].setUV(v1, u2);
/*  268:     */     }
/*  269:     */     else
/*  270:     */     {
/*  271: 221 */       u1 += tx;u2 += tx;v1 += ty;v2 += ty;
/*  272: 222 */       this.cornerList[s][0].setUV(u1, v1);
/*  273: 223 */       this.cornerList[s][1].setUV(u1, v2);
/*  274: 224 */       this.cornerList[s][2].setUV(u2, v2);
/*  275: 225 */       this.cornerList[s][3].setUV(u2, v1);
/*  276:     */     }
/*  277:     */   }
/*  278:     */   
/*  279:     */   public void doMappingBox(int sides)
/*  280:     */   {
/*  281: 231 */     this.cornerList = this.cornerListBox;
/*  282: 233 */     if ((sides & 0x3) > 0)
/*  283:     */     {
/*  284: 234 */       double v1 = 1.0D - this.boxSize2.x;double v2 = 1.0D - this.boxSize1.x;
/*  285: 235 */       if ((sides & 0x1) > 0)
/*  286:     */       {
/*  287: 236 */         double u1 = 1.0D - this.boxSize2.z;double u2 = 1.0D - this.boxSize1.z;
/*  288: 237 */         setSideUV(0, u1, u2, v1, v2);
/*  289:     */       }
/*  290: 239 */       if ((sides & 0x2) > 0)
/*  291:     */       {
/*  292: 240 */         double u1 = this.boxSize1.z;double u2 = this.boxSize2.z;
/*  293: 241 */         setSideUV(1, u1, u2, v1, v2);
/*  294:     */       }
/*  295:     */     }
/*  296: 244 */     if ((sides & 0x3C) == 0) {
/*  297: 244 */       return;
/*  298:     */     }
/*  299: 245 */     double v1 = 1.0D - this.boxSize2.y;double v2 = 1.0D - this.boxSize1.y;
/*  300: 247 */     if ((sides & 0x4) > 0)
/*  301:     */     {
/*  302: 248 */       double u1 = 1.0D - this.boxSize2.x;double u2 = 1.0D - this.boxSize1.x;
/*  303: 249 */       setSideUV(2, u1, u2, v1, v2);
/*  304:     */     }
/*  305: 251 */     if ((sides & 0x8) > 0)
/*  306:     */     {
/*  307: 252 */       double u1 = this.boxSize1.x;double u2 = this.boxSize2.x;
/*  308: 253 */       setSideUV(3, u1, u2, v1, v2);
/*  309:     */     }
/*  310: 255 */     if ((sides & 0x10) > 0)
/*  311:     */     {
/*  312: 256 */       double u1 = this.boxSize1.z;double u2 = this.boxSize2.z;
/*  313: 257 */       setSideUV(4, u1, u2, v1, v2);
/*  314:     */     }
/*  315: 259 */     if ((sides & 0x20) > 0)
/*  316:     */     {
/*  317: 260 */       double u1 = 1.0D - this.boxSize2.z;double u2 = 1.0D - this.boxSize1.z;
/*  318: 261 */       setSideUV(5, u1, u2, v1, v2);
/*  319:     */     }
/*  320:     */   }
/*  321:     */   
/*  322:     */   public void calcBoundsGlobal()
/*  323:     */   {
/*  324: 266 */     setupBox();
/*  325: 267 */     transform();
/*  326:     */   }
/*  327:     */   
/*  328:     */   public void calcBounds()
/*  329:     */   {
/*  330: 271 */     setupBox();
/*  331: 272 */     transformRotate();
/*  332:     */   }
/*  333:     */   
/*  334:     */   private void swaptex(int a, int b)
/*  335:     */   {
/*  336: 277 */     int t = this.texIndexBox[a];
/*  337: 278 */     this.texIndexBox[a] = this.texIndexBox[b];
/*  338: 279 */     this.texIndexBox[b] = t;
/*  339:     */   }
/*  340:     */   
/*  341:     */   public void orientTextures(int down)
/*  342:     */   {
/*  343: 283 */     switch (down)
/*  344:     */     {
/*  345:     */     case 0: 
/*  346:     */       break;
/*  347:     */     case 1: 
/*  348: 286 */       swaptex(0, 1);
/*  349: 287 */       swaptex(4, 5);
/*  350: 288 */       this.texFlags = 112347;
/*  351: 289 */       break;
/*  352:     */     case 2: 
/*  353: 291 */       swaptex(0, 2);
/*  354: 292 */       swaptex(1, 3);
/*  355: 293 */       swaptex(0, 4);
/*  356: 294 */       swaptex(1, 5);
/*  357: 295 */       this.texFlags = 217134;
/*  358: 296 */       break;
/*  359:     */     case 3: 
/*  360: 298 */       swaptex(0, 3);
/*  361: 299 */       swaptex(1, 2);
/*  362: 300 */       swaptex(0, 4);
/*  363: 301 */       swaptex(1, 5);
/*  364: 302 */       this.texFlags = 188469;
/*  365: 303 */       break;
/*  366:     */     case 4: 
/*  367: 305 */       swaptex(0, 4);
/*  368: 306 */       swaptex(1, 5);
/*  369: 307 */       swaptex(2, 3);
/*  370: 308 */       this.texFlags = 2944;
/*  371: 309 */       break;
/*  372:     */     case 5: 
/*  373: 311 */       swaptex(0, 5);
/*  374: 312 */       swaptex(1, 4);
/*  375: 313 */       swaptex(0, 1);
/*  376: 314 */       this.texFlags = 3419;
/*  377: 315 */       break;
/*  378:     */     }
/*  379:     */   }
/*  380:     */   
/*  381:     */   public void orientTextureRot(int down, int rot)
/*  382:     */   {
/*  383: 323 */     int r = rot == 0 ? 0 : rot > 1 ? 6 : rot == 2 ? 3 : 5;
/*  384: 324 */     r |= r << 3;
/*  385: 325 */     switch (down)
/*  386:     */     {
/*  387:     */     case 0: 
/*  388: 327 */       this.texFlags = r;
/*  389: 328 */       break;
/*  390:     */     case 1: 
/*  391: 330 */       swaptex(0, 1);
/*  392: 331 */       swaptex(4, 5);
/*  393: 332 */       this.texFlags = (0x1B6DB ^ r);
/*  394: 333 */       break;
/*  395:     */     case 2: 
/*  396: 335 */       swaptex(0, 2);
/*  397: 336 */       swaptex(1, 3);
/*  398: 337 */       swaptex(0, 4);
/*  399: 338 */       swaptex(1, 5);
/*  400: 339 */       this.texFlags = (0x3502E ^ r << 6);
/*  401: 340 */       break;
/*  402:     */     case 3: 
/*  403: 342 */       swaptex(0, 3);
/*  404: 343 */       swaptex(1, 2);
/*  405: 344 */       swaptex(0, 4);
/*  406: 345 */       swaptex(1, 5);
/*  407: 346 */       this.texFlags = (0x2E035 ^ r << 6);
/*  408: 347 */       break;
/*  409:     */     case 4: 
/*  410: 349 */       swaptex(0, 4);
/*  411: 350 */       swaptex(1, 5);
/*  412: 351 */       swaptex(2, 3);
/*  413: 352 */       this.texFlags = (0xB80 ^ r << 12);
/*  414: 353 */       break;
/*  415:     */     case 5: 
/*  416: 355 */       swaptex(0, 5);
/*  417: 356 */       swaptex(1, 4);
/*  418: 357 */       swaptex(0, 1);
/*  419: 358 */       this.texFlags = (0xD5B ^ r << 12);
/*  420: 359 */       break;
/*  421:     */     }
/*  422:     */   }
/*  423:     */   
/*  424:     */   private void swaptexfl(int a, int b)
/*  425:     */   {
/*  426: 366 */     int t = this.texIndexBox[a];
/*  427: 367 */     this.texIndexBox[a] = this.texIndexBox[b];
/*  428: 368 */     this.texIndexBox[b] = t;
/*  429:     */     
/*  430: 370 */     a *= 3;b *= 3;
/*  431: 371 */     int f1 = this.texFlags >> a & 0x7;
/*  432: 372 */     int f2 = this.texFlags >> b & 0x7;
/*  433: 373 */     this.texFlags &= ((7 << a | 7 << b) ^ 0xFFFFFFFF);
/*  434: 374 */     this.texFlags |= f1 << b | f2 << a;
/*  435:     */   }
/*  436:     */   
/*  437:     */   public void rotateTextures(int rot)
/*  438:     */   {
/*  439: 378 */     int r = rot == 0 ? 0 : rot > 1 ? 6 : rot == 2 ? 3 : 5;
/*  440: 379 */     r |= r << 3;
/*  441: 380 */     this.texFlags ^= r;
/*  442: 382 */     switch (rot)
/*  443:     */     {
/*  444:     */     case 1: 
/*  445: 384 */       swaptexfl(2, 4);
/*  446: 385 */       swaptexfl(3, 4);
/*  447: 386 */       swaptexfl(3, 5);
/*  448: 387 */       break;
/*  449:     */     case 2: 
/*  450: 389 */       swaptexfl(2, 3);
/*  451: 390 */       swaptexfl(4, 5);
/*  452: 391 */       break;
/*  453:     */     case 3: 
/*  454: 393 */       swaptexfl(2, 5);
/*  455: 394 */       swaptexfl(3, 5);
/*  456: 395 */       swaptexfl(3, 4);
/*  457:     */     }
/*  458:     */   }
/*  459:     */   
/*  460:     */   public void orientTextureFl(int down)
/*  461:     */   {
/*  462: 402 */     switch (down)
/*  463:     */     {
/*  464:     */     case 0: 
/*  465:     */       break;
/*  466:     */     case 1: 
/*  467: 406 */       swaptexfl(0, 1);
/*  468: 407 */       swaptexfl(4, 5);
/*  469: 408 */       this.texFlags ^= 0x1B6DB;
/*  470: 409 */       break;
/*  471:     */     case 2: 
/*  472: 411 */       swaptexfl(0, 2);
/*  473: 412 */       swaptexfl(1, 3);
/*  474: 413 */       swaptexfl(0, 4);
/*  475: 414 */       swaptexfl(1, 5);
/*  476: 415 */       this.texFlags ^= 0x3502E;
/*  477: 416 */       break;
/*  478:     */     case 3: 
/*  479: 418 */       swaptexfl(0, 3);
/*  480: 419 */       swaptexfl(1, 2);
/*  481: 420 */       swaptexfl(0, 4);
/*  482: 421 */       swaptexfl(1, 5);
/*  483: 422 */       this.texFlags ^= 0x2E035;
/*  484: 423 */       break;
/*  485:     */     case 4: 
/*  486: 425 */       swaptexfl(0, 4);
/*  487: 426 */       swaptexfl(1, 5);
/*  488: 427 */       swaptexfl(2, 3);
/*  489: 428 */       this.texFlags ^= 0xB80;
/*  490: 429 */       break;
/*  491:     */     case 5: 
/*  492: 431 */       swaptexfl(0, 5);
/*  493: 432 */       swaptexfl(1, 4);
/*  494: 433 */       swaptexfl(0, 1);
/*  495: 434 */       this.texFlags ^= 0xD5B;
/*  496: 435 */       break;
/*  497:     */     }
/*  498:     */   }
/*  499:     */   
/*  500: 440 */   public static final int[][] texRotTable = { { 0, 1, 2, 3, 4, 5, 0, 112347, 0 }, { 0, 1, 4, 5, 3, 2, 45, 112320, 27 }, { 0, 1, 3, 2, 5, 4, 27, 112347, 0 }, { 0, 1, 5, 4, 2, 3, 54, 112320, 27 }, { 1, 0, 2, 3, 5, 4, 112347, 112347, 0 }, { 1, 0, 4, 5, 2, 3, 112374, 112320, 27 }, { 1, 0, 3, 2, 4, 5, 112320, 112347, 0 }, { 1, 0, 5, 4, 3, 2, 112365, 112320, 27 }, { 4, 5, 0, 1, 2, 3, 217134, 1728, 110619 }, { 3, 2, 0, 1, 4, 5, 220014, 0, 112347 }, { 5, 4, 0, 1, 3, 2, 218862, 1728, 110619 }, { 2, 3, 0, 1, 5, 4, 220590, 0, 112347 }, { 4, 5, 1, 0, 3, 2, 188469, 1728, 110619 }, { 3, 2, 1, 0, 5, 4, 191349, 0, 112347 }, { 5, 4, 1, 0, 2, 3, 190197, 1728, 110619 }, { 2, 3, 1, 0, 4, 5, 191925, 0, 112347 }, { 4, 5, 3, 2, 0, 1, 2944, 110619, 1728 }, { 3, 2, 5, 4, 0, 1, 187264, 27, 112320 }, { 5, 4, 2, 3, 0, 1, 113536, 110619, 1728 }, { 2, 3, 4, 5, 0, 1, 224128, 27, 112320 }, { 4, 5, 2, 3, 1, 0, 3419, 110619, 1728 }, { 3, 2, 4, 5, 1, 0, 187739, 27, 112320 }, { 5, 4, 3, 2, 1, 0, 114011, 110619, 1728 }, { 2, 3, 5, 4, 1, 0, 224603, 27, 112320 } };
/*  501:     */   
/*  502:     */   public void orientTextureNew(int rv)
/*  503:     */   {
/*  504: 468 */     int[] texSrc = new int[6];
/*  505: 469 */     System.arraycopy(this.texIndexBox, 0, texSrc, 0, 6);
/*  506:     */     
/*  507: 471 */     int[] rot = texRotTable[rv];
/*  508: 472 */     int tfo = 0;
/*  509: 473 */     for (int i = 0; i < 6; i++)
/*  510:     */     {
/*  511: 474 */       this.texIndexBox[i] = texSrc[rot[i]];
/*  512: 475 */       tfo |= (this.texFlags >> rot[i] * 3 & 0x7) << i * 3;
/*  513:     */     }
/*  514: 478 */     int t2 = (tfo & 0x9249) << 1 | (tfo & 0x12492) >> 1;
/*  515: 479 */     this.texFlags = (rot[6] ^ tfo & rot[7] ^ t2 & rot[8]);
/*  516:     */   }
/*  517:     */   
/*  518:     */   public void flipTextures()
/*  519:     */   {
/*  520: 483 */     swaptex(0, 1);swaptex(2, 3);swaptex(4, 5);
/*  521:     */   }
/*  522:     */   
/*  523:     */   public void renderBox(int sides, double x1, double y1, double z1, double x2, double y2, double z2)
/*  524:     */   {
/*  525: 490 */     setSize(x1, y1, z1, x2, y2, z2);
/*  526: 491 */     setupBox();
/*  527: 492 */     transformRotate();
/*  528: 493 */     renderFaces(sides);
/*  529:     */   }
/*  530:     */   
/*  531:     */   public void doubleBox(int sides, double x1, double y1, double z1, double x2, double y2, double z2, double ino)
/*  532:     */   {
/*  533: 498 */     int s2 = sides << 1 & 0x2A | sides >> 1 & 0x15;
/*  534:     */     
/*  535: 500 */     renderBox(sides, x1, y1, z1, x2, y2, z2);
/*  536: 501 */     flipTextures();
/*  537: 502 */     renderBox(s2, x2 - ino, y2 - ino, z2 - ino, x1 + ino, y1 + ino, z1 + ino);
/*  538:     */   }
/*  539:     */   
/*  540:     */   public void doLightLocal(int sides)
/*  541:     */   {
/*  542: 511 */     for (int i = 0; i < this.cornerList.length; i++) {
/*  543: 512 */       if ((sides & 1 << i) != 0)
/*  544:     */       {
/*  545: 513 */         TexVertex c = this.cornerList[i][0];
/*  546: 514 */         c.r = (this.lightLocal[i] * this.tintR);
/*  547: 515 */         c.g = (this.lightLocal[i] * this.tintG);
/*  548: 516 */         c.b = (this.lightLocal[i] * this.tintB);
/*  549: 517 */         c.brtex = this.brightLocal[i];
/*  550:     */       }
/*  551:     */     }
/*  552:     */   }
/*  553:     */   
/*  554:     */   public void readGlobalLights(ym iba, int i, int j, int k)
/*  555:     */   {
/*  556: 526 */     amq block = amq.p[iba.a(i, j, k)];
/*  557: 527 */     if ((!Minecraft.u()) || (this.forceFlat))
/*  558:     */     {
/*  559: 528 */       this.lightFlat[0] = block.f(iba, i, j - 1, k);
/*  560: 529 */       this.lightFlat[1] = block.f(iba, i, j + 1, k);
/*  561: 530 */       this.lightFlat[2] = block.f(iba, i, j, k - 1);
/*  562: 531 */       this.lightFlat[3] = block.f(iba, i, j, k + 1);
/*  563: 532 */       this.lightFlat[4] = block.f(iba, i - 1, j, k);
/*  564: 533 */       this.lightFlat[5] = block.f(iba, i + 1, j, k);
/*  565: 534 */       return;
/*  566:     */     }
/*  567: 536 */     for (int a = 0; a < 3; a++) {
/*  568: 536 */       for (int b = 0; b < 3; b++) {
/*  569: 536 */         for (int c = 0; c < 3; c++)
/*  570:     */         {
/*  571: 537 */           this.aoGlobal[a][b][c] = block.j(iba, i + a - 1, j + b - 1, k + c - 1);
/*  572:     */           
/*  573: 539 */           this.lightGlobal[a][b][c] = block.e(iba, i + a - 1, j + b - 1, k + c - 1);
/*  574:     */         }
/*  575:     */       }
/*  576:     */     }
/*  577: 543 */     int t = 0;
/*  578: 544 */     if (amq.s[iba.a(i, j - 1, k - 1)] != 0) {
/*  579: 544 */       t |= 0x1;
/*  580:     */     }
/*  581: 545 */     if (amq.s[iba.a(i, j - 1, k + 1)] != 0) {
/*  582: 545 */       t |= 0x2;
/*  583:     */     }
/*  584: 546 */     if (amq.s[iba.a(i - 1, j - 1, k)] != 0) {
/*  585: 546 */       t |= 0x4;
/*  586:     */     }
/*  587: 547 */     if (amq.s[iba.a(i + 1, j - 1, k)] != 0) {
/*  588: 547 */       t |= 0x8;
/*  589:     */     }
/*  590: 549 */     if (amq.s[iba.a(i - 1, j, k - 1)] != 0) {
/*  591: 549 */       t |= 0x10;
/*  592:     */     }
/*  593: 550 */     if (amq.s[iba.a(i - 1, j, k + 1)] != 0) {
/*  594: 550 */       t |= 0x20;
/*  595:     */     }
/*  596: 551 */     if (amq.s[iba.a(i + 1, j, k - 1)] != 0) {
/*  597: 551 */       t |= 0x40;
/*  598:     */     }
/*  599: 552 */     if (amq.s[iba.a(i + 1, j, k + 1)] != 0) {
/*  600: 552 */       t |= 0x80;
/*  601:     */     }
/*  602: 554 */     if (amq.s[iba.a(i, j + 1, k - 1)] != 0) {
/*  603: 554 */       t |= 0x100;
/*  604:     */     }
/*  605: 555 */     if (amq.s[iba.a(i, j + 1, k + 1)] != 0) {
/*  606: 555 */       t |= 0x200;
/*  607:     */     }
/*  608: 556 */     if (amq.s[iba.a(i - 1, j + 1, k)] != 0) {
/*  609: 556 */       t |= 0x400;
/*  610:     */     }
/*  611: 557 */     if (amq.s[iba.a(i + 1, j + 1, k)] != 0) {
/*  612: 557 */       t |= 0x800;
/*  613:     */     }
/*  614: 558 */     this.globTrans = t;
/*  615:     */   }
/*  616:     */   
/*  617:     */   public static int blendLight(int i, int j, int k, int l)
/*  618:     */   {
/*  619: 562 */     if (j == 0) {
/*  620: 562 */       j = i;
/*  621:     */     }
/*  622: 563 */     if (k == 0) {
/*  623: 563 */       k = i;
/*  624:     */     }
/*  625: 564 */     if (l == 0) {
/*  626: 564 */       l = i;
/*  627:     */     }
/*  628: 565 */     return i + j + k + l >> 2 & 0xFF00FF;
/*  629:     */   }
/*  630:     */   
/*  631:     */   private void lightSmoothFace(int fn)
/*  632:     */   {
/*  633: 575 */     int ff = 0;
/*  634: 576 */     if (this.boxSize1.y > 0.0D) {
/*  635: 576 */       ff |= 0x1;
/*  636:     */     }
/*  637: 577 */     if (this.boxSize2.y < 1.0D) {
/*  638: 577 */       ff |= 0x2;
/*  639:     */     }
/*  640: 578 */     if (this.boxSize1.z > 0.0D) {
/*  641: 578 */       ff |= 0x4;
/*  642:     */     }
/*  643: 579 */     if (this.boxSize2.z < 1.0D) {
/*  644: 579 */       ff |= 0x8;
/*  645:     */     }
/*  646: 580 */     if (this.boxSize1.x > 0.0D) {
/*  647: 580 */       ff |= 0x10;
/*  648:     */     }
/*  649: 581 */     if (this.boxSize2.x < 1.0D) {
/*  650: 581 */       ff |= 0x20;
/*  651:     */     }
/*  652:     */     float gf4;
/*  653:     */     float gf3;
/*  654:     */     float gf2;
/*  655: 583 */     float gf1 = gf2 = gf3 = gf4 = this.aoGlobal[1][1][1];
/*  656:     */     int gl4;
/*  657:     */     int gl3;
/*  658:     */     int gl2;
/*  659: 585 */     int gl1 = gl2 = gl3 = gl4 = this.lightGlobal[1][1][1];
/*  660:     */     float ao2;
/*  661:     */     float ao1;
/*  662:     */     float ao4;
/*  663:     */     float ao3;
/*  664:     */     int lv2;
/*  665:     */     int lv1;
/*  666:     */     int lv4;
/*  667:     */     int lv3;
/*  668: 587 */     switch (fn)
/*  669:     */     {
/*  670:     */     case 0: 
/*  671: 589 */       if ((ff & 0x3D) <= 0)
/*  672:     */       {
/*  673: 590 */         ao1 = ao2 = this.aoGlobal[0][0][1];
/*  674: 591 */         ao3 = ao4 = this.aoGlobal[2][0][1];
/*  675: 592 */         lv1 = lv2 = this.lightGlobal[0][0][1];
/*  676: 593 */         lv3 = lv4 = this.lightGlobal[2][0][1];
/*  677: 594 */         if ((this.globTrans & 0x5) > 0)
/*  678:     */         {
/*  679: 595 */           ao1 = this.aoGlobal[0][0][0];
/*  680: 596 */           lv1 = this.lightGlobal[0][0][0];
/*  681:     */         }
/*  682: 598 */         if ((this.globTrans & 0x6) > 0)
/*  683:     */         {
/*  684: 599 */           ao2 = this.aoGlobal[0][0][2];
/*  685: 600 */           lv2 = this.lightGlobal[0][0][2];
/*  686:     */         }
/*  687: 602 */         if ((this.globTrans & 0x9) > 0)
/*  688:     */         {
/*  689: 603 */           ao3 = this.aoGlobal[2][0][0];
/*  690: 604 */           lv3 = this.lightGlobal[2][0][0];
/*  691:     */         }
/*  692: 606 */         if ((this.globTrans & 0xA) > 0)
/*  693:     */         {
/*  694: 607 */           ao4 = this.aoGlobal[2][0][2];
/*  695: 608 */           lv4 = this.lightGlobal[2][0][2];
/*  696:     */         }
/*  697: 610 */         gf3 = 0.25F * (this.aoGlobal[1][0][1] + this.aoGlobal[1][0][0] + this.aoGlobal[0][0][1] + ao1);
/*  698:     */         
/*  699: 612 */         gf4 = 0.25F * (this.aoGlobal[1][0][1] + this.aoGlobal[1][0][0] + this.aoGlobal[2][0][1] + ao3);
/*  700:     */         
/*  701: 614 */         gf2 = 0.25F * (this.aoGlobal[1][0][1] + this.aoGlobal[1][0][2] + this.aoGlobal[0][0][1] + ao2);
/*  702:     */         
/*  703: 616 */         gf1 = 0.25F * (this.aoGlobal[1][0][1] + this.aoGlobal[1][0][2] + this.aoGlobal[2][0][1] + ao4);
/*  704:     */         
/*  705:     */ 
/*  706: 619 */         gl3 = blendLight(this.lightGlobal[1][0][1], this.lightGlobal[1][0][0], this.lightGlobal[0][0][1], lv1);
/*  707:     */         
/*  708: 621 */         gl4 = blendLight(this.lightGlobal[1][0][1], this.lightGlobal[1][0][0], this.lightGlobal[2][0][1], lv3);
/*  709:     */         
/*  710: 623 */         gl2 = blendLight(this.lightGlobal[1][0][1], this.lightGlobal[1][0][2], this.lightGlobal[0][0][1], lv2);
/*  711:     */         
/*  712: 625 */         gl1 = blendLight(this.lightGlobal[1][0][1], this.lightGlobal[1][0][2], this.lightGlobal[2][0][1], lv4);
/*  713:     */       }
/*  714: 627 */       break;
/*  715:     */     case 1: 
/*  716: 646 */       if ((ff & 0x3E) <= 0)
/*  717:     */       {
/*  718: 647 */         ao1 = ao2 = this.aoGlobal[0][2][1];
/*  719: 648 */         ao3 = ao4 = this.aoGlobal[2][2][1];
/*  720: 649 */         lv1 = lv2 = this.lightGlobal[0][2][1];
/*  721: 650 */         lv3 = lv4 = this.lightGlobal[2][2][1];
/*  722: 651 */         if ((this.globTrans & 0x500) > 0)
/*  723:     */         {
/*  724: 652 */           ao1 = this.aoGlobal[0][2][0];
/*  725: 653 */           lv1 = this.lightGlobal[0][2][0];
/*  726:     */         }
/*  727: 655 */         if ((this.globTrans & 0x600) > 0)
/*  728:     */         {
/*  729: 656 */           ao2 = this.aoGlobal[0][2][2];
/*  730: 657 */           lv2 = this.lightGlobal[0][2][2];
/*  731:     */         }
/*  732: 659 */         if ((this.globTrans & 0x900) > 0)
/*  733:     */         {
/*  734: 660 */           ao3 = this.aoGlobal[2][2][0];
/*  735: 661 */           lv3 = this.lightGlobal[2][2][0];
/*  736:     */         }
/*  737: 663 */         if ((this.globTrans & 0xA00) > 0)
/*  738:     */         {
/*  739: 664 */           ao4 = this.aoGlobal[2][2][2];
/*  740: 665 */           lv4 = this.lightGlobal[2][2][2];
/*  741:     */         }
/*  742: 667 */         gf2 = 0.25F * (this.aoGlobal[1][2][1] + this.aoGlobal[1][2][0] + this.aoGlobal[0][2][1] + ao1);
/*  743:     */         
/*  744: 669 */         gf1 = 0.25F * (this.aoGlobal[1][2][1] + this.aoGlobal[1][2][0] + this.aoGlobal[2][2][1] + ao3);
/*  745:     */         
/*  746: 671 */         gf3 = 0.25F * (this.aoGlobal[1][2][1] + this.aoGlobal[1][2][2] + this.aoGlobal[0][2][1] + ao2);
/*  747:     */         
/*  748: 673 */         gf4 = 0.25F * (this.aoGlobal[1][2][1] + this.aoGlobal[1][2][2] + this.aoGlobal[2][2][1] + ao4);
/*  749:     */         
/*  750:     */ 
/*  751: 676 */         gl2 = blendLight(this.lightGlobal[1][2][1], this.lightGlobal[1][2][0], this.lightGlobal[0][2][1], lv1);
/*  752:     */         
/*  753: 678 */         gl1 = blendLight(this.lightGlobal[1][2][1], this.lightGlobal[1][2][0], this.lightGlobal[2][2][1], lv3);
/*  754:     */         
/*  755: 680 */         gl3 = blendLight(this.lightGlobal[1][2][1], this.lightGlobal[1][2][2], this.lightGlobal[0][2][1], lv2);
/*  756:     */         
/*  757: 682 */         gl4 = blendLight(this.lightGlobal[1][2][1], this.lightGlobal[1][2][2], this.lightGlobal[2][2][1], lv4);
/*  758:     */       }
/*  759: 684 */       break;
/*  760:     */     case 2: 
/*  761: 703 */       if ((ff & 0x37) <= 0)
/*  762:     */       {
/*  763: 704 */         ao1 = ao2 = this.aoGlobal[0][1][0];
/*  764: 705 */         ao3 = ao4 = this.aoGlobal[2][1][0];
/*  765: 706 */         lv1 = lv2 = this.lightGlobal[0][1][0];
/*  766: 707 */         lv3 = lv4 = this.lightGlobal[2][1][0];
/*  767: 708 */         if ((this.globTrans & 0x11) > 0)
/*  768:     */         {
/*  769: 709 */           ao1 = this.aoGlobal[0][0][0];
/*  770: 710 */           lv1 = this.lightGlobal[0][0][0];
/*  771:     */         }
/*  772: 712 */         if ((this.globTrans & 0x110) > 0)
/*  773:     */         {
/*  774: 713 */           ao2 = this.aoGlobal[0][2][0];
/*  775: 714 */           lv2 = this.lightGlobal[0][2][0];
/*  776:     */         }
/*  777: 716 */         if ((this.globTrans & 0x41) > 0)
/*  778:     */         {
/*  779: 717 */           ao3 = this.aoGlobal[2][0][0];
/*  780: 718 */           lv3 = this.lightGlobal[2][0][0];
/*  781:     */         }
/*  782: 720 */         if ((this.globTrans & 0x140) > 0)
/*  783:     */         {
/*  784: 721 */           ao4 = this.aoGlobal[2][2][0];
/*  785: 722 */           lv4 = this.lightGlobal[2][2][0];
/*  786:     */         }
/*  787: 724 */         gf3 = 0.25F * (this.aoGlobal[1][1][0] + this.aoGlobal[1][0][0] + this.aoGlobal[0][1][0] + ao1);
/*  788:     */         
/*  789: 726 */         gf4 = 0.25F * (this.aoGlobal[1][1][0] + this.aoGlobal[1][2][0] + this.aoGlobal[0][1][0] + ao2);
/*  790:     */         
/*  791: 728 */         gf2 = 0.25F * (this.aoGlobal[1][1][0] + this.aoGlobal[1][0][0] + this.aoGlobal[2][1][0] + ao3);
/*  792:     */         
/*  793: 730 */         gf1 = 0.25F * (this.aoGlobal[1][1][0] + this.aoGlobal[1][2][0] + this.aoGlobal[2][1][0] + ao4);
/*  794:     */         
/*  795:     */ 
/*  796: 733 */         gl3 = blendLight(this.lightGlobal[1][1][0], this.lightGlobal[1][0][0], this.lightGlobal[0][1][0], lv1);
/*  797:     */         
/*  798: 735 */         gl4 = blendLight(this.lightGlobal[1][1][0], this.lightGlobal[1][2][0], this.lightGlobal[0][1][0], lv2);
/*  799:     */         
/*  800: 737 */         gl2 = blendLight(this.lightGlobal[1][1][0], this.lightGlobal[1][0][0], this.lightGlobal[2][1][0], lv3);
/*  801:     */         
/*  802: 739 */         gl1 = blendLight(this.lightGlobal[1][1][0], this.lightGlobal[1][2][0], this.lightGlobal[2][1][0], lv4);
/*  803:     */       }
/*  804: 741 */       break;
/*  805:     */     case 3: 
/*  806: 760 */       if ((ff & 0x3B) <= 0)
/*  807:     */       {
/*  808: 761 */         ao1 = ao2 = this.aoGlobal[0][1][2];
/*  809: 762 */         ao3 = ao4 = this.aoGlobal[2][1][2];
/*  810: 763 */         lv1 = lv2 = this.lightGlobal[0][1][2];
/*  811: 764 */         lv3 = lv4 = this.lightGlobal[2][1][2];
/*  812: 765 */         if ((this.globTrans & 0x22) > 0)
/*  813:     */         {
/*  814: 766 */           ao1 = this.aoGlobal[0][0][2];
/*  815: 767 */           lv1 = this.lightGlobal[0][0][2];
/*  816:     */         }
/*  817: 769 */         if ((this.globTrans & 0x220) > 0)
/*  818:     */         {
/*  819: 770 */           ao2 = this.aoGlobal[0][2][2];
/*  820: 771 */           lv2 = this.lightGlobal[0][2][2];
/*  821:     */         }
/*  822: 773 */         if ((this.globTrans & 0x82) > 0)
/*  823:     */         {
/*  824: 774 */           ao3 = this.aoGlobal[2][0][2];
/*  825: 775 */           lv3 = this.lightGlobal[2][0][2];
/*  826:     */         }
/*  827: 777 */         if ((this.globTrans & 0x280) > 0)
/*  828:     */         {
/*  829: 778 */           ao4 = this.aoGlobal[2][2][2];
/*  830: 779 */           lv4 = this.lightGlobal[2][2][2];
/*  831:     */         }
/*  832: 781 */         gf2 = 0.25F * (this.aoGlobal[1][1][2] + this.aoGlobal[1][0][2] + this.aoGlobal[0][1][2] + ao1);
/*  833:     */         
/*  834: 783 */         gf1 = 0.25F * (this.aoGlobal[1][1][2] + this.aoGlobal[1][2][2] + this.aoGlobal[0][1][2] + ao3);
/*  835:     */         
/*  836: 785 */         gf3 = 0.25F * (this.aoGlobal[1][1][2] + this.aoGlobal[1][0][2] + this.aoGlobal[2][1][2] + ao2);
/*  837:     */         
/*  838: 787 */         gf4 = 0.25F * (this.aoGlobal[1][1][2] + this.aoGlobal[1][2][2] + this.aoGlobal[2][1][2] + ao4);
/*  839:     */         
/*  840:     */ 
/*  841: 790 */         gl2 = blendLight(this.lightGlobal[1][1][2], this.lightGlobal[1][0][2], this.lightGlobal[0][1][2], lv1);
/*  842:     */         
/*  843: 792 */         gl1 = blendLight(this.lightGlobal[1][1][2], this.lightGlobal[1][2][2], this.lightGlobal[0][1][2], lv2);
/*  844:     */         
/*  845: 794 */         gl3 = blendLight(this.lightGlobal[1][1][2], this.lightGlobal[1][0][2], this.lightGlobal[2][1][2], lv3);
/*  846:     */         
/*  847: 796 */         gl4 = blendLight(this.lightGlobal[1][1][2], this.lightGlobal[1][2][2], this.lightGlobal[2][1][2], lv4);
/*  848:     */       }
/*  849: 798 */       break;
/*  850:     */     case 4: 
/*  851: 817 */       if ((ff & 0x1F) <= 0)
/*  852:     */       {
/*  853: 818 */         ao1 = ao2 = this.aoGlobal[0][1][0];
/*  854: 819 */         ao3 = ao4 = this.aoGlobal[0][1][2];
/*  855: 820 */         lv1 = lv2 = this.lightGlobal[0][1][0];
/*  856: 821 */         lv3 = lv4 = this.lightGlobal[0][1][2];
/*  857: 822 */         if ((this.globTrans & 0x14) > 0)
/*  858:     */         {
/*  859: 823 */           ao1 = this.aoGlobal[0][0][0];
/*  860: 824 */           lv1 = this.lightGlobal[0][0][0];
/*  861:     */         }
/*  862: 826 */         if ((this.globTrans & 0x410) > 0)
/*  863:     */         {
/*  864: 827 */           ao2 = this.aoGlobal[0][2][0];
/*  865: 828 */           lv2 = this.lightGlobal[0][2][0];
/*  866:     */         }
/*  867: 830 */         if ((this.globTrans & 0x24) > 0)
/*  868:     */         {
/*  869: 831 */           ao3 = this.aoGlobal[0][0][2];
/*  870: 832 */           lv3 = this.lightGlobal[0][0][2];
/*  871:     */         }
/*  872: 834 */         if ((this.globTrans & 0x420) > 0)
/*  873:     */         {
/*  874: 835 */           ao4 = this.aoGlobal[0][2][2];
/*  875: 836 */           lv4 = this.lightGlobal[0][2][2];
/*  876:     */         }
/*  877: 838 */         gf2 = 0.25F * (this.aoGlobal[0][1][1] + this.aoGlobal[0][0][1] + this.aoGlobal[0][1][0] + ao1);
/*  878:     */         
/*  879: 840 */         gf1 = 0.25F * (this.aoGlobal[0][1][1] + this.aoGlobal[0][2][1] + this.aoGlobal[0][1][0] + ao2);
/*  880:     */         
/*  881: 842 */         gf3 = 0.25F * (this.aoGlobal[0][1][1] + this.aoGlobal[0][0][1] + this.aoGlobal[0][1][2] + ao3);
/*  882:     */         
/*  883: 844 */         gf4 = 0.25F * (this.aoGlobal[0][1][1] + this.aoGlobal[0][2][1] + this.aoGlobal[0][1][2] + ao4);
/*  884:     */         
/*  885:     */ 
/*  886: 847 */         gl2 = blendLight(this.lightGlobal[0][1][1], this.lightGlobal[0][0][1], this.lightGlobal[0][1][0], lv1);
/*  887:     */         
/*  888: 849 */         gl1 = blendLight(this.lightGlobal[0][1][1], this.lightGlobal[0][2][1], this.lightGlobal[0][1][0], lv2);
/*  889:     */         
/*  890: 851 */         gl3 = blendLight(this.lightGlobal[0][1][1], this.lightGlobal[0][0][1], this.lightGlobal[0][1][2], lv3);
/*  891:     */         
/*  892: 853 */         gl4 = blendLight(this.lightGlobal[0][1][1], this.lightGlobal[0][2][1], this.lightGlobal[0][1][2], lv4);
/*  893:     */       }
/*  894: 855 */       break;
/*  895:     */     default: 
/*  896: 874 */       if ((ff & 0x2F) <= 0)
/*  897:     */       {
/*  898: 875 */         ao1 = ao2 = this.aoGlobal[2][1][0];
/*  899: 876 */         ao3 = ao4 = this.aoGlobal[2][1][2];
/*  900: 877 */         lv1 = lv2 = this.lightGlobal[2][1][0];
/*  901: 878 */         lv3 = lv4 = this.lightGlobal[2][1][2];
/*  902: 879 */         if ((this.globTrans & 0x48) > 0)
/*  903:     */         {
/*  904: 880 */           ao1 = this.aoGlobal[2][0][0];
/*  905: 881 */           lv1 = this.lightGlobal[2][0][0];
/*  906:     */         }
/*  907: 883 */         if ((this.globTrans & 0x840) > 0)
/*  908:     */         {
/*  909: 884 */           ao2 = this.aoGlobal[2][2][0];
/*  910: 885 */           lv2 = this.lightGlobal[2][2][0];
/*  911:     */         }
/*  912: 887 */         if ((this.globTrans & 0x88) > 0)
/*  913:     */         {
/*  914: 888 */           ao3 = this.aoGlobal[2][0][2];
/*  915: 889 */           lv3 = this.lightGlobal[2][0][2];
/*  916:     */         }
/*  917: 891 */         if ((this.globTrans & 0x880) > 0)
/*  918:     */         {
/*  919: 892 */           ao4 = this.aoGlobal[2][2][2];
/*  920: 893 */           lv4 = this.lightGlobal[2][2][2];
/*  921:     */         }
/*  922: 895 */         gf3 = 0.25F * (this.aoGlobal[2][1][1] + this.aoGlobal[2][0][1] + this.aoGlobal[2][1][0] + ao1);
/*  923:     */         
/*  924: 897 */         gf4 = 0.25F * (this.aoGlobal[2][1][1] + this.aoGlobal[2][2][1] + this.aoGlobal[2][1][0] + ao2);
/*  925:     */         
/*  926: 899 */         gf2 = 0.25F * (this.aoGlobal[2][1][1] + this.aoGlobal[2][0][1] + this.aoGlobal[2][1][2] + ao3);
/*  927:     */         
/*  928: 901 */         gf1 = 0.25F * (this.aoGlobal[2][1][1] + this.aoGlobal[2][2][1] + this.aoGlobal[2][1][2] + ao4);
/*  929:     */         
/*  930:     */ 
/*  931: 904 */         gl3 = blendLight(this.lightGlobal[2][1][1], this.lightGlobal[2][0][1], this.lightGlobal[2][1][0], lv1);
/*  932:     */         
/*  933: 906 */         gl4 = blendLight(this.lightGlobal[2][1][1], this.lightGlobal[2][2][1], this.lightGlobal[2][1][0], lv2);
/*  934:     */         
/*  935: 908 */         gl2 = blendLight(this.lightGlobal[2][1][1], this.lightGlobal[2][0][1], this.lightGlobal[2][1][2], lv3);
/*  936:     */         
/*  937: 910 */         gl1 = blendLight(this.lightGlobal[2][1][1], this.lightGlobal[2][2][1], this.lightGlobal[2][1][2], lv4);
/*  938:     */       }
/*  939:     */       break;
/*  940:     */     }
/*  941: 917 */     TexVertex c = this.cornerList[fn][0];
/*  942: 918 */     float fc = this.lightLocal[fn] * gf1;
/*  943: 919 */     c.r = (fc * this.tintR);c.g = (fc * this.tintG);c.b = (fc * this.tintB);
/*  944: 920 */     c.brtex = gl1;
/*  945:     */     
/*  946: 922 */     c = this.cornerList[fn][1];
/*  947: 923 */     fc = this.lightLocal[fn] * gf2;
/*  948: 924 */     c.r = (fc * this.tintR);c.g = (fc * this.tintG);c.b = (fc * this.tintB);
/*  949: 925 */     c.brtex = gl2;
/*  950:     */     
/*  951: 927 */     c = this.cornerList[fn][2];
/*  952: 928 */     fc = this.lightLocal[fn] * gf3;
/*  953: 929 */     c.r = (fc * this.tintR);c.g = (fc * this.tintG);c.b = (fc * this.tintB);
/*  954: 930 */     c.brtex = gl3;
/*  955:     */     
/*  956: 932 */     c = this.cornerList[fn][3];
/*  957: 933 */     fc = this.lightLocal[fn] * gf4;
/*  958: 934 */     c.r = (fc * this.tintR);c.g = (fc * this.tintG);c.b = (fc * this.tintB);
/*  959: 935 */     c.brtex = gl4;
/*  960:     */   }
/*  961:     */   
/*  962:     */   public void doLightSmooth(int sides)
/*  963:     */   {
/*  964: 940 */     for (int i = 0; i < 6; i++) {
/*  965: 941 */       if ((sides & 1 << i) != 0) {
/*  966: 942 */         lightSmoothFace(i);
/*  967:     */       }
/*  968:     */     }
/*  969:     */   }
/*  970:     */   
/*  971:     */   private void doLightFlat(int sides)
/*  972:     */   {
/*  973: 950 */     for (int i = 0; i < this.cornerList.length; i++) {
/*  974: 951 */       if ((sides & 1 << i) != 0)
/*  975:     */       {
/*  976: 952 */         TexVertex c = this.cornerList[i][0];
/*  977: 953 */         c.r = (this.lightFlat[i] * this.lightLocal[i] * this.tintR);
/*  978: 954 */         c.g = (this.lightFlat[i] * this.lightLocal[i] * this.tintG);
/*  979: 955 */         c.b = (this.lightFlat[i] * this.lightLocal[i] * this.tintB);
/*  980: 956 */         c.brtex = this.brightLocal[i];
/*  981:     */       }
/*  982:     */     }
/*  983:     */   }
/*  984:     */   
/*  985:     */   public void renderFlat(int sides)
/*  986:     */   {
/*  987: 963 */     baz tessellator = baz.a;
/*  988: 968 */     for (int i = 0; i < this.cornerList.length; i++) {
/*  989: 969 */       if ((sides & 1 << i) != 0)
/*  990:     */       {
/*  991: 971 */         if (this.texBinds != null)
/*  992:     */         {
/*  993: 972 */           if (this.texBinds[i] != null) {
/*  994: 973 */             RenderLib.bindTexture(this.texBinds[i]);
/*  995:     */           } else {
/*  996: 975 */             RenderLib.unbindTexture();
/*  997:     */           }
/*  998: 977 */           tessellator = baz.a;
/*  999:     */         }
/* 1000: 980 */         TexVertex c = this.cornerList[i][0];
/* 1001: 981 */         tessellator.a(c.r, c.g, c.b);
/* 1002: 982 */         if (this.useNormal)
/* 1003:     */         {
/* 1004: 983 */           Vector3 v = this.vertexList[c.vtx];
/* 1005: 984 */           c = this.cornerList[i][1];
/* 1006: 985 */           Vector3 v1 = new Vector3(this.vertexList[c.vtx]);
/* 1007: 986 */           c = this.cornerList[i][2];
/* 1008: 987 */           Vector3 v2 = new Vector3(this.vertexList[c.vtx]);
/* 1009: 988 */           v1.subtract(v);v2.subtract(v);
/* 1010: 989 */           v1.crossProduct(v2);
/* 1011: 990 */           v1.normalize();
/* 1012: 991 */           tessellator.b((float)v1.x, (float)v1.y, (float)v1.z);
/* 1013:     */         }
/* 1014:     */         else
/* 1015:     */         {
/* 1016: 993 */           tessellator.c(c.brtex);
/* 1017:     */         }
/* 1018: 996 */         for (int j = 0; j < 4; j++)
/* 1019:     */         {
/* 1020: 997 */           c = this.cornerList[i][j];
/* 1021: 998 */           Vector3 v = this.vertexList[c.vtx];
/* 1022: 999 */           tessellator.a(v.x, v.y, v.z, c.u, c.v);
/* 1023:     */         }
/* 1024:     */       }
/* 1025:     */     }
/* 1026:1003 */     if (this.texBinds != null) {
/* 1027:1003 */       RenderLib.unbindTexture();
/* 1028:     */     }
/* 1029:     */   }
/* 1030:     */   
/* 1031:     */   public void renderRangeFlat(int st, int ed)
/* 1032:     */   {
/* 1033:1007 */     baz tessellator = baz.a;
/* 1034:1012 */     for (int i = st; i < ed; i++)
/* 1035:     */     {
/* 1036:1013 */       if (this.texBinds != null)
/* 1037:     */       {
/* 1038:1014 */         if (this.texBinds[i] != null) {
/* 1039:1015 */           RenderLib.bindTexture(this.texBinds[i]);
/* 1040:     */         } else {
/* 1041:1017 */           RenderLib.unbindTexture();
/* 1042:     */         }
/* 1043:1019 */         tessellator = baz.a;
/* 1044:     */       }
/* 1045:1022 */       TexVertex c = this.cornerList[i][0];
/* 1046:1023 */       tessellator.a(c.r * this.tintR, c.g * this.tintG, c.b * this.tintB, this.tintA);
/* 1047:1025 */       if (this.useNormal)
/* 1048:     */       {
/* 1049:1026 */         Vector3 v = this.vertexList[c.vtx];
/* 1050:1027 */         c = this.cornerList[i][1];
/* 1051:1028 */         Vector3 v1 = new Vector3(this.vertexList[c.vtx]);
/* 1052:1029 */         c = this.cornerList[i][2];
/* 1053:1030 */         Vector3 v2 = new Vector3(this.vertexList[c.vtx]);
/* 1054:1031 */         v1.subtract(v);v2.subtract(v);
/* 1055:1032 */         v1.crossProduct(v2);
/* 1056:1033 */         v1.normalize();
/* 1057:1034 */         tessellator.b((float)v1.x, (float)v1.y, (float)v1.z);
/* 1058:     */       }
/* 1059:     */       else
/* 1060:     */       {
/* 1061:1036 */         tessellator.c(c.brtex);
/* 1062:     */       }
/* 1063:1038 */       for (int j = 0; j < 4; j++)
/* 1064:     */       {
/* 1065:1039 */         c = this.cornerList[i][j];
/* 1066:1040 */         Vector3 v = this.vertexList[c.vtx];
/* 1067:1041 */         tessellator.a(v.x, v.y, v.z, c.u, c.v);
/* 1068:     */       }
/* 1069:     */     }
/* 1070:1045 */     if (this.texBinds != null) {
/* 1071:1045 */       RenderLib.unbindTexture();
/* 1072:     */     }
/* 1073:     */   }
/* 1074:     */   
/* 1075:     */   public void renderAlpha(int sides, float alpha)
/* 1076:     */   {
/* 1077:1049 */     baz tessellator = baz.a;
/* 1078:1054 */     for (int i = 0; i < this.cornerList.length; i++) {
/* 1079:1055 */       if ((sides & 1 << i) != 0)
/* 1080:     */       {
/* 1081:1057 */         TexVertex c = this.cornerList[i][0];
/* 1082:1058 */         tessellator.a(c.r, c.g, c.b, alpha);
/* 1083:1059 */         if (!this.useNormal) {
/* 1084:1060 */           tessellator.c(c.brtex);
/* 1085:     */         }
/* 1086:1063 */         for (int j = 0; j < 4; j++)
/* 1087:     */         {
/* 1088:1064 */           c = this.cornerList[i][j];
/* 1089:1065 */           Vector3 v = this.vertexList[c.vtx];
/* 1090:1066 */           tessellator.a(v.x, v.y, v.z, c.u, c.v);
/* 1091:     */         }
/* 1092:     */       }
/* 1093:     */     }
/* 1094:     */   }
/* 1095:     */   
/* 1096:     */   public void renderSmooth(int sides)
/* 1097:     */   {
/* 1098:1073 */     baz tessellator = baz.a;
/* 1099:1078 */     for (int i = 0; i < this.cornerList.length; i++) {
/* 1100:1079 */       if ((sides & 1 << i) != 0)
/* 1101:     */       {
/* 1102:1081 */         if (this.texBinds != null)
/* 1103:     */         {
/* 1104:1082 */           if (this.texBinds[i] != null) {
/* 1105:1083 */             RenderLib.bindTexture(this.texBinds[i]);
/* 1106:     */           } else {
/* 1107:1085 */             RenderLib.unbindTexture();
/* 1108:     */           }
/* 1109:1086 */           tessellator = baz.a;
/* 1110:     */         }
/* 1111:1090 */         for (int j = 0; j < 4; j++)
/* 1112:     */         {
/* 1113:1091 */           TexVertex c = this.cornerList[i][j];
/* 1114:1092 */           tessellator.a(c.r, c.g, c.b);
/* 1115:1093 */           if (!this.useNormal) {
/* 1116:1094 */             tessellator.c(c.brtex);
/* 1117:     */           }
/* 1118:1096 */           Vector3 v = this.vertexList[c.vtx];
/* 1119:1097 */           tessellator.a(v.x, v.y, v.z, c.u, c.v);
/* 1120:     */         }
/* 1121:     */       }
/* 1122:     */     }
/* 1123:1101 */     if (this.texBinds != null) {
/* 1124:1101 */       RenderLib.unbindTexture();
/* 1125:     */     }
/* 1126:     */   }
/* 1127:     */   
/* 1128:     */   public void renderFaces(int faces)
/* 1129:     */   {
/* 1130:1105 */     doMappingBox(faces);
/* 1131:1106 */     doLightLocal(faces);
/* 1132:1107 */     renderFlat(faces);
/* 1133:     */   }
/* 1134:     */   
/* 1135:     */   public void renderGlobFaces(int faces)
/* 1136:     */   {
/* 1137:1111 */     doMappingBox(faces);
/* 1138:1112 */     doLightLocal(faces);
/* 1139:1114 */     if ((!Minecraft.u()) || (this.forceFlat))
/* 1140:     */     {
/* 1141:1115 */       doLightFlat(faces);
/* 1142:1116 */       renderFlat(faces);
/* 1143:     */     }
/* 1144:     */     else
/* 1145:     */     {
/* 1146:1118 */       doLightSmooth(faces);
/* 1147:1119 */       renderSmooth(faces);
/* 1148:     */     }
/* 1149:     */   }
/* 1150:     */   
/* 1151:     */   public void drawPoints(int... points)
/* 1152:     */   {
/* 1153:1124 */     baz tessellator = baz.a;
/* 1154:1126 */     for (int p : points)
/* 1155:     */     {
/* 1156:1127 */       Vector3 v = this.vertexList[p];
/* 1157:1128 */       tessellator.a(v.x, v.y, v.z);
/* 1158:     */     }
/* 1159:     */   }
/* 1160:     */   
/* 1161:     */   public void bindModel(RenderModel model)
/* 1162:     */   {
/* 1163:1136 */     this.vertexList = new Vector3[model.vertexList.length];
/* 1164:1137 */     for (int i = 0; i < this.vertexList.length; i++)
/* 1165:     */     {
/* 1166:1138 */       Vector3 v = new Vector3(model.vertexList[i]);
/* 1167:1139 */       this.basis.rotate(v);
/* 1168:1140 */       v.add(this.globalOrigin);
/* 1169:1141 */       this.vertexList[i] = v;
/* 1170:     */     }
/* 1171:1143 */     this.cornerList = model.texList;
/* 1172:1144 */     this.boundModel = model;
/* 1173:     */   }
/* 1174:     */   
/* 1175:     */   public void bindModelOffset(RenderModel model, double ofx, double ofy, double ofz)
/* 1176:     */   {
/* 1177:1149 */     this.vertexList = new Vector3[model.vertexList.length];
/* 1178:1150 */     for (int i = 0; i < this.vertexList.length; i++)
/* 1179:     */     {
/* 1180:1151 */       Vector3 v = new Vector3(model.vertexList[i]);
/* 1181:1152 */       v.add(this.localOffset.x - ofx, this.localOffset.y - ofy, this.localOffset.z - ofz);
/* 1182:     */       
/* 1183:1154 */       this.basis.rotate(v);
/* 1184:1155 */       v.add(ofx, ofy, ofz);
/* 1185:1156 */       v.add(this.globalOrigin);
/* 1186:1157 */       this.vertexList[i] = v;
/* 1187:     */     }
/* 1188:1159 */     this.cornerList = model.texList;
/* 1189:1160 */     this.boundModel = model;
/* 1190:     */   }
/* 1191:     */   
/* 1192:     */   public void renderModelGroup(int gr, int sgr)
/* 1193:     */   {
/* 1194:1165 */     for (int i = 0; i < this.cornerList.length; i++)
/* 1195:     */     {
/* 1196:1166 */       TexVertex c = this.cornerList[i][0];
/* 1197:1167 */       c.brtex = this.brightLocal[0];
/* 1198:     */     }
/* 1199:1170 */     renderRangeFlat(this.boundModel.groups[gr][sgr][0], this.boundModel.groups[gr][sgr][1]);
/* 1200:     */   }
/* 1201:     */   
/* 1202:     */   public void renderModel(RenderModel model)
/* 1203:     */   {
/* 1204:1175 */     bindModel(model);
/* 1205:1186 */     for (int i = 0; i < this.cornerList.length; i++)
/* 1206:     */     {
/* 1207:1187 */       TexVertex c = this.cornerList[i][0];
/* 1208:1188 */       c.brtex = this.brightLocal[0];
/* 1209:     */     }
/* 1210:1191 */     renderRangeFlat(0, this.cornerList.length);
/* 1211:     */   }
/* 1212:     */   
/* 1213:1197 */   public Matrix3 basis = new Matrix3();
/* 1214:1198 */   public Vector3 localOffset = new Vector3();
/* 1215:1199 */   public Vector3 globalOrigin = new Vector3();
/* 1216:1200 */   public Vector3 boxSize1 = new Vector3();
/* 1217:1200 */   public Vector3 boxSize2 = new Vector3();
/* 1218:1201 */   public RenderModel boundModel = null;
/* 1219:     */   public Vector3[] vertexList;
/* 1220:1204 */   private Vector3[] vertexListBox = new Vector3[8];
/* 1221:     */   public TexVertex[][] cornerList;
/* 1222:1207 */   private TexVertex[][] cornerListBox = new TexVertex[6][4];
/* 1223:1209 */   private String[] texBinds = null;
/* 1224:1210 */   private String[] texBindsBox = new String[6];
/* 1225:     */   private int[] texIndex;
/* 1226:1212 */   private int[] texIndexBox = new int[6];
/* 1227:     */   private int[][] texIndexList;
/* 1228:1214 */   public boolean lockTexture = false;
/* 1229:1215 */   public boolean exactTextureCoordinates = false;
/* 1230:1217 */   private int texFlags = 0;
/* 1231:1218 */   public boolean useNormal = false;
/* 1232:1219 */   public boolean forceFlat = false;
/* 1233:1221 */   private float tintR = 1.0F;
/* 1234:1221 */   private float tintG = 1.0F;
/* 1235:1221 */   private float tintB = 1.0F;
/* 1236:1221 */   private float tintA = 1.0F;
/* 1237:     */   public float[] lightLocal;
/* 1238:1224 */   private float[] lightLocalBox = new float[6];
/* 1239:     */   public int[] brightLocal;
/* 1240:1227 */   private int[] brightLocalBox = new int[6];
/* 1241:1229 */   private int[][][] lightGlobal = new int[3][3][3];
/* 1242:1230 */   private float[][][] aoGlobal = new float[3][3][3];
/* 1243:1231 */   private float[] lightFlat = new float[6];
/* 1244:     */   private int globTrans;
/* 1245:     */   
/* 1246:     */   public RenderContext()
/* 1247:     */   {
/* 1248:1236 */     for (int i = 0; i < 8; i++) {
/* 1249:1236 */       this.vertexListBox[i] = new Vector3();
/* 1250:     */     }
/* 1251:1237 */     int[][] vtxl = { { 7, 6, 5, 4 }, { 0, 1, 2, 3 }, { 0, 4, 5, 1 }, { 2, 6, 7, 3 }, { 1, 5, 6, 2 }, { 3, 7, 4, 0 } };
/* 1252:1244 */     for (i = 0; i < 6; i++) {
/* 1253:1244 */       for (int j = 0; j < 4; j++)
/* 1254:     */       {
/* 1255:1245 */         this.cornerListBox[i][j] = new TexVertex();
/* 1256:1246 */         this.cornerListBox[i][j].vtx = vtxl[i][j];
/* 1257:     */       }
/* 1258:     */     }
/* 1259:1249 */     setDefaults();
/* 1260:     */   }
/* 1261:     */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.RenderContext
 * JD-Core Version:    0.7.0.1
 */