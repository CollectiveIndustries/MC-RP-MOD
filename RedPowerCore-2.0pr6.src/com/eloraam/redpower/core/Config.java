/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.RedPowerCore;
/*   5:    */ import cpw.mods.fml.common.Loader;
/*   6:    */ import cpw.mods.fml.common.registry.LanguageRegistry;
/*   7:    */ import java.io.File;
/*   8:    */ import java.io.FileInputStream;
/*   9:    */ import java.io.FileOutputStream;
/*  10:    */ import java.io.IOException;
/*  11:    */ import java.io.InputStream;
/*  12:    */ import java.util.Map.Entry;
/*  13:    */ import java.util.Properties;
/*  14:    */ 
/*  15:    */ public class Config
/*  16:    */ {
/*  17:    */   public static void loadConfig()
/*  18:    */   {
/*  19: 17 */     config = new TagFile();
/*  20:    */     
/*  21: 19 */     InputStream is = RedPowerCore.class.getResourceAsStream("/eloraam/core/default.cfg");
/*  22:    */     
/*  23: 21 */     config.readStream(is);
/*  24: 23 */     if (configDir == null)
/*  25:    */     {
/*  26: 24 */       File d = Loader.instance().getConfigDir();
/*  27: 25 */       d = new File(d, "/redpower/");
/*  28: 26 */       d.mkdir();
/*  29: 27 */       configDir = d;
/*  30: 28 */       configFile = new File(d, "redpower.cfg");
/*  31:    */     }
/*  32: 30 */     if (configFile.exists()) {
/*  33: 31 */       config.readFile(configFile);
/*  34:    */     }
/*  35: 33 */     config.commentFile("RedPower 2 Configuration");
/*  36: 35 */     for (String key : config.query("blocks.%.%.id")) {
/*  37: 36 */       reservedIds[config.getInt(key)] = true;
/*  38:    */     }
/*  39: 37 */     for (String key : config.query("items.%.%.id")) {
/*  40: 38 */       reservedIds[(config.getInt(key) + 256)] = true;
/*  41:    */     }
/*  42: 40 */     if (rpTranslateTable == null) {
/*  43: 41 */       rpTranslateTable = new Properties();
/*  44:    */     }
/*  45:    */     try
/*  46:    */     {
/*  47: 44 */       rpTranslateTable.load(RedPowerCore.class.getResourceAsStream("/eloraam/core/redpower.lang"));
/*  48:    */       
/*  49:    */ 
/*  50: 47 */       File trf = new File(configDir, "redpower.lang");
/*  51: 48 */       if (trf.exists())
/*  52:    */       {
/*  53: 49 */         FileInputStream ifs = new FileInputStream(trf);
/*  54: 50 */         rpTranslateTable.load(ifs);
/*  55:    */       }
/*  56:    */     }
/*  57:    */     catch (IOException e)
/*  58:    */     {
/*  59: 53 */       e.printStackTrace();
/*  60:    */     }
/*  61: 57 */     for (Map.Entry entry : rpTranslateTable.entrySet()) {
/*  62: 58 */       LanguageRegistry.instance().addStringLocalization((String)entry.getKey(), (String)entry.getValue());
/*  63:    */     }
/*  64: 63 */     autoAssign = config.getInt("settings.core.autoAssign") > 0;
/*  65: 64 */     config.addInt("settings.core.autoAssign", 0);
/*  66: 65 */     config.commentTag("settings.core.autoAssign", "Automatically remap conflicting IDs.\nWARNING: May corrupt existing worlds");
/*  67:    */   }
/*  68:    */   
/*  69:    */   public static void saveConfig()
/*  70:    */   {
/*  71: 69 */     config.saveFile(configFile);
/*  72:    */     try
/*  73:    */     {
/*  74: 71 */       File trf = new File(configDir, "redpower.lang");
/*  75: 72 */       FileOutputStream os = new FileOutputStream(trf);
/*  76: 73 */       rpTranslateTable.store(os, "RedPower Language File");
/*  77:    */     }
/*  78:    */     catch (IOException e)
/*  79:    */     {
/*  80: 75 */       e.printStackTrace();
/*  81:    */     }
/*  82:    */   }
/*  83:    */   
/*  84:    */   public static void addName(String tag, String name)
/*  85:    */   {
/*  86: 80 */     if (rpTranslateTable.get(tag) != null) {
/*  87: 80 */       return;
/*  88:    */     }
/*  89: 81 */     rpTranslateTable.put(tag, name);
/*  90: 82 */     LanguageRegistry.instance().addStringLocalization(tag, name);
/*  91:    */   }
/*  92:    */   
/*  93:    */   public static void addName(amq bl, String name)
/*  94:    */   {
/*  95: 86 */     addName(bl.a() + ".name", name);
/*  96:    */   }
/*  97:    */   
/*  98:    */   private static void die(String msg)
/*  99:    */   {
/* 100: 90 */     throw new RuntimeException("RedPowerCore: " + msg);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public static int getItemID(String name)
/* 104:    */   {
/* 105: 94 */     int cid = config.getInt(name);
/* 106: 95 */     if (up.e[(256 + cid)] == null) {
/* 107: 95 */       return cid;
/* 108:    */     }
/* 109: 96 */     if (!autoAssign)
/* 110:    */     {
/* 111: 97 */       die(String.format("ItemID %d exists, autoAssign is disabled.", new Object[] { Integer.valueOf(cid) }));
/* 112: 98 */       return -1;
/* 113:    */     }
/* 114:100 */     for (int i = 1024; i < 32000; i++) {
/* 115:101 */       if ((reservedIds[i] == 0) && 
/* 116:102 */         (up.e[i] == null))
/* 117:    */       {
/* 118:103 */         config.addInt(name, i - 256);
/* 119:104 */         return i;
/* 120:    */       }
/* 121:    */     }
/* 122:106 */     die("Out of available ItemIDs, could not autoassign!");
/* 123:107 */     return -1;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public static int getBlockID(String name)
/* 127:    */   {
/* 128:111 */     int cid = config.getInt(name);
/* 129:112 */     if (amq.p[cid] == null) {
/* 130:112 */       return cid;
/* 131:    */     }
/* 132:114 */     if (!autoAssign)
/* 133:    */     {
/* 134:115 */       die(String.format("BlockID %d occupied by %s, autoAssign is disabled.", new Object[] { Integer.valueOf(cid), amq.p[cid].getClass().getName() }));
/* 135:116 */       return -1;
/* 136:    */     }
/* 137:118 */     for (int i = 255; i >= 20; i--) {
/* 138:119 */       if ((reservedIds[i] == 0) && 
/* 139:120 */         (amq.p[i] == null))
/* 140:    */       {
/* 141:121 */         config.addInt(name, i);
/* 142:122 */         return i;
/* 143:    */       }
/* 144:    */     }
/* 145:124 */     die("Out of available BlockIDs, could not autoassign!");
/* 146:125 */     return -1;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public static int getInt(String name)
/* 150:    */   {
/* 151:129 */     return config.getInt(name);
/* 152:    */   }
/* 153:    */   
/* 154:    */   public static String getString(String name)
/* 155:    */   {
/* 156:133 */     return config.getString(name);
/* 157:    */   }
/* 158:    */   
/* 159:136 */   static boolean[] reservedIds = new boolean[32768];
/* 160:138 */   static File configDir = null;
/* 161:139 */   static File configFile = null;
/* 162:140 */   static TagFile config = null;
/* 163:141 */   static Properties rpTranslateTable = null;
/* 164:142 */   static boolean autoAssign = true;
/* 165:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.Config
 * JD-Core Version:    0.7.0.1
 */