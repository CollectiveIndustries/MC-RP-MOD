/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import agi;
/*   4:    */ import aoe;
/*   5:    */ import aoh;
/*   6:    */ import aoi;
/*   7:    */ import aoj;
/*   8:    */ import java.util.List;
/*   9:    */ import lq;
/*  10:    */ import qx;
/*  11:    */ import yc;
/*  12:    */ 
/*  13:    */ public class BlockMultipart
/*  14:    */   extends BlockExtended
/*  15:    */ {
/*  16:    */   public BlockMultipart(int i, agi m)
/*  17:    */   {
/*  18: 19 */     super(i, m);
/*  19:    */   }
/*  20:    */   
/*  21:    */   public void a(yc world, int i, int j, int k, int l)
/*  22:    */   {
/*  23: 24 */     TileMultipart tl = (TileMultipart)CoreLib.getTileEntity(world, i, j, k, TileMultipart.class);
/*  24: 26 */     if (tl == null)
/*  25:    */     {
/*  26: 27 */       world.e(i, j, k, 0);
/*  27: 28 */       return;
/*  28:    */     }
/*  29: 30 */     tl.onBlockNeighborChange(l);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public boolean removeBlockByPlayer(yc world, qx player, int i, int j, int k)
/*  33:    */   {
/*  34: 37 */     if (CoreLib.isClient(world)) {
/*  35: 37 */       return true;
/*  36:    */     }
/*  37: 39 */     aoh pos = CoreLib.retraceBlock(world, player, i, j, k);
/*  38: 41 */     if (pos == null) {
/*  39: 41 */       return false;
/*  40:    */     }
/*  41: 42 */     if (pos.a != aoi.a) {
/*  42: 42 */       return false;
/*  43:    */     }
/*  44: 44 */     TileMultipart tl = (TileMultipart)CoreLib.getTileEntity(world, i, j, k, TileMultipart.class);
/*  45: 46 */     if (tl == null) {
/*  46: 46 */       return false;
/*  47:    */     }
/*  48: 47 */     tl.onHarvestPart(player, pos.subHit);
/*  49: 48 */     return false;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public boolean a(yc world, int i, int j, int k, qx player, int side, float xp, float yp, float zp)
/*  53:    */   {
/*  54: 55 */     aoh pos = CoreLib.retraceBlock(world, player, i, j, k);
/*  55: 57 */     if (pos == null) {
/*  56: 57 */       return false;
/*  57:    */     }
/*  58: 58 */     if (pos.a != aoi.a) {
/*  59: 58 */       return false;
/*  60:    */     }
/*  61: 60 */     TileMultipart tl = (TileMultipart)CoreLib.getTileEntity(world, i, j, k, TileMultipart.class);
/*  62: 62 */     if (tl == null) {
/*  63: 62 */       return false;
/*  64:    */     }
/*  65: 63 */     return tl.onPartActivateSide(player, pos.subHit, pos.e);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public float a(qx player, yc world, int x, int y, int z)
/*  69:    */   {
/*  70: 69 */     aoh pos = CoreLib.retraceBlock(world, player, x, y, z);
/*  71: 71 */     if (pos == null) {
/*  72: 71 */       return 0.0F;
/*  73:    */     }
/*  74: 72 */     if (pos.a != aoi.a) {
/*  75: 72 */       return 0.0F;
/*  76:    */     }
/*  77: 74 */     TileMultipart tl = (TileMultipart)CoreLib.getTileEntity(player.p, x, y, z, TileMultipart.class);
/*  78: 76 */     if (tl == null) {
/*  79: 76 */       return 0.0F;
/*  80:    */     }
/*  81: 77 */     return tl.getPartStrength(player, pos.subHit);
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void k(yc world, int i, int j, int k)
/*  85:    */   {
/*  86: 83 */     TileMultipart tl = (TileMultipart)CoreLib.getTileEntity(world, i, j, k, TileMultipart.class);
/*  87: 85 */     if (tl == null) {
/*  88: 85 */       return;
/*  89:    */     }
/*  90: 86 */     tl.breakBlock();
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void a(yc world, int i, int j, int k, aoe box, List list, lq ent)
/*  94:    */   {
/*  95: 93 */     TileMultipart tl = (TileMultipart)CoreLib.getTileEntity(world, i, j, k, TileMultipart.class);
/*  96: 95 */     if (tl == null) {
/*  97: 95 */       return;
/*  98:    */     }
/*  99: 96 */     int pm = tl.getSolidPartsMask();
/* 100: 98 */     while (pm > 0)
/* 101:    */     {
/* 102: 99 */       int pt = Integer.numberOfTrailingZeros(pm);
/* 103:100 */       pm &= (1 << pt ^ 0xFFFFFFFF);
/* 104:    */       
/* 105:102 */       tl.setPartBounds(this, pt);
/* 106:103 */       super.a(world, i, j, k, box, list, ent);
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   public aoh a(yc world, int i, int j, int k, aoj vec3d, aoj vec3d1)
/* 111:    */   {
/* 112:110 */     TileMultipart tl = (TileMultipart)CoreLib.getTileEntity(world, i, j, k, TileMultipart.class);
/* 113:112 */     if (tl == null) {
/* 114:112 */       return null;
/* 115:    */     }
/* 116:114 */     int pm = tl.getPartsMask();
/* 117:    */     
/* 118:116 */     aoh p1 = null;
/* 119:117 */     int cpt = -1;
/* 120:118 */     double d1 = 0.0D;
/* 121:119 */     while (pm > 0)
/* 122:    */     {
/* 123:120 */       int pt = Integer.numberOfTrailingZeros(pm);
/* 124:121 */       pm &= (1 << pt ^ 0xFFFFFFFF);
/* 125:    */       
/* 126:123 */       tl.setPartBounds(this, pt);
/* 127:124 */       aoh p2 = super.a(world, i, j, k, vec3d, vec3d1);
/* 128:125 */       if (p2 != null)
/* 129:    */       {
/* 130:126 */         double d2 = p2.f.e(vec3d);
/* 131:127 */         if ((p1 == null) || (d2 < d1))
/* 132:    */         {
/* 133:128 */           d1 = d2;p1 = p2;cpt = pt;
/* 134:    */         }
/* 135:    */       }
/* 136:    */     }
/* 137:131 */     if (p1 == null) {
/* 138:131 */       return null;
/* 139:    */     }
/* 140:132 */     tl.setPartBounds(this, cpt);
/* 141:133 */     p1.subHit = cpt;
/* 142:134 */     return p1;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public static void removeMultipart(yc world, int i, int j, int k)
/* 146:    */   {
/* 147:145 */     world.b(i, j, k, 0);
/* 148:    */   }
/* 149:    */   
/* 150:    */   public static void removeMultipartWithNotify(yc world, int i, int j, int k)
/* 151:    */   {
/* 152:156 */     world.e(i, j, k, 0);
/* 153:    */   }
/* 154:    */   
/* 155:    */   protected aoh traceCurrentBlock(yc world, int i, int j, int k, aoj src, aoj dest)
/* 156:    */   {
/* 157:162 */     return super.a(world, i, j, k, src, dest);
/* 158:    */   }
/* 159:    */   
/* 160:    */   public void setPartBounds(yc world, int i, int j, int k, int part)
/* 161:    */   {
/* 162:167 */     TileMultipart tl = (TileMultipart)CoreLib.getTileEntity(world, i, j, k, TileMultipart.class);
/* 163:169 */     if (tl == null)
/* 164:    */     {
/* 165:170 */       a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 166:171 */       return;
/* 167:    */     }
/* 168:173 */     tl.setPartBounds(this, part);
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void computeCollidingBoxes(yc world, int i, int j, int k, aoe box, List list, TileMultipart tl)
/* 172:    */   {
/* 173:179 */     int pm = tl.getSolidPartsMask();
/* 174:181 */     while (pm > 0)
/* 175:    */     {
/* 176:182 */       int pt = Integer.numberOfTrailingZeros(pm);
/* 177:183 */       pm &= (1 << pt ^ 0xFFFFFFFF);
/* 178:    */       
/* 179:185 */       tl.setPartBounds(this, pt);
/* 180:186 */       super.a(world, i, j, k, box, list, null);
/* 181:    */     }
/* 182:    */   }
/* 183:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.BlockMultipart
 * JD-Core Version:    0.7.0.1
 */