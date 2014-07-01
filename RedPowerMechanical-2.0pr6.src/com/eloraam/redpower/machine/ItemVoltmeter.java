/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.BluePowerConductor;
/*  4:   */ import com.eloraam.redpower.core.CoreLib;
/*  5:   */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  6:   */ import com.eloraam.redpower.core.IPipeConnectable;
/*  7:   */ import qx;
/*  8:   */ import tj;
/*  9:   */ import up;
/* 10:   */ import ur;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class ItemVoltmeter
/* 14:   */   extends up
/* 15:   */ {
/* 16:   */   public ItemVoltmeter(int i)
/* 17:   */   {
/* 18:16 */     super(i);
/* 19:17 */     c(24);
/* 20:18 */     d(1);
/* 21:19 */     a(tj.i);
/* 22:   */   }
/* 23:   */   
/* 24:   */   private boolean measureBlue(qx player, yc world, int i, int j, int k, int l)
/* 25:   */   {
/* 26:25 */     IBluePowerConnectable ibc = (IBluePowerConnectable)CoreLib.getTileEntity(world, i, j, k, IBluePowerConnectable.class);
/* 27:27 */     if (ibc == null) {
/* 28:27 */       return false;
/* 29:   */     }
/* 30:28 */     BluePowerConductor bpc = ibc.getBlueConductor(l);
/* 31:   */     
/* 32:30 */     double v = bpc.getVoltage();
/* 33:31 */     CoreLib.writeChat(player, String.format("Reading %.2fV %.2fA (%.2fW)", new Object[] { Double.valueOf(v), Double.valueOf(bpc.Itot), Double.valueOf(v * bpc.Itot) }));
/* 34:   */     
/* 35:   */ 
/* 36:34 */     return true;
/* 37:   */   }
/* 38:   */   
/* 39:   */   private boolean measurePressure(qx player, yc world, int i, int j, int k, int l)
/* 40:   */   {
/* 41:39 */     IPipeConnectable ipc = (IPipeConnectable)CoreLib.getTileEntity(world, i, j, k, IPipeConnectable.class);
/* 42:41 */     if (ipc == null) {
/* 43:41 */       return false;
/* 44:   */     }
/* 45:42 */     int psi = ipc.getPipePressure(l);
/* 46:   */     
/* 47:44 */     CoreLib.writeChat(player, String.format("Reading %d psi", new Object[] { Integer.valueOf(psi) }));
/* 48:45 */     return true;
/* 49:   */   }
/* 50:   */   
/* 51:   */   private boolean itemUseShared(ur ist, qx player, yc world, int i, int j, int k, int l)
/* 52:   */   {
/* 53:51 */     if (measureBlue(player, world, i, j, k, l)) {
/* 54:52 */       return true;
/* 55:   */     }
/* 56:53 */     if (measurePressure(player, world, i, j, k, l)) {
/* 57:54 */       return true;
/* 58:   */     }
/* 59:55 */     return false;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public boolean a(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/* 63:   */   {
/* 64:63 */     if (player.ah()) {
/* 65:63 */       return false;
/* 66:   */     }
/* 67:64 */     return itemUseShared(ist, player, world, i, j, k, l);
/* 68:   */   }
/* 69:   */   
/* 70:   */   public boolean onItemUseFirst(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/* 71:   */   {
/* 72:72 */     if (CoreLib.isClient(world)) {
/* 73:72 */       return false;
/* 74:   */     }
/* 75:73 */     if (!player.ah()) {
/* 76:73 */       return false;
/* 77:   */     }
/* 78:74 */     return itemUseShared(ist, player, world, i, j, k, l);
/* 79:   */   }
/* 80:   */   
/* 81:   */   public String getTextureFile()
/* 82:   */   {
/* 83:78 */     return "/eloraam/base/items1.png";
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ItemVoltmeter
 * JD-Core Version:    0.7.0.1
 */