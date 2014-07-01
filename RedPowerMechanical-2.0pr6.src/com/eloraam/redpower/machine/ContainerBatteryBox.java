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
/* 11:   */ public class ContainerBatteryBox
/* 12:   */   extends rq
/* 13:   */ {
/* 14:   */   private TileBatteryBox tileBB;
/* 15:   */   public int charge;
/* 16:   */   public int storage;
/* 17:   */   
/* 18:   */   public ContainerBatteryBox(la inv, TileBatteryBox tf)
/* 19:   */   {
/* 20:13 */     this.tileBB = tf;
/* 21:   */     
/* 22:   */ 
/* 23:   */ 
/* 24:   */ 
/* 25:   */ 
/* 26:   */ 
/* 27:20 */     a(new sr(tf, 0, 120, 27));
/* 28:21 */     a(new sr(tf, 1, 120, 55));
/* 29:23 */     for (int i = 0; i < 3; i++) {
/* 30:23 */       for (int j = 0; j < 9; j++) {
/* 31:24 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 88 + i * 18));
/* 32:   */       }
/* 33:   */     }
/* 34:26 */     for (i = 0; i < 9; i++) {
/* 35:27 */       a(new sr(inv, i, 8 + i * 18, 146));
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public boolean a(qx player)
/* 40:   */   {
/* 41:32 */     return this.tileBB.a_(player);
/* 42:   */   }
/* 43:   */   
/* 44:   */   public ur b(qx player, int i)
/* 45:   */   {
/* 46:37 */     ur itemstack = null;
/* 47:38 */     sr slot = (sr)this.c.get(i);
/* 48:39 */     if ((slot != null) && (slot.d()))
/* 49:   */     {
/* 50:40 */       ur itemstack1 = slot.c();
/* 51:41 */       itemstack = itemstack1.l();
/* 52:42 */       if (i < 2)
/* 53:   */       {
/* 54:43 */         if (!a(itemstack1, 2, 38, true)) {
/* 55:44 */           return null;
/* 56:   */         }
/* 57:   */       }
/* 58:   */       else
/* 59:   */       {
/* 60:46 */         sr sl0 = (sr)this.c.get(0);
/* 61:47 */         ur slst = sl0.c();
/* 62:48 */         if ((slst != null) && (slst.a != 0)) {
/* 63:49 */           return null;
/* 64:   */         }
/* 65:50 */         sl0.c(itemstack1.a(1));
/* 66:   */       }
/* 67:52 */       if (itemstack1.a == 0) {
/* 68:53 */         slot.c(null);
/* 69:   */       } else {
/* 70:55 */         slot.e();
/* 71:   */       }
/* 72:57 */       if (itemstack1.a != itemstack.a) {
/* 73:58 */         slot.a(player, itemstack1);
/* 74:   */       } else {
/* 75:60 */         return null;
/* 76:   */       }
/* 77:   */     }
/* 78:63 */     return itemstack;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public void b()
/* 82:   */   {
/* 83:67 */     super.b();
/* 84:69 */     for (int i = 0; i < this.e.size(); i++)
/* 85:   */     {
/* 86:70 */       rw ic = (rw)this.e.get(i);
/* 87:72 */       if (this.charge != this.tileBB.Charge) {
/* 88:73 */         ic.a(this, 0, this.tileBB.Charge);
/* 89:   */       }
/* 90:75 */       if (this.storage != this.tileBB.Storage) {
/* 91:76 */         ic.a(this, 1, this.tileBB.Storage);
/* 92:   */       }
/* 93:   */     }
/* 94:79 */     this.charge = this.tileBB.Charge;
/* 95:80 */     this.storage = this.tileBB.Storage;
/* 96:   */   }
/* 97:   */   
/* 98:   */   public void b(int i, int j)
/* 99:   */   {
/* :0:84 */     switch (i)
/* :1:   */     {
/* :2:   */     case 0: 
/* :3:85 */       this.tileBB.Charge = j; break;
/* :4:   */     case 1: 
/* :5:86 */       this.tileBB.Storage = j;
/* :6:   */     }
/* :7:   */   }
/* :8:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerBatteryBox
 * JD-Core Version:    0.7.0.1
 */