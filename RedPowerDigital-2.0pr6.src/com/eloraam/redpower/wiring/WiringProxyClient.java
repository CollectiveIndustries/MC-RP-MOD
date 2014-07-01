/*  1:   */ package com.eloraam.redpower.wiring;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import com.eloraam.redpower.core.RenderLib;
/*  5:   */ import net.minecraftforge.client.MinecraftForgeClient;
/*  6:   */ 
/*  7:   */ public class WiringProxyClient
/*  8:   */   extends WiringProxy
/*  9:   */ {
/* 10:   */   public void registerRenderers()
/* 11:   */   {
/* 12:17 */     RenderLib.setDefaultRenderer(RedPowerBase.blockMicro, 8, RenderRedwire.class);
/* 13:   */     
/* 14:19 */     MinecraftForgeClient.preloadTexture("/eloraam/wiring/redpower1.png");
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.WiringProxyClient
 * JD-Core Version:    0.7.0.1
 */