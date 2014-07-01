/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import com.eloraam.redpower.core.Quat;
/*   6:    */ import com.eloraam.redpower.core.RenderContext;
/*   7:    */ import com.eloraam.redpower.core.RenderLib;
/*   8:    */ import com.eloraam.redpower.core.Vector3;
/*   9:    */ import ym;
/*  10:    */ 
/*  11:    */ public class RenderLogicPointer
/*  12:    */   extends RenderLogic
/*  13:    */ {
/*  14:    */   public RenderLogicPointer(amq bl)
/*  15:    */   {
/*  16: 14 */     super(bl);
/*  17:    */   }
/*  18:    */   
/*  19: 17 */   private static RenderLogic.TorchPos[] torchMapSequencer = { new RenderLogic.TorchPos(0.0D, 0.125D, 0.0D, 1.0D), new RenderLogic.TorchPos(0.0D, -0.3D, 0.3D, 0.6D), new RenderLogic.TorchPos(-0.3D, -0.3D, 0.0D, 0.6D), new RenderLogic.TorchPos(0.0D, -0.3D, -0.3D, 0.6D), new RenderLogic.TorchPos(0.3D, -0.3D, 0.0D, 0.6D) };
/*  20: 24 */   private static RenderLogic.TorchPos[] torchMapTimer = { new RenderLogic.TorchPos(0.0D, 0.125D, 0.0D, 1.0D), new RenderLogic.TorchPos(0.3D, -0.3D, 0.0D, 0.6D) };
/*  21: 28 */   private static RenderLogic.TorchPos[] torchMapStateCell = { new RenderLogic.TorchPos(0.0D, 0.125D, 0.25D, 1.0D), new RenderLogic.TorchPos(0.281D, -0.3D, 0.156D, 0.6D) };
/*  22: 33 */   private static RenderLogic.TorchPos[] torchMapStateCell2 = { new RenderLogic.TorchPos(0.0D, 0.125D, -0.25D, 1.0D), new RenderLogic.TorchPos(0.281D, -0.3D, -0.156D, 0.6D) };
/*  23:    */   
/*  24:    */   protected int getTorchState(TileLogic tl)
/*  25:    */   {
/*  26: 41 */     int md = tl.getExtendedMetadata();
/*  27: 42 */     switch (md)
/*  28:    */     {
/*  29:    */     case 0: 
/*  30: 44 */       return (tl.Disabled ? 0 : 1) | ((tl.Powered) && (!tl.Disabled) ? 2 : 0);
/*  31:    */     case 1: 
/*  32: 47 */       return 0x1 | 2 << tl.PowerState & 0x1F;
/*  33:    */     case 2: 
/*  34: 49 */       return ((tl.Active) && (!tl.Powered) && (!tl.Disabled) ? 1 : 0) | ((tl.Active) && (tl.Powered) ? 2 : 0);
/*  35:    */     }
/*  36: 52 */     return 0;
/*  37:    */   }
/*  38:    */   
/*  39:    */   protected int getInvTorchState(int md)
/*  40:    */   {
/*  41: 56 */     switch (md)
/*  42:    */     {
/*  43:    */     case 0: 
/*  44: 58 */       return 1;
/*  45:    */     case 1: 
/*  46: 60 */       return 5;
/*  47:    */     case 2: 
/*  48: 62 */       return 0;
/*  49:    */     }
/*  50: 64 */     return 0;
/*  51:    */   }
/*  52:    */   
/*  53:    */   protected RenderLogic.TorchPos[] getTorchVectors(TileLogic tl)
/*  54:    */   {
/*  55: 68 */     int md = tl.getExtendedMetadata();
/*  56: 70 */     switch (md)
/*  57:    */     {
/*  58:    */     case 0: 
/*  59: 71 */       return torchMapTimer;
/*  60:    */     case 1: 
/*  61: 72 */       return torchMapSequencer;
/*  62:    */     case 2: 
/*  63: 74 */       if (tl.Deadmap > 0) {
/*  64: 75 */         return torchMapStateCell2;
/*  65:    */       }
/*  66: 76 */       return torchMapStateCell;
/*  67:    */     }
/*  68: 78 */     return null;
/*  69:    */   }
/*  70:    */   
/*  71:    */   protected RenderLogic.TorchPos[] getInvTorchVectors(int md)
/*  72:    */   {
/*  73: 82 */     switch (md)
/*  74:    */     {
/*  75:    */     case 0: 
/*  76: 83 */       return torchMapTimer;
/*  77:    */     case 1: 
/*  78: 84 */       return torchMapSequencer;
/*  79:    */     case 2: 
/*  80: 85 */       return torchMapStateCell;
/*  81:    */     }
/*  82: 87 */     return null;
/*  83:    */   }
/*  84:    */   
/*  85:    */   protected void renderWorldPart(ym iba, TileLogic tl)
/*  86:    */   {
/*  87: 91 */     int md = tl.getExtendedMetadata();
/*  88:    */     int tx;
/*  89:    */     int tx;
/*  90: 94 */     switch (md)
/*  91:    */     {
/*  92:    */     case 0: 
/*  93: 96 */       tx = 16 + (tl.PowerState | (tl.Powered ? 5 : 0));
/*  94: 97 */       break;
/*  95:    */     case 1: 
/*  96: 99 */       if (tl.Deadmap == 1) {
/*  97: 99 */         tx = 4;
/*  98:    */       } else {
/*  99:100 */         tx = 3;
/* 100:    */       }
/* 101:101 */       break;
/* 102:    */     case 2: 
/* 103:103 */       tx = 32 + ((tl.Deadmap > 0 ? 32 : 0) | tl.PowerState | ((tl.Active) && (tl.Powered) ? 8 : 0) | ((tl.Active) && (!tl.Powered) && (!tl.Disabled) ? 0 : 16) | ((tl.Active) && (!tl.Powered) ? 4 : tl.Deadmap > 0 ? 1 : 0));
/* 104:    */       
/* 105:    */ 
/* 106:    */ 
/* 107:107 */       break;
/* 108:    */     default: 
/* 109:108 */       return;
/* 110:    */     }
/* 111:110 */     renderWafer(tx);
/* 112:112 */     if (md == 2) {
/* 113:113 */       if (tl.Deadmap > 0) {
/* 114:114 */         renderChip(-0.125D, 0.0D, 0.125D, tl.Active ? 2 : 1);
/* 115:    */       } else {
/* 116:116 */         renderChip(-0.125D, 0.0D, -0.125D, tl.Active ? 2 : 1);
/* 117:    */       }
/* 118:    */     }
/* 119:    */   }
/* 120:    */   
/* 121:    */   protected void renderInvPart(int md)
/* 122:    */   {
/* 123:123 */     switch (md)
/* 124:    */     {
/* 125:    */     case 0: 
/* 126:125 */       this.context.setOrientation(0, 1);
/* 127:126 */       renderInvWafer(16);
/* 128:127 */       break;
/* 129:    */     case 1: 
/* 130:129 */       renderInvWafer(3);
/* 131:130 */       break;
/* 132:    */     case 2: 
/* 133:132 */       this.context.setOrientation(0, 1);
/* 134:133 */       renderInvWafer(48);
/* 135:    */     }
/* 136:139 */     baz tessellator = baz.a;
/* 137:140 */     tessellator.b();
/* 138:141 */     tessellator.b(0.0F, 0.0F, 1.0F);
/* 139:142 */     if (md == 2)
/* 140:    */     {
/* 141:143 */       RenderLib.renderPointer(new Vector3(-0.25D, -0.1D, 0.0D), Quat.aroundAxis(0.0D, 1.0D, 0.0D, 0.0D));
/* 142:    */       
/* 143:    */ 
/* 144:146 */       this.context.useNormal = true;
/* 145:147 */       renderChip(-0.125D, 0.0D, -0.125D, 1);
/* 146:148 */       this.context.useNormal = false;
/* 147:    */     }
/* 148:    */     else
/* 149:    */     {
/* 150:150 */       RenderLib.renderPointer(new Vector3(0.0D, -0.1D, 0.0D), Quat.aroundAxis(0.0D, 1.0D, 0.0D, -1.570796326794897D));
/* 151:    */     }
/* 152:153 */     tessellator.a();
/* 153:    */   }
/* 154:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.RenderLogicPointer
 * JD-Core Version:    0.7.0.1
 */