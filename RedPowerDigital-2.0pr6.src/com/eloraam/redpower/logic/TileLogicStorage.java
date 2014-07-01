/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerLogic;
/*   5:    */ import com.eloraam.redpower.core.CoreLib;
/*   6:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   7:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   8:    */ import java.io.IOException;
/*   9:    */ import qx;
/*  10:    */ import yc;
/*  11:    */ 
/*  12:    */ public class TileLogicStorage
/*  13:    */   extends TileLogic
/*  14:    */ {
/*  15:    */   public class LogicStorageCounter
/*  16:    */     extends TileLogicStorage.LogicStorageModule
/*  17:    */   {
/*  18:    */     public LogicStorageCounter()
/*  19:    */     {
/*  20: 36 */       super();
/*  21:    */     }
/*  22:    */     
/*  23:    */     public void updatePowerState()
/*  24:    */     {
/*  25: 41 */       int ps = RedPowerLib.getRotPowerState(TileLogicStorage.this.k, TileLogicStorage.this.l, TileLogicStorage.this.m, TileLogicStorage.this.n, 5, TileLogicStorage.this.Rotation, 0);
/*  26: 43 */       if (ps != TileLogicStorage.this.PowerState)
/*  27:    */       {
/*  28: 44 */         if ((ps & (TileLogicStorage.this.PowerState ^ 0xFFFFFFFF) & 0x1) > 0) {
/*  29: 44 */           TileLogicStorage.this.Active = true;
/*  30:    */         }
/*  31: 45 */         if ((ps & (TileLogicStorage.this.PowerState ^ 0xFFFFFFFF) & 0x4) > 0) {
/*  32: 45 */           TileLogicStorage.this.Disabled = true;
/*  33:    */         }
/*  34: 46 */         TileLogicStorage.this.PowerState = ps;
/*  35: 47 */         TileLogicStorage.this.updateBlock();
/*  36: 49 */         if ((TileLogicStorage.this.Active) || (TileLogicStorage.this.Disabled)) {
/*  37: 49 */           TileLogicStorage.this.scheduleTick(2);
/*  38:    */         }
/*  39:    */       }
/*  40:    */     }
/*  41:    */     
/*  42:    */     public void tileTick()
/*  43:    */     {
/*  44: 56 */       int co = this.Count;
/*  45: 57 */       if (TileLogicStorage.this.Deadmap > 0)
/*  46:    */       {
/*  47: 58 */         if (TileLogicStorage.this.Active)
/*  48:    */         {
/*  49: 58 */           this.Count -= this.Dec;TileLogicStorage.this.Active = false;
/*  50:    */         }
/*  51: 59 */         if (TileLogicStorage.this.Disabled)
/*  52:    */         {
/*  53: 59 */           this.Count += this.Inc;TileLogicStorage.this.Disabled = false;
/*  54:    */         }
/*  55:    */       }
/*  56:    */       else
/*  57:    */       {
/*  58: 61 */         if (TileLogicStorage.this.Active)
/*  59:    */         {
/*  60: 61 */           this.Count += this.Inc;TileLogicStorage.this.Active = false;
/*  61:    */         }
/*  62: 62 */         if (TileLogicStorage.this.Disabled)
/*  63:    */         {
/*  64: 62 */           this.Count -= this.Dec;TileLogicStorage.this.Disabled = false;
/*  65:    */         }
/*  66:    */       }
/*  67: 64 */       if (this.Count < 0) {
/*  68: 64 */         this.Count = 0;
/*  69:    */       }
/*  70: 65 */       if (this.Count > this.CountMax) {
/*  71: 65 */         this.Count = this.CountMax;
/*  72:    */       }
/*  73: 66 */       if (co != this.Count)
/*  74:    */       {
/*  75: 67 */         TileLogicStorage.this.updateBlockChange();
/*  76: 68 */         TileLogicStorage.this.playSound("random.click", 0.3F, 0.5F, false);
/*  77:    */       }
/*  78: 70 */       updatePowerState();
/*  79:    */     }
/*  80:    */     
/*  81:    */     public int getSubType()
/*  82:    */     {
/*  83: 73 */       return 0;
/*  84:    */     }
/*  85:    */     
/*  86:    */     public int getPoweringMask(int ch)
/*  87:    */     {
/*  88: 76 */       int ps = 0;
/*  89: 77 */       if (ch != 0) {
/*  90: 77 */         return 0;
/*  91:    */       }
/*  92: 78 */       if (this.Count == 0) {
/*  93: 78 */         ps |= 0x2;
/*  94:    */       }
/*  95: 79 */       if (this.Count == this.CountMax) {
/*  96: 79 */         ps |= 0x8;
/*  97:    */       }
/*  98: 80 */       return RedPowerLib.mapRotToCon(ps, TileLogicStorage.this.Rotation);
/*  99:    */     }
/* 100:    */     
/* 101:    */     public void readFromNBT(bq tag)
/* 102:    */     {
/* 103: 84 */       this.Count = tag.e("cnt");
/* 104: 85 */       this.CountMax = tag.e("max");
/* 105: 86 */       this.Inc = tag.e("inc");
/* 106: 87 */       this.Dec = tag.e("dec");
/* 107:    */     }
/* 108:    */     
/* 109:    */     public void writeToNBT(bq tag)
/* 110:    */     {
/* 111: 91 */       tag.a("cnt", this.Count);
/* 112: 92 */       tag.a("max", this.CountMax);
/* 113: 93 */       tag.a("inc", this.Inc);
/* 114: 94 */       tag.a("dec", this.Dec);
/* 115:    */     }
/* 116:    */     
/* 117:    */     public void readFromPacket(Packet211TileDesc pkt)
/* 118:    */       throws IOException
/* 119:    */     {
/* 120: 99 */       this.Count = ((int)pkt.getUVLC());
/* 121:100 */       this.CountMax = ((int)pkt.getUVLC());
/* 122:    */     }
/* 123:    */     
/* 124:    */     public void writeToPacket(Packet211TileDesc pkt)
/* 125:    */     {
/* 126:103 */       pkt.addUVLC(this.Count);
/* 127:104 */       pkt.addUVLC(this.CountMax);
/* 128:    */     }
/* 129:    */     
/* 130:106 */     public int Dec = 1;
/* 131:106 */     public int Inc = 1;
/* 132:106 */     public int CountMax = 10;
/* 133:106 */     public int Count = 0;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public int getExtendedID()
/* 137:    */   {
/* 138:112 */     return 3;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void initSubType(int st)
/* 142:    */   {
/* 143:116 */     super.initSubType(st);
/* 144:117 */     initStorage();
/* 145:    */   }
/* 146:    */   
/* 147:    */   public LogicStorageModule getLogicStorage(Class cl)
/* 148:    */   {
/* 149:121 */     if (!cl.isInstance(this.storage)) {
/* 150:122 */       initStorage();
/* 151:    */     }
/* 152:123 */     return this.storage;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public boolean isUseableByPlayer(qx player)
/* 156:    */   {
/* 157:127 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 158:128 */       return false;
/* 159:    */     }
/* 160:129 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public int getPartMaxRotation(int part, boolean sec)
/* 164:    */   {
/* 165:136 */     if (sec) {
/* 166:137 */       switch (this.SubId)
/* 167:    */       {
/* 168:    */       case 0: 
/* 169:139 */         return 1;
/* 170:    */       }
/* 171:    */     }
/* 172:142 */     return super.getPartMaxRotation(part, sec);
/* 173:    */   }
/* 174:    */   
/* 175:    */   public int getPartRotation(int part, boolean sec)
/* 176:    */   {
/* 177:146 */     if (sec) {
/* 178:147 */       switch (this.SubId)
/* 179:    */       {
/* 180:    */       case 0: 
/* 181:149 */         return this.Deadmap;
/* 182:    */       }
/* 183:    */     }
/* 184:152 */     return super.getPartRotation(part, sec);
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void setPartRotation(int part, boolean sec, int rot)
/* 188:    */   {
/* 189:156 */     if (sec) {
/* 190:157 */       switch (this.SubId)
/* 191:    */       {
/* 192:    */       case 0: 
/* 193:159 */         this.Deadmap = rot;
/* 194:160 */         updateBlockChange();
/* 195:161 */         return;
/* 196:    */       }
/* 197:    */     }
/* 198:164 */     super.setPartRotation(part, sec, rot);
/* 199:    */   }
/* 200:    */   
/* 201:    */   void initStorage()
/* 202:    */   {
/* 203:170 */     if ((this.storage != null) && (this.storage.getSubType() == this.SubId)) {
/* 204:171 */       return;
/* 205:    */     }
/* 206:173 */     switch (this.SubId)
/* 207:    */     {
/* 208:    */     case 0: 
/* 209:175 */       this.storage = new LogicStorageCounter();
/* 210:176 */       break;
/* 211:    */     default: 
/* 212:178 */       this.storage = null;
/* 213:    */     }
/* 214:    */   }
/* 215:    */   
/* 216:    */   public void onBlockNeighborChange(int l)
/* 217:    */   {
/* 218:183 */     if (tryDropBlock()) {
/* 219:183 */       return;
/* 220:    */     }
/* 221:184 */     initStorage();
/* 222:186 */     switch (this.SubId)
/* 223:    */     {
/* 224:    */     case 0: 
/* 225:188 */       if (isTickRunnable()) {
/* 226:188 */         return;
/* 227:    */       }
/* 228:189 */       this.storage.updatePowerState();
/* 229:    */     }
/* 230:    */   }
/* 231:    */   
/* 232:    */   public void onTileTick()
/* 233:    */   {
/* 234:195 */     initStorage();
/* 235:196 */     this.storage.tileTick();
/* 236:    */   }
/* 237:    */   
/* 238:    */   public int getPoweringMask(int ch)
/* 239:    */   {
/* 240:201 */     initStorage();
/* 241:    */     
/* 242:203 */     return this.storage.getPoweringMask(ch);
/* 243:    */   }
/* 244:    */   
/* 245:    */   public boolean onPartActivateSide(qx player, int part, int side)
/* 246:    */   {
/* 247:208 */     if (part != this.Rotation >> 2) {
/* 248:208 */       return false;
/* 249:    */     }
/* 250:209 */     if (player.ah()) {
/* 251:209 */       return false;
/* 252:    */     }
/* 253:210 */     if (CoreLib.isClient(this.k)) {
/* 254:211 */       return true;
/* 255:    */     }
/* 256:213 */     switch (this.SubId)
/* 257:    */     {
/* 258:    */     case 0: 
/* 259:215 */       player.openGui(RedPowerLogic.instance, 1, this.k, this.l, this.m, this.n);
/* 260:    */       
/* 261:217 */       return true;
/* 262:    */     }
/* 263:219 */     return true;
/* 264:    */   }
/* 265:    */   
/* 266:    */   public void a(bq tag)
/* 267:    */   {
/* 268:225 */     super.a(tag);
/* 269:226 */     initStorage();
/* 270:227 */     this.storage.readFromNBT(tag);
/* 271:    */   }
/* 272:    */   
/* 273:    */   public void b(bq tag)
/* 274:    */   {
/* 275:231 */     super.b(tag);
/* 276:232 */     this.storage.writeToNBT(tag);
/* 277:    */   }
/* 278:    */   
/* 279:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 280:    */     throws IOException
/* 281:    */   {
/* 282:237 */     super.readFromPacket(pkt);
/* 283:238 */     initStorage();
/* 284:239 */     this.storage.readFromPacket(pkt);
/* 285:    */   }
/* 286:    */   
/* 287:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 288:    */   {
/* 289:243 */     super.writeToPacket(pkt);
/* 290:244 */     this.storage.writeToPacket(pkt);
/* 291:    */   }
/* 292:    */   
/* 293:247 */   LogicStorageModule storage = null;
/* 294:    */   
/* 295:    */   public abstract class LogicStorageModule
/* 296:    */   {
/* 297:    */     public LogicStorageModule() {}
/* 298:    */     
/* 299:    */     public abstract void updatePowerState();
/* 300:    */     
/* 301:    */     public abstract void tileTick();
/* 302:    */     
/* 303:    */     public abstract int getSubType();
/* 304:    */     
/* 305:    */     public abstract int getPoweringMask(int paramInt);
/* 306:    */     
/* 307:    */     public abstract void readFromNBT(bq parambq);
/* 308:    */     
/* 309:    */     public abstract void writeToNBT(bq parambq);
/* 310:    */     
/* 311:    */     public void readFromPacket(Packet211TileDesc pkt)
/* 312:    */       throws IOException
/* 313:    */     {}
/* 314:    */     
/* 315:    */     public void writeToPacket(Packet211TileDesc pkt) {}
/* 316:    */   }
/* 317:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.TileLogicStorage
 * JD-Core Version:    0.7.0.1
 */