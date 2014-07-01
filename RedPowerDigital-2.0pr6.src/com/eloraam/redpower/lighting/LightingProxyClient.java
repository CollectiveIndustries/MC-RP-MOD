/*  1:   */ package com.eloraam.redpower.lighting;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerLighting;
/*  4:   */ import com.eloraam.redpower.core.RenderLib;
/*  5:   */ import net.minecraftforge.client.IRenderContextHandler;
/*  6:   */ import net.minecraftforge.client.MinecraftForgeClient;
/*  7:   */ import org.lwjgl.opengl.GL11;
/*  8:   */ 
/*  9:   */ public class LightingProxyClient
/* 10:   */   extends LightingProxy
/* 11:   */ {
/* 12:   */   public void registerRenderers()
/* 13:   */   {
/* 14:25 */     MinecraftForgeClient.registerRenderContextHandler("/eloraam/lighting/lighting1.png", 1, new IRenderContextHandler()
/* 15:   */     {
/* 16:   */       public void beforeRenderContext()
/* 17:   */       {
/* 18:30 */         GL11.glBlendFunc(770, 1);
/* 19:   */         
/* 20:32 */         GL11.glDisable(3553);
/* 21:33 */         GL11.glDepthMask(false);
/* 22:   */       }
/* 23:   */       
/* 24:   */       public void afterRenderContext()
/* 25:   */       {
/* 26:39 */         GL11.glDepthMask(true);
/* 27:40 */         GL11.glEnable(3553);
/* 28:41 */         GL11.glBlendFunc(770, 771);
/* 29:   */       }
/* 30:45 */     });
/* 31:46 */     RenderLib.setRenderer(RedPowerLighting.blockLampOff, RenderLamp.class);
/* 32:   */     
/* 33:48 */     RenderLib.setRenderer(RedPowerLighting.blockLampOn, RenderLamp.class);
/* 34:   */     
/* 35:50 */     RenderLib.setRenderer(RedPowerLighting.blockInvLampOff, RenderLamp.class);
/* 36:   */     
/* 37:52 */     RenderLib.setRenderer(RedPowerLighting.blockInvLampOn, RenderLamp.class);
/* 38:   */     
/* 39:   */ 
/* 40:55 */     RenderLib.setDefaultRenderer(RedPowerLighting.blockShapedLamp, 10, RenderShapedLamp.class);
/* 41:   */     
/* 42:   */ 
/* 43:58 */     MinecraftForgeClient.preloadTexture("/eloraam/lighting/lighting1.png");
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.lighting.LightingProxyClient
 * JD-Core Version:    0.7.0.1
 */