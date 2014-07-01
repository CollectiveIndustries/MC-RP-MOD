/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import any;
/*   5:    */ import arw;
/*   6:    */ import baz;
/*   7:    */ import bbb;
/*   8:    */ import bdx;
/*   9:    */ import com.eloraam.redpower.core.CoreLib;
/*  10:    */ import com.eloraam.redpower.core.RenderContext;
/*  11:    */ import com.eloraam.redpower.core.WorldCoord;
/*  12:    */ import net.minecraft.client.Minecraft;
/*  13:    */ import net.minecraftforge.client.ForgeHooksClient;
/*  14:    */ import org.lwjgl.opengl.GL11;
/*  15:    */ import yc;
/*  16:    */ import ym;
/*  17:    */ 
/*  18:    */ public class TileFrameRenderer
/*  19:    */   extends bdx
/*  20:    */ {
/*  21:    */   private bbb rblocks;
/*  22:    */   
/*  23:    */   public void a(any te, double x, double y, double z, float f)
/*  24:    */   {
/*  25: 33 */     if (te.r()) {
/*  26: 33 */       return;
/*  27:    */     }
/*  28: 34 */     TileFrameMoving tfm = (TileFrameMoving)te;
/*  29:    */     
/*  30: 36 */     amq block = amq.p[tfm.movingBlockID];
/*  31: 37 */     if (block == null) {
/*  32: 37 */       return;
/*  33:    */     }
/*  34: 39 */     baz tessellator = baz.a;
/*  35: 40 */     a("/terrain.png");
/*  36:    */     
/*  37: 42 */     int lv = te.k.i(te.l, te.m, te.n, 0);
/*  38:    */     
/*  39: 44 */     tessellator.c(lv);
/*  40:    */     
/*  41: 46 */     arw.a();
/*  42: 47 */     GL11.glBlendFunc(770, 771);
/*  43: 48 */     GL11.glEnable(3042);
/*  44: 49 */     GL11.glEnable(2884);
/*  45: 50 */     if (Minecraft.u()) {
/*  46: 51 */       GL11.glShadeModel(7425);
/*  47:    */     } else {
/*  48: 53 */       GL11.glShadeModel(7424);
/*  49:    */     }
/*  50: 56 */     ym wba = this.rblocks.a;
/*  51: 57 */     this.rblocks.a = tfm.getFrameBlockAccess();
/*  52:    */     
/*  53: 59 */     ForgeHooksClient.beforeBlockRender(block, this.rblocks);
/*  54:    */     
/*  55: 61 */     TileMotor tm = (TileMotor)CoreLib.getTileEntity(tfm.k, tfm.motorX, tfm.motorY, tfm.motorZ, TileMotor.class);
/*  56:    */     
/*  57:    */ 
/*  58: 64 */     GL11.glPushMatrix();
/*  59: 66 */     if (tm != null)
/*  60:    */     {
/*  61: 67 */       WorldCoord wc = new WorldCoord(0, 0, 0);
/*  62: 68 */       wc.step(tm.MoveDir);
/*  63: 69 */       float ms = tm.getMoveScaled();
/*  64: 70 */       GL11.glTranslatef(wc.x * ms, wc.y * ms, wc.z * ms);
/*  65:    */     }
/*  66: 73 */     tessellator.b();
/*  67: 74 */     tessellator.b(x - tfm.l, y - tfm.m, z - tfm.n);
/*  68: 75 */     tessellator.a(1, 1, 1);
/*  69: 77 */     if (tfm.movingCrate)
/*  70:    */     {
/*  71: 78 */       this.context.setDefaults();
/*  72: 79 */       this.context.setBrightness(lv);
/*  73: 80 */       this.context.setPos(tfm.l, tfm.m, tfm.n);
/*  74: 81 */       this.context.setTexFile("/eloraam/machine/machine1.png");
/*  75: 82 */       this.context.setTex(5);
/*  76: 83 */       this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  77:    */     }
/*  78:    */     else
/*  79:    */     {
/*  80: 85 */       tfm.doRefresh(tfm.getFrameBlockAccess());
/*  81: 86 */       this.rblocks.f = true;
/*  82: 87 */       this.rblocks.b(block, tfm.l, tfm.m, tfm.n);
/*  83:    */       
/*  84: 89 */       this.rblocks.f = false;
/*  85:    */     }
/*  86: 92 */     tessellator.b(0.0D, 0.0D, 0.0D);
/*  87: 93 */     tessellator.a();
/*  88: 94 */     GL11.glPopMatrix();
/*  89: 95 */     ForgeHooksClient.afterBlockRender(block, this.rblocks);
/*  90: 96 */     this.rblocks.a = wba;
/*  91:    */     
/*  92: 98 */     arw.b();
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void a(yc world)
/*  96:    */   {
/*  97:104 */     this.rblocks = new bbb(world);
/*  98:    */   }
/*  99:    */   
/* 100:108 */   RenderContext context = new RenderContext();
/* 101:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileFrameRenderer
 * JD-Core Version:    0.7.0.1
 */