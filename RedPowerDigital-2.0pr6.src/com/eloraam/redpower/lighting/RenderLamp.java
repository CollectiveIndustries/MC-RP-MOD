/*   1:    */ package com.eloraam.redpower.lighting;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.core.RenderContext;
/*   7:    */ import com.eloraam.redpower.core.RenderCustomBlock;
/*   8:    */ import com.eloraam.redpower.core.RenderLib;
/*   9:    */ import java.util.Random;
/*  10:    */ import net.minecraftforge.client.MinecraftForgeClient;
/*  11:    */ import yc;
/*  12:    */ import ym;
/*  13:    */ 
/*  14:    */ public class RenderLamp
/*  15:    */   extends RenderCustomBlock
/*  16:    */ {
/*  17:    */   public RenderLamp(amq bl)
/*  18:    */   {
/*  19: 22 */     super(bl);
/*  20: 23 */     this.context = new RenderContext();
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  24:    */   
/*  25:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  26:    */   {
/*  27: 57 */     boolean lit = ((BlockLamp)this.block).lit;
/*  28:    */     
/*  29: 59 */     this.context.setPos(i, j, k);
/*  30: 60 */     this.context.setOrientation(0, 0);
/*  31: 61 */     this.context.readGlobalLights(iba, i, j, k);
/*  32: 63 */     if (MinecraftForgeClient.getRenderPass() != 1)
/*  33:    */     {
/*  34: 64 */       float f = this.block.f(iba, i, j, k);
/*  35: 65 */       if (lit) {
/*  36: 65 */         f = 1.0F;
/*  37:    */       }
/*  38: 67 */       this.context.startWorldRender(renderblocks);
/*  39: 68 */       this.context.bindTexture("/eloraam/lighting/lighting1.png");
/*  40:    */       
/*  41: 70 */       this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  42: 71 */       this.context.setTexFlags(0);
/*  43: 72 */       this.context.setupBox();
/*  44: 73 */       this.context.transform();
/*  45: 74 */       if (lit)
/*  46:    */       {
/*  47: 75 */         this.context.setTint(f, f, f);
/*  48: 76 */         this.context.setLocalLights(1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F);
/*  49: 77 */         this.context.setTex(32 + md);
/*  50:    */         
/*  51: 79 */         this.context.doMappingBox(63);
/*  52: 80 */         this.context.doLightLocal(63);
/*  53: 81 */         this.context.renderFlat(63);
/*  54:    */       }
/*  55:    */       else
/*  56:    */       {
/*  57: 83 */         this.context.setTint(1.0F, 1.0F, 1.0F);
/*  58: 84 */         this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  59: 85 */         this.context.setTex(16 + md);
/*  60:    */         
/*  61: 87 */         this.context.renderGlobFaces(63);
/*  62:    */       }
/*  63: 89 */       this.context.unbindTexture();
/*  64: 90 */       this.context.endWorldRender();
/*  65: 91 */       return;
/*  66:    */     }
/*  67: 93 */     if (!lit) {
/*  68: 93 */       return;
/*  69:    */     }
/*  70: 95 */     RenderLib.bindTexture("/eloraam/lighting/lighting1.png", 1);
/*  71:    */     
/*  72:    */ 
/*  73:    */ 
/*  74:    */ 
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:104 */     int tc = this.lightColors[md];
/*  80:105 */     this.context.setTint((tc >> 16) / 255.0F, (tc >> 8 & 0xFF) / 255.0F, (tc & 0xFF) / 255.0F);
/*  81:    */     
/*  82:    */ 
/*  83:108 */     this.context.setLocalLights(1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F);
/*  84:109 */     this.context.setSize(-0.05D, -0.05D, -0.05D, 1.05D, 1.05D, 1.05D);
/*  85:110 */     this.context.setupBox();
/*  86:111 */     this.context.transform();
/*  87:112 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  88:113 */     this.context.doMappingBox(63);
/*  89:114 */     this.context.doLightLocal(63);
/*  90:115 */     this.context.renderAlpha(63, 0.5F);
/*  91:    */     
/*  92:    */ 
/*  93:    */ 
/*  94:    */ 
/*  95:    */ 
/*  96:    */ 
/*  97:122 */     RenderLib.unbindTexture();
/*  98:    */   }
/*  99:    */   
/* 100:126 */   int[] lightColors = { 16777215, 12608256, 11868853, 7308529, 12566272, 7074048, 15812213, 5460819, 9671571, 34695, 6160576, 1250240, 5187328, 558848, 10620678, 2039583 };
/* 101:    */   RenderContext context;
/* 102:    */   
/* 103:    */   public void renderInvBlock(bbb renderblocks, int md)
/* 104:    */   {
/* 105:133 */     baz tessellator = baz.a;
/* 106:134 */     this.block.f();
/* 107:    */     
/* 108:136 */     boolean lit = ((BlockLamp)this.block).lit;
/* 109:    */     
/* 110:138 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 111:139 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/* 112:140 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 113:141 */     if (lit) {
/* 114:142 */       this.context.setTex(32 + md);
/* 115:    */     } else {
/* 116:144 */       this.context.setTex(16 + md);
/* 117:    */     }
/* 118:146 */     this.context.setOrientation(0, 0);
/* 119:    */     
/* 120:148 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 121:149 */     this.context.calcBounds();
/* 122:    */     
/* 123:151 */     this.context.bindTexture("/eloraam/lighting/lighting1.png");
/* 124:152 */     tessellator.b();
/* 125:153 */     this.context.useNormal = true;
/* 126:154 */     this.context.renderFaces(63);
/* 127:155 */     this.context.useNormal = false;
/* 128:156 */     tessellator.a();
/* 129:157 */     this.context.unbindTexture();
/* 130:    */   }
/* 131:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.lighting.RenderLamp
 * JD-Core Version:    0.7.0.1
 */