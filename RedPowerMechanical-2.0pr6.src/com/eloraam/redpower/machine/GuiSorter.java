/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import atq;
/*   4:    */ import avf;
/*   5:    */ import ayp;
/*   6:    */ import baz;
/*   7:    */ import bba;
/*   8:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   9:    */ import com.eloraam.redpower.core.CoreProxy;
/*  10:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  11:    */ import net.minecraft.client.Minecraft;
/*  12:    */ import org.lwjgl.opengl.GL11;
/*  13:    */ import qw;
/*  14:    */ import rq;
/*  15:    */ 
/*  16:    */ public class GuiSorter
/*  17:    */   extends avf
/*  18:    */ {
/*  19:    */   public GuiSorter(qw pli, TileSorter td)
/*  20:    */   {
/*  21: 13 */     super(new ContainerSorter(pli, td));
/*  22: 14 */     this.sorter = td;
/*  23: 15 */     this.c = 222;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public GuiSorter(rq cn)
/*  27:    */   {
/*  28: 19 */     super(cn);
/*  29: 20 */     this.c = 222;
/*  30:    */   }
/*  31:    */   
/*  32:    */   protected void b(int p1, int p2)
/*  33:    */   {
/*  34: 25 */     this.l.b("Sorting Machine", 50, 6, 4210752);
/*  35: 26 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/*  36:    */   }
/*  37:    */   
/*  38:    */   protected void a(float f, int p1, int p2)
/*  39:    */   {
/*  40: 32 */     int i = this.f.o.b("/eloraam/machine/sortmachine.png");
/*  41: 33 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  42: 34 */     this.f.o.b(i);
/*  43: 35 */     int j = (this.g - this.b) / 2;
/*  44: 36 */     int k = (this.h - this.c) / 2;
/*  45: 37 */     b(j, k, 0, 0, this.b, this.c);
/*  46: 40 */     if (this.sorter.mode < 2) {
/*  47: 41 */       b(j + 24 + 18 * this.sorter.column, k + 16, 176, 0, 20, 92);
/*  48:    */     }
/*  49: 44 */     for (int n = 0; n < 8; n++) {
/*  50: 45 */       if (this.sorter.colors[n] > 0) {
/*  51: 46 */         rect(j + 32 + n * 18, k + 114, 4, 4, paintColors[(this.sorter.colors[n] - 1)]);
/*  52:    */       } else {
/*  53: 48 */         b(j + 32 + n * 18, k + 114, 187, 92, 4, 4);
/*  54:    */       }
/*  55:    */     }
/*  56: 55 */     int s = this.sorter.cond.getChargeScaled(48);
/*  57: 56 */     b(j + 8, k + 68 - s, 176, 140 - s, 5, s);
/*  58:    */     
/*  59:    */ 
/*  60: 59 */     s = this.sorter.cond.getFlowScaled(48);
/*  61: 60 */     b(j + 15, k + 68 - s, 176, 140 - s, 5, s);
/*  62: 63 */     if (this.sorter.cond.Charge > 600) {
/*  63: 64 */       b(j + 9, k + 12, 181, 92, 3, 6);
/*  64:    */     }
/*  65: 67 */     if (this.sorter.cond.Flow == -1) {
/*  66: 68 */       b(j + 16, k + 12, 184, 92, 3, 6);
/*  67:    */     }
/*  68: 71 */     b(j + 7, k + 73, 210, 14 * this.sorter.automode, 14, 14);
/*  69:    */     
/*  70: 73 */     b(j + 7, k + 91, 196, 14 * this.sorter.mode, 14, 14);
/*  71: 74 */     if ((this.sorter.mode == 4) || (this.sorter.mode == 6))
/*  72:    */     {
/*  73: 75 */       b(j + 7, k + 109, 27, 109, 14, 14);
/*  74: 76 */       if (this.sorter.defcolor > 0) {
/*  75: 77 */         rect(j + 12, k + 114, 4, 4, paintColors[(this.sorter.defcolor - 1)]);
/*  76:    */       } else {
/*  77: 79 */         b(j + 12, k + 114, 187, 92, 4, 4);
/*  78:    */       }
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   void sendMode()
/*  83:    */   {
/*  84: 84 */     if (!this.f.e.I) {
/*  85: 85 */       return;
/*  86:    */     }
/*  87: 86 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  88: 87 */     pkt.eventId = 1;
/*  89: 88 */     pkt.windowId = this.d.d;
/*  90: 89 */     pkt.addByte(this.sorter.mode);
/*  91: 90 */     pkt.encode();
/*  92: 91 */     CoreProxy.sendPacketToServer(pkt);
/*  93:    */   }
/*  94:    */   
/*  95:    */   void sendAutoMode()
/*  96:    */   {
/*  97: 95 */     if (!this.f.e.I) {
/*  98: 96 */       return;
/*  99:    */     }
/* 100: 97 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 101: 98 */     pkt.eventId = 4;
/* 102: 99 */     pkt.windowId = this.d.d;
/* 103:100 */     pkt.addByte(this.sorter.automode);
/* 104:101 */     pkt.encode();
/* 105:102 */     CoreProxy.sendPacketToServer(pkt);
/* 106:    */   }
/* 107:    */   
/* 108:    */   void sendColor(int n)
/* 109:    */   {
/* 110:106 */     if (!this.f.e.I) {
/* 111:107 */       return;
/* 112:    */     }
/* 113:108 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 114:109 */     pkt.eventId = 2;
/* 115:110 */     pkt.windowId = this.d.d;
/* 116:111 */     pkt.addByte(n);
/* 117:112 */     pkt.addByte(this.sorter.colors[n]);
/* 118:113 */     pkt.encode();
/* 119:114 */     CoreProxy.sendPacketToServer(pkt);
/* 120:    */   }
/* 121:    */   
/* 122:    */   void sendDefColor()
/* 123:    */   {
/* 124:118 */     if (!this.f.e.I) {
/* 125:119 */       return;
/* 126:    */     }
/* 127:120 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 128:121 */     pkt.eventId = 3;
/* 129:122 */     pkt.windowId = this.d.d;
/* 130:123 */     pkt.addByte(this.sorter.defcolor);
/* 131:124 */     pkt.encode();
/* 132:125 */     CoreProxy.sendPacketToServer(pkt);
/* 133:    */   }
/* 134:    */   
/* 135:    */   protected void changeColor(int n, boolean incdec)
/* 136:    */   {
/* 137:129 */     if (incdec)
/* 138:    */     {
/* 139:130 */       int tmp12_11 = n; byte[] tmp12_8 = this.sorter.colors;tmp12_8[tmp12_11] = ((byte)(tmp12_8[tmp12_11] + 1));
/* 140:131 */       if (this.sorter.colors[n] > 16) {
/* 141:131 */         this.sorter.colors[n] = 0;
/* 142:    */       }
/* 143:    */     }
/* 144:    */     else
/* 145:    */     {
/* 146:133 */       int tmp53_52 = n; byte[] tmp53_49 = this.sorter.colors;tmp53_49[tmp53_52] = ((byte)(tmp53_49[tmp53_52] - 1));
/* 147:134 */       if (this.sorter.colors[n] < 0) {
/* 148:134 */         this.sorter.colors[n] = 16;
/* 149:    */       }
/* 150:    */     }
/* 151:136 */     sendColor(n);
/* 152:    */   }
/* 153:    */   
/* 154:    */   protected void changeDefColor(boolean incdec)
/* 155:    */   {
/* 156:140 */     if (incdec)
/* 157:    */     {
/* 158:141 */       TileSorter tmp8_5 = this.sorter;tmp8_5.defcolor = ((byte)(tmp8_5.defcolor + 1));
/* 159:142 */       if (this.sorter.defcolor > 16) {
/* 160:142 */         this.sorter.defcolor = 0;
/* 161:    */       }
/* 162:    */     }
/* 163:    */     else
/* 164:    */     {
/* 165:144 */       TileSorter tmp45_42 = this.sorter;tmp45_42.defcolor = ((byte)(tmp45_42.defcolor - 1));
/* 166:145 */       if (this.sorter.defcolor < 0) {
/* 167:145 */         this.sorter.defcolor = 16;
/* 168:    */       }
/* 169:    */     }
/* 170:147 */     sendDefColor();
/* 171:    */   }
/* 172:    */   
/* 173:    */   protected void a(int i, int j, int k)
/* 174:    */   {
/* 175:152 */     int x = i - (this.g - this.b) / 2;
/* 176:153 */     int y = j - (this.h - this.c) / 2;
/* 177:155 */     if ((x <= 21) && (x >= 7))
/* 178:    */     {
/* 179:156 */       if ((y <= 105) && (y >= 91))
/* 180:    */       {
/* 181:157 */         if (k == 0)
/* 182:    */         {
/* 183:158 */           TileSorter tmp66_63 = this.sorter;tmp66_63.mode = ((byte)(tmp66_63.mode + 1));
/* 184:159 */           if (this.sorter.mode > 6) {
/* 185:159 */             this.sorter.mode = 0;
/* 186:    */           }
/* 187:    */         }
/* 188:    */         else
/* 189:    */         {
/* 190:161 */           TileSorter tmp103_100 = this.sorter;tmp103_100.mode = ((byte)(tmp103_100.mode - 1));
/* 191:162 */           if (this.sorter.mode < 0) {
/* 192:162 */             this.sorter.mode = 6;
/* 193:    */           }
/* 194:    */         }
/* 195:164 */         sendMode();
/* 196:    */       }
/* 197:166 */       if ((y <= 87) && (y >= 73))
/* 198:    */       {
/* 199:167 */         if (k == 0)
/* 200:    */         {
/* 201:168 */           TileSorter tmp158_155 = this.sorter;tmp158_155.automode = ((byte)(tmp158_155.automode + 1));
/* 202:169 */           if (this.sorter.automode > 2) {
/* 203:169 */             this.sorter.automode = 0;
/* 204:    */           }
/* 205:    */         }
/* 206:    */         else
/* 207:    */         {
/* 208:171 */           TileSorter tmp194_191 = this.sorter;tmp194_191.automode = ((byte)(tmp194_191.automode - 1));
/* 209:172 */           if (this.sorter.automode < 0) {
/* 210:172 */             this.sorter.automode = 2;
/* 211:    */           }
/* 212:    */         }
/* 213:174 */         sendAutoMode();
/* 214:    */       }
/* 215:    */     }
/* 216:178 */     if ((y >= 110) && (y <= 121))
/* 217:    */     {
/* 218:179 */       for (int n = 0; n < 8; n++) {
/* 219:180 */         if ((x >= 28 + n * 18) && (x <= 39 + n * 18))
/* 220:    */         {
/* 221:181 */           changeColor(n, k == 0);
/* 222:182 */           return;
/* 223:    */         }
/* 224:    */       }
/* 225:185 */       if (((this.sorter.mode == 4) || (this.sorter.mode == 6)) && (x >= 7) && (x <= 21))
/* 226:    */       {
/* 227:186 */         changeDefColor(k == 0);
/* 228:187 */         return;
/* 229:    */       }
/* 230:    */     }
/* 231:190 */     super.a(i, j, k);
/* 232:    */   }
/* 233:    */   
/* 234:194 */   static int[] paintColors = { 16777215, 16744448, 16711935, 7110911, 16776960, 65280, 16737408, 5460819, 9671571, 65535, 8388863, 255, 5187328, 32768, 16711680, 2039583 };
/* 235:    */   TileSorter sorter;
/* 236:    */   
/* 237:    */   private void rect(int x, int y, int w, int h, int c)
/* 238:    */   {
/* 239:201 */     w += x;h += y;
/* 240:202 */     float r = (c >> 16 & 0xFF) / 255.0F;
/* 241:203 */     float g = (c >> 8 & 0xFF) / 255.0F;
/* 242:204 */     float b = (c & 0xFF) / 255.0F;
/* 243:205 */     baz tessellator = baz.a;
/* 244:206 */     GL11.glDisable(3553);
/* 245:207 */     GL11.glColor4f(r, g, b, 1.0F);
/* 246:208 */     tessellator.b();
/* 247:209 */     tessellator.a(x, h, 0.0D);
/* 248:210 */     tessellator.a(w, h, 0.0D);
/* 249:211 */     tessellator.a(w, y, 0.0D);
/* 250:212 */     tessellator.a(x, y, 0.0D);
/* 251:213 */     tessellator.a();
/* 252:214 */     GL11.glEnable(3553);
/* 253:215 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 254:    */   }
/* 255:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiSorter
 * JD-Core Version:    0.7.0.1
 */