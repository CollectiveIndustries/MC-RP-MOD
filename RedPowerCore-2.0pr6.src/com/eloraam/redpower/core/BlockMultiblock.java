/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import akb;
/*   4:    */ import any;
/*   5:    */ import aoe;
/*   6:    */ import com.eloraam.redpower.RedPowerCore;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import qx;
/*   9:    */ import yc;
/*  10:    */ import ym;
/*  11:    */ 
/*  12:    */ public class BlockMultiblock
/*  13:    */   extends akb
/*  14:    */ {
/*  15:    */   public BlockMultiblock(int i)
/*  16:    */   {
/*  17: 16 */     super(i, CoreLib.materialRedpower);
/*  18:    */   }
/*  19:    */   
/*  20:    */   public int d()
/*  21:    */   {
/*  22: 20 */     return RedPowerCore.nullBlockModel;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public boolean c()
/*  26:    */   {
/*  27: 24 */     return false;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public boolean b()
/*  31:    */   {
/*  32: 27 */     return false;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public ArrayList getBlockDropped(yc world, int x, int y, int z, int md, int fortune)
/*  36:    */   {
/*  37: 32 */     return new ArrayList();
/*  38:    */   }
/*  39:    */   
/*  40:    */   public any a(yc world)
/*  41:    */   {
/*  42: 37 */     return null;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public any createTileEntity(yc world, int md)
/*  46:    */   {
/*  47: 42 */     switch (md)
/*  48:    */     {
/*  49:    */     case 0: 
/*  50: 43 */       return new TileMultiblock();
/*  51:    */     }
/*  52: 44 */     return null;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void a(yc world, int x, int y, int z, int id, int md)
/*  56:    */   {
/*  57: 53 */     TileMultiblock tmb = (TileMultiblock)CoreLib.getTileEntity(world, x, y, z, TileMultiblock.class);
/*  58: 55 */     if (tmb == null) {
/*  59: 55 */       return;
/*  60:    */     }
/*  61: 57 */     IMultiblock imb = (IMultiblock)CoreLib.getTileEntity(world, tmb.relayX, tmb.relayY, tmb.relayZ, IMultiblock.class);
/*  62: 60 */     if (imb == null) {
/*  63: 60 */       return;
/*  64:    */     }
/*  65: 62 */     imb.onMultiRemoval(tmb.relayNum);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void a(ym iba, int x, int y, int z)
/*  69:    */   {
/*  70: 68 */     TileMultiblock tmb = (TileMultiblock)CoreLib.getTileEntity(iba, x, y, z, TileMultiblock.class);
/*  71: 70 */     if (tmb == null)
/*  72:    */     {
/*  73: 71 */       a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  74: 72 */       return;
/*  75:    */     }
/*  76: 75 */     IMultiblock imb = (IMultiblock)CoreLib.getTileEntity(iba, tmb.relayX, tmb.relayY, tmb.relayZ, IMultiblock.class);
/*  77: 78 */     if (imb == null) {
/*  78: 78 */       return;
/*  79:    */     }
/*  80: 80 */     aoe aabb = imb.getMultiBounds(tmb.relayNum);
/*  81:    */     
/*  82: 82 */     int xa = tmb.relayX - x;int ya = tmb.relayY - y;int za = tmb.relayZ - z;
/*  83: 83 */     a((float)aabb.a + xa, (float)aabb.b + ya, (float)aabb.c + za, (float)aabb.d + xa, (float)aabb.e + ya, (float)aabb.f + za);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public float a(qx player, yc world, int x, int y, int z)
/*  87:    */   {
/*  88: 94 */     TileMultiblock tmb = (TileMultiblock)CoreLib.getTileEntity(world, x, y, z, TileMultiblock.class);
/*  89: 96 */     if (tmb == null) {
/*  90: 96 */       return 0.0F;
/*  91:    */     }
/*  92: 98 */     IMultiblock imb = (IMultiblock)CoreLib.getTileEntity(world, tmb.relayX, tmb.relayY, tmb.relayZ, IMultiblock.class);
/*  93:101 */     if (imb == null) {
/*  94:101 */       return 0.0F;
/*  95:    */     }
/*  96:103 */     return imb.getMultiBlockStrength(tmb.relayNum, player);
/*  97:    */   }
/*  98:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.BlockMultiblock
 * JD-Core Version:    0.7.0.1
 */