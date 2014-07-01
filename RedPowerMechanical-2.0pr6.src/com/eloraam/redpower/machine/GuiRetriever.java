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
/*  16:    */ public class GuiRetriever
/*  17:    */   extends avf
/*  18:    */ {
/*  19:    */   public GuiRetriever(qw pli, TileRetriever retr)
/*  20:    */   {
/*  21: 14 */     super(new ContainerRetriever(pli, retr));
/*  22: 15 */     this.tileRetriever = retr;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public GuiRetriever(rq cn)
/*  26:    */   {
/*  27: 19 */     super(cn);
/*  28:    */   }
/*  29:    */   
/*  30:    */   protected void b(int p1, int p2)
/*  31:    */   {
/*  32: 24 */     this.l.b("Retriever", 65, 6, 4210752);
/*  33: 25 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/*  34:    */   }
/*  35:    */   
/*  36:    */   protected void a(float f, int p1, int p2)
/*  37:    */   {
/*  38: 31 */     int i = this.f.o.b("/eloraam/machine/retriever.png");
/*  39: 32 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  40: 33 */     this.f.o.b(i);
/*  41: 34 */     int j = (this.g - this.b) / 2;
/*  42: 35 */     int k = (this.h - this.c) / 2;
/*  43: 36 */     b(j, k, 0, 0, this.b, this.c);
/*  44:    */     
/*  45:    */ 
/*  46:    */ 
/*  47: 40 */     int s = this.tileRetriever.cond.getChargeScaled(48);
/*  48:    */     
/*  49: 42 */     b(j + 10, k + 69 - s, 176, 48 - s, 5, s);
/*  50:    */     
/*  51:    */ 
/*  52: 45 */     s = this.tileRetriever.cond.getFlowScaled(48);
/*  53: 46 */     b(j + 17, k + 69 - s, 176, 48 - s, 5, s);
/*  54: 49 */     if (this.tileRetriever.cond.Charge > 600) {
/*  55: 50 */       b(j + 11, k + 13, 181, 0, 3, 6);
/*  56:    */     }
/*  57: 53 */     if (this.tileRetriever.cond.Flow == -1) {
/*  58: 54 */       b(j + 18, k + 13, 184, 0, 3, 6);
/*  59:    */     }
/*  60: 57 */     if (this.tileRetriever.color > 0) {
/*  61: 58 */       rect(j + 122, k + 59, 4, 4, paintColors[(this.tileRetriever.color - 1)]);
/*  62:    */     } else {
/*  63: 60 */       b(j + 122, k + 59, 187, 0, 4, 4);
/*  64:    */     }
/*  65: 63 */     b(j + 45, k + 54, 211, 14 * this.tileRetriever.mode, 14, 14);
/*  66: 64 */     if (this.tileRetriever.mode == 0) {
/*  67: 65 */       b(j + 60 + 18 * (this.tileRetriever.select % 3), k + 15 + 18 * (this.tileRetriever.select / 3), 191, 0, 20, 20);
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   void sendColor()
/*  72:    */   {
/*  73: 73 */     if (!this.f.e.I) {
/*  74: 74 */       return;
/*  75:    */     }
/*  76: 75 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  77: 76 */     pkt.eventId = 1;
/*  78: 77 */     pkt.windowId = this.d.d;
/*  79: 78 */     pkt.addByte(this.tileRetriever.color);
/*  80: 79 */     pkt.encode();
/*  81: 80 */     CoreProxy.sendPacketToServer(pkt);
/*  82:    */   }
/*  83:    */   
/*  84:    */   void sendMode()
/*  85:    */   {
/*  86: 84 */     if (!this.f.e.I) {
/*  87: 85 */       return;
/*  88:    */     }
/*  89: 86 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  90: 87 */     pkt.eventId = 2;
/*  91: 88 */     pkt.windowId = this.d.d;
/*  92: 89 */     pkt.addByte(this.tileRetriever.mode);
/*  93: 90 */     pkt.encode();
/*  94: 91 */     CoreProxy.sendPacketToServer(pkt);
/*  95:    */   }
/*  96:    */   
/*  97:    */   protected void changeColor(boolean incdec)
/*  98:    */   {
/*  99: 95 */     if (incdec)
/* 100:    */     {
/* 101: 96 */       TileRetriever tmp8_5 = this.tileRetriever;tmp8_5.color = ((byte)(tmp8_5.color + 1));
/* 102: 97 */       if (this.tileRetriever.color > 16) {
/* 103: 97 */         this.tileRetriever.color = 0;
/* 104:    */       }
/* 105:    */     }
/* 106:    */     else
/* 107:    */     {
/* 108: 99 */       TileRetriever tmp45_42 = this.tileRetriever;tmp45_42.color = ((byte)(tmp45_42.color - 1));
/* 109:100 */       if (this.tileRetriever.color < 0) {
/* 110:100 */         this.tileRetriever.color = 16;
/* 111:    */       }
/* 112:    */     }
/* 113:102 */     sendColor();
/* 114:    */   }
/* 115:    */   
/* 116:    */   protected void a(int i, int j, int k)
/* 117:    */   {
/* 118:107 */     int x = i - (this.g - this.b) / 2;
/* 119:108 */     int y = j - (this.h - this.c) / 2;
/* 120:110 */     if ((y >= 55) && (y <= 66))
/* 121:    */     {
/* 122:111 */       if ((x >= 118) && (x <= 129))
/* 123:    */       {
/* 124:112 */         changeColor(k == 0);
/* 125:113 */         return;
/* 126:    */       }
/* 127:115 */       if ((x >= 45) && (x <= 58))
/* 128:    */       {
/* 129:116 */         if (k == 0)
/* 130:    */         {
/* 131:117 */           TileRetriever tmp95_92 = this.tileRetriever;tmp95_92.mode = ((byte)(tmp95_92.mode + 1));
/* 132:118 */           if (this.tileRetriever.mode > 1) {
/* 133:119 */             this.tileRetriever.mode = 0;
/* 134:    */           }
/* 135:    */         }
/* 136:    */         else
/* 137:    */         {
/* 138:121 */           TileRetriever tmp131_128 = this.tileRetriever;tmp131_128.mode = ((byte)(tmp131_128.mode - 1));
/* 139:122 */           if (this.tileRetriever.mode < 0) {
/* 140:123 */             this.tileRetriever.mode = 1;
/* 141:    */           }
/* 142:    */         }
/* 143:125 */         sendMode();
/* 144:    */       }
/* 145:    */     }
/* 146:128 */     super.a(i, j, k);
/* 147:    */   }
/* 148:    */   
/* 149:132 */   static int[] paintColors = { 16777215, 16744448, 16711935, 7110911, 16776960, 65280, 16737408, 5460819, 9671571, 65535, 8388863, 255, 5187328, 32768, 16711680, 2039583 };
/* 150:    */   TileRetriever tileRetriever;
/* 151:    */   
/* 152:    */   private void rect(int x, int y, int w, int h, int c)
/* 153:    */   {
/* 154:139 */     w += x;h += y;
/* 155:140 */     float r = (c >> 16 & 0xFF) / 255.0F;
/* 156:141 */     float g = (c >> 8 & 0xFF) / 255.0F;
/* 157:142 */     float b = (c & 0xFF) / 255.0F;
/* 158:143 */     baz tessellator = baz.a;
/* 159:144 */     GL11.glDisable(3553);
/* 160:145 */     GL11.glColor4f(r, g, b, 1.0F);
/* 161:146 */     tessellator.b();
/* 162:147 */     tessellator.a(x, h, 0.0D);
/* 163:148 */     tessellator.a(w, h, 0.0D);
/* 164:149 */     tessellator.a(w, y, 0.0D);
/* 165:150 */     tessellator.a(x, y, 0.0D);
/* 166:151 */     tessellator.a();
/* 167:152 */     GL11.glEnable(3553);
/* 168:153 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 169:    */   }
/* 170:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiRetriever
 * JD-Core Version:    0.7.0.1
 */