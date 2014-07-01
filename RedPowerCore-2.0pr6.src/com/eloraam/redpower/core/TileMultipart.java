/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ import qx;
/*  6:   */ 
/*  7:   */ public abstract class TileMultipart
/*  8:   */   extends TileExtended
/*  9:   */   implements IMultipart
/* 10:   */ {
/* 11:   */   public boolean isSideSolid(int side)
/* 12:   */   {
/* 13:16 */     return false;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public boolean isSideNormal(int side)
/* 17:   */   {
/* 18:20 */     return false;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public List harvestMultipart()
/* 22:   */   {
/* 23:25 */     ArrayList ist = new ArrayList();
/* 24:26 */     addHarvestContents(ist);
/* 25:27 */     deleteBlock();
/* 26:28 */     return ist;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void onHarvestPart(qx player, int part) {}
/* 30:   */   
/* 31:   */   public boolean onPartActivateSide(qx player, int part, int side)
/* 32:   */   {
/* 33:36 */     return false;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public float getPartStrength(qx player, int part)
/* 37:   */   {
/* 38:40 */     return 0.0F;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public abstract boolean blockEmpty();
/* 42:   */   
/* 43:   */   public abstract void setPartBounds(BlockMultipart paramBlockMultipart, int paramInt);
/* 44:   */   
/* 45:   */   public abstract int getSolidPartsMask();
/* 46:   */   
/* 47:   */   public abstract int getPartsMask();
/* 48:   */   
/* 49:   */   public void deleteBlock()
/* 50:   */   {
/* 51:61 */     BlockMultipart.removeMultipartWithNotify(this.k, this.l, this.m, this.n);
/* 52:   */   }
/* 53:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TileMultipart
 * JD-Core Version:    0.7.0.1
 */