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
/* 14:   */ public class RenderChargingBench
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderChargingBench(amq bl)
/* 20:   */   {
/* 21:16 */     super(bl);
/* 22:17 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:28 */     TileChargingBench tb = (TileChargingBench)CoreLib.getTileEntity(iba, i, j, k, TileChargingBench.class);
/* 30:30 */     if (tb == null) {
/* 31:30 */       return;
/* 32:   */     }
/* 33:32 */     this.context.setDefaults();
/* 34:33 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 35:   */     
/* 36:35 */     this.context.setPos(i, j, k);
/* 37:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:38 */     if (tb.Active)
/* 39:   */     {
/* 40:39 */       int tx = 192 + tb.getStorageForRender();
/* 41:40 */       this.context.setTex(180, 177, tx, tx, 179, 179);
/* 42:   */     }
/* 43:   */     else
/* 44:   */     {
/* 45:42 */       int tx = 181 + tb.getStorageForRender() + (tb.Powered ? 5 : 0);
/* 46:   */       
/* 47:44 */       this.context.setTex(180, 176, tx, tx, 178, 178);
/* 48:   */     }
/* 49:47 */     this.context.rotateTextures(tb.Rotation);
/* 50:48 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 51:49 */     this.context.setupBox();
/* 52:50 */     this.context.transform();
/* 53:   */     
/* 54:   */ 
/* 55:53 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 56:54 */     this.context.renderGlobFaces(63);
/* 57:55 */     RenderLib.unbindTexture();
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 61:   */   {
/* 62:59 */     this.block.f();
/* 63:   */     
/* 64:61 */     this.context.setDefaults();
/* 65:62 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 66:63 */     this.context.useNormal = true;
/* 67:   */     
/* 68:65 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 69:66 */     baz tessellator = baz.a;
/* 70:67 */     tessellator.b();
/* 71:   */     
/* 72:69 */     this.context.setTex(180, 176, 181, 181, 178, 178);
/* 73:70 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 74:   */     
/* 75:72 */     tessellator.a();
/* 76:73 */     RenderLib.unbindTexture();
/* 77:   */     
/* 78:75 */     this.context.useNormal = false;
/* 79:   */   }
/* 80:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderChargingBench
 * JD-Core Version:    0.7.0.1
 */