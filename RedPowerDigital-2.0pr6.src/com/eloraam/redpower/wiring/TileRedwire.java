/*   1:    */ package com.eloraam.redpower.wiring;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.core.CoreLib;
/*   5:    */ import com.eloraam.redpower.core.IRedPowerWiring;
/*   6:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   7:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   8:    */ import java.io.IOException;
/*   9:    */ 
/*  10:    */ public class TileRedwire
/*  11:    */   extends TileWiring
/*  12:    */   implements IRedPowerWiring
/*  13:    */ {
/*  14:    */   public int getExtendedID()
/*  15:    */   {
/*  16: 20 */     return 1;
/*  17:    */   }
/*  18:    */   
/*  19:    */   public boolean isBlockStrongPoweringTo(int side)
/*  20:    */   {
/*  21: 24 */     if (RedPowerLib.isSearching()) {
/*  22: 24 */       return false;
/*  23:    */     }
/*  24: 26 */     int dir = 15 << ((side ^ 0x1) << 2);
/*  25: 27 */     dir &= getConnectableMask();
/*  26: 28 */     if (dir == 0) {
/*  27: 28 */       return false;
/*  28:    */     }
/*  29: 29 */     return this.PowerState > 0;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public boolean isBlockWeakPoweringTo(int side)
/*  33:    */   {
/*  34: 33 */     if (RedPowerLib.isSearching()) {
/*  35: 33 */       return false;
/*  36:    */     }
/*  37: 35 */     int dir = 15 << ((side ^ 0x1) << 2);
/*  38: 36 */     dir |= RedPowerLib.getConDirMask(side ^ 0x1);
/*  39: 37 */     dir &= getConnectableMask();
/*  40: 39 */     if (dir == 0) {
/*  41: 39 */       return false;
/*  42:    */     }
/*  43: 40 */     if (RedPowerLib.isBlockRedstone(this.k, this.l, this.m, this.n, side ^ 0x1)) {
/*  44: 42 */       return this.PowerState > 15;
/*  45:    */     }
/*  46: 43 */     return this.PowerState > 0;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public int getConnectClass(int side)
/*  50:    */   {
/*  51: 48 */     return 1;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public int getConnectableMask()
/*  55:    */   {
/*  56: 52 */     if (this.ConaMask >= 0) {
/*  57: 52 */       return this.ConaMask;
/*  58:    */     }
/*  59: 54 */     int tr = super.getConnectableMask();
/*  60: 56 */     if ((this.ConSides & 0x1) > 0) {
/*  61: 56 */       tr |= 0x1000000;
/*  62:    */     }
/*  63: 57 */     if ((this.ConSides & 0x2) > 0) {
/*  64: 57 */       tr |= 0x2000000;
/*  65:    */     }
/*  66: 58 */     if ((this.ConSides & 0x4) > 0) {
/*  67: 58 */       tr |= 0x4000000;
/*  68:    */     }
/*  69: 59 */     if ((this.ConSides & 0x8) > 0) {
/*  70: 59 */       tr |= 0x8000000;
/*  71:    */     }
/*  72: 60 */     if ((this.ConSides & 0x10) > 0) {
/*  73: 60 */       tr |= 0x10000000;
/*  74:    */     }
/*  75: 61 */     if ((this.ConSides & 0x20) > 0) {
/*  76: 61 */       tr |= 0x20000000;
/*  77:    */     }
/*  78: 63 */     this.ConaMask = tr;
/*  79: 64 */     return tr;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public int getCurrentStrength(int cons, int ch)
/*  83:    */   {
/*  84: 68 */     if (ch != 0) {
/*  85: 68 */       return -1;
/*  86:    */     }
/*  87: 69 */     if ((cons & getConnectableMask()) == 0) {
/*  88: 69 */       return -1;
/*  89:    */     }
/*  90: 70 */     return this.PowerState;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public int scanPoweringStrength(int cons, int ch)
/*  94:    */   {
/*  95: 74 */     if (ch != 0) {
/*  96: 74 */       return 0;
/*  97:    */     }
/*  98: 75 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, cons, this.ConSides)) {
/*  99: 77 */       return 255;
/* 100:    */     }
/* 101: 78 */     return 0;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void updateCurrentStrength()
/* 105:    */   {
/* 106: 82 */     this.PowerState = ((short)RedPowerLib.updateBlockCurrentStrength(this.k, this, this.l, this.m, this.n, 1073741823, 1));
/* 107:    */     
/* 108: 84 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/* 109:    */   }
/* 110:    */   
/* 111:    */   public int getPoweringMask(int ch)
/* 112:    */   {
/* 113: 88 */     if ((ch != 0) || (this.PowerState == 0)) {
/* 114: 88 */       return 0;
/* 115:    */     }
/* 116: 89 */     return getConnectableMask();
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void a(bq nbttagcompound)
/* 120:    */   {
/* 121: 94 */     super.a(nbttagcompound);
/* 122: 95 */     this.PowerState = ((short)(nbttagcompound.c("pwr") & 0xFF));
/* 123:    */   }
/* 124:    */   
/* 125:    */   public void b(bq nbttagcompound)
/* 126:    */   {
/* 127: 99 */     super.b(nbttagcompound);
/* 128:100 */     nbttagcompound.a("pwr", (byte)this.PowerState);
/* 129:    */   }
/* 130:    */   
/* 131:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 132:    */     throws IOException
/* 133:    */   {
/* 134:105 */     super.readFromPacket(pkt);
/* 135:106 */     this.PowerState = ((short)pkt.getByte());
/* 136:    */   }
/* 137:    */   
/* 138:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 139:    */   {
/* 140:110 */     super.writeToPacket(pkt);
/* 141:111 */     pkt.addByte(this.PowerState);
/* 142:    */   }
/* 143:    */   
/* 144:114 */   public short PowerState = 0;
/* 145:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.TileRedwire
 * JD-Core Version:    0.7.0.1
 */