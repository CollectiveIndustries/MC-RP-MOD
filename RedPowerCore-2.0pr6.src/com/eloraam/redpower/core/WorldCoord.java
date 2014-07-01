/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import java.util.Comparator;
/*   5:    */ 
/*   6:    */ public class WorldCoord
/*   7:    */   implements Comparable
/*   8:    */ {
/*   9:    */   public int x;
/*  10:    */   public int y;
/*  11:    */   public int z;
/*  12:    */   
/*  13:    */   public WorldCoord(int xi, int yi, int zi)
/*  14:    */   {
/*  15:  9 */     this.x = xi;this.y = yi;this.z = zi;
/*  16:    */   }
/*  17:    */   
/*  18:    */   public WorldCoord(any te)
/*  19:    */   {
/*  20: 13 */     this.x = te.l;this.y = te.m;this.z = te.n;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public WorldCoord copy()
/*  24:    */   {
/*  25: 17 */     return new WorldCoord(this.x, this.y, this.z);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public WorldCoord coordStep(int dir)
/*  29:    */   {
/*  30: 21 */     switch (dir)
/*  31:    */     {
/*  32:    */     case 0: 
/*  33: 22 */       return new WorldCoord(this.x, this.y - 1, this.z);
/*  34:    */     case 1: 
/*  35: 23 */       return new WorldCoord(this.x, this.y + 1, this.z);
/*  36:    */     case 2: 
/*  37: 24 */       return new WorldCoord(this.x, this.y, this.z - 1);
/*  38:    */     case 3: 
/*  39: 25 */       return new WorldCoord(this.x, this.y, this.z + 1);
/*  40:    */     case 4: 
/*  41: 26 */       return new WorldCoord(this.x - 1, this.y, this.z);
/*  42:    */     }
/*  43: 27 */     return new WorldCoord(this.x + 1, this.y, this.z);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void set(WorldCoord wc)
/*  47:    */   {
/*  48: 32 */     this.x = wc.x;this.y = wc.y;this.z = wc.z;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public int squareDist(int xi, int yi, int zi)
/*  52:    */   {
/*  53: 36 */     return (xi - this.x) * (xi - this.x) + (yi - this.y) * (yi - this.y) + (zi - this.z) * (zi - this.z);
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void step(int dir)
/*  57:    */   {
/*  58: 40 */     switch (dir)
/*  59:    */     {
/*  60:    */     case 0: 
/*  61: 41 */       this.y -= 1; break;
/*  62:    */     case 1: 
/*  63: 42 */       this.y += 1; break;
/*  64:    */     case 2: 
/*  65: 43 */       this.z -= 1; break;
/*  66:    */     case 3: 
/*  67: 44 */       this.z += 1; break;
/*  68:    */     case 4: 
/*  69: 45 */       this.x -= 1; break;
/*  70:    */     default: 
/*  71: 46 */       this.x += 1;
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void step(int dir, int dist)
/*  76:    */   {
/*  77: 51 */     switch (dir)
/*  78:    */     {
/*  79:    */     case 0: 
/*  80: 52 */       this.y -= dist; break;
/*  81:    */     case 1: 
/*  82: 53 */       this.y += dist; break;
/*  83:    */     case 2: 
/*  84: 54 */       this.z -= dist; break;
/*  85:    */     case 3: 
/*  86: 55 */       this.z += dist; break;
/*  87:    */     case 4: 
/*  88: 56 */       this.x -= dist; break;
/*  89:    */     default: 
/*  90: 57 */       this.x += dist;
/*  91:    */     }
/*  92:    */   }
/*  93:    */   
/*  94:    */   public static int getRightDir(int dir)
/*  95:    */   {
/*  96: 62 */     if (dir < 2) {
/*  97: 62 */       return dir;
/*  98:    */     }
/*  99: 63 */     switch (dir)
/* 100:    */     {
/* 101:    */     case 0: 
/* 102: 64 */       return 0;
/* 103:    */     case 1: 
/* 104: 65 */       return 1;
/* 105:    */     case 2: 
/* 106: 66 */       return 4;
/* 107:    */     case 3: 
/* 108: 67 */       return 5;
/* 109:    */     case 4: 
/* 110: 68 */       return 3;
/* 111:    */     }
/* 112: 69 */     return 2;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public static int getIndStepDir(int d1, int d2)
/* 116:    */   {
/* 117: 74 */     switch (d1)
/* 118:    */     {
/* 119:    */     case 0: 
/* 120: 75 */       return d2 + 2;
/* 121:    */     case 1: 
/* 122: 76 */       return d2 + 2;
/* 123:    */     case 2: 
/* 124: 77 */       return d2 + (d2 & 0x2);
/* 125:    */     case 3: 
/* 126: 78 */       return d2 + (d2 & 0x2);
/* 127:    */     case 4: 
/* 128: 79 */       return d2;
/* 129:    */     }
/* 130: 80 */     return d2;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void indStep(int d1, int d2)
/* 134:    */   {
/* 135: 86 */     step(d1);
/* 136: 87 */     step(getIndStepDir(d1, d2));
/* 137:    */   }
/* 138:    */   
/* 139:    */   public int hashCode()
/* 140:    */   {
/* 141: 91 */     int c1 = Integer.valueOf(this.x).hashCode();
/* 142: 92 */     int c2 = Integer.valueOf(this.y).hashCode();
/* 143: 93 */     int c3 = Integer.valueOf(this.z).hashCode();
/* 144: 94 */     return c1 + 31 * (c2 + 31 * c3);
/* 145:    */   }
/* 146:    */   
/* 147:    */   public int compareTo(Object obj)
/* 148:    */   {
/* 149: 98 */     WorldCoord wc = (WorldCoord)obj;
/* 150: 99 */     if (this.x == wc.x)
/* 151:    */     {
/* 152:100 */       if (this.y == wc.y) {
/* 153:101 */         return this.z - wc.z;
/* 154:    */       }
/* 155:103 */       return this.y - wc.y;
/* 156:    */     }
/* 157:106 */     return this.x - wc.x;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public boolean equals(Object obj)
/* 161:    */   {
/* 162:111 */     if (!(obj instanceof WorldCoord)) {
/* 163:111 */       return false;
/* 164:    */     }
/* 165:112 */     WorldCoord wc = (WorldCoord)obj;
/* 166:    */     
/* 167:114 */     return (this.x == wc.x) && (this.y == wc.y) && (this.z == wc.z);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public static class WCComparator
/* 171:    */     implements Comparator
/* 172:    */   {
/* 173:    */     int dir;
/* 174:    */     
/* 175:    */     private WCComparator(int d)
/* 176:    */     {
/* 177:119 */       this.dir = d;
/* 178:    */     }
/* 179:    */     
/* 180:    */     public int compare(Object a, Object b)
/* 181:    */     {
/* 182:123 */       WorldCoord wa = (WorldCoord)a;
/* 183:124 */       WorldCoord wb = (WorldCoord)b;
/* 184:125 */       switch (this.dir)
/* 185:    */       {
/* 186:    */       case 0: 
/* 187:126 */         return wa.y - wb.y;
/* 188:    */       case 1: 
/* 189:127 */         return wb.y - wa.y;
/* 190:    */       case 2: 
/* 191:128 */         return wa.z - wb.z;
/* 192:    */       case 3: 
/* 193:129 */         return wb.z - wa.z;
/* 194:    */       case 4: 
/* 195:130 */         return wa.x - wb.x;
/* 196:    */       }
/* 197:131 */       return wb.x - wa.x;
/* 198:    */     }
/* 199:    */   }
/* 200:    */   
/* 201:    */   public static Comparator getCompareDir(int dir)
/* 202:    */   {
/* 203:138 */     return new WCComparator(dir, null);
/* 204:    */   }
/* 205:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.WorldCoord
 * JD-Core Version:    0.7.0.1
 */