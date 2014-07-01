/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import avf;
/*   4:    */ import ayp;
/*   5:    */ import bba;
/*   6:    */ import com.eloraam.redpower.core.CoreProxy;
/*   7:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*   8:    */ import net.minecraft.client.Minecraft;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ import qw;
/*  11:    */ import rq;
/*  12:    */ 
/*  13:    */ public class GuiCPU
/*  14:    */   extends avf
/*  15:    */ {
/*  16:    */   TileCPU tileCPU;
/*  17:    */   
/*  18:    */   public GuiCPU(qw pli, TileCPU cpu)
/*  19:    */   {
/*  20: 13 */     super(new ContainerCPU(pli, cpu));
/*  21: 14 */     this.tileCPU = cpu;
/*  22: 15 */     this.c = 145;
/*  23: 16 */     this.b = 227;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public GuiCPU(rq cn)
/*  27:    */   {
/*  28: 20 */     super(cn);
/*  29: 21 */     this.c = 145;
/*  30: 22 */     this.b = 227;
/*  31:    */   }
/*  32:    */   
/*  33:    */   protected void b(int p1, int p2) {}
/*  34:    */   
/*  35:    */   protected void a(float f, int p1, int p2)
/*  36:    */   {
/*  37: 34 */     int i = this.f.o.b("/eloraam/control/cpugui.png");
/*  38: 35 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  39: 36 */     this.f.o.b(i);
/*  40: 37 */     int j = (this.g - this.b) / 2;
/*  41: 38 */     int k = (this.h - this.c) / 2;
/*  42: 39 */     b(j, k, 0, 0, this.b, this.c);
/*  43:    */     
/*  44: 41 */     int bits = this.tileCPU.byte0;
/*  45: 42 */     for (int n = 0; n < 8; n++) {
/*  46: 43 */       if ((bits & 1 << n) != 0) {
/*  47: 45 */         b(j + 14 + n * 12, k + 57, 227 + (n >> 2) * 12, 0, 12, 32);
/*  48:    */       }
/*  49:    */     }
/*  50: 48 */     bits = this.tileCPU.byte1;
/*  51: 49 */     for (int n = 0; n < 8; n++) {
/*  52: 50 */       if ((bits & 1 << n) != 0) {
/*  53: 52 */         b(j + 118 + n * 12, k + 57, 227 + (n >> 2) * 12, 0, 12, 32);
/*  54:    */       }
/*  55:    */     }
/*  56: 55 */     bits = this.tileCPU.rbaddr;
/*  57: 56 */     for (int n = 0; n < 8; n++) {
/*  58: 57 */       if ((bits & 1 << n) != 0) {
/*  59: 59 */         b(j + 118 + n * 12, k + 101, 227 + (n >> 2) * 12, 0, 12, 32);
/*  60:    */       }
/*  61:    */     }
/*  62: 62 */     if (this.tileCPU.isRunning()) {
/*  63: 63 */       b(j + 102, k + 99, 227, 32, 8, 8);
/*  64:    */     } else {
/*  65: 65 */       b(j + 102, k + 112, 227, 32, 8, 8);
/*  66:    */     }
/*  67: 68 */     b(this.l, String.format("Disk: %d", new Object[] { Integer.valueOf(this.tileCPU.byte0) }), j + 14, k + 47, -1);
/*  68:    */     
/*  69: 70 */     b(this.l, String.format("Console: %d", new Object[] { Integer.valueOf(this.tileCPU.byte1) }), j + 118, k + 47, -1);
/*  70:    */     
/*  71: 72 */     b(this.l, String.format("ID: %d", new Object[] { Integer.valueOf(this.tileCPU.rbaddr) }), j + 118, k + 91, -1);
/*  72:    */     
/*  73:    */ 
/*  74: 75 */     b(this.l, String.format("START", new Object[] { Integer.valueOf(this.tileCPU.rbaddr) }), j + 50, k + 99, -1);
/*  75:    */     
/*  76: 77 */     b(this.l, String.format("HALT", new Object[] { Integer.valueOf(this.tileCPU.rbaddr) }), j + 50, k + 112, -1);
/*  77:    */     
/*  78: 79 */     b(this.l, String.format("RESET", new Object[] { Integer.valueOf(this.tileCPU.rbaddr) }), j + 50, k + 125, -1);
/*  79:    */   }
/*  80:    */   
/*  81:    */   void sendSimple(int n, int m)
/*  82:    */   {
/*  83: 84 */     if (!this.f.e.I) {
/*  84: 85 */       return;
/*  85:    */     }
/*  86: 86 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  87: 87 */     pkt.eventId = n;
/*  88: 88 */     pkt.windowId = this.d.d;
/*  89: 89 */     pkt.addByte(m);
/*  90: 90 */     pkt.encode();
/*  91: 91 */     CoreProxy.sendPacketToServer(pkt);
/*  92:    */   }
/*  93:    */   
/*  94:    */   boolean sendEvent(int n)
/*  95:    */   {
/*  96: 95 */     if (!this.f.e.I) {
/*  97: 96 */       return true;
/*  98:    */     }
/*  99: 97 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 100: 98 */     pkt.eventId = n;
/* 101: 99 */     pkt.windowId = this.d.d;
/* 102:100 */     pkt.addByte(0);
/* 103:101 */     pkt.encode();
/* 104:102 */     CoreProxy.sendPacketToServer(pkt);
/* 105:103 */     return false;
/* 106:    */   }
/* 107:    */   
/* 108:    */   protected void a(int i, int j, int k)
/* 109:    */   {
/* 110:108 */     int x = i - (this.g - this.b) / 2;
/* 111:109 */     int y = j - (this.h - this.c) / 2;
/* 112:111 */     if ((y >= 57) && (y <= 89))
/* 113:    */     {
/* 114:112 */       for (int n = 0; n < 8; n++) {
/* 115:113 */         if ((x >= 14 + n * 12) && (x <= 26 + n * 12))
/* 116:    */         {
/* 117:114 */           this.tileCPU.byte0 ^= 1 << n;
/* 118:115 */           sendSimple(1, this.tileCPU.byte0);
/* 119:116 */           return;
/* 120:    */         }
/* 121:    */       }
/* 122:119 */       for (int n = 0; n < 8; n++) {
/* 123:120 */         if ((x >= 118 + n * 12) && (x <= 130 + n * 12))
/* 124:    */         {
/* 125:121 */           this.tileCPU.byte1 ^= 1 << n;
/* 126:122 */           sendSimple(2, this.tileCPU.byte1);
/* 127:123 */           return;
/* 128:    */         }
/* 129:    */       }
/* 130:    */     }
/* 131:127 */     if ((y >= 101) && (y <= 133)) {
/* 132:128 */       for (int n = 0; n < 8; n++) {
/* 133:129 */         if ((x >= 118 + n * 12) && (x <= 130 + n * 12))
/* 134:    */         {
/* 135:130 */           this.tileCPU.rbaddr ^= 1 << n;
/* 136:131 */           sendSimple(3, this.tileCPU.rbaddr);
/* 137:132 */           return;
/* 138:    */         }
/* 139:    */       }
/* 140:    */     }
/* 141:136 */     if ((x >= 87) && (x <= 96))
/* 142:    */     {
/* 143:137 */       if ((y >= 98) && (y <= 107))
/* 144:    */       {
/* 145:138 */         if (sendEvent(4)) {
/* 146:139 */           this.tileCPU.warmBootCPU();
/* 147:    */         }
/* 148:140 */         return;
/* 149:    */       }
/* 150:142 */       if ((y >= 111) && (y <= 120))
/* 151:    */       {
/* 152:143 */         if (sendEvent(5)) {
/* 153:144 */           this.tileCPU.haltCPU();
/* 154:    */         }
/* 155:145 */         return;
/* 156:    */       }
/* 157:147 */       if ((y >= 124) && (y <= 133))
/* 158:    */       {
/* 159:148 */         if (sendEvent(6)) {
/* 160:149 */           this.tileCPU.coldBootCPU();
/* 161:    */         }
/* 162:150 */         return;
/* 163:    */       }
/* 164:    */     }
/* 165:153 */     super.a(i, j, k);
/* 166:    */   }
/* 167:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.GuiCPU
 * JD-Core Version:    0.7.0.1
 */