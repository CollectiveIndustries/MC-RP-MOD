/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.RedPowerControl;
/*   4:    */ import com.eloraam.redpower.core.BlockExtended;
/*   5:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.ItemExtended;
/*   8:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   9:    */ import com.eloraam.redpower.core.WorldCoord;
/*  10:    */ import qx;
/*  11:    */ import ur;
/*  12:    */ import yc;
/*  13:    */ 
/*  14:    */ public class ItemBackplane
/*  15:    */   extends ItemExtended
/*  16:    */ {
/*  17:    */   public ItemBackplane(int i)
/*  18:    */   {
/*  19: 13 */     super(i);
/*  20:    */   }
/*  21:    */   
/*  22:    */   public boolean a(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/*  23:    */   {
/*  24: 20 */     if (player.ah()) {
/*  25: 20 */       return false;
/*  26:    */     }
/*  27: 21 */     return itemUseShared(ist, player, world, i, j, k, l);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public boolean onItemUseFirst(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/*  31:    */   {
/*  32: 28 */     if (CoreLib.isClient(world)) {
/*  33: 28 */       return false;
/*  34:    */     }
/*  35: 29 */     if (!player.ah()) {
/*  36: 29 */       return false;
/*  37:    */     }
/*  38: 30 */     return itemUseShared(ist, player, world, i, j, k, l);
/*  39:    */   }
/*  40:    */   
/*  41:    */   protected boolean itemUseShared(ur ist, qx player, yc world, int i, int j, int k, int l)
/*  42:    */   {
/*  43: 37 */     int bid = world.a(i, j, k);
/*  44: 38 */     int md = world.h(i, j, k);
/*  45: 39 */     int dmg = ist.j();
/*  46: 41 */     if ((bid == ist.c) && (md == 0) && (dmg != 0))
/*  47:    */     {
/*  48: 42 */       TileBackplane tb = (TileBackplane)CoreLib.getTileEntity(world, i, j, k, TileBackplane.class);
/*  49: 44 */       if (tb == null) {
/*  50: 44 */         return false;
/*  51:    */       }
/*  52: 45 */       int r = tb.Rotation;
/*  53:    */       
/*  54: 47 */       BlockMultipart.removeMultipart(world, i, j, k);
/*  55: 48 */       if (!world.c(i, j, k, bid, dmg)) {
/*  56: 49 */         return false;
/*  57:    */       }
/*  58: 50 */       tb = (TileBackplane)CoreLib.getTileEntity(world, i, j, k, TileBackplane.class);
/*  59: 52 */       if (tb != null) {
/*  60: 52 */         tb.Rotation = r;
/*  61:    */       }
/*  62: 53 */       world.i(i, j, k);
/*  63:    */       
/*  64: 55 */       CoreLib.placeNoise(world, i, j, k, ist.c);
/*  65: 56 */       ist.a -= 1;
/*  66: 57 */       RedPowerLib.updateIndirectNeighbors(world, i, j, k, ist.c);
/*  67:    */       
/*  68: 59 */       return true;
/*  69:    */     }
/*  70: 61 */     if (dmg != 0) {
/*  71: 61 */       return false;
/*  72:    */     }
/*  73: 63 */     WorldCoord wc = new WorldCoord(i, j, k);
/*  74: 64 */     wc.step(l);
/*  75: 66 */     if (!world.a(ist.c, wc.x, wc.y, wc.z, false, 1, player)) {
/*  76: 67 */       return false;
/*  77:    */     }
/*  78: 68 */     if (!RedPowerLib.isSideNormal(world, wc.x, wc.y, wc.z, 0)) {
/*  79: 69 */       return false;
/*  80:    */     }
/*  81: 71 */     int rx = -1;
/*  82: 73 */     for (int rt = 0; rt < 4; rt++)
/*  83:    */     {
/*  84: 74 */       WorldCoord wc2 = wc.copy();
/*  85: 75 */       int dir = CoreLib.rotToSide(rt) ^ 0x1;
/*  86: 76 */       wc2.step(dir);
/*  87: 77 */       TileCPU tcpu = (TileCPU)CoreLib.getTileEntity(world, wc2, TileCPU.class);
/*  88: 79 */       if ((tcpu != null) && (tcpu.Rotation == rt))
/*  89:    */       {
/*  90: 79 */         rx = rt; break;
/*  91:    */       }
/*  92: 80 */       TileBackplane tb = (TileBackplane)CoreLib.getTileEntity(world, wc2, TileBackplane.class);
/*  93: 82 */       if ((tb != null) && (tb.Rotation == rt)) {
/*  94: 83 */         for (int pb = 0; pb < 6; pb++)
/*  95:    */         {
/*  96: 84 */           wc2.step(dir);
/*  97: 85 */           if ((world.a(wc2.x, wc2.y, wc2.z) == RedPowerControl.blockPeripheral.cm) && (world.h(wc2.x, wc2.y, wc2.z) == 1))
/*  98:    */           {
/*  99: 89 */             rx = rt;
/* 100:    */             break label465;
/* 101:    */           }
/* 102:    */         }
/* 103:    */       }
/* 104:    */     }
/* 105:    */     label465:
/* 106: 93 */     if (rx < 0) {
/* 107: 93 */       return false;
/* 108:    */     }
/* 109: 95 */     if (!world.c(wc.x, wc.y, wc.z, ist.c, dmg)) {
/* 110: 96 */       return true;
/* 111:    */     }
/* 112: 97 */     TileBackplane tb = (TileBackplane)CoreLib.getTileEntity(world, wc, TileBackplane.class);
/* 113:    */     
/* 114: 99 */     tb.Rotation = rx;
/* 115:    */     
/* 116:101 */     CoreLib.placeNoise(world, wc.x, wc.y, wc.z, ist.c);
/* 117:102 */     ist.a -= 1;
/* 118:103 */     world.i(wc.x, wc.y, wc.z);
/* 119:104 */     RedPowerLib.updateIndirectNeighbors(world, wc.x, wc.y, wc.z, ist.c);
/* 120:    */     
/* 121:106 */     return true;
/* 122:    */   }
/* 123:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.ItemBackplane
 * JD-Core Version:    0.7.0.1
 */