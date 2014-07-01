/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import bq;
/*   5:    */ import ef;
/*   6:    */ import java.io.ByteArrayOutputStream;
/*   7:    */ import java.io.IOException;
/*   8:    */ import java.util.Arrays;
/*   9:    */ import yc;
/*  10:    */ import ym;
/*  11:    */ 
/*  12:    */ public class TileCovered
/*  13:    */   extends TileCoverable
/*  14:    */   implements IHandlePackets, IFrameSupport
/*  15:    */ {
/*  16:    */   public void replaceWithCovers()
/*  17:    */   {
/*  18: 18 */     CoverLib.replaceWithCovers(this.k, this.l, this.m, this.n, this.CoverSides, this.Covers);
/*  19:    */   }
/*  20:    */   
/*  21:    */   public boolean canUpdate()
/*  22:    */   {
/*  23: 24 */     return false;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getExtendedID()
/*  27:    */   {
/*  28: 29 */     return 0;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void onBlockNeighborChange(int l)
/*  32:    */   {
/*  33: 33 */     if (this.CoverSides == 0) {
/*  34: 33 */       deleteBlock();
/*  35:    */     }
/*  36:    */   }
/*  37:    */   
/*  38:    */   public int getBlockID()
/*  39:    */   {
/*  40: 37 */     return CoverLib.blockCoverPlate.cm;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public boolean canAddCover(int side, int cover)
/*  44:    */   {
/*  45: 43 */     if ((this.CoverSides & 1 << side) > 0) {
/*  46: 43 */       return false;
/*  47:    */     }
/*  48: 45 */     short[] test = Arrays.copyOf(this.Covers, 29);
/*  49: 46 */     test[side] = ((short)cover);
/*  50: 47 */     if (!CoverLib.checkPlacement(this.CoverSides | 1 << side, test, 0, false)) {
/*  51: 48 */       return false;
/*  52:    */     }
/*  53: 49 */     return true;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public boolean tryAddCover(int side, int cover)
/*  57:    */   {
/*  58: 53 */     if (!canAddCover(side, cover)) {
/*  59: 54 */       return false;
/*  60:    */     }
/*  61: 55 */     this.CoverSides |= 1 << side;
/*  62: 56 */     this.Covers[side] = ((short)cover);
/*  63: 57 */     updateBlockChange();
/*  64:    */     
/*  65: 59 */     return true;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public int tryRemoveCover(int side)
/*  69:    */   {
/*  70: 64 */     if ((this.CoverSides & 1 << side) == 0) {
/*  71: 64 */       return -1;
/*  72:    */     }
/*  73: 65 */     this.CoverSides &= (1 << side ^ 0xFFFFFFFF);
/*  74: 66 */     int tr = this.Covers[side];this.Covers[side] = 0;
/*  75:    */     
/*  76: 68 */     updateBlockChange();
/*  77: 69 */     return tr;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public int getCover(int side)
/*  81:    */   {
/*  82: 73 */     if ((this.CoverSides & 1 << side) == 0) {
/*  83: 73 */       return -1;
/*  84:    */     }
/*  85: 74 */     return this.Covers[side];
/*  86:    */   }
/*  87:    */   
/*  88:    */   public int getCoverMask()
/*  89:    */   {
/*  90: 78 */     return this.CoverSides;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public boolean blockEmpty()
/*  94:    */   {
/*  95: 84 */     return this.CoverSides == 0;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public byte[] getFramePacket()
/*  99:    */   {
/* 100: 90 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 101: 91 */     pkt.subId = 5;
/* 102: 92 */     writeToPacket(pkt);
/* 103: 93 */     pkt.headout.write(pkt.subId);
/* 104: 94 */     return pkt.toByteArray();
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void handleFramePacket(byte[] ba)
/* 108:    */     throws IOException
/* 109:    */   {
/* 110: 98 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 111: 99 */     pkt.subId = pkt.getByte();
/* 112:100 */     readFromPacket(pkt);
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void onFrameRefresh(ym iba) {}
/* 116:    */   
/* 117:    */   public void onFramePickup(ym iba) {}
/* 118:    */   
/* 119:    */   public void onFrameDrop() {}
/* 120:    */   
/* 121:    */   public void a(bq nbttagcompound)
/* 122:    */   {
/* 123:110 */     super.a(nbttagcompound);
/* 124:    */     
/* 125:    */ 
/* 126:    */ 
/* 127:    */ 
/* 128:115 */     int cs2 = nbttagcompound.e("cvm") & 0x1FFFFFFF;
/* 129:116 */     this.CoverSides |= cs2;
/* 130:117 */     byte[] cov = nbttagcompound.j("cvs");
/* 131:118 */     if ((cov != null) && (cs2 > 0))
/* 132:    */     {
/* 133:119 */       int sp = 0;
/* 134:120 */       for (int i = 0; i < 29; i++) {
/* 135:121 */         if ((cs2 & 1 << i) != 0)
/* 136:    */         {
/* 137:122 */           this.Covers[i] = ((short)((cov[sp] & 0xFF) + ((cov[(sp + 1)] & 0xFF) << 8)));
/* 138:    */           
/* 139:124 */           sp += 2;
/* 140:    */         }
/* 141:    */       }
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void b(bq nbttagcompound)
/* 146:    */   {
/* 147:130 */     super.b(nbttagcompound);
/* 148:    */     
/* 149:132 */     nbttagcompound.a("cvm", this.CoverSides);
/* 150:133 */     byte[] cov = new byte[Integer.bitCount(this.CoverSides) * 2];
/* 151:134 */     int dp = 0;
/* 152:135 */     for (int i = 0; i < 29; i++) {
/* 153:136 */       if ((this.CoverSides & 1 << i) != 0)
/* 154:    */       {
/* 155:137 */         cov[dp] = ((byte)(this.Covers[i] & 0xFF));
/* 156:138 */         cov[(dp + 1)] = ((byte)(this.Covers[i] >> 8));
/* 157:139 */         dp += 2;
/* 158:    */       }
/* 159:    */     }
/* 160:141 */     nbttagcompound.a("cvs", cov);
/* 161:    */   }
/* 162:    */   
/* 163:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 164:    */     throws IOException
/* 165:    */   {
/* 166:146 */     if (pkt.subId != 5) {
/* 167:146 */       return;
/* 168:    */     }
/* 169:147 */     this.CoverSides = ((int)pkt.getUVLC());
/* 170:149 */     for (int i = 0; i < 29; i++) {
/* 171:150 */       if ((this.CoverSides & 1 << i) > 0) {
/* 172:151 */         this.Covers[i] = ((short)(int)pkt.getUVLC());
/* 173:    */       }
/* 174:    */     }
/* 175:    */   }
/* 176:    */   
/* 177:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 178:    */   {
/* 179:156 */     pkt.addUVLC(this.CoverSides);
/* 180:158 */     for (int i = 0; i < 29; i++) {
/* 181:159 */       if ((this.CoverSides & 1 << i) > 0) {
/* 182:160 */         pkt.addUVLC(this.Covers[i]);
/* 183:    */       }
/* 184:    */     }
/* 185:    */   }
/* 186:    */   
/* 187:    */   public ef l()
/* 188:    */   {
/* 189:165 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 190:166 */     packet.subId = 5;
/* 191:167 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 192:168 */     packet.zCoord = this.n;
/* 193:169 */     writeToPacket(packet);
/* 194:170 */     packet.encode();
/* 195:171 */     return packet;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void handlePacket(Packet211TileDesc packet)
/* 199:    */   {
/* 200:    */     try
/* 201:    */     {
/* 202:176 */       readFromPacket(packet);
/* 203:    */     }
/* 204:    */     catch (IOException e) {}
/* 205:178 */     this.k.i(this.l, this.m, this.n);
/* 206:    */   }
/* 207:    */   
/* 208:183 */   public int CoverSides = 0;
/* 209:184 */   public short[] Covers = new short[29];
/* 210:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TileCovered
 * JD-Core Version:    0.7.0.1
 */