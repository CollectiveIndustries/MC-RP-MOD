/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import baz;
/*  5:   */ import bbb;
/*  6:   */ import com.eloraam.redpower.base.TileAppliance;
/*  7:   */ import com.eloraam.redpower.core.CoreLib;
/*  8:   */ import com.eloraam.redpower.core.RenderContext;
/*  9:   */ import com.eloraam.redpower.core.RenderCustomBlock;
/* 10:   */ import com.eloraam.redpower.core.RenderLib;
/* 11:   */ import java.util.Random;
/* 12:   */ import yc;
/* 13:   */ import ym;
/* 14:   */ 
/* 15:   */ public class RenderBufferChest
/* 16:   */   extends RenderCustomBlock
/* 17:   */ {
/* 18:   */   protected RenderContext context;
/* 19:   */   
/* 20:   */   public RenderBufferChest(amq bl)
/* 21:   */   {
/* 22:17 */     super(bl);
/* 23:18 */     this.context = new RenderContext();
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 27:   */   
/* 28:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 29:   */   {
/* 30:29 */     TileAppliance tb = (TileAppliance)CoreLib.getTileEntity(iba, i, j, k, TileAppliance.class);
/* 31:31 */     if (tb == null) {
/* 32:31 */       return;
/* 33:   */     }
/* 34:33 */     this.context.setDefaults();
/* 35:34 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 36:   */     
/* 37:36 */     this.context.setPos(i, j, k);
/* 38:37 */     this.context.readGlobalLights(iba, i, j, k);
/* 39:   */     
/* 40:39 */     this.context.setTex(87, 89, 88, 88, 88, 88);
/* 41:   */     
/* 42:41 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 43:42 */     this.context.setupBox();
/* 44:43 */     this.context.transform();
/* 45:44 */     this.context.orientTextures(tb.Rotation);
/* 46:   */     
/* 47:46 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 48:47 */     this.context.renderGlobFaces(63);
/* 49:48 */     RenderLib.unbindTexture();
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 53:   */   {
/* 54:52 */     this.block.f();
/* 55:   */     
/* 56:54 */     this.context.setDefaults();
/* 57:55 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 58:56 */     this.context.useNormal = true;
/* 59:   */     
/* 60:58 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 61:59 */     baz tessellator = baz.a;
/* 62:60 */     tessellator.b();
/* 63:   */     
/* 64:62 */     this.context.setTex(87, 89, 88, 88, 88, 88);
/* 65:63 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 66:   */     
/* 67:65 */     tessellator.a();
/* 68:66 */     RenderLib.unbindTexture();
/* 69:   */     
/* 70:68 */     this.context.useNormal = false;
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderBufferChest
 * JD-Core Version:    0.7.0.1
 */