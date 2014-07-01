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
/* 14:   */ public class RenderMotor
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderMotor(amq bl)
/* 20:   */   {
/* 21:16 */     super(bl);
/* 22:17 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:28 */     TileMotor tb = (TileMotor)CoreLib.getTileEntity(iba, i, j, k, TileMotor.class);
/* 30:30 */     if (tb == null) {
/* 31:30 */       return;
/* 32:   */     }
/* 33:32 */     this.context.setDefaults();
/* 34:33 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 35:   */     
/* 36:35 */     this.context.setPos(i, j, k);
/* 37:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:   */     
/* 39:38 */     this.context.setTexFlags(64);
/* 40:39 */     if (tb.Active)
/* 41:   */     {
/* 42:40 */       this.context.setTex(150, 255, 239, 239, 155, 155);
/* 43:   */     }
/* 44:   */     else
/* 45:   */     {
/* 46:42 */       int tx = '' + (tb.Charged ? 1 : 0);
/* 47:43 */       this.context.setTex(150, 151, tx, tx, 155, 155);
/* 48:   */     }
/* 49:46 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 50:47 */     this.context.setupBox();
/* 51:48 */     this.context.transform();
/* 52:   */     
/* 53:   */ 
/* 54:51 */     this.context.orientTextureNew(tb.Rotation);
/* 55:   */     
/* 56:53 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 57:54 */     this.context.renderGlobFaces(63);
/* 58:55 */     RenderLib.unbindTexture();
/* 59:   */   }
/* 60:   */   
/* 61:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 62:   */   {
/* 63:59 */     this.block.f();
/* 64:   */     
/* 65:61 */     this.context.setDefaults();
/* 66:62 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 67:63 */     this.context.useNormal = true;
/* 68:   */     
/* 69:65 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 70:66 */     baz tessellator = baz.a;
/* 71:67 */     tessellator.b();
/* 72:   */     
/* 73:69 */     this.context.setTex(150, 151, 144, 144, 155, 155);
/* 74:70 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 75:   */     
/* 76:72 */     tessellator.a();
/* 77:73 */     RenderLib.unbindTexture();
/* 78:   */     
/* 79:75 */     this.context.useNormal = false;
/* 80:   */   }
/* 81:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderMotor
 * JD-Core Version:    0.7.0.1
 */