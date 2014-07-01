/*  1:   */ package com.eloraam.redpower.compat;
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
/* 14:   */ public class RenderBlueEngine
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderModel modelBase;
/* 18:   */   protected RenderModel modelSlide;
/* 19:   */   protected RenderModel modelGear;
/* 20:   */   protected RenderContext context;
/* 21:   */   
/* 22:   */   public RenderBlueEngine(amq bl)
/* 23:   */   {
/* 24:16 */     super(bl);
/* 25:17 */     this.context = new RenderContext();
/* 26:18 */     this.modelBase = RenderModel.loadModel("/eloraam/compat/btengine1.obj");
/* 27:19 */     this.modelSlide = RenderModel.loadModel("/eloraam/compat/btengine2.obj");
/* 28:20 */     this.modelGear = RenderModel.loadModel("/eloraam/compat/btengine3.obj");
/* 29:21 */     this.modelBase.scale(0.0625D);
/* 30:22 */     this.modelSlide.scale(0.0625D);
/* 31:23 */     this.modelGear.scale(0.0625D);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 35:   */   
/* 36:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 37:   */   {
/* 38:34 */     TileBlueEngine tb = (TileBlueEngine)CoreLib.getTileEntity(iba, i, j, k, TileBlueEngine.class);
/* 39:36 */     if (tb == null) {
/* 40:36 */       return;
/* 41:   */     }
/* 42:38 */     this.context.setDefaults();
/* 43:39 */     this.context.setPos(i, j, k);
/* 44:40 */     this.context.setOrientation(tb.Rotation, 0);
/* 45:41 */     this.context.readGlobalLights(iba, i, j, k);
/* 46:42 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/* 47:   */     
/* 48:   */ 
/* 49:45 */     this.context.bindTexture("/eloraam/compat/compat1.png");
/* 50:46 */     this.context.bindModelOffset(this.modelBase, 0.5D, 0.5D, 0.5D);
/* 51:47 */     this.context.renderModelGroup(0, 0);
/* 52:48 */     this.context.renderModelGroup(1, tb.Charged ? 2 : tb.Active ? 3 : 1);
/* 53:   */     
/* 54:   */ 
/* 55:   */ 
/* 56:   */ 
/* 57:   */ 
/* 58:   */ 
/* 59:55 */     this.context.unbindTexture();
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 63:   */   {
/* 64:59 */     this.block.f();
/* 65:   */     
/* 66:61 */     this.context.setDefaults();
/* 67:62 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 68:63 */     this.context.bindTexture("/eloraam/compat/compat1.png");
/* 69:64 */     baz tessellator = baz.a;
/* 70:65 */     tessellator.b();
/* 71:66 */     this.context.useNormal = true;
/* 72:   */     
/* 73:68 */     this.context.bindModelOffset(this.modelBase, 0.5D, 0.5D, 0.5D);
/* 74:69 */     this.context.renderModelGroup(0, 0);
/* 75:70 */     this.context.renderModelGroup(1, 1);
/* 76:   */     
/* 77:   */ 
/* 78:73 */     this.context.bindModelOffset(this.modelSlide, 0.5D, 0.5D, 0.5D);
/* 79:74 */     this.context.renderModelGroup(0, 0);
/* 80:   */     
/* 81:76 */     this.context.setPos(0.0D, -0.15625D, 0.0D);
/* 82:77 */     this.context.bindModel(this.modelGear);
/* 83:78 */     this.context.renderModelGroup(0, 0);
/* 84:   */     
/* 85:80 */     this.context.useNormal = false;
/* 86:81 */     tessellator.a();
/* 87:82 */     this.context.unbindTexture();
/* 88:   */   }
/* 89:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.compat.RenderBlueEngine
 * JD-Core Version:    0.7.0.1
 */