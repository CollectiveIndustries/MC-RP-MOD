/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.CoverRenderer;
/*   8:    */ import com.eloraam.redpower.core.RenderContext;
/*   9:    */ import com.eloraam.redpower.core.RenderLib;
/*  10:    */ import com.eloraam.redpower.core.TubeLib;
/*  11:    */ import java.util.Random;
/*  12:    */ import yc;
/*  13:    */ import ym;
/*  14:    */ 
/*  15:    */ public class RenderFrameTube
/*  16:    */   extends RenderTube
/*  17:    */ {
/*  18:    */   public RenderFrameTube(amq bl)
/*  19:    */   {
/*  20: 17 */     super(bl);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  24:    */   
/*  25:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  26:    */   {
/*  27: 28 */     int cons = 0;
/*  28: 29 */     TileFrameTube tc = (TileFrameTube)CoreLib.getTileEntity(iba, i, j, k, TileFrameTube.class);
/*  29: 31 */     if (tc == null) {
/*  30: 31 */       return;
/*  31:    */     }
/*  32: 33 */     this.context.setDefaults();
/*  33: 34 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/*  34: 35 */     this.context.setPos(i, j, k);
/*  35: 36 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  36: 37 */     this.context.readGlobalLights(iba, i, j, k);
/*  37: 39 */     if (tc.CoverSides > 0)
/*  38:    */     {
/*  39: 40 */       short[] sides = new short[6];
/*  40: 41 */       for (int n = 0; n < 6; n++)
/*  41:    */       {
/*  42: 42 */         sides[n] = tc.Covers[n];
/*  43: 43 */         int m = sides[n] >> 8;
/*  44: 44 */         if ((m == 1) || (m == 4))
/*  45:    */         {
/*  46: 44 */           int tmp152_150 = n; short[] tmp152_148 = sides;tmp152_148[tmp152_150] = ((short)(tmp152_148[tmp152_150] - 256));
/*  47:    */         }
/*  48:    */       }
/*  49: 46 */       this.coverRenderer.render(tc.CoverSides, sides);
/*  50:    */     }
/*  51: 50 */     cons = TubeLib.getConnections(iba, i, j, k);
/*  52:    */     
/*  53: 52 */     this.context.exactTextureCoordinates = true;
/*  54: 53 */     RenderLib.bindTexture("/eloraam/machine/machine1.png", 1);
/*  55: 54 */     this.context.setTex(2);
/*  56:    */     
/*  57: 56 */     int sides = tc.CoverSides | cons;
/*  58: 57 */     for (int n = 0; n < 6; n++)
/*  59:    */     {
/*  60: 58 */       int m = 1 << n;
/*  61: 59 */       int tx = 1;
/*  62: 60 */       this.coverRenderer.start();
/*  63: 61 */       if ((sides & m) > 0)
/*  64:    */       {
/*  65: 62 */         if ((tc.StickySides & m) > 0) {
/*  66: 62 */           tx = 4;
/*  67:    */         } else {
/*  68: 63 */           tx = 2;
/*  69:    */         }
/*  70:    */       }
/*  71:    */       else
/*  72:    */       {
/*  73: 65 */         m |= 1 << (n ^ 0x1);
/*  74: 66 */         this.context.setTexNum(n ^ 0x1, 1);
/*  75:    */       }
/*  76: 68 */       this.context.setTexNum(n, tx);
/*  77: 69 */       this.coverRenderer.setSize(n, 0.0625F);
/*  78: 70 */       this.context.calcBoundsGlobal();
/*  79: 71 */       this.context.renderGlobFaces(m);
/*  80:    */     }
/*  81: 73 */     this.context.exactTextureCoordinates = false;
/*  82:    */     
/*  83: 75 */     RenderLib.unbindTexture();
/*  84:    */     
/*  85: 77 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/*  86:    */     
/*  87: 79 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  88:    */     
/*  89: 81 */     renderCenterBlock(cons, 64, 65);
/*  90: 83 */     if (tc.paintColor > 0)
/*  91:    */     {
/*  92: 84 */       int pc = this.paintColors[(tc.paintColor - 1)];
/*  93: 85 */       this.context.setTint((pc >> 16) / 255.0F, (pc >> 8 & 0xFF) / 255.0F, (pc & 0xFF) / 255.0F);
/*  94:    */       
/*  95: 87 */       renderBlockPaint(cons, 66);
/*  96:    */     }
/*  97: 89 */     RenderLib.unbindTexture();
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void renderInvBlock(bbb renderblocks, int md)
/* 101:    */   {
/* 102: 93 */     this.block.f();
/* 103:    */     
/* 104: 95 */     this.context.setDefaults();
/* 105: 96 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 106: 97 */     this.context.useNormal = true;
/* 107: 98 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 108:    */     
/* 109:100 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 110:101 */     baz tessellator = baz.a;
/* 111:102 */     tessellator.b();
/* 112:    */     
/* 113:104 */     this.context.setTex(2, 2, 1, 1, 1, 1);
/* 114:105 */     doubleBox(63, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.01F);
/* 115:    */     
/* 116:107 */     this.context.setTex(65, 65, 64, 64, 64, 64);
/* 117:108 */     this.context.renderBox(63, 0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 118:109 */     this.context.renderBox(63, 0.7400000095367432D, 0.9900000095367432D, 0.7400000095367432D, 0.2599999904632568D, 0.009999999776482582D, 0.2599999904632568D);
/* 119:    */     
/* 120:111 */     tessellator.a();
/* 121:112 */     RenderLib.unbindTexture();
/* 122:113 */     this.context.useNormal = false;
/* 123:    */   }
/* 124:    */   
/* 125:    */   void doubleBox(int sides, float x1, float y1, float z1, float x2, float y2, float z2, float ino)
/* 126:    */   {
/* 127:119 */     int s2 = sides << 1 & 0x2A | sides >> 1 & 0x15;
/* 128:    */     
/* 129:121 */     this.context.renderBox(sides, x1, y1, z1, x2, y2, z2);
/* 130:122 */     this.context.renderBox(s2, x2 - ino, y2 - ino, z2 - ino, x1 + ino, y1 + ino, z1 + ino);
/* 131:    */   }
/* 132:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderFrameTube
 * JD-Core Version:    0.7.0.1
 */