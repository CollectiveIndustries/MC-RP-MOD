/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*  4:   */ import com.eloraam.redpower.core.IRedbusConnectable;
/*  5:   */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.util.List;
/*  8:   */ import la;
/*  9:   */ import qx;
/* 10:   */ import rq;
/* 11:   */ import rw;
/* 12:   */ import ur;
/* 13:   */ 
/* 14:   */ public class ContainerBusId
/* 15:   */   extends rq
/* 16:   */   implements IHandleGuiEvent
/* 17:   */ {
/* 18:   */   private IRedbusConnectable rbConn;
/* 19:   */   
/* 20:   */   public ContainerBusId(la inv, IRedbusConnectable irc)
/* 21:   */   {
/* 22:16 */     this.rbConn = irc;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public boolean a(qx player)
/* 26:   */   {
/* 27:20 */     return true;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public ur b(qx player, int i)
/* 31:   */   {
/* 32:26 */     return null;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void b()
/* 36:   */   {
/* 37:30 */     super.b();
/* 38:32 */     for (int i = 0; i < this.e.size(); i++)
/* 39:   */     {
/* 40:33 */       rw ic = (rw)this.e.get(i);
/* 41:35 */       if (this.rbConn.rbGetAddr() != this.addr) {
/* 42:36 */         ic.a(this, 0, this.rbConn.rbGetAddr());
/* 43:   */       }
/* 44:   */     }
/* 45:40 */     this.addr = this.rbConn.rbGetAddr();
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void b(int i, int j)
/* 49:   */   {
/* 50:44 */     switch (i)
/* 51:   */     {
/* 52:   */     case 0: 
/* 53:45 */       this.rbConn.rbSetAddr(j);return;
/* 54:   */     }
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 58:   */   {
/* 59:   */     try
/* 60:   */     {
/* 61:51 */       if (packet.eventId != 1) {
/* 62:51 */         return;
/* 63:   */       }
/* 64:52 */       this.rbConn.rbSetAddr(packet.getByte());
/* 65:   */     }
/* 66:   */     catch (IOException e) {}
/* 67:   */   }
/* 68:   */   
/* 69:57 */   int addr = 0;
/* 70:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ContainerBusId
 * JD-Core Version:    0.7.0.1
 */