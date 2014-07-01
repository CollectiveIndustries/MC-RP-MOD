/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import bbb;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.PipeLib;
/*   8:    */ import com.eloraam.redpower.core.RenderContext;
/*   9:    */ import com.eloraam.redpower.core.RenderCovers;
/*  10:    */ import com.eloraam.redpower.core.RenderLib;
/*  11:    */ import java.util.Random;
/*  12:    */ import yc;
/*  13:    */ import ym;
/*  14:    */ 
/*  15:    */ public class RenderPipe
/*  16:    */   extends RenderCovers
/*  17:    */ {
/*  18:    */   public RenderPipe(amq bl)
/*  19:    */   {
/*  20: 16 */     super(bl);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/*  24:    */   
/*  25:    */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/*  26:    */   {
/*  27: 26 */     int cons = 0;
/*  28: 27 */     TilePipe tt = (TilePipe)CoreLib.getTileEntity(iba, i, j, k, TilePipe.class);
/*  29: 29 */     if (tt == null) {
/*  30: 29 */       return;
/*  31:    */     }
/*  32: 31 */     this.context.exactTextureCoordinates = true;
/*  33: 32 */     this.context.setTexFlags(55);
/*  34: 33 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/*  35: 34 */     this.context.setPos(i, j, k);
/*  36: 35 */     if (tt.CoverSides > 0)
/*  37:    */     {
/*  38: 36 */       this.context.readGlobalLights(iba, i, j, k);
/*  39: 37 */       renderCovers(tt.CoverSides, tt.Covers);
/*  40:    */     }
/*  41: 40 */     cons = PipeLib.getConnections(iba, i, j, k);
/*  42:    */     
/*  43:    */ 
/*  44:    */ 
/*  45: 44 */     this.context.setBrightness(this.block.e(iba, i, j, k));
/*  46:    */     
/*  47: 46 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  48: 47 */     this.context.setPos(i, j, k);
/*  49:    */     
/*  50: 49 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  51:    */     
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58:    */ 
/*  59:    */ 
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64: 63 */     renderCenterBlock(cons, 26, 28);
/*  65:    */     
/*  66: 65 */     tt.cacheFlange();
/*  67: 66 */     renderFlanges(tt.Flanges, 27);
/*  68:    */     
/*  69: 68 */     RenderLib.unbindTexture();
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void renderInvBlock(bbb renderblocks, int md)
/*  73:    */   {
/*  74: 72 */     this.block.f();
/*  75:    */     
/*  76: 74 */     this.context.setDefaults();
/*  77: 75 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/*  78: 76 */     this.context.useNormal = true;
/*  79: 77 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/*  80:    */     
/*  81: 79 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/*  82: 80 */     baz tessellator = baz.a;
/*  83:    */     
/*  84: 82 */     tessellator.b();
/*  85: 83 */     this.context.useNormal = true;
/*  86:    */     
/*  87:    */ 
/*  88: 86 */     this.context.setTex(28, 28, 26, 26, 26, 26);
/*  89:    */     
/*  90: 88 */     this.context.renderBox(60, 0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
/*  91: 89 */     this.context.renderBox(60, 0.6240000128746033D, 0.9990000128746033D, 0.6240000128746033D, 0.3759999871253967D, 0.001000000047497451D, 0.3759999871253967D);
/*  92: 90 */     renderFlanges(3, 27);
/*  93:    */     
/*  94: 92 */     tessellator.a();
/*  95: 93 */     RenderLib.unbindTexture();
/*  96: 94 */     this.context.useNormal = false;
/*  97:    */   }
/*  98:    */   
/*  99:    */   void doubleBox(int sides, float x1, float y1, float z1, float x2, float y2, float z2)
/* 100:    */   {
/* 101: 99 */     int s2 = sides << 1 & 0x2A | sides >> 1 & 0x15;
/* 102:    */     
/* 103:101 */     this.context.renderBox(sides, x1, y1, z1, x2, y2, z2);
/* 104:102 */     this.context.renderBox(s2, x2, y2, z2, x1, y1, z1);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void renderFlanges(int cons, int tex)
/* 108:    */   {
/* 109:106 */     this.context.setTex(tex);
/* 110:108 */     if ((cons & 0x1) > 0)
/* 111:    */     {
/* 112:109 */       this.context.setTexFlags(0);
/* 113:110 */       this.context.renderBox(63, 0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
/* 114:    */     }
/* 115:112 */     if ((cons & 0x2) > 0)
/* 116:    */     {
/* 117:113 */       this.context.setTexFlags(112320);
/* 118:114 */       this.context.renderBox(63, 0.25D, 0.875D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 119:    */     }
/* 120:116 */     if ((cons & 0x4) > 0)
/* 121:    */     {
/* 122:117 */       this.context.setTexFlags(217134);
/* 123:118 */       this.context.renderBox(63, 0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.125D);
/* 124:    */     }
/* 125:120 */     if ((cons & 0x8) > 0)
/* 126:    */     {
/* 127:121 */       this.context.setTexFlags(188469);
/* 128:122 */       this.context.renderBox(63, 0.25D, 0.25D, 0.875D, 0.75D, 0.75D, 1.0D);
/* 129:    */     }
/* 130:124 */     if ((cons & 0x10) > 0)
/* 131:    */     {
/* 132:125 */       this.context.setTexFlags(2944);
/* 133:126 */       this.context.renderBox(63, 0.0D, 0.25D, 0.25D, 0.125D, 0.75D, 0.75D);
/* 134:    */     }
/* 135:128 */     if ((cons & 0x20) > 0)
/* 136:    */     {
/* 137:129 */       this.context.setTexFlags(3419);
/* 138:130 */       this.context.renderBox(63, 0.875D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
/* 139:    */     }
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void renderCenterBlock(int cons, int side, int end)
/* 143:    */   {
/* 144:135 */     if (cons == 0)
/* 145:    */     {
/* 146:136 */       this.context.setTex(end);
/* 147:137 */       doubleBox(63, 0.375F, 0.375F, 0.375F, 0.625F, 0.625F, 0.625F);
/* 148:138 */       return;
/* 149:    */     }
/* 150:139 */     if (cons == 3)
/* 151:    */     {
/* 152:140 */       this.context.setTexFlags(1773);
/* 153:141 */       this.context.setTex(end, end, side, side, side, side);
/* 154:142 */       doubleBox(60, 0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
/* 155:143 */       return;
/* 156:    */     }
/* 157:144 */     if (cons == 12)
/* 158:    */     {
/* 159:145 */       this.context.setTexFlags(184365);
/* 160:146 */       this.context.setTex(side, side, end, end, side, side);
/* 161:147 */       doubleBox(51, 0.375F, 0.375F, 0.0F, 0.625F, 0.625F, 1.0F);
/* 162:148 */       return;
/* 163:    */     }
/* 164:149 */     if (cons == 48)
/* 165:    */     {
/* 166:150 */       this.context.setTexFlags(187200);
/* 167:151 */       this.context.setTex(side, side, side, side, end, end);
/* 168:152 */       doubleBox(15, 0.0F, 0.375F, 0.375F, 1.0F, 0.625F, 0.625F);
/* 169:153 */       return;
/* 170:    */     }
/* 171:155 */     this.context.setTex(end);
/* 172:156 */     doubleBox(0x3F ^ cons, 0.375F, 0.375F, 0.375F, 0.625F, 0.625F, 0.625F);
/* 173:157 */     if ((cons & 0x1) > 0)
/* 174:    */     {
/* 175:158 */       this.context.setTexFlags(1773);
/* 176:159 */       this.context.setTex(end, end, side, side, side, side);
/* 177:160 */       doubleBox(60, 0.375F, 0.0F, 0.375F, 0.625F, 0.375F, 0.625F);
/* 178:    */     }
/* 179:162 */     if ((cons & 0x2) > 0)
/* 180:    */     {
/* 181:163 */       this.context.setTexFlags(1773);
/* 182:164 */       this.context.setTex(end, end, side, side, side, side);
/* 183:165 */       doubleBox(60, 0.375F, 0.625F, 0.375F, 0.625F, 1.0F, 0.625F);
/* 184:    */     }
/* 185:167 */     if ((cons & 0x4) > 0)
/* 186:    */     {
/* 187:168 */       this.context.setTexFlags(184365);
/* 188:169 */       this.context.setTex(side, side, end, end, side, side);
/* 189:170 */       doubleBox(51, 0.375F, 0.375F, 0.0F, 0.625F, 0.625F, 0.375F);
/* 190:    */     }
/* 191:172 */     if ((cons & 0x8) > 0)
/* 192:    */     {
/* 193:173 */       this.context.setTexFlags(184365);
/* 194:174 */       this.context.setTex(side, side, end, end, side, side);
/* 195:175 */       doubleBox(51, 0.375F, 0.375F, 0.625F, 0.625F, 0.625F, 1.0F);
/* 196:    */     }
/* 197:177 */     if ((cons & 0x10) > 0)
/* 198:    */     {
/* 199:178 */       this.context.setTexFlags(187200);
/* 200:179 */       this.context.setTex(side, side, side, side, end, end);
/* 201:180 */       doubleBox(15, 0.0F, 0.375F, 0.375F, 0.375F, 0.625F, 0.625F);
/* 202:    */     }
/* 203:182 */     if ((cons & 0x20) > 0)
/* 204:    */     {
/* 205:183 */       this.context.setTexFlags(187200);
/* 206:184 */       this.context.setTex(side, side, side, side, end, end);
/* 207:185 */       doubleBox(15, 0.625F, 0.375F, 0.375F, 1.0F, 0.625F, 0.625F);
/* 208:    */     }
/* 209:    */   }
/* 210:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderPipe
 * JD-Core Version:    0.7.0.1
 */