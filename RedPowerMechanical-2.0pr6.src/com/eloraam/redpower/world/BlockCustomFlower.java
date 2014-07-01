/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import abm;
/*  4:   */ import aje;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.Random;
/*  7:   */ import ur;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class BlockCustomFlower
/* 11:   */   extends aje
/* 12:   */ {
/* 13:   */   public BlockCustomFlower(int i, int j)
/* 14:   */   {
/* 15:16 */     super(i, j);
/* 16:17 */     c(0.0F);
/* 17:18 */     a(g);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public int a(int i, int j)
/* 21:   */   {
/* 22:22 */     switch (j)
/* 23:   */     {
/* 24:   */     case 0: 
/* 25:23 */       return 1;
/* 26:   */     case 1: 
/* 27:24 */       return 2;
/* 28:   */     case 2: 
/* 29:25 */       return 2;
/* 30:   */     }
/* 31:26 */     return 1;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void b(yc world, int i, int j, int k, Random random)
/* 35:   */   {
/* 36:32 */     int md = world.h(i, j, k);
/* 37:33 */     if ((md != 1) && (md != 2)) {
/* 38:33 */       return;
/* 39:   */     }
/* 40:34 */     if ((world.m(i, j + 1, k) >= 9) && (random.nextInt(300) == 0)) {
/* 41:36 */       if (md == 1) {
/* 42:37 */         world.d(i, j, k, 2);
/* 43:38 */       } else if (md == 2) {
/* 44:39 */         growTree(world, i, j, k);
/* 45:   */       }
/* 46:   */     }
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void growTree(yc world, int i, int j, int k)
/* 50:   */   {
/* 51:45 */     world.b(i, j, k, 0);
/* 52:46 */     abm wg = new WorldGenRubberTree();
/* 53:47 */     if (!wg.a(world, world.t, i, j, k)) {
/* 54:48 */       world.c(i, j, k, this.cm, 1);
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   public int b(int i)
/* 59:   */   {
/* 60:53 */     if (i == 2) {
/* 61:53 */       return 1;
/* 62:   */     }
/* 63:54 */     return i;
/* 64:   */   }
/* 65:   */   
/* 66:   */   public void addCreativeItems(ArrayList itemList)
/* 67:   */   {
/* 68:58 */     for (int i = 0; i <= 1; i++) {
/* 69:59 */       itemList.add(new ur(this, 1, i));
/* 70:   */     }
/* 71:   */   }
/* 72:   */   
/* 73:   */   public String getTextureFile()
/* 74:   */   {
/* 75:64 */     return "/eloraam/world/worlditems1.png";
/* 76:   */   }
/* 77:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockCustomFlower
 * JD-Core Version:    0.7.0.1
 */