/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import atq;
/*   4:    */ import avf;
/*   5:    */ import ayp;
/*   6:    */ import ays;
/*   7:    */ import baz;
/*   8:    */ import bba;
/*   9:    */ import com.eloraam.redpower.core.CoreProxy;
/*  10:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  11:    */ import la;
/*  12:    */ import net.minecraft.client.Minecraft;
/*  13:    */ import org.lwjgl.opengl.GL11;
/*  14:    */ import rq;
/*  15:    */ 
/*  16:    */ public class GuiDisplay
/*  17:    */   extends avf
/*  18:    */ {
/*  19:    */   TileDisplay disp;
/*  20:    */   
/*  21:    */   public GuiDisplay(la inv, TileDisplay td)
/*  22:    */   {
/*  23: 17 */     super(new ContainerDisplay(inv, td));
/*  24: 18 */     this.b = 350;
/*  25: 19 */     this.c = 230;
/*  26: 20 */     this.disp = td;
/*  27:    */   }
/*  28:    */   
/*  29:    */   void sendKey(int b)
/*  30:    */   {
/*  31: 24 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  32: 25 */     pkt.eventId = 1;
/*  33: 26 */     pkt.windowId = this.d.d;
/*  34: 27 */     pkt.addByte((byte)b);
/*  35: 28 */     pkt.encode();
/*  36: 29 */     CoreProxy.sendPacketToServer(pkt);
/*  37:    */   }
/*  38:    */   
/*  39:    */   protected void a(char c, int i)
/*  40:    */   {
/*  41: 33 */     if (i == 1)
/*  42:    */     {
/*  43: 34 */       this.f.g.i();
/*  44: 35 */       return;
/*  45:    */     }
/*  46: 37 */     if (c == '\n') {
/*  47: 37 */       c = '\r';
/*  48:    */     }
/*  49: 39 */     int m = 0;
/*  50: 40 */     if (p()) {
/*  51: 40 */       m |= 0x40;
/*  52:    */     }
/*  53: 41 */     if (o()) {
/*  54: 41 */       m |= 0x20;
/*  55:    */     }
/*  56: 43 */     switch (i)
/*  57:    */     {
/*  58:    */     case 200: 
/*  59: 44 */       sendKey(0x80 | m); break;
/*  60:    */     case 208: 
/*  61: 45 */       sendKey(0x81 | m); break;
/*  62:    */     case 203: 
/*  63: 46 */       sendKey(0x82 | m); break;
/*  64:    */     case 205: 
/*  65: 47 */       sendKey(0x83 | m); break;
/*  66:    */     case 199: 
/*  67: 48 */       sendKey(0x84 | m); break;
/*  68:    */     case 207: 
/*  69: 49 */       sendKey(0x85 | m); break;
/*  70:    */     case 210: 
/*  71: 50 */       sendKey(0x86 | m); break;
/*  72:    */     case 201: 
/*  73:    */     case 202: 
/*  74:    */     case 204: 
/*  75:    */     case 206: 
/*  76:    */     case 209: 
/*  77:    */     default: 
/*  78: 52 */       if ((c > 0) && (c <= '')) {
/*  79: 52 */         sendKey(c);
/*  80:    */       }
/*  81:    */       break;
/*  82:    */     }
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void a(float f, int i, int j)
/*  86:    */   {
/*  87: 59 */     atq fontrenderer = this.f.p;
/*  88:    */     
/*  89:    */ 
/*  90: 62 */     int k = this.f.o.b("/eloraam/control/displaygui.png");
/*  91:    */     
/*  92: 64 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  93: 65 */     this.f.o.b(k);
/*  94: 66 */     int l = (this.g - this.b) / 2;
/*  95: 67 */     int m = (this.h - this.c) / 2;
/*  96: 68 */     drawDoubledRect(l, m, this.b, this.c, 0, 0, this.b, this.c);
/*  97:    */     
/*  98: 70 */     GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
/*  99: 74 */     for (int y = 0; y < 50; y++) {
/* 100: 75 */       for (int x = 0; x < 80; x++)
/* 101:    */       {
/* 102: 76 */         int b = this.disp.screen[(y * 80 + x)] & 0xFF;
/* 103: 77 */         if ((x == this.disp.cursX) && (y == this.disp.cursY))
/* 104:    */         {
/* 105: 78 */           if (this.disp.cursMode == 1) {
/* 106: 78 */             b ^= 0x80;
/* 107:    */           }
/* 108: 79 */           if (this.disp.cursMode == 2)
/* 109:    */           {
/* 110: 80 */             long tm = this.f.e.G();
/* 111: 81 */             if ((tm >> 2 & 1L) > 0L) {
/* 112: 82 */               b ^= 0x80;
/* 113:    */             }
/* 114:    */           }
/* 115:    */         }
/* 116: 85 */         if (b != 32) {
/* 117: 86 */           drawDoubledRect(l + 15 + x * 4, m + 15 + y * 4, 4, 4, 350 + (b & 0xF) * 8, (b >> 4) * 8, 8, 8);
/* 118:    */         }
/* 119:    */       }
/* 120:    */     }
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void drawDoubledRect(int xd, int yd, int wd, int hd, int xs, int ys, int ws, int hs)
/* 124:    */   {
/* 125: 95 */     float xm = 0.00195313F;
/* 126: 96 */     float ym = 0.0039063F;
/* 127:    */     
/* 128: 98 */     baz tessellator = baz.a;
/* 129: 99 */     tessellator.b();
/* 130:100 */     tessellator.a(xd, yd + hd, this.j, xs * xm, (ys + hs) * ym);
/* 131:101 */     tessellator.a(xd + wd, yd + hd, this.j, (xs + ws) * xm, (ys + hs) * ym);
/* 132:    */     
/* 133:103 */     tessellator.a(xd + wd, yd, this.j, (xs + ws) * xm, ys * ym);
/* 134:104 */     tessellator.a(xd, yd, this.j, xs * xm, ys * ym);
/* 135:105 */     tessellator.a();
/* 136:    */   }
/* 137:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.GuiDisplay
 * JD-Core Version:    0.7.0.1
 */