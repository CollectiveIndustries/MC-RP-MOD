/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import atq;
/*  4:   */ import avf;
/*  5:   */ import bba;
/*  6:   */ import la;
/*  7:   */ import net.minecraft.client.Minecraft;
/*  8:   */ import org.lwjgl.opengl.GL11;
/*  9:   */ import qw;
/* 10:   */ import rq;
/* 11:   */ 
/* 12:   */ public class GuiSeedBag
/* 13:   */   extends avf
/* 14:   */ {
/* 15:   */   public GuiSeedBag(qw pli, la td)
/* 16:   */   {
/* 17:12 */     super(new ContainerSeedBag(pli, td, null));
/* 18:13 */     this.c = 167;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public GuiSeedBag(rq cn)
/* 22:   */   {
/* 23:17 */     super(cn);
/* 24:18 */     this.c = 167;
/* 25:   */   }
/* 26:   */   
/* 27:   */   protected void b(int p1, int p2)
/* 28:   */   {
/* 29:23 */     this.l.b("Seed Bag", 65, 6, 4210752);
/* 30:24 */     this.l.b("Inventory", 8, this.c - 94 + 2, 4210752);
/* 31:   */   }
/* 32:   */   
/* 33:   */   protected void a(float f, int p1, int p2)
/* 34:   */   {
/* 35:30 */     int i = this.f.o.b("/gui/trap.png");
/* 36:31 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 37:32 */     this.f.o.b(i);
/* 38:33 */     int j = (this.g - this.b) / 2;
/* 39:34 */     int k = (this.h - this.c) / 2;
/* 40:35 */     b(j, k, 0, 0, this.b, this.c);
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.GuiSeedBag
 * JD-Core Version:    0.7.0.1
 */