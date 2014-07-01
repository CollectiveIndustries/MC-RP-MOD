/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ 
/*  5:   */ public abstract class BluePowerEndpoint
/*  6:   */   extends BluePowerConductor
/*  7:   */ {
/*  8:   */   public double getInvCap()
/*  9:   */   {
/* 10:12 */     return 0.25D;
/* 11:   */   }
/* 12:   */   
/* 13:   */   public int getChargeScaled(int i)
/* 14:   */   {
/* 15:16 */     return Math.min(i, i * this.Charge / 1000);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public int getFlowScaled(int i)
/* 19:   */   {
/* 20:20 */     return Integer.bitCount(this.Flow) * i / 32;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void iterate()
/* 24:   */   {
/* 25:24 */     super.iterate();
/* 26:25 */     this.Charge = ((int)(getVoltage() * 10.0D));
/* 27:26 */     this.Flow = (this.Flow << 1 | (this.Charge >= 600 ? 1 : 0));
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void readFromNBT(bq tag)
/* 31:   */   {
/* 32:30 */     super.readFromNBT(tag);
/* 33:   */     
/* 34:32 */     this.Charge = tag.d("chg");
/* 35:33 */     this.Flow = tag.e("flw");
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void writeToNBT(bq tag)
/* 39:   */   {
/* 40:37 */     super.writeToNBT(tag);
/* 41:   */     
/* 42:39 */     tag.a("chg", (short)this.Charge);
/* 43:40 */     tag.a("flw", this.Flow);
/* 44:   */   }
/* 45:   */   
/* 46:43 */   public int Flow = 0;
/* 47:43 */   public int Charge = 0;
/* 48:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.BluePowerEndpoint
 * JD-Core Version:    0.7.0.1
 */