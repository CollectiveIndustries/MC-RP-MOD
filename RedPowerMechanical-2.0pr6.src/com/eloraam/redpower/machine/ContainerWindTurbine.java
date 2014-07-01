/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import la;
/*  5:   */ import qx;
/*  6:   */ import rq;
/*  7:   */ import rw;
/*  8:   */ import sr;
/*  9:   */ import ur;
/* 10:   */ 
/* 11:   */ public class ContainerWindTurbine
/* 12:   */   extends rq
/* 13:   */ {
/* 14:   */   int windSpeed;
/* 15:   */   private TileWindTurbine tileWT;
/* 16:   */   
/* 17:   */   public ContainerWindTurbine(la inv, TileWindTurbine wt)
/* 18:   */   {
/* 19:13 */     this.tileWT = wt;
/* 20:   */     
/* 21:15 */     a(new sr(wt, 0, 80, 35));
/* 22:17 */     for (int i = 0; i < 3; i++) {
/* 23:17 */       for (int j = 0; j < 9; j++) {
/* 24:18 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/* 25:   */       }
/* 26:   */     }
/* 27:20 */     for (i = 0; i < 9; i++) {
/* 28:21 */       a(new sr(inv, i, 8 + i * 18, 142));
/* 29:   */     }
/* 30:   */   }
/* 31:   */   
/* 32:   */   public boolean a(qx player)
/* 33:   */   {
/* 34:26 */     return this.tileWT.a_(player);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public ur b(qx player, int i)
/* 38:   */   {
/* 39:31 */     ur itemstack = null;
/* 40:32 */     sr slot = (sr)this.c.get(i);
/* 41:33 */     if ((slot != null) && (slot.d()))
/* 42:   */     {
/* 43:34 */       ur itemstack1 = slot.c();
/* 44:35 */       itemstack = itemstack1.l();
/* 45:36 */       if (i < 1)
/* 46:   */       {
/* 47:37 */         if (!a(itemstack1, 1, 37, true)) {
/* 48:38 */           return null;
/* 49:   */         }
/* 50:   */       }
/* 51:   */       else
/* 52:   */       {
/* 53:40 */         sr sl0 = (sr)this.c.get(0);
/* 54:41 */         ur slst = sl0.c();
/* 55:42 */         if ((slst != null) && (slst.a != 0)) {
/* 56:43 */           return null;
/* 57:   */         }
/* 58:44 */         sl0.c(itemstack1.a(1));
/* 59:   */       }
/* 60:46 */       if (itemstack1.a == 0) {
/* 61:47 */         slot.c(null);
/* 62:   */       } else {
/* 63:49 */         slot.e();
/* 64:   */       }
/* 65:51 */       if (itemstack1.a != itemstack.a) {
/* 66:52 */         slot.a(player, itemstack1);
/* 67:   */       } else {
/* 68:54 */         return null;
/* 69:   */       }
/* 70:   */     }
/* 71:57 */     return itemstack;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public void b()
/* 75:   */   {
/* 76:61 */     super.b();
/* 77:63 */     for (int i = 0; i < this.e.size(); i++)
/* 78:   */     {
/* 79:64 */       rw ic = (rw)this.e.get(i);
/* 80:66 */       if (this.windSpeed != this.tileWT.windSpeed) {
/* 81:67 */         ic.a(this, 0, this.tileWT.windSpeed);
/* 82:   */       }
/* 83:   */     }
/* 84:70 */     this.windSpeed = this.tileWT.windSpeed;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public void b(int i, int j)
/* 88:   */   {
/* 89:74 */     switch (i)
/* 90:   */     {
/* 91:   */     case 0: 
/* 92:75 */       this.tileWT.windSpeed = j;
/* 93:   */     }
/* 94:   */   }
/* 95:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerWindTurbine
 * JD-Core Version:    0.7.0.1
 */