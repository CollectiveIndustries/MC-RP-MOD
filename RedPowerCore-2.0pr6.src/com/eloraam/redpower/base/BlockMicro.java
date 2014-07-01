/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.BlockCoverable;
/*  4:   */ import com.eloraam.redpower.core.CoreLib;
/*  5:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  6:   */ import com.eloraam.redpower.core.IMicroPlacement;
/*  7:   */ import com.eloraam.redpower.core.RedPowerLib;
/*  8:   */ import ym;
/*  9:   */ 
/* 10:   */ public class BlockMicro
/* 11:   */   extends BlockCoverable
/* 12:   */ {
/* 13:   */   public BlockMicro(int i)
/* 14:   */   {
/* 15:15 */     super(i, CoreLib.materialRedpower);
/* 16:16 */     c(0.1F);
/* 17:17 */     a(CreativeExtraTabs.tabWires);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public boolean i()
/* 21:   */   {
/* 22:23 */     return !RedPowerLib.isSearching();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean canConnectRedstone(ym iba, int i, int j, int k, int dir)
/* 26:   */   {
/* 27:30 */     if (RedPowerLib.isSearching()) {
/* 28:30 */       return false;
/* 29:   */     }
/* 30:31 */     int md = iba.h(i, j, k);
/* 31:32 */     if ((md == 1) || (md == 2)) {
/* 32:32 */       return true;
/* 33:   */     }
/* 34:33 */     return false;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void registerPlacement(int md, IMicroPlacement imp)
/* 38:   */   {
/* 39:37 */     ((ItemMicro)up.e[this.cm]).registerPlacement(md, imp);
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.BlockMicro
 * JD-Core Version:    0.7.0.1
 */