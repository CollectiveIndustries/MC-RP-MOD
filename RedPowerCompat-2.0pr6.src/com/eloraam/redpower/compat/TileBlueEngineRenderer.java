/*  1:   */ package com.eloraam.redpower.compat;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ import baz;
/*  5:   */ import bdx;
/*  6:   */ import com.eloraam.redpower.core.Matrix3;
/*  7:   */ import com.eloraam.redpower.core.RenderContext;
/*  8:   */ import com.eloraam.redpower.core.RenderModel;
/*  9:   */ import yc;
/* 10:   */ 
/* 11:   */ public class TileBlueEngineRenderer
/* 12:   */   extends bdx
/* 13:   */ {
/* 14:   */   public TileBlueEngineRenderer()
/* 15:   */   {
/* 16:23 */     this.context.setDefaults();
/* 17:24 */     this.modelSlide = RenderModel.loadModel("/eloraam/compat/btengine2.obj");
/* 18:25 */     this.modelGear = RenderModel.loadModel("/eloraam/compat/btengine3.obj");
/* 19:26 */     this.modelSlide.scale(0.0625D);
/* 20:27 */     this.modelGear.scale(0.0625D);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void a(any te, double x, double y, double z, float f)
/* 24:   */   {
/* 25:33 */     TileBlueEngine tp = (TileBlueEngine)te;
/* 26:   */     
/* 27:35 */     baz tessellator = baz.a;
/* 28:   */     
/* 29:37 */     a("/eloraam/compat/compat1.png");
/* 30:   */     
/* 31:39 */     int lv = te.k.i(te.l, te.m, te.n, 0);
/* 32:   */     
/* 33:41 */     tessellator.b();
/* 34:42 */     tessellator.c(lv);
/* 35:44 */     if (tp.Active)
/* 36:   */     {
/* 37:45 */       f += tp.PumpTick;
/* 38:47 */       if (tp.PumpSpeed > 0) {
/* 39:47 */         f /= tp.PumpSpeed;
/* 40:   */       }
/* 41:   */     }
/* 42:   */     else
/* 43:   */     {
/* 44:48 */       f = 0.0F;
/* 45:   */     }
/* 46:50 */     this.context.useNormal = true;
/* 47:51 */     this.context.setPos(x, y, z);
/* 48:52 */     this.context.setOrientation(tp.Rotation, 0);
/* 49:   */     
/* 50:54 */     this.context.setRelPos(0.0D, 0.1875D * (0.5D - 0.5D * Math.cos(3.141592653589793D * f)), 0.0D);
/* 51:55 */     this.context.bindModelOffset(this.modelSlide, 0.5D, 0.5D, 0.5D);
/* 52:56 */     this.context.renderModelGroup(0, 0);
/* 53:   */     
/* 54:58 */     this.context.basis = Matrix3.getRotY(1.570796326794897D * f).multiply(this.context.basis);
/* 55:   */     
/* 56:   */ 
/* 57:61 */     this.context.setRelPos(0.5D, 0.34375D, 0.5D);
/* 58:62 */     this.context.bindModelOffset(this.modelGear, 0.5D, 0.5D, 0.5D);
/* 59:63 */     this.context.renderModelGroup(0, 0);
/* 60:64 */     tessellator.a();
/* 61:   */   }
/* 62:   */   
/* 63:67 */   RenderContext context = new RenderContext();
/* 64:   */   protected RenderModel modelSlide;
/* 65:   */   protected RenderModel modelGear;
/* 66:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.compat.TileBlueEngineRenderer
 * JD-Core Version:    0.7.0.1
 */