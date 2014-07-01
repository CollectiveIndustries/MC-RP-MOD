/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import abm;
/*  4:   */ import amq;
/*  5:   */ import java.util.Random;
/*  6:   */ import ke;
/*  7:   */ import yc;
/*  8:   */ 
/*  9:   */ public class WorldGenCustomOre
/* 10:   */   extends abm
/* 11:   */ {
/* 12:   */   protected int minableBlockId;
/* 13:   */   protected int minableBlockMeta;
/* 14:   */   protected int numberOfBlocks;
/* 15:   */   
/* 16:   */   public WorldGenCustomOre(int id, int meta, int num)
/* 17:   */   {
/* 18:17 */     this.minableBlockId = id;
/* 19:18 */     this.minableBlockMeta = meta;
/* 20:19 */     this.numberOfBlocks = num;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void tryGenerateBlock(yc world, Random random, int i, int j, int k)
/* 24:   */   {
/* 25:24 */     if (world.a(i, j, k) == amq.w.cm) {
/* 26:25 */       world.c(i, j, k, this.minableBlockId, this.minableBlockMeta);
/* 27:   */     }
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean a(yc world, Random random, int i, int j, int k)
/* 31:   */   {
/* 32:31 */     float f = random.nextFloat() * 3.141593F;
/* 33:32 */     double d = i + 8 + ke.a(f) * this.numberOfBlocks / 8.0F;
/* 34:33 */     double d1 = i + 8 - ke.a(f) * this.numberOfBlocks / 8.0F;
/* 35:34 */     double d2 = k + 8 + ke.b(f) * this.numberOfBlocks / 8.0F;
/* 36:35 */     double d3 = k + 8 - ke.b(f) * this.numberOfBlocks / 8.0F;
/* 37:36 */     double d4 = j + random.nextInt(3) + 2;
/* 38:37 */     double d5 = j + random.nextInt(3) + 2;
/* 39:38 */     for (int l = 0; l <= this.numberOfBlocks; l++)
/* 40:   */     {
/* 41:40 */       double d6 = d + (d1 - d) * l / this.numberOfBlocks;
/* 42:41 */       double d7 = d4 + (d5 - d4) * l / this.numberOfBlocks;
/* 43:42 */       double d8 = d2 + (d3 - d2) * l / this.numberOfBlocks;
/* 44:43 */       double d9 = random.nextDouble() * this.numberOfBlocks / 16.0D;
/* 45:44 */       double d10 = (ke.a(l * 3.141593F / this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
/* 46:45 */       double d11 = (ke.a(l * 3.141593F / this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
/* 47:46 */       int i1 = ke.c(d6 - d10 / 2.0D);
/* 48:47 */       int j1 = ke.c(d7 - d11 / 2.0D);
/* 49:48 */       int k1 = ke.c(d8 - d10 / 2.0D);
/* 50:49 */       int l1 = ke.c(d6 + d10 / 2.0D);
/* 51:50 */       int i2 = ke.c(d7 + d11 / 2.0D);
/* 52:51 */       int j2 = ke.c(d8 + d10 / 2.0D);
/* 53:52 */       for (int k2 = i1; k2 <= l1; k2++)
/* 54:   */       {
/* 55:54 */         double d12 = (k2 + 0.5D - d6) / (d10 / 2.0D);
/* 56:55 */         if (d12 * d12 < 1.0D) {
/* 57:59 */           for (int l2 = j1; l2 <= i2; l2++)
/* 58:   */           {
/* 59:61 */             double d13 = (l2 + 0.5D - d7) / (d11 / 2.0D);
/* 60:62 */             if (d12 * d12 + d13 * d13 < 1.0D) {
/* 61:66 */               for (int i3 = k1; i3 <= j2; i3++)
/* 62:   */               {
/* 63:68 */                 double d14 = (i3 + 0.5D - d8) / (d10 / 2.0D);
/* 64:69 */                 if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
/* 65:70 */                   tryGenerateBlock(world, random, k2, l2, i3);
/* 66:   */                 }
/* 67:   */               }
/* 68:   */             }
/* 69:   */           }
/* 70:   */         }
/* 71:   */       }
/* 72:   */     }
/* 73:79 */     return true;
/* 74:   */   }
/* 75:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.WorldGenCustomOre
 * JD-Core Version:    0.7.0.1
 */