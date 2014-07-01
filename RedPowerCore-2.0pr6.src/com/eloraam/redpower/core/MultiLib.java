/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import java.util.List;
/*  5:   */ import yc;
/*  6:   */ 
/*  7:   */ public class MultiLib
/*  8:   */ {
/*  9:   */   public static boolean isClear(yc world, WorldCoord parent, List coords)
/* 10:   */   {
/* 11:10 */     for (WorldCoord wc : coords) {
/* 12:11 */       if (!RedPowerBase.blockMultiblock.b(world, wc.x, wc.y, wc.z))
/* 13:   */       {
/* 14:14 */         TileMultiblock tmb = (TileMultiblock)CoreLib.getTileEntity(world, wc, TileMultiblock.class);
/* 15:16 */         if (tmb == null) {
/* 16:16 */           return false;
/* 17:   */         }
/* 18:18 */         if ((tmb.relayX != parent.x) || (tmb.relayY != parent.y) || (tmb.relayZ != parent.z)) {
/* 19:21 */           return false;
/* 20:   */         }
/* 21:   */       }
/* 22:   */     }
/* 23:23 */     return true;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public static void addRelays(yc world, WorldCoord parent, int md, List coords)
/* 27:   */   {
/* 28:28 */     int num = 0;
/* 29:29 */     for (WorldCoord wc : coords)
/* 30:   */     {
/* 31:30 */       world.c(wc.x, wc.y, wc.z, RedPowerBase.blockMultiblock.cm, md);
/* 32:   */       
/* 33:32 */       TileMultiblock tmb = (TileMultiblock)CoreLib.getTileEntity(world, wc, TileMultiblock.class);
/* 34:34 */       if (tmb != null)
/* 35:   */       {
/* 36:35 */         tmb.relayX = parent.x;
/* 37:36 */         tmb.relayY = parent.y;
/* 38:37 */         tmb.relayZ = parent.z;
/* 39:38 */         tmb.relayNum = num;
/* 40:39 */         num++;
/* 41:   */       }
/* 42:   */     }
/* 43:   */   }
/* 44:   */   
/* 45:   */   public static void removeRelays(yc world, WorldCoord parent, List coords)
/* 46:   */   {
/* 47:45 */     for (WorldCoord wc : coords)
/* 48:   */     {
/* 49:46 */       TileMultiblock tmb = (TileMultiblock)CoreLib.getTileEntity(world, wc, TileMultiblock.class);
/* 50:48 */       if ((tmb != null) && 
/* 51:49 */         (tmb.relayX == parent.x) && (tmb.relayY == parent.y) && (tmb.relayZ == parent.z)) {
/* 52:53 */         world.b(wc.x, wc.y, wc.z, 0);
/* 53:   */       }
/* 54:   */     }
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.MultiLib
 * JD-Core Version:    0.7.0.1
 */