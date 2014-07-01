/*   1:    */ package com.eloraam.redpower;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.base.BlockMicro;
/*   5:    */ import com.eloraam.redpower.core.Config;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.CoverLib;
/*   8:    */ import com.eloraam.redpower.core.CoverLib.IMaterialHandler;
/*   9:    */ import com.eloraam.redpower.core.CraftLib;
/*  10:    */ import com.eloraam.redpower.core.IMicroPlacement;
/*  11:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  12:    */ import com.eloraam.redpower.core.TileCovered;
/*  13:    */ import com.eloraam.redpower.wiring.MicroPlacementJacket;
/*  14:    */ import com.eloraam.redpower.wiring.MicroPlacementWire;
/*  15:    */ import com.eloraam.redpower.wiring.TileBluewire;
/*  16:    */ import com.eloraam.redpower.wiring.TileCable;
/*  17:    */ import com.eloraam.redpower.wiring.TileInsulatedWire;
/*  18:    */ import com.eloraam.redpower.wiring.TileRedwire;
/*  19:    */ import com.eloraam.redpower.wiring.WiringProxy;
/*  20:    */ import cpw.mods.fml.common.Mod;
/*  21:    */ import cpw.mods.fml.common.Mod.Init;
/*  22:    */ import cpw.mods.fml.common.Mod.Instance;
/*  23:    */ import cpw.mods.fml.common.Mod.PostInit;
/*  24:    */ import cpw.mods.fml.common.Mod.PreInit;
/*  25:    */ import cpw.mods.fml.common.SidedProxy;
/*  26:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  27:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  28:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  29:    */ import cpw.mods.fml.common.network.NetworkMod;
/*  30:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  31:    */ import up;
/*  32:    */ import ur;
/*  33:    */ 
/*  34:    */ @Mod(modid="RedPowerWiring", name="RedPower Wiring", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b", dependencies="required-after:RedPowerBase")
/*  35:    */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/*  36:    */ public class RedPowerWiring
/*  37:    */ {
/*  38:    */   @Mod.Instance("RedPowerWiring")
/*  39:    */   public static RedPowerWiring instance;
/*  40:    */   @SidedProxy(clientSide="com.eloraam.redpower.wiring.WiringProxyClient", serverSide="com.eloraam.redpower.wiring.WiringProxy")
/*  41:    */   public static WiringProxy proxy;
/*  42:    */   
/*  43:    */   @Mod.PreInit
/*  44:    */   public void preInit(FMLPreInitializationEvent event) {}
/*  45:    */   
/*  46:    */   @Mod.Init
/*  47:    */   public void load(FMLInitializationEvent event)
/*  48:    */   {
/*  49: 44 */     initJacketRecipes();
/*  50: 45 */     setupWires();
/*  51: 46 */     proxy.registerRenderers();
/*  52:    */   }
/*  53:    */   
/*  54:    */   @Mod.PostInit
/*  55:    */   public void postInit(FMLPostInitializationEvent event) {}
/*  56:    */   
/*  57:    */   private static void initJacketRecipes()
/*  58:    */   {
/*  59: 54 */     CoverLib.addMaterialHandler(new CoverLib.IMaterialHandler()
/*  60:    */     {
/*  61:    */       public void addMaterial(int n)
/*  62:    */       {
/*  63: 56 */         if (CoverLib.isTransparent(n)) {
/*  64: 56 */           return;
/*  65:    */         }
/*  66: 58 */         String name = CoverLib.getName(n);
/*  67: 59 */         String desc = CoverLib.getDesc(n);
/*  68: 60 */         Config.addName("tile.rparmwire." + name + ".name", desc + " Jacketed Wire");
/*  69:    */         
/*  70: 62 */         Config.addName("tile.rparmcable." + name + ".name", desc + " Jacketed Cable");
/*  71:    */         
/*  72: 64 */         Config.addName("tile.rparmbwire." + name + ".name", desc + " Jacketed Bluewire");
/*  73:    */         
/*  74:    */ 
/*  75: 67 */         GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 4, 16384 + n), new Object[] { "SSS", "SRS", "SSS", Character.valueOf('S'), new ur(RedPowerBase.blockMicro, 1, n), Character.valueOf('R'), RedPowerBase.itemIngotRed });
/*  76:    */         
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83: 75 */         GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 1, 16640 + n), new Object[] { "SSS", "SCS", "SSS", Character.valueOf('S'), new ur(RedPowerBase.blockMicro, 1, n), Character.valueOf('C'), new ur(RedPowerBase.blockMicro, 1, 768) });
/*  84:    */         
/*  85:    */ 
/*  86:    */ 
/*  87:    */ 
/*  88:    */ 
/*  89:    */ 
/*  90:    */ 
/*  91:    */ 
/*  92:    */ 
/*  93: 85 */         GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 4, 16896 + n), new Object[] { "SSS", "SBS", "SSS", Character.valueOf('S'), new ur(RedPowerBase.blockMicro, 1, n), Character.valueOf('B'), RedPowerBase.itemIngotBlue });
/*  94:    */         
/*  95:    */ 
/*  96:    */ 
/*  97:    */ 
/*  98:    */ 
/*  99:    */ 
/* 100: 92 */         CraftLib.addAlloyResult(CoreLib.copyStack(RedPowerBase.itemIngotRed, 1), new Object[] { new ur(RedPowerBase.blockMicro, 4, 16384 + n) });
/* 101:    */         
/* 102:    */ 
/* 103:    */ 
/* 104: 96 */         CraftLib.addAlloyResult(CoreLib.copyStack(RedPowerBase.itemIngotRed, 5), new Object[] { new ur(RedPowerBase.blockMicro, 8, 16640 + n) });
/* 105:    */         
/* 106:    */ 
/* 107:    */ 
/* 108:100 */         CraftLib.addAlloyResult(CoreLib.copyStack(RedPowerBase.itemIngotBlue, 1), new Object[] { new ur(RedPowerBase.blockMicro, 4, 16896 + n) });
/* 109:    */       }
/* 110:    */     });
/* 111:    */   }
/* 112:    */   
/* 113:    */   public static void setupWires()
/* 114:    */   {
/* 115:111 */     GameRegistry.registerTileEntity(TileRedwire.class, "Redwire");
/* 116:112 */     GameRegistry.registerTileEntity(TileInsulatedWire.class, "InsRedwire");
/* 117:    */     
/* 118:114 */     GameRegistry.registerTileEntity(TileCable.class, "RedCable");
/* 119:115 */     GameRegistry.registerTileEntity(TileCovered.class, "Covers");
/* 120:116 */     GameRegistry.registerTileEntity(TileBluewire.class, "Bluewire");
/* 121:    */     
/* 122:118 */     IMicroPlacement imp = new MicroPlacementWire();
/* 123:119 */     RedPowerBase.blockMicro.registerPlacement(1, imp);
/* 124:120 */     RedPowerBase.blockMicro.registerPlacement(2, imp);
/* 125:121 */     RedPowerBase.blockMicro.registerPlacement(3, imp);
/* 126:122 */     RedPowerBase.blockMicro.registerPlacement(5, imp);
/* 127:    */     
/* 128:124 */     imp = new MicroPlacementJacket();
/* 129:125 */     RedPowerBase.blockMicro.registerPlacement(64, imp);
/* 130:126 */     RedPowerBase.blockMicro.registerPlacement(65, imp);
/* 131:127 */     RedPowerBase.blockMicro.registerPlacement(66, imp);
/* 132:    */     
/* 133:129 */     RedPowerBase.blockMicro.addTileEntityMapping(1, TileRedwire.class);
/* 134:    */     
/* 135:131 */     RedPowerBase.blockMicro.addTileEntityMapping(2, TileInsulatedWire.class);
/* 136:    */     
/* 137:133 */     RedPowerBase.blockMicro.addTileEntityMapping(3, TileCable.class);
/* 138:    */     
/* 139:135 */     RedPowerBase.blockMicro.addTileEntityMapping(5, TileBluewire.class);
/* 140:    */     
/* 141:    */ 
/* 142:    */ 
/* 143:139 */     GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 12, 256), new Object[] { "R", "R", "R", Character.valueOf('R'), RedPowerBase.itemIngotRed });
/* 144:    */     
/* 145:    */ 
/* 146:    */ 
/* 147:143 */     CraftLib.addAlloyResult(RedPowerBase.itemIngotRed, new Object[] { new ur(RedPowerBase.blockMicro, 4, 256) });
/* 148:    */     
/* 149:145 */     CraftLib.addAlloyResult(CoreLib.copyStack(RedPowerBase.itemIngotRed, 5), new Object[] { new ur(RedPowerBase.blockMicro, 8, 768) });
/* 150:    */     
/* 151:    */ 
/* 152:    */ 
/* 153:149 */     GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 12, 1280), new Object[] { "WBW", "WBW", "WBW", Character.valueOf('B'), RedPowerBase.itemIngotBlue, Character.valueOf('W'), amq.ae });
/* 154:    */     
/* 155:    */ 
/* 156:    */ 
/* 157:    */ 
/* 158:154 */     CraftLib.addAlloyResult(RedPowerBase.itemIngotBlue, new Object[] { new ur(RedPowerBase.blockMicro, 4, 1280) });
/* 159:    */     
/* 160:    */ 
/* 161:    */ 
/* 162:158 */     GameRegistry.addShapelessRecipe(new ur(RedPowerBase.blockMicro, 1, 1281), new Object[] { new ur(RedPowerBase.blockMicro, 1, 1280), amq.ae });
/* 163:    */     
/* 164:    */ 
/* 165:    */ 
/* 166:    */ 
/* 167:163 */     CraftLib.addAlloyResult(RedPowerBase.itemIngotBlue, new Object[] { new ur(RedPowerBase.blockMicro, 4, 1281) });
/* 168:177 */     for (int i = 0; i < 16; i++)
/* 169:    */     {
/* 170:178 */       Config.addName("tile.rpinsulated." + CoreLib.rawColorNames[i] + ".name", CoreLib.enColorNames[i] + " Insulated Wire");
/* 171:    */       
/* 172:    */ 
/* 173:181 */       Config.addName("tile.rpcable." + CoreLib.rawColorNames[i] + ".name", CoreLib.enColorNames[i] + " Bundled Cable");
/* 174:    */       
/* 175:    */ 
/* 176:    */ 
/* 177:185 */       GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 12, 512 + i), new Object[] { "WRW", "WRW", "WRW", Character.valueOf('R'), RedPowerBase.itemIngotRed, Character.valueOf('W'), new ur(amq.ae, 1, i) });
/* 178:192 */       for (int j = 0; j < 16; j++) {
/* 179:193 */         if (i != j)
/* 180:    */         {
/* 181:194 */           GameRegistry.addShapelessRecipe(new ur(RedPowerBase.blockMicro, 1, 512 + i), new Object[] { new ur(RedPowerBase.blockMicro, 1, 512 + j), new ur(up.aW, 1, 15 - i) });
/* 182:    */           
/* 183:    */ 
/* 184:    */ 
/* 185:    */ 
/* 186:    */ 
/* 187:    */ 
/* 188:    */ 
/* 189:202 */           GameRegistry.addShapelessRecipe(new ur(RedPowerBase.blockMicro, 1, 769 + i), new Object[] { new ur(RedPowerBase.blockMicro, 1, 769 + j), new ur(up.aW, 1, 15 - i) });
/* 190:    */         }
/* 191:    */       }
/* 192:210 */       CraftLib.addAlloyResult(RedPowerBase.itemIngotRed, new Object[] { new ur(RedPowerBase.blockMicro, 4, 512 + i) });
/* 193:    */       
/* 194:    */ 
/* 195:    */ 
/* 196:214 */       GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 2, 768), new Object[] { "SWS", "WWW", "SWS", Character.valueOf('W'), new ur(RedPowerBase.blockMicro, 1, 512 + i), Character.valueOf('S'), up.K });
/* 197:    */       
/* 198:    */ 
/* 199:    */ 
/* 200:    */ 
/* 201:    */ 
/* 202:    */ 
/* 203:    */ 
/* 204:222 */       GameRegistry.addShapelessRecipe(new ur(RedPowerBase.blockMicro, 1, 769 + i), new Object[] { new ur(RedPowerBase.blockMicro, 1, 768), new ur(up.aW, 1, 15 - i), up.aK });
/* 205:    */       
/* 206:    */ 
/* 207:    */ 
/* 208:    */ 
/* 209:    */ 
/* 210:228 */       CraftLib.addAlloyResult(CoreLib.copyStack(RedPowerBase.itemIngotRed, 5), new Object[] { new ur(RedPowerBase.blockMicro, 8, 769 + i) });
/* 211:    */     }
/* 212:234 */     for (int j = 0; j < 16; j++) {
/* 213:235 */       if (j != 11)
/* 214:    */       {
/* 215:236 */         CraftLib.addShapelessOreRecipe(new ur(RedPowerBase.blockMicro, 1, 523), new Object[] { new ur(RedPowerBase.blockMicro, 1, 512 + j), "dyeBlue" });
/* 216:    */         
/* 217:    */ 
/* 218:    */ 
/* 219:    */ 
/* 220:    */ 
/* 221:242 */         CraftLib.addShapelessOreRecipe(new ur(RedPowerBase.blockMicro, 1, 780), new Object[] { new ur(RedPowerBase.blockMicro, 1, 769 + j), "dyeBlue" });
/* 222:    */       }
/* 223:    */     }
/* 224:248 */     CraftLib.addShapelessOreRecipe(new ur(RedPowerBase.blockMicro, 1, 780), new Object[] { new ur(RedPowerBase.blockMicro, 1, 768), "dyeBlue", up.aK });
/* 225:    */     
/* 226:    */ 
/* 227:    */ 
/* 228:    */ 
/* 229:    */ 
/* 230:254 */     RedPowerLib.addCompatibleMapping(0, 1);
/* 231:255 */     for (int i = 0; i < 16; i++)
/* 232:    */     {
/* 233:256 */       RedPowerLib.addCompatibleMapping(0, 2 + i);
/* 234:257 */       RedPowerLib.addCompatibleMapping(1, 2 + i);
/* 235:258 */       RedPowerLib.addCompatibleMapping(65, 2 + i);
/* 236:259 */       for (int j = 0; j < 16; j++) {
/* 237:260 */         RedPowerLib.addCompatibleMapping(19 + j, 2 + i);
/* 238:    */       }
/* 239:261 */       RedPowerLib.addCompatibleMapping(18, 2 + i);
/* 240:262 */       RedPowerLib.addCompatibleMapping(18, 19 + i);
/* 241:    */     }
/* 242:264 */     RedPowerLib.addCompatibleMapping(0, 65);
/* 243:265 */     RedPowerLib.addCompatibleMapping(1, 65);
/* 244:266 */     RedPowerLib.addCompatibleMapping(64, 65);
/* 245:    */     
/* 246:    */ 
/* 247:    */ 
/* 248:    */ 
/* 249:    */ 
/* 250:272 */     RedPowerLib.addCompatibleMapping(64, 67);
/* 251:273 */     RedPowerLib.addCompatibleMapping(65, 67);
/* 252:274 */     RedPowerLib.addCompatibleMapping(66, 67);
/* 253:    */   }
/* 254:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerWiring
 * JD-Core Version:    0.7.0.1
 */