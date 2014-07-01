/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerBase;
/*   5:    */ import com.eloraam.redpower.RedPowerControl;
/*   6:    */ import com.eloraam.redpower.base.ItemScrewdriver;
/*   7:    */ import com.eloraam.redpower.core.BlockExtended;
/*   8:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   9:    */ import com.eloraam.redpower.core.CoreLib;
/*  10:    */ import com.eloraam.redpower.core.IFrameSupport;
/*  11:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  12:    */ import com.eloraam.redpower.core.IRedPowerConnectable;
/*  13:    */ import com.eloraam.redpower.core.IRedbusConnectable;
/*  14:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  15:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  16:    */ import com.eloraam.redpower.core.TileMultipart;
/*  17:    */ import ef;
/*  18:    */ import java.io.ByteArrayOutputStream;
/*  19:    */ import java.io.IOException;
/*  20:    */ import java.util.ArrayList;
/*  21:    */ import md;
/*  22:    */ import qw;
/*  23:    */ import qx;
/*  24:    */ import ur;
/*  25:    */ import yc;
/*  26:    */ import ym;
/*  27:    */ 
/*  28:    */ public class TileIOExpander
/*  29:    */   extends TileMultipart
/*  30:    */   implements IRedbusConnectable, IRedPowerConnectable, IHandlePackets, IFrameSupport
/*  31:    */ {
/*  32:    */   public int rbGetAddr()
/*  33:    */   {
/*  34: 29 */     return this.rbaddr;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void rbSetAddr(int addr)
/*  38:    */   {
/*  39: 33 */     this.rbaddr = addr;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public int rbRead(int reg)
/*  43:    */   {
/*  44: 37 */     switch (reg)
/*  45:    */     {
/*  46:    */     case 0: 
/*  47: 38 */       return this.RBuf & 0xFF;
/*  48:    */     case 1: 
/*  49: 39 */       return this.RBuf >> 8;
/*  50:    */     case 2: 
/*  51: 40 */       return this.WBufNew & 0xFF;
/*  52:    */     case 3: 
/*  53: 41 */       return this.WBufNew >> 8;
/*  54:    */     }
/*  55: 42 */     return 0;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void rbWrite(int reg, int dat)
/*  59:    */   {
/*  60: 47 */     dirtyBlock();
/*  61: 48 */     switch (reg)
/*  62:    */     {
/*  63:    */     case 0: 
/*  64:    */     case 2: 
/*  65: 50 */       this.WBufNew = (this.WBufNew & 0xFF00 | dat);
/*  66: 51 */       scheduleTick(2);
/*  67: 52 */       break;
/*  68:    */     case 1: 
/*  69:    */     case 3: 
/*  70: 54 */       this.WBufNew = (this.WBufNew & 0xFF | dat << 8);
/*  71: 55 */       scheduleTick(2);
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public int getConnectableMask()
/*  76:    */   {
/*  77: 63 */     return 15;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public int getConnectClass(int side)
/*  81:    */   {
/*  82: 67 */     if (side == CoreLib.rotToSide(this.Rotation)) {
/*  83: 68 */       return 18;
/*  84:    */     }
/*  85: 69 */     return 66;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public int getCornerPowerMode()
/*  89:    */   {
/*  90: 72 */     return 0;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public int getPoweringMask(int ch)
/*  94:    */   {
/*  95: 77 */     if (ch == 0) {
/*  96: 77 */       return 0;
/*  97:    */     }
/*  98: 78 */     if ((this.WBuf & 1 << ch - 1) > 0) {
/*  99: 79 */       return RedPowerLib.mapRotToCon(8, this.Rotation);
/* 100:    */     }
/* 101: 80 */     return 0;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 105:    */   {
/* 106: 87 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) + 1 & 0x3);
/* 107:    */   }
/* 108:    */   
/* 109:    */   public boolean onPartActivateSide(qx player, int part, int side)
/* 110:    */   {
/* 111: 92 */     if (player.ah())
/* 112:    */     {
/* 113: 93 */       if (CoreLib.isClient(this.k)) {
/* 114: 94 */         return false;
/* 115:    */       }
/* 116: 95 */       ur ist = player.bJ.g();
/* 117: 96 */       if (ist == null) {
/* 118: 96 */         return false;
/* 119:    */       }
/* 120: 97 */       if (!(ist.b() instanceof ItemScrewdriver)) {
/* 121: 98 */         return false;
/* 122:    */       }
/* 123: 99 */       player.openGui(RedPowerBase.instance, 3, this.k, this.l, this.m, this.n);
/* 124:    */       
/* 125:101 */       return false;
/* 126:    */     }
/* 127:103 */     return false;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void onTileTick()
/* 131:    */   {
/* 132:107 */     if (this.WBuf == this.WBufNew) {
/* 133:107 */       return;
/* 134:    */     }
/* 135:108 */     this.WBuf = this.WBufNew;
/* 136:109 */     onBlockNeighborChange(0);
/* 137:110 */     updateBlockChange();
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void onBlockNeighborChange(int l)
/* 141:    */   {
/* 142:116 */     boolean ch = false;
/* 143:117 */     for (int n = 0; n < 16; n++)
/* 144:    */     {
/* 145:118 */       int ps = RedPowerLib.getRotPowerState(this.k, this.l, this.m, this.n, 8, this.Rotation, n + 1);
/* 146:120 */       if (ps == 0)
/* 147:    */       {
/* 148:121 */         if ((this.RBuf & 1 << n) > 0)
/* 149:    */         {
/* 150:122 */           this.RBuf &= (1 << n ^ 0xFFFFFFFF);
/* 151:123 */           ch = true;
/* 152:    */         }
/* 153:    */       }
/* 154:126 */       else if ((this.RBuf & 1 << n) == 0)
/* 155:    */       {
/* 156:127 */         this.RBuf |= 1 << n;
/* 157:128 */         ch = true;
/* 158:    */       }
/* 159:    */     }
/* 160:132 */     if (ch) {
/* 161:132 */       updateBlock();
/* 162:    */     }
/* 163:    */   }
/* 164:    */   
/* 165:    */   public int getBlockID()
/* 166:    */   {
/* 167:136 */     return RedPowerControl.blockFlatPeripheral.cm;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public int getExtendedID()
/* 171:    */   {
/* 172:140 */     return 0;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void addHarvestContents(ArrayList ist)
/* 176:    */   {
/* 177:146 */     super.addHarvestContents(ist);
/* 178:147 */     ist.add(new ur(getBlockID(), 1, 0));
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void onHarvestPart(qx player, int part)
/* 182:    */   {
/* 183:151 */     breakBlock();
/* 184:    */   }
/* 185:    */   
/* 186:    */   public float getPartStrength(qx player, int part)
/* 187:    */   {
/* 188:155 */     return 0.1F;
/* 189:    */   }
/* 190:    */   
/* 191:    */   public boolean blockEmpty()
/* 192:    */   {
/* 193:158 */     return false;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void setPartBounds(BlockMultipart bl, int part)
/* 197:    */   {
/* 198:161 */     bl.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/* 199:    */   }
/* 200:    */   
/* 201:    */   public int getSolidPartsMask()
/* 202:    */   {
/* 203:164 */     return 1;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public int getPartsMask()
/* 207:    */   {
/* 208:165 */     return 1;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public byte[] getFramePacket()
/* 212:    */   {
/* 213:170 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 214:171 */     pkt.subId = 7;
/* 215:172 */     writeToPacket(pkt);
/* 216:173 */     pkt.headout.write(pkt.subId);
/* 217:174 */     return pkt.toByteArray();
/* 218:    */   }
/* 219:    */   
/* 220:    */   public void handleFramePacket(byte[] ba)
/* 221:    */     throws IOException
/* 222:    */   {
/* 223:178 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 224:179 */     pkt.subId = pkt.getByte();
/* 225:180 */     readFromPacket(pkt);
/* 226:    */   }
/* 227:    */   
/* 228:    */   public void onFrameRefresh(ym iba) {}
/* 229:    */   
/* 230:    */   public void onFramePickup(ym iba) {}
/* 231:    */   
/* 232:    */   public void onFrameDrop() {}
/* 233:    */   
/* 234:    */   public void a(bq tag)
/* 235:    */   {
/* 236:190 */     super.a(tag);
/* 237:191 */     this.Rotation = tag.c("rot");
/* 238:    */     
/* 239:193 */     this.WBuf = (tag.d("wb") & 0xFFFF);
/* 240:194 */     this.WBufNew = (tag.d("wbn") & 0xFFFF);
/* 241:195 */     this.RBuf = (tag.d("rb") & 0xFFFF);
/* 242:196 */     this.rbaddr = (tag.c("rbaddr") & 0xFF);
/* 243:    */   }
/* 244:    */   
/* 245:    */   public void b(bq tag)
/* 246:    */   {
/* 247:200 */     super.b(tag);
/* 248:201 */     tag.a("rot", (byte)this.Rotation);
/* 249:    */     
/* 250:203 */     tag.a("wb", (short)this.WBuf);
/* 251:204 */     tag.a("wbn", (short)this.WBufNew);
/* 252:205 */     tag.a("rb", (short)this.RBuf);
/* 253:206 */     tag.a("rbaddr", (byte)this.rbaddr);
/* 254:    */   }
/* 255:    */   
/* 256:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 257:    */     throws IOException
/* 258:    */   {
/* 259:211 */     this.Rotation = pkt.getByte();
/* 260:212 */     this.WBuf = ((int)pkt.getUVLC());
/* 261:    */   }
/* 262:    */   
/* 263:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 264:    */   {
/* 265:216 */     pkt.addByte(this.Rotation);
/* 266:217 */     pkt.addUVLC(this.WBuf);
/* 267:    */   }
/* 268:    */   
/* 269:    */   public ef l()
/* 270:    */   {
/* 271:221 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 272:222 */     packet.subId = 7;
/* 273:223 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 274:224 */     packet.zCoord = this.n;
/* 275:225 */     writeToPacket(packet);
/* 276:226 */     packet.encode();
/* 277:227 */     return packet;
/* 278:    */   }
/* 279:    */   
/* 280:    */   public void handlePacket(Packet211TileDesc packet)
/* 281:    */   {
/* 282:    */     try
/* 283:    */     {
/* 284:232 */       if (packet.subId != 7) {
/* 285:232 */         return;
/* 286:    */       }
/* 287:233 */       readFromPacket(packet);
/* 288:    */     }
/* 289:    */     catch (IOException e) {}
/* 290:235 */     this.k.i(this.l, this.m, this.n);
/* 291:    */   }
/* 292:    */   
/* 293:238 */   public int Rotation = 0;
/* 294:240 */   public int WBuf = 0;
/* 295:240 */   public int WBufNew = 0;
/* 296:240 */   public int RBuf = 0;
/* 297:241 */   private int rbaddr = 3;
/* 298:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.TileIOExpander
 * JD-Core Version:    0.7.0.1
 */