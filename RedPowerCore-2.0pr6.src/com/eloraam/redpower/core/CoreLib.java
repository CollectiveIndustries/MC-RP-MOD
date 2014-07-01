/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import agi;
/*   4:    */ import agk;
/*   5:    */ import amq;
/*   6:    */ import amu;
/*   7:    */ import anr;
/*   8:    */ import any;
/*   9:    */ import aoe;
/*  10:    */ import aoh;
/*  11:    */ import aoj;
/*  12:    */ import cu;
/*  13:    */ import iq;
/*  14:    */ import iv;
/*  15:    */ import java.lang.reflect.InvocationTargetException;
/*  16:    */ import java.lang.reflect.Method;
/*  17:    */ import java.util.Comparator;
/*  18:    */ import java.util.Random;
/*  19:    */ import java.util.TreeMap;
/*  20:    */ import md;
/*  21:    */ import net.minecraftforge.oredict.OreDictionary;
/*  22:    */ import px;
/*  23:    */ import qx;
/*  24:    */ import up;
/*  25:    */ import ur;
/*  26:    */ import yc;
/*  27:    */ import ym;
/*  28:    */ import zz;
/*  29:    */ 
/*  30:    */ public class CoreLib
/*  31:    */ {
/*  32:    */   public static boolean isClient(yc world)
/*  33:    */   {
/*  34: 28 */     return world.I;
/*  35:    */   }
/*  36:    */   
/*  37:    */   @Deprecated
/*  38:    */   void initModule(String name)
/*  39:    */   {
/*  40:    */     Class cl;
/*  41:    */     try
/*  42:    */     {
/*  43: 36 */       cl = Class.forName(name);
/*  44:    */     }
/*  45:    */     catch (ClassNotFoundException e)
/*  46:    */     {
/*  47:    */       return;
/*  48:    */     }
/*  49:    */     Method mth;
/*  50:    */     try
/*  51:    */     {
/*  52: 41 */       mth = cl.getDeclaredMethod("initialize", new Class[0]);
/*  53:    */     }
/*  54:    */     catch (NoSuchMethodException e)
/*  55:    */     {
/*  56: 43 */       return;
/*  57:    */     }
/*  58:    */     try
/*  59:    */     {
/*  60: 46 */       mth.invoke(null, new Object[0]);
/*  61:    */     }
/*  62:    */     catch (IllegalAccessException e) {}catch (InvocationTargetException e) {}
/*  63:    */   }
/*  64:    */   
/*  65:    */   public static Object getTileEntity(ym iba, int i, int j, int k, Class cl)
/*  66:    */   {
/*  67: 56 */     any tr = iba.q(i, j, k);
/*  68: 57 */     if (!cl.isInstance(tr)) {
/*  69: 57 */       return null;
/*  70:    */     }
/*  71: 58 */     return tr;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public static Object getTileEntity(ym iba, WorldCoord wc, Class cl)
/*  75:    */   {
/*  76: 63 */     any tr = iba.q(wc.x, wc.y, wc.z);
/*  77: 64 */     if (!cl.isInstance(tr)) {
/*  78: 64 */       return null;
/*  79:    */     }
/*  80: 65 */     return tr;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static Object getGuiTileEntity(yc world, int i, int j, int k, Class cl)
/*  84:    */   {
/*  85: 70 */     if (world.I) {
/*  86:    */       try
/*  87:    */       {
/*  88: 72 */         return cl.newInstance();
/*  89:    */       }
/*  90:    */       catch (InstantiationException e)
/*  91:    */       {
/*  92: 74 */         return null;
/*  93:    */       }
/*  94:    */       catch (IllegalAccessException e)
/*  95:    */       {
/*  96: 76 */         return null;
/*  97:    */       }
/*  98:    */     }
/*  99: 79 */     any tr = world.q(i, j, k);
/* 100: 80 */     if (!cl.isInstance(tr)) {
/* 101: 80 */       return null;
/* 102:    */     }
/* 103: 81 */     return tr;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public static void markBlockDirty(yc world, int i, int j, int k)
/* 107:    */   {
/* 108: 86 */     if (world.f(i, j, k)) {
/* 109: 87 */       world.d(i, k).e();
/* 110:    */     }
/* 111:    */   }
/* 112:    */   
/* 113:    */   public static int compareItemStack(ur a, ur b)
/* 114:    */   {
/* 115: 91 */     if (a.c != b.c) {
/* 116: 92 */       return a.c - b.c;
/* 117:    */     }
/* 118: 93 */     if (a.j() == b.j()) {
/* 119: 94 */       return 0;
/* 120:    */     }
/* 121: 95 */     if (a.b().l()) {
/* 122: 96 */       return a.j() - b.j();
/* 123:    */     }
/* 124: 98 */     return 0;
/* 125:    */   }
/* 126:    */   
/* 127:101 */   public static Comparator itemStackComparator = new Comparator()
/* 128:    */   {
/* 129:    */     public int compare(ur o1, ur o2)
/* 130:    */     {
/* 131:104 */       return CoreLib.compareItemStack(o1, o2);
/* 132:    */     }
/* 133:    */   };
/* 134:109 */   private static TreeMap oreMap = new TreeMap(itemStackComparator);
/* 135:    */   
/* 136:    */   static void registerOre(String name, ur ore)
/* 137:    */   {
/* 138:112 */     oreMap.put(ore, name);
/* 139:    */   }
/* 140:    */   
/* 141:    */   public static void readOres()
/* 142:    */   {
/* 143:    */     String st;
/* 144:115 */     for (st : ) {
/* 145:116 */       for (ur ist : OreDictionary.getOres(st)) {
/* 146:117 */         registerOre(st, ist);
/* 147:    */       }
/* 148:    */     }
/* 149:    */   }
/* 150:    */   
/* 151:    */   public static String getOreClass(ur ist)
/* 152:    */   {
/* 153:123 */     String st = (String)oreMap.get(ist);
/* 154:124 */     if (st != null) {
/* 155:124 */       return st;
/* 156:    */     }
/* 157:125 */     ist = new ur(ist.c, 1, -1);
/* 158:126 */     return (String)oreMap.get(ist);
/* 159:    */   }
/* 160:    */   
/* 161:    */   public static boolean matchItemStackOre(ur a, ur b)
/* 162:    */   {
/* 163:130 */     String s1 = getOreClass(a);
/* 164:131 */     String s2 = getOreClass(b);
/* 165:132 */     if ((s1 != null) && (s2 != null) && (s1.equals(s2))) {
/* 166:132 */       return true;
/* 167:    */     }
/* 168:133 */     return compareItemStack(a, b) == 0;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public static void dropItem(yc world, int i, int j, int k, ur ist)
/* 172:    */   {
/* 173:138 */     if (isClient(world)) {
/* 174:138 */       return;
/* 175:    */     }
/* 176:139 */     double d = 0.7D;
/* 177:140 */     double x = world.t.nextFloat() * d + (1.0D - d) * 0.5D;
/* 178:141 */     double y = world.t.nextFloat() * d + (1.0D - d) * 0.5D;
/* 179:142 */     double z = world.t.nextFloat() * d + (1.0D - d) * 0.5D;
/* 180:143 */     px item = new px(world, i + x, j + y, k + z, ist);
/* 181:144 */     item.b = 10;
/* 182:145 */     world.d(item);
/* 183:    */   }
/* 184:    */   
/* 185:    */   public static ur copyStack(ur ist, int n)
/* 186:    */   {
/* 187:149 */     return new ur(ist.c, n, ist.j());
/* 188:    */   }
/* 189:    */   
/* 190:153 */   public static String[] rawColorNames = { "white", "orange", "magenta", "lightBlue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black" };
/* 191:160 */   public static String[] enColorNames = { "White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
/* 192:167 */   public static int[] paintColors = { 16777215, 16744448, 16711935, 7110911, 16776960, 65280, 16737408, 5460819, 9671571, 65535, 8388863, 255, 5187328, 32768, 16711680, 2039583 };
/* 193:177 */   public static final agi materialRedpower = new agi(agk.o);
/* 194:    */   
/* 195:    */   public static int rotToSide(int r)
/* 196:    */   {
/* 197:182 */     switch (r)
/* 198:    */     {
/* 199:    */     case 0: 
/* 200:183 */       return 5;
/* 201:    */     case 1: 
/* 202:184 */       return 3;
/* 203:    */     case 2: 
/* 204:185 */       return 4;
/* 205:    */     }
/* 206:186 */     return 2;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public static aoh retraceBlock(yc world, md ent, int i, int j, int k)
/* 210:    */   {
/* 211:193 */     aoj org = aoj.a(ent.t, ent.u + 1.62D - ent.M, ent.v);
/* 212:    */     
/* 213:    */ 
/* 214:196 */     aoj vec = ent.i(1.0F);
/* 215:197 */     aoj end = org.c(vec.c * 5.0D, vec.d * 5.0D, vec.e * 5.0D);
/* 216:    */     
/* 217:199 */     amq bl = amq.p[world.a(i, j, k)];
/* 218:200 */     if (bl == null) {
/* 219:200 */       return null;
/* 220:    */     }
/* 221:201 */     return bl.a(world, i, j, k, org, end);
/* 222:    */   }
/* 223:    */   
/* 224:    */   public static aoh traceBlock(qx player)
/* 225:    */   {
/* 226:205 */     aoj org = aoj.a(player.t, player.u + 1.62D - player.M, player.v);
/* 227:    */     
/* 228:    */ 
/* 229:208 */     aoj vec = player.i(1.0F);
/* 230:209 */     aoj end = org.c(vec.c * 5.0D, vec.d * 5.0D, vec.e * 5.0D);
/* 231:    */     
/* 232:211 */     return player.p.a(org, end);
/* 233:    */   }
/* 234:    */   
/* 235:    */   public static void placeNoise(yc world, int i, int j, int k, int bid)
/* 236:    */   {
/* 237:217 */     amq block = amq.p[bid];
/* 238:218 */     world.a(i + 0.5F, j + 0.5F, k + 0.5F, "step.stone", (block.cz.c() + 1.0F) / 2.0F, block.cz.d() * 0.8F);
/* 239:    */   }
/* 240:    */   
/* 241:    */   public static int getBurnTime(ur ist)
/* 242:    */   {
/* 243:226 */     return anr.a(ist);
/* 244:    */   }
/* 245:    */   
/* 246:    */   public static double getAverageEdgeLength(aoe aabb)
/* 247:    */   {
/* 248:243 */     double d = aabb.d - aabb.a;
/* 249:244 */     double d1 = aabb.e - aabb.b;
/* 250:245 */     double d2 = aabb.f - aabb.c;
/* 251:246 */     return (d + d1 + d2) / 3.0D;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public static void writeChat(qx pl, String str)
/* 255:    */   {
/* 256:251 */     if (!(pl instanceof iq)) {
/* 257:251 */       return;
/* 258:    */     }
/* 259:252 */     iq emp = (iq)pl;
/* 260:253 */     emp.a.b(new cu(str));
/* 261:    */   }
/* 262:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CoreLib
 * JD-Core Version:    0.7.0.1
 */