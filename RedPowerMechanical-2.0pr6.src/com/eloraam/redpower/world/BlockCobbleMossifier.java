/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import amq;
/*  5:   */ import com.eloraam.redpower.core.WorldCoord;
/*  6:   */ import java.util.Random;
/*  7:   */ import tj;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class BlockCobbleMossifier
/* 11:   */   extends amq
/* 12:   */ {
/* 13:   */   public BlockCobbleMossifier(int i)
/* 14:   */   {
/* 15:13 */     super(i, 36, agi.e);
/* 16:14 */     b(true);
/* 17:15 */     c(2.0F);
/* 18:16 */     b(10.0F);
/* 19:17 */     a(amq.h);
/* 20:18 */     b("stoneMoss");
/* 21:19 */     a(tj.b);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void b(yc world, int i, int j, int k, Random random)
/* 25:   */   {
/* 26:25 */     if (!world.c(i, j + 1, k)) {
/* 27:25 */       return;
/* 28:   */     }
/* 29:26 */     if (world.k(i, j + 1, k)) {
/* 30:26 */       return;
/* 31:   */     }
/* 32:28 */     WorldCoord wc1 = new WorldCoord(i, j, k);
/* 33:30 */     for (int n = 0; n < 4; n++)
/* 34:   */     {
/* 35:31 */       WorldCoord wc2 = wc1.coordStep(2 + n);
/* 36:32 */       int bid = world.a(wc2.x, wc2.y, wc2.z);
/* 37:   */       
/* 38:34 */       int rpb = bid;int rpmd = 0;
/* 39:35 */       if (bid == amq.z.cm)
/* 40:   */       {
/* 41:36 */         rpb = this.cm;
/* 42:   */       }
/* 43:   */       else
/* 44:   */       {
/* 45:37 */         if ((bid != amq.bp.cm) || 
/* 46:38 */           (world.h(wc2.x, wc2.y, wc2.z) != 0)) {
/* 47:   */           continue;
/* 48:   */         }
/* 49:40 */         rpmd = 1;
/* 50:   */       }
/* 51:42 */       if (world.a(wc2.x, wc2.y + 1, wc2.z) == 0)
/* 52:   */       {
/* 53:44 */         if (world.k(wc2.x, wc2.y + 1, wc2.z)) {
/* 54:44 */           return;
/* 55:   */         }
/* 56:46 */         boolean wet = false;
/* 57:47 */         for (int m = 0; m < 4; m++)
/* 58:   */         {
/* 59:48 */           WorldCoord wc3 = wc2.coordStep(2 + m);
/* 60:49 */           int bd2 = world.a(wc3.x, wc3.y, wc3.z);
/* 61:50 */           if ((bd2 == amq.E.cm) || (bd2 == amq.D.cm))
/* 62:   */           {
/* 63:53 */             wet = true;
/* 64:54 */             break;
/* 65:   */           }
/* 66:   */         }
/* 67:57 */         if ((wet) && 
/* 68:58 */           (random.nextInt(2) == 0)) {
/* 69:59 */           world.d(wc2.x, wc2.y, wc2.z, rpb, rpmd);
/* 70:   */         }
/* 71:   */       }
/* 72:   */     }
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockCobbleMossifier
 * JD-Core Version:    0.7.0.1
 */