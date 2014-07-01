/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import net.minecraftforge.liquids.LiquidStack;
/*  4:   */ import yc;
/*  5:   */ 
/*  6:   */ public class FluidClassItem
/*  7:   */   extends FluidClass
/*  8:   */ {
/*  9:   */   int fluidID;
/* 10:   */   int itemID;
/* 11:   */   int itemMeta;
/* 12:   */   String texFile;
/* 13:   */   int texture;
/* 14:   */   
/* 15:   */   public FluidClassItem(int id, int item, int md, String texfile, int tex)
/* 16:   */   {
/* 17:12 */     this.fluidID = id;
/* 18:13 */     this.itemID = item;this.itemMeta = md;
/* 19:14 */     this.texture = tex;this.texFile = texfile;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int getFluidId()
/* 23:   */   {
/* 24:23 */     return this.fluidID;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int getFluidId(yc world, WorldCoord wc)
/* 28:   */   {
/* 29:27 */     return 0;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getFluidLevel(yc world, WorldCoord wc)
/* 33:   */   {
/* 34:31 */     return 0;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public boolean setFluidLevel(yc world, WorldCoord wc, int level)
/* 38:   */   {
/* 39:35 */     return false;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public int getFluidQuanta()
/* 43:   */   {
/* 44:39 */     return 0;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public String getTextureFile()
/* 48:   */   {
/* 49:43 */     return this.texFile;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public int getTexture()
/* 53:   */   {
/* 54:47 */     return this.texture;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public LiquidStack getLiquidStack(int size)
/* 58:   */   {
/* 59:51 */     return new LiquidStack(this.itemID, size, this.itemMeta);
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.FluidClassItem
 * JD-Core Version:    0.7.0.1
 */