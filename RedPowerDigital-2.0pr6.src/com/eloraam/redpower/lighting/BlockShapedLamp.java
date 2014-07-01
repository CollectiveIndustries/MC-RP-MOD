/*  1:   */ package com.eloraam.redpower.lighting;
/*  2:   */ 
/*  3:   */ import aoe;
/*  4:   */ import com.eloraam.redpower.RedPowerLighting;
/*  5:   */ import com.eloraam.redpower.core.BlockExtended;
/*  6:   */ import com.eloraam.redpower.core.CoreLib;
/*  7:   */ import ym;
/*  8:   */ 
/*  9:   */ public class BlockShapedLamp
/* 10:   */   extends BlockExtended
/* 11:   */ {
/* 12:   */   public BlockShapedLamp(int i)
/* 13:   */   {
/* 14:19 */     super(i, CoreLib.materialRedpower);
/* 15:20 */     c(1.0F);
/* 16:21 */     a(RedPowerLighting.tabLamp);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public boolean canRenderInPass(int n)
/* 20:   */   {
/* 21:25 */     return true;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean c()
/* 25:   */   {
/* 26:28 */     return false;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public boolean b()
/* 30:   */   {
/* 31:31 */     return false;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean isACube()
/* 35:   */   {
/* 36:34 */     return false;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public boolean i()
/* 40:   */   {
/* 41:37 */     return true;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public int n()
/* 45:   */   {
/* 46:41 */     return 1;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public int getLightValue(ym iba, int i, int j, int k)
/* 50:   */   {
/* 51:45 */     TileShapedLamp taf = (TileShapedLamp)CoreLib.getTileEntity(iba, i, j, k, TileShapedLamp.class);
/* 52:47 */     if (taf == null) {
/* 53:47 */       return 0;
/* 54:   */     }
/* 55:48 */     return taf.getLightValue();
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void a(ym iba, int x, int y, int z)
/* 59:   */   {
/* 60:54 */     TileShapedLamp tsl = (TileShapedLamp)CoreLib.getTileEntity(iba, x, y, z, TileShapedLamp.class);
/* 61:56 */     if (tsl == null)
/* 62:   */     {
/* 63:57 */       a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 64:58 */       return;
/* 65:   */     }
/* 66:61 */     aoe aabb = tsl.getBounds();
/* 67:62 */     a((float)aabb.a, (float)aabb.b, (float)aabb.c, (float)aabb.d, (float)aabb.e, (float)aabb.f);
/* 68:   */   }
/* 69:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.lighting.BlockShapedLamp
 * JD-Core Version:    0.7.0.1
 */