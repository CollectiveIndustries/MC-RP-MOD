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
/* 13:   */ public class RenderBackplane
/* 14:   */   extends RenderCustomBlock
/* 15:   */ {
/* 16:   */   RenderContext context;
/* 17:   */   
/* 18:   */   public RenderBackplane(amq bl)
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
/* 29:28 */     TileBackplane tb = (TileBackplane)CoreLib.getTileEntity(iba, i, j, k, TileBackplane.class);
/* 30:30 */     if (tb == null) {
/* 31:30 */       return;
/* 32:   */     }
/* 33:32 */     this.context.setDefaults();
/* 34:33 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/* 35:   */     
/* 36:   */ 
/* 37:36 */     this.context.bindTexture("/eloraam/control/control1.png");
/* 38:   */     
/* 39:38 */     this.context.setOrientation(0, tb.Rotation);
/* 40:39 */     this.context.setPos(i, j, k);
/* 41:40 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 42:42 */     if (md == 0)
/* 43:   */     {
/* 44:43 */       this.context.setTex(0, 18, 17, 17, 16, 16);
/* 45:44 */       this.context.renderBox(62, 0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/* 46:   */     }
/* 47:45 */     else if (md == 1)
/* 48:   */     {
/* 49:46 */       this.context.setTex(0, 34, 33, 33, 32, 32);
/* 50:47 */       this.context.renderBox(62, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 51:   */     }
/* 52:49 */     this.context.unbindTexture();
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 56:   */   {
/* 57:54 */     baz tessellator = baz.a;
/* 58:55 */     this.block.f();
/* 59:56 */     this.context.setDefaults();
/* 60:57 */     this.context.useNormal = true;
/* 61:   */     
/* 62:59 */     this.context.setOrientation(0, 3);
/* 63:60 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 64:   */     
/* 65:62 */     this.context.bindTexture("/eloraam/control/control1.png");
/* 66:63 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 67:   */     
/* 68:65 */     tessellator.b();
/* 69:66 */     if (md == 0)
/* 70:   */     {
/* 71:67 */       this.context.setTex(0, 18, 17, 17, 16, 16);
/* 72:68 */       this.context.renderBox(62, 0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/* 73:   */     }
/* 74:69 */     else if (md == 1)
/* 75:   */     {
/* 76:70 */       this.context.setTex(0, 34, 33, 33, 32, 32);
/* 77:71 */       this.context.renderBox(62, 0.0D, 0.125D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 78:   */     }
/* 79:73 */     tessellator.a();
/* 80:   */     
/* 81:75 */     this.context.useNormal = false;
/* 82:76 */     this.context.unbindTexture();
/* 83:   */   }
/* 84:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.RenderBackplane
 * JD-Core Version:    0.7.0.1
 */