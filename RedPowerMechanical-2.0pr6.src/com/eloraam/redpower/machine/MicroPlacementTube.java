/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.CoreLib;
/*  4:   */ import com.eloraam.redpower.core.CoverLib;
/*  5:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  6:   */ import com.eloraam.redpower.core.IMicroPlacement;
/*  7:   */ import com.eloraam.redpower.core.RedPowerLib;
/*  8:   */ import com.eloraam.redpower.core.TileCovered;
/*  9:   */ import com.eloraam.redpower.core.WorldCoord;
/* 10:   */ import java.util.List;
/* 11:   */ import qx;
/* 12:   */ import tj;
/* 13:   */ import ur;
/* 14:   */ import yc;
/* 15:   */ 
/* 16:   */ public class MicroPlacementTube
/* 17:   */   implements IMicroPlacement
/* 18:   */ {
/* 19:   */   private void blockUsed(yc world, WorldCoord wc, ur ist)
/* 20:   */   {
/* 21:14 */     ist.a -= 1;
/* 22:15 */     CoreLib.placeNoise(world, wc.x, wc.y, wc.z, ist.c);
/* 23:16 */     world.i(wc.x, wc.y, wc.z);
/* 24:17 */     RedPowerLib.updateIndirectNeighbors(world, wc.x, wc.y, wc.z, ist.c);
/* 25:   */   }
/* 26:   */   
/* 27:   */   private boolean initialPlace(ur ist, qx player, yc world, WorldCoord wc, int l)
/* 28:   */   {
/* 29:24 */     int md = ist.j() >> 8;
/* 30:25 */     int bid = ist.c;
/* 31:27 */     if (!world.a(bid, wc.x, wc.y, wc.z, false, l, player)) {
/* 32:28 */       return false;
/* 33:   */     }
/* 34:29 */     if (!world.c(wc.x, wc.y, wc.z, bid, md)) {
/* 35:30 */       return true;
/* 36:   */     }
/* 37:31 */     blockUsed(world, wc, ist);
/* 38:32 */     return true;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public boolean onPlaceMicro(ur ist, qx player, yc world, WorldCoord wc, int l)
/* 42:   */   {
/* 43:39 */     wc.step(l);
/* 44:   */     
/* 45:41 */     int bid = world.a(wc.x, wc.y, wc.z);
/* 46:42 */     if (bid != ist.c) {
/* 47:43 */       return initialPlace(ist, player, world, wc, l);
/* 48:   */     }
/* 49:45 */     TileCovered tc = (TileCovered)CoreLib.getTileEntity(world, wc, TileCovered.class);
/* 50:47 */     if (tc == null) {
/* 51:47 */       return false;
/* 52:   */     }
/* 53:49 */     int eid = tc.getExtendedID();
/* 54:50 */     if ((eid == 7) || (eid == 8) || (eid == 9) || (eid == 10) || (eid == 11)) {
/* 55:50 */       return false;
/* 56:   */     }
/* 57:52 */     if (!CoverLib.tryMakeCompatible(world, wc, ist.c, ist.j())) {
/* 58:54 */       return false;
/* 59:   */     }
/* 60:55 */     blockUsed(world, wc, ist);
/* 61:56 */     return true;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public String getMicroName(int hb, int lb)
/* 65:   */   {
/* 66:60 */     if (hb == 7) {
/* 67:60 */       return "tile.rppipe";
/* 68:   */     }
/* 69:61 */     if (hb == 8) {
/* 70:61 */       return "tile.rptube";
/* 71:   */     }
/* 72:62 */     if (hb == 9) {
/* 73:62 */       return "tile.rprstube";
/* 74:   */     }
/* 75:63 */     if (hb == 10) {
/* 76:63 */       return "tile.rprtube";
/* 77:   */     }
/* 78:64 */     if (hb == 11) {
/* 79:64 */       return "tile.rpmtube";
/* 80:   */     }
/* 81:65 */     return null;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public void addCreativeItems(int hb, tj tab, List itemList)
/* 85:   */   {
/* 86:70 */     if (tab != CreativeExtraTabs.tabMachine) {
/* 87:70 */       return;
/* 88:   */     }
/* 89:72 */     if (hb == 7) {
/* 90:73 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 1792));
/* 91:75 */     } else if (hb == 8) {
/* 92:76 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 2048));
/* 93:78 */     } else if (hb == 9) {
/* 94:79 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 2304));
/* 95:81 */     } else if (hb == 10) {
/* 96:82 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 2560));
/* 97:84 */     } else if (hb == 11) {
/* 98:85 */       itemList.add(new ur(CoverLib.blockCoverPlate, 1, 2816));
/* 99:   */     }
/* :0:   */   }
/* :1:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.MicroPlacementTube
 * JD-Core Version:    0.7.0.1
 */