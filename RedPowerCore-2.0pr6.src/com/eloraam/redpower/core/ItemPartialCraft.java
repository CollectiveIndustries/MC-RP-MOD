/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import up;
/*  4:   */ import ur;
/*  5:   */ 
/*  6:   */ public class ItemPartialCraft
/*  7:   */   extends up
/*  8:   */ {
/*  9:   */   public ItemPartialCraft(int i)
/* 10:   */   {
/* 11: 7 */     super(i);
/* 12: 8 */     d(1);
/* 13: 9 */     setNoRepair();
/* 14:   */   }
/* 15:   */   
/* 16:12 */   private ur emptyItem = null;
/* 17:   */   
/* 18:   */   public void setEmptyItem(ur ei)
/* 19:   */   {
/* 20:15 */     this.emptyItem = ei;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public ur getContainerItemStack(ur ist)
/* 24:   */   {
/* 25:20 */     int dmg = ist.j();
/* 26:22 */     if ((dmg == ist.k()) && 
/* 27:23 */       (this.emptyItem != null)) {
/* 28:24 */       return CoreLib.copyStack(this.emptyItem, 1);
/* 29:   */     }
/* 30:26 */     ur tr = CoreLib.copyStack(ist, 1);
/* 31:27 */     tr.b(dmg + 1);
/* 32:28 */     return tr;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean s()
/* 36:   */   {
/* 37:33 */     return true;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean j(ur ist)
/* 41:   */   {
/* 42:38 */     return false;
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.ItemPartialCraft
 * JD-Core Version:    0.7.0.1
 */