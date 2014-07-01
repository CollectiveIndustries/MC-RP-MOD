/*   1:    */ package com.eloraam.redpower.world;
/*   2:    */ 
/*   3:    */ import aax;
/*   4:    */ import abc;
/*   5:    */ import abn;
/*   6:    */ import com.eloraam.redpower.RedPowerWorld;
/*   7:    */ import com.eloraam.redpower.core.Config;
/*   8:    */ import cpw.mods.fml.common.IWorldGenerator;
/*   9:    */ import java.util.Random;
/*  10:    */ import yc;
/*  11:    */ import yy;
/*  12:    */ import zd;
/*  13:    */ import zw;
/*  14:    */ 
/*  15:    */ public class WorldGenHandler
/*  16:    */   implements IWorldGenerator
/*  17:    */ {
/*  18:    */   public void generate(Random rin, int chunkX, int chunkZ, yc world, zw generator, zw provider)
/*  19:    */   {
/*  20: 24 */     if (((generator instanceof aax)) || ((generator instanceof abc))) {
/*  21: 26 */       return;
/*  22:    */     }
/*  23: 28 */     Random rand = new Random(Integer.valueOf(chunkX).hashCode() * 31 + Integer.valueOf(chunkZ).hashCode());
/*  24: 32 */     for (int a = 0; a < 2; a++)
/*  25:    */     {
/*  26: 33 */       int x = chunkX * 16 + rand.nextInt(16);
/*  27: 34 */       int y = rand.nextInt(48);
/*  28: 35 */       int z = chunkZ * 16 + rand.nextInt(16);
/*  29: 36 */       new WorldGenCustomOre(RedPowerWorld.blockOres.cm, 0, 7).a(world, rand, x, y, z);
/*  30:    */     }
/*  31: 40 */     for (a = 0; a < 2; a++)
/*  32:    */     {
/*  33: 41 */       int x = chunkX * 16 + rand.nextInt(16);
/*  34: 42 */       int y = rand.nextInt(48);
/*  35: 43 */       int z = chunkZ * 16 + rand.nextInt(16);
/*  36: 44 */       new WorldGenCustomOre(RedPowerWorld.blockOres.cm, 1, 7).a(world, rand, x, y, z);
/*  37:    */     }
/*  38: 48 */     for (a = 0; a < 2; a++)
/*  39:    */     {
/*  40: 49 */       int x = chunkX * 16 + rand.nextInt(16);
/*  41: 50 */       int y = rand.nextInt(48);
/*  42: 51 */       int z = chunkZ * 16 + rand.nextInt(16);
/*  43: 52 */       new WorldGenCustomOre(RedPowerWorld.blockOres.cm, 2, 7).a(world, rand, x, y, z);
/*  44:    */     }
/*  45: 58 */     if (Config.getInt("settings.world.generate.silver") > 0) {
/*  46: 59 */       for (a = 0; a < 4; a++)
/*  47:    */       {
/*  48: 60 */         int x = chunkX * 16 + rand.nextInt(16);
/*  49: 61 */         int y = rand.nextInt(32);
/*  50: 62 */         int z = chunkZ * 16 + rand.nextInt(16);
/*  51: 63 */         new WorldGenCustomOre(RedPowerWorld.blockOres.cm, 3, 8).a(world, rand, x, y, z);
/*  52:    */       }
/*  53:    */     }
/*  54: 70 */     if (Config.getInt("settings.world.generate.tin") > 0) {
/*  55: 71 */       for (a = 0; a < 10; a++)
/*  56:    */       {
/*  57: 72 */         int x = chunkX * 16 + rand.nextInt(16);
/*  58: 73 */         int y = rand.nextInt(48);
/*  59: 74 */         int z = chunkZ * 16 + rand.nextInt(16);
/*  60: 75 */         new WorldGenCustomOre(RedPowerWorld.blockOres.cm, 4, 8).a(world, rand, x, y, z);
/*  61:    */       }
/*  62:    */     }
/*  63: 82 */     if (Config.getInt("settings.world.generate.copper") > 0) {
/*  64: 83 */       for (a = 0; a < 20; a++)
/*  65:    */       {
/*  66: 84 */         int x = chunkX * 16 + rand.nextInt(16);
/*  67: 85 */         int y = rand.nextInt(64);
/*  68: 86 */         int z = chunkZ * 16 + rand.nextInt(16);
/*  69: 87 */         new WorldGenCustomOre(RedPowerWorld.blockOres.cm, 5, 8).a(world, rand, x, y, z);
/*  70:    */       }
/*  71:    */     }
/*  72: 94 */     for (a = 0; a < 1; a++)
/*  73:    */     {
/*  74: 95 */       int x = chunkX * 16 + rand.nextInt(16);
/*  75: 96 */       int y = rand.nextInt(16);
/*  76: 97 */       int z = chunkZ * 16 + rand.nextInt(16);
/*  77: 98 */       new WorldGenCustomOre(RedPowerWorld.blockOres.cm, 6, 4).a(world, rand, x, y, z);
/*  78:    */     }
/*  79:103 */     for (a = 0; a < 4; a++)
/*  80:    */     {
/*  81:104 */       int x = chunkX * 16 + rand.nextInt(16);
/*  82:105 */       int y = rand.nextInt(16);
/*  83:106 */       int z = chunkZ * 16 + rand.nextInt(16);
/*  84:107 */       new WorldGenCustomOre(RedPowerWorld.blockOres.cm, 7, 10).a(world, rand, x, y, z);
/*  85:    */     }
/*  86:113 */     for (a = 0; a < 4; a++)
/*  87:    */     {
/*  88:114 */       int x = chunkX * 16 + rand.nextInt(16);
/*  89:115 */       int y = 32 + rand.nextInt(32);
/*  90:116 */       int z = chunkZ * 16 + rand.nextInt(16);
/*  91:117 */       new WorldGenMarble(RedPowerWorld.blockStone.cm, 0, rand.nextInt(4096)).a(world, rand, x, y, z);
/*  92:    */     }
/*  93:124 */     int vc = Math.max(1, rand.nextInt(10) - 6);
/*  94:125 */     vc *= vc;
/*  95:126 */     for (a = 0; a < vc; a++)
/*  96:    */     {
/*  97:127 */       int x = chunkX * 16 + rand.nextInt(16);
/*  98:128 */       int y = rand.nextInt(32);
/*  99:129 */       int z = chunkZ * 16 + rand.nextInt(16);
/* 100:130 */       new WorldGenVolcano(RedPowerWorld.blockStone.cm, 1, rand.nextInt(65536)).a(world, rand, x, y, z);
/* 101:    */     }
/* 102:136 */     yy bgb = world.t().a(chunkX * 16 + 16, chunkZ * 16 + 16);
/* 103:    */     
/* 104:    */ 
/* 105:139 */     int n = 0;
/* 106:140 */     if (bgb == yy.w) {
/* 107:140 */       n = 1;
/* 108:141 */     } else if (bgb == yy.x) {
/* 109:141 */       n = 1;
/* 110:142 */     } else if (bgb == yy.f) {
/* 111:142 */       n = 1;
/* 112:143 */     } else if (bgb == yy.c) {
/* 113:143 */       n = 4;
/* 114:    */     }
/* 115:148 */     for (a = 0; a < n; a++)
/* 116:    */     {
/* 117:149 */       int x = chunkX * 16 + rand.nextInt(16) + 8;
/* 118:150 */       int y = rand.nextInt(128);
/* 119:151 */       int z = chunkZ * 16 + rand.nextInt(16) + 8;
/* 120:152 */       new abn(RedPowerWorld.blockPlants.cm).a(world, rand, x, y, z);
/* 121:    */     }
/* 122:157 */     if ((bgb == yy.w) || (bgb == yy.x)) {
/* 123:158 */       for (a = 0; a < 6; a++)
/* 124:    */       {
/* 125:159 */         int x = chunkX * 16 + rand.nextInt(16) + 8;
/* 126:160 */         int z = chunkZ * 16 + rand.nextInt(16) + 8;
/* 127:161 */         int y = world.f(x, z);
/* 128:162 */         new WorldGenRubberTree().a(world, world.t, x, y, z);
/* 129:    */       }
/* 130:    */     }
/* 131:    */   }
/* 132:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.WorldGenHandler
 * JD-Core Version:    0.7.0.1
 */