/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ import bq;
/*  5:   */ import com.eloraam.redpower.RedPowerBase;
/*  6:   */ import ef;
/*  7:   */ import java.io.IOException;
/*  8:   */ 
/*  9:   */ public class TileMultiblock
/* 10:   */   extends any
/* 11:   */   implements IHandlePackets
/* 12:   */ {
/* 13:   */   public int relayX;
/* 14:   */   public int relayY;
/* 15:   */   public int relayZ;
/* 16:   */   public int relayNum;
/* 17:   */   
/* 18:   */   public boolean canUpdate()
/* 19:   */   {
/* 20:18 */     return false;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public int getBlockID()
/* 24:   */   {
/* 25:21 */     return RedPowerBase.blockMultiblock.cm;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void a(bq tag)
/* 29:   */   {
/* 30:27 */     super.a(tag);
/* 31:   */     
/* 32:29 */     this.relayX = tag.e("rlx");
/* 33:30 */     this.relayY = tag.e("rly");
/* 34:31 */     this.relayZ = tag.e("rlz");
/* 35:32 */     this.relayNum = tag.e("rln");
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void b(bq tag)
/* 39:   */   {
/* 40:36 */     super.b(tag);
/* 41:   */     
/* 42:38 */     tag.a("rlx", this.relayX);
/* 43:39 */     tag.a("rly", this.relayY);
/* 44:40 */     tag.a("rlz", this.relayZ);
/* 45:41 */     tag.a("rln", this.relayNum);
/* 46:   */   }
/* 47:   */   
/* 48:   */   protected void readFromPacket(Packet211TileDesc pkt)
/* 49:   */     throws IOException
/* 50:   */   {
/* 51:46 */     this.relayX = ((int)pkt.getVLC());
/* 52:47 */     this.relayY = ((int)pkt.getVLC());
/* 53:48 */     this.relayZ = ((int)pkt.getVLC());
/* 54:49 */     this.relayNum = ((int)pkt.getUVLC());
/* 55:   */   }
/* 56:   */   
/* 57:   */   protected void writeToPacket(Packet211TileDesc pkt)
/* 58:   */   {
/* 59:53 */     pkt.addVLC(this.relayX);
/* 60:54 */     pkt.addVLC(this.relayY);
/* 61:55 */     pkt.addVLC(this.relayZ);
/* 62:56 */     pkt.addUVLC(this.relayNum);
/* 63:   */   }
/* 64:   */   
/* 65:   */   public ef l()
/* 66:   */   {
/* 67:60 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 68:61 */     packet.subId = 7;
/* 69:62 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 70:63 */     packet.zCoord = this.n;
/* 71:64 */     writeToPacket(packet);
/* 72:65 */     packet.encode();
/* 73:66 */     return packet;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public void handlePacket(Packet211TileDesc packet)
/* 77:   */   {
/* 78:   */     try
/* 79:   */     {
/* 80:71 */       if (packet.subId != 7) {
/* 81:71 */         return;
/* 82:   */       }
/* 83:72 */       readFromPacket(packet);
/* 84:   */     }
/* 85:   */     catch (IOException e) {}
/* 86:   */   }
/* 87:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TileMultiblock
 * JD-Core Version:    0.7.0.1
 */