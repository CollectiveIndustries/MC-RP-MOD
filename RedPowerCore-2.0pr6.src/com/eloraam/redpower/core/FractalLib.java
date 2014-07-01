/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ public class FractalLib
/*   4:    */ {
/*   5:    */   public static long hash64shift(long key)
/*   6:    */   {
/*   7:  5 */     key = (key ^ 0xFFFFFFFF) + (key << 21);
/*   8:  6 */     key ^= key >>> 24;
/*   9:  7 */     key = key + (key << 3) + (key << 8);
/*  10:  8 */     key ^= key >>> 14;
/*  11:  9 */     key = key + (key << 2) + (key << 4);
/*  12: 10 */     key ^= key >>> 28;
/*  13: 11 */     key += (key << 31);
/*  14: 12 */     return key;
/*  15:    */   }
/*  16:    */   
/*  17:    */   public static double hashFloat(long key)
/*  18:    */   {
/*  19: 16 */     long f = hash64shift(key);
/*  20: 17 */     return Double.longBitsToDouble(0x0 | f & 0xFFFFFFFF) - 1.0D;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public static double noise1D(long seed, double pos, float lac, int octave)
/*  24:    */   {
/*  25: 23 */     double tr = 0.5D;
/*  26: 24 */     double scale = 1 << octave;
/*  27: 25 */     for (int i = 0; i < octave; i++)
/*  28:    */     {
/*  29: 26 */       double p = pos * scale;
/*  30: 27 */       long pint = Math.floor(p);
/*  31: 28 */       double m1 = hashFloat(seed + pint);
/*  32: 29 */       double m2 = hashFloat(seed + pint + 1L);
/*  33: 30 */       p -= Math.floor(p);
/*  34: 31 */       double v = 0.5D + 0.5D * Math.cos(3.141592653589793D * p);
/*  35: 32 */       v = v * m1 + (1.0D - v) * m2;
/*  36:    */       
/*  37: 34 */       tr = (1.0F - lac) * tr + lac * v;
/*  38: 35 */       scale *= 0.5D;
/*  39:    */     }
/*  40: 37 */     return tr;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public static double perturbOld(long seed, float pos, float lac, int octave)
/*  44:    */   {
/*  45: 42 */     double tr = 0.0D;
/*  46: 43 */     double mscale = 1.0D;
/*  47: 44 */     double scale = 1.0D;
/*  48: 45 */     for (int i = 0; i < octave; i++)
/*  49:    */     {
/*  50: 46 */       long v = Math.floor(pos * scale);
/*  51: 47 */       long p = hash64shift(seed + v);
/*  52: 48 */       double mag = Double.longBitsToDouble(0x0 | p & 0xFFFFFFFF) - 1.0D;
/*  53:    */       
/*  54: 50 */       tr += mscale * mag * Math.sin(6.283185307179586D * pos * scale);
/*  55: 51 */       scale *= 2.0D;mscale *= lac;
/*  56:    */     }
/*  57: 53 */     return tr;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public static void fillVector(Vector3 v, Vector3 org, Vector3 dest, float pos, long seed)
/*  61:    */   {
/*  62: 58 */     double window = 4.0D * Math.sin(3.141592653589793D * pos);
/*  63: 59 */     v.x = (org.x + pos * pos * dest.x + window * perturbOld(seed, pos, 0.7F, 5));
/*  64: 60 */     v.y = (org.y + pos * dest.y + window * perturbOld(seed + 1L, pos, 0.7F, 5));
/*  65: 61 */     v.z = (org.z + pos * pos * dest.z + window * perturbOld(seed + 2L, pos, 0.7F, 5));
/*  66:    */   }
/*  67:    */   
/*  68:    */   public static int mdist(Vector3 a, Vector3 b)
/*  69:    */   {
/*  70: 65 */     return (int)(Math.abs(Math.floor(a.x) - Math.floor(b.x)) + Math.abs(Math.floor(a.y) - Math.floor(b.y)) + Math.abs(Math.floor(a.z) - Math.floor(b.z)));
/*  71:    */   }
/*  72:    */   
/*  73:    */   public static class BlockRay
/*  74:    */   {
/*  75:    */     private Vector3 p1;
/*  76:    */     private Vector3 p2;
/*  77:    */     private Vector3 dv;
/*  78:    */     public Vector3 enter;
/*  79:    */     public Vector3 exit;
/*  80:    */     public int xp;
/*  81:    */     public int yp;
/*  82:    */     public int zp;
/*  83:    */     public int dir;
/*  84:    */     public int face;
/*  85:    */     
/*  86:    */     public BlockRay(Vector3 s, Vector3 d)
/*  87:    */     {
/*  88: 72 */       this.p1 = new Vector3(s);
/*  89: 73 */       this.p2 = new Vector3(d);
/*  90: 74 */       this.dv = new Vector3(d);
/*  91: 75 */       this.dv.subtract(s);
/*  92: 76 */       this.exit = new Vector3(s);
/*  93: 77 */       this.enter = new Vector3();
/*  94:    */       
/*  95: 79 */       this.xp = ((int)Math.floor(s.x));
/*  96: 80 */       this.yp = ((int)Math.floor(s.y));
/*  97: 81 */       this.zp = ((int)Math.floor(s.z));
/*  98:    */       
/*  99: 83 */       this.dir = 0;
/* 100: 84 */       this.dir |= (d.x > s.x ? 4 : 0);
/* 101: 85 */       this.dir |= (d.y > s.y ? 1 : 0);
/* 102: 86 */       this.dir |= (d.z > s.z ? 2 : 0);
/* 103:    */     }
/* 104:    */     
/* 105:    */     public void set(Vector3 s, Vector3 d)
/* 106:    */     {
/* 107: 90 */       this.p1.set(s);this.p2.set(d);
/* 108: 91 */       this.dv.set(d);this.dv.subtract(s);
/* 109: 92 */       this.exit.set(s);
/* 110:    */       
/* 111: 94 */       this.xp = ((int)Math.floor(s.x));
/* 112: 95 */       this.yp = ((int)Math.floor(s.y));
/* 113: 96 */       this.zp = ((int)Math.floor(s.z));
/* 114:    */       
/* 115: 98 */       this.dir = 0;
/* 116: 99 */       this.dir |= (d.x > s.x ? 4 : 0);
/* 117:100 */       this.dir |= (d.y > s.y ? 1 : 0);
/* 118:101 */       this.dir |= (d.z > s.z ? 2 : 0);
/* 119:    */     }
/* 120:    */     
/* 121:    */     boolean step()
/* 122:    */     {
/* 123:106 */       double bp = 1.0D;
/* 124:107 */       int sd = -1;
/* 125:109 */       if (this.dv.x != 0.0D)
/* 126:    */       {
/* 127:110 */         int x = this.xp;
/* 128:111 */         if ((this.dir & 0x4) > 0) {
/* 129:111 */           x++;
/* 130:    */         }
/* 131:112 */         double d = (x - this.p1.x) / this.dv.x;
/* 132:113 */         if ((d >= 0.0D) && (d <= bp))
/* 133:    */         {
/* 134:114 */           bp = d;
/* 135:115 */           sd = (this.dir & 0x4) > 0 ? 4 : 5;
/* 136:    */         }
/* 137:    */       }
/* 138:118 */       if (this.dv.y != 0.0D)
/* 139:    */       {
/* 140:119 */         int y = this.yp;
/* 141:120 */         if ((this.dir & 0x1) > 0) {
/* 142:120 */           y++;
/* 143:    */         }
/* 144:121 */         double d = (y - this.p1.y) / this.dv.y;
/* 145:122 */         if ((d >= 0.0D) && (d <= bp))
/* 146:    */         {
/* 147:123 */           bp = d;
/* 148:124 */           sd = (this.dir & 0x1) > 0 ? 0 : 1;
/* 149:    */         }
/* 150:    */       }
/* 151:127 */       if (this.dv.z != 0.0D)
/* 152:    */       {
/* 153:128 */         int z = this.zp;
/* 154:129 */         if ((this.dir & 0x2) > 0) {
/* 155:129 */           z++;
/* 156:    */         }
/* 157:130 */         double d = (z - this.p1.z) / this.dv.z;
/* 158:131 */         if ((d >= 0.0D) && (d <= bp))
/* 159:    */         {
/* 160:132 */           bp = d;
/* 161:133 */           sd = (this.dir & 0x2) > 0 ? 2 : 3;
/* 162:    */         }
/* 163:    */       }
/* 164:136 */       this.face = sd;
/* 165:137 */       switch (sd)
/* 166:    */       {
/* 167:    */       case 0: 
/* 168:138 */         this.yp += 1; break;
/* 169:    */       case 1: 
/* 170:139 */         this.yp -= 1; break;
/* 171:    */       case 2: 
/* 172:140 */         this.zp += 1; break;
/* 173:    */       case 3: 
/* 174:141 */         this.zp -= 1; break;
/* 175:    */       case 4: 
/* 176:142 */         this.xp += 1; break;
/* 177:    */       case 5: 
/* 178:143 */         this.xp -= 1;
/* 179:    */       }
/* 180:145 */       this.enter.set(this.exit);
/* 181:146 */       this.exit.set(this.dv);
/* 182:147 */       this.exit.multiply(bp);
/* 183:148 */       this.exit.add(this.p1);
/* 184:149 */       return bp >= 1.0D;
/* 185:    */     }
/* 186:    */   }
/* 187:    */   
/* 188:    */   public static class BlockSnake
/* 189:    */   {
/* 190:    */     public BlockSnake(Vector3 o, Vector3 d, long s)
/* 191:    */     {
/* 192:158 */       this.org = new Vector3(o);this.dest = new Vector3(d);
/* 193:159 */       this.fracs = new Vector3(o);
/* 194:160 */       this.frace = new Vector3();
/* 195:161 */       this.seed = s;
/* 196:    */       
/* 197:163 */       FractalLib.fillVector(this.frace, this.org, this.dest, 0.125F, this.seed);
/* 198:164 */       this.ray = new FractalLib.BlockRay(this.fracs, this.frace);
/* 199:    */     }
/* 200:    */     
/* 201:    */     public boolean iterate()
/* 202:    */     {
/* 203:168 */       if (this.fep == -1)
/* 204:    */       {
/* 205:168 */         this.fep += 1;return true;
/* 206:    */       }
/* 207:169 */       if (!this.ray.step()) {
/* 208:169 */         return true;
/* 209:    */       }
/* 210:170 */       if (this.fep == 8) {
/* 211:170 */         return false;
/* 212:    */       }
/* 213:172 */       this.fracs.set(this.frace);
/* 214:173 */       FractalLib.fillVector(this.frace, this.org, this.dest, this.fep / 8.0F, this.seed);
/* 215:174 */       this.ray.set(this.fracs, this.frace);
/* 216:175 */       this.fep += 1;
/* 217:176 */       return true;
/* 218:    */     }
/* 219:    */     
/* 220:    */     public Vector3 get()
/* 221:    */     {
/* 222:180 */       return new Vector3(this.ray.xp, this.ray.yp, this.ray.zp);
/* 223:    */     }
/* 224:    */     
/* 225:183 */     int fep = -1;
/* 226:    */     FractalLib.BlockRay ray;
/* 227:    */     Vector3 org;
/* 228:    */     Vector3 dest;
/* 229:    */     Vector3 fracs;
/* 230:    */     Vector3 frace;
/* 231:    */     long seed;
/* 232:    */   }
/* 233:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.FractalLib
 * JD-Core Version:    0.7.0.1
 */