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
/* 14:   */ public class RenderTransformer
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderModel model;
/* 18:   */   protected RenderContext context;
/* 19:   */   
/* 20:   */   public RenderTransformer(amq bl)
/* 21:   */   {
/* 22:16 */     super(bl);
/* 23:17 */     this.context = new RenderContext();
/* 24:18 */     this.model = RenderModel.loadModel("/eloraam/machine/transform.obj");
/* 25:19 */     this.model.scale(0.0625D);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 29:   */   
/* 30:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 31:   */   {
/* 32:30 */     TileTransformer tb = (TileTransformer)CoreLib.getTileEntity(iba, i, j, k, TileTransformer.class);
/* 33:32 */     if (tb == null) {
/* 34:32 */       return;
/* 35:   */     }
/* 36:34 */     this.context.setDefaults();
/* 37:35 */     this.context.setPos(i, j, k);
/* 38:36 */     this.context.setOrientation(tb.Rotation >> 2, tb.Rotation + 3 & 0x3);
/* 39:37 */     this.context.readGlobalLights(iba, i, j, k);
/* 40:38 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/* 41:   */     
/* 42:   */ 
/* 43:41 */     this.context.bindTexture("/eloraam/machine/machine2.png");
/* 44:42 */     this.context.bindModelOffset(this.model, 0.5D, 0.5D, 0.5D);
/* 45:43 */     this.context.renderModelGroup(0, 0);
/* 46:   */     
/* 47:45 */     this.context.unbindTexture();
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 51:   */   {
/* 52:49 */     this.block.f();
/* 53:   */     
/* 54:51 */     this.context.setDefaults();
/* 55:52 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 56:53 */     this.context.bindTexture("/eloraam/machine/machine2.png");
/* 57:54 */     baz tessellator = baz.a;
/* 58:55 */     tessellator.b();
/* 59:56 */     this.context.useNormal = true;
/* 60:   */     
/* 61:58 */     this.context.bindModelOffset(this.model, 0.5D, 0.5D, 0.5D);
/* 62:59 */     this.context.renderModelGroup(0, 0);
/* 63:   */     
/* 64:61 */     this.context.useNormal = false;
/* 65:62 */     tessellator.a();
/* 66:63 */     this.context.unbindTexture();
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderTransformer
 * JD-Core Version:    0.7.0.1
 */