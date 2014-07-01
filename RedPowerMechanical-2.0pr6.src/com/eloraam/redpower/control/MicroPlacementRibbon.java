/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.CoverLib;
/*  4:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  5:   */ import com.eloraam.redpower.wiring.MicroPlacementWire;
/*  6:   */ import java.util.List;
/*  7:   */ import tj;
/*  8:   */ import ur;
/*  9:   */ 
/* 10:   */ public class MicroPlacementRibbon
/* 11:   */   extends MicroPlacementWire
/* 12:   */ {
/* 13:   */   public String getMicroName(int hb, int lb)
/* 14:   */   {
/* 15:11 */     if ((hb != 12) && (lb != 0)) {
/* 16:11 */       return null;
/* 17:   */     }
/* 18:12 */     return "tile.ribbon";
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void addCreativeItems(int hb, tj tab, List itemList)
/* 22:   */   {
/* 23:18 */     if (tab != CreativeExtraTabs.tabWires) {
/* 24:18 */       return;
/* 25:   */     }
/* 26:19 */     if (hb == 12) {
/* 27:20 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 3072));
/* 28:   */     }
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.MicroPlacementRibbon
 * JD-Core Version:    0.7.0.1
 */