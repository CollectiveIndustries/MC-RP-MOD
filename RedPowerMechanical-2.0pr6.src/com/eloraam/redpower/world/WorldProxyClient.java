/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import net.minecraftforge.client.MinecraftForgeClient;
/*  4:   */ 
/*  5:   */ public class WorldProxyClient
/*  6:   */   extends WorldProxy
/*  7:   */ {
/*  8:   */   public void registerRenderers()
/*  9:   */   {
/* 10:13 */     MinecraftForgeClient.preloadTexture("/eloraam/world/world1.png");
/* 11:14 */     MinecraftForgeClient.preloadTexture("/eloraam/world/worlditems1.png");
/* 12:   */   }
/* 13:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.WorldProxyClient
 * JD-Core Version:    0.7.0.1
 */