/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import by;
/*   6:    */ import com.eloraam.redpower.RedPowerMachine;
/*   7:    */ import com.eloraam.redpower.base.TileAppliance;
/*   8:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   9:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*  10:    */ import com.eloraam.redpower.core.CoreLib;
/*  11:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  12:    */ import com.eloraam.redpower.core.IChargeable;
/*  13:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  14:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  15:    */ import java.io.IOException;
/*  16:    */ import la;
/*  17:    */ import md;
/*  18:    */ import qx;
/*  19:    */ import ur;
/*  20:    */ import yc;
/*  21:    */ 
/*  22:    */ public class TileChargingBench
/*  23:    */   extends TileAppliance
/*  24:    */   implements la, IBluePowerConnectable
/*  25:    */ {
/*  26:    */   public TileChargingBench()
/*  27:    */   {
/*  28: 28 */     this.contents = new ur[16];
/*  29:    */   }
/*  30:    */   
/*  31:    */   public int getConnectableMask()
/*  32:    */   {
/*  33: 34 */     return 1073741823;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public int getConnectClass(int side)
/*  37:    */   {
/*  38: 38 */     return 64;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getCornerPowerMode()
/*  42:    */   {
/*  43: 41 */     return 0;
/*  44:    */   }
/*  45:    */   
/*  46: 45 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  47:    */   {
/*  48:    */     public any getParent()
/*  49:    */     {
/*  50: 47 */       return TileChargingBench.this;
/*  51:    */     }
/*  52:    */   };
/*  53:    */   
/*  54:    */   public BluePowerConductor getBlueConductor(int side)
/*  55:    */   {
/*  56: 52 */     return this.cond;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public int getLightValue()
/*  60:    */   {
/*  61: 59 */     return 0;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public int getExtendedID()
/*  65:    */   {
/*  66: 63 */     return 5;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public int getMaxStorage()
/*  70:    */   {
/*  71: 68 */     return 3000;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public int getStorageForRender()
/*  75:    */   {
/*  76: 72 */     return this.Storage * 4 / getMaxStorage();
/*  77:    */   }
/*  78:    */   
/*  79:    */   public int getChargeScaled(int i)
/*  80:    */   {
/*  81: 76 */     return Math.min(i, i * this.cond.Charge / 1000);
/*  82:    */   }
/*  83:    */   
/*  84:    */   public int getStorageScaled(int i)
/*  85:    */   {
/*  86: 80 */     return Math.min(i, i * this.Storage / getMaxStorage());
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void g()
/*  90:    */   {
/*  91: 84 */     super.g();
/*  92: 85 */     if (CoreLib.isClient(this.k)) {
/*  93: 85 */       return;
/*  94:    */     }
/*  95: 87 */     if (this.ConMask < 0)
/*  96:    */     {
/*  97: 88 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/*  98:    */       
/*  99: 90 */       this.cond.recache(this.ConMask, 0);
/* 100:    */     }
/* 101: 92 */     this.cond.iterate();
/* 102: 93 */     dirtyBlock();
/* 103: 95 */     if (this.cond.Flow == 0)
/* 104:    */     {
/* 105: 96 */       if (this.Powered)
/* 106:    */       {
/* 107: 97 */         this.Powered = false;
/* 108: 98 */         updateBlock();
/* 109:    */       }
/* 110:    */     }
/* 111:100 */     else if (!this.Powered)
/* 112:    */     {
/* 113:101 */       this.Powered = true;
/* 114:102 */       updateBlock();
/* 115:    */     }
/* 116:104 */     int rs = getStorageForRender();
/* 117:105 */     if ((this.cond.Charge > 600) && (this.Storage < getMaxStorage()))
/* 118:    */     {
/* 119:106 */       int n = Math.min((this.cond.Charge - 600) / 40, 5);
/* 120:107 */       n = Math.min(n, getMaxStorage() - this.Storage);
/* 121:108 */       this.cond.drawPower(n * 1000);
/* 122:109 */       this.Storage += n;
/* 123:    */     }
/* 124:111 */     boolean lastact = this.Active;this.Active = false;
/* 125:112 */     if (this.Storage > 0) {
/* 126:113 */       for (int i = 0; i < 16; i++) {
/* 127:114 */         if ((this.contents[i] != null) && 
/* 128:115 */           ((this.contents[i].b() instanceof IChargeable))) {
/* 129:117 */           if (this.contents[i].j() > 1)
/* 130:    */           {
/* 131:118 */             int d = Math.min(this.contents[i].j() - 1, this.Storage);
/* 132:    */             
/* 133:120 */             d = Math.min(d, 25);
/* 134:    */             
/* 135:122 */             this.contents[i].b(this.contents[i].j() - d);
/* 136:    */             
/* 137:124 */             this.Storage -= d;
/* 138:125 */             d();
/* 139:126 */             this.Active = true;
/* 140:    */           }
/* 141:    */         }
/* 142:    */       }
/* 143:    */     }
/* 144:129 */     if ((rs != getStorageForRender()) || (lastact != this.Active)) {
/* 145:130 */       updateBlock();
/* 146:    */     }
/* 147:    */   }
/* 148:    */   
/* 149:    */   public boolean onBlockActivated(qx player)
/* 150:    */   {
/* 151:136 */     if (player.ah()) {
/* 152:136 */       return false;
/* 153:    */     }
/* 154:137 */     if (CoreLib.isClient(this.k)) {
/* 155:138 */       return true;
/* 156:    */     }
/* 157:139 */     player.openGui(RedPowerMachine.instance, 14, this.k, this.l, this.m, this.n);
/* 158:    */     
/* 159:141 */     return true;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 163:    */   {
/* 164:146 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3);
/* 165:    */   }
/* 166:    */   
/* 167:    */   public void onBlockRemoval()
/* 168:    */   {
/* 169:150 */     for (int i = 0; i < 2; i++)
/* 170:    */     {
/* 171:151 */       ur ist = this.contents[i];
/* 172:152 */       if ((ist != null) && (ist.a > 0)) {
/* 173:153 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/* 174:    */       }
/* 175:    */     }
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void onBlockNeighborChange(int l)
/* 179:    */   {
/* 180:160 */     this.ConMask = -1;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public int k_()
/* 184:    */   {
/* 185:166 */     return 16;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public ur a(int i)
/* 189:    */   {
/* 190:170 */     return this.contents[i];
/* 191:    */   }
/* 192:    */   
/* 193:    */   public ur a(int i, int j)
/* 194:    */   {
/* 195:175 */     if (this.contents[i] == null) {
/* 196:175 */       return null;
/* 197:    */     }
/* 198:177 */     if (this.contents[i].a <= j)
/* 199:    */     {
/* 200:178 */       ur tr = this.contents[i];
/* 201:179 */       this.contents[i] = null;
/* 202:180 */       d();
/* 203:181 */       return tr;
/* 204:    */     }
/* 205:183 */     ur tr = this.contents[i].a(j);
/* 206:184 */     if (this.contents[i].a == 0) {
/* 207:185 */       this.contents[i] = null;
/* 208:    */     }
/* 209:186 */     d();
/* 210:187 */     return tr;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public ur a_(int i)
/* 214:    */   {
/* 215:191 */     if (this.contents[i] == null) {
/* 216:191 */       return null;
/* 217:    */     }
/* 218:192 */     ur ist = this.contents[i];
/* 219:193 */     this.contents[i] = null;
/* 220:194 */     return ist;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public void a(int i, ur ist)
/* 224:    */   {
/* 225:198 */     this.contents[i] = ist;
/* 226:199 */     if ((ist != null) && (ist.a > c())) {
/* 227:200 */       ist.a = c();
/* 228:    */     }
/* 229:201 */     d();
/* 230:    */   }
/* 231:    */   
/* 232:    */   public String b()
/* 233:    */   {
/* 234:205 */     return "Charging Bench";
/* 235:    */   }
/* 236:    */   
/* 237:    */   public int c()
/* 238:    */   {
/* 239:209 */     return 64;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public boolean a_(qx player)
/* 243:    */   {
/* 244:213 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 245:214 */       return false;
/* 246:    */     }
/* 247:215 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 248:    */   }
/* 249:    */   
/* 250:    */   public void f() {}
/* 251:    */   
/* 252:    */   public void l_() {}
/* 253:    */   
/* 254:    */   public void a(bq tag)
/* 255:    */   {
/* 256:226 */     super.a(tag);
/* 257:    */     
/* 258:228 */     by items = tag.m("Items");
/* 259:229 */     this.contents = new ur[k_()];
/* 260:230 */     for (int i = 0; i < items.c(); i++)
/* 261:    */     {
/* 262:231 */       bq item = (bq)items.b(i);
/* 263:    */       
/* 264:233 */       int j = item.c("Slot") & 0xFF;
/* 265:234 */       if ((j >= 0) && (j < this.contents.length)) {
/* 266:235 */         this.contents[j] = ur.a(item);
/* 267:    */       }
/* 268:    */     }
/* 269:239 */     this.cond.readFromNBT(tag);
/* 270:    */     
/* 271:241 */     this.Storage = tag.d("stor");
/* 272:242 */     int k = tag.c("ps");
/* 273:243 */     this.Powered = ((k & 0x1) > 0);
/* 274:    */   }
/* 275:    */   
/* 276:    */   public void b(bq tag)
/* 277:    */   {
/* 278:247 */     super.b(tag);
/* 279:    */     
/* 280:249 */     by items = new by();
/* 281:250 */     for (int i = 0; i < this.contents.length; i++) {
/* 282:251 */       if (this.contents[i] != null)
/* 283:    */       {
/* 284:252 */         bq item = new bq();
/* 285:253 */         item.a("Slot", (byte)i);
/* 286:254 */         this.contents[i].b(item);
/* 287:255 */         items.a(item);
/* 288:    */       }
/* 289:    */     }
/* 290:258 */     tag.a("Items", items);
/* 291:    */     
/* 292:260 */     this.cond.writeToNBT(tag);
/* 293:    */     
/* 294:262 */     tag.a("stor", (short)this.Storage);
/* 295:263 */     int ps = this.Powered ? 1 : 0;
/* 296:264 */     tag.a("ps2", (byte)ps);
/* 297:    */   }
/* 298:    */   
/* 299:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 300:    */     throws IOException
/* 301:    */   {
/* 302:269 */     this.Rotation = pkt.getByte();
/* 303:270 */     int ps = pkt.getByte();
/* 304:271 */     this.Active = ((ps & 0x1) > 0);
/* 305:272 */     this.Powered = ((ps & 0x2) > 0);
/* 306:273 */     this.Storage = ((int)pkt.getUVLC());
/* 307:    */   }
/* 308:    */   
/* 309:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 310:    */   {
/* 311:277 */     pkt.addByte(this.Rotation);
/* 312:278 */     int ps = (this.Active ? 1 : 0) | (this.Powered ? 2 : 0);
/* 313:279 */     pkt.addByte(ps);
/* 314:280 */     pkt.addUVLC(this.Storage);
/* 315:    */   }
/* 316:    */   
/* 317:284 */   public boolean Powered = false;
/* 318:285 */   public int Storage = 0;
/* 319:    */   private ur[] contents;
/* 320:288 */   public int ConMask = -1;
/* 321:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileChargingBench
 * JD-Core Version:    0.7.0.1
 */