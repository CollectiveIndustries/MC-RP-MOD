/*   1:    */ package com.eloraam.redpower;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.compat.BlockMachineCompat;
/*   5:    */ import com.eloraam.redpower.compat.CompatProxy;
/*   6:    */ import com.eloraam.redpower.compat.ItemMachineCompat;
/*   7:    */ import com.eloraam.redpower.compat.TileBlueEngine;
/*   8:    */ import com.eloraam.redpower.core.Config;
/*   9:    */ import com.eloraam.redpower.core.CraftLib;
/*  10:    */ import com.eloraam.redpower.core.ItemParts;
/*  11:    */ import com.eloraam.redpower.core.PipeLib;
/*  12:    */ import com.eloraam.redpower.core.ReflectLib;
/*  13:    */ import cpw.mods.fml.common.Mod;
/*  14:    */ import cpw.mods.fml.common.Mod.Init;
/*  15:    */ import cpw.mods.fml.common.Mod.Instance;
/*  16:    */ import cpw.mods.fml.common.Mod.PostInit;
/*  17:    */ import cpw.mods.fml.common.Mod.PreInit;
/*  18:    */ import cpw.mods.fml.common.SidedProxy;
/*  19:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  20:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  21:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  22:    */ import cpw.mods.fml.common.network.NetworkMod;
/*  23:    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*  24:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  25:    */ import tj;
/*  26:    */ import up;
/*  27:    */ import ur;
/*  28:    */ 
/*  29:    */ @Mod(modid="RedPowerCompat", name="RedPower Compat", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b", dependencies="required-after:RedPowerBase;required-after:RedPowerMachine;after:BuildCraftBase")
/*  30:    */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/*  31:    */ public class RedPowerCompat
/*  32:    */ {
/*  33:    */   @Mod.Instance("RedPowerCompat")
/*  34:    */   public static RedPowerCompat instance;
/*  35:    */   @SidedProxy(clientSide="com.eloraam.redpower.compat.CompatProxyClient", serverSide="com.eloraam.redpower.compat.CompatProxy")
/*  36:    */   public static CompatProxy proxy;
/*  37:    */   public static BlockMachineCompat blockMachineCompat;
/*  38:    */   public static ItemParts itemCompatParts;
/*  39:    */   public static ur itemGearBrass;
/*  40:    */   public static final String textureFile = "/eloraam/compat/compat1.png";
/*  41:    */   
/*  42:    */   @Mod.PreInit
/*  43:    */   public void preInit(FMLPreInitializationEvent event) {}
/*  44:    */   
/*  45:    */   @Mod.Init
/*  46:    */   public void load(FMLInitializationEvent event)
/*  47:    */   {
/*  48: 53 */     setupBlocks();
/*  49: 54 */     initFluids();
/*  50:    */     
/*  51: 56 */     proxy.registerRenderers();
/*  52: 57 */     NetworkRegistry.instance().registerGuiHandler(instance, proxy);
/*  53:    */   }
/*  54:    */   
/*  55:    */   @Mod.PostInit
/*  56:    */   public void postInit(FMLPostInitializationEvent event) {}
/*  57:    */   
/*  58:    */   private void initFluids()
/*  59:    */   {
/*  60: 65 */     amq bl1 = (amq)ReflectLib.getStaticField("buildcraft.BuildCraftEnergy", "oilStill", amq.class);
/*  61:    */     
/*  62:    */ 
/*  63: 68 */     amq bl2 = (amq)ReflectLib.getStaticField("buildcraft.BuildCraftEnergy", "oilMoving", amq.class);
/*  64: 71 */     if ((bl1 != null) && (bl2 != null)) {
/*  65: 73 */       PipeLib.registerVanillaFluid(bl1.cm, bl2.cm);
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   private void setupBlocks()
/*  70:    */   {
/*  71: 94 */     GameRegistry.registerTileEntity(TileBlueEngine.class, "RPBTEngine");
/*  72: 95 */     blockMachineCompat = new BlockMachineCompat(Config.getBlockID("blocks.compat.machine.id"));
/*  73:    */     
/*  74: 97 */     GameRegistry.registerBlock(blockMachineCompat, ItemMachineCompat.class, "compat");
/*  75:    */     
/*  76:    */ 
/*  77:100 */     blockMachineCompat.setItemName(0, "rpbtengine");
/*  78:    */     
/*  79:102 */     blockMachineCompat.addTileEntityMapping(0, TileBlueEngine.class);
/*  80:    */     
/*  81:    */ 
/*  82:105 */     itemCompatParts = new ItemParts(Config.getItemID("items.compat.parts.id"), "/eloraam/compat/compat1.png");
/*  83:    */     
/*  84:    */ 
/*  85:108 */     itemCompatParts.addItem(0, 1, "item.rpbgear");
/*  86:109 */     itemCompatParts.a(tj.l);
/*  87:    */     
/*  88:111 */     itemGearBrass = new ur(itemCompatParts, 1, 0);
/*  89:    */     
/*  90:113 */     up stoneGear = (up)ReflectLib.getStaticField("buildcraft.BuildCraftCore", "stoneGearItem", up.class);
/*  91:117 */     if (stoneGear != null) {
/*  92:118 */       CraftLib.addOreRecipe(new ur(itemCompatParts, 1, 0), new Object[] { " B ", "BGB", " B ", Character.valueOf('B'), "ingotBrass", Character.valueOf('G'), stoneGear });
/*  93:    */     }
/*  94:125 */     if (Config.getInt("settings.compat.gear.altRecipe") > 0) {
/*  95:126 */       CraftLib.addOreRecipe(new ur(itemCompatParts, 1, 0), new Object[] { " B ", "BIB", " B ", Character.valueOf('B'), "ingotBrass", Character.valueOf('I'), new ur(RedPowerBase.blockMicro, 1, 5649) });
/*  96:    */     }
/*  97:133 */     CraftLib.addOreRecipe(new ur(blockMachineCompat, 1, 0), new Object[] { "BBB", " G ", "ZMZ", Character.valueOf('B'), "ingotBrass", Character.valueOf('G'), amq.P, Character.valueOf('Z'), itemGearBrass, Character.valueOf('M'), RedPowerBase.itemMotor });
/*  98:    */   }
/*  99:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerCompat
 * JD-Core Version:    0.7.0.1
 */