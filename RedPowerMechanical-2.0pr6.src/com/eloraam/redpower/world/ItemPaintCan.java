/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerWorld;
/*  4:   */ import com.eloraam.redpower.core.ItemPartialCraft;
/*  5:   */ import qw;
/*  6:   */ import qx;
/*  7:   */ import tj;
/*  8:   */ import up;
/*  9:   */ import ur;
/* 10:   */ import yc;
/* 11:   */ 
/* 12:   */ public class ItemPaintCan
/* 13:   */   extends ItemPartialCraft
/* 14:   */ {
/* 15:   */   int color;
/* 16:   */   
/* 17:   */   public ItemPaintCan(int i, int col)
/* 18:   */   {
/* 19:16 */     super(i);
/* 20:17 */     this.color = col;
/* 21:18 */     c(96 + col);
/* 22:19 */     e(15);
/* 23:20 */     a(tj.i);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public ur a(ur ist, yc world, qx player)
/* 27:   */   {
/* 28:25 */     for (int n = 0; n < 9; n++)
/* 29:   */     {
/* 30:26 */       ur isl = player.bJ.a(n);
/* 31:27 */       if ((isl != null) && 
/* 32:28 */         (isl.c == RedPowerWorld.itemBrushDry.cj) && 
/* 33:29 */         (isl.a == 1))
/* 34:   */       {
/* 35:30 */         player.bJ.a(n, new ur(RedPowerWorld.itemBrushPaint[this.color]));
/* 36:   */         
/* 37:   */ 
/* 38:33 */         ist.a(1, player);
/* 39:34 */         if (ist.a == 0) {
/* 40:35 */           return new ur(RedPowerWorld.itemPaintCanEmpty);
/* 41:   */         }
/* 42:38 */         return ist;
/* 43:   */       }
/* 44:   */     }
/* 45:41 */     return ist;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public String getTextureFile()
/* 49:   */   {
/* 50:45 */     return "/eloraam/base/items1.png";
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemPaintCan
 * JD-Core Version:    0.7.0.1
 */