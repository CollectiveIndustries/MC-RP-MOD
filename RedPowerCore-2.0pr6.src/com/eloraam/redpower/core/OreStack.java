/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ public class OreStack
/*  4:   */ {
/*  5:   */   public String material;
/*  6:   */   public int quantity;
/*  7:   */   
/*  8:   */   public OreStack(String mat, int qty)
/*  9:   */   {
/* 10: 8 */     this.material = mat;
/* 11: 9 */     this.quantity = qty;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public OreStack(String mat)
/* 15:   */   {
/* 16:13 */     this.material = mat;
/* 17:14 */     this.quantity = 1;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.OreStack
 * JD-Core Version:    0.7.0.1
 */