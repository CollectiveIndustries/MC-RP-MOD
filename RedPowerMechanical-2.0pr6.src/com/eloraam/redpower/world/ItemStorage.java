/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import ur;
/*  4:   */ import vq;
/*  5:   */ 
/*  6:   */ public class ItemStorage
/*  7:   */   extends vq
/*  8:   */ {
/*  9:   */   public ItemStorage(int i)
/* 10:   */   {
/* 11:14 */     super(i);
/* 12:15 */     e(0);
/* 13:16 */     a(true);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getPlacedBlockMetadata(int i)
/* 17:   */   {
/* 18:20 */     return i;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int a(int i)
/* 22:   */   {
/* 23:24 */     return i;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String d(ur itemstack)
/* 27:   */   {
/* 28:28 */     switch (itemstack.j())
/* 29:   */     {
/* 30:   */     case 0: 
/* 31:29 */       return "tile.blockRuby";
/* 32:   */     case 1: 
/* 33:30 */       return "tile.blockGreenSapphire";
/* 34:   */     case 2: 
/* 35:31 */       return "tile.blockSapphire";
/* 36:   */     case 3: 
/* 37:32 */       return "tile.blockSilver";
/* 38:   */     case 4: 
/* 39:33 */       return "tile.blockTin";
/* 40:   */     case 5: 
/* 41:34 */       return "tile.blockCopper";
/* 42:   */     }
/* 43:36 */     throw new IndexOutOfBoundsException();
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemStorage
 * JD-Core Version:    0.7.0.1
 */