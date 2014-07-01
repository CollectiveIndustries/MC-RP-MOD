/*  1:   */ package com.eloraam.redpower;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.Config;
/*  4:   */ import com.eloraam.redpower.core.CoreEvents;
/*  5:   */ import com.eloraam.redpower.core.CoreLib;
/*  6:   */ import com.eloraam.redpower.core.CoreProxy;
/*  7:   */ import com.eloraam.redpower.core.CoverRecipe;
/*  8:   */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  9:   */ import com.eloraam.redpower.core.Packet212GuiEvent;
/* 10:   */ import cpw.mods.fml.common.Mod;
/* 11:   */ import cpw.mods.fml.common.Mod.Init;
/* 12:   */ import cpw.mods.fml.common.Mod.Instance;
/* 13:   */ import cpw.mods.fml.common.Mod.PostInit;
/* 14:   */ import cpw.mods.fml.common.Mod.PreInit;
/* 15:   */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/* 16:   */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/* 17:   */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/* 18:   */ import cpw.mods.fml.common.network.NetworkMod;
/* 19:   */ import ef;
/* 20:   */ import java.io.File;
/* 21:   */ import java.util.List;
/* 22:   */ import net.minecraftforge.common.DimensionManager;
/* 23:   */ import net.minecraftforge.common.MinecraftForge;
/* 24:   */ import net.minecraftforge.event.EventBus;
/* 25:   */ import wn;
/* 26:   */ import yc;
/* 27:   */ 
/* 28:   */ @Mod(modid="RedPowerCore", name="RedPower Core", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b")
/* 29:   */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/* 30:   */ public class RedPowerCore
/* 31:   */ {
/* 32:   */   @Mod.Instance("RedPowerCore")
/* 33:   */   public static RedPowerCore instance;
/* 34:   */   
/* 35:   */   @Mod.PreInit
/* 36:   */   public void preInit(FMLPreInitializationEvent event)
/* 37:   */   {
/* 38:37 */     Config.loadConfig();
/* 39:   */     
/* 40:39 */     CoreLib.readOres();
/* 41:40 */     MinecraftForge.EVENT_BUS.register(new CoreEvents());
/* 42:   */   }
/* 43:   */   
/* 44:   */   @Mod.Init
/* 45:   */   public void load(FMLInitializationEvent event)
/* 46:   */   {
/* 47:46 */     ef.a(211, true, true, Packet211TileDesc.class);
/* 48:47 */     ef.a(212, true, true, Packet212GuiEvent.class);
/* 49:   */     
/* 50:49 */     CoreProxy.instance.setupRenderers();
/* 51:50 */     wn.a().b().add(new CoverRecipe());
/* 52:   */   }
/* 53:   */   
/* 54:   */   @Mod.PostInit
/* 55:   */   public void postInit(FMLPostInitializationEvent event) {}
/* 56:   */   
/* 57:   */   public static File getSaveDir(yc world)
/* 58:   */   {
/* 59:67 */     return DimensionManager.getCurrentSaveRootDirectory();
/* 60:   */   }
/* 61:   */   
/* 62:71 */   public static int customBlockModel = -1;
/* 63:72 */   public static int nullBlockModel = -1;
/* 64:   */   public static final int idLogic = 1;
/* 65:   */   public static final int idTimer = 2;
/* 66:   */   public static final int idSequencer = 3;
/* 67:   */   public static final int idCounter = 4;
/* 68:   */   public static final int idWiring = 5;
/* 69:   */   public static final int idArray = 6;
/* 70:   */   public static final int idMachine = 7;
/* 71:   */   public static final int idMachinePanel = 8;
/* 72:   */   public static final int idFrame = 9;
/* 73:   */   public static final int idItemUpdate = 10;
/* 74:   */   public static final int idPipeUpdate = 11;
/* 75:   */   public static final int idLighting = 11;
/* 76:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerCore
 * JD-Core Version:    0.7.0.1
 */