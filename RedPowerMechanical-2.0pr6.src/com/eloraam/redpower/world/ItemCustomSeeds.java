/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import com.eloraam.redpower.RedPowerWorld;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import net.minecraftforge.common.EnumPlantType;
/*  7:   */ import net.minecraftforge.common.ForgeDirection;
/*  8:   */ import net.minecraftforge.common.IPlantable;
/*  9:   */ import qx;
/* 10:   */ import tj;
/* 11:   */ import up;
/* 12:   */ import ur;
/* 13:   */ import yc;
/* 14:   */ 
/* 15:   */ public class ItemCustomSeeds
/* 16:   */   extends up
/* 17:   */   implements IPlantable
/* 18:   */ {
/* 19:   */   public ItemCustomSeeds(int i)
/* 20:   */   {
/* 21:22 */     super(i);
/* 22:23 */     e(0);
/* 23:24 */     a(true);
/* 24:25 */     a(tj.l);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int b(int i)
/* 28:   */   {
/* 29:29 */     switch (i)
/* 30:   */     {
/* 31:   */     case 0: 
/* 32:30 */       return 3;
/* 33:   */     }
/* 34:32 */     return 0;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public boolean a(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/* 38:   */   {
/* 39:39 */     if (l != 1) {
/* 40:39 */       return false;
/* 41:   */     }
/* 42:41 */     int bid = world.a(i, j, k);
/* 43:42 */     amq soil = amq.p[bid];
/* 44:43 */     if (soil == null) {
/* 45:43 */       return false;
/* 46:   */     }
/* 47:45 */     if ((soil.canSustainPlant(world, i, j, k, ForgeDirection.UP, this)) && (world.c(i, j + 1, k)))
/* 48:   */     {
/* 49:47 */       world.c(i, j + 1, k, RedPowerWorld.blockCrops.cm, 0);
/* 50:   */       
/* 51:49 */       ist.a -= 1;
/* 52:50 */       return true;
/* 53:   */     }
/* 54:52 */     return false;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public String d(ur itemstack)
/* 58:   */   {
/* 59:56 */     switch (itemstack.j())
/* 60:   */     {
/* 61:   */     case 0: 
/* 62:57 */       return "item.seedFlax";
/* 63:   */     }
/* 64:59 */     throw new IndexOutOfBoundsException();
/* 65:   */   }
/* 66:   */   
/* 67:   */   public void addCreativeItems(ArrayList itemList)
/* 68:   */   {
/* 69:63 */     for (int i = 0; i <= 0; i++) {
/* 70:64 */       itemList.add(new ur(this, 1, i));
/* 71:   */     }
/* 72:   */   }
/* 73:   */   
/* 74:   */   public String getTextureFile()
/* 75:   */   {
/* 76:69 */     return "/eloraam/world/worlditems1.png";
/* 77:   */   }
/* 78:   */   
/* 79:   */   public EnumPlantType getPlantType(yc world, int x, int y, int z)
/* 80:   */   {
/* 81:74 */     return EnumPlantType.Crop;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public int getPlantID(yc world, int x, int y, int z)
/* 85:   */   {
/* 86:79 */     return RedPowerWorld.blockCrops.cm;
/* 87:   */   }
/* 88:   */   
/* 89:   */   public int getPlantMetadata(yc world, int x, int y, int z)
/* 90:   */   {
/* 91:84 */     return 0;
/* 92:   */   }
/* 93:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemCustomSeeds
 * JD-Core Version:    0.7.0.1
 */