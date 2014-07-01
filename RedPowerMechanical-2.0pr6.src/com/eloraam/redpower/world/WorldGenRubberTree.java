/*   1:    */ package com.eloraam.redpower.world;
/*   2:    */ 
/*   3:    */ import abm;
/*   4:    */ import akj;
/*   5:    */ import amm;
/*   6:    */ import amq;
/*   7:    */ import com.eloraam.redpower.RedPowerWorld;
/*   8:    */ import com.eloraam.redpower.core.FractalLib.BlockSnake;
/*   9:    */ import com.eloraam.redpower.core.Vector3;
/*  10:    */ import java.util.Random;
/*  11:    */ import yc;
/*  12:    */ 
/*  13:    */ public class WorldGenRubberTree
/*  14:    */   extends abm
/*  15:    */ {
/*  16:    */   public void putLeaves(yc world, int i, int j, int k)
/*  17:    */   {
/*  18: 19 */     int bid = world.a(i, j, k);
/*  19: 20 */     if (bid != 0) {
/*  20: 20 */       return;
/*  21:    */     }
/*  22: 21 */     world.c(i, j, k, RedPowerWorld.blockLeaves.cm, 0);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public boolean fillBlock(yc world, int i, int j, int k)
/*  26:    */   {
/*  27: 26 */     if ((j < 0) || (j > 126)) {
/*  28: 26 */       return false;
/*  29:    */     }
/*  30: 28 */     int bid = world.a(i, j, k);
/*  31: 29 */     amq bl = amq.p[bid];
/*  32: 31 */     if ((bl != null) && (bl.isWood(world, i, j, k))) {
/*  33: 31 */       return true;
/*  34:    */     }
/*  35: 32 */     if ((bl != null) && (!bl.isLeaves(world, i, j, k)) && (bid != amq.aa.cm) && (bid != amq.x.cm) && (bid != amq.bx.cm)) {
/*  36: 36 */       return false;
/*  37:    */     }
/*  38: 37 */     world.c(i, j, k, RedPowerWorld.blockLogs.cm, 0);
/*  39:    */     
/*  40: 39 */     putLeaves(world, i, j - 1, k);
/*  41: 40 */     putLeaves(world, i, j + 1, k);
/*  42: 41 */     putLeaves(world, i, j, k - 1);
/*  43: 42 */     putLeaves(world, i, j, k + 1);
/*  44: 43 */     putLeaves(world, i - 1, j, k);
/*  45: 44 */     putLeaves(world, i + 1, j, k);
/*  46: 45 */     return true;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public boolean a(yc world, Random random, int i, int j, int k)
/*  50:    */   {
/*  51: 53 */     int trh = random.nextInt(6) + 25;
/*  52: 54 */     if ((j < 1) || (j + trh + 2 > world.O())) {
/*  53: 54 */       return false;
/*  54:    */     }
/*  55: 56 */     for (int x = -1; x <= 1; x++) {
/*  56: 56 */       for (int z = -1; z <= 1; z++)
/*  57:    */       {
/*  58: 57 */         int bid = world.a(i + x, j - 1, k + z);
/*  59: 58 */         if ((bid != amq.x.cm) && (bid != amq.y.cm)) {
/*  60: 59 */           return false;
/*  61:    */         }
/*  62:    */       }
/*  63:    */     }
/*  64: 61 */     int rw = 1;
/*  65: 62 */     for (int y = j; y < j + trh; y++)
/*  66:    */     {
/*  67: 63 */       if (y > j + 3) {
/*  68: 63 */         rw = 5;
/*  69:    */       }
/*  70: 64 */       for (x = i - rw; x <= i + rw; x++) {
/*  71: 64 */         for (int z = k - rw; z <= k + rw; z++)
/*  72:    */         {
/*  73: 65 */           int bid = world.a(x, y, z);
/*  74: 66 */           amq bl = amq.p[bid];
/*  75: 67 */           if ((bl != null) && (!bl.isLeaves(world, x, y, z)) && (!bl.isWood(world, x, y, z)) && (bid != amq.aa.cm) && (bid != amq.x.cm) && (bid != amq.bx.cm)) {
/*  76: 73 */             return false;
/*  77:    */           }
/*  78:    */         }
/*  79:    */       }
/*  80:    */     }
/*  81: 77 */     for (x = -1; x <= 1; x++) {
/*  82: 77 */       for (int z = -1; z <= 1; z++) {
/*  83: 78 */         world.b(i + x, j - 1, k + z, amq.y.cm);
/*  84:    */       }
/*  85:    */     }
/*  86: 80 */     for (int y = 0; y <= 6; y++)
/*  87:    */     {
/*  88: 81 */       for (x = -1; x <= 1; x++) {
/*  89: 81 */         for (int z = -1; z <= 1; z++) {
/*  90: 82 */           world.d(i + x, j + y, k + z, RedPowerWorld.blockLogs.cm, 1);
/*  91:    */         }
/*  92:    */       }
/*  93: 85 */       for (x = -1; x <= 1; x++)
/*  94:    */       {
/*  95: 86 */         if ((random.nextInt(5) == 1) && (world.a(i + x, j + y, k - 2) == 0)) {
/*  96: 88 */           world.d(i + x, j + y, k - 2, amq.bx.cm, 1);
/*  97:    */         }
/*  98: 91 */         if ((random.nextInt(5) == 1) && (world.a(i + x, j + y, k + 2) == 0)) {
/*  99: 93 */           world.d(i + x, j + y, k + 2, amq.bx.cm, 4);
/* 100:    */         }
/* 101:    */       }
/* 102: 97 */       for (int z = -1; z <= 1; z++)
/* 103:    */       {
/* 104: 98 */         if ((random.nextInt(5) == 1) && (world.a(i - 2, j + y, k + z) == 0)) {
/* 105:100 */           world.d(i - 2, j + y, k + z, amq.bx.cm, 8);
/* 106:    */         }
/* 107:103 */         if ((random.nextInt(5) == 1) && (world.a(i + 2, j + y, k + z) == 0)) {
/* 108:105 */           world.d(i + 2, j + y, k + z, amq.bx.cm, 2);
/* 109:    */         }
/* 110:    */       }
/* 111:    */     }
/* 112:111 */     Vector3 org = new Vector3();
/* 113:112 */     Vector3 dest = new Vector3();
/* 114:    */     
/* 115:    */ 
/* 116:    */ 
/* 117:116 */     int nbr = random.nextInt(100) + 10;
/* 118:118 */     for (int br = 0; br < nbr; br++)
/* 119:    */     {
/* 120:122 */       dest.set(random.nextFloat() - 0.5D, random.nextFloat(), random.nextFloat() - 0.5D);
/* 121:    */       
/* 122:124 */       dest.normalize();
/* 123:125 */       double m = (nbr / 10.0D + 4.0D) * (1.0F + 1.0F * random.nextFloat());
/* 124:126 */       dest.x *= m;dest.z *= m;
/* 125:127 */       dest.y = (dest.y * (trh - 15) + nbr / 10.0D);
/* 126:128 */       if (nbr < 8) {
/* 127:129 */         switch (nbr)
/* 128:    */         {
/* 129:    */         case 0: 
/* 130:130 */           org.set(i - 1, j + 6, k - 1); break;
/* 131:    */         case 1: 
/* 132:131 */           org.set(i - 1, j + 6, k); break;
/* 133:    */         case 2: 
/* 134:132 */           org.set(i - 1, j + 6, k + 1); break;
/* 135:    */         case 3: 
/* 136:133 */           org.set(i, j + 6, k + 1); break;
/* 137:    */         case 4: 
/* 138:134 */           org.set(i + 1, j + 6, k + 1); break;
/* 139:    */         case 5: 
/* 140:135 */           org.set(i + 1, j + 6, k); break;
/* 141:    */         case 6: 
/* 142:136 */           org.set(i + 1, j + 6, k - 1); break;
/* 143:    */         default: 
/* 144:137 */           org.set(i, j + 6, k - 1); break;
/* 145:    */         }
/* 146:    */       } else {
/* 147:140 */         org.set(i + random.nextInt(3) - 1, j + 6, k + random.nextInt(3) - 1);
/* 148:    */       }
/* 149:143 */       long brseed = random.nextLong();
/* 150:    */       
/* 151:145 */       FractalLib.BlockSnake bsn = new FractalLib.BlockSnake(org, dest, brseed);
/* 152:147 */       while (bsn.iterate())
/* 153:    */       {
/* 154:148 */         Vector3 v = bsn.get();
/* 155:150 */         if (!fillBlock(world, (int)Math.floor(v.x), (int)Math.floor(v.y), (int)Math.floor(v.z))) {
/* 156:    */           break;
/* 157:    */         }
/* 158:    */       }
/* 159:    */     }
/* 160:157 */     return true;
/* 161:    */   }
/* 162:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.WorldGenRubberTree
 * JD-Core Version:    0.7.0.1
 */