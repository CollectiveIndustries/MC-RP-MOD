/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerMachine;
/*   5:    */ import com.eloraam.redpower.core.IFrameSupport;
/*   6:    */ import com.eloraam.redpower.core.IHandlePackets;
/*   7:    */ import com.eloraam.redpower.core.IRotatable;
/*   8:    */ import com.eloraam.redpower.core.MachineLib;
/*   9:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  10:    */ import com.eloraam.redpower.core.TileExtended;
/*  11:    */ import com.eloraam.redpower.core.TubeItem;
/*  12:    */ import com.eloraam.redpower.core.WorldCoord;
/*  13:    */ import ef;
/*  14:    */ import java.io.ByteArrayOutputStream;
/*  15:    */ import java.io.IOException;
/*  16:    */ import md;
/*  17:    */ import ur;
/*  18:    */ import yc;
/*  19:    */ import ym;
/*  20:    */ 
/*  21:    */ public class TileMachine
/*  22:    */   extends TileExtended
/*  23:    */   implements IHandlePackets, IRotatable, IFrameSupport
/*  24:    */ {
/*  25:    */   public int getFacing(md ent)
/*  26:    */   {
/*  27: 27 */     int yawrx = (int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  28: 28 */     if ((Math.abs(ent.t - this.l) < 2.0D) && (Math.abs(ent.v - this.n) < 2.0D))
/*  29:    */     {
/*  30: 30 */       double p = ent.u + 1.82D - ent.M - this.m;
/*  31: 31 */       if (p > 2.0D) {
/*  32: 31 */         return 0;
/*  33:    */       }
/*  34: 32 */       if (p < 0.0D) {
/*  35: 32 */         return 1;
/*  36:    */       }
/*  37:    */     }
/*  38: 34 */     switch (yawrx)
/*  39:    */     {
/*  40:    */     case 0: 
/*  41: 35 */       return 3;
/*  42:    */     case 1: 
/*  43: 36 */       return 4;
/*  44:    */     case 2: 
/*  45: 37 */       return 2;
/*  46:    */     }
/*  47: 38 */     return 5;
/*  48:    */   }
/*  49:    */   
/*  50:    */   protected boolean handleItem(TubeItem ti)
/*  51:    */   {
/*  52: 50 */     return MachineLib.handleItem(this.k, ti, new WorldCoord(this.l, this.m, this.n), this.Rotation);
/*  53:    */   }
/*  54:    */   
/*  55:    */   public boolean isPoweringTo(int side)
/*  56:    */   {
/*  57: 55 */     return false;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public int getPartMaxRotation(int part, boolean sec)
/*  61:    */   {
/*  62: 61 */     if (sec) {
/*  63: 61 */       return 0;
/*  64:    */     }
/*  65: 62 */     return 5;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public int getPartRotation(int part, boolean sec)
/*  69:    */   {
/*  70: 66 */     if (sec) {
/*  71: 66 */       return 0;
/*  72:    */     }
/*  73: 67 */     return this.Rotation;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void setPartRotation(int part, boolean sec, int rot)
/*  77:    */   {
/*  78: 71 */     if (sec) {
/*  79: 71 */       return;
/*  80:    */     }
/*  81: 72 */     this.Rotation = rot;
/*  82: 73 */     updateBlockChange();
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void onBlockPlaced(ur ist, int side, md ent)
/*  86:    */   {
/*  87: 80 */     this.Rotation = getFacing(ent);
/*  88:    */   }
/*  89:    */   
/*  90:    */   public int getBlockID()
/*  91:    */   {
/*  92: 84 */     return RedPowerMachine.blockMachine.cm;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public byte[] getFramePacket()
/*  96:    */   {
/*  97: 90 */     Packet211TileDesc pkt = new Packet211TileDesc();
/*  98: 91 */     pkt.subId = 7;
/*  99: 92 */     writeToPacket(pkt);
/* 100: 93 */     pkt.headout.write(pkt.subId);
/* 101: 94 */     return pkt.toByteArray();
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void handleFramePacket(byte[] ba)
/* 105:    */     throws IOException
/* 106:    */   {
/* 107: 98 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 108: 99 */     pkt.subId = pkt.getByte();
/* 109:100 */     readFromPacket(pkt);
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void onFrameRefresh(ym iba) {}
/* 113:    */   
/* 114:    */   public void onFramePickup(ym iba) {}
/* 115:    */   
/* 116:    */   public void onFrameDrop() {}
/* 117:    */   
/* 118:    */   public void a(bq nbttagcompound)
/* 119:    */   {
/* 120:110 */     super.a(nbttagcompound);
/* 121:    */     
/* 122:112 */     int k = nbttagcompound.c("ps");
/* 123:113 */     this.Rotation = nbttagcompound.c("rot");
/* 124:114 */     this.Active = ((k & 0x1) > 0);
/* 125:115 */     this.Powered = ((k & 0x2) > 0);
/* 126:116 */     this.Delay = ((k & 0x4) > 0);
/* 127:117 */     this.Charged = ((k & 0x8) > 0);
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void b(bq nbttagcompound)
/* 131:    */   {
/* 132:121 */     super.b(nbttagcompound);
/* 133:    */     
/* 134:123 */     int ps = (this.Active ? 1 : 0) | (this.Powered ? 2 : 0) | (this.Delay ? 4 : 0) | (this.Charged ? 8 : 0);
/* 135:124 */     nbttagcompound.a("ps", (byte)ps);
/* 136:125 */     nbttagcompound.a("rot", (byte)this.Rotation);
/* 137:    */   }
/* 138:    */   
/* 139:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 140:    */     throws IOException
/* 141:    */   {
/* 142:130 */     this.Rotation = pkt.getByte();
/* 143:131 */     int ps = pkt.getByte();
/* 144:132 */     this.Active = ((ps & 0x1) > 0);
/* 145:133 */     this.Powered = ((ps & 0x2) > 0);
/* 146:134 */     this.Delay = ((ps & 0x4) > 0);
/* 147:135 */     this.Charged = ((ps & 0x8) > 0);
/* 148:    */   }
/* 149:    */   
/* 150:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 151:    */   {
/* 152:139 */     pkt.addByte(this.Rotation);
/* 153:140 */     int ps = (this.Active ? 1 : 0) | (this.Powered ? 2 : 0) | (this.Delay ? 4 : 0) | (this.Charged ? 8 : 0);
/* 154:141 */     pkt.addByte(ps);
/* 155:    */   }
/* 156:    */   
/* 157:    */   public ef l()
/* 158:    */   {
/* 159:145 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 160:146 */     packet.subId = 7;
/* 161:147 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 162:148 */     packet.zCoord = this.n;
/* 163:149 */     writeToPacket(packet);
/* 164:150 */     packet.encode();
/* 165:151 */     return packet;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public void handlePacket(Packet211TileDesc packet)
/* 169:    */   {
/* 170:    */     try
/* 171:    */     {
/* 172:156 */       if (packet.subId != 7) {
/* 173:156 */         return;
/* 174:    */       }
/* 175:157 */       readFromPacket(packet);
/* 176:    */     }
/* 177:    */     catch (IOException e) {}
/* 178:159 */     this.k.i(this.l, this.m, this.n);
/* 179:    */   }
/* 180:    */   
/* 181:162 */   public int Rotation = 0;
/* 182:163 */   public boolean Active = false;
/* 183:163 */   public boolean Powered = false;
/* 184:163 */   public boolean Delay = false;
/* 185:163 */   public boolean Charged = false;
/* 186:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileMachine
 * JD-Core Version:    0.7.0.1
 */