/*   1:    */ package com.eloraam.redpower.lighting;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.RenderContext;
/*   8:    */ import com.eloraam.redpower.core.RenderCustomBlock;
/*   9:    */ import com.eloraam.redpower.core.RenderLib;
/*  10:    */ import com.eloraam.redpower.core.RenderModel;
/*  11:    */ import java.util.Random;
/*  12:    */ import net.minecraftforge.client.MinecraftForgeClient;
/*  13:    */ import yc;
/*  14:    */ import ym;
/*  15:    */ 
/*  16:    */ public class RenderShapedLamp
/*  17:    */   extends RenderCustomBlock
/*  18:    */ {
/*  19:    */   public RenderShapedLamp(amq bl)
/*  20:    */   {
/*  21: 20 */     super(bl);
/*  22: 21 */     this.context = new RenderContext();
/*  23: 22 */     this.modelLamp1 = RenderModel.loadModel("/eloraam/lighting/shlamp1.obj");
/*  24: 23 */     this.modelLamp2 = RenderModel.loadModel("/eloraam/lighting/shlamp2.obj");
/*  25:    */     
/*  26: 25 */     this.lightColorsOff = new int[16];
/*  27: 26 */     for (int i = 0; i < 16; i++)
/*  28:    */     {
/*  29: 27 */       int r = this.lightColors[i] & 0xFF;
/*  30: 28 */       int g = this.lightColors[i] >> 8 & 0xFF;
/*  31: 29 */       int b = this.lightColors[i] >> 16 & 0xFF;
/*  32: 30 */       int v = (r + g + b) / 3;
/*  33:    */       
/*  34: 32 */       r = (r + 2 * v) / 5;g = (g + 2 * v) / 5;b = (b + 2 * v) / 5;
/*  35:    */       
/*  36: 34 */       this.lightColorsOff[i] = (r | g << 8 | b << 16);
/*  37:    */     }
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  41:    */   
/*  42:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  43:    */   {
/*  44: 45 */     TileShapedLamp tsl = (TileShapedLamp)CoreLib.getTileEntity(iba, i, j, k, TileShapedLamp.class);
/*  45: 47 */     if (tsl == null) {
/*  46: 47 */       return;
/*  47:    */     }
/*  48: 49 */     boolean lit = tsl.Powered != tsl.Inverted;
/*  49:    */     
/*  50: 51 */     this.context.setDefaults();
/*  51: 52 */     this.context.setPos(i, j, k);
/*  52: 53 */     this.context.setOrientation(tsl.Rotation, 0);
/*  53: 54 */     this.context.readGlobalLights(iba, i, j, k);
/*  54: 56 */     switch (tsl.Style)
/*  55:    */     {
/*  56:    */     case 0: 
/*  57: 58 */       this.context.bindModelOffset(this.modelLamp1, 0.5D, 0.5D, 0.5D);
/*  58: 59 */       break;
/*  59:    */     case 1: 
/*  60: 61 */       this.context.bindModelOffset(this.modelLamp2, 0.5D, 0.5D, 0.5D);
/*  61:    */     }
/*  62: 65 */     if (MinecraftForgeClient.getRenderPass() != 1)
/*  63:    */     {
/*  64: 66 */       int f = this.block.e(iba, i, j, k);
/*  65: 67 */       this.context.bindTexture("/eloraam/lighting/lighting1.png");
/*  66: 68 */       this.context.setBrightness(f);
/*  67: 69 */       this.context.renderModelGroup(0, 0);
/*  68: 70 */       if (lit)
/*  69:    */       {
/*  70: 71 */         this.context.setTintHex(this.lightColors[(tsl.Color & 0xF)]);
/*  71: 72 */         this.context.setBrightness(15728880);
/*  72:    */       }
/*  73:    */       else
/*  74:    */       {
/*  75: 74 */         this.context.setTintHex(this.lightColorsOff[(tsl.Color & 0xF)]);
/*  76:    */       }
/*  77: 76 */       this.context.renderModelGroup(1, 0);
/*  78: 77 */       this.context.unbindTexture();
/*  79: 78 */       return;
/*  80:    */     }
/*  81: 80 */     if (!lit) {
/*  82: 80 */       return;
/*  83:    */     }
/*  84: 82 */     RenderLib.bindTexture("/eloraam/lighting/lighting1.png", 1);
/*  85:    */     
/*  86: 84 */     int tc = this.lightColors[(tsl.Color & 0xF)];
/*  87: 85 */     this.context.setTint((tc >> 16) / 255.0F, (tc >> 8 & 0xFF) / 255.0F, (tc & 0xFF) / 255.0F);
/*  88:    */     
/*  89: 87 */     this.context.setAlpha(0.3F);
/*  90:    */     
/*  91: 89 */     this.context.renderModelGroup(2, 0);
/*  92: 90 */     RenderLib.unbindTexture();
/*  93:    */   }
/*  94:    */   
/*  95: 94 */   int[] lightColors = { 16777215, 12608256, 11868853, 7308529, 12566272, 7074048, 15812213, 5460819, 9671571, 34695, 6160576, 1250240, 5187328, 558848, 10620678, 2039583 };
/*  96:    */   int[] lightColorsOff;
/*  97:    */   RenderContext context;
/*  98:    */   protected RenderModel modelLamp1;
/*  99:    */   protected RenderModel modelLamp2;
/* 100:    */   
/* 101:    */   public void renderInvBlock(bbb renderblocks, int md)
/* 102:    */   {
/* 103:103 */     baz tessellator = baz.a;
/* 104:104 */     this.block.f();
/* 105:    */     
/* 106:106 */     boolean lit = false;
/* 107:    */     
/* 108:108 */     this.context.setDefaults();
/* 109:109 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 110:    */     
/* 111:111 */     this.context.bindTexture("/eloraam/lighting/lighting1.png");
/* 112:112 */     tessellator.b();
/* 113:113 */     this.context.useNormal = true;
/* 114:115 */     switch (md >> 5)
/* 115:    */     {
/* 116:    */     case 0: 
/* 117:117 */       this.context.bindModelOffset(this.modelLamp1, 0.5D, 0.5D, 0.5D);
/* 118:118 */       break;
/* 119:    */     case 1: 
/* 120:120 */       this.context.bindModelOffset(this.modelLamp2, 0.5D, 0.5D, 0.5D);
/* 121:    */     }
/* 122:123 */     this.context.renderModelGroup(0, 0);
/* 123:125 */     if ((md & 0x10) > 0) {
/* 124:126 */       this.context.setTintHex(this.lightColors[(md & 0xF)]);
/* 125:    */     } else {
/* 126:128 */       this.context.setTintHex(this.lightColorsOff[(md & 0xF)]);
/* 127:    */     }
/* 128:130 */     this.context.renderModelGroup(1, 0);
/* 129:    */     
/* 130:132 */     this.context.useNormal = false;
/* 131:133 */     tessellator.a();
/* 132:134 */     this.context.unbindTexture();
/* 133:    */   }
/* 134:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.lighting.RenderShapedLamp
 * JD-Core Version:    0.7.0.1
 */