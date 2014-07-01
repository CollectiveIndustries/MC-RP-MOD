/*   1:    */ package com.eloraam.redpower;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.base.BlockAppliance;
/*   5:    */ import com.eloraam.redpower.base.BlockMicro;
/*   6:    */ import com.eloraam.redpower.core.AchieveLib;
/*   7:    */ import com.eloraam.redpower.core.Config;
/*   8:    */ import com.eloraam.redpower.core.CraftLib;
/*   9:    */ import com.eloraam.redpower.core.IMicroPlacement;
/*  10:    */ import com.eloraam.redpower.core.ItemExtended;
/*  11:    */ import com.eloraam.redpower.core.ItemParts;
/*  12:    */ import com.eloraam.redpower.core.ItemTextured;
/*  13:    */ import com.eloraam.redpower.machine.BlockFrame;
/*  14:    */ import com.eloraam.redpower.machine.BlockMachine;
/*  15:    */ import com.eloraam.redpower.machine.BlockMachinePanel;
/*  16:    */ import com.eloraam.redpower.machine.ItemBattery;
/*  17:    */ import com.eloraam.redpower.machine.ItemMachinePanel;
/*  18:    */ import com.eloraam.redpower.machine.ItemSonicDriver;
/*  19:    */ import com.eloraam.redpower.machine.ItemVoltmeter;
/*  20:    */ import com.eloraam.redpower.machine.ItemWindmill;
/*  21:    */ import com.eloraam.redpower.machine.MachineProxy;
/*  22:    */ import com.eloraam.redpower.machine.MicroPlacementTube;
/*  23:    */ import com.eloraam.redpower.machine.TileAccel;
/*  24:    */ import com.eloraam.redpower.machine.TileAssemble;
/*  25:    */ import com.eloraam.redpower.machine.TileBatteryBox;
/*  26:    */ import com.eloraam.redpower.machine.TileBlueAlloyFurnace;
/*  27:    */ import com.eloraam.redpower.machine.TileBlueFurnace;
/*  28:    */ import com.eloraam.redpower.machine.TileBreaker;
/*  29:    */ import com.eloraam.redpower.machine.TileBufferChest;
/*  30:    */ import com.eloraam.redpower.machine.TileChargingBench;
/*  31:    */ import com.eloraam.redpower.machine.TileDeploy;
/*  32:    */ import com.eloraam.redpower.machine.TileEject;
/*  33:    */ import com.eloraam.redpower.machine.TileFilter;
/*  34:    */ import com.eloraam.redpower.machine.TileFrame;
/*  35:    */ import com.eloraam.redpower.machine.TileFrameMoving;
/*  36:    */ import com.eloraam.redpower.machine.TileFrameRedstoneTube;
/*  37:    */ import com.eloraam.redpower.machine.TileFrameTube;
/*  38:    */ import com.eloraam.redpower.machine.TileGrate;
/*  39:    */ import com.eloraam.redpower.machine.TileIgniter;
/*  40:    */ import com.eloraam.redpower.machine.TileItemDetect;
/*  41:    */ import com.eloraam.redpower.machine.TileMagTube;
/*  42:    */ import com.eloraam.redpower.machine.TileManager;
/*  43:    */ import com.eloraam.redpower.machine.TileMotor;
/*  44:    */ import com.eloraam.redpower.machine.TilePipe;
/*  45:    */ import com.eloraam.redpower.machine.TilePump;
/*  46:    */ import com.eloraam.redpower.machine.TileRedstoneTube;
/*  47:    */ import com.eloraam.redpower.machine.TileRegulator;
/*  48:    */ import com.eloraam.redpower.machine.TileRelay;
/*  49:    */ import com.eloraam.redpower.machine.TileRestrictTube;
/*  50:    */ import com.eloraam.redpower.machine.TileRetriever;
/*  51:    */ import com.eloraam.redpower.machine.TileSolarPanel;
/*  52:    */ import com.eloraam.redpower.machine.TileSorter;
/*  53:    */ import com.eloraam.redpower.machine.TileSortron;
/*  54:    */ import com.eloraam.redpower.machine.TileThermopile;
/*  55:    */ import com.eloraam.redpower.machine.TileTransformer;
/*  56:    */ import com.eloraam.redpower.machine.TileTranspose;
/*  57:    */ import com.eloraam.redpower.machine.TileTube;
/*  58:    */ import com.eloraam.redpower.machine.TileWindTurbine;
/*  59:    */ import cpw.mods.fml.common.Mod;
/*  60:    */ import cpw.mods.fml.common.Mod.Init;
/*  61:    */ import cpw.mods.fml.common.Mod.Instance;
/*  62:    */ import cpw.mods.fml.common.Mod.PostInit;
/*  63:    */ import cpw.mods.fml.common.Mod.PreInit;
/*  64:    */ import cpw.mods.fml.common.SidedProxy;
/*  65:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  66:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  67:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  68:    */ import cpw.mods.fml.common.network.NetworkMod;
/*  69:    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*  70:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  71:    */ import jh;
/*  72:    */ import up;
/*  73:    */ import ur;
/*  74:    */ 
/*  75:    */ @Mod(modid="RedPowerMachine", name="RedPower Machine", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b", dependencies="required-after:RedPowerBase")
/*  76:    */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/*  77:    */ public class RedPowerMachine
/*  78:    */ {
/*  79:    */   @Mod.Instance("RedPowerMachine")
/*  80:    */   public static RedPowerMachine instance;
/*  81:    */   @SidedProxy(clientSide="com.eloraam.redpower.machine.MachineProxyClient", serverSide="com.eloraam.redpower.machine.MachineProxy")
/*  82:    */   public static MachineProxy proxy;
/*  83:    */   public static BlockMachine blockMachine;
/*  84:    */   public static BlockMachine blockMachine2;
/*  85:    */   public static BlockMachinePanel blockMachinePanel;
/*  86:    */   public static BlockFrame blockFrame;
/*  87:    */   public static ItemVoltmeter itemVoltmeter;
/*  88:    */   public static ItemSonicDriver itemSonicDriver;
/*  89:    */   public static up itemBatteryEmpty;
/*  90:    */   public static up itemBatteryPowered;
/*  91:    */   public static ItemParts itemMachineParts;
/*  92:    */   public static ur itemWoodSail;
/*  93:    */   public static up itemWoodTurbine;
/*  94:    */   public static up itemWoodWindmill;
/*  95:    */   public static final String textureFile = "/eloraam/machine/machine1.png";
/*  96:    */   public static final String textureFile2 = "/eloraam/machine/machine2.png";
/*  97:    */   public static boolean FrameAlwaysCrate;
/*  98:    */   public static int FrameLinkSize;
/*  99:    */   
/* 100:    */   @Mod.PreInit
/* 101:    */   public void preInit(FMLPreInitializationEvent event) {}
/* 102:    */   
/* 103:    */   @Mod.Init
/* 104:    */   public void load(FMLInitializationEvent event)
/* 105:    */   {
/* 106: 62 */     FrameAlwaysCrate = Config.getInt("settings.machine.frame.alwayscrate") > 0;
/* 107: 63 */     FrameLinkSize = Config.getInt("settings.machine.frame.linksize");
/* 108:    */     
/* 109: 65 */     setupItems();
/* 110: 66 */     setupBlocks();
/* 111: 67 */     initAchievements();
/* 112:    */     
/* 113: 69 */     proxy.registerRenderers();
/* 114: 70 */     NetworkRegistry.instance().registerGuiHandler(instance, proxy);
/* 115:    */   }
/* 116:    */   
/* 117:    */   @Mod.PostInit
/* 118:    */   public void postInit(FMLPostInitializationEvent event) {}
/* 119:    */   
/* 120:    */   private static void setupItems()
/* 121:    */   {
/* 122: 80 */     itemVoltmeter = new ItemVoltmeter(Config.getItemID("items.machine.voltmeter.id"));
/* 123:    */     
/* 124: 82 */     itemVoltmeter.b("voltmeter");
/* 125:    */     
/* 126:    */ 
/* 127: 85 */     itemBatteryEmpty = new ItemTextured(Config.getItemID("items.machine.battery.empty.id"), 25, "/eloraam/base/items1.png");
/* 128:    */     
/* 129:    */ 
/* 130: 88 */     itemBatteryEmpty.b("btbattery");
/* 131:    */     
/* 132: 90 */     itemBatteryPowered = new ItemBattery(Config.getItemID("items.machine.battery.powered.id"));
/* 133:    */     
/* 134: 92 */     itemBatteryPowered.b("btbattery");
/* 135:    */     
/* 136: 94 */     CraftLib.addOreRecipe(new ur(itemVoltmeter), new Object[] { "WWW", "WNW", "CCC", Character.valueOf('W'), "plankWood", Character.valueOf('N'), RedPowerBase.itemNikolite, Character.valueOf('C'), "ingotCopper" });
/* 137:    */     
/* 138:    */ 
/* 139:    */ 
/* 140:    */ 
/* 141:    */ 
/* 142:100 */     CraftLib.addOreRecipe(new ur(itemBatteryEmpty, 1), new Object[] { "NCN", "NTN", "NCN", Character.valueOf('N'), RedPowerBase.itemNikolite, Character.valueOf('C'), "ingotCopper", Character.valueOf('T'), "ingotTin" });
/* 143:    */     
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:    */ 
/* 149:107 */     itemSonicDriver = new ItemSonicDriver(Config.getItemID("items.machine.sonicDriver.id"));
/* 150:    */     
/* 151:109 */     itemSonicDriver.b("sonicDriver").c(129);
/* 152:    */     
/* 153:111 */     GameRegistry.addRecipe(new ur(itemSonicDriver, 1, itemSonicDriver.m()), new Object[] { "E  ", " R ", "  B", Character.valueOf('R'), RedPowerBase.itemIngotBrass, Character.valueOf('E'), RedPowerBase.itemGreenSapphire, Character.valueOf('B'), itemBatteryEmpty });
/* 154:    */     
/* 155:    */ 
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:    */ 
/* 161:119 */     itemWoodTurbine = new ItemWindmill(Config.getItemID("items.machine.turbineWood.id"), 1);
/* 162:    */     
/* 163:121 */     itemWoodWindmill = new ItemWindmill(Config.getItemID("items.machine.windmillWood.id"), 2);
/* 164:    */     
/* 165:123 */     itemWoodWindmill.c(178).b("windmillWood");
/* 166:    */     
/* 167:125 */     itemMachineParts = new ItemParts(Config.getItemID("items.machine.parts.id"), "/eloraam/base/items1.png");
/* 168:    */     
/* 169:    */ 
/* 170:128 */     itemMachineParts.addItem(0, 176, "item.windSailWood");
/* 171:    */     
/* 172:130 */     itemWoodSail = new ur(itemMachineParts, 1, 0);
/* 173:    */     
/* 174:132 */     CraftLib.addOreRecipe(itemWoodSail, new Object[] { "CCS", "CCW", "CCS", Character.valueOf('C'), RedPowerBase.itemCanvas, Character.valueOf('W'), "plankWood", Character.valueOf('S'), up.D });
/* 175:    */     
/* 176:    */ 
/* 177:    */ 
/* 178:    */ 
/* 179:    */ 
/* 180:138 */     GameRegistry.addRecipe(new ur(itemWoodTurbine), new Object[] { "SAS", "SAS", "SAS", Character.valueOf('S'), itemWoodSail, Character.valueOf('A'), new ur(RedPowerBase.blockMicro, 1, 5905) });
/* 181:    */     
/* 182:    */ 
/* 183:    */ 
/* 184:    */ 
/* 185:143 */     GameRegistry.addRecipe(new ur(itemWoodWindmill), new Object[] { " S ", "SAS", " S ", Character.valueOf('S'), itemWoodSail, Character.valueOf('A'), new ur(RedPowerBase.blockMicro, 1, 5905) });
/* 186:    */   }
/* 187:    */   
/* 188:    */   private static void setupBlocks()
/* 189:    */   {
/* 190:153 */     blockMachine = new BlockMachine(Config.getBlockID("blocks.machine.machine.id"));
/* 191:    */     
/* 192:155 */     blockMachine.b("rpmachine");
/* 193:156 */     GameRegistry.registerBlock(blockMachine, ItemExtended.class, "machine");
/* 194:    */     
/* 195:    */ 
/* 196:159 */     blockMachine.setItemName(0, "rpdeploy");
/* 197:160 */     blockMachine.setItemName(1, "rpbreaker");
/* 198:161 */     blockMachine.setItemName(2, "rptranspose");
/* 199:162 */     blockMachine.setItemName(3, "rpfilter");
/* 200:163 */     blockMachine.setItemName(4, "rpitemdet");
/* 201:164 */     blockMachine.setItemName(5, "rpsorter");
/* 202:165 */     blockMachine.setItemName(6, "rpbatbox");
/* 203:166 */     blockMachine.setItemName(7, "rpmotor");
/* 204:167 */     blockMachine.setItemName(8, "rpretriever");
/* 205:168 */     blockMachine.setItemName(9, "rpkgen");
/* 206:169 */     blockMachine.setItemName(10, "rpregulate");
/* 207:170 */     blockMachine.setItemName(11, "rpthermo");
/* 208:171 */     blockMachine.setItemName(12, "rpignite");
/* 209:172 */     blockMachine.setItemName(13, "rpassemble");
/* 210:173 */     blockMachine.setItemName(14, "rpeject");
/* 211:174 */     blockMachine.setItemName(15, "rprelay");
/* 212:    */     
/* 213:    */ 
/* 214:    */ 
/* 215:178 */     GameRegistry.registerTileEntity(TileWindTurbine.class, "RPWind");
/* 216:179 */     GameRegistry.registerTileEntity(TilePipe.class, "RPPipe");
/* 217:180 */     GameRegistry.registerTileEntity(TilePump.class, "RPPump");
/* 218:181 */     GameRegistry.registerTileEntity(TileTube.class, "RPTube");
/* 219:182 */     GameRegistry.registerTileEntity(TileRedstoneTube.class, "RPRSTube");
/* 220:183 */     GameRegistry.registerTileEntity(TileRestrictTube.class, "RPRTube");
/* 221:184 */     GameRegistry.registerTileEntity(TileMagTube.class, "RPMTube");
/* 222:185 */     GameRegistry.registerTileEntity(TileAccel.class, "RPAccel");
/* 223:    */     
/* 224:    */ 
/* 225:188 */     GameRegistry.registerTileEntity(TileDeploy.class, "RPDeploy");
/* 226:189 */     GameRegistry.registerTileEntity(TileBreaker.class, "RPBreaker");
/* 227:190 */     GameRegistry.registerTileEntity(TileTranspose.class, "RPTranspose");
/* 228:191 */     GameRegistry.registerTileEntity(TileFilter.class, "RPFilter");
/* 229:192 */     GameRegistry.registerTileEntity(TileItemDetect.class, "RPItemDet");
/* 230:193 */     GameRegistry.registerTileEntity(TileSorter.class, "RPSorter");
/* 231:194 */     GameRegistry.registerTileEntity(TileBatteryBox.class, "RPBatBox");
/* 232:195 */     GameRegistry.registerTileEntity(TileMotor.class, "RPMotor");
/* 233:196 */     GameRegistry.registerTileEntity(TileRetriever.class, "RPRetrieve");
/* 234:197 */     GameRegistry.registerTileEntity(TileRegulator.class, "RPRegulate");
/* 235:198 */     GameRegistry.registerTileEntity(TileThermopile.class, "RPThermo");
/* 236:199 */     GameRegistry.registerTileEntity(TileIgniter.class, "RPIgnite");
/* 237:200 */     GameRegistry.registerTileEntity(TileAssemble.class, "RPAssemble");
/* 238:201 */     GameRegistry.registerTileEntity(TileEject.class, "RPEject");
/* 239:202 */     GameRegistry.registerTileEntity(TileRelay.class, "RPRelay");
/* 240:    */     
/* 241:204 */     blockMachine.addTileEntityMapping(0, TileDeploy.class);
/* 242:205 */     blockMachine.addTileEntityMapping(1, TileBreaker.class);
/* 243:206 */     blockMachine.addTileEntityMapping(2, TileTranspose.class);
/* 244:207 */     blockMachine.addTileEntityMapping(3, TileFilter.class);
/* 245:208 */     blockMachine.addTileEntityMapping(4, TileItemDetect.class);
/* 246:209 */     blockMachine.addTileEntityMapping(5, TileSorter.class);
/* 247:210 */     blockMachine.addTileEntityMapping(6, TileBatteryBox.class);
/* 248:211 */     blockMachine.addTileEntityMapping(7, TileMotor.class);
/* 249:212 */     blockMachine.addTileEntityMapping(8, TileRetriever.class);
/* 250:213 */     blockMachine.addTileEntityMapping(9, TileWindTurbine.class);
/* 251:214 */     blockMachine.addTileEntityMapping(10, TileRegulator.class);
/* 252:215 */     blockMachine.addTileEntityMapping(11, TileThermopile.class);
/* 253:216 */     blockMachine.addTileEntityMapping(12, TileIgniter.class);
/* 254:217 */     blockMachine.addTileEntityMapping(13, TileAssemble.class);
/* 255:218 */     blockMachine.addTileEntityMapping(14, TileEject.class);
/* 256:219 */     blockMachine.addTileEntityMapping(15, TileRelay.class);
/* 257:    */     
/* 258:    */ 
/* 259:222 */     blockMachine2 = new BlockMachine(Config.getBlockID("blocks.machine.machine2.id"));
/* 260:    */     
/* 261:224 */     blockMachine.b("rpmachine2");
/* 262:225 */     GameRegistry.registerBlock(blockMachine2, ItemExtended.class, "machine2");
/* 263:    */     
/* 264:    */ 
/* 265:228 */     blockMachine2.setItemName(0, "rpsortron");
/* 266:229 */     blockMachine2.setItemName(1, "rpmanager");
/* 267:    */     
/* 268:231 */     GameRegistry.registerTileEntity(TileSortron.class, "RPSortron");
/* 269:232 */     GameRegistry.registerTileEntity(TileManager.class, "RPManager");
/* 270:    */     
/* 271:234 */     blockMachine2.addTileEntityMapping(0, TileSortron.class);
/* 272:235 */     blockMachine2.addTileEntityMapping(1, TileManager.class);
/* 273:    */     
/* 274:    */ 
/* 275:238 */     blockMachinePanel = new BlockMachinePanel(Config.getBlockID("blocks.machine.machinePanel.id"));
/* 276:    */     
/* 277:240 */     GameRegistry.registerBlock(blockMachinePanel, ItemMachinePanel.class, "machinePanel");
/* 278:    */     
/* 279:242 */     GameRegistry.registerTileEntity(TileSolarPanel.class, "RPSolar");
/* 280:243 */     GameRegistry.registerTileEntity(TileGrate.class, "RPGrate");
/* 281:244 */     GameRegistry.registerTileEntity(TileTransformer.class, "RPXfmr");
/* 282:    */     
/* 283:246 */     blockMachinePanel.addTileEntityMapping(0, TileSolarPanel.class);
/* 284:247 */     blockMachinePanel.addTileEntityMapping(1, TilePump.class);
/* 285:248 */     blockMachinePanel.addTileEntityMapping(2, TileAccel.class);
/* 286:249 */     blockMachinePanel.addTileEntityMapping(3, TileGrate.class);
/* 287:250 */     blockMachinePanel.addTileEntityMapping(4, TileTransformer.class);
/* 288:251 */     blockMachinePanel.setItemName(0, "rpsolar");
/* 289:252 */     blockMachinePanel.setItemName(1, "rppump");
/* 290:253 */     blockMachinePanel.setItemName(2, "rpaccel");
/* 291:254 */     blockMachinePanel.setItemName(3, "rpgrate");
/* 292:255 */     blockMachinePanel.setItemName(4, "rptransformer");
/* 293:    */     
/* 294:    */ 
/* 295:258 */     GameRegistry.registerTileEntity(TileBlueFurnace.class, "RPBFurnace");
/* 296:259 */     GameRegistry.registerTileEntity(TileBufferChest.class, "RPBuffer");
/* 297:260 */     GameRegistry.registerTileEntity(TileBlueAlloyFurnace.class, "RPBAFurnace");
/* 298:261 */     GameRegistry.registerTileEntity(TileChargingBench.class, "RPCharge");
/* 299:262 */     RedPowerBase.blockAppliance.setItemName(1, "rpbfurnace");
/* 300:263 */     RedPowerBase.blockAppliance.addTileEntityMapping(1, TileBlueFurnace.class);
/* 301:    */     
/* 302:265 */     RedPowerBase.blockAppliance.setItemName(2, "rpbuffer");
/* 303:266 */     RedPowerBase.blockAppliance.addTileEntityMapping(2, TileBufferChest.class);
/* 304:    */     
/* 305:268 */     RedPowerBase.blockAppliance.setItemName(4, "rpbafurnace");
/* 306:269 */     RedPowerBase.blockAppliance.addTileEntityMapping(4, TileBlueAlloyFurnace.class);
/* 307:    */     
/* 308:271 */     RedPowerBase.blockAppliance.setItemName(5, "rpcharge");
/* 309:272 */     RedPowerBase.blockAppliance.addTileEntityMapping(5, TileChargingBench.class);
/* 310:    */     
/* 311:    */ 
/* 312:275 */     blockFrame = new BlockFrame(Config.getBlockID("blocks.machine.frame.id"));
/* 313:    */     
/* 314:277 */     GameRegistry.registerBlock(blockFrame, ItemExtended.class, "frame");
/* 315:    */     
/* 316:279 */     blockFrame.b("rpframe");
/* 317:    */     
/* 318:281 */     blockFrame.setItemName(0, "rpframe");
/* 319:282 */     blockFrame.setItemName(2, "rptframe");
/* 320:283 */     blockFrame.setItemName(3, "rprtframe");
/* 321:    */     
/* 322:285 */     GameRegistry.registerTileEntity(TileFrame.class, "RPFrame");
/* 323:286 */     GameRegistry.registerTileEntity(TileFrameMoving.class, "RPMFrame");
/* 324:287 */     GameRegistry.registerTileEntity(TileFrameTube.class, "RPTFrame");
/* 325:288 */     GameRegistry.registerTileEntity(TileFrameRedstoneTube.class, "RPRTFrame");
/* 326:    */     
/* 327:290 */     blockFrame.addTileEntityMapping(0, TileFrame.class);
/* 328:291 */     blockFrame.addTileEntityMapping(1, TileFrameMoving.class);
/* 329:292 */     blockFrame.addTileEntityMapping(2, TileFrameTube.class);
/* 330:293 */     blockFrame.addTileEntityMapping(3, TileFrameRedstoneTube.class);
/* 331:    */     
/* 332:    */ 
/* 333:296 */     IMicroPlacement imp = new MicroPlacementTube();
/* 334:297 */     RedPowerBase.blockMicro.registerPlacement(7, imp);
/* 335:298 */     RedPowerBase.blockMicro.registerPlacement(8, imp);
/* 336:299 */     RedPowerBase.blockMicro.registerPlacement(9, imp);
/* 337:300 */     RedPowerBase.blockMicro.registerPlacement(10, imp);
/* 338:301 */     RedPowerBase.blockMicro.registerPlacement(11, imp);
/* 339:    */     
/* 340:    */ 
/* 341:304 */     RedPowerBase.blockMicro.addTileEntityMapping(7, TilePipe.class);
/* 342:305 */     RedPowerBase.blockMicro.addTileEntityMapping(8, TileTube.class);
/* 343:306 */     RedPowerBase.blockMicro.addTileEntityMapping(9, TileRedstoneTube.class);
/* 344:307 */     RedPowerBase.blockMicro.addTileEntityMapping(10, TileRestrictTube.class);
/* 345:308 */     RedPowerBase.blockMicro.addTileEntityMapping(11, TileMagTube.class);
/* 346:    */     
/* 347:    */ 
/* 348:311 */     GameRegistry.addRecipe(new ur(blockMachine, 1, 0), new Object[] { "SCS", "SPS", "SRS", Character.valueOf('S'), amq.z, Character.valueOf('C'), amq.ax, Character.valueOf('R'), up.aC, Character.valueOf('P'), amq.ac });
/* 349:    */     
/* 350:    */ 
/* 351:    */ 
/* 352:    */ 
/* 353:    */ 
/* 354:317 */     GameRegistry.addRecipe(new ur(blockMachine, 1, 1), new Object[] { "SAS", "SPS", "SRS", Character.valueOf('S'), amq.z, Character.valueOf('A'), up.g, Character.valueOf('R'), up.aC, Character.valueOf('P'), amq.ac });
/* 355:    */     
/* 356:    */ 
/* 357:    */ 
/* 358:    */ 
/* 359:    */ 
/* 360:323 */     CraftLib.addOreRecipe(new ur(blockMachine, 1, 2), new Object[] { "SSS", "WPW", "SRS", Character.valueOf('S'), amq.z, Character.valueOf('R'), up.aC, Character.valueOf('P'), amq.ac, Character.valueOf('W'), "plankWood" });
/* 361:    */     
/* 362:    */ 
/* 363:    */ 
/* 364:    */ 
/* 365:    */ 
/* 366:329 */     GameRegistry.addRecipe(new ur(blockMachine, 1, 3), new Object[] { "SSS", "GPG", "SRS", Character.valueOf('S'), amq.z, Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('P'), amq.ac, Character.valueOf('G'), up.p });
/* 367:    */     
/* 368:    */ 
/* 369:    */ 
/* 370:    */ 
/* 371:    */ 
/* 372:335 */     CraftLib.addOreRecipe(new ur(blockMachine, 1, 4), new Object[] { "BTB", "RPR", "WTW", Character.valueOf('B'), "ingotBrass", Character.valueOf('T'), new ur(RedPowerBase.blockMicro, 1, 2048), Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('W'), "plankWood", Character.valueOf('P'), amq.aP });
/* 373:    */     
/* 374:    */ 
/* 375:    */ 
/* 376:    */ 
/* 377:    */ 
/* 378:    */ 
/* 379:    */ 
/* 380:343 */     GameRegistry.addRecipe(new ur(blockMachine, 1, 5), new Object[] { "III", "RFR", "IBI", Character.valueOf('B'), RedPowerBase.itemIngotBlue, Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('F'), new ur(blockMachine, 1, 3), Character.valueOf('I'), up.o });
/* 381:    */     
/* 382:    */ 
/* 383:    */ 
/* 384:    */ 
/* 385:    */ 
/* 386:    */ 
/* 387:350 */     GameRegistry.addRecipe(new ur(blockMachine, 1, 8), new Object[] { "BLB", "EFE", "INI", Character.valueOf('N'), RedPowerBase.itemIngotBlue, Character.valueOf('B'), RedPowerBase.itemIngotBrass, Character.valueOf('E'), up.bn, Character.valueOf('L'), up.aF, Character.valueOf('F'), new ur(blockMachine, 1, 3), Character.valueOf('I'), up.o });
/* 388:    */     
/* 389:    */ 
/* 390:    */ 
/* 391:    */ 
/* 392:    */ 
/* 393:    */ 
/* 394:    */ 
/* 395:    */ 
/* 396:    */ 
/* 397:360 */     GameRegistry.addRecipe(new ur(blockMachine, 1, 9), new Object[] { "IBI", "IMI", "IUI", Character.valueOf('I'), up.o, Character.valueOf('B'), RedPowerBase.itemIngotBrass, Character.valueOf('M'), RedPowerBase.itemMotor, Character.valueOf('U'), RedPowerBase.itemIngotBlue });
/* 398:    */     
/* 399:    */ 
/* 400:    */ 
/* 401:    */ 
/* 402:    */ 
/* 403:    */ 
/* 404:367 */     CraftLib.addOreRecipe(new ur(RedPowerBase.blockAppliance, 1, 2), new Object[] { "BWB", "W W", "BWB", Character.valueOf('B'), amq.bs, Character.valueOf('W'), "plankWood" });
/* 405:    */     
/* 406:    */ 
/* 407:    */ 
/* 408:    */ 
/* 409:    */ 
/* 410:373 */     CraftLib.addOreRecipe(new ur(blockMachine, 1, 10), new Object[] { "BCB", "RDR", "WCW", Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('B'), "ingotBrass", Character.valueOf('D'), new ur(blockMachine, 1, 4), Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ur(RedPowerBase.blockAppliance, 1, 2) });
/* 411:    */     
/* 412:    */ 
/* 413:    */ 
/* 414:    */ 
/* 415:    */ 
/* 416:    */ 
/* 417:    */ 
/* 418:    */ 
/* 419:    */ 
/* 420:383 */     CraftLib.addOreRecipe(new ur(blockMachine, 1, 11), new Object[] { "CIC", "WBW", "CIC", Character.valueOf('I'), up.o, Character.valueOf('B'), RedPowerBase.itemIngotBlue, Character.valueOf('W'), RedPowerBase.itemWaferBlue, Character.valueOf('C'), "ingotCopper" });
/* 421:    */     
/* 422:    */ 
/* 423:    */ 
/* 424:    */ 
/* 425:    */ 
/* 426:    */ 
/* 427:390 */     CraftLib.addOreRecipe(new ur(RedPowerBase.blockMicro, 8, 2048), new Object[] { "BGB", Character.valueOf('G'), amq.P, Character.valueOf('B'), "ingotBrass" });
/* 428:    */     
/* 429:    */ 
/* 430:    */ 
/* 431:    */ 
/* 432:395 */     GameRegistry.addShapelessRecipe(new ur(RedPowerBase.blockMicro, 1, 2304), new Object[] { up.aC, new ur(RedPowerBase.blockMicro, 1, 2048) });
/* 433:    */     
/* 434:    */ 
/* 435:    */ 
/* 436:    */ 
/* 437:400 */     GameRegistry.addShapelessRecipe(new ur(RedPowerBase.blockMicro, 1, 2560), new Object[] { up.o, new ur(RedPowerBase.blockMicro, 1, 2048) });
/* 438:    */     
/* 439:    */ 
/* 440:    */ 
/* 441:    */ 
/* 442:405 */     GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 8, 2816), new Object[] { "CCC", "OGO", "CCC", Character.valueOf('G'), amq.P, Character.valueOf('O'), amq.as, Character.valueOf('C'), RedPowerBase.itemFineCopper });
/* 443:    */     
/* 444:    */ 
/* 445:    */ 
/* 446:    */ 
/* 447:    */ 
/* 448:    */ 
/* 449:    */ 
/* 450:413 */     GameRegistry.addRecipe(new ur(RedPowerBase.blockAppliance, 1, 1), new Object[] { "CCC", "C C", "IBI", Character.valueOf('C'), amq.aZ, Character.valueOf('B'), RedPowerBase.itemIngotBlue, Character.valueOf('I'), up.o });
/* 451:    */     
/* 452:    */ 
/* 453:    */ 
/* 454:    */ 
/* 455:    */ 
/* 456:419 */     GameRegistry.addRecipe(new ur(RedPowerBase.blockAppliance, 1, 4), new Object[] { "CCC", "C C", "IBI", Character.valueOf('C'), amq.ao, Character.valueOf('B'), RedPowerBase.itemIngotBlue, Character.valueOf('I'), up.o });
/* 457:    */     
/* 458:    */ 
/* 459:    */ 
/* 460:    */ 
/* 461:    */ 
/* 462:425 */     GameRegistry.addRecipe(new ur(blockMachinePanel, 1, 0), new Object[] { "WWW", "WBW", "WWW", Character.valueOf('W'), RedPowerBase.itemWaferBlue, Character.valueOf('B'), RedPowerBase.itemIngotBlue });
/* 463:    */     
/* 464:    */ 
/* 465:    */ 
/* 466:    */ 
/* 467:430 */     GameRegistry.addRecipe(new ur(blockMachinePanel, 1, 2), new Object[] { "BOB", "O O", "BOB", Character.valueOf('O'), amq.as, Character.valueOf('B'), RedPowerBase.itemIngotBlue });
/* 468:    */     
/* 469:    */ 
/* 470:    */ 
/* 471:    */ 
/* 472:435 */     CraftLib.addOreRecipe(new ur(blockMachine, 1, 6), new Object[] { "BWB", "BIB", "IAI", Character.valueOf('I'), up.o, Character.valueOf('W'), "plankWood", Character.valueOf('A'), RedPowerBase.itemIngotBlue, Character.valueOf('B'), itemBatteryEmpty });
/* 473:    */     
/* 474:    */ 
/* 475:    */ 
/* 476:    */ 
/* 477:    */ 
/* 478:    */ 
/* 479:    */ 
/* 480:443 */     GameRegistry.addRecipe(new ur(blockMachinePanel, 1, 4), new Object[] { "III", "CIC", "BIB", Character.valueOf('I'), up.o, Character.valueOf('C'), RedPowerBase.itemCopperCoil, Character.valueOf('B'), RedPowerBase.itemIngotBlue });
/* 481:    */     
/* 482:    */ 
/* 483:    */ 
/* 484:    */ 
/* 485:    */ 
/* 486:    */ 
/* 487:450 */     GameRegistry.addRecipe(new ur(blockMachine2, 1, 0), new Object[] { "IDI", "RSR", "IWI", Character.valueOf('D'), up.n, Character.valueOf('I'), up.o, Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('W'), new ur(RedPowerBase.blockMicro, 1, 3072), Character.valueOf('S'), new ur(blockMachine, 1, 5) });
/* 488:    */     
/* 489:    */ 
/* 490:    */ 
/* 491:    */ 
/* 492:    */ 
/* 493:    */ 
/* 494:    */ 
/* 495:    */ 
/* 496:    */ 
/* 497:460 */     CraftLib.addOreRecipe(new ur(blockMachine2, 1, 1), new Object[] { "IMI", "RSR", "WBW", Character.valueOf('I'), up.o, Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('S'), new ur(blockMachine, 1, 5), Character.valueOf('M'), new ur(blockMachine, 1, 10), Character.valueOf('W'), "plankWood", Character.valueOf('B'), RedPowerBase.itemIngotBlue });
/* 498:    */     
/* 499:    */ 
/* 500:    */ 
/* 501:    */ 
/* 502:    */ 
/* 503:    */ 
/* 504:    */ 
/* 505:    */ 
/* 506:    */ 
/* 507:470 */     CraftLib.addOreRecipe(new ur(RedPowerBase.blockAppliance, 1, 5), new Object[] { "OQO", "BCB", "WUW", Character.valueOf('O'), amq.as, Character.valueOf('W'), "plankWood", Character.valueOf('U'), RedPowerBase.itemIngotBlue, Character.valueOf('C'), amq.ax, Character.valueOf('Q'), RedPowerBase.itemCopperCoil, Character.valueOf('B'), itemBatteryEmpty });
/* 508:    */     
/* 509:    */ 
/* 510:    */ 
/* 511:    */ 
/* 512:    */ 
/* 513:    */ 
/* 514:    */ 
/* 515:    */ 
/* 516:    */ 
/* 517:    */ 
/* 518:481 */     GameRegistry.addRecipe(new ur(blockMachine, 1, 12), new Object[] { "NFN", "SDS", "SRS", Character.valueOf('N'), amq.be, Character.valueOf('F'), up.i, Character.valueOf('D'), new ur(blockMachine, 1, 0), Character.valueOf('S'), amq.z, Character.valueOf('R'), up.aC });
/* 519:    */     
/* 520:    */ 
/* 521:    */ 
/* 522:    */ 
/* 523:    */ 
/* 524:    */ 
/* 525:    */ 
/* 526:    */ 
/* 527:490 */     GameRegistry.addRecipe(new ur(blockMachine, 1, 13), new Object[] { "BIB", "CDC", "IRI", Character.valueOf('I'), up.o, Character.valueOf('D'), new ur(blockMachine, 1, 0), Character.valueOf('C'), new ur(RedPowerBase.blockMicro, 1, 768), Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('B'), RedPowerBase.itemIngotBrass });
/* 528:    */     
/* 529:    */ 
/* 530:    */ 
/* 531:    */ 
/* 532:    */ 
/* 533:    */ 
/* 534:    */ 
/* 535:    */ 
/* 536:    */ 
/* 537:500 */     CraftLib.addOreRecipe(new ur(blockMachine, 1, 14), new Object[] { "WBW", "WTW", "SRS", Character.valueOf('R'), up.aC, Character.valueOf('T'), new ur(blockMachine, 1, 2), Character.valueOf('W'), "plankWood", Character.valueOf('B'), new ur(RedPowerBase.blockAppliance, 1, 2), Character.valueOf('S'), amq.z });
/* 538:    */     
/* 539:    */ 
/* 540:    */ 
/* 541:    */ 
/* 542:    */ 
/* 543:    */ 
/* 544:    */ 
/* 545:508 */     CraftLib.addOreRecipe(new ur(blockMachine, 1, 15), new Object[] { "WBW", "WTW", "SRS", Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('T'), new ur(blockMachine, 1, 2), Character.valueOf('W'), "plankWood", Character.valueOf('B'), new ur(RedPowerBase.blockAppliance, 1, 2), Character.valueOf('S'), amq.z });
/* 546:    */     
/* 547:    */ 
/* 548:    */ 
/* 549:    */ 
/* 550:    */ 
/* 551:    */ 
/* 552:    */ 
/* 553:    */ 
/* 554:    */ 
/* 555:518 */     GameRegistry.addRecipe(RedPowerBase.itemCopperCoil, new Object[] { "FBF", "BIB", "FBF", Character.valueOf('F'), RedPowerBase.itemFineCopper, Character.valueOf('B'), amq.bs, Character.valueOf('I'), up.o });
/* 556:    */     
/* 557:    */ 
/* 558:    */ 
/* 559:    */ 
/* 560:523 */     GameRegistry.addRecipe(RedPowerBase.itemMotor, new Object[] { "ICI", "ICI", "IBI", Character.valueOf('C'), RedPowerBase.itemCopperCoil, Character.valueOf('B'), RedPowerBase.itemIngotBlue, Character.valueOf('I'), up.o });
/* 561:    */     
/* 562:    */ 
/* 563:    */ 
/* 564:    */ 
/* 565:    */ 
/* 566:    */ 
/* 567:530 */     CraftLib.addOreRecipe(new ur(blockFrame, 1), new Object[] { "SSS", "SBS", "SSS", Character.valueOf('S'), up.D, Character.valueOf('B'), "ingotBrass" });
/* 568:    */     
/* 569:    */ 
/* 570:    */ 
/* 571:534 */     GameRegistry.addShapelessRecipe(new ur(blockFrame, 1, 2), new Object[] { new ur(blockFrame, 1), new ur(RedPowerBase.blockMicro, 1, 2048) });
/* 572:    */     
/* 573:    */ 
/* 574:537 */     GameRegistry.addShapelessRecipe(new ur(blockFrame, 1, 3), new Object[] { new ur(blockFrame, 1), new ur(RedPowerBase.blockMicro, 1, 2304) });
/* 575:    */     
/* 576:    */ 
/* 577:540 */     GameRegistry.addShapelessRecipe(new ur(blockFrame, 1, 3), new Object[] { new ur(blockFrame, 1, 2), up.aC });
/* 578:    */     
/* 579:    */ 
/* 580:    */ 
/* 581:544 */     CraftLib.addOreRecipe(new ur(blockMachine, 1, 7), new Object[] { "III", "BMB", "IAI", Character.valueOf('I'), up.o, Character.valueOf('A'), RedPowerBase.itemIngotBlue, Character.valueOf('B'), "ingotBrass", Character.valueOf('M'), RedPowerBase.itemMotor });
/* 582:    */     
/* 583:    */ 
/* 584:    */ 
/* 585:    */ 
/* 586:    */ 
/* 587:    */ 
/* 588:    */ 
/* 589:552 */     CraftLib.addOreRecipe(new ur(RedPowerBase.blockMicro, 16, 1792), new Object[] { "B B", "BGB", "B B", Character.valueOf('G'), amq.P, Character.valueOf('B'), "ingotBrass" });
/* 590:    */     
/* 591:    */ 
/* 592:    */ 
/* 593:    */ 
/* 594:557 */     GameRegistry.addRecipe(new ur(blockMachinePanel, 1, 3), new Object[] { "III", "I I", "IPI", Character.valueOf('P'), new ur(RedPowerBase.blockMicro, 1, 1792), Character.valueOf('I'), amq.bs });
/* 595:    */     
/* 596:    */ 
/* 597:    */ 
/* 598:    */ 
/* 599:562 */     GameRegistry.addRecipe(new ur(blockMachinePanel, 1, 1), new Object[] { "III", "PMP", "IAI", Character.valueOf('I'), up.o, Character.valueOf('A'), RedPowerBase.itemIngotBlue, Character.valueOf('P'), new ur(RedPowerBase.blockMicro, 1, 1792), Character.valueOf('M'), RedPowerBase.itemMotor });
/* 600:    */   }
/* 601:    */   
/* 602:    */   public static void initAchievements()
/* 603:    */   {
/* 604:572 */     AchieveLib.registerAchievement(117283, "rpTranspose", -2, 2, new ur(blockMachine, 1, 2), jh.k);
/* 605:    */     
/* 606:    */ 
/* 607:575 */     AchieveLib.registerAchievement(117284, "rpBreaker", -2, 4, new ur(blockMachine, 1, 1), jh.k);
/* 608:    */     
/* 609:    */ 
/* 610:578 */     AchieveLib.registerAchievement(117285, "rpDeploy", -2, 6, new ur(blockMachine, 1, 0), jh.k);
/* 611:    */     
/* 612:    */ 
/* 613:    */ 
/* 614:582 */     AchieveLib.addCraftingAchievement(new ur(blockMachine, 1, 2), "rpTranspose");
/* 615:    */     
/* 616:584 */     AchieveLib.addCraftingAchievement(new ur(blockMachine, 1, 1), "rpBreaker");
/* 617:    */     
/* 618:586 */     AchieveLib.addCraftingAchievement(new ur(blockMachine, 1, 0), "rpDeploy");
/* 619:    */     
/* 620:    */ 
/* 621:589 */     AchieveLib.registerAchievement(117286, "rpFrames", 4, 4, new ur(blockMachine, 1, 7), "rpIngotBlue");
/* 622:    */     
/* 623:591 */     AchieveLib.registerAchievement(117287, "rpPump", 4, 5, new ur(blockMachinePanel, 1, 1), "rpIngotBlue");
/* 624:    */     
/* 625:    */ 
/* 626:594 */     AchieveLib.addCraftingAchievement(new ur(blockMachine, 1, 7), "rpFrames");
/* 627:    */     
/* 628:596 */     AchieveLib.addCraftingAchievement(new ur(blockMachinePanel, 1, 1), "rpPump");
/* 629:    */   }
/* 630:    */   
/* 631:    */   public static int blockDamageDropped(amq bl, int md)
/* 632:    */   {
/* 633:601 */     return bl.b(md);
/* 634:    */   }
/* 635:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerMachine
 * JD-Core Version:    0.7.0.1
 */