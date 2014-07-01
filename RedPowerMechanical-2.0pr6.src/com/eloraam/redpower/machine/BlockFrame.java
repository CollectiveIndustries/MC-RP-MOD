/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import aoe;
/*  4:   */ import com.eloraam.redpower.core.BlockCoverable;
/*  5:   */ import com.eloraam.redpower.core.CoreLib;
/*  6:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  7:   */ import com.eloraam.redpower.core.WorldCoord;
/*  8:   */ import java.util.List;
/*  9:   */ import lq;
/* 10:   */ import yc;
/* 11:   */ 
/* 12:   */ public class BlockFrame
/* 13:   */   extends BlockCoverable
/* 14:   */ {
/* 15:   */   public BlockFrame(int i)
/* 16:   */   {
/* 17:16 */     super(i, CoreLib.materialRedpower);
/* 18:17 */     c(0.5F);
/* 19:18 */     a(CreativeExtraTabs.tabMachine);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void a(yc world, int i, int j, int k, aoe box, List list, lq ent)
/* 23:   */   {
/* 24:24 */     TileFrameMoving tl = (TileFrameMoving)CoreLib.getTileEntity(world, i, j, k, TileFrameMoving.class);
/* 25:26 */     if (tl == null)
/* 26:   */     {
/* 27:27 */       super.a(world, i, j, k, box, list, ent);
/* 28:28 */       return;
/* 29:   */     }
/* 30:31 */     computeCollidingBoxes(world, i, j, k, box, list, tl);
/* 31:   */     
/* 32:33 */     TileMotor tm = (TileMotor)CoreLib.getTileEntity(world, tl.motorX, tl.motorY, tl.motorZ, TileMotor.class);
/* 33:35 */     if (tm == null) {
/* 34:35 */       return;
/* 35:   */     }
/* 36:36 */     WorldCoord wc = new WorldCoord(i, j, k);
/* 37:37 */     wc.step(tm.MoveDir ^ 0x1);
/* 38:   */     
/* 39:39 */     tl = (TileFrameMoving)CoreLib.getTileEntity(world, wc, TileFrameMoving.class);
/* 40:40 */     if (tl == null) {
/* 41:40 */       return;
/* 42:   */     }
/* 43:42 */     computeCollidingBoxes(world, wc.x, wc.y, wc.z, box, list, tl);
/* 44:   */   }
/* 45:   */   
/* 46:   */   public String getTextureFile()
/* 47:   */   {
/* 48:46 */     return "/eloraam/machine/machine1.png";
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.BlockFrame
 * JD-Core Version:    0.7.0.1
 */