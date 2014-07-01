/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ public abstract interface IRedbusConnectable
/*  4:   */   extends IConnectable
/*  5:   */ {
/*  6:   */   public abstract int rbGetAddr();
/*  7:   */   
/*  8:   */   public abstract void rbSetAddr(int paramInt);
/*  9:   */   
/* 10:   */   public abstract int rbRead(int paramInt);
/* 11:   */   
/* 12:   */   public abstract void rbWrite(int paramInt1, int paramInt2);
/* 13:   */   
/* 14:   */   public static class Dummy
/* 15:   */     implements IRedbusConnectable
/* 16:   */   {
/* 17:   */     private int address;
/* 18:   */     
/* 19:   */     public int getConnectableMask()
/* 20:   */     {
/* 21: 5 */       return 0;
/* 22:   */     }
/* 23:   */     
/* 24:   */     public int getConnectClass(int side)
/* 25:   */     {
/* 26: 6 */       return 0;
/* 27:   */     }
/* 28:   */     
/* 29:   */     public int getCornerPowerMode()
/* 30:   */     {
/* 31: 7 */       return 0;
/* 32:   */     }
/* 33:   */     
/* 34:   */     public int rbGetAddr()
/* 35:   */     {
/* 36: 9 */       return this.address;
/* 37:   */     }
/* 38:   */     
/* 39:   */     public void rbSetAddr(int addr)
/* 40:   */     {
/* 41:10 */       this.address = addr;
/* 42:   */     }
/* 43:   */     
/* 44:   */     public int rbRead(int reg)
/* 45:   */     {
/* 46:11 */       return 0;
/* 47:   */     }
/* 48:   */     
/* 49:   */     public void rbWrite(int reg, int dat) {}
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.IRedbusConnectable
 * JD-Core Version:    0.7.0.1
 */