/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import eg;
/*  4:   */ import java.io.ByteArrayInputStream;
/*  5:   */ import java.io.ByteArrayOutputStream;
/*  6:   */ import java.io.DataInputStream;
/*  7:   */ import java.io.IOException;
/*  8:   */ 
/*  9:   */ public class Packet212GuiEvent
/* 10:   */   extends PacketVLC
/* 11:   */ {
/* 12:   */   public void a(DataInputStream in)
/* 13:   */     throws IOException
/* 14:   */   {
/* 15:15 */     this.windowId = in.read();
/* 16:16 */     if (this.windowId == -1) {
/* 17:16 */       throw new IOException("Not enough data");
/* 18:   */     }
/* 19:17 */     this.eventId = ((int)readUVLC(in));
/* 20:18 */     int l = (int)readUVLC(in);
/* 21:19 */     if (l > 65535) {
/* 22:19 */       throw new IOException("Packet too big");
/* 23:   */     }
/* 24:20 */     this.size = (this.cnt1 + l + 1);
/* 25:21 */     byte[] body = new byte[l];
/* 26:22 */     int p = 0;
/* 27:23 */     while (l - p > 0)
/* 28:   */     {
/* 29:24 */       int rl = in.read(body, p, l - p);
/* 30:25 */       if (rl < 1) {
/* 31:25 */         throw new IOException("Not enough data");
/* 32:   */       }
/* 33:26 */       if (p + rl >= l) {
/* 34:   */         break;
/* 35:   */       }
/* 36:27 */       p += rl;
/* 37:   */     }
/* 38:29 */     this.bodyin = new ByteArrayInputStream(body);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void encode()
/* 42:   */   {
/* 43:33 */     this.headout.write(this.windowId);
/* 44:34 */     writeUVLC(this.headout, this.eventId);
/* 45:35 */     writeUVLC(this.headout, this.bodyout.size());
/* 46:36 */     this.size = (this.headout.size() + this.bodyout.size());
/* 47:37 */     fixLocalPacket();
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void a(eg nethandler)
/* 51:   */   {
/* 52:41 */     CoreProxy.instance.processPacket212(this, nethandler);
/* 53:   */   }
/* 54:   */   
/* 55:45 */   private int size = 0;
/* 56:45 */   private int cnt1 = 0;
/* 57:   */   public int windowId;
/* 58:   */   public int eventId;
/* 59:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.Packet212GuiEvent
 * JD-Core Version:    0.7.0.1
 */