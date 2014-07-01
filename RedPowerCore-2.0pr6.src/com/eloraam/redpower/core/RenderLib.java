/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import baz;
/*   5:    */ import java.lang.reflect.Constructor;
/*   6:    */ import net.minecraftforge.client.ForgeHooksClient;
/*   7:    */ 
/*   8:    */ public class RenderLib
/*   9:    */ {
/*  10:    */   public static void bindTexture(String tex, int subid)
/*  11:    */   {
/*  12: 16 */     ForgeHooksClient.bindTexture(tex, subid);
/*  13:    */   }
/*  14:    */   
/*  15:    */   public static void bindTexture(String tex)
/*  16:    */   {
/*  17: 20 */     ForgeHooksClient.bindTexture(tex, 0);
/*  18:    */   }
/*  19:    */   
/*  20:    */   public static void unbindTexture() {}
/*  21:    */   
/*  22:    */   public static void setIntredTexture()
/*  23:    */   {
/*  24: 29 */     bindTexture("/eloraam/logic/logic1.png");
/*  25:    */   }
/*  26:    */   
/*  27:    */   public static void setRedPowerTexture()
/*  28:    */   {
/*  29: 33 */     bindTexture("/eloraam/wiring/redpower1.png");
/*  30:    */   }
/*  31:    */   
/*  32:    */   public static void setDefaultTexture() {}
/*  33:    */   
/*  34:    */   public static void renderSpecialLever(Vector3 pos, Quat rot, int tex)
/*  35:    */   {
/*  36: 41 */     int k1 = (tex & 0xF) << 4;
/*  37: 42 */     int l1 = tex & 0xF0;
/*  38:    */     
/*  39: 44 */     Vector3[] pl = new Vector3[8];
/*  40: 45 */     float f8 = 0.0625F;
/*  41: 46 */     float f9 = 0.0625F;
/*  42: 47 */     float f10 = 0.375F;
/*  43:    */     
/*  44: 49 */     pl[0] = new Vector3(-f8, 0.0D, -f9);
/*  45: 50 */     pl[1] = new Vector3(f8, 0.0D, -f9);
/*  46: 51 */     pl[2] = new Vector3(f8, 0.0D, f9);
/*  47: 52 */     pl[3] = new Vector3(-f8, 0.0D, f9);
/*  48: 53 */     pl[4] = new Vector3(-f8, f10, -f9);
/*  49: 54 */     pl[5] = new Vector3(f8, f10, -f9);
/*  50: 55 */     pl[6] = new Vector3(f8, f10, f9);
/*  51: 56 */     pl[7] = new Vector3(-f8, f10, f9);
/*  52: 59 */     for (int i = 0; i < 8; i++)
/*  53:    */     {
/*  54: 60 */       rot.rotate(pl[i]);
/*  55: 61 */       pl[i].add(pos.x + 0.5D, pos.y + 0.5D, pos.z + 0.5D);
/*  56:    */     }
/*  57: 64 */     float u1 = (k1 + 7) / 256.0F;
/*  58: 65 */     float u2 = (k1 + 9 - 0.01F) / 256.0F;
/*  59: 66 */     float v1 = (l1 + 6) / 256.0F;
/*  60: 67 */     float v2 = (l1 + 8 - 0.01F) / 256.0F;
/*  61:    */     
/*  62:    */ 
/*  63: 70 */     addVectWithUV(pl[0], u1, v2);
/*  64: 71 */     addVectWithUV(pl[1], u2, v2);
/*  65: 72 */     addVectWithUV(pl[2], u2, v1);
/*  66: 73 */     addVectWithUV(pl[3], u1, v1);
/*  67:    */     
/*  68: 75 */     addVectWithUV(pl[7], u1, v2);
/*  69: 76 */     addVectWithUV(pl[6], u2, v2);
/*  70: 77 */     addVectWithUV(pl[5], u2, v1);
/*  71: 78 */     addVectWithUV(pl[4], u1, v1);
/*  72:    */     
/*  73:    */ 
/*  74: 81 */     u1 = (k1 + 7) / 256.0F;
/*  75: 82 */     u2 = (k1 + 9 - 0.01F) / 256.0F;
/*  76: 83 */     v1 = (l1 + 6) / 256.0F;
/*  77: 84 */     v2 = (l1 + 12 - 0.01F) / 256.0F;
/*  78:    */     
/*  79: 86 */     addVectWithUV(pl[1], u1, v2);
/*  80: 87 */     addVectWithUV(pl[0], u2, v2);
/*  81: 88 */     addVectWithUV(pl[4], u2, v1);
/*  82: 89 */     addVectWithUV(pl[5], u1, v1);
/*  83:    */     
/*  84: 91 */     addVectWithUV(pl[2], u1, v2);
/*  85: 92 */     addVectWithUV(pl[1], u2, v2);
/*  86: 93 */     addVectWithUV(pl[5], u2, v1);
/*  87: 94 */     addVectWithUV(pl[6], u1, v1);
/*  88:    */     
/*  89: 96 */     addVectWithUV(pl[3], u1, v2);
/*  90: 97 */     addVectWithUV(pl[2], u2, v2);
/*  91: 98 */     addVectWithUV(pl[6], u2, v1);
/*  92: 99 */     addVectWithUV(pl[7], u1, v1);
/*  93:    */     
/*  94:101 */     addVectWithUV(pl[0], u1, v2);
/*  95:102 */     addVectWithUV(pl[3], u2, v2);
/*  96:103 */     addVectWithUV(pl[7], u2, v1);
/*  97:104 */     addVectWithUV(pl[4], u1, v1);
/*  98:    */   }
/*  99:    */   
/* 100:    */   public static void addVectWithUV(Vector3 vect, double u, double v)
/* 101:    */   {
/* 102:108 */     baz tessellator = baz.a;
/* 103:109 */     tessellator.a(vect.x, vect.y, vect.z, u, v);
/* 104:    */   }
/* 105:    */   
/* 106:    */   public static void renderPointer(Vector3 pos, Quat rot)
/* 107:    */   {
/* 108:114 */     baz tessellator = baz.a;
/* 109:115 */     double u = 0.390625D;double v = 0.015625D;double uvd = 0.0312109375D;
/* 110:116 */     double uvd2 = 0.0077734375D;
/* 111:    */     
/* 112:118 */     tessellator.a(0.9F, 0.9F, 0.9F);
/* 113:    */     
/* 114:120 */     Vector3[] pl = new Vector3[8];
/* 115:121 */     pl[0] = new Vector3(0.4D, 0.0D, 0.0D);
/* 116:122 */     pl[1] = new Vector3(0.0D, 0.0D, 0.2D);
/* 117:123 */     pl[2] = new Vector3(-0.2D, 0.0D, 0.0D);
/* 118:124 */     pl[3] = new Vector3(0.0D, 0.0D, -0.2D);
/* 119:125 */     pl[4] = new Vector3(0.4D, 0.1D, 0.0D);
/* 120:126 */     pl[5] = new Vector3(0.0D, 0.1D, 0.2D);
/* 121:127 */     pl[6] = new Vector3(-0.2D, 0.1D, 0.0D);
/* 122:128 */     pl[7] = new Vector3(0.0D, 0.1D, -0.2D);
/* 123:131 */     for (int i = 0; i < 8; i++)
/* 124:    */     {
/* 125:132 */       rot.rotate(pl[i]);
/* 126:133 */       pl[i].add(pos);
/* 127:    */     }
/* 128:137 */     addVectWithUV(pl[0], u, v);
/* 129:138 */     addVectWithUV(pl[1], u + uvd, v);
/* 130:139 */     addVectWithUV(pl[2], u + uvd, v + uvd);
/* 131:140 */     addVectWithUV(pl[3], u, v + uvd);
/* 132:    */     
/* 133:    */ 
/* 134:143 */     addVectWithUV(pl[4], u, v);
/* 135:144 */     addVectWithUV(pl[7], u, v + uvd);
/* 136:145 */     addVectWithUV(pl[6], u + uvd, v + uvd);
/* 137:146 */     addVectWithUV(pl[5], u + uvd, v);
/* 138:    */     
/* 139:148 */     tessellator.a(0.6F, 0.6F, 0.6F);
/* 140:    */     
/* 141:    */ 
/* 142:151 */     addVectWithUV(pl[0], u + uvd2, v);
/* 143:152 */     addVectWithUV(pl[4], u, v);
/* 144:153 */     addVectWithUV(pl[5], u, v + uvd);
/* 145:154 */     addVectWithUV(pl[1], u + uvd2, v + uvd);
/* 146:    */     
/* 147:156 */     addVectWithUV(pl[0], u, v + uvd2);
/* 148:157 */     addVectWithUV(pl[3], u + uvd, v + uvd2);
/* 149:158 */     addVectWithUV(pl[7], u + uvd, v);
/* 150:159 */     addVectWithUV(pl[4], u, v);
/* 151:    */     
/* 152:    */ 
/* 153:162 */     addVectWithUV(pl[2], u + uvd, v + uvd - uvd2);
/* 154:163 */     addVectWithUV(pl[6], u + uvd, v + uvd);
/* 155:164 */     addVectWithUV(pl[7], u, v + uvd);
/* 156:165 */     addVectWithUV(pl[3], u, v + uvd - uvd2);
/* 157:    */     
/* 158:167 */     addVectWithUV(pl[2], u + uvd, v + uvd - uvd2);
/* 159:168 */     addVectWithUV(pl[1], u, v + uvd - uvd2);
/* 160:169 */     addVectWithUV(pl[5], u, v + uvd);
/* 161:170 */     addVectWithUV(pl[6], u + uvd, v + uvd);
/* 162:    */   }
/* 163:    */   
/* 164:    */   private static class RenderListEntry
/* 165:    */   {
/* 166:    */     public int mapDamageValue(int dmg)
/* 167:    */     {
/* 168:178 */       return dmg;
/* 169:    */     }
/* 170:    */     
/* 171:180 */     public RenderCustomBlock[] metaRenders = new RenderCustomBlock[16];
/* 172:    */     RenderCustomBlock defaultRender;
/* 173:    */   }
/* 174:    */   
/* 175:    */   private static class RenderShiftedEntry
/* 176:    */     extends RenderLib.RenderListEntry
/* 177:    */   {
/* 178:    */     public int shift;
/* 179:    */     
/* 180:    */     public RenderShiftedEntry(int sh)
/* 181:    */     {
/* 182:186 */       this.shift = sh;
/* 183:    */     }
/* 184:    */     
/* 185:    */     public int mapDamageValue(int dmg)
/* 186:    */     {
/* 187:187 */       return dmg >> this.shift;
/* 188:    */     }
/* 189:    */   }
/* 190:    */   
/* 191:191 */   private static RenderListEntry[] renderers = new RenderListEntry[4096];
/* 192:    */   
/* 193:    */   public static RenderCustomBlock getRenderer(int bid, int md)
/* 194:    */   {
/* 195:195 */     RenderListEntry rle = renderers[bid];
/* 196:196 */     if (rle == null) {
/* 197:196 */       return null;
/* 198:    */     }
/* 199:197 */     return rle.metaRenders[md];
/* 200:    */   }
/* 201:    */   
/* 202:    */   public static RenderCustomBlock getInvRenderer(int bid, int md)
/* 203:    */   {
/* 204:216 */     RenderListEntry rle = renderers[bid];
/* 205:217 */     if (rle == null) {
/* 206:217 */       return null;
/* 207:    */     }
/* 208:218 */     int mdv = rle.mapDamageValue(md);
/* 209:219 */     if (mdv > 15) {
/* 210:219 */       return rle.defaultRender;
/* 211:    */     }
/* 212:220 */     return rle.metaRenders[rle.mapDamageValue(md)];
/* 213:    */   }
/* 214:    */   
/* 215:    */   private static RenderCustomBlock makeRenderer(amq bl, Class rcl)
/* 216:    */   {
/* 217:    */     try
/* 218:    */     {
/* 219:241 */       return (RenderCustomBlock)rcl.getDeclaredConstructor(new Class[] { amq.class }).newInstance(new Object[] { bl });
/* 220:    */     }
/* 221:    */     catch (Exception e)
/* 222:    */     {
/* 223:245 */       e.printStackTrace();
/* 224:    */     }
/* 225:247 */     return null;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public static void setRenderer(amq bl, Class rcl)
/* 229:    */   {
/* 230:251 */     RenderCustomBlock rnd = makeRenderer(bl, rcl);
/* 231:252 */     if (renderers[bl.cm] == null) {
/* 232:253 */       renderers[bl.cm] = new RenderListEntry();
/* 233:    */     }
/* 234:255 */     for (int i = 0; i < 16; i++) {
/* 235:256 */       renderers[bl.cm].metaRenders[i] = rnd;
/* 236:    */     }
/* 237:    */   }
/* 238:    */   
/* 239:    */   public static void setRenderer(amq bl, int md, Class rcl)
/* 240:    */   {
/* 241:269 */     RenderCustomBlock rnd = makeRenderer(bl, rcl);
/* 242:270 */     if (renderers[bl.cm] == null) {
/* 243:271 */       renderers[bl.cm] = new RenderListEntry();
/* 244:    */     }
/* 245:273 */     renderers[bl.cm].metaRenders[md] = rnd;
/* 246:    */   }
/* 247:    */   
/* 248:    */   public static void setHighRenderer(amq bl, int md, Class rcl)
/* 249:    */   {
/* 250:287 */     RenderCustomBlock rnd = makeRenderer(bl, rcl);
/* 251:288 */     if (renderers[bl.cm] == null) {
/* 252:289 */       renderers[bl.cm] = new RenderShiftedEntry(8);
/* 253:    */     }
/* 254:291 */     renderers[bl.cm].metaRenders[md] = rnd;
/* 255:    */   }
/* 256:    */   
/* 257:    */   public static void setDefaultRenderer(amq bl, int shift, Class rcl)
/* 258:    */   {
/* 259:306 */     RenderCustomBlock rnd = makeRenderer(bl, rcl);
/* 260:307 */     if (renderers[bl.cm] == null) {
/* 261:308 */       renderers[bl.cm] = new RenderShiftedEntry(shift);
/* 262:    */     }
/* 263:310 */     for (int i = 0; i < 16; i++) {
/* 264:311 */       if (renderers[bl.cm].metaRenders[i] == null) {
/* 265:312 */         renderers[bl.cm].metaRenders[i] = rnd;
/* 266:    */       }
/* 267:    */     }
/* 268:314 */     renderers[bl.cm].defaultRender = rnd;
/* 269:    */   }
/* 270:    */   
/* 271:    */   public static void setShiftedRenderer(amq bl, int md, int shift, Class rcl)
/* 272:    */   {
/* 273:319 */     RenderCustomBlock rnd = makeRenderer(bl, rcl);
/* 274:320 */     if (renderers[bl.cm] == null) {
/* 275:321 */       renderers[bl.cm] = new RenderShiftedEntry(shift);
/* 276:    */     }
/* 277:323 */     renderers[bl.cm].metaRenders[md] = rnd;
/* 278:    */   }
/* 279:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.RenderLib
 * JD-Core Version:    0.7.0.1
 */