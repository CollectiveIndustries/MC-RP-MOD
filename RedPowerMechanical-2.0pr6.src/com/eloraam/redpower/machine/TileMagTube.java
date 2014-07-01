/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.BlockMultipart;
/*  4:   */ 
/*  5:   */ public class TileMagTube
/*  6:   */   extends TileTube
/*  7:   */ {
/*  8:   */   public int getTubeConnectableSides()
/*  9:   */   {
/* 10:16 */     int tr = 63;
/* 11:17 */     for (int i = 0; i < 6; i++) {
/* 12:18 */       if (((this.CoverSides & 1 << i) > 0) && 
/* 13:19 */         (this.Covers[i] >> 8 < 3)) {
/* 14:20 */         tr &= (1 << i ^ 0xFFFFFFFF);
/* 15:   */       }
/* 16:   */     }
/* 17:23 */     return tr;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public int getSpeed()
/* 21:   */   {
/* 22:26 */     return 128;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int getTubeConClass()
/* 26:   */   {
/* 27:29 */     return 18 + this.paintColor;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void setPartBounds(BlockMultipart bl, int part)
/* 31:   */   {
/* 32:35 */     if (part == 29) {
/* 33:36 */       bl.a(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
/* 34:   */     } else {
/* 35:38 */       super.setPartBounds(bl, part);
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public int getExtendedID()
/* 40:   */   {
/* 41:45 */     return 11;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileMagTube
 * JD-Core Version:    0.7.0.1
 */