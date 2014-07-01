/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import com.eloraam.redpower.core.RenderLib;
/*  5:   */ import net.minecraftforge.client.MinecraftForgeClient;
/*  6:   */ 
/*  7:   */ public class BaseProxyClient
/*  8:   */   extends BaseProxy
/*  9:   */ {
/* 10:   */   public void registerRenderers()
/* 11:   */   {
/* 12:16 */     RenderLib.setRenderer(RedPowerBase.blockAppliance, 0, RenderAlloyFurnace.class);
/* 13:   */     
/* 14:18 */     RenderLib.setRenderer(RedPowerBase.blockAppliance, 3, RenderAdvBench.class);
/* 15:   */     
/* 16:   */ 
/* 17:21 */     MinecraftForgeClient.preloadTexture("/eloraam/base/base1.png");
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.BaseProxyClient
 * JD-Core Version:    0.7.0.1
 */