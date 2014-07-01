/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import ajr;
/*  4:   */ import amm;
/*  5:   */ import amq;
/*  6:   */ import amu;
/*  7:   */ import com.eloraam.redpower.core.BlockExtended;
/*  8:   */ import com.eloraam.redpower.core.ItemExtended;
/*  9:   */ import net.minecraftforge.common.ForgeDirection;
/* 10:   */ import qx;
/* 11:   */ import ur;
/* 12:   */ import yc;
/* 13:   */ 
/* 14:   */ public class ItemMachinePanel
/* 15:   */   extends ItemExtended
/* 16:   */ {
/* 17:   */   public ItemMachinePanel(int i)
/* 18:   */   {
/* 19:18 */     super(i);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean a(ur ist, qx player, yc world, int x, int y, int z, int side, float xp, float yp, float zp)
/* 23:   */   {
/* 24:27 */     int bid = world.a(x, y, z);
/* 25:28 */     int blockID = g();
/* 26:30 */     if (bid == amq.aV.cm) {
/* 27:31 */       side = 1;
/* 28:32 */     } else if ((bid != amq.bx.cm) && (bid != amq.aa.cm) && (bid != amq.ab.cm)) {
/* 29:35 */       switch (side)
/* 30:   */       {
/* 31:   */       case 0: 
/* 32:36 */         y--; break;
/* 33:   */       case 1: 
/* 34:37 */         y++; break;
/* 35:   */       case 2: 
/* 36:38 */         z--; break;
/* 37:   */       case 3: 
/* 38:39 */         z++; break;
/* 39:   */       case 4: 
/* 40:40 */         x--; break;
/* 41:   */       default: 
/* 42:41 */         x++;
/* 43:   */       }
/* 44:   */     }
/* 45:44 */     if (ist.a == 0) {
/* 46:44 */       return false;
/* 47:   */     }
/* 48:45 */     if (!player.a(x, y, z, side, ist)) {
/* 49:45 */       return false;
/* 50:   */     }
/* 51:47 */     if (y >= world.O() - 1) {
/* 52:47 */       return false;
/* 53:   */     }
/* 54:49 */     if (!world.a(blockID, x, y, z, false, side, player)) {
/* 55:50 */       return false;
/* 56:   */     }
/* 57:52 */     if ((ist.j() == 0) && 
/* 58:53 */       (!world.isBlockSolidOnSide(x, y - 1, z, ForgeDirection.UP))) {
/* 59:54 */       return false;
/* 60:   */     }
/* 61:57 */     amq bl = amq.p[blockID];
/* 62:59 */     if (world.d(x, y, z, blockID, a(ist.j())))
/* 63:   */     {
/* 64:61 */       if (world.a(x, y, z) == blockID)
/* 65:   */       {
/* 66:62 */         BlockExtended bex = (BlockExtended)amq.p[blockID];
/* 67:63 */         bex.onBlockPlacedUseful(world, x, y, z, side, player, ist);
/* 68:   */       }
/* 69:65 */       world.a(x + 0.5F, y + 0.5F, z + 0.5F, bl.cz.e(), (bl.cz.c() + 1.0F) / 2.0F, bl.cz.d() * 0.8F);
/* 70:   */       
/* 71:   */ 
/* 72:   */ 
/* 73:   */ 
/* 74:   */ 
/* 75:71 */       ist.a -= 1;
/* 76:   */     }
/* 77:73 */     return true;
/* 78:   */   }
/* 79:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ItemMachinePanel
 * JD-Core Version:    0.7.0.1
 */