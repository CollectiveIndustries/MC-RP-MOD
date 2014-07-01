/*  1:   */ package com.eloraam.redpower.logic;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.CoreLib;
/*  4:   */ import cpw.mods.fml.common.network.IGuiHandler;
/*  5:   */ import qx;
/*  6:   */ import yc;
/*  7:   */ 
/*  8:   */ public class LogicProxy
/*  9:   */   implements IGuiHandler
/* 10:   */ {
/* 11:   */   public void registerRenderers() {}
/* 12:   */   
/* 13:   */   public Object getClientGuiElement(int ID, qx player, yc world, int X, int Y, int Z)
/* 14:   */   {
/* 15:17 */     switch (ID)
/* 16:   */     {
/* 17:   */     case 1: 
/* 18:19 */       return new GuiCounter(player.bJ, (TileLogicStorage)CoreLib.getGuiTileEntity(world, X, Y, Z, TileLogicStorage.class));
/* 19:   */     case 2: 
/* 20:24 */       return new GuiTimer(player.bJ, (TileLogicPointer)CoreLib.getGuiTileEntity(world, X, Y, Z, TileLogicPointer.class));
/* 21:   */     }
/* 22:29 */     return null;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public Object getServerGuiElement(int ID, qx player, yc world, int X, int Y, int Z)
/* 26:   */   {
/* 27:35 */     switch (ID)
/* 28:   */     {
/* 29:   */     case 1: 
/* 30:37 */       return new ContainerCounter(player.bJ, (TileLogicStorage)CoreLib.getTileEntity(world, X, Y, Z, TileLogicStorage.class));
/* 31:   */     case 2: 
/* 32:42 */       return new ContainerTimer(player.bJ, (TileLogicPointer)CoreLib.getTileEntity(world, X, Y, Z, TileLogicPointer.class));
/* 33:   */     }
/* 34:47 */     return null;
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.LogicProxy
 * JD-Core Version:    0.7.0.1
 */