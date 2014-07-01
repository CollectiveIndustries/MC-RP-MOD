/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*  4:   */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.util.List;
/*  7:   */ import la;
/*  8:   */ import qx;
/*  9:   */ import rq;
/* 10:   */ import rw;
/* 11:   */ import sr;
/* 12:   */ import ur;
/* 13:   */ 
/* 14:   */ public class ContainerItemDetect
/* 15:   */   extends rq
/* 16:   */   implements IHandleGuiEvent
/* 17:   */ {
/* 18:   */   private TileItemDetect tileDetect;
/* 19:   */   byte mode;
/* 20:   */   
/* 21:   */   public ContainerItemDetect(la inv, TileItemDetect tid)
/* 22:   */   {
/* 23:16 */     this.tileDetect = tid;
/* 24:18 */     for (int i = 0; i < 3; i++) {
/* 25:18 */       for (int j = 0; j < 3; j++) {
/* 26:19 */         a(new sr(tid, j + i * 3, 62 + j * 18, 17 + i * 18));
/* 27:   */       }
/* 28:   */     }
/* 29:21 */     for (i = 0; i < 3; i++) {
/* 30:21 */       for (int j = 0; j < 9; j++) {
/* 31:22 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/* 32:   */       }
/* 33:   */     }
/* 34:24 */     for (i = 0; i < 9; i++) {
/* 35:25 */       a(new sr(inv, i, 8 + i * 18, 142));
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public boolean a(qx player)
/* 40:   */   {
/* 41:30 */     return this.tileDetect.a_(player);
/* 42:   */   }
/* 43:   */   
/* 44:   */   public ur b(qx player, int i)
/* 45:   */   {
/* 46:35 */     ur itemstack = null;
/* 47:36 */     sr slot = (sr)this.c.get(i);
/* 48:37 */     if ((slot != null) && (slot.d()))
/* 49:   */     {
/* 50:38 */       ur itemstack1 = slot.c();
/* 51:39 */       itemstack = itemstack1.l();
/* 52:40 */       if (i < 9)
/* 53:   */       {
/* 54:41 */         if (!a(itemstack1, 9, 45, true)) {
/* 55:42 */           return null;
/* 56:   */         }
/* 57:   */       }
/* 58:44 */       else if (!a(itemstack1, 0, 9, false)) {
/* 59:45 */         return null;
/* 60:   */       }
/* 61:47 */       if (itemstack1.a == 0) {
/* 62:48 */         slot.c(null);
/* 63:   */       } else {
/* 64:50 */         slot.e();
/* 65:   */       }
/* 66:52 */       if (itemstack1.a != itemstack.a) {
/* 67:53 */         slot.a(player, itemstack1);
/* 68:   */       } else {
/* 69:55 */         return null;
/* 70:   */       }
/* 71:   */     }
/* 72:58 */     return itemstack;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public void b()
/* 76:   */   {
/* 77:62 */     super.b();
/* 78:64 */     for (int i = 0; i < this.e.size(); i++)
/* 79:   */     {
/* 80:65 */       rw ic = (rw)this.e.get(i);
/* 81:67 */       if (this.mode != this.tileDetect.mode) {
/* 82:68 */         ic.a(this, 0, this.tileDetect.mode);
/* 83:   */       }
/* 84:   */     }
/* 85:71 */     this.mode = this.tileDetect.mode;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public void func_20112_a(int i, int j)
/* 89:   */   {
/* 90:76 */     b(i, j);
/* 91:   */   }
/* 92:   */   
/* 93:   */   public void b(int i, int j)
/* 94:   */   {
/* 95:80 */     if (i == 0) {
/* 96:80 */       this.tileDetect.mode = ((byte)j);
/* 97:   */     }
/* 98:   */   }
/* 99:   */   
/* :0:   */   public void handleGuiEvent(Packet212GuiEvent packet)
/* :1:   */   {
/* :2:84 */     if (packet.eventId != 1) {
/* :3:84 */       return;
/* :4:   */     }
/* :5:   */     try
/* :6:   */     {
/* :7:86 */       this.tileDetect.mode = ((byte)packet.getByte());
/* :8:87 */       this.tileDetect.dirtyBlock();
/* :9:   */     }
/* ;0:   */     catch (IOException e) {}
/* ;1:89 */     b();
/* ;2:   */   }
/* ;3:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerItemDetect
 * JD-Core Version:    0.7.0.1
 */