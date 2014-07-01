/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ public class PowerLib
/*  4:   */ {
/*  5:   */   public static int cutBits(int bits, int cut)
/*  6:   */   {
/*  7:28 */     int i = 1;
/*  8:29 */     while (i <= cut) {
/*  9:30 */       if ((cut & i) == 0)
/* 10:   */       {
/* 11:30 */         i <<= 1;
/* 12:   */       }
/* 13:   */       else
/* 14:   */       {
/* 15:31 */         bits = bits & i - 1 | bits >> 1 & (i - 1 ^ 0xFFFFFFFF);
/* 16:32 */         cut >>= 1;
/* 17:   */       }
/* 18:   */     }
/* 19:34 */     return bits;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.PowerLib
 * JD-Core Version:    0.7.0.1
 */