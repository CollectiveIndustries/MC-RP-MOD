/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import bt;
/*   6:    */ import by;
/*   7:    */ import yc;
/*   8:    */ 
/*   9:    */ public abstract class BluePowerConductor
/*  10:    */ {
/*  11:    */   public abstract any getParent();
/*  12:    */   
/*  13:    */   public abstract double getInvCap();
/*  14:    */   
/*  15:    */   public int getChargeScaled(int i)
/*  16:    */   {
/*  17: 13 */     return 0;
/*  18:    */   }
/*  19:    */   
/*  20:    */   public int getFlowScaled(int i)
/*  21:    */   {
/*  22: 14 */     return 0;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public double getResistance()
/*  26:    */   {
/*  27: 16 */     return 0.01D;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public double getIndScale()
/*  31:    */   {
/*  32: 17 */     return 0.07000000000000001D;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public double getCondParallel()
/*  36:    */   {
/*  37: 18 */     return 0.5D;
/*  38:    */   }
/*  39:    */   
/*  40: 21 */   private static int[] dirmap = { 0, 1, 2, 3, 4, 5, 6, 7, 11, 14, 18, 23 };
/*  41:    */   
/*  42:    */   public void recache(int conm, int econm)
/*  43:    */   {
/*  44: 23 */     int imo = 0;
/*  45: 25 */     for (int a = 0; a < 3; a++) {
/*  46: 26 */       if ((conm & RedPowerLib.getConDirMask(a * 2)) > 0) {
/*  47: 27 */         imo |= 1 << a;
/*  48:    */       }
/*  49:    */     }
/*  50: 29 */     for (int a = 0; a < 12; a++) {
/*  51: 30 */       if ((econm & 1 << dirmap[a]) > 0) {
/*  52: 31 */         imo |= 8 << a;
/*  53:    */       }
/*  54:    */     }
/*  55: 33 */     if (this.imask == imo) {
/*  56: 33 */       return;
/*  57:    */     }
/*  58: 35 */     double[] c2 = new double[Integer.bitCount(imo)];
/*  59:    */     
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68: 45 */     int s = 0;int d = 0;
/*  69: 46 */     for (int a = 0; a < 15; a++)
/*  70:    */     {
/*  71: 47 */       int m = 1 << a;
/*  72: 48 */       double v = 0.0D;
/*  73: 49 */       if ((this.imask & m) > 0) {
/*  74: 49 */         v = this.currents[(s++)];
/*  75:    */       }
/*  76: 50 */       if ((imo & m) > 0) {
/*  77: 50 */         c2[(d++)] = v;
/*  78:    */       }
/*  79:    */     }
/*  80: 52 */     this.currents = c2;
/*  81: 53 */     this.imask = imo;
/*  82:    */   }
/*  83:    */   
/*  84:    */   protected void computeVoltage()
/*  85:    */   {
/*  86: 60 */     this.Itot = (0.5D * this.It1);this.It1 = 0.0D;
/*  87:    */     
/*  88:    */ 
/*  89: 63 */     this.Vcap += 0.05D * this.Icap * getInvCap();this.Icap = 0.0D;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public double getVoltage()
/*  93:    */   {
/*  94: 68 */     long lt = getParent().k.G();
/*  95: 69 */     if ((lt & 0xFFFF) == this.lastTick) {
/*  96: 69 */       return this.Vcap;
/*  97:    */     }
/*  98: 70 */     this.lastTick = ((int)(lt & 0xFFFF));
/*  99:    */     
/* 100: 72 */     computeVoltage();
/* 101:    */     
/* 102: 74 */     return this.Vcap;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void applyCurrent(double Iin)
/* 106:    */   {
/* 107: 78 */     getVoltage();
/* 108: 79 */     this.Icap += Iin;
/* 109: 80 */     this.It1 += Math.abs(Iin);
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void drawPower(double P)
/* 113:    */   {
/* 114: 84 */     double p1 = this.Vcap * this.Vcap - 0.1D * P * getInvCap();
/* 115: 85 */     double t = p1 < 0.0D ? 0.0D : Math.sqrt(p1) - this.Vcap;
/* 116: 86 */     applyDirect(20.0D * t / getInvCap());
/* 117:    */   }
/* 118:    */   
/* 119:    */   public double getEnergy(double vthresh)
/* 120:    */   {
/* 121: 96 */     double d = getVoltage();
/* 122: 97 */     double tr = 0.5D * (d * d - vthresh * vthresh) / getInvCap();
/* 123: 98 */     return tr < 0.0D ? 0.0D : tr;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public void applyPower(double P)
/* 127:    */   {
/* 128:102 */     double t = Math.sqrt(this.Vcap * this.Vcap + 0.1D * P * getInvCap()) - this.Vcap;
/* 129:103 */     applyDirect(20.0D * t / getInvCap());
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void applyDirect(double Iin)
/* 133:    */   {
/* 134:122 */     applyCurrent(Iin);
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void iterate()
/* 138:    */   {
/* 139:128 */     any parent = getParent();
/* 140:129 */     yc world = parent.k;
/* 141:130 */     getVoltage();
/* 142:    */     
/* 143:132 */     int dm = this.imask;
/* 144:133 */     int s = 0;
/* 145:134 */     while (dm > 0)
/* 146:    */     {
/* 147:135 */       int d = Integer.numberOfTrailingZeros(dm);
/* 148:136 */       dm &= (1 << d ^ 0xFFFFFFFF);
/* 149:    */       
/* 150:    */ 
/* 151:139 */       WorldCoord wc = new WorldCoord(parent);
/* 152:    */       int facing;
/* 153:140 */       if (d < 3)
/* 154:    */       {
/* 155:141 */         int facing = d * 2;
/* 156:142 */         wc.step(facing);
/* 157:    */       }
/* 158:    */       else
/* 159:    */       {
/* 160:144 */         int n = dirmap[(d - 3)];
/* 161:145 */         wc.step(n >> 2);
/* 162:146 */         facing = WorldCoord.getIndStepDir(n >> 2, n & 0x3);
/* 163:147 */         wc.step(facing);
/* 164:    */       }
/* 165:150 */       IBluePowerConnectable ibc = (IBluePowerConnectable)CoreLib.getTileEntity(world, wc, IBluePowerConnectable.class);
/* 166:152 */       if (ibc == null)
/* 167:    */       {
/* 168:152 */         s++;
/* 169:    */       }
/* 170:    */       else
/* 171:    */       {
/* 172:153 */         BluePowerConductor bpc = ibc.getBlueConductor(facing ^ 0x1);
/* 173:    */         
/* 174:155 */         double r = getResistance() + bpc.getResistance();
/* 175:156 */         double I = this.currents[s];
/* 176:157 */         double V = this.Vcap - bpc.getVoltage();
/* 177:158 */         this.currents[s] += (V - I * r) * getIndScale();
/* 178:159 */         I += V * getCondParallel();
/* 179:    */         
/* 180:    */ 
/* 181:162 */         applyCurrent(-I);
/* 182:163 */         bpc.applyCurrent(I);
/* 183:164 */         s++;
/* 184:    */       }
/* 185:    */     }
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void readFromNBT(bq tag)
/* 189:    */   {
/* 190:169 */     this.imask = tag.e("bpim");
/* 191:170 */     int l = Integer.bitCount(this.imask);
/* 192:171 */     this.currents = new double[l];
/* 193:172 */     by clist = tag.m("bpil");
/* 194:174 */     if (clist.c() != l) {
/* 195:174 */       return;
/* 196:    */     }
/* 197:175 */     for (int i = 0; i < l; i++)
/* 198:    */     {
/* 199:176 */       bt val = (bt)clist.b(i);
/* 200:177 */       this.currents[i] = val.a;
/* 201:    */     }
/* 202:180 */     this.Vcap = tag.h("vcap");
/* 203:181 */     this.Icap = tag.h("icap");
/* 204:182 */     this.Veff = tag.h("veff");
/* 205:183 */     this.It1 = tag.h("it1");
/* 206:184 */     this.Itot = tag.h("itot");
/* 207:185 */     this.lastTick = tag.e("ltk");
/* 208:    */   }
/* 209:    */   
/* 210:    */   public void writeToNBT(bq tag)
/* 211:    */   {
/* 212:190 */     tag.a("bpim", this.imask);
/* 213:191 */     int l = Integer.bitCount(this.imask);
/* 214:    */     
/* 215:193 */     by clist = new by();
/* 216:194 */     for (int i = 0; i < l; i++)
/* 217:    */     {
/* 218:195 */       bt val = new bt(null, this.currents[i]);
/* 219:196 */       clist.a(val);
/* 220:    */     }
/* 221:198 */     tag.a("bpil", clist);
/* 222:    */     
/* 223:200 */     tag.a("vcap", this.Vcap);
/* 224:201 */     tag.a("icap", this.Icap);
/* 225:202 */     tag.a("veff", this.Veff);
/* 226:203 */     tag.a("it1", this.It1);
/* 227:204 */     tag.a("itot", this.Itot);
/* 228:205 */     tag.a("ltk", this.lastTick);
/* 229:    */   }
/* 230:    */   
/* 231:208 */   int imask = 0;
/* 232:    */   double[] currents;
/* 233:210 */   public double Vcap = 0.0D;
/* 234:210 */   public double Icap = 0.0D;
/* 235:210 */   public double Veff = 0.0D;
/* 236:211 */   int lastTick = 0;
/* 237:212 */   public double It1 = 0.0D;
/* 238:212 */   public double Itot = 0.0D;
/* 239:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.BluePowerConductor
 * JD-Core Version:    0.7.0.1
 */