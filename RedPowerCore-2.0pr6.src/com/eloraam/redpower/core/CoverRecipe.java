/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.base.ItemHandsaw;
/*   5:    */ import ry;
/*   6:    */ import ur;
/*   7:    */ import wp;
/*   8:    */ import yc;
/*   9:    */ 
/*  10:    */ public class CoverRecipe
/*  11:    */   implements wp
/*  12:    */ {
/*  13:    */   private static ur newCover(int num, int type, int mat)
/*  14:    */   {
/*  15: 11 */     return new ur(CoverLib.blockCoverPlate, num, type << 8 | mat);
/*  16:    */   }
/*  17:    */   
/*  18:    */   private ur getSawRecipe(ry inv, ur saw, int sawpos, ur mat, int matpos)
/*  19:    */   {
/*  20: 17 */     int sp1 = sawpos & 0xF;int sp2 = sawpos >> 4;
/*  21: 18 */     int mp1 = matpos & 0xF;int mp2 = matpos >> 4;
/*  22:    */     
/*  23: 20 */     int mn = -1;
/*  24: 21 */     int dmg = -1;
/*  25: 23 */     if (mat.c == CoverLib.blockCoverPlate.cm)
/*  26:    */     {
/*  27: 24 */       dmg = mat.j();
/*  28: 25 */       mn = dmg & 0xFF;dmg >>= 8;
/*  29:    */     }
/*  30:    */     else
/*  31:    */     {
/*  32: 27 */       Integer mni = CoverLib.getMaterial(mat);
/*  33: 28 */       if (mni == null) {
/*  34: 28 */         return null;
/*  35:    */       }
/*  36: 29 */       mn = mni.intValue();
/*  37:    */     }
/*  38: 31 */     ItemHandsaw ihs = (ItemHandsaw)saw.b();
/*  39: 32 */     if (ihs.getSharpness() < CoverLib.getHardness(mn)) {
/*  40: 33 */       return null;
/*  41:    */     }
/*  42: 35 */     if ((sp1 == mp1) && ((sp2 == mp2 + 1) || (sp2 == mp2 - 1)))
/*  43:    */     {
/*  44: 37 */       switch (dmg)
/*  45:    */       {
/*  46:    */       case -1: 
/*  47: 38 */         return newCover(2, 17, mn);
/*  48:    */       case 17: 
/*  49: 39 */         return newCover(2, 16, mn);
/*  50:    */       case 16: 
/*  51: 40 */         return newCover(2, 0, mn);
/*  52:    */       case 29: 
/*  53: 41 */         return newCover(2, 27, mn);
/*  54:    */       case 26: 
/*  55: 43 */         return newCover(2, 25, mn);
/*  56:    */       case 25: 
/*  57: 44 */         return newCover(2, 24, mn);
/*  58:    */       case 33: 
/*  59: 45 */         return newCover(2, 31, mn);
/*  60:    */       }
/*  61: 46 */       return null;
/*  62:    */     }
/*  63: 49 */     if ((sp2 == mp2) && ((sp1 == mp1 + 1) || (sp1 == mp1 - 1)))
/*  64:    */     {
/*  65: 50 */       switch (dmg)
/*  66:    */       {
/*  67:    */       case 17: 
/*  68: 52 */         return newCover(2, 23, mn);
/*  69:    */       case 16: 
/*  70: 53 */         return newCover(2, 22, mn);
/*  71:    */       case 0: 
/*  72: 54 */         return newCover(2, 21, mn);
/*  73:    */       case 23: 
/*  74: 56 */         return newCover(2, 20, mn);
/*  75:    */       case 22: 
/*  76: 57 */         return newCover(2, 19, mn);
/*  77:    */       case 21: 
/*  78: 58 */         return newCover(2, 18, mn);
/*  79:    */       case 27: 
/*  80: 60 */         return newCover(2, 39, mn);
/*  81:    */       case 28: 
/*  82: 61 */         return newCover(2, 40, mn);
/*  83:    */       case 29: 
/*  84: 62 */         return newCover(2, 41, mn);
/*  85:    */       case 30: 
/*  86: 63 */         return newCover(2, 42, mn);
/*  87:    */       case 39: 
/*  88: 65 */         return newCover(2, 35, mn);
/*  89:    */       case 40: 
/*  90: 66 */         return newCover(2, 36, mn);
/*  91:    */       case 41: 
/*  92: 67 */         return newCover(2, 37, mn);
/*  93:    */       case 42: 
/*  94: 68 */         return newCover(2, 38, mn);
/*  95:    */       }
/*  96: 70 */       return null;
/*  97:    */     }
/*  98: 73 */     return null;
/*  99:    */   }
/* 100:    */   
/* 101:    */   private ur getColumnRecipe(ur mat)
/* 102:    */   {
/* 103: 77 */     if (mat.c != CoverLib.blockCoverPlate.cm) {
/* 104: 78 */       return null;
/* 105:    */     }
/* 106: 79 */     int dmg = mat.j();
/* 107: 80 */     int mn = dmg & 0xFF;dmg >>= 8;
/* 108: 81 */     switch (dmg)
/* 109:    */     {
/* 110:    */     case 22: 
/* 111: 82 */       return newCover(1, 43, mn);
/* 112:    */     case 43: 
/* 113: 83 */       return newCover(1, 22, mn);
/* 114:    */     case 23: 
/* 115: 85 */       return newCover(1, 44, mn);
/* 116:    */     case 44: 
/* 117: 86 */       return newCover(1, 23, mn);
/* 118:    */     case 41: 
/* 119: 88 */       return newCover(1, 45, mn);
/* 120:    */     case 45: 
/* 121: 89 */       return newCover(1, 41, mn);
/* 122:    */     }
/* 123: 91 */     return null;
/* 124:    */   }
/* 125:    */   
/* 126:    */   private ur getMergeRecipe(int mn, int tth, int ic)
/* 127:    */   {
/* 128: 95 */     int mc = mn >> 20;mn &= 0xFF;
/* 129: 97 */     switch (mc)
/* 130:    */     {
/* 131:    */     case 0: 
/* 132: 99 */       switch (tth)
/* 133:    */       {
/* 134:    */       case 2: 
/* 135:100 */         return newCover(1, 16, mn);
/* 136:    */       case 3: 
/* 137:101 */         return newCover(1, 27, mn);
/* 138:    */       case 4: 
/* 139:102 */         return newCover(1, 17, mn);
/* 140:    */       case 5: 
/* 141:103 */         return newCover(1, 28, mn);
/* 142:    */       case 6: 
/* 143:104 */         return newCover(1, 29, mn);
/* 144:    */       case 7: 
/* 145:105 */         return newCover(1, 30, mn);
/* 146:    */       case 8: 
/* 147:106 */         return CoverLib.getItemStack(mn);
/* 148:    */       }
/* 149:108 */       break;
/* 150:    */     case 1: 
/* 151:110 */       switch (tth)
/* 152:    */       {
/* 153:    */       case 2: 
/* 154:111 */         return newCover(1, 25, mn);
/* 155:    */       case 3: 
/* 156:112 */         return newCover(1, 31, mn);
/* 157:    */       case 4: 
/* 158:113 */         return newCover(1, 26, mn);
/* 159:    */       case 5: 
/* 160:114 */         return newCover(1, 32, mn);
/* 161:    */       case 6: 
/* 162:115 */         return newCover(1, 33, mn);
/* 163:    */       case 7: 
/* 164:116 */         return newCover(1, 34, mn);
/* 165:    */       case 8: 
/* 166:117 */         return CoverLib.getItemStack(mn);
/* 167:    */       }
/* 168:119 */       break;
/* 169:    */     case 16: 
/* 170:121 */       switch (tth)
/* 171:    */       {
/* 172:    */       case 2: 
/* 173:122 */         return newCover(1, 0, mn);
/* 174:    */       case 4: 
/* 175:123 */         return newCover(1, 16, mn);
/* 176:    */       case 8: 
/* 177:124 */         return newCover(1, 17, mn);
/* 178:    */       case 16: 
/* 179:125 */         return CoverLib.getItemStack(mn);
/* 180:    */       }
/* 181:127 */       break;
/* 182:    */     case 32: 
/* 183:129 */       if (ic == 2) {
/* 184:130 */         switch (tth)
/* 185:    */         {
/* 186:    */         case 2: 
/* 187:131 */           return newCover(1, 21, mn);
/* 188:    */         case 4: 
/* 189:132 */           return newCover(1, 22, mn);
/* 190:    */         case 8: 
/* 191:133 */           return newCover(1, 23, mn);
/* 192:    */         }
/* 193:    */       } else {
/* 194:137 */         switch (tth)
/* 195:    */         {
/* 196:    */         case 4: 
/* 197:138 */           return newCover(1, 0, mn);
/* 198:    */         case 8: 
/* 199:139 */           return newCover(1, 16, mn);
/* 200:    */         case 16: 
/* 201:140 */           return newCover(1, 17, mn);
/* 202:    */         case 32: 
/* 203:141 */           return CoverLib.getItemStack(mn);
/* 204:    */         }
/* 205:    */       }
/* 206:    */       break;
/* 207:    */     }
/* 208:147 */     return null;
/* 209:    */   }
/* 210:    */   
/* 211:    */   private ur getHollowRecipe(int mn)
/* 212:    */   {
/* 213:151 */     int mc = mn >> 8 & 0xFF;mn &= 0xFF;
/* 214:152 */     switch (mc)
/* 215:    */     {
/* 216:    */     case 0: 
/* 217:153 */       return newCover(8, 24, mn);
/* 218:    */     case 16: 
/* 219:154 */       return newCover(8, 25, mn);
/* 220:    */     case 17: 
/* 221:155 */       return newCover(8, 26, mn);
/* 222:    */     case 27: 
/* 223:157 */       return newCover(8, 31, mn);
/* 224:    */     case 28: 
/* 225:158 */       return newCover(8, 32, mn);
/* 226:    */     case 29: 
/* 227:159 */       return newCover(8, 33, mn);
/* 228:    */     case 30: 
/* 229:160 */       return newCover(8, 34, mn);
/* 230:    */     }
/* 231:162 */     return null;
/* 232:    */   }
/* 233:    */   
/* 234:    */   private int getMicroClass(ur ist)
/* 235:    */   {
/* 236:166 */     if (ist.c != CoverLib.blockCoverPlate.cm) {
/* 237:167 */       return -1;
/* 238:    */     }
/* 239:168 */     int dmg = ist.j();
/* 240:169 */     return CoverLib.damageToCoverData(dmg);
/* 241:    */   }
/* 242:    */   
/* 243:    */   private ur findResult(ry inv)
/* 244:    */   {
/* 245:173 */     ur saw = null;
/* 246:174 */     ur mat = null;
/* 247:175 */     boolean bad = false;
/* 248:176 */     boolean allmicro = true;
/* 249:177 */     boolean strict = true;
/* 250:178 */     int sp = 0;int mp = 0;
/* 251:179 */     int mn = -1;
/* 252:180 */     int tth = 0;
/* 253:181 */     int ic = 0;
/* 254:183 */     for (int i = 0; i < 3; i++) {
/* 255:184 */       for (int j = 0; j < 3; j++)
/* 256:    */       {
/* 257:185 */         ur ist = inv.b(i, j);
/* 258:186 */         if (ist != null) {
/* 259:187 */           if ((ist.b() instanceof ItemHandsaw))
/* 260:    */           {
/* 261:188 */             if (saw != null)
/* 262:    */             {
/* 263:189 */               bad = true;
/* 264:    */             }
/* 265:    */             else
/* 266:    */             {
/* 267:192 */               saw = ist;sp = i + j * 16;
/* 268:    */             }
/* 269:    */           }
/* 270:195 */           else if (mat == null)
/* 271:    */           {
/* 272:196 */             mat = ist;mp = i + j * 16;
/* 273:197 */             mn = getMicroClass(ist);
/* 274:198 */             if (mn >= 0) {
/* 275:199 */               tth += (mn >> 16 & 0xF);
/* 276:    */             } else {
/* 277:200 */               allmicro = false;
/* 278:    */             }
/* 279:201 */             ic = 1;
/* 280:    */           }
/* 281:    */           else
/* 282:    */           {
/* 283:204 */             bad = true;
/* 284:205 */             if (allmicro)
/* 285:    */             {
/* 286:207 */               int t = getMicroClass(ist);
/* 287:208 */               if (((t ^ mn) & 0xFFF000FF) != 0)
/* 288:    */               {
/* 289:209 */                 allmicro = false;
/* 290:    */               }
/* 291:    */               else
/* 292:    */               {
/* 293:211 */                 if (t != mn) {
/* 294:211 */                   strict = false;
/* 295:    */                 }
/* 296:212 */                 tth += (t >> 16 & 0xF);
/* 297:213 */                 ic++;
/* 298:    */               }
/* 299:    */             }
/* 300:    */           }
/* 301:    */         }
/* 302:    */       }
/* 303:    */     }
/* 304:217 */     if ((saw != null) && (mat != null) && (!bad)) {
/* 305:218 */       return getSawRecipe(inv, saw, sp, mat, mp);
/* 306:    */     }
/* 307:220 */     if ((saw == null) && (mat != null) && (!bad)) {
/* 308:221 */       return getColumnRecipe(mat);
/* 309:    */     }
/* 310:223 */     if ((allmicro) && (bad) && (saw == null))
/* 311:    */     {
/* 312:224 */       if ((ic == 8) && (strict) && 
/* 313:225 */         (inv.b(1, 1) == null) && (mn >> 20 == 0)) {
/* 314:227 */         return getHollowRecipe(mn);
/* 315:    */       }
/* 316:229 */       return getMergeRecipe(mn, tth, ic);
/* 317:    */     }
/* 318:232 */     return null;
/* 319:    */   }
/* 320:    */   
/* 321:    */   public boolean a(ry inv, yc world)
/* 322:    */   {
/* 323:236 */     return findResult(inv) != null;
/* 324:    */   }
/* 325:    */   
/* 326:    */   public int a()
/* 327:    */   {
/* 328:240 */     return 9;
/* 329:    */   }
/* 330:    */   
/* 331:    */   public ur a(ry inv)
/* 332:    */   {
/* 333:244 */     return findResult(inv).l();
/* 334:    */   }
/* 335:    */   
/* 336:    */   public ur b()
/* 337:    */   {
/* 338:249 */     return new ur(CoverLib.blockCoverPlate, 1, 0);
/* 339:    */   }
/* 340:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CoverRecipe
 * JD-Core Version:    0.7.0.1
 */