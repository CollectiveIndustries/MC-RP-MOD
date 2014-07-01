/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import com.eloraam.redpower.core.MathLib;
/*   6:    */ import com.eloraam.redpower.core.PowerLib;
/*   7:    */ import com.eloraam.redpower.core.Quat;
/*   8:    */ import com.eloraam.redpower.core.RenderContext;
/*   9:    */ import com.eloraam.redpower.core.RenderLib;
/*  10:    */ import com.eloraam.redpower.core.Vector3;
/*  11:    */ import ym;
/*  12:    */ 
/*  13:    */ public class RenderLogicSimple
/*  14:    */   extends RenderLogic
/*  15:    */ {
/*  16:    */   public RenderLogicSimple(amq bl)
/*  17:    */   {
/*  18: 14 */     super(bl);
/*  19:    */   }
/*  20:    */   
/*  21: 17 */   private static RenderLogic.TorchPos[] torchMapLatch = { new RenderLogic.TorchPos(-0.3D, -0.15D, 0.0D, 0.8D), new RenderLogic.TorchPos(0.3D, -0.15D, 0.0D, 0.8D) };
/*  22: 21 */   private static RenderLogic.TorchPos[] torchMapLatch2 = { new RenderLogic.TorchPos(-0.281D, -0.15D, -0.09379999999999999D, 0.8D), new RenderLogic.TorchPos(0.281D, -0.15D, 0.09379999999999999D, 0.8D) };
/*  23: 25 */   private static RenderLogic.TorchPos[] torchMapLatch2b = { new RenderLogic.TorchPos(-0.281D, -0.15D, 0.09379999999999999D, 0.8D), new RenderLogic.TorchPos(0.281D, -0.15D, -0.09379999999999999D, 0.8D) };
/*  24: 29 */   private static RenderLogic.TorchPos[] torchMapNor = { new RenderLogic.TorchPos(-0.094D, -0.25D, 0.031D, 0.7D) };
/*  25: 32 */   private static RenderLogic.TorchPos[] torchMapOr = { new RenderLogic.TorchPos(-0.094D, -0.25D, 0.031D, 0.7D), new RenderLogic.TorchPos(0.28D, -0.15D, 0.0D, 0.8D) };
/*  26: 36 */   private static RenderLogic.TorchPos[] torchMapNand = { new RenderLogic.TorchPos(-0.031D, -0.25D, 0.22D, 0.7D), new RenderLogic.TorchPos(-0.031D, -0.25D, 0.0D, 0.7D), new RenderLogic.TorchPos(-0.031D, -0.25D, -0.22D, 0.7D) };
/*  27: 41 */   private static RenderLogic.TorchPos[] torchMapAnd = { new RenderLogic.TorchPos(-0.031D, -0.25D, 0.22D, 0.7D), new RenderLogic.TorchPos(-0.031D, -0.25D, 0.0D, 0.7D), new RenderLogic.TorchPos(-0.031D, -0.25D, -0.22D, 0.7D), new RenderLogic.TorchPos(0.28D, -0.15D, 0.0D, 0.8D) };
/*  28: 47 */   private static RenderLogic.TorchPos[] torchMapXnor = { new RenderLogic.TorchPos(-0.031D, -0.25D, 0.22D, 0.7D), new RenderLogic.TorchPos(-0.031D, -0.25D, -0.22D, 0.7D), new RenderLogic.TorchPos(-0.28D, -0.25D, 0.0D, 0.7D), new RenderLogic.TorchPos(0.28D, -0.15D, 0.0D, 0.8D) };
/*  29: 53 */   private static RenderLogic.TorchPos[] torchMapXor = { new RenderLogic.TorchPos(-0.031D, -0.25D, 0.22D, 0.7D), new RenderLogic.TorchPos(-0.031D, -0.25D, -0.22D, 0.7D), new RenderLogic.TorchPos(-0.28D, -0.25D, 0.0D, 0.7D) };
/*  30: 58 */   private static RenderLogic.TorchPos[] torchMapPulse = { new RenderLogic.TorchPos(-0.09D, -0.25D, -0.22D, 0.7D), new RenderLogic.TorchPos(-0.09D, -0.25D, 0.22D, 0.7D), new RenderLogic.TorchPos(0.28D, -0.15D, 0.0D, 0.8D) };
/*  31: 63 */   private static RenderLogic.TorchPos[] torchMapToggle = { new RenderLogic.TorchPos(0.28D, -0.25D, -0.22D, 0.7D), new RenderLogic.TorchPos(-0.28D, -0.25D, -0.22D, 0.7D) };
/*  32: 67 */   private static RenderLogic.TorchPos[] torchMapNot = { new RenderLogic.TorchPos(-0.031D, -0.25D, 0.031D, 0.7D) };
/*  33: 70 */   private static RenderLogic.TorchPos[] torchMapBuffer = { new RenderLogic.TorchPos(0.281D, -0.15D, 0.031D, 0.8D), new RenderLogic.TorchPos(-0.094D, -0.25D, 0.031D, 0.7D) };
/*  34: 74 */   private static RenderLogic.TorchPos[] torchMapMux = { new RenderLogic.TorchPos(-0.031D, -0.25D, 0.22D, 0.7D), new RenderLogic.TorchPos(-0.031D, -0.25D, -0.22D, 0.7D), new RenderLogic.TorchPos(-0.156D, -0.25D, 0.031D, 0.7D), new RenderLogic.TorchPos(0.28D, -0.15D, 0.0D, 0.8D) };
/*  35: 80 */   private static RenderLogic.TorchPos[] torchMapMux2 = { new RenderLogic.TorchPos(-0.031D, -0.25D, 0.22D, 0.7D), new RenderLogic.TorchPos(-0.031D, -0.25D, -0.22D, 0.7D), new RenderLogic.TorchPos(-0.156D, -0.25D, -0.031D, 0.7D), new RenderLogic.TorchPos(0.28D, -0.15D, 0.0D, 0.8D) };
/*  36: 86 */   private static RenderLogic.TorchPos[] torchMapRepS = { new RenderLogic.TorchPos(0.313D, -0.25D, -0.125D, 0.7D), new RenderLogic.TorchPos(-0.25D, -0.25D, 0.25D, 0.7D) };
/*  37: 91 */   private static RenderLogic.TorchPos[] torchMapSync = { new RenderLogic.TorchPos(0.28D, -0.25D, 0.0D, 0.7D) };
/*  38: 95 */   private static RenderLogic.TorchPos[] torchMapDLatch = { new RenderLogic.TorchPos(-0.28D, -0.25D, -0.219D, 0.7D), new RenderLogic.TorchPos(0.031D, -0.25D, -0.219D, 0.7D), new RenderLogic.TorchPos(0.031D, -0.25D, -0.031D, 0.7D), new RenderLogic.TorchPos(0.031D, -0.15D, 0.281D, 0.8D), new RenderLogic.TorchPos(0.281D, -0.15D, -0.094D, 0.8D) };
/*  39:102 */   private static RenderLogic.TorchPos[] torchMapDLatch2 = { new RenderLogic.TorchPos(-0.28D, -0.25D, 0.219D, 0.7D), new RenderLogic.TorchPos(0.031D, -0.25D, 0.219D, 0.7D), new RenderLogic.TorchPos(0.031D, -0.25D, 0.031D, 0.7D), new RenderLogic.TorchPos(0.031D, -0.15D, -0.281D, 0.8D), new RenderLogic.TorchPos(0.281D, -0.15D, 0.094D, 0.8D) };
/*  40:    */   
/*  41:    */   protected int getTorchState(TileLogic tl)
/*  42:    */   {
/*  43:113 */     int md = tl.getExtendedMetadata();
/*  44:    */     int eps;
/*  45:115 */     switch (md)
/*  46:    */     {
/*  47:    */     case 0: 
/*  48:117 */       if (tl.Deadmap > 1) {
/*  49:118 */         return ((tl.PowerState & 0x2) > 0 ? 1 : 0) | ((tl.PowerState & 0x8) > 0 ? 2 : 0);
/*  50:    */       }
/*  51:121 */       if ((tl.Disabled) || (tl.Active)) {
/*  52:121 */         return 0;
/*  53:    */       }
/*  54:122 */       if (tl.Deadmap == 1) {
/*  55:122 */         return tl.Powered ? 1 : 2;
/*  56:    */       }
/*  57:123 */       return tl.Powered ? 2 : 1;
/*  58:    */     case 1: 
/*  59:125 */       return tl.Powered ? 1 : 0;
/*  60:    */     case 2: 
/*  61:127 */       eps = tl.PowerState & (tl.Deadmap ^ 0xFFFFFFFF);
/*  62:128 */       return (eps == 0 ? 1 : 0) | (tl.Powered ? 2 : 0);
/*  63:    */     case 3: 
/*  64:130 */       eps = tl.PowerState | tl.Deadmap;
/*  65:131 */       return eps & 0x7 ^ 0x7;
/*  66:    */     case 4: 
/*  67:133 */       eps = tl.PowerState | tl.Deadmap;
/*  68:134 */       return eps & 0x7 ^ 0x7 | (tl.Powered ? 8 : 0);
/*  69:    */     case 5: 
/*  70:    */     case 6: 
/*  71:137 */       switch (tl.PowerState & 0x5)
/*  72:    */       {
/*  73:    */       case 0: 
/*  74:138 */         eps = 4; break;
/*  75:    */       case 1: 
/*  76:139 */         eps = 2; break;
/*  77:    */       case 4: 
/*  78:140 */         eps = 1; break;
/*  79:    */       case 2: 
/*  80:    */       case 3: 
/*  81:    */       default: 
/*  82:141 */         eps = 0;
/*  83:    */       }
/*  84:143 */       if (md == 6) {
/*  85:143 */         return eps;
/*  86:    */       }
/*  87:144 */       return eps | (tl.Powered ? 8 : 0);
/*  88:    */     case 7: 
/*  89:146 */       return ((!tl.Powered) && (!tl.Active) ? 1 : 0) | ((tl.Powered) || (tl.Active) ? 2 : 0) | ((tl.Powered) && (!tl.Active) ? 4 : 0);
/*  90:    */     case 8: 
/*  91:150 */       return !tl.Powered ? 1 : 2;
/*  92:    */     case 9: 
/*  93:152 */       return tl.Powered ? 1 : 0;
/*  94:    */     case 10: 
/*  95:154 */       return (tl.Powered ? 1 : 0) | tl.PowerState & 0x2;
/*  96:    */     case 11: 
/*  97:156 */       if (tl.Deadmap == 0) {
/*  98:157 */         return (tl.Powered ? 8 : 0) | ((tl.PowerState & 0x3) == 0 ? 1 : 0) | ((tl.PowerState & 0x6) == 2 ? 2 : 0) | ((tl.PowerState & 0x2) == 0 ? 4 : 0);
/*  99:    */       }
/* 100:162 */       return (tl.Powered ? 8 : 0) | ((tl.PowerState & 0x3) == 2 ? 1 : 0) | ((tl.PowerState & 0x6) == 0 ? 2 : 0) | ((tl.PowerState & 0x2) == 0 ? 4 : 0);
/* 101:    */     case 12: 
/* 102:168 */       return (tl.Powered ? 1 : 0) | (tl.PowerState == 0 ? 2 : 0);
/* 103:    */     case 13: 
/* 104:171 */       return tl.Powered ? 1 : 0;
/* 105:    */     case 14: 
/* 106:173 */       return 0;
/* 107:    */     case 15: 
/* 108:175 */       if (tl.Deadmap == 0)
/* 109:    */       {
/* 110:176 */         switch (tl.PowerState & 0x6)
/* 111:    */         {
/* 112:    */         case 0: 
/* 113:177 */           return tl.Powered ? 25 : 5;
/* 114:    */         case 2: 
/* 115:178 */           return tl.Powered ? 26 : 2;
/* 116:    */         case 4: 
/* 117:179 */           return tl.Powered ? 25 : 5;
/* 118:    */         }
/* 119:180 */         return tl.Powered ? 24 : 0;
/* 120:    */       }
/* 121:183 */       switch (tl.PowerState & 0x3)
/* 122:    */       {
/* 123:    */       case 0: 
/* 124:184 */         return tl.Powered ? 25 : 5;
/* 125:    */       case 1: 
/* 126:185 */         return tl.Powered ? 25 : 5;
/* 127:    */       case 2: 
/* 128:186 */         return tl.Powered ? 26 : 2;
/* 129:    */       }
/* 130:187 */       return tl.Powered ? 24 : 0;
/* 131:    */     }
/* 132:191 */     return 0;
/* 133:    */   }
/* 134:    */   
/* 135:    */   protected int getInvTorchState(int md)
/* 136:    */   {
/* 137:195 */     switch (md)
/* 138:    */     {
/* 139:    */     case 256: 
/* 140:    */     case 257: 
/* 141:    */     case 258: 
/* 142:199 */       return 1;
/* 143:    */     case 259: 
/* 144:    */     case 260: 
/* 145:202 */       return 7;
/* 146:    */     case 261: 
/* 147:204 */       return 12;
/* 148:    */     case 262: 
/* 149:206 */       return 4;
/* 150:    */     case 263: 
/* 151:    */     case 264: 
/* 152:    */     case 265: 
/* 153:210 */       return 1;
/* 154:    */     case 266: 
/* 155:212 */       return 2;
/* 156:    */     case 267: 
/* 157:214 */       return 12;
/* 158:    */     case 268: 
/* 159:216 */       return 1;
/* 160:    */     case 269: 
/* 161:218 */       return 0;
/* 162:    */     case 270: 
/* 163:220 */       return 0;
/* 164:    */     case 271: 
/* 165:222 */       return 5;
/* 166:    */     }
/* 167:224 */     return 0;
/* 168:    */   }
/* 169:    */   
/* 170:    */   protected RenderLogic.TorchPos[] getTorchVectors(TileLogic tl)
/* 171:    */   {
/* 172:228 */     int md = tl.getExtendedMetadata();
/* 173:230 */     switch (md)
/* 174:    */     {
/* 175:    */     case 0: 
/* 176:232 */       if (tl.Deadmap == 2) {
/* 177:232 */         return torchMapLatch2;
/* 178:    */       }
/* 179:233 */       if (tl.Deadmap == 3) {
/* 180:233 */         return torchMapLatch2b;
/* 181:    */       }
/* 182:234 */       return torchMapLatch;
/* 183:    */     case 1: 
/* 184:235 */       return torchMapNor;
/* 185:    */     case 2: 
/* 186:236 */       return torchMapOr;
/* 187:    */     case 3: 
/* 188:237 */       return torchMapNand;
/* 189:    */     case 4: 
/* 190:238 */       return torchMapAnd;
/* 191:    */     case 5: 
/* 192:239 */       return torchMapXnor;
/* 193:    */     case 6: 
/* 194:240 */       return torchMapXor;
/* 195:    */     case 7: 
/* 196:241 */       return torchMapPulse;
/* 197:    */     case 8: 
/* 198:242 */       return torchMapToggle;
/* 199:    */     case 9: 
/* 200:243 */       return torchMapNot;
/* 201:    */     case 10: 
/* 202:244 */       return torchMapBuffer;
/* 203:    */     case 11: 
/* 204:246 */       if (tl.Deadmap == 0) {
/* 205:246 */         return torchMapMux;
/* 206:    */       }
/* 207:247 */       return torchMapMux2;
/* 208:    */     case 12: 
/* 209:249 */       return new RenderLogic.TorchPos[] { new RenderLogic.TorchPos(0.313D, -0.25D, -0.125D, 0.7D), new RenderLogic.TorchPos(-0.25D + tl.Deadmap * 0.063D, -0.25D, 0.25D, 0.7D) };
/* 210:    */     case 13: 
/* 211:253 */       return torchMapSync;
/* 212:    */     case 14: 
/* 213:254 */       return null;
/* 214:    */     case 15: 
/* 215:256 */       if (tl.Deadmap == 0) {
/* 216:256 */         return torchMapDLatch;
/* 217:    */       }
/* 218:257 */       return torchMapDLatch2;
/* 219:    */     }
/* 220:259 */     return null;
/* 221:    */   }
/* 222:    */   
/* 223:    */   protected RenderLogic.TorchPos[] getInvTorchVectors(int md)
/* 224:    */   {
/* 225:263 */     switch (md)
/* 226:    */     {
/* 227:    */     case 256: 
/* 228:264 */       return torchMapLatch;
/* 229:    */     case 257: 
/* 230:265 */       return torchMapNor;
/* 231:    */     case 258: 
/* 232:266 */       return torchMapOr;
/* 233:    */     case 259: 
/* 234:267 */       return torchMapNand;
/* 235:    */     case 260: 
/* 236:268 */       return torchMapAnd;
/* 237:    */     case 261: 
/* 238:269 */       return torchMapXnor;
/* 239:    */     case 262: 
/* 240:270 */       return torchMapXor;
/* 241:    */     case 263: 
/* 242:271 */       return torchMapPulse;
/* 243:    */     case 264: 
/* 244:272 */       return torchMapToggle;
/* 245:    */     case 265: 
/* 246:273 */       return torchMapNot;
/* 247:    */     case 266: 
/* 248:274 */       return torchMapBuffer;
/* 249:    */     case 267: 
/* 250:275 */       return torchMapMux;
/* 251:    */     case 268: 
/* 252:276 */       return torchMapRepS;
/* 253:    */     case 269: 
/* 254:277 */       return torchMapSync;
/* 255:    */     case 270: 
/* 256:278 */       return null;
/* 257:    */     case 271: 
/* 258:279 */       return torchMapDLatch;
/* 259:    */     }
/* 260:281 */     return null;
/* 261:    */   }
/* 262:    */   
/* 263:    */   protected void renderWorldPart(ym iba, TileLogic tl)
/* 264:    */   {
/* 265:285 */     int md = tl.getExtendedMetadata();
/* 266:    */     int tx;
/* 267:    */     int tmp;
/* 268:    */     int tx;
/* 269:    */     int tx;
/* 270:    */     int tx;
/* 271:288 */     switch (md)
/* 272:    */     {
/* 273:    */     case 0: 
/* 274:290 */       if (tl.Deadmap < 2)
/* 275:    */       {
/* 276:291 */         int tx = ((tl.PowerState & 0x1) > 0 ? 1 : 0) | ((tl.PowerState & 0x4) > 0 ? 2 : 0);
/* 277:293 */         if ((!tl.Disabled) || (tl.Active)) {
/* 278:294 */           tx |= (tl.Powered ? 2 : 1);
/* 279:    */         }
/* 280:296 */         tx = 24 + (tl.Deadmap == 1 ? 4 : 0) + tx;
/* 281:    */       }
/* 282:    */       else
/* 283:    */       {
/* 284:298 */         tx = 96 + (tl.Deadmap == 3 ? 16 : 0) + tl.PowerState;
/* 285:    */       }
/* 286:301 */       break;
/* 287:    */     case 1: 
/* 288:303 */       tx = texIdxNor[tl.Deadmap] + PowerLib.cutBits(tl.PowerState | (tl.Powered ? 8 : 0), tl.Deadmap);
/* 289:    */       
/* 290:    */ 
/* 291:306 */       break;
/* 292:    */     case 2: 
/* 293:308 */       tx = texIdxOr[tl.Deadmap] + PowerLib.cutBits(tl.PowerState, tl.Deadmap);
/* 294:    */       
/* 295:310 */       break;
/* 296:    */     case 3: 
/* 297:312 */       tx = texIdxNand[tl.Deadmap] + PowerLib.cutBits(tl.PowerState | (tl.Powered ? 8 : 0), tl.Deadmap);
/* 298:    */       
/* 299:    */ 
/* 300:315 */       break;
/* 301:    */     case 4: 
/* 302:317 */       tx = texIdxAnd[tl.Deadmap] + PowerLib.cutBits(tl.PowerState, tl.Deadmap);
/* 303:    */       
/* 304:319 */       break;
/* 305:    */     case 5: 
/* 306:321 */       tx = 128 + (tl.PowerState & 0x1) + ((tl.PowerState & 0x4) >> 1);
/* 307:322 */       break;
/* 308:    */     case 6: 
/* 309:324 */       tx = 132 + ((tl.Powered ? 4 : 0) | (tl.PowerState & 0xC) >> 1 | tl.PowerState & 0x1);
/* 310:    */       
/* 311:326 */       break;
/* 312:    */     case 7: 
/* 313:328 */       tx = 5;
/* 314:329 */       if ((tl.Powered) && (!tl.Active)) {
/* 315:329 */         tx = 6;
/* 316:330 */       } else if ((!tl.Powered) && (tl.Active)) {
/* 317:330 */         tx = 7;
/* 318:    */       }
/* 319:    */       break;
/* 320:    */     case 8: 
/* 321:333 */       tx = 140 + (tl.PowerState & 0x1) + (tl.PowerState >> 1 & 0x2);
/* 322:334 */       break;
/* 323:    */     case 9: 
/* 324:336 */       if (tl.Deadmap == 0)
/* 325:    */       {
/* 326:337 */         tx = 432 + (tl.PowerState | (tl.Powered ? 13 : 0));
/* 327:    */       }
/* 328:    */       else
/* 329:    */       {
/* 330:339 */         tmp = PowerLib.cutBits(tl.Deadmap, 2);
/* 331:    */         int tx;
/* 332:340 */         if (tl.Powered) {
/* 333:341 */           tx = 480 + (tmp - 1 << 1) + ((tl.PowerState & 0x2) >> 1);
/* 334:    */         } else {
/* 335:344 */           tx = texIdxNot[tmp] + PowerLib.cutBits(tl.PowerState, tl.Deadmap);
/* 336:    */         }
/* 337:    */       }
/* 338:349 */       break;
/* 339:    */     case 10: 
/* 340:351 */       if (tl.Deadmap == 0)
/* 341:    */       {
/* 342:352 */         tx = 496 + (tl.PowerState | (tl.Powered ? 5 : 0));
/* 343:    */       }
/* 344:    */       else
/* 345:    */       {
/* 346:354 */         tmp = PowerLib.cutBits(tl.Deadmap, 2);
/* 347:    */         int tx;
/* 348:355 */         if (tl.Powered) {
/* 349:356 */           tx = 256 + (tmp << 1) + ((tl.PowerState & 0x2) >> 1);
/* 350:    */         } else {
/* 351:359 */           tx = texIdxBuf[tmp] + PowerLib.cutBits(tl.PowerState, tl.Deadmap);
/* 352:    */         }
/* 353:    */       }
/* 354:364 */       break;
/* 355:    */     case 11: 
/* 356:366 */       tx = '' + (tl.Deadmap > 0 ? 8 : 0) + tl.PowerState;
/* 357:367 */       break;
/* 358:    */     case 12: 
/* 359:369 */       tx = 492 + (tl.PowerState >> 1) + (tl.Powered ? 0 : 2);
/* 360:370 */       break;
/* 361:    */     case 13: 
/* 362:372 */       tx = 160 + tl.PowerState + (tl.Active ? 8 : 0) + (tl.Disabled ? 16 : 0);
/* 363:    */       
/* 364:374 */       break;
/* 365:    */     case 14: 
/* 366:376 */       tx = 192 + (tl.PowerState | (tl.Active ? 1 : 0) | (tl.Powered ? 4 : 0) | (tl.Disabled ? 8 : 0));
/* 367:    */       
/* 368:378 */       break;
/* 369:    */     case 15: 
/* 370:380 */       if (tl.Deadmap > 0) {
/* 371:381 */         tx = 216 + tl.PowerState + (tl.Powered ? 4 : 0);
/* 372:    */       } else {
/* 373:383 */         tx = 208 + (tl.PowerState >> 1) + (tl.Powered ? 4 : 0);
/* 374:    */       }
/* 375:385 */       break;
/* 376:    */     case 16: 
/* 377:387 */       tx = 513 + ((tl.Powered) || (tl.PowerState > 0) ? 1 : 0);
/* 378:388 */       break;
/* 379:    */     default: 
/* 380:390 */       return;
/* 381:    */     }
/* 382:392 */     renderWafer(tx);
/* 383:394 */     if (md == 8)
/* 384:    */     {
/* 385:395 */       this.context.setTexFlags(44);
/* 386:396 */       this.context.setSize(0.25D, 0.0D, 0.5550000071525574D, 0.75D, 0.300000011920929D, 0.8050000071525574D);
/* 387:397 */       this.context.setTex(amq.z.cl);
/* 388:398 */       this.context.calcBounds();
/* 389:399 */       this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 390:400 */       this.context.renderFaces(62);
/* 391:    */       
/* 392:402 */       Vector3 pos = new Vector3(0.0D, -0.3D, 0.18D);
/* 393:403 */       Quat q = MathLib.orientQuat(tl.Rotation >> 2, tl.Rotation & 0x3);
/* 394:404 */       q.rotate(pos);
/* 395:405 */       pos.add(this.context.globalOrigin);
/* 396:406 */       q.rightMultiply(leverPositions[0]);
/* 397:407 */       RenderLib.renderSpecialLever(pos, q, amq.aM.cl);
/* 398:    */     }
/* 399:409 */     else if (md == 13)
/* 400:    */     {
/* 401:410 */       renderChip(-0.125D, 0.0D, -0.1875D, tl.Disabled ? 2 : 1);
/* 402:411 */       renderChip(-0.125D, 0.0D, 0.1875D, tl.Active ? 2 : 1);
/* 403:    */     }
/* 404:412 */     else if (md == 14)
/* 405:    */     {
/* 406:413 */       renderChip(-0.25D, 0.0D, -0.25D, tl.Disabled ? 9 : 8);
/* 407:414 */       renderChip(-0.25D, 0.0D, 0.25D, tl.Active ? 9 : 8);
/* 408:415 */       renderChip(0.125D, 0.0D, 0.0D, tl.Powered ? 9 : 8);
/* 409:    */     }
/* 410:416 */     else if (md == 16)
/* 411:    */     {
/* 412:417 */       this.context.bindTexture("/eloraam/logic/sensor1.png");
/* 413:418 */       this.context.setTexFlags(64);
/* 414:419 */       tx = 16 + tl.Deadmap;
/* 415:420 */       this.context.setTex(tx, tx, 20, 20, tx, tx);
/* 416:421 */       this.context.renderBox(62, 0.125D, 0.0D, 0.1879999935626984D, 0.625D, 0.1879999935626984D, 0.8130000233650208D);
/* 417:422 */       this.context.unbindTexture();
/* 418:    */     }
/* 419:    */   }
/* 420:    */   
/* 421:426 */   private static final int[] texIdxNor = { 272, 288, 296, 312, 304, 316, 320 };
/* 422:429 */   private static final int[] texIdxOr = { 376, 384, 388, 416, 392, 418, 420 };
/* 423:432 */   private static final int[] texIdxNand = { 336, 352, 360, 324, 368, 328, 332 };
/* 424:435 */   private static final int[] texIdxAnd = { 400, 408, 412, 422, 396, 424, 426 };
/* 425:438 */   private static final int[] texIdxNot = { 432, 448, 456, 472, 464, 476, 428 };
/* 426:441 */   private static final int[] texIdxBuf = { 496, 504, 508, 257 };
/* 427:445 */   private static Quat[] leverPositions = new Quat[2];
/* 428:    */   
/* 429:    */   static
/* 430:    */   {
/* 431:447 */     leverPositions[0] = Quat.aroundAxis(1.0D, 0.0D, 0.0D, 0.8639379797371932D);
/* 432:448 */     leverPositions[1] = Quat.aroundAxis(1.0D, 0.0D, 0.0D, -0.8639379797371932D);
/* 433:449 */     leverPositions[0].multiply(MathLib.orientQuat(0, 3));
/* 434:450 */     leverPositions[1].multiply(MathLib.orientQuat(0, 3));
/* 435:    */   }
/* 436:    */   
/* 437:    */   protected void renderInvPart(int md)
/* 438:    */   {
/* 439:454 */     switch (md)
/* 440:    */     {
/* 441:    */     case 256: 
/* 442:456 */       renderInvWafer(25);
/* 443:457 */       break;
/* 444:    */     case 257: 
/* 445:459 */       renderInvWafer(280);
/* 446:460 */       break;
/* 447:    */     case 258: 
/* 448:462 */       renderInvWafer(384);
/* 449:463 */       break;
/* 450:    */     case 259: 
/* 451:465 */       renderInvWafer(344);
/* 452:466 */       break;
/* 453:    */     case 260: 
/* 454:468 */       renderInvWafer(400);
/* 455:469 */       break;
/* 456:    */     case 261: 
/* 457:471 */       renderInvWafer(128);
/* 458:472 */       break;
/* 459:    */     case 262: 
/* 460:474 */       renderInvWafer(132);
/* 461:475 */       break;
/* 462:    */     case 263: 
/* 463:477 */       renderInvWafer(5);
/* 464:478 */       break;
/* 465:    */     case 264: 
/* 466:480 */       renderInvWafer(140);
/* 467:481 */       break;
/* 468:    */     case 265: 
/* 469:483 */       renderInvWafer(440);
/* 470:484 */       break;
/* 471:    */     case 266: 
/* 472:486 */       renderInvWafer(496);
/* 473:487 */       break;
/* 474:    */     case 267: 
/* 475:489 */       renderInvWafer(144);
/* 476:490 */       break;
/* 477:    */     case 268: 
/* 478:492 */       renderInvWafer(493);
/* 479:493 */       break;
/* 480:    */     case 269: 
/* 481:495 */       renderInvWafer(160);
/* 482:496 */       break;
/* 483:    */     case 270: 
/* 484:498 */       renderInvWafer(192);
/* 485:499 */       break;
/* 486:    */     case 271: 
/* 487:501 */       renderInvWafer(208);
/* 488:502 */       break;
/* 489:    */     case 272: 
/* 490:504 */       renderInvWafer(513);
/* 491:    */     }
/* 492:508 */     if (md == 264)
/* 493:    */     {
/* 494:509 */       baz tessellator = baz.a;
/* 495:510 */       tessellator.b();
/* 496:511 */       this.context.useNormal = true;
/* 497:    */       
/* 498:513 */       this.context.setTexFlags(44);
/* 499:514 */       this.context.setSize(0.25D, 0.0D, 0.5550000071525574D, 0.75D, 0.300000011920929D, 0.8050000071525574D);
/* 500:515 */       this.context.setTex(amq.z.cl);
/* 501:516 */       this.context.calcBounds();
/* 502:517 */       this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 503:518 */       this.context.renderFaces(62);
/* 504:    */       
/* 505:520 */       this.context.useNormal = false;
/* 506:521 */       tessellator.a();
/* 507:    */       
/* 508:523 */       tessellator.b();
/* 509:524 */       tessellator.b(0.0F, 0.0F, 1.0F);
/* 510:525 */       Vector3 pos = new Vector3(0.0D, -0.3D, 0.18D);
/* 511:526 */       Quat q = MathLib.orientQuat(0, 3);
/* 512:527 */       q.rotate(pos);pos.add(this.context.globalOrigin);
/* 513:528 */       q.rightMultiply(leverPositions[0]);
/* 514:529 */       RenderLib.renderSpecialLever(pos, q, amq.aM.cl);
/* 515:    */       
/* 516:531 */       tessellator.a();
/* 517:    */     }
/* 518:532 */     else if (md == 269)
/* 519:    */     {
/* 520:533 */       baz tessellator = baz.a;
/* 521:534 */       tessellator.b();
/* 522:535 */       this.context.useNormal = true;
/* 523:536 */       renderChip(-0.125D, 0.0D, -0.1875D, 2);
/* 524:537 */       renderChip(-0.125D, 0.0D, 0.1875D, 2);
/* 525:538 */       this.context.useNormal = false;
/* 526:539 */       tessellator.a();
/* 527:    */     }
/* 528:540 */     else if (md == 270)
/* 529:    */     {
/* 530:541 */       baz tessellator = baz.a;
/* 531:542 */       tessellator.b();
/* 532:543 */       this.context.useNormal = true;
/* 533:544 */       renderChip(-0.25D, 0.0D, -0.25D, 8);
/* 534:545 */       renderChip(-0.25D, 0.0D, 0.25D, 8);
/* 535:546 */       renderChip(0.125D, 0.0D, 0.0D, 8);
/* 536:547 */       this.context.useNormal = false;
/* 537:548 */       tessellator.a();
/* 538:    */     }
/* 539:549 */     else if (md == 272)
/* 540:    */     {
/* 541:550 */       baz tessellator = baz.a;
/* 542:551 */       tessellator.b();
/* 543:552 */       this.context.useNormal = true;
/* 544:553 */       this.context.bindTexture("/eloraam/logic/sensor1.png");
/* 545:554 */       this.context.setTex(16, 16, 20, 20, 16, 16);
/* 546:555 */       this.context.setTexFlags(64);
/* 547:556 */       this.context.renderBox(62, 0.125D, 0.0D, 0.1879999935626984D, 0.625D, 0.1879999935626984D, 0.8130000233650208D);
/* 548:557 */       this.context.unbindTexture();
/* 549:558 */       this.context.useNormal = false;
/* 550:559 */       tessellator.a();
/* 551:    */     }
/* 552:    */   }
/* 553:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.RenderLogicSimple
 * JD-Core Version:    0.7.0.1
 */