/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.RedPowerBase;
/*   6:    */ import com.eloraam.redpower.RedPowerMachine;
/*   7:    */ import com.eloraam.redpower.base.BlockMicro;
/*   8:    */ import com.eloraam.redpower.core.BlockExtended;
/*   9:    */ import com.eloraam.redpower.core.BlockMultipart;
/*  10:    */ import com.eloraam.redpower.core.CoreLib;
/*  11:    */ import com.eloraam.redpower.core.CoreProxy;
/*  12:    */ import com.eloraam.redpower.core.FluidBuffer;
/*  13:    */ import com.eloraam.redpower.core.IPipeConnectable;
/*  14:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  15:    */ import com.eloraam.redpower.core.PipeLib;
/*  16:    */ import com.eloraam.redpower.core.TileCovered;
/*  17:    */ import com.eloraam.redpower.core.WorldCoord;
/*  18:    */ import java.io.IOException;
/*  19:    */ import java.util.ArrayList;
/*  20:    */ import qx;
/*  21:    */ import ur;
/*  22:    */ import yc;
/*  23:    */ import ym;
/*  24:    */ 
/*  25:    */ public class TilePipe
/*  26:    */   extends TileCovered
/*  27:    */   implements IPipeConnectable
/*  28:    */ {
/*  29:    */   public int getPipeConnectableSides()
/*  30:    */   {
/*  31: 28 */     int tr = 63;
/*  32: 29 */     for (int i = 0; i < 6; i++) {
/*  33: 30 */       if (((this.CoverSides & 1 << i) > 0) && 
/*  34: 31 */         (this.Covers[i] >> 8 < 3)) {
/*  35: 32 */         tr &= (1 << i ^ 0xFFFFFFFF);
/*  36:    */       }
/*  37:    */     }
/*  38: 35 */     return tr;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getPipeFlangeSides()
/*  42:    */   {
/*  43: 39 */     cacheCon();
/*  44: 40 */     if ((this.ConCache == 3) || (this.ConCache == 12) || (this.ConCache == 48)) {
/*  45: 41 */       return 0;
/*  46:    */     }
/*  47: 42 */     if (Integer.bitCount(this.ConCache) == 1) {
/*  48: 43 */       return 0;
/*  49:    */     }
/*  50: 44 */     return this.ConCache;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public int getPipePressure(int side)
/*  54:    */   {
/*  55: 48 */     return this.Pressure;
/*  56:    */   }
/*  57:    */   
/*  58: 51 */   public FluidBuffer pipebuf = new FluidBuffer()
/*  59:    */   {
/*  60:    */     public any getParent()
/*  61:    */     {
/*  62: 53 */       return TilePipe.this;
/*  63:    */     }
/*  64:    */     
/*  65:    */     public void onChange()
/*  66:    */     {
/*  67: 56 */       TilePipe.this.dirtyBlock();
/*  68:    */     }
/*  69:    */   };
/*  70:    */   
/*  71:    */   public FluidBuffer getPipeBuffer(int side)
/*  72:    */   {
/*  73: 61 */     return this.pipebuf;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public boolean tryAddCover(int side, int cover)
/*  77:    */   {
/*  78: 67 */     if (!super.tryAddCover(side, cover)) {
/*  79: 68 */       return false;
/*  80:    */     }
/*  81: 69 */     uncache();
/*  82: 70 */     updateBlockChange();
/*  83: 71 */     return true;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public int tryRemoveCover(int side)
/*  87:    */   {
/*  88: 75 */     int tr = super.tryRemoveCover(side);
/*  89: 76 */     if (tr < 0) {
/*  90: 76 */       return -1;
/*  91:    */     }
/*  92: 77 */     uncache();
/*  93: 78 */     updateBlockChange();
/*  94: 79 */     return tr;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public boolean canUpdate()
/*  98:    */   {
/*  99: 85 */     return true;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void g()
/* 103:    */   {
/* 104: 89 */     super.g();
/* 105: 90 */     if (CoreLib.isClient(this.k)) {
/* 106: 90 */       return;
/* 107:    */     }
/* 108: 92 */     int pr = 0;int d = 0;int min = 0;int max = 0;
/* 109: 93 */     cacheCon();
/* 110: 94 */     for (int i = 0; i < 6; i++) {
/* 111: 95 */       if ((this.ConCache & 1 << i) != 0)
/* 112:    */       {
/* 113: 97 */         WorldCoord wc = new WorldCoord(this);
/* 114: 98 */         wc.step(i);
/* 115:    */         
/* 116:100 */         Integer p = PipeLib.getPressure(this.k, wc, i ^ 0x1);
/* 117:101 */         if (p != null)
/* 118:    */         {
/* 119:109 */           min = Math.min(p.intValue(), min);
/* 120:110 */           max = Math.max(p.intValue(), max);
/* 121:111 */           pr += p.intValue();
/* 122:112 */           d++;
/* 123:    */         }
/* 124:    */       }
/* 125:    */     }
/* 126:114 */     if (d == 0)
/* 127:    */     {
/* 128:115 */       this.Pressure = 0;
/* 129:    */     }
/* 130:    */     else
/* 131:    */     {
/* 132:117 */       if (min < 0) {
/* 133:117 */         min++;
/* 134:    */       }
/* 135:117 */       if (max > 0) {
/* 136:117 */         max--;
/* 137:    */       }
/* 138:118 */       this.Pressure = Math.max(min, Math.min(max, pr / d + Integer.signum(pr)));
/* 139:    */     }
/* 140:122 */     PipeLib.movePipeLiquid(this.k, this, new WorldCoord(this), this.ConCache);
/* 141:    */     
/* 142:124 */     dirtyBlock();
/* 143:126 */     if ((!CoreLib.isClient(this.k)) && ((this.k.G() & 0x10) == 0L)) {
/* 144:128 */       sendItemUpdate();
/* 145:    */     }
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void uncache()
/* 149:    */   {
/* 150:134 */     this.ConCache = -1;
/* 151:135 */     this.Flanges = -1;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void cacheCon()
/* 155:    */   {
/* 156:139 */     if (this.ConCache < 0) {
/* 157:140 */       this.ConCache = PipeLib.getConnections(this.k, this.l, this.m, this.n);
/* 158:    */     }
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void cacheFlange()
/* 162:    */   {
/* 163:146 */     if (this.Flanges >= 0) {
/* 164:146 */       return;
/* 165:    */     }
/* 166:147 */     cacheCon();
/* 167:    */     
/* 168:149 */     this.Flanges = getPipeFlangeSides();
/* 169:150 */     this.Flanges |= PipeLib.getFlanges(this.k, new WorldCoord(this), this.ConCache);
/* 170:    */   }
/* 171:    */   
/* 172:    */   public void onFrameRefresh(ym iba)
/* 173:    */   {
/* 174:184 */     if (this.ConCache < 0) {
/* 175:185 */       this.ConCache = PipeLib.getConnections(iba, this.l, this.m, this.n);
/* 176:    */     }
/* 177:188 */     this.Flanges = 0;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public int getBlockID()
/* 181:    */   {
/* 182:194 */     return RedPowerBase.blockMicro.cm;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public int getExtendedID()
/* 186:    */   {
/* 187:198 */     return 7;
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void onBlockNeighborChange(int l)
/* 191:    */   {
/* 192:202 */     int cfl = this.Flanges;int ccc = this.ConCache;
/* 193:203 */     uncache();
/* 194:204 */     cacheFlange();
/* 195:205 */     if ((this.Flanges != cfl) || (ccc != this.ConCache)) {
/* 196:206 */       updateBlock();
/* 197:    */     }
/* 198:    */   }
/* 199:    */   
/* 200:    */   public int getPartsMask()
/* 201:    */   {
/* 202:212 */     return this.CoverSides | 0x20000000;
/* 203:    */   }
/* 204:    */   
/* 205:    */   public int getSolidPartsMask()
/* 206:    */   {
/* 207:216 */     return this.CoverSides | 0x20000000;
/* 208:    */   }
/* 209:    */   
/* 210:    */   public boolean blockEmpty()
/* 211:    */   {
/* 212:220 */     return false;
/* 213:    */   }
/* 214:    */   
/* 215:    */   public void onHarvestPart(qx player, int part)
/* 216:    */   {
/* 217:224 */     if (part == 29)
/* 218:    */     {
/* 219:225 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(RedPowerBase.blockMicro.cm, 1, getExtendedID() << 8));
/* 220:231 */       if (this.CoverSides > 0) {
/* 221:231 */         replaceWithCovers();
/* 222:    */       } else {
/* 223:232 */         deleteBlock();
/* 224:    */       }
/* 225:    */     }
/* 226:    */     else
/* 227:    */     {
/* 228:234 */       super.onHarvestPart(player, part);
/* 229:235 */       return;
/* 230:    */     }
/* 231:237 */     uncache();
/* 232:238 */     updateBlockChange();
/* 233:    */   }
/* 234:    */   
/* 235:    */   public void addHarvestContents(ArrayList ist)
/* 236:    */   {
/* 237:242 */     super.addHarvestContents(ist);
/* 238:    */     
/* 239:244 */     ist.add(new ur(RedPowerBase.blockMicro.cm, 1, getExtendedID() << 8));
/* 240:    */   }
/* 241:    */   
/* 242:    */   public float getPartStrength(qx player, int part)
/* 243:    */   {
/* 244:249 */     BlockExtended bl = RedPowerMachine.blockMachine;
/* 245:250 */     if (part == 29) {
/* 246:251 */       return player.getCurrentPlayerStrVsBlock(bl, 0) / (bl.getHardness() * 30.0F);
/* 247:    */     }
/* 248:254 */     return super.getPartStrength(player, part);
/* 249:    */   }
/* 250:    */   
/* 251:    */   public void setPartBounds(BlockMultipart bl, int part)
/* 252:    */   {
/* 253:258 */     if (part == 29) {
/* 254:259 */       bl.a(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/* 255:    */     } else {
/* 256:261 */       super.setPartBounds(bl, part);
/* 257:    */     }
/* 258:    */   }
/* 259:    */   
/* 260:    */   public void a(bq tag)
/* 261:    */   {
/* 262:268 */     super.a(tag);
/* 263:269 */     this.Pressure = tag.e("psi");
/* 264:270 */     this.pipebuf.readFromNBT(tag, "buf");
/* 265:    */   }
/* 266:    */   
/* 267:    */   public void b(bq tag)
/* 268:    */   {
/* 269:274 */     super.b(tag);
/* 270:275 */     tag.a("psi", this.Pressure);
/* 271:276 */     this.pipebuf.writeToNBT(tag, "buf");
/* 272:    */   }
/* 273:    */   
/* 274:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 275:    */     throws IOException
/* 276:    */   {
/* 277:282 */     if (pkt.subId == 11)
/* 278:    */     {
/* 279:283 */       this.pipebuf.readFromPacket(pkt);
/* 280:284 */       updateBlock();
/* 281:    */     }
/* 282:    */     else
/* 283:    */     {
/* 284:286 */       super.readFromPacket(pkt);
/* 285:287 */       this.pipebuf.readFromPacket(pkt);
/* 286:288 */       this.ConCache = -1;this.Flanges = -1;
/* 287:289 */       updateBlock();
/* 288:    */     }
/* 289:    */   }
/* 290:    */   
/* 291:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 292:    */   {
/* 293:294 */     super.writeToPacket(pkt);
/* 294:295 */     this.pipebuf.writeToPacket(pkt);
/* 295:    */   }
/* 296:    */   
/* 297:    */   protected void sendItemUpdate()
/* 298:    */   {
/* 299:299 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 300:300 */     pkt.subId = 11;
/* 301:301 */     pkt.xCoord = this.l;pkt.yCoord = this.m;
/* 302:302 */     pkt.zCoord = this.n;
/* 303:    */     
/* 304:304 */     this.pipebuf.writeToPacket(pkt);
/* 305:    */     
/* 306:306 */     pkt.encode();
/* 307:307 */     CoreProxy.sendPacketToPosition(this.k, pkt, this.l, this.n);
/* 308:    */   }
/* 309:    */   
/* 310:    */   public void handlePacket(Packet211TileDesc packet)
/* 311:    */   {
/* 312:    */     try
/* 313:    */     {
/* 314:312 */       readFromPacket(packet);
/* 315:    */     }
/* 316:    */     catch (IOException e) {}
/* 317:    */   }
/* 318:    */   
/* 319:315 */   public int Pressure = 0;
/* 320:317 */   public int ConCache = -1;
/* 321:317 */   public int Flanges = -1;
/* 322:318 */   private boolean hasChanged = false;
/* 323:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TilePipe
 * JD-Core Version:    0.7.0.1
 */