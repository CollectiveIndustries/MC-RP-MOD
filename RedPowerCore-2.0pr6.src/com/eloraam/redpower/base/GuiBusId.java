/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import atq;
/*  4:   */ import avf;
/*  5:   */ import ayp;
/*  6:   */ import bba;
/*  7:   */ import com.eloraam.redpower.core.CoreProxy;
/*  8:   */ import com.eloraam.redpower.core.IRedbusConnectable;
/*  9:   */ import com.eloraam.redpower.core.Packet212GuiEvent;
/* 10:   */ import net.minecraft.client.Minecraft;
/* 11:   */ import org.lwjgl.opengl.GL11;
/* 12:   */ import qw;
/* 13:   */ import rq;
/* 14:   */ 
/* 15:   */ public class GuiBusId
/* 16:   */   extends avf
/* 17:   */ {
/* 18:   */   IRedbusConnectable rbConn;
/* 19:   */   
/* 20:   */   public GuiBusId(qw pli, IRedbusConnectable irc)
/* 21:   */   {
/* 22:13 */     super(new ContainerBusId(pli, irc));
/* 23:14 */     this.rbConn = irc;
/* 24:15 */     this.c = 81;
/* 25:16 */     this.b = 123;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public GuiBusId(rq cn)
/* 29:   */   {
/* 30:20 */     super(cn);
/* 31:21 */     this.c = 81;
/* 32:22 */     this.b = 123;
/* 33:   */   }
/* 34:   */   
/* 35:   */   protected void b(int p1, int p2)
/* 36:   */   {
/* 37:27 */     this.l.b("Set Bus Id", 32, 6, 4210752);
/* 38:   */   }
/* 39:   */   
/* 40:   */   protected void a(float f, int p1, int p2)
/* 41:   */   {
/* 42:33 */     int i = this.f.o.b("/eloraam/base/idgui.png");
/* 43:34 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 44:35 */     this.f.o.b(i);
/* 45:36 */     int j = (this.g - this.b) / 2;
/* 46:37 */     int k = (this.h - this.c) / 2;
/* 47:38 */     b(j, k, 0, 0, this.b, this.c);
/* 48:   */     
/* 49:40 */     int bits = this.rbConn.rbGetAddr();
/* 50:41 */     for (int n = 0; n < 8; n++) {
/* 51:42 */       if ((bits & 1 << n) != 0) {
/* 52:44 */         b(j + 16 + n * 12, k + 25, 123, 0, 8, 16);
/* 53:   */       }
/* 54:   */     }
/* 55:46 */     a(this.l, String.format("ID: %d", new Object[] { Integer.valueOf(bits) }), this.g / 2, k + 60, -1);
/* 56:   */   }
/* 57:   */   
/* 58:   */   void sendAddr()
/* 59:   */   {
/* 60:51 */     if (!this.f.e.I) {
/* 61:52 */       return;
/* 62:   */     }
/* 63:53 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 64:54 */     pkt.eventId = 1;
/* 65:55 */     pkt.windowId = this.d.d;
/* 66:56 */     pkt.addByte(this.rbConn.rbGetAddr());
/* 67:57 */     pkt.encode();
/* 68:58 */     CoreProxy.sendPacketToServer(pkt);
/* 69:   */   }
/* 70:   */   
/* 71:   */   protected void a(int i, int j, int k)
/* 72:   */   {
/* 73:63 */     int x = i - (this.g - this.b) / 2;
/* 74:64 */     int y = j - (this.h - this.c) / 2;
/* 75:66 */     if ((y >= 25) && (y <= 41)) {
/* 76:67 */       for (int n = 0; n < 8; n++) {
/* 77:68 */         if ((x >= 16 + n * 12) && (x <= 24 + n * 12))
/* 78:   */         {
/* 79:69 */           this.rbConn.rbSetAddr(this.rbConn.rbGetAddr() ^ 1 << n);
/* 80:   */           
/* 81:71 */           sendAddr();
/* 82:72 */           return;
/* 83:   */         }
/* 84:   */       }
/* 85:   */     }
/* 86:76 */     super.a(i, j, k);
/* 87:   */   }
/* 88:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.GuiBusId
 * JD-Core Version:    0.7.0.1
 */