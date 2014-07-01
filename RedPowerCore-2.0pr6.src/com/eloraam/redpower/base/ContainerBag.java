/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import la;
/*  5:   */ import qw;
/*  6:   */ import qx;
/*  7:   */ import rq;
/*  8:   */ import sr;
/*  9:   */ import ur;
/* 10:   */ 
/* 11:   */ public class ContainerBag
/* 12:   */   extends rq
/* 13:   */ {
/* 14:   */   ur itemBag;
/* 15:   */   
/* 16:   */   public static class SlotBag
/* 17:   */     extends sr
/* 18:   */   {
/* 19:   */     public SlotBag(la inv, int par2, int par3, int par4)
/* 20:   */     {
/* 21:18 */       super(par2, par3, par4);
/* 22:   */     }
/* 23:   */     
/* 24:   */     public boolean a(ur ist)
/* 25:   */     {
/* 26:22 */       if ((ist.b() instanceof ItemBag)) {
/* 27:23 */         return false;
/* 28:   */       }
/* 29:24 */       return true;
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   public ContainerBag(qw inv, la bag, ur ist)
/* 34:   */   {
/* 35:31 */     for (int i = 0; i < 3; i++) {
/* 36:31 */       for (int j = 0; j < 9; j++) {
/* 37:32 */         a(new SlotBag(bag, j + i * 9, 8 + j * 18, 18 + i * 18));
/* 38:   */       }
/* 39:   */     }
/* 40:35 */     for (i = 0; i < 3; i++) {
/* 41:35 */       for (int j = 0; j < 9; j++) {
/* 42:36 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
/* 43:   */       }
/* 44:   */     }
/* 45:38 */     for (i = 0; i < 9; i++) {
/* 46:39 */       a(new sr(inv, i, 8 + i * 18, 144));
/* 47:   */     }
/* 48:41 */     this.itemBag = ist;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public boolean a(qx player)
/* 52:   */   {
/* 53:45 */     return player.bJ.g() == this.itemBag;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public ur b(qx player, int i)
/* 57:   */   {
/* 58:50 */     ur itemstack = null;
/* 59:51 */     sr slot = (sr)this.c.get(i);
/* 60:52 */     if ((slot != null) && (slot.d()))
/* 61:   */     {
/* 62:53 */       ur itemstack1 = slot.c();
/* 63:54 */       if ((itemstack1.b() instanceof ItemBag)) {
/* 64:55 */         return null;
/* 65:   */       }
/* 66:56 */       itemstack = itemstack1.l();
/* 67:57 */       if (i < 27)
/* 68:   */       {
/* 69:58 */         if (!a(itemstack1, 27, 63, true)) {
/* 70:59 */           return null;
/* 71:   */         }
/* 72:   */       }
/* 73:61 */       else if (!a(itemstack1, 0, 27, false)) {
/* 74:62 */         return null;
/* 75:   */       }
/* 76:64 */       if (itemstack1.a == 0) {
/* 77:65 */         slot.c(null);
/* 78:   */       } else {
/* 79:67 */         slot.e();
/* 80:   */       }
/* 81:69 */       if (itemstack1.a != itemstack.a) {
/* 82:70 */         slot.a(player, itemstack1);
/* 83:   */       } else {
/* 84:72 */         return null;
/* 85:   */       }
/* 86:   */     }
/* 87:75 */     return itemstack;
/* 88:   */   }
/* 89:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ContainerBag
 * JD-Core Version:    0.7.0.1
 */