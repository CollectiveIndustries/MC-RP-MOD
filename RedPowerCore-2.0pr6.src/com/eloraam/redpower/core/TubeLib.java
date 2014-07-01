/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import java.util.Arrays;
/*   5:    */ import java.util.HashSet;
/*   6:    */ import java.util.PriorityQueue;
/*   7:    */ import la;
/*   8:    */ import net.minecraftforge.common.ForgeDirection;
/*   9:    */ import net.minecraftforge.common.ISidedInventory;
/*  10:    */ import ur;
/*  11:    */ import yc;
/*  12:    */ import ym;
/*  13:    */ 
/*  14:    */ public class TubeLib
/*  15:    */ {
/*  16:    */   public static void addCompatibleMapping(int a, int b)
/*  17:    */   {
/*  18: 15 */     tubeClassMapping.add(Arrays.asList(new Integer[] { Integer.valueOf(a), Integer.valueOf(b) }));
/*  19: 16 */     tubeClassMapping.add(Arrays.asList(new Integer[] { Integer.valueOf(b), Integer.valueOf(a) }));
/*  20:    */   }
/*  21:    */   
/*  22:    */   public static boolean isCompatible(int a, int b)
/*  23:    */   {
/*  24: 20 */     if (a != b) {}
/*  25: 20 */     return tubeClassMapping.contains(Arrays.asList(new Integer[] { Integer.valueOf(a), Integer.valueOf(b) }));
/*  26:    */   }
/*  27:    */   
/*  28: 26 */   private static HashSet tubeClassMapping = new HashSet();
/*  29:    */   
/*  30:    */   static
/*  31:    */   {
/*  32: 28 */     addCompatibleMapping(0, 17);
/*  33: 29 */     addCompatibleMapping(17, 18);
/*  34: 30 */     for (int i = 0; i < 16; i++)
/*  35:    */     {
/*  36: 31 */       addCompatibleMapping(0, 1 + i);
/*  37: 32 */       addCompatibleMapping(17, 1 + i);
/*  38: 33 */       addCompatibleMapping(17, 19 + i);
/*  39: 34 */       addCompatibleMapping(18, 19 + i);
/*  40:    */     }
/*  41:    */   }
/*  42:    */   
/*  43:    */   private static boolean isConSide(ym iba, int i, int j, int k, int col, int side)
/*  44:    */   {
/*  45: 40 */     any te = iba.q(i, j, k);
/*  46: 41 */     if ((isCompatible(col, 0)) && 
/*  47: 42 */       ((te instanceof la))) {
/*  48: 43 */       if ((te instanceof ISidedInventory))
/*  49:    */       {
/*  50: 44 */         ISidedInventory isi = (ISidedInventory)te;
/*  51: 45 */         if (isi.getSizeInventorySide(ForgeDirection.getOrientation(side)) > 0) {
/*  52: 48 */           return true;
/*  53:    */         }
/*  54:    */       }
/*  55:    */       else
/*  56:    */       {
/*  57: 49 */         return true;
/*  58:    */       }
/*  59:    */     }
/*  60: 52 */     if ((te instanceof ITubeConnectable))
/*  61:    */     {
/*  62: 53 */       ITubeConnectable itc = (ITubeConnectable)te;
/*  63: 54 */       if (!isCompatible(col, itc.getTubeConClass())) {
/*  64: 55 */         return false;
/*  65:    */       }
/*  66: 56 */       int s = itc.getTubeConnectableSides();
/*  67: 57 */       return (s & 1 << side) > 0;
/*  68:    */     }
/*  69: 59 */     return false;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public static int getConnections(ym iba, int i, int j, int k)
/*  73:    */   {
/*  74: 63 */     ITubeConnectable itc = (ITubeConnectable)CoreLib.getTileEntity(iba, i, j, k, ITubeConnectable.class);
/*  75: 65 */     if (itc == null) {
/*  76: 65 */       return 0;
/*  77:    */     }
/*  78: 67 */     int trs = 0;
/*  79: 68 */     int col = itc.getTubeConClass();
/*  80: 69 */     int sides = itc.getTubeConnectableSides();
/*  81: 70 */     if (((sides & 0x1) > 0) && (isConSide(iba, i, j - 1, k, col, 1))) {
/*  82: 70 */       trs |= 0x1;
/*  83:    */     }
/*  84: 71 */     if (((sides & 0x2) > 0) && (isConSide(iba, i, j + 1, k, col, 0))) {
/*  85: 71 */       trs |= 0x2;
/*  86:    */     }
/*  87: 72 */     if (((sides & 0x4) > 0) && (isConSide(iba, i, j, k - 1, col, 3))) {
/*  88: 72 */       trs |= 0x4;
/*  89:    */     }
/*  90: 73 */     if (((sides & 0x8) > 0) && (isConSide(iba, i, j, k + 1, col, 2))) {
/*  91: 73 */       trs |= 0x8;
/*  92:    */     }
/*  93: 74 */     if (((sides & 0x10) > 0) && (isConSide(iba, i - 1, j, k, col, 5))) {
/*  94: 74 */       trs |= 0x10;
/*  95:    */     }
/*  96: 75 */     if (((sides & 0x20) > 0) && (isConSide(iba, i + 1, j, k, col, 4))) {
/*  97: 75 */       trs |= 0x20;
/*  98:    */     }
/*  99: 76 */     return trs;
/* 100:    */   }
/* 101:    */   
/* 102:    */   private static class WorldRoute
/* 103:    */     implements Comparable
/* 104:    */   {
/* 105:    */     public WorldCoord wc;
/* 106:    */     public int start;
/* 107:    */     public int side;
/* 108:    */     public int weight;
/* 109:    */     
/* 110:    */     public WorldRoute(WorldCoord w, int st, int s, int wt)
/* 111:    */     {
/* 112: 81 */       this.wc = w;this.start = st;this.side = s;
/* 113: 82 */       this.weight = wt;
/* 114:    */     }
/* 115:    */     
/* 116:    */     public int compareTo(Object obj)
/* 117:    */     {
/* 118: 86 */       WorldRoute wr = (WorldRoute)obj;
/* 119: 87 */       return this.weight - wr.weight;
/* 120:    */     }
/* 121:    */     
/* 122: 93 */     public boolean solved = false;
/* 123:    */   }
/* 124:    */   
/* 125:    */   private static class RouteFinder
/* 126:    */   {
/* 127:    */     public RouteFinder(yc world)
/* 128:    */     {
/* 129: 98 */       this.worldObj = world;
/* 130:    */     }
/* 131:    */     
/* 132:    */     public void addPoint(WorldCoord wc, int start, int side, int weight)
/* 133:    */     {
/* 134:103 */       ITubeConnectable itc = (ITubeConnectable)CoreLib.getTileEntity(this.worldObj, wc, ITubeConnectable.class);
/* 135:105 */       if (itc == null) {
/* 136:105 */         return;
/* 137:    */       }
/* 138:106 */       if (!itc.canRouteItems()) {
/* 139:106 */         return;
/* 140:    */       }
/* 141:107 */       if (this.scanmap.contains(wc)) {
/* 142:107 */         return;
/* 143:    */       }
/* 144:108 */       this.scanmap.add(wc);
/* 145:109 */       this.scanpos.add(new TubeLib.WorldRoute(wc, start, side ^ 0x1, weight));
/* 146:    */     }
/* 147:    */     
/* 148:    */     public int find(WorldCoord wc, int sides)
/* 149:    */     {
/* 150:113 */       for (int n = 0; n < 6; n++) {
/* 151:114 */         if ((sides & 1 << n) != 0)
/* 152:    */         {
/* 153:115 */           WorldCoord wcp = wc.copy();
/* 154:116 */           wcp.step(n);
/* 155:117 */           addPoint(wcp, n, n, n == this.startDir ? 0 : 1);
/* 156:    */         }
/* 157:    */       }
/* 158:121 */       while (this.scanpos.size() > 0)
/* 159:    */       {
/* 160:122 */         TubeLib.WorldRoute wr = (TubeLib.WorldRoute)this.scanpos.poll();
/* 161:123 */         if (wr.solved)
/* 162:    */         {
/* 163:124 */           this.result = wr;
/* 164:125 */           return wr.start;
/* 165:    */         }
/* 166:128 */         int cons = TubeLib.getConnections(this.worldObj, wr.wc.x, wr.wc.y, wr.wc.z);
/* 167:130 */         for (int n = 0; n < 6; n++) {
/* 168:131 */           if ((n != wr.side) && 
/* 169:132 */             ((cons & 1 << n) != 0))
/* 170:    */           {
/* 171:134 */             WorldCoord wcp = wr.wc.copy();
/* 172:135 */             wcp.step(n);
/* 173:    */             
/* 174:137 */             addPoint(wcp, wr.start, n, wr.weight + 2);
/* 175:    */           }
/* 176:    */         }
/* 177:    */       }
/* 178:140 */       return -1;
/* 179:    */     }
/* 180:    */     
/* 181:    */     public WorldCoord getResultPoint()
/* 182:    */     {
/* 183:144 */       return this.result.wc;
/* 184:    */     }
/* 185:    */     
/* 186:147 */     int startDir = 0;
/* 187:    */     TubeLib.WorldRoute result;
/* 188:    */     yc worldObj;
/* 189:150 */     HashSet scanmap = new HashSet();
/* 190:151 */     PriorityQueue scanpos = new PriorityQueue();
/* 191:    */   }
/* 192:    */   
/* 193:    */   private static class OutRouteFinder
/* 194:    */     extends TubeLib.RouteFinder
/* 195:    */   {
/* 196:    */     int state;
/* 197:    */     TubeItem tubeItem;
/* 198:    */     
/* 199:    */     public OutRouteFinder(yc world, TubeItem ti, int st)
/* 200:    */     {
/* 201:157 */       super();
/* 202:158 */       this.state = st;
/* 203:159 */       this.tubeItem = ti;
/* 204:    */     }
/* 205:    */     
/* 206:    */     public void addPoint(WorldCoord wc, int start, int side, int weight)
/* 207:    */     {
/* 208:164 */       int side1 = (side ^ 0x1) & 0xFF;
/* 209:165 */       if ((this.state != 3) && (this.tubeItem.priority == 0) && (MachineLib.canAddToInventory(this.worldObj, this.tubeItem.item, wc, side1)))
/* 210:    */       {
/* 211:168 */         TubeLib.WorldRoute nr = new TubeLib.WorldRoute(wc, start, side, weight);
/* 212:169 */         nr.solved = true;
/* 213:170 */         this.scanpos.add(nr);
/* 214:171 */         return;
/* 215:    */       }
/* 216:173 */       ITubeConnectable itc = (ITubeConnectable)CoreLib.getTileEntity(this.worldObj, wc, ITubeConnectable.class);
/* 217:175 */       if (itc == null) {
/* 218:175 */         return;
/* 219:    */       }
/* 220:177 */       if (itc.tubeItemCanEnter(side1, this.state, this.tubeItem))
/* 221:    */       {
/* 222:178 */         TubeLib.WorldRoute nr = new TubeLib.WorldRoute(wc, start, side1, weight + itc.tubeWeight(side1, this.state));
/* 223:    */         
/* 224:180 */         nr.solved = true;
/* 225:181 */         this.scanpos.add(nr);
/* 226:182 */         return;
/* 227:    */       }
/* 228:184 */       if (!itc.tubeItemCanEnter(side1, 0, this.tubeItem)) {
/* 229:184 */         return;
/* 230:    */       }
/* 231:185 */       if (!itc.canRouteItems()) {
/* 232:185 */         return;
/* 233:    */       }
/* 234:186 */       if (this.scanmap.contains(wc)) {
/* 235:186 */         return;
/* 236:    */       }
/* 237:187 */       this.scanmap.add(wc);
/* 238:188 */       this.scanpos.add(new TubeLib.WorldRoute(wc, start, side1, weight + itc.tubeWeight(side1, this.state)));
/* 239:    */     }
/* 240:    */   }
/* 241:    */   
/* 242:    */   public static class InRouteFinder
/* 243:    */     extends TubeLib.RouteFinder
/* 244:    */   {
/* 245:    */     MachineLib.FilterMap filterMap;
/* 246:    */     
/* 247:    */     public InRouteFinder(yc world, MachineLib.FilterMap map)
/* 248:    */     {
/* 249:197 */       super();
/* 250:198 */       this.filterMap = map;
/* 251:    */     }
/* 252:    */     
/* 253:    */     public void addPoint(WorldCoord wc, int st, int side, int weight)
/* 254:    */     {
/* 255:203 */       la inv = MachineLib.getInventory(this.worldObj, wc);
/* 256:204 */       if (inv == null)
/* 257:    */       {
/* 258:205 */         super.addPoint(wc, st, side, weight);
/* 259:206 */         return;
/* 260:    */       }
/* 261:209 */       int side2 = (side ^ 0x1) & 0x3F;
/* 262:210 */       int istart = 0;
/* 263:211 */       int len = inv.k_();
/* 264:212 */       if ((inv instanceof ISidedInventory))
/* 265:    */       {
/* 266:213 */         ISidedInventory isi = (ISidedInventory)inv;
/* 267:214 */         istart = isi.getStartInventorySide(ForgeDirection.getOrientation(side2));
/* 268:    */         
/* 269:216 */         len = isi.getSizeInventorySide(ForgeDirection.getOrientation(side2));
/* 270:    */       }
/* 271:220 */       if (this.filterMap.size() == 0)
/* 272:    */       {
/* 273:221 */         if (!MachineLib.emptyInventory(inv, istart, len))
/* 274:    */         {
/* 275:222 */           TubeLib.WorldRoute nr = new TubeLib.WorldRoute(wc, 0, side2, weight);
/* 276:223 */           nr.solved = true;
/* 277:224 */           this.scanpos.add(nr);
/* 278:225 */           return;
/* 279:    */         }
/* 280:227 */         super.addPoint(wc, st, side, weight);
/* 281:228 */         return;
/* 282:    */       }
/* 283:230 */       int sm = -1;
/* 284:231 */       if (this.subFilt < 0) {
/* 285:232 */         sm = MachineLib.matchAnyStack(this.filterMap, inv, istart, len);
/* 286:235 */       } else if (MachineLib.matchOneStack(this.filterMap, inv, istart, len, this.subFilt)) {
/* 287:237 */         sm = this.subFilt;
/* 288:    */       }
/* 289:239 */       if (sm < 0)
/* 290:    */       {
/* 291:240 */         super.addPoint(wc, st, side, weight);
/* 292:241 */         return;
/* 293:    */       }
/* 294:243 */       TubeLib.WorldRoute nr = new TubeLib.WorldRoute(wc, sm, side2, weight);
/* 295:244 */       nr.solved = true;
/* 296:245 */       this.scanpos.add(nr);
/* 297:    */     }
/* 298:    */     
/* 299:    */     public void setSubFilt(int sf)
/* 300:    */     {
/* 301:248 */       this.subFilt = sf;
/* 302:    */     }
/* 303:    */     
/* 304:    */     public int getResultSide()
/* 305:    */     {
/* 306:251 */       return this.result.side;
/* 307:    */     }
/* 308:    */     
/* 309:255 */     int subFilt = -1;
/* 310:    */   }
/* 311:    */   
/* 312:    */   public static class RequestRouteFinder
/* 313:    */     extends TubeLib.RouteFinder
/* 314:    */   {
/* 315:    */     TubeItem tubeItem;
/* 316:    */     
/* 317:    */     public RequestRouteFinder(yc world, TubeItem item)
/* 318:    */     {
/* 319:260 */       super();
/* 320:261 */       this.tubeItem = item;
/* 321:    */     }
/* 322:    */     
/* 323:    */     public void addPoint(WorldCoord wc, int st, int side, int weight)
/* 324:    */     {
/* 325:266 */       ITubeRequest itr = (ITubeRequest)CoreLib.getTileEntity(this.worldObj, wc, ITubeRequest.class);
/* 326:268 */       if (itr != null)
/* 327:    */       {
/* 328:269 */         if (itr.requestTubeItem(this.tubeItem, false))
/* 329:    */         {
/* 330:270 */           TubeLib.WorldRoute nr = new TubeLib.WorldRoute(wc, 0, side, weight);
/* 331:    */           
/* 332:272 */           nr.solved = true;
/* 333:273 */           this.scanpos.add(nr);
/* 334:    */         }
/* 335:275 */         return;
/* 336:    */       }
/* 337:278 */       ITubeConnectable itc = (ITubeConnectable)CoreLib.getTileEntity(this.worldObj, wc, ITubeConnectable.class);
/* 338:280 */       if (itc == null) {
/* 339:280 */         return;
/* 340:    */       }
/* 341:282 */       int side1 = (side ^ 0x1) & 0xFF;
/* 342:283 */       if (!itc.tubeItemCanEnter(side1, 0, this.tubeItem)) {
/* 343:283 */         return;
/* 344:    */       }
/* 345:284 */       if (!itc.canRouteItems()) {
/* 346:284 */         return;
/* 347:    */       }
/* 348:285 */       if (this.scanmap.contains(wc)) {
/* 349:285 */         return;
/* 350:    */       }
/* 351:286 */       this.scanmap.add(wc);
/* 352:287 */       this.scanpos.add(new TubeLib.WorldRoute(wc, st, side1, weight + itc.tubeWeight(side1, 0)));
/* 353:    */     }
/* 354:    */   }
/* 355:    */   
/* 356:    */   public static int findRoute(yc world, WorldCoord wc, TubeItem te, int sides, int state)
/* 357:    */   {
/* 358:297 */     OutRouteFinder rf = new OutRouteFinder(world, te, state);
/* 359:298 */     return rf.find(wc, sides);
/* 360:    */   }
/* 361:    */   
/* 362:    */   public static int findRoute(yc world, WorldCoord wc, TubeItem te, int sides, int state, int start)
/* 363:    */   {
/* 364:303 */     OutRouteFinder rf = new OutRouteFinder(world, te, state);
/* 365:304 */     rf.startDir = start;
/* 366:305 */     return rf.find(wc, sides);
/* 367:    */   }
/* 368:    */   
/* 369:    */   public static boolean addToTubeRoute(yc world, ur ist, WorldCoord src, WorldCoord wc, int side)
/* 370:    */   {
/* 371:311 */     return addToTubeRoute(world, new TubeItem(0, ist), src, wc, side);
/* 372:    */   }
/* 373:    */   
/* 374:    */   public static boolean addToTubeRoute(yc world, TubeItem ti, WorldCoord src, WorldCoord wc, int side)
/* 375:    */   {
/* 376:316 */     ITubeConnectable ite = (ITubeConnectable)CoreLib.getTileEntity(world, wc, ITubeConnectable.class);
/* 377:318 */     if (ite == null) {
/* 378:318 */       return false;
/* 379:    */     }
/* 380:319 */     ti.mode = 1;
/* 381:320 */     int s = findRoute(world, src, ti, 1 << (side ^ 0x1), 1);
/* 382:321 */     if (s < 0) {
/* 383:321 */       return false;
/* 384:    */     }
/* 385:322 */     return ite.tubeItemEnter(side, 0, ti);
/* 386:    */   }
/* 387:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TubeLib
 * JD-Core Version:    0.7.0.1
 */