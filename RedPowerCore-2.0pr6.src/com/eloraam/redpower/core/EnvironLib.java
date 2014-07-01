/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import aaq;
/*  4:   */ import ahx;
/*  5:   */ import yc;
/*  6:   */ import yn;
/*  7:   */ import yy;
/*  8:   */ 
/*  9:   */ public class EnvironLib
/* 10:   */ {
/* 11:   */   public static double getWindSpeed(yc world, WorldCoord wc)
/* 12:   */   {
/* 13: 9 */     if (world.u.e) {
/* 14:10 */       return 0.5D;
/* 15:   */     }
/* 16:12 */     double nv = FractalLib.noise1D(2576710L, world.G() * 0.0001D, 0.6F, 5);
/* 17:   */     
/* 18:14 */     nv = Math.max(0.0D, 1.6D * (nv - 0.5D) + 0.5D);
/* 19:16 */     if (world.K().u() != yn.c) {
/* 20:17 */       nv *= Math.sqrt(wc.y) / 16.0D;
/* 21:   */     }
/* 22:19 */     yy bgb = world.a(wc.x, wc.z);
/* 23:21 */     if (bgb.d())
/* 24:   */     {
/* 25:22 */       if (world.M()) {
/* 26:23 */         return 4.0D * nv;
/* 27:   */       }
/* 28:24 */       if (world.N()) {
/* 29:25 */         return 0.5D + 0.5D * nv;
/* 30:   */       }
/* 31:   */     }
/* 32:27 */     return nv;
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.EnvironLib
 * JD-Core Version:    0.7.0.1
 */