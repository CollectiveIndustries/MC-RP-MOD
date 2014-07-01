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
/*  15:    */ public class GuiFilter
/*  16:    */   extends avf
/*  17:    */ {
/*  18:    */   public GuiFilter(qw pli, TileFilter filter)
/*  19:    */   {
/*  20: 14 */     super(new ContainerFilter(pli, filter));
/*  21: 15 */     this.tileFilter = filter;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public GuiFilter(rq cn)
/*  25:    */   {
/*  26: 19 */     super(cn);
/*  27:    */   }
/*  28:    */   
/*  29:    */   protected void b(int p1, int p2)
/*  30:    */   {
/*  31: 24 */     this.l.b(this.tileFilter.b(), 60, 6, 4210752);
/*  32: 25 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/*  33:    */   }
/*  34:    */   
/*  35:    */   protected void a(float f, int p1, int p2)
/*  36:    */   {
/*  37: 31 */     int i = this.f.o.b("/eloraam/machine/filter9.png");
/*  38: 32 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  39: 33 */     this.f.o.b(i);
/*  40: 34 */     int j = (this.g - this.b) / 2;
/*  41: 35 */     int k = (this.h - this.c) / 2;
/*  42: 36 */     b(j, k, 0, 0, this.b, this.c);
/*  43: 39 */     if (this.tileFilter.color > 0) {
/*  44: 40 */       rect(j + 122, k + 59, 4, 4, paintColors[(this.tileFilter.color - 1)]);
/*  45:    */     } else {
/*  46: 42 */       b(j + 122, k + 59, 176, 0, 4, 4);
/*  47:    */     }
/*  48:    */   }
/*  49:    */   
/*  50:    */   void sendColor()
/*  51:    */   {
/*  52: 48 */     if (!this.f.e.I) {
/*  53: 49 */       return;
/*  54:    */     }
/*  55: 50 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  56: 51 */     pkt.eventId = 1;
/*  57: 52 */     pkt.windowId = this.d.d;
/*  58: 53 */     pkt.addByte(this.tileFilter.color);
/*  59: 54 */     pkt.encode();
/*  60: 55 */     CoreProxy.sendPacketToServer(pkt);
/*  61:    */   }
/*  62:    */   
/*  63:    */   protected void changeColor(boolean incdec)
/*  64:    */   {
/*  65: 59 */     if (incdec)
/*  66:    */     {
/*  67: 60 */       TileFilter tmp8_5 = this.tileFilter;tmp8_5.color = ((byte)(tmp8_5.color + 1));
/*  68: 61 */       if (this.tileFilter.color > 16) {
/*  69: 61 */         this.tileFilter.color = 0;
/*  70:    */       }
/*  71:    */     }
/*  72:    */     else
/*  73:    */     {
/*  74: 63 */       TileFilter tmp45_42 = this.tileFilter;tmp45_42.color = ((byte)(tmp45_42.color - 1));
/*  75: 64 */       if (this.tileFilter.color < 0) {
/*  76: 64 */         this.tileFilter.color = 16;
/*  77:    */       }
/*  78:    */     }
/*  79: 66 */     sendColor();
/*  80:    */   }
/*  81:    */   
/*  82:    */   protected void a(int i, int j, int k)
/*  83:    */   {
/*  84: 71 */     int x = i - (this.g - this.b) / 2;
/*  85: 72 */     int y = j - (this.h - this.c) / 2;
/*  86: 74 */     if ((y >= 55) && (y <= 66) && 
/*  87: 75 */       (x >= 118) && (x <= 129))
/*  88:    */     {
/*  89: 76 */       changeColor(k == 0);
/*  90: 77 */       return;
/*  91:    */     }
/*  92: 80 */     super.a(i, j, k);
/*  93:    */   }
/*  94:    */   
/*  95: 84 */   static int[] paintColors = { 16777215, 16744448, 16711935, 7110911, 16776960, 65280, 16737408, 5460819, 9671571, 65535, 8388863, 255, 5187328, 32768, 16711680, 2039583 };
/*  96:    */   TileFilter tileFilter;
/*  97:    */   
/*  98:    */   private void rect(int x, int y, int w, int h, int c)
/*  99:    */   {
/* 100: 91 */     w += x;h += y;
/* 101: 92 */     float r = (c >> 16 & 0xFF) / 255.0F;
/* 102: 93 */     float g = (c >> 8 & 0xFF) / 255.0F;
/* 103: 94 */     float b = (c & 0xFF) / 255.0F;
/* 104: 95 */     baz tessellator = baz.a;
/* 105: 96 */     GL11.glDisable(3553);
/* 106: 97 */     GL11.glColor4f(r, g, b, 1.0F);
/* 107: 98 */     tessellator.b();
/* 108: 99 */     tessellator.a(x, h, 0.0D);
/* 109:100 */     tessellator.a(w, h, 0.0D);
/* 110:101 */     tessellator.a(w, y, 0.0D);
/* 111:102 */     tessellator.a(x, y, 0.0D);
/* 112:103 */     tessellator.a();
/* 113:104 */     GL11.glEnable(3553);
/* 114:105 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 115:    */   }
/* 116:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiFilter
 * JD-Core Version:    0.7.0.1
 */