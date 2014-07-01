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
/* 14:   */ public class ContainerFilter
/* 15:   */   extends rq
/* 16:   */   implements IHandleGuiEvent
/* 17:   */ {
/* 18:   */   private TileFilter tileFilter;
/* 19:   */   
/* 20:   */   public ContainerFilter(la inv, TileFilter tf)
/* 21:   */   {
/* 22:17 */     this.tileFilter = tf;
/* 23:19 */     for (int i = 0; i < 3; i++) {
/* 24:19 */       for (int j = 0; j < 3; j++) {
/* 25:20 */         a(new sr(tf, j + i * 3, 62 + j * 18, 17 + i * 18));
/* 26:   */       }
/* 27:   */     }
/* 28:22 */     for (i = 0; i < 3; i++) {
/* 29:22 */       for (int j = 0; j < 9; j++) {
/* 30:23 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/* 31:   */       }
/* 32:   */     }
/* 33:25 */     for (i = 0; i < 9; i++) {
/* 34:26 */       a(new sr(inv, i, 8 + i * 18, 142));
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean a(qx player)
/* 39:   */   {
/* 40:31 */     return this.tileFilter.a_(player);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public ur b(qx player, int i)
/* 44:   */   {
/* 45:36 */     ur itemstack = null;
/* 46:37 */     sr slot = (sr)this.c.get(i);
/* 47:38 */     if ((slot != null) && (slot.d()))
/* 48:   */     {
/* 49:39 */       ur itemstack1 = slot.c();
/* 50:40 */       itemstack = itemstack1.l();
/* 51:41 */       if (i < 9)
/* 52:   */       {
/* 53:42 */         if (!a(itemstack1, 9, 45, true)) {
/* 54:43 */           return null;
/* 55:   */         }
/* 56:   */       }
/* 57:45 */       else if (!a(itemstack1, 0, 9, false)) {
/* 58:46 */         return null;
/* 59:   */       }
/* 60:48 */       if (itemstack1.a == 0) {
/* 61:49 */         slot.c(null);
/* 62:   */       } else {
/* 63:51 */         slot.e();
/* 64:   */       }
/* 65:53 */       if (itemstack1.a != itemstack.a) {
/* 66:54 */         slot.a(player, itemstack1);
/* 67:   */       } else {
/* 68:56 */         return null;
/* 69:   */       }
/* 70:   */     }
/* 71:59 */     return itemstack;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public void b()
/* 75:   */   {
/* 76:63 */     super.b();
/* 77:65 */     for (int i = 0; i < this.e.size(); i++)
/* 78:   */     {
/* 79:66 */       rw ic = (rw)this.e.get(i);
/* 80:67 */       if (this.color != this.tileFilter.color) {
/* 81:68 */         ic.a(this, 0, this.tileFilter.color);
/* 82:   */       }
/* 83:   */     }
/* 84:71 */     this.color = this.tileFilter.color;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public void b(int i, int j)
/* 88:   */   {
/* 89:75 */     switch (i)
/* 90:   */     {
/* 91:   */     case 0: 
/* 92:76 */       this.tileFilter.color = ((byte)j);
/* 93:   */     }
/* 94:   */   }
/* 95:   */   
/* 96:   */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 97:   */   {
/* 98:   */     try
/* 99:   */     {
/* :0:83 */       if (packet.eventId != 1) {
/* :1:83 */         return;
/* :2:   */       }
/* :3:84 */       this.tileFilter.color = ((byte)packet.getByte());
/* :4:85 */       this.tileFilter.dirtyBlock();
/* :5:   */     }
/* :6:   */     catch (IOException e) {}
/* :7:   */   }
/* :8:   */   
/* :9:90 */   public int color = 0;
/* ;0:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerFilter
 * JD-Core Version:    0.7.0.1
 */