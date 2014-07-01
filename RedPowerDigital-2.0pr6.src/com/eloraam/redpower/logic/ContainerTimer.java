/*  1:   */ package com.eloraam.redpower.logic;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.CoreProxy;
/*  4:   */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*  5:   */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.util.List;
/*  8:   */ import la;
/*  9:   */ import qx;
/* 10:   */ import rq;
/* 11:   */ import rw;
/* 12:   */ import ur;
/* 13:   */ 
/* 14:   */ public class ContainerTimer
/* 15:   */   extends rq
/* 16:   */   implements IHandleGuiEvent
/* 17:   */ {
/* 18:   */   public ContainerTimer(la inv, TileLogicPointer tf)
/* 19:   */   {
/* 20:17 */     this.tileLogic = tf;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean a(qx player)
/* 24:   */   {
/* 25:21 */     return this.tileLogic.isUseableByPlayer(player);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public ur b(qx player, int i)
/* 29:   */   {
/* 30:26 */     return null;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void b()
/* 34:   */   {
/* 35:31 */     super.b();
/* 36:   */     
/* 37:33 */     long iv = this.tileLogic.getInterval();
/* 38:35 */     for (int i = 0; i < this.e.size(); i++)
/* 39:   */     {
/* 40:36 */       rw ic = (rw)this.e.get(i);
/* 41:38 */       if (iv != this.interval)
/* 42:   */       {
/* 43:39 */         Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 44:40 */         pkt.eventId = 1;
/* 45:41 */         pkt.windowId = this.d;
/* 46:42 */         pkt.addUVLC(iv);
/* 47:43 */         pkt.encode();
/* 48:44 */         CoreProxy.sendPacketToCrafting(ic, pkt);
/* 49:   */       }
/* 50:   */     }
/* 51:47 */     this.interval = iv;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void b(int i, int j) {}
/* 55:   */   
/* 56:   */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 57:   */   {
/* 58:   */     try
/* 59:   */     {
/* 60:57 */       switch (packet.eventId)
/* 61:   */       {
/* 62:   */       case 1: 
/* 63:59 */         long i = packet.getUVLC();
/* 64:60 */         this.tileLogic.setInterval(i);
/* 65:61 */         if (this.tileLogic.k != null) {
/* 66:62 */           this.tileLogic.updateBlock();
/* 67:   */         }
/* 68:   */         break;
/* 69:   */       }
/* 70:   */     }
/* 71:   */     catch (IOException e) {}
/* 72:   */   }
/* 73:   */   
/* 74:67 */   long interval = 0L;
/* 75:   */   private TileLogicPointer tileLogic;
/* 76:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.ContainerTimer
 * JD-Core Version:    0.7.0.1
 */