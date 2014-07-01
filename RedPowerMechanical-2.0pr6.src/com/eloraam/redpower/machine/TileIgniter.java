/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import akf;
/*  4:   */ import alm;
/*  5:   */ import amq;
/*  6:   */ import com.eloraam.redpower.core.RedPowerLib;
/*  7:   */ import com.eloraam.redpower.core.WorldCoord;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class TileIgniter
/* 11:   */   extends TileMachine
/* 12:   */ {
/* 13:   */   public int getExtendedID()
/* 14:   */   {
/* 15:19 */     return 12;
/* 16:   */   }
/* 17:   */   
/* 18:   */   private void fireAction()
/* 19:   */   {
/* 20:23 */     WorldCoord wc = new WorldCoord(this);
/* 21:24 */     wc.step(this.Rotation ^ 0x1);
/* 22:25 */     if (this.Active)
/* 23:   */     {
/* 24:26 */       if (this.k.c(wc.x, wc.y, wc.z)) {
/* 25:27 */         this.k.e(wc.x, wc.y, wc.z, amq.au.cm);
/* 26:   */       }
/* 27:   */     }
/* 28:   */     else
/* 29:   */     {
/* 30:31 */       int bid = this.k.a(wc.x, wc.y, wc.z);
/* 31:32 */       if ((bid == amq.au.cm) || (bid == amq.bh.cm)) {
/* 32:33 */         this.k.e(wc.x, wc.y, wc.z, 0);
/* 33:   */       }
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void onBlockNeighborChange(int l)
/* 38:   */   {
/* 39:39 */     if (!RedPowerLib.isPowered(this.k, this.l, this.m, this.n, 16777215, 63))
/* 40:   */     {
/* 41:41 */       if (!this.Powered) {
/* 42:41 */         return;
/* 43:   */       }
/* 44:42 */       this.Powered = false;
/* 45:43 */       if (this.Delay) {
/* 46:43 */         return;
/* 47:   */       }
/* 48:44 */       this.Active = false;
/* 49:45 */       this.Delay = true;
/* 50:   */       
/* 51:47 */       fireAction();
/* 52:   */     }
/* 53:   */     else
/* 54:   */     {
/* 55:49 */       if (this.Powered) {
/* 56:49 */         return;
/* 57:   */       }
/* 58:50 */       this.Powered = true;
/* 59:51 */       if (this.Delay) {
/* 60:51 */         return;
/* 61:   */       }
/* 62:52 */       if (this.Active) {
/* 63:52 */         return;
/* 64:   */       }
/* 65:53 */       this.Active = true;
/* 66:54 */       this.Delay = true;
/* 67:   */       
/* 68:56 */       fireAction();
/* 69:   */     }
/* 70:58 */     scheduleTick(5);
/* 71:59 */     updateBlock();
/* 72:   */   }
/* 73:   */   
/* 74:   */   public boolean isOnFire(int face)
/* 75:   */   {
/* 76:63 */     if (this.Rotation != 0) {
/* 77:63 */       return false;
/* 78:   */     }
/* 79:64 */     return this.Active;
/* 80:   */   }
/* 81:   */   
/* 82:   */   public void onTileTick()
/* 83:   */   {
/* 84:68 */     this.Delay = false;
/* 85:69 */     if (this.Active == this.Powered) {
/* 86:70 */       return;
/* 87:   */     }
/* 88:71 */     this.Active = this.Powered;
/* 89:72 */     fireAction();
/* 90:73 */     updateBlock();
/* 91:   */   }
/* 92:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileIgniter
 * JD-Core Version:    0.7.0.1
 */