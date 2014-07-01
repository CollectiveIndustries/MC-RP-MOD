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
/* 11:   */ public class GuiEject
/* 12:   */   extends avf
/* 13:   */ {
/* 14:   */   TileEjectBase tileEject;
/* 15:   */   
/* 16:   */   public GuiEject(qw pli, TileEjectBase td)
/* 17:   */   {
/* 18:11 */     super(new ContainerEject(pli, td));
/* 19:12 */     this.tileEject = td;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public GuiEject(rq cn)
/* 23:   */   {
/* 24:16 */     super(cn);
/* 25:   */   }
/* 26:   */   
/* 27:   */   protected void b(int p1, int p2)
/* 28:   */   {
/* 29:21 */     this.l.b(this.tileEject.b(), 60, 6, 4210752);
/* 30:22 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 31:   */   }
/* 32:   */   
/* 33:   */   protected void a(float f, int p1, int p2)
/* 34:   */   {
/* 35:28 */     int i = this.f.o.b("/gui/trap.png");
/* 36:29 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 37:30 */     this.f.o.b(i);
/* 38:31 */     int j = (this.g - this.b) / 2;
/* 39:32 */     int k = (this.h - this.c) / 2;
/* 40:33 */     b(j, k, 0, 0, this.b, this.c);
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiEject
 * JD-Core Version:    0.7.0.1
 */