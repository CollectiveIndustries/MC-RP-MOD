/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.core.CoreLib;
/*   4:    */ import cpw.mods.fml.common.network.IGuiHandler;
/*   5:    */ import qx;
/*   6:    */ import yc;
/*   7:    */ 
/*   8:    */ public class MachineProxy
/*   9:    */   implements IGuiHandler
/*  10:    */ {
/*  11:    */   public void registerRenderers() {}
/*  12:    */   
/*  13:    */   public Object getClientGuiElement(int ID, qx player, yc world, int X, int Y, int Z)
/*  14:    */   {
/*  15: 16 */     switch (ID)
/*  16:    */     {
/*  17:    */     case 1: 
/*  18: 18 */       return new GuiDeploy(player.bJ, (TileDeploy)CoreLib.getGuiTileEntity(world, X, Y, Z, TileDeploy.class));
/*  19:    */     case 2: 
/*  20: 23 */       return new GuiFilter(player.bJ, (TileFilter)CoreLib.getGuiTileEntity(world, X, Y, Z, TileFilter.class));
/*  21:    */     case 3: 
/*  22: 28 */       return new GuiBlueFurnace(player.bJ, (TileBlueFurnace)CoreLib.getGuiTileEntity(world, X, Y, Z, TileBlueFurnace.class));
/*  23:    */     case 4: 
/*  24: 33 */       return new GuiBufferChest(player.bJ, (TileBufferChest)CoreLib.getGuiTileEntity(world, X, Y, Z, TileBufferChest.class));
/*  25:    */     case 5: 
/*  26: 38 */       return new GuiSorter(player.bJ, (TileSorter)CoreLib.getGuiTileEntity(world, X, Y, Z, TileSorter.class));
/*  27:    */     case 6: 
/*  28: 43 */       return new GuiItemDetect(player.bJ, (TileItemDetect)CoreLib.getGuiTileEntity(world, X, Y, Z, TileItemDetect.class));
/*  29:    */     case 7: 
/*  30: 48 */       return new GuiRetriever(player.bJ, (TileRetriever)CoreLib.getGuiTileEntity(world, X, Y, Z, TileRetriever.class));
/*  31:    */     case 8: 
/*  32: 53 */       return new GuiBatteryBox(player.bJ, (TileBatteryBox)CoreLib.getGuiTileEntity(world, X, Y, Z, TileBatteryBox.class));
/*  33:    */     case 9: 
/*  34: 58 */       return new GuiRegulator(player.bJ, (TileRegulator)CoreLib.getGuiTileEntity(world, X, Y, Z, TileRegulator.class));
/*  35:    */     case 10: 
/*  36: 63 */       return new GuiBlueAlloyFurnace(player.bJ, (TileBlueAlloyFurnace)CoreLib.getGuiTileEntity(world, X, Y, Z, TileBlueAlloyFurnace.class));
/*  37:    */     case 11: 
/*  38: 68 */       return new GuiAssemble(player.bJ, (TileAssemble)CoreLib.getGuiTileEntity(world, X, Y, Z, TileAssemble.class));
/*  39:    */     case 12: 
/*  40: 73 */       return new GuiEject(player.bJ, (TileEjectBase)CoreLib.getGuiTileEntity(world, X, Y, Z, TileEjectBase.class));
/*  41:    */     case 13: 
/*  42: 78 */       return new GuiEject(player.bJ, (TileEjectBase)CoreLib.getGuiTileEntity(world, X, Y, Z, TileRelay.class));
/*  43:    */     case 14: 
/*  44: 83 */       return new GuiChargingBench(player.bJ, (TileChargingBench)CoreLib.getGuiTileEntity(world, X, Y, Z, TileChargingBench.class));
/*  45:    */     case 15: 
/*  46: 88 */       return new GuiWindTurbine(player.bJ, (TileWindTurbine)CoreLib.getGuiTileEntity(world, X, Y, Z, TileWindTurbine.class));
/*  47:    */     case 16: 
/*  48: 93 */       return new GuiManager(player.bJ, (TileManager)CoreLib.getGuiTileEntity(world, X, Y, Z, TileManager.class));
/*  49:    */     }
/*  50: 98 */     return null;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public Object getServerGuiElement(int ID, qx player, yc world, int X, int Y, int Z)
/*  54:    */   {
/*  55:104 */     switch (ID)
/*  56:    */     {
/*  57:    */     case 1: 
/*  58:106 */       return new ContainerDeploy(player.bJ, (TileDeploy)CoreLib.getTileEntity(world, X, Y, Z, TileDeploy.class));
/*  59:    */     case 2: 
/*  60:112 */       return new ContainerFilter(player.bJ, (TileFilter)CoreLib.getTileEntity(world, X, Y, Z, TileFilter.class));
/*  61:    */     case 3: 
/*  62:118 */       return new ContainerBlueFurnace(player.bJ, (TileBlueFurnace)CoreLib.getTileEntity(world, X, Y, Z, TileBlueFurnace.class));
/*  63:    */     case 4: 
/*  64:124 */       return new ContainerBufferChest(player.bJ, (TileBufferChest)CoreLib.getTileEntity(world, X, Y, Z, TileBufferChest.class));
/*  65:    */     case 5: 
/*  66:130 */       return new ContainerSorter(player.bJ, (TileSorter)CoreLib.getTileEntity(world, X, Y, Z, TileSorter.class));
/*  67:    */     case 6: 
/*  68:136 */       return new ContainerItemDetect(player.bJ, (TileItemDetect)CoreLib.getTileEntity(world, X, Y, Z, TileItemDetect.class));
/*  69:    */     case 7: 
/*  70:142 */       return new ContainerRetriever(player.bJ, (TileRetriever)CoreLib.getTileEntity(world, X, Y, Z, TileRetriever.class));
/*  71:    */     case 8: 
/*  72:148 */       return new ContainerBatteryBox(player.bJ, (TileBatteryBox)CoreLib.getTileEntity(world, X, Y, Z, TileBatteryBox.class));
/*  73:    */     case 9: 
/*  74:154 */       return new ContainerRegulator(player.bJ, (TileRegulator)CoreLib.getTileEntity(world, X, Y, Z, TileRegulator.class));
/*  75:    */     case 10: 
/*  76:160 */       return new ContainerBlueAlloyFurnace(player.bJ, (TileBlueAlloyFurnace)CoreLib.getTileEntity(world, X, Y, Z, TileBlueAlloyFurnace.class));
/*  77:    */     case 11: 
/*  78:166 */       return new ContainerAssemble(player.bJ, (TileAssemble)CoreLib.getTileEntity(world, X, Y, Z, TileAssemble.class));
/*  79:    */     case 12: 
/*  80:172 */       return new ContainerEject(player.bJ, (TileEjectBase)CoreLib.getTileEntity(world, X, Y, Z, TileEjectBase.class));
/*  81:    */     case 13: 
/*  82:178 */       return new ContainerEject(player.bJ, (TileEjectBase)CoreLib.getTileEntity(world, X, Y, Z, TileRelay.class));
/*  83:    */     case 14: 
/*  84:184 */       return new ContainerChargingBench(player.bJ, (TileChargingBench)CoreLib.getTileEntity(world, X, Y, Z, TileChargingBench.class));
/*  85:    */     case 15: 
/*  86:190 */       return new ContainerWindTurbine(player.bJ, (TileWindTurbine)CoreLib.getTileEntity(world, X, Y, Z, TileWindTurbine.class));
/*  87:    */     case 16: 
/*  88:196 */       return new ContainerManager(player.bJ, (TileManager)CoreLib.getTileEntity(world, X, Y, Z, TileManager.class));
/*  89:    */     }
/*  90:201 */     return null;
/*  91:    */   }
/*  92:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.MachineProxy
 * JD-Core Version:    0.7.0.1
 */