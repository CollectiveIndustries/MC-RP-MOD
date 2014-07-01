/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import amq;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import ur;
/*  7:   */ import yc;
/*  8:   */ 
/*  9:   */ public class BlockCustomLog
/* 10:   */   extends amq
/* 11:   */ {
/* 12:   */   public BlockCustomLog(int i)
/* 13:   */   {
/* 14:14 */     super(i, agi.d);
/* 15:15 */     c(1.5F);
/* 16:16 */     a(amq.e);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int a(int i, int j)
/* 20:   */   {
/* 21:20 */     if (i < 2) {
/* 22:20 */       return 51;
/* 23:   */     }
/* 24:21 */     return 50;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int b(int i)
/* 28:   */   {
/* 29:26 */     if (i == 1) {
/* 30:26 */       return 0;
/* 31:   */     }
/* 32:27 */     return i;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean isWood(yc world, int x, int y, int z)
/* 36:   */   {
/* 37:32 */     return true;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void onBlockRemoval(yc world, int i, int j, int k)
/* 41:   */   {
/* 42:36 */     BlockCustomLeaves.updateLeaves(world, i, j, k, 4);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void addCreativeItems(ArrayList itemList)
/* 46:   */   {
/* 47:40 */     itemList.add(new ur(this, 1, 0));
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getTextureFile()
/* 51:   */   {
/* 52:44 */     return "/eloraam/world/world1.png";
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockCustomLog
 * JD-Core Version:    0.7.0.1
 */