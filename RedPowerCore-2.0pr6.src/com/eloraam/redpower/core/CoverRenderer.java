/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ public class CoverRenderer
/*   4:    */ {
/*   5:    */   float cx1;
/*   6:    */   float cx2;
/*   7:    */   float cy1;
/*   8:    */   float cy2;
/*   9:    */   float cz1;
/*  10:    */   float cz2;
/*  11:    */   
/*  12:    */   public CoverRenderer(RenderContext ctx)
/*  13:    */   {
/*  14:  8 */     this.context = ctx;
/*  15:    */   }
/*  16:    */   
/*  17:    */   public void start()
/*  18:    */   {
/*  19: 12 */     this.cx1 = 0.0F;this.cx2 = 1.0F;this.cy1 = 0.0F;this.cy2 = 1.0F;this.cz1 = 0.0F;this.cz2 = 1.0F;
/*  20:    */   }
/*  21:    */   
/*  22:    */   public void startShrink(float sh)
/*  23:    */   {
/*  24: 16 */     this.cx1 = sh;this.cx2 = (1.0F - sh);
/*  25: 17 */     this.cy1 = sh;this.cy2 = (1.0F - sh);
/*  26: 18 */     this.cz1 = sh;this.cz2 = (1.0F - sh);
/*  27:    */   }
/*  28:    */   
/*  29:    */   void sizeHollow(int part, int s)
/*  30:    */   {
/*  31: 22 */     switch (part)
/*  32:    */     {
/*  33:    */     case 0: 
/*  34:    */     case 1: 
/*  35: 24 */       if (s == 0) {
/*  36: 24 */         this.context.boxSize2.x = 0.25D;
/*  37:    */       }
/*  38: 25 */       if (s == 1) {
/*  39: 25 */         this.context.boxSize1.x = 0.75D;
/*  40:    */       }
/*  41: 26 */       if (s > 1)
/*  42:    */       {
/*  43: 27 */         this.context.boxSize1.x = 0.25D;
/*  44: 28 */         this.context.boxSize2.x = 0.75D;
/*  45:    */       }
/*  46: 30 */       if (s == 2) {
/*  47: 30 */         this.context.boxSize2.z = 0.25D;
/*  48:    */       }
/*  49: 31 */       if (s == 3) {
/*  50: 31 */         this.context.boxSize1.z = 0.75D;
/*  51:    */       }
/*  52:    */       break;
/*  53:    */     case 2: 
/*  54:    */     case 3: 
/*  55: 34 */       if (s == 0) {
/*  56: 34 */         this.context.boxSize2.x = 0.25D;
/*  57:    */       }
/*  58: 35 */       if (s == 1) {
/*  59: 35 */         this.context.boxSize1.x = 0.75D;
/*  60:    */       }
/*  61: 36 */       if (s > 1)
/*  62:    */       {
/*  63: 37 */         this.context.boxSize1.x = 0.25D;
/*  64: 38 */         this.context.boxSize2.x = 0.75D;
/*  65:    */       }
/*  66: 40 */       if (s == 2) {
/*  67: 40 */         this.context.boxSize2.y = 0.25D;
/*  68:    */       }
/*  69: 41 */       if (s == 3) {
/*  70: 41 */         this.context.boxSize1.y = 0.75D;
/*  71:    */       }
/*  72:    */       break;
/*  73:    */     default: 
/*  74: 44 */       if (s == 0) {
/*  75: 44 */         this.context.boxSize2.z = 0.25D;
/*  76:    */       }
/*  77: 45 */       if (s == 1) {
/*  78: 45 */         this.context.boxSize1.z = 0.75D;
/*  79:    */       }
/*  80: 46 */       if (s > 1)
/*  81:    */       {
/*  82: 47 */         this.context.boxSize1.z = 0.25D;
/*  83: 48 */         this.context.boxSize2.z = 0.75D;
/*  84:    */       }
/*  85: 50 */       if (s == 2) {
/*  86: 50 */         this.context.boxSize2.y = 0.25D;
/*  87:    */       }
/*  88: 51 */       if (s == 3) {
/*  89: 51 */         this.context.boxSize1.y = 0.75D;
/*  90:    */       }
/*  91:    */       break;
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   int innerFace(int part, int s)
/*  96:    */   {
/*  97:    */     int m;
/*  98: 58 */     switch (part)
/*  99:    */     {
/* 100:    */     case 0: 
/* 101:    */     case 1: 
/* 102: 59 */       m = 67637280; break;
/* 103:    */     case 2: 
/* 104:    */     case 3: 
/* 105: 60 */       m = 16912416; break;
/* 106:    */     default: 
/* 107: 61 */       m = 16909320;
/* 108:    */     }
/* 109: 63 */     return m >> s * 8;
/* 110:    */   }
/* 111:    */   
/* 112:    */   boolean sizeColumnSpoke(int part, boolean n1, float f)
/* 113:    */   {
/* 114: 67 */     part = part - 26 + (n1 ? 3 : 0);
/* 115: 68 */     switch (part)
/* 116:    */     {
/* 117:    */     case 0: 
/* 118: 70 */       this.context.boxSize2.y = (0.5D - f);
/* 119: 71 */       return 0.5D - f > this.cy1;
/* 120:    */     case 1: 
/* 121: 73 */       this.context.boxSize2.z = (0.5D - f);
/* 122: 74 */       return 0.5D - f > this.cz1;
/* 123:    */     case 2: 
/* 124: 76 */       this.context.boxSize2.x = (0.5D - f);
/* 125: 77 */       return 0.5D - f > this.cx1;
/* 126:    */     case 3: 
/* 127: 79 */       this.context.boxSize2.y = this.cy2;
/* 128: 80 */       this.context.boxSize1.y = (0.5D + f);
/* 129: 81 */       return 0.5D + f < this.cy2;
/* 130:    */     case 4: 
/* 131: 83 */       this.context.boxSize2.z = this.cz2;
/* 132: 84 */       this.context.boxSize1.z = (0.5D + f);
/* 133: 85 */       return 0.5D + f < this.cz2;
/* 134:    */     case 5: 
/* 135: 87 */       this.context.boxSize2.x = this.cx2;
/* 136: 88 */       this.context.boxSize1.x = (0.5D + f);
/* 137: 89 */       return 0.5D + f < this.cx2;
/* 138:    */     }
/* 139: 91 */     return false;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void setSize(int part, float th)
/* 143:    */   {
/* 144: 95 */     switch (part)
/* 145:    */     {
/* 146:    */     case 0: 
/* 147: 98 */       this.context.setSize(this.cx1, 0.0D, this.cz1, this.cx2, th, this.cz2);
/* 148: 99 */       this.cy1 = th;
/* 149:100 */       break;
/* 150:    */     case 1: 
/* 151:102 */       this.context.setSize(this.cx1, 1.0F - th, this.cz1, this.cx2, 1.0D, this.cz2);
/* 152:103 */       this.cy2 = (1.0F - th);
/* 153:104 */       break;
/* 154:    */     case 2: 
/* 155:106 */       this.context.setSize(this.cx1, this.cy1, 0.0D, this.cx2, this.cy2, th);
/* 156:107 */       this.cz1 = th;
/* 157:108 */       break;
/* 158:    */     case 3: 
/* 159:110 */       this.context.setSize(this.cx1, this.cy1, 1.0F - th, this.cx2, this.cy2, 1.0D);
/* 160:111 */       this.cz2 = (1.0F - th);
/* 161:112 */       break;
/* 162:    */     case 4: 
/* 163:114 */       this.context.setSize(0.0D, this.cy1, this.cz1, th, this.cy2, this.cz2);
/* 164:115 */       this.cx1 = th;
/* 165:116 */       break;
/* 166:    */     case 5: 
/* 167:118 */       this.context.setSize(1.0F - th, this.cy1, this.cz1, 1.0D, this.cy2, this.cz2);
/* 168:119 */       this.cx2 = (1.0F - th);
/* 169:120 */       break;
/* 170:    */     case 6: 
/* 171:124 */       this.context.setSize(this.cx1, this.cy1, this.cz1, th, th, th);
/* 172:125 */       this.x1[0] = th;this.y1[0] = th;this.z1[0] = th;
/* 173:126 */       break;
/* 174:    */     case 7: 
/* 175:128 */       this.context.setSize(this.cx1, this.cy1, 1.0F - th, th, th, this.cz2);
/* 176:129 */       this.x1[1] = th;this.y1[1] = th;this.z2[0] = (1.0F - th);
/* 177:130 */       break;
/* 178:    */     case 8: 
/* 179:132 */       this.context.setSize(1.0F - th, this.cy1, this.cz1, this.cx2, th, th);
/* 180:133 */       this.x2[0] = (1.0F - th);this.y1[2] = th;this.z1[1] = th;
/* 181:134 */       break;
/* 182:    */     case 9: 
/* 183:136 */       this.context.setSize(1.0F - th, this.cy1, 1.0F - th, this.cx2, th, this.cz2);
/* 184:137 */       this.x2[1] = (1.0F - th);this.y1[3] = th;this.z2[1] = (1.0F - th);
/* 185:138 */       break;
/* 186:    */     case 10: 
/* 187:140 */       this.context.setSize(this.cx1, 1.0F - th, this.cz1, th, this.cy2, th);
/* 188:141 */       this.x1[2] = th;this.y2[0] = (1.0F - th);this.z1[2] = th;
/* 189:142 */       break;
/* 190:    */     case 11: 
/* 191:144 */       this.context.setSize(this.cx1, 1.0F - th, 1.0F - th, th, this.cy2, this.cz2);
/* 192:145 */       this.x1[3] = th;this.y2[1] = (1.0F - th);this.z2[2] = (1.0F - th);
/* 193:146 */       break;
/* 194:    */     case 12: 
/* 195:148 */       this.context.setSize(1.0F - th, 1.0F - th, this.cz1, this.cx2, this.cy2, th);
/* 196:149 */       this.x2[2] = (1.0F - th);this.y2[2] = (1.0F - th);this.z1[3] = th;
/* 197:150 */       break;
/* 198:    */     case 13: 
/* 199:152 */       this.context.setSize(1.0F - th, 1.0F - th, 1.0F - th, this.cx2, this.cy2, this.cz2);
/* 200:153 */       this.x2[3] = (1.0F - th);this.y2[3] = (1.0F - th);this.z2[3] = (1.0F - th);
/* 201:154 */       break;
/* 202:    */     case 14: 
/* 203:158 */       this.context.setSize(this.x1[0], this.cy1, this.cz1, this.x2[0], th, th);
/* 204:159 */       this.z1[0] = Math.max(this.z1[0], th);
/* 205:160 */       this.z1[1] = Math.max(this.z1[1], th);
/* 206:161 */       this.y1[0] = Math.max(this.y1[0], th);
/* 207:162 */       this.y1[2] = Math.max(this.y1[2], th);
/* 208:163 */       break;
/* 209:    */     case 15: 
/* 210:165 */       this.context.setSize(this.x1[1], this.cy1, 1.0F - th, this.x2[1], th, this.cz2);
/* 211:166 */       this.z2[0] = Math.min(this.z2[0], 1.0F - th);
/* 212:167 */       this.z2[1] = Math.min(this.z2[1], 1.0F - th);
/* 213:168 */       this.y1[1] = Math.max(this.y1[1], th);
/* 214:169 */       this.y1[3] = Math.max(this.y1[3], th);
/* 215:170 */       break;
/* 216:    */     case 16: 
/* 217:172 */       this.context.setSize(this.cx1, this.cy1, this.z1[0], th, th, this.z2[0]);
/* 218:173 */       this.x1[0] = Math.max(this.x1[0], th);
/* 219:174 */       this.x1[1] = Math.max(this.x1[1], th);
/* 220:175 */       this.y1[0] = Math.max(this.y1[0], th);
/* 221:176 */       this.y1[1] = Math.max(this.y1[1], th);
/* 222:177 */       break;
/* 223:    */     case 17: 
/* 224:179 */       this.context.setSize(1.0F - th, this.cy1, this.z1[1], this.cx2, th, this.z2[1]);
/* 225:180 */       this.x2[0] = Math.min(this.x2[0], 1.0F - th);
/* 226:181 */       this.x2[1] = Math.min(this.x2[1], 1.0F - th);
/* 227:182 */       this.y1[2] = Math.max(this.y1[2], th);
/* 228:183 */       this.y1[3] = Math.max(this.y1[3], th);
/* 229:184 */       break;
/* 230:    */     case 18: 
/* 231:187 */       this.context.setSize(this.cx1, this.y1[0], this.cz1, th, this.y2[0], th);
/* 232:188 */       this.x1[0] = Math.max(this.x1[0], th);
/* 233:189 */       this.x1[2] = Math.max(this.x1[2], th);
/* 234:190 */       this.z1[0] = Math.max(this.z1[0], th);
/* 235:191 */       this.z1[2] = Math.max(this.z1[2], th);
/* 236:192 */       break;
/* 237:    */     case 19: 
/* 238:194 */       this.context.setSize(this.cx1, this.y1[1], 1.0F - th, th, this.y2[1], this.cz2);
/* 239:195 */       this.x1[1] = Math.max(this.x1[1], th);
/* 240:196 */       this.x1[3] = Math.max(this.x1[3], th);
/* 241:197 */       this.z2[0] = Math.min(this.z2[0], 1.0F - th);
/* 242:198 */       this.z2[2] = Math.min(this.z2[2], 1.0F - th);
/* 243:199 */       break;
/* 244:    */     case 20: 
/* 245:201 */       this.context.setSize(1.0F - th, this.y1[2], this.cz1, this.cx2, this.y2[2], th);
/* 246:202 */       this.x2[0] = Math.min(this.x2[0], 1.0F - th);
/* 247:203 */       this.x2[2] = Math.min(this.x2[2], 1.0F - th);
/* 248:204 */       this.z1[1] = Math.max(this.z1[1], th);
/* 249:205 */       this.z1[3] = Math.max(this.z1[3], th);
/* 250:206 */       break;
/* 251:    */     case 21: 
/* 252:208 */       this.context.setSize(1.0F - th, this.y1[3], 1.0F - th, this.cx2, this.y2[3], this.cz2);
/* 253:209 */       this.x2[1] = Math.min(this.x2[1], 1.0F - th);
/* 254:210 */       this.x2[3] = Math.min(this.x2[3], 1.0F - th);
/* 255:211 */       this.z2[1] = Math.min(this.z2[1], 1.0F - th);
/* 256:212 */       this.z2[3] = Math.min(this.z2[3], 1.0F - th);
/* 257:213 */       break;
/* 258:    */     case 22: 
/* 259:216 */       this.context.setSize(this.x1[2], 1.0F - th, this.cz1, this.x2[2], this.cy2, th);
/* 260:217 */       this.z1[2] = Math.max(this.z1[2], th);
/* 261:218 */       this.z1[3] = Math.max(this.z1[3], th);
/* 262:219 */       this.y2[0] = Math.min(this.y2[0], 1.0F - th);
/* 263:220 */       this.y2[2] = Math.min(this.y2[2], 1.0F - th);
/* 264:221 */       break;
/* 265:    */     case 23: 
/* 266:223 */       this.context.setSize(this.x1[3], 1.0F - th, 1.0F - th, this.x2[3], this.cy2, this.cz2);
/* 267:224 */       this.z2[2] = Math.max(this.z2[2], 1.0F - th);
/* 268:225 */       this.z2[3] = Math.max(this.z2[3], 1.0F - th);
/* 269:226 */       this.y2[1] = Math.min(this.y2[1], 1.0F - th);
/* 270:227 */       this.y2[3] = Math.min(this.y2[3], 1.0F - th);
/* 271:228 */       break;
/* 272:    */     case 24: 
/* 273:230 */       this.context.setSize(this.cx1, 1.0F - th, this.z1[2], th, this.cy2, this.z2[2]);
/* 274:231 */       this.x1[2] = Math.max(this.x1[2], th);
/* 275:232 */       this.x1[3] = Math.max(this.x1[3], th);
/* 276:233 */       this.y2[0] = Math.min(this.y2[0], 1.0F - th);
/* 277:234 */       this.y2[1] = Math.min(this.y2[1], 1.0F - th);
/* 278:235 */       break;
/* 279:    */     case 25: 
/* 280:237 */       this.context.setSize(1.0F - th, 1.0F - th, this.z1[3], this.cx2, this.cy2, this.z2[3]);
/* 281:238 */       this.x2[2] = Math.min(this.x2[2], 1.0F - th);
/* 282:239 */       this.x2[3] = Math.min(this.x2[3], 1.0F - th);
/* 283:240 */       this.y2[2] = Math.min(this.y2[2], 1.0F - th);
/* 284:241 */       this.y2[3] = Math.min(this.y2[3], 1.0F - th);
/* 285:242 */       break;
/* 286:    */     case 26: 
/* 287:246 */       this.context.setSize(0.5D - th, this.cy1, 0.5D - th, 0.5D + th, this.cy2, 0.5D + th);
/* 288:    */       
/* 289:248 */       break;
/* 290:    */     case 27: 
/* 291:250 */       this.context.setSize(0.5D - th, 0.5D - th, this.cz1, 0.5D + th, 0.5D + th, this.cz2);
/* 292:    */       
/* 293:252 */       break;
/* 294:    */     case 28: 
/* 295:254 */       this.context.setSize(this.cx1, 0.5D - th, 0.5D - th, this.cx2, 0.5D + th, 0.5D + th);
/* 296:    */     }
/* 297:    */   }
/* 298:    */   
/* 299:    */   void setupCorners()
/* 300:    */   {
/* 301:261 */     for (int i = 0; i < 4; i++)
/* 302:    */     {
/* 303:262 */       this.x1[i] = this.cx1;this.y1[i] = this.cy1;this.z1[i] = this.cz1;
/* 304:263 */       this.x2[i] = this.cx2;this.y2[i] = this.cy2;this.z2[i] = this.cz2;
/* 305:    */     }
/* 306:    */   }
/* 307:    */   
/* 308:    */   public void initMasks(int uc, short[] cv)
/* 309:    */   {
/* 310:268 */     this.covmask = uc;
/* 311:269 */     this.covs = cv;
/* 312:    */     
/* 313:271 */     this.covmaskt = 0;this.covmaskh = 0;this.covmasko = 0;
/* 314:273 */     for (int i = 0; i < 6; i++) {
/* 315:274 */       if ((uc & 1 << i) != 0)
/* 316:    */       {
/* 317:276 */         if (CoverLib.isTransparent(this.covs[i] & 0xFF)) {
/* 318:277 */           this.covmaskt |= 1 << i;
/* 319:    */         }
/* 320:278 */         if (this.covs[i] >> 8 > 2) {
/* 321:278 */           this.covmaskh |= 1 << i;
/* 322:    */         }
/* 323:    */       }
/* 324:    */     }
/* 325:280 */     this.covmasko = (this.covmask & (this.covmaskt ^ 0xFFFFFFFF) & (this.covmaskh ^ 0xFFFFFFFF));
/* 326:    */   }
/* 327:    */   
/* 328:    */   public void render(int uc, short[] cv)
/* 329:    */   {
/* 330:284 */     initMasks(uc, cv);
/* 331:285 */     start();
/* 332:286 */     renderShell();
/* 333:287 */     if ((uc & 0xFFFFFFC0) == 0)
/* 334:    */     {
/* 335:288 */       this.context.clearTexFiles();
/* 336:289 */       return;
/* 337:    */     }
/* 338:291 */     renderOthers();
/* 339:    */   }
/* 340:    */   
/* 341:    */   public void renderShrink(int uc, short[] cv, float sh)
/* 342:    */   {
/* 343:295 */     initMasks(uc, cv);
/* 344:296 */     startShrink(sh);
/* 345:297 */     renderShell();
/* 346:298 */     if ((uc & 0xFFFFFFC0) == 0)
/* 347:    */     {
/* 348:299 */       this.context.clearTexFiles();
/* 349:300 */       return;
/* 350:    */     }
/* 351:302 */     renderOthers();
/* 352:    */   }
/* 353:    */   
/* 354:    */   private String getTerrain(String fn)
/* 355:    */   {
/* 356:319 */     return fn == null ? "/terrain.png" : fn;
/* 357:    */   }
/* 358:    */   
/* 359:    */   public void setTex(int cn)
/* 360:    */   {
/* 361:323 */     this.context.setTex(coverTextures[cn]);
/* 362:324 */     this.context.setTexFile(getTerrain(coverTextureFiles[cn]));
/* 363:    */   }
/* 364:    */   
/* 365:    */   public void setTex(int c1, int c2, int c3, int c4, int c5, int c6)
/* 366:    */   {
/* 367:328 */     this.context.setTex(coverTextures[c1][0], coverTextures[c2][1], coverTextures[c3][2], coverTextures[c4][3], coverTextures[c5][4], coverTextures[c6][5]);
/* 368:    */     
/* 369:    */ 
/* 370:    */ 
/* 371:332 */     this.context.setTexFiles(getTerrain(coverTextureFiles[c1]), getTerrain(coverTextureFiles[c2]), getTerrain(coverTextureFiles[c3]), getTerrain(coverTextureFiles[c4]), getTerrain(coverTextureFiles[c5]), getTerrain(coverTextureFiles[c6]));
/* 372:    */   }
/* 373:    */   
/* 374:    */   public void renderShell()
/* 375:    */   {
/* 376:342 */     this.context.setOrientation(0, 0);
/* 377:343 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 378:345 */     if (this.covmasko > 0)
/* 379:    */     {
/* 380:346 */       this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 381:    */       
/* 382:348 */       setTex(this.covs[0] & 0xFF, this.covs[1] & 0xFF, this.covs[2] & 0xFF, this.covs[3] & 0xFF, this.covs[4] & 0xFF, this.covs[5] & 0xFF);
/* 383:    */       
/* 384:    */ 
/* 385:    */ 
/* 386:    */ 
/* 387:    */ 
/* 388:    */ 
/* 389:    */ 
/* 390:    */ 
/* 391:    */ 
/* 392:    */ 
/* 393:    */ 
/* 394:    */ 
/* 395:    */ 
/* 396:    */ 
/* 397:    */ 
/* 398:364 */       this.context.setTexFlags(55);
/* 399:365 */       this.context.calcBoundsGlobal();
/* 400:366 */       this.context.renderGlobFaces(this.covmasko);
/* 401:    */     }
/* 402:370 */     int rsf = (this.covmasko | this.covmaskh) & (this.covmaskt ^ 0xFFFFFFFF);
/* 403:371 */     if (rsf > 0) {
/* 404:371 */       for (int i = 0; i < 6; i++) {
/* 405:372 */         if ((rsf & 1 << i) != 0)
/* 406:    */         {
/* 407:374 */           setTex(this.covs[i] & 0xFF);
/* 408:    */           
/* 409:    */ 
/* 410:377 */           int cn = this.covs[i] >> 8;
/* 411:378 */           int vf = 1 << (i ^ 0x1) | 0x3F ^ this.covmasko;
/* 412:380 */           if (((cn >= 3) && (cn <= 5)) || ((cn >= 10) && (cn <= 13)))
/* 413:    */           {
/* 414:381 */             for (int j = 0; j < 4; j++)
/* 415:    */             {
/* 416:382 */               setSize(i, CoverLib.getThickness(i, this.covs[i]));
/* 417:383 */               sizeHollow(i, j);
/* 418:384 */               this.context.calcBoundsGlobal();
/* 419:385 */               this.context.renderGlobFaces(vf | innerFace(i, j));
/* 420:    */             }
/* 421:    */           }
/* 422:    */           else
/* 423:    */           {
/* 424:388 */             setSize(i, CoverLib.getThickness(i, this.covs[i]));
/* 425:389 */             this.context.calcBoundsGlobal();
/* 426:390 */             this.context.renderGlobFaces(vf);
/* 427:    */           }
/* 428:    */         }
/* 429:    */       }
/* 430:    */     }
/* 431:394 */     if (this.covmaskt > 0) {
/* 432:394 */       for (int i = 0; i < 6; i++) {
/* 433:395 */         if ((this.covmaskt & 1 << i) != 0)
/* 434:    */         {
/* 435:396 */           setTex(this.covs[i] & 0xFF);
/* 436:    */           
/* 437:    */ 
/* 438:399 */           int cn = this.covs[i] >> 8;
/* 439:400 */           int vf = 1 << (i ^ 0x1) | 0x3F ^ this.covmasko;
/* 440:406 */           if (((cn >= 3) && (cn <= 5)) || ((cn >= 10) && (cn <= 13)))
/* 441:    */           {
/* 442:407 */             for (int j = 0; j < 4; j++)
/* 443:    */             {
/* 444:408 */               setSize(i, CoverLib.getThickness(i, this.covs[i]));
/* 445:409 */               sizeHollow(i, j);
/* 446:410 */               this.context.calcBoundsGlobal();
/* 447:411 */               this.context.renderGlobFaces(vf | innerFace(i, j));
/* 448:    */             }
/* 449:    */           }
/* 450:    */           else
/* 451:    */           {
/* 452:414 */             setSize(i, CoverLib.getThickness(i, this.covs[i]));
/* 453:415 */             this.context.calcBoundsGlobal();
/* 454:416 */             this.context.renderGlobFaces(vf);
/* 455:    */           }
/* 456:    */         }
/* 457:    */       }
/* 458:    */     }
/* 459:    */   }
/* 460:    */   
/* 461:    */   public void renderOthers()
/* 462:    */   {
/* 463:423 */     float cth = 0.0F;
/* 464:424 */     int colc = 0;int coln = 0;
/* 465:425 */     for (int i = 26; i < 29; i++) {
/* 466:426 */       if ((this.covmasko & 1 << i) != 0)
/* 467:    */       {
/* 468:427 */         colc++;
/* 469:428 */         float f = CoverLib.getThickness(i, this.covs[i]);
/* 470:429 */         if (f > cth)
/* 471:    */         {
/* 472:429 */           coln = i;cth = f;
/* 473:    */         }
/* 474:    */       }
/* 475:    */     }
/* 476:432 */     if (colc > 1)
/* 477:    */     {
/* 478:433 */       setTex(this.covs[coln] & 0xFF);
/* 479:    */       
/* 480:    */ 
/* 481:    */ 
/* 482:437 */       this.context.setSize(0.5D - cth, 0.5D - cth, 0.5D - cth, 0.5D + cth, 0.5D + cth, 0.5D + cth);
/* 483:    */       
/* 484:439 */       this.context.calcBoundsGlobal();
/* 485:440 */       this.context.renderGlobFaces(63);
/* 486:442 */       for (int i = 26; i < 29; i++) {
/* 487:443 */         if ((this.covmasko & 1 << i) != 0)
/* 488:    */         {
/* 489:444 */           setTex(this.covs[i] & 0xFF);
/* 490:    */           
/* 491:    */ 
/* 492:447 */           setSize(i, CoverLib.getThickness(i, this.covs[i]));
/* 493:448 */           if (sizeColumnSpoke(i, false, cth))
/* 494:    */           {
/* 495:449 */             this.context.calcBoundsGlobal();
/* 496:450 */             this.context.renderGlobFaces(63);
/* 497:    */           }
/* 498:452 */           if (sizeColumnSpoke(i, true, cth))
/* 499:    */           {
/* 500:453 */             this.context.calcBoundsGlobal();
/* 501:454 */             this.context.renderGlobFaces(63);
/* 502:    */           }
/* 503:    */         }
/* 504:    */       }
/* 505:    */     }
/* 506:457 */     else if (colc == 1)
/* 507:    */     {
/* 508:458 */       setTex(this.covs[coln] & 0xFF);
/* 509:    */       
/* 510:    */ 
/* 511:    */ 
/* 512:462 */       setSize(coln, CoverLib.getThickness(coln, this.covs[coln]));
/* 513:463 */       this.context.calcBoundsGlobal();
/* 514:464 */       this.context.renderGlobFaces(0x3F ^ 3 << coln - 25 & this.covmasko);
/* 515:    */     }
/* 516:466 */     setupCorners();
/* 517:468 */     for (int i = 6; i < 14; i++) {
/* 518:469 */       if ((this.covmasko & 1 << i) != 0)
/* 519:    */       {
/* 520:470 */         setSize(i, CoverLib.getThickness(i, this.covs[i]));
/* 521:471 */         this.context.calcBoundsGlobal();
/* 522:472 */         setTex(this.covs[i] & 0xFF);
/* 523:    */         
/* 524:    */ 
/* 525:475 */         this.context.renderGlobFaces(63);
/* 526:    */       }
/* 527:    */     }
/* 528:477 */     for (int j = 6; j >= 0; j--) {
/* 529:477 */       for (int i = 14; i < 26; i++) {
/* 530:478 */         if (((this.covmasko & 1 << i) != 0) && 
/* 531:479 */           (this.covs[i] >> 8 == j))
/* 532:    */         {
/* 533:480 */           setSize(i, CoverLib.getThickness(i, this.covs[i]));
/* 534:481 */           this.context.calcBoundsGlobal();
/* 535:482 */           setTex(this.covs[i] & 0xFF);
/* 536:    */           
/* 537:    */ 
/* 538:485 */           this.context.renderGlobFaces(63);
/* 539:    */         }
/* 540:    */       }
/* 541:    */     }
/* 542:487 */     this.context.clearTexFiles();
/* 543:    */   }
/* 544:    */   
/* 545:491 */   float[] x1 = new float[4];
/* 546:492 */   float[] x2 = new float[4];
/* 547:493 */   float[] y1 = new float[4];
/* 548:494 */   float[] y2 = new float[4];
/* 549:495 */   float[] z1 = new float[4];
/* 550:496 */   float[] z2 = new float[4];
/* 551:    */   public short[] covs;
/* 552:    */   public int covmask;
/* 553:    */   public int covmaskt;
/* 554:    */   public int covmaskh;
/* 555:    */   public int covmasko;
/* 556:502 */   protected static int[][] coverTextures = CoverLib.coverTextures;
/* 557:503 */   protected static String[] coverTextureFiles = CoverLib.coverTextureFiles;
/* 558:    */   protected RenderContext context;
/* 559:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CoverRenderer
 * JD-Core Version:    0.7.0.1
 */