/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import baz;
/*  5:   */ import bbb;
/*  6:   */ import com.eloraam.redpower.core.CoreLib;
/*  7:   */ import com.eloraam.redpower.core.RenderContext;
/*  8:   */ import com.eloraam.redpower.core.RenderCustomBlock;
/*  9:   */ import com.eloraam.redpower.core.RenderModel;
/* 10:   */ import java.util.Random;
/* 11:   */ import yc;
/* 12:   */ import ym;
/* 13:   */ 
/* 14:   */ public class RenderPump
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderModel modelBase;
/* 18:   */   protected RenderModel modelSlide;
/* 19:   */   protected RenderContext context;
/* 20:   */   
/* 21:   */   public RenderPump(amq bl)
/* 22:   */   {
/* 23:16 */     super(bl);
/* 24:17 */     this.context = new RenderContext();
/* 25:18 */     this.modelBase = RenderModel.loadModel("/eloraam/machine/pump1.obj");
/* 26:19 */     this.modelSlide = RenderModel.loadModel("/eloraam/machine/pump2.obj");
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 30:   */   
/* 31:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 32:   */   {
/* 33:30 */     TilePump tb = (TilePump)CoreLib.getTileEntity(iba, i, j, k, TilePump.class);
/* 34:32 */     if (tb == null) {
/* 35:32 */       return;
/* 36:   */     }
/* 37:34 */     this.context.setDefaults();
/* 38:35 */     this.context.setPos(i, j, k);
/* 39:36 */     this.context.setOrientation(0, tb.Rotation);
/* 40:37 */     this.context.readGlobalLights(iba, i, j, k);
/* 41:38 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/* 42:   */     
/* 43:   */ 
/* 44:41 */     this.context.bindTexture("/eloraam/machine/machine1.png");
/* 45:42 */     this.context.bindModelOffset(this.modelBase, 0.5D, 0.5D, 0.5D);
/* 46:43 */     this.context.renderModelGroup(0, 0);
/* 47:44 */     this.context.renderModelGroup(1, tb.Charged ? 2 : tb.Active ? 3 : 1);
/* 48:   */     
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:51 */     this.context.unbindTexture();
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 58:   */   {
/* 59:55 */     this.block.f();
/* 60:   */     
/* 61:57 */     this.context.setDefaults();
/* 62:58 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 63:59 */     this.context.bindTexture("/eloraam/machine/machine1.png");
/* 64:60 */     baz tessellator = baz.a;
/* 65:61 */     tessellator.b();
/* 66:62 */     this.context.useNormal = true;
/* 67:   */     
/* 68:64 */     this.context.bindModelOffset(this.modelBase, 0.5D, 0.5D, 0.5D);
/* 69:65 */     this.context.renderModelGroup(0, 0);
/* 70:66 */     this.context.renderModelGroup(1, 1);
/* 71:   */     
/* 72:68 */     this.context.setRelPos(0.375D, 0.0D, 0.0D);
/* 73:69 */     this.context.bindModelOffset(this.modelSlide, 0.5D, 0.5D, 0.5D);
/* 74:70 */     this.context.renderModelGroup(0, 0);
/* 75:   */     
/* 76:72 */     this.context.useNormal = false;
/* 77:73 */     tessellator.a();
/* 78:74 */     this.context.unbindTexture();
/* 79:   */   }
/* 80:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderPump
 * JD-Core Version:    0.7.0.1
 */