/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.wiring.TileWiring;
/*  4:   */ 
/*  5:   */ public class TileRibbon
/*  6:   */   extends TileWiring
/*  7:   */ {
/*  8:   */   public int getExtendedID()
/*  9:   */   {
/* 10:18 */     return 12;
/* 11:   */   }
/* 12:   */   
/* 13:   */   public int getConnectClass(int side)
/* 14:   */   {
/* 15:23 */     return 66;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void onBlockNeighborChange(int l)
/* 19:   */   {
/* 20:27 */     super.onBlockNeighborChange(l);
/* 21:28 */     getConnectionMask();getExtConnectionMask();
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.TileRibbon
 * JD-Core Version:    0.7.0.1
 */