/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.List;
/*  5:   */ import java.util.TreeMap;
/*  6:   */ import jg;
/*  7:   */ import net.minecraftforge.common.AchievementPage;
/*  8:   */ import qx;
/*  9:   */ import ur;
/* 10:   */ 
/* 11:   */ public class AchieveLib
/* 12:   */ {
/* 13:11 */   private static HashMap achievelist = new HashMap();
/* 14:13 */   public static AchievementPage achievepage = new AchievementPage("RedPower", new jg[0]);
/* 15:15 */   private static TreeMap achievebycraft = new TreeMap(CoreLib.itemStackComparator);
/* 16:17 */   private static TreeMap achievebyfurnace = new TreeMap(CoreLib.itemStackComparator);
/* 17:19 */   private static TreeMap achievebyalloy = new TreeMap(CoreLib.itemStackComparator);
/* 18:   */   
/* 19:   */   public static void registerAchievement(int id, String name, int x, int y, ur icon, Object require, boolean special)
/* 20:   */   {
/* 21:26 */     jg acreq = null;
/* 22:27 */     if ((require instanceof jg)) {
/* 23:28 */       acreq = (jg)require;
/* 24:29 */     } else if ((require instanceof String)) {
/* 25:30 */       acreq = (jg)achievelist.get((String)require);
/* 26:   */     }
/* 27:32 */     jg ac = new jg(id, name, x, y, icon, acreq);
/* 28:33 */     ac.c();
/* 29:34 */     if (special) {
/* 30:34 */       ac.b();
/* 31:   */     }
/* 32:35 */     achievelist.put(name, ac);
/* 33:36 */     achievepage.getAchievements().add(ac);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public static void registerAchievement(int id, String name, int x, int y, ur icon, Object require)
/* 37:   */   {
/* 38:41 */     registerAchievement(id, name, x, y, icon, require, false);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public static void addCraftingAchievement(ur target, String id)
/* 42:   */   {
/* 43:46 */     jg ac = (jg)achievelist.get(id);
/* 44:47 */     if (ac == null) {
/* 45:47 */       return;
/* 46:   */     }
/* 47:48 */     achievebycraft.put(target, ac);
/* 48:   */   }
/* 49:   */   
/* 50:   */   public static void addAlloyAchievement(ur target, String id)
/* 51:   */   {
/* 52:52 */     jg ac = (jg)achievelist.get(id);
/* 53:53 */     if (ac == null) {
/* 54:53 */       return;
/* 55:   */     }
/* 56:54 */     achievebyalloy.put(target, ac);
/* 57:   */   }
/* 58:   */   
/* 59:   */   public static void addFurnaceAchievement(ur target, String id)
/* 60:   */   {
/* 61:59 */     jg ac = (jg)achievelist.get(id);
/* 62:60 */     if (ac == null) {
/* 63:60 */       return;
/* 64:   */     }
/* 65:61 */     achievebyfurnace.put(target, ac);
/* 66:   */   }
/* 67:   */   
/* 68:   */   public static void triggerAchievement(qx player, String id)
/* 69:   */   {
/* 70:66 */     jg ac = (jg)achievelist.get(id);
/* 71:67 */     if (ac == null) {
/* 72:67 */       return;
/* 73:   */     }
/* 74:68 */     player.a(ac);
/* 75:   */   }
/* 76:   */   
/* 77:   */   public static void onCrafting(qx player, ur ist)
/* 78:   */   {
/* 79:72 */     jg ac = (jg)achievebycraft.get(ist);
/* 80:73 */     if (ac == null) {
/* 81:73 */       return;
/* 82:   */     }
/* 83:74 */     player.a(ac);
/* 84:   */   }
/* 85:   */   
/* 86:   */   public static void onFurnace(qx player, ur ist)
/* 87:   */   {
/* 88:78 */     jg ac = (jg)achievebyfurnace.get(ist);
/* 89:79 */     if (ac == null) {
/* 90:79 */       return;
/* 91:   */     }
/* 92:80 */     player.a(ac);
/* 93:   */   }
/* 94:   */   
/* 95:   */   public static void onAlloy(qx player, ur ist)
/* 96:   */   {
/* 97:84 */     jg ac = (jg)achievebyalloy.get(ist);
/* 98:85 */     if (ac == null) {
/* 99:85 */       return;
/* :0:   */     }
/* :1:86 */     player.a(ac);
/* :2:   */   }
/* :3:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.AchieveLib
 * JD-Core Version:    0.7.0.1
 */