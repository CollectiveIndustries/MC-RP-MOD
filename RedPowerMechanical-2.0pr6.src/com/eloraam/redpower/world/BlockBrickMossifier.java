/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import amd;
/*  4:   */ import amq;
/*  5:   */ import com.eloraam.redpower.core.WorldCoord;
/*  6:   */ import java.util.Random;
/*  7:   */ import yc;
/*  8:   */ 
/*  9:   */ public class BlockBrickMossifier
/* 10:   */   extends amd
/* 11:   */ {
/* 12:   */   public BlockBrickMossifier(int i)
/* 13:   */   {
/* 14:13 */     super(i);
/* 15:14 */     b(true);
/* 16:15 */     c(1.5F);
/* 17:16 */     b(10.0F);
/* 18:17 */     a(amq.h);
/* 19:18 */     b("stonebricksmooth");
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void b(yc world, int i, int j, int k, Random random)
/* 23:   */   {
/* 24:23 */     switch (world.h(i, j, k))
/* 25:   */     {
/* 26:   */     case 0: 
/* 27:24 */       crackBrick(world, i, j, k, random); break;
/* 28:   */     case 1: 
/* 29:25 */       spreadMoss(world, i, j, k, random);
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   private void crackBrick(yc world, int i, int j, int k, Random random)
/* 34:   */   {
/* 35:33 */     WorldCoord wc1 = new WorldCoord(i, j, k);
/* 36:   */     
/* 37:35 */     boolean lava = false;boolean water = false;
/* 38:36 */     for (int n = 0; n < 6; n++)
/* 39:   */     {
/* 40:37 */       WorldCoord wc2 = wc1.coordStep(n);
/* 41:38 */       int bid = world.a(wc2.x, wc2.y, wc2.z);
/* 42:40 */       if ((bid == amq.E.cm) || (bid == amq.D.cm)) {
/* 43:42 */         water = true;
/* 44:43 */       } else if ((bid == amq.G.cm) || (bid == amq.F.cm)) {
/* 45:45 */         lava = true;
/* 46:   */       }
/* 47:   */     }
/* 48:48 */     if ((!lava) || (!water)) {
/* 49:48 */       return;
/* 50:   */     }
/* 51:49 */     if (random.nextInt(2) != 0) {
/* 52:49 */       return;
/* 53:   */     }
/* 54:50 */     world.c(i, j, k, 2);
/* 55:   */   }
/* 56:   */   
/* 57:   */   private void spreadMoss(yc world, int i, int j, int k, Random random)
/* 58:   */   {
/* 59:56 */     if (!world.c(i, j + 1, k)) {
/* 60:56 */       return;
/* 61:   */     }
/* 62:57 */     if (world.k(i, j + 1, k)) {
/* 63:57 */       return;
/* 64:   */     }
/* 65:59 */     WorldCoord wc1 = new WorldCoord(i, j, k);
/* 66:61 */     for (int n = 0; n < 4; n++)
/* 67:   */     {
/* 68:62 */       WorldCoord wc2 = wc1.coordStep(2 + n);
/* 69:63 */       int bid = world.a(wc2.x, wc2.y, wc2.z);
/* 70:   */       
/* 71:65 */       int rpb = bid;int rpmd = 0;
/* 72:66 */       if (bid == amq.z.cm)
/* 73:   */       {
/* 74:67 */         rpb = amq.ar.cm;
/* 75:   */       }
/* 76:   */       else
/* 77:   */       {
/* 78:68 */         if ((bid != amq.bp.cm) || 
/* 79:69 */           (world.h(wc2.x, wc2.y, wc2.z) != 0)) {
/* 80:   */           continue;
/* 81:   */         }
/* 82:71 */         rpmd = 1;
/* 83:   */       }
/* 84:73 */       if (world.a(wc2.x, wc2.y + 1, wc2.z) == 0)
/* 85:   */       {
/* 86:75 */         if (world.k(wc2.x, wc2.y + 1, wc2.z)) {
/* 87:75 */           return;
/* 88:   */         }
/* 89:77 */         boolean wet = false;
/* 90:78 */         for (int m = 0; m < 4; m++)
/* 91:   */         {
/* 92:79 */           WorldCoord wc3 = wc2.coordStep(2 + m);
/* 93:80 */           int bd2 = world.a(wc3.x, wc3.y, wc3.z);
/* 94:81 */           if ((bd2 == amq.E.cm) || (bd2 == amq.D.cm))
/* 95:   */           {
/* 96:84 */             wet = true;
/* 97:85 */             break;
/* 98:   */           }
/* 99:   */         }
/* :0:88 */         if ((wet) && 
/* :1:89 */           (random.nextInt(2) == 0)) {
/* :2:90 */           world.d(wc2.x, wc2.y, wc2.z, rpb, rpmd);
/* :3:   */         }
/* :4:   */       }
/* :5:   */     }
/* :6:   */   }
/* :7:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockBrickMossifier
 * JD-Core Version:    0.7.0.1
 */