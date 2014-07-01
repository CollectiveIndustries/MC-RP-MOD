/*   1:    */ package com.eloraam.redpower.world;
/*   2:    */ 
/*   3:    */ import aje;
/*   4:    */ import amq;
/*   5:    */ import com.eloraam.redpower.RedPowerWorld;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.Random;
/*   8:    */ import up;
/*   9:    */ import ur;
/*  10:    */ import yc;
/*  11:    */ import ym;
/*  12:    */ 
/*  13:    */ public class BlockCustomCrops
/*  14:    */   extends aje
/*  15:    */ {
/*  16:    */   public BlockCustomCrops(int i)
/*  17:    */   {
/*  18: 17 */     super(i, 0);
/*  19: 18 */     c(0.0F);
/*  20: 19 */     a(g);
/*  21: 20 */     b(true);
/*  22: 21 */     r();
/*  23: 22 */     a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int a(int i, int j)
/*  27:    */   {
/*  28: 26 */     switch (j)
/*  29:    */     {
/*  30:    */     case 0: 
/*  31: 27 */       return 64;
/*  32:    */     case 1: 
/*  33: 28 */       return 65;
/*  34:    */     case 2: 
/*  35: 29 */       return 66;
/*  36:    */     case 3: 
/*  37: 30 */       return 67;
/*  38:    */     case 4: 
/*  39: 31 */       return 68;
/*  40:    */     case 5: 
/*  41: 32 */       return 69;
/*  42:    */     }
/*  43: 34 */     return 69;
/*  44:    */   }
/*  45:    */   
/*  46:    */   protected boolean d_(int par1)
/*  47:    */   {
/*  48: 41 */     return par1 == amq.aD.cm;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void a(ym iba, int i, int j, int k)
/*  52:    */   {
/*  53: 46 */     int md = iba.h(i, j, k);
/*  54: 47 */     float h = Math.min(1.0F, 0.1F + 0.25F * md);
/*  55: 48 */     a(0.0F, 0.0F, 0.0F, 1.0F, h, 1.0F);
/*  56:    */   }
/*  57:    */   
/*  58:    */   public int d()
/*  59:    */   {
/*  60: 51 */     return 6;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public int a(int i, Random random, int j)
/*  64:    */   {
/*  65: 55 */     return -1;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean fertilize(yc world, int i, int j, int k)
/*  69:    */   {
/*  70: 59 */     if (world.m(i, j + 1, k) < 9) {
/*  71: 59 */       return false;
/*  72:    */     }
/*  73: 60 */     int md = world.h(i, j, k);
/*  74: 61 */     if ((md == 4) || (md == 5)) {
/*  75: 61 */       return false;
/*  76:    */     }
/*  77: 62 */     if ((world.a(i, j - 1, k) != amq.aD.cm) || (!world.c(i, j + 1, k))) {
/*  78: 63 */       return false;
/*  79:    */     }
/*  80: 65 */     world.c(i, j, k, 4);
/*  81: 66 */     world.d(i, j + 1, k, this.cm, 5);
/*  82: 67 */     return true;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public ArrayList getBlockDropped(yc world, int i, int j, int k, int md, int fortune)
/*  86:    */   {
/*  87: 72 */     ArrayList tr = new ArrayList();
/*  88: 73 */     if ((md == 4) || (md == 5))
/*  89:    */     {
/*  90: 74 */       int q = 1 + world.t.nextInt(3) + world.t.nextInt(1 + fortune);
/*  91: 76 */       while (q-- > 0) {
/*  92: 76 */         tr.add(new ur(up.K));
/*  93:    */       }
/*  94:    */     }
/*  95: 78 */     for (int n = 0; n < 3 + fortune; n++)
/*  96:    */     {
/*  97: 79 */       if (md == 5) {
/*  98: 79 */         md = 4;
/*  99:    */       }
/* 100: 80 */       if (world.t.nextInt(8) <= md) {
/* 101: 81 */         tr.add(new ur(RedPowerWorld.itemSeeds, 1, 0));
/* 102:    */       }
/* 103:    */     }
/* 104: 84 */     return tr;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void b(yc world, int i, int j, int k, Random random)
/* 108:    */   {
/* 109: 89 */     super.b(world, i, j, k, random);
/* 110: 90 */     if (world.m(i, j + 1, k) < 9) {
/* 111: 90 */       return;
/* 112:    */     }
/* 113: 91 */     int md = world.h(i, j, k);
/* 114: 92 */     if ((md == 4) || (md == 5)) {
/* 115: 92 */       return;
/* 116:    */     }
/* 117: 94 */     if ((world.a(i, j - 1, k) != amq.aD.cm) || (world.h(i, j - 1, k) == 0) || (!world.c(i, j + 1, k))) {
/* 118: 96 */       return;
/* 119:    */     }
/* 120: 97 */     if (random.nextInt(30) == 0)
/* 121:    */     {
/* 122: 98 */       world.c(i, j, k, md + 1);
/* 123: 99 */       if (md == 3) {
/* 124:100 */         world.d(i, j + 1, k, this.cm, 5);
/* 125:    */       }
/* 126:    */     }
/* 127:    */   }
/* 128:    */   
/* 129:    */   public boolean d(yc world, int i, int j, int k)
/* 130:    */   {
/* 131:107 */     int md = world.h(i, j, k);
/* 132:108 */     if (md == 5)
/* 133:    */     {
/* 134:109 */       if (world.a(i, j - 1, k) != this.cm) {
/* 135:109 */         return false;
/* 136:    */       }
/* 137:110 */       if (world.h(i, j - 1, k) != 4) {
/* 138:110 */         return false;
/* 139:    */       }
/* 140:111 */       return true;
/* 141:    */     }
/* 142:113 */     if (world.a(i, j - 1, k) != amq.aD.cm) {
/* 143:114 */       return false;
/* 144:    */     }
/* 145:115 */     if (md == 4) {
/* 146:115 */       return true;
/* 147:    */     }
/* 148:116 */     if (!world.c(i, j + 1, k)) {
/* 149:116 */       return false;
/* 150:    */     }
/* 151:117 */     return true;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public String getTextureFile()
/* 155:    */   {
/* 156:121 */     return "/eloraam/world/world1.png";
/* 157:    */   }
/* 158:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockCustomCrops
 * JD-Core Version:    0.7.0.1
 */