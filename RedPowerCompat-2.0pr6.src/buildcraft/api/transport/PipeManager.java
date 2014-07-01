/*  1:   */ package buildcraft.api.transport;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.FMLCommonHandler;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.List;
/*  7:   */ import java.util.TreeMap;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public abstract class PipeManager
/* 11:   */ {
/* 12:   */   @Deprecated
/* 13:14 */   private static TreeMap allServerEntities = new TreeMap();
/* 14:   */   @Deprecated
/* 15:16 */   private static TreeMap allClientEntities = new TreeMap();
/* 16:18 */   public static List extractionHandlers = new ArrayList();
/* 17:   */   
/* 18:   */   public static void registerExtractionHandler(IExtractionHandler handler)
/* 19:   */   {
/* 20:21 */     extractionHandlers.add(handler);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public static boolean canExtractItems(IPipe pipe, yc world, int i, int j, int k)
/* 24:   */   {
/* 25:25 */     for (IExtractionHandler handler : extractionHandlers) {
/* 26:26 */       if (!handler.canExtractItems(pipe, world, i, j, k)) {
/* 27:27 */         return false;
/* 28:   */       }
/* 29:   */     }
/* 30:29 */     return true;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public static boolean canExtractLiquids(IPipe pipe, yc world, int i, int j, int k)
/* 34:   */   {
/* 35:33 */     for (IExtractionHandler handler : extractionHandlers) {
/* 36:34 */       if (!handler.canExtractLiquids(pipe, world, i, j, k)) {
/* 37:35 */         return false;
/* 38:   */       }
/* 39:   */     }
/* 40:37 */     return true;
/* 41:   */   }
/* 42:   */   
/* 43:   */   @Deprecated
/* 44:   */   public static TreeMap getAllEntities()
/* 45:   */   {
/* 46:42 */     if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
/* 47:43 */       return allClientEntities;
/* 48:   */     }
/* 49:44 */     return allServerEntities;
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.transport.PipeManager
 * JD-Core Version:    0.7.0.1
 */