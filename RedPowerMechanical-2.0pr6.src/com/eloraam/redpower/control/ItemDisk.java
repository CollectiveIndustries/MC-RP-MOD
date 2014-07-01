/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import com.eloraam.redpower.core.CoreLib;
/*  5:   */ import cpw.mods.fml.relauncher.Side;
/*  6:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  7:   */ import java.util.ArrayList;
/*  8:   */ import qx;
/*  9:   */ import up;
/* 10:   */ import ur;
/* 11:   */ import vb;
/* 12:   */ import yc;
/* 13:   */ 
/* 14:   */ public class ItemDisk
/* 15:   */   extends up
/* 16:   */ {
/* 17:   */   public ItemDisk(int i)
/* 18:   */   {
/* 19:19 */     super(i);
/* 20:20 */     e(0);
/* 21:21 */     a(true);
/* 22:22 */     d(1);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public String d(ur itemstack)
/* 26:   */   {
/* 27:26 */     switch (itemstack.j())
/* 28:   */     {
/* 29:   */     case 0: 
/* 30:28 */       return "item.disk";
/* 31:   */     case 1: 
/* 32:30 */       return "item.disk.forth";
/* 33:   */     case 2: 
/* 34:32 */       return "item.disk.forthxp";
/* 35:   */     }
/* 36:34 */     return null;
/* 37:   */   }
/* 38:   */   
/* 39:   */   @SideOnly(Side.CLIENT)
/* 40:   */   public String l(ur ist)
/* 41:   */   {
/* 42:39 */     if (ist.d == null) {
/* 43:40 */       return super.l(ist);
/* 44:   */     }
/* 45:41 */     if (!ist.d.b("label")) {
/* 46:42 */       return super.l(ist);
/* 47:   */     }
/* 48:44 */     return ist.d.i("label");
/* 49:   */   }
/* 50:   */   
/* 51:   */   @SideOnly(Side.CLIENT)
/* 52:   */   public vb f(ur ist)
/* 53:   */   {
/* 54:49 */     if (ist.j() >= 1) {
/* 55:50 */       return vb.b;
/* 56:   */     }
/* 57:51 */     return vb.a;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public boolean onItemUseFirst(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/* 61:   */   {
/* 62:58 */     if (CoreLib.isClient(world)) {
/* 63:58 */       return false;
/* 64:   */     }
/* 65:59 */     TileDiskDrive tdd = (TileDiskDrive)CoreLib.getTileEntity(world, i, j, k, TileDiskDrive.class);
/* 66:61 */     if (tdd == null) {
/* 67:61 */       return false;
/* 68:   */     }
/* 69:62 */     if (tdd.setDisk(ist.l()))
/* 70:   */     {
/* 71:63 */       ist.a = 0;
/* 72:64 */       return true;
/* 73:   */     }
/* 74:66 */     return false;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public boolean q()
/* 78:   */   {
/* 79:71 */     return true;
/* 80:   */   }
/* 81:   */   
/* 82:   */   public int b(int i)
/* 83:   */   {
/* 84:75 */     return 3 + i;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public String getTextureFile()
/* 88:   */   {
/* 89:79 */     return "/eloraam/control/control1.png";
/* 90:   */   }
/* 91:   */   
/* 92:   */   public void addCreativeItems(ArrayList itemList)
/* 93:   */   {
/* 94:83 */     for (int i = 0; i <= 1; i++) {
/* 95:84 */       itemList.add(new ur(this, 1, i));
/* 96:   */     }
/* 97:   */   }
/* 98:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.ItemDisk
 * JD-Core Version:    0.7.0.1
 */