/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.CoreLib;
/*  4:   */ import com.eloraam.redpower.core.IRedbusConnectable;
/*  5:   */ import com.eloraam.redpower.core.IRedbusConnectable.Dummy;
/*  6:   */ import cpw.mods.fml.common.network.IGuiHandler;
/*  7:   */ import ld;
/*  8:   */ import qw;
/*  9:   */ import qx;
/* 10:   */ import yc;
/* 11:   */ 
/* 12:   */ public class BaseProxy
/* 13:   */   implements IGuiHandler
/* 14:   */ {
/* 15:   */   public void registerRenderers() {}
/* 16:   */   
/* 17:   */   public Object getClientGuiElement(int ID, qx player, yc world, int x, int y, int z)
/* 18:   */   {
/* 19:18 */     switch (ID)
/* 20:   */     {
/* 21:   */     case 1: 
/* 22:20 */       return new GuiAlloyFurnace(player.bJ, (TileAlloyFurnace)CoreLib.getGuiTileEntity(world, x, y, z, TileAlloyFurnace.class));
/* 23:   */     case 2: 
/* 24:25 */       return new GuiAdvBench(player.bJ, (TileAdvBench)CoreLib.getGuiTileEntity(world, x, y, z, TileAdvBench.class));
/* 25:   */     case 3: 
/* 26:30 */       return new GuiBusId(player.bJ, new IRedbusConnectable.Dummy());
/* 27:   */     case 4: 
/* 28:33 */       return new GuiBag(player.bJ, new ld("", 27));
/* 29:   */     }
/* 30:36 */     return null;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public Object getServerGuiElement(int ID, qx player, yc world, int x, int y, int z)
/* 34:   */   {
/* 35:42 */     switch (ID)
/* 36:   */     {
/* 37:   */     case 1: 
/* 38:44 */       return new ContainerAlloyFurnace(player.bJ, (TileAlloyFurnace)CoreLib.getTileEntity(world, x, y, z, TileAlloyFurnace.class));
/* 39:   */     case 2: 
/* 40:49 */       return new ContainerAdvBench(player.bJ, (TileAdvBench)CoreLib.getTileEntity(world, x, y, z, TileAdvBench.class));
/* 41:   */     case 3: 
/* 42:54 */       return new ContainerBusId(player.bJ, (IRedbusConnectable)CoreLib.getTileEntity(world, x, y, z, IRedbusConnectable.class));
/* 43:   */     case 4: 
/* 44:60 */       return new ContainerBag(player.bJ, ItemBag.getBagInventory(player.bJ.g()), player.bJ.g());
/* 45:   */     }
/* 46:66 */     return null;
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.BaseProxy
 * JD-Core Version:    0.7.0.1
 */