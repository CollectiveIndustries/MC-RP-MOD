/*   1:    */ package com.eloraam.redpower;
/*   2:    */ 
/*   3:    */ import agi;
/*   4:    */ import amq;
/*   5:    */ import com.eloraam.redpower.base.BlockMicro;
/*   6:    */ import com.eloraam.redpower.control.BlockPeripheral;
/*   7:    */ import com.eloraam.redpower.control.ControlProxy;
/*   8:    */ import com.eloraam.redpower.control.ItemBackplane;
/*   9:    */ import com.eloraam.redpower.control.ItemDisk;
/*  10:    */ import com.eloraam.redpower.control.MicroPlacementRibbon;
/*  11:    */ import com.eloraam.redpower.control.TileBackplane;
/*  12:    */ import com.eloraam.redpower.control.TileCPU;
/*  13:    */ import com.eloraam.redpower.control.TileDiskDrive;
/*  14:    */ import com.eloraam.redpower.control.TileDisplay;
/*  15:    */ import com.eloraam.redpower.control.TileIOExpander;
/*  16:    */ import com.eloraam.redpower.control.TileRAM;
/*  17:    */ import com.eloraam.redpower.control.TileRibbon;
/*  18:    */ import com.eloraam.redpower.core.BlockExtended;
/*  19:    */ import com.eloraam.redpower.core.BlockMultipart;
/*  20:    */ import com.eloraam.redpower.core.Config;
/*  21:    */ import com.eloraam.redpower.core.CoreLib;
/*  22:    */ import com.eloraam.redpower.core.CraftLib;
/*  23:    */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  24:    */ import com.eloraam.redpower.core.IMicroPlacement;
/*  25:    */ import com.eloraam.redpower.core.ItemExtended;
/*  26:    */ import cpw.mods.fml.common.Mod;
/*  27:    */ import cpw.mods.fml.common.Mod.Init;
/*  28:    */ import cpw.mods.fml.common.Mod.Instance;
/*  29:    */ import cpw.mods.fml.common.Mod.PostInit;
/*  30:    */ import cpw.mods.fml.common.Mod.PreInit;
/*  31:    */ import cpw.mods.fml.common.SidedProxy;
/*  32:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  33:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  34:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  35:    */ import cpw.mods.fml.common.network.NetworkMod;
/*  36:    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*  37:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  38:    */ import net.minecraftforge.common.DungeonHooks;
/*  39:    */ import up;
/*  40:    */ import ur;
/*  41:    */ 
/*  42:    */ @Mod(modid="RedPowerControl", name="RedPower Control", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b", dependencies="required-after:RedPowerBase")
/*  43:    */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/*  44:    */ public class RedPowerControl
/*  45:    */ {
/*  46:    */   @Mod.Instance("RedPowerControl")
/*  47:    */   public static RedPowerControl instance;
/*  48:    */   @SidedProxy(clientSide="com.eloraam.redpower.control.ControlProxyClient", serverSide="com.eloraam.redpower.control.ControlProxy")
/*  49:    */   public static ControlProxy proxy;
/*  50:    */   public static BlockExtended blockBackplane;
/*  51:    */   public static BlockExtended blockPeripheral;
/*  52:    */   public static BlockExtended blockFlatPeripheral;
/*  53:    */   public static ItemDisk itemDisk;
/*  54:    */   
/*  55:    */   @Mod.PreInit
/*  56:    */   public void preInit(FMLPreInitializationEvent event) {}
/*  57:    */   
/*  58:    */   @Mod.Init
/*  59:    */   public void load(FMLInitializationEvent event)
/*  60:    */   {
/*  61: 50 */     setupBlocks();
/*  62: 51 */     proxy.registerRenderers();
/*  63: 52 */     NetworkRegistry.instance().registerGuiHandler(instance, proxy);
/*  64:    */   }
/*  65:    */   
/*  66:    */   @Mod.PostInit
/*  67:    */   public void postInit(FMLPostInitializationEvent event) {}
/*  68:    */   
/*  69:    */   private static void setupBlocks()
/*  70:    */   {
/*  71: 61 */     blockBackplane = new BlockMultipart(Config.getBlockID("blocks.control.backplane.id"), CoreLib.materialRedpower);
/*  72:    */     
/*  73:    */ 
/*  74: 64 */     GameRegistry.registerBlock(blockBackplane, ItemBackplane.class, "backplane");
/*  75:    */     
/*  76: 66 */     blockBackplane.c(1.0F);
/*  77:    */     
/*  78: 68 */     blockBackplane.setItemName(0, "rpbackplane");
/*  79: 69 */     blockBackplane.setItemName(1, "rpram");
/*  80:    */     
/*  81:    */ 
/*  82: 72 */     blockPeripheral = new BlockPeripheral(Config.getBlockID("blocks.control.peripheral.id"));
/*  83:    */     
/*  84: 74 */     GameRegistry.registerBlock(blockPeripheral, ItemExtended.class, "peripheral");
/*  85:    */     
/*  86: 76 */     blockPeripheral.c(1.0F);
/*  87:    */     
/*  88: 78 */     blockPeripheral.setItemName(0, "rpdisplay");
/*  89: 79 */     blockPeripheral.setItemName(1, "rpcpu");
/*  90: 80 */     blockPeripheral.setItemName(2, "rpdiskdrive");
/*  91:    */     
/*  92:    */ 
/*  93: 83 */     blockFlatPeripheral = new BlockMultipart(Config.getBlockID("blocks.control.peripheralFlat.id"), agi.e);
/*  94:    */     
/*  95:    */ 
/*  96: 86 */     blockFlatPeripheral.a(CreativeExtraTabs.tabMachine);
/*  97: 87 */     GameRegistry.registerBlock(blockFlatPeripheral, ItemExtended.class, "peripheralFlat");
/*  98:    */     
/*  99: 89 */     blockFlatPeripheral.c(1.0F);
/* 100:    */     
/* 101:    */ 
/* 102:    */ 
/* 103: 93 */     blockFlatPeripheral.setItemName(0, "rpioexp");
/* 104:    */     
/* 105:    */ 
/* 106:    */ 
/* 107:    */ 
/* 108:    */ 
/* 109: 99 */     GameRegistry.registerTileEntity(TileBackplane.class, "RPConBP");
/* 110:100 */     blockBackplane.addTileEntityMapping(0, TileBackplane.class);
/* 111:101 */     GameRegistry.registerTileEntity(TileRAM.class, "RPConRAM");
/* 112:102 */     blockBackplane.addTileEntityMapping(1, TileRAM.class);
/* 113:    */     
/* 114:104 */     GameRegistry.registerTileEntity(TileDisplay.class, "RPConDisp");
/* 115:105 */     blockPeripheral.addTileEntityMapping(0, TileDisplay.class);
/* 116:106 */     GameRegistry.registerTileEntity(TileDiskDrive.class, "RPConDDrv");
/* 117:107 */     blockPeripheral.addTileEntityMapping(2, TileDiskDrive.class);
/* 118:    */     
/* 119:109 */     GameRegistry.registerTileEntity(TileCPU.class, "RPConCPU");
/* 120:110 */     blockPeripheral.addTileEntityMapping(1, TileCPU.class);
/* 121:    */     
/* 122:112 */     GameRegistry.registerTileEntity(TileIOExpander.class, "RPConIOX");
/* 123:113 */     blockFlatPeripheral.addTileEntityMapping(0, TileIOExpander.class);
/* 124:    */     
/* 125:    */ 
/* 126:116 */     GameRegistry.registerTileEntity(TileRibbon.class, "RPConRibbon");
/* 127:117 */     RedPowerBase.blockMicro.addTileEntityMapping(12, TileRibbon.class);
/* 128:    */     
/* 129:    */ 
/* 130:120 */     IMicroPlacement imp = new MicroPlacementRibbon();
/* 131:121 */     RedPowerBase.blockMicro.registerPlacement(12, imp);
/* 132:    */     
/* 133:    */ 
/* 134:    */ 
/* 135:125 */     itemDisk = new ItemDisk(Config.getItemID("items.control.disk.id"));
/* 136:    */     
/* 137:    */ 
/* 138:128 */     CraftLib.addOreRecipe(new ur(itemDisk, 1), new Object[] { "WWW", "W W", "WIW", Character.valueOf('I'), up.o, Character.valueOf('W'), "plankWood" });
/* 139:    */     
/* 140:    */ 
/* 141:    */ 
/* 142:    */ 
/* 143:133 */     GameRegistry.addShapelessRecipe(new ur(itemDisk, 1, 1), new Object[] { new ur(itemDisk, 1, 0), up.aC });
/* 144:    */     
/* 145:135 */     GameRegistry.addShapelessRecipe(new ur(itemDisk, 1, 2), new Object[] { new ur(itemDisk, 1, 1), up.aC });
/* 146:    */     
/* 147:    */ 
/* 148:    */ 
/* 149:139 */     GameRegistry.addRecipe(new ur(blockBackplane, 1, 0), new Object[] { "ICI", "IGI", "ICI", Character.valueOf('C'), RedPowerBase.itemFineCopper, Character.valueOf('I'), amq.bs, Character.valueOf('G'), up.p });
/* 150:    */     
/* 151:    */ 
/* 152:    */ 
/* 153:    */ 
/* 154:    */ 
/* 155:145 */     GameRegistry.addRecipe(new ur(blockBackplane, 1, 1), new Object[] { "IRI", "RDR", "IRI", Character.valueOf('I'), amq.bs, Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('D'), up.n });
/* 156:    */     
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:    */ 
/* 161:151 */     CraftLib.addOreRecipe(new ur(blockPeripheral, 1, 0), new Object[] { "GWW", "GPR", "GBW", Character.valueOf('P'), new ur(RedPowerBase.itemLumar, 1, 5), Character.valueOf('G'), amq.P, Character.valueOf('W'), "plankWood", Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('B'), new ur(RedPowerBase.blockMicro, 1, 3072) });
/* 162:    */     
/* 163:    */ 
/* 164:    */ 
/* 165:    */ 
/* 166:    */ 
/* 167:    */ 
/* 168:    */ 
/* 169:    */ 
/* 170:160 */     CraftLib.addOreRecipe(new ur(blockPeripheral, 1, 1), new Object[] { "WWW", "RDR", "WBW", Character.valueOf('W'), "plankWood", Character.valueOf('D'), amq.aA, Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('B'), new ur(RedPowerBase.blockMicro, 1, 3072) });
/* 171:    */     
/* 172:    */ 
/* 173:    */ 
/* 174:    */ 
/* 175:    */ 
/* 176:    */ 
/* 177:    */ 
/* 178:168 */     CraftLib.addOreRecipe(new ur(blockPeripheral, 1, 2), new Object[] { "WWW", "WMR", "WBW", Character.valueOf('G'), amq.P, Character.valueOf('W'), "plankWood", Character.valueOf('M'), RedPowerBase.itemMotor, Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('B'), new ur(RedPowerBase.blockMicro, 1, 3072) });
/* 179:    */     
/* 180:    */ 
/* 181:    */ 
/* 182:    */ 
/* 183:    */ 
/* 184:    */ 
/* 185:    */ 
/* 186:    */ 
/* 187:177 */     CraftLib.addOreRecipe(new ur(blockFlatPeripheral, 1, 0), new Object[] { "WCW", "WRW", "WBW", Character.valueOf('W'), "plankWood", Character.valueOf('R'), RedPowerBase.itemWaferRed, Character.valueOf('C'), new ur(RedPowerBase.blockMicro, 1, 768), Character.valueOf('B'), new ur(RedPowerBase.blockMicro, 1, 3072) });
/* 188:    */     
/* 189:    */ 
/* 190:    */ 
/* 191:    */ 
/* 192:    */ 
/* 193:    */ 
/* 194:    */ 
/* 195:    */ 
/* 196:186 */     GameRegistry.addRecipe(new ur(RedPowerBase.blockMicro, 8, 3072), new Object[] { "C", "C", "C", Character.valueOf('C'), RedPowerBase.itemFineCopper });
/* 197:    */     
/* 198:    */ 
/* 199:    */ 
/* 200:    */ 
/* 201:191 */     DungeonHooks.addDungeonLoot(new ur(itemDisk, 1, 1), 1);
/* 202:192 */     DungeonHooks.addDungeonLoot(new ur(itemDisk, 1, 2), 1);
/* 203:    */   }
/* 204:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerControl
 * JD-Core Version:    0.7.0.1
 */