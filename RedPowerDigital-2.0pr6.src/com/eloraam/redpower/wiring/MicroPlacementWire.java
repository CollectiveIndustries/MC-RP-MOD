/*   1:    */ 
/*   2:    */ 
/*   3:    */ com.eloraam.redpower.core.CoreLib
/*   4:    */ com.eloraam.redpower.core.CoverLib
/*   5:    */ com.eloraam.redpower.core.CreativeExtraTabs
/*   6:    */ com.eloraam.redpower.core.IMicroPlacement
/*   7:    */ com.eloraam.redpower.core.RedPowerLib
/*   8:    */ com.eloraam.redpower.core.TileCovered
/*   9:    */ com.eloraam.redpower.core.WorldCoord
/*  10:    */ java.util.List
/*  11:    */ qx
/*  12:    */ tj
/*  13:    */ ur
/*  14:    */ yc
/*  15:    */ 
/*  16:    */ MicroPlacementWire
/*  17:    */   
/*  18:    */ 
/*  19:    */   blockUsed, , 
/*  20:    */   
/*  21: 15 */     a -= 1;
/*  22: 16 */     CoreLib.placeNoise(world, wc.x, wc.y, wc.z, ist.c);
/*  23: 17 */     world.i(wc.x, wc.y, wc.z);
/*  24: 18 */     RedPowerLib.updateIndirectNeighbors(world, wc.x, wc.y, wc.z, ist.c);
/*  25:    */   }
/*  26:    */   
/*  27:    */   private boolean initialPlace(ur ist, qx player, yc world, WorldCoord wc, int l)
/*  28:    */   {
/*  29: 25 */     int md = ist.j() >> 8;
/*  30: 26 */     int bid = ist.c;
/*  31: 28 */     if (!world.a(bid, wc.x, wc.y, wc.z, false, l, player)) {
/*  32: 29 */       return false;
/*  33:    */     }
/*  34: 30 */     if (!RedPowerLib.canSupportWire(world, wc.x, wc.y, wc.z, l ^ 0x1)) {
/*  35: 31 */       return false;
/*  36:    */     }
/*  37: 32 */     if (!world.c(wc.x, wc.y, wc.z, bid, md)) {
/*  38: 33 */       return true;
/*  39:    */     }
/*  40: 35 */     TileWiring tw = (TileWiring)CoreLib.getTileEntity(world, wc, TileWiring.class);
/*  41: 36 */     if (tw == null) {
/*  42: 36 */       return false;
/*  43:    */     }
/*  44: 37 */     tw.ConSides = (1 << (l ^ 0x1));
/*  45: 38 */     tw.Metadata = (ist.j() & 0xFF);
/*  46:    */     
/*  47: 40 */     blockUsed(world, wc, ist);
/*  48: 41 */     return true;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public boolean onPlaceMicro(ur ist, qx player, yc world, WorldCoord wc, int l)
/*  52:    */   {
/*  53: 48 */     wc.step(l);
/*  54:    */     
/*  55: 50 */     int bid = world.a(wc.x, wc.y, wc.z);
/*  56: 51 */     if (bid != ist.c) {
/*  57: 52 */       return initialPlace(ist, player, world, wc, l);
/*  58:    */     }
/*  59: 54 */     TileCovered tc = (TileCovered)CoreLib.getTileEntity(world, wc, TileCovered.class);
/*  60: 56 */     if (tc == null) {
/*  61: 56 */       return false;
/*  62:    */     }
/*  63: 58 */     int d = 1 << (l ^ 0x1);
/*  64: 59 */     if ((tc.CoverSides & d) > 0) {
/*  65: 59 */       return false;
/*  66:    */     }
/*  67: 62 */     int hb = ist.j();
/*  68: 63 */     int lb = hb & 0xFF;hb >>= 8;
/*  69: 65 */     if (!CoverLib.tryMakeCompatible(world, wc, ist.c, ist.j())) {
/*  70: 67 */       return false;
/*  71:    */     }
/*  72: 68 */     TileWiring tw = (TileWiring)CoreLib.getTileEntity(world, wc, TileWiring.class);
/*  73: 69 */     if (tw == null) {
/*  74: 69 */       return false;
/*  75:    */     }
/*  76: 71 */     if (!RedPowerLib.canSupportWire(world, wc.x, wc.y, wc.z, l ^ 0x1)) {
/*  77: 72 */       return false;
/*  78:    */     }
/*  79: 74 */     if (((tw.ConSides | tw.CoverSides) & d) > 0) {
/*  80: 74 */       return false;
/*  81:    */     }
/*  82: 75 */     d |= tw.ConSides;
/*  83: 76 */     int t = d & 0x3F;
/*  84: 77 */     if ((t == 3) || (t == 12) || (t == 48)) {
/*  85: 77 */       return false;
/*  86:    */     }
/*  87: 79 */     if (!CoverLib.checkPlacement(tw.CoverSides, tw.Covers, t, (tw.ConSides & 0x40) > 0)) {
/*  88: 81 */       return false;
/*  89:    */     }
/*  90: 83 */     tw.ConSides = d;
/*  91: 84 */     tw.uncache();
/*  92:    */     
/*  93: 86 */     blockUsed(world, wc, ist);
/*  94: 87 */     return true;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public String getMicroName(int hb, int lb)
/*  98:    */   {
/*  99: 91 */     switch (hb)
/* 100:    */     {
/* 101:    */     case 1: 
/* 102: 93 */       if (lb == 0) {
/* 103: 93 */         return "tile.rpwire";
/* 104:    */       }
/* 105:    */       break;
/* 106:    */     case 2: 
/* 107: 96 */       return "tile.rpinsulated." + CoreLib.rawColorNames[lb];
/* 108:    */     case 3: 
/* 109: 99 */       if (lb == 0) {
/* 110: 99 */         return "tile.rpcable";
/* 111:    */       }
/* 112:100 */       return "tile.rpcable." + CoreLib.rawColorNames[(lb - 1)];
/* 113:    */     case 5: 
/* 114:104 */       if (lb == 0) {
/* 115:104 */         return "tile.bluewire";
/* 116:    */       }
/* 117:105 */       if (lb == 1) {
/* 118:105 */         return "tile.bluewire10";
/* 119:    */       }
/* 120:106 */       if (lb == 2) {
/* 121:106 */         return "tile.bluewire1M";
/* 122:    */       }
/* 123:    */       break;
/* 124:    */     }
/* 125:109 */     return null;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void addCreativeItems(int hb, tj tab, List itemList)
/* 129:    */   {
/* 130:113 */     if (tab != CreativeExtraTabs.tabWires) {
/* 131:113 */       return;
/* 132:    */     }
/* 133:114 */     switch (hb)
/* 134:    */     {
/* 135:    */     case 1: 
/* 136:116 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 256));
/* 137:    */       
/* 138:118 */       break;
/* 139:    */     case 2: 
/* 140:120 */       for (int i = 0; i < 16; i++) {
/* 141:121 */         itemList.add(new ur(CoverLib.blockCoverPlate, 1, 512 + i));
/* 142:    */       }
/* 143:124 */       break;
/* 144:    */     case 3: 
/* 145:126 */       for (int i = 0; i < 17; i++) {
/* 146:127 */         itemList.add(new ur(CoverLib.blockCoverPlate, 1, 768 + i));
/* 147:    */       }
/* 148:130 */       break;
/* 149:    */     case 5: 
/* 150:132 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 1280));
/* 151:    */       
/* 152:134 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 1281));
/* 153:    */     }
/* 154:    */   }
/* 155:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.MicroPlacementWire
 * JD-Core Version:    0.7.0.1
 */