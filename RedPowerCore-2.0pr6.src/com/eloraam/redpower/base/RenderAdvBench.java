/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import baz;
/*  5:   */ import bbb;
/*  6:   */ import com.eloraam.redpower.core.CoreLib;
/*  7:   */ import com.eloraam.redpower.core.RenderContext;
/*  8:   */ import com.eloraam.redpower.core.RenderCustomBlock;
/*  9:   */ import com.eloraam.redpower.core.RenderLib;
/* 10:   */ import java.util.Random;
/* 11:   */ import yc;
/* 12:   */ import ym;
/* 13:   */ 
/* 14:   */ public class RenderAdvBench
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderAdvBench(amq bl)
/* 20:   */   {
/* 21:14 */     super(bl);
/* 22:15 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:26 */     TileAdvBench tb = (TileAdvBench)CoreLib.getTileEntity(iba, i, j, k, TileAdvBench.class);
/* 30:28 */     if (tb == null) {
/* 31:28 */       return;
/* 32:   */     }
/* 33:30 */     this.context.setDefaults();
/* 34:31 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 35:   */     
/* 36:33 */     this.context.setPos(i, j, k);
/* 37:34 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:   */     
/* 39:36 */     this.context.setTex(35, 32, 34, 33, 33, 33);
/* 40:37 */     this.context.rotateTextures(tb.Rotation);
/* 41:38 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 42:39 */     this.context.setupBox();
/* 43:40 */     this.context.transform();
/* 44:   */     
/* 45:   */ 
/* 46:43 */     RenderLib.bindTexture("/eloraam/base/base1.png");
/* 47:44 */     this.context.renderGlobFaces(63);
/* 48:45 */     RenderLib.unbindTexture();
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 52:   */   {
/* 53:49 */     this.block.f();
/* 54:   */     
/* 55:51 */     this.context.setDefaults();
/* 56:52 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 57:53 */     this.context.useNormal = true;
/* 58:   */     
/* 59:55 */     RenderLib.bindTexture("/eloraam/base/base1.png");
/* 60:56 */     baz tessellator = baz.a;
/* 61:57 */     tessellator.b();
/* 62:   */     
/* 63:59 */     this.context.setTex(35, 32, 33, 34, 33, 33);
/* 64:60 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 65:   */     
/* 66:62 */     tessellator.a();
/* 67:63 */     RenderLib.unbindTexture();
/* 68:   */     
/* 69:65 */     this.context.useNormal = false;
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.RenderAdvBench
 * JD-Core Version:    0.7.0.1
 */