/*   1:    */ package com.eloraam.redpower.lighting;
/*   2:    */ 
/*   3:    */ import aoe;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.RedPowerLighting;
/*   6:    */ import com.eloraam.redpower.core.IConnectable;
/*   7:    */ import com.eloraam.redpower.core.IFrameSupport;
/*   8:    */ import com.eloraam.redpower.core.IHandlePackets;
/*   9:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  10:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  11:    */ import com.eloraam.redpower.core.TileExtended;
/*  12:    */ import ef;
/*  13:    */ import java.io.ByteArrayOutputStream;
/*  14:    */ import java.io.IOException;
/*  15:    */ import java.util.ArrayList;
/*  16:    */ import md;
/*  17:    */ import ur;
/*  18:    */ import yc;
/*  19:    */ import ym;
/*  20:    */ 
/*  21:    */ public class TileShapedLamp
/*  22:    */   extends TileExtended
/*  23:    */   implements IHandlePackets, IFrameSupport, IConnectable
/*  24:    */ {
/*  25:    */   public int getConnectableMask()
/*  26:    */   {
/*  27: 29 */     return 16777216 << this.Rotation | 15 << (this.Rotation << 2);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public int getConnectClass(int side)
/*  31:    */   {
/*  32: 33 */     return 1;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public int getCornerPowerMode()
/*  36:    */   {
/*  37: 36 */     return 0;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void onBlockPlaced(ur ist, int side, md ent)
/*  41:    */   {
/*  42: 42 */     this.Rotation = (side ^ 0x1);
/*  43: 43 */     onBlockNeighborChange(0);
/*  44: 44 */     this.Inverted = ((ist.j() & 0x10) > 0);
/*  45: 45 */     this.Color = (ist.j() & 0xF);
/*  46: 46 */     this.Style = ((ist.j() & 0x3FF) >> 5);
/*  47:    */   }
/*  48:    */   
/*  49:    */   public int getBlockID()
/*  50:    */   {
/*  51: 50 */     return RedPowerLighting.blockShapedLamp.cm;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public int getExtendedID()
/*  55:    */   {
/*  56: 54 */     return 0;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void onBlockNeighborChange(int l)
/*  60:    */   {
/*  61: 58 */     int mask = getConnectableMask();
/*  62: 59 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, mask & 0xFFFFFF, mask >> 24))
/*  63:    */     {
/*  64: 61 */       if (this.Powered) {
/*  65: 61 */         return;
/*  66:    */       }
/*  67: 62 */       this.Powered = true;
/*  68: 63 */       updateBlock();
/*  69: 64 */       this.k.z(this.l, this.m, this.n);
/*  70:    */     }
/*  71:    */     else
/*  72:    */     {
/*  73: 66 */       if (!this.Powered) {
/*  74: 66 */         return;
/*  75:    */       }
/*  76: 67 */       this.Powered = false;
/*  77: 68 */       updateBlock();
/*  78: 69 */       this.k.z(this.l, this.m, this.n);
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   public int getLightValue()
/*  83:    */   {
/*  84: 74 */     if (this.Powered != this.Inverted) {
/*  85: 74 */       return 15;
/*  86:    */     }
/*  87: 75 */     return 0;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void addHarvestContents(ArrayList ist)
/*  91:    */   {
/*  92: 79 */     ur is = new ur(getBlockID(), 1, (getExtendedID() << 10) + (this.Style << 5) + (this.Inverted ? 16 : 0) + this.Color);
/*  93:    */     
/*  94:    */ 
/*  95: 82 */     ist.add(is);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public static aoe getRotatedBB(float x1, float y1, float z1, float x2, float y2, float z2, int rot)
/*  99:    */   {
/* 100: 90 */     switch (rot)
/* 101:    */     {
/* 102:    */     case 0: 
/* 103: 91 */       return aoe.a(x1, y1, z1, x2, y2, z2);
/* 104:    */     case 1: 
/* 105: 92 */       return aoe.a(x1, 1.0F - y2, z1, x2, 1.0F - y1, z2);
/* 106:    */     case 2: 
/* 107: 93 */       return aoe.a(x1, z1, y1, x2, z2, y2);
/* 108:    */     case 3: 
/* 109: 94 */       return aoe.a(x1, 1.0F - z2, 1.0F - y2, x2, 1.0F - z1, 1.0F - y1);
/* 110:    */     case 4: 
/* 111: 95 */       return aoe.a(y1, x1, z1, y2, x2, z2);
/* 112:    */     }
/* 113: 97 */     return aoe.a(1.0F - y2, 1.0F - x2, z1, 1.0F - y1, 1.0F - x1, z2);
/* 114:    */   }
/* 115:    */   
/* 116:    */   public aoe getBounds()
/* 117:    */   {
/* 118:103 */     switch (this.Style)
/* 119:    */     {
/* 120:    */     case 0: 
/* 121:105 */       return getRotatedBB(0.125F, 0.0F, 0.125F, 0.875F, 0.5F, 0.875F, this.Rotation);
/* 122:    */     case 1: 
/* 123:108 */       return getRotatedBB(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.75F, 0.8125F, this.Rotation);
/* 124:    */     }
/* 125:111 */     return getRotatedBB(0.125F, 0.0F, 0.125F, 0.875F, 0.5F, 0.875F, this.Rotation);
/* 126:    */   }
/* 127:    */   
/* 128:    */   public byte[] getFramePacket()
/* 129:    */   {
/* 130:118 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 131:119 */     pkt.subId = 11;
/* 132:120 */     writeToPacket(pkt);
/* 133:121 */     pkt.headout.write(pkt.subId);
/* 134:122 */     return pkt.toByteArray();
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void handleFramePacket(byte[] ba)
/* 138:    */     throws IOException
/* 139:    */   {
/* 140:126 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 141:127 */     pkt.subId = pkt.getByte();
/* 142:128 */     readFromPacket(pkt);
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void onFrameRefresh(ym iba) {}
/* 146:    */   
/* 147:    */   public void onFramePickup(ym iba) {}
/* 148:    */   
/* 149:    */   public void onFrameDrop() {}
/* 150:    */   
/* 151:    */   public void a(bq tag)
/* 152:    */   {
/* 153:138 */     super.a(tag);
/* 154:    */     
/* 155:140 */     int k = tag.c("ps");
/* 156:141 */     this.Rotation = tag.c("rot");
/* 157:142 */     this.Powered = ((k & 0x1) > 0);
/* 158:143 */     this.Inverted = ((k & 0x2) > 0);
/* 159:    */     
/* 160:145 */     this.Color = tag.c("color");
/* 161:146 */     this.Style = tag.c("style");
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void b(bq tag)
/* 165:    */   {
/* 166:150 */     super.b(tag);
/* 167:    */     
/* 168:152 */     int ps = (this.Powered ? 1 : 0) | (this.Inverted ? 2 : 0);
/* 169:153 */     tag.a("ps", (byte)ps);
/* 170:154 */     tag.a("rot", (byte)this.Rotation);
/* 171:    */     
/* 172:156 */     tag.a("color", (byte)this.Color);
/* 173:157 */     tag.a("style", (byte)this.Style);
/* 174:    */   }
/* 175:    */   
/* 176:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 177:    */     throws IOException
/* 178:    */   {
/* 179:162 */     this.Rotation = pkt.getByte();
/* 180:163 */     this.Color = pkt.getByte();
/* 181:164 */     this.Style = pkt.getByte();
/* 182:165 */     int ps = pkt.getByte();
/* 183:166 */     this.Powered = ((ps & 0x1) > 0);
/* 184:167 */     this.Inverted = ((ps & 0x2) > 0);
/* 185:    */     
/* 186:169 */     this.k.z(this.l, this.m, this.n);
/* 187:    */   }
/* 188:    */   
/* 189:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 190:    */   {
/* 191:173 */     pkt.addByte(this.Rotation);
/* 192:174 */     pkt.addByte(this.Color);
/* 193:175 */     pkt.addByte(this.Style);
/* 194:176 */     int ps = (this.Powered ? 1 : 0) | (this.Inverted ? 2 : 0);
/* 195:177 */     pkt.addByte(ps);
/* 196:    */   }
/* 197:    */   
/* 198:    */   public ef l()
/* 199:    */   {
/* 200:181 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 201:182 */     packet.subId = 7;
/* 202:183 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 203:184 */     packet.zCoord = this.n;
/* 204:185 */     writeToPacket(packet);
/* 205:186 */     packet.encode();
/* 206:187 */     return packet;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public void handlePacket(Packet211TileDesc packet)
/* 210:    */   {
/* 211:    */     try
/* 212:    */     {
/* 213:192 */       if (packet.subId != 7) {
/* 214:192 */         return;
/* 215:    */       }
/* 216:193 */       readFromPacket(packet);
/* 217:    */     }
/* 218:    */     catch (IOException e) {}
/* 219:195 */     this.k.i(this.l, this.m, this.n);
/* 220:    */   }
/* 221:    */   
/* 222:198 */   public int Rotation = 0;
/* 223:199 */   public boolean Powered = false;
/* 224:199 */   public boolean Inverted = false;
/* 225:200 */   public int Style = 0;
/* 226:201 */   public int Color = 0;
/* 227:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.lighting.TileShapedLamp
 * JD-Core Version:    0.7.0.1
 */