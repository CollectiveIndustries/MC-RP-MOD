/*  1:   */ package com.eloraam.redpower.wiring;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import com.eloraam.redpower.core.CoreLib;
/*  5:   */ import com.eloraam.redpower.core.IRedPowerWiring;
/*  6:   */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  7:   */ import com.eloraam.redpower.core.RedPowerLib;
/*  8:   */ import java.io.IOException;
/*  9:   */ 
/* 10:   */ public class TileInsulatedWire
/* 11:   */   extends TileWiring
/* 12:   */   implements IRedPowerWiring
/* 13:   */ {
/* 14:   */   public float getWireHeight()
/* 15:   */   {
/* 16:15 */     return 0.188F;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int getExtendedID()
/* 20:   */   {
/* 21:19 */     return 2;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean isBlockWeakPoweringTo(int side)
/* 25:   */   {
/* 26:23 */     if (RedPowerLib.isSearching()) {
/* 27:23 */       return false;
/* 28:   */     }
/* 29:25 */     int dir = RedPowerLib.getConDirMask(side ^ 0x1);
/* 30:26 */     dir &= getConnectableMask();
/* 31:27 */     if (dir == 0) {
/* 32:27 */       return false;
/* 33:   */     }
/* 34:29 */     if (RedPowerLib.isBlockRedstone(this.k, this.l, this.m, this.n, side ^ 0x1)) {
/* 35:31 */       return this.PowerState > 15;
/* 36:   */     }
/* 37:32 */     return this.PowerState > 0;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public int getConnectClass(int side)
/* 41:   */   {
/* 42:37 */     return 2 + this.Metadata;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public int scanPoweringStrength(int cons, int ch)
/* 46:   */   {
/* 47:41 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, cons, 0)) {
/* 48:43 */       return 255;
/* 49:   */     }
/* 50:44 */     return 0;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public int getCurrentStrength(int cons, int ch)
/* 54:   */   {
/* 55:48 */     if ((ch != 0) && (ch != this.Metadata + 1)) {
/* 56:48 */       return -1;
/* 57:   */     }
/* 58:49 */     if ((cons & getConnectableMask()) == 0) {
/* 59:49 */       return -1;
/* 60:   */     }
/* 61:50 */     return this.PowerState;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public void updateCurrentStrength()
/* 65:   */   {
/* 66:54 */     this.PowerState = ((short)RedPowerLib.updateBlockCurrentStrength(this.k, this, this.l, this.m, this.n, 16777215, 0x1 | 2 << this.Metadata));
/* 67:   */     
/* 68:   */ 
/* 69:57 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/* 70:   */   }
/* 71:   */   
/* 72:   */   public int getPoweringMask(int ch)
/* 73:   */   {
/* 74:61 */     if (this.PowerState == 0) {
/* 75:61 */       return 0;
/* 76:   */     }
/* 77:62 */     if ((ch != 0) && (ch != this.Metadata + 1)) {
/* 78:62 */       return 0;
/* 79:   */     }
/* 80:63 */     return getConnectableMask();
/* 81:   */   }
/* 82:   */   
/* 83:   */   public void a(bq nbttagcompound)
/* 84:   */   {
/* 85:68 */     super.a(nbttagcompound);
/* 86:69 */     this.PowerState = ((short)(nbttagcompound.c("pwr") & 0xFF));
/* 87:   */   }
/* 88:   */   
/* 89:   */   public void b(bq nbttagcompound)
/* 90:   */   {
/* 91:73 */     super.b(nbttagcompound);
/* 92:74 */     nbttagcompound.a("pwr", (byte)this.PowerState);
/* 93:   */   }
/* 94:   */   
/* 95:   */   protected void readFromPacket(Packet211TileDesc pkt)
/* 96:   */     throws IOException
/* 97:   */   {
/* 98:79 */     super.readFromPacket(pkt);
/* 99:80 */     this.PowerState = ((short)pkt.getByte());
/* :0:   */   }
/* :1:   */   
/* :2:   */   protected void writeToPacket(Packet211TileDesc pkt)
/* :3:   */   {
/* :4:84 */     super.writeToPacket(pkt);
/* :5:85 */     pkt.addByte(this.PowerState);
/* :6:   */   }
/* :7:   */   
/* :8:88 */   public short PowerState = 0;
/* :9:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.TileInsulatedWire
 * JD-Core Version:    0.7.0.1
 */