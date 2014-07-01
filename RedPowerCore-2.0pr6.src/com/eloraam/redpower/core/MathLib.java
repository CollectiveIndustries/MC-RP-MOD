/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ public class MathLib
/*  4:   */ {
/*  5:   */   public static void orientMatrix(Matrix3 m, int down, int rot)
/*  6:   */   {
/*  7: 5 */     m.set(orientMatrixList[(down * 4 + rot)]);
/*  8:   */   }
/*  9:   */   
/* 10:   */   public static Quat orientQuat(int down, int rot)
/* 11:   */   {
/* 12: 9 */     return new Quat(orientQuatList[(down * 4 + rot)]);
/* 13:   */   }
/* 14:   */   
/* 15:12 */   private static Matrix3[] orientMatrixList = new Matrix3[24];
/* 16:13 */   private static Quat[] orientQuatList = new Quat[24];
/* 17:   */   
/* 18:   */   static
/* 19:   */   {
/* 20:18 */     Quat q2 = Quat.aroundAxis(1.0D, 0.0D, 0.0D, 3.141592653589793D);
/* 21:19 */     for (int j = 0; j < 4; j++)
/* 22:   */     {
/* 23:20 */       Quat q1 = Quat.aroundAxis(0.0D, 1.0D, 0.0D, -1.570796326794897D * j);
/* 24:21 */       orientQuatList[j] = q1;
/* 25:   */       
/* 26:23 */       q1 = new Quat(q1);
/* 27:24 */       q1.multiply(q2);
/* 28:25 */       orientQuatList[(j + 4)] = q1;
/* 29:   */     }
/* 30:28 */     for (int i = 0; i < 4; i++)
/* 31:   */     {
/* 32:29 */       int k = (i >> 1 | i << 1) & 0x3;
/* 33:30 */       q2 = Quat.aroundAxis(0.0D, 0.0D, 1.0D, 1.570796326794897D);
/* 34:31 */       q2.multiply(Quat.aroundAxis(0.0D, 1.0D, 0.0D, 1.570796326794897D * (k + 1)));
/* 35:32 */       for (j = 0; j < 4; j++)
/* 36:   */       {
/* 37:33 */         Quat q1 = new Quat(orientQuatList[j]);
/* 38:34 */         q1.multiply(q2);
/* 39:35 */         orientQuatList[(8 + 4 * i + j)] = q1;
/* 40:   */       }
/* 41:   */     }
/* 42:38 */     for (i = 0; i < 24; i++) {
/* 43:39 */       orientMatrixList[i] = new Matrix3(orientQuatList[i]);
/* 44:   */     }
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.MathLib
 * JD-Core Version:    0.7.0.1
 */