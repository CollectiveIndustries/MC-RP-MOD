/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ 
/*  5:   */ public abstract class RenderCovers
/*  6:   */   extends RenderCustomBlock
/*  7:   */ {
/*  8:   */   public RenderCovers(amq bl)
/*  9:   */   {
/* 10: 8 */     super(bl);
/* 11: 9 */     this.context = new RenderContext();
/* 12:10 */     this.coverRenderer = new CoverRenderer(this.context);
/* 13:   */   }
/* 14:   */   
/* 15:14 */   protected static int[][] coverTextures = CoverLib.coverTextures;
/* 16:   */   protected CoverRenderer coverRenderer;
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public void renderCovers(int uc, short[] covs)
/* 20:   */   {
/* 21:17 */     this.coverRenderer.render(uc, covs);
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.RenderCovers
 * JD-Core Version:    0.7.0.1
 */