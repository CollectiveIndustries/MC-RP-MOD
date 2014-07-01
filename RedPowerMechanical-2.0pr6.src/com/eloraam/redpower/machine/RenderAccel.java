/*  1:   */ package com.eloraam.redpower.machine;
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
/* 14:   */ public class RenderAccel
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderModel model;
/* 18:   */   protected RenderContext context;
/* 19:   */   
/* 20:   */   public RenderAccel(amq bl)
/* 21:   */   {
/* 22:16 */     super(bl);
/* 23:17 */     this.context = new RenderContext();
/* 24:18 */     this.model = RenderModel.loadModel("/eloraam/machine/accel.obj");
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 28:   */   
/* 29:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 30:   */   {
/* 31:29 */     TileAccel tb = (TileAccel)CoreLib.getTileEntity(iba, i, j, k, TileAccel.class);
/* 32:31 */     if (tb == null) {
/* 33:31 */       return;
/* 34:   */     }
/* 35:33 */     this.context.setDefaults();
/* 36:34 */     this.context.setPos(i, j, k);
/* 37:35 */     this.context.setOrientation(tb.Rotation, 0);
/* 38:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 39:38 */     if (tb.Charged) {
/* 40:39 */       this.context.setBrightness(15728880);
/* 41:   */     } else {
/* 42:41 */       this.context.setBrightness(this.block.e(iba, i, j, k));
/* 43:   */     }
/* 44:45 */     this.context.bindTexture("/eloraam/machine/machine1.png");
/* 45:46 */     this.context.bindModelOffset(this.model, 0.5D, 0.5D, 0.5D);
/* 46:47 */     this.context.renderModelGroup(0, 0);
/* 47:48 */     this.context.renderModelGroup(1, 1 + (tb.Charged ? 1 : 0));
/* 48:50 */     if (tb.Charged) {
/* 49:51 */       this.context.setBrightness(this.block.e(iba, i, j, k));
/* 50:   */     }
/* 51:55 */     tb.recache();
/* 52:57 */     if ((tb.conCache & 0x1) > 0) {
/* 53:57 */       this.context.renderModelGroup(2, 2);
/* 54:   */     }
/* 55:58 */     if ((tb.conCache & 0x2) > 0) {
/* 56:58 */       this.context.renderModelGroup(2, 1);
/* 57:   */     }
/* 58:59 */     if ((tb.conCache & 0x4) > 0) {
/* 59:59 */       this.context.renderModelGroup(3, 2);
/* 60:   */     }
/* 61:60 */     if ((tb.conCache & 0x8) > 0) {
/* 62:60 */       this.context.renderModelGroup(3, 1);
/* 63:   */     }
/* 64:62 */     this.context.unbindTexture();
/* 65:   */   }
/* 66:   */   
/* 67:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 68:   */   {
/* 69:66 */     this.block.f();
/* 70:   */     
/* 71:68 */     this.context.setDefaults();
/* 72:69 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 73:70 */     this.context.setOrientation(2, 0);
/* 74:71 */     this.context.bindTexture("/eloraam/machine/machine1.png");
/* 75:72 */     baz tessellator = baz.a;
/* 76:73 */     tessellator.b();
/* 77:74 */     this.context.useNormal = true;
/* 78:   */     
/* 79:76 */     this.context.bindModelOffset(this.model, 0.5D, 0.5D, 0.5D);
/* 80:77 */     this.context.renderModelGroup(0, 0);
/* 81:78 */     this.context.renderModelGroup(1, 1);
/* 82:   */     
/* 83:80 */     this.context.useNormal = false;
/* 84:81 */     tessellator.a();
/* 85:82 */     this.context.unbindTexture();
/* 86:   */   }
/* 87:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderAccel
 * JD-Core Version:    0.7.0.1
 */