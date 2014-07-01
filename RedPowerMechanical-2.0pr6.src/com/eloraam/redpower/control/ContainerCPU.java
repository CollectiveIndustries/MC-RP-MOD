/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*  4:   */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.util.List;
/*  7:   */ import la;
/*  8:   */ import qx;
/*  9:   */ import rq;
/* 10:   */ import rw;
/* 11:   */ import ur;
/* 12:   */ 
/* 13:   */ public class ContainerCPU
/* 14:   */   extends rq
/* 15:   */   implements IHandleGuiEvent
/* 16:   */ {
/* 17:   */   public ContainerCPU(la inv, TileCPU cpu)
/* 18:   */   {
/* 19:16 */     this.tileCPU = cpu;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean a(qx player)
/* 23:   */   {
/* 24:20 */     return this.tileCPU.isUseableByPlayer(player);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public ur b(qx player, int i)
/* 28:   */   {
/* 29:25 */     return null;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void b()
/* 33:   */   {
/* 34:29 */     super.b();
/* 35:31 */     for (int i = 0; i < this.e.size(); i++)
/* 36:   */     {
/* 37:32 */       rw ic = (rw)this.e.get(i);
/* 38:34 */       if (this.tileCPU.byte0 != this.byte0) {
/* 39:35 */         ic.a(this, 0, this.tileCPU.byte0);
/* 40:   */       }
/* 41:38 */       if (this.tileCPU.byte1 != this.byte1) {
/* 42:39 */         ic.a(this, 1, this.tileCPU.byte1);
/* 43:   */       }
/* 44:42 */       if (this.tileCPU.rbaddr != this.rbaddr) {
/* 45:43 */         ic.a(this, 2, this.tileCPU.rbaddr);
/* 46:   */       }
/* 47:46 */       if (this.tileCPU.isRunning() != this.isrun) {
/* 48:47 */         ic.a(this, 3, this.tileCPU.isRunning() ? 1 : 0);
/* 49:   */       }
/* 50:   */     }
/* 51:51 */     this.byte0 = this.tileCPU.byte0;
/* 52:52 */     this.byte1 = this.tileCPU.byte1;
/* 53:53 */     this.rbaddr = this.tileCPU.rbaddr;
/* 54:54 */     this.isrun = this.tileCPU.isRunning();
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void b(int i, int j)
/* 58:   */   {
/* 59:58 */     switch (i)
/* 60:   */     {
/* 61:   */     case 0: 
/* 62:59 */       this.tileCPU.byte0 = j; break;
/* 63:   */     case 1: 
/* 64:60 */       this.tileCPU.byte1 = j; break;
/* 65:   */     case 2: 
/* 66:61 */       this.tileCPU.rbaddr = j; break;
/* 67:   */     case 3: 
/* 68:62 */       this.tileCPU.sliceCycles = (j > 0 ? 0 : -1);
/* 69:   */     }
/* 70:   */   }
/* 71:   */   
/* 72:   */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 73:   */   {
/* 74:   */     try
/* 75:   */     {
/* 76:68 */       switch (packet.eventId)
/* 77:   */       {
/* 78:   */       case 1: 
/* 79:69 */         this.tileCPU.byte0 = packet.getByte(); break;
/* 80:   */       case 2: 
/* 81:70 */         this.tileCPU.byte1 = packet.getByte(); break;
/* 82:   */       case 3: 
/* 83:71 */         this.tileCPU.rbaddr = packet.getByte(); break;
/* 84:   */       case 4: 
/* 85:72 */         this.tileCPU.warmBootCPU(); break;
/* 86:   */       case 5: 
/* 87:73 */         this.tileCPU.haltCPU(); break;
/* 88:   */       case 6: 
/* 89:74 */         this.tileCPU.coldBootCPU();
/* 90:   */       }
/* 91:   */     }
/* 92:   */     catch (IOException e) {}
/* 93:   */   }
/* 94:   */   
/* 95:80 */   int byte0 = 0;
/* 96:80 */   int byte1 = 0;
/* 97:80 */   int rbaddr = 0;
/* 98:81 */   boolean isrun = false;
/* 99:   */   private TileCPU tileCPU;
/* :0:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.ContainerCPU
 * JD-Core Version:    0.7.0.1
 */