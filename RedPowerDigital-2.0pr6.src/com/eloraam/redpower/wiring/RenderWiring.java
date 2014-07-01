/*   1:    */ package com.eloraam.redpower.wiring;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   5:    */ import com.eloraam.redpower.core.RenderContext;
/*   6:    */ import com.eloraam.redpower.core.RenderCovers;
/*   7:    */ 
/*   8:    */ public abstract class RenderWiring
/*   9:    */   extends RenderCovers
/*  10:    */ {
/*  11:    */   private float wireWidth;
/*  12:    */   private float wireHeight;
/*  13:    */   
/*  14:    */   public RenderWiring(amq bl)
/*  15:    */   {
/*  16:  9 */     super(bl);
/*  17:    */   }
/*  18:    */   
/*  19:    */   public void setWireSize(float w, float h)
/*  20:    */   {
/*  21: 16 */     this.wireWidth = (w * 0.5F);
/*  22: 17 */     this.wireHeight = h;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void renderSideWire(int n)
/*  26:    */   {
/*  27: 21 */     this.context.setLocalLights(0.5F, 1.0F, 0.7F, 0.7F, 0.7F, 0.7F);
/*  28: 22 */     switch (n)
/*  29:    */     {
/*  30:    */     case 2: 
/*  31: 24 */       this.context.setSize(0.0D, 0.0D, 0.5F - this.wireWidth, 0.5F - this.wireWidth, this.wireHeight, 0.5F + this.wireWidth);
/*  32:    */       
/*  33: 26 */       this.context.calcBounds();
/*  34: 27 */       this.context.renderFaces(54);
/*  35: 28 */       break;
/*  36:    */     case 3: 
/*  37: 30 */       this.context.setSize(0.5F + this.wireWidth, 0.0D, 0.5F - this.wireWidth, 1.0D, this.wireHeight, 0.5F + this.wireWidth);
/*  38:    */       
/*  39: 32 */       this.context.calcBounds();
/*  40: 33 */       this.context.renderFaces(58);
/*  41: 34 */       break;
/*  42:    */     case 4: 
/*  43: 36 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, 0.0D, 0.5F + this.wireWidth, this.wireHeight, 0.5F - this.wireWidth);
/*  44:    */       
/*  45: 38 */       this.context.calcBounds();
/*  46: 39 */       this.context.renderFaces(30);
/*  47: 40 */       break;
/*  48:    */     case 5: 
/*  49: 42 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, 0.5F + this.wireWidth, 0.5F + this.wireWidth, this.wireHeight, 1.0D);
/*  50:    */       
/*  51: 44 */       this.context.calcBounds();
/*  52: 45 */       this.context.renderFaces(46);
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56: 50 */   private int[][] sidetex = new int[7][6];
/*  57:    */   
/*  58:    */   public void setSideTex(int top, int cent, int cfix)
/*  59:    */   {
/*  60: 53 */     for (int j = 0; j < 6; j++) {
/*  61: 54 */       this.sidetex[0][j] = (j >> 1 == 0 ? cent : cfix);
/*  62:    */     }
/*  63: 56 */     for (int i = 1; i < 3; i++) {
/*  64: 56 */       for (j = 0; j < 6; j++) {
/*  65: 57 */         this.sidetex[i][j] = (j >> 1 == i ? cent : top);
/*  66:    */       }
/*  67:    */     }
/*  68: 59 */     for (i = 1; i < 3; i++) {
/*  69: 59 */       for (j = 0; j < 6; j++) {
/*  70: 60 */         this.sidetex[(i + 2)][j] = (j >> 1 == i ? cent : cfix);
/*  71:    */       }
/*  72:    */     }
/*  73: 62 */     for (i = 0; i < 6; i++)
/*  74:    */     {
/*  75: 63 */       this.sidetex[5][i] = top;
/*  76: 64 */       this.sidetex[6][i] = top;
/*  77:    */     }
/*  78: 66 */     this.sidetex[5][4] = cent;this.sidetex[5][5] = cent;
/*  79: 67 */     this.sidetex[6][2] = cent;this.sidetex[6][3] = cent;
/*  80: 68 */     this.sidetex[5][0] = cent;this.sidetex[6][0] = cent;
/*  81: 69 */     this.context.setTex(this.sidetex);
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setSideTexJumbo(int sides, int top, int cent, int centside, int end, int corners)
/*  85:    */   {
/*  86: 75 */     for (int j = 0; j < 6; j++) {
/*  87: 76 */       this.sidetex[0][j] = (j >> 1 == 0 ? cent : centside);
/*  88:    */     }
/*  89: 80 */     for (int i = 1; i < 3; i++) {
/*  90: 80 */       for (j = 0; j < 6; j++) {
/*  91: 81 */         this.sidetex[i][j] = (j >> 1 == i ? end : j >> 1 == 0 ? top : sides);
/*  92:    */       }
/*  93:    */     }
/*  94: 85 */     for (i = 1; i < 3; i++) {
/*  95: 85 */       for (j = 0; j < 6; j++) {
/*  96: 86 */         this.sidetex[(i + 2)][j] = (j >> 1 == i ? end : j >> 1 == 0 ? top : centside);
/*  97:    */       }
/*  98:    */     }
/*  99: 90 */     for (i = 0; i < 6; i++)
/* 100:    */     {
/* 101: 91 */       this.sidetex[5][i] = top;
/* 102: 92 */       this.sidetex[6][i] = top;
/* 103:    */     }
/* 104: 94 */     this.sidetex[5][4] = corners;this.sidetex[5][5] = corners;
/* 105: 95 */     this.sidetex[6][2] = corners;this.sidetex[6][3] = corners;
/* 106: 96 */     this.sidetex[5][0] = corners;this.sidetex[6][0] = corners;
/* 107: 97 */     this.context.setTex(this.sidetex);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void renderSideWires(int cs, int ucs, int fn)
/* 111:    */   {
/* 112:101 */     int fxl = 0;int fzl = 0;int fc = 62;int fxs1 = 0;int fxs2 = 0;int fzs1 = 0;int fzs2 = 0;
/* 113:    */     
/* 114:103 */     int stb = 3;
/* 115:    */     
/* 116:105 */     float x1 = (ucs & 0x4) == 0 ? 0.0F : this.wireHeight;
/* 117:106 */     float x2 = (ucs & 0x8) == 0 ? 1.0F : 1.0F - this.wireHeight;
/* 118:107 */     float z1 = (ucs & 0x1) == 0 ? 0.0F : this.wireHeight;
/* 119:108 */     float z2 = (ucs & 0x2) == 0 ? 1.0F : 1.0F - this.wireHeight;
/* 120:    */     
/* 121:110 */     this.context.setLocalLights(0.5F, 1.0F, 0.7F, 0.7F, 0.7F, 0.7F);
/* 122:111 */     cs |= ucs;
/* 123:112 */     if ((cs & 0xC) == 0)
/* 124:    */     {
/* 125:113 */       fzl |= 0x3E;fc = 0;
/* 126:114 */       if ((cs & 0x1) == 0) {
/* 127:114 */         z1 = 0.26F;
/* 128:    */       }
/* 129:115 */       if ((cs & 0x2) == 0) {
/* 130:115 */         z2 = 0.74F;
/* 131:    */       }
/* 132:116 */       stb = 1;
/* 133:    */     }
/* 134:117 */     else if ((cs & 0x3) == 0)
/* 135:    */     {
/* 136:118 */       fxl |= 0x3E;fc = 0;
/* 137:119 */       if ((cs & 0x4) == 0) {
/* 138:119 */         x1 = 0.26F;
/* 139:    */       }
/* 140:120 */       if ((cs & 0x8) == 0) {
/* 141:120 */         x2 = 0.74F;
/* 142:    */       }
/* 143:121 */       stb = 1;
/* 144:    */     }
/* 145:    */     else
/* 146:    */     {
/* 147:123 */       if ((cs & 0x7) == 3)
/* 148:    */       {
/* 149:124 */         fzl |= 0x1C;fc &= 0xFFFFFFEF;
/* 150:    */       }
/* 151:    */       else
/* 152:    */       {
/* 153:126 */         if ((cs & 0x1) > 0) {
/* 154:126 */           fzs1 |= 0x14;
/* 155:    */         }
/* 156:127 */         if ((cs & 0x2) > 0) {
/* 157:127 */           fzs2 |= 0x18;
/* 158:    */         }
/* 159:    */       }
/* 160:129 */       if ((cs & 0xB) == 3)
/* 161:    */       {
/* 162:130 */         fzl |= 0x2C;fc &= 0xFFFFFFDF;
/* 163:    */       }
/* 164:    */       else
/* 165:    */       {
/* 166:132 */         if ((cs & 0x1) > 0) {
/* 167:132 */           fzs1 |= 0x24;
/* 168:    */         }
/* 169:133 */         if ((cs & 0x2) > 0) {
/* 170:133 */           fzs2 |= 0x28;
/* 171:    */         }
/* 172:    */       }
/* 173:135 */       if ((cs & 0xD) == 12)
/* 174:    */       {
/* 175:136 */         fxl |= 0x34;fc &= 0xFFFFFFFB;
/* 176:    */       }
/* 177:    */       else
/* 178:    */       {
/* 179:138 */         if ((cs & 0x4) > 0) {
/* 180:138 */           fxs1 |= 0x14;
/* 181:    */         }
/* 182:139 */         if ((cs & 0x8) > 0) {
/* 183:139 */           fxs2 |= 0x24;
/* 184:    */         }
/* 185:    */       }
/* 186:141 */       if ((cs & 0xE) == 12)
/* 187:    */       {
/* 188:142 */         fxl |= 0x38;fc &= 0xFFFFFFF7;
/* 189:    */       }
/* 190:    */       else
/* 191:    */       {
/* 192:144 */         if ((cs & 0x4) > 0) {
/* 193:144 */           fxs1 |= 0x18;
/* 194:    */         }
/* 195:145 */         if ((cs & 0x8) > 0) {
/* 196:145 */           fxs2 |= 0x28;
/* 197:    */         }
/* 198:    */       }
/* 199:147 */       if ((cs & 0x1) > 0)
/* 200:    */       {
/* 201:147 */         fzs1 |= 0x2;fc &= 0xFFFFFFFB;
/* 202:    */       }
/* 203:148 */       if ((cs & 0x2) > 0)
/* 204:    */       {
/* 205:148 */         fzs2 |= 0x2;fc &= 0xFFFFFFF7;
/* 206:    */       }
/* 207:149 */       if ((cs & 0x4) > 0)
/* 208:    */       {
/* 209:149 */         fxs1 |= 0x2;fc &= 0xFFFFFFEF;
/* 210:    */       }
/* 211:150 */       if ((cs & 0x8) > 0)
/* 212:    */       {
/* 213:150 */         fxs2 |= 0x2;fc &= 0xFFFFFFDF;
/* 214:    */       }
/* 215:151 */       if ((cs & 0x40) > 0)
/* 216:    */       {
/* 217:152 */         fxs1 |= 0x1;fxs2 |= 0x1;
/* 218:153 */         fzs1 |= 0x1;fzs2 |= 0x1;
/* 219:154 */         fc |= 0x1;
/* 220:    */       }
/* 221:    */     }
/* 222:157 */     int tmpf = (ucs & 0xC) << 2 ^ 0xFFFFFFFF;
/* 223:158 */     fxl &= tmpf;fxs1 &= tmpf;fxs2 &= tmpf;
/* 224:159 */     tmpf = (ucs & 0x3) << 2 ^ 0xFFFFFFFF;
/* 225:160 */     fzl &= tmpf;fzs1 &= tmpf;fzs2 &= tmpf;
/* 226:    */     
/* 227:162 */     int fxf = 35712;
/* 228:163 */     int fzf = 217640;
/* 229:164 */     int fcf = 220032;
/* 230:166 */     switch (fn)
/* 231:    */     {
/* 232:    */     case 1: 
/* 233:    */     case 2: 
/* 234:    */     case 4: 
/* 235:168 */       fxf = 7512;
/* 236:169 */       fcf = 220488;
/* 237:    */     }
/* 238:173 */     if (fxl > 0)
/* 239:    */     {
/* 240:174 */       this.context.setSize(x1, 0.0D, 0.5F - this.wireWidth, x2, this.wireHeight, 0.5F + this.wireWidth);
/* 241:    */       
/* 242:176 */       this.context.calcBounds();
/* 243:177 */       this.context.setTexFlags(fxf);
/* 244:178 */       this.context.setTexIndex(stb + 1);
/* 245:179 */       this.context.renderFaces(fxl);
/* 246:    */     }
/* 247:181 */     if (fzl > 0)
/* 248:    */     {
/* 249:182 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, z1, 0.5F + this.wireWidth, this.wireHeight, z2);
/* 250:    */       
/* 251:184 */       this.context.calcBounds();
/* 252:185 */       this.context.setTexFlags(fzf);
/* 253:186 */       this.context.setTexIndex(stb);
/* 254:187 */       this.context.renderFaces(fzl);
/* 255:    */     }
/* 256:189 */     if (fc > 0)
/* 257:    */     {
/* 258:190 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, 0.5F - this.wireWidth, 0.5F + this.wireWidth, this.wireHeight, 0.5F + this.wireWidth);
/* 259:    */       
/* 260:192 */       this.context.calcBounds();
/* 261:193 */       this.context.setTexFlags(fcf);
/* 262:194 */       this.context.setTexIndex(0);
/* 263:195 */       this.context.renderFaces(fc);
/* 264:    */     }
/* 265:197 */     if (fxs1 > 0)
/* 266:    */     {
/* 267:198 */       this.context.setSize(x1, 0.0D, 0.5F - this.wireWidth, 0.5F - this.wireWidth, this.wireHeight, 0.5F + this.wireWidth);
/* 268:    */       
/* 269:200 */       this.context.calcBounds();
/* 270:201 */       this.context.setTexFlags(fxf);
/* 271:202 */       this.context.setTexIndex(stb + 1);
/* 272:203 */       this.context.renderFaces(fxs1);
/* 273:    */     }
/* 274:205 */     if (fxs2 > 0)
/* 275:    */     {
/* 276:206 */       this.context.setSize(0.5F + this.wireWidth, 0.0D, 0.5F - this.wireWidth, x2, this.wireHeight, 0.5F + this.wireWidth);
/* 277:    */       
/* 278:208 */       this.context.calcBounds();
/* 279:209 */       this.context.setTexFlags(fxf);
/* 280:210 */       this.context.setTexIndex(stb + 1);
/* 281:211 */       this.context.renderFaces(fxs2);
/* 282:    */     }
/* 283:213 */     if (fzs1 > 0)
/* 284:    */     {
/* 285:214 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, z1, 0.5F + this.wireWidth, this.wireHeight, 0.5F - this.wireWidth);
/* 286:    */       
/* 287:216 */       this.context.calcBounds();
/* 288:217 */       this.context.setTexFlags(fzf);
/* 289:218 */       this.context.setTexIndex(stb);
/* 290:219 */       this.context.renderFaces(fzs1);
/* 291:    */     }
/* 292:221 */     if (fzs2 > 0)
/* 293:    */     {
/* 294:222 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, 0.5F + this.wireWidth, 0.5F + this.wireWidth, this.wireHeight, z2);
/* 295:    */       
/* 296:224 */       this.context.calcBounds();
/* 297:225 */       this.context.setTexFlags(fzf);
/* 298:226 */       this.context.setTexIndex(stb);
/* 299:227 */       this.context.renderFaces(fzs2);
/* 300:    */     }
/* 301:229 */     if (fn < 2)
/* 302:    */     {
/* 303:230 */       this.context.setTexFlags(0);
/* 304:231 */       return;
/* 305:    */     }
/* 306:233 */     if ((ucs & 0x2) > 0)
/* 307:    */     {
/* 308:234 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, 1.0F - this.wireHeight, 0.5F + this.wireWidth, this.wireHeight, 1.0D);
/* 309:    */       
/* 310:236 */       this.context.calcBounds();
/* 311:237 */       this.context.setTexFlags(73728);
/* 312:238 */       this.context.setTexIndex(5);
/* 313:239 */       this.context.renderFaces(48);
/* 314:    */     }
/* 315:241 */     if ((ucs & 0x4) > 0)
/* 316:    */     {
/* 317:242 */       this.context.setSize(0.0D, 0.0D, 0.5F - this.wireWidth, this.wireHeight, this.wireHeight, 0.5F + this.wireWidth);
/* 318:    */       
/* 319:244 */       this.context.calcBounds();
/* 320:245 */       if ((fn == 2) || (fn == 4)) {
/* 321:246 */         this.context.setTexFlags(1152);
/* 322:    */       } else {
/* 323:248 */         this.context.setTexFlags(1728);
/* 324:    */       }
/* 325:249 */       this.context.setTexIndex(6);
/* 326:250 */       this.context.renderFaces(12);
/* 327:    */     }
/* 328:252 */     if ((ucs & 0x8) > 0)
/* 329:    */     {
/* 330:253 */       this.context.setSize(1.0F - this.wireHeight, 0.0D, 0.5F - this.wireWidth, 1.0D, this.wireHeight, 0.5F + this.wireWidth);
/* 331:    */       
/* 332:255 */       this.context.calcBounds();
/* 333:256 */       if ((fn == 2) || (fn == 4)) {
/* 334:257 */         this.context.setTexFlags(1728);
/* 335:    */       } else {
/* 336:259 */         this.context.setTexFlags(1152);
/* 337:    */       }
/* 338:260 */       this.context.setTexIndex(6);
/* 339:261 */       this.context.renderFaces(12);
/* 340:    */     }
/* 341:263 */     this.context.setTexFlags(0);
/* 342:    */   }
/* 343:    */   
/* 344:    */   public void renderEndCaps(int cs, int fn)
/* 345:    */   {
/* 346:267 */     if (cs == 0) {
/* 347:267 */       return;
/* 348:    */     }
/* 349:268 */     this.context.setTexIndex(5);
/* 350:269 */     if ((cs & 0x1) > 0)
/* 351:    */     {
/* 352:270 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, 1.0F - this.wireHeight, 0.5F + this.wireWidth, this.wireHeight, 1.0D);
/* 353:    */       
/* 354:272 */       this.context.setRelPos(0.0D, 0.0D, -1.0D);
/* 355:    */       
/* 356:274 */       this.context.setTexFlags(38444);
/* 357:275 */       this.context.setLocalLights(0.7F, 1.0F, 0.7F, 1.0F, 0.7F, 0.7F);
/* 358:276 */       this.context.calcBounds();
/* 359:277 */       this.context.renderFaces(55);
/* 360:    */     }
/* 361:279 */     if ((cs & 0x2) > 0)
/* 362:    */     {
/* 363:280 */       this.context.setSize(0.5F - this.wireWidth, 0.0D, 0.0D, 0.5F + this.wireWidth, this.wireHeight, this.wireHeight);
/* 364:    */       
/* 365:282 */       this.context.setRelPos(0.0D, 0.0D, 1.0D);
/* 366:283 */       this.context.setTexFlags(38444);
/* 367:284 */       this.context.setLocalLights(0.7F, 1.0F, 0.7F, 1.0F, 0.7F, 0.7F);
/* 368:285 */       this.context.calcBounds();
/* 369:286 */       this.context.renderFaces(59);
/* 370:    */     }
/* 371:288 */     this.context.setTexIndex(6);
/* 372:289 */     if ((cs & 0x4) > 0)
/* 373:    */     {
/* 374:290 */       this.context.setSize(1.0F - this.wireHeight, 0.0D, 0.5F - this.wireWidth, 1.0D, this.wireHeight, 0.5F + this.wireWidth);
/* 375:    */       
/* 376:292 */       this.context.setRelPos(-1.0D, 0.0D, 0.0D);
/* 377:293 */       if ((fn == 2) || (fn == 4)) {
/* 378:293 */         this.context.setTexFlags(45658);
/* 379:    */       } else {
/* 380:294 */         this.context.setTexFlags(3);
/* 381:    */       }
/* 382:296 */       this.context.setLocalLights(0.7F, 1.0F, 0.7F, 0.7F, 1.0F, 0.7F);
/* 383:297 */       this.context.calcBounds();
/* 384:298 */       this.context.renderFaces(31);
/* 385:    */     }
/* 386:300 */     if ((cs & 0x8) > 0)
/* 387:    */     {
/* 388:301 */       this.context.setSize(0.0D, 0.0D, 0.5F - this.wireWidth, this.wireHeight, this.wireHeight, 0.5F + this.wireWidth);
/* 389:    */       
/* 390:303 */       this.context.setRelPos(1.0D, 0.0D, 0.0D);
/* 391:304 */       if ((fn == 2) || (fn == 4)) {
/* 392:304 */         this.context.setTexFlags(24);
/* 393:    */       } else {
/* 394:305 */         this.context.setTexFlags(102977);
/* 395:    */       }
/* 396:307 */       this.context.setLocalLights(0.7F, 1.0F, 0.7F, 0.7F, 0.7F, 1.0F);
/* 397:308 */       this.context.calcBounds();
/* 398:309 */       this.context.renderFaces(47);
/* 399:    */     }
/* 400:311 */     this.context.setRelPos(0.0D, 0.0D, 0.0D);
/* 401:    */   }
/* 402:    */   
/* 403:    */   public void renderWireBlock(int consides, int cons, int indcon, int indconex)
/* 404:    */   {
/* 405:316 */     int ucons = 0;
/* 406:    */     
/* 407:318 */     indcon &= (indconex ^ 0xFFFFFFFF);
/* 408:319 */     if ((consides & 0x1) > 0) {
/* 409:319 */       ucons |= 0x111100;
/* 410:    */     }
/* 411:320 */     if ((consides & 0x2) > 0) {
/* 412:320 */       ucons |= 0x222200;
/* 413:    */     }
/* 414:321 */     if ((consides & 0x4) > 0) {
/* 415:321 */       ucons |= 0x440011;
/* 416:    */     }
/* 417:322 */     if ((consides & 0x8) > 0) {
/* 418:322 */       ucons |= 0x880022;
/* 419:    */     }
/* 420:323 */     if ((consides & 0x10) > 0) {
/* 421:323 */       ucons |= 0x4444;
/* 422:    */     }
/* 423:324 */     if ((consides & 0x20) > 0) {
/* 424:324 */       ucons |= 0x8888;
/* 425:    */     }
/* 426:326 */     if ((consides & 0x1) > 0)
/* 427:    */     {
/* 428:327 */       this.context.setOrientation(0, 0);
/* 429:328 */       renderSideWires(RedPowerLib.mapConToLocal(cons, 0), RedPowerLib.mapConToLocal(ucons, 0), 0);
/* 430:    */       
/* 431:    */ 
/* 432:331 */       renderEndCaps(RedPowerLib.mapConToLocal(indconex, 0), 0);
/* 433:    */     }
/* 434:333 */     if ((consides & 0x2) > 0)
/* 435:    */     {
/* 436:334 */       this.context.setOrientation(1, 0);
/* 437:335 */       renderSideWires(RedPowerLib.mapConToLocal(cons, 1), RedPowerLib.mapConToLocal(ucons, 1), 1);
/* 438:    */       
/* 439:    */ 
/* 440:338 */       renderEndCaps(RedPowerLib.mapConToLocal(indconex, 1), 1);
/* 441:    */     }
/* 442:340 */     if ((consides & 0x4) > 0)
/* 443:    */     {
/* 444:341 */       this.context.setOrientation(2, 0);
/* 445:342 */       renderSideWires(RedPowerLib.mapConToLocal(cons, 2), RedPowerLib.mapConToLocal(ucons, 2), 2);
/* 446:    */       
/* 447:    */ 
/* 448:345 */       renderEndCaps(RedPowerLib.mapConToLocal(indcon, 2) & 0xE, 2);
/* 449:346 */       renderEndCaps(RedPowerLib.mapConToLocal(indconex, 2), 2);
/* 450:    */     }
/* 451:348 */     if ((consides & 0x8) > 0)
/* 452:    */     {
/* 453:349 */       this.context.setOrientation(3, 0);
/* 454:350 */       renderSideWires(RedPowerLib.mapConToLocal(cons, 3), RedPowerLib.mapConToLocal(ucons, 3), 3);
/* 455:    */       
/* 456:    */ 
/* 457:353 */       renderEndCaps(RedPowerLib.mapConToLocal(indcon, 3) & 0xE, 3);
/* 458:354 */       renderEndCaps(RedPowerLib.mapConToLocal(indconex, 3), 3);
/* 459:    */     }
/* 460:356 */     if ((consides & 0x10) > 0)
/* 461:    */     {
/* 462:357 */       this.context.setOrientation(4, 0);
/* 463:358 */       renderSideWires(RedPowerLib.mapConToLocal(cons, 4), RedPowerLib.mapConToLocal(ucons, 4), 4);
/* 464:    */       
/* 465:    */ 
/* 466:361 */       renderEndCaps(RedPowerLib.mapConToLocal(indcon, 4) & 0xE, 4);
/* 467:362 */       renderEndCaps(RedPowerLib.mapConToLocal(indconex, 4), 4);
/* 468:    */     }
/* 469:364 */     if ((consides & 0x20) > 0)
/* 470:    */     {
/* 471:365 */       this.context.setOrientation(5, 0);
/* 472:366 */       renderSideWires(RedPowerLib.mapConToLocal(cons, 5), RedPowerLib.mapConToLocal(ucons, 5), 5);
/* 473:    */       
/* 474:    */ 
/* 475:369 */       renderEndCaps(RedPowerLib.mapConToLocal(indcon, 5) & 0xE, 5);
/* 476:370 */       renderEndCaps(RedPowerLib.mapConToLocal(indconex, 5), 5);
/* 477:    */     }
/* 478:    */   }
/* 479:    */   
/* 480:    */   void setJacketTexFiles(int cons, String itf, int[] tex, int st)
/* 481:    */   {
/* 482:375 */     String tf = "/eloraam/wiring/redpower1.png";
/* 483:376 */     this.context.setTexFiles((cons & 0x1) > 0 ? tf : itf, (cons & 0x2) > 0 ? tf : itf, (cons & 0x4) > 0 ? tf : itf, (cons & 0x8) > 0 ? tf : itf, (cons & 0x10) > 0 ? tf : itf, (cons & 0x20) > 0 ? tf : itf);
/* 484:    */     
/* 485:    */ 
/* 486:    */ 
/* 487:    */ 
/* 488:    */ 
/* 489:    */ 
/* 490:383 */     this.context.setTex((cons & 0x1) > 0 ? st : tex[0], (cons & 0x2) > 0 ? st : tex[1], (cons & 0x4) > 0 ? st : tex[2], (cons & 0x8) > 0 ? st : tex[3], (cons & 0x10) > 0 ? st : tex[4], (cons & 0x20) > 0 ? st : tex[5]);
/* 491:    */   }
/* 492:    */   
/* 493:    */   public void renderCenterBlock(int cons, String tf, int[] tex, int sidtex)
/* 494:    */   {
/* 495:394 */     if (cons == 0)
/* 496:    */     {
/* 497:395 */       setJacketTexFiles(3, tf, tex, sidtex);
/* 498:396 */       this.context.renderBox(63, 0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
/* 499:397 */       this.context.clearTexFiles();
/* 500:398 */       return;
/* 501:    */     }
/* 502:399 */     if (cons == 3)
/* 503:    */     {
/* 504:400 */       setJacketTexFiles(3, tf, tex, sidtex);
/* 505:401 */       this.context.renderBox(63, 0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 506:402 */       this.context.clearTexFiles();
/* 507:403 */       return;
/* 508:    */     }
/* 509:404 */     if (cons == 12)
/* 510:    */     {
/* 511:405 */       setJacketTexFiles(12, tf, tex, sidtex);
/* 512:406 */       this.context.renderBox(63, 0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
/* 513:407 */       this.context.clearTexFiles();
/* 514:408 */       return;
/* 515:    */     }
/* 516:409 */     if (cons == 48)
/* 517:    */     {
/* 518:410 */       setJacketTexFiles(48, tf, tex, sidtex);
/* 519:411 */       this.context.renderBox(63, 0.0D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
/* 520:412 */       this.context.clearTexFiles();
/* 521:413 */       return;
/* 522:    */     }
/* 523:415 */     if (Integer.bitCount(cons) > 1)
/* 524:    */     {
/* 525:416 */       this.context.setTexFile(tf);
/* 526:417 */       this.context.setTex(tex);
/* 527:    */     }
/* 528:    */     else
/* 529:    */     {
/* 530:419 */       int rc = cons;
/* 531:420 */       if (rc == 0) {
/* 532:420 */         rc = 3;
/* 533:    */       }
/* 534:421 */       rc = (rc & 0x15) << 1 | (rc & 0x2A) >> 1;
/* 535:422 */       setJacketTexFiles(rc, tf, tex, sidtex);
/* 536:    */     }
/* 537:424 */     this.context.renderBox(0x3F ^ cons, 0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
/* 538:425 */     if ((cons & 0x1) > 0)
/* 539:    */     {
/* 540:426 */       setJacketTexFiles(1, tf, tex, sidtex);
/* 541:427 */       this.context.renderBox(61, 0.25D, 0.0D, 0.25D, 0.75D, 0.25D, 0.75D);
/* 542:    */     }
/* 543:429 */     if ((cons & 0x2) > 0)
/* 544:    */     {
/* 545:430 */       setJacketTexFiles(2, tf, tex, sidtex);
/* 546:431 */       this.context.renderBox(62, 0.25D, 0.75D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 547:    */     }
/* 548:433 */     if ((cons & 0x4) > 0)
/* 549:    */     {
/* 550:434 */       setJacketTexFiles(4, tf, tex, sidtex);
/* 551:435 */       this.context.renderBox(55, 0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.25D);
/* 552:    */     }
/* 553:437 */     if ((cons & 0x8) > 0)
/* 554:    */     {
/* 555:438 */       setJacketTexFiles(8, tf, tex, sidtex);
/* 556:439 */       this.context.renderBox(59, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D, 1.0D);
/* 557:    */     }
/* 558:441 */     if ((cons & 0x10) > 0)
/* 559:    */     {
/* 560:442 */       setJacketTexFiles(16, tf, tex, sidtex);
/* 561:443 */       this.context.renderBox(31, 0.0D, 0.25D, 0.25D, 0.25D, 0.75D, 0.75D);
/* 562:    */     }
/* 563:445 */     if ((cons & 0x20) > 0)
/* 564:    */     {
/* 565:446 */       setJacketTexFiles(32, tf, tex, sidtex);
/* 566:447 */       this.context.renderBox(47, 0.75D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
/* 567:    */     }
/* 568:449 */     this.context.clearTexFiles();
/* 569:    */   }
/* 570:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.RenderWiring
 * JD-Core Version:    0.7.0.1
 */