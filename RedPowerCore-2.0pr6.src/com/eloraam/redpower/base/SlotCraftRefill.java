/*   1:    */ package com.eloraam.redpower.base;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.core.CoreLib;
/*   4:    */ import la;
/*   5:    */ import qx;
/*   6:    */ import sq;
/*   7:    */ import up;
/*   8:    */ import ur;
/*   9:    */ 
/*  10:    */ public class SlotCraftRefill
/*  11:    */   extends sq
/*  12:    */ {
/*  13:    */   la allSlots;
/*  14:    */   la craftingMatrix;
/*  15:    */   ContainerAdvBench eventHandler;
/*  16:    */   
/*  17:    */   public SlotCraftRefill(qx player, la matrix, la result, la all, ContainerAdvBench evh, int i, int j, int k)
/*  18:    */   {
/*  19: 18 */     super(player, matrix, result, i, j, k);
/*  20: 19 */     this.allSlots = all;
/*  21: 20 */     this.craftingMatrix = matrix;
/*  22: 21 */     this.eventHandler = evh;
/*  23:    */   }
/*  24:    */   
/*  25:    */   private int findMatch(ur a)
/*  26:    */   {
/*  27: 26 */     for (int i = 0; i < 18; i++)
/*  28:    */     {
/*  29: 27 */       ur test = this.allSlots.a(10 + i);
/*  30: 28 */       if ((test != null) && (test.a != 0) && 
/*  31: 29 */         (CoreLib.matchItemStackOre(a, test))) {
/*  32: 31 */         return 10 + i;
/*  33:    */       }
/*  34:    */     }
/*  35: 33 */     return -1;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public boolean isLastUse()
/*  39:    */   {
/*  40: 37 */     int bits = 0;
/*  41: 39 */     for (int i = 0; i < 9; i++)
/*  42:    */     {
/*  43: 40 */       ur st = this.allSlots.a(i);
/*  44: 41 */       if (st == null) {
/*  45: 41 */         bits |= 1 << i;
/*  46: 42 */       } else if (!st.e()) {
/*  47: 42 */         bits |= 1 << i;
/*  48: 43 */       } else if (st.a > 1) {
/*  49: 43 */         bits |= 1 << i;
/*  50:    */       }
/*  51:    */     }
/*  52: 45 */     if (bits == 511) {
/*  53: 45 */       return false;
/*  54:    */     }
/*  55: 47 */     for (int i = 0; i < 18; i++)
/*  56:    */     {
/*  57: 48 */       ur test = this.allSlots.a(10 + i);
/*  58: 49 */       if ((test != null) && (test.a != 0))
/*  59:    */       {
/*  60: 51 */         int sc = test.a;
/*  61: 52 */         for (int j = 0; j < 9; j++) {
/*  62: 53 */           if ((bits & 1 << j) <= 0)
/*  63:    */           {
/*  64: 54 */             ur st = this.allSlots.a(j);
/*  65: 55 */             if (st != null) {
/*  66: 57 */               if (CoreLib.matchItemStackOre(st, test))
/*  67:    */               {
/*  68: 63 */                 bits |= 1 << j;
/*  69: 64 */                 sc--;
/*  70: 65 */                 if (sc == 0) {
/*  71:    */                   break;
/*  72:    */                 }
/*  73:    */               }
/*  74:    */             }
/*  75:    */           }
/*  76:    */         }
/*  77:    */       }
/*  78:    */     }
/*  79: 68 */     return bits != 511;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void a(qx player, ur ist)
/*  83:    */   {
/*  84: 73 */     ur[] plan = this.eventHandler.getPlanItems();
/*  85: 74 */     ur[] cur = new ur[9];
/*  86: 75 */     for (int i = 0; i < 9; i++)
/*  87:    */     {
/*  88: 76 */       ur st = this.allSlots.a(i);
/*  89: 77 */       if (st == null) {
/*  90: 77 */         cur[i] = null;
/*  91:    */       } else {
/*  92: 78 */         cur[i] = st.l();
/*  93:    */       }
/*  94:    */     }
/*  95: 80 */     boolean last = isLastUse();
/*  96: 82 */     if (plan != null) {
/*  97: 83 */       for (int i = 0; i < 9; i++) {
/*  98: 84 */         if ((cur[i] == null) && (plan[i] != null))
/*  99:    */         {
/* 100: 85 */           int p = findMatch(plan[i]);
/* 101: 86 */           if (p >= 0)
/* 102:    */           {
/* 103: 88 */             ur is2 = this.allSlots.a(p);
/* 104: 89 */             if (is2 != null)
/* 105:    */             {
/* 106: 91 */               this.allSlots.a(p, 1);
/* 107: 92 */               if (is2.b().s())
/* 108:    */               {
/* 109: 93 */                 ur is3 = is2.b().getContainerItemStack(is2);
/* 110: 94 */                 this.allSlots.a(p, is3);
/* 111:    */               }
/* 112:    */             }
/* 113:    */           }
/* 114:    */         }
/* 115:    */       }
/* 116:    */     }
/* 117: 98 */     super.a(player, ist);
/* 118:100 */     if (last)
/* 119:    */     {
/* 120:101 */       this.eventHandler.a(this.craftingMatrix);
/* 121:102 */       return;
/* 122:    */     }
/* 123:108 */     boolean ch = false;
/* 124:109 */     for (int i = 0; i < 9; i++) {
/* 125:110 */       if (cur[i] != null)
/* 126:    */       {
/* 127:111 */         ur nsl = this.allSlots.a(i);
/* 128:113 */         if ((plan == null) || (plan[i] == null)) {
/* 129:115 */           if (nsl != null)
/* 130:    */           {
/* 131:116 */             if (!CoreLib.matchItemStackOre(nsl, cur[i])) {
/* 132:123 */               if (cur[i].b().s())
/* 133:    */               {
/* 134:125 */                 ur ctr = cur[i].b().getContainerItemStack(cur[i]);
/* 135:126 */                 if ((ctr != null) && 
/* 136:127 */                   (ctr.b().cj == nsl.b().cj))
/* 137:    */                 {
/* 138:131 */                   int idx = findMatch(cur[i]);
/* 139:132 */                   if (idx >= 0)
/* 140:    */                   {
/* 141:134 */                     ur i1 = this.allSlots.a(idx);
/* 142:    */                     
/* 143:136 */                     this.allSlots.a(idx, nsl);
/* 144:137 */                     this.allSlots.a(i, i1);
/* 145:138 */                     ch = true;
/* 146:    */                   }
/* 147:    */                 }
/* 148:    */               }
/* 149:    */             }
/* 150:    */           }
/* 151:    */           else
/* 152:    */           {
/* 153:141 */             int idx = findMatch(cur[i]);
/* 154:142 */             if (idx >= 0)
/* 155:    */             {
/* 156:144 */               ur i1 = this.allSlots.a(idx);
/* 157:145 */               this.allSlots.a(i, this.allSlots.a(idx, 1));
/* 158:    */               
/* 159:147 */               ch = true;
/* 160:    */             }
/* 161:    */           }
/* 162:    */         }
/* 163:    */       }
/* 164:    */     }
/* 165:149 */     this.eventHandler.a(this.craftingMatrix);
/* 166:    */   }
/* 167:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.SlotCraftRefill
 * JD-Core Version:    0.7.0.1
 */