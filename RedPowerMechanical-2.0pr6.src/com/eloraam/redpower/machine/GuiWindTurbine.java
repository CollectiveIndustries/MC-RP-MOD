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
/* 11:   */ public class GuiWindTurbine
/* 12:   */   extends avf
/* 13:   */ {
/* 14:   */   TileWindTurbine tileWT;
/* 15:   */   
/* 16:   */   public GuiWindTurbine(qw pli, TileWindTurbine wt)
/* 17:   */   {
/* 18:12 */     super(new ContainerWindTurbine(pli, wt));
/* 19:13 */     this.tileWT = wt;
/* 20:14 */     this.c = 167;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public GuiWindTurbine(rq cn)
/* 24:   */   {
/* 25:18 */     super(cn);
/* 26:19 */     this.c = 167;
/* 27:   */   }
/* 28:   */   
/* 29:   */   protected void b(int p1, int p2)
/* 30:   */   {
/* 31:24 */     this.l.b("Wind Turbine", 60, 6, 4210752);
/* 32:25 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 33:   */   }
/* 34:   */   
/* 35:   */   protected void a(float f, int p1, int p2)
/* 36:   */   {
/* 37:31 */     int i = this.f.o.b("/eloraam/machine/windgui.png");
/* 38:32 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 39:33 */     this.f.o.b(i);
/* 40:34 */     int j = (this.g - this.b) / 2;
/* 41:35 */     int k = (this.h - this.c) / 2;
/* 42:36 */     b(j, k, 0, 0, this.b, this.c);
/* 43:   */     
/* 44:   */ 
/* 45:39 */     b(j + 55, k + 65 - this.tileWT.getWindScaled(48), 176, 0, 5, 3);
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiWindTurbine
 * JD-Core Version:    0.7.0.1
 */