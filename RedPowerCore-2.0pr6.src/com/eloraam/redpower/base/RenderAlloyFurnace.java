/*  1:   */ package com.eloraam.redpower.base;
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
/* 14:   */ public class RenderAlloyFurnace
/* 15:   */   extends RenderCustomBlock
/* 16:   */ {
/* 17:   */   protected RenderContext context;
/* 18:   */   
/* 19:   */   public RenderAlloyFurnace(amq bl)
/* 20:   */   {
/* 21:14 */     super(bl);
/* 22:15 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random)
/* 26:   */   {
/* 27:20 */     TileAlloyFurnace tb = (TileAlloyFurnace)CoreLib.getTileEntity(world, i, j, k, TileAlloyFurnace.class);
/* 28:22 */     if (tb == null) {
/* 29:22 */       return;
/* 30:   */     }
/* 31:23 */     if (!tb.Active) {
/* 32:23 */       return;
/* 33:   */     }
/* 34:25 */     float f = i + 0.5F;
/* 35:26 */     float f1 = j + 0.0F + random.nextFloat() * 6.0F / 16.0F;
/* 36:27 */     float f2 = k + 0.5F;
/* 37:28 */     float f3 = 0.52F;
/* 38:29 */     float f4 = random.nextFloat() * 0.6F - 0.3F;
/* 39:30 */     switch (tb.Rotation)
/* 40:   */     {
/* 41:   */     case 0: 
/* 42:32 */       world.a("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
/* 43:33 */       world.a("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
/* 44:34 */       break;
/* 45:   */     case 1: 
/* 46:36 */       world.a("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 47:37 */       world.a("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 48:38 */       break;
/* 49:   */     case 2: 
/* 50:40 */       world.a("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
/* 51:41 */       world.a("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
/* 52:42 */       break;
/* 53:   */     case 3: 
/* 54:44 */       world.a("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 55:45 */       world.a("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 56:   */     }
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 60:   */   {
/* 61:54 */     TileAlloyFurnace tb = (TileAlloyFurnace)CoreLib.getTileEntity(iba, i, j, k, TileAlloyFurnace.class);
/* 62:56 */     if (tb == null) {
/* 63:56 */       return;
/* 64:   */     }
/* 65:58 */     this.context.setDefaults();
/* 66:59 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 67:   */     
/* 68:61 */     int tx = tb.Active ? 18 : 17;
/* 69:62 */     this.context.setPos(i, j, k);
/* 70:63 */     this.context.readGlobalLights(iba, i, j, k);
/* 71:   */     
/* 72:65 */     this.context.setTex(19, 19, tx, 16, 16, 16);
/* 73:66 */     this.context.rotateTextures(tb.Rotation);
/* 74:67 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 75:68 */     this.context.setupBox();
/* 76:69 */     this.context.transform();
/* 77:   */     
/* 78:   */ 
/* 79:72 */     RenderLib.bindTexture("/eloraam/base/base1.png");
/* 80:73 */     this.context.renderGlobFaces(63);
/* 81:74 */     RenderLib.unbindTexture();
/* 82:   */   }
/* 83:   */   
/* 84:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 85:   */   {
/* 86:78 */     this.block.f();
/* 87:   */     
/* 88:80 */     this.context.setDefaults();
/* 89:81 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 90:82 */     this.context.useNormal = true;
/* 91:   */     
/* 92:84 */     RenderLib.bindTexture("/eloraam/base/base1.png");
/* 93:85 */     baz tessellator = baz.a;
/* 94:86 */     tessellator.b();
/* 95:   */     
/* 96:88 */     this.context.setTex(19, 19, 16, 17, 16, 16);
/* 97:89 */     this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 98:   */     
/* 99:91 */     tessellator.a();
/* :0:92 */     RenderLib.unbindTexture();
/* :1:   */     
/* :2:94 */     this.context.useNormal = false;
/* :3:   */   }
/* :4:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.RenderAlloyFurnace
 * JD-Core Version:    0.7.0.1
 */