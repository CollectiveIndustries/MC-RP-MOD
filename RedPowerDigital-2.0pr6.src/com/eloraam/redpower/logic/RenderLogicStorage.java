/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import com.eloraam.redpower.core.MathLib;
/*   6:    */ import com.eloraam.redpower.core.Matrix3;
/*   7:    */ import com.eloraam.redpower.core.Quat;
/*   8:    */ import com.eloraam.redpower.core.RenderContext;
/*   9:    */ import com.eloraam.redpower.core.RenderLib;
/*  10:    */ import com.eloraam.redpower.core.Vector3;
/*  11:    */ import ym;
/*  12:    */ 
/*  13:    */ public class RenderLogicStorage
/*  14:    */   extends RenderLogic
/*  15:    */ {
/*  16:    */   public RenderLogicStorage(amq bl)
/*  17:    */   {
/*  18: 14 */     super(bl);
/*  19:    */   }
/*  20:    */   
/*  21: 17 */   private static RenderLogic.TorchPos[] torchMapCounter = { new RenderLogic.TorchPos(0.0D, 0.125D, 0.188D, 1.0D), new RenderLogic.TorchPos(0.3D, -0.3D, 0.0D, 0.6000000238418579D), new RenderLogic.TorchPos(-0.3D, -0.3D, 0.0D, 0.6000000238418579D) };
/*  22:    */   
/*  23:    */   protected int getTorchState(TileLogic tl)
/*  24:    */   {
/*  25: 26 */     TileLogicStorage tls = (TileLogicStorage)tl;
/*  26: 27 */     int md = tl.getExtendedMetadata();
/*  27: 29 */     switch (md)
/*  28:    */     {
/*  29:    */     case 0: 
/*  30: 31 */       TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)tls.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/*  31:    */       
/*  32:    */ 
/*  33: 34 */       return 0x1 | (lsc.Count == lsc.CountMax ? 2 : 0) | (lsc.Count == 0 ? 4 : 0);
/*  34:    */     }
/*  35: 38 */     return 0;
/*  36:    */   }
/*  37:    */   
/*  38:    */   protected int getInvTorchState(int md)
/*  39:    */   {
/*  40: 42 */     switch (md)
/*  41:    */     {
/*  42:    */     case 768: 
/*  43: 44 */       return 5;
/*  44:    */     }
/*  45: 46 */     return 0;
/*  46:    */   }
/*  47:    */   
/*  48:    */   protected RenderLogic.TorchPos[] getTorchVectors(TileLogic tl)
/*  49:    */   {
/*  50: 50 */     int md = tl.getExtendedMetadata();
/*  51: 52 */     switch (md)
/*  52:    */     {
/*  53:    */     case 0: 
/*  54: 54 */       return torchMapCounter;
/*  55:    */     }
/*  56: 56 */     return null;
/*  57:    */   }
/*  58:    */   
/*  59:    */   protected RenderLogic.TorchPos[] getInvTorchVectors(int md)
/*  60:    */   {
/*  61: 60 */     switch (md)
/*  62:    */     {
/*  63:    */     case 768: 
/*  64: 61 */       return torchMapCounter;
/*  65:    */     }
/*  66: 63 */     return null;
/*  67:    */   }
/*  68:    */   
/*  69:    */   protected void renderWorldPart(ym iba, TileLogic tl)
/*  70:    */   {
/*  71: 67 */     int md = tl.getExtendedMetadata();
/*  72: 68 */     TileLogicStorage tls = (TileLogicStorage)tl;
/*  73:    */     int tx;
/*  74: 71 */     switch (md)
/*  75:    */     {
/*  76:    */     case 0: 
/*  77: 73 */       tx = 'à' + (tl.Deadmap > 0 ? 4 : 0) + (tl.PowerState & 0x1) + ((tl.PowerState & 0x4) >> 1);
/*  78:    */       
/*  79: 75 */       break;
/*  80:    */     default: 
/*  81: 76 */       return;
/*  82:    */     }
/*  83: 78 */     renderWafer(tx);
/*  84: 80 */     if (md == 0)
/*  85:    */     {
/*  86: 81 */       TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)tls.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/*  87: 84 */       if (lsc.CountMax == 0) {
/*  88: 84 */         lsc.CountMax = 1;
/*  89:    */       }
/*  90: 85 */       float dir = 0.58F + 0.34F * (lsc.Count / lsc.CountMax);
/*  91:    */       
/*  92: 87 */       Vector3 pos = new Vector3(0.0D, -0.1D, 0.188D);
/*  93: 88 */       this.context.basis.rotate(pos);
/*  94: 89 */       pos.add(this.context.globalOrigin);
/*  95: 90 */       pos.add(0.5D, 0.5D, 0.5D);
/*  96:    */       
/*  97: 92 */       Quat q = Quat.aroundAxis(0.0D, 1.0D, 0.0D, -dir * 3.141592653589793D * 2.0D);
/*  98: 93 */       q.multiply(MathLib.orientQuat(tl.Rotation >> 2, tl.Rotation & 0x3));
/*  99: 94 */       RenderLib.renderPointer(pos, q);
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   protected void renderInvPart(int md)
/* 104:    */   {
/* 105:100 */     switch (md)
/* 106:    */     {
/* 107:    */     case 768: 
/* 108:102 */       renderInvWafer(224);
/* 109:    */     }
/* 110:106 */     if (md == 768)
/* 111:    */     {
/* 112:107 */       baz tessellator = baz.a;
/* 113:108 */       tessellator.b();
/* 114:109 */       tessellator.b(0.0F, 0.0F, 1.0F);
/* 115:110 */       Vector3 v = new Vector3(0.0D, -0.1D, 0.188D);
/* 116:111 */       Quat q = Quat.aroundAxis(0.0D, 1.0D, 0.0D, 3.64424747816416D);
/* 117:112 */       this.context.basis.rotate(v);
/* 118:113 */       q.multiply(MathLib.orientQuat(0, 1));
/* 119:    */       
/* 120:115 */       RenderLib.renderPointer(v, q);
/* 121:116 */       tessellator.a();
/* 122:    */     }
/* 123:    */   }
/* 124:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.RenderLogicStorage
 * JD-Core Version:    0.7.0.1
 */