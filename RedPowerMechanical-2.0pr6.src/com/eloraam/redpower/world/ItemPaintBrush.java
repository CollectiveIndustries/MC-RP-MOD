/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import aoh;
/*  4:   */ import com.eloraam.redpower.RedPowerWorld;
/*  5:   */ import com.eloraam.redpower.core.CoreLib;
/*  6:   */ import com.eloraam.redpower.core.IPaintable;
/*  7:   */ import qx;
/*  8:   */ import tj;
/*  9:   */ import up;
/* 10:   */ import ur;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class ItemPaintBrush
/* 14:   */   extends up
/* 15:   */ {
/* 16:   */   int color;
/* 17:   */   
/* 18:   */   public ItemPaintBrush(int i, int col)
/* 19:   */   {
/* 20:17 */     super(i);
/* 21:18 */     this.color = col;
/* 22:19 */     c(112 + col);
/* 23:20 */     d(1);
/* 24:21 */     e(15);
/* 25:22 */     setNoRepair();
/* 26:23 */     a(tj.i);
/* 27:   */   }
/* 28:   */   
/* 29:   */   private boolean itemUseShared(ur ist, qx player, yc world, int i, int j, int k, int l)
/* 30:   */   {
/* 31:30 */     IPaintable ip = (IPaintable)CoreLib.getTileEntity(world, i, j, k, IPaintable.class);
/* 32:32 */     if (ip == null) {
/* 33:32 */       return false;
/* 34:   */     }
/* 35:33 */     aoh mop = CoreLib.retraceBlock(world, player, i, j, k);
/* 36:35 */     if (mop == null) {
/* 37:35 */       return false;
/* 38:   */     }
/* 39:36 */     if (!ip.tryPaint(mop.subHit, mop.e, this.color + 1)) {
/* 40:37 */       return false;
/* 41:   */     }
/* 42:39 */     ist.a(1, player);
/* 43:40 */     if (ist.a == 0)
/* 44:   */     {
/* 45:41 */       ist.a = 1;
/* 46:42 */       ist.c = RedPowerWorld.itemBrushDry.cj;
/* 47:   */     }
/* 48:44 */     return true;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public boolean a(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/* 52:   */   {
/* 53:52 */     if (CoreLib.isClient(world)) {
/* 54:52 */       return false;
/* 55:   */     }
/* 56:53 */     if (player.ah()) {
/* 57:53 */       return false;
/* 58:   */     }
/* 59:54 */     return itemUseShared(ist, player, world, i, j, k, l);
/* 60:   */   }
/* 61:   */   
/* 62:   */   public boolean onItemUseFirst(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/* 63:   */   {
/* 64:62 */     if (CoreLib.isClient(world)) {
/* 65:62 */       return false;
/* 66:   */     }
/* 67:63 */     if (!player.ah()) {
/* 68:63 */       return false;
/* 69:   */     }
/* 70:64 */     return itemUseShared(ist, player, world, i, j, k, l);
/* 71:   */   }
/* 72:   */   
/* 73:   */   public String getTextureFile()
/* 74:   */   {
/* 75:69 */     return "/eloraam/base/items1.png";
/* 76:   */   }
/* 77:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemPaintBrush
 * JD-Core Version:    0.7.0.1
 */