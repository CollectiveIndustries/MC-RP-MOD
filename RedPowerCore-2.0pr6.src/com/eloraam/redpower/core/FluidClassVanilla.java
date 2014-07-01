/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import net.minecraftforge.liquids.LiquidStack;
/*  4:   */ import yc;
/*  5:   */ 
/*  6:   */ public class FluidClassVanilla
/*  7:   */   extends FluidClass
/*  8:   */ {
/*  9:   */   int fluidID;
/* 10:   */   int idStill;
/* 11:   */   int idMoving;
/* 12:   */   String texFile;
/* 13:   */   int texture;
/* 14:   */   
/* 15:   */   public FluidClassVanilla(int id, int idstill, int idmoving, String texfile, int tex)
/* 16:   */   {
/* 17:12 */     this.idStill = idstill;this.idMoving = idmoving;
/* 18:13 */     this.texture = tex;this.texFile = texfile;
/* 19:14 */     this.fluidID = id;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int getFluidId()
/* 23:   */   {
/* 24:23 */     return this.fluidID;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int getFluidId(yc world, WorldCoord wc)
/* 28:   */   {
/* 29:27 */     int bid = world.a(wc.x, wc.y, wc.z);
/* 30:28 */     if ((bid == this.idStill) || (bid == this.idMoving)) {
/* 31:29 */       return this.fluidID;
/* 32:   */     }
/* 33:30 */     return 0;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public int getFluidLevel(yc world, WorldCoord wc)
/* 37:   */   {
/* 38:34 */     int md = world.h(wc.x, wc.y, wc.z);
/* 39:36 */     if (md == 0) {
/* 40:36 */       return 1000;
/* 41:   */     }
/* 42:37 */     return 0;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public boolean setFluidLevel(yc world, WorldCoord wc, int level)
/* 46:   */   {
/* 47:41 */     if (level == 1000)
/* 48:   */     {
/* 49:42 */       world.e(wc.x, wc.y, wc.z, this.idMoving);
/* 50:43 */       return true;
/* 51:   */     }
/* 52:45 */     return false;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public int getFluidQuanta()
/* 56:   */   {
/* 57:49 */     return 1000;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public String getTextureFile()
/* 61:   */   {
/* 62:53 */     return this.texFile;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public int getTexture()
/* 66:   */   {
/* 67:57 */     return this.texture;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public LiquidStack getLiquidStack(int size)
/* 71:   */   {
/* 72:61 */     return new LiquidStack(this.idStill, size, 0);
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.FluidClassVanilla
 * JD-Core Version:    0.7.0.1
 */