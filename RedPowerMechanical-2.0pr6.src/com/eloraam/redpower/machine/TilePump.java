/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   6:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.FluidBuffer;
/*   9:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  10:    */ import com.eloraam.redpower.core.IPipeConnectable;
/*  11:    */ import com.eloraam.redpower.core.PipeLib;
/*  12:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  13:    */ import com.eloraam.redpower.core.WorldCoord;
/*  14:    */ import md;
/*  15:    */ import ur;
/*  16:    */ 
/*  17:    */ public class TilePump
/*  18:    */   extends TileMachinePanel
/*  19:    */   implements IPipeConnectable, IBluePowerConnectable
/*  20:    */ {
/*  21:    */   public int getPipeConnectableSides()
/*  22:    */   {
/*  23: 17 */     return 12 << (((this.Rotation ^ 0x1) & 0x1) << 1);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getPipeFlangeSides()
/*  27:    */   {
/*  28: 21 */     return 12 << (((this.Rotation ^ 0x1) & 0x1) << 1);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public int getPipePressure(int side)
/*  32:    */   {
/*  33: 25 */     int rt = CoreLib.rotToSide(this.Rotation);
/*  34: 27 */     if (!this.Active) {
/*  35: 27 */       return 0;
/*  36:    */     }
/*  37: 29 */     if (side == rt) {
/*  38: 29 */       return 1000;
/*  39:    */     }
/*  40: 30 */     if (side == ((rt ^ 0x1) & 0xFF)) {
/*  41: 30 */       return -1000;
/*  42:    */     }
/*  43: 32 */     return 0;
/*  44:    */   }
/*  45:    */   
/*  46:    */   private class PumpBuffer
/*  47:    */     extends FluidBuffer
/*  48:    */   {
/*  49:    */     public PumpBuffer() {}
/*  50:    */     
/*  51:    */     public any getParent()
/*  52:    */     {
/*  53: 38 */       return TilePump.this;
/*  54:    */     }
/*  55:    */     
/*  56:    */     public void onChange()
/*  57:    */     {
/*  58: 41 */       TilePump.this.dirtyBlock();
/*  59:    */     }
/*  60:    */     
/*  61:    */     public int getMaxLevel()
/*  62:    */     {
/*  63: 43 */       return 1000;
/*  64:    */     }
/*  65:    */   }
/*  66:    */   
/*  67: 45 */   PumpBuffer inbuf = new PumpBuffer();
/*  68: 46 */   PumpBuffer outbuf = new PumpBuffer();
/*  69:    */   
/*  70:    */   public FluidBuffer getPipeBuffer(int side)
/*  71:    */   {
/*  72: 49 */     int rt = CoreLib.rotToSide(this.Rotation);
/*  73: 51 */     if (side == rt) {
/*  74: 51 */       return this.outbuf;
/*  75:    */     }
/*  76: 52 */     if (side == ((rt ^ 0x1) & 0xFF)) {
/*  77: 53 */       return this.inbuf;
/*  78:    */     }
/*  79: 54 */     return null;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public int getConnectableMask()
/*  83:    */   {
/*  84: 60 */     return 3 << ((this.Rotation & 0x1) << 1) | 0x1111100;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public int getConnectClass(int side)
/*  88:    */   {
/*  89: 64 */     return 65;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public int getCornerPowerMode()
/*  93:    */   {
/*  94: 67 */     return 0;
/*  95:    */   }
/*  96:    */   
/*  97: 71 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  98:    */   {
/*  99:    */     public any getParent()
/* 100:    */     {
/* 101: 73 */       return TilePump.this;
/* 102:    */     }
/* 103:    */   };
/* 104:    */   
/* 105:    */   public BluePowerConductor getBlueConductor(int side)
/* 106:    */   {
/* 107: 78 */     return this.cond;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public int getExtendedID()
/* 111:    */   {
/* 112: 84 */     return 1;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 116:    */   {
/* 117: 89 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 2.5D) & 0x3);
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void onBlockNeighborChange(int l)
/* 121:    */   {
/* 122: 93 */     this.ConMask = -1;
/* 123: 95 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, 16777215, 63))
/* 124:    */     {
/* 125: 97 */       if (this.Powered) {
/* 126: 97 */         return;
/* 127:    */       }
/* 128: 98 */       this.Powered = true;
/* 129: 99 */       dirtyBlock();
/* 130:    */     }
/* 131:    */     else
/* 132:    */     {
/* 133:101 */       this.Powered = false;
/* 134:102 */       dirtyBlock();
/* 135:103 */       return;
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   private void pumpFluid()
/* 140:    */   {
/* 141:108 */     if (this.inbuf.Type == 0) {
/* 142:108 */       return;
/* 143:    */     }
/* 144:110 */     int lv = Math.min(this.inbuf.getLevel(), this.outbuf.getMaxLevel() - this.outbuf.getLevel());
/* 145:    */     
/* 146:112 */     lv = Math.min(lv, this.inbuf.getLevel() + this.inbuf.Delta);
/* 147:113 */     if (lv <= 0) {
/* 148:113 */       return;
/* 149:    */     }
/* 150:114 */     if ((this.inbuf.Type != this.outbuf.Type) && (this.outbuf.Type != 0)) {
/* 151:114 */       return;
/* 152:    */     }
/* 153:116 */     this.outbuf.addLevel(this.inbuf.Type, lv);
/* 154:117 */     this.inbuf.addLevel(this.inbuf.Type, -lv);
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void g()
/* 158:    */   {
/* 159:121 */     super.g();
/* 160:122 */     if (CoreLib.isClient(this.k))
/* 161:    */     {
/* 162:123 */       if (this.Active)
/* 163:    */       {
/* 164:124 */         this.PumpTick = ((byte)(this.PumpTick + 1));
/* 165:125 */         if (this.PumpTick >= 16) {
/* 166:125 */           this.PumpTick = 0;
/* 167:    */         }
/* 168:    */       }
/* 169:127 */       return;
/* 170:    */     }
/* 171:130 */     if (this.ConMask < 0)
/* 172:    */     {
/* 173:131 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 174:    */       
/* 175:133 */       this.cond.recache(this.ConMask, 0);
/* 176:    */     }
/* 177:135 */     this.cond.iterate();
/* 178:136 */     dirtyBlock();
/* 179:    */     
/* 180:138 */     int rt = CoreLib.rotToSide(this.Rotation);
/* 181:139 */     PipeLib.movePipeLiquid(this.k, this, new WorldCoord(this), 3 << (rt & 0xFFFFFFFE));
/* 182:    */     
/* 183:    */ 
/* 184:142 */     boolean act = this.Active;
/* 185:143 */     if (this.Active)
/* 186:    */     {
/* 187:144 */       this.PumpTick = ((byte)(this.PumpTick + 1));
/* 188:145 */       if (this.PumpTick == 8)
/* 189:    */       {
/* 190:146 */         this.cond.drawPower(10000.0D);
/* 191:147 */         pumpFluid();
/* 192:    */       }
/* 193:149 */       if (this.PumpTick >= 16)
/* 194:    */       {
/* 195:150 */         this.PumpTick = 0;
/* 196:151 */         this.Active = false;
/* 197:    */       }
/* 198:153 */       this.cond.drawPower(200.0D);
/* 199:    */     }
/* 200:156 */     if (this.cond.getVoltage() < 60.0D)
/* 201:    */     {
/* 202:157 */       if ((this.Charged) && (this.cond.Flow == 0))
/* 203:    */       {
/* 204:158 */         this.Charged = false;
/* 205:159 */         updateBlock();
/* 206:    */       }
/* 207:161 */       return;
/* 208:    */     }
/* 209:162 */     if (!this.Charged)
/* 210:    */     {
/* 211:163 */       this.Charged = true;
/* 212:164 */       updateBlock();
/* 213:    */     }
/* 214:166 */     if ((this.Charged) && (this.Powered)) {
/* 215:166 */       this.Active = true;
/* 216:    */     }
/* 217:167 */     if (this.Active != act) {
/* 218:167 */       updateBlock();
/* 219:    */     }
/* 220:    */   }
/* 221:    */   
/* 222:    */   public void onTileTick()
/* 223:    */   {
/* 224:171 */     if (CoreLib.isClient(this.k)) {
/* 225:172 */       return;
/* 226:    */     }
/* 227:173 */     if (!this.Powered)
/* 228:    */     {
/* 229:174 */       this.Active = false;
/* 230:175 */       updateBlock();
/* 231:    */     }
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void a(bq tag)
/* 235:    */   {
/* 236:182 */     super.a(tag);
/* 237:183 */     this.cond.readFromNBT(tag);
/* 238:184 */     this.inbuf.readFromNBT(tag, "inb");
/* 239:185 */     this.outbuf.readFromNBT(tag, "outb");
/* 240:186 */     this.PumpTick = tag.c("ptk");
/* 241:    */   }
/* 242:    */   
/* 243:    */   public void b(bq tag)
/* 244:    */   {
/* 245:190 */     super.b(tag);
/* 246:191 */     this.cond.writeToNBT(tag);
/* 247:192 */     this.inbuf.writeToNBT(tag, "inb");
/* 248:193 */     this.outbuf.writeToNBT(tag, "outb");
/* 249:194 */     tag.a("ptk", this.PumpTick);
/* 250:    */   }
/* 251:    */   
/* 252:197 */   public int ConMask = -1;
/* 253:198 */   public byte PumpTick = 0;
/* 254:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TilePump
 * JD-Core Version:    0.7.0.1
 */