/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.core.CoreProxy;
/*   4:    */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*   5:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*   6:    */ import java.io.ByteArrayOutputStream;
/*   7:    */ import java.io.IOException;
/*   8:    */ import java.util.List;
/*   9:    */ import la;
/*  10:    */ import qx;
/*  11:    */ import rq;
/*  12:    */ import rw;
/*  13:    */ import ur;
/*  14:    */ 
/*  15:    */ public class ContainerDisplay
/*  16:    */   extends rq
/*  17:    */   implements IHandleGuiEvent
/*  18:    */ {
/*  19:    */   private TileDisplay tileDisplay;
/*  20:    */   
/*  21:    */   public class RLECompressor
/*  22:    */   {
/*  23: 17 */     ByteArrayOutputStream bas = new ByteArrayOutputStream();
/*  24: 18 */     byte[] datbuf = new byte[256];
/*  25: 19 */     byte srledat = 0;
/*  26: 20 */     int rleoffs = 0;
/*  27: 21 */     int srleoffs = 0;
/*  28: 22 */     int datpos = 0;
/*  29: 23 */     boolean changed = false;
/*  30:    */     
/*  31:    */     public RLECompressor() {}
/*  32:    */     
/*  33:    */     public void writeRLE()
/*  34:    */     {
/*  35: 26 */       this.bas.write((byte)this.rleoffs);
/*  36: 27 */       this.datpos = 0;this.rleoffs = 0;this.srleoffs = 0;
/*  37:    */     }
/*  38:    */     
/*  39:    */     public void writeSRLE()
/*  40:    */     {
/*  41: 31 */       this.bas.write(-1);
/*  42: 32 */       this.bas.write((byte)this.srleoffs);
/*  43: 33 */       this.bas.write(this.srledat);
/*  44: 34 */       this.datpos = 0;this.rleoffs = 0;this.srleoffs = 0;
/*  45:    */     }
/*  46:    */     
/*  47:    */     public void writeDat(int bytes)
/*  48:    */     {
/*  49: 38 */       if (bytes == 0) {
/*  50: 38 */         return;
/*  51:    */       }
/*  52: 40 */       this.bas.write((byte)(0x80 | bytes));
/*  53: 41 */       this.bas.write(this.datbuf, 0, bytes);
/*  54: 42 */       this.datpos -= bytes;
/*  55:    */     }
/*  56:    */     
/*  57:    */     public void addByte(byte b, boolean diff)
/*  58:    */     {
/*  59: 46 */       if (diff)
/*  60:    */       {
/*  61: 47 */         this.changed = true;
/*  62: 48 */         if ((this.rleoffs > 5) && (this.rleoffs >= this.srleoffs))
/*  63:    */         {
/*  64: 49 */           writeDat(this.datpos - this.rleoffs);
/*  65: 50 */           writeRLE();
/*  66:    */         }
/*  67: 52 */         this.rleoffs = 0;
/*  68:    */       }
/*  69:    */       else
/*  70:    */       {
/*  71: 54 */         this.rleoffs += 1;
/*  72: 55 */         if (this.rleoffs >= 127)
/*  73:    */         {
/*  74: 56 */           this.datpos += 1;
/*  75: 57 */           writeDat(this.datpos - this.rleoffs);
/*  76: 58 */           writeRLE();
/*  77: 59 */           return;
/*  78:    */         }
/*  79:    */       }
/*  80: 62 */       if (this.srleoffs == 0)
/*  81:    */       {
/*  82: 63 */         this.srledat = b;
/*  83: 64 */         this.srleoffs = 1;
/*  84:    */       }
/*  85: 65 */       else if (b == this.srledat)
/*  86:    */       {
/*  87: 66 */         this.srleoffs += 1;
/*  88: 67 */         if (this.srleoffs >= 127)
/*  89:    */         {
/*  90: 68 */           this.datpos += 1;
/*  91: 69 */           writeDat(this.datpos - this.srleoffs);
/*  92: 70 */           writeSRLE();
/*  93:    */         }
/*  94:    */       }
/*  95:    */       else
/*  96:    */       {
/*  97: 74 */         if ((this.srleoffs > 5) && (this.srleoffs >= this.rleoffs))
/*  98:    */         {
/*  99: 75 */           writeDat(this.datpos - this.srleoffs);
/* 100: 76 */           writeSRLE();
/* 101:    */         }
/* 102: 78 */         this.srledat = b;
/* 103: 79 */         this.srleoffs = 1;
/* 104:    */       }
/* 105: 82 */       this.datbuf[this.datpos] = b;
/* 106: 83 */       this.datpos += 1;
/* 107:    */       
/* 108: 85 */       int rem = Math.max(this.srleoffs, this.rleoffs);
/* 109: 86 */       if ((rem <= 5) && (this.datpos >= 126))
/* 110:    */       {
/* 111: 87 */         writeDat(this.datpos);
/* 112: 88 */         this.srleoffs = 0;
/* 113: 89 */         this.rleoffs = 0;
/* 114:    */       }
/* 115: 90 */       else if (this.datpos - rem >= 126)
/* 116:    */       {
/* 117: 91 */         writeDat(this.datpos - rem);
/* 118:    */       }
/* 119:    */     }
/* 120:    */     
/* 121:    */     public void flush()
/* 122:    */     {
/* 123: 96 */       this.datpos -= this.rleoffs;
/* 124: 97 */       this.srleoffs = Math.max(0, this.srleoffs - this.rleoffs);
/* 125: 98 */       if (this.datpos == 0) {
/* 126: 98 */         return;
/* 127:    */       }
/* 128:100 */       if (this.srleoffs > 5)
/* 129:    */       {
/* 130:101 */         writeDat(this.datpos - this.srleoffs);
/* 131:102 */         writeSRLE();
/* 132:    */       }
/* 133:    */       else
/* 134:    */       {
/* 135:104 */         writeDat(this.datpos);
/* 136:    */       }
/* 137:    */     }
/* 138:    */     
/* 139:    */     byte[] getByteArray()
/* 140:    */     {
/* 141:109 */       if (!this.changed) {
/* 142:109 */         return null;
/* 143:    */       }
/* 144:110 */       return this.bas.toByteArray();
/* 145:    */     }
/* 146:    */   }
/* 147:    */   
/* 148:    */   void decompress(byte[] compress, byte[] out)
/* 149:    */   {
/* 150:115 */     int opos = 0;
/* 151:116 */     int i = 0;
/* 152:118 */     while (i < compress.length)
/* 153:    */     {
/* 154:119 */       if (opos >= out.length) {
/* 155:119 */         return;
/* 156:    */       }
/* 157:120 */       int cmd = compress[(i++)] & 0xFF;
/* 158:121 */       if ((cmd & 0x80) == 0)
/* 159:    */       {
/* 160:122 */         opos += (cmd & 0x7F);
/* 161:    */       }
/* 162:123 */       else if (cmd == 255)
/* 163:    */       {
/* 164:124 */         if (i + 2 > compress.length) {
/* 165:124 */           return;
/* 166:    */         }
/* 167:125 */         int ln = Math.min(compress[i] & 0xFF, out.length - opos);
/* 168:127 */         for (int j = 0; j < ln; j++) {
/* 169:128 */           out[(opos + j)] = compress[(i + 1)];
/* 170:    */         }
/* 171:130 */         opos += ln;
/* 172:131 */         i += 2;
/* 173:    */       }
/* 174:    */       else
/* 175:    */       {
/* 176:133 */         int ln = Math.min(Math.min(cmd & 0x7F, out.length - opos), compress.length - i);
/* 177:137 */         for (int j = 0; j < ln; j++) {
/* 178:138 */           out[(opos + j)] = compress[(i + j)];
/* 179:    */         }
/* 180:140 */         opos += ln;
/* 181:141 */         i += ln;
/* 182:    */       }
/* 183:    */     }
/* 184:    */   }
/* 185:    */   
/* 186:    */   public ContainerDisplay(la inv, TileDisplay td)
/* 187:    */   {
/* 188:147 */     this.tileDisplay = td;
/* 189:    */   }
/* 190:    */   
/* 191:    */   public boolean a(qx player)
/* 192:    */   {
/* 193:151 */     return this.tileDisplay.isUseableByPlayer(player);
/* 194:    */   }
/* 195:    */   
/* 196:    */   public ur b(qx player, int i)
/* 197:    */   {
/* 198:156 */     return null;
/* 199:    */   }
/* 200:    */   
/* 201:    */   byte[] getDisplayRLE()
/* 202:    */   {
/* 203:160 */     RLECompressor rle = new RLECompressor();
/* 204:161 */     for (int i = 0; i < 4000; i++)
/* 205:    */     {
/* 206:162 */       rle.addByte(this.tileDisplay.screen[i], this.screen[i] != this.tileDisplay.screen[i]);
/* 207:    */       
/* 208:164 */       this.screen[i] = this.tileDisplay.screen[i];
/* 209:    */     }
/* 210:166 */     rle.flush();
/* 211:167 */     return rle.getByteArray();
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void b()
/* 215:    */   {
/* 216:171 */     super.b();
/* 217:    */     
/* 218:173 */     byte[] drl = getDisplayRLE();
/* 219:174 */     for (int i = 0; i < this.e.size(); i++)
/* 220:    */     {
/* 221:175 */       rw ic = (rw)this.e.get(i);
/* 222:177 */       if (this.tileDisplay.cursX != this.cursx) {
/* 223:178 */         ic.a(this, 0, this.tileDisplay.cursX);
/* 224:    */       }
/* 225:181 */       if (this.tileDisplay.cursY != this.cursy) {
/* 226:182 */         ic.a(this, 1, this.tileDisplay.cursY);
/* 227:    */       }
/* 228:185 */       if (this.tileDisplay.cursMode != this.cursmode) {
/* 229:186 */         ic.a(this, 2, this.tileDisplay.cursMode);
/* 230:    */       }
/* 231:189 */       if (drl != null)
/* 232:    */       {
/* 233:190 */         Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 234:191 */         pkt.eventId = 2;
/* 235:192 */         pkt.windowId = this.d;
/* 236:193 */         pkt.addByteArray(drl);
/* 237:194 */         pkt.encode();
/* 238:195 */         CoreProxy.sendPacketToCrafting(ic, pkt);
/* 239:    */       }
/* 240:    */     }
/* 241:207 */     this.cursx = this.tileDisplay.cursX;
/* 242:208 */     this.cursy = this.tileDisplay.cursY;
/* 243:209 */     this.cursmode = this.tileDisplay.cursMode;
/* 244:    */   }
/* 245:    */   
/* 246:    */   public void b(int i, int j)
/* 247:    */   {
/* 248:213 */     switch (i)
/* 249:    */     {
/* 250:    */     case 0: 
/* 251:214 */       this.tileDisplay.cursX = j;return;
/* 252:    */     case 1: 
/* 253:215 */       this.tileDisplay.cursY = j;return;
/* 254:    */     case 2: 
/* 255:216 */       this.tileDisplay.cursMode = j;return;
/* 256:    */     }
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 260:    */   {
/* 261:    */     try
/* 262:    */     {
/* 263:228 */       switch (packet.eventId)
/* 264:    */       {
/* 265:    */       case 1: 
/* 266:230 */         this.tileDisplay.pushKey((byte)packet.getByte());
/* 267:231 */         this.tileDisplay.dirtyBlock();
/* 268:232 */         break;
/* 269:    */       case 2: 
/* 270:234 */         byte[] ba = packet.getByteArray();
/* 271:235 */         decompress(ba, this.tileDisplay.screen);
/* 272:    */       }
/* 273:    */     }
/* 274:    */     catch (IOException e) {}
/* 275:    */   }
/* 276:    */   
/* 277:243 */   private byte[] screen = new byte[4000];
/* 278:244 */   int cursx = 0;
/* 279:244 */   int cursy = 0;
/* 280:244 */   int cursmode = 0;
/* 281:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.ContainerDisplay
 * JD-Core Version:    0.7.0.1
 */