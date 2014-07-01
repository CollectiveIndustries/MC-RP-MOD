/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import amq;
/*  5:   */ import com.eloraam.redpower.RedPowerBase;
/*  6:   */ import com.eloraam.redpower.core.ItemParts;
/*  7:   */ import java.util.ArrayList;
/*  8:   */ import java.util.Random;
/*  9:   */ import ke;
/* 10:   */ import ur;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class BlockCustomOre
/* 14:   */   extends amq
/* 15:   */ {
/* 16:   */   public BlockCustomOre(int i)
/* 17:   */   {
/* 18:17 */     super(i, agi.e);
/* 19:18 */     c(3.0F);
/* 20:19 */     b(5.0F);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public float m(yc world, int x, int y, int z)
/* 24:   */   {
/* 25:25 */     return 3.0F;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int a(int i, int j)
/* 29:   */   {
/* 30:32 */     return 32 + j;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public int a(int i, Random random, int j)
/* 34:   */   {
/* 35:36 */     if ((i < 3) || (i == 7)) {
/* 36:36 */       return RedPowerBase.itemResource.cj;
/* 37:   */     }
/* 38:37 */     return this.cm;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public int quantityDropped(int i, int fortune, Random random)
/* 42:   */   {
/* 43:41 */     if (i == 7) {
/* 44:41 */       return 4 + random.nextInt(2) + random.nextInt(fortune + 1);
/* 45:   */     }
/* 46:42 */     if (i < 3)
/* 47:   */     {
/* 48:43 */       int b = random.nextInt(fortune + 2) - 1;
/* 49:44 */       if (b < 0) {
/* 50:44 */         b = 0;
/* 51:   */       }
/* 52:45 */       return b + 1;
/* 53:   */     }
/* 54:47 */     return 1;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public int b(int i)
/* 58:   */   {
/* 59:52 */     if (i == 7) {
/* 60:52 */       return 6;
/* 61:   */     }
/* 62:53 */     return i;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void addCreativeItems(ArrayList itemList)
/* 66:   */   {
/* 67:57 */     for (int i = 0; i <= 7; i++) {
/* 68:58 */       itemList.add(new ur(this, 1, i));
/* 69:   */     }
/* 70:   */   }
/* 71:   */   
/* 72:   */   public void a(yc world, int x, int y, int z, int md, float chance, int fortune)
/* 73:   */   {
/* 74:66 */     super.a(world, x, y, z, md, chance, fortune);
/* 75:   */     
/* 76:68 */     int min = 0;int max = 0;
/* 77:69 */     switch (md)
/* 78:   */     {
/* 79:   */     case 0: 
/* 80:   */     case 1: 
/* 81:   */     case 2: 
/* 82:71 */       min = 3;max = 7;
/* 83:72 */       break;
/* 84:   */     case 7: 
/* 85:74 */       min = 1;max = 5;
/* 86:   */     }
/* 87:77 */     if (max > 0) {
/* 88:78 */       f(world, x, y, z, ke.a(world.t, min, max));
/* 89:   */     }
/* 90:   */   }
/* 91:   */   
/* 92:   */   public String getTextureFile()
/* 93:   */   {
/* 94:85 */     return "/eloraam/world/world1.png";
/* 95:   */   }
/* 96:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockCustomOre
 * JD-Core Version:    0.7.0.1
 */