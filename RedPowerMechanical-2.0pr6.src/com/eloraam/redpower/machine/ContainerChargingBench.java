/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*  4:   */ import java.util.List;
/*  5:   */ import la;
/*  6:   */ import qx;
/*  7:   */ import rq;
/*  8:   */ import rw;
/*  9:   */ import sr;
/* 10:   */ import ur;
/* 11:   */ 
/* 12:   */ public class ContainerChargingBench
/* 13:   */   extends rq
/* 14:   */ {
/* 15:   */   private TileChargingBench tileCB;
/* 16:   */   public int charge;
/* 17:   */   public int storage;
/* 18:   */   
/* 19:   */   public ContainerChargingBench(la inv, TileChargingBench tf)
/* 20:   */   {
/* 21:13 */     this.tileCB = tf;
/* 22:15 */     for (int i = 0; i < 4; i++) {
/* 23:15 */       for (int j = 0; j < 4; j++) {
/* 24:16 */         a(new sr(tf, j + i * 4, 80 + j * 18, 18 + i * 18));
/* 25:   */       }
/* 26:   */     }
/* 27:19 */     for (i = 0; i < 3; i++) {
/* 28:19 */       for (int j = 0; j < 9; j++) {
/* 29:20 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 104 + i * 18));
/* 30:   */       }
/* 31:   */     }
/* 32:22 */     for (i = 0; i < 9; i++) {
/* 33:23 */       a(new sr(inv, i, 8 + i * 18, 162));
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public boolean a(qx player)
/* 38:   */   {
/* 39:28 */     return this.tileCB.a_(player);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public ur b(qx player, int i)
/* 43:   */   {
/* 44:33 */     ur itemstack = null;
/* 45:34 */     sr slot = (sr)this.c.get(i);
/* 46:35 */     if ((slot != null) && (slot.d()))
/* 47:   */     {
/* 48:36 */       ur itemstack1 = slot.c();
/* 49:37 */       itemstack = itemstack1.l();
/* 50:38 */       if (i < 16)
/* 51:   */       {
/* 52:39 */         if (!a(itemstack1, 16, 52, true)) {
/* 53:40 */           return null;
/* 54:   */         }
/* 55:   */       }
/* 56:42 */       else if (!a(itemstack1, 0, 16, false)) {
/* 57:43 */         return null;
/* 58:   */       }
/* 59:45 */       if (itemstack1.a == 0) {
/* 60:46 */         slot.c(null);
/* 61:   */       } else {
/* 62:48 */         slot.e();
/* 63:   */       }
/* 64:50 */       if (itemstack1.a != itemstack.a) {
/* 65:51 */         slot.a(player, itemstack1);
/* 66:   */       } else {
/* 67:53 */         return null;
/* 68:   */       }
/* 69:   */     }
/* 70:56 */     return itemstack;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public void b()
/* 74:   */   {
/* 75:60 */     super.b();
/* 76:62 */     for (int i = 0; i < this.e.size(); i++)
/* 77:   */     {
/* 78:63 */       rw ic = (rw)this.e.get(i);
/* 79:65 */       if (this.charge != this.tileCB.cond.Charge) {
/* 80:66 */         ic.a(this, 0, this.tileCB.cond.Charge);
/* 81:   */       }
/* 82:68 */       if (this.storage != this.tileCB.Storage) {
/* 83:69 */         ic.a(this, 1, this.tileCB.Storage);
/* 84:   */       }
/* 85:   */     }
/* 86:72 */     this.charge = this.tileCB.cond.Charge;
/* 87:73 */     this.storage = this.tileCB.Storage;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public void b(int i, int j)
/* 91:   */   {
/* 92:77 */     switch (i)
/* 93:   */     {
/* 94:   */     case 0: 
/* 95:78 */       this.tileCB.cond.Charge = j; break;
/* 96:   */     case 1: 
/* 97:79 */       this.tileCB.Storage = j;
/* 98:   */     }
/* 99:   */   }
/* :0:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerChargingBench
 * JD-Core Version:    0.7.0.1
 */