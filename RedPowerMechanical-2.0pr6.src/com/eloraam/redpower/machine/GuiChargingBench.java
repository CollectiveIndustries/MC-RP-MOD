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
/* 12:   */ public class GuiChargingBench
/* 13:   */   extends avf
/* 14:   */ {
/* 15:   */   TileChargingBench tileCB;
/* 16:   */   
/* 17:   */   public GuiChargingBench(qw pli, TileChargingBench cb)
/* 18:   */   {
/* 19:12 */     super(new ContainerChargingBench(pli, cb));
/* 20:13 */     this.tileCB = cb;
/* 21:14 */     this.c = 186;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public GuiChargingBench(rq cn)
/* 25:   */   {
/* 26:18 */     super(cn);
/* 27:19 */     this.c = 186;
/* 28:   */   }
/* 29:   */   
/* 30:   */   protected void b(int p1, int p2)
/* 31:   */   {
/* 32:25 */     this.l.b("Charging Bench", 60, 6, 4210752);
/* 33:26 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 34:   */   }
/* 35:   */   
/* 36:   */   protected void a(float f, int p1, int p2)
/* 37:   */   {
/* 38:32 */     int i = this.f.o.b("/eloraam/machine/charging.png");
/* 39:33 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 40:34 */     this.f.o.b(i);
/* 41:35 */     int j = (this.g - this.b) / 2;
/* 42:36 */     int k = (this.h - this.c) / 2;
/* 43:37 */     b(j, k, 0, 0, this.b, this.c);
/* 44:   */     
/* 45:   */ 
/* 46:40 */     int mx = this.tileCB.getMaxStorage();
/* 47:   */     
/* 48:   */ 
/* 49:43 */     int s = this.tileCB.getChargeScaled(48);
/* 50:44 */     b(j + 21, k + 78 - s, 176, 48 - s, 5, s);
/* 51:47 */     if (this.tileCB.cond.Charge > 600) {
/* 52:48 */       b(j + 22, k + 22, 197, 8, 3, 6);
/* 53:   */     }
/* 54:49 */     if ((this.tileCB.cond.Charge > 600) && (this.tileCB.Storage < mx)) {
/* 55:50 */       b(j + 32, k + 51, 197, 0, 10, 8);
/* 56:   */     }
/* 57:53 */     s = this.tileCB.getStorageScaled(48);
/* 58:54 */     b(j + 48, k + 78 - s, 181, 48 - s, 16, s);
/* 59:56 */     if (this.tileCB.Storage == mx) {
/* 60:57 */       b(j + 53, k + 22, 200, 8, 6, 6);
/* 61:   */     }
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.GuiChargingBench
 * JD-Core Version:    0.7.0.1
 */