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
/* 14:   */ public class RenderBatteryBox
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderBatteryBox(amq bl)
/* 20:   */   {
/* 21:16 */     super(bl);
/* 22:17 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:28 */     TileBatteryBox tb = (TileBatteryBox)CoreLib.getTileEntity(iba, i, j, k, TileBatteryBox.class);
/* 30:30 */     if (tb == null) {
/* 31:30 */       return;
/* 32:   */     }
/* 33:32 */     this.context.setDefaults();
/* 34:33 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 35:   */     
/* 36:35 */     this.context.setPos(i, j, k);
/* 37:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:   */     
/* 39:38 */     int tx = 129 + tb.getStorageForRender();
/* 40:39 */     this.context.setTex(84, 128, tx, tx, tx, tx);
/* 41:   */     
/* 42:41 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 43:42 */     this.context.setupBox();
/* 44:43 */     this.context.transform();
/* 45:   */     
/* 46:45 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 47:46 */     this.context.renderGlobFaces(63);
/* 48:47 */     RenderLib.unbindTexture();
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 52:   */   {
/* 53:51 */     this.block.f();
/* 54:   */     
/* 55:53 */     this.context.setDefaults();
/* 56:54 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 57:55 */     this.context.useNormal = true;
/* 58:   */     
/* 59:57 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 60:58 */     baz tessellator = baz.a;
/* 61:59 */     tessellator.b();
/* 62:   */     
/* 63:61 */     this.context.setTex(84, 128, 129, 129, 129, 129);
/* 64:62 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 65:   */     
/* 66:64 */     tessellator.a();
/* 67:65 */     RenderLib.unbindTexture();
/* 68:   */     
/* 69:67 */     this.context.useNormal = false;
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderBatteryBox
 * JD-Core Version:    0.7.0.1
 */