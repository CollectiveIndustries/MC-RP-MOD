/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.ITubeConnectable;
/*   8:    */ import com.eloraam.redpower.core.MachineLib;
/*   9:    */ import com.eloraam.redpower.core.MachineLib.FilterMap;
/*  10:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  11:    */ import com.eloraam.redpower.core.TubeItem;
/*  12:    */ import la;
/*  13:    */ import net.minecraftforge.common.ForgeDirection;
/*  14:    */ import net.minecraftforge.common.ISidedInventory;
/*  15:    */ import qx;
/*  16:    */ import ur;
/*  17:    */ import yc;
/*  18:    */ 
/*  19:    */ public class TileItemDetect
/*  20:    */   extends TileMachine
/*  21:    */   implements ITubeConnectable, la, ISidedInventory
/*  22:    */ {
/*  23:    */   public TileItemDetect()
/*  24:    */   {
/*  25: 19 */     this.contents = new ur[9];
/*  26:    */   }
/*  27:    */   
/*  28:    */   void regenFilterMap()
/*  29:    */   {
/*  30: 25 */     this.filterMap = MachineLib.makeFilterMap(this.contents);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public int getTubeConnectableSides()
/*  34:    */   {
/*  35: 31 */     return 3 << (this.Rotation & 0xFFFFFFFE);
/*  36:    */   }
/*  37:    */   
/*  38:    */   public int getTubeConClass()
/*  39:    */   {
/*  40: 35 */     return 0;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public boolean canRouteItems()
/*  44:    */   {
/*  45: 39 */     return false;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  49:    */   {
/*  50: 43 */     if ((side == this.Rotation) && (state == 2))
/*  51:    */     {
/*  52: 44 */       this.buffer.addBounce(ti);
/*  53: 45 */       this.Active = true;
/*  54: 46 */       updateBlock();
/*  55: 47 */       scheduleTick(5);
/*  56: 48 */       return true;
/*  57:    */     }
/*  58: 49 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1))
/*  59:    */     {
/*  60: 50 */       if (!this.buffer.isEmpty()) {
/*  61: 50 */         return false;
/*  62:    */       }
/*  63: 51 */       this.buffer.add(ti);
/*  64: 53 */       if (this.filterMap == null) {
/*  65: 53 */         regenFilterMap();
/*  66:    */       }
/*  67: 54 */       if ((this.filterMap.size() == 0) || (this.filterMap.containsKey(ti.item))) {
/*  68: 56 */         if (this.mode == 0) {
/*  69: 57 */           this.count += ti.item.a;
/*  70: 58 */         } else if (this.mode == 1) {
/*  71: 59 */           this.count += 1;
/*  72:    */         }
/*  73:    */       }
/*  74: 61 */       this.Active = true;
/*  75: 62 */       updateBlock();
/*  76: 63 */       scheduleTick(5);
/*  77: 64 */       drainBuffer();
/*  78: 65 */       return true;
/*  79:    */     }
/*  80: 67 */     return false;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  84:    */   {
/*  85: 71 */     if ((side == this.Rotation) && (state == 2)) {
/*  86: 72 */       return true;
/*  87:    */     }
/*  88: 73 */     if ((side == (this.Rotation ^ 0x1)) && (state == 1)) {
/*  89: 74 */       return this.buffer.isEmpty();
/*  90:    */     }
/*  91: 75 */     return false;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public int tubeWeight(int side, int state)
/*  95:    */   {
/*  96: 79 */     if ((side == this.Rotation) && (state == 2)) {
/*  97: 80 */       return this.buffer.size();
/*  98:    */     }
/*  99: 81 */     return 0;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public int getStartInventorySide(ForgeDirection side)
/* 103:    */   {
/* 104: 87 */     return 0;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 108:    */   {
/* 109: 91 */     int side = fd.ordinal();
/* 110: 92 */     if ((side == this.Rotation) || (side == (this.Rotation ^ 0x1))) {
/* 111: 93 */       return 0;
/* 112:    */     }
/* 113: 94 */     return 9;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void drainBuffer()
/* 117:    */   {
/* 118:100 */     while (!this.buffer.isEmpty())
/* 119:    */     {
/* 120:101 */       TubeItem ti = this.buffer.getLast();
/* 121:102 */       if (!handleItem(ti))
/* 122:    */       {
/* 123:103 */         this.buffer.plugged = true;
/* 124:104 */         if ((this.mode == 2) && (!this.Powered))
/* 125:    */         {
/* 126:105 */           this.Delay = false;
/* 127:106 */           this.Powered = true;
/* 128:107 */           this.count = 0;
/* 129:108 */           updateBlockChange();
/* 130:    */         }
/* 131:110 */         return;
/* 132:    */       }
/* 133:112 */       this.buffer.pop();
/* 134:113 */       if (this.buffer.plugged)
/* 135:    */       {
/* 136:114 */         if ((this.mode == 2) && (!this.Powered))
/* 137:    */         {
/* 138:115 */           this.Delay = false;
/* 139:116 */           this.Powered = true;
/* 140:117 */           this.count = 0;
/* 141:118 */           updateBlockChange();
/* 142:    */         }
/* 143:120 */         return;
/* 144:    */       }
/* 145:    */     }
/* 146:123 */     if ((this.mode == 2) && (this.Powered))
/* 147:    */     {
/* 148:124 */       this.Powered = false;
/* 149:125 */       updateBlockChange();
/* 150:    */     }
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void g()
/* 154:    */   {
/* 155:130 */     super.g();
/* 156:131 */     if (CoreLib.isClient(this.k)) {
/* 157:131 */       return;
/* 158:    */     }
/* 159:133 */     if (this.mode == 2) {
/* 160:133 */       return;
/* 161:    */     }
/* 162:134 */     if (this.Powered)
/* 163:    */     {
/* 164:135 */       if (this.Delay)
/* 165:    */       {
/* 166:135 */         this.Delay = false;dirtyBlock();return;
/* 167:    */       }
/* 168:136 */       this.Powered = false;
/* 169:137 */       if (this.count > 0) {
/* 170:137 */         this.Delay = true;
/* 171:    */       }
/* 172:138 */       updateBlockChange();
/* 173:139 */       return;
/* 174:    */     }
/* 175:141 */     if (this.count == 0) {
/* 176:141 */       return;
/* 177:    */     }
/* 178:142 */     if (this.Delay)
/* 179:    */     {
/* 180:142 */       this.Delay = false;dirtyBlock();return;
/* 181:    */     }
/* 182:143 */     this.count -= 1;this.Powered = true;this.Delay = true;
/* 183:144 */     updateBlockChange();
/* 184:    */   }
/* 185:    */   
/* 186:    */   public boolean isPoweringTo(int side)
/* 187:    */   {
/* 188:149 */     if (side == (this.Rotation ^ 0x1)) {
/* 189:149 */       return false;
/* 190:    */     }
/* 191:150 */     return this.Powered;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public boolean onBlockActivated(qx player)
/* 195:    */   {
/* 196:156 */     if (player.ah()) {
/* 197:156 */       return false;
/* 198:    */     }
/* 199:157 */     if (CoreLib.isClient(this.k)) {
/* 200:158 */       return true;
/* 201:    */     }
/* 202:159 */     player.openGui(RedPowerMachine.instance, 6, this.k, this.l, this.m, this.n);
/* 203:    */     
/* 204:161 */     return true;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public int getExtendedID()
/* 208:    */   {
/* 209:165 */     return 4;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public void onBlockRemoval()
/* 213:    */   {
/* 214:169 */     this.buffer.onRemove(this);
/* 215:171 */     for (int i = 0; i < 9; i++)
/* 216:    */     {
/* 217:172 */       ur ist = this.contents[i];
/* 218:173 */       if ((ist != null) && (ist.a > 0)) {
/* 219:174 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 220:    */       }
/* 221:    */     }
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void onTileTick()
/* 225:    */   {
/* 226:181 */     if (CoreLib.isClient(this.k)) {
/* 227:182 */       return;
/* 228:    */     }
/* 229:183 */     if (!this.buffer.isEmpty())
/* 230:    */     {
/* 231:184 */       drainBuffer();
/* 232:185 */       if (!this.buffer.isEmpty()) {
/* 233:185 */         scheduleTick(10);
/* 234:    */       } else {
/* 235:186 */         scheduleTick(5);
/* 236:    */       }
/* 237:187 */       return;
/* 238:    */     }
/* 239:189 */     this.Active = false;
/* 240:190 */     updateBlock();
/* 241:    */   }
/* 242:    */   
/* 243:    */   public int k_()
/* 244:    */   {
/* 245:196 */     return 9;
/* 246:    */   }
/* 247:    */   
/* 248:    */   public ur a(int i)
/* 249:    */   {
/* 250:200 */     return this.contents[i];
/* 251:    */   }
/* 252:    */   
/* 253:    */   public ur a(int i, int j)
/* 254:    */   {
/* 255:205 */     if (this.contents[i] == null) {
/* 256:205 */       return null;
/* 257:    */     }
/* 258:207 */     if (this.contents[i].a <= j)
/* 259:    */     {
/* 260:208 */       ur tr = this.contents[i];
/* 261:209 */       this.contents[i] = null;
/* 262:210 */       d();
/* 263:211 */       return tr;
/* 264:    */     }
/* 265:213 */     ur tr = this.contents[i].a(j);
/* 266:214 */     if (this.contents[i].a == 0) {
/* 267:215 */       this.contents[i] = null;
/* 268:    */     }
/* 269:216 */     d();
/* 270:217 */     return tr;
/* 271:    */   }
/* 272:    */   
/* 273:    */   public ur a_(int i)
/* 274:    */   {
/* 275:221 */     if (this.contents[i] == null) {
/* 276:221 */       return null;
/* 277:    */     }
/* 278:222 */     ur ist = this.contents[i];
/* 279:223 */     this.contents[i] = null;
/* 280:224 */     return ist;
/* 281:    */   }
/* 282:    */   
/* 283:    */   public void a(int i, ur ist)
/* 284:    */   {
/* 285:228 */     this.contents[i] = ist;
/* 286:229 */     if ((ist != null) && (ist.a > c())) {
/* 287:230 */       ist.a = c();
/* 288:    */     }
/* 289:231 */     d();
/* 290:    */   }
/* 291:    */   
/* 292:    */   public String b()
/* 293:    */   {
/* 294:235 */     return "Item Detector";
/* 295:    */   }
/* 296:    */   
/* 297:    */   public int c()
/* 298:    */   {
/* 299:239 */     return 64;
/* 300:    */   }
/* 301:    */   
/* 302:    */   public boolean a_(qx player)
/* 303:    */   {
/* 304:243 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 305:244 */       return false;
/* 306:    */     }
/* 307:245 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 308:    */   }
/* 309:    */   
/* 310:    */   public void d()
/* 311:    */   {
/* 312:250 */     this.filterMap = null;
/* 313:251 */     super.d();
/* 314:    */   }
/* 315:    */   
/* 316:    */   public void f() {}
/* 317:    */   
/* 318:    */   public void l_() {}
/* 319:    */   
/* 320:    */   public void a(bq tag)
/* 321:    */   {
/* 322:261 */     super.a(tag);
/* 323:    */     
/* 324:263 */     by items = tag.m("Items");
/* 325:264 */     this.contents = new ur[k_()];
/* 326:265 */     for (int i = 0; i < items.c(); i++)
/* 327:    */     {
/* 328:266 */       bq item = (bq)items.b(i);
/* 329:    */       
/* 330:268 */       int j = item.c("Slot") & 0xFF;
/* 331:269 */       if ((j >= 0) && (j < this.contents.length)) {
/* 332:270 */         this.contents[j] = ur.a(item);
/* 333:    */       }
/* 334:    */     }
/* 335:274 */     this.buffer.readFromNBT(tag);
/* 336:275 */     this.count = tag.e("cnt");
/* 337:276 */     this.mode = tag.c("mode");
/* 338:    */   }
/* 339:    */   
/* 340:    */   public void b(bq tag)
/* 341:    */   {
/* 342:280 */     super.b(tag);
/* 343:    */     
/* 344:282 */     by items = new by();
/* 345:283 */     for (int i = 0; i < this.contents.length; i++) {
/* 346:284 */       if (this.contents[i] != null)
/* 347:    */       {
/* 348:285 */         bq item = new bq();
/* 349:286 */         item.a("Slot", (byte)i);
/* 350:287 */         this.contents[i].b(item);
/* 351:288 */         items.a(item);
/* 352:    */       }
/* 353:    */     }
/* 354:291 */     tag.a("Items", items);
/* 355:292 */     this.buffer.writeToNBT(tag);
/* 356:293 */     tag.a("cnt", this.count);
/* 357:294 */     tag.a("mode", this.mode);
/* 358:    */   }
/* 359:    */   
/* 360:297 */   TubeBuffer buffer = new TubeBuffer();
/* 361:298 */   int count = 0;
/* 362:299 */   public byte mode = 0;
/* 363:    */   protected ur[] contents;
/* 364:301 */   protected MachineLib.FilterMap filterMap = null;
/* 365:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileItemDetect
 * JD-Core Version:    0.7.0.1
 */