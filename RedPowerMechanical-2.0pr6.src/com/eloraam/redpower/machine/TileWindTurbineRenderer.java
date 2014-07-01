/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import baz;
/*   5:    */ import bdx;
/*   6:    */ import com.eloraam.redpower.core.Matrix3;
/*   7:    */ import com.eloraam.redpower.core.RenderContext;
/*   8:    */ import com.eloraam.redpower.core.RenderModel;
/*   9:    */ import com.eloraam.redpower.core.WorldCoord;
/*  10:    */ import yc;
/*  11:    */ 
/*  12:    */ public class TileWindTurbineRenderer
/*  13:    */   extends bdx
/*  14:    */ {
/*  15:    */   RenderModel modelWoodTurbine;
/*  16:    */   RenderModel modelWoodWindmill;
/*  17:    */   
/*  18:    */   public TileWindTurbineRenderer()
/*  19:    */   {
/*  20: 22 */     this.modelWoodTurbine = RenderModel.loadModel("/eloraam/machine/vawt.obj");
/*  21: 23 */     this.modelWoodWindmill = RenderModel.loadModel("/eloraam/machine/windmill.obj");
/*  22: 24 */     this.modelWoodTurbine.scale(0.0625D);
/*  23: 25 */     this.modelWoodWindmill.scale(0.0625D);
/*  24: 26 */     this.context.setDefaults();
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void a(any te, double x, double y, double z, float f)
/*  28:    */   {
/*  29: 32 */     if (!(te instanceof TileWindTurbine)) {
/*  30: 32 */       return;
/*  31:    */     }
/*  32: 33 */     TileWindTurbine twt = (TileWindTurbine)te;
/*  33: 35 */     if (!twt.hasBlades) {
/*  34: 35 */       return;
/*  35:    */     }
/*  36: 37 */     int wtt = twt.windmillType;
/*  37:    */     
/*  38: 39 */     baz tessellator = baz.a;
/*  39: 40 */     a("/eloraam/machine/vawt.png");
/*  40:    */     
/*  41:    */ 
/*  42: 43 */     tessellator.b();
/*  43:    */     
/*  44: 45 */     WorldCoord wc = new WorldCoord(te);
/*  45: 46 */     wc.step(twt.Rotation ^ 0x1);
/*  46:    */     
/*  47: 48 */     int lv = te.k.i(wc.x, wc.y, wc.z, 0);
/*  48:    */     
/*  49:    */ 
/*  50: 51 */     tessellator.c(lv);
/*  51:    */     
/*  52: 53 */     this.context.useNormal = true;
/*  53: 55 */     if (twt.hasBrakes) {
/*  54: 55 */       f = (float)(f * 0.1D);
/*  55:    */     }
/*  56: 56 */     double tm = f * twt.speed + twt.phase;
/*  57: 58 */     if (wtt == 2) {
/*  58: 58 */       tm = -tm;
/*  59:    */     }
/*  60: 60 */     this.context.setOrientation(twt.Rotation, 0);
/*  61:    */     
/*  62: 62 */     this.context.basis = Matrix3.getRotY(-4.0E-006D * tm).multiply(this.context.basis);
/*  63:    */     
/*  64:    */ 
/*  65: 65 */     this.context.setPos(x, y, z);
/*  66: 66 */     this.context.setRelPos(0.5D, 0.875D, 0.5D);
/*  67: 67 */     switch (wtt)
/*  68:    */     {
/*  69:    */     case 1: 
/*  70: 69 */       this.context.bindModelOffset(this.modelWoodTurbine, 0.5D, 0.5D, 0.5D);
/*  71: 70 */       break;
/*  72:    */     case 2: 
/*  73: 72 */       this.context.bindModelOffset(this.modelWoodWindmill, 0.5D, 0.5D, 0.5D);
/*  74: 73 */       break;
/*  75:    */     default: 
/*  76: 74 */       return;
/*  77:    */     }
/*  78: 77 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/*  79: 78 */     this.context.renderModelGroup(0, 0);
/*  80: 80 */     if (wtt == 1)
/*  81:    */     {
/*  82: 81 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/*  83: 82 */       this.context.renderModelGroup(1, 1);
/*  84: 83 */       this.context.renderModelGroup(1, 3);
/*  85: 84 */       this.context.renderModelGroup(1, 5);
/*  86:    */       
/*  87: 86 */       this.context.setTint(1.0F, 0.1F, 0.1F);
/*  88: 87 */       this.context.renderModelGroup(1, 2);
/*  89: 88 */       this.context.renderModelGroup(1, 4);
/*  90: 89 */       this.context.renderModelGroup(1, 6);
/*  91:    */     }
/*  92:    */     else
/*  93:    */     {
/*  94: 91 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/*  95: 92 */       this.context.renderModelGroup(1, 1);
/*  96: 93 */       this.context.renderModelGroup(1, 3);
/*  97:    */       
/*  98: 95 */       this.context.setTint(1.0F, 0.1F, 0.1F);
/*  99: 96 */       this.context.renderModelGroup(1, 2);
/* 100: 97 */       this.context.renderModelGroup(1, 4);
/* 101:    */     }
/* 102: 99 */     tessellator.a();
/* 103:    */   }
/* 104:    */   
/* 105:104 */   RenderContext context = new RenderContext();
/* 106:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileWindTurbineRenderer
 * JD-Core Version:    0.7.0.1
 */