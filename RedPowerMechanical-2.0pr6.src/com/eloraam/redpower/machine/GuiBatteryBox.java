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
/* 11:   */ public class GuiBatteryBox
/* 12:   */   extends avf
/* 13:   */ {
/* 14:   */   TileBatteryBox tileBB;
/* 15:   */   
/* 16:   */   public GuiBatteryBox(qw pli, TileBatteryBox bb)
/* 17:   */   {
/* 18:12 */     super(new ContainerBatteryBox(pli, bb));
/* 19:13 */     this.tileBB = bb;
/* 20:14 */     this.c = 170;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public GuiBatteryBox(rq cn)
/* 24:   */   {
/* 25:18 */     super(cn);
/* 26:19 */     this.c = 170;
/* 27:   */   }
/* 28:   */   
/* 29:   */   protected void b(int p1, int p2)
/* 30:   */   {
/* 31:24 */     this.l.b("Battery Box", 60, 6, 4210752);
/* 32:25 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 33:   */   }
/* 34:   */   
/* 35:   */   protected void a(float f, int p1, int p2)
/* 36:   */   {
/* 37:31 */     int i = this.f.o.b("/eloraam/machine/batbox.png");
/* 38:32 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 39:33 */     this.f.o.b(i);
/* 40:34 */     int j = (this.g - this.b) / 2;
/* 41:35 */     int k = (this.h - this.c) / 2;
/* 42:36 */     b(j, k, 0, 0, this.b, this.c);
/* 43:   */     
/* 44:   */ 
/* 45:39 */     int mx = this.tileBB.getMaxStorage();
/* 46:   */     
/* 47:   */ 
/* 48:42 */     int s = this.tileBB.getChargeScaled(48);
/* 49:43 */     b(j + 71, k + 73 - s, 176, 48 - s, 5, s);
/* 50:46 */     if (this.tileBB.Charge > 600) {
/* 51:47 */       b(j + 72, k + 17, 197, 16, 3, 6);
/* 52:   */     }
/* 53:48 */     if ((this.tileBB.Charge > 900) && (this.tileBB.Storage < mx)) {
/* 54:49 */       b(j + 82, k + 37, 197, 0, 10, 8);
/* 55:   */     }
/* 56:50 */     if ((this.tileBB.Charge < 800) && (this.tileBB.Storage > 0)) {
/* 57:51 */       b(j + 82, k + 55, 197, 8, 10, 8);
/* 58:   */     }
/* 59:54 */     s = this.tileBB.getStorageScaled(48);
/* 60:55 */     b(j + 98, k + 73 - s, 181, 48 - s, 16, s);
/* 61:57 */     if (this.tileBB.Storage == mx) {
/* 62:58 */       b(j + 103, k + 17, 200, 16, 6, 6);
/* 63:   */     }
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiBatteryBox
 * JD-Core Version:    0.7.0.1
 */