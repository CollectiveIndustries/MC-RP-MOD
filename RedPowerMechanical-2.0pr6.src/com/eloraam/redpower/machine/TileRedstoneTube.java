/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.core.CoreLib;
/*   5:    */ import com.eloraam.redpower.core.IRedPowerWiring;
/*   6:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   7:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   8:    */ import java.io.IOException;
/*   9:    */ import yc;
/*  10:    */ import ym;
/*  11:    */ 
/*  12:    */ public class TileRedstoneTube
/*  13:    */   extends TileTube
/*  14:    */   implements IRedPowerWiring
/*  15:    */ {
/*  16:    */   public int getConnectableMask()
/*  17:    */   {
/*  18: 15 */     int tr = 63;
/*  19: 16 */     for (int i = 0; i < 6; i++) {
/*  20: 17 */       if (((this.CoverSides & 1 << i) > 0) && 
/*  21: 18 */         (this.Covers[i] >> 8 < 3)) {
/*  22: 19 */         tr &= (1 << i ^ 0xFFFFFFFF);
/*  23:    */       }
/*  24:    */     }
/*  25: 22 */     return tr << 24;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public int getConnectionMask()
/*  29:    */   {
/*  30: 26 */     if (this.ConMask >= 0) {
/*  31: 26 */       return this.ConMask;
/*  32:    */     }
/*  33: 27 */     this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/*  34:    */     
/*  35: 29 */     return this.ConMask;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public int getExtConnectionMask()
/*  39:    */   {
/*  40: 33 */     return 0;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public int getCornerPowerMode()
/*  44:    */   {
/*  45: 37 */     return 0;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void onFrameRefresh(ym iba)
/*  49:    */   {
/*  50: 43 */     if (this.ConMask < 0) {
/*  51: 44 */       this.ConMask = RedPowerLib.getConnections(iba, this, this.l, this.m, this.n);
/*  52:    */     }
/*  53:    */   }
/*  54:    */   
/*  55:    */   public int getConnectClass(int side)
/*  56:    */   {
/*  57: 52 */     return 1;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public int getCurrentStrength(int cons, int ch)
/*  61:    */   {
/*  62: 56 */     if (ch != 0) {
/*  63: 56 */       return -1;
/*  64:    */     }
/*  65: 57 */     if ((cons & getConnectableMask()) == 0) {
/*  66: 57 */       return -1;
/*  67:    */     }
/*  68: 58 */     return this.PowerState;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public int scanPoweringStrength(int cons, int ch)
/*  72:    */   {
/*  73: 62 */     if (ch != 0) {
/*  74: 62 */       return 0;
/*  75:    */     }
/*  76: 63 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, cons, getConnectionMask())) {
/*  77: 65 */       return 255;
/*  78:    */     }
/*  79: 66 */     return 0;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void updateCurrentStrength()
/*  83:    */   {
/*  84: 70 */     this.PowerState = ((short)RedPowerLib.updateBlockCurrentStrength(this.k, this, this.l, this.m, this.n, 1073741823, 1));
/*  85:    */     
/*  86: 72 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/*  87:    */   }
/*  88:    */   
/*  89:    */   public int getPoweringMask(int ch)
/*  90:    */   {
/*  91: 76 */     if ((ch != 0) || (this.PowerState == 0)) {
/*  92: 76 */       return 0;
/*  93:    */     }
/*  94: 77 */     return getConnectableMask();
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void onBlockNeighborChange(int l)
/*  98:    */   {
/*  99: 83 */     super.onBlockNeighborChange(l);
/* 100: 84 */     if (this.ConMask >= 0) {
/* 101: 85 */       this.k.i(this.l, this.m, this.n);
/* 102:    */     }
/* 103: 86 */     this.ConMask = -1;
/* 104: 87 */     RedPowerLib.updateCurrent(this.k, this.l, this.m, this.n);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public int getExtendedID()
/* 108:    */   {
/* 109: 91 */     return 9;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public boolean isBlockWeakPoweringTo(int side)
/* 113:    */   {
/* 114: 95 */     if (RedPowerLib.isSearching()) {
/* 115: 95 */       return false;
/* 116:    */     }
/* 117: 97 */     if ((getConnectionMask() & 16777216 << (side ^ 0x1)) == 0) {
/* 118: 98 */       return false;
/* 119:    */     }
/* 120: 99 */     if (RedPowerLib.isBlockRedstone(this.k, this.l, this.m, this.n, side ^ 0x1)) {
/* 121:101 */       return this.PowerState > 15;
/* 122:    */     }
/* 123:102 */     return this.PowerState > 0;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public boolean tryAddCover(int side, int cover)
/* 127:    */   {
/* 128:108 */     if (!canAddCover(side, cover)) {
/* 129:109 */       return false;
/* 130:    */     }
/* 131:110 */     this.CoverSides |= 1 << side;
/* 132:111 */     this.Covers[side] = ((short)cover);
/* 133:112 */     this.ConMask = -1;
/* 134:113 */     updateBlockChange();
/* 135:114 */     return true;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public int tryRemoveCover(int side)
/* 139:    */   {
/* 140:119 */     if ((this.CoverSides & 1 << side) == 0) {
/* 141:119 */       return -1;
/* 142:    */     }
/* 143:120 */     this.CoverSides &= (1 << side ^ 0xFFFFFFFF);
/* 144:121 */     int tr = this.Covers[side];this.Covers[side] = 0;
/* 145:122 */     this.ConMask = -1;
/* 146:123 */     updateBlockChange();
/* 147:124 */     return tr;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public void a(bq nbttagcompound)
/* 151:    */   {
/* 152:130 */     super.a(nbttagcompound);
/* 153:131 */     this.PowerState = ((short)(nbttagcompound.c("pwr") & 0xFF));
/* 154:    */   }
/* 155:    */   
/* 156:    */   public void b(bq nbttagcompound)
/* 157:    */   {
/* 158:135 */     super.b(nbttagcompound);
/* 159:136 */     nbttagcompound.a("pwr", (byte)this.PowerState);
/* 160:    */   }
/* 161:    */   
/* 162:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 163:    */     throws IOException
/* 164:    */   {
/* 165:141 */     super.readFromPacket(pkt);
/* 166:142 */     this.PowerState = ((short)pkt.getByte());
/* 167:143 */     this.ConMask = -1;
/* 168:    */   }
/* 169:    */   
/* 170:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 171:    */   {
/* 172:147 */     super.writeToPacket(pkt);
/* 173:148 */     pkt.addByte(this.PowerState);
/* 174:    */   }
/* 175:    */   
/* 176:151 */   public short PowerState = 0;
/* 177:152 */   public int ConMask = -1;
/* 178:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileRedstoneTube
 * JD-Core Version:    0.7.0.1
 */