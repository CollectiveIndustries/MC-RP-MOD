/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import md;
/*  4:   */ import pe;
/*  5:   */ import tj;
/*  6:   */ import up;
/*  7:   */ import ur;
/*  8:   */ 
/*  9:   */ public class ItemDyeIndigo
/* 10:   */   extends up
/* 11:   */ {
/* 12:   */   public ItemDyeIndigo(int i)
/* 13:   */   {
/* 14:11 */     super(i);
/* 15:12 */     e(0);
/* 16:13 */     a(true);
/* 17:14 */     a(tj.l);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String d(ur itemstack)
/* 21:   */   {
/* 22:18 */     return "item.dyeIndigo";
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int b(int i)
/* 26:   */   {
/* 27:22 */     return 80;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String getTextureFile()
/* 31:   */   {
/* 32:26 */     return "/eloraam/base/items1.png";
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void useItemOnEntity(ur itemstack, md entityliving)
/* 36:   */   {
/* 37:32 */     if (itemstack.j() != 0) {
/* 38:32 */       return;
/* 39:   */     }
/* 40:33 */     if ((entityliving instanceof pe))
/* 41:   */     {
/* 42:34 */       pe entitysheep = (pe)entityliving;
/* 43:35 */       if ((!entitysheep.n()) && (entitysheep.m() != 11))
/* 44:   */       {
/* 45:38 */         entitysheep.s(11);
/* 46:39 */         itemstack.a -= 1;
/* 47:   */       }
/* 48:   */     }
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ItemDyeIndigo
 * JD-Core Version:    0.7.0.1
 */