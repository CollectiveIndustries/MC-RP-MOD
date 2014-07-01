/*  1:   */ package com.eloraam.redpower.compat;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.network.IGuiHandler;
/*  4:   */ import qx;
/*  5:   */ import yc;
/*  6:   */ 
/*  7:   */ public class CompatProxy
/*  8:   */   implements IGuiHandler
/*  9:   */ {
/* 10:   */   public void registerRenderers() {}
/* 11:   */   
/* 12:   */   public Object getClientGuiElement(int ID, qx player, yc world, int X, int Y, int Z)
/* 13:   */   {
/* 14:16 */     return null;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public Object getServerGuiElement(int ID, qx player, yc world, int X, int Y, int Z)
/* 18:   */   {
/* 19:22 */     return null;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.compat.CompatProxy
 * JD-Core Version:    0.7.0.1
 */