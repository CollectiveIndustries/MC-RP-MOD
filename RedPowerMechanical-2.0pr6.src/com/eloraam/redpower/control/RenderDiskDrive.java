/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import baz;
/*  5:   */ import bbb;
/*  6:   */ import com.eloraam.redpower.core.CoreLib;
/*  7:   */ import com.eloraam.redpower.core.RenderContext;
/*  8:   */ import com.eloraam.redpower.core.RenderCustomBlock;
/*  9:   */ import java.util.Random;
/* 10:   */ import yc;
/* 11:   */ import ym;
/* 12:   */ 
/* 13:   */ public class RenderDiskDrive
/* 14:   */   extends RenderCustomBlock
/* 15:   */ {
/* 16:   */   RenderContext context;
/* 17:   */   
/* 18:   */   public RenderDiskDrive(amq bl)
/* 19:   */   {
/* 20:15 */     super(bl);
/* 21:   */     
/* 22:17 */     this.context = new RenderContext();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void randomDisplayTick(yc world, int i, int j, int k, Random random) {}
/* 26:   */   
/* 27:   */   public void renderWorldBlock(bbb renderblocks, ym iba, int i, int j, int k, int md)
/* 28:   */   {
/* 29:28 */     TileDiskDrive disk = (TileDiskDrive)CoreLib.getTileEntity(iba, i, j, k, TileDiskDrive.class);
/* 30:30 */     if (disk == null) {
/* 31:30 */       return;
/* 32:   */     }
/* 33:32 */     this.context.setDefaults();
/* 34:   */     
/* 35:   */ 
/* 36:   */ 
/* 37:36 */     this.context.readGlobalLights(iba, i, j, k);
/* 38:37 */     this.context.bindTexture("/eloraam/control/control1.png");
/* 39:   */     
/* 40:   */ 
/* 41:   */ 
/* 42:41 */     int tx = disk.hasDisk ? 26 : disk.Active ? 27 : 25;
/* 43:42 */     this.context.setTex(23, 29, 28, 28, tx, 22);
/* 44:43 */     this.context.setTexFlags(512);
/* 45:44 */     this.context.rotateTextures(disk.Rotation);
/* 46:45 */     this.context.setPos(i, j, k);
/* 47:   */     
/* 48:47 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 49:   */     
/* 50:   */ 
/* 51:50 */     this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 52:51 */     this.context.setupBox();
/* 53:52 */     this.context.transform();
/* 54:53 */     this.context.renderGlobFaces(63);
/* 55:   */     
/* 56:55 */     this.context.unbindTexture();
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void renderInvBlock(bbb renderblocks, int md)
/* 60:   */   {
/* 61:60 */     baz tessellator = baz.a;
/* 62:61 */     this.block.f();
/* 63:62 */     this.context.setDefaults();
/* 64:63 */     this.context.useNormal = true;
/* 65:   */     
/* 66:65 */     this.context.setOrientation(0, 3);
/* 67:66 */     this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 68:   */     
/* 69:68 */     this.context.bindTexture("/eloraam/control/control1.png");
/* 70:69 */     this.context.setTexFlags(512);
/* 71:70 */     this.context.setTex(23, 29, 28, 28, 25, 22);
/* 72:71 */     this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
/* 73:   */     
/* 74:73 */     tessellator.b();
/* 75:74 */     this.context.renderBox(62, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 76:75 */     tessellator.a();
/* 77:   */     
/* 78:77 */     this.context.useNormal = false;
/* 79:78 */     this.context.unbindTexture();
/* 80:   */   }
/* 81:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.RenderDiskDrive
 * JD-Core Version:    0.7.0.1
 */