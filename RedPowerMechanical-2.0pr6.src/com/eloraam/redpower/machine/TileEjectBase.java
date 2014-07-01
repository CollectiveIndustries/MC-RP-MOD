/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.ITubeConnectable;
/*   8:    */ import com.eloraam.redpower.core.TubeBuffer;
/*   9:    */ import com.eloraam.redpower.core.TubeItem;
/*  10:    */ import la;
/*  11:    */ import net.minecraftforge.common.ForgeDirection;
/*  12:    */ import net.minecraftforge.common.ISidedInventory;
/*  13:    */ import qx;
/*  14:    */ import ur;
/*  15:    */ import yc;
/*  16:    */ 
/*  17:    */ public class TileEjectBase
/*  18:    */   extends TileMachine
/*  19:    */   implements la, ISidedInventory, ITubeConnectable
/*  20:    */ {
/*  21:    */   public TileEjectBase()
/*  22:    */   {
/*  23: 28 */     this.contents = new ur[9];
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getTubeConnectableSides()
/*  27:    */   {
/*  28: 34 */     return 1 << this.Rotation;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public int getTubeConClass()
/*  32:    */   {
/*  33: 38 */     return 0;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public boolean canRouteItems()
/*  37:    */   {
/*  38: 42 */     return false;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  42:    */   {
/*  43: 46 */     if ((side == this.Rotation) && (state == 2))
/*  44:    */     {
/*  45: 47 */       this.buffer.addBounce(ti);
/*  46: 48 */       this.Active = true;
/*  47: 49 */       updateBlock();
/*  48: 50 */       scheduleTick(5);
/*  49: 51 */       return true;
/*  50:    */     }
/*  51: 53 */     return false;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  55:    */   {
/*  56: 57 */     if ((side == this.Rotation) && (state == 2)) {
/*  57: 58 */       return true;
/*  58:    */     }
/*  59: 59 */     return false;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public int tubeWeight(int side, int state)
/*  63:    */   {
/*  64: 63 */     if ((side == this.Rotation) && (state == 2)) {
/*  65: 64 */       return this.buffer.size();
/*  66:    */     }
/*  67: 65 */     return 0;
/*  68:    */   }
/*  69:    */   
/*  70:    */   protected void addToBuffer(ur ist)
/*  71:    */   {
/*  72: 69 */     this.buffer.addNew(ist);
/*  73:    */   }
/*  74:    */   
/*  75:    */   public boolean onBlockActivated(qx player)
/*  76:    */   {
/*  77: 75 */     if (player.ah()) {
/*  78: 75 */       return false;
/*  79:    */     }
/*  80: 76 */     if (CoreLib.isClient(this.k)) {
/*  81: 77 */       return true;
/*  82:    */     }
/*  83: 79 */     player.openGui(RedPowerMachine.instance, 12, this.k, this.l, this.m, this.n);
/*  84:    */     
/*  85: 81 */     return true;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void onBlockRemoval()
/*  89:    */   {
/*  90: 85 */     for (int i = 0; i < 9; i++)
/*  91:    */     {
/*  92: 86 */       ur ist = this.contents[i];
/*  93: 87 */       if ((ist != null) && (ist.a > 0)) {
/*  94: 88 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/*  95:    */       }
/*  96:    */     }
/*  97: 92 */     this.buffer.onRemove(this);
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void drainBuffer()
/* 101:    */   {
/* 102: 96 */     while (!this.buffer.isEmpty())
/* 103:    */     {
/* 104: 97 */       TubeItem ti = this.buffer.getLast();
/* 105: 98 */       if (!handleItem(ti))
/* 106:    */       {
/* 107: 99 */         this.buffer.plugged = true;
/* 108:100 */         return;
/* 109:    */       }
/* 110:102 */       this.buffer.pop();
/* 111:103 */       if (this.buffer.plugged) {
/* 112:103 */         return;
/* 113:    */       }
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void onTileTick()
/* 118:    */   {
/* 119:108 */     if (CoreLib.isClient(this.k)) {
/* 120:109 */       return;
/* 121:    */     }
/* 122:110 */     if (!this.buffer.isEmpty())
/* 123:    */     {
/* 124:111 */       drainBuffer();
/* 125:112 */       if (!this.buffer.isEmpty()) {
/* 126:112 */         scheduleTick(10);
/* 127:    */       } else {
/* 128:113 */         scheduleTick(5);
/* 129:    */       }
/* 130:114 */       return;
/* 131:    */     }
/* 132:116 */     if (!this.Powered)
/* 133:    */     {
/* 134:117 */       this.Active = false;
/* 135:118 */       updateBlock();
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   public int k_()
/* 140:    */   {
/* 141:125 */     return 9;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public ur a(int i)
/* 145:    */   {
/* 146:129 */     return this.contents[i];
/* 147:    */   }
/* 148:    */   
/* 149:    */   public ur a(int i, int j)
/* 150:    */   {
/* 151:134 */     if (this.contents[i] == null) {
/* 152:134 */       return null;
/* 153:    */     }
/* 154:136 */     if (this.contents[i].a <= j)
/* 155:    */     {
/* 156:137 */       ur tr = this.contents[i];
/* 157:138 */       this.contents[i] = null;
/* 158:139 */       d();
/* 159:140 */       return tr;
/* 160:    */     }
/* 161:142 */     ur tr = this.contents[i].a(j);
/* 162:143 */     if (this.contents[i].a == 0) {
/* 163:144 */       this.contents[i] = null;
/* 164:    */     }
/* 165:145 */     d();
/* 166:146 */     return tr;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public ur a_(int i)
/* 170:    */   {
/* 171:150 */     if (this.contents[i] == null) {
/* 172:150 */       return null;
/* 173:    */     }
/* 174:151 */     ur ist = this.contents[i];
/* 175:152 */     this.contents[i] = null;
/* 176:153 */     return ist;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void a(int i, ur ist)
/* 180:    */   {
/* 181:157 */     this.contents[i] = ist;
/* 182:158 */     if ((ist != null) && (ist.a > c())) {
/* 183:159 */       ist.a = c();
/* 184:    */     }
/* 185:160 */     d();
/* 186:    */   }
/* 187:    */   
/* 188:    */   public String b()
/* 189:    */   {
/* 190:164 */     return "Ejector";
/* 191:    */   }
/* 192:    */   
/* 193:    */   public int c()
/* 194:    */   {
/* 195:168 */     return 64;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public boolean a_(qx player)
/* 199:    */   {
/* 200:172 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 201:173 */       return false;
/* 202:    */     }
/* 203:174 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public void f() {}
/* 207:    */   
/* 208:    */   public void l_() {}
/* 209:    */   
/* 210:    */   public int getStartInventorySide(ForgeDirection side)
/* 211:    */   {
/* 212:185 */     return 0;
/* 213:    */   }
/* 214:    */   
/* 215:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 216:    */   {
/* 217:189 */     int side = fd.ordinal();
/* 218:190 */     if (side == this.Rotation) {
/* 219:190 */       return 0;
/* 220:    */     }
/* 221:191 */     return 9;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void a(bq tag)
/* 225:    */   {
/* 226:197 */     super.a(tag);
/* 227:    */     
/* 228:199 */     by items = tag.m("Items");
/* 229:200 */     this.contents = new ur[k_()];
/* 230:201 */     for (int i = 0; i < items.c(); i++)
/* 231:    */     {
/* 232:202 */       bq item = (bq)items.b(i);
/* 233:    */       
/* 234:204 */       int j = item.c("Slot") & 0xFF;
/* 235:205 */       if ((j >= 0) && (j < this.contents.length)) {
/* 236:206 */         this.contents[j] = ur.a(item);
/* 237:    */       }
/* 238:    */     }
/* 239:209 */     this.buffer.readFromNBT(tag);
/* 240:    */   }
/* 241:    */   
/* 242:    */   public void b(bq tag)
/* 243:    */   {
/* 244:213 */     super.b(tag);
/* 245:    */     
/* 246:215 */     by items = new by();
/* 247:216 */     for (int i = 0; i < this.contents.length; i++) {
/* 248:217 */       if (this.contents[i] != null)
/* 249:    */       {
/* 250:218 */         bq item = new bq();
/* 251:219 */         item.a("Slot", (byte)i);
/* 252:220 */         this.contents[i].b(item);
/* 253:221 */         items.a(item);
/* 254:    */       }
/* 255:    */     }
/* 256:224 */     tag.a("Items", items);
/* 257:225 */     this.buffer.writeToNBT(tag);
/* 258:    */   }
/* 259:    */   
/* 260:228 */   TubeBuffer buffer = new TubeBuffer();
/* 261:    */   protected ur[] contents;
/* 262:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileEjectBase
 * JD-Core Version:    0.7.0.1
 */