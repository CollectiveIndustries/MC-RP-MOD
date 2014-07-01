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
/* 14:   */ public class RenderGrate
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderGrate(amq bl)
/* 20:   */   {
/* 21:16 */     super(bl);
/* 22:17 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:28 */     TileGrate tb = (TileGrate)CoreLib.getTileEntity(iba, i, j, k, TileGrate.class);
/* 30:30 */     if (tb == null) {
/* 31:30 */       return;
/* 32:   */     }
/* 33:32 */     this.context.setDefaults();
/* 34:33 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 35:   */     
/* 36:35 */     this.context.setPos(i, j, k);
/* 37:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:37 */     this.context.setTex(tb.Rotation == 0 ? 45 : 43, tb.Rotation == 1 ? 45 : 43, tb.Rotation == 2 ? 45 : 44, tb.Rotation == 3 ? 45 : 44, tb.Rotation == 4 ? 45 : 44, tb.Rotation == 5 ? 45 : 44);
/* 39:   */     
/* 40:   */ 
/* 41:   */ 
/* 42:   */ 
/* 43:   */ 
/* 44:   */ 
/* 45:   */ 
/* 46:45 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 47:   */     
/* 48:47 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 49:48 */     this.context.setupBox();
/* 50:49 */     this.context.transform();
/* 51:   */     
/* 52:51 */     this.context.renderGlobFaces(63);
/* 53:   */     
/* 54:53 */     this.context.setTex(tb.Rotation == 1 ? 46 : 43, tb.Rotation == 0 ? 46 : 43, tb.Rotation == 3 ? 46 : 43, tb.Rotation == 2 ? 46 : 43, tb.Rotation == 5 ? 46 : 43, tb.Rotation == 4 ? 46 : 43);
/* 55:   */     
/* 56:   */ 
/* 57:   */ 
/* 58:   */ 
/* 59:   */ 
/* 60:   */ 
/* 61:   */ 
/* 62:61 */     this.context.setLocalLights(0.3F);
/* 63:62 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/* 64:   */     
/* 65:64 */     this.context.renderBox(63, 0.99D, 0.99D, 0.99D, 0.01D, 0.01D, 0.01D);
/* 66:65 */     RenderLib.unbindTexture();
/* 67:   */   }
/* 68:   */   
/* 69:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 70:   */   {
/* 71:70 */     this.block.f();
/* 72:   */     
/* 73:72 */     this.context.setDefaults();
/* 74:73 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 75:74 */     this.context.useNormal = true;
/* 76:   */     
/* 77:76 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 78:77 */     baz tessellator = baz.a;
/* 79:78 */     tessellator.b();
/* 80:   */     
/* 81:80 */     this.context.setTex(43, 45, 44, 44, 44, 44);
/* 82:81 */     this.context.doubleBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D, 0.01D);
/* 83:   */     
/* 84:83 */     tessellator.a();
/* 85:84 */     RenderLib.unbindTexture();
/* 86:   */     
/* 87:86 */     this.context.useNormal = false;
/* 88:   */   }
/* 89:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderGrate
 * JD-Core Version:    0.7.0.1
 */