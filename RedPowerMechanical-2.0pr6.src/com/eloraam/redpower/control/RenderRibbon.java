/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.core.RenderContext;
/*   7:    */ import com.eloraam.redpower.core.RenderLib;
/*   8:    */ import com.eloraam.redpower.core.TileCovered;
/*   9:    */ import com.eloraam.redpower.wiring.RenderWiring;
/*  10:    */ import com.eloraam.redpower.wiring.TileWiring;
/*  11:    */ import java.util.Random;
/*  12:    */ import yc;
/*  13:    */ import ym;
/*  14:    */ 
/*  15:    */ public class RenderRibbon
/*  16:    */   extends RenderWiring
/*  17:    */ {
/*  18:    */   public RenderRibbon(amq bl)
/*  19:    */   {
/*  20: 16 */     super(bl);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  24:    */   
/*  25:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  26:    */   {
/*  27: 26 */     baz tessellator = baz.a;
/*  28:    */     
/*  29: 28 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/*  30:    */     
/*  31:    */ 
/*  32:    */ 
/*  33: 32 */     TileCovered tc = (TileCovered)iba.q(i, j, k);
/*  34: 33 */     if (tc == null) {
/*  35: 33 */       return;
/*  36:    */     }
/*  37: 35 */     this.context.setPos(i, j, k);
/*  38: 36 */     if (tc.CoverSides > 0)
/*  39:    */     {
/*  40: 37 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/*  41:    */       
/*  42:    */ 
/*  43:    */ 
/*  44:    */ 
/*  45:    */ 
/*  46:    */ 
/*  47:    */ 
/*  48: 45 */       this.context.readGlobalLights(iba, i, j, k);
/*  49: 46 */       renderCovers(tc.CoverSides, tc.Covers);
/*  50:    */     }
/*  51: 52 */     TileWiring tw = (TileWiring)tc;
/*  52:    */     
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58: 59 */     int cons = tw.getConnectionMask();
/*  59: 60 */     int indcon = tw.getExtConnectionMask();
/*  60: 61 */     int indconex = tw.EConEMask;
/*  61: 62 */     cons |= indcon;
/*  62:    */     
/*  63: 64 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/*  64: 65 */     setSideTex(1, 2, 1);
/*  65: 66 */     setWireSize(0.5F, 0.0625F);
/*  66:    */     
/*  67: 68 */     RenderLib.bindTexture("/eloraam/control/control1.png");
/*  68: 69 */     renderWireBlock(tw.ConSides, cons, indcon, indconex);
/*  69: 70 */     RenderLib.unbindTexture();
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void renderInvBlock(bbb renderblocks, int md)
/*  73:    */   {
/*  74: 91 */     baz tessellator = baz.a;
/*  75: 92 */     this.block.f();
/*  76: 93 */     int bid = md >> 8;md &= 0xFF;
/*  77:    */     
/*  78: 95 */     this.context.setDefaults();
/*  79: 96 */     this.context.setTexFlags(55);
/*  80: 97 */     this.context.setPos(-0.5D, -0.2000000029802322D, -0.5D);
/*  81:    */     
/*  82: 99 */     setSideTex(1, 2, 1);
/*  83:100 */     setWireSize(0.5F, 0.0625F);
/*  84:    */     
/*  85:102 */     this.context.useNormal = true;
/*  86:103 */     RenderLib.bindTexture("/eloraam/control/control1.png");
/*  87:104 */     tessellator.b();
/*  88:105 */     renderSideWires(127, 0, 0);
/*  89:106 */     tessellator.a();
/*  90:107 */     RenderLib.unbindTexture();
/*  91:108 */     this.context.useNormal = false;
/*  92:    */   }
/*  93:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.RenderRibbon
 * JD-Core Version:    0.7.0.1
 */