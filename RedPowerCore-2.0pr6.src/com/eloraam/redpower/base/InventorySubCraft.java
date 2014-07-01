/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import la;
/*  4:   */ import rq;
/*  5:   */ import ry;
/*  6:   */ import ur;
/*  7:   */ 
/*  8:   */ public class InventorySubCraft
/*  9:   */   extends ry
/* 10:   */ {
/* 11:   */   private rq eventHandler;
/* 12:   */   private la parent;
/* 13:   */   
/* 14:   */   public InventorySubCraft(rq container, la par)
/* 15:   */   {
/* 16:15 */     super(container, 3, 3);
/* 17:16 */     this.parent = par;
/* 18:17 */     this.eventHandler = container;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int k_()
/* 22:   */   {
/* 23:21 */     return 9;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public ur a(int i)
/* 27:   */   {
/* 28:25 */     if (i >= 9) {
/* 29:25 */       return null;
/* 30:   */     }
/* 31:26 */     return this.parent.a(i);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public ur b(int i, int j)
/* 35:   */   {
/* 36:30 */     if ((i < 0) || (i >= 3)) {
/* 37:30 */       return null;
/* 38:   */     }
/* 39:32 */     int k = i + j * 3;
/* 40:33 */     return a(k);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public ur a(int i, int j)
/* 44:   */   {
/* 45:37 */     ur tr = this.parent.a(i, j);
/* 46:38 */     if (tr != null) {
/* 47:39 */       this.eventHandler.a(this);
/* 48:   */     }
/* 49:41 */     return tr;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void a(int i, ur ist)
/* 53:   */   {
/* 54:45 */     this.parent.a(i, ist);
/* 55:46 */     this.eventHandler.a(this);
/* 56:   */   }
/* 57:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.InventorySubCraft
 * JD-Core Version:    0.7.0.1
 */