/*  1:   */ package com.eloraam.redpower.logic;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.BlockCoverable;
/*  4:   */ import com.eloraam.redpower.core.CoreLib;
/*  5:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  6:   */ import com.eloraam.redpower.core.IRedPowerConnectable;
/*  7:   */ import com.eloraam.redpower.core.RedPowerLib;
/*  8:   */ import ym;
/*  9:   */ 
/* 10:   */ public class BlockLogic
/* 11:   */   extends BlockCoverable
/* 12:   */ {
/* 13:   */   public BlockLogic(int i)
/* 14:   */   {
/* 15:21 */     super(i, CoreLib.materialRedpower);
/* 16:22 */     a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/* 17:   */     
/* 18:24 */     c(0.1F);
/* 19:25 */     a(0.625F);
/* 20:26 */     a(CreativeExtraTabs.tabWires);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean canConnectRedstone(ym iba, int i, int j, int k, int dir)
/* 24:   */   {
/* 25:31 */     if (dir < 0) {
/* 26:31 */       return false;
/* 27:   */     }
/* 28:33 */     IRedPowerConnectable irp = (IRedPowerConnectable)CoreLib.getTileEntity(iba, i, j, k, IRedPowerConnectable.class);
/* 29:36 */     if (irp == null) {
/* 30:36 */       return false;
/* 31:   */     }
/* 32:37 */     int s = RedPowerLib.mapLocalToRot(irp.getConnectableMask(), 2);
/* 33:38 */     return (s & 1 << dir) > 0;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public int getLightValue(ym iba, int i, int j, int k)
/* 37:   */   {
/* 38:42 */     TileLogic tl = (TileLogic)CoreLib.getTileEntity(iba, i, j, k, TileLogic.class);
/* 39:44 */     if (tl == null) {
/* 40:44 */       return super.getLightValue(iba, i, j, k);
/* 41:   */     }
/* 42:46 */     return tl.getLightValue();
/* 43:   */   }
/* 44:   */   
/* 45:   */   public boolean i()
/* 46:   */   {
/* 47:50 */     return true;
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.BlockLogic
 * JD-Core Version:    0.7.0.1
 */