/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.core.IRedPowerWiring;
/*   5:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   6:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   7:    */ import java.io.IOException;
/*   8:    */ import qx;
/*   9:    */ import yc;
/*  10:    */ 
/*  11:    */ public class TileLogicAdv
/*  12:    */   extends TileLogic
/*  13:    */   implements IRedPowerWiring
/*  14:    */ {
/*  15:    */   public class LogicAdvXcvr
/*  16:    */     extends TileLogicAdv.LogicAdvModule
/*  17:    */   {
/*  18:    */     public LogicAdvXcvr()
/*  19:    */     {
/*  20: 35 */       super();
/*  21:    */     }
/*  22:    */     
/*  23:    */     public void updatePowerState()
/*  24:    */     {
/*  25: 40 */       int ps = RedPowerLib.getRotPowerState(TileLogicAdv.this.k, TileLogicAdv.this.l, TileLogicAdv.this.m, TileLogicAdv.this.n, 5, TileLogicAdv.this.Rotation, 0);
/*  26: 42 */       if (ps != TileLogicAdv.this.PowerState)
/*  27:    */       {
/*  28: 43 */         TileLogicAdv.this.PowerState = ps;
/*  29: 44 */         TileLogicAdv.this.updateBlock();
/*  30: 45 */         TileLogicAdv.this.scheduleTick(2);
/*  31:    */       }
/*  32:    */     }
/*  33:    */     
/*  34:    */     public void tileTick()
/*  35:    */     {
/*  36: 50 */       TileLogicAdv.this.Powered = ((TileLogicAdv.this.PowerState & 0x1) > 0);
/*  37: 51 */       TileLogicAdv.this.Active = ((TileLogicAdv.this.PowerState & 0x4) > 0);
/*  38:    */       
/*  39: 53 */       int sd1 = this.State1N;int sd2 = this.State2N;
/*  40: 54 */       if (TileLogicAdv.this.Deadmap == 0)
/*  41:    */       {
/*  42: 55 */         if (!TileLogicAdv.this.Powered) {
/*  43: 55 */           sd1 = 0;
/*  44:    */         }
/*  45: 56 */         if (!TileLogicAdv.this.Active) {
/*  46: 56 */           sd2 = 0;
/*  47:    */         }
/*  48:    */       }
/*  49:    */       else
/*  50:    */       {
/*  51: 58 */         if (!TileLogicAdv.this.Powered) {
/*  52: 58 */           sd2 = 0;
/*  53:    */         }
/*  54: 59 */         if (!TileLogicAdv.this.Active) {
/*  55: 59 */           sd1 = 0;
/*  56:    */         }
/*  57:    */       }
/*  58: 62 */       boolean ch = (this.State1 != sd1) || (this.State2 != sd2);
/*  59: 63 */       this.State1 = sd1;this.State2 = sd2;
/*  60: 65 */       if (ch)
/*  61:    */       {
/*  62: 66 */         TileLogicAdv.this.updateBlock();
/*  63: 67 */         RedPowerLib.updateCurrent(TileLogicAdv.this.k, TileLogicAdv.this.l, TileLogicAdv.this.m, TileLogicAdv.this.n);
/*  64:    */       }
/*  65: 70 */       updatePowerState();
/*  66: 71 */       updateCurrentStrength();
/*  67:    */     }
/*  68:    */     
/*  69:    */     public int getSubType()
/*  70:    */     {
/*  71: 74 */       return 0;
/*  72:    */     }
/*  73:    */     
/*  74:    */     public int getPoweringMask(int ch)
/*  75:    */     {
/*  76: 77 */       int ps = 0;
/*  77: 78 */       if ((ch < 1) || (ch > 16)) {
/*  78: 78 */         return 0;
/*  79:    */       }
/*  80: 79 */       ch--;
/*  81: 80 */       if ((this.State1 >> ch & 0x1) > 0) {
/*  82: 80 */         ps |= 0x8;
/*  83:    */       }
/*  84: 81 */       if ((this.State2 >> ch & 0x1) > 0) {
/*  85: 81 */         ps |= 0x2;
/*  86:    */       }
/*  87: 82 */       return RedPowerLib.mapRotToCon(ps, TileLogicAdv.this.Rotation);
/*  88:    */     }
/*  89:    */     
/*  90:    */     public void updateCurrentStrength()
/*  91:    */     {
/*  92: 86 */       if (TileLogicAdv.this.isTickRunnable()) {
/*  93: 86 */         return;
/*  94:    */       }
/*  95: 88 */       this.State1N = this.State2;this.State2N = this.State1;
/*  96: 89 */       for (int ch = 0; ch < 16; ch++)
/*  97:    */       {
/*  98: 90 */         int p1 = (short)RedPowerLib.updateBlockCurrentStrength(TileLogicAdv.this.k, TileLogicAdv.this, TileLogicAdv.this.l, TileLogicAdv.this.m, TileLogicAdv.this.n, RedPowerLib.mapRotToCon(2, TileLogicAdv.this.Rotation), 2 << ch);
/*  99:    */         
/* 100:    */ 
/* 101:    */ 
/* 102:    */ 
/* 103:    */ 
/* 104: 96 */         int p2 = (short)RedPowerLib.updateBlockCurrentStrength(TileLogicAdv.this.k, TileLogicAdv.this, TileLogicAdv.this.l, TileLogicAdv.this.m, TileLogicAdv.this.n, RedPowerLib.mapRotToCon(8, TileLogicAdv.this.Rotation), 2 << ch);
/* 105:103 */         if (p1 > 0) {
/* 106:103 */           this.State1N |= 1 << ch;
/* 107:    */         }
/* 108:104 */         if (p2 > 0) {
/* 109:104 */           this.State2N |= 1 << ch;
/* 110:    */         }
/* 111:    */       }
/* 112:106 */       TileLogicAdv.this.dirtyBlock();
/* 113:107 */       if ((this.State1N != this.State1) || (this.State2N != this.State2)) {
/* 114:108 */         TileLogicAdv.this.scheduleTick(2);
/* 115:    */       }
/* 116:    */     }
/* 117:    */     
/* 118:    */     public void readFromNBT(bq tag)
/* 119:    */     {
/* 120:112 */       this.State1 = tag.e("s1");
/* 121:113 */       this.State2 = tag.e("s2");
/* 122:114 */       this.State1N = tag.e("s1n");
/* 123:115 */       this.State2N = tag.e("s2n");
/* 124:    */     }
/* 125:    */     
/* 126:    */     public void writeToNBT(bq tag)
/* 127:    */     {
/* 128:119 */       tag.a("s1", this.State1);
/* 129:120 */       tag.a("s2", this.State2);
/* 130:121 */       tag.a("s1n", this.State1N);
/* 131:122 */       tag.a("s2n", this.State2N);
/* 132:    */     }
/* 133:    */     
/* 134:    */     public void readFromPacket(Packet211TileDesc pkt)
/* 135:    */       throws IOException
/* 136:    */     {
/* 137:127 */       this.State1 = ((int)pkt.getUVLC());
/* 138:128 */       this.State2 = ((int)pkt.getUVLC());
/* 139:    */     }
/* 140:    */     
/* 141:    */     public void writeToPacket(Packet211TileDesc pkt)
/* 142:    */     {
/* 143:131 */       pkt.addUVLC(this.State1);
/* 144:132 */       pkt.addUVLC(this.State2);
/* 145:    */     }
/* 146:    */     
/* 147:134 */     public int State2N = 0;
/* 148:134 */     public int State1N = 0;
/* 149:134 */     public int State2 = 0;
/* 150:134 */     public int State1 = 0;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void updateCurrentStrength()
/* 154:    */   {
/* 155:140 */     initStorage();
/* 156:141 */     this.storage.updateCurrentStrength();
/* 157:    */   }
/* 158:    */   
/* 159:    */   public int getCurrentStrength(int cons, int ch)
/* 160:    */   {
/* 161:145 */     initStorage();
/* 162:146 */     if ((this.storage.getPoweringMask(ch) & cons) > 0) {
/* 163:147 */       return 255;
/* 164:    */     }
/* 165:148 */     return -1;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public int scanPoweringStrength(int cons, int ch)
/* 169:    */   {
/* 170:152 */     return 0;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public int getConnectionMask()
/* 174:    */   {
/* 175:158 */     return RedPowerLib.mapRotToCon(15, this.Rotation);
/* 176:    */   }
/* 177:    */   
/* 178:    */   public int getExtConnectionMask()
/* 179:    */   {
/* 180:162 */     return 0;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public int getConnectClass(int side)
/* 184:    */   {
/* 185:169 */     int s = RedPowerLib.mapRotToCon(10, this.Rotation);
/* 186:170 */     if ((s & RedPowerLib.getConDirMask(side)) > 0) {
/* 187:170 */       return 18;
/* 188:    */     }
/* 189:171 */     return 0;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public int getExtendedID()
/* 193:    */   {
/* 194:177 */     return 4;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public void initSubType(int st)
/* 198:    */   {
/* 199:181 */     this.SubId = st;
/* 200:182 */     initStorage();
/* 201:    */   }
/* 202:    */   
/* 203:    */   public LogicAdvModule getLogicStorage(Class cl)
/* 204:    */   {
/* 205:186 */     if (!cl.isInstance(this.storage)) {
/* 206:187 */       initStorage();
/* 207:    */     }
/* 208:188 */     return this.storage;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public boolean isUseableByPlayer(qx player)
/* 212:    */   {
/* 213:192 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 214:193 */       return false;
/* 215:    */     }
/* 216:194 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public int getPartMaxRotation(int part, boolean sec)
/* 220:    */   {
/* 221:201 */     if (sec) {
/* 222:202 */       switch (this.SubId)
/* 223:    */       {
/* 224:    */       case 0: 
/* 225:204 */         return 1;
/* 226:    */       }
/* 227:    */     }
/* 228:207 */     return super.getPartMaxRotation(part, sec);
/* 229:    */   }
/* 230:    */   
/* 231:    */   public int getPartRotation(int part, boolean sec)
/* 232:    */   {
/* 233:211 */     if (sec) {
/* 234:212 */       switch (this.SubId)
/* 235:    */       {
/* 236:    */       case 0: 
/* 237:214 */         return this.Deadmap;
/* 238:    */       }
/* 239:    */     }
/* 240:217 */     return super.getPartRotation(part, sec);
/* 241:    */   }
/* 242:    */   
/* 243:    */   public void setPartRotation(int part, boolean sec, int rot)
/* 244:    */   {
/* 245:221 */     if (sec) {
/* 246:222 */       switch (this.SubId)
/* 247:    */       {
/* 248:    */       case 0: 
/* 249:224 */         this.Deadmap = rot;
/* 250:225 */         updateBlockChange();
/* 251:226 */         return;
/* 252:    */       }
/* 253:    */     }
/* 254:229 */     super.setPartRotation(part, sec, rot);
/* 255:    */   }
/* 256:    */   
/* 257:    */   void initStorage()
/* 258:    */   {
/* 259:235 */     if ((this.storage != null) && (this.storage.getSubType() == this.SubId)) {
/* 260:236 */       return;
/* 261:    */     }
/* 262:238 */     switch (this.SubId)
/* 263:    */     {
/* 264:    */     case 0: 
/* 265:240 */       this.storage = new LogicAdvXcvr();
/* 266:241 */       break;
/* 267:    */     default: 
/* 268:243 */       this.storage = null;
/* 269:    */     }
/* 270:    */   }
/* 271:    */   
/* 272:    */   public void onBlockNeighborChange(int l)
/* 273:    */   {
/* 274:248 */     if (tryDropBlock()) {
/* 275:248 */       return;
/* 276:    */     }
/* 277:249 */     initStorage();
/* 278:251 */     switch (this.SubId)
/* 279:    */     {
/* 280:    */     case 0: 
/* 281:253 */       if (isTickRunnable()) {
/* 282:253 */         return;
/* 283:    */       }
/* 284:254 */       this.storage.updatePowerState();
/* 285:    */     }
/* 286:    */   }
/* 287:    */   
/* 288:    */   public void onTileTick()
/* 289:    */   {
/* 290:260 */     initStorage();
/* 291:261 */     this.storage.tileTick();
/* 292:    */   }
/* 293:    */   
/* 294:    */   public int getPoweringMask(int ch)
/* 295:    */   {
/* 296:265 */     initStorage();
/* 297:266 */     return this.storage.getPoweringMask(ch);
/* 298:    */   }
/* 299:    */   
/* 300:    */   public void a(bq tag)
/* 301:    */   {
/* 302:290 */     super.a(tag);
/* 303:291 */     initStorage();
/* 304:292 */     this.storage.readFromNBT(tag);
/* 305:    */   }
/* 306:    */   
/* 307:    */   public void b(bq tag)
/* 308:    */   {
/* 309:296 */     super.b(tag);
/* 310:297 */     this.storage.writeToNBT(tag);
/* 311:    */   }
/* 312:    */   
/* 313:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 314:    */     throws IOException
/* 315:    */   {
/* 316:302 */     super.readFromPacket(pkt);
/* 317:303 */     initStorage();
/* 318:304 */     this.storage.readFromPacket(pkt);
/* 319:    */   }
/* 320:    */   
/* 321:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 322:    */   {
/* 323:308 */     super.writeToPacket(pkt);
/* 324:309 */     this.storage.writeToPacket(pkt);
/* 325:    */   }
/* 326:    */   
/* 327:312 */   LogicAdvModule storage = null;
/* 328:    */   
/* 329:    */   public abstract class LogicAdvModule
/* 330:    */   {
/* 331:    */     public LogicAdvModule() {}
/* 332:    */     
/* 333:    */     public abstract void updatePowerState();
/* 334:    */     
/* 335:    */     public abstract void tileTick();
/* 336:    */     
/* 337:    */     public abstract int getSubType();
/* 338:    */     
/* 339:    */     public abstract int getPoweringMask(int paramInt);
/* 340:    */     
/* 341:    */     public void updateCurrentStrength() {}
/* 342:    */     
/* 343:    */     public abstract void readFromNBT(bq parambq);
/* 344:    */     
/* 345:    */     public abstract void writeToNBT(bq parambq);
/* 346:    */     
/* 347:    */     public void readFromPacket(Packet211TileDesc pkt)
/* 348:    */       throws IOException
/* 349:    */     {}
/* 350:    */     
/* 351:    */     public void writeToPacket(Packet211TileDesc pkt) {}
/* 352:    */   }
/* 353:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.TileLogicAdv
 * JD-Core Version:    0.7.0.1
 */