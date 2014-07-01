/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.CoverRenderer;
/*   8:    */ import com.eloraam.redpower.core.RenderContext;
/*   9:    */ import com.eloraam.redpower.core.RenderCovers;
/*  10:    */ import com.eloraam.redpower.core.RenderLib;
/*  11:    */ import java.util.Random;
/*  12:    */ import yc;
/*  13:    */ import ym;
/*  14:    */ 
/*  15:    */ public class RenderFrame
/*  16:    */   extends RenderCovers
/*  17:    */ {
/*  18:    */   public RenderFrame(amq bl)
/*  19:    */   {
/*  20: 19 */     super(bl);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  24:    */   
/*  25:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  26:    */   {
/*  27: 29 */     int cons = 0;
/*  28: 30 */     TileFrame tc = (TileFrame)CoreLib.getTileEntity(iba, i, j, k, TileFrame.class);
/*  29: 32 */     if (tc == null) {
/*  30: 32 */       return;
/*  31:    */     }
/*  32: 34 */     this.context.setDefaults();
/*  33: 35 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/*  34: 36 */     this.context.setPos(i, j, k);
/*  35: 37 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  36: 38 */     this.context.readGlobalLights(iba, i, j, k);
/*  37: 40 */     if (tc.CoverSides > 0)
/*  38:    */     {
/*  39: 41 */       short[] sides = new short[6];
/*  40: 42 */       for (int n = 0; n < 6; n++)
/*  41:    */       {
/*  42: 43 */         sides[n] = tc.Covers[n];
/*  43: 44 */         int m = sides[n] >> 8;
/*  44: 45 */         if ((m == 1) || (m == 4))
/*  45:    */         {
/*  46: 45 */           int tmp152_150 = n; short[] tmp152_148 = sides;tmp152_148[tmp152_150] = ((short)(tmp152_148[tmp152_150] - 256));
/*  47:    */         }
/*  48:    */       }
/*  49: 47 */       this.coverRenderer.render(tc.CoverSides, sides);
/*  50:    */     }
/*  51: 53 */     this.context.exactTextureCoordinates = true;
/*  52: 54 */     RenderLib.bindTexture("/eloraam/machine/machine1.png", 1);
/*  53:    */     
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59:    */ 
/*  60:    */ 
/*  61: 63 */     this.context.setTex(2);
/*  62: 66 */     for (int n = 0; n < 6; n++)
/*  63:    */     {
/*  64: 67 */       int m = 1 << n;
/*  65:    */       
/*  66: 69 */       int tx = 1;
/*  67: 70 */       this.coverRenderer.start();
/*  68: 71 */       if ((tc.CoverSides & m) > 0)
/*  69:    */       {
/*  70: 72 */         if ((tc.StickySides & m) > 0) {
/*  71: 72 */           tx = 4;
/*  72:    */         } else {
/*  73: 73 */           tx = 2;
/*  74:    */         }
/*  75:    */       }
/*  76:    */       else
/*  77:    */       {
/*  78: 75 */         m |= 1 << (n ^ 0x1);
/*  79: 76 */         this.context.setTexNum(n ^ 0x1, 1);
/*  80:    */       }
/*  81: 78 */       this.context.setTexNum(n, tx);
/*  82: 79 */       this.coverRenderer.setSize(n, 0.0625F);
/*  83: 80 */       this.context.calcBoundsGlobal();
/*  84: 81 */       this.context.renderGlobFaces(m);
/*  85:    */     }
/*  86: 83 */     this.context.exactTextureCoordinates = false;
/*  87:    */     
/*  88: 85 */     RenderLib.unbindTexture();
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void renderInvBlock(bbb renderblocks, int md)
/*  92:    */   {
/*  93: 89 */     this.block.f();
/*  94:    */     
/*  95: 91 */     this.context.setDefaults();
/*  96: 92 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/*  97: 93 */     this.context.useNormal = true;
/*  98: 94 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  99:    */     
/* 100: 96 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 101: 97 */     baz tessellator = baz.a;
/* 102: 98 */     tessellator.b();
/* 103:    */     
/* 104:100 */     this.context.setTex(1);
/* 105:101 */     doubleBox(63, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.01F);
/* 106:    */     
/* 107:103 */     tessellator.a();
/* 108:104 */     RenderLib.unbindTexture();
/* 109:105 */     this.context.useNormal = false;
/* 110:    */   }
/* 111:    */   
/* 112:    */   void doubleBox(int sides, float x1, float y1, float z1, float x2, float y2, float z2, float ino)
/* 113:    */   {
/* 114:111 */     int s2 = sides << 1 & 0x2A | sides >> 1 & 0x15;
/* 115:    */     
/* 116:113 */     this.context.renderBox(sides, x1, y1, z1, x2, y2, z2);
/* 117:114 */     this.context.renderBox(s2, x2 - ino, y2 - ino, z2 - ino, x1 + ino, y1 + ino, z1 + ino);
/* 118:    */   }
/* 119:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderFrame
 * JD-Core Version:    0.7.0.1
 */