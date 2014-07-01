/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import com.eloraam.redpower.core.CoreLib;
/*   6:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   7:    */ import com.eloraam.redpower.core.RenderContext;
/*   8:    */ import com.eloraam.redpower.core.RenderModel;
/*   9:    */ import com.eloraam.redpower.core.WorldCoord;
/*  10:    */ import ym;
/*  11:    */ 
/*  12:    */ public class RenderLogicArray
/*  13:    */   extends RenderLogic
/*  14:    */ {
/*  15:    */   RenderModel model;
/*  16:    */   
/*  17:    */   public RenderLogicArray(amq bl)
/*  18:    */   {
/*  19: 14 */     super(bl);
/*  20: 15 */     this.model = RenderModel.loadModel("/eloraam/logic/arraycells.obj");
/*  21:    */   }
/*  22:    */   
/*  23: 19 */   private static RenderLogic.TorchPos[] torchMapInvert = { new RenderLogic.TorchPos(0.0D, -0.25D, 0.0D, 0.7D) };
/*  24: 22 */   private static RenderLogic.TorchPos[] torchMapNonInv = { new RenderLogic.TorchPos(0.0D, -0.25D, 0.0D, 0.7D), new RenderLogic.TorchPos(-0.188D, -0.25D, 0.219D, 0.7D) };
/*  25:    */   
/*  26:    */   protected int getTorchState(TileLogic tl)
/*  27:    */   {
/*  28: 30 */     int md = tl.getExtendedMetadata();
/*  29: 31 */     switch (md)
/*  30:    */     {
/*  31:    */     case 1: 
/*  32: 33 */       return tl.Powered ? 1 : 0;
/*  33:    */     case 2: 
/*  34: 35 */       return tl.Powered ? 1 : 2;
/*  35:    */     }
/*  36: 37 */     return 0;
/*  37:    */   }
/*  38:    */   
/*  39:    */   protected int getInvTorchState(int md)
/*  40:    */   {
/*  41: 41 */     if (md == 514) {
/*  42: 41 */       return 2;
/*  43:    */     }
/*  44: 42 */     return 0;
/*  45:    */   }
/*  46:    */   
/*  47:    */   protected RenderLogic.TorchPos[] getTorchVectors(TileLogic tl)
/*  48:    */   {
/*  49: 46 */     int md = tl.getExtendedMetadata();
/*  50: 47 */     switch (md)
/*  51:    */     {
/*  52:    */     case 1: 
/*  53: 49 */       return torchMapInvert;
/*  54:    */     case 2: 
/*  55: 51 */       return torchMapNonInv;
/*  56:    */     }
/*  57: 53 */     return null;
/*  58:    */   }
/*  59:    */   
/*  60:    */   protected RenderLogic.TorchPos[] getInvTorchVectors(int md)
/*  61:    */   {
/*  62: 57 */     switch (md)
/*  63:    */     {
/*  64:    */     case 513: 
/*  65: 59 */       return torchMapInvert;
/*  66:    */     case 514: 
/*  67: 61 */       return torchMapNonInv;
/*  68:    */     }
/*  69: 63 */     return null;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public static int getFacingDir(int rot, int rel)
/*  73:    */   {
/*  74:    */     int n;
/*  75: 70 */     switch (rot >> 2)
/*  76:    */     {
/*  77:    */     case 0: 
/*  78: 71 */       n = 13604; break;
/*  79:    */     case 1: 
/*  80: 72 */       n = 13349; break;
/*  81:    */     case 2: 
/*  82: 73 */       n = 20800; break;
/*  83:    */     case 3: 
/*  84: 74 */       n = 16720; break;
/*  85:    */     case 4: 
/*  86: 75 */       n = 8496; break;
/*  87:    */     default: 
/*  88: 76 */       n = 12576;
/*  89:    */     }
/*  90: 78 */     n >>= (rot + rel & 0x3) << 2;
/*  91: 79 */     n &= 0x7;
/*  92: 80 */     return n;
/*  93:    */   }
/*  94:    */   
/*  95:    */   private boolean isArrayTopwire(ym iba, WorldCoord wc, int mask, int dir)
/*  96:    */   {
/*  97: 85 */     wc = wc.coordStep(dir);
/*  98:    */     
/*  99: 87 */     TileLogicArray tla = (TileLogicArray)CoreLib.getTileEntity(iba, wc, TileLogicArray.class);
/* 100: 89 */     if (tla == null) {
/* 101: 89 */       return false;
/* 102:    */     }
/* 103: 91 */     int m = tla.getTopwireMask();
/* 104: 92 */     m &= RedPowerLib.getConDirMask(dir);
/* 105: 93 */     m = (m & 0x55555555) << 1 | (m & 0x2AAAAAAA) >> 1;
/* 106: 94 */     m &= mask;
/* 107: 95 */     return m > 0;
/* 108:    */   }
/* 109:    */   
/* 110:    */   protected void renderWorldPart(ym iba, TileLogic tl)
/* 111:    */   {
/* 112: 99 */     if (!(tl instanceof TileLogicArray)) {
/* 113: 99 */       return;
/* 114:    */     }
/* 115:100 */     TileLogicArray tla = (TileLogicArray)tl;
/* 116:101 */     int md = tl.getExtendedMetadata();
/* 117:    */     
/* 118:103 */     this.context.bindTexture("/eloraam/logic/array1.png");
/* 119:104 */     this.context.bindModelOffset(this.model, 0.5D, 0.5D, 0.5D);
/* 120:105 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/* 121:106 */     this.context.renderModelGroup(0, 0);
/* 122:108 */     switch (md)
/* 123:    */     {
/* 124:    */     case 0: 
/* 125:110 */       this.context.renderModelGroup(1, 1);
/* 126:111 */       this.context.setTint(0.3F + 0.7F * (tla.PowerVal1 / 255.0F), 0.0F, 0.0F);
/* 127:112 */       this.context.renderModelGroup(2, 1);
/* 128:113 */       this.context.setTint(0.3F + 0.7F * (tla.PowerVal2 / 255.0F), 0.0F, 0.0F);
/* 129:114 */       this.context.renderModelGroup(3, 1);
/* 130:115 */       break;
/* 131:    */     case 1: 
/* 132:117 */       this.context.renderModelGroup(1, 2 + (tla.PowerVal1 > 0 ? 1 : 0));
/* 133:118 */       this.context.renderModelGroup(5, 0);
/* 134:119 */       this.context.setTint(0.3F + 0.7F * (tla.PowerVal1 / 255.0F), 0.0F, 0.0F);
/* 135:120 */       this.context.renderModelGroup(2, 2);
/* 136:121 */       this.context.setTint(0.3F + 0.7F * (tla.PowerVal2 / 255.0F), 0.0F, 0.0F);
/* 137:122 */       this.context.renderModelGroup(3, 2);
/* 138:123 */       break;
/* 139:    */     case 2: 
/* 140:125 */       this.context.renderModelGroup(1, 4 + (tla.PowerVal1 > 0 ? 1 : 0) + (tla.Powered ? 0 : 2));
/* 141:    */       
/* 142:127 */       this.context.renderModelGroup(5, 0);
/* 143:128 */       this.context.setTint(0.3F + 0.7F * (tla.PowerVal1 / 255.0F), 0.0F, 0.0F);
/* 144:129 */       this.context.renderModelGroup(2, 2);
/* 145:130 */       this.context.setTint(0.3F + 0.7F * (tla.PowerVal2 / 255.0F), 0.0F, 0.0F);
/* 146:131 */       this.context.renderModelGroup(3, 2);
/* 147:    */     }
/* 148:135 */     int fd = getFacingDir(tla.Rotation, 1);
/* 149:136 */     int fm = tla.getTopwireMask();
/* 150:    */     
/* 151:138 */     WorldCoord wc = new WorldCoord(tl);
/* 152:139 */     this.context.renderModelGroup(4, (isArrayTopwire(iba, wc, fm, fd) ? 0 : 1) + (isArrayTopwire(iba, wc, fm, fd ^ 0x1) ? 0 : 2));
/* 153:    */     
/* 154:    */ 
/* 155:    */ 
/* 156:143 */     this.context.unbindTexture();
/* 157:    */   }
/* 158:    */   
/* 159:    */   protected void renderInvPart(int md)
/* 160:    */   {
/* 161:147 */     this.context.bindTexture("/eloraam/logic/array1.png");
/* 162:148 */     baz tessellator = baz.a;
/* 163:149 */     tessellator.b();
/* 164:150 */     this.context.useNormal = true;
/* 165:    */     
/* 166:152 */     this.context.bindModelOffset(this.model, 0.5D, 0.5D, 0.5D);
/* 167:153 */     this.context.setTint(1.0F, 1.0F, 1.0F);
/* 168:154 */     this.context.renderModelGroup(0, 0);
/* 169:155 */     switch (md)
/* 170:    */     {
/* 171:    */     case 512: 
/* 172:157 */       this.context.renderModelGroup(1, 1);
/* 173:158 */       this.context.setTint(0.3F, 0.0F, 0.0F);
/* 174:159 */       this.context.renderModelGroup(2, 1);
/* 175:160 */       this.context.renderModelGroup(3, 1);
/* 176:161 */       this.context.renderModelGroup(4, 3);
/* 177:162 */       break;
/* 178:    */     case 513: 
/* 179:164 */       this.context.renderModelGroup(1, 2);
/* 180:165 */       this.context.renderModelGroup(5, 0);
/* 181:166 */       this.context.setTint(0.3F, 0.0F, 0.0F);
/* 182:167 */       this.context.renderModelGroup(2, 2);
/* 183:168 */       this.context.renderModelGroup(3, 2);
/* 184:169 */       this.context.renderModelGroup(4, 3);
/* 185:170 */       break;
/* 186:    */     case 514: 
/* 187:172 */       this.context.renderModelGroup(1, 6);
/* 188:173 */       this.context.renderModelGroup(5, 0);
/* 189:174 */       this.context.setTint(0.3F, 0.0F, 0.0F);
/* 190:175 */       this.context.renderModelGroup(2, 2);
/* 191:176 */       this.context.renderModelGroup(3, 2);
/* 192:177 */       this.context.renderModelGroup(4, 3);
/* 193:    */     }
/* 194:180 */     this.context.useNormal = false;
/* 195:181 */     tessellator.a();
/* 196:182 */     this.context.unbindTexture();
/* 197:    */   }
/* 198:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.RenderLogicArray
 * JD-Core Version:    0.7.0.1
 */