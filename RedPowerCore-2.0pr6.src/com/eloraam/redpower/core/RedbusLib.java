/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import ym;
/*  4:   */ 
/*  5:   */ public class RedbusLib
/*  6:   */ {
/*  7:   */   private static class RedbusPathfinder
/*  8:   */     extends WirePathfinder
/*  9:   */   {
/* 10:   */     public RedbusPathfinder(ym ib, int ad)
/* 11:   */     {
/* 12:11 */       this.iba = ib;
/* 13:12 */       this.addr = ad;
/* 14:13 */       init();
/* 15:   */     }
/* 16:   */     
/* 17:   */     public boolean step(WorldCoord wc)
/* 18:   */     {
/* 19:17 */       IRedbusConnectable irb = (IRedbusConnectable)CoreLib.getTileEntity(this.iba, wc, IRedbusConnectable.class);
/* 20:19 */       if ((irb != null) && 
/* 21:20 */         (irb.rbGetAddr() == this.addr))
/* 22:   */       {
/* 23:21 */         this.result = irb;
/* 24:22 */         return false;
/* 25:   */       }
/* 26:25 */       IWiring iw = (IWiring)CoreLib.getTileEntity(this.iba, wc, IWiring.class);
/* 27:27 */       if (iw == null) {
/* 28:27 */         return true;
/* 29:   */       }
/* 30:28 */       addSearchBlocks(wc, iw.getConnectionMask(), iw.getExtConnectionMask());
/* 31:   */       
/* 32:30 */       return true;
/* 33:   */     }
/* 34:   */     
/* 35:33 */     public IRedbusConnectable result = null;
/* 36:   */     ym iba;
/* 37:   */     int addr;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public static IRedbusConnectable getAddr(ym iba, WorldCoord pos, int addr)
/* 41:   */   {
/* 42:40 */     RedbusPathfinder pf = new RedbusPathfinder(iba, addr);
/* 43:41 */     pf.addSearchBlocks(pos, 16777215, 0);
/* 44:42 */     while (pf.iterate()) {}
/* 45:43 */     return pf.result;
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.RedbusLib
 * JD-Core Version:    0.7.0.1
 */