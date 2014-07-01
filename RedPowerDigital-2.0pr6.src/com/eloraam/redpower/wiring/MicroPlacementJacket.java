/*   1:    */ package com.eloraam.redpower.wiring;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.core.CoreLib;
/*   4:    */ import com.eloraam.redpower.core.CoverLib;
/*   5:    */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*   6:    */ import com.eloraam.redpower.core.IMicroPlacement;
/*   7:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   8:    */ import com.eloraam.redpower.core.WorldCoord;
/*   9:    */ import java.util.List;
/*  10:    */ import qx;
/*  11:    */ import tj;
/*  12:    */ import ur;
/*  13:    */ import yc;
/*  14:    */ 
/*  15:    */ public class MicroPlacementJacket
/*  16:    */   implements IMicroPlacement
/*  17:    */ {
/*  18:    */   private void blockUsed(yc world, WorldCoord wc, ur ist)
/*  19:    */   {
/*  20: 14 */     ist.a -= 1;
/*  21: 15 */     CoreLib.placeNoise(world, wc.x, wc.y, wc.z, ist.c);
/*  22: 16 */     world.i(wc.x, wc.y, wc.z);
/*  23: 17 */     RedPowerLib.updateIndirectNeighbors(world, wc.x, wc.y, wc.z, ist.c);
/*  24:    */   }
/*  25:    */   
/*  26:    */   private int getWireMeta(int md)
/*  27:    */   {
/*  28: 22 */     switch (md)
/*  29:    */     {
/*  30:    */     case 64: 
/*  31: 23 */       return 1;
/*  32:    */     case 65: 
/*  33: 24 */       return 3;
/*  34:    */     case 66: 
/*  35: 25 */       return 5;
/*  36:    */     }
/*  37: 27 */     return 0;
/*  38:    */   }
/*  39:    */   
/*  40:    */   private boolean initialPlace(ur ist, qx player, yc world, WorldCoord wc, int l)
/*  41:    */   {
/*  42: 33 */     int md = ist.j() >> 8;
/*  43: 34 */     int bid = ist.c;
/*  44:    */     
/*  45: 36 */     md = getWireMeta(md);
/*  46: 38 */     if (!world.a(bid, wc.x, wc.y, wc.z, false, l, player)) {
/*  47: 39 */       return false;
/*  48:    */     }
/*  49: 40 */     if (!world.c(wc.x, wc.y, wc.z, bid, md)) {
/*  50: 41 */       return true;
/*  51:    */     }
/*  52: 42 */     TileWiring tw = (TileWiring)CoreLib.getTileEntity(world, wc, TileWiring.class);
/*  53: 43 */     if (tw == null) {
/*  54: 43 */       return false;
/*  55:    */     }
/*  56: 44 */     tw.CenterPost = ((short)(ist.j() & 0xFF));
/*  57: 45 */     tw.ConSides |= 0x40;
/*  58: 46 */     blockUsed(world, wc, ist);
/*  59: 47 */     return true;
/*  60:    */   }
/*  61:    */   
/*  62:    */   private boolean tryAddingJacket(yc world, WorldCoord wc, ur ist)
/*  63:    */   {
/*  64: 52 */     TileWiring tw = (TileWiring)CoreLib.getTileEntity(world, wc, TileWiring.class);
/*  65: 53 */     if (tw == null) {
/*  66: 53 */       return false;
/*  67:    */     }
/*  68: 54 */     if ((tw.ConSides & 0x40) > 0) {
/*  69: 54 */       return false;
/*  70:    */     }
/*  71: 55 */     if (!CoverLib.checkPlacement(tw.CoverSides, tw.Covers, tw.ConSides, true)) {
/*  72: 57 */       return false;
/*  73:    */     }
/*  74: 58 */     tw.CenterPost = ((short)(ist.j() & 0xFF));
/*  75: 59 */     tw.ConSides |= 0x40;
/*  76: 60 */     tw.uncache();
/*  77: 61 */     blockUsed(world, wc, ist);
/*  78: 62 */     return true;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public boolean onPlaceMicro(ur ist, qx player, yc world, WorldCoord wc, int l)
/*  82:    */   {
/*  83: 69 */     int hb = ist.j();
/*  84: 70 */     int lb = hb & 0xFF;hb >>= 8;
/*  85:    */     
/*  86: 72 */     hb = getWireMeta(hb);
/*  87: 73 */     int dmg = hb << 8;
/*  88: 75 */     if ((CoverLib.tryMakeCompatible(world, wc, ist.c, dmg)) && (tryAddingJacket(world, wc, ist))) {
/*  89: 77 */       return true;
/*  90:    */     }
/*  91: 79 */     wc.step(l);
/*  92:    */     
/*  93: 81 */     int bid = world.a(wc.x, wc.y, wc.z);
/*  94: 82 */     if (bid != ist.c) {
/*  95: 83 */       return initialPlace(ist, player, world, wc, l);
/*  96:    */     }
/*  97: 84 */     if (!CoverLib.tryMakeCompatible(world, wc, ist.c, dmg)) {
/*  98: 85 */       return false;
/*  99:    */     }
/* 100: 86 */     return tryAddingJacket(world, wc, ist);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public String getMicroName(int hb, int lb)
/* 104:    */   {
/* 105:    */     String nm;
/* 106: 91 */     switch (hb)
/* 107:    */     {
/* 108:    */     case 64: 
/* 109: 93 */       nm = CoverLib.getName(lb);
/* 110: 94 */       if (nm == null) {
/* 111: 94 */         return null;
/* 112:    */       }
/* 113: 95 */       if (CoverLib.isTransparent(lb)) {
/* 114: 95 */         return null;
/* 115:    */       }
/* 116: 96 */       return "tile.rparmwire." + nm;
/* 117:    */     case 65: 
/* 118: 98 */       nm = CoverLib.getName(lb);
/* 119: 99 */       if (nm == null) {
/* 120: 99 */         return null;
/* 121:    */       }
/* 122:100 */       if (CoverLib.isTransparent(lb)) {
/* 123:100 */         return null;
/* 124:    */       }
/* 125:101 */       return "tile.rparmcable." + nm;
/* 126:    */     case 66: 
/* 127:103 */       nm = CoverLib.getName(lb);
/* 128:104 */       if (nm == null) {
/* 129:104 */         return null;
/* 130:    */       }
/* 131:105 */       if (CoverLib.isTransparent(lb)) {
/* 132:105 */         return null;
/* 133:    */       }
/* 134:106 */       return "tile.rparmbwire." + nm;
/* 135:    */     }
/* 136:108 */     return null;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void addCreativeItems(int hb, tj tab, List itemList)
/* 140:    */   {
/* 141:112 */     if (tab != CreativeExtraTabs.tabWires) {
/* 142:112 */       return;
/* 143:    */     }
/* 144:113 */     switch (hb)
/* 145:    */     {
/* 146:    */     case 64: 
/* 147:115 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 16386));
/* 148:    */       
/* 149:117 */       break;
/* 150:    */     case 65: 
/* 151:119 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 16666));
/* 152:    */       
/* 153:121 */       break;
/* 154:    */     case 66: 
/* 155:123 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 16902));
/* 156:    */     }
/* 157:    */   }
/* 158:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.MicroPlacementJacket
 * JD-Core Version:    0.7.0.1
 */