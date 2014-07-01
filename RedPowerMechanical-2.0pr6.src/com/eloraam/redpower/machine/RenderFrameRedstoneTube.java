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
/*  15:    */ public class RenderFrameRedstoneTube
/*  16:    */   extends RenderTube
/*  17:    */ {
/*  18:    */   public RenderFrameRedstoneTube(amq bl)
/*  19:    */   {
/*  20: 17 */     super(bl);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  24:    */   
/*  25:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  26:    */   {
/*  27: 28 */     int cons = 0;
/*  28: 29 */     TileFrameRedstoneTube tc = (TileFrameRedstoneTube)CoreLib.getTileEntity(iba, i, j, k, TileFrameRedstoneTube.class);
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
/*  51: 50 */     cons = TubeLib.getConnections(iba, i, j, k) | tc.getConnectionMask() >> 24;
/*  52:    */     
/*  53:    */ 
/*  54: 53 */     this.context.exactTextureCoordinates = true;
/*  55: 54 */     RenderLib.bindTexture("/eloraam/machine/machine1.png", 1);
/*  56: 55 */     this.context.setTex(2);
/*  57:    */     
/*  58: 57 */     int sides = tc.CoverSides | cons;
/*  59: 58 */     for (int n = 0; n < 6; n++)
/*  60:    */     {
/*  61: 59 */       int m = 1 << n;
/*  62: 60 */       int tx = 1;
/*  63: 61 */       this.coverRenderer.start();
/*  64: 62 */       if ((sides & m) > 0)
/*  65:    */       {
/*  66: 63 */         if ((tc.StickySides & m) > 0) {
/*  67: 63 */           tx = 4;
/*  68:    */         } else {
/*  69: 64 */           tx = 2;
/*  70:    */         }
/*  71:    */       }
/*  72:    */       else
/*  73:    */       {
/*  74: 66 */         m |= 1 << (n ^ 0x1);
/*  75: 67 */         this.context.setTexNum(n ^ 0x1, 1);
/*  76:    */       }
/*  77: 69 */       this.context.setTexNum(n, tx);
/*  78: 70 */       this.coverRenderer.setSize(n, 0.0625F);
/*  79: 71 */       this.context.calcBoundsGlobal();
/*  80: 72 */       this.context.renderGlobFaces(m);
/*  81:    */     }
/*  82: 74 */     this.context.exactTextureCoordinates = false;
/*  83:    */     
/*  84: 76 */     RenderLib.unbindTexture();
/*  85:    */     
/*  86: 78 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/*  87:    */     
/*  88: 80 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  89:    */     
/*  90: 82 */     int ps = (tc.PowerState + 84) / 85;
/*  91:    */     
/*  92: 84 */     renderCenterBlock(cons, 68 + ps, 72 + ps);
/*  93: 86 */     if (tc.paintColor > 0)
/*  94:    */     {
/*  95: 87 */       int pc = this.paintColors[(tc.paintColor - 1)];
/*  96: 88 */       this.context.setTint((pc >> 16) / 255.0F, (pc >> 8 & 0xFF) / 255.0F, (pc & 0xFF) / 255.0F);
/*  97:    */       
/*  98: 90 */       renderBlockPaint(cons, 66);
/*  99:    */     }
/* 100: 92 */     RenderLib.unbindTexture();
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void renderInvBlock(bbb renderblocks, int md)
/* 104:    */   {
/* 105: 96 */     this.block.f();
/* 106:    */     
/* 107: 98 */     this.context.setDefaults();
/* 108: 99 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 109:100 */     this.context.useNormal = true;
/* 110:101 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 111:    */     
/* 112:103 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 113:104 */     baz tessellator = baz.a;
/* 114:105 */     tessellator.b();
/* 115:    */     
/* 116:107 */     this.context.setTex(2, 2, 1, 1, 1, 1);
/* 117:108 */     doubleBox(63, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.01F);
/* 118:    */     
/* 119:110 */     this.context.setTex(72, 72, 68, 68, 68, 68);
/* 120:111 */     this.context.renderBox(63, 0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 121:112 */     this.context.renderBox(63, 0.7400000095367432D, 0.9900000095367432D, 0.7400000095367432D, 0.2599999904632568D, 0.009999999776482582D, 0.2599999904632568D);
/* 122:    */     
/* 123:114 */     tessellator.a();
/* 124:115 */     RenderLib.unbindTexture();
/* 125:116 */     this.context.useNormal = false;
/* 126:    */   }
/* 127:    */   
/* 128:    */   void doubleBox(int sides, float x1, float y1, float z1, float x2, float y2, float z2, float ino)
/* 129:    */   {
/* 130:122 */     int s2 = sides << 1 & 0x2A | sides >> 1 & 0x15;
/* 131:    */     
/* 132:124 */     this.context.renderBox(sides, x1, y1, z1, x2, y2, z2);
/* 133:125 */     this.context.renderBox(s2, x2 - ino, y2 - ino, z2 - ino, x1 + ino, y1 + ino, z1 + ino);
/* 134:    */   }
/* 135:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderFrameRedstoneTube
 * JD-Core Version:    0.7.0.1
 */