/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import atq;
/*  4:   */ import avf;
/*  5:   */ import bba;
/*  6:   */ import net.minecraft.client.Minecraft;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ import qw;
/*  9:   */ import rq;
/* 10:   */ 
/* 11:   */ public class GuiAlloyFurnace
/* 12:   */   extends avf
/* 13:   */ {
/* 14:   */   TileAlloyFurnace furnace;
/* 15:   */   
/* 16:   */   public GuiAlloyFurnace(qw pli, TileAlloyFurnace td)
/* 17:   */   {
/* 18:11 */     super(new ContainerAlloyFurnace(pli, td));
/* 19:12 */     this.furnace = td;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public GuiAlloyFurnace(rq cn)
/* 23:   */   {
/* 24:16 */     super(cn);
/* 25:   */   }
/* 26:   */   
/* 27:   */   protected void b(int p1, int p2)
/* 28:   */   {
/* 29:22 */     this.l.b("Alloy Furnace", 60, 6, 4210752);
/* 30:23 */     this.l.b("Inventory", 8, this.c - 96 + 2, 4210752);
/* 31:   */   }
/* 32:   */   
/* 33:   */   protected void a(float f, int p1, int p2)
/* 34:   */   {
/* 35:29 */     int i = this.f.o.b("/eloraam/base/afurnacegui.png");
/* 36:30 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 37:31 */     this.f.o.b(i);
/* 38:32 */     int j = (this.g - this.b) / 2;
/* 39:33 */     int k = (this.h - this.c) / 2;
/* 40:34 */     b(j, k, 0, 0, this.b, this.c);
/* 41:36 */     if (this.furnace.burntime > 0)
/* 42:   */     {
/* 43:37 */       int l = this.furnace.getBurnScaled(12);
/* 44:38 */       b(j + 17, k + 25 + 12 - l, 176, 12 - l, 14, l + 2);
/* 45:   */     }
/* 46:40 */     int i1 = this.furnace.getCookScaled(24);
/* 47:41 */     b(j + 107, k + 34, 176, 14, i1 + 1, 16);
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.GuiAlloyFurnace
 * JD-Core Version:    0.7.0.1
 */