/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import java.util.Arrays;
/*  5:   */ import java.util.HashSet;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import java.util.List;
/*  8:   */ import java.util.Random;
/*  9:   */ import yc;
/* 10:   */ 
/* 11:   */ public class WorldGenMarble
/* 12:   */   extends WorldGenCustomOre
/* 13:   */ {
/* 14:   */   public WorldGenMarble(int id, int meta, int num)
/* 15:   */   {
/* 16:15 */     super(id, meta, num);
/* 17:   */   }
/* 18:   */   
/* 19:18 */   LinkedList fillStack = new LinkedList();
/* 20:19 */   HashSet fillStackTest = new HashSet();
/* 21:   */   
/* 22:   */   private void addBlock(int i, int j, int k, int p)
/* 23:   */   {
/* 24:23 */     List sb = Arrays.asList(new Integer[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) });
/* 25:24 */     if (this.fillStackTest.contains(sb)) {
/* 26:24 */       return;
/* 27:   */     }
/* 28:25 */     this.fillStack.addLast(Arrays.asList(new Integer[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(p) }));
/* 29:26 */     this.fillStackTest.add(sb);
/* 30:   */   }
/* 31:   */   
/* 32:   */   private void searchBlock(yc world, int i, int j, int k, int p)
/* 33:   */   {
/* 34:32 */     if ((world.a(i - 1, j, k) == 0) || (world.a(i + 1, j, k) == 0) || (world.a(i, j - 1, k) == 0) || (world.a(i, j + 1, k) == 0) || (world.a(i, j, k - 1) == 0) || (world.a(i, j, k + 1) == 0)) {
/* 35:37 */       p = 6;
/* 36:   */     }
/* 37:39 */     addBlock(i - 1, j, k, p);addBlock(i + 1, j, k, p);
/* 38:40 */     addBlock(i, j - 1, k, p);addBlock(i, j + 1, k, p);
/* 39:41 */     addBlock(i, j, k - 1, p);addBlock(i, j, k + 1, p);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean a(yc world, Random random, int i, int j, int k)
/* 43:   */   {
/* 44:46 */     if (world.a(i, j, k) != 0) {
/* 45:46 */       return false;
/* 46:   */     }
/* 47:47 */     int l = j;
/* 48:49 */     while (world.a(i, l, k) != amq.w.cm)
/* 49:   */     {
/* 50:51 */       if (l > 96) {
/* 51:51 */         return false;
/* 52:   */       }
/* 53:52 */       l++;
/* 54:   */     }
/* 55:54 */     addBlock(i, l, k, 6);
/* 56:56 */     while ((this.fillStack.size() > 0) && (this.numberOfBlocks > 0))
/* 57:   */     {
/* 58:57 */       List sp1 = (List)this.fillStack.removeFirst();
/* 59:58 */       Integer[] sp = (Integer[])sp1.toArray();
/* 60:60 */       if (world.a(sp[0].intValue(), sp[1].intValue(), sp[2].intValue()) == amq.w.cm)
/* 61:   */       {
/* 62:62 */         world.c(sp[0].intValue(), sp[1].intValue(), sp[2].intValue(), this.minableBlockId, this.minableBlockMeta);
/* 63:64 */         if (sp[3].intValue() > 0) {
/* 64:65 */           searchBlock(world, sp[0].intValue(), sp[1].intValue(), sp[2].intValue(), sp[3].intValue() - 1);
/* 65:   */         }
/* 66:68 */         this.numberOfBlocks -= 1;
/* 67:   */       }
/* 68:   */     }
/* 69:71 */     return true;
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.WorldGenMarble
 * JD-Core Version:    0.7.0.1
 */