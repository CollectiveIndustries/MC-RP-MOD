/*   1:    */ package com.eloraam.redpower;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.base.ItemHandsaw;
/*   5:    */ import com.eloraam.redpower.core.Config;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.CoverLib;
/*   8:    */ import com.eloraam.redpower.core.CraftLib;
/*   9:    */ import com.eloraam.redpower.core.ItemPartialCraft;
/*  10:    */ import com.eloraam.redpower.core.ItemTextured;
/*  11:    */ import com.eloraam.redpower.world.BlockBrickMossifier;
/*  12:    */ import com.eloraam.redpower.world.BlockCobbleMossifier;
/*  13:    */ import com.eloraam.redpower.world.BlockCustomCrops;
/*  14:    */ import com.eloraam.redpower.world.BlockCustomFlower;
/*  15:    */ import com.eloraam.redpower.world.BlockCustomLeaves;
/*  16:    */ import com.eloraam.redpower.world.BlockCustomLog;
/*  17:    */ import com.eloraam.redpower.world.BlockCustomOre;
/*  18:    */ import com.eloraam.redpower.world.BlockCustomStone;
/*  19:    */ import com.eloraam.redpower.world.BlockStorage;
/*  20:    */ import com.eloraam.redpower.world.EnchantmentDisjunction;
/*  21:    */ import com.eloraam.redpower.world.EnchantmentVorpal;
/*  22:    */ import com.eloraam.redpower.world.ItemAthame;
/*  23:    */ import com.eloraam.redpower.world.ItemCustomAxe;
/*  24:    */ import com.eloraam.redpower.world.ItemCustomFlower;
/*  25:    */ import com.eloraam.redpower.world.ItemCustomHoe;
/*  26:    */ import com.eloraam.redpower.world.ItemCustomOre;
/*  27:    */ import com.eloraam.redpower.world.ItemCustomPickaxe;
/*  28:    */ import com.eloraam.redpower.world.ItemCustomSeeds;
/*  29:    */ import com.eloraam.redpower.world.ItemCustomShovel;
/*  30:    */ import com.eloraam.redpower.world.ItemCustomStone;
/*  31:    */ import com.eloraam.redpower.world.ItemCustomSword;
/*  32:    */ import com.eloraam.redpower.world.ItemPaintBrush;
/*  33:    */ import com.eloraam.redpower.world.ItemPaintCan;
/*  34:    */ import com.eloraam.redpower.world.ItemSeedBag;
/*  35:    */ import com.eloraam.redpower.world.ItemSickle;
/*  36:    */ import com.eloraam.redpower.world.ItemStorage;
/*  37:    */ import com.eloraam.redpower.world.ItemWoolCard;
/*  38:    */ import com.eloraam.redpower.world.WorldEvents;
/*  39:    */ import com.eloraam.redpower.world.WorldGenHandler;
/*  40:    */ import com.eloraam.redpower.world.WorldProxy;
/*  41:    */ import cpw.mods.fml.common.Mod;
/*  42:    */ import cpw.mods.fml.common.Mod.Init;
/*  43:    */ import cpw.mods.fml.common.Mod.Instance;
/*  44:    */ import cpw.mods.fml.common.Mod.PostInit;
/*  45:    */ import cpw.mods.fml.common.Mod.PreInit;
/*  46:    */ import cpw.mods.fml.common.SidedProxy;
/*  47:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  48:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  49:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  50:    */ import cpw.mods.fml.common.network.NetworkMod;
/*  51:    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*  52:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  53:    */ import net.minecraftforge.common.EnumHelper;
/*  54:    */ import net.minecraftforge.common.MinecraftForge;
/*  55:    */ import net.minecraftforge.event.EventBus;
/*  56:    */ import net.minecraftforge.oredict.OreDictionary;
/*  57:    */ import up;
/*  58:    */ import uq;
/*  59:    */ import ur;
/*  60:    */ import wj;
/*  61:    */ import xc;
/*  62:    */ 
/*  63:    */ @Mod(modid="RedPowerWorld", name="RedPower World", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b", dependencies="required-after:RedPowerBase")
/*  64:    */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/*  65:    */ public class RedPowerWorld
/*  66:    */ {
/*  67:    */   @Mod.Instance("RedPowerWorld")
/*  68:    */   public static RedPowerWorld instance;
/*  69:    */   @SidedProxy(clientSide="com.eloraam.redpower.world.WorldProxyClient", serverSide="com.eloraam.redpower.world.WorldProxy")
/*  70:    */   public static WorldProxy proxy;
/*  71:    */   public static BlockCustomFlower blockPlants;
/*  72:    */   public static BlockCustomOre blockOres;
/*  73:    */   public static BlockCustomLeaves blockLeaves;
/*  74:    */   public static BlockCustomLog blockLogs;
/*  75:    */   public static BlockCustomStone blockStone;
/*  76:    */   public static BlockCustomCrops blockCrops;
/*  77:    */   public static BlockStorage blockStorage;
/*  78:    */   public static ur itemOreRuby;
/*  79:    */   public static ur itemOreGreenSapphire;
/*  80:    */   public static ur itemOreSapphire;
/*  81:    */   public static ur itemMarble;
/*  82:    */   public static uq toolMaterialRuby;
/*  83:    */   public static uq toolMaterialGreenSapphire;
/*  84:    */   public static uq toolMaterialSapphire;
/*  85:    */   public static ItemSickle itemSickleWood;
/*  86:    */   public static ItemSickle itemSickleStone;
/*  87:    */   public static ItemSickle itemSickleIron;
/*  88:    */   public static ItemSickle itemSickleDiamond;
/*  89:    */   public static ItemSickle itemSickleGold;
/*  90:    */   public static ItemSickle itemSickleRuby;
/*  91:    */   public static ItemSickle itemSickleGreenSapphire;
/*  92:    */   public static ItemSickle itemSickleSapphire;
/*  93:    */   public static ItemCustomPickaxe itemPickaxeRuby;
/*  94:    */   public static ItemCustomPickaxe itemPickaxeGreenSapphire;
/*  95:    */   public static ItemCustomPickaxe itemPickaxeSapphire;
/*  96:    */   public static ItemCustomShovel itemShovelRuby;
/*  97:    */   public static ItemCustomShovel itemShovelGreenSapphire;
/*  98:    */   public static ItemCustomShovel itemShovelSapphire;
/*  99:    */   public static ItemCustomAxe itemAxeRuby;
/* 100:    */   public static ItemCustomAxe itemAxeGreenSapphire;
/* 101:    */   public static ItemCustomAxe itemAxeSapphire;
/* 102:    */   public static ItemCustomSword itemSwordRuby;
/* 103:    */   public static ItemCustomSword itemSwordGreenSapphire;
/* 104:    */   public static ItemCustomSword itemSwordSapphire;
/* 105:    */   public static ItemAthame itemAthame;
/* 106:    */   public static ItemCustomHoe itemHoeRuby;
/* 107:    */   public static ItemCustomHoe itemHoeGreenSapphire;
/* 108:    */   public static ItemCustomHoe itemHoeSapphire;
/* 109:    */   public static ItemCustomSeeds itemSeeds;
/* 110:    */   public static up itemHandsawRuby;
/* 111:    */   public static up itemHandsawGreenSapphire;
/* 112:    */   public static up itemHandsawSapphire;
/* 113:    */   public static up itemBrushDry;
/* 114:    */   public static up itemPaintCanEmpty;
/* 115:103 */   public static up[] itemBrushPaint = new up[16];
/* 116:104 */   public static ItemPartialCraft[] itemPaintCanPaint = new ItemPartialCraft[16];
/* 117:    */   public static up itemWoolCard;
/* 118:    */   public static up itemSeedBag;
/* 119:    */   public static xc enchantDisjunction;
/* 120:    */   public static xc enchantVorpal;
/* 121:    */   public static final String blockTextureFile = "/eloraam/world/world1.png";
/* 122:    */   public static final String itemTextureFile = "/eloraam/world/worlditems1.png";
/* 123:    */   
/* 124:    */   @Mod.PreInit
/* 125:    */   public void preInit(FMLPreInitializationEvent event)
/* 126:    */   {
/* 127:114 */     MinecraftForge.EVENT_BUS.register(new WorldEvents());
/* 128:    */   }
/* 129:    */   
/* 130:    */   @Mod.Init
/* 131:    */   public void load(FMLInitializationEvent event)
/* 132:    */   {
/* 133:119 */     GameRegistry.registerWorldGenerator(new WorldGenHandler());
/* 134:120 */     setupOres();
/* 135:121 */     setupPlants();
/* 136:122 */     setupTools();
/* 137:123 */     setupMisc();
/* 138:124 */     proxy.registerRenderers();
/* 139:125 */     NetworkRegistry.instance().registerGuiHandler(instance, proxy);
/* 140:    */   }
/* 141:    */   
/* 142:    */   @Mod.PostInit
/* 143:    */   public void postInit(FMLPostInitializationEvent event) {}
/* 144:    */   
/* 145:    */   public void setupPlants()
/* 146:    */   {
/* 147:133 */     blockPlants = new BlockCustomFlower(Config.getBlockID("blocks.world.plants.id"), 1);
/* 148:    */     
/* 149:135 */     blockPlants.b("indigo");
/* 150:136 */     GameRegistry.registerBlock(blockPlants, ItemCustomFlower.class, "plants");
/* 151:    */     
/* 152:138 */     MinecraftForge.addGrassPlant(blockPlants, 0, 10);
/* 153:    */     
/* 154:    */ 
/* 155:141 */     GameRegistry.addShapelessRecipe(new ur(RedPowerBase.itemDyeIndigo, 2, 0), new Object[] { blockPlants });
/* 156:    */     
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:146 */     itemSeeds = new ItemCustomSeeds(Config.getItemID("items.world.seeds.id"));
/* 161:    */     
/* 162:    */ 
/* 163:149 */     MinecraftForge.addGrassSeed(new ur(itemSeeds.cj, 1, 0), 5);
/* 164:    */     
/* 165:151 */     blockCrops = new BlockCustomCrops(Config.getBlockID("blocks.world.crops.id"));
/* 166:    */     
/* 167:    */ 
/* 168:    */ 
/* 169:155 */     blockLeaves = new BlockCustomLeaves(Config.getBlockID("blocks.world.leaves.id"));
/* 170:    */     
/* 171:157 */     blockLeaves.b("rpleaves");
/* 172:158 */     GameRegistry.registerBlock(blockLeaves, "leaves");
/* 173:    */     
/* 174:160 */     blockLogs = new BlockCustomLog(Config.getBlockID("blocks.world.log.id"));
/* 175:    */     
/* 176:162 */     blockLogs.b("rplog");
/* 177:163 */     GameRegistry.registerBlock(blockLogs, "logs");
/* 178:164 */     MinecraftForge.setBlockHarvestLevel(blockLogs, "axe", 0);
/* 179:    */     
/* 180:166 */     OreDictionary.registerOre("woodRubber", new ur(blockLogs));
/* 181:    */     
/* 182:168 */     GameRegistry.addRecipe(new ur(up.D, 8), new Object[] { "W", Character.valueOf('W'), blockLogs });
/* 183:    */     
/* 184:    */ 
/* 185:171 */     wj.a().addSmelting(blockLogs.cm, 0, new ur(up.m, 1, 1), 0.15F);
/* 186:    */     
/* 187:    */ 
/* 188:174 */     CoverLib.addMaterial(53, 0, blockLogs, 0, "rplog", "Rubberwood");
/* 189:    */   }
/* 190:    */   
/* 191:    */   public void setupOres()
/* 192:    */   {
/* 193:180 */     blockStone = new BlockCustomStone(Config.getBlockID("blocks.world.stone.id"));
/* 194:    */     
/* 195:182 */     blockStone.b("rpstone");
/* 196:183 */     GameRegistry.registerBlock(blockStone, ItemCustomStone.class, "stone");
/* 197:    */     
/* 198:    */ 
/* 199:186 */     itemMarble = new ur(blockStone, 0);
/* 200:    */     
/* 201:188 */     MinecraftForge.setBlockHarvestLevel(blockStone, "pickaxe", 0);
/* 202:    */     
/* 203:190 */     CoverLib.addMaterial(48, 1, blockStone, 0, "marble", "Marble");
/* 204:191 */     CoverLib.addMaterial(49, 1, blockStone, 1, "basalt", "Basalt");
/* 205:192 */     CoverLib.addMaterial(50, 1, blockStone, 2, "marbleBrick", "Marble Brick");
/* 206:    */     
/* 207:194 */     CoverLib.addMaterial(51, 1, blockStone, 3, "basaltCobble", "Basalt Cobblestone");
/* 208:    */     
/* 209:196 */     CoverLib.addMaterial(52, 1, blockStone, 4, "basaltBrick", "Basalt Brick");
/* 210:    */     
/* 211:198 */     CoverLib.addMaterial(57, 1, blockStone, 5, "basaltCircle", "Chiseled Basalt Brick");
/* 212:    */     
/* 213:200 */     CoverLib.addMaterial(58, 1, blockStone, 6, "basaltPaver", "Basalt Paver");
/* 214:    */     
/* 215:    */ 
/* 216:    */ 
/* 217:204 */     blockOres = new BlockCustomOre(Config.getBlockID("blocks.world.ores.id"));
/* 218:    */     
/* 219:206 */     blockOres.b("rpores");
/* 220:207 */     GameRegistry.registerBlock(blockOres, ItemCustomOre.class, "ores");
/* 221:    */     
/* 222:    */ 
/* 223:210 */     itemOreRuby = new ur(blockOres, 1, 0);
/* 224:211 */     itemOreGreenSapphire = new ur(blockOres, 1, 1);
/* 225:212 */     itemOreSapphire = new ur(blockOres, 1, 2);
/* 226:    */     
/* 227:214 */     MinecraftForge.setBlockHarvestLevel(blockOres, 0, "pickaxe", 2);
/* 228:215 */     MinecraftForge.setBlockHarvestLevel(blockOres, 1, "pickaxe", 2);
/* 229:216 */     MinecraftForge.setBlockHarvestLevel(blockOres, 2, "pickaxe", 2);
/* 230:217 */     MinecraftForge.setBlockHarvestLevel(blockOres, 3, "pickaxe", 1);
/* 231:218 */     MinecraftForge.setBlockHarvestLevel(blockOres, 4, "pickaxe", 0);
/* 232:219 */     MinecraftForge.setBlockHarvestLevel(blockOres, 5, "pickaxe", 0);
/* 233:220 */     MinecraftForge.setBlockHarvestLevel(blockOres, 6, "pickaxe", 2);
/* 234:221 */     MinecraftForge.setBlockHarvestLevel(blockOres, 7, "pickaxe", 2);
/* 235:    */     
/* 236:    */ 
/* 237:224 */     wj.a().addSmelting(blockOres.cm, 3, RedPowerBase.itemIngotSilver, 1.0F);
/* 238:    */     
/* 239:226 */     wj.a().addSmelting(blockOres.cm, 4, RedPowerBase.itemIngotTin, 0.7F);
/* 240:    */     
/* 241:228 */     wj.a().addSmelting(blockOres.cm, 5, RedPowerBase.itemIngotCopper, 0.7F);
/* 242:    */     
/* 243:    */ 
/* 244:231 */     OreDictionary.registerOre("oreRuby", new ur(blockOres, 1, 0));
/* 245:    */     
/* 246:233 */     OreDictionary.registerOre("oreGreenSapphire", new ur(blockOres, 1, 1));
/* 247:    */     
/* 248:235 */     OreDictionary.registerOre("oreSapphire", new ur(blockOres, 1, 2));
/* 249:    */     
/* 250:237 */     OreDictionary.registerOre("oreSilver", new ur(blockOres, 1, 3));
/* 251:    */     
/* 252:239 */     OreDictionary.registerOre("oreTin", new ur(blockOres, 1, 4));
/* 253:    */     
/* 254:241 */     OreDictionary.registerOre("oreCopper", new ur(blockOres, 1, 5));
/* 255:    */     
/* 256:243 */     OreDictionary.registerOre("oreTungsten", new ur(blockOres, 1, 6));
/* 257:    */     
/* 258:245 */     OreDictionary.registerOre("oreNikolite", new ur(blockOres, 1, 7));
/* 259:    */     
/* 260:    */ 
/* 261:    */ 
/* 262:249 */     GameRegistry.addRecipe(new ur(blockStone, 4, 2), new Object[] { "SS", "SS", Character.valueOf('S'), new ur(blockStone, 1, 0) });
/* 263:    */     
/* 264:    */ 
/* 265:    */ 
/* 266:253 */     wj.a().addSmelting(blockStone.cm, 3, new ur(blockStone, 1, 1), 0.2F);
/* 267:    */     
/* 268:    */ 
/* 269:256 */     GameRegistry.addRecipe(new ur(blockStone, 4, 4), new Object[] { "SS", "SS", Character.valueOf('S'), new ur(blockStone, 1, 1) });
/* 270:    */     
/* 271:    */ 
/* 272:    */ 
/* 273:260 */     GameRegistry.addRecipe(new ur(blockStone, 4, 5), new Object[] { "SS", "SS", Character.valueOf('S'), new ur(blockStone, 1, 4) });
/* 274:    */     
/* 275:    */ 
/* 276:263 */     GameRegistry.addRecipe(new ur(blockStone, 1, 6), new Object[] { "S", Character.valueOf('S'), new ur(blockStone, 1, 1) });
/* 277:    */     
/* 278:    */ 
/* 279:    */ 
/* 280:    */ 
/* 281:268 */     blockStorage = new BlockStorage(Config.getBlockID("blocks.world.storage.id"));
/* 282:    */     
/* 283:270 */     GameRegistry.registerBlock(blockStorage, ItemStorage.class, "orestorage");
/* 284:    */     
/* 285:    */ 
/* 286:273 */     GameRegistry.addRecipe(new ur(blockStorage, 1, 0), new Object[] { "GGG", "GGG", "GGG", Character.valueOf('G'), RedPowerBase.itemRuby });
/* 287:    */     
/* 288:    */ 
/* 289:276 */     GameRegistry.addRecipe(new ur(blockStorage, 1, 1), new Object[] { "GGG", "GGG", "GGG", Character.valueOf('G'), RedPowerBase.itemGreenSapphire });
/* 290:    */     
/* 291:    */ 
/* 292:279 */     GameRegistry.addRecipe(new ur(blockStorage, 1, 2), new Object[] { "GGG", "GGG", "GGG", Character.valueOf('G'), RedPowerBase.itemSapphire });
/* 293:    */     
/* 294:    */ 
/* 295:282 */     GameRegistry.addRecipe(new ur(blockStorage, 1, 3), new Object[] { "GGG", "GGG", "GGG", Character.valueOf('G'), RedPowerBase.itemIngotSilver });
/* 296:    */     
/* 297:    */ 
/* 298:285 */     GameRegistry.addRecipe(new ur(blockStorage, 1, 4), new Object[] { "GGG", "GGG", "GGG", Character.valueOf('G'), RedPowerBase.itemIngotTin });
/* 299:    */     
/* 300:    */ 
/* 301:288 */     GameRegistry.addRecipe(new ur(blockStorage, 1, 5), new Object[] { "GGG", "GGG", "GGG", Character.valueOf('G'), RedPowerBase.itemIngotCopper });
/* 302:    */     
/* 303:    */ 
/* 304:    */ 
/* 305:292 */     GameRegistry.addRecipe(CoreLib.copyStack(RedPowerBase.itemRuby, 9), new Object[] { "G", Character.valueOf('G'), new ur(blockStorage, 1, 0) });
/* 306:    */     
/* 307:    */ 
/* 308:295 */     GameRegistry.addRecipe(CoreLib.copyStack(RedPowerBase.itemGreenSapphire, 9), new Object[] { "G", Character.valueOf('G'), new ur(blockStorage, 1, 1) });
/* 309:    */     
/* 310:    */ 
/* 311:298 */     GameRegistry.addRecipe(CoreLib.copyStack(RedPowerBase.itemSapphire, 9), new Object[] { "G", Character.valueOf('G'), new ur(blockStorage, 1, 2) });
/* 312:    */     
/* 313:    */ 
/* 314:301 */     GameRegistry.addRecipe(CoreLib.copyStack(RedPowerBase.itemIngotSilver, 9), new Object[] { "G", Character.valueOf('G'), new ur(blockStorage, 1, 3) });
/* 315:    */     
/* 316:    */ 
/* 317:304 */     GameRegistry.addRecipe(CoreLib.copyStack(RedPowerBase.itemIngotTin, 9), new Object[] { "G", Character.valueOf('G'), new ur(blockStorage, 1, 4) });
/* 318:    */     
/* 319:    */ 
/* 320:307 */     GameRegistry.addRecipe(CoreLib.copyStack(RedPowerBase.itemIngotCopper, 9), new Object[] { "G", Character.valueOf('G'), new ur(blockStorage, 1, 5) });
/* 321:    */     
/* 322:    */ 
/* 323:310 */     MinecraftForge.setBlockHarvestLevel(blockStorage, 0, "pickaxe", 2);
/* 324:311 */     MinecraftForge.setBlockHarvestLevel(blockStorage, 1, "pickaxe", 2);
/* 325:312 */     MinecraftForge.setBlockHarvestLevel(blockStorage, 2, "pickaxe", 2);
/* 326:313 */     MinecraftForge.setBlockHarvestLevel(blockStorage, 3, "pickaxe", 2);
/* 327:314 */     MinecraftForge.setBlockHarvestLevel(blockStorage, 4, "pickaxe", 2);
/* 328:315 */     MinecraftForge.setBlockHarvestLevel(blockStorage, 5, "pickaxe", 2);
/* 329:    */     
/* 330:317 */     CoverLib.addMaterial(54, 2, blockStorage, 0, "rubyBlock", "Ruby Block");
/* 331:    */     
/* 332:319 */     CoverLib.addMaterial(55, 2, blockStorage, 1, "greenSapphireBlock", "Green Sapphire Block");
/* 333:    */     
/* 334:321 */     CoverLib.addMaterial(56, 2, blockStorage, 2, "sapphireBlock", "Sapphire Block");
/* 335:    */     
/* 336:323 */     CoverLib.addMaterial(66, 2, blockStorage, 3, "silverBlock", "Silver Block");
/* 337:    */     
/* 338:325 */     CoverLib.addMaterial(67, 2, blockStorage, 4, "tinBlock", "Tin Block");
/* 339:    */     
/* 340:327 */     CoverLib.addMaterial(68, 2, blockStorage, 5, "copperBlock", "Copper Block");
/* 341:    */   }
/* 342:    */   
/* 343:    */   public void setupTools()
/* 344:    */   {
/* 345:332 */     toolMaterialRuby = EnumHelper.addToolMaterial("RUBY", 2, 500, 8.0F, 3, 12);
/* 346:    */     
/* 347:334 */     toolMaterialGreenSapphire = EnumHelper.addToolMaterial("GREENSAPPHIRE", 2, 500, 8.0F, 3, 12);
/* 348:    */     
/* 349:336 */     toolMaterialSapphire = EnumHelper.addToolMaterial("SAPPHIRE", 2, 500, 8.0F, 3, 12);
/* 350:    */     
/* 351:    */ 
/* 352:    */ 
/* 353:    */ 
/* 354:341 */     itemPickaxeRuby = new ItemCustomPickaxe(Config.getItemID("items.world.pickaxeRuby.id"), toolMaterialRuby);
/* 355:    */     
/* 356:    */ 
/* 357:344 */     itemPickaxeRuby.b("pickaxeRuby");
/* 358:345 */     itemPickaxeRuby.b(0, 4);
/* 359:    */     
/* 360:    */ 
/* 361:    */ 
/* 362:349 */     itemPickaxeGreenSapphire = new ItemCustomPickaxe(Config.getItemID("items.world.pickaxeGreenSapphire.id"), toolMaterialGreenSapphire);
/* 363:    */     
/* 364:    */ 
/* 365:352 */     itemPickaxeGreenSapphire.b("pickaxeGreenSapphire");
/* 366:353 */     itemPickaxeGreenSapphire.b(1, 4);
/* 367:    */     
/* 368:    */ 
/* 369:    */ 
/* 370:357 */     itemPickaxeSapphire = new ItemCustomPickaxe(Config.getItemID("items.world.pickaxeSapphire.id"), toolMaterialSapphire);
/* 371:    */     
/* 372:    */ 
/* 373:360 */     itemPickaxeSapphire.b("pickaxeSapphire");
/* 374:361 */     itemPickaxeSapphire.b(2, 4);
/* 375:    */     
/* 376:    */ 
/* 377:    */ 
/* 378:365 */     MinecraftForge.setToolClass(itemPickaxeRuby, "pickaxe", 2);
/* 379:366 */     MinecraftForge.setToolClass(itemPickaxeGreenSapphire, "pickaxe", 2);
/* 380:367 */     MinecraftForge.setToolClass(itemPickaxeSapphire, "pickaxe", 2);
/* 381:    */     
/* 382:369 */     GameRegistry.addRecipe(new ur(itemPickaxeRuby, 1), new Object[] { "GGG", " W ", " W ", Character.valueOf('G'), RedPowerBase.itemRuby, Character.valueOf('W'), up.D });
/* 383:    */     
/* 384:    */ 
/* 385:    */ 
/* 386:373 */     GameRegistry.addRecipe(new ur(itemPickaxeGreenSapphire, 1), new Object[] { "GGG", " W ", " W ", Character.valueOf('G'), RedPowerBase.itemGreenSapphire, Character.valueOf('W'), up.D });
/* 387:    */     
/* 388:    */ 
/* 389:    */ 
/* 390:377 */     GameRegistry.addRecipe(new ur(itemPickaxeSapphire, 1), new Object[] { "GGG", " W ", " W ", Character.valueOf('G'), RedPowerBase.itemSapphire, Character.valueOf('W'), up.D });
/* 391:    */     
/* 392:    */ 
/* 393:    */ 
/* 394:    */ 
/* 395:    */ 
/* 396:383 */     itemShovelRuby = new ItemCustomShovel(Config.getItemID("items.world.shovelRuby.id"), toolMaterialRuby);
/* 397:    */     
/* 398:    */ 
/* 399:386 */     itemShovelRuby.b("shovelRuby");
/* 400:387 */     itemShovelRuby.b(0, 3);
/* 401:    */     
/* 402:    */ 
/* 403:    */ 
/* 404:391 */     itemShovelGreenSapphire = new ItemCustomShovel(Config.getItemID("items.world.shovelGreenSapphire.id"), toolMaterialGreenSapphire);
/* 405:    */     
/* 406:    */ 
/* 407:394 */     itemShovelGreenSapphire.b("shovelGreenSapphire");
/* 408:395 */     itemShovelGreenSapphire.b(1, 3);
/* 409:    */     
/* 410:    */ 
/* 411:    */ 
/* 412:399 */     itemShovelSapphire = new ItemCustomShovel(Config.getItemID("items.world.shovelSapphire.id"), toolMaterialSapphire);
/* 413:    */     
/* 414:    */ 
/* 415:402 */     itemShovelSapphire.b("shovelSapphire");
/* 416:403 */     itemShovelSapphire.b(2, 3);
/* 417:    */     
/* 418:    */ 
/* 419:    */ 
/* 420:407 */     MinecraftForge.setToolClass(itemShovelRuby, "shovel", 2);
/* 421:408 */     MinecraftForge.setToolClass(itemShovelGreenSapphire, "shovel", 2);
/* 422:409 */     MinecraftForge.setToolClass(itemShovelSapphire, "shovel", 2);
/* 423:    */     
/* 424:411 */     GameRegistry.addRecipe(new ur(itemShovelRuby, 1), new Object[] { "G", "W", "W", Character.valueOf('G'), RedPowerBase.itemRuby, Character.valueOf('W'), up.D });
/* 425:    */     
/* 426:    */ 
/* 427:    */ 
/* 428:415 */     GameRegistry.addRecipe(new ur(itemShovelGreenSapphire, 1), new Object[] { "G", "W", "W", Character.valueOf('G'), RedPowerBase.itemGreenSapphire, Character.valueOf('W'), up.D });
/* 429:    */     
/* 430:    */ 
/* 431:    */ 
/* 432:419 */     GameRegistry.addRecipe(new ur(itemShovelSapphire, 1), new Object[] { "G", "W", "W", Character.valueOf('G'), RedPowerBase.itemSapphire, Character.valueOf('W'), up.D });
/* 433:    */     
/* 434:    */ 
/* 435:    */ 
/* 436:    */ 
/* 437:    */ 
/* 438:425 */     itemAxeRuby = new ItemCustomAxe(Config.getItemID("items.world.axeRuby.id"), toolMaterialRuby);
/* 439:    */     
/* 440:    */ 
/* 441:428 */     itemAxeRuby.b("axeRuby");
/* 442:429 */     itemAxeRuby.b(0, 5);
/* 443:    */     
/* 444:    */ 
/* 445:    */ 
/* 446:433 */     itemAxeGreenSapphire = new ItemCustomAxe(Config.getItemID("items.world.axeGreenSapphire.id"), toolMaterialGreenSapphire);
/* 447:    */     
/* 448:    */ 
/* 449:436 */     itemAxeGreenSapphire.b("axeGreenSapphire");
/* 450:437 */     itemAxeGreenSapphire.b(1, 5);
/* 451:    */     
/* 452:    */ 
/* 453:    */ 
/* 454:441 */     itemAxeSapphire = new ItemCustomAxe(Config.getItemID("items.world.axeSapphire.id"), toolMaterialSapphire);
/* 455:    */     
/* 456:    */ 
/* 457:444 */     itemAxeSapphire.b("axeSapphire");
/* 458:445 */     itemAxeSapphire.b(2, 5);
/* 459:    */     
/* 460:    */ 
/* 461:    */ 
/* 462:449 */     MinecraftForge.setToolClass(itemAxeRuby, "axe", 2);
/* 463:450 */     MinecraftForge.setToolClass(itemAxeGreenSapphire, "axe", 2);
/* 464:451 */     MinecraftForge.setToolClass(itemAxeSapphire, "axe", 2);
/* 465:    */     
/* 466:453 */     GameRegistry.addRecipe(new ur(itemAxeRuby, 1), new Object[] { "GG", "GW", " W", Character.valueOf('G'), RedPowerBase.itemRuby, Character.valueOf('W'), up.D });
/* 467:    */     
/* 468:    */ 
/* 469:    */ 
/* 470:457 */     GameRegistry.addRecipe(new ur(itemAxeGreenSapphire, 1), new Object[] { "GG", "GW", " W", Character.valueOf('G'), RedPowerBase.itemGreenSapphire, Character.valueOf('W'), up.D });
/* 471:    */     
/* 472:    */ 
/* 473:    */ 
/* 474:461 */     GameRegistry.addRecipe(new ur(itemAxeSapphire, 1), new Object[] { "GG", "GW", " W", Character.valueOf('G'), RedPowerBase.itemSapphire, Character.valueOf('W'), up.D });
/* 475:    */     
/* 476:    */ 
/* 477:    */ 
/* 478:    */ 
/* 479:    */ 
/* 480:467 */     itemSwordRuby = new ItemCustomSword(Config.getItemID("items.world.swordRuby.id"), toolMaterialRuby);
/* 481:    */     
/* 482:    */ 
/* 483:470 */     itemSwordRuby.b("swordRuby");
/* 484:471 */     itemSwordRuby.b(0, 2);
/* 485:    */     
/* 486:    */ 
/* 487:474 */     itemSwordGreenSapphire = new ItemCustomSword(Config.getItemID("items.world.swordGreenSapphire.id"), toolMaterialGreenSapphire);
/* 488:    */     
/* 489:    */ 
/* 490:477 */     itemSwordGreenSapphire.b("swordGreenSapphire");
/* 491:478 */     itemSwordGreenSapphire.b(1, 2);
/* 492:    */     
/* 493:    */ 
/* 494:481 */     itemSwordSapphire = new ItemCustomSword(Config.getItemID("items.world.swordSapphire.id"), toolMaterialSapphire);
/* 495:    */     
/* 496:    */ 
/* 497:484 */     itemSwordSapphire.b("swordSapphire");
/* 498:485 */     itemSwordSapphire.b(2, 2);
/* 499:    */     
/* 500:    */ 
/* 501:    */ 
/* 502:489 */     itemAthame = new ItemAthame(Config.getItemID("items.world.athame.id"));
/* 503:    */     
/* 504:491 */     itemAthame.b("athame");
/* 505:    */     
/* 506:493 */     MinecraftForge.setToolClass(itemSwordRuby, "sword", 2);
/* 507:494 */     MinecraftForge.setToolClass(itemSwordGreenSapphire, "sword", 2);
/* 508:495 */     MinecraftForge.setToolClass(itemSwordSapphire, "sword", 2);
/* 509:496 */     MinecraftForge.setToolClass(itemAthame, "sword", 0);
/* 510:    */     
/* 511:498 */     CraftLib.addOreRecipe(new ur(itemAthame, 1), new Object[] { "S", "W", Character.valueOf('S'), "ingotSilver", Character.valueOf('W'), up.D });
/* 512:    */     
/* 513:    */ 
/* 514:    */ 
/* 515:    */ 
/* 516:503 */     GameRegistry.addRecipe(new ur(itemSwordRuby, 1), new Object[] { "G", "G", "W", Character.valueOf('G'), RedPowerBase.itemRuby, Character.valueOf('W'), up.D });
/* 517:    */     
/* 518:    */ 
/* 519:    */ 
/* 520:507 */     GameRegistry.addRecipe(new ur(itemSwordGreenSapphire, 1), new Object[] { "G", "G", "W", Character.valueOf('G'), RedPowerBase.itemGreenSapphire, Character.valueOf('W'), up.D });
/* 521:    */     
/* 522:    */ 
/* 523:    */ 
/* 524:511 */     GameRegistry.addRecipe(new ur(itemSwordSapphire, 1), new Object[] { "G", "G", "W", Character.valueOf('G'), RedPowerBase.itemSapphire, Character.valueOf('W'), up.D });
/* 525:    */     
/* 526:    */ 
/* 527:    */ 
/* 528:    */ 
/* 529:    */ 
/* 530:517 */     itemHoeRuby = new ItemCustomHoe(Config.getItemID("items.world.hoeRuby.id"), toolMaterialRuby);
/* 531:    */     
/* 532:    */ 
/* 533:520 */     itemHoeRuby.b("hoeRuby");
/* 534:521 */     itemHoeRuby.b(0, 6);
/* 535:522 */     itemHoeRuby.e(500);
/* 536:    */     
/* 537:524 */     itemHoeGreenSapphire = new ItemCustomHoe(Config.getItemID("items.world.hoeGreenSapphire.id"), toolMaterialGreenSapphire);
/* 538:    */     
/* 539:    */ 
/* 540:527 */     itemHoeGreenSapphire.b("hoeGreenSapphire");
/* 541:528 */     itemHoeGreenSapphire.b(1, 6);
/* 542:529 */     itemHoeGreenSapphire.e(500);
/* 543:    */     
/* 544:531 */     itemHoeSapphire = new ItemCustomHoe(Config.getItemID("items.world.hoeSapphire.id"), toolMaterialSapphire);
/* 545:    */     
/* 546:    */ 
/* 547:534 */     itemHoeSapphire.b("hoeSapphire");
/* 548:535 */     itemHoeSapphire.b(2, 6);
/* 549:536 */     itemHoeSapphire.e(500);
/* 550:    */     
/* 551:538 */     MinecraftForge.setToolClass(itemHoeRuby, "hoe", 2);
/* 552:539 */     MinecraftForge.setToolClass(itemHoeGreenSapphire, "hoe", 2);
/* 553:540 */     MinecraftForge.setToolClass(itemHoeSapphire, "hoe", 2);
/* 554:    */     
/* 555:542 */     GameRegistry.addRecipe(new ur(itemHoeRuby, 1), new Object[] { "GG", " W", " W", Character.valueOf('G'), RedPowerBase.itemRuby, Character.valueOf('W'), up.D });
/* 556:    */     
/* 557:    */ 
/* 558:    */ 
/* 559:546 */     GameRegistry.addRecipe(new ur(itemHoeGreenSapphire, 1), new Object[] { "GG", " W", " W", Character.valueOf('G'), RedPowerBase.itemGreenSapphire, Character.valueOf('W'), up.D });
/* 560:    */     
/* 561:    */ 
/* 562:    */ 
/* 563:550 */     GameRegistry.addRecipe(new ur(itemHoeSapphire, 1), new Object[] { "GG", " W", " W", Character.valueOf('G'), RedPowerBase.itemSapphire, Character.valueOf('W'), up.D });
/* 564:    */     
/* 565:    */ 
/* 566:    */ 
/* 567:    */ 
/* 568:    */ 
/* 569:556 */     itemSickleWood = new ItemSickle(Config.getItemID("items.world.sickleWood.id"), uq.a);
/* 570:    */     
/* 571:    */ 
/* 572:559 */     itemSickleWood.b("sickleWood");
/* 573:560 */     itemSickleWood.b(0, 1);
/* 574:    */     
/* 575:    */ 
/* 576:    */ 
/* 577:564 */     itemSickleStone = new ItemSickle(Config.getItemID("items.world.sickleStone.id"), uq.b);
/* 578:    */     
/* 579:    */ 
/* 580:567 */     itemSickleStone.b("sickleStone");
/* 581:568 */     itemSickleStone.b(1, 1);
/* 582:    */     
/* 583:    */ 
/* 584:    */ 
/* 585:572 */     itemSickleIron = new ItemSickle(Config.getItemID("items.world.sickleIron.id"), uq.c);
/* 586:    */     
/* 587:    */ 
/* 588:575 */     itemSickleIron.b("sickleIron");
/* 589:576 */     itemSickleIron.b(2, 1);
/* 590:    */     
/* 591:    */ 
/* 592:    */ 
/* 593:580 */     itemSickleDiamond = new ItemSickle(Config.getItemID("items.world.sickleDiamond.id"), uq.d);
/* 594:    */     
/* 595:    */ 
/* 596:583 */     itemSickleDiamond.b("sickleDiamond");
/* 597:584 */     itemSickleDiamond.b(3, 1);
/* 598:    */     
/* 599:    */ 
/* 600:    */ 
/* 601:588 */     itemSickleGold = new ItemSickle(Config.getItemID("items.world.sickleGold.id"), uq.e);
/* 602:    */     
/* 603:    */ 
/* 604:591 */     itemSickleGold.b("sickleGold");
/* 605:592 */     itemSickleGold.b(4, 1);
/* 606:    */     
/* 607:    */ 
/* 608:    */ 
/* 609:596 */     itemSickleRuby = new ItemSickle(Config.getItemID("items.world.sickleRuby.id"), toolMaterialRuby);
/* 610:    */     
/* 611:    */ 
/* 612:599 */     itemSickleRuby.b("sickleRuby");
/* 613:600 */     itemSickleRuby.b(5, 1);
/* 614:    */     
/* 615:    */ 
/* 616:    */ 
/* 617:604 */     itemSickleGreenSapphire = new ItemSickle(Config.getItemID("items.world.sickleGreenSapphire.id"), toolMaterialGreenSapphire);
/* 618:    */     
/* 619:    */ 
/* 620:607 */     itemSickleGreenSapphire.b("sickleGreenSapphire");
/* 621:608 */     itemSickleGreenSapphire.b(6, 1);
/* 622:    */     
/* 623:    */ 
/* 624:    */ 
/* 625:612 */     itemSickleSapphire = new ItemSickle(Config.getItemID("items.world.sickleSapphire.id"), toolMaterialSapphire);
/* 626:    */     
/* 627:    */ 
/* 628:615 */     itemSickleSapphire.b("sickleSapphire");
/* 629:616 */     itemSickleSapphire.b(7, 1);
/* 630:    */     
/* 631:    */ 
/* 632:    */ 
/* 633:620 */     CraftLib.addOreRecipe(new ur(itemSickleWood, 1), new Object[] { " I ", "  I", "WI ", Character.valueOf('I'), "plankWood", Character.valueOf('W'), up.D });
/* 634:    */     
/* 635:    */ 
/* 636:    */ 
/* 637:624 */     GameRegistry.addRecipe(new ur(itemSickleStone, 1), new Object[] { " I ", "  I", "WI ", Character.valueOf('I'), amq.z, Character.valueOf('W'), up.D });
/* 638:    */     
/* 639:    */ 
/* 640:    */ 
/* 641:628 */     GameRegistry.addRecipe(new ur(itemSickleIron, 1), new Object[] { " I ", "  I", "WI ", Character.valueOf('I'), up.o, Character.valueOf('W'), up.D });
/* 642:    */     
/* 643:    */ 
/* 644:    */ 
/* 645:632 */     GameRegistry.addRecipe(new ur(itemSickleDiamond, 1), new Object[] { " I ", "  I", "WI ", Character.valueOf('I'), up.n, Character.valueOf('W'), up.D });
/* 646:    */     
/* 647:    */ 
/* 648:    */ 
/* 649:636 */     GameRegistry.addRecipe(new ur(itemSickleGold, 1), new Object[] { " I ", "  I", "WI ", Character.valueOf('I'), up.p, Character.valueOf('W'), up.D });
/* 650:    */     
/* 651:    */ 
/* 652:    */ 
/* 653:640 */     GameRegistry.addRecipe(new ur(itemSickleRuby, 1), new Object[] { " I ", "  I", "WI ", Character.valueOf('I'), RedPowerBase.itemRuby, Character.valueOf('W'), up.D });
/* 654:    */     
/* 655:    */ 
/* 656:    */ 
/* 657:644 */     GameRegistry.addRecipe(new ur(itemSickleGreenSapphire, 1), new Object[] { " I ", "  I", "WI ", Character.valueOf('I'), RedPowerBase.itemGreenSapphire, Character.valueOf('W'), up.D });
/* 658:    */     
/* 659:    */ 
/* 660:    */ 
/* 661:648 */     GameRegistry.addRecipe(new ur(itemSickleSapphire, 1), new Object[] { " I ", "  I", "WI ", Character.valueOf('I'), RedPowerBase.itemSapphire, Character.valueOf('W'), up.D });
/* 662:    */     
/* 663:    */ 
/* 664:    */ 
/* 665:    */ 
/* 666:    */ 
/* 667:654 */     itemHandsawRuby = new ItemHandsaw(Config.getItemID("items.world.handsawRuby.id"), 1);
/* 668:    */     
/* 669:656 */     itemHandsawGreenSapphire = new ItemHandsaw(Config.getItemID("items.world.handsawGreenSapphire.id"), 1);
/* 670:    */     
/* 671:658 */     itemHandsawSapphire = new ItemHandsaw(Config.getItemID("items.world.handsawSapphire.id"), 1);
/* 672:    */     
/* 673:    */ 
/* 674:661 */     itemHandsawRuby.b("handsawRuby").c(19);
/* 675:662 */     itemHandsawGreenSapphire.b("handsawGreenSapphire").c(20);
/* 676:663 */     itemHandsawSapphire.b("handsawSapphire").c(21);
/* 677:664 */     itemHandsawRuby.e(640);
/* 678:665 */     itemHandsawGreenSapphire.e(640);
/* 679:666 */     itemHandsawSapphire.e(640);
/* 680:    */     
/* 681:668 */     GameRegistry.addRecipe(new ur(itemHandsawRuby, 1), new Object[] { "WWW", " II", " GG", Character.valueOf('I'), up.o, Character.valueOf('G'), RedPowerBase.itemRuby, Character.valueOf('W'), up.D });
/* 682:    */     
/* 683:    */ 
/* 684:    */ 
/* 685:    */ 
/* 686:673 */     GameRegistry.addRecipe(new ur(itemHandsawGreenSapphire, 1), new Object[] { "WWW", " II", " GG", Character.valueOf('I'), up.o, Character.valueOf('G'), RedPowerBase.itemGreenSapphire, Character.valueOf('W'), up.D });
/* 687:    */     
/* 688:    */ 
/* 689:    */ 
/* 690:    */ 
/* 691:678 */     GameRegistry.addRecipe(new ur(itemHandsawSapphire, 1), new Object[] { "WWW", " II", " GG", Character.valueOf('I'), up.o, Character.valueOf('G'), RedPowerBase.itemSapphire, Character.valueOf('W'), up.D });
/* 692:    */     
/* 693:    */ 
/* 694:    */ 
/* 695:    */ 
/* 696:    */ 
/* 697:    */ 
/* 698:685 */     itemWoolCard = new ItemWoolCard(Config.getItemID("items.world.woolcard.id"));
/* 699:    */     
/* 700:687 */     itemWoolCard.b("woolcard").c(28);
/* 701:688 */     itemWoolCard.e(63);
/* 702:    */     
/* 703:690 */     CraftLib.addOreRecipe(new ur(itemWoolCard, 1), new Object[] { "W", "P", "S", Character.valueOf('W'), RedPowerBase.itemFineIron, Character.valueOf('P'), "plankWood", Character.valueOf('S'), up.D });
/* 704:    */     
/* 705:    */ 
/* 706:    */ 
/* 707:    */ 
/* 708:695 */     GameRegistry.addShapelessRecipe(new ur(up.K, 4), new Object[] { new ur(itemWoolCard, 1, -1), new ur(amq.ae, 1, -1) });
/* 709:    */     
/* 710:    */ 
/* 711:    */ 
/* 712:    */ 
/* 713:700 */     itemBrushDry = new ItemTextured(Config.getItemID("items.world.paintbrush.dry.id"), 22, "/eloraam/base/items1.png");
/* 714:    */     
/* 715:    */ 
/* 716:703 */     itemBrushDry.b("paintbrush.dry");
/* 717:    */     
/* 718:705 */     GameRegistry.addRecipe(new ur(itemBrushDry), new Object[] { "W ", " S", Character.valueOf('S'), up.D, Character.valueOf('W'), amq.ae });
/* 719:    */     
/* 720:    */ 
/* 721:    */ 
/* 722:    */ 
/* 723:710 */     itemPaintCanEmpty = new ItemTextured(Config.getItemID("items.world.paintcan.empty.id"), 23, "/eloraam/base/items1.png");
/* 724:    */     
/* 725:    */ 
/* 726:713 */     itemPaintCanEmpty.b("paintcan.empty");
/* 727:    */     
/* 728:715 */     GameRegistry.addRecipe(new ur(itemPaintCanEmpty, 3), new Object[] { "T T", "T T", "TTT", Character.valueOf('T'), RedPowerBase.itemTinplate });
/* 729:719 */     for (int i = 0; i < 16; i++)
/* 730:    */     {
/* 731:720 */       itemPaintCanPaint[i] = new ItemPaintCan(Config.getItemID("items.world.paintcan." + CoreLib.rawColorNames[i] + ".id"), i);
/* 732:    */       
/* 733:    */ 
/* 734:723 */       itemPaintCanPaint[i].b("paintcan." + CoreLib.rawColorNames[i]);
/* 735:    */       
/* 736:725 */       itemPaintCanPaint[i].setEmptyItem(new ur(itemPaintCanEmpty));
/* 737:    */       
/* 738:727 */       Config.addName("item.paintcan." + CoreLib.rawColorNames[i] + ".name", CoreLib.enColorNames[i] + " Paint");
/* 739:    */       
/* 740:    */ 
/* 741:730 */       GameRegistry.addShapelessRecipe(new ur(itemPaintCanPaint[i]), new Object[] { itemPaintCanEmpty, new ur(up.aW, 1, 15 - i), new ur(itemSeeds, 1, 0), new ur(itemSeeds, 1, 0) });
/* 742:    */       
/* 743:    */ 
/* 744:    */ 
/* 745:    */ 
/* 746:    */ 
/* 747:    */ 
/* 748:737 */       itemBrushPaint[i] = new ItemPaintBrush(Config.getItemID("items.world.paintbrush." + CoreLib.rawColorNames[i] + ".id"), i);
/* 749:    */       
/* 750:    */ 
/* 751:740 */       itemBrushPaint[i].b("paintbrush." + CoreLib.rawColorNames[i]);
/* 752:    */       
/* 753:742 */       Config.addName("item.paintbrush." + CoreLib.rawColorNames[i] + ".name", CoreLib.enColorNames[i] + " Paint Brush");
/* 754:    */       
/* 755:    */ 
/* 756:    */ 
/* 757:746 */       GameRegistry.addShapelessRecipe(new ur(itemBrushPaint[i]), new Object[] { new ur(itemPaintCanPaint[i], 1, -1), itemBrushDry });
/* 758:    */     }
/* 759:751 */     CraftLib.addShapelessOreRecipe(new ur(itemPaintCanPaint[11]), new Object[] { itemPaintCanEmpty, "dyeBlue", new ur(itemSeeds, 1, 0), new ur(itemSeeds, 1, 0) });
/* 760:    */     
/* 761:    */ 
/* 762:    */ 
/* 763:    */ 
/* 764:    */ 
/* 765:    */ 
/* 766:758 */     itemSeedBag = new ItemSeedBag(Config.getItemID("items.world.seedbag.id"));
/* 767:    */     
/* 768:760 */     GameRegistry.addRecipe(new ur(itemSeedBag, 1, 0), new Object[] { " S ", "C C", "CCC", Character.valueOf('S'), up.K, Character.valueOf('C'), RedPowerBase.itemCanvas });
/* 769:    */   }
/* 770:    */   
/* 771:    */   void setupMisc()
/* 772:    */   {
/* 773:769 */     if (Config.getInt("settings.world.tweaks.spreadmoss") > 0)
/* 774:    */     {
/* 775:770 */       int cbid = amq.ar.cm;
/* 776:771 */       amq.p[cbid] = null;
/* 777:772 */       new BlockCobbleMossifier(cbid);
/* 778:    */       
/* 779:774 */       cbid = amq.bp.cm;
/* 780:775 */       amq.p[cbid] = null;
/* 781:776 */       new BlockBrickMossifier(cbid);
/* 782:    */     }
/* 783:779 */     if (Config.getInt("settings.world.tweaks.craftcircle") > 0) {
/* 784:780 */       GameRegistry.addRecipe(new ur(amq.bp, 4, 3), new Object[] { "BB", "BB", Character.valueOf('B'), new ur(amq.bp, 1, 0) });
/* 785:    */     }
/* 786:786 */     if (Config.getInt("settings.world.tweaks.unbricks") > 0) {
/* 787:787 */       GameRegistry.addShapelessRecipe(new ur(up.aH, 4, 0), new Object[] { new ur(amq.ao, 1, 0) });
/* 788:    */     }
/* 789:793 */     enchantDisjunction = new EnchantmentDisjunction(Config.getInt("enchant.disjunction.id"), 10);
/* 790:    */     
/* 791:795 */     enchantVorpal = new EnchantmentVorpal(Config.getInt("enchant.vorpal.id"), 10);
/* 792:    */   }
/* 793:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerWorld
 * JD-Core Version:    0.7.0.1
 */