/*   1:    */ package com.eloraam.redpower.base;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerBase;
/*   5:    */ import com.eloraam.redpower.core.IFrameSupport;
/*   6:    */ import com.eloraam.redpower.core.IHandlePackets;
/*   7:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   8:    */ import com.eloraam.redpower.core.TileExtended;
/*   9:    */ import ef;
/*  10:    */ import java.io.ByteArrayOutputStream;
/*  11:    */ import java.io.IOException;
/*  12:    */ import md;
/*  13:    */ import ur;
/*  14:    */ import yc;
/*  15:    */ import ym;
/*  16:    */ 
/*  17:    */ public class TileAppliance
/*  18:    */   extends TileExtended
/*  19:    */   implements IHandlePackets, IFrameSupport
/*  20:    */ {
/*  21:    */   public void onBlockPlaced(ur ist, int side, md ent)
/*  22:    */   {
/*  23: 29 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getBlockID()
/*  27:    */   {
/*  28: 33 */     return RedPowerBase.blockAppliance.cm;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public int getLightValue()
/*  32:    */   {
/*  33: 37 */     return this.Active ? 13 : 0;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public byte[] getFramePacket()
/*  37:    */   {
/*  38: 43 */     Packet211TileDesc pkt = new Packet211TileDesc();
/*  39: 44 */     pkt.subId = 7;
/*  40: 45 */     writeToPacket(pkt);
/*  41: 46 */     pkt.headout.write(pkt.subId);
/*  42: 47 */     return pkt.toByteArray();
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void handleFramePacket(byte[] ba)
/*  46:    */     throws IOException
/*  47:    */   {
/*  48: 51 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/*  49: 52 */     pkt.subId = pkt.getByte();
/*  50: 53 */     readFromPacket(pkt);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void onFrameRefresh(ym iba) {}
/*  54:    */   
/*  55:    */   public void onFramePickup(ym iba) {}
/*  56:    */   
/*  57:    */   public void onFrameDrop() {}
/*  58:    */   
/*  59:    */   public void a(bq nbttagcompound)
/*  60:    */   {
/*  61: 63 */     super.a(nbttagcompound);
/*  62:    */     
/*  63: 65 */     int k = nbttagcompound.c("ps");
/*  64: 66 */     this.Rotation = nbttagcompound.c("rot");
/*  65: 67 */     this.Active = (k > 0);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void b(bq nbttagcompound)
/*  69:    */   {
/*  70: 71 */     super.b(nbttagcompound);
/*  71:    */     
/*  72: 73 */     nbttagcompound.a("ps", (byte)(this.Active ? 1 : 0));
/*  73: 74 */     nbttagcompound.a("rot", (byte)this.Rotation);
/*  74:    */   }
/*  75:    */   
/*  76:    */   protected void readFromPacket(Packet211TileDesc pkt)
/*  77:    */     throws IOException
/*  78:    */   {
/*  79: 79 */     this.Rotation = pkt.getByte();
/*  80: 80 */     int ps = pkt.getByte();
/*  81: 81 */     this.Active = (ps > 0);
/*  82:    */   }
/*  83:    */   
/*  84:    */   protected void writeToPacket(Packet211TileDesc pkt)
/*  85:    */   {
/*  86: 85 */     pkt.addByte(this.Rotation);
/*  87: 86 */     int ps = this.Active ? 1 : 0;
/*  88: 87 */     pkt.addByte(ps);
/*  89:    */   }
/*  90:    */   
/*  91:    */   public ef l()
/*  92:    */   {
/*  93: 91 */     Packet211TileDesc packet = new Packet211TileDesc();
/*  94: 92 */     packet.subId = 7;
/*  95: 93 */     packet.xCoord = this.l;packet.yCoord = this.m;
/*  96: 94 */     packet.zCoord = this.n;
/*  97: 95 */     writeToPacket(packet);
/*  98: 96 */     packet.encode();
/*  99: 97 */     return packet;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void handlePacket(Packet211TileDesc packet)
/* 103:    */   {
/* 104:    */     try
/* 105:    */     {
/* 106:102 */       if (packet.subId != 7) {
/* 107:102 */         return;
/* 108:    */       }
/* 109:103 */       readFromPacket(packet);
/* 110:    */     }
/* 111:    */     catch (IOException e) {}
/* 112:105 */     this.k.i(this.l, this.m, this.n);
/* 113:    */   }
/* 114:    */   
/* 115:108 */   public int Rotation = 0;
/* 116:109 */   public boolean Active = false;
/* 117:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.TileAppliance
 * JD-Core Version:    0.7.0.1
 */