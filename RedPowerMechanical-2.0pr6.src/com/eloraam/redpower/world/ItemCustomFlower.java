/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import ur;
/*  4:   */ import vq;
/*  5:   */ 
/*  6:   */ public class ItemCustomFlower
/*  7:   */   extends vq
/*  8:   */ {
/*  9:   */   public ItemCustomFlower(int i)
/* 10:   */   {
/* 11:17 */     super(i);
/* 12:18 */     e(0);
/* 13:19 */     a(true);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int b(int i)
/* 17:   */   {
/* 18:23 */     switch (i)
/* 19:   */     {
/* 20:   */     case 0: 
/* 21:24 */       return 1;
/* 22:   */     case 1: 
/* 23:25 */       return 2;
/* 24:   */     }
/* 25:26 */     return 1;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int getPlacedBlockMetadata(int i)
/* 29:   */   {
/* 30:31 */     return i;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public int a(int i)
/* 34:   */   {
/* 35:35 */     return i;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public String d(ur itemstack)
/* 39:   */   {
/* 40:39 */     switch (itemstack.j())
/* 41:   */     {
/* 42:   */     case 0: 
/* 43:40 */       return "tile.indigo";
/* 44:   */     case 1: 
/* 45:41 */       return "tile.rubbersapling";
/* 46:   */     }
/* 47:43 */     throw new IndexOutOfBoundsException();
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getTextureFile()
/* 51:   */   {
/* 52:48 */     return "/eloraam/world/worlditems1.png";
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemCustomFlower
 * JD-Core Version:    0.7.0.1
 */