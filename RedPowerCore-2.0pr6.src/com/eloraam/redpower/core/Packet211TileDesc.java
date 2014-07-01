/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import eg;
/*  4:   */ import java.io.ByteArrayInputStream;
/*  5:   */ import java.io.ByteArrayOutputStream;
/*  6:   */ import java.io.DataInputStream;
/*  7:   */ import java.io.IOException;
/*  8:   */ 
/*  9:   */ public class Packet211TileDesc
/* 10:   */   extends PacketVLC
/* 11:   */ {
/* 12:   */   public Packet211TileDesc()
/* 13:   */   {
/* 14:11 */     this.r = true;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public Packet211TileDesc(byte[] data)
/* 18:   */   {
/* 19:15 */     this.bodyin = new ByteArrayInputStream(data);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void a(DataInputStream in)
/* 23:   */     throws IOException
/* 24:   */   {
/* 25:21 */     this.subId = in.read();
/* 26:22 */     if (this.subId == -1) {
/* 27:22 */       throw new IOException("Not enough data");
/* 28:   */     }
/* 29:23 */     this.xCoord = ((int)readVLC(in));
/* 30:24 */     this.yCoord = ((int)readVLC(in));
/* 31:25 */     this.zCoord = ((int)readVLC(in));
/* 32:26 */     int l = (int)readUVLC(in);
/* 33:27 */     if (l > 65535) {
/* 34:27 */       throw new IOException("Packet too big");
/* 35:   */     }
/* 36:29 */     this.size = (this.cnt1 + l + 1);
/* 37:30 */     byte[] body = new byte[l];
/* 38:31 */     int p = 0;
/* 39:32 */     while (l - p > 0)
/* 40:   */     {
/* 41:33 */       int rl = in.read(body, p, l - p);
/* 42:34 */       if (rl < 1) {
/* 43:34 */         throw new IOException("Not enough data");
/* 44:   */       }
/* 45:35 */       if (p + rl >= l) {
/* 46:   */         break;
/* 47:   */       }
/* 48:36 */       p += rl;
/* 49:   */     }
/* 50:38 */     this.bodyin = new ByteArrayInputStream(body);
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void encode()
/* 54:   */   {
/* 55:51 */     this.headout.write(this.subId);
/* 56:52 */     writeVLC(this.headout, this.xCoord);
/* 57:53 */     writeVLC(this.headout, this.yCoord);
/* 58:54 */     writeVLC(this.headout, this.zCoord);
/* 59:55 */     writeUVLC(this.headout, this.bodyout.size());
/* 60:56 */     this.size = (this.headout.size() + this.bodyout.size());
/* 61:   */     
/* 62:58 */     fixLocalPacket();
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void a(eg nethandler)
/* 66:   */   {
/* 67:65 */     CoreProxy.instance.processPacket211(this, nethandler);
/* 68:   */   }
/* 69:   */   
/* 70:73 */   private int size = 0;
/* 71:73 */   private int cnt1 = 0;
/* 72:   */   public int zCoord;
/* 73:   */   public int yCoord;
/* 74:   */   public int xCoord;
/* 75:   */   public int subId;
/* 76:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.Packet211TileDesc
 * JD-Core Version:    0.7.0.1
 */