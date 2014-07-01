/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import qw;
/*  5:   */ import qx;
/*  6:   */ import rq;
/*  7:   */ import rw;
/*  8:   */ import sr;
/*  9:   */ import ur;
/* 10:   */ 
/* 11:   */ public class ContainerAlloyFurnace
/* 12:   */   extends rq
/* 13:   */ {
/* 14:   */   public ContainerAlloyFurnace(qw inv, TileAlloyFurnace td)
/* 15:   */   {
/* 16:15 */     this.tileFurnace = td;
/* 17:17 */     for (int i = 0; i < 3; i++) {
/* 18:17 */       for (int j = 0; j < 3; j++) {
/* 19:18 */         a(new sr(td, j + i * 3, 48 + j * 18, 17 + i * 18));
/* 20:   */       }
/* 21:   */     }
/* 22:20 */     a(new sr(td, 9, 17, 42));
/* 23:21 */     a(new SlotAlloyFurnace(inv.d, td, 10, 141, 35));
/* 24:23 */     for (i = 0; i < 3; i++) {
/* 25:23 */       for (int j = 0; j < 9; j++) {
/* 26:24 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/* 27:   */       }
/* 28:   */     }
/* 29:26 */     for (i = 0; i < 9; i++) {
/* 30:27 */       a(new sr(inv, i, 8 + i * 18, 142));
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean a(qx player)
/* 35:   */   {
/* 36:32 */     return this.tileFurnace.a_(player);
/* 37:   */   }
/* 38:   */   
/* 39:   */   public ur b(qx player, int i)
/* 40:   */   {
/* 41:37 */     ur itemstack = null;
/* 42:38 */     sr slot = (sr)this.c.get(i);
/* 43:39 */     if ((slot != null) && (slot.d()))
/* 44:   */     {
/* 45:40 */       ur itemstack1 = slot.c();
/* 46:41 */       itemstack = itemstack1.l();
/* 47:42 */       if (i < 11)
/* 48:   */       {
/* 49:43 */         if (!a(itemstack1, 11, 47, true)) {
/* 50:44 */           return null;
/* 51:   */         }
/* 52:   */       }
/* 53:46 */       else if (!a(itemstack1, 0, 9, false)) {
/* 54:47 */         return null;
/* 55:   */       }
/* 56:49 */       if (itemstack1.a == 0) {
/* 57:50 */         slot.c(null);
/* 58:   */       } else {
/* 59:52 */         slot.e();
/* 60:   */       }
/* 61:54 */       if (itemstack1.a != itemstack.a) {
/* 62:55 */         slot.a(player, itemstack1);
/* 63:   */       } else {
/* 64:57 */         return null;
/* 65:   */       }
/* 66:   */     }
/* 67:60 */     return itemstack;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void b()
/* 71:   */   {
/* 72:64 */     super.b();
/* 73:65 */     for (int i = 0; i < this.e.size(); i++)
/* 74:   */     {
/* 75:66 */       rw ic = (rw)this.e.get(i);
/* 76:67 */       if (this.totalburn != this.tileFurnace.totalburn) {
/* 77:68 */         ic.a(this, 0, this.tileFurnace.totalburn);
/* 78:   */       }
/* 79:71 */       if (this.burntime != this.tileFurnace.burntime) {
/* 80:72 */         ic.a(this, 1, this.tileFurnace.burntime);
/* 81:   */       }
/* 82:75 */       if (this.cooktime != this.tileFurnace.cooktime) {
/* 83:76 */         ic.a(this, 2, this.tileFurnace.cooktime);
/* 84:   */       }
/* 85:   */     }
/* 86:80 */     this.totalburn = this.tileFurnace.totalburn;
/* 87:81 */     this.cooktime = this.tileFurnace.cooktime;
/* 88:82 */     this.burntime = this.tileFurnace.burntime;
/* 89:   */   }
/* 90:   */   
/* 91:   */   public void func_20112_a(int i, int j)
/* 92:   */   {
/* 93:87 */     b(i, j);
/* 94:   */   }
/* 95:   */   
/* 96:   */   public void b(int i, int j)
/* 97:   */   {
/* 98:91 */     switch (i)
/* 99:   */     {
/* :0:   */     case 0: 
/* :1:92 */       this.tileFurnace.totalburn = j; break;
/* :2:   */     case 1: 
/* :3:93 */       this.tileFurnace.burntime = j; break;
/* :4:   */     case 2: 
/* :5:94 */       this.tileFurnace.cooktime = j;
/* :6:   */     }
/* :7:   */   }
/* :8:   */   
/* :9:99 */   public int cooktime = 0;
/* ;0:99 */   public int burntime = 0;
/* ;1:99 */   public int totalburn = 0;
/* ;2:   */   private TileAlloyFurnace tileFurnace;
/* ;3:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ContainerAlloyFurnace
 * JD-Core Version:    0.7.0.1
 */