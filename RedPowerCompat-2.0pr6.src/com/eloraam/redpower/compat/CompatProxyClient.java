/*  1:   */ package com.eloraam.redpower.compat;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerCompat;
/*  4:   */ import com.eloraam.redpower.core.RenderLib;
/*  5:   */ import cpw.mods.fml.client.registry.ClientRegistry;
/*  6:   */ import net.minecraftforge.client.MinecraftForgeClient;
/*  7:   */ 
/*  8:   */ public class CompatProxyClient
/*  9:   */   extends CompatProxy
/* 10:   */ {
/* 11:   */   public void registerRenderers()
/* 12:   */   {
/* 13:14 */     RenderLib.setRenderer(RedPowerCompat.blockMachineCompat, 0, RenderBlueEngine.class);
/* 14:   */     
/* 15:   */ 
/* 16:17 */     ClientRegistry.bindTileEntitySpecialRenderer(TileBlueEngine.class, new TileBlueEngineRenderer());
/* 17:   */     
/* 18:   */ 
/* 19:20 */     MinecraftForgeClient.preloadTexture("/eloraam/compat/compat1.png");
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.compat.CompatProxyClient
 * JD-Core Version:    0.7.0.1
 */