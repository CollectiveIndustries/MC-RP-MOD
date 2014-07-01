/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import atq;
/*  4:   */ import avf;
/*  5:   */ import bba;
/*  6:   */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*  7:   */ import net.minecraft.client.Minecraft;
/*  8:   */ import org.lwjgl.opengl.GL11;
/*  9:   */ import qw;
/* 10:   */ import rq;
/* 11:   */ 
/* 12:   */ public class GuiBlueFurnace
/* 13:   */   extends avf
/* 14:   */ {
/* 15:   */   TileBlueFurnace furnace;
/* 16:   */   
/* 17:   */   public GuiBlueFurnace(qw pli, TileBlueFurnace td)
/* 18:   */   {
/* 19:11 */     super(new ContainerBlueFurnace(pli, td));
/* 20:12 */     this.furnace = td;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public GuiBlueFurnace(rq cn)
/* 24:   */   {
/* 25:16 */     super(cn);
/* 26:   */   }
/* 27:   */   
/* 28:   */   protected void b(int p1, int p2)
/* 29:   */   {
/* 30:21 */     this.l.b("Blulectric Furnace", 48, 6, 4210752);
/* 31:22 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 32:   */   }
/* 33:   */   
/* 34:   */   protected void a(float f, int p1, int p2)
/* 35:   */   {
/* 36:28 */     int i = this.f.o.b("/eloraam/machine/btfurnace.png");
/* 37:29 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 38:30 */     this.f.o.b(i);
/* 39:31 */     int j = (this.g - this.b) / 2;
/* 40:32 */     int k = (this.h - this.c) / 2;
/* 41:33 */     b(j, k, 0, 0, this.b, this.c);
/* 42:   */     
/* 43:35 */     int s = this.furnace.getCookScaled(24);
/* 44:36 */     b(j + 89, k + 34, 176, 0, s + 1, 16);
/* 45:   */     
/* 46:   */ 
/* 47:39 */     s = this.furnace.cond.getChargeScaled(48);
/* 48:40 */     b(j + 25, k + 69 - s, 176, 65 - s, 5, s);
/* 49:   */     
/* 50:   */ 
/* 51:43 */     s = this.furnace.cond.getFlowScaled(48);
/* 52:44 */     b(j + 32, k + 69 - s, 176, 65 - s, 5, s);
/* 53:47 */     if (this.furnace.cond.Charge > 600) {
/* 54:48 */       b(j + 26, k + 13, 181, 17, 3, 6);
/* 55:   */     }
/* 56:51 */     if (this.furnace.cond.Flow == -1) {
/* 57:52 */       b(j + 33, k + 13, 184, 17, 3, 6);
/* 58:   */     }
/* 59:   */   }
/* 60:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiBlueFurnace
 * JD-Core Version:    0.7.0.1
 */