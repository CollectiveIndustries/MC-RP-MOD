/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.RedPowerBase;
/*   4:    */ import com.eloraam.redpower.RedPowerMachine;
/*   5:    */ import com.eloraam.redpower.core.RenderLib;
/*   6:    */ import com.eloraam.redpower.core.TextureAnimFX;
/*   7:    */ import cpw.mods.fml.client.TextureFXManager;
/*   8:    */ import cpw.mods.fml.client.registry.ClientRegistry;
/*   9:    */ import net.minecraftforge.client.IRenderContextHandler;
/*  10:    */ import net.minecraftforge.client.MinecraftForgeClient;
/*  11:    */ import org.lwjgl.opengl.GL11;
/*  12:    */ 
/*  13:    */ public class MachineProxyClient
/*  14:    */   extends MachineProxy
/*  15:    */ {
/*  16:    */   public void registerRenderers()
/*  17:    */   {
/*  18: 19 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 0, RenderMachine.class);
/*  19:    */     
/*  20: 21 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 1, RenderBreaker.class);
/*  21:    */     
/*  22: 23 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 2, RenderMachine.class);
/*  23:    */     
/*  24: 25 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 3, RenderMachine.class);
/*  25:    */     
/*  26: 27 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 4, RenderMachine.class);
/*  27:    */     
/*  28: 29 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 5, RenderMachine.class);
/*  29:    */     
/*  30: 31 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 6, RenderBatteryBox.class);
/*  31:    */     
/*  32: 33 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 7, RenderMotor.class);
/*  33:    */     
/*  34: 35 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 8, RenderMachine.class);
/*  35:    */     
/*  36: 37 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 9, RenderWindTurbine.class);
/*  37:    */     
/*  38: 39 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 10, RenderMachine.class);
/*  39:    */     
/*  40: 41 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 11, RenderThermopile.class);
/*  41:    */     
/*  42: 43 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 12, RenderMachine.class);
/*  43:    */     
/*  44: 45 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 13, RenderMachine.class);
/*  45:    */     
/*  46: 47 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 14, RenderMachine.class);
/*  47:    */     
/*  48: 49 */     RenderLib.setRenderer(RedPowerMachine.blockMachine, 15, RenderMachine.class);
/*  49:    */     
/*  50:    */ 
/*  51: 52 */     RenderLib.setRenderer(RedPowerMachine.blockMachine2, 0, RenderMachine2.class);
/*  52:    */     
/*  53: 54 */     RenderLib.setRenderer(RedPowerMachine.blockMachine2, 1, RenderMachine2.class);
/*  54:    */     
/*  55:    */ 
/*  56: 57 */     RenderLib.setRenderer(RedPowerBase.blockAppliance, 1, RenderBlueFurnace.class);
/*  57:    */     
/*  58: 59 */     RenderLib.setRenderer(RedPowerBase.blockAppliance, 2, RenderBufferChest.class);
/*  59:    */     
/*  60: 61 */     RenderLib.setRenderer(RedPowerBase.blockAppliance, 4, RenderBlueFurnace.class);
/*  61:    */     
/*  62: 63 */     RenderLib.setRenderer(RedPowerBase.blockAppliance, 5, RenderChargingBench.class);
/*  63:    */     
/*  64:    */ 
/*  65: 66 */     RenderLib.setHighRenderer(RedPowerBase.blockMicro, 7, RenderPipe.class);
/*  66:    */     
/*  67: 68 */     RenderLib.setHighRenderer(RedPowerBase.blockMicro, 8, RenderTube.class);
/*  68:    */     
/*  69: 70 */     RenderLib.setHighRenderer(RedPowerBase.blockMicro, 9, RenderRedstoneTube.class);
/*  70:    */     
/*  71: 72 */     RenderLib.setHighRenderer(RedPowerBase.blockMicro, 10, RenderTube.class);
/*  72:    */     
/*  73: 74 */     RenderLib.setHighRenderer(RedPowerBase.blockMicro, 11, RenderTube.class);
/*  74:    */     
/*  75:    */ 
/*  76: 77 */     RenderLib.setRenderer(RedPowerMachine.blockMachinePanel, 0, RenderSolarPanel.class);
/*  77:    */     
/*  78: 79 */     RenderLib.setRenderer(RedPowerMachine.blockMachinePanel, 1, RenderPump.class);
/*  79:    */     
/*  80: 81 */     RenderLib.setRenderer(RedPowerMachine.blockMachinePanel, 2, RenderAccel.class);
/*  81:    */     
/*  82: 83 */     RenderLib.setRenderer(RedPowerMachine.blockMachinePanel, 3, RenderGrate.class);
/*  83:    */     
/*  84: 85 */     RenderLib.setRenderer(RedPowerMachine.blockMachinePanel, 4, RenderTransformer.class);
/*  85:    */     
/*  86:    */ 
/*  87: 88 */     RenderLib.setRenderer(RedPowerMachine.blockFrame, 0, RenderFrame.class);
/*  88:    */     
/*  89: 90 */     RenderLib.setRenderer(RedPowerMachine.blockFrame, 1, RenderFrameMoving.class);
/*  90:    */     
/*  91: 92 */     RenderLib.setRenderer(RedPowerMachine.blockFrame, 2, RenderFrameTube.class);
/*  92:    */     
/*  93: 94 */     RenderLib.setRenderer(RedPowerMachine.blockFrame, 3, RenderFrameRedstoneTube.class);
/*  94:    */     
/*  95:    */ 
/*  96: 97 */     ClientRegistry.bindTileEntitySpecialRenderer(TileWindTurbine.class, new TileWindTurbineRenderer());
/*  97:    */     
/*  98: 99 */     ClientRegistry.bindTileEntitySpecialRenderer(TilePipe.class, new TilePipeRenderer());
/*  99:    */     
/* 100:101 */     ClientRegistry.bindTileEntitySpecialRenderer(TilePump.class, new TilePumpRenderer());
/* 101:    */     
/* 102:103 */     ClientRegistry.bindTileEntitySpecialRenderer(TileTube.class, new TileTubeRenderer());
/* 103:    */     
/* 104:105 */     ClientRegistry.bindTileEntitySpecialRenderer(TileRedstoneTube.class, new TileTubeRenderer());
/* 105:    */     
/* 106:107 */     ClientRegistry.bindTileEntitySpecialRenderer(TileRestrictTube.class, new TileTubeRenderer());
/* 107:    */     
/* 108:109 */     ClientRegistry.bindTileEntitySpecialRenderer(TileMagTube.class, new TileTubeRenderer());
/* 109:    */     
/* 110:111 */     ClientRegistry.bindTileEntitySpecialRenderer(TileAccel.class, new TileTubeRenderer());
/* 111:    */     
/* 112:113 */     ClientRegistry.bindTileEntitySpecialRenderer(TileFrameMoving.class, new TileFrameRenderer());
/* 113:    */     
/* 114:    */ 
/* 115:116 */     MinecraftForgeClient.preloadTexture("/eloraam/machine/machine1.png");
/* 116:117 */     MinecraftForgeClient.preloadTexture("/eloraam/machine/machine2.png");
/* 117:    */     
/* 118:119 */     TextureFXManager.instance().addAnimation(new TextureAnimFX(239, "/eloraam/machine/machine1.png", 2, new int[] { 146, 147, 148, 149 }));
/* 119:    */     
/* 120:    */ 
/* 121:122 */     TextureFXManager.instance().addAnimation(new TextureAnimFX(255, "/eloraam/machine/machine1.png", 2, new int[] { 151, 152, 153, 154 }));
/* 122:    */     
/* 123:    */ 
/* 124:    */ 
/* 125:126 */     MinecraftForgeClient.registerRenderContextHandler("/eloraam/machine/machine1.png", 1, new IRenderContextHandler()
/* 126:    */     {
/* 127:    */       public void beforeRenderContext()
/* 128:    */       {
/* 129:131 */         GL11.glPolygonOffset(-0.1F, -1.0F);
/* 130:132 */         GL11.glEnable(32823);
/* 131:    */       }
/* 132:    */       
/* 133:    */       public void afterRenderContext()
/* 134:    */       {
/* 135:135 */         GL11.glPolygonOffset(0.0F, 0.0F);
/* 136:136 */         GL11.glDisable(32823);
/* 137:    */       }
/* 138:139 */     });
/* 139:140 */     MinecraftForgeClient.registerItemRenderer(RedPowerMachine.blockMachine.cm, new ItemRenderMachine());
/* 140:    */   }
/* 141:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.MachineProxyClient
 * JD-Core Version:    0.7.0.1
 */