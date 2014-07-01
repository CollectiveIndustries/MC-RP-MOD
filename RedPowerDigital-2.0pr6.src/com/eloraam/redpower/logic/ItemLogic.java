/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import amu;
/*   5:    */ import com.eloraam.redpower.core.CoreLib;
/*   6:    */ import com.eloraam.redpower.core.ItemExtended;
/*   7:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   8:    */ import qx;
/*   9:    */ import ur;
/*  10:    */ import yc;
/*  11:    */ 
/*  12:    */ public class ItemLogic
/*  13:    */   extends ItemExtended
/*  14:    */ {
/*  15:    */   public ItemLogic(int i)
/*  16:    */   {
/*  17: 14 */     super(i);
/*  18: 15 */     e(0);
/*  19: 16 */     a(true);
/*  20:    */   }
/*  21:    */   
/*  22:    */   public void placeNoise(yc world, int i, int j, int k, int bid)
/*  23:    */   {
/*  24: 20 */     amq block = amq.p[bid];
/*  25: 21 */     world.a(i + 0.5F, j + 0.5F, k + 0.5F, "step.stone", (block.cz.c() + 1.0F) / 2.0F, block.cz.d() * 0.8F);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public boolean a(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/*  29:    */   {
/*  30: 32 */     if (player.ah()) {
/*  31: 32 */       return false;
/*  32:    */     }
/*  33: 33 */     return itemUseShared(ist, player, world, i, j, k, l);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public boolean onItemUseFirst(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/*  37:    */   {
/*  38: 41 */     if (CoreLib.isClient(world)) {
/*  39: 41 */       return false;
/*  40:    */     }
/*  41: 42 */     if (!player.ah()) {
/*  42: 42 */       return false;
/*  43:    */     }
/*  44: 43 */     return itemUseShared(ist, player, world, i, j, k, l);
/*  45:    */   }
/*  46:    */   
/*  47:    */   protected boolean tryPlace(ur ist, qx player, yc world, int i, int j, int k, int l, int down, int rot)
/*  48:    */   {
/*  49: 49 */     int md = ist.j();
/*  50: 50 */     int bid = ist.c;
/*  51: 52 */     if (!world.c(i, j, k, bid, md >> 8)) {
/*  52: 53 */       return false;
/*  53:    */     }
/*  54: 54 */     TileLogic tl = (TileLogic)CoreLib.getTileEntity(world, i, j, k, TileLogic.class);
/*  55: 55 */     if (tl == null) {
/*  56: 55 */       return false;
/*  57:    */     }
/*  58: 56 */     tl.Rotation = (down << 2 | rot);
/*  59: 57 */     tl.initSubType(md & 0xFF);
/*  60: 58 */     return true;
/*  61:    */   }
/*  62:    */   
/*  63:    */   protected boolean itemUseShared(ur ist, qx player, yc world, int i, int j, int k, int l)
/*  64:    */   {
/*  65: 64 */     switch (l)
/*  66:    */     {
/*  67:    */     case 0: 
/*  68: 65 */       j--; break;
/*  69:    */     case 1: 
/*  70: 66 */       j++; break;
/*  71:    */     case 2: 
/*  72: 67 */       k--; break;
/*  73:    */     case 3: 
/*  74: 68 */       k++; break;
/*  75:    */     case 4: 
/*  76: 69 */       i--; break;
/*  77:    */     case 5: 
/*  78: 70 */       i++;
/*  79:    */     }
/*  80: 73 */     int bid = ist.c;
/*  81: 76 */     if (!world.a(bid, i, j, k, false, l, player)) {
/*  82: 77 */       return false;
/*  83:    */     }
/*  84: 78 */     if (!RedPowerLib.isSideNormal(world, i, j, k, l ^ 0x1)) {
/*  85: 79 */       return false;
/*  86:    */     }
/*  87: 83 */     int yaw = (int)Math.floor(player.z / 90.0F + 0.5F);
/*  88: 84 */     int pitch = (int)Math.floor(player.A / 90.0F + 0.5F);
/*  89: 85 */     yaw = yaw + 1 & 0x3;
/*  90: 86 */     int down = l ^ 0x1;
/*  91:    */     int rot;
/*  92: 88 */     switch (down)
/*  93:    */     {
/*  94:    */     case 0: 
/*  95: 89 */       rot = yaw; break;
/*  96:    */     case 1: 
/*  97: 90 */       rot = yaw ^ (yaw & 0x1) << 1; break;
/*  98:    */     case 2: 
/*  99: 91 */       rot = (yaw & 0x1) > 0 ? 0 : pitch > 0 ? 2 : 1 - yaw & 0x3;
/* 100: 92 */       break;
/* 101:    */     case 3: 
/* 102: 93 */       rot = (yaw & 0x1) > 0 ? 0 : pitch > 0 ? 2 : yaw - 1 & 0x3;
/* 103: 94 */       break;
/* 104:    */     case 4: 
/* 105: 95 */       rot = (yaw & 0x1) == 0 ? 0 : pitch > 0 ? 2 : yaw - 2 & 0x3;
/* 106: 96 */       break;
/* 107:    */     case 5: 
/* 108: 97 */       rot = (yaw & 0x1) == 0 ? 0 : pitch > 0 ? 2 : 2 - yaw & 0x3;
/* 109: 98 */       break;
/* 110:    */     default: 
/* 111: 99 */       rot = 0;
/* 112:    */     }
/* 113:102 */     if (!tryPlace(ist, player, world, i, j, k, l, down, rot)) {
/* 114:103 */       return true;
/* 115:    */     }
/* 116:104 */     placeNoise(world, i, j, k, bid);
/* 117:105 */     ist.a -= 1;
/* 118:106 */     world.i(i, j, k);
/* 119:107 */     RedPowerLib.updateIndirectNeighbors(world, i, j, k, bid);
/* 120:108 */     return true;
/* 121:    */   }
/* 122:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.ItemLogic
 * JD-Core Version:    0.7.0.1
 */