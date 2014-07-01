/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   6:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.CoreProxy;
/*   9:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  10:    */ import com.eloraam.redpower.core.ITubeConnectable;
/*  11:    */ import com.eloraam.redpower.core.ITubeFlow;
/*  12:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  13:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  14:    */ import com.eloraam.redpower.core.TubeFlow;
/*  15:    */ import com.eloraam.redpower.core.TubeFlow.TubeScheduleContext;
/*  16:    */ import com.eloraam.redpower.core.TubeItem;
/*  17:    */ import com.eloraam.redpower.core.WorldCoord;
/*  18:    */ import java.io.IOException;
/*  19:    */ import java.util.Iterator;
/*  20:    */ import java.util.LinkedList;
/*  21:    */ import md;
/*  22:    */ import qx;
/*  23:    */ import ur;
/*  24:    */ 
/*  25:    */ public class TileAccel
/*  26:    */   extends TileMachinePanel
/*  27:    */   implements IBluePowerConnectable, ITubeFlow
/*  28:    */ {
/*  29:    */   public int getTubeConnectableSides()
/*  30:    */   {
/*  31: 25 */     return 3 << (this.Rotation & 0x6);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public int getTubeConClass()
/*  35:    */   {
/*  36: 29 */     return 17;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public boolean canRouteItems()
/*  40:    */   {
/*  41: 33 */     return true;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  45:    */   {
/*  46: 37 */     if (state != 0) {
/*  47: 37 */       return false;
/*  48:    */     }
/*  49: 38 */     if ((side != this.Rotation) && (side != (this.Rotation ^ 0x1))) {
/*  50: 39 */       return false;
/*  51:    */     }
/*  52: 40 */     ti.side = ((byte)side);
/*  53:    */     
/*  54: 42 */     this.flow.add(ti);
/*  55:    */     
/*  56: 44 */     this.hasChanged = true;
/*  57: 45 */     dirtyBlock();
/*  58: 46 */     return true;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  62:    */   {
/*  63: 50 */     if (state == 0) {
/*  64: 50 */       return true;
/*  65:    */     }
/*  66: 51 */     return false;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public int tubeWeight(int side, int state)
/*  70:    */   {
/*  71: 55 */     return 0;
/*  72:    */   }
/*  73:    */   
/*  74: 60 */   TubeFlow flow = new TubeFlow()
/*  75:    */   {
/*  76:    */     public any getParent()
/*  77:    */     {
/*  78: 62 */       return TileAccel.this;
/*  79:    */     }
/*  80:    */     
/*  81:    */     public boolean schedule(TubeItem ti, TubeFlow.TubeScheduleContext tsc)
/*  82:    */     {
/*  83: 66 */       ti.scheduled = true;
/*  84: 67 */       ti.progress = 0; TubeItem 
/*  85: 68 */         tmp11_10 = ti;tmp11_10.side = ((byte)(tmp11_10.side ^ 0x1));
/*  86:    */       
/*  87: 70 */       TileAccel.this.recache();
/*  88: 71 */       ti.power = 0;
/*  89: 72 */       if (((ti.side == TileAccel.this.Rotation) && ((TileAccel.this.conCache & 0x2) > 0)) || ((ti.side == (TileAccel.this.Rotation ^ 0x1)) && ((TileAccel.this.conCache & 0x8) > 0))) {
/*  90: 75 */         if (TileAccel.this.cond.getVoltage() >= 60.0D)
/*  91:    */         {
/*  92: 76 */           TileAccel.this.cond.drawPower(100 * ti.item.a);
/*  93: 77 */           ti.power = 255;
/*  94:    */         }
/*  95:    */       }
/*  96: 80 */       return true;
/*  97:    */     }
/*  98:    */   };
/*  99:    */   
/* 100:    */   public void addTubeItem(TubeItem ti)
/* 101:    */   {
/* 102: 85 */     TubeItem tmp1_0 = ti;tmp1_0.side = ((byte)(tmp1_0.side ^ 0x1));
/* 103: 86 */     this.flow.add(ti);
/* 104: 87 */     this.hasChanged = true;
/* 105: 88 */     dirtyBlock();
/* 106:    */   }
/* 107:    */   
/* 108:    */   public TubeFlow getTubeFlow()
/* 109:    */   {
/* 110: 92 */     return this.flow;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public int getPartMaxRotation(int part, boolean sec)
/* 114:    */   {
/* 115: 98 */     if (sec) {
/* 116: 98 */       return 0;
/* 117:    */     }
/* 118: 99 */     return 5;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public int getLightValue()
/* 122:    */   {
/* 123:105 */     return this.Charged ? 6 : 0;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public void recache()
/* 127:    */   {
/* 128:109 */     if (this.conCache >= 0) {
/* 129:109 */       return;
/* 130:    */     }
/* 131:111 */     WorldCoord wc = new WorldCoord(this);
/* 132:112 */     ITubeConnectable fw = (ITubeConnectable)CoreLib.getTileEntity(this.k, wc.coordStep(this.Rotation), ITubeConnectable.class);
/* 133:    */     
/* 134:    */ 
/* 135:115 */     ITubeConnectable bw = (ITubeConnectable)CoreLib.getTileEntity(this.k, wc.coordStep(this.Rotation ^ 0x1), ITubeConnectable.class);
/* 136:    */     
/* 137:    */ 
/* 138:    */ 
/* 139:119 */     this.conCache = 0;
/* 140:120 */     if (fw != null)
/* 141:    */     {
/* 142:121 */       int mcl = fw.getTubeConClass();
/* 143:122 */       if (mcl < 17) {
/* 144:123 */         this.conCache |= 0x1;
/* 145:124 */       } else if (mcl >= 17) {
/* 146:125 */         this.conCache |= 0x2;
/* 147:    */       }
/* 148:    */     }
/* 149:128 */     if (bw != null)
/* 150:    */     {
/* 151:129 */       int mcl = bw.getTubeConClass();
/* 152:130 */       if (mcl < 17) {
/* 153:131 */         this.conCache |= 0x4;
/* 154:132 */       } else if (mcl >= 17) {
/* 155:133 */         this.conCache |= 0x8;
/* 156:    */       }
/* 157:    */     }
/* 158:    */   }
/* 159:    */   
/* 160:    */   public int getConnectableMask()
/* 161:    */   {
/* 162:141 */     return 1073741823;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public int getConnectClass(int side)
/* 166:    */   {
/* 167:145 */     return 65;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public int getCornerPowerMode()
/* 171:    */   {
/* 172:148 */     return 0;
/* 173:    */   }
/* 174:    */   
/* 175:152 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/* 176:    */   {
/* 177:    */     public any getParent()
/* 178:    */     {
/* 179:154 */       return TileAccel.this;
/* 180:    */     }
/* 181:    */   };
/* 182:    */   
/* 183:    */   public BluePowerConductor getBlueConductor(int side)
/* 184:    */   {
/* 185:159 */     return this.cond;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void onHarvestPart(qx player, int part)
/* 189:    */   {
/* 190:165 */     this.flow.onRemove();
/* 191:166 */     breakBlock();
/* 192:    */   }
/* 193:    */   
/* 194:    */   public int getExtendedID()
/* 195:    */   {
/* 196:172 */     return 2;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public void g()
/* 200:    */   {
/* 201:176 */     super.g();
/* 202:179 */     if (this.flow.update()) {
/* 203:179 */       this.hasChanged = true;
/* 204:    */     }
/* 205:180 */     if (this.hasChanged)
/* 206:    */     {
/* 207:181 */       this.hasChanged = false;
/* 208:182 */       if (!CoreLib.isClient(this.k)) {
/* 209:183 */         sendItemUpdate();
/* 210:    */       }
/* 211:184 */       dirtyBlock();
/* 212:    */     }
/* 213:187 */     if (CoreLib.isClient(this.k)) {
/* 214:187 */       return;
/* 215:    */     }
/* 216:189 */     if (this.ConMask < 0)
/* 217:    */     {
/* 218:190 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 219:    */       
/* 220:192 */       this.cond.recache(this.ConMask, 0);
/* 221:    */     }
/* 222:194 */     this.cond.iterate();
/* 223:195 */     dirtyBlock();
/* 224:196 */     if (this.cond.Flow == 0)
/* 225:    */     {
/* 226:197 */       if (this.Charged)
/* 227:    */       {
/* 228:198 */         this.Charged = false;
/* 229:199 */         updateBlock();
/* 230:200 */         updateLight();
/* 231:    */       }
/* 232:    */     }
/* 233:202 */     else if (!this.Charged)
/* 234:    */     {
/* 235:203 */       this.Charged = true;
/* 236:204 */       updateBlock();
/* 237:205 */       updateLight();
/* 238:    */     }
/* 239:    */   }
/* 240:    */   
/* 241:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 242:    */   {
/* 243:280 */     this.Rotation = getFacing(ent);
/* 244:    */   }
/* 245:    */   
/* 246:    */   public void onBlockNeighborChange(int l)
/* 247:    */   {
/* 248:284 */     this.ConMask = -1;
/* 249:285 */     this.conCache = -1;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public void a(bq tag)
/* 253:    */   {
/* 254:291 */     super.a(tag);
/* 255:292 */     this.cond.readFromNBT(tag);
/* 256:293 */     this.flow.readFromNBT(tag);
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void b(bq tag)
/* 260:    */   {
/* 261:305 */     super.b(tag);
/* 262:306 */     this.cond.writeToNBT(tag);
/* 263:307 */     this.flow.writeToNBT(tag);
/* 264:    */   }
/* 265:    */   
/* 266:    */   protected void sendItemUpdate()
/* 267:    */   {
/* 268:321 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 269:322 */     pkt.subId = 10;
/* 270:323 */     pkt.xCoord = this.l;pkt.yCoord = this.m;
/* 271:324 */     pkt.zCoord = this.n;
/* 272:    */     
/* 273:326 */     int cs = this.flow.contents.size();
/* 274:327 */     if (cs > 6) {
/* 275:327 */       cs = 6;
/* 276:    */     }
/* 277:328 */     pkt.addUVLC(cs);
/* 278:    */     
/* 279:330 */     Iterator tii = this.flow.contents.iterator();
/* 280:331 */     for (int i = 0; i < cs; i++)
/* 281:    */     {
/* 282:332 */       TubeItem ti = (TubeItem)tii.next();
/* 283:333 */       ti.writeToPacket(pkt);
/* 284:    */     }
/* 285:335 */     pkt.encode();
/* 286:336 */     CoreProxy.sendPacketToPosition(this.k, pkt, this.l, this.n);
/* 287:    */   }
/* 288:    */   
/* 289:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 290:    */     throws IOException
/* 291:    */   {
/* 292:341 */     if (pkt.subId == 10)
/* 293:    */     {
/* 294:342 */       this.flow.contents.clear();
/* 295:343 */       int cs = (int)pkt.getUVLC();
/* 296:344 */       for (int i = 0; i < cs; i++) {
/* 297:345 */         this.flow.contents.add(TubeItem.newFromPacket(pkt));
/* 298:    */       }
/* 299:    */     }
/* 300:    */     else
/* 301:    */     {
/* 302:348 */       super.readFromPacket(pkt);
/* 303:349 */       updateBlock();
/* 304:    */     }
/* 305:    */   }
/* 306:    */   
/* 307:353 */   private boolean hasChanged = false;
/* 308:354 */   public int ConMask = -1;
/* 309:357 */   public int conCache = -1;
/* 310:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileAccel
 * JD-Core Version:    0.7.0.1
 */