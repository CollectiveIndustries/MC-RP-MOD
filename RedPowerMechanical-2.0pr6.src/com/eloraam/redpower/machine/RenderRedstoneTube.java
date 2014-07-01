/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import baz;
/*  5:   */ import bbb;
/*  6:   */ import com.eloraam.redpower.core.CoreLib;
/*  7:   */ import com.eloraam.redpower.core.RenderContext;
/*  8:   */ import com.eloraam.redpower.core.RenderLib;
/*  9:   */ import com.eloraam.redpower.core.TubeLib;
/* 10:   */ import java.util.Random;
/* 11:   */ import yc;
/* 12:   */ import ym;
/* 13:   */ 
/* 14:   */ public class RenderRedstoneTube
/* 15:   */   extends RenderTube
/* 16:   */ {
/* 17:   */   public RenderRedstoneTube(amq bl)
/* 18:   */   {
/* 19:16 */     super(bl);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 23:   */   
/* 24:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 25:   */   {
/* 26:26 */     int cons = 0;
/* 27:27 */     TileRedstoneTube tt = (TileRedstoneTube)CoreLib.getTileEntity(iba, i, j, k, TileRedstoneTube.class);
/* 28:29 */     if (tt == null) {
/* 29:29 */       return;
/* 30:   */     }
/* 31:31 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/* 32:32 */     this.context.setPos(i, j, k);
/* 33:33 */     if (tt.CoverSides > 0)
/* 34:   */     {
/* 35:34 */       this.context.readGlobalLights(iba, i, j, k);
/* 36:35 */       renderCovers(tt.CoverSides, tt.Covers);
/* 37:   */     }
/* 38:38 */     cons = TubeLib.getConnections(iba, i, j, k) | tt.getConnectionMask() >> 24;
/* 39:   */     
/* 40:   */ 
/* 41:   */ 
/* 42:   */ 
/* 43:43 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/* 44:   */     
/* 45:45 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 46:46 */     this.context.setPos(i, j, k);
/* 47:   */     
/* 48:48 */     int ps = (tt.PowerState + 84) / 85;
/* 49:   */     
/* 50:50 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 51:51 */     renderCenterBlock(cons, 68 + ps, 72 + ps);
/* 52:53 */     if (tt.paintColor > 0)
/* 53:   */     {
/* 54:54 */       int tc = this.paintColors[(tt.paintColor - 1)];
/* 55:55 */       this.context.setTint((tc >> 16) / 255.0F, (tc >> 8 & 0xFF) / 255.0F, (tc & 0xFF) / 255.0F);
/* 56:   */       
/* 57:57 */       renderBlockPaint(cons, 66);
/* 58:   */     }
/* 59:60 */     RenderLib.unbindTexture();
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 63:   */   {
/* 64:64 */     this.block.f();
/* 65:   */     
/* 66:66 */     this.context.setDefaults();
/* 67:67 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 68:68 */     this.context.useNormal = true;
/* 69:69 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 70:   */     
/* 71:71 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 72:72 */     baz tessellator = baz.a;
/* 73:   */     
/* 74:74 */     tessellator.b();
/* 75:75 */     this.context.useNormal = true;
/* 76:   */     
/* 77:77 */     this.context.setTex(72, 72, 68, 68, 68, 68);
/* 78:78 */     this.context.renderBox(63, 0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 79:79 */     this.context.renderBox(63, 0.7400000095367432D, 0.9900000095367432D, 0.7400000095367432D, 0.2599999904632568D, 0.009999999776482582D, 0.2599999904632568D);
/* 80:   */     
/* 81:81 */     tessellator.a();
/* 82:82 */     RenderLib.unbindTexture();
/* 83:83 */     this.context.useNormal = false;
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderRedstoneTube
 * JD-Core Version:    0.7.0.1
 */