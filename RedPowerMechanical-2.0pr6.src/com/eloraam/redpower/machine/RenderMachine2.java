/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import baz;
/*  5:   */ import bbb;
/*  6:   */ import com.eloraam.redpower.core.CoreLib;
/*  7:   */ import com.eloraam.redpower.core.RenderContext;
/*  8:   */ import com.eloraam.redpower.core.RenderCustomBlock;
/*  9:   */ import com.eloraam.redpower.core.RenderLib;
/* 10:   */ import java.util.Random;
/* 11:   */ import yc;
/* 12:   */ import ym;
/* 13:   */ 
/* 14:   */ public class RenderMachine2
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderMachine2(amq bl)
/* 20:   */   {
/* 21:16 */     super(bl);
/* 22:17 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:28 */     TileMachine tb = (TileMachine)CoreLib.getTileEntity(iba, i, j, k, TileMachine.class);
/* 30:30 */     if (tb == null) {
/* 31:30 */       return;
/* 32:   */     }
/* 33:32 */     this.context.setDefaults();
/* 34:33 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 35:   */     
/* 36:35 */     this.context.setPos(i, j, k);
/* 37:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:37 */     if (md == 0)
/* 39:   */     {
/* 40:38 */       int t1 = 'É' + (tb.Charged ? 1 : 0) + (tb.Active ? 2 : 0);
/* 41:39 */       int t2 = 'Ç' + (tb.Charged ? 1 : 0);
/* 42:40 */       this.context.setTex(198, 197, t2, t2, t1, t1);
/* 43:   */     }
/* 44:41 */     else if (md == 1)
/* 45:   */     {
/* 46:42 */       int tx = 'Ò' + (tb.Charged ? 4 : 0) + (tb.Active ? 1 : 0) + ((tb.Delay) || (tb.Powered) ? 2 : 0);
/* 47:   */       
/* 48:44 */       this.context.setTex(209, 208, tx, tx, tx, tx);
/* 49:   */     }
/* 50:46 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 51:47 */     this.context.setupBox();
/* 52:48 */     this.context.transform();
/* 53:49 */     this.context.orientTextures(tb.Rotation);
/* 54:   */     
/* 55:51 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 56:52 */     this.context.renderGlobFaces(63);
/* 57:53 */     RenderLib.unbindTexture();
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 61:   */   {
/* 62:57 */     this.block.f();
/* 63:   */     
/* 64:59 */     this.context.setDefaults();
/* 65:60 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 66:61 */     this.context.useNormal = true;
/* 67:   */     
/* 68:63 */     RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 69:64 */     baz tessellator = baz.a;
/* 70:65 */     tessellator.b();
/* 71:67 */     if (md == 0) {
/* 72:68 */       this.context.setTex(198, 197, 199, 199, 201, 201);
/* 73:69 */     } else if (md == 1) {
/* 74:70 */       this.context.setTex(209, 208, 210, 210, 210, 210);
/* 75:   */     }
/* 76:72 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 77:   */     
/* 78:74 */     tessellator.a();
/* 79:75 */     RenderLib.unbindTexture();
/* 80:   */     
/* 81:77 */     this.context.useNormal = false;
/* 82:   */   }
/* 83:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.RenderMachine2
 * JD-Core Version:    0.7.0.1
 */