/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import atq;
/*  4:   */ import avf;
/*  5:   */ import ayp;
/*  6:   */ import bba;
/*  7:   */ import com.eloraam.redpower.core.CoreProxy;
/*  8:   */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  9:   */ import net.minecraft.client.Minecraft;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ import qw;
/* 12:   */ import rq;
/* 13:   */ 
/* 14:   */ public class GuiItemDetect
/* 15:   */   extends avf
/* 16:   */ {
/* 17:   */   TileItemDetect tileDetect;
/* 18:   */   
/* 19:   */   public GuiItemDetect(qw pli, TileItemDetect filter)
/* 20:   */   {
/* 21:13 */     super(new ContainerItemDetect(pli, filter));
/* 22:14 */     this.tileDetect = filter;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public GuiItemDetect(rq cn)
/* 26:   */   {
/* 27:18 */     super(cn);
/* 28:   */   }
/* 29:   */   
/* 30:   */   protected void b(int p1, int p2)
/* 31:   */   {
/* 32:23 */     this.l.b("Item Detector", 60, 6, 4210752);
/* 33:24 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 34:   */   }
/* 35:   */   
/* 36:   */   protected void a(float f, int p1, int p2)
/* 37:   */   {
/* 38:30 */     int i = this.f.o.b("/eloraam/machine/itemdet.png");
/* 39:31 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 40:32 */     this.f.o.b(i);
/* 41:33 */     int j = (this.g - this.b) / 2;
/* 42:34 */     int k = (this.h - this.c) / 2;
/* 43:35 */     b(j, k, 0, 0, this.b, this.c);
/* 44:   */     
/* 45:   */ 
/* 46:38 */     b(j + 117, k + 54, 176, 14 * this.tileDetect.mode, 14, 14);
/* 47:   */   }
/* 48:   */   
/* 49:   */   void sendButton(int n)
/* 50:   */   {
/* 51:42 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 52:43 */     pkt.eventId = 1;
/* 53:44 */     pkt.windowId = this.d.d;
/* 54:45 */     pkt.addByte(n);
/* 55:46 */     pkt.encode();
/* 56:47 */     CoreProxy.sendPacketToServer(pkt);
/* 57:   */   }
/* 58:   */   
/* 59:   */   protected void a(int i, int j, int k)
/* 60:   */   {
/* 61:52 */     int x = i - (this.g - this.b) / 2;
/* 62:53 */     int y = j - (this.h - this.c) / 2;
/* 63:55 */     if ((x >= 117) && (y >= 54) && (x <= 131) && (y <= 68))
/* 64:   */     {
/* 65:56 */       if (k == 0)
/* 66:   */       {
/* 67:57 */         TileItemDetect tmp67_64 = this.tileDetect;tmp67_64.mode = ((byte)(tmp67_64.mode + 1));
/* 68:58 */         if (this.tileDetect.mode > 2) {
/* 69:58 */           this.tileDetect.mode = 0;
/* 70:   */         }
/* 71:   */       }
/* 72:   */       else
/* 73:   */       {
/* 74:60 */         TileItemDetect tmp103_100 = this.tileDetect;tmp103_100.mode = ((byte)(tmp103_100.mode - 1));
/* 75:61 */         if (this.tileDetect.mode < 0) {
/* 76:61 */           this.tileDetect.mode = 2;
/* 77:   */         }
/* 78:   */       }
/* 79:63 */       if (this.f.e.I) {
/* 80:64 */         sendButton(this.tileDetect.mode);
/* 81:   */       }
/* 82:   */     }
/* 83:66 */     super.a(i, j, k);
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiItemDetect
 * JD-Core Version:    0.7.0.1
 */