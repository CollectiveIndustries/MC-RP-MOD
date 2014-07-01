/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import com.eloraam.redpower.core.RedPowerLib;
/*  5:   */ import ur;
/*  6:   */ 
/*  7:   */ public class TileEject
/*  8:   */   extends TileEjectBase
/*  9:   */ {
/* 10:   */   public int getExtendedID()
/* 11:   */   {
/* 12:29 */     return 14;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void onBlockNeighborChange(int l)
/* 16:   */   {
/* 17:33 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, 16777215, 63))
/* 18:   */     {
/* 19:35 */       if (this.Powered) {
/* 20:35 */         return;
/* 21:   */       }
/* 22:36 */       this.Powered = true;
/* 23:37 */       dirtyBlock();
/* 24:38 */       if (this.Active) {
/* 25:38 */         return;
/* 26:   */       }
/* 27:39 */       this.Active = true;
/* 28:   */     }
/* 29:   */     else
/* 30:   */     {
/* 31:41 */       if ((this.Active) && (!isTickScheduled())) {
/* 32:42 */         scheduleTick(5);
/* 33:   */       }
/* 34:43 */       this.Powered = false;
/* 35:44 */       dirtyBlock();
/* 36:45 */       return;
/* 37:   */     }
/* 38:47 */     if (handleExtract()) {
/* 39:47 */       updateBlock();
/* 40:   */     }
/* 41:   */   }
/* 42:   */   
/* 43:   */   protected boolean handleExtract()
/* 44:   */   {
/* 45:53 */     for (int n = 0; n < k_(); n++)
/* 46:   */     {
/* 47:54 */       ur ist = a(n);
/* 48:55 */       if ((ist != null) && (ist.a != 0))
/* 49:   */       {
/* 50:57 */         addToBuffer(a(n, 1));
/* 51:58 */         drainBuffer();
/* 52:59 */         return true;
/* 53:   */       }
/* 54:   */     }
/* 55:61 */     return false;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void a(bq tag)
/* 59:   */   {
/* 60:67 */     super.a(tag);
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void b(bq tag)
/* 64:   */   {
/* 65:71 */     super.b(tag);
/* 66:   */   }
/* 67:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileEject
 * JD-Core Version:    0.7.0.1
 */