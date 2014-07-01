/*    1:     */ package com.eloraam.redpower.control;
/*    2:     */ 
/*    3:     */ import akf;
/*    4:     */ import amq;
/*    5:     */ import bq;
/*    6:     */ import com.eloraam.redpower.RedPowerControl;
/*    7:     */ import com.eloraam.redpower.core.BlockExtended;
/*    8:     */ import com.eloraam.redpower.core.CoreLib;
/*    9:     */ import com.eloraam.redpower.core.IFrameSupport;
/*   10:     */ import com.eloraam.redpower.core.IHandlePackets;
/*   11:     */ import com.eloraam.redpower.core.IRedbusConnectable;
/*   12:     */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   13:     */ import com.eloraam.redpower.core.RedbusLib;
/*   14:     */ import com.eloraam.redpower.core.TileExtended;
/*   15:     */ import com.eloraam.redpower.core.WorldCoord;
/*   16:     */ import ef;
/*   17:     */ import java.io.ByteArrayOutputStream;
/*   18:     */ import java.io.IOException;
/*   19:     */ import java.io.InputStream;
/*   20:     */ import java.util.Random;
/*   21:     */ import md;
/*   22:     */ import qx;
/*   23:     */ import ur;
/*   24:     */ import yc;
/*   25:     */ import ym;
/*   26:     */ 
/*   27:     */ public class TileCPU
/*   28:     */   extends TileExtended
/*   29:     */   implements IRedbusConnectable, IHandlePackets, IFrameSupport
/*   30:     */ {
/*   31:     */   public TileCPU()
/*   32:     */   {
/*   33:  21 */     this.memory = new byte[8192];
/*   34:  22 */     coldBootCPU();
/*   35:     */   }
/*   36:     */   
/*   37:     */   public void coldBootCPU()
/*   38:     */   {
/*   39:  28 */     this.addrPOR = 8192;this.addrBRK = 8192;
/*   40:  29 */     this.regSP = 512;this.regPC = 1024;
/*   41:  30 */     this.regR = 768;
/*   42:     */     
/*   43:  32 */     this.regA = 0;this.regX = 0;this.regY = 0;this.regD = 0;
/*   44:  33 */     this.flagC = false;this.flagZ = false;this.flagID = false;
/*   45:  34 */     this.flagD = false;this.flagBRK = false;this.flagO = false;this.flagN = false;
/*   46:     */     
/*   47:  36 */     this.flagE = true;this.flagM = true;this.flagX = true;
/*   48:     */     
/*   49:  38 */     this.memory[0] = ((byte)this.byte0);this.memory[1] = ((byte)this.byte1);
/*   50:     */     
/*   51:  40 */     InputStream is = RedPowerControl.class.getResourceAsStream("/eloraam/control/rpcboot.bin");
/*   52:     */     try
/*   53:     */     {
/*   54:     */       try
/*   55:     */       {
/*   56:  44 */         is.read(this.memory, 1024, 256);
/*   57:     */       }
/*   58:     */       finally
/*   59:     */       {
/*   60:  46 */         if (is != null) {
/*   61:  46 */           is.close();
/*   62:     */         }
/*   63:     */       }
/*   64:     */     }
/*   65:     */     catch (IOException e)
/*   66:     */     {
/*   67:  49 */       e.printStackTrace();
/*   68:     */     }
/*   69:  51 */     this.sliceCycles = -1;
/*   70:     */   }
/*   71:     */   
/*   72:     */   public void warmBootCPU()
/*   73:     */   {
/*   74:  55 */     if (this.sliceCycles >= 0)
/*   75:     */     {
/*   76:  56 */       this.regSP = 512;this.regR = 768;
/*   77:  57 */       this.regPC = this.addrPOR;
/*   78:     */     }
/*   79:  59 */     this.sliceCycles = 0;
/*   80:     */   }
/*   81:     */   
/*   82:     */   public void haltCPU()
/*   83:     */   {
/*   84:  63 */     this.sliceCycles = -1;
/*   85:     */   }
/*   86:     */   
/*   87:     */   public boolean isRunning()
/*   88:     */   {
/*   89:  67 */     return this.sliceCycles >= 0;
/*   90:     */   }
/*   91:     */   
/*   92:     */   public int rbGetAddr()
/*   93:     */   {
/*   94:  73 */     return this.rbaddr;
/*   95:     */   }
/*   96:     */   
/*   97:     */   public void rbSetAddr(int addr) {}
/*   98:     */   
/*   99:     */   public int rbRead(int reg)
/*  100:     */   {
/*  101:  79 */     if (!this.mmuEnRBW) {
/*  102:  79 */       return 0;
/*  103:     */     }
/*  104:  80 */     return readOnlyMem(this.mmuRBW + reg);
/*  105:     */   }
/*  106:     */   
/*  107:     */   public void rbWrite(int reg, int dat)
/*  108:     */   {
/*  109:  84 */     if (!this.mmuEnRBW) {
/*  110:  84 */       return;
/*  111:     */     }
/*  112:  85 */     writeOnlyMem(this.mmuRBW + reg, dat);
/*  113:     */   }
/*  114:     */   
/*  115:     */   public int getConnectableMask()
/*  116:     */   {
/*  117:  91 */     return 16777215;
/*  118:     */   }
/*  119:     */   
/*  120:     */   public int getConnectClass(int side)
/*  121:     */   {
/*  122:  95 */     return 66;
/*  123:     */   }
/*  124:     */   
/*  125:     */   public int getCornerPowerMode()
/*  126:     */   {
/*  127:  98 */     return 0;
/*  128:     */   }
/*  129:     */   
/*  130:     */   public void onBlockPlaced(ur ist, int side, md ent)
/*  131:     */   {
/*  132: 104 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) + 1 & 0x3);
/*  133:     */   }
/*  134:     */   
/*  135:     */   public boolean onBlockActivated(qx player)
/*  136:     */   {
/*  137: 108 */     if (player.ah()) {
/*  138: 109 */       return false;
/*  139:     */     }
/*  140: 110 */     if (CoreLib.isClient(this.k)) {
/*  141: 111 */       return true;
/*  142:     */     }
/*  143: 112 */     player.openGui(RedPowerControl.instance, 2, this.k, this.l, this.m, this.n);
/*  144:     */     
/*  145: 114 */     return true;
/*  146:     */   }
/*  147:     */   
/*  148:     */   public int getBlockID()
/*  149:     */   {
/*  150: 118 */     return RedPowerControl.blockPeripheral.cm;
/*  151:     */   }
/*  152:     */   
/*  153:     */   public int getExtendedID()
/*  154:     */   {
/*  155: 122 */     return 1;
/*  156:     */   }
/*  157:     */   
/*  158:     */   public boolean isUseableByPlayer(qx player)
/*  159:     */   {
/*  160: 128 */     if (this.k.q(this.l, this.m, this.n) != this) {
/*  161: 129 */       return false;
/*  162:     */     }
/*  163: 130 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/*  164:     */   }
/*  165:     */   
/*  166:     */   protected void refreshBackplane()
/*  167:     */   {
/*  168: 137 */     boolean bpok = true;
/*  169: 138 */     WorldCoord wc = new WorldCoord(this);
/*  170: 139 */     for (int i = 0; i < 7; i++) {
/*  171: 140 */       if (!bpok)
/*  172:     */       {
/*  173: 141 */         this.backplane[i] = null;
/*  174:     */       }
/*  175:     */       else
/*  176:     */       {
/*  177: 144 */         wc.step(CoreLib.rotToSide(this.Rotation));
/*  178: 145 */         TileBackplane tbp = (TileBackplane)CoreLib.getTileEntity(this.k, wc, TileBackplane.class);
/*  179:     */         
/*  180:     */ 
/*  181: 148 */         this.backplane[i] = tbp;
/*  182: 149 */         if (tbp == null) {
/*  183: 149 */           bpok = false;
/*  184:     */         }
/*  185:     */       }
/*  186:     */     }
/*  187:     */   }
/*  188:     */   
/*  189:     */   public void g()
/*  190:     */   {
/*  191: 156 */     this.rtcTicks += 1;
/*  192: 158 */     if (this.sliceCycles < 0) {
/*  193: 158 */       return;
/*  194:     */     }
/*  195: 159 */     this.rbTimeout = false;
/*  196: 160 */     this.rbCache = null;
/*  197: 161 */     this.waiTimeout = false;
/*  198: 162 */     this.sliceCycles += 1000;
/*  199: 163 */     if (this.sliceCycles > 100000) {
/*  200: 163 */       this.sliceCycles = 100000;
/*  201:     */     }
/*  202: 165 */     refreshBackplane();
/*  203: 166 */     while ((this.sliceCycles > 0) && 
/*  204: 167 */       (!this.waiTimeout) && 
/*  205: 168 */       (!this.rbTimeout))
/*  206:     */     {
/*  207: 172 */       this.sliceCycles -= 1;
/*  208: 173 */       executeInsn();
/*  209:     */     }
/*  210:     */   }
/*  211:     */   
/*  212:     */   protected int readOnlyMem(int addr)
/*  213:     */   {
/*  214: 180 */     addr &= 0xFFFF;
/*  215: 181 */     if (addr < 8192) {
/*  216: 181 */       return this.memory[addr] & 0xFF;
/*  217:     */     }
/*  218: 182 */     int atop = (addr >> 13) - 1;
/*  219: 183 */     if (this.backplane[atop] == null) {
/*  220: 183 */       return 255;
/*  221:     */     }
/*  222: 184 */     return this.backplane[atop].readBackplane(addr & 0x1FFF);
/*  223:     */   }
/*  224:     */   
/*  225:     */   public int readMem(int addr)
/*  226:     */   {
/*  227: 189 */     if ((this.mmuEnRB) && (addr >= this.mmuRBB) && (addr < this.mmuRBB + 256))
/*  228:     */     {
/*  229: 190 */       if (this.rbCache == null) {
/*  230: 191 */         this.rbCache = RedbusLib.getAddr(this.k, new WorldCoord(this), this.mmuRBA);
/*  231:     */       }
/*  232: 194 */       if (this.rbCache == null)
/*  233:     */       {
/*  234: 195 */         this.rbTimeout = true;
/*  235: 196 */         return 0;
/*  236:     */       }
/*  237: 198 */       int tr = this.rbCache.rbRead(addr - this.mmuRBB);
/*  238:     */       
/*  239: 200 */       return tr;
/*  240:     */     }
/*  241: 202 */     return readOnlyMem(addr);
/*  242:     */   }
/*  243:     */   
/*  244:     */   protected void writeOnlyMem(int addr, int val)
/*  245:     */   {
/*  246: 206 */     addr &= 0xFFFF;
/*  247: 207 */     if (addr < 8192)
/*  248:     */     {
/*  249: 208 */       this.memory[addr] = ((byte)val);
/*  250: 209 */       return;
/*  251:     */     }
/*  252: 211 */     int atop = (addr >> 13) - 1;
/*  253: 212 */     if (this.backplane[atop] == null) {
/*  254: 212 */       return;
/*  255:     */     }
/*  256: 213 */     this.backplane[atop].writeBackplane(addr & 0x1FFF, val);
/*  257:     */   }
/*  258:     */   
/*  259:     */   public void writeMem(int addr, int val)
/*  260:     */   {
/*  261: 218 */     if ((this.mmuEnRB) && (addr >= this.mmuRBB) && (addr < this.mmuRBB + 256))
/*  262:     */     {
/*  263: 219 */       if (this.rbCache == null) {
/*  264: 220 */         this.rbCache = RedbusLib.getAddr(this.k, new WorldCoord(this), this.mmuRBA);
/*  265:     */       }
/*  266: 223 */       if (this.rbCache == null)
/*  267:     */       {
/*  268: 224 */         this.rbTimeout = true;
/*  269: 225 */         return;
/*  270:     */       }
/*  271: 227 */       this.rbCache.rbWrite(addr - this.mmuRBB, val & 0xFF);
/*  272: 228 */       return;
/*  273:     */     }
/*  274: 230 */     writeOnlyMem(addr, val);
/*  275:     */   }
/*  276:     */   
/*  277:     */   private void incPC()
/*  278:     */   {
/*  279: 233 */     this.regPC = (this.regPC + 1 & 0xFFFF);
/*  280:     */   }
/*  281:     */   
/*  282:     */   private int maskM()
/*  283:     */   {
/*  284: 235 */     return this.flagM ? 255 : 65535;
/*  285:     */   }
/*  286:     */   
/*  287:     */   private int maskX()
/*  288:     */   {
/*  289: 236 */     return this.flagX ? 255 : 65535;
/*  290:     */   }
/*  291:     */   
/*  292:     */   private int negM()
/*  293:     */   {
/*  294: 237 */     return this.flagM ? 128 : 32768;
/*  295:     */   }
/*  296:     */   
/*  297:     */   private int negX()
/*  298:     */   {
/*  299: 238 */     return this.flagX ? 128 : 32768;
/*  300:     */   }
/*  301:     */   
/*  302:     */   private int readB()
/*  303:     */   {
/*  304: 241 */     int i = readMem(this.regPC);incPC();
/*  305: 242 */     return i;
/*  306:     */   }
/*  307:     */   
/*  308:     */   private int readM()
/*  309:     */   {
/*  310: 245 */     int i = readMem(this.regPC);incPC();
/*  311: 246 */     if (!this.flagM)
/*  312:     */     {
/*  313: 246 */       i |= readMem(this.regPC) << 8;incPC();
/*  314:     */     }
/*  315: 247 */     return i;
/*  316:     */   }
/*  317:     */   
/*  318:     */   private int readX()
/*  319:     */   {
/*  320: 250 */     int i = readMem(this.regPC);incPC();
/*  321: 251 */     if (!this.flagX)
/*  322:     */     {
/*  323: 251 */       i |= readMem(this.regPC) << 8;incPC();
/*  324:     */     }
/*  325: 252 */     return i;
/*  326:     */   }
/*  327:     */   
/*  328:     */   private int readM(int addr)
/*  329:     */   {
/*  330: 255 */     int i = readMem(addr);
/*  331: 256 */     if (!this.flagM) {
/*  332: 256 */       i |= readMem(addr + 1) << 8;
/*  333:     */     }
/*  334: 257 */     return i;
/*  335:     */   }
/*  336:     */   
/*  337:     */   private int readX(int addr)
/*  338:     */   {
/*  339: 260 */     int i = readMem(addr);
/*  340: 261 */     if (!this.flagX) {
/*  341: 261 */       i |= readMem(addr + 1) << 8;
/*  342:     */     }
/*  343: 262 */     return i;
/*  344:     */   }
/*  345:     */   
/*  346:     */   private void writeM(int addr, int reg)
/*  347:     */   {
/*  348: 265 */     writeMem(addr, reg);
/*  349: 266 */     if (!this.flagM) {
/*  350: 266 */       writeMem(addr + 1, reg >> 8);
/*  351:     */     }
/*  352:     */   }
/*  353:     */   
/*  354:     */   private void writeX(int addr, int reg)
/*  355:     */   {
/*  356: 269 */     writeMem(addr, reg);
/*  357: 270 */     if (!this.flagX) {
/*  358: 270 */       writeMem(addr + 1, reg >> 8);
/*  359:     */     }
/*  360:     */   }
/*  361:     */   
/*  362:     */   private int readBX()
/*  363:     */   {
/*  364: 274 */     int i = readMem(this.regPC) + this.regX;
/*  365: 275 */     if (this.flagX) {
/*  366: 275 */       i &= 0xFF;
/*  367:     */     }
/*  368: 276 */     incPC();
/*  369: 277 */     return i;
/*  370:     */   }
/*  371:     */   
/*  372:     */   private int readBY()
/*  373:     */   {
/*  374: 280 */     int i = readMem(this.regPC) + this.regY;
/*  375: 281 */     if (this.flagX) {
/*  376: 281 */       i &= 0xFF;
/*  377:     */     }
/*  378: 282 */     incPC();
/*  379: 283 */     return i;
/*  380:     */   }
/*  381:     */   
/*  382:     */   private int readBS()
/*  383:     */   {
/*  384: 286 */     int i = readMem(this.regPC) + this.regSP & 0xFFFF;
/*  385: 287 */     incPC();
/*  386: 288 */     return i;
/*  387:     */   }
/*  388:     */   
/*  389:     */   private int readBR()
/*  390:     */   {
/*  391: 291 */     int i = readMem(this.regPC) + this.regR & 0xFFFF;
/*  392: 292 */     incPC();
/*  393: 293 */     return i;
/*  394:     */   }
/*  395:     */   
/*  396:     */   private int readBSWY()
/*  397:     */   {
/*  398: 296 */     int i = readMem(this.regPC) + this.regSP & 0xFFFF;
/*  399: 297 */     incPC();
/*  400: 298 */     return readW(i) + this.regY & 0xFFFF;
/*  401:     */   }
/*  402:     */   
/*  403:     */   private int readBRWY()
/*  404:     */   {
/*  405: 301 */     int i = readMem(this.regPC) + this.regR & 0xFFFF;
/*  406: 302 */     incPC();
/*  407: 303 */     return readW(i) + this.regY & 0xFFFF;
/*  408:     */   }
/*  409:     */   
/*  410:     */   private int readW()
/*  411:     */   {
/*  412: 306 */     int i = readMem(this.regPC);incPC();
/*  413: 307 */     i |= readMem(this.regPC) << 8;incPC();
/*  414: 308 */     return i;
/*  415:     */   }
/*  416:     */   
/*  417:     */   private int readW(int addr)
/*  418:     */   {
/*  419: 311 */     int i = readMem(addr);
/*  420: 312 */     i |= readMem(addr + 1) << 8;
/*  421: 313 */     return i;
/*  422:     */   }
/*  423:     */   
/*  424:     */   private int readWX()
/*  425:     */   {
/*  426: 316 */     int i = readMem(this.regPC);incPC();
/*  427: 317 */     i |= readMem(this.regPC) << 8;incPC();
/*  428: 318 */     return i + this.regX & 0xFFFF;
/*  429:     */   }
/*  430:     */   
/*  431:     */   private int readWY()
/*  432:     */   {
/*  433: 321 */     int i = readMem(this.regPC);incPC();
/*  434: 322 */     i |= readMem(this.regPC) << 8;incPC();
/*  435: 323 */     return i + this.regY & 0xFFFF;
/*  436:     */   }
/*  437:     */   
/*  438:     */   private int readWXW()
/*  439:     */   {
/*  440: 326 */     int i = readMem(this.regPC);incPC();
/*  441: 327 */     i |= readMem(this.regPC) << 8;incPC();
/*  442: 328 */     i = i + this.regX & 0xFFFF;
/*  443: 329 */     int j = readMem(i);
/*  444: 330 */     j |= readMem(i + 1) << 8;
/*  445: 331 */     return j;
/*  446:     */   }
/*  447:     */   
/*  448:     */   private int readBW()
/*  449:     */   {
/*  450: 334 */     int i = readMem(this.regPC);incPC();
/*  451: 335 */     int j = readMem(i);
/*  452: 336 */     j |= readMem(i + 1) << 8;
/*  453: 337 */     return j;
/*  454:     */   }
/*  455:     */   
/*  456:     */   private int readWW()
/*  457:     */   {
/*  458: 340 */     int i = readMem(this.regPC);incPC();
/*  459: 341 */     i |= readMem(this.regPC) << 8;incPC();
/*  460: 342 */     int j = readMem(i);
/*  461: 343 */     j |= readMem(i + 1) << 8;
/*  462: 344 */     return j;
/*  463:     */   }
/*  464:     */   
/*  465:     */   private int readBXW()
/*  466:     */   {
/*  467: 347 */     int i = readMem(this.regPC) + this.regX & 0xFF;incPC();
/*  468: 348 */     int j = readMem(i);
/*  469: 349 */     j |= readMem(i + 1) << 8;
/*  470: 350 */     return j;
/*  471:     */   }
/*  472:     */   
/*  473:     */   private int readBWY()
/*  474:     */   {
/*  475: 353 */     int i = readMem(this.regPC);incPC();
/*  476: 354 */     int j = readMem(i);
/*  477: 355 */     j |= readMem(i + 1) << 8;
/*  478: 356 */     return j + this.regY & 0xFFFF;
/*  479:     */   }
/*  480:     */   
/*  481:     */   private void upNZ()
/*  482:     */   {
/*  483: 360 */     this.flagN = ((this.regA & negM()) > 0);
/*  484: 361 */     this.flagZ = (this.regA == 0);
/*  485:     */   }
/*  486:     */   
/*  487:     */   private void upNZ(int i)
/*  488:     */   {
/*  489: 365 */     this.flagN = ((i & negM()) > 0);
/*  490: 366 */     this.flagZ = (i == 0);
/*  491:     */   }
/*  492:     */   
/*  493:     */   private void upNZX(int i)
/*  494:     */   {
/*  495: 370 */     this.flagN = ((i & negX()) > 0);
/*  496: 371 */     this.flagZ = (i == 0);
/*  497:     */   }
/*  498:     */   
/*  499:     */   private void push1(int b)
/*  500:     */   {
/*  501: 375 */     if (this.flagE) {
/*  502: 376 */       this.regSP = (this.regSP - 1 & 0xFF | this.regSP & 0xFF00);
/*  503:     */     } else {
/*  504: 378 */       this.regSP = (this.regSP - 1 & 0xFFFF);
/*  505:     */     }
/*  506: 380 */     writeMem(this.regSP, b);
/*  507:     */   }
/*  508:     */   
/*  509:     */   private void push1r(int b)
/*  510:     */   {
/*  511: 384 */     this.regR = (this.regR - 1 & 0xFFFF);
/*  512: 385 */     writeMem(this.regR, b);
/*  513:     */   }
/*  514:     */   
/*  515:     */   private void push2(int w)
/*  516:     */   {
/*  517: 389 */     push1(w >> 8);
/*  518: 390 */     push1(w & 0xFF);
/*  519:     */   }
/*  520:     */   
/*  521:     */   private void push2r(int w)
/*  522:     */   {
/*  523: 394 */     push1r(w >> 8);
/*  524: 395 */     push1r(w & 0xFF);
/*  525:     */   }
/*  526:     */   
/*  527:     */   private void pushM(int b)
/*  528:     */   {
/*  529: 399 */     if (this.flagM) {
/*  530: 399 */       push1(b);
/*  531:     */     } else {
/*  532: 400 */       push2(b);
/*  533:     */     }
/*  534:     */   }
/*  535:     */   
/*  536:     */   private void pushX(int b)
/*  537:     */   {
/*  538: 404 */     if (this.flagX) {
/*  539: 404 */       push1(b);
/*  540:     */     } else {
/*  541: 405 */       push2(b);
/*  542:     */     }
/*  543:     */   }
/*  544:     */   
/*  545:     */   private void pushMr(int b)
/*  546:     */   {
/*  547: 409 */     if (this.flagM) {
/*  548: 409 */       push1r(b);
/*  549:     */     } else {
/*  550: 410 */       push2r(b);
/*  551:     */     }
/*  552:     */   }
/*  553:     */   
/*  554:     */   private void pushXr(int b)
/*  555:     */   {
/*  556: 414 */     if (this.flagX) {
/*  557: 414 */       push1r(b);
/*  558:     */     } else {
/*  559: 415 */       push2r(b);
/*  560:     */     }
/*  561:     */   }
/*  562:     */   
/*  563:     */   private int pop1()
/*  564:     */   {
/*  565: 419 */     int tr = readMem(this.regSP);
/*  566: 420 */     if (this.flagE) {
/*  567: 421 */       this.regSP = (this.regSP + 1 & 0xFF | this.regSP & 0xFF00);
/*  568:     */     } else {
/*  569: 423 */       this.regSP = (this.regSP + 1 & 0xFFFF);
/*  570:     */     }
/*  571: 425 */     return tr;
/*  572:     */   }
/*  573:     */   
/*  574:     */   private int pop1r()
/*  575:     */   {
/*  576: 429 */     int tr = readMem(this.regR);
/*  577: 430 */     this.regR = (this.regR + 1 & 0xFFFF);
/*  578: 431 */     return tr;
/*  579:     */   }
/*  580:     */   
/*  581:     */   private int pop2()
/*  582:     */   {
/*  583: 435 */     int tr = pop1();
/*  584: 436 */     tr |= pop1() << 8;
/*  585: 437 */     return tr;
/*  586:     */   }
/*  587:     */   
/*  588:     */   private int pop2r()
/*  589:     */   {
/*  590: 441 */     int tr = pop1r();
/*  591: 442 */     tr |= pop1r() << 8;
/*  592: 443 */     return tr;
/*  593:     */   }
/*  594:     */   
/*  595:     */   private int popM()
/*  596:     */   {
/*  597: 447 */     if (this.flagM) {
/*  598: 447 */       return pop1();
/*  599:     */     }
/*  600: 448 */     return pop2();
/*  601:     */   }
/*  602:     */   
/*  603:     */   private int popMr()
/*  604:     */   {
/*  605: 452 */     if (this.flagM) {
/*  606: 452 */       return pop1r();
/*  607:     */     }
/*  608: 453 */     return pop2r();
/*  609:     */   }
/*  610:     */   
/*  611:     */   private int popX()
/*  612:     */   {
/*  613: 457 */     if (this.flagX) {
/*  614: 457 */       return pop1();
/*  615:     */     }
/*  616: 458 */     return pop2();
/*  617:     */   }
/*  618:     */   
/*  619:     */   private int popXr()
/*  620:     */   {
/*  621: 462 */     if (this.flagX) {
/*  622: 462 */       return pop1r();
/*  623:     */     }
/*  624: 463 */     return pop2r();
/*  625:     */   }
/*  626:     */   
/*  627:     */   private int getFlags()
/*  628:     */   {
/*  629: 467 */     return (this.flagC ? 1 : 0) | (this.flagZ ? 2 : 0) | (this.flagID ? 4 : 0) | (this.flagD ? 8 : 0) | (this.flagX ? 16 : 0) | (this.flagM ? 32 : 0) | (this.flagO ? 64 : 0) | (this.flagN ? 128 : 0);
/*  630:     */   }
/*  631:     */   
/*  632:     */   private void setFlags(int flags)
/*  633:     */   {
/*  634: 475 */     this.flagC = ((flags & 0x1) > 0);
/*  635: 476 */     this.flagZ = ((flags & 0x2) > 0);
/*  636: 477 */     this.flagID = ((flags & 0x4) > 0);
/*  637: 478 */     this.flagD = ((flags & 0x8) > 0);
/*  638: 479 */     boolean m2 = (flags & 0x20) > 0;
/*  639: 480 */     this.flagO = ((flags & 0x40) > 0);
/*  640: 481 */     this.flagN = ((flags & 0x80) > 0);
/*  641: 483 */     if (this.flagE)
/*  642:     */     {
/*  643: 484 */       this.flagX = false;this.flagM = false;
/*  644:     */     }
/*  645:     */     else
/*  646:     */     {
/*  647: 486 */       this.flagX = ((flags & 0x10) > 0);
/*  648: 487 */       if (this.flagX)
/*  649:     */       {
/*  650: 487 */         this.regX &= 0xFF;this.regY &= 0xFF;
/*  651:     */       }
/*  652: 488 */       if (m2 != this.flagM)
/*  653:     */       {
/*  654: 489 */         if (m2)
/*  655:     */         {
/*  656: 490 */           this.regB = (this.regA >> 8);this.regA &= 0xFF;
/*  657:     */         }
/*  658:     */         else
/*  659:     */         {
/*  660: 492 */           this.regA |= this.regB << 8;
/*  661:     */         }
/*  662: 494 */         this.flagM = m2;
/*  663:     */       }
/*  664:     */     }
/*  665:     */   }
/*  666:     */   
/*  667:     */   private void i_adc(int val)
/*  668:     */   {
/*  669: 500 */     if (this.flagM)
/*  670:     */     {
/*  671: 501 */       if (this.flagD)
/*  672:     */       {
/*  673: 502 */         int v1 = (this.regA & 0xF) + (val & 0xF) + (this.flagC ? 1 : 0);
/*  674: 503 */         if (v1 > 9) {
/*  675: 503 */           v1 = (v1 + 6 & 0xF) + 16;
/*  676:     */         }
/*  677: 504 */         int v2 = (this.regA & 0xF0) + (val & 0xF0) + v1;
/*  678: 505 */         if (v2 > 160) {
/*  679: 505 */           v2 += 96;
/*  680:     */         }
/*  681: 506 */         this.flagC = (v2 > 100);
/*  682: 507 */         this.regA = (v2 & 0xFF);
/*  683: 508 */         this.flagO = false;
/*  684:     */       }
/*  685:     */       else
/*  686:     */       {
/*  687: 510 */         int v = this.regA + val + (this.flagC ? 1 : 0);
/*  688: 511 */         this.flagC = (v > 255);
/*  689: 512 */         this.flagO = (((v ^ this.regA) & (v ^ val) & 0x80) > 0);
/*  690:     */         
/*  691: 514 */         this.regA = (v & 0xFF);
/*  692:     */       }
/*  693:     */     }
/*  694:     */     else
/*  695:     */     {
/*  696: 518 */       int v = this.regA + val + (this.flagC ? 1 : 0);
/*  697: 519 */       this.flagC = (v > 65535);
/*  698: 520 */       this.flagO = (((v ^ this.regA) & (v ^ val) & 0x8000) > 0);
/*  699:     */       
/*  700: 522 */       this.regA = (v & 0xFFFF);
/*  701:     */     }
/*  702: 524 */     upNZ();
/*  703:     */   }
/*  704:     */   
/*  705:     */   private void i_sbc(int val)
/*  706:     */   {
/*  707: 528 */     if (this.flagM)
/*  708:     */     {
/*  709: 529 */       if (this.flagD)
/*  710:     */       {
/*  711: 530 */         int v1 = (this.regA & 0xF) - (val & 0xF) + (this.flagC ? 1 : 0) - 1;
/*  712: 531 */         if (v1 < 0) {
/*  713: 531 */           v1 = (v1 - 6 & 0xF) - 16;
/*  714:     */         }
/*  715: 532 */         int v2 = (this.regA & 0xF0) - (val & 0xF0) + v1;
/*  716: 533 */         if (v2 < 0) {
/*  717: 533 */           v2 -= 96;
/*  718:     */         }
/*  719: 534 */         this.flagC = (v2 < 100);
/*  720: 535 */         this.regA = (v2 & 0xFF);
/*  721: 536 */         this.flagO = false;
/*  722:     */       }
/*  723:     */       else
/*  724:     */       {
/*  725: 538 */         int v = this.regA - val + (this.flagC ? 1 : 0) - 1;
/*  726: 539 */         this.flagC = ((v & 0x100) == 0);
/*  727:     */         
/*  728: 541 */         this.flagO = (((v ^ this.regA) & (v ^ -val) & 0x80) > 0);
/*  729: 542 */         this.regA = (v & 0xFF);
/*  730:     */       }
/*  731:     */     }
/*  732:     */     else
/*  733:     */     {
/*  734: 546 */       int v = this.regA - val + (this.flagC ? 1 : 0) - 1;
/*  735: 547 */       this.flagC = ((v & 0x10000) == 0);
/*  736:     */       
/*  737: 549 */       this.flagO = (((v ^ this.regA) & (v ^ -val) & 0x8000) > 0);
/*  738: 550 */       this.regA = (v & 0xFFFF);
/*  739:     */     }
/*  740: 552 */     upNZ();
/*  741:     */   }
/*  742:     */   
/*  743:     */   private void i_mul(int val)
/*  744:     */   {
/*  745: 556 */     if (this.flagM)
/*  746:     */     {
/*  747:     */       int v;
/*  748:     */       int v;
/*  749: 558 */       if (this.flagC) {
/*  750: 559 */         v = (byte)val * (byte)this.regA;
/*  751:     */       } else {
/*  752: 561 */         v = val * this.regA;
/*  753:     */       }
/*  754: 563 */       this.regA = (v & 0xFF);
/*  755: 564 */       this.regD = (v >> 8 & 0xFF);
/*  756: 565 */       this.flagN = (v < 0);
/*  757: 566 */       this.flagZ = (v == 0);
/*  758: 567 */       this.flagO = ((this.regD != 0) && (this.regD != 255));
/*  759:     */     }
/*  760:     */     else
/*  761:     */     {
/*  762:     */       long v;
/*  763:     */       long v;
/*  764: 570 */       if (this.flagC) {
/*  765: 571 */         v = (short)val * (short)this.regA;
/*  766:     */       } else {
/*  767: 573 */         v = val * this.regA;
/*  768:     */       }
/*  769: 575 */       this.regA = ((int)(v & 0xFFFF));
/*  770: 576 */       this.regD = ((int)(v >> 16 & 0xFFFF));
/*  771: 577 */       this.flagN = (v < 0L);
/*  772: 578 */       this.flagZ = (v == 0L);
/*  773: 579 */       this.flagO = ((this.regD != 0) && (this.regD != 65535));
/*  774:     */     }
/*  775:     */   }
/*  776:     */   
/*  777:     */   private void i_div(int val)
/*  778:     */   {
/*  779: 584 */     if (val == 0)
/*  780:     */     {
/*  781: 585 */       this.regA = 0;this.regD = 0;this.flagO = true;
/*  782: 586 */       this.flagZ = false;this.flagN = false;
/*  783: 587 */       return;
/*  784:     */     }
/*  785: 589 */     if (this.flagM)
/*  786:     */     {
/*  787:     */       int q;
/*  788: 591 */       if (this.flagC)
/*  789:     */       {
/*  790: 592 */         int q = (byte)this.regD << 8 | this.regA;
/*  791: 593 */         val = (byte)val;
/*  792:     */       }
/*  793:     */       else
/*  794:     */       {
/*  795: 595 */         q = this.regD << 8 | this.regA;
/*  796:     */       }
/*  797: 597 */       this.regD = (q % val & 0xFF);
/*  798: 598 */       q /= val;
/*  799: 599 */       this.regA = (q & 0xFF);
/*  800: 600 */       if (this.flagC) {
/*  801: 601 */         this.flagO = ((q > 127) || (q < -128));
/*  802:     */       } else {
/*  803: 603 */         this.flagO = (q > 255);
/*  804:     */       }
/*  805: 605 */       this.flagZ = (this.regA == 0);
/*  806: 606 */       this.flagN = (q < 0);
/*  807:     */     }
/*  808: 608 */     else if (this.flagC)
/*  809:     */     {
/*  810: 609 */       int q = (short)this.regD << 16 | this.regA;
/*  811: 610 */       val = (short)val;
/*  812: 611 */       this.regD = (q % val & 0xFFFF);
/*  813: 612 */       q /= val;
/*  814: 613 */       this.regA = (q & 0xFFFF);
/*  815: 614 */       this.flagO = ((q > 32767) || (q < -32768));
/*  816: 615 */       this.flagZ = (this.regA == 0);
/*  817: 616 */       this.flagN = (q < 0);
/*  818:     */     }
/*  819:     */     else
/*  820:     */     {
/*  821: 618 */       long q = this.regD << 16 | this.regA;
/*  822: 619 */       this.regD = ((int)(q % val & 0xFFFF));
/*  823: 620 */       q /= val;
/*  824: 621 */       this.regA = ((int)(q & 0xFFFF));
/*  825: 622 */       this.flagO = (q > 65535L);
/*  826: 623 */       this.flagZ = (this.regA == 0);
/*  827: 624 */       this.flagN = (q < 0L);
/*  828:     */     }
/*  829:     */   }
/*  830:     */   
/*  831:     */   private void i_and(int val)
/*  832:     */   {
/*  833: 630 */     this.regA &= val;
/*  834: 631 */     upNZ();
/*  835:     */   }
/*  836:     */   
/*  837:     */   private void i_asl(int addr)
/*  838:     */   {
/*  839: 636 */     int i = readM(addr);
/*  840:     */     
/*  841: 638 */     this.flagC = ((i & negM()) > 0);
/*  842: 639 */     i = i << 1 & maskM();upNZ(i);
/*  843: 640 */     writeM(addr, i);
/*  844:     */   }
/*  845:     */   
/*  846:     */   private void i_lsr(int addr)
/*  847:     */   {
/*  848: 645 */     int i = readM(addr);
/*  849: 646 */     this.flagC = ((i & 0x1) > 0);
/*  850: 647 */     i >>>= 1;upNZ(i);
/*  851: 648 */     writeM(addr, i);
/*  852:     */   }
/*  853:     */   
/*  854:     */   private void i_rol(int addr)
/*  855:     */   {
/*  856: 653 */     int i = readM(addr);
/*  857: 654 */     int n = (i << 1 | (this.flagC ? 1 : 0)) & maskM();
/*  858: 655 */     this.flagC = ((i & negM()) > 0);
/*  859: 656 */     upNZ(n);
/*  860: 657 */     writeM(addr, n);
/*  861:     */   }
/*  862:     */   
/*  863:     */   private void i_ror(int addr)
/*  864:     */   {
/*  865: 662 */     int i = readM(addr);
/*  866: 663 */     int n = i >>> 1 | (this.flagC ? negM() : 0);
/*  867: 664 */     this.flagC = ((i & 0x1) > 0);
/*  868: 665 */     upNZ(n);
/*  869: 666 */     writeM(addr, n);
/*  870:     */   }
/*  871:     */   
/*  872:     */   private void i_brc(boolean cond)
/*  873:     */   {
/*  874: 670 */     int n = readB();
/*  875: 671 */     if (cond) {
/*  876: 671 */       this.regPC = (this.regPC + (byte)n & 0xFFFF);
/*  877:     */     }
/*  878:     */   }
/*  879:     */   
/*  880:     */   private void i_bit(int val)
/*  881:     */   {
/*  882: 675 */     if (this.flagM)
/*  883:     */     {
/*  884: 676 */       this.flagO = ((val & 0x40) > 0);
/*  885: 677 */       this.flagN = ((val & 0x80) > 0);
/*  886:     */     }
/*  887:     */     else
/*  888:     */     {
/*  889: 679 */       this.flagO = ((val & 0x4000) > 0);
/*  890: 680 */       this.flagN = ((val & 0x8000) > 0);
/*  891:     */     }
/*  892: 682 */     this.flagZ = ((val & this.regA) > 0);
/*  893:     */   }
/*  894:     */   
/*  895:     */   private void i_trb(int val)
/*  896:     */   {
/*  897: 686 */     this.flagZ = ((val & this.regA) > 0);
/*  898: 687 */     this.regA &= (val ^ 0xFFFFFFFF);
/*  899:     */   }
/*  900:     */   
/*  901:     */   private void i_tsb(int val)
/*  902:     */   {
/*  903: 691 */     this.flagZ = ((val & this.regA) > 0);
/*  904: 692 */     this.regA |= val;
/*  905:     */   }
/*  906:     */   
/*  907:     */   private void i_cmp(int reg, int val)
/*  908:     */   {
/*  909: 696 */     reg -= val;
/*  910: 697 */     this.flagC = (reg >= 0);
/*  911: 698 */     this.flagZ = (reg == 0);
/*  912: 699 */     this.flagN = ((reg & negM()) > 0);
/*  913:     */   }
/*  914:     */   
/*  915:     */   private void i_cmpx(int reg, int val)
/*  916:     */   {
/*  917: 703 */     reg -= val;
/*  918: 704 */     this.flagC = (reg >= 0);
/*  919: 705 */     this.flagZ = (reg == 0);
/*  920: 706 */     this.flagN = ((reg & negX()) > 0);
/*  921:     */   }
/*  922:     */   
/*  923:     */   private void i_dec(int addr)
/*  924:     */   {
/*  925: 710 */     int i = readM(addr);
/*  926: 711 */     i = i - 1 & maskM();
/*  927: 712 */     writeM(addr, i);
/*  928: 713 */     upNZ(i);
/*  929:     */   }
/*  930:     */   
/*  931:     */   private void i_inc(int addr)
/*  932:     */   {
/*  933: 717 */     int i = readM(addr);
/*  934: 718 */     i = i + 1 & maskM();
/*  935: 719 */     writeM(addr, i);
/*  936: 720 */     upNZ(i);
/*  937:     */   }
/*  938:     */   
/*  939:     */   private void i_eor(int val)
/*  940:     */   {
/*  941: 723 */     this.regA ^= val;upNZ();
/*  942:     */   }
/*  943:     */   
/*  944:     */   private void i_or(int val)
/*  945:     */   {
/*  946: 724 */     this.regA |= val;upNZ();
/*  947:     */   }
/*  948:     */   
/*  949:     */   private void i_mmu(int mmu)
/*  950:     */   {
/*  951: 729 */     switch (mmu)
/*  952:     */     {
/*  953:     */     case 0: 
/*  954: 731 */       int t = this.regA & 0xFF;
/*  955: 732 */       if (this.mmuRBA != t)
/*  956:     */       {
/*  957: 733 */         if (this.rbCache != null) {
/*  958: 734 */           this.rbTimeout = true;
/*  959:     */         }
/*  960: 735 */         this.mmuRBA = t;
/*  961:     */       }
/*  962: 736 */       break;
/*  963:     */     case 128: 
/*  964: 738 */       this.regA = this.mmuRBA;
/*  965: 739 */       break;
/*  966:     */     case 1: 
/*  967: 742 */       this.mmuRBB = this.regA;
/*  968: 743 */       break;
/*  969:     */     case 129: 
/*  970: 745 */       this.regA = this.mmuRBB;
/*  971: 746 */       if (this.flagM)
/*  972:     */       {
/*  973: 746 */         this.regB = (this.regA >> 8);this.regA &= 0xFF;
/*  974:     */       }
/*  975:     */       break;
/*  976:     */     case 2: 
/*  977: 749 */       this.mmuEnRB = true;
/*  978: 750 */       break;
/*  979:     */     case 130: 
/*  980: 752 */       this.mmuEnRB = false;
/*  981: 753 */       break;
/*  982:     */     case 3: 
/*  983: 755 */       this.mmuRBW = this.regA;
/*  984: 756 */       break;
/*  985:     */     case 131: 
/*  986: 758 */       this.regA = this.mmuRBW;
/*  987: 759 */       if (this.flagM)
/*  988:     */       {
/*  989: 759 */         this.regB = (this.regA >> 8);this.regA &= 0xFF;
/*  990:     */       }
/*  991:     */       break;
/*  992:     */     case 4: 
/*  993: 762 */       this.mmuEnRBW = true;
/*  994: 763 */       break;
/*  995:     */     case 132: 
/*  996: 765 */       this.mmuEnRBW = false;
/*  997: 766 */       break;
/*  998:     */     case 5: 
/*  999: 769 */       this.addrBRK = this.regA;
/* 1000: 770 */       break;
/* 1001:     */     case 133: 
/* 1002: 772 */       this.regA = this.addrBRK;
/* 1003: 773 */       if (this.flagM)
/* 1004:     */       {
/* 1005: 773 */         this.regB = (this.regA >> 8);this.regA &= 0xFF;
/* 1006:     */       }
/* 1007:     */       break;
/* 1008:     */     case 6: 
/* 1009: 776 */       this.addrPOR = this.regA;
/* 1010: 777 */       break;
/* 1011:     */     case 134: 
/* 1012: 779 */       this.regA = this.addrPOR;
/* 1013: 780 */       if (this.flagM)
/* 1014:     */       {
/* 1015: 780 */         this.regB = (this.regA >> 8);this.regA &= 0xFF;
/* 1016:     */       }
/* 1017:     */       break;
/* 1018:     */     case 135: 
/* 1019: 784 */       this.regA = (this.rtcTicks & 0xFFFF);
/* 1020: 785 */       this.regD = (this.rtcTicks >> 16 & 0xFFFF);
/* 1021:     */     }
/* 1022:     */   }
/* 1023:     */   
/* 1024:     */   public void executeInsn()
/* 1025:     */   {
/* 1026: 797 */     int insn = readMem(this.regPC);
/* 1027:     */     
/* 1028:     */ 
/* 1029: 800 */     incPC();
/* 1030:     */     int n;
/* 1031: 802 */     switch (insn)
/* 1032:     */     {
/* 1033:     */     case 105: 
/* 1034: 808 */       i_adc(readM()); break;
/* 1035:     */     case 101: 
/* 1036: 809 */       i_adc(readM(readB())); break;
/* 1037:     */     case 117: 
/* 1038: 810 */       i_adc(readM(readBX())); break;
/* 1039:     */     case 109: 
/* 1040: 811 */       i_adc(readM(readW())); break;
/* 1041:     */     case 125: 
/* 1042: 812 */       i_adc(readM(readWX())); break;
/* 1043:     */     case 121: 
/* 1044: 813 */       i_adc(readM(readWY())); break;
/* 1045:     */     case 97: 
/* 1046: 814 */       i_adc(readM(readBXW())); break;
/* 1047:     */     case 113: 
/* 1048: 815 */       i_adc(readM(readBWY())); break;
/* 1049:     */     case 114: 
/* 1050: 816 */       i_adc(readM(readBW())); break;
/* 1051:     */     case 99: 
/* 1052: 817 */       i_adc(readM(readBS())); break;
/* 1053:     */     case 115: 
/* 1054: 818 */       i_adc(readM(readBSWY())); break;
/* 1055:     */     case 103: 
/* 1056: 819 */       i_adc(readM(readBR())); break;
/* 1057:     */     case 119: 
/* 1058: 820 */       i_adc(readM(readBRWY())); break;
/* 1059:     */     case 41: 
/* 1060: 823 */       i_and(readM()); break;
/* 1061:     */     case 37: 
/* 1062: 824 */       i_and(readM(readB())); break;
/* 1063:     */     case 53: 
/* 1064: 825 */       i_and(readM(readBX())); break;
/* 1065:     */     case 45: 
/* 1066: 826 */       i_and(readM(readW())); break;
/* 1067:     */     case 61: 
/* 1068: 827 */       i_and(readM(readWX())); break;
/* 1069:     */     case 57: 
/* 1070: 828 */       i_and(readM(readWY())); break;
/* 1071:     */     case 33: 
/* 1072: 829 */       i_and(readM(readBXW())); break;
/* 1073:     */     case 49: 
/* 1074: 830 */       i_and(readM(readBWY())); break;
/* 1075:     */     case 50: 
/* 1076: 831 */       i_and(readM(readBW())); break;
/* 1077:     */     case 35: 
/* 1078: 832 */       i_and(readM(readBS())); break;
/* 1079:     */     case 51: 
/* 1080: 833 */       i_and(readM(readBSWY())); break;
/* 1081:     */     case 39: 
/* 1082: 834 */       i_and(readM(readBR())); break;
/* 1083:     */     case 55: 
/* 1084: 835 */       i_and(readM(readBRWY())); break;
/* 1085:     */     case 10: 
/* 1086: 839 */       this.flagC = ((this.regA & negM()) > 0);
/* 1087: 840 */       this.regA = (this.regA << 1 & maskM());upNZ();
/* 1088: 841 */       break;
/* 1089:     */     case 6: 
/* 1090: 842 */       i_asl(readB()); break;
/* 1091:     */     case 22: 
/* 1092: 843 */       i_asl(readBX()); break;
/* 1093:     */     case 14: 
/* 1094: 844 */       i_asl(readW()); break;
/* 1095:     */     case 30: 
/* 1096: 845 */       i_asl(readWX()); break;
/* 1097:     */     case 144: 
/* 1098: 847 */       i_brc(!this.flagC); break;
/* 1099:     */     case 176: 
/* 1100: 848 */       i_brc(this.flagC); break;
/* 1101:     */     case 240: 
/* 1102: 849 */       i_brc(this.flagZ); break;
/* 1103:     */     case 48: 
/* 1104: 850 */       i_brc(this.flagN); break;
/* 1105:     */     case 208: 
/* 1106: 851 */       i_brc(!this.flagZ); break;
/* 1107:     */     case 16: 
/* 1108: 852 */       i_brc(!this.flagN); break;
/* 1109:     */     case 80: 
/* 1110: 853 */       i_brc(!this.flagO); break;
/* 1111:     */     case 112: 
/* 1112: 854 */       i_brc(this.flagO); break;
/* 1113:     */     case 128: 
/* 1114: 855 */       i_brc(true); break;
/* 1115:     */     case 137: 
/* 1116: 858 */       this.flagZ = ((readM() & this.regA) == 0); break;
/* 1117:     */     case 36: 
/* 1118: 859 */       i_bit(readM(readB())); break;
/* 1119:     */     case 52: 
/* 1120: 860 */       i_bit(readM(readBX())); break;
/* 1121:     */     case 44: 
/* 1122: 861 */       i_bit(readM(readW())); break;
/* 1123:     */     case 60: 
/* 1124: 862 */       i_bit(readM(readWX())); break;
/* 1125:     */     case 0: 
/* 1126: 866 */       push2(this.regPC);
/* 1127: 867 */       push1(getFlags());
/* 1128: 868 */       this.flagBRK = true;
/* 1129: 869 */       this.regPC = this.addrBRK;
/* 1130: 870 */       break;
/* 1131:     */     case 24: 
/* 1132: 872 */       this.flagC = false; break;
/* 1133:     */     case 216: 
/* 1134: 873 */       this.flagD = false; break;
/* 1135:     */     case 88: 
/* 1136: 874 */       this.flagID = false; break;
/* 1137:     */     case 184: 
/* 1138: 875 */       this.flagO = false; break;
/* 1139:     */     case 201: 
/* 1140: 878 */       i_cmp(this.regA, readM()); break;
/* 1141:     */     case 197: 
/* 1142: 879 */       i_cmp(this.regA, readM(readB())); break;
/* 1143:     */     case 213: 
/* 1144: 880 */       i_cmp(this.regA, readM(readBX())); break;
/* 1145:     */     case 205: 
/* 1146: 881 */       i_cmp(this.regA, readM(readW())); break;
/* 1147:     */     case 221: 
/* 1148: 882 */       i_cmp(this.regA, readM(readWX())); break;
/* 1149:     */     case 217: 
/* 1150: 883 */       i_cmp(this.regA, readM(readWY())); break;
/* 1151:     */     case 193: 
/* 1152: 884 */       i_cmp(this.regA, readM(readBXW())); break;
/* 1153:     */     case 209: 
/* 1154: 885 */       i_cmp(this.regA, readM(readBWY())); break;
/* 1155:     */     case 210: 
/* 1156: 886 */       i_cmp(this.regA, readM(readBW())); break;
/* 1157:     */     case 195: 
/* 1158: 887 */       i_cmp(this.regA, readM(readBS())); break;
/* 1159:     */     case 211: 
/* 1160: 888 */       i_cmp(this.regA, readM(readBSWY())); break;
/* 1161:     */     case 199: 
/* 1162: 889 */       i_cmp(this.regA, readM(readBR())); break;
/* 1163:     */     case 215: 
/* 1164: 890 */       i_cmp(this.regA, readM(readBRWY())); break;
/* 1165:     */     case 224: 
/* 1166: 893 */       i_cmpx(this.regX, readX()); break;
/* 1167:     */     case 228: 
/* 1168: 894 */       i_cmpx(this.regX, readX(readB())); break;
/* 1169:     */     case 236: 
/* 1170: 895 */       i_cmpx(this.regX, readX(readW())); break;
/* 1171:     */     case 192: 
/* 1172: 898 */       i_cmpx(this.regY, readX()); break;
/* 1173:     */     case 196: 
/* 1174: 899 */       i_cmpx(this.regY, readX(readB())); break;
/* 1175:     */     case 204: 
/* 1176: 900 */       i_cmpx(this.regY, readX(readW())); break;
/* 1177:     */     case 58: 
/* 1178: 903 */       this.regA = (this.regA - 1 & maskM());upNZ(this.regA); break;
/* 1179:     */     case 198: 
/* 1180: 904 */       i_dec(readB()); break;
/* 1181:     */     case 214: 
/* 1182: 905 */       i_dec(readBX()); break;
/* 1183:     */     case 206: 
/* 1184: 906 */       i_dec(readW()); break;
/* 1185:     */     case 222: 
/* 1186: 907 */       i_dec(readWX()); break;
/* 1187:     */     case 202: 
/* 1188: 910 */       this.regX = (this.regX - 1 & maskX());upNZ(this.regX); break;
/* 1189:     */     case 136: 
/* 1190: 913 */       this.regY = (this.regY - 1 & maskX());upNZ(this.regY); break;
/* 1191:     */     case 73: 
/* 1192: 916 */       i_eor(readM()); break;
/* 1193:     */     case 69: 
/* 1194: 917 */       i_eor(readM(readB())); break;
/* 1195:     */     case 85: 
/* 1196: 918 */       i_eor(readM(readBX())); break;
/* 1197:     */     case 77: 
/* 1198: 919 */       i_eor(readM(readW())); break;
/* 1199:     */     case 93: 
/* 1200: 920 */       i_eor(readM(readWX())); break;
/* 1201:     */     case 89: 
/* 1202: 921 */       i_eor(readM(readWY())); break;
/* 1203:     */     case 65: 
/* 1204: 922 */       i_eor(readM(readBXW())); break;
/* 1205:     */     case 81: 
/* 1206: 923 */       i_eor(readM(readBWY())); break;
/* 1207:     */     case 82: 
/* 1208: 924 */       i_eor(readM(readBW())); break;
/* 1209:     */     case 67: 
/* 1210: 925 */       i_eor(readM(readBS())); break;
/* 1211:     */     case 83: 
/* 1212: 926 */       i_eor(readM(readBSWY())); break;
/* 1213:     */     case 71: 
/* 1214: 927 */       i_eor(readM(readBR())); break;
/* 1215:     */     case 87: 
/* 1216: 928 */       i_eor(readM(readBRWY())); break;
/* 1217:     */     case 26: 
/* 1218: 931 */       this.regA = (this.regA + 1 & maskM());upNZ(this.regA); break;
/* 1219:     */     case 230: 
/* 1220: 932 */       i_inc(readB()); break;
/* 1221:     */     case 246: 
/* 1222: 933 */       i_inc(readBX()); break;
/* 1223:     */     case 238: 
/* 1224: 934 */       i_inc(readW()); break;
/* 1225:     */     case 254: 
/* 1226: 935 */       i_inc(readWX()); break;
/* 1227:     */     case 232: 
/* 1228: 938 */       this.regX = (this.regX + 1 & maskX());upNZ(this.regX); break;
/* 1229:     */     case 200: 
/* 1230: 941 */       this.regY = (this.regY + 1 & maskX());upNZ(this.regY); break;
/* 1231:     */     case 76: 
/* 1232: 944 */       this.regPC = readW(); break;
/* 1233:     */     case 108: 
/* 1234: 945 */       this.regPC = readWW(); break;
/* 1235:     */     case 124: 
/* 1236: 946 */       this.regPC = readWXW(); break;
/* 1237:     */     case 32: 
/* 1238: 948 */       push2(this.regPC + 1);this.regPC = readW(); break;
/* 1239:     */     case 252: 
/* 1240: 949 */       push2(this.regPC + 1);this.regPC = readWXW(); break;
/* 1241:     */     case 169: 
/* 1242: 952 */       this.regA = readM();upNZ(); break;
/* 1243:     */     case 165: 
/* 1244: 953 */       this.regA = readM(readB());upNZ(); break;
/* 1245:     */     case 181: 
/* 1246: 954 */       this.regA = readM(readBX());upNZ(); break;
/* 1247:     */     case 173: 
/* 1248: 955 */       this.regA = readM(readW());upNZ(); break;
/* 1249:     */     case 189: 
/* 1250: 956 */       this.regA = readM(readWX());upNZ(); break;
/* 1251:     */     case 185: 
/* 1252: 957 */       this.regA = readM(readWY());upNZ(); break;
/* 1253:     */     case 161: 
/* 1254: 958 */       this.regA = readM(readBXW());upNZ(); break;
/* 1255:     */     case 177: 
/* 1256: 959 */       this.regA = readM(readBWY());upNZ(); break;
/* 1257:     */     case 178: 
/* 1258: 960 */       this.regA = readM(readBW());upNZ(); break;
/* 1259:     */     case 163: 
/* 1260: 961 */       this.regA = readM(readBS());upNZ(); break;
/* 1261:     */     case 179: 
/* 1262: 962 */       this.regA = readM(readBSWY());upNZ(); break;
/* 1263:     */     case 167: 
/* 1264: 963 */       this.regA = readM(readBR());upNZ(); break;
/* 1265:     */     case 183: 
/* 1266: 964 */       this.regA = readM(readBRWY());upNZ(); break;
/* 1267:     */     case 162: 
/* 1268: 967 */       this.regX = readX();upNZ(this.regX); break;
/* 1269:     */     case 166: 
/* 1270: 968 */       this.regX = readX(readB());upNZ(this.regX); break;
/* 1271:     */     case 182: 
/* 1272: 969 */       this.regX = readX(readBY());upNZ(this.regX); break;
/* 1273:     */     case 174: 
/* 1274: 970 */       this.regX = readX(readW());upNZ(this.regX); break;
/* 1275:     */     case 190: 
/* 1276: 971 */       this.regX = readX(readWY());upNZ(this.regX); break;
/* 1277:     */     case 160: 
/* 1278: 974 */       this.regY = readX();upNZ(this.regY); break;
/* 1279:     */     case 164: 
/* 1280: 975 */       this.regY = readX(readB());upNZ(this.regY); break;
/* 1281:     */     case 180: 
/* 1282: 976 */       this.regY = readX(readBX());upNZ(this.regY); break;
/* 1283:     */     case 172: 
/* 1284: 977 */       this.regY = readX(readW());upNZ(this.regY); break;
/* 1285:     */     case 188: 
/* 1286: 978 */       this.regY = readX(readWX());upNZ(this.regY); break;
/* 1287:     */     case 74: 
/* 1288: 982 */       this.flagC = ((this.regA & 0x1) > 0);
/* 1289: 983 */       this.regA >>>= 1;upNZ();
/* 1290: 984 */       break;
/* 1291:     */     case 70: 
/* 1292: 985 */       i_lsr(readB()); break;
/* 1293:     */     case 86: 
/* 1294: 986 */       i_lsr(readBX()); break;
/* 1295:     */     case 78: 
/* 1296: 987 */       i_lsr(readW()); break;
/* 1297:     */     case 94: 
/* 1298: 988 */       i_lsr(readWX()); break;
/* 1299:     */     case 234: 
/* 1300:     */       break;
/* 1301:     */     case 9: 
/* 1302: 994 */       i_or(readM()); break;
/* 1303:     */     case 5: 
/* 1304: 995 */       i_or(readM(readB())); break;
/* 1305:     */     case 21: 
/* 1306: 996 */       i_or(readM(readBX())); break;
/* 1307:     */     case 13: 
/* 1308: 997 */       i_or(readM(readW())); break;
/* 1309:     */     case 29: 
/* 1310: 998 */       i_or(readM(readWX())); break;
/* 1311:     */     case 25: 
/* 1312: 999 */       i_or(readM(readWY())); break;
/* 1313:     */     case 1: 
/* 1314:1000 */       i_or(readM(readBXW())); break;
/* 1315:     */     case 17: 
/* 1316:1001 */       i_or(readM(readBWY())); break;
/* 1317:     */     case 18: 
/* 1318:1002 */       i_or(readM(readBW())); break;
/* 1319:     */     case 3: 
/* 1320:1003 */       i_or(readM(readBS())); break;
/* 1321:     */     case 19: 
/* 1322:1004 */       i_or(readM(readBSWY())); break;
/* 1323:     */     case 7: 
/* 1324:1005 */       i_or(readM(readBR())); break;
/* 1325:     */     case 23: 
/* 1326:1006 */       i_or(readM(readBRWY())); break;
/* 1327:     */     case 72: 
/* 1328:1008 */       pushM(this.regA); break;
/* 1329:     */     case 8: 
/* 1330:1009 */       push1(getFlags()); break;
/* 1331:     */     case 218: 
/* 1332:1010 */       pushX(this.regX); break;
/* 1333:     */     case 90: 
/* 1334:1011 */       pushX(this.regY); break;
/* 1335:     */     case 104: 
/* 1336:1013 */       this.regA = popM();upNZ(); break;
/* 1337:     */     case 40: 
/* 1338:1014 */       setFlags(pop1()); break;
/* 1339:     */     case 250: 
/* 1340:1015 */       this.regX = popX();upNZX(this.regX); break;
/* 1341:     */     case 122: 
/* 1342:1016 */       this.regY = popX();upNZX(this.regY); break;
/* 1343:     */     case 42: 
/* 1344:1020 */       n = (this.regA << 1 | (this.flagC ? 1 : 0)) & maskM();
/* 1345:1021 */       this.flagC = ((this.regA & negM()) > 0);
/* 1346:1022 */       this.regA = n;upNZ();
/* 1347:1023 */       break;
/* 1348:     */     case 38: 
/* 1349:1024 */       i_rol(readB()); break;
/* 1350:     */     case 54: 
/* 1351:1025 */       i_rol(readBX()); break;
/* 1352:     */     case 46: 
/* 1353:1026 */       i_rol(readW()); break;
/* 1354:     */     case 62: 
/* 1355:1027 */       i_rol(readWX()); break;
/* 1356:     */     case 106: 
/* 1357:1031 */       n = this.regA >>> 1 | (this.flagC ? negM() : 0);
/* 1358:1032 */       this.flagC = ((this.regA & 0x1) > 0);
/* 1359:1033 */       this.regA = n;upNZ();
/* 1360:1034 */       break;
/* 1361:     */     case 102: 
/* 1362:1035 */       i_ror(readB()); break;
/* 1363:     */     case 118: 
/* 1364:1036 */       i_ror(readBX()); break;
/* 1365:     */     case 110: 
/* 1366:1037 */       i_ror(readW()); break;
/* 1367:     */     case 126: 
/* 1368:1038 */       i_ror(readWX()); break;
/* 1369:     */     case 64: 
/* 1370:1041 */       setFlags(pop1());
/* 1371:1042 */       this.regPC = pop2();
/* 1372:1043 */       break;
/* 1373:     */     case 96: 
/* 1374:1046 */       this.regPC = (pop2() + 1); break;
/* 1375:     */     case 233: 
/* 1376:1049 */       i_sbc(readM()); break;
/* 1377:     */     case 229: 
/* 1378:1050 */       i_sbc(readM(readB())); break;
/* 1379:     */     case 245: 
/* 1380:1051 */       i_sbc(readM(readBX())); break;
/* 1381:     */     case 237: 
/* 1382:1052 */       i_sbc(readM(readW())); break;
/* 1383:     */     case 253: 
/* 1384:1053 */       i_sbc(readM(readWX())); break;
/* 1385:     */     case 249: 
/* 1386:1054 */       i_sbc(readM(readWY())); break;
/* 1387:     */     case 225: 
/* 1388:1055 */       i_sbc(readM(readBXW())); break;
/* 1389:     */     case 241: 
/* 1390:1056 */       i_sbc(readM(readBWY())); break;
/* 1391:     */     case 242: 
/* 1392:1057 */       i_sbc(readM(readBW())); break;
/* 1393:     */     case 227: 
/* 1394:1058 */       i_sbc(readM(readBS())); break;
/* 1395:     */     case 243: 
/* 1396:1059 */       i_sbc(readM(readBSWY())); break;
/* 1397:     */     case 231: 
/* 1398:1060 */       i_sbc(readM(readBR())); break;
/* 1399:     */     case 247: 
/* 1400:1061 */       i_sbc(readM(readBRWY())); break;
/* 1401:     */     case 56: 
/* 1402:1064 */       this.flagC = true; break;
/* 1403:     */     case 248: 
/* 1404:1065 */       this.flagD = true; break;
/* 1405:     */     case 120: 
/* 1406:1066 */       this.flagID = true; break;
/* 1407:     */     case 133: 
/* 1408:1069 */       writeM(readB(), this.regA); break;
/* 1409:     */     case 149: 
/* 1410:1070 */       writeM(readBX(), this.regA); break;
/* 1411:     */     case 141: 
/* 1412:1071 */       writeM(readW(), this.regA); break;
/* 1413:     */     case 157: 
/* 1414:1072 */       writeM(readWX(), this.regA); break;
/* 1415:     */     case 153: 
/* 1416:1073 */       writeM(readWY(), this.regA); break;
/* 1417:     */     case 129: 
/* 1418:1074 */       writeM(readBXW(), this.regA); break;
/* 1419:     */     case 145: 
/* 1420:1075 */       writeM(readBWY(), this.regA); break;
/* 1421:     */     case 146: 
/* 1422:1076 */       writeM(readBW(), this.regA); break;
/* 1423:     */     case 131: 
/* 1424:1077 */       writeM(readBS(), this.regA); break;
/* 1425:     */     case 147: 
/* 1426:1078 */       writeM(readBSWY(), this.regA); break;
/* 1427:     */     case 135: 
/* 1428:1079 */       writeM(readBR(), this.regA); break;
/* 1429:     */     case 151: 
/* 1430:1080 */       writeM(readBRWY(), this.regA); break;
/* 1431:     */     case 134: 
/* 1432:1083 */       writeX(readB(), this.regX); break;
/* 1433:     */     case 150: 
/* 1434:1084 */       writeX(readBY(), this.regX); break;
/* 1435:     */     case 142: 
/* 1436:1085 */       writeX(readW(), this.regX); break;
/* 1437:     */     case 132: 
/* 1438:1088 */       writeX(readB(), this.regY); break;
/* 1439:     */     case 148: 
/* 1440:1089 */       writeX(readBX(), this.regY); break;
/* 1441:     */     case 140: 
/* 1442:1090 */       writeX(readW(), this.regY); break;
/* 1443:     */     case 170: 
/* 1444:1092 */       this.regX = this.regA;
/* 1445:1093 */       if (this.flagX) {
/* 1446:1093 */         this.regX &= 0xFF;
/* 1447:     */       }
/* 1448:1094 */       upNZX(this.regX); break;
/* 1449:     */     case 168: 
/* 1450:1095 */       this.regY = this.regA;
/* 1451:1096 */       if (this.flagX) {
/* 1452:1096 */         this.regY &= 0xFF;
/* 1453:     */       }
/* 1454:1097 */       upNZX(this.regY); break;
/* 1455:     */     case 186: 
/* 1456:1098 */       this.regX = this.regSP;
/* 1457:1099 */       if (this.flagX) {
/* 1458:1099 */         this.regX &= 0xFF;
/* 1459:     */       }
/* 1460:1100 */       upNZX(this.regX); break;
/* 1461:     */     case 138: 
/* 1462:1101 */       this.regA = this.regX;
/* 1463:1102 */       if (this.flagM) {
/* 1464:1102 */         this.regA &= 0xFF;
/* 1465:     */       }
/* 1466:1103 */       upNZ(); break;
/* 1467:     */     case 154: 
/* 1468:1106 */       if (this.flagX) {
/* 1469:1106 */         this.regSP = (this.regSP & 0xFF00 | this.regX & 0xFF);
/* 1470:     */       } else {
/* 1471:1107 */         this.regSP = this.regX;
/* 1472:     */       }
/* 1473:1108 */       upNZX(this.regX);
/* 1474:1109 */       break;
/* 1475:     */     case 152: 
/* 1476:1110 */       this.regA = this.regY;
/* 1477:1111 */       if (this.flagM) {
/* 1478:1111 */         this.regA &= 0xFF;
/* 1479:     */       }
/* 1480:1112 */       upNZX(this.regY); break;
/* 1481:     */     case 100: 
/* 1482:1118 */       writeM(readB(), 0); break;
/* 1483:     */     case 116: 
/* 1484:1119 */       writeM(readBX(), 0); break;
/* 1485:     */     case 156: 
/* 1486:1120 */       writeM(readW(), 0); break;
/* 1487:     */     case 158: 
/* 1488:1121 */       writeM(readWX(), 0); break;
/* 1489:     */     case 20: 
/* 1490:1124 */       i_trb(readM(readB())); break;
/* 1491:     */     case 28: 
/* 1492:1125 */       i_trb(readM(readW())); break;
/* 1493:     */     case 4: 
/* 1494:1128 */       i_tsb(readM(readB())); break;
/* 1495:     */     case 12: 
/* 1496:1129 */       i_tsb(readM(readW())); break;
/* 1497:     */     case 219: 
/* 1498:1133 */       this.sliceCycles = -1;
/* 1499:1134 */       if (this.k.c(this.l, this.m + 1, this.n))
/* 1500:     */       {
/* 1501:1135 */         this.k.a(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D, "fire.ignite", 1.0F, this.k.t.nextFloat() * 0.4F + 0.8F);
/* 1502:     */         
/* 1503:     */ 
/* 1504:     */ 
/* 1505:1139 */         this.k.e(this.l, this.m + 1, this.n, amq.au.cm);
/* 1506:     */       }
/* 1507:     */       break;
/* 1508:     */     case 203: 
/* 1509:1146 */       this.waiTimeout = true;
/* 1510:1147 */       break;
/* 1511:     */     case 155: 
/* 1512:1152 */       this.regY = this.regX;upNZX(this.regY); break;
/* 1513:     */     case 187: 
/* 1514:1153 */       this.regX = this.regY;upNZX(this.regX); break;
/* 1515:     */     case 244: 
/* 1516:1155 */       push2(readW()); break;
/* 1517:     */     case 212: 
/* 1518:1156 */       push2(readBW()); break;
/* 1519:     */     case 98: 
/* 1520:1157 */       n = readB();push2(this.regPC + n); break;
/* 1521:     */     case 235: 
/* 1522:1161 */       if (this.flagM)
/* 1523:     */       {
/* 1524:1162 */         n = this.regA;this.regA = this.regB;this.regB = n;
/* 1525:     */       }
/* 1526:     */       else
/* 1527:     */       {
/* 1528:1164 */         this.regA = (this.regA >> 8 & 0xFF | this.regA << 8 & 0xFF00);
/* 1529:     */       }
/* 1530:1166 */       break;
/* 1531:     */     case 251: 
/* 1532:1170 */       if (this.flagE != this.flagC) {
/* 1533:1171 */         if (this.flagE)
/* 1534:     */         {
/* 1535:1172 */           this.flagE = false;this.flagC = true;
/* 1536:     */         }
/* 1537:     */         else
/* 1538:     */         {
/* 1539:1174 */           this.flagE = true;this.flagC = false;
/* 1540:1175 */           if (!this.flagM) {
/* 1541:1175 */             this.regB = (this.regA >> 8);
/* 1542:     */           }
/* 1543:1176 */           this.flagM = true;this.flagX = true;
/* 1544:1177 */           this.regA &= 0xFF;this.regX &= 0xFF;this.regY &= 0xFF;
/* 1545:     */         }
/* 1546:     */       }
/* 1547:1179 */       break;
/* 1548:     */     case 194: 
/* 1549:1182 */       setFlags(getFlags() & (readB() ^ 0xFFFFFFFF)); break;
/* 1550:     */     case 226: 
/* 1551:1185 */       setFlags(getFlags() | readB()); break;
/* 1552:     */     case 139: 
/* 1553:1190 */       if (this.flagX) {
/* 1554:1190 */         this.regSP = (this.regR & 0xFF00 | this.regX & 0xFF);
/* 1555:     */       } else {
/* 1556:1191 */         this.regR = this.regX;
/* 1557:     */       }
/* 1558:1192 */       upNZX(this.regR);
/* 1559:1193 */       break;
/* 1560:     */     case 171: 
/* 1561:1195 */       this.regX = this.regR;
/* 1562:1196 */       if (this.flagX) {
/* 1563:1196 */         this.regX &= 0xFF;
/* 1564:     */       }
/* 1565:1197 */       upNZX(this.regX);
/* 1566:1198 */       break;
/* 1567:     */     case 68: 
/* 1568:1200 */       push2r(readW()); break;
/* 1569:     */     case 84: 
/* 1570:1201 */       push2r(readBW()); break;
/* 1571:     */     case 130: 
/* 1572:1202 */       int n = readB();push2r(this.regPC + n); break;
/* 1573:     */     case 59: 
/* 1574:1204 */       this.regX = popXr();upNZX(this.regX); break;
/* 1575:     */     case 107: 
/* 1576:1205 */       this.regA = popMr();upNZ(this.regA); break;
/* 1577:     */     case 123: 
/* 1578:1206 */       this.regY = popXr();upNZX(this.regY); break;
/* 1579:     */     case 27: 
/* 1580:1208 */       pushXr(this.regX); break;
/* 1581:     */     case 75: 
/* 1582:1209 */       pushMr(this.regA); break;
/* 1583:     */     case 91: 
/* 1584:1210 */       pushXr(this.regY); break;
/* 1585:     */     case 11: 
/* 1586:1212 */       push2r(this.regI); break;
/* 1587:     */     case 43: 
/* 1588:1213 */       this.regI = pop2r();upNZX(this.regI); break;
/* 1589:     */     case 92: 
/* 1590:1215 */       this.regI = this.regX;upNZX(this.regX); break;
/* 1591:     */     case 220: 
/* 1592:1217 */       this.regX = this.regI;
/* 1593:1218 */       if (this.flagX) {
/* 1594:1218 */         this.regX &= 0xFF;
/* 1595:     */       }
/* 1596:1219 */       upNZX(this.regX);
/* 1597:1220 */       break;
/* 1598:     */     case 2: 
/* 1599:1222 */       this.regPC = readW(this.regI);this.regI += 2;
/* 1600:1223 */       break;
/* 1601:     */     case 66: 
/* 1602:1225 */       if (this.flagM)
/* 1603:     */       {
/* 1604:1226 */         this.regA = readMem(this.regI);this.regI += 1;
/* 1605:     */       }
/* 1606:     */       else
/* 1607:     */       {
/* 1608:1228 */         this.regA = readW(this.regI);this.regI += 2;
/* 1609:     */       }
/* 1610:1230 */       break;
/* 1611:     */     case 34: 
/* 1612:1232 */       push2r(this.regI);this.regI = (this.regPC + 2);
/* 1613:1233 */       this.regPC = readW(this.regPC);
/* 1614:1234 */       break;
/* 1615:     */     case 15: 
/* 1616:1237 */       i_mul(readM(readB())); break;
/* 1617:     */     case 31: 
/* 1618:1238 */       i_mul(readM(readBX())); break;
/* 1619:     */     case 47: 
/* 1620:1239 */       i_mul(readM(readW())); break;
/* 1621:     */     case 63: 
/* 1622:1240 */       i_mul(readM(readWX())); break;
/* 1623:     */     case 79: 
/* 1624:1242 */       i_div(readM(readB())); break;
/* 1625:     */     case 95: 
/* 1626:1243 */       i_div(readM(readBX())); break;
/* 1627:     */     case 111: 
/* 1628:1244 */       i_div(readM(readW())); break;
/* 1629:     */     case 127: 
/* 1630:1245 */       i_div(readM(readWX())); break;
/* 1631:     */     case 143: 
/* 1632:1248 */       this.regD = 0;this.regB = 0;
/* 1633:1249 */       break;
/* 1634:     */     case 159: 
/* 1635:1251 */       this.regD = ((this.regA & negM()) > 0 ? 65535 : 0);
/* 1636:1252 */       this.regB = (this.regD & 0xFF);
/* 1637:1253 */       break;
/* 1638:     */     case 175: 
/* 1639:1255 */       this.regA = this.regD;
/* 1640:1256 */       if (this.flagM) {
/* 1641:1256 */         this.regA &= 0xFF;
/* 1642:     */       }
/* 1643:1257 */       upNZ(this.regA);
/* 1644:1258 */       break;
/* 1645:     */     case 191: 
/* 1646:1260 */       if (this.flagM) {
/* 1647:1261 */         this.regD = (this.regA | this.regB << 8);
/* 1648:     */       } else {
/* 1649:1263 */         this.regD = this.regA;
/* 1650:     */       }
/* 1651:1265 */       upNZ(this.regA);
/* 1652:1266 */       break;
/* 1653:     */     case 207: 
/* 1654:1267 */       this.regD = popM(); break;
/* 1655:     */     case 223: 
/* 1656:1268 */       pushM(this.regD); break;
/* 1657:     */     case 239: 
/* 1658:1271 */       i_mmu(readB());
/* 1659:1272 */       break;
/* 1660:     */     }
/* 1661:     */   }
/* 1662:     */   
/* 1663:     */   public byte[] getFramePacket()
/* 1664:     */   {
/* 1665:1284 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 1666:1285 */     pkt.subId = 7;
/* 1667:1286 */     writeToPacket(pkt);
/* 1668:1287 */     pkt.headout.write(pkt.subId);
/* 1669:1288 */     return pkt.toByteArray();
/* 1670:     */   }
/* 1671:     */   
/* 1672:     */   public void handleFramePacket(byte[] ba)
/* 1673:     */     throws IOException
/* 1674:     */   {
/* 1675:1292 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 1676:1293 */     pkt.subId = pkt.getByte();
/* 1677:1294 */     readFromPacket(pkt);
/* 1678:     */   }
/* 1679:     */   
/* 1680:     */   public void onFrameRefresh(ym iba) {}
/* 1681:     */   
/* 1682:     */   public void onFramePickup(ym iba) {}
/* 1683:     */   
/* 1684:     */   public void onFrameDrop() {}
/* 1685:     */   
/* 1686:     */   public void a(bq tag)
/* 1687:     */   {
/* 1688:1304 */     super.a(tag);
/* 1689:     */     
/* 1690:1306 */     this.memory = tag.j("ram");
/* 1691:1307 */     if (this.memory.length != 8192) {
/* 1692:1308 */       this.memory = new byte[8192];
/* 1693:     */     }
/* 1694:1309 */     this.Rotation = tag.c("rot");
/* 1695:1310 */     this.addrPOR = (tag.d("por") & 0xFFFF);
/* 1696:1311 */     this.addrBRK = (tag.d("brk") & 0xFFFF);
/* 1697:     */     
/* 1698:1313 */     int efl = tag.c("efl");
/* 1699:1314 */     this.flagE = ((efl & 0x1) > 0);
/* 1700:1315 */     this.mmuEnRB = ((efl & 0x2) > 0);
/* 1701:1316 */     this.mmuEnRBW = ((efl & 0x4) > 0);
/* 1702:1317 */     setFlags(tag.c("fl"));
/* 1703:     */     
/* 1704:1319 */     this.regSP = (tag.d("rsp") & 0xFFFF);
/* 1705:1320 */     this.regPC = (tag.d("rpc") & 0xFFFF);
/* 1706:1321 */     this.regA = (tag.d("ra") & 0xFFFF);
/* 1707:1322 */     if (this.flagM)
/* 1708:     */     {
/* 1709:1322 */       this.regB = (this.regA >> 8);this.regA &= 0xFF;
/* 1710:     */     }
/* 1711:1323 */     this.regX = (tag.d("rx") & 0xFFFF);
/* 1712:1324 */     this.regY = (tag.d("ry") & 0xFFFF);
/* 1713:1325 */     this.regD = (tag.d("rd") & 0xFFFF);
/* 1714:1326 */     this.regR = (tag.d("rr") & 0xFFFF);
/* 1715:1327 */     this.regI = (tag.d("ri") & 0xFFFF);
/* 1716:     */     
/* 1717:1329 */     this.mmuRBB = (tag.d("mmrb") & 0xFFFF);
/* 1718:1330 */     this.mmuRBW = (tag.d("mmrbw") & 0xFFFF);
/* 1719:1331 */     this.mmuRBA = (tag.c("mmra") & 0xFF);
/* 1720:1332 */     this.sliceCycles = tag.e("cyc");
/* 1721:1333 */     this.rtcTicks = tag.e("rtct");
/* 1722:     */     
/* 1723:1335 */     this.byte0 = (tag.c("b0") & 0xFF);
/* 1724:1336 */     this.byte1 = (tag.c("b1") & 0xFF);
/* 1725:1337 */     this.rbaddr = (tag.c("rbaddr") & 0xFF);
/* 1726:     */   }
/* 1727:     */   
/* 1728:     */   public void b(bq tag)
/* 1729:     */   {
/* 1730:1341 */     super.b(tag);
/* 1731:1342 */     tag.a("rot", (byte)this.Rotation);
/* 1732:1343 */     tag.a("ram", this.memory);
/* 1733:1344 */     tag.a("por", (short)this.addrPOR);
/* 1734:1345 */     tag.a("brk", (short)this.addrBRK);
/* 1735:     */     
/* 1736:1347 */     int efl = (this.flagE ? 1 : 0) | (this.mmuEnRB ? 2 : 0) | (this.mmuEnRBW ? 4 : 0);
/* 1737:1348 */     tag.a("efl", (byte)efl);
/* 1738:1349 */     tag.a("fl", (byte)getFlags());
/* 1739:     */     
/* 1740:1351 */     tag.a("rsp", (short)this.regSP);
/* 1741:1352 */     tag.a("rpc", (short)this.regPC);
/* 1742:1353 */     if (this.flagM) {
/* 1743:1353 */       this.regA = (this.regA & 0xFF | this.regB << 8);
/* 1744:     */     }
/* 1745:1354 */     tag.a("ra", (short)this.regA);
/* 1746:1355 */     if (this.flagM) {
/* 1747:1355 */       this.regA &= 0xFF;
/* 1748:     */     }
/* 1749:1357 */     tag.a("rx", (short)this.regX);
/* 1750:1358 */     tag.a("ry", (short)this.regY);
/* 1751:1359 */     tag.a("rd", (short)this.regD);
/* 1752:1360 */     tag.a("rr", (short)this.regR);
/* 1753:1361 */     tag.a("ri", (short)this.regI);
/* 1754:     */     
/* 1755:1363 */     tag.a("mmrb", (short)this.mmuRBB);
/* 1756:1364 */     tag.a("mmrbw", (short)this.mmuRBW);
/* 1757:1365 */     tag.a("mmra", (byte)this.mmuRBA);
/* 1758:1366 */     tag.a("cyc", this.sliceCycles);
/* 1759:1367 */     tag.a("rtct", this.rtcTicks);
/* 1760:     */     
/* 1761:1369 */     tag.a("b0", (byte)this.byte0);
/* 1762:1370 */     tag.a("b1", (byte)this.byte1);
/* 1763:1371 */     tag.a("rbaddr", (byte)this.rbaddr);
/* 1764:     */   }
/* 1765:     */   
/* 1766:     */   protected void readFromPacket(Packet211TileDesc pkt)
/* 1767:     */     throws IOException
/* 1768:     */   {
/* 1769:1376 */     this.Rotation = pkt.getByte();
/* 1770:     */   }
/* 1771:     */   
/* 1772:     */   protected void writeToPacket(Packet211TileDesc pkt)
/* 1773:     */   {
/* 1774:1380 */     pkt.addByte(this.Rotation);
/* 1775:     */   }
/* 1776:     */   
/* 1777:     */   public ef l()
/* 1778:     */   {
/* 1779:1384 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 1780:1385 */     packet.subId = 7;
/* 1781:1386 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 1782:1387 */     packet.zCoord = this.n;
/* 1783:1388 */     writeToPacket(packet);
/* 1784:1389 */     packet.encode();
/* 1785:1390 */     return packet;
/* 1786:     */   }
/* 1787:     */   
/* 1788:     */   public void handlePacket(Packet211TileDesc packet)
/* 1789:     */   {
/* 1790:     */     try
/* 1791:     */     {
/* 1792:1395 */       if (packet.subId != 7) {
/* 1793:1395 */         return;
/* 1794:     */       }
/* 1795:1396 */       readFromPacket(packet);
/* 1796:     */     }
/* 1797:     */     catch (IOException e) {}
/* 1798:1398 */     this.k.i(this.l, this.m, this.n);
/* 1799:     */   }
/* 1800:     */   
/* 1801:1402 */   public int Rotation = 0;
/* 1802:     */   public byte[] memory;
/* 1803:     */   int addrPOR;
/* 1804:     */   int addrBRK;
/* 1805:     */   int regSP;
/* 1806:     */   int regPC;
/* 1807:     */   int regA;
/* 1808:     */   int regB;
/* 1809:     */   int regX;
/* 1810:     */   int regY;
/* 1811:     */   int regR;
/* 1812:     */   int regI;
/* 1813:     */   int regD;
/* 1814:     */   boolean flagC;
/* 1815:     */   boolean flagZ;
/* 1816:     */   boolean flagID;
/* 1817:     */   boolean flagD;
/* 1818:     */   boolean flagBRK;
/* 1819:     */   boolean flagO;
/* 1820:     */   boolean flagN;
/* 1821:     */   boolean flagE;
/* 1822:     */   boolean flagM;
/* 1823:     */   boolean flagX;
/* 1824:1413 */   int mmuRBB = 0;
/* 1825:1413 */   int mmuRBA = 0;
/* 1826:1413 */   int mmuRBW = 0;
/* 1827:1414 */   boolean mmuEnRB = false;
/* 1828:1414 */   boolean mmuEnRBW = false;
/* 1829:1415 */   private boolean rbTimeout = false;
/* 1830:1416 */   private boolean waiTimeout = false;
/* 1831:1417 */   public int sliceCycles = -1;
/* 1832:1418 */   IRedbusConnectable rbCache = null;
/* 1833:1419 */   public int rtcTicks = 0;
/* 1834:1422 */   public int byte0 = 2;
/* 1835:1422 */   public int byte1 = 1;
/* 1836:1422 */   public int rbaddr = 0;
/* 1837:1425 */   TileBackplane[] backplane = new TileBackplane[7];
/* 1838:     */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.TileCPU
 * JD-Core Version:    0.7.0.1
 */