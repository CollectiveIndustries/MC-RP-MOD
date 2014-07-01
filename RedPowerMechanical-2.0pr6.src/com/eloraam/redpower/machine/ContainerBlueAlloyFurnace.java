/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.base.SlotAlloyFurnace;
/*   4:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   5:    */ import java.util.List;
/*   6:    */ import qw;
/*   7:    */ import qx;
/*   8:    */ import rq;
/*   9:    */ import rw;
/*  10:    */ import sr;
/*  11:    */ import ur;
/*  12:    */ 
/*  13:    */ public class ContainerBlueAlloyFurnace
/*  14:    */   extends rq
/*  15:    */ {
/*  16:    */   public ContainerBlueAlloyFurnace(qw inv, TileBlueAlloyFurnace td)
/*  17:    */   {
/*  18: 17 */     this.tileFurnace = td;
/*  19: 19 */     for (int i = 0; i < 3; i++) {
/*  20: 19 */       for (int j = 0; j < 3; j++) {
/*  21: 20 */         a(new sr(td, j + i * 3, 48 + j * 18, 17 + i * 18));
/*  22:    */       }
/*  23:    */     }
/*  24: 22 */     a(new SlotAlloyFurnace(inv.d, td, 9, 141, 35));
/*  25: 24 */     for (i = 0; i < 3; i++) {
/*  26: 24 */       for (int j = 0; j < 9; j++) {
/*  27: 25 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*  28:    */       }
/*  29:    */     }
/*  30: 27 */     for (i = 0; i < 9; i++) {
/*  31: 28 */       a(new sr(inv, i, 8 + i * 18, 142));
/*  32:    */     }
/*  33:    */   }
/*  34:    */   
/*  35:    */   public boolean a(qx player)
/*  36:    */   {
/*  37: 33 */     return this.tileFurnace.a_(player);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public ur b(qx player, int i)
/*  41:    */   {
/*  42: 38 */     ur itemstack = null;
/*  43: 39 */     sr slot = (sr)this.c.get(i);
/*  44: 40 */     if ((slot != null) && (slot.d()))
/*  45:    */     {
/*  46: 41 */       ur itemstack1 = slot.c();
/*  47: 42 */       itemstack = itemstack1.l();
/*  48: 43 */       if (i < 10)
/*  49:    */       {
/*  50: 44 */         if (!a(itemstack1, 10, 46, true)) {
/*  51: 45 */           return null;
/*  52:    */         }
/*  53:    */       }
/*  54: 47 */       else if (!a(itemstack1, 0, 9, false)) {
/*  55: 48 */         return null;
/*  56:    */       }
/*  57: 50 */       if (itemstack1.a == 0) {
/*  58: 51 */         slot.c(null);
/*  59:    */       } else {
/*  60: 53 */         slot.e();
/*  61:    */       }
/*  62: 55 */       if (itemstack1.a != itemstack.a) {
/*  63: 56 */         slot.a(player, itemstack1);
/*  64:    */       } else {
/*  65: 58 */         return null;
/*  66:    */       }
/*  67:    */     }
/*  68: 61 */     return itemstack;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void b()
/*  72:    */   {
/*  73: 65 */     super.b();
/*  74: 67 */     for (int i = 0; i < this.e.size(); i++)
/*  75:    */     {
/*  76: 68 */       rw ic = (rw)this.e.get(i);
/*  77: 69 */       if (this.cooktime != this.tileFurnace.cooktime) {
/*  78: 70 */         ic.a(this, 0, this.tileFurnace.cooktime);
/*  79:    */       }
/*  80: 73 */       if (this.charge != this.tileFurnace.cond.Charge) {
/*  81: 74 */         ic.a(this, 1, this.tileFurnace.cond.Charge);
/*  82:    */       }
/*  83: 77 */       if (this.flow != this.tileFurnace.cond.Flow)
/*  84:    */       {
/*  85: 78 */         ic.a(this, 2, this.tileFurnace.cond.Flow & 0xFFFF);
/*  86:    */         
/*  87: 80 */         ic.a(this, 3, this.tileFurnace.cond.Flow >> 16 & 0xFFFF);
/*  88:    */       }
/*  89:    */     }
/*  90: 84 */     this.cooktime = this.tileFurnace.cooktime;
/*  91: 85 */     this.charge = this.tileFurnace.cond.Charge;
/*  92: 86 */     this.flow = this.tileFurnace.cond.Flow;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void func_20112_a(int i, int j)
/*  96:    */   {
/*  97: 91 */     b(i, j);
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void b(int i, int j)
/* 101:    */   {
/* 102: 95 */     switch (i)
/* 103:    */     {
/* 104:    */     case 0: 
/* 105: 96 */       this.tileFurnace.cooktime = j; break;
/* 106:    */     case 1: 
/* 107: 97 */       this.tileFurnace.cond.Charge = j; break;
/* 108:    */     case 2: 
/* 109: 99 */       this.tileFurnace.cond.Flow = (this.tileFurnace.cond.Flow & 0xFFFF0000 | j & 0xFFFF);
/* 110:    */       
/* 111:101 */       break;
/* 112:    */     case 3: 
/* 113:103 */       this.tileFurnace.cond.Flow = (this.tileFurnace.cond.Flow & 0xFFFF | (j & 0xFFFF) << 16);
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:109 */   public int cooktime = 0;
/* 118:    */   private TileBlueAlloyFurnace tileFurnace;
/* 119:111 */   public int charge = 0;
/* 120:111 */   public int flow = 0;
/* 121:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerBlueAlloyFurnace
 * JD-Core Version:    0.7.0.1
 */