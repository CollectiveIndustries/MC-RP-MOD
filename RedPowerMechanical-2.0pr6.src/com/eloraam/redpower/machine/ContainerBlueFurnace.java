/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   4:    */ import java.util.List;
/*   5:    */ import qw;
/*   6:    */ import qx;
/*   7:    */ import rq;
/*   8:    */ import rw;
/*   9:    */ import se;
/*  10:    */ import sr;
/*  11:    */ import ur;
/*  12:    */ 
/*  13:    */ public class ContainerBlueFurnace
/*  14:    */   extends rq
/*  15:    */ {
/*  16:    */   public ContainerBlueFurnace(qw inv, TileBlueFurnace td)
/*  17:    */   {
/*  18: 15 */     this.tileFurnace = td;
/*  19:    */     
/*  20: 17 */     a(new sr(td, 0, 62, 35));
/*  21: 18 */     a(new se(inv.d, td, 1, 126, 35));
/*  22: 20 */     for (int i = 0; i < 3; i++) {
/*  23: 20 */       for (int j = 0; j < 9; j++) {
/*  24: 21 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*  25:    */       }
/*  26:    */     }
/*  27: 23 */     for (i = 0; i < 9; i++) {
/*  28: 24 */       a(new sr(inv, i, 8 + i * 18, 142));
/*  29:    */     }
/*  30:    */   }
/*  31:    */   
/*  32:    */   public boolean a(qx player)
/*  33:    */   {
/*  34: 29 */     return this.tileFurnace.a_(player);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public ur b(qx player, int i)
/*  38:    */   {
/*  39: 34 */     ur itemstack = null;
/*  40: 35 */     sr slot = (sr)this.c.get(i);
/*  41: 36 */     if ((slot != null) && (slot.d()))
/*  42:    */     {
/*  43: 37 */       ur itemstack1 = slot.c();
/*  44: 38 */       itemstack = itemstack1.l();
/*  45: 39 */       if (i < 2)
/*  46:    */       {
/*  47: 40 */         if (!a(itemstack1, 2, 38, true)) {
/*  48: 41 */           return null;
/*  49:    */         }
/*  50:    */       }
/*  51: 43 */       else if (!a(itemstack1, 0, 1, false)) {
/*  52: 44 */         return null;
/*  53:    */       }
/*  54: 46 */       if (itemstack1.a == 0) {
/*  55: 47 */         slot.c(null);
/*  56:    */       } else {
/*  57: 49 */         slot.e();
/*  58:    */       }
/*  59: 51 */       if (itemstack1.a != itemstack.a) {
/*  60: 52 */         slot.a(player, itemstack1);
/*  61:    */       } else {
/*  62: 54 */         return null;
/*  63:    */       }
/*  64:    */     }
/*  65: 57 */     return itemstack;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void b()
/*  69:    */   {
/*  70: 61 */     super.b();
/*  71: 63 */     for (int i = 0; i < this.e.size(); i++)
/*  72:    */     {
/*  73: 64 */       rw ic = (rw)this.e.get(i);
/*  74: 65 */       if (this.cooktime != this.tileFurnace.cooktime) {
/*  75: 66 */         ic.a(this, 0, this.tileFurnace.cooktime);
/*  76:    */       }
/*  77: 69 */       if (this.charge != this.tileFurnace.cond.Charge) {
/*  78: 70 */         ic.a(this, 1, this.tileFurnace.cond.Charge);
/*  79:    */       }
/*  80: 73 */       if (this.flow != this.tileFurnace.cond.Flow)
/*  81:    */       {
/*  82: 74 */         ic.a(this, 2, this.tileFurnace.cond.Flow & 0xFFFF);
/*  83:    */         
/*  84: 76 */         ic.a(this, 3, this.tileFurnace.cond.Flow >> 16 & 0xFFFF);
/*  85:    */       }
/*  86:    */     }
/*  87: 80 */     this.cooktime = this.tileFurnace.cooktime;
/*  88: 81 */     this.charge = this.tileFurnace.cond.Charge;
/*  89: 82 */     this.flow = this.tileFurnace.cond.Flow;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void func_20112_a(int i, int j)
/*  93:    */   {
/*  94: 87 */     b(i, j);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void b(int i, int j)
/*  98:    */   {
/*  99: 91 */     switch (i)
/* 100:    */     {
/* 101:    */     case 0: 
/* 102: 92 */       this.tileFurnace.cooktime = j; break;
/* 103:    */     case 1: 
/* 104: 93 */       this.tileFurnace.cond.Charge = j; break;
/* 105:    */     case 2: 
/* 106: 95 */       this.tileFurnace.cond.Flow = (this.tileFurnace.cond.Flow & 0xFFFF0000 | j & 0xFFFF);
/* 107:    */       
/* 108: 97 */       break;
/* 109:    */     case 3: 
/* 110: 99 */       this.tileFurnace.cond.Flow = (this.tileFurnace.cond.Flow & 0xFFFF | (j & 0xFFFF) << 16);
/* 111:    */     }
/* 112:    */   }
/* 113:    */   
/* 114:105 */   public int cooktime = 0;
/* 115:    */   private TileBlueFurnace tileFurnace;
/* 116:107 */   public int charge = 0;
/* 117:107 */   public int flow = 0;
/* 118:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerBlueFurnace
 * JD-Core Version:    0.7.0.1
 */