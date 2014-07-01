/*  1:   */ package com.eloraam.redpower.machine;
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
/* 14:   */ public class RenderThermopile
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderThermopile(amq bl)
/* 20:   */   {
/* 21:15 */     super(bl);
/* 22:16 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:27 */     TileThermopile tb = (TileThermopile)CoreLib.getTileEntity(iba, i, j, k, TileThermopile.class);
/* 30:29 */     if (tb == null) {
/* 31:29 */       return;
/* 32:   */     }
/* 33:31 */     this.context.setDefaults();
/* 34:32 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 35:   */     
/* 36:34 */     this.context.setPos(i, j, k);
/* 37:35 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:   */     
/* 39:37 */     this.context.setTex(140, 140, 138, 138, 139, 139);
/* 40:   */     
/* 41:   */ 
/* 42:40 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 43:41 */     this.context.setupBox();
/* 44:42 */     this.context.transform();
/* 45:   */     
/* 46:   */ 
/* 47:45 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 48:46 */     this.context.renderGlobFaces(63);
/* 49:47 */     RenderLib.unbindTexture();
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 53:   */   {
/* 54:51 */     this.block.f();
/* 55:   */     
/* 56:53 */     this.context.setDefaults();
/* 57:54 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 58:55 */     this.context.useNormal = true;
/* 59:   */     
/* 60:57 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 61:58 */     baz tessellator = baz.a;
/* 62:59 */     tessellator.b();
/* 63:   */     
/* 64:61 */     this.context.setTex(140, 140, 138, 138, 139, 139);
/* 65:62 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 66:   */     
/* 67:64 */     tessellator.a();
/* 68:65 */     RenderLib.unbindTexture();
/* 69:   */     
/* 70:67 */     this.context.useNormal = false;
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderThermopile
 * JD-Core Version:    0.7.0.1
 */