/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import up;
/*  4:   */ 
/*  5:   */ public class ItemTextured
/*  6:   */   extends up
/*  7:   */ {
/*  8:   */   public ItemTextured(int i, String txf)
/*  9:   */   {
/* 10:10 */     super(i);
/* 11:11 */     setTextureFile(txf);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public ItemTextured(int i, int j, String txf)
/* 15:   */   {
/* 16:15 */     super(i);
/* 17:16 */     this.cl = j;
/* 18:17 */     setTextureFile(txf);
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.ItemTextured
 * JD-Core Version:    0.7.0.1
 */