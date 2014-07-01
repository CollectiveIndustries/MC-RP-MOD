/*  1:   */ package com.eloraam.redpower.compat;
/*  2:   */ 
/*  3:   */ import ajr;
/*  4:   */ import amm;
/*  5:   */ import amq;
/*  6:   */ import amu;
/*  7:   */ import com.eloraam.redpower.core.BlockExtended;
/*  8:   */ import com.eloraam.redpower.core.ItemExtended;
/*  9:   */ import qx;
/* 10:   */ import ur;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class ItemMachineCompat
/* 14:   */   extends ItemExtended
/* 15:   */ {
/* 16:   */   public ItemMachineCompat(int i)
/* 17:   */   {
/* 18:16 */     super(i);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean a(ur ist, qx player, yc world, int x, int y, int z, int side, float xp, float yp, float zp)
/* 22:   */   {
/* 23:25 */     int bid = world.a(x, y, z);
/* 24:26 */     int blockID = g();
/* 25:28 */     if (bid == amq.aV.cm) {
/* 26:29 */       side = 1;
/* 27:30 */     } else if ((bid != amq.bx.cm) && (bid != amq.aa.cm) && (bid != amq.ab.cm)) {
/* 28:33 */       switch (side)
/* 29:   */       {
/* 30:   */       case 0: 
/* 31:34 */         y--; break;
/* 32:   */       case 1: 
/* 33:35 */         y++; break;
/* 34:   */       case 2: 
/* 35:36 */         z--; break;
/* 36:   */       case 3: 
/* 37:37 */         z++; break;
/* 38:   */       case 4: 
/* 39:38 */         x--; break;
/* 40:   */       default: 
/* 41:39 */         x++;
/* 42:   */       }
/* 43:   */     }
/* 44:42 */     if (ist.a == 0) {
/* 45:42 */       return false;
/* 46:   */     }
/* 47:43 */     if (!player.a(x, y, z, side, ist)) {
/* 48:43 */       return false;
/* 49:   */     }
/* 50:45 */     if (y >= world.O() - 1) {
/* 51:45 */       return false;
/* 52:   */     }
/* 53:47 */     if (!world.a(blockID, x, y, z, false, side, player)) {
/* 54:48 */       return false;
/* 55:   */     }
/* 56:50 */     amq bl = amq.p[blockID];
/* 57:52 */     if (world.d(x, y, z, blockID, a(ist.j())))
/* 58:   */     {
/* 59:54 */       if (world.a(x, y, z) == blockID)
/* 60:   */       {
/* 61:55 */         BlockExtended bex = (BlockExtended)amq.p[blockID];
/* 62:56 */         bex.onBlockPlacedUseful(world, x, y, z, side, player, ist);
/* 63:   */       }
/* 64:58 */       world.a(x + 0.5F, y + 0.5F, z + 0.5F, bl.cz.e(), (bl.cz.c() + 1.0F) / 2.0F, bl.cz.d() * 0.8F);
/* 65:   */       
/* 66:   */ 
/* 67:   */ 
/* 68:   */ 
/* 69:   */ 
/* 70:64 */       ist.a -= 1;
/* 71:   */     }
/* 72:66 */     return true;
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.compat.ItemMachineCompat
 * JD-Core Version:    0.7.0.1
 */