/*  1:   */ package buildcraft.api.core;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import yc;
/*  5:   */ 
/*  6:   */ public class BuildCraftAPI
/*  7:   */ {
/*  8:   */   @Deprecated
/*  9:   */   public static final int BUCKET_VOLUME = 1000;
/* 10:   */   public static final int LAST_ORIGINAL_BLOCK = 122;
/* 11:   */   public static final int LAST_ORIGINAL_ITEM = 126;
/* 12:23 */   public static final boolean[] softBlocks = new boolean[amq.p.length];
/* 13:   */   
/* 14:   */   @Deprecated
/* 15:   */   public static boolean softBlock(int blockId)
/* 16:   */   {
/* 17:28 */     return (blockId == 0) || (softBlocks[blockId] != 0) || (amq.p[blockId] == null);
/* 18:   */   }
/* 19:   */   
/* 20:   */   @Deprecated
/* 21:   */   public static boolean unbreakableBlock(int blockId)
/* 22:   */   {
/* 23:34 */     return (blockId == amq.C.cm) || (blockId == amq.G.cm) || (blockId == amq.F.cm);
/* 24:   */   }
/* 25:   */   
/* 26:   */   @Deprecated
/* 27:   */   public static void breakBlock(yc world, int x, int y, int z)
/* 28:   */   {
/* 29:40 */     int blockId = world.a(x, y, z);
/* 30:42 */     if (blockId != 0) {
/* 31:43 */       amq.p[blockId].c(world, x, y, z, world.h(x, y, z), 0);
/* 32:   */     }
/* 33:46 */     world.e(x, y, z, 0);
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.core.BuildCraftAPI
 * JD-Core Version:    0.7.0.1
 */