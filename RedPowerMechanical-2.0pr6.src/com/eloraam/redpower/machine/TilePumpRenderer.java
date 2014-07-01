/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ import baz;
/*  5:   */ import bdx;
/*  6:   */ import com.eloraam.redpower.core.RenderContext;
/*  7:   */ import com.eloraam.redpower.core.RenderModel;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class TilePumpRenderer
/* 11:   */   extends bdx
/* 12:   */ {
/* 13:   */   public TilePumpRenderer()
/* 14:   */   {
/* 15:24 */     this.context.setDefaults();
/* 16:25 */     this.modelSlide = RenderModel.loadModel("/eloraam/machine/pump2.obj");
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void a(any te, double x, double y, double z, float f)
/* 20:   */   {
/* 21:31 */     TilePump tp = (TilePump)te;
/* 22:   */     
/* 23:33 */     baz tessellator = baz.a;
/* 24:   */     
/* 25:35 */     a("/eloraam/machine/machine1.png");
/* 26:   */     
/* 27:37 */     int lv = te.k.i(te.l, te.m, te.n, 0);
/* 28:   */     
/* 29:39 */     tessellator.b();
/* 30:40 */     tessellator.c(lv);
/* 31:42 */     if (tp.Active)
/* 32:   */     {
/* 33:43 */       f += tp.PumpTick;
/* 34:44 */       if (f > 8.0F) {
/* 35:44 */         f = 16.0F - f;
/* 36:   */       }
/* 37:45 */       f = (float)(f / 8.0D);
/* 38:   */     }
/* 39:   */     else
/* 40:   */     {
/* 41:46 */       f = 0.0F;
/* 42:   */     }
/* 43:48 */     this.context.useNormal = true;
/* 44:49 */     this.context.setPos(x, y, z);
/* 45:50 */     this.context.setOrientation(0, tp.Rotation);
/* 46:   */     
/* 47:52 */     this.context.setRelPos(0.375D + 0.3125D * f, 0.0D, 0.0D);
/* 48:53 */     this.context.bindModelOffset(this.modelSlide, 0.5D, 0.5D, 0.5D);
/* 49:54 */     this.context.renderModelGroup(0, 0);
/* 50:55 */     tessellator.a();
/* 51:   */   }
/* 52:   */   
/* 53:58 */   RenderContext context = new RenderContext();
/* 54:   */   protected RenderModel modelSlide;
/* 55:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TilePumpRenderer
 * JD-Core Version:    0.7.0.1
 */