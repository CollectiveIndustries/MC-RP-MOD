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
/*  16:    */ public class GuiManager
/*  17:    */   extends avf
/*  18:    */ {
/*  19:    */   public GuiManager(qw pli, TileManager td)
/*  20:    */   {
/*  21: 13 */     super(new ContainerManager(pli, td));
/*  22: 14 */     this.manager = td;
/*  23: 15 */     this.c = 186;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public GuiManager(rq cn)
/*  27:    */   {
/*  28: 19 */     super(cn);
/*  29: 20 */     this.c = 186;
/*  30:    */   }
/*  31:    */   
/*  32:    */   protected void b(int p1, int p2)
/*  33:    */   {
/*  34: 25 */     this.l.b("Manager", 68, 6, 4210752);
/*  35: 26 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/*  36:    */   }
/*  37:    */   
/*  38:    */   protected void a(float f, int p1, int p2)
/*  39:    */   {
/*  40: 32 */     int i = this.f.o.b("/eloraam/machine/manager.png");
/*  41: 33 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  42: 34 */     this.f.o.b(i);
/*  43: 35 */     int j = (this.g - this.b) / 2;
/*  44: 36 */     int k = (this.h - this.c) / 2;
/*  45: 37 */     b(j, k, 0, 0, this.b, this.c);
/*  46:    */     
/*  47:    */ 
/*  48:    */ 
/*  49:    */ 
/*  50:    */ 
/*  51: 43 */     int s = this.manager.cond.getChargeScaled(48);
/*  52: 44 */     b(j + 17, k + 76 - s, 176, 48 - s, 5, s);
/*  53:    */     
/*  54:    */ 
/*  55: 47 */     s = this.manager.cond.getFlowScaled(48);
/*  56: 48 */     b(j + 24, k + 76 - s, 176, 48 - s, 5, s);
/*  57: 51 */     if (this.manager.cond.Charge > 600) {
/*  58: 52 */       b(j + 18, k + 20, 181, 0, 3, 6);
/*  59:    */     }
/*  60: 55 */     if (this.manager.cond.Flow == -1) {
/*  61: 56 */       b(j + 25, k + 20, 184, 0, 3, 6);
/*  62:    */     }
/*  63: 59 */     b(j + 153, k + 37, 191, 14 * this.manager.mode, 14, 14);
/*  64: 62 */     if (this.manager.color > 0) {
/*  65: 63 */       rect(j + 158, k + 78, 4, 4, paintColors[(this.manager.color - 1)]);
/*  66:    */     } else {
/*  67: 65 */       b(j + 158, k + 78, 187, 0, 4, 4);
/*  68:    */     }
/*  69: 68 */     String nm = String.format("%d", new Object[] { Integer.valueOf(this.manager.priority) });
/*  70: 69 */     this.l.a(nm, j + 160 - this.l.a(nm) / 2, k + 58, 16777215);
/*  71:    */   }
/*  72:    */   
/*  73:    */   void sendMode()
/*  74:    */   {
/*  75: 74 */     if (!this.f.e.I) {
/*  76: 75 */       return;
/*  77:    */     }
/*  78: 76 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  79: 77 */     pkt.eventId = 1;
/*  80: 78 */     pkt.windowId = this.d.d;
/*  81: 79 */     pkt.addByte(this.manager.mode);
/*  82: 80 */     pkt.encode();
/*  83: 81 */     CoreProxy.sendPacketToServer(pkt);
/*  84:    */   }
/*  85:    */   
/*  86:    */   void sendColor()
/*  87:    */   {
/*  88: 85 */     if (!this.f.e.I) {
/*  89: 86 */       return;
/*  90:    */     }
/*  91: 87 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  92: 88 */     pkt.eventId = 2;
/*  93: 89 */     pkt.windowId = this.d.d;
/*  94: 90 */     pkt.addByte(this.manager.color);
/*  95: 91 */     pkt.encode();
/*  96: 92 */     CoreProxy.sendPacketToServer(pkt);
/*  97:    */   }
/*  98:    */   
/*  99:    */   void sendPriority()
/* 100:    */   {
/* 101: 96 */     if (!this.f.e.I) {
/* 102: 97 */       return;
/* 103:    */     }
/* 104: 98 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 105: 99 */     pkt.eventId = 3;
/* 106:100 */     pkt.windowId = this.d.d;
/* 107:101 */     pkt.addUVLC(this.manager.priority);
/* 108:102 */     pkt.encode();
/* 109:103 */     CoreProxy.sendPacketToServer(pkt);
/* 110:    */   }
/* 111:    */   
/* 112:    */   protected void changeColor(boolean incdec)
/* 113:    */   {
/* 114:107 */     if (incdec)
/* 115:    */     {
/* 116:108 */       TileManager tmp8_5 = this.manager;tmp8_5.color = ((byte)(tmp8_5.color + 1));
/* 117:109 */       if (this.manager.color > 16) {
/* 118:109 */         this.manager.color = 0;
/* 119:    */       }
/* 120:    */     }
/* 121:    */     else
/* 122:    */     {
/* 123:111 */       TileManager tmp45_42 = this.manager;tmp45_42.color = ((byte)(tmp45_42.color - 1));
/* 124:112 */       if (this.manager.color < 0) {
/* 125:112 */         this.manager.color = 16;
/* 126:    */       }
/* 127:    */     }
/* 128:114 */     sendColor();
/* 129:    */   }
/* 130:    */   
/* 131:    */   protected void a(int i, int j, int k)
/* 132:    */   {
/* 133:119 */     int x = i - (this.g - this.b) / 2;
/* 134:120 */     int y = j - (this.h - this.c) / 2;
/* 135:122 */     if ((x >= 154) && (x <= 165))
/* 136:    */     {
/* 137:123 */       if ((y >= 38) && (y <= 50))
/* 138:    */       {
/* 139:124 */         if (k == 0)
/* 140:    */         {
/* 141:125 */           TileManager tmp68_65 = this.manager;tmp68_65.mode = ((byte)(tmp68_65.mode + 1));
/* 142:126 */           if (this.manager.mode > 1) {
/* 143:126 */             this.manager.mode = 0;
/* 144:    */           }
/* 145:    */         }
/* 146:    */         else
/* 147:    */         {
/* 148:128 */           TileManager tmp104_101 = this.manager;tmp104_101.mode = ((byte)(tmp104_101.mode - 1));
/* 149:129 */           if (this.manager.mode < 0) {
/* 150:129 */             this.manager.mode = 1;
/* 151:    */           }
/* 152:    */         }
/* 153:131 */         sendMode();
/* 154:    */       }
/* 155:133 */       if ((y >= 56) && (y <= 68))
/* 156:    */       {
/* 157:134 */         if (k == 0)
/* 158:    */         {
/* 159:135 */           this.manager.priority += 1;
/* 160:136 */           if (this.manager.priority > 9) {
/* 161:137 */             this.manager.priority = 0;
/* 162:    */           }
/* 163:    */         }
/* 164:    */         else
/* 165:    */         {
/* 166:139 */           this.manager.priority -= 1;
/* 167:140 */           if (this.manager.priority < 0) {
/* 168:141 */             this.manager.priority = 9;
/* 169:    */           }
/* 170:    */         }
/* 171:143 */         sendPriority();
/* 172:    */       }
/* 173:145 */       if ((y >= 74) && (y <= 86)) {
/* 174:146 */         changeColor(k == 0);
/* 175:    */       }
/* 176:    */     }
/* 177:149 */     super.a(i, j, k);
/* 178:    */   }
/* 179:    */   
/* 180:153 */   static int[] paintColors = { 16777215, 16744448, 16711935, 7110911, 16776960, 65280, 16737408, 5460819, 9671571, 65535, 8388863, 255, 5187328, 32768, 16711680, 2039583 };
/* 181:    */   TileManager manager;
/* 182:    */   
/* 183:    */   private void rect(int x, int y, int w, int h, int c)
/* 184:    */   {
/* 185:160 */     w += x;h += y;
/* 186:161 */     float r = (c >> 16 & 0xFF) / 255.0F;
/* 187:162 */     float g = (c >> 8 & 0xFF) / 255.0F;
/* 188:163 */     float b = (c & 0xFF) / 255.0F;
/* 189:164 */     baz tessellator = baz.a;
/* 190:165 */     GL11.glDisable(3553);
/* 191:166 */     GL11.glColor4f(r, g, b, 1.0F);
/* 192:167 */     tessellator.b();
/* 193:168 */     tessellator.a(x, h, 0.0D);
/* 194:169 */     tessellator.a(w, h, 0.0D);
/* 195:170 */     tessellator.a(w, y, 0.0D);
/* 196:171 */     tessellator.a(x, y, 0.0D);
/* 197:172 */     tessellator.a();
/* 198:173 */     GL11.glEnable(3553);
/* 199:174 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 200:    */   }
/* 201:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiManager
 * JD-Core Version:    0.7.0.1
 */