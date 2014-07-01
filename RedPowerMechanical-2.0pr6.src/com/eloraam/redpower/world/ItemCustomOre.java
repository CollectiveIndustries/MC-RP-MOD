/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import ur;
/*  4:   */ import vq;
/*  5:   */ 
/*  6:   */ public class ItemCustomOre
/*  7:   */   extends vq
/*  8:   */ {
/*  9:   */   public ItemCustomOre(int i)
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
/* 31:29 */       return "tile.oreRuby";
/* 32:   */     case 1: 
/* 33:30 */       return "tile.oreGreenSapphire";
/* 34:   */     case 2: 
/* 35:31 */       return "tile.oreSapphire";
/* 36:   */     case 3: 
/* 37:32 */       return "tile.oreSilver";
/* 38:   */     case 4: 
/* 39:33 */       return "tile.oreTin";
/* 40:   */     case 5: 
/* 41:34 */       return "tile.oreCopper";
/* 42:   */     case 6: 
/* 43:35 */       return "tile.oreTungsten";
/* 44:   */     case 7: 
/* 45:36 */       return "tile.oreNikolite";
/* 46:   */     }
/* 47:38 */     throw new IndexOutOfBoundsException();
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemCustomOre
 * JD-Core Version:    0.7.0.1
 */