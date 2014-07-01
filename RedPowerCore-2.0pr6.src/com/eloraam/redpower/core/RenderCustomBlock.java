/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import bbb;
/*  5:   */ import java.util.Random;
/*  6:   */ import yc;
/*  7:   */ import ym;
/*  8:   */ 
/*  9:   */ public abstract class RenderCustomBlock
/* 10:   */ {
/* 11:   */   protected amq block;
/* 12:   */   
/* 13:   */   public RenderCustomBlock(amq bl)
/* 14:   */   {
/* 15:12 */     this.block = bl;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public abstract void randomDisplayTick(yc paramyc, int paramInt1, int paramInt2, int paramInt3, Random paramRandom);
/* 19:   */   
/* 20:   */   public abstract void renderWorldBlock(bbb parambbb, ym paramym, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/* 21:   */   
/* 22:   */   public abstract void renderInvBlock(bbb parambbb, int paramInt);
/* 23:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.RenderCustomBlock
 * JD-Core Version:    0.7.0.1
 */