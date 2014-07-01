/*   1:    */ package com.eloraam.redpower;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.core.Config;
/*   5:    */ import com.eloraam.redpower.lighting.BlockLamp;
/*   6:    */ import com.eloraam.redpower.lighting.BlockShapedLamp;
/*   7:    */ import com.eloraam.redpower.lighting.ItemLamp;
/*   8:    */ import com.eloraam.redpower.lighting.ItemShapedLamp;
/*   9:    */ import com.eloraam.redpower.lighting.LightingProxy;
/*  10:    */ import com.eloraam.redpower.lighting.TileShapedLamp;
/*  11:    */ import cpw.mods.fml.common.Mod;
/*  12:    */ import cpw.mods.fml.common.Mod.Init;
/*  13:    */ import cpw.mods.fml.common.Mod.Instance;
/*  14:    */ import cpw.mods.fml.common.Mod.PostInit;
/*  15:    */ import cpw.mods.fml.common.Mod.PreInit;
/*  16:    */ import cpw.mods.fml.common.SidedProxy;
/*  17:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  18:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  19:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  20:    */ import cpw.mods.fml.common.network.NetworkMod;
/*  21:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  22:    */ import tj;
/*  23:    */ import up;
/*  24:    */ import ur;
/*  25:    */ 
/*  26:    */ @Mod(modid="RedPowerLighting", name="RedPower Lighting", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b", dependencies="required-after:RedPowerBase")
/*  27:    */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/*  28:    */ public class RedPowerLighting
/*  29:    */ {
/*  30:    */   @Mod.Instance("RedPowerLighting")
/*  31:    */   public static RedPowerLighting instance;
/*  32:    */   @SidedProxy(clientSide="com.eloraam.redpower.lighting.LightingProxyClient", serverSide="com.eloraam.redpower.lighting.LightingProxy")
/*  33:    */   public static LightingProxy proxy;
/*  34:    */   public static BlockLamp blockLampOff;
/*  35:    */   public static BlockLamp blockLampOn;
/*  36:    */   public static BlockLamp blockInvLampOff;
/*  37:    */   public static BlockLamp blockInvLampOn;
/*  38:    */   public static BlockShapedLamp blockShapedLamp;
/*  39: 43 */   public static tj tabLamp = new tj(tj.getNextID(), "RPLights")
/*  40:    */   {
/*  41:    */     public ur getIconItemStack()
/*  42:    */     {
/*  43: 46 */       return new ur(RedPowerLighting.blockLampOn, 1, 0);
/*  44:    */     }
/*  45:    */   };
/*  46:    */   public static final String textureFile = "/eloraam/lighting/lighting1.png";
/*  47:    */   
/*  48:    */   @Mod.PreInit
/*  49:    */   public void preInit(FMLPreInitializationEvent event) {}
/*  50:    */   
/*  51:    */   @Mod.Init
/*  52:    */   public void load(FMLInitializationEvent event)
/*  53:    */   {
/*  54: 56 */     setupLighting();
/*  55: 57 */     proxy.registerRenderers();
/*  56:    */   }
/*  57:    */   
/*  58:    */   @Mod.PostInit
/*  59:    */   public void postInit(FMLPostInitializationEvent event) {}
/*  60:    */   
/*  61:    */   public static void setupLighting()
/*  62:    */   {
/*  63: 67 */     blockLampOff = new BlockLamp(Config.getBlockID("blocks.lighting.lampOff.id"), false, false);
/*  64:    */     
/*  65:    */ 
/*  66: 70 */     blockLampOn = new BlockLamp(Config.getBlockID("blocks.lighting.lampOn.id"), true, true);
/*  67:    */     
/*  68:    */ 
/*  69: 73 */     blockLampOn.a(1.0F);
/*  70:    */     
/*  71: 75 */     blockLampOff.b("rplampoff");
/*  72: 76 */     blockLampOn.b("rplampon");
/*  73: 77 */     GameRegistry.registerBlock(blockLampOn, "lampon");
/*  74: 78 */     GameRegistry.registerBlock(blockLampOff, ItemLamp.class, "lampoff");
/*  75:    */     
/*  76: 80 */     blockLampOn.onID = blockLampOn.cm;
/*  77: 81 */     blockLampOn.offID = blockLampOff.cm;
/*  78: 82 */     blockLampOff.onID = blockLampOn.cm;
/*  79: 83 */     blockLampOff.offID = blockLampOff.cm;
/*  80:    */     
/*  81:    */ 
/*  82: 86 */     blockInvLampOff = new BlockLamp(Config.getBlockID("blocks.lighting.lampInvOff.id"), false, true);
/*  83:    */     
/*  84:    */ 
/*  85: 89 */     blockInvLampOn = new BlockLamp(Config.getBlockID("blocks.lighting.lampInvOn.id"), true, false);
/*  86:    */     
/*  87:    */ 
/*  88: 92 */     blockInvLampOn.a(1.0F);
/*  89:    */     
/*  90: 94 */     blockInvLampOff.b("rplampoff");
/*  91: 95 */     blockInvLampOn.b("rplampon");
/*  92: 96 */     GameRegistry.registerBlock(blockInvLampOn, ItemLamp.class, "ilampon");
/*  93: 97 */     GameRegistry.registerBlock(blockInvLampOff, "ilampoff");
/*  94:    */     
/*  95: 99 */     blockInvLampOn.onID = blockInvLampOff.cm;
/*  96:100 */     blockInvLampOn.offID = blockInvLampOn.cm;
/*  97:101 */     blockInvLampOff.onID = blockInvLampOff.cm;
/*  98:102 */     blockInvLampOff.offID = blockInvLampOn.cm;
/*  99:104 */     for (int i = 0; i < 16; i++)
/* 100:    */     {
/* 101:105 */       Config.addName("tile.rplamp." + com.eloraam.redpower.core.CoreLib.rawColorNames[i] + ".name", com.eloraam.redpower.core.CoreLib.enColorNames[i] + " Lamp");
/* 102:    */       
/* 103:    */ 
/* 104:108 */       Config.addName("tile.rpilamp." + com.eloraam.redpower.core.CoreLib.rawColorNames[i] + ".name", "Inverted " + com.eloraam.redpower.core.CoreLib.enColorNames[i] + " Lamp");
/* 105:    */       
/* 106:    */ 
/* 107:111 */       GameRegistry.addRecipe(new ur(blockLampOff, 1, i), new Object[] { "GLG", "GLG", "GRG", Character.valueOf('G'), amq.bt, Character.valueOf('L'), new ur(RedPowerBase.itemLumar, 1, i), Character.valueOf('R'), up.aC });
/* 108:    */       
/* 109:    */ 
/* 110:    */ 
/* 111:    */ 
/* 112:    */ 
/* 113:    */ 
/* 114:118 */       GameRegistry.addRecipe(new ur(blockInvLampOn, 1, i), new Object[] { "GLG", "GLG", "GRG", Character.valueOf('G'), amq.bt, Character.valueOf('L'), new ur(RedPowerBase.itemLumar, 1, i), Character.valueOf('R'), amq.aT });
/* 115:    */     }
/* 116:128 */     blockShapedLamp = new BlockShapedLamp(Config.getBlockID("blocks.lighting.lampShaped.id"));
/* 117:    */     
/* 118:130 */     GameRegistry.registerBlock(blockShapedLamp, ItemShapedLamp.class, "shlamp");
/* 119:    */     
/* 120:132 */     GameRegistry.registerTileEntity(TileShapedLamp.class, "RPShLamp");
/* 121:133 */     blockShapedLamp.addTileEntityMapping(0, TileShapedLamp.class);
/* 122:136 */     for (int i = 0; i < 16; i++)
/* 123:    */     {
/* 124:137 */       String nm = "rpshlamp." + com.eloraam.redpower.core.CoreLib.rawColorNames[i];
/* 125:138 */       blockShapedLamp.setItemName(i, nm);
/* 126:139 */       Config.addName("tile." + nm + ".name", com.eloraam.redpower.core.CoreLib.enColorNames[i] + " Fixture");
/* 127:    */       
/* 128:    */ 
/* 129:142 */       GameRegistry.addRecipe(new ur(blockShapedLamp, 1, i), new Object[] { "GLG", "GLG", "SRS", Character.valueOf('G'), amq.bt, Character.valueOf('L'), new ur(RedPowerBase.itemLumar, 1, i), Character.valueOf('R'), up.aC, Character.valueOf('S'), amq.an });
/* 130:    */     }
/* 131:152 */     for (int i = 0; i < 16; i++)
/* 132:    */     {
/* 133:153 */       String nm = "rpishlamp." + com.eloraam.redpower.core.CoreLib.rawColorNames[i];
/* 134:154 */       blockShapedLamp.setItemName(i + 16, nm);
/* 135:155 */       Config.addName("tile." + nm + ".name", "Inverted " + com.eloraam.redpower.core.CoreLib.enColorNames[i] + " Fixture");
/* 136:    */       
/* 137:    */ 
/* 138:    */ 
/* 139:159 */       GameRegistry.addRecipe(new ur(blockShapedLamp, 1, 16 + i), new Object[] { "GLG", "GLG", "SRS", Character.valueOf('G'), amq.bt, Character.valueOf('L'), new ur(RedPowerBase.itemLumar, 1, i), Character.valueOf('R'), amq.aT, Character.valueOf('S'), new ur(amq.an, 1, 0) });
/* 140:    */     }
/* 141:169 */     for (int i = 0; i < 16; i++)
/* 142:    */     {
/* 143:170 */       String nm = "rpshlamp2." + com.eloraam.redpower.core.CoreLib.rawColorNames[i];
/* 144:171 */       blockShapedLamp.setItemName(i + 32, nm);
/* 145:172 */       Config.addName("tile." + nm + ".name", com.eloraam.redpower.core.CoreLib.enColorNames[i] + " Cage Lamp");
/* 146:    */       
/* 147:    */ 
/* 148:175 */       GameRegistry.addRecipe(new ur(blockShapedLamp, 1, 32 + i), new Object[] { "ILI", "GLG", "SRS", Character.valueOf('G'), amq.bt, Character.valueOf('L'), new ur(RedPowerBase.itemLumar, 1, i), Character.valueOf('R'), up.aC, Character.valueOf('I'), amq.bs, Character.valueOf('S'), new ur(amq.an, 1, 0) });
/* 149:    */     }
/* 150:186 */     for (int i = 0; i < 16; i++)
/* 151:    */     {
/* 152:187 */       String nm = "rpishlamp2." + com.eloraam.redpower.core.CoreLib.rawColorNames[i];
/* 153:188 */       blockShapedLamp.setItemName(i + 48, nm);
/* 154:189 */       Config.addName("tile." + nm + ".name", "Inverted " + com.eloraam.redpower.core.CoreLib.enColorNames[i] + " Cage Lamp");
/* 155:    */       
/* 156:    */ 
/* 157:    */ 
/* 158:193 */       GameRegistry.addRecipe(new ur(blockShapedLamp, 1, 48 + i), new Object[] { "ILI", "GLG", "SRS", Character.valueOf('G'), amq.bt, Character.valueOf('L'), new ur(RedPowerBase.itemLumar, 1, i), Character.valueOf('R'), amq.aT, Character.valueOf('I'), amq.bs, Character.valueOf('S'), amq.an });
/* 159:    */     }
/* 160:    */   }
/* 161:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerLighting
 * JD-Core Version:    0.7.0.1
 */