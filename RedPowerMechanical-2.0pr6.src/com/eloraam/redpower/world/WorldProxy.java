/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.network.IGuiHandler;
/*  4:   */ import ld;
/*  5:   */ import qw;
/*  6:   */ import qx;
/*  7:   */ import yc;
/*  8:   */ 
/*  9:   */ public class WorldProxy
/* 10:   */   implements IGuiHandler
/* 11:   */ {
/* 12:   */   public void registerRenderers() {}
/* 13:   */   
/* 14:   */   public Object getClientGuiElement(int ID, qx player, yc world, int x, int y, int z)
/* 15:   */   {
/* 16:18 */     switch (ID)
/* 17:   */     {
/* 18:   */     case 1: 
/* 19:20 */       return new GuiSeedBag(player.bJ, new ld("", 9));
/* 20:   */     }
/* 21:23 */     return null;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public Object getServerGuiElement(int ID, qx player, yc world, int x, int y, int z)
/* 25:   */   {
/* 26:29 */     switch (ID)
/* 27:   */     {
/* 28:   */     case 1: 
/* 29:31 */       return new ContainerSeedBag(player.bJ, ItemSeedBag.getBagInventory(player.bJ.g()), player.bJ.g());
/* 30:   */     }
/* 31:37 */     return null;
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.WorldProxy
 * JD-Core Version:    0.7.0.1
 */