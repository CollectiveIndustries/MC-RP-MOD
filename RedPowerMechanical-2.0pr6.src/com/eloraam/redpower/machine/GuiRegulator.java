/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import atq;
/*   4:    */ import avf;
/*   5:    */ import ayp;
/*   6:    */ import baz;
/*   7:    */ import bba;
/*   8:    */ import com.eloraam.redpower.core.CoreProxy;
/*   9:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  10:    */ import net.minecraft.client.Minecraft;
/*  11:    */ import org.lwjgl.opengl.GL11;
/*  12:    */ import qw;
/*  13:    */ import rq;
/*  14:    */ 
/*  15:    */ public class GuiRegulator
/*  16:    */   extends avf
/*  17:    */ {
/*  18:    */   public GuiRegulator(qw pli, TileRegulator reg)
/*  19:    */   {
/*  20: 14 */     super(new ContainerRegulator(pli, reg));
/*  21: 15 */     this.tileRegulator = reg;
/*  22: 16 */     this.b = 211;this.c = 167;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public GuiRegulator(rq cn)
/*  26:    */   {
/*  27: 20 */     super(cn);
/*  28: 21 */     this.b = 211;this.c = 167;
/*  29:    */   }
/*  30:    */   
/*  31:    */   protected void b(int p1, int p2)
/*  32:    */   {
/*  33: 26 */     this.l.b(this.tileRegulator.b(), 79, 6, 4210752);
/*  34: 27 */     this.l.b("Inventory", 25, this.c - 96 + 3, 4210752);
/*  35:    */   }
/*  36:    */   
/*  37:    */   protected void a(float f, int p1, int p2)
/*  38:    */   {
/*  39: 33 */     int i = this.f.o.b("/eloraam/machine/regulator.png");
/*  40: 34 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  41: 35 */     this.f.o.b(i);
/*  42: 36 */     int j = (this.g - this.b) / 2;
/*  43: 37 */     int k = (this.h - this.c) / 2;
/*  44: 38 */     b(j, k, 0, 0, this.b, this.c);
/*  45: 41 */     if (this.tileRegulator.color > 0) {
/*  46: 42 */       rect(j + 140, k + 60, 4, 4, paintColors[(this.tileRegulator.color - 1)]);
/*  47:    */     } else {
/*  48: 44 */       b(j + 140, k + 60, 212, 0, 4, 4);
/*  49:    */     }
/*  50: 47 */     b(j + 135, k + 19, 216, 14 * this.tileRegulator.mode, 14, 14);
/*  51:    */   }
/*  52:    */   
/*  53:    */   void sendColor()
/*  54:    */   {
/*  55: 53 */     if (!this.f.e.I) {
/*  56: 54 */       return;
/*  57:    */     }
/*  58: 55 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  59: 56 */     pkt.eventId = 1;
/*  60: 57 */     pkt.windowId = this.d.d;
/*  61: 58 */     pkt.addByte(this.tileRegulator.color);
/*  62: 59 */     pkt.encode();
/*  63: 60 */     CoreProxy.sendPacketToServer(pkt);
/*  64:    */   }
/*  65:    */   
/*  66:    */   void sendMode()
/*  67:    */   {
/*  68: 64 */     if (!this.f.e.I) {
/*  69: 65 */       return;
/*  70:    */     }
/*  71: 66 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  72: 67 */     pkt.eventId = 2;
/*  73: 68 */     pkt.windowId = this.d.d;
/*  74: 69 */     pkt.addByte(this.tileRegulator.mode);
/*  75: 70 */     pkt.encode();
/*  76: 71 */     CoreProxy.sendPacketToServer(pkt);
/*  77:    */   }
/*  78:    */   
/*  79:    */   protected void changeColor(boolean incdec)
/*  80:    */   {
/*  81: 75 */     if (incdec)
/*  82:    */     {
/*  83: 76 */       this.tileRegulator.color += 1;
/*  84: 77 */       if (this.tileRegulator.color > 16) {
/*  85: 77 */         this.tileRegulator.color = 0;
/*  86:    */       }
/*  87:    */     }
/*  88:    */     else
/*  89:    */     {
/*  90: 79 */       this.tileRegulator.color -= 1;
/*  91: 80 */       if (this.tileRegulator.color < 0) {
/*  92: 80 */         this.tileRegulator.color = 16;
/*  93:    */       }
/*  94:    */     }
/*  95: 82 */     sendColor();
/*  96:    */   }
/*  97:    */   
/*  98:    */   protected void a(int i, int j, int k)
/*  99:    */   {
/* 100: 87 */     int x = i - (this.g - this.b) / 2;
/* 101: 88 */     int y = j - (this.h - this.c) / 2;
/* 102: 90 */     if ((x >= 136) && (x <= 147))
/* 103:    */     {
/* 104: 91 */       if ((y >= 56) && (y <= 67))
/* 105:    */       {
/* 106: 92 */         changeColor(k == 0);
/* 107: 93 */         return;
/* 108:    */       }
/* 109: 95 */       if ((y >= 19) && (y <= 32))
/* 110:    */       {
/* 111: 96 */         if (k == 0)
/* 112:    */         {
/* 113: 97 */           TileRegulator tmp96_93 = this.tileRegulator;tmp96_93.mode = ((byte)(tmp96_93.mode + 1));
/* 114: 98 */           if (this.tileRegulator.mode > 1) {
/* 115: 99 */             this.tileRegulator.mode = 0;
/* 116:    */           }
/* 117:    */         }
/* 118:    */         else
/* 119:    */         {
/* 120:101 */           TileRegulator tmp132_129 = this.tileRegulator;tmp132_129.mode = ((byte)(tmp132_129.mode - 1));
/* 121:102 */           if (this.tileRegulator.mode < 0) {
/* 122:103 */             this.tileRegulator.mode = 1;
/* 123:    */           }
/* 124:    */         }
/* 125:105 */         sendMode();
/* 126:    */       }
/* 127:    */     }
/* 128:108 */     super.a(i, j, k);
/* 129:    */   }
/* 130:    */   
/* 131:112 */   static int[] paintColors = { 16777215, 16744448, 16711935, 7110911, 16776960, 65280, 16737408, 5460819, 9671571, 65535, 8388863, 255, 5187328, 32768, 16711680, 2039583 };
/* 132:    */   TileRegulator tileRegulator;
/* 133:    */   
/* 134:    */   private void rect(int x, int y, int w, int h, int c)
/* 135:    */   {
/* 136:119 */     w += x;h += y;
/* 137:120 */     float r = (c >> 16 & 0xFF) / 255.0F;
/* 138:121 */     float g = (c >> 8 & 0xFF) / 255.0F;
/* 139:122 */     float b = (c & 0xFF) / 255.0F;
/* 140:123 */     baz tessellator = baz.a;
/* 141:124 */     GL11.glDisable(3553);
/* 142:125 */     GL11.glColor4f(r, g, b, 1.0F);
/* 143:126 */     tessellator.b();
/* 144:127 */     tessellator.a(x, h, 0.0D);
/* 145:128 */     tessellator.a(w, h, 0.0D);
/* 146:129 */     tessellator.a(w, y, 0.0D);
/* 147:130 */     tessellator.a(x, y, 0.0D);
/* 148:131 */     tessellator.a();
/* 149:132 */     GL11.glEnable(3553);
/* 150:133 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 151:    */   }
/* 152:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiRegulator
 * JD-Core Version:    0.7.0.1
 */