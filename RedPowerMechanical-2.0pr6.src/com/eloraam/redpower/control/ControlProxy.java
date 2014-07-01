/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.CoreLib;
/*  4:   */ import cpw.mods.fml.common.network.IGuiHandler;
/*  5:   */ import qx;
/*  6:   */ import yc;
/*  7:   */ 
/*  8:   */ public class ControlProxy
/*  9:   */   implements IGuiHandler
/* 10:   */ {
/* 11:   */   public void registerRenderers() {}
/* 12:   */   
/* 13:   */   public Object getClientGuiElement(int ID, qx player, yc world, int X, int Y, int Z)
/* 14:   */   {
/* 15:16 */     switch (ID)
/* 16:   */     {
/* 17:   */     case 1: 
/* 18:18 */       return new GuiDisplay(player.bJ, (TileDisplay)CoreLib.getGuiTileEntity(world, X, Y, Z, TileDisplay.class));
/* 19:   */     case 2: 
/* 20:23 */       return new GuiCPU(player.bJ, (TileCPU)CoreLib.getGuiTileEntity(world, X, Y, Z, TileCPU.class));
/* 21:   */     }
/* 22:28 */     return null;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public Object getServerGuiElement(int ID, qx player, yc world, int X, int Y, int Z)
/* 26:   */   {
/* 27:34 */     switch (ID)
/* 28:   */     {
/* 29:   */     case 1: 
/* 30:36 */       return new ContainerDisplay(player.bJ, (TileDisplay)CoreLib.getTileEntity(world, X, Y, Z, TileDisplay.class));
/* 31:   */     case 2: 
/* 32:42 */       return new ContainerCPU(player.bJ, (TileCPU)CoreLib.getTileEntity(world, X, Y, Z, TileCPU.class));
/* 33:   */     }
/* 34:48 */     return null;
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.ControlProxy
 * JD-Core Version:    0.7.0.1
 */