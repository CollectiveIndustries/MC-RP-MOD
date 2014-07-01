/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import amq;
/*  5:   */ import com.eloraam.redpower.core.IBlockHardness;
/*  6:   */ import cpw.mods.fml.relauncher.Side;
/*  7:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  8:   */ import java.util.List;
/*  9:   */ import java.util.Random;
/* 10:   */ import lq;
/* 11:   */ import tj;
/* 12:   */ import ur;
/* 13:   */ import yc;
/* 14:   */ 
/* 15:   */ public class BlockCustomStone
/* 16:   */   extends amq
/* 17:   */   implements IBlockHardness
/* 18:   */ {
/* 19:   */   public BlockCustomStone(int i)
/* 20:   */   {
/* 21:20 */     super(i, agi.e);
/* 22:21 */     c(3.0F);
/* 23:22 */     b(10.0F);
/* 24:23 */     a(tj.b);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public float getPrototypicalHardness(int md)
/* 28:   */   {
/* 29:28 */     switch (md)
/* 30:   */     {
/* 31:   */     case 0: 
/* 32:29 */       return 1.0F;
/* 33:   */     case 1: 
/* 34:30 */       return 2.5F;
/* 35:   */     case 2: 
/* 36:31 */       return 1.0F;
/* 37:   */     case 3: 
/* 38:32 */       return 2.5F;
/* 39:   */     case 4: 
/* 40:33 */       return 2.5F;
/* 41:   */     case 5: 
/* 42:34 */       return 2.5F;
/* 43:   */     case 6: 
/* 44:35 */       return 2.5F;
/* 45:   */     }
/* 46:36 */     return 3.0F;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public float m(yc world, int x, int y, int z)
/* 50:   */   {
/* 51:42 */     int md = world.h(x, y, z);
/* 52:43 */     return getPrototypicalHardness(md);
/* 53:   */   }
/* 54:   */   
/* 55:   */   public float getExplosionResistance(lq exploder, yc world, int X, int Y, int Z, double srcX, double srcY, double srcZ)
/* 56:   */   {
/* 57:51 */     int md = world.h(X, Y, Z);
/* 58:52 */     switch (md)
/* 59:   */     {
/* 60:   */     case 1: 
/* 61:   */     case 3: 
/* 62:   */     case 4: 
/* 63:   */     case 5: 
/* 64:   */     case 6: 
/* 65:54 */       return 12.0F;
/* 66:   */     }
/* 67:56 */     return 6.0F;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public int a(int i, int j)
/* 71:   */   {
/* 72:61 */     return 16 + j;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public int a(int i, Random random, int j)
/* 76:   */   {
/* 77:65 */     return this.cm;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public int a(Random random)
/* 81:   */   {
/* 82:69 */     return 1;
/* 83:   */   }
/* 84:   */   
/* 85:   */   public int b(int i)
/* 86:   */   {
/* 87:74 */     if (i == 1) {
/* 88:74 */       return 3;
/* 89:   */     }
/* 90:75 */     if (i == 6) {
/* 91:75 */       return 3;
/* 92:   */     }
/* 93:76 */     return i;
/* 94:   */   }
/* 95:   */   
/* 96:   */   @SideOnly(Side.CLIENT)
/* 97:   */   public void a(int id, tj tab, List list)
/* 98:   */   {
/* 99:82 */     for (int i = 0; i <= 6; i++) {
/* :0:83 */       list.add(new ur(this, 1, i));
/* :1:   */     }
/* :2:   */   }
/* :3:   */   
/* :4:   */   public String getTextureFile()
/* :5:   */   {
/* :6:88 */     return "/eloraam/world/world1.png";
/* :7:   */   }
/* :8:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockCustomStone
 * JD-Core Version:    0.7.0.1
 */