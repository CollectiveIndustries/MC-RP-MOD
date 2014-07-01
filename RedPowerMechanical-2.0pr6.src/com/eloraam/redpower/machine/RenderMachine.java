/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.RenderContext;
/*   8:    */ import com.eloraam.redpower.core.RenderCustomBlock;
/*   9:    */ import com.eloraam.redpower.core.RenderLib;
/*  10:    */ import java.util.Random;
/*  11:    */ import yc;
/*  12:    */ import ym;
/*  13:    */ 
/*  14:    */ public class RenderMachine
/*  15:    */   extends RenderCustomBlock
/*  16:    */ {
/*  17:    */   protected RenderContext context;
/*  18:    */   
/*  19:    */   public RenderMachine(amq bl)
/*  20:    */   {
/*  21: 16 */     super(bl);
/*  22: 17 */     this.context = new RenderContext();
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  26:    */   
/*  27:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  28:    */   {
/*  29: 28 */     TileMachine tb = (TileMachine)CoreLib.getTileEntity(iba, i, j, k, TileMachine.class);
/*  30: 30 */     if (tb == null) {
/*  31: 30 */       return;
/*  32:    */     }
/*  33: 32 */     this.context.setDefaults();
/*  34: 33 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  35:    */     
/*  36: 35 */     this.context.setPos(i, j, k);
/*  37: 36 */     this.context.readGlobalLights(iba, i, j, k);
/*  38: 37 */     if (md == 0)
/*  39:    */     {
/*  40: 38 */       int act = tb.Active ? 1 : 0;
/*  41: 39 */       this.context.setTex(48, 53 + act, 56, 56, 55, 55);
/*  42:    */     }
/*  43: 40 */     else if (md == 4)
/*  44:    */     {
/*  45: 41 */       int t1 = 96 + (tb.Active ? 1 : 0);
/*  46: 42 */       int t2 = t1 + 2 + (tb.Powered ? 2 : 0);
/*  47: 43 */       this.context.setTex(102, 103, t1, t1, t2, t2);
/*  48:    */     }
/*  49: 44 */     else if (md == 5)
/*  50:    */     {
/*  51: 45 */       int act = tb.Charged ? 1 : tb.Active ? 2 : 0;
/*  52: 46 */       int t1 = 116 + (tb.Charged ? 1 : 0) + (tb.Active ? 2 : 0);
/*  53: 47 */       this.context.setTex(113 + act, 112, t1, t1, t1, t1);
/*  54:    */     }
/*  55: 48 */     else if (md == 8)
/*  56:    */     {
/*  57: 49 */       int tex = 120 + (tb.Charged ? 1 : 0) + ((tb.Delay | tb.Active) ? 2 : 0);
/*  58: 50 */       this.context.setTex(124, 125, tex, tex, tex, tex);
/*  59:    */     }
/*  60: 51 */     else if (md == 10)
/*  61:    */     {
/*  62: 52 */       int t1 = 104 + (tb.Active ? 1 : 0);
/*  63: 53 */       int t2 = t1 + 2 + (tb.Powered ? 2 : 0);
/*  64: 54 */       this.context.setTex(102, 103, t1, t1, t2, t2);
/*  65:    */     }
/*  66: 55 */     else if (md == 12)
/*  67:    */     {
/*  68: 56 */       int act = tb.Active ? 1 : 0;
/*  69: 57 */       this.context.setTex(48, 164 + act, 167, 167, 166, 166);
/*  70:    */     }
/*  71: 58 */     else if (md == 13)
/*  72:    */     {
/*  73: 59 */       int act = tb.Active ? 1 : 0;
/*  74: 60 */       this.context.setTex(172 + act, 168 + act, 171, 171, 170, 170);
/*  75:    */     }
/*  76: 61 */     else if (md == 14)
/*  77:    */     {
/*  78: 62 */       int act = tb.Active ? 1 : 0;
/*  79: 63 */       this.context.setTex(58, 89, 91 + act, 91 + act, 90, 90);
/*  80:    */     }
/*  81: 64 */     else if (md == 15)
/*  82:    */     {
/*  83: 65 */       int act = tb.Active ? 1 : 0;
/*  84: 66 */       this.context.setTex(58, 89, 93 + act, 93 + act, 90, 90);
/*  85:    */     }
/*  86:    */     else
/*  87:    */     {
/*  88: 68 */       int tex = 59 + (tb.Active ? 1 : 0) + (md == 3 ? 2 : 0);
/*  89: 69 */       this.context.setTex(58, 57, tex, tex, tex, tex);
/*  90:    */     }
/*  91: 71 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  92: 72 */     this.context.setupBox();
/*  93: 73 */     this.context.transform();
/*  94: 74 */     this.context.orientTextures(tb.Rotation);
/*  95:    */     
/*  96: 76 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  97: 77 */     this.context.renderGlobFaces(63);
/*  98: 78 */     RenderLib.unbindTexture();
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void renderInvBlock(bbb renderblocks, int md)
/* 102:    */   {
/* 103: 82 */     this.block.f();
/* 104:    */     
/* 105: 84 */     this.context.setDefaults();
/* 106: 85 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 107: 86 */     this.context.useNormal = true;
/* 108:    */     
/* 109: 88 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 110: 89 */     baz tessellator = baz.a;
/* 111: 90 */     tessellator.b();
/* 112: 92 */     if (md == 0) {
/* 113: 93 */       this.context.setTex(48, 53, 56, 56, 55, 55);
/* 114: 94 */     } else if (md == 2) {
/* 115: 95 */       this.context.setTex(58, 57, 59, 59, 59, 59);
/* 116: 96 */     } else if (md == 4) {
/* 117: 97 */       this.context.setTex(102, 103, 98, 98, 96, 96);
/* 118: 98 */     } else if (md == 5) {
/* 119: 99 */       this.context.setTex(113, 112, 117, 117, 117, 117);
/* 120:100 */     } else if (md == 8) {
/* 121:101 */       this.context.setTex(124, 125, 120, 120, 120, 120);
/* 122:102 */     } else if (md == 10) {
/* 123:103 */       this.context.setTex(102, 103, 106, 106, 104, 104);
/* 124:104 */     } else if (md == 12) {
/* 125:105 */       this.context.setTex(48, 164, 167, 167, 166, 166);
/* 126:106 */     } else if (md == 13) {
/* 127:107 */       this.context.setTex(172, 168, 171, 171, 170, 170);
/* 128:108 */     } else if (md == 14) {
/* 129:109 */       this.context.setTex(58, 89, 91, 91, 90, 90);
/* 130:110 */     } else if (md == 15) {
/* 131:111 */       this.context.setTex(58, 89, 93, 93, 90, 90);
/* 132:    */     } else {
/* 133:113 */       this.context.setTex(58, 57, 61, 61, 61, 61);
/* 134:    */     }
/* 135:115 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 136:    */     
/* 137:117 */     tessellator.a();
/* 138:118 */     RenderLib.unbindTexture();
/* 139:    */     
/* 140:120 */     this.context.useNormal = false;
/* 141:    */   }
/* 142:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderMachine
 * JD-Core Version:    0.7.0.1
 */