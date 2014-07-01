/*   1:    */ package com.eloraam.redpower.compat;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerCompat;
/*   5:    */ import com.eloraam.redpower.core.BlockExtended;
/*   6:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   7:    */ import com.eloraam.redpower.core.IFrameSupport;
/*   8:    */ import com.eloraam.redpower.core.IHandlePackets;
/*   9:    */ import com.eloraam.redpower.core.IRotatable;
/*  10:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  11:    */ import com.eloraam.redpower.core.TileMultipart;
/*  12:    */ import ef;
/*  13:    */ import java.io.ByteArrayOutputStream;
/*  14:    */ import java.io.IOException;
/*  15:    */ import java.util.ArrayList;
/*  16:    */ import md;
/*  17:    */ import qx;
/*  18:    */ import ur;
/*  19:    */ import yc;
/*  20:    */ import ym;
/*  21:    */ 
/*  22:    */ public class TileMachineCompat
/*  23:    */   extends TileMultipart
/*  24:    */   implements IHandlePackets, IRotatable, IFrameSupport
/*  25:    */ {
/*  26:    */   public int getFacing(md ent)
/*  27:    */   {
/*  28: 28 */     int yawrx = (int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  29: 29 */     if ((Math.abs(ent.t - this.l) < 2.0D) && (Math.abs(ent.v - this.n) < 2.0D))
/*  30:    */     {
/*  31: 31 */       double p = ent.u + 1.82D - ent.M - this.m;
/*  32: 32 */       if (p > 2.0D) {
/*  33: 32 */         return 0;
/*  34:    */       }
/*  35: 33 */       if (p < 0.0D) {
/*  36: 33 */         return 1;
/*  37:    */       }
/*  38:    */     }
/*  39: 35 */     switch (yawrx)
/*  40:    */     {
/*  41:    */     case 0: 
/*  42: 36 */       return 3;
/*  43:    */     case 1: 
/*  44: 37 */       return 4;
/*  45:    */     case 2: 
/*  46: 38 */       return 2;
/*  47:    */     }
/*  48: 39 */     return 5;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void onBlockPlaced(ur ist, int side, md ent)
/*  52:    */   {
/*  53: 47 */     this.Rotation = getFacing(ent);
/*  54:    */   }
/*  55:    */   
/*  56:    */   public int getBlockID()
/*  57:    */   {
/*  58: 51 */     return RedPowerCompat.blockMachineCompat.cm;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void addHarvestContents(ArrayList ist)
/*  62:    */   {
/*  63: 57 */     ist.add(new ur(getBlockID(), 1, getExtendedID()));
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void onHarvestPart(qx player, int part)
/*  67:    */   {
/*  68: 61 */     breakBlock();
/*  69:    */   }
/*  70:    */   
/*  71:    */   public float getPartStrength(qx player, int part)
/*  72:    */   {
/*  73: 65 */     BlockExtended bl = RedPowerCompat.blockMachineCompat;
/*  74: 66 */     return player.getCurrentPlayerStrVsBlock(bl, 0) / (bl.getHardness() * 30.0F);
/*  75:    */   }
/*  76:    */   
/*  77:    */   public boolean blockEmpty()
/*  78:    */   {
/*  79: 70 */     return false;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void setPartBounds(BlockMultipart bl, int part)
/*  83:    */   {
/*  84: 73 */     bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public int getSolidPartsMask()
/*  88:    */   {
/*  89: 76 */     return 1;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public int getPartsMask()
/*  93:    */   {
/*  94: 77 */     return 1;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public int getPartMaxRotation(int part, boolean sec)
/*  98:    */   {
/*  99: 82 */     if (sec) {
/* 100: 82 */       return 0;
/* 101:    */     }
/* 102: 83 */     return 5;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public int getPartRotation(int part, boolean sec)
/* 106:    */   {
/* 107: 87 */     if (sec) {
/* 108: 87 */       return 0;
/* 109:    */     }
/* 110: 88 */     return this.Rotation;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void setPartRotation(int part, boolean sec, int rot)
/* 114:    */   {
/* 115: 92 */     if (sec) {
/* 116: 92 */       return;
/* 117:    */     }
/* 118: 93 */     this.Rotation = rot;
/* 119: 94 */     updateBlockChange();
/* 120:    */   }
/* 121:    */   
/* 122:    */   public byte[] getFramePacket()
/* 123:    */   {
/* 124:100 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 125:101 */     pkt.subId = 8;
/* 126:102 */     writeToPacket(pkt);
/* 127:103 */     pkt.headout.write(pkt.subId);
/* 128:104 */     return pkt.toByteArray();
/* 129:    */   }
/* 130:    */   
/* 131:    */   public void handleFramePacket(byte[] ba)
/* 132:    */     throws IOException
/* 133:    */   {
/* 134:108 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 135:109 */     pkt.subId = pkt.getByte();
/* 136:110 */     readFromPacket(pkt);
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void onFrameRefresh(ym iba) {}
/* 140:    */   
/* 141:    */   public void onFramePickup(ym iba) {}
/* 142:    */   
/* 143:    */   public void onFrameDrop() {}
/* 144:    */   
/* 145:    */   public void a(bq tag)
/* 146:    */   {
/* 147:120 */     super.a(tag);
/* 148:    */     
/* 149:122 */     int k = tag.c("ps");
/* 150:123 */     this.Rotation = tag.c("rot");
/* 151:    */     
/* 152:125 */     this.Active = ((k & 0x1) > 0);
/* 153:126 */     this.Powered = ((k & 0x2) > 0);
/* 154:127 */     this.Delay = ((k & 0x4) > 0);
/* 155:128 */     this.Charged = ((k & 0x8) > 0);
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void b(bq tag)
/* 159:    */   {
/* 160:132 */     super.b(tag);
/* 161:    */     
/* 162:134 */     int ps = (this.Active ? 1 : 0) | (this.Powered ? 2 : 0) | (this.Delay ? 4 : 0) | (this.Charged ? 8 : 0);
/* 163:135 */     tag.a("ps", (byte)ps);
/* 164:136 */     tag.a("rot", (byte)this.Rotation);
/* 165:    */   }
/* 166:    */   
/* 167:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 168:    */     throws IOException
/* 169:    */   {
/* 170:141 */     this.Rotation = pkt.getByte();
/* 171:142 */     int ps = pkt.getByte();
/* 172:143 */     this.Active = ((ps & 0x1) > 0);
/* 173:144 */     this.Powered = ((ps & 0x2) > 0);
/* 174:145 */     this.Delay = ((ps & 0x4) > 0);
/* 175:146 */     this.Charged = ((ps & 0x8) > 0);
/* 176:    */   }
/* 177:    */   
/* 178:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 179:    */   {
/* 180:150 */     pkt.addByte(this.Rotation);
/* 181:151 */     int ps = (this.Active ? 1 : 0) | (this.Powered ? 2 : 0) | (this.Delay ? 4 : 0) | (this.Charged ? 8 : 0);
/* 182:152 */     pkt.addByte(ps);
/* 183:    */   }
/* 184:    */   
/* 185:    */   public ef l()
/* 186:    */   {
/* 187:156 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 188:157 */     packet.subId = 8;
/* 189:158 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 190:159 */     packet.zCoord = this.n;
/* 191:160 */     writeToPacket(packet);
/* 192:161 */     packet.encode();
/* 193:162 */     return packet;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void handlePacket(Packet211TileDesc packet)
/* 197:    */   {
/* 198:    */     try
/* 199:    */     {
/* 200:167 */       if (packet.subId != 8) {
/* 201:167 */         return;
/* 202:    */       }
/* 203:168 */       readFromPacket(packet);
/* 204:    */     }
/* 205:    */     catch (IOException e) {}
/* 206:170 */     this.k.i(this.l, this.m, this.n);
/* 207:    */   }
/* 208:    */   
/* 209:173 */   public int Rotation = 0;
/* 210:174 */   public boolean Active = false;
/* 211:174 */   public boolean Powered = false;
/* 212:174 */   public boolean Delay = false;
/* 213:174 */   public boolean Charged = false;
/* 214:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.compat.TileMachineCompat
 * JD-Core Version:    0.7.0.1
 */