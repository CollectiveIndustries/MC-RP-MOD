/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import la;
/*  5:   */ import qx;
/*  6:   */ import rq;
/*  7:   */ import sr;
/*  8:   */ import ur;
/*  9:   */ 
/* 10:   */ public class ContainerEject
/* 11:   */   extends rq
/* 12:   */ {
/* 13:   */   private TileEjectBase tileEject;
/* 14:   */   
/* 15:   */   public ContainerEject(la inv, TileEjectBase td)
/* 16:   */   {
/* 17:12 */     this.tileEject = td;
/* 18:14 */     for (int i = 0; i < 3; i++) {
/* 19:14 */       for (int j = 0; j < 3; j++) {
/* 20:15 */         a(new sr(td, j + i * 3, 62 + j * 18, 17 + i * 18));
/* 21:   */       }
/* 22:   */     }
/* 23:17 */     for (i = 0; i < 3; i++) {
/* 24:17 */       for (int j = 0; j < 9; j++) {
/* 25:18 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/* 26:   */       }
/* 27:   */     }
/* 28:20 */     for (i = 0; i < 9; i++) {
/* 29:21 */       a(new sr(inv, i, 8 + i * 18, 142));
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean a(qx player)
/* 34:   */   {
/* 35:26 */     return this.tileEject.a_(player);
/* 36:   */   }
/* 37:   */   
/* 38:   */   public ur b(qx player, int i)
/* 39:   */   {
/* 40:31 */     ur itemstack = null;
/* 41:32 */     sr slot = (sr)this.c.get(i);
/* 42:33 */     if ((slot != null) && (slot.d()))
/* 43:   */     {
/* 44:34 */       ur itemstack1 = slot.c();
/* 45:35 */       itemstack = itemstack1.l();
/* 46:36 */       if (i < 9)
/* 47:   */       {
/* 48:37 */         if (!a(itemstack1, 9, 45, true)) {
/* 49:38 */           return null;
/* 50:   */         }
/* 51:   */       }
/* 52:40 */       else if (!a(itemstack1, 0, 9, false)) {
/* 53:41 */         return null;
/* 54:   */       }
/* 55:43 */       if (itemstack1.a == 0) {
/* 56:44 */         slot.c(null);
/* 57:   */       } else {
/* 58:46 */         slot.e();
/* 59:   */       }
/* 60:48 */       if (itemstack1.a != itemstack.a) {
/* 61:49 */         slot.a(player, itemstack1);
/* 62:   */       } else {
/* 63:51 */         return null;
/* 64:   */       }
/* 65:   */     }
/* 66:54 */     return itemstack;
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerEject
 * JD-Core Version:    0.7.0.1
 */