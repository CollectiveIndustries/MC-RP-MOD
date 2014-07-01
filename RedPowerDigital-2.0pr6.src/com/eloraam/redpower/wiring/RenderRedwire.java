/*   1:    */ package com.eloraam.redpower.wiring;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import any;
/*   5:    */ import baz;
/*   6:    */ import bbb;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.RenderContext;
/*   9:    */ import com.eloraam.redpower.core.RenderLib;
/*  10:    */ import com.eloraam.redpower.core.TileCovered;
/*  11:    */ import java.util.Random;
/*  12:    */ import yc;
/*  13:    */ import ym;
/*  14:    */ 
/*  15:    */ public class RenderRedwire
/*  16:    */   extends RenderWiring
/*  17:    */ {
/*  18:    */   public RenderRedwire(amq bl)
/*  19:    */   {
/*  20: 16 */     super(bl);
/*  21:    */   }
/*  22:    */   
/*  23:    */   static TileRedwire getTileEntity(ym iba, int i, int j, int k)
/*  24:    */   {
/*  25: 20 */     any te = iba.q(i, j, k);
/*  26: 21 */     if (!(te instanceof TileRedwire)) {
/*  27: 21 */       return null;
/*  28:    */     }
/*  29: 22 */     return (TileRedwire)te;
/*  30:    */   }
/*  31:    */   
/*  32: 36 */   private int[] glowtex = { 5, 5, 5, 5, 5, 5 };
/*  33:    */   
/*  34:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  35:    */   
/*  36:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  37:    */   {
/*  38: 41 */     baz tessellator = baz.a;
/*  39: 42 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/*  40:    */     
/*  41: 44 */     TileCovered tc = (TileCovered)CoreLib.getTileEntity(iba, i, j, k, TileCovered.class);
/*  42: 46 */     if (tc == null) {
/*  43: 46 */       return;
/*  44:    */     }
/*  45: 48 */     this.context.setTexFlags(55);
/*  46:    */     
/*  47: 50 */     this.context.setPos(i, j, k);
/*  48: 51 */     if (tc.CoverSides > 0)
/*  49:    */     {
/*  50: 52 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/*  51: 53 */       if (renderblocks.d >= 0)
/*  52:    */       {
/*  53: 54 */         this.context.setTex(renderblocks.d);
/*  54: 55 */         this.context.lockTexture = true;
/*  55: 56 */         this.context.forceFlat = true;
/*  56:    */       }
/*  57: 58 */       this.context.readGlobalLights(iba, i, j, k);
/*  58: 59 */       renderCovers(tc.CoverSides, tc.Covers);
/*  59: 60 */       this.context.forceFlat = false;
/*  60: 61 */       this.context.lockTexture = false;
/*  61:    */     }
/*  62: 63 */     if (md == 0) {
/*  63: 63 */       return;
/*  64:    */     }
/*  65: 65 */     TileWiring tw = (TileWiring)tc;
/*  66:    */     
/*  67:    */ 
/*  68:    */ 
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72: 72 */     int cons = tw.getConnectionMask();
/*  73: 73 */     int indcon = tw.getExtConnectionMask();
/*  74: 74 */     int indconex = tw.EConEMask;
/*  75: 75 */     cons |= indcon;
/*  76: 77 */     if (md == 1)
/*  77:    */     {
/*  78: 78 */       TileRedwire tl = (TileRedwire)tw;
/*  79:    */       
/*  80: 80 */       this.context.setTint(0.3F + 0.7F * (tl.PowerState / 255.0F), 0.0F, 0.0F);
/*  81: 81 */       setSideTex(1, 2, 1);
/*  82: 82 */       setWireSize(0.125F, 0.125F);
/*  83:    */     }
/*  84: 83 */     else if (md == 2)
/*  85:    */     {
/*  86: 84 */       TileInsulatedWire tl = (TileInsulatedWire)tw;
/*  87:    */       
/*  88: 86 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/*  89: 87 */       setSideTex(16 + tw.Metadata, (tl.PowerState > 0 ? 48 : 32) + tw.Metadata, 16 + tw.Metadata);
/*  90:    */       
/*  91:    */ 
/*  92: 90 */       setWireSize(0.25F, 0.188F);
/*  93:    */     }
/*  94: 91 */     else if (md == 3)
/*  95:    */     {
/*  96: 92 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/*  97: 93 */       if (tw.Metadata == 0) {
/*  98: 93 */         setSideTex(3, 4, 3);
/*  99:    */       } else {
/* 100: 94 */         setSideTex(63 + tw.Metadata, 79 + tw.Metadata, 3);
/* 101:    */       }
/* 102: 96 */       setWireSize(0.375F, 0.25F);
/* 103:    */     }
/* 104: 97 */     else if (md == 5)
/* 105:    */     {
/* 106: 98 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/* 107: 99 */       if (tw.Metadata == 0)
/* 108:    */       {
/* 109:100 */         setSideTex(8, 9, 8);
/* 110:101 */         setWireSize(0.25F, 0.188F);
/* 111:    */       }
/* 112:102 */       else if (tw.Metadata == 1)
/* 113:    */       {
/* 114:103 */         setSideTex(11, 12, 11);
/* 115:104 */         setWireSize(0.375F, 0.25F);
/* 116:    */       }
/* 117:105 */       else if (tw.Metadata == 2)
/* 118:    */       {
/* 119:107 */         setSideTexJumbo(96, 97, 98, 99, 100, 101);
/* 120:108 */         setWireSize(0.5F, 0.3125F);
/* 121:    */       }
/* 122:    */     }
/* 123:111 */     RenderLib.setRedPowerTexture();
/* 124:112 */     renderWireBlock(tw.ConSides, cons, indcon, indconex);
/* 125:113 */     RenderLib.setDefaultTexture();
/* 126:115 */     if ((md != 1) && (md != 3) && (md != 5)) {
/* 127:115 */       return;
/* 128:    */     }
/* 129:116 */     if ((tw.ConSides & 0x40) == 0) {
/* 130:116 */       return;
/* 131:    */     }
/* 132:117 */     this.context.setTexFlags(0);
/* 133:118 */     this.context.setOrientation(0, 0);
/* 134:119 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/* 135:120 */     this.context.setLocalLights(0.5F, 1.0F, 0.7F, 0.7F, 0.7F, 0.7F);
/* 136:    */     int tx;
/* 137:    */     int tx;
/* 138:122 */     if (md == 1)
/* 139:    */     {
/* 140:123 */       tx = ((TileRedwire)tw).PowerState > 0 ? 6 : 5;
/* 141:    */     }
/* 142:    */     else
/* 143:    */     {
/* 144:    */       int tx;
/* 145:124 */       if (md == 3) {
/* 146:125 */         tx = 7;
/* 147:    */       } else {
/* 148:127 */         tx = 10;
/* 149:    */       }
/* 150:    */     }
/* 151:129 */     renderCenterBlock(cons >> 24 | tw.ConSides & 0x3F, com.eloraam.redpower.core.CoverLib.coverTextureFiles[tw.CenterPost], coverTextures[tw.CenterPost], tx);
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void renderInvBlock(bbb renderblocks, int md)
/* 155:    */   {
/* 156:135 */     baz tessellator = baz.a;
/* 157:136 */     this.block.f();
/* 158:137 */     int bid = md >> 8;md &= 0xFF;
/* 159:    */     
/* 160:139 */     this.context.setDefaults();
/* 161:140 */     this.context.setTexFlags(55);
/* 162:141 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 163:    */     float th;
/* 164:144 */     switch (bid)
/* 165:    */     {
/* 166:    */     case 0: 
/* 167:    */     case 16: 
/* 168:    */     case 17: 
/* 169:    */     case 27: 
/* 170:    */     case 28: 
/* 171:    */     case 29: 
/* 172:    */     case 30: 
/* 173:148 */       switch (bid)
/* 174:    */       {
/* 175:    */       case 0: 
/* 176:149 */         th = 0.063F; break;
/* 177:    */       case 16: 
/* 178:150 */         th = 0.125F; break;
/* 179:    */       case 17: 
/* 180:151 */         th = 0.25F; break;
/* 181:    */       case 27: 
/* 182:153 */         th = 0.188F; break;
/* 183:    */       case 28: 
/* 184:154 */         th = 0.313F; break;
/* 185:    */       case 29: 
/* 186:155 */         th = 0.375F; break;
/* 187:    */       case 30: 
/* 188:156 */         th = 0.438F; break;
/* 189:    */       default: 
/* 190:157 */         return;
/* 191:    */       }
/* 192:159 */       this.context.setTex(coverTextures[md]);
/* 193:160 */       this.context.setTexFile(com.eloraam.redpower.core.CoverLib.coverTextureFiles[md]);
/* 194:    */       
/* 195:162 */       this.context.setSize(0.0D, 0.0D, 0.5F - th, 1.0D, 1.0D, 0.5F + th);
/* 196:163 */       this.context.calcBounds();
/* 197:    */       
/* 198:165 */       tessellator.b();
/* 199:166 */       this.context.useNormal = true;
/* 200:167 */       this.context.renderFaces(63);
/* 201:168 */       this.context.useNormal = false;
/* 202:169 */       tessellator.a();
/* 203:170 */       return;
/* 204:    */     case 24: 
/* 205:    */     case 25: 
/* 206:    */     case 26: 
/* 207:    */     case 31: 
/* 208:    */     case 32: 
/* 209:    */     case 33: 
/* 210:    */     case 34: 
/* 211:175 */       switch (bid)
/* 212:    */       {
/* 213:    */       case 24: 
/* 214:176 */         th = 0.063F; break;
/* 215:    */       case 25: 
/* 216:177 */         th = 0.125F; break;
/* 217:    */       case 26: 
/* 218:178 */         th = 0.25F; break;
/* 219:    */       case 31: 
/* 220:180 */         th = 0.188F; break;
/* 221:    */       case 32: 
/* 222:181 */         th = 0.313F; break;
/* 223:    */       case 33: 
/* 224:182 */         th = 0.375F; break;
/* 225:    */       case 34: 
/* 226:183 */         th = 0.438F; break;
/* 227:    */       case 27: 
/* 228:    */       case 28: 
/* 229:    */       case 29: 
/* 230:    */       case 30: 
/* 231:    */       default: 
/* 232:184 */         return;
/* 233:    */       }
/* 234:186 */       this.context.setTex(coverTextures[md]);
/* 235:187 */       this.context.setTexFile(com.eloraam.redpower.core.CoverLib.coverTextureFiles[md]);
/* 236:    */       
/* 237:189 */       tessellator.b();
/* 238:190 */       this.context.useNormal = true;
/* 239:191 */       this.context.renderBox(63, 0.0D, 0.0D, 0.5F - th, 0.25D, 1.0D, 0.5F + th);
/* 240:192 */       this.context.renderBox(63, 0.75D, 0.0D, 0.5F - th, 1.0D, 1.0D, 0.5F + th);
/* 241:193 */       this.context.renderBox(15, 0.25D, 0.0D, 0.5F - th, 0.75D, 0.25D, 0.5F + th);
/* 242:194 */       this.context.renderBox(15, 0.25D, 0.75D, 0.5F - th, 0.75D, 1.0D, 0.5F + th);
/* 243:195 */       this.context.useNormal = false;
/* 244:196 */       tessellator.a();
/* 245:197 */       return;
/* 246:    */     case 18: 
/* 247:    */     case 19: 
/* 248:    */     case 20: 
/* 249:    */     case 35: 
/* 250:    */     case 36: 
/* 251:    */     case 37: 
/* 252:    */     case 38: 
/* 253:202 */       switch (bid)
/* 254:    */       {
/* 255:    */       case 18: 
/* 256:203 */         th = 0.063F; break;
/* 257:    */       case 19: 
/* 258:204 */         th = 0.125F; break;
/* 259:    */       case 20: 
/* 260:205 */         th = 0.25F; break;
/* 261:    */       case 35: 
/* 262:207 */         th = 0.188F; break;
/* 263:    */       case 36: 
/* 264:208 */         th = 0.313F; break;
/* 265:    */       case 37: 
/* 266:209 */         th = 0.375F; break;
/* 267:    */       case 38: 
/* 268:210 */         th = 0.438F; break;
/* 269:    */       case 21: 
/* 270:    */       case 22: 
/* 271:    */       case 23: 
/* 272:    */       case 24: 
/* 273:    */       case 25: 
/* 274:    */       case 26: 
/* 275:    */       case 27: 
/* 276:    */       case 28: 
/* 277:    */       case 29: 
/* 278:    */       case 30: 
/* 279:    */       case 31: 
/* 280:    */       case 32: 
/* 281:    */       case 33: 
/* 282:    */       case 34: 
/* 283:    */       default: 
/* 284:211 */         return;
/* 285:    */       }
/* 286:213 */       this.context.setTex(coverTextures[md]);
/* 287:214 */       this.context.setTexFile(com.eloraam.redpower.core.CoverLib.coverTextureFiles[md]);
/* 288:    */       
/* 289:216 */       this.context.setSize(0.5F - th, 0.5F - th, 0.5F - th, 0.5F + th, 0.5F + th, 0.5F + th);
/* 290:    */       
/* 291:218 */       this.context.calcBounds();
/* 292:    */       
/* 293:220 */       tessellator.b();
/* 294:221 */       this.context.useNormal = true;
/* 295:222 */       this.context.renderFaces(63);
/* 296:223 */       this.context.useNormal = false;
/* 297:224 */       tessellator.a();
/* 298:225 */       return;
/* 299:    */     case 21: 
/* 300:    */     case 22: 
/* 301:    */     case 23: 
/* 302:    */     case 39: 
/* 303:    */     case 40: 
/* 304:    */     case 41: 
/* 305:    */     case 42: 
/* 306:230 */       switch (bid)
/* 307:    */       {
/* 308:    */       case 21: 
/* 309:231 */         th = 0.063F; break;
/* 310:    */       case 22: 
/* 311:232 */         th = 0.125F; break;
/* 312:    */       case 23: 
/* 313:233 */         th = 0.25F; break;
/* 314:    */       case 39: 
/* 315:235 */         th = 0.188F; break;
/* 316:    */       case 40: 
/* 317:236 */         th = 0.313F; break;
/* 318:    */       case 41: 
/* 319:237 */         th = 0.375F; break;
/* 320:    */       case 42: 
/* 321:238 */         th = 0.438F; break;
/* 322:    */       case 24: 
/* 323:    */       case 25: 
/* 324:    */       case 26: 
/* 325:    */       case 27: 
/* 326:    */       case 28: 
/* 327:    */       case 29: 
/* 328:    */       case 30: 
/* 329:    */       case 31: 
/* 330:    */       case 32: 
/* 331:    */       case 33: 
/* 332:    */       case 34: 
/* 333:    */       case 35: 
/* 334:    */       case 36: 
/* 335:    */       case 37: 
/* 336:    */       case 38: 
/* 337:    */       default: 
/* 338:239 */         return;
/* 339:    */       }
/* 340:242 */       this.context.setTex(coverTextures[md]);
/* 341:243 */       this.context.setTexFile(com.eloraam.redpower.core.CoverLib.coverTextureFiles[md]);
/* 342:    */       
/* 343:245 */       this.context.setSize(0.5F - th, 0.0D, 0.5F - th, 0.5F + th, 1.0D, 0.5F + th);
/* 344:    */       
/* 345:247 */       this.context.calcBounds();
/* 346:    */       
/* 347:249 */       tessellator.b();
/* 348:250 */       this.context.useNormal = true;
/* 349:251 */       this.context.renderFaces(63);
/* 350:252 */       this.context.useNormal = false;
/* 351:253 */       tessellator.a();
/* 352:254 */       return;
/* 353:    */     case 43: 
/* 354:    */     case 44: 
/* 355:    */     case 45: 
/* 356:258 */       switch (bid)
/* 357:    */       {
/* 358:    */       case 43: 
/* 359:259 */         th = 0.125F; break;
/* 360:    */       case 44: 
/* 361:260 */         th = 0.25F; break;
/* 362:    */       case 45: 
/* 363:261 */         th = 0.375F; break;
/* 364:    */       default: 
/* 365:262 */         return;
/* 366:    */       }
/* 367:265 */       this.context.setTex(coverTextures[md]);
/* 368:266 */       this.context.setTexFile(com.eloraam.redpower.core.CoverLib.coverTextureFiles[md]);
/* 369:    */       
/* 370:268 */       this.context.setSize(0.5F - th, 0.125D, 0.5F - th, 0.5F + th, 0.875D, 0.5F + th);
/* 371:    */       
/* 372:270 */       this.context.calcBounds();
/* 373:    */       
/* 374:272 */       tessellator.b();
/* 375:273 */       this.context.useNormal = true;
/* 376:274 */       this.context.renderFaces(63);
/* 377:    */       
/* 378:276 */       this.context.setSize(0.45F - th, 0.0D, 0.45F - th, 0.55F + th, 0.125D, 0.55F + th);
/* 379:    */       
/* 380:278 */       this.context.calcBounds();
/* 381:279 */       this.context.renderFaces(63);
/* 382:    */       
/* 383:281 */       this.context.setSize(0.45F - th, 0.875D, 0.45F - th, 0.55F + th, 1.0D, 0.55F + th);
/* 384:    */       
/* 385:283 */       this.context.calcBounds();
/* 386:284 */       this.context.renderFaces(63);
/* 387:    */       
/* 388:286 */       this.context.useNormal = false;
/* 389:287 */       tessellator.a();
/* 390:288 */       return;
/* 391:    */     case 64: 
/* 392:    */     case 65: 
/* 393:    */     case 66: 
/* 394:292 */       this.context.setTex(coverTextures[md]);
/* 395:293 */       this.context.setTexFile(com.eloraam.redpower.core.CoverLib.coverTextureFiles[md]);
/* 396:    */       
/* 397:295 */       tessellator.b();
/* 398:296 */       this.context.useNormal = true;
/* 399:    */       
/* 400:298 */       this.context.renderBox(60, 0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 401:299 */       this.context.renderBox(15, 0.0D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
/* 402:300 */       this.context.renderBox(51, 0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
/* 403:    */       
/* 404:302 */       tessellator.a();
/* 405:303 */       tessellator.b();
/* 406:    */       
/* 407:305 */       this.context.setTexFile("/eloraam/wiring/redpower1.png");
/* 408:    */       
/* 409:307 */       this.context.setTex(bid == 64 ? 5 : bid == 66 ? 10 : 7);
/* 410:    */       
/* 411:    */ 
/* 412:310 */       this.context.renderBox(3, 0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 413:311 */       this.context.renderBox(48, 0.0D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
/* 414:312 */       this.context.renderBox(12, 0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
/* 415:    */       
/* 416:314 */       tessellator.a();
/* 417:315 */       this.context.clearTexFiles();
/* 418:316 */       this.context.useNormal = false;
/* 419:317 */       return;
/* 420:    */     }
/* 421:320 */     this.context.setPos(-0.5D, -0.2000000029802322D, -0.5D);
/* 422:321 */     this.context.setOrientation(0, 0);
/* 423:323 */     if (bid == 1)
/* 424:    */     {
/* 425:324 */       setSideTex(1, 2, 1);
/* 426:325 */       setWireSize(0.125F, 0.125F);
/* 427:326 */       this.context.setTint(1.0F, 0.0F, 0.0F);
/* 428:    */     }
/* 429:327 */     else if (bid == 2)
/* 430:    */     {
/* 431:328 */       setSideTex(16 + md, 32 + md, 16 + md);
/* 432:329 */       setWireSize(0.25F, 0.188F);
/* 433:    */     }
/* 434:330 */     else if (bid == 3)
/* 435:    */     {
/* 436:331 */       if (md == 0) {
/* 437:331 */         setSideTex(3, 4, 3);
/* 438:    */       } else {
/* 439:332 */         setSideTex(63 + md, 79 + md, 3);
/* 440:    */       }
/* 441:333 */       setWireSize(0.375F, 0.25F);
/* 442:    */     }
/* 443:334 */     else if (bid == 5)
/* 444:    */     {
/* 445:335 */       if (md == 0)
/* 446:    */       {
/* 447:336 */         setSideTex(8, 9, 8);
/* 448:337 */         setWireSize(0.25F, 0.188F);
/* 449:    */       }
/* 450:338 */       else if (md == 1)
/* 451:    */       {
/* 452:339 */         setSideTex(11, 12, 11);
/* 453:340 */         setWireSize(0.375F, 0.25F);
/* 454:    */       }
/* 455:341 */       else if (md == 2)
/* 456:    */       {
/* 457:342 */         setSideTexJumbo(96, 97, 98, 99, 100, 101);
/* 458:343 */         setWireSize(0.5F, 0.3125F);
/* 459:    */       }
/* 460:    */     }
/* 461:    */     else
/* 462:    */     {
/* 463:345 */       return;
/* 464:    */     }
/* 465:347 */     this.context.useNormal = true;
/* 466:348 */     RenderLib.setRedPowerTexture();
/* 467:349 */     tessellator.b();
/* 468:350 */     renderSideWires(127, 0, 0);
/* 469:351 */     tessellator.a();
/* 470:352 */     RenderLib.setDefaultTexture();
/* 471:353 */     this.context.useNormal = false;
/* 472:    */   }
/* 473:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.RenderRedwire
 * JD-Core Version:    0.7.0.1
 */