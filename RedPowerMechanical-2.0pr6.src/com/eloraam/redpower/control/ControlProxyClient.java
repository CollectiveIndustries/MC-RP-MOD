/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import com.eloraam.redpower.RedPowerControl;
/*  5:   */ import com.eloraam.redpower.core.RenderLib;
/*  6:   */ import net.minecraftforge.client.MinecraftForgeClient;
/*  7:   */ 
/*  8:   */ public class ControlProxyClient
/*  9:   */   extends ControlProxy
/* 10:   */ {
/* 11:   */   public void registerRenderers()
/* 12:   */   {
/* 13:14 */     RenderLib.setRenderer(RedPowerControl.blockBackplane, 0, RenderBackplane.class);
/* 14:   */     
/* 15:16 */     RenderLib.setRenderer(RedPowerControl.blockBackplane, 1, RenderBackplane.class);
/* 16:   */     
/* 17:   */ 
/* 18:19 */     RenderLib.setRenderer(RedPowerControl.blockPeripheral, 0, RenderDisplay.class);
/* 19:   */     
/* 20:21 */     RenderLib.setRenderer(RedPowerControl.blockPeripheral, 1, RenderCPU.class);
/* 21:   */     
/* 22:23 */     RenderLib.setRenderer(RedPowerControl.blockPeripheral, 2, RenderDiskDrive.class);
/* 23:   */     
/* 24:   */ 
/* 25:26 */     RenderLib.setRenderer(RedPowerControl.blockFlatPeripheral, 0, RenderIOExpander.class);
/* 26:   */     
/* 27:   */ 
/* 28:   */ 
/* 29:   */ 
/* 30:   */ 
/* 31:   */ 
/* 32:   */ 
/* 33:34 */     MinecraftForgeClient.preloadTexture("/eloraam/control/control1.png");
/* 34:   */     
/* 35:36 */     RenderLib.setHighRenderer(RedPowerBase.blockMicro, 12, RenderRibbon.class);
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.ControlProxyClient
 * JD-Core Version:    0.7.0.1
 */