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
/* 11:   */ public class GuiDeploy
/* 12:   */   extends avf
/* 13:   */ {
/* 14:   */   public GuiDeploy(qw pli, TileDeploy td)
/* 15:   */   {
/* 16:11 */     super(new ContainerDeploy(pli, td));
/* 17:   */   }
/* 18:   */   
/* 19:   */   public GuiDeploy(rq cn)
/* 20:   */   {
/* 21:15 */     super(cn);
/* 22:   */   }
/* 23:   */   
/* 24:   */   protected void b(int p1, int p2)
/* 25:   */   {
/* 26:20 */     this.l.b("Deployer", 60, 6, 4210752);
/* 27:21 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 28:   */   }
/* 29:   */   
/* 30:   */   protected void a(float f, int p1, int p2)
/* 31:   */   {
/* 32:27 */     int i = this.f.o.b("/gui/trap.png");
/* 33:28 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 34:29 */     this.f.o.b(i);
/* 35:30 */     int j = (this.g - this.b) / 2;
/* 36:31 */     int k = (this.h - this.c) / 2;
/* 37:32 */     b(j, k, 0, 0, this.b, this.c);
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiDeploy
 * JD-Core Version:    0.7.0.1
 */