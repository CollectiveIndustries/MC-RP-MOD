/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import com.eloraam.redpower.RedPowerMachine;
/*  5:   */ import com.eloraam.redpower.core.CoreLib;
/*  6:   */ import qx;
/*  7:   */ import ur;
/*  8:   */ 
/*  9:   */ public class TileRelay
/* 10:   */   extends TileEjectBase
/* 11:   */ {
/* 12:   */   public int getExtendedID()
/* 13:   */   {
/* 14:31 */     return 15;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void onTileTick()
/* 18:   */   {
/* 19:35 */     super.onTileTick();
/* 20:37 */     if (CoreLib.isClient(this.k)) {
/* 21:38 */       return;
/* 22:   */     }
/* 23:39 */     if (this.Active) {
/* 24:39 */       return;
/* 25:   */     }
/* 26:40 */     if (handleExtract())
/* 27:   */     {
/* 28:41 */       this.Active = true;
/* 29:42 */       updateBlock();
/* 30:43 */       scheduleTick(5);
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void g()
/* 35:   */   {
/* 36:48 */     super.g();
/* 37:49 */     if (!isTickScheduled()) {
/* 38:50 */       scheduleTick(10);
/* 39:   */     }
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean onBlockActivated(qx player)
/* 43:   */   {
/* 44:54 */     if (player.ah()) {
/* 45:54 */       return false;
/* 46:   */     }
/* 47:55 */     if (CoreLib.isClient(this.k)) {
/* 48:56 */       return true;
/* 49:   */     }
/* 50:58 */     player.openGui(RedPowerMachine.instance, 13, this.k, this.l, this.m, this.n);
/* 51:   */     
/* 52:60 */     return true;
/* 53:   */   }
/* 54:   */   
/* 55:   */   protected boolean handleExtract()
/* 56:   */   {
/* 57:67 */     for (int n = 0; n < k_(); n++)
/* 58:   */     {
/* 59:68 */       ur ist = a(n);
/* 60:69 */       if ((ist != null) && (ist.a != 0))
/* 61:   */       {
/* 62:71 */         addToBuffer(this.contents[n]);
/* 63:72 */         a(n, null);
/* 64:73 */         drainBuffer();
/* 65:74 */         return true;
/* 66:   */       }
/* 67:   */     }
/* 68:76 */     return false;
/* 69:   */   }
/* 70:   */   
/* 71:   */   public String b()
/* 72:   */   {
/* 73:82 */     return "Relay";
/* 74:   */   }
/* 75:   */   
/* 76:   */   public void a(bq tag)
/* 77:   */   {
/* 78:88 */     super.a(tag);
/* 79:   */   }
/* 80:   */   
/* 81:   */   public void b(bq tag)
/* 82:   */   {
/* 83:92 */     super.b(tag);
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileRelay
 * JD-Core Version:    0.7.0.1
 */