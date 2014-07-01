/*   1:    */ package com.eloraam.redpower.world;
/*   2:    */ 
/*   3:    */ import aje;
/*   4:    */ import akt;
/*   5:    */ import amq;
/*   6:    */ import com.eloraam.redpower.RedPowerWorld;
/*   7:    */ import java.util.Arrays;
/*   8:    */ import java.util.HashMap;
/*   9:    */ import java.util.LinkedList;
/*  10:    */ import java.util.List;
/*  11:    */ import java.util.Random;
/*  12:    */ import yc;
/*  13:    */ 
/*  14:    */ public class WorldGenVolcano
/*  15:    */   extends WorldGenCustomOre
/*  16:    */ {
/*  17:    */   public WorldGenVolcano(int id, int meta, int num)
/*  18:    */   {
/*  19: 18 */     super(id, meta, num);
/*  20:    */   }
/*  21:    */   
/*  22: 21 */   LinkedList fillStack = new LinkedList();
/*  23: 22 */   HashMap fillStackTest = new HashMap();
/*  24:    */   
/*  25:    */   private void addBlock(int i, int j, int k, int p)
/*  26:    */   {
/*  27: 25 */     if (p <= 0) {
/*  28: 25 */       return;
/*  29:    */     }
/*  30: 26 */     List sb = Arrays.asList(new Integer[] { Integer.valueOf(i), Integer.valueOf(k) });
/*  31: 27 */     Integer o = (Integer)this.fillStackTest.get(sb);
/*  32: 29 */     if ((o != null) && (p <= o.intValue())) {
/*  33: 29 */       return;
/*  34:    */     }
/*  35: 30 */     this.fillStack.addLast(Arrays.asList(new Integer[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) }));
/*  36:    */     
/*  37: 32 */     this.fillStackTest.put(sb, Integer.valueOf(p));
/*  38:    */   }
/*  39:    */   
/*  40:    */   private void searchBlock(int i, int j, int k, int p, Random random)
/*  41:    */   {
/*  42: 37 */     int rp = random.nextInt(16);
/*  43:    */     
/*  44: 39 */     addBlock(i - 1, j, k, (rp & 0x1) > 0 ? p - 1 : p);
/*  45: 40 */     addBlock(i + 1, j, k, (rp & 0x2) > 0 ? p - 1 : p);
/*  46: 41 */     addBlock(i, j, k - 1, (rp & 0x4) > 0 ? p - 1 : p);
/*  47: 42 */     addBlock(i, j, k + 1, (rp & 0x8) > 0 ? p - 1 : p);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public boolean canReplaceId(int bid)
/*  51:    */   {
/*  52: 46 */     if (bid == 0) {
/*  53: 46 */       return true;
/*  54:    */     }
/*  55: 47 */     if ((bid == amq.D.cm) || (bid == amq.E.cm) || (bid == amq.M.cm) || (bid == amq.N.cm) || (bid == amq.bx.cm) || (bid == amq.aV.cm) || (bid == amq.aW.cm)) {
/*  56: 54 */       return true;
/*  57:    */     }
/*  58: 55 */     if ((bid == RedPowerWorld.blockLogs.cm) || (bid == RedPowerWorld.blockLeaves.cm)) {
/*  59: 57 */       return true;
/*  60:    */     }
/*  61: 58 */     if ((amq.p[bid] instanceof aje)) {
/*  62: 59 */       return true;
/*  63:    */     }
/*  64: 60 */     return false;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void eatTree(yc world, int i, int j, int k)
/*  68:    */   {
/*  69: 64 */     int bid = world.a(i, j, k);
/*  70: 65 */     if (bid == amq.aV.cm)
/*  71:    */     {
/*  72: 66 */       world.b(i, j, k, 0);
/*  73: 67 */       return;
/*  74:    */     }
/*  75: 69 */     if ((bid != amq.M.cm) && (bid != amq.N.cm) && (bid != amq.bx.cm)) {
/*  76: 72 */       return;
/*  77:    */     }
/*  78: 73 */     world.b(i, j, k, 0);
/*  79:    */     
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83:    */ 
/*  84:    */ 
/*  85:    */ 
/*  86: 81 */     eatTree(world, i, j + 1, k);
/*  87:    */   }
/*  88:    */   
/*  89:    */   public boolean a(yc world, Random random, int i, int j, int k)
/*  90:    */   {
/*  91: 86 */     if (world.a(i, j, k) != amq.G.cm) {
/*  92: 87 */       return false;
/*  93:    */     }
/*  94: 89 */     int swh = world.f(i, k);
/*  95: 90 */     int lavaid = amq.F.cm;
/*  96: 92 */     while (canReplaceId(world.a(i, swh - 1, k))) {
/*  97: 93 */       swh--;
/*  98:    */     }
/*  99: 94 */     for (int n = j; n < swh; n++)
/* 100:    */     {
/* 101: 95 */       world.b(i, n, k, lavaid);
/* 102: 96 */       world.c(i - 1, n, k, this.minableBlockId, this.minableBlockMeta);
/* 103:    */       
/* 104: 98 */       world.c(i + 1, n, k, this.minableBlockId, this.minableBlockMeta);
/* 105:    */       
/* 106:100 */       world.c(i, n, k - 1, this.minableBlockId, this.minableBlockMeta);
/* 107:    */       
/* 108:102 */       world.c(i, n, k + 1, this.minableBlockId, this.minableBlockMeta);
/* 109:    */     }
/* 110:106 */     int head = 3 + random.nextInt(4);
/* 111:107 */     int spread = random.nextInt(3);
/* 112:109 */     while (this.numberOfBlocks > 0)
/* 113:    */     {
/* 114:110 */       while (this.fillStack.size() == 0)
/* 115:    */       {
/* 116:111 */         world.b(i, n, k, lavaid);
/* 117:112 */         this.fillStackTest.clear();
/* 118:113 */         searchBlock(i, n, k, head, random);
/* 119:114 */         n++;
/* 120:115 */         if (n > 125) {
/* 121:    */           break label575;
/* 122:    */         }
/* 123:    */       }
/* 124:118 */       List sp1 = (List)this.fillStack.removeFirst();
/* 125:119 */       Integer[] sp = (Integer[])sp1.toArray();
/* 126:    */       
/* 127:    */ 
/* 128:122 */       world.a(sp[0].intValue(), 64, sp[2].intValue());
/* 129:123 */       if (world.f(sp[0].intValue(), 64, sp[2].intValue()))
/* 130:    */       {
/* 131:125 */         int pow = ((Integer)this.fillStackTest.get(Arrays.asList(new Integer[] { sp[0], sp[2] }))).intValue();
/* 132:126 */         int hm = world.f(sp[0].intValue(), sp[2].intValue()) + 1;
/* 133:127 */         while ((hm > 0) && (canReplaceId(world.a(sp[0].intValue(), hm - 1, sp[2].intValue())))) {
/* 134:128 */           hm--;
/* 135:    */         }
/* 136:129 */         if (hm <= sp[1].intValue())
/* 137:    */         {
/* 138:130 */           int bid = world.a(sp[0].intValue(), hm, sp[2].intValue());
/* 139:131 */           if (canReplaceId(bid))
/* 140:    */           {
/* 141:132 */             eatTree(world, sp[0].intValue(), hm, sp[2].intValue());
/* 142:133 */             world.c(sp[0].intValue(), hm, sp[2].intValue(), this.minableBlockId, this.minableBlockMeta);
/* 143:136 */             if (sp[1].intValue() > hm) {
/* 144:136 */               pow = Math.max(pow, spread);
/* 145:    */             }
/* 146:137 */             searchBlock(sp[0].intValue(), hm, sp[2].intValue(), pow, random);
/* 147:138 */             this.numberOfBlocks -= 1;
/* 148:    */           }
/* 149:    */         }
/* 150:    */       }
/* 151:    */     }
/* 152:    */     label575:
/* 153:141 */     world.b(i, n, k, lavaid);
/* 154:142 */     while ((n > swh) && 
/* 155:143 */       (world.a(i, n, k) == lavaid))
/* 156:    */     {
/* 157:145 */       world.i(i, n, k);
/* 158:146 */       world.h(i, n, k, lavaid);
/* 159:147 */       world.d = true;
/* 160:148 */       amq.p[lavaid].b(world, i, n, k, random);
/* 161:    */       
/* 162:150 */       world.d = false;
/* 163:151 */       n--;
/* 164:    */     }
/* 165:153 */     return true;
/* 166:    */   }
/* 167:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.WorldGenVolcano
 * JD-Core Version:    0.7.0.1
 */