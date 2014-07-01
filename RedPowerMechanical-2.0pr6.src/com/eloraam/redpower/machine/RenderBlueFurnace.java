/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.base.TileAppliance;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.RenderContext;
/*   9:    */ import com.eloraam.redpower.core.RenderCustomBlock;
/*  10:    */ import com.eloraam.redpower.core.RenderLib;
/*  11:    */ import java.util.Random;
/*  12:    */ import yc;
/*  13:    */ import ym;
/*  14:    */ 
/*  15:    */ public class RenderBlueFurnace
/*  16:    */   extends RenderCustomBlock
/*  17:    */ {
/*  18:    */   protected RenderContext context;
/*  19:    */   
/*  20:    */   public RenderBlueFurnace(amq bl)
/*  21:    */   {
/*  22: 16 */     super(bl);
/*  23: 17 */     this.context = new RenderContext();
/*  24:    */   }
/*  25:    */   
/*  26:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  27:    */   
/*  28:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  29:    */   {
/*  30: 59 */     TileAppliance tb = (TileAppliance)CoreLib.getTileEntity(iba, i, j, k, TileAppliance.class);
/*  31: 61 */     if (tb == null) {
/*  32: 61 */       return;
/*  33:    */     }
/*  34: 63 */     this.context.setDefaults();
/*  35: 64 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  36:    */     
/*  37: 66 */     this.context.setPos(i, j, k);
/*  38: 67 */     this.context.readGlobalLights(iba, i, j, k);
/*  39: 69 */     if (md == 1)
/*  40:    */     {
/*  41: 70 */       int tx = tb.Active ? 82 : 81;
/*  42: 71 */       this.context.setTex(84, 83, tx, 80, 80, 80);
/*  43:    */     }
/*  44:    */     else
/*  45:    */     {
/*  46: 73 */       int tx = tb.Active ? 162 : 161;
/*  47: 74 */       this.context.setTex(84, 163, tx, 160, 160, 160);
/*  48:    */     }
/*  49: 76 */     this.context.rotateTextures(tb.Rotation);
/*  50: 77 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  51: 78 */     this.context.setupBox();
/*  52: 79 */     this.context.transform();
/*  53:    */     
/*  54:    */ 
/*  55: 82 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  56: 83 */     this.context.renderGlobFaces(63);
/*  57: 84 */     RenderLib.unbindTexture();
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void renderInvBlock(bbb renderblocks, int md)
/*  61:    */   {
/*  62: 88 */     this.block.f();
/*  63:    */     
/*  64: 90 */     this.context.setDefaults();
/*  65: 91 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/*  66: 92 */     this.context.useNormal = true;
/*  67:    */     
/*  68: 94 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  69: 95 */     baz tessellator = baz.a;
/*  70: 96 */     tessellator.b();
/*  71: 98 */     if (md == 1) {
/*  72: 99 */       this.context.setTex(84, 83, 80, 81, 80, 80);
/*  73:    */     } else {
/*  74:101 */       this.context.setTex(84, 163, 160, 161, 160, 160);
/*  75:    */     }
/*  76:103 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  77:    */     
/*  78:105 */     tessellator.a();
/*  79:106 */     RenderLib.unbindTexture();
/*  80:    */     
/*  81:108 */     this.context.useNormal = false;
/*  82:    */   }
/*  83:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderBlueFurnace
 * JD-Core Version:    0.7.0.1
 */