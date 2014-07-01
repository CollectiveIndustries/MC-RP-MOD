/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import lq;
/*   6:    */ import net.minecraftforge.common.ForgeHooks;
/*   7:    */ import qx;
/*   8:    */ import ur;
/*   9:    */ 
/*  10:    */ public abstract class TileCoverable
/*  11:    */   extends TileMultipart
/*  12:    */   implements ICoverable, IMultipart
/*  13:    */ {
/*  14:    */   public abstract boolean canAddCover(int paramInt1, int paramInt2);
/*  15:    */   
/*  16:    */   public abstract boolean tryAddCover(int paramInt1, int paramInt2);
/*  17:    */   
/*  18:    */   public abstract int tryRemoveCover(int paramInt);
/*  19:    */   
/*  20:    */   public abstract int getCover(int paramInt);
/*  21:    */   
/*  22:    */   public abstract int getCoverMask();
/*  23:    */   
/*  24:    */   public boolean isSideSolid(int side)
/*  25:    */   {
/*  26: 27 */     int cm = getCoverMask();
/*  27: 28 */     return (cm & 1 << side) > 0;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public boolean isSideNormal(int side)
/*  31:    */   {
/*  32: 32 */     int cm = getCoverMask();
/*  33: 33 */     if ((cm & 1 << side) == 0) {
/*  34: 34 */       return false;
/*  35:    */     }
/*  36: 35 */     int c = getCover(side);
/*  37: 36 */     int n = c >> 8;
/*  38: 37 */     return (!CoverLib.isTransparent(c & 0xFF)) && ((n < 3) || ((n >= 6) && (n <= 9)));
/*  39:    */   }
/*  40:    */   
/*  41:    */   public void addCoverableHarvestContents(ArrayList ist)
/*  42:    */   {
/*  43: 44 */     if (CoverLib.blockCoverPlate == null) {
/*  44: 44 */       return;
/*  45:    */     }
/*  46: 47 */     for (int i = 0; i < 29; i++)
/*  47:    */     {
/*  48: 48 */       int j = getCover(i);
/*  49: 49 */       if (j >= 0) {
/*  50: 51 */         ist.add(CoverLib.convertCoverPlate(i, j));
/*  51:    */       }
/*  52:    */     }
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void addHarvestContents(ArrayList ist)
/*  56:    */   {
/*  57: 56 */     addCoverableHarvestContents(ist);
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void onHarvestPart(qx player, int part)
/*  61:    */   {
/*  62: 60 */     int i = tryRemoveCover(part);
/*  63: 61 */     if (i < 0) {
/*  64: 61 */       return;
/*  65:    */     }
/*  66: 62 */     dropCover(part, i);
/*  67: 63 */     if (blockEmpty()) {
/*  68: 63 */       deleteBlock();
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   public float getPartStrength(qx player, int part)
/*  73:    */   {
/*  74: 74 */     int i = getCover(part);
/*  75: 75 */     if (i < 0) {
/*  76: 75 */       return 0.0F;
/*  77:    */     }
/*  78: 76 */     i &= 0xFF;
/*  79:    */     
/*  80: 78 */     float hv = CoverLib.getMiningHardness(i);
/*  81: 79 */     if (hv < 0.0F) {
/*  82: 79 */       return 0.0F;
/*  83:    */     }
/*  84: 81 */     ur ist = CoverLib.getItemStack(i);
/*  85: 82 */     amq bl = amq.p[ist.c];
/*  86: 83 */     int md = ist.j();
/*  87: 84 */     if (!ForgeHooks.canHarvestBlock(bl, player, md)) {
/*  88: 85 */       return 1.0F / hv / 100.0F;
/*  89:    */     }
/*  90: 87 */     return player.getCurrentPlayerStrVsBlock(bl, md) / hv / 30.0F;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void setPartBounds(BlockMultipart bl, int part)
/*  94:    */   {
/*  95: 93 */     int i = getCover(part);
/*  96: 94 */     float th = CoverLib.getThickness(part, i);
/*  97: 95 */     switch (part)
/*  98:    */     {
/*  99:    */     case 0: 
/* 100: 98 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, th, 1.0F);
/* 101: 99 */       break;
/* 102:    */     case 1: 
/* 103:101 */       bl.a(0.0F, 1.0F - th, 0.0F, 1.0F, 1.0F, 1.0F);
/* 104:102 */       break;
/* 105:    */     case 2: 
/* 106:104 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, th);
/* 107:105 */       break;
/* 108:    */     case 3: 
/* 109:107 */       bl.a(0.0F, 0.0F, 1.0F - th, 1.0F, 1.0F, 1.0F);
/* 110:108 */       break;
/* 111:    */     case 4: 
/* 112:110 */       bl.a(0.0F, 0.0F, 0.0F, th, 1.0F, 1.0F);
/* 113:111 */       break;
/* 114:    */     case 5: 
/* 115:113 */       bl.a(1.0F - th, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 116:114 */       break;
/* 117:    */     case 6: 
/* 118:118 */       bl.a(0.0F, 0.0F, 0.0F, th, th, th);
/* 119:119 */       break;
/* 120:    */     case 7: 
/* 121:121 */       bl.a(0.0F, 0.0F, 1.0F - th, th, th, 1.0F);
/* 122:122 */       break;
/* 123:    */     case 8: 
/* 124:124 */       bl.a(1.0F - th, 0.0F, 0.0F, 1.0F, th, th);
/* 125:125 */       break;
/* 126:    */     case 9: 
/* 127:127 */       bl.a(1.0F - th, 0.0F, 1.0F - th, 1.0F, th, 1.0F);
/* 128:128 */       break;
/* 129:    */     case 10: 
/* 130:130 */       bl.a(0.0F, 1.0F - th, 0.0F, th, 1.0F, th);
/* 131:131 */       break;
/* 132:    */     case 11: 
/* 133:133 */       bl.a(0.0F, 1.0F - th, 1.0F - th, th, 1.0F, 1.0F);
/* 134:134 */       break;
/* 135:    */     case 12: 
/* 136:136 */       bl.a(1.0F - th, 1.0F - th, 0.0F, 1.0F, 1.0F, th);
/* 137:137 */       break;
/* 138:    */     case 13: 
/* 139:139 */       bl.a(1.0F - th, 1.0F - th, 1.0F - th, 1.0F, 1.0F, 1.0F);
/* 140:140 */       break;
/* 141:    */     case 14: 
/* 142:144 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, th, th);
/* 143:145 */       break;
/* 144:    */     case 15: 
/* 145:147 */       bl.a(0.0F, 0.0F, 1.0F - th, 1.0F, th, 1.0F);
/* 146:148 */       break;
/* 147:    */     case 16: 
/* 148:150 */       bl.a(0.0F, 0.0F, 0.0F, th, th, 1.0F);
/* 149:151 */       break;
/* 150:    */     case 17: 
/* 151:153 */       bl.a(1.0F - th, 0.0F, 0.0F, 1.0F, th, 1.0F);
/* 152:154 */       break;
/* 153:    */     case 18: 
/* 154:156 */       bl.a(0.0F, 0.0F, 0.0F, th, 1.0F, th);
/* 155:157 */       break;
/* 156:    */     case 19: 
/* 157:159 */       bl.a(0.0F, 0.0F, 1.0F - th, th, 1.0F, 1.0F);
/* 158:160 */       break;
/* 159:    */     case 20: 
/* 160:162 */       bl.a(1.0F - th, 0.0F, 0.0F, 1.0F, 1.0F, th);
/* 161:163 */       break;
/* 162:    */     case 21: 
/* 163:165 */       bl.a(1.0F - th, 0.0F, 1.0F - th, 1.0F, 1.0F, 1.0F);
/* 164:166 */       break;
/* 165:    */     case 22: 
/* 166:168 */       bl.a(0.0F, 1.0F - th, 0.0F, 1.0F, 1.0F, th);
/* 167:169 */       break;
/* 168:    */     case 23: 
/* 169:171 */       bl.a(0.0F, 1.0F - th, 1.0F - th, 1.0F, 1.0F, 1.0F);
/* 170:172 */       break;
/* 171:    */     case 24: 
/* 172:174 */       bl.a(0.0F, 1.0F - th, 0.0F, th, 1.0F, 1.0F);
/* 173:175 */       break;
/* 174:    */     case 25: 
/* 175:177 */       bl.a(1.0F - th, 1.0F - th, 0.0F, 1.0F, 1.0F, 1.0F);
/* 176:178 */       break;
/* 177:    */     case 26: 
/* 178:182 */       bl.a(0.5F - th, 0.0F, 0.5F - th, 0.5F + th, 1.0F, 0.5F + th);
/* 179:183 */       break;
/* 180:    */     case 27: 
/* 181:185 */       bl.a(0.5F - th, 0.5F - th, 0.0F, 0.5F + th, 0.5F + th, 1.0F);
/* 182:186 */       break;
/* 183:    */     case 28: 
/* 184:188 */       bl.a(0.0F, 0.5F - th, 0.5F - th, 1.0F, 0.5F + th, 0.5F + th);
/* 185:    */     }
/* 186:    */   }
/* 187:    */   
/* 188:    */   public int getSolidPartsMask()
/* 189:    */   {
/* 190:193 */     return getCoverMask();
/* 191:    */   }
/* 192:    */   
/* 193:    */   public int getPartsMask()
/* 194:    */   {
/* 195:194 */     return getCoverMask();
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void dropCover(int side, int cov)
/* 199:    */   {
/* 200:197 */     ur ist = CoverLib.convertCoverPlate(side, cov);
/* 201:198 */     if (ist == null) {
/* 202:198 */       return;
/* 203:    */     }
/* 204:199 */     CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 205:    */   }
/* 206:    */   
/* 207:    */   public float getExplosionResistance(int part, int side, lq exploder)
/* 208:    */   {
/* 209:207 */     int i = getCover(part);
/* 210:208 */     if (i < 0) {
/* 211:208 */       return -1.0F;
/* 212:    */     }
/* 213:209 */     i &= 0xFF;
/* 214:210 */     ur ist = CoverLib.getItemStack(i);
/* 215:211 */     return amq.p[ist.c].a(exploder);
/* 216:    */   }
/* 217:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TileCoverable
 * JD-Core Version:    0.7.0.1
 */