/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import atq;
/*  4:   */ import avf;
/*  5:   */ import bba;
/*  6:   */ import net.minecraft.client.Minecraft;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ import qw;
/*  9:   */ import rq;
/* 10:   */ 
/* 11:   */ public class GuiBufferChest
/* 12:   */   extends avf
/* 13:   */ {
/* 14:   */   public GuiBufferChest(qw pli, TileBufferChest td)
/* 15:   */   {
/* 16:11 */     super(new ContainerBufferChest(pli, td));
/* 17:12 */     this.c = 186;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public GuiBufferChest(rq cn)
/* 21:   */   {
/* 22:16 */     super(cn);
/* 23:17 */     this.c = 186;
/* 24:   */   }
/* 25:   */   
/* 26:   */   protected void b(int p1, int p2)
/* 27:   */   {
/* 28:22 */     this.l.b("Buffer", 70, 6, 4210752);
/* 29:23 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 30:   */   }
/* 31:   */   
/* 32:   */   protected void a(float f, int p1, int p2)
/* 33:   */   {
/* 34:29 */     int i = this.f.o.b("/eloraam/machine/buffer.png");
/* 35:30 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 36:31 */     this.f.o.b(i);
/* 37:32 */     int j = (this.g - this.b) / 2;
/* 38:33 */     int k = (this.h - this.c) / 2;
/* 39:34 */     b(j, k, 0, 0, this.b, this.c);
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiBufferChest
 * JD-Core Version:    0.7.0.1
 */