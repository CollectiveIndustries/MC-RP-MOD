/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.base.ItemScrewdriver;
/*  4:   */ import com.eloraam.redpower.core.CoreLib;
/*  5:   */ import com.eloraam.redpower.core.IChargeable;
/*  6:   */ import lq;
/*  7:   */ import md;
/*  8:   */ import qx;
/*  9:   */ import ur;
/* 10:   */ import yc;
/* 11:   */ 
/* 12:   */ public class ItemSonicDriver
/* 13:   */   extends ItemScrewdriver
/* 14:   */   implements IChargeable
/* 15:   */ {
/* 16:   */   public ItemSonicDriver(int i)
/* 17:   */   {
/* 18:16 */     super(i);
/* 19:17 */     e(400);
/* 20:18 */     setNoRepair();
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean onItemUseFirst(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/* 24:   */   {
/* 25:25 */     if (CoreLib.isClient(world)) {
/* 26:25 */       return false;
/* 27:   */     }
/* 28:26 */     if (ist.j() == ist.k()) {
/* 29:27 */       return false;
/* 30:   */     }
/* 31:28 */     return super.onItemUseFirst(ist, player, world, i, j, k, l, xp, yp, zp);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public int a(lq entity)
/* 35:   */   {
/* 36:32 */     return 1;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public boolean a(ur itemstack, md entityliving, md player)
/* 40:   */   {
/* 41:37 */     return false;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ItemSonicDriver
 * JD-Core Version:    0.7.0.1
 */