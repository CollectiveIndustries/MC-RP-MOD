/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.MachineLib;
/*   8:    */ import com.eloraam.redpower.core.MachineLib.FilterMap;
/*   9:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  10:    */ import com.eloraam.redpower.core.TubeItem;
/*  11:    */ import la;
/*  12:    */ import net.minecraftforge.common.ForgeDirection;
/*  13:    */ import net.minecraftforge.common.ISidedInventory;
/*  14:    */ import qx;
/*  15:    */ import ur;
/*  16:    */ import yc;
/*  17:    */ 
/*  18:    */ public class TileFilter
/*  19:    */   extends TileTranspose
/*  20:    */   implements la, ISidedInventory
/*  21:    */ {
/*  22:    */   protected ur[] contents;
/*  23:    */   
/*  24:    */   public TileFilter()
/*  25:    */   {
/*  26: 19 */     this.contents = new ur[9];
/*  27:    */   }
/*  28:    */   
/*  29:    */   void regenFilterMap()
/*  30:    */   {
/*  31: 25 */     this.filterMap = MachineLib.makeFilterMap(this.contents);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  35:    */   {
/*  36: 31 */     if ((side != (this.Rotation ^ 0x1)) || (state != 1)) {
/*  37: 32 */       return super.tubeItemEnter(side, state, ti);
/*  38:    */     }
/*  39: 33 */     if (this.filterMap == null) {
/*  40: 33 */       regenFilterMap();
/*  41:    */     }
/*  42: 34 */     if (this.filterMap.size() == 0) {
/*  43: 35 */       return super.tubeItemEnter(side, state, ti);
/*  44:    */     }
/*  45: 36 */     if (!this.filterMap.containsKey(ti.item)) {
/*  46: 37 */       return false;
/*  47:    */     }
/*  48: 38 */     return super.tubeItemEnter(side, state, ti);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  52:    */   {
/*  53: 42 */     if ((side != (this.Rotation ^ 0x1)) || (state != 1)) {
/*  54: 43 */       return super.tubeItemCanEnter(side, state, ti);
/*  55:    */     }
/*  56: 44 */     if (this.filterMap == null) {
/*  57: 44 */       regenFilterMap();
/*  58:    */     }
/*  59: 45 */     if (this.filterMap.size() == 0) {
/*  60: 46 */       return super.tubeItemCanEnter(side, state, ti);
/*  61:    */     }
/*  62: 47 */     if (!this.filterMap.containsKey(ti.item)) {
/*  63: 48 */       return false;
/*  64:    */     }
/*  65: 49 */     return super.tubeItemCanEnter(side, state, ti);
/*  66:    */   }
/*  67:    */   
/*  68:    */   protected void addToBuffer(ur ist)
/*  69:    */   {
/*  70: 53 */     this.buffer.addNewColor(ist, this.color);
/*  71:    */   }
/*  72:    */   
/*  73:    */   public int getStartInventorySide(ForgeDirection side)
/*  74:    */   {
/*  75: 59 */     return 0;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public int getSizeInventorySide(ForgeDirection fd)
/*  79:    */   {
/*  80: 63 */     int side = fd.ordinal();
/*  81: 64 */     if ((side == this.Rotation) || (side == (this.Rotation ^ 0x1))) {
/*  82: 65 */       return 0;
/*  83:    */     }
/*  84: 66 */     return 9;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public boolean onBlockActivated(qx player)
/*  88:    */   {
/*  89: 72 */     if (player.ah()) {
/*  90: 72 */       return false;
/*  91:    */     }
/*  92: 73 */     if (CoreLib.isClient(this.k)) {
/*  93: 74 */       return true;
/*  94:    */     }
/*  95: 75 */     player.openGui(RedPowerMachine.instance, 2, this.k, this.l, this.m, this.n);
/*  96:    */     
/*  97: 77 */     return true;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public int getExtendedID()
/* 101:    */   {
/* 102: 81 */     return 3;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void onBlockRemoval()
/* 106:    */   {
/* 107: 85 */     super.onBlockRemoval();
/* 108: 87 */     for (int i = 0; i < 9; i++)
/* 109:    */     {
/* 110: 88 */       ur ist = this.contents[i];
/* 111: 89 */       if ((ist != null) && (ist.a > 0)) {
/* 112: 90 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 113:    */       }
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   protected boolean handleExtract(la inv, int start, int len)
/* 118:    */   {
/* 119: 99 */     if (this.filterMap == null) {
/* 120: 99 */       regenFilterMap();
/* 121:    */     }
/* 122:100 */     if (this.filterMap.size() == 0)
/* 123:    */     {
/* 124:101 */       ur ist = MachineLib.collectOneStack(inv, start, len, null);
/* 125:103 */       if (ist == null) {
/* 126:103 */         return false;
/* 127:    */       }
/* 128:104 */       this.buffer.addNewColor(ist, this.color);
/* 129:105 */       drainBuffer();
/* 130:106 */       return true;
/* 131:    */     }
/* 132:108 */     int sm = MachineLib.matchAnyStack(this.filterMap, inv, start, len);
/* 133:109 */     if (sm < 0) {
/* 134:109 */       return false;
/* 135:    */     }
/* 136:111 */     ur coll = MachineLib.collectOneStack(inv, start, len, this.contents[sm]);
/* 137:    */     
/* 138:113 */     this.buffer.addNewColor(coll, this.color);
/* 139:114 */     drainBuffer();
/* 140:115 */     return true;
/* 141:    */   }
/* 142:    */   
/* 143:    */   protected boolean suckFilter(ur ist)
/* 144:    */   {
/* 145:122 */     if (this.filterMap == null) {
/* 146:122 */       regenFilterMap();
/* 147:    */     }
/* 148:123 */     if (this.filterMap.size() == 0) {
/* 149:123 */       return true;
/* 150:    */     }
/* 151:124 */     return this.filterMap.containsKey(ist);
/* 152:    */   }
/* 153:    */   
/* 154:    */   public int k_()
/* 155:    */   {
/* 156:130 */     return 9;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public ur a(int i)
/* 160:    */   {
/* 161:134 */     return this.contents[i];
/* 162:    */   }
/* 163:    */   
/* 164:    */   public ur a(int i, int j)
/* 165:    */   {
/* 166:139 */     if (this.contents[i] == null) {
/* 167:139 */       return null;
/* 168:    */     }
/* 169:141 */     if (this.contents[i].a <= j)
/* 170:    */     {
/* 171:142 */       ur tr = this.contents[i];
/* 172:143 */       this.contents[i] = null;
/* 173:144 */       d();
/* 174:145 */       return tr;
/* 175:    */     }
/* 176:147 */     ur tr = this.contents[i].a(j);
/* 177:148 */     if (this.contents[i].a == 0) {
/* 178:149 */       this.contents[i] = null;
/* 179:    */     }
/* 180:150 */     d();
/* 181:151 */     return tr;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public ur a_(int i)
/* 185:    */   {
/* 186:155 */     if (this.contents[i] == null) {
/* 187:155 */       return null;
/* 188:    */     }
/* 189:156 */     ur ist = this.contents[i];
/* 190:157 */     this.contents[i] = null;
/* 191:158 */     return ist;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void a(int i, ur ist)
/* 195:    */   {
/* 196:162 */     this.contents[i] = ist;
/* 197:163 */     if ((ist != null) && (ist.a > c())) {
/* 198:164 */       ist.a = c();
/* 199:    */     }
/* 200:165 */     d();
/* 201:    */   }
/* 202:    */   
/* 203:    */   public String b()
/* 204:    */   {
/* 205:169 */     return "Filter";
/* 206:    */   }
/* 207:    */   
/* 208:    */   public int c()
/* 209:    */   {
/* 210:173 */     return 64;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public boolean a_(qx player)
/* 214:    */   {
/* 215:177 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 216:178 */       return false;
/* 217:    */     }
/* 218:179 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 219:    */   }
/* 220:    */   
/* 221:    */   public void d()
/* 222:    */   {
/* 223:184 */     this.filterMap = null;
/* 224:185 */     super.d();
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void f() {}
/* 228:    */   
/* 229:    */   public void l_() {}
/* 230:    */   
/* 231:    */   public void a(bq tag)
/* 232:    */   {
/* 233:195 */     super.a(tag);
/* 234:    */     
/* 235:197 */     by items = tag.m("Items");
/* 236:198 */     this.contents = new ur[k_()];
/* 237:199 */     for (int i = 0; i < items.c(); i++)
/* 238:    */     {
/* 239:200 */       bq item = (bq)items.b(i);
/* 240:    */       
/* 241:202 */       int j = item.c("Slot") & 0xFF;
/* 242:203 */       if ((j >= 0) && (j < this.contents.length)) {
/* 243:204 */         this.contents[j] = ur.a(item);
/* 244:    */       }
/* 245:    */     }
/* 246:207 */     this.color = tag.c("color");
/* 247:    */   }
/* 248:    */   
/* 249:    */   public void b(bq tag)
/* 250:    */   {
/* 251:211 */     super.b(tag);
/* 252:    */     
/* 253:213 */     by items = new by();
/* 254:214 */     for (int i = 0; i < this.contents.length; i++) {
/* 255:215 */       if (this.contents[i] != null)
/* 256:    */       {
/* 257:216 */         bq item = new bq();
/* 258:217 */         item.a("Slot", (byte)i);
/* 259:218 */         this.contents[i].b(item);
/* 260:219 */         items.a(item);
/* 261:    */       }
/* 262:    */     }
/* 263:222 */     tag.a("Items", items);
/* 264:223 */     tag.a("color", this.color);
/* 265:    */   }
/* 266:    */   
/* 267:227 */   protected MachineLib.FilterMap filterMap = null;
/* 268:228 */   public byte color = 0;
/* 269:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileFilter
 * JD-Core Version:    0.7.0.1
 */