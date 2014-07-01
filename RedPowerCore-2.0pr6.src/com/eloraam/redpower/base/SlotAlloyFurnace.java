/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.AchieveLib;
/*  4:   */ import la;
/*  5:   */ import qx;
/*  6:   */ import sr;
/*  7:   */ import ur;
/*  8:   */ 
/*  9:   */ public class SlotAlloyFurnace
/* 10:   */   extends sr
/* 11:   */ {
/* 12:   */   private qx thePlayer;
/* 13:   */   int totalCrafted;
/* 14:   */   
/* 15:   */   public SlotAlloyFurnace(qx player, la inv, int i, int j, int k)
/* 16:   */   {
/* 17:17 */     super(inv, i, j, k);
/* 18:18 */     this.thePlayer = player;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean a(ur ist)
/* 22:   */   {
/* 23:22 */     return false;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public ur a(int num)
/* 27:   */   {
/* 28:26 */     if (d()) {
/* 29:27 */       this.totalCrafted += Math.min(num, c().a);
/* 30:   */     }
/* 31:29 */     return super.a(num);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void a(qx player, ur ist)
/* 35:   */   {
/* 36:34 */     b(ist);
/* 37:35 */     super.a(player, ist);
/* 38:   */   }
/* 39:   */   
/* 40:   */   protected void a(ur ist, int num)
/* 41:   */   {
/* 42:40 */     this.totalCrafted += num;
/* 43:41 */     b(ist);
/* 44:   */   }
/* 45:   */   
/* 46:   */   protected void b(ur ist)
/* 47:   */   {
/* 48:46 */     ist.a(this.thePlayer.p, this.thePlayer, this.totalCrafted);
/* 49:47 */     this.totalCrafted = 0;
/* 50:48 */     AchieveLib.onAlloy(this.thePlayer, ist);
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.SlotAlloyFurnace
 * JD-Core Version:    0.7.0.1
 */