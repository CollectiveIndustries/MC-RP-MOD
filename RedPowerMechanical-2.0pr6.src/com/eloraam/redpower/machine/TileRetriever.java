/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   7:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   8:    */ import com.eloraam.redpower.core.CoreLib;
/*   9:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  10:    */ import com.eloraam.redpower.core.ITubeConnectable;
/*  11:    */ import com.eloraam.redpower.core.MachineLib;
/*  12:    */ import com.eloraam.redpower.core.MachineLib.FilterMap;
/*  13:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  14:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  15:    */ import com.eloraam.redpower.core.TubeItem;
/*  16:    */ import com.eloraam.redpower.core.TubeLib.InRouteFinder;
/*  17:    */ import com.eloraam.redpower.core.WorldCoord;
/*  18:    */ import java.util.List;
/*  19:    */ import la;
/*  20:    */ import net.minecraftforge.common.ForgeDirection;
/*  21:    */ import net.minecraftforge.common.ISidedInventory;
/*  22:    */ import py;
/*  23:    */ import qx;
/*  24:    */ import ur;
/*  25:    */ 
/*  26:    */ public class TileRetriever
/*  27:    */   extends TileFilter
/*  28:    */   implements IBluePowerConnectable
/*  29:    */ {
/*  30:    */   public int getConnectableMask()
/*  31:    */   {
/*  32: 28 */     return 1073741823;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public int getConnectClass(int side)
/*  36:    */   {
/*  37: 32 */     return 65;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getCornerPowerMode()
/*  41:    */   {
/*  42: 35 */     return 0;
/*  43:    */   }
/*  44:    */   
/*  45: 39 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  46:    */   {
/*  47:    */     public any getParent()
/*  48:    */     {
/*  49: 41 */       return TileRetriever.this;
/*  50:    */     }
/*  51:    */   };
/*  52:    */   
/*  53:    */   public BluePowerConductor getBlueConductor(int side)
/*  54:    */   {
/*  55: 46 */     return this.cond;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  59:    */   {
/*  60: 52 */     if ((side == (this.Rotation ^ 0x1)) && (state == 3))
/*  61:    */     {
/*  62: 53 */       if (!this.buffer.isEmpty()) {
/*  63: 53 */         return false;
/*  64:    */       }
/*  65: 54 */       if (this.filterMap == null) {
/*  66: 54 */         regenFilterMap();
/*  67:    */       }
/*  68: 55 */       if ((this.filterMap.size() > 0) && (!this.filterMap.containsKey(ti.item))) {
/*  69: 57 */         return false;
/*  70:    */       }
/*  71: 58 */       this.buffer.addNewColor(ti.item, this.color);
/*  72: 59 */       this.Delay = true;
/*  73: 60 */       updateBlock();
/*  74: 61 */       scheduleTick(5);
/*  75: 62 */       drainBuffer();
/*  76: 63 */       return true;
/*  77:    */     }
/*  78: 64 */     if ((side == this.Rotation) && (state == 2)) {
/*  79: 65 */       return super.tubeItemEnter(side, state, ti);
/*  80:    */     }
/*  81: 67 */     return false;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  85:    */   {
/*  86: 71 */     if ((side == (this.Rotation ^ 0x1)) && (state == 3))
/*  87:    */     {
/*  88: 72 */       if (!this.buffer.isEmpty()) {
/*  89: 72 */         return false;
/*  90:    */       }
/*  91: 73 */       if (this.filterMap == null) {
/*  92: 73 */         regenFilterMap();
/*  93:    */       }
/*  94: 74 */       if (this.filterMap.size() == 0) {
/*  95: 74 */         return true;
/*  96:    */       }
/*  97: 75 */       return this.filterMap.containsKey(ti.item);
/*  98:    */     }
/*  99: 76 */     if ((side == this.Rotation) && (state == 2)) {
/* 100: 77 */       return super.tubeItemCanEnter(side, state, ti);
/* 101:    */     }
/* 102: 79 */     return false;
/* 103:    */   }
/* 104:    */   
/* 105:    */   private void stepSelect()
/* 106:    */   {
/* 107: 85 */     for (int i = 0; i < 9; i++)
/* 108:    */     {
/* 109: 86 */       this.select = ((byte)(this.select + 1));
/* 110: 86 */       if (this.select > 8) {
/* 111: 86 */         this.select = 0;
/* 112:    */       }
/* 113: 87 */       ur ct = this.contents[this.select];
/* 114: 88 */       if ((ct != null) && (ct.a > 0)) {
/* 115: 89 */         return;
/* 116:    */       }
/* 117:    */     }
/* 118: 91 */     this.select = 0;
/* 119:    */   }
/* 120:    */   
/* 121:    */   protected boolean handleExtract(WorldCoord wc)
/* 122:    */   {
/* 123: 95 */     ITubeConnectable itc = (ITubeConnectable)CoreLib.getTileEntity(this.k, wc, ITubeConnectable.class);
/* 124: 97 */     if ((itc == null) || (!itc.canRouteItems())) {
/* 125: 98 */       return super.handleExtract(wc);
/* 126:    */     }
/* 127:100 */     if (this.cond.getVoltage() < 60.0D) {
/* 128:100 */       return false;
/* 129:    */     }
/* 130:102 */     if (this.filterMap == null) {
/* 131:102 */       regenFilterMap();
/* 132:    */     }
/* 133:103 */     TubeLib.InRouteFinder irf = new TubeLib.InRouteFinder(this.k, this.filterMap);
/* 134:105 */     if (this.mode == 0) {
/* 135:105 */       irf.setSubFilt(this.select);
/* 136:    */     }
/* 137:106 */     int sm = irf.find(new WorldCoord(this), 1 << (this.Rotation ^ 0x1));
/* 138:107 */     if (sm < 0) {
/* 139:107 */       return false;
/* 140:    */     }
/* 141:109 */     WorldCoord dest = irf.getResultPoint();
/* 142:110 */     la inv = MachineLib.getInventory(this.k, dest);
/* 143:111 */     if (inv == null) {
/* 144:111 */       return false;
/* 145:    */     }
/* 146:113 */     int side = irf.getResultSide();
/* 147:114 */     int start = 0;
/* 148:115 */     int len = inv.k_();
/* 149:116 */     if ((inv instanceof ISidedInventory))
/* 150:    */     {
/* 151:117 */       ISidedInventory isi = (ISidedInventory)inv;
/* 152:118 */       start = isi.getStartInventorySide(ForgeDirection.getOrientation(side));
/* 153:    */       
/* 154:120 */       len = isi.getSizeInventorySide(ForgeDirection.getOrientation(side));
/* 155:    */     }
/* 156:123 */     dest.step(side);
/* 157:124 */     TileTube tt = (TileTube)CoreLib.getTileEntity(this.k, dest, TileTube.class);
/* 158:126 */     if (tt == null) {
/* 159:126 */       return false;
/* 160:    */     }
/* 161:128 */     ur ist = MachineLib.collectOneStack(inv, start, len, this.contents[sm]);
/* 162:130 */     if (ist == null) {
/* 163:130 */       return false;
/* 164:    */     }
/* 165:131 */     TubeItem ti = new TubeItem(side, ist);
/* 166:    */     
/* 167:133 */     this.cond.drawPower(25 * ist.a);
/* 168:134 */     ti.mode = 3;
/* 169:135 */     tt.addTubeItem(ti);
/* 170:137 */     if (this.mode == 0) {
/* 171:137 */       stepSelect();
/* 172:    */     }
/* 173:138 */     return true;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void g()
/* 177:    */   {
/* 178:166 */     super.g();
/* 179:167 */     if (CoreLib.isClient(this.k)) {
/* 180:167 */       return;
/* 181:    */     }
/* 182:169 */     if (this.ConMask < 0)
/* 183:    */     {
/* 184:170 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 185:    */       
/* 186:172 */       this.cond.recache(this.ConMask, 0);
/* 187:    */     }
/* 188:174 */     this.cond.iterate();
/* 189:175 */     dirtyBlock();
/* 190:176 */     if (this.cond.Flow == 0)
/* 191:    */     {
/* 192:177 */       if (this.Charged)
/* 193:    */       {
/* 194:178 */         this.Charged = false;
/* 195:179 */         updateBlock();
/* 196:    */       }
/* 197:    */     }
/* 198:181 */     else if (!this.Charged)
/* 199:    */     {
/* 200:182 */       this.Charged = true;
/* 201:183 */       updateBlock();
/* 202:    */     }
/* 203:    */   }
/* 204:    */   
/* 205:    */   public void onBlockNeighborChange(int l)
/* 206:    */   {
/* 207:188 */     this.ConMask = -1;
/* 208:189 */     super.onBlockNeighborChange(l);
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void onTileTick()
/* 212:    */   {
/* 213:193 */     super.onTileTick();
/* 214:194 */     if (this.Delay)
/* 215:    */     {
/* 216:195 */       this.Delay = false;
/* 217:196 */       updateBlock();
/* 218:    */     }
/* 219:    */   }
/* 220:    */   
/* 221:    */   protected void doSuck()
/* 222:    */   {
/* 223:201 */     suckEntities(getSizeBox(2.55D, 5.05D, -0.95D));
/* 224:    */   }
/* 225:    */   
/* 226:    */   protected boolean suckFilter(ur ist)
/* 227:    */   {
/* 228:205 */     if (this.cond.getVoltage() < 60.0D) {
/* 229:205 */       return false;
/* 230:    */     }
/* 231:206 */     if (!super.suckFilter(ist)) {
/* 232:206 */       return false;
/* 233:    */     }
/* 234:207 */     this.cond.drawPower(25 * ist.a);
/* 235:208 */     return true;
/* 236:    */   }
/* 237:    */   
/* 238:    */   protected int suckEntity(Object ent)
/* 239:    */   {
/* 240:212 */     if ((ent instanceof py))
/* 241:    */     {
/* 242:213 */       if (this.cond.getVoltage() < 60.0D) {
/* 243:213 */         return 0;
/* 244:    */       }
/* 245:215 */       if (this.filterMap == null) {
/* 246:215 */         regenFilterMap();
/* 247:    */       }
/* 248:216 */       py em = (py)ent;
/* 249:217 */       if (!MachineLib.emptyInventory(em, 0, em.k_())) {
/* 250:219 */         return super.suckEntity(ent);
/* 251:    */       }
/* 252:221 */       List items = em.getItemsDropped();
/* 253:222 */       for (ur ist : items) {
/* 254:223 */         this.buffer.addNewColor(ist, this.color);
/* 255:    */       }
/* 256:225 */       em.x();
/* 257:226 */       this.cond.drawPower(200.0D);
/* 258:227 */       return 2;
/* 259:    */     }
/* 260:270 */     return super.suckEntity(ent);
/* 261:    */   }
/* 262:    */   
/* 263:    */   public boolean onBlockActivated(qx player)
/* 264:    */   {
/* 265:276 */     if (player.ah()) {
/* 266:276 */       return false;
/* 267:    */     }
/* 268:277 */     if (CoreLib.isClient(this.k)) {
/* 269:278 */       return true;
/* 270:    */     }
/* 271:279 */     player.openGui(RedPowerMachine.instance, 7, this.k, this.l, this.m, this.n);
/* 272:    */     
/* 273:281 */     return true;
/* 274:    */   }
/* 275:    */   
/* 276:    */   public int getExtendedID()
/* 277:    */   {
/* 278:285 */     return 8;
/* 279:    */   }
/* 280:    */   
/* 281:    */   public String b()
/* 282:    */   {
/* 283:291 */     return "Retriever";
/* 284:    */   }
/* 285:    */   
/* 286:    */   public void a(bq tag)
/* 287:    */   {
/* 288:297 */     super.a(tag);
/* 289:298 */     this.cond.readFromNBT(tag);
/* 290:    */     
/* 291:300 */     this.mode = tag.c("mode");
/* 292:301 */     this.select = tag.c("sel");
/* 293:    */   }
/* 294:    */   
/* 295:    */   public void b(bq tag)
/* 296:    */   {
/* 297:305 */     super.b(tag);
/* 298:306 */     this.cond.writeToNBT(tag);
/* 299:307 */     tag.a("mode", this.mode);
/* 300:308 */     tag.a("sel", this.select);
/* 301:    */   }
/* 302:    */   
/* 303:311 */   public int ConMask = -1;
/* 304:312 */   public byte select = 0;
/* 305:312 */   public byte mode = 0;
/* 306:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileRetriever
 * JD-Core Version:    0.7.0.1
 */