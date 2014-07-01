/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import atq;
/*   4:    */ import avf;
/*   5:    */ import ayp;
/*   6:    */ import ays;
/*   7:    */ import bba;
/*   8:    */ import com.eloraam.redpower.core.CoreProxy;
/*   9:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  10:    */ import net.minecraft.client.Minecraft;
/*  11:    */ import org.lwjgl.opengl.GL11;
/*  12:    */ import qw;
/*  13:    */ import rq;
/*  14:    */ import sr;
/*  15:    */ 
/*  16:    */ public class GuiAssemble
/*  17:    */   extends avf
/*  18:    */ {
/*  19:    */   TileAssemble assemble;
/*  20:    */   
/*  21:    */   public GuiAssemble(qw pli, TileAssemble td)
/*  22:    */   {
/*  23: 13 */     super(new ContainerAssemble(pli, td));
/*  24: 14 */     this.assemble = td;
/*  25: 15 */     this.c = 195;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public GuiAssemble(rq cn)
/*  29:    */   {
/*  30: 19 */     super(cn);
/*  31: 20 */     this.c = 195;
/*  32:    */   }
/*  33:    */   
/*  34:    */   protected void b(int p1, int p2)
/*  35:    */   {
/*  36: 25 */     this.l.b("Assembler", 65, 6, 4210752);
/*  37: 26 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/*  38:    */   }
/*  39:    */   
/*  40:    */   protected void a(float f, int p1, int p2)
/*  41:    */   {
/*  42:    */     int tex;
/*  43:    */     int tex;
/*  44: 33 */     if (this.assemble.mode == 0) {
/*  45: 34 */       tex = this.f.o.b("/eloraam/machine/assembler.png");
/*  46:    */     } else {
/*  47: 37 */       tex = this.f.o.b("/eloraam/machine/assembler2.png");
/*  48:    */     }
/*  49: 40 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  50: 41 */     this.f.o.b(tex);
/*  51: 42 */     int j = (this.g - this.b) / 2;
/*  52: 43 */     int k = (this.h - this.c) / 2;
/*  53: 44 */     b(j, k, 0, 0, this.b, this.c);
/*  54:    */     
/*  55:    */ 
/*  56: 47 */     b(j + 152, k + 37, 196, 14 * this.assemble.mode, 14, 14);
/*  57: 48 */     if (this.assemble.mode == 0)
/*  58:    */     {
/*  59: 49 */       b(j + 6 + 18 * (this.assemble.select & 0x7), k + 16 + 18 * (this.assemble.select >> 3), 176, 0, 20, 20);
/*  60: 54 */       for (int i = 1; i < 16; i++) {
/*  61: 55 */         if ((this.assemble.skipSlots & 1 << i) != 0) {
/*  62: 57 */           b(j + 8 + 18 * (i & 0x7), k + 18 + 18 * (i >> 3), 176, 20, 16, 16);
/*  63:    */         }
/*  64:    */       }
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   void sendMode()
/*  69:    */   {
/*  70: 66 */     if (!this.f.e.I)
/*  71:    */     {
/*  72: 67 */       this.assemble.updateBlockChange();
/*  73: 68 */       return;
/*  74:    */     }
/*  75: 70 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  76: 71 */     pkt.eventId = 1;
/*  77: 72 */     pkt.windowId = this.d.d;
/*  78: 73 */     pkt.addByte(this.assemble.mode);
/*  79: 74 */     pkt.encode();
/*  80: 75 */     CoreProxy.sendPacketToServer(pkt);
/*  81:    */   }
/*  82:    */   
/*  83:    */   void sendSkip()
/*  84:    */   {
/*  85: 79 */     if (!this.f.e.I)
/*  86:    */     {
/*  87: 80 */       this.assemble.updateBlockChange();
/*  88: 81 */       return;
/*  89:    */     }
/*  90: 83 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  91: 84 */     pkt.eventId = 2;
/*  92: 85 */     pkt.windowId = this.d.d;
/*  93: 86 */     pkt.addUVLC(this.assemble.skipSlots);
/*  94: 87 */     pkt.encode();
/*  95: 88 */     CoreProxy.sendPacketToServer(pkt);
/*  96:    */   }
/*  97:    */   
/*  98:    */   protected void a(int i, int j, int k)
/*  99:    */   {
/* 100: 93 */     int x = i - (this.g - this.b) / 2;
/* 101: 94 */     int y = j - (this.h - this.c) / 2;
/* 102: 96 */     if ((x >= 152) && (y >= 37) && (x <= 166) && (y <= 51))
/* 103:    */     {
/* 104: 97 */       if (k == 0)
/* 105:    */       {
/* 106: 98 */         TileAssemble tmp68_65 = this.assemble;tmp68_65.mode = ((byte)(tmp68_65.mode + 1));
/* 107: 99 */         if (this.assemble.mode > 1) {
/* 108: 99 */           this.assemble.mode = 0;
/* 109:    */         }
/* 110:    */       }
/* 111:    */       else
/* 112:    */       {
/* 113:101 */         TileAssemble tmp104_101 = this.assemble;tmp104_101.mode = ((byte)(tmp104_101.mode - 1));
/* 114:102 */         if (this.assemble.mode < 0) {
/* 115:102 */           this.assemble.mode = 1;
/* 116:    */         }
/* 117:    */       }
/* 118:104 */       sendMode();
/* 119:105 */       return;
/* 120:    */     }
/* 121:107 */     if ((this.assemble.mode == 0) && (this.f.g.bJ.n() == null))
/* 122:    */     {
/* 123:109 */       boolean send = false;
/* 124:110 */       for (int v = 1; v < 16; v++)
/* 125:    */       {
/* 126:111 */         int x2 = 8 + 18 * (v & 0x7);
/* 127:112 */         int y2 = 18 + 18 * (v >> 3);
/* 128:113 */         if ((x >= x2) && (x < x2 + 16) && (y >= y2) && (y < y2 + 16))
/* 129:    */         {
/* 130:114 */           if (this.d.a(v).d()) {
/* 131:    */             break;
/* 132:    */           }
/* 133:117 */           this.assemble.skipSlots ^= 1 << v;
/* 134:118 */           send = true;
/* 135:    */         }
/* 136:    */       }
/* 137:121 */       if (send)
/* 138:    */       {
/* 139:122 */         sendSkip();
/* 140:123 */         return;
/* 141:    */       }
/* 142:    */     }
/* 143:126 */     super.a(i, j, k);
/* 144:    */   }
/* 145:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiAssemble
 * JD-Core Version:    0.7.0.1
 */