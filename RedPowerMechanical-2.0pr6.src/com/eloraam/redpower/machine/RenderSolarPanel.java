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
/* 14:   */ public class RenderSolarPanel
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderSolarPanel(amq bl)
/* 20:   */   {
/* 21:15 */     super(bl);
/* 22:16 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:27 */     TileMachinePanel tm = (TileMachinePanel)CoreLib.getTileEntity(iba, i, j, k, TileMachinePanel.class);
/* 30:29 */     if (tm == null) {
/* 31:29 */       return;
/* 32:   */     }
/* 33:31 */     this.context.setDefaults();
/* 34:32 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 35:   */     
/* 36:34 */     this.context.setPos(i, j, k);
/* 37:35 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:   */     
/* 39:37 */     this.context.setTex(85, 85, 86, 86, 86, 86);
/* 40:   */     
/* 41:39 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
/* 42:40 */     this.context.setupBox();
/* 43:41 */     this.context.transform();
/* 44:   */     
/* 45:43 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 46:44 */     this.context.renderGlobFaces(62);
/* 47:45 */     RenderLib.unbindTexture();
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 51:   */   {
/* 52:49 */     this.block.f();
/* 53:   */     
/* 54:51 */     this.context.setDefaults();
/* 55:52 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 56:53 */     this.context.useNormal = true;
/* 57:   */     
/* 58:55 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 59:56 */     baz tessellator = baz.a;
/* 60:57 */     tessellator.b();
/* 61:   */     
/* 62:59 */     this.context.setTex(85, 85, 86, 86, 86, 86);
/* 63:60 */     this.context.renderBox(62, 0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
/* 64:   */     
/* 65:62 */     tessellator.a();
/* 66:63 */     RenderLib.unbindTexture();
/* 67:   */     
/* 68:65 */     this.context.useNormal = false;
/* 69:   */   }
/* 70:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderSolarPanel
 * JD-Core Version:    0.7.0.1
 */