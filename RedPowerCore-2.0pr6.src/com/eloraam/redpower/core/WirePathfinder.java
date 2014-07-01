/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import java.util.HashSet;
/*  4:   */ import java.util.LinkedList;
/*  5:   */ 
/*  6:   */ public abstract class WirePathfinder
/*  7:   */ {
/*  8:   */   HashSet scanmap;
/*  9:   */   LinkedList scanpos;
/* 10:   */   
/* 11:   */   public void init()
/* 12:   */   {
/* 13:10 */     this.scanmap = new HashSet();
/* 14:11 */     this.scanpos = new LinkedList();
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void addSearchBlock(WorldCoord wc)
/* 18:   */   {
/* 19:15 */     if (this.scanmap.contains(wc)) {
/* 20:15 */       return;
/* 21:   */     }
/* 22:16 */     this.scanmap.add(wc);
/* 23:17 */     this.scanpos.addLast(wc);
/* 24:   */   }
/* 25:   */   
/* 26:   */   private void addIndBl(WorldCoord wc, int d1, int d2)
/* 27:   */   {
/* 28:22 */     wc = wc.coordStep(d1);
/* 29:   */     int d3;
/* 30:23 */     switch (d1)
/* 31:   */     {
/* 32:   */     case 0: 
/* 33:24 */       d3 = d2 + 2; break;
/* 34:   */     case 1: 
/* 35:25 */       d3 = d2 + 2; break;
/* 36:   */     case 2: 
/* 37:26 */       d3 = d2 + (d2 & 0x2); break;
/* 38:   */     case 3: 
/* 39:27 */       d3 = d2 + (d2 & 0x2); break;
/* 40:   */     case 4: 
/* 41:28 */       d3 = d2; break;
/* 42:   */     default: 
/* 43:29 */       d3 = d2;
/* 44:   */     }
/* 45:31 */     wc.step(d3);
/* 46:32 */     addSearchBlock(wc);
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void addSearchBlocks(WorldCoord wc, int cons, int indcon)
/* 50:   */   {
/* 51:38 */     for (int a = 0; a < 6; a++) {
/* 52:39 */       if ((cons & RedPowerLib.getConDirMask(a)) > 0) {
/* 53:40 */         addSearchBlock(wc.coordStep(a));
/* 54:   */       }
/* 55:   */     }
/* 56:42 */     for (a = 0; a < 6; a++) {
/* 57:42 */       for (int b = 0; b < 4; b++) {
/* 58:43 */         if ((indcon & 1 << a * 4 + b) > 0) {
/* 59:44 */           addIndBl(wc, a, b);
/* 60:   */         }
/* 61:   */       }
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public boolean step(WorldCoord coord)
/* 66:   */   {
/* 67:49 */     return false;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public boolean iterate()
/* 71:   */   {
/* 72:52 */     if (this.scanpos.size() == 0) {
/* 73:52 */       return false;
/* 74:   */     }
/* 75:53 */     WorldCoord wc = (WorldCoord)this.scanpos.removeFirst();
/* 76:54 */     return step(wc);
/* 77:   */   }
/* 78:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.WirePathfinder
 * JD-Core Version:    0.7.0.1
 */