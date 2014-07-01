/*  1:   */ package com.eloraam.redpower.control;
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
/* 14:   */ public class RenderIOExpander
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   RenderContext context;
/* 18:   */   protected RenderModel modelModem;
/* 19:   */   
/* 20:   */   public RenderIOExpander(amq bl)
/* 21:   */   {
/* 22:15 */     super(bl);
/* 23:   */     
/* 24:17 */     this.context = new RenderContext();
/* 25:18 */     this.modelModem = RenderModel.loadModel("/eloraam/control/modem.obj");
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 29:   */   
/* 30:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 31:   */   {
/* 32:29 */     TileIOExpander iox = (TileIOExpander)CoreLib.getTileEntity(iba, i, j, k, TileIOExpander.class);
/* 33:31 */     if (iox == null) {
/* 34:31 */       return;
/* 35:   */     }
/* 36:33 */     this.context.setDefaults();
/* 37:34 */     this.context.setPos(i, j, k);
/* 38:35 */     this.context.setOrientation(0, iox.Rotation);
/* 39:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 40:37 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/* 41:   */     
/* 42:39 */     this.context.bindTexture("/eloraam/control/control1.png");
/* 43:40 */     this.context.bindModelOffset(this.modelModem, 0.5D, 0.5D, 0.5D);
/* 44:   */     
/* 45:   */ 
/* 46:43 */     this.context.renderModelGroup(1, 1 + (CoreLib.rotToSide(iox.Rotation) & 0x1));
/* 47:   */     
/* 48:45 */     this.context.renderModelGroup(2, 1 + (iox.WBuf & 0xF));
/* 49:46 */     this.context.renderModelGroup(3, 1 + (iox.WBuf >> 4 & 0xF));
/* 50:47 */     this.context.renderModelGroup(4, 1 + (iox.WBuf >> 8 & 0xF));
/* 51:48 */     this.context.renderModelGroup(5, 1 + (iox.WBuf >> 12 & 0xF));
/* 52:   */     
/* 53:50 */     this.context.unbindTexture();
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 57:   */   {
/* 58:54 */     this.block.f();
/* 59:   */     
/* 60:56 */     this.context.setDefaults();
/* 61:57 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 62:58 */     this.context.bindTexture("/eloraam/control/control1.png");
/* 63:59 */     baz tessellator = baz.a;
/* 64:60 */     tessellator.b();
/* 65:61 */     this.context.useNormal = true;
/* 66:   */     
/* 67:63 */     this.context.setOrientation(0, 3);
/* 68:   */     
/* 69:65 */     this.context.bindModelOffset(this.modelModem, 0.5D, 0.5D, 0.5D);
/* 70:66 */     this.context.renderModelGroup(1, 1);
/* 71:67 */     this.context.renderModelGroup(2, 1);
/* 72:68 */     this.context.renderModelGroup(3, 1);
/* 73:69 */     this.context.renderModelGroup(4, 1);
/* 74:70 */     this.context.renderModelGroup(5, 1);
/* 75:   */     
/* 76:72 */     this.context.useNormal = false;
/* 77:73 */     tessellator.a();
/* 78:74 */     this.context.unbindTexture();
/* 79:   */   }
/* 80:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.RenderIOExpander
 * JD-Core Version:    0.7.0.1
 */