/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.ItemPartialCraft;
/*  4:   */ import tj;
/*  5:   */ 
/*  6:   */ public class ItemHandsaw
/*  7:   */   extends ItemPartialCraft
/*  8:   */ {
/*  9:   */   private int sharp;
/* 10:   */   
/* 11:   */   public ItemHandsaw(int i, int sh)
/* 12:   */   {
/* 13:12 */     super(i);
/* 14:13 */     this.sharp = sh;
/* 15:14 */     a(tj.i);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public boolean n_()
/* 19:   */   {
/* 20:18 */     return true;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean o_()
/* 24:   */   {
/* 25:22 */     return true;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int getSharpness()
/* 29:   */   {
/* 30:26 */     return this.sharp;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String getTextureFile()
/* 34:   */   {
/* 35:30 */     return "/eloraam/base/items1.png";
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ItemHandsaw
 * JD-Core Version:    0.7.0.1
 */