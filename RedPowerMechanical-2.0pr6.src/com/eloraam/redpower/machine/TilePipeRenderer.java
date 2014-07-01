/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ import baz;
/*  5:   */ import bdx;
/*  6:   */ import com.eloraam.redpower.core.FluidBuffer;
/*  7:   */ import com.eloraam.redpower.core.FluidClass;
/*  8:   */ import com.eloraam.redpower.core.PipeLib;
/*  9:   */ import com.eloraam.redpower.core.RenderContext;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class TilePipeRenderer
/* 14:   */   extends bdx
/* 15:   */ {
/* 16:   */   public TilePipeRenderer()
/* 17:   */   {
/* 18:24 */     this.context.setDefaults();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void a(any te, double x, double y, double z, float f)
/* 22:   */   {
/* 23:30 */     TilePipe tp = (TilePipe)te;
/* 24:31 */     int lvl = tp.pipebuf.getLevel();
/* 25:32 */     if ((tp.pipebuf.Type == 0) || (lvl <= 0)) {
/* 26:32 */       return;
/* 27:   */     }
/* 28:34 */     float lvn = Math.min(1.0F, lvl / tp.pipebuf.getMaxLevel());
/* 29:35 */     tp.cacheCon();
/* 30:36 */     int sides = tp.ConCache;
/* 31:37 */     FluidClass fcl = PipeLib.getLiquidClass(tp.pipebuf.Type);
/* 32:38 */     if (fcl == null) {
/* 33:38 */       return;
/* 34:   */     }
/* 35:40 */     baz tessellator = baz.a;
/* 36:   */     
/* 37:42 */     a(fcl.getTextureFile());
/* 38:   */     
/* 39:44 */     int lv = te.k.i(te.l, te.m, te.n, 0);
/* 40:   */     
/* 41:   */ 
/* 42:47 */     GL11.glEnable(3042);
/* 43:48 */     GL11.glBlendFunc(770, 771);
/* 44:   */     
/* 45:50 */     GL11.glDisable(2896);
/* 46:   */     
/* 47:52 */     tessellator.b();
/* 48:53 */     this.context.setBrightness(lv);
/* 49:54 */     this.context.setPos(x, y, z);
/* 50:   */     
/* 51:56 */     this.context.setTex(fcl.getTexture());
/* 52:58 */     if ((sides & 0x3) > 0)
/* 53:   */     {
/* 54:59 */       float y1 = 0.5F;float y2 = 0.5F;
/* 55:60 */       if ((sides & 0x1) > 0) {
/* 56:60 */         y1 = 0.0F;
/* 57:   */       }
/* 58:61 */       if ((sides & 0x2) > 0) {
/* 59:61 */         y2 = 1.0F;
/* 60:   */       }
/* 61:63 */       float n = 0.124F * lvn;
/* 62:64 */       this.context.renderBox(60, 0.5F - n, y1, 0.5F - n, 0.5F + n, y2, 0.5F + n);
/* 63:   */     }
/* 64:66 */     if ((sides & 0xC) > 0)
/* 65:   */     {
/* 66:67 */       float z1 = 0.5F;float z2 = 0.5F;
/* 67:68 */       if ((sides & 0x4) > 0) {
/* 68:68 */         z1 = 0.0F;
/* 69:   */       }
/* 70:69 */       if ((sides & 0x8) > 0) {
/* 71:69 */         z2 = 1.0F;
/* 72:   */       }
/* 73:71 */       float n = 0.248F * lvn;
/* 74:72 */       this.context.renderBox(51, 0.3759999871253967D, 0.3759999871253967D, z1, 0.6240000128746033D, 0.376F + n, z2);
/* 75:   */     }
/* 76:74 */     if ((sides & 0x30) > 0)
/* 77:   */     {
/* 78:75 */       float x1 = 0.5F;float x2 = 0.5F;
/* 79:76 */       if ((sides & 0x10) > 0) {
/* 80:76 */         x1 = 0.0F;
/* 81:   */       }
/* 82:77 */       if ((sides & 0x20) > 0) {
/* 83:77 */         x2 = 1.0F;
/* 84:   */       }
/* 85:79 */       float n = 0.248F * lvn;
/* 86:80 */       this.context.renderBox(15, x1, 0.3759999871253967D, 0.3759999871253967D, x2, 0.376F + n, 0.6240000128746033D);
/* 87:   */     }
/* 88:83 */     tessellator.a();
/* 89:84 */     GL11.glEnable(2896);
/* 90:85 */     GL11.glDisable(3042);
/* 91:   */   }
/* 92:   */   
/* 93:88 */   RenderContext context = new RenderContext();
/* 94:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TilePipeRenderer
 * JD-Core Version:    0.7.0.1
 */