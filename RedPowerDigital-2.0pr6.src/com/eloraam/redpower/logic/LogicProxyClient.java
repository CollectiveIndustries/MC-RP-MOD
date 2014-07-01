/*  1:   */ package com.eloraam.redpower.logic;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerLogic;
/*  4:   */ import com.eloraam.redpower.core.RenderLib;
/*  5:   */ import cpw.mods.fml.client.registry.ClientRegistry;
/*  6:   */ import net.minecraftforge.client.MinecraftForgeClient;
/*  7:   */ 
/*  8:   */ public class LogicProxyClient
/*  9:   */   extends LogicProxy
/* 10:   */ {
/* 11:   */   public void registerRenderers()
/* 12:   */   {
/* 13:17 */     RenderLib.setHighRenderer(RedPowerLogic.blockLogic, 0, RenderLogicPointer.class);
/* 14:   */     
/* 15:19 */     RenderLib.setHighRenderer(RedPowerLogic.blockLogic, 1, RenderLogicSimple.class);
/* 16:   */     
/* 17:21 */     RenderLib.setHighRenderer(RedPowerLogic.blockLogic, 2, RenderLogicArray.class);
/* 18:   */     
/* 19:23 */     RenderLib.setHighRenderer(RedPowerLogic.blockLogic, 3, RenderLogicStorage.class);
/* 20:   */     
/* 21:25 */     RenderLib.setHighRenderer(RedPowerLogic.blockLogic, 4, RenderLogicAdv.class);
/* 22:   */     
/* 23:   */ 
/* 24:28 */     ClientRegistry.bindTileEntitySpecialRenderer(TileLogicPointer.class, new TilePointerRenderer());
/* 25:   */     
/* 26:   */ 
/* 27:31 */     MinecraftForgeClient.preloadTexture("/eloraam/logic/logic1.png");
/* 28:32 */     MinecraftForgeClient.preloadTexture("/eloraam/logic/logic2.png");
/* 29:33 */     MinecraftForgeClient.preloadTexture("/eloraam/logic/array1.png");
/* 30:34 */     MinecraftForgeClient.preloadTexture("/eloraam/logic/sensor1.png");
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.LogicProxyClient
 * JD-Core Version:    0.7.0.1
 */