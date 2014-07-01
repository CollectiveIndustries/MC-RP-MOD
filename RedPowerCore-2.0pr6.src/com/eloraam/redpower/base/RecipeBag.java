/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import ry;
/*  5:   */ import up;
/*  6:   */ import ur;
/*  7:   */ import wp;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class RecipeBag
/* 11:   */   implements wp
/* 12:   */ {
/* 13:   */   public int a()
/* 14:   */   {
/* 15:17 */     return 2;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public ur b()
/* 19:   */   {
/* 20:21 */     return new ur(RedPowerBase.itemBag, 1, 0);
/* 21:   */   }
/* 22:   */   
/* 23:   */   private ur findResult(ry inv)
/* 24:   */   {
/* 25:25 */     ur bag = null;
/* 26:26 */     int color = -1;
/* 27:28 */     for (int i = 0; i < 3; i++) {
/* 28:29 */       for (int j = 0; j < 3; j++)
/* 29:   */       {
/* 30:30 */         ur ist = inv.b(i, j);
/* 31:31 */         if (ist != null) {
/* 32:33 */           if ((ist.b() instanceof ItemBag))
/* 33:   */           {
/* 34:34 */             if (bag != null) {
/* 35:34 */               return null;
/* 36:   */             }
/* 37:35 */             bag = ist;
/* 38:   */           }
/* 39:36 */           else if (ist.c == up.aW.cj)
/* 40:   */           {
/* 41:37 */             if (color >= 0) {
/* 42:37 */               return null;
/* 43:   */             }
/* 44:38 */             color = 15 - ist.j();
/* 45:   */           }
/* 46:   */           else
/* 47:   */           {
/* 48:40 */             return null;
/* 49:   */           }
/* 50:   */         }
/* 51:   */       }
/* 52:   */     }
/* 53:44 */     if ((bag == null) || (color < 0)) {
/* 54:44 */       return null;
/* 55:   */     }
/* 56:45 */     if (bag.j() == color) {
/* 57:45 */       return null;
/* 58:   */     }
/* 59:47 */     bag = bag.l();
/* 60:48 */     bag.b(color);
/* 61:49 */     return bag;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public ur a(ry inv)
/* 65:   */   {
/* 66:53 */     return findResult(inv).l();
/* 67:   */   }
/* 68:   */   
/* 69:   */   public boolean a(ry inv, yc world)
/* 70:   */   {
/* 71:57 */     return findResult(inv) != null;
/* 72:   */   }
/* 73:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.RecipeBag
 * JD-Core Version:    0.7.0.1
 */