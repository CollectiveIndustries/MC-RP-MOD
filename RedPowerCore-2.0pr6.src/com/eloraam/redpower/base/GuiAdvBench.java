/*   1:    */ package com.eloraam.redpower.base;
/*   2:    */ 
/*   3:    */ import arw;
/*   4:    */ import atq;
/*   5:    */ import avf;
/*   6:    */ import bba;
/*   7:    */ import bce;
/*   8:    */ import bfe;
/*   9:    */ import com.eloraam.redpower.RedPowerBase;
/*  10:    */ import com.eloraam.redpower.core.CoreProxy;
/*  11:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  12:    */ import net.minecraft.client.Minecraft;
/*  13:    */ import org.lwjgl.opengl.GL11;
/*  14:    */ import qw;
/*  15:    */ import rq;
/*  16:    */ import sr;
/*  17:    */ import up;
/*  18:    */ import ur;
/*  19:    */ 
/*  20:    */ public class GuiAdvBench
/*  21:    */   extends avf
/*  22:    */ {
/*  23:    */   TileAdvBench bench;
/*  24:    */   
/*  25:    */   public GuiAdvBench(qw pli, TileAdvBench td)
/*  26:    */   {
/*  27: 21 */     super(new ContainerAdvBench(pli, td));
/*  28: 22 */     this.bench = td;
/*  29: 23 */     this.c = 222;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public GuiAdvBench(rq cn)
/*  33:    */   {
/*  34: 27 */     super(cn);
/*  35: 28 */     this.c = 222;
/*  36:    */   }
/*  37:    */   
/*  38:    */   protected void b(int p1, int p2)
/*  39:    */   {
/*  40: 34 */     this.l.b("Project Table", 60, 6, 4210752);
/*  41: 35 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/*  42:    */   }
/*  43:    */   
/*  44:    */   protected void a(float f, int p1, int p2)
/*  45:    */   {
/*  46: 41 */     int i = this.f.o.b("/eloraam/base/advbench.png");
/*  47: 42 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  48: 43 */     this.f.o.b(i);
/*  49: 44 */     int j = (this.g - this.b) / 2;
/*  50: 45 */     int k = (this.h - this.c) / 2;
/*  51: 46 */     b(j, k, 0, 0, this.b, this.c);
/*  52:    */     
/*  53: 48 */     ur plan = this.d.a(9).c();
/*  54: 49 */     ur craft = this.d.a(10).c();
/*  55: 51 */     if ((plan != null) && (craft != null) && (plan.c == RedPowerBase.itemPlanBlank.cj)) {
/*  56: 54 */       b(j + 18, k + 55, 176, 0, 14, 14);
/*  57:    */     }
/*  58: 57 */     if ((plan != null) && (plan.c == RedPowerBase.itemPlanFull.cj))
/*  59:    */     {
/*  60: 59 */       ContainerAdvBench cont = (ContainerAdvBench)this.d;
/*  61: 60 */       ur[] ist = ContainerAdvBench.getShadowItems(plan);
/*  62:    */       
/*  63: 62 */       arw.c();
/*  64: 63 */       bfe.a(bfe.b, 240.0F, 240.0F);
/*  65: 64 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  66: 65 */       GL11.glEnable(32826);
/*  67: 66 */       GL11.glEnable(2896);
/*  68: 67 */       GL11.glEnable(2929);
/*  69: 68 */       for (int n = 0; n < 9; n++) {
/*  70: 69 */         if (ist[n] != null)
/*  71:    */         {
/*  72: 70 */           sr sl = this.d.a(n);
/*  73: 71 */           if (sl.c() == null)
/*  74:    */           {
/*  75: 72 */             int slx = sl.h + j;
/*  76: 73 */             int sly = sl.i + k;
/*  77:    */             
/*  78: 75 */             a.a(this.l, this.f.o, ist[n], slx, sly);
/*  79:    */             
/*  80: 77 */             a.c(this.l, this.f.o, ist[n], slx, sly);
/*  81:    */           }
/*  82:    */         }
/*  83:    */       }
/*  84: 81 */       GL11.glDisable(2896);
/*  85: 82 */       GL11.glDisable(2929);
/*  86:    */       
/*  87: 84 */       GL11.glEnable(3042);
/*  88: 85 */       this.f.o.b(i);
/*  89: 86 */       for (int n = 0; n < 9; n++) {
/*  90: 87 */         if (ist[n] != null)
/*  91:    */         {
/*  92: 88 */           sr sl = this.d.a(n);
/*  93: 89 */           if (sl.c() == null)
/*  94:    */           {
/*  95: 90 */             int slx = sl.h;
/*  96: 91 */             int sly = sl.i;
/*  97: 93 */             if ((cont.satisfyMask & 1 << n) > 0) {
/*  98: 94 */               GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/*  99:    */             } else {
/* 100: 96 */               GL11.glColor4f(1.0F, 0.1F, 0.1F, 0.6F);
/* 101:    */             }
/* 102: 99 */             b(j + slx, k + sly, slx, sly, 16, 16);
/* 103:    */           }
/* 104:    */         }
/* 105:    */       }
/* 106:102 */       GL11.glDisable(3042);
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   protected void a(int i, int j, int k)
/* 111:    */   {
/* 112:108 */     int x = i - (this.g - this.b) / 2;
/* 113:109 */     int y = j - (this.h - this.c) / 2;
/* 114:111 */     if ((x >= 18) && (y >= 55) && (x <= 32) && (y <= 69))
/* 115:    */     {
/* 116:112 */       ur plan = this.d.a(9).c();
/* 117:113 */       ur craft = this.d.a(10).c();
/* 118:115 */       if ((plan == null) || (craft == null) || (plan.c != RedPowerBase.itemPlanBlank.cj)) {
/* 119:118 */         return;
/* 120:    */       }
/* 121:120 */       Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 122:121 */       pkt.eventId = 1;
/* 123:122 */       pkt.windowId = this.d.d;
/* 124:123 */       pkt.encode();
/* 125:124 */       CoreProxy.sendPacketToServer(pkt);
/* 126:    */     }
/* 127:126 */     super.a(i, j, k);
/* 128:    */   }
/* 129:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.GuiAdvBench
 * JD-Core Version:    0.7.0.1
 */