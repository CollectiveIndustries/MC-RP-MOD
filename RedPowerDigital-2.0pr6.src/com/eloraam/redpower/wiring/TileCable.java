/*  1:   */ package com.eloraam.redpower.wiring;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import com.eloraam.redpower.core.CoreLib;
/*  5:   */ import com.eloraam.redpower.core.IRedPowerWiring;
/*  6:   */ import com.eloraam.redpower.core.RedPowerLib;
/*  7:   */ 
/*  8:   */ public class TileCable
/*  9:   */   extends TileWiring
/* 10:   */   implements IRedPowerWiring
/* 11:   */ {
/* 12:   */   public float getWireHeight()
/* 13:   */   {
/* 14:19 */     return 0.25F;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public int getExtendedID()
/* 18:   */   {
/* 19:23 */     return 3;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int getConnectClass(int side)
/* 23:   */   {
/* 24:28 */     return 18 + this.Metadata;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int scanPoweringStrength(int cons, int ch)
/* 28:   */   {
/* 29:32 */     return 0;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getCurrentStrength(int cons, int ch)
/* 33:   */   {
/* 34:36 */     if ((ch < 1) || (ch > 16)) {
/* 35:36 */       return -1;
/* 36:   */     }
/* 37:37 */     if ((cons & getConnectableMask()) == 0) {
/* 38:37 */       return -1;
/* 39:   */     }
/* 40:38 */     return this.PowerState[(ch - 1)];
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void updateCurrentStrength()
/* 44:   */   {
/* 45:42 */     for (int ch = 0; ch < 16; ch++) {
/* 46:43 */       this.PowerState[ch] = ((short)RedPowerLib.updateBlockCurrentStrength(this.k, this, this.l, this.m, this.n, 1073741823, 2 << ch));
/* 47:   */     }
/* 48:47 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/* 49:   */   }
/* 50:   */   
/* 51:   */   public int getPoweringMask(int ch)
/* 52:   */   {
/* 53:51 */     if ((ch < 1) || (ch > 16)) {
/* 54:51 */       return 0;
/* 55:   */     }
/* 56:52 */     if (this.PowerState[(ch - 1)] == 0) {
/* 57:52 */       return 0;
/* 58:   */     }
/* 59:53 */     return getConnectableMask();
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void a(bq nbttagcompound)
/* 63:   */   {
/* 64:58 */     super.a(nbttagcompound);
/* 65:59 */     byte[] pwr = nbttagcompound.j("pwrs");
/* 66:60 */     if (pwr == null) {
/* 67:60 */       return;
/* 68:   */     }
/* 69:61 */     for (int i = 0; i < 16; i++) {
/* 70:61 */       this.PowerState[i] = ((short)(pwr[i] & 0xFF));
/* 71:   */     }
/* 72:   */   }
/* 73:   */   
/* 74:   */   public void b(bq nbttagcompound)
/* 75:   */   {
/* 76:65 */     super.b(nbttagcompound);
/* 77:66 */     byte[] pwr = new byte[16];
/* 78:67 */     for (int i = 0; i < 16; i++) {
/* 79:67 */       pwr[i] = ((byte)this.PowerState[i]);
/* 80:   */     }
/* 81:68 */     nbttagcompound.a("pwrs", pwr);
/* 82:   */   }
/* 83:   */   
/* 84:71 */   public short[] PowerState = new short[16];
/* 85:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.TileCable
 * JD-Core Version:    0.7.0.1
 */