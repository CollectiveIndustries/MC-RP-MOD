/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerMachine;
/*   5:    */ import com.eloraam.redpower.core.BlockExtended;
/*   6:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.CoverLib;
/*   9:    */ import com.eloraam.redpower.core.IFrameLink;
/*  10:    */ import com.eloraam.redpower.core.IFrameSupport;
/*  11:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  12:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  13:    */ import com.eloraam.redpower.core.TileCoverable;
/*  14:    */ import com.eloraam.redpower.core.WorldCoord;
/*  15:    */ import ef;
/*  16:    */ import java.io.ByteArrayOutputStream;
/*  17:    */ import java.io.IOException;
/*  18:    */ import java.util.ArrayList;
/*  19:    */ import java.util.Arrays;
/*  20:    */ import qx;
/*  21:    */ import ur;
/*  22:    */ import yc;
/*  23:    */ import ym;
/*  24:    */ 
/*  25:    */ public class TileFrame
/*  26:    */   extends TileCoverable
/*  27:    */   implements IHandlePackets, IFrameLink, IFrameSupport
/*  28:    */ {
/*  29:    */   public boolean isFrameMoving()
/*  30:    */   {
/*  31: 23 */     return false;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public boolean canFrameConnectIn(int dir)
/*  35:    */   {
/*  36: 27 */     return (this.StickySides & 1 << dir) > 0;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public boolean canFrameConnectOut(int dir)
/*  40:    */   {
/*  41: 31 */     return (this.StickySides & 1 << dir) > 0;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public WorldCoord getFrameLinkset()
/*  45:    */   {
/*  46: 34 */     return null;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public int getExtendedID()
/*  50:    */   {
/*  51: 39 */     return 0;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void onBlockNeighborChange(int l) {}
/*  55:    */   
/*  56:    */   public int getBlockID()
/*  57:    */   {
/*  58: 46 */     return RedPowerMachine.blockFrame.cm;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public int getPartsMask()
/*  62:    */   {
/*  63: 52 */     return this.CoverSides | 0x20000000;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public int getSolidPartsMask()
/*  67:    */   {
/*  68: 56 */     return this.CoverSides | 0x20000000;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public boolean blockEmpty()
/*  72:    */   {
/*  73: 60 */     return false;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void onHarvestPart(qx player, int part)
/*  77:    */   {
/*  78: 64 */     boolean change = false;
/*  79: 65 */     if (part == 29)
/*  80:    */     {
/*  81: 66 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(RedPowerMachine.blockFrame, 1));
/*  82: 69 */       if (this.CoverSides > 0)
/*  83:    */       {
/*  84: 70 */         replaceWithCovers();
/*  85: 71 */         updateBlockChange();
/*  86:    */       }
/*  87:    */       else
/*  88:    */       {
/*  89: 72 */         deleteBlock();
/*  90:    */       }
/*  91:    */     }
/*  92:    */     else
/*  93:    */     {
/*  94: 74 */       super.onHarvestPart(player, part);
/*  95: 75 */       return;
/*  96:    */     }
/*  97:    */   }
/*  98:    */   
/*  99:    */   public void addHarvestContents(ArrayList ist)
/* 100:    */   {
/* 101: 80 */     super.addHarvestContents(ist);
/* 102: 81 */     ist.add(new ur(RedPowerMachine.blockFrame, 1));
/* 103:    */   }
/* 104:    */   
/* 105:    */   public float getPartStrength(qx player, int part)
/* 106:    */   {
/* 107: 85 */     BlockExtended bl = RedPowerMachine.blockMachine;
/* 108: 86 */     if (part == 29) {
/* 109: 87 */       return player.getCurrentPlayerStrVsBlock(bl, 0) / (bl.getHardness() * 30.0F);
/* 110:    */     }
/* 111: 90 */     return super.getPartStrength(player, part);
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setPartBounds(BlockMultipart bl, int part)
/* 115:    */   {
/* 116: 94 */     if (part == 29) {
/* 117: 95 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 118:    */     } else {
/* 119: 97 */       super.setPartBounds(bl, part);
/* 120:    */     }
/* 121:    */   }
/* 122:    */   
/* 123:    */   public boolean canAddCover(int side, int cover)
/* 124:    */   {
/* 125:104 */     if (side > 5) {
/* 126:104 */       return false;
/* 127:    */     }
/* 128:105 */     int n = cover >> 8;
/* 129:106 */     if ((n != 0) && (n != 1) && (n != 3) && (n != 4)) {
/* 130:106 */       return false;
/* 131:    */     }
/* 132:107 */     if ((this.CoverSides & 1 << side) > 0) {
/* 133:107 */       return false;
/* 134:    */     }
/* 135:108 */     return true;
/* 136:    */   }
/* 137:    */   
/* 138:    */   void rebuildSticky()
/* 139:    */   {
/* 140:112 */     int ss = 0;
/* 141:113 */     for (int i = 0; i < 6; i++)
/* 142:    */     {
/* 143:114 */       int m = 1 << i;
/* 144:115 */       if ((this.CoverSides & m) == 0)
/* 145:    */       {
/* 146:116 */         ss |= m;
/* 147:    */       }
/* 148:    */       else
/* 149:    */       {
/* 150:118 */         int n = this.Covers[i] >> 8;
/* 151:119 */         if ((n == 1) || (n == 4)) {
/* 152:119 */           ss |= m;
/* 153:    */         }
/* 154:    */       }
/* 155:    */     }
/* 156:122 */     this.StickySides = ss;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public boolean tryAddCover(int side, int cover)
/* 160:    */   {
/* 161:126 */     if (!canAddCover(side, cover)) {
/* 162:126 */       return false;
/* 163:    */     }
/* 164:127 */     this.CoverSides |= 1 << side;
/* 165:128 */     this.Covers[side] = ((short)cover);
/* 166:129 */     rebuildSticky();
/* 167:130 */     updateBlockChange();
/* 168:131 */     return true;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public int tryRemoveCover(int side)
/* 172:    */   {
/* 173:136 */     if ((this.CoverSides & 1 << side) == 0) {
/* 174:136 */       return -1;
/* 175:    */     }
/* 176:137 */     this.CoverSides &= (1 << side ^ 0xFFFFFFFF);
/* 177:138 */     int tr = this.Covers[side];this.Covers[side] = 0;
/* 178:139 */     rebuildSticky();
/* 179:140 */     updateBlockChange();
/* 180:141 */     return tr;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public int getCover(int side)
/* 184:    */   {
/* 185:145 */     if ((this.CoverSides & 1 << side) == 0) {
/* 186:145 */       return -1;
/* 187:    */     }
/* 188:146 */     return this.Covers[side];
/* 189:    */   }
/* 190:    */   
/* 191:    */   public int getCoverMask()
/* 192:    */   {
/* 193:150 */     return this.CoverSides;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void replaceWithCovers()
/* 197:    */   {
/* 198:156 */     short[] covs = Arrays.copyOf(this.Covers, 29);
/* 199:157 */     CoverLib.replaceWithCovers(this.k, this.l, this.m, this.n, this.CoverSides, covs);
/* 200:    */   }
/* 201:    */   
/* 202:    */   public byte[] getFramePacket()
/* 203:    */   {
/* 204:164 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 205:165 */     pkt.subId = 9;
/* 206:166 */     writeToPacket(pkt);
/* 207:167 */     pkt.headout.write(pkt.subId);
/* 208:168 */     return pkt.toByteArray();
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void handleFramePacket(byte[] ba)
/* 212:    */     throws IOException
/* 213:    */   {
/* 214:172 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 215:173 */     pkt.subId = pkt.getByte();
/* 216:174 */     readFromPacket(pkt);
/* 217:    */   }
/* 218:    */   
/* 219:    */   public void onFramePickup(ym iba) {}
/* 220:    */   
/* 221:    */   public void onFrameRefresh(ym iba) {}
/* 222:    */   
/* 223:    */   public void onFrameDrop() {}
/* 224:    */   
/* 225:    */   public void a(bq nbttagcompound)
/* 226:    */   {
/* 227:184 */     super.a(nbttagcompound);
/* 228:    */     
/* 229:    */ 
/* 230:    */ 
/* 231:188 */     int cs2 = nbttagcompound.e("cvm") & 0x3F;
/* 232:189 */     this.CoverSides |= cs2;
/* 233:190 */     byte[] cov = nbttagcompound.j("cvs");
/* 234:191 */     if ((cov != null) && (cs2 > 0))
/* 235:    */     {
/* 236:192 */       int sp = 0;
/* 237:193 */       for (int i = 0; i < 6; i++) {
/* 238:194 */         if ((cs2 & 1 << i) != 0)
/* 239:    */         {
/* 240:195 */           this.Covers[i] = ((short)((cov[sp] & 0xFF) + ((cov[(sp + 1)] & 0xFF) << 8)));
/* 241:    */           
/* 242:197 */           sp += 2;
/* 243:    */         }
/* 244:    */       }
/* 245:    */     }
/* 246:200 */     rebuildSticky();
/* 247:    */   }
/* 248:    */   
/* 249:    */   public void b(bq nbttagcompound)
/* 250:    */   {
/* 251:204 */     super.b(nbttagcompound);
/* 252:    */     
/* 253:206 */     nbttagcompound.a("cvm", this.CoverSides);
/* 254:207 */     byte[] cov = new byte[Integer.bitCount(this.CoverSides) * 2];
/* 255:208 */     int dp = 0;
/* 256:209 */     for (int i = 0; i < 6; i++) {
/* 257:210 */       if ((this.CoverSides & 1 << i) != 0)
/* 258:    */       {
/* 259:211 */         cov[dp] = ((byte)(this.Covers[i] & 0xFF));
/* 260:212 */         cov[(dp + 1)] = ((byte)(this.Covers[i] >> 8));
/* 261:213 */         dp += 2;
/* 262:    */       }
/* 263:    */     }
/* 264:215 */     nbttagcompound.a("cvs", cov);
/* 265:    */   }
/* 266:    */   
/* 267:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 268:    */     throws IOException
/* 269:    */   {
/* 270:220 */     if (pkt.subId != 9) {
/* 271:220 */       return;
/* 272:    */     }
/* 273:221 */     this.CoverSides = ((int)pkt.getUVLC());
/* 274:223 */     for (int i = 0; i < 6; i++) {
/* 275:224 */       if ((this.CoverSides & 1 << i) > 0) {
/* 276:225 */         this.Covers[i] = ((short)(int)pkt.getUVLC());
/* 277:    */       }
/* 278:    */     }
/* 279:227 */     rebuildSticky();
/* 280:    */   }
/* 281:    */   
/* 282:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 283:    */   {
/* 284:231 */     pkt.addUVLC(this.CoverSides);
/* 285:233 */     for (int i = 0; i < 6; i++) {
/* 286:234 */       if ((this.CoverSides & 1 << i) > 0) {
/* 287:235 */         pkt.addUVLC(this.Covers[i]);
/* 288:    */       }
/* 289:    */     }
/* 290:    */   }
/* 291:    */   
/* 292:    */   public ef l()
/* 293:    */   {
/* 294:240 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 295:241 */     packet.subId = 9;
/* 296:242 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 297:243 */     packet.zCoord = this.n;
/* 298:244 */     writeToPacket(packet);
/* 299:245 */     packet.encode();
/* 300:246 */     return packet;
/* 301:    */   }
/* 302:    */   
/* 303:    */   public void handlePacket(Packet211TileDesc packet)
/* 304:    */   {
/* 305:    */     try
/* 306:    */     {
/* 307:251 */       readFromPacket(packet);
/* 308:    */     }
/* 309:    */     catch (IOException e) {}
/* 310:253 */     this.k.i(this.l, this.m, this.n);
/* 311:    */   }
/* 312:    */   
/* 313:258 */   public int CoverSides = 0;
/* 314:259 */   public int StickySides = 63;
/* 315:260 */   public short[] Covers = new short[6];
/* 316:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileFrame
 * JD-Core Version:    0.7.0.1
 */