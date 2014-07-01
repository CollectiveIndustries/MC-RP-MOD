/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerMachine;
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
/*  22:    */ public class TileMachinePanel
/*  23:    */   extends TileMultipart
/*  24:    */   implements IHandlePackets, IRotatable, IFrameSupport
/*  25:    */ {
/*  26:    */   public int getLightValue()
/*  27:    */   {
/*  28: 30 */     return 0;
/*  29:    */   }
/*  30:    */   
/*  31:    */   void updateLight()
/*  32:    */   {
/*  33: 34 */     this.k.z(this.l, this.m, this.n);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public int getFacing(md ent)
/*  37:    */   {
/*  38: 38 */     int yawrx = (int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  39: 39 */     if ((Math.abs(ent.t - this.l) < 2.0D) && (Math.abs(ent.v - this.n) < 2.0D))
/*  40:    */     {
/*  41: 41 */       double p = ent.u + 1.82D - ent.M - this.m;
/*  42: 42 */       if (p > 2.0D) {
/*  43: 42 */         return 0;
/*  44:    */       }
/*  45: 43 */       if (p < 0.0D) {
/*  46: 43 */         return 1;
/*  47:    */       }
/*  48:    */     }
/*  49: 45 */     switch (yawrx)
/*  50:    */     {
/*  51:    */     case 0: 
/*  52: 46 */       return 3;
/*  53:    */     case 1: 
/*  54: 47 */       return 4;
/*  55:    */     case 2: 
/*  56: 48 */       return 2;
/*  57:    */     }
/*  58: 49 */     return 5;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void onBlockPlaced(ur ist, int side, md ent)
/*  62:    */   {
/*  63: 57 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public int getBlockID()
/*  67:    */   {
/*  68: 61 */     return RedPowerMachine.blockMachinePanel.cm;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void addHarvestContents(ArrayList ist)
/*  72:    */   {
/*  73: 67 */     ist.add(new ur(getBlockID(), 1, getExtendedID()));
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void onHarvestPart(qx player, int part)
/*  77:    */   {
/*  78: 71 */     breakBlock();
/*  79:    */   }
/*  80:    */   
/*  81:    */   public float getPartStrength(qx player, int part)
/*  82:    */   {
/*  83: 75 */     BlockExtended bl = RedPowerMachine.blockMachinePanel;
/*  84: 76 */     return player.getCurrentPlayerStrVsBlock(bl, 0) / (bl.getHardness() * 30.0F);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public boolean blockEmpty()
/*  88:    */   {
/*  89: 80 */     return false;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void setPartBounds(BlockMultipart bl, int part)
/*  93:    */   {
/*  94: 83 */     bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public int getSolidPartsMask()
/*  98:    */   {
/*  99: 86 */     return 1;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public int getPartsMask()
/* 103:    */   {
/* 104: 87 */     return 1;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public int getPartMaxRotation(int part, boolean sec)
/* 108:    */   {
/* 109: 92 */     if (sec) {
/* 110: 92 */       return 0;
/* 111:    */     }
/* 112: 93 */     return 3;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public int getPartRotation(int part, boolean sec)
/* 116:    */   {
/* 117: 97 */     if (sec) {
/* 118: 97 */       return 0;
/* 119:    */     }
/* 120: 98 */     return this.Rotation;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void setPartRotation(int part, boolean sec, int rot)
/* 124:    */   {
/* 125:102 */     if (sec) {
/* 126:102 */       return;
/* 127:    */     }
/* 128:103 */     this.Rotation = rot;
/* 129:104 */     updateBlockChange();
/* 130:    */   }
/* 131:    */   
/* 132:    */   public byte[] getFramePacket()
/* 133:    */   {
/* 134:110 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 135:111 */     pkt.subId = 8;
/* 136:112 */     writeToPacket(pkt);
/* 137:113 */     pkt.headout.write(pkt.subId);
/* 138:114 */     return pkt.toByteArray();
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void handleFramePacket(byte[] ba)
/* 142:    */     throws IOException
/* 143:    */   {
/* 144:118 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 145:119 */     pkt.subId = pkt.getByte();
/* 146:120 */     readFromPacket(pkt);
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void onFrameRefresh(ym iba) {}
/* 150:    */   
/* 151:    */   public void onFramePickup(ym iba) {}
/* 152:    */   
/* 153:    */   public void onFrameDrop() {}
/* 154:    */   
/* 155:    */   public void a(bq tag)
/* 156:    */   {
/* 157:130 */     super.a(tag);
/* 158:    */     
/* 159:132 */     int k = tag.c("ps");
/* 160:133 */     this.Rotation = tag.c("rot");
/* 161:    */     
/* 162:135 */     this.Active = ((k & 0x1) > 0);
/* 163:136 */     this.Powered = ((k & 0x2) > 0);
/* 164:137 */     this.Delay = ((k & 0x4) > 0);
/* 165:138 */     this.Charged = ((k & 0x8) > 0);
/* 166:    */   }
/* 167:    */   
/* 168:    */   public void b(bq tag)
/* 169:    */   {
/* 170:142 */     super.b(tag);
/* 171:    */     
/* 172:144 */     int ps = (this.Active ? 1 : 0) | (this.Powered ? 2 : 0) | (this.Delay ? 4 : 0) | (this.Charged ? 8 : 0);
/* 173:145 */     tag.a("ps", (byte)ps);
/* 174:146 */     tag.a("rot", (byte)this.Rotation);
/* 175:    */   }
/* 176:    */   
/* 177:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 178:    */     throws IOException
/* 179:    */   {
/* 180:151 */     this.Rotation = pkt.getByte();
/* 181:152 */     int ps = pkt.getByte();
/* 182:153 */     this.Active = ((ps & 0x1) > 0);
/* 183:154 */     this.Powered = ((ps & 0x2) > 0);
/* 184:155 */     this.Delay = ((ps & 0x4) > 0);
/* 185:156 */     this.Charged = ((ps & 0x8) > 0);
/* 186:    */   }
/* 187:    */   
/* 188:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 189:    */   {
/* 190:160 */     pkt.addByte(this.Rotation);
/* 191:161 */     int ps = (this.Active ? 1 : 0) | (this.Powered ? 2 : 0) | (this.Delay ? 4 : 0) | (this.Charged ? 8 : 0);
/* 192:162 */     pkt.addByte(ps);
/* 193:    */   }
/* 194:    */   
/* 195:    */   public ef l()
/* 196:    */   {
/* 197:166 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 198:167 */     packet.subId = 8;
/* 199:168 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 200:169 */     packet.zCoord = this.n;
/* 201:170 */     writeToPacket(packet);
/* 202:171 */     packet.encode();
/* 203:172 */     return packet;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public void handlePacket(Packet211TileDesc packet)
/* 207:    */   {
/* 208:    */     try
/* 209:    */     {
/* 210:177 */       if (packet.subId != 8) {
/* 211:177 */         return;
/* 212:    */       }
/* 213:178 */       readFromPacket(packet);
/* 214:    */     }
/* 215:    */     catch (IOException e) {}
/* 216:180 */     this.k.i(this.l, this.m, this.n);
/* 217:    */   }
/* 218:    */   
/* 219:183 */   public int Rotation = 0;
/* 220:184 */   public boolean Active = false;
/* 221:184 */   public boolean Powered = false;
/* 222:184 */   public boolean Delay = false;
/* 223:184 */   public boolean Charged = false;
/* 224:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileMachinePanel
 * JD-Core Version:    0.7.0.1
 */