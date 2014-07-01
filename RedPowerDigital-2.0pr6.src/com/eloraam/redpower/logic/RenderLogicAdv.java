/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import com.eloraam.redpower.core.RenderContext;
/*   6:    */ import com.eloraam.redpower.core.RenderModel;
/*   7:    */ import ym;
/*   8:    */ 
/*   9:    */ public class RenderLogicAdv
/*  10:    */   extends RenderLogic
/*  11:    */ {
/*  12:    */   RenderModel modelXcvr;
/*  13:    */   
/*  14:    */   public RenderLogicAdv(amq bl)
/*  15:    */   {
/*  16: 14 */     super(bl);
/*  17: 15 */     this.modelXcvr = RenderModel.loadModel("/eloraam/logic/busxcvr.obj");
/*  18:    */   }
/*  19:    */   
/*  20:    */   protected int getTorchState(TileLogic tl)
/*  21:    */   {
/*  22: 22 */     int md = tl.getExtendedMetadata();
/*  23:    */     
/*  24:    */ 
/*  25:    */ 
/*  26: 26 */     return 0;
/*  27:    */   }
/*  28:    */   
/*  29:    */   protected int getInvTorchState(int md)
/*  30:    */   {
/*  31: 32 */     return 0;
/*  32:    */   }
/*  33:    */   
/*  34:    */   protected RenderLogic.TorchPos[] getTorchVectors(TileLogic tl)
/*  35:    */   {
/*  36: 36 */     int md = tl.getExtendedMetadata();
/*  37:    */     
/*  38:    */ 
/*  39:    */ 
/*  40: 40 */     return null;
/*  41:    */   }
/*  42:    */   
/*  43:    */   protected RenderLogic.TorchPos[] getInvTorchVectors(int md)
/*  44:    */   {
/*  45: 46 */     return null;
/*  46:    */   }
/*  47:    */   
/*  48:    */   protected void renderWorldPart(ym iba, TileLogic tl)
/*  49:    */   {
/*  50: 50 */     int md = tl.getExtendedMetadata();
/*  51: 51 */     TileLogicAdv tls = (TileLogicAdv)tl;
/*  52: 53 */     switch (md)
/*  53:    */     {
/*  54:    */     case 0: 
/*  55: 55 */       TileLogicAdv.LogicAdvXcvr lsc = (TileLogicAdv.LogicAdvXcvr)tls.getLogicStorage(TileLogicAdv.LogicAdvXcvr.class);
/*  56:    */       
/*  57:    */ 
/*  58:    */ 
/*  59: 59 */       this.context.bindTexture("/eloraam/logic/array1.png");
/*  60: 60 */       this.context.bindModelOffset(this.modelXcvr, 0.5D, 0.5D, 0.5D);
/*  61: 61 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/*  62:    */       
/*  63: 63 */       boolean b = (3552867 >> tl.Rotation & 0x1) == 0;
/*  64: 64 */       this.context.renderModelGroup(1, 1 + (b ? 1 : 0) + (tl.Deadmap == 0 ? 2 : 0));
/*  65:    */       
/*  66: 66 */       this.context.renderModelGroup(2, 1 + ((tl.PowerState & 0x1) > 0 ? 1 : 0) + ((tl.PowerState & 0x4) > 0 ? 2 : 0));
/*  67: 69 */       for (int i = 0; i < 4; i++) {
/*  68: 70 */         if (tl.Deadmap == 0)
/*  69:    */         {
/*  70: 71 */           this.context.renderModelGroup(3 + i, 1 + (lsc.State2 >> 4 * i & 0xF));
/*  71:    */           
/*  72: 73 */           this.context.renderModelGroup(7 + i, 1 + (lsc.State1 >> 4 * i & 0xF));
/*  73:    */         }
/*  74:    */         else
/*  75:    */         {
/*  76: 76 */           this.context.renderModelGroup(3 + i, 1 + (lsc.State1 >> 4 * i & 0xF));
/*  77:    */           
/*  78: 78 */           this.context.renderModelGroup(7 + i, 1 + (lsc.State2 >> 4 * i & 0xF));
/*  79:    */         }
/*  80:    */       }
/*  81: 82 */       this.context.unbindTexture();
/*  82: 83 */       break;
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   protected void renderInvPart(int md)
/*  87:    */   {
/*  88: 92 */     switch (md)
/*  89:    */     {
/*  90:    */     case 1024: 
/*  91: 94 */       this.context.bindTexture("/eloraam/logic/array1.png");
/*  92: 95 */       baz tessellator = baz.a;
/*  93: 96 */       tessellator.b();
/*  94: 97 */       this.context.useNormal = true;
/*  95:    */       
/*  96: 99 */       this.context.bindModelOffset(this.modelXcvr, 0.5D, 0.5D, 0.5D);
/*  97:100 */       this.context.setTint(1.0F, 1.0F, 1.0F);
/*  98:101 */       this.context.renderModelGroup(1, 1);
/*  99:102 */       this.context.renderModelGroup(2, 1);
/* 100:103 */       for (int i = 0; i < 8; i++) {
/* 101:104 */         this.context.renderModelGroup(3 + i, 1);
/* 102:    */       }
/* 103:106 */       this.context.useNormal = false;
/* 104:107 */       tessellator.a();
/* 105:108 */       this.context.unbindTexture();
/* 106:    */     }
/* 107:    */   }
/* 108:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.RenderLogicAdv
 * JD-Core Version:    0.7.0.1
 */