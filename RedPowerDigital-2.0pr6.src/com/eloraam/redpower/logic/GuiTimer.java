/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import atb;
/*   4:    */ import atq;
/*   5:    */ import avf;
/*   6:    */ import ayp;
/*   7:    */ import bba;
/*   8:    */ import com.eloraam.redpower.core.CoreProxy;
/*   9:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  10:    */ import java.util.List;
/*  11:    */ import net.minecraft.client.Minecraft;
/*  12:    */ import org.lwjgl.opengl.GL11;
/*  13:    */ import qw;
/*  14:    */ import rq;
/*  15:    */ 
/*  16:    */ public class GuiTimer
/*  17:    */   extends avf
/*  18:    */ {
/*  19:    */   private TileLogicPointer tileLogic;
/*  20: 17 */   private atb[] buttons = new atb[6];
/*  21:    */   
/*  22:    */   public GuiTimer(qw pli, TileLogicPointer te)
/*  23:    */   {
/*  24: 20 */     super(new ContainerTimer(pli, te));
/*  25: 21 */     this.b = 228;
/*  26: 22 */     this.c = 82;
/*  27: 23 */     this.tileLogic = te;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public GuiTimer(rq cn)
/*  31:    */   {
/*  32: 27 */     super(cn);
/*  33:    */     
/*  34: 29 */     this.b = 228;
/*  35: 30 */     this.c = 82;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void A_()
/*  39:    */   {
/*  40: 34 */     super.A_();
/*  41:    */     
/*  42: 36 */     int bw = this.b - 20;
/*  43: 37 */     int l = (this.g - this.b) / 2;
/*  44: 38 */     int m = (this.h - this.c) / 2;
/*  45:    */     
/*  46: 40 */     this.i.add(this.buttons[0] =  = new atb(1, l + 10, m + 50, bw / 6, 20, "-10s"));
/*  47: 41 */     this.i.add(this.buttons[1] =  = new atb(2, l + 10 + bw / 6, m + 50, bw / 6, 20, "-1s"));
/*  48: 42 */     this.i.add(this.buttons[2] =  = new atb(3, l + 10 + bw * 2 / 6, m + 50, bw / 6, 20, "-50ms"));
/*  49: 43 */     this.i.add(this.buttons[3] =  = new atb(4, l + 10 + bw * 3 / 6, m + 50, bw / 6, 20, "+50ms"));
/*  50: 44 */     this.i.add(this.buttons[4] =  = new atb(5, l + 10 + bw * 4 / 6, m + 50, bw / 6, 20, "+1s"));
/*  51: 45 */     this.i.add(this.buttons[5] =  = new atb(6, l + 10 + bw * 5 / 6, m + 50, bw / 6, 20, "+10s"));
/*  52:    */   }
/*  53:    */   
/*  54:    */   protected void b(int p1, int p2) {}
/*  55:    */   
/*  56:    */   protected void a(float f, int p1, int p2)
/*  57:    */   {
/*  58: 81 */     atq fontrenderer = this.f.p;
/*  59:    */     
/*  60:    */ 
/*  61: 84 */     int k = this.f.o.b("/eloraam/logic/timersgui.png");
/*  62:    */     
/*  63: 86 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  64: 87 */     this.f.o.b(k);
/*  65: 88 */     int l = (this.g - this.b) / 2;
/*  66: 89 */     int m = (this.h - this.c) / 2;
/*  67: 90 */     b(l, m, 0, 0, this.b, this.c);
/*  68:    */     
/*  69: 92 */     String str = String.format("Timer Interval: %.3fs", new Object[] { Double.valueOf(this.tileLogic.getInterval() / 20.0D) });
/*  70:    */     
/*  71: 94 */     a(fontrenderer, str, this.g / 2, m + 10, -1);
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void changeInterval(int cc)
/*  75:    */   {
/*  76:106 */     long iv = this.tileLogic.getInterval() + cc;
/*  77:107 */     if (iv < 4L) {
/*  78:107 */       iv = 4L;
/*  79:    */     }
/*  80:108 */     this.tileLogic.setInterval(iv);
/*  81:110 */     if (!this.f.e.I)
/*  82:    */     {
/*  83:111 */       this.tileLogic.updateBlock();
/*  84:112 */       return;
/*  85:    */     }
/*  86:115 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/*  87:116 */     pkt.eventId = 1;
/*  88:117 */     pkt.windowId = this.d.d;
/*  89:118 */     pkt.addUVLC(iv);
/*  90:119 */     pkt.encode();
/*  91:120 */     CoreProxy.sendPacketToServer(pkt);
/*  92:    */   }
/*  93:    */   
/*  94:    */   protected void a(atb guibutton)
/*  95:    */   {
/*  96:125 */     if (!guibutton.g) {
/*  97:125 */       return;
/*  98:    */     }
/*  99:126 */     switch (guibutton.f)
/* 100:    */     {
/* 101:    */     case 1: 
/* 102:127 */       changeInterval(-200); break;
/* 103:    */     case 2: 
/* 104:128 */       changeInterval(-20); break;
/* 105:    */     case 3: 
/* 106:129 */       changeInterval(-1); break;
/* 107:    */     case 4: 
/* 108:130 */       changeInterval(1); break;
/* 109:    */     case 5: 
/* 110:131 */       changeInterval(20); break;
/* 111:    */     case 6: 
/* 112:132 */       changeInterval(200);
/* 113:    */     }
/* 114:    */   }
/* 115:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.GuiTimer
 * JD-Core Version:    0.7.0.1
 */