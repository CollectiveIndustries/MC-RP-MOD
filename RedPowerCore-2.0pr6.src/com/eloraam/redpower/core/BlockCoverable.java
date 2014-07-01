/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import amq;
/*  5:   */ import aoh;
/*  6:   */ import aoj;
/*  7:   */ import lq;
/*  8:   */ import net.minecraftforge.common.ForgeDirection;
/*  9:   */ import yc;
/* 10:   */ 
/* 11:   */ public abstract class BlockCoverable
/* 12:   */   extends BlockMultipart
/* 13:   */ {
/* 14:   */   public BlockCoverable(int i, agi m)
/* 15:   */   {
/* 16:20 */     super(i, m);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public boolean isBlockSolidOnSide(yc world, int i, int j, int k, ForgeDirection side)
/* 20:   */   {
/* 21:29 */     TileCoverable tc = (TileCoverable)CoreLib.getTileEntity(world, i, j, k, TileCoverable.class);
/* 22:31 */     if (tc == null) {
/* 23:31 */       return false;
/* 24:   */     }
/* 25:32 */     return tc.isSideNormal(side.ordinal());
/* 26:   */   }
/* 27:   */   
/* 28:   */   public float getExplosionResistance(lq exploder, yc world, int X, int Y, int Z, double srcX, double srcY, double srcZ)
/* 29:   */   {
/* 30:41 */     aoj org = aoj.a(srcX, srcY, srcZ);
/* 31:42 */     aoj end = aoj.a(X + 0.5D, Y + 0.5D, Z + 0.5D);
/* 32:43 */     amq bl = amq.p[world.a(X, Y, Z)];
/* 33:44 */     if (bl == null) {
/* 34:44 */       return 0.0F;
/* 35:   */     }
/* 36:46 */     aoh mop = bl.a(world, X, Y, Z, org, end);
/* 37:48 */     if (mop == null) {
/* 38:48 */       return bl.a(exploder);
/* 39:   */     }
/* 40:50 */     TileCoverable tl = (TileCoverable)CoreLib.getTileEntity(world, X, Y, Z, TileCoverable.class);
/* 41:52 */     if (tl == null) {
/* 42:52 */       return bl.a(exploder);
/* 43:   */     }
/* 44:53 */     float er = tl.getExplosionResistance(mop.subHit, mop.e, exploder);
/* 45:55 */     if (er < 0.0F) {
/* 46:55 */       return bl.a(exploder);
/* 47:   */     }
/* 48:56 */     return er;
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.BlockCoverable
 * JD-Core Version:    0.7.0.1
 */