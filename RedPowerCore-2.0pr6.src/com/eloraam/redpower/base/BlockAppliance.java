/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import com.eloraam.redpower.core.BlockExtended;
/*  5:   */ import com.eloraam.redpower.core.CoreLib;
/*  6:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  7:   */ import ym;
/*  8:   */ 
/*  9:   */ public class BlockAppliance
/* 10:   */   extends BlockExtended
/* 11:   */ {
/* 12:   */   public BlockAppliance(int i)
/* 13:   */   {
/* 14:17 */     super(i, agi.e);
/* 15:18 */     c(2.0F);
/* 16:19 */     a(CreativeExtraTabs.tabMachine);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int getLightValue(ym iba, int i, int j, int k)
/* 20:   */   {
/* 21:23 */     TileAppliance taf = (TileAppliance)CoreLib.getTileEntity(iba, i, j, k, TileAppliance.class);
/* 22:25 */     if (taf == null) {
/* 23:25 */       return super.getLightValue(iba, i, j, k);
/* 24:   */     }
/* 25:26 */     return taf.getLightValue();
/* 26:   */   }
/* 27:   */   
/* 28:   */   public boolean c()
/* 29:   */   {
/* 30:29 */     return true;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean isACube()
/* 34:   */   {
/* 35:30 */     return true;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean b()
/* 39:   */   {
/* 40:31 */     return true;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public int b(int i)
/* 44:   */   {
/* 45:35 */     return i;
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.BlockAppliance
 * JD-Core Version:    0.7.0.1
 */