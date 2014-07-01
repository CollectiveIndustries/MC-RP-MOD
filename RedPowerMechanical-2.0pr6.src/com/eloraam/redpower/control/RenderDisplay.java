/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import baz;
/*  5:   */ import bbb;
/*  6:   */ import com.eloraam.redpower.core.CoreLib;
/*  7:   */ import com.eloraam.redpower.core.RenderContext;
/*  8:   */ import com.eloraam.redpower.core.RenderCustomBlock;
/*  9:   */ import java.util.Random;
/* 10:   */ import yc;
/* 11:   */ import ym;
/* 12:   */ 
/* 13:   */ public class RenderDisplay
/* 14:   */   extends RenderCustomBlock
/* 15:   */ {
/* 16:   */   RenderContext context;
/* 17:   */   
/* 18:   */   public RenderDisplay(amq bl)
/* 19:   */   {
/* 20:15 */     super(bl);
/* 21:   */     
/* 22:17 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:28 */     TileDisplay disp = (TileDisplay)CoreLib.getTileEntity(iba, i, j, k, TileDisplay.class);
/* 30:30 */     if (disp == null) {
/* 31:30 */       return;
/* 32:   */     }
/* 33:32 */     this.context.setDefaults();
/* 34:   */     
/* 35:   */ 
/* 36:   */ 
/* 37:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:37 */     this.context.bindTexture("/eloraam/control/control1.png");
/* 39:   */     
/* 40:   */ 
/* 41:40 */     this.context.setTex(23, 21, 20, 20, 19, 22);
/* 42:41 */     this.context.rotateTextures(disp.Rotation);
/* 43:42 */     this.context.setPos(i, j, k);
/* 44:   */     
/* 45:44 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 46:   */     
/* 47:   */ 
/* 48:47 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 49:48 */     this.context.setupBox();
/* 50:49 */     this.context.transform();
/* 51:50 */     this.context.renderGlobFaces(63);
/* 52:   */     
/* 53:52 */     this.context.unbindTexture();
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 57:   */   {
/* 58:57 */     baz tessellator = baz.a;
/* 59:58 */     this.block.f();
/* 60:59 */     this.context.setDefaults();
/* 61:60 */     this.context.useNormal = true;
/* 62:   */     
/* 63:62 */     this.context.setOrientation(0, 3);
/* 64:63 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 65:   */     
/* 66:65 */     this.context.bindTexture("/eloraam/control/control1.png");
/* 67:66 */     this.context.setTex(23, 21, 20, 20, 19, 22);
/* 68:67 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 69:   */     
/* 70:69 */     tessellator.b();
/* 71:70 */     this.context.renderBox(62, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 72:71 */     tessellator.a();
/* 73:   */     
/* 74:73 */     this.context.useNormal = false;
/* 75:74 */     this.context.unbindTexture();
/* 76:   */   }
/* 77:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.RenderDisplay
 * JD-Core Version:    0.7.0.1
 */