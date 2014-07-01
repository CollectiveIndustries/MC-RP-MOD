/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import any;
/*   5:    */ import ayh;
/*   6:    */ import ays;
/*   7:    */ import bbb;
/*   8:    */ import com.eloraam.redpower.RedPowerCore;
/*   9:    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*  10:    */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*  11:    */ import ef;
/*  12:    */ import eg;
/*  13:    */ import java.io.PrintStream;
/*  14:    */ import net.minecraft.client.Minecraft;
/*  15:    */ import net.minecraftforge.common.MinecraftForge;
/*  16:    */ import net.minecraftforge.event.EventBus;
/*  17:    */ import qx;
/*  18:    */ import rq;
/*  19:    */ import up;
/*  20:    */ import yc;
/*  21:    */ import ym;
/*  22:    */ 
/*  23:    */ public class CoreProxyClient
/*  24:    */   extends CoreProxy
/*  25:    */ {
/*  26:    */   public static class RenderHandler
/*  27:    */     implements ISimpleBlockRenderingHandler
/*  28:    */   {
/*  29:    */     public void renderInventoryBlock(amq block, int metadata, int modelID, bbb renderer)
/*  30:    */     {
/*  31: 32 */       if (modelID != RedPowerCore.customBlockModel) {
/*  32: 33 */         return;
/*  33:    */       }
/*  34: 35 */       RenderCustomBlock rcb = RenderLib.getInvRenderer(block.cm, metadata);
/*  35: 37 */       if (rcb == null)
/*  36:    */       {
/*  37: 38 */         System.out.printf("Bad Render at %d:%d\n", new Object[] { Integer.valueOf(block.cm), Integer.valueOf(metadata) });
/*  38:    */         
/*  39: 40 */         return;
/*  40:    */       }
/*  41: 42 */       rcb.renderInvBlock(renderer, metadata);
/*  42:    */     }
/*  43:    */     
/*  44:    */     public boolean renderWorldBlock(ym world, int x, int y, int z, amq block, int modelId, bbb renderer)
/*  45:    */     {
/*  46: 49 */       if (renderer.d >= 0) {
/*  47: 50 */         return true;
/*  48:    */       }
/*  49: 51 */       if (modelId != RedPowerCore.customBlockModel) {
/*  50: 52 */         return false;
/*  51:    */       }
/*  52: 54 */       int md = world.h(x, y, z);
/*  53: 55 */       RenderCustomBlock rcb = RenderLib.getRenderer(block.cm, md);
/*  54: 57 */       if (rcb == null)
/*  55:    */       {
/*  56: 58 */         System.out.printf("Bad Render at %d:%d\n", new Object[] { Integer.valueOf(block.cm), Integer.valueOf(md) });
/*  57:    */         
/*  58: 60 */         return true;
/*  59:    */       }
/*  60: 62 */       rcb.renderWorldBlock(renderer, world, x, y, z, md);
/*  61: 63 */       return true;
/*  62:    */     }
/*  63:    */     
/*  64:    */     public boolean shouldRender3DInInventory()
/*  65:    */     {
/*  66: 67 */       return true;
/*  67:    */     }
/*  68:    */     
/*  69:    */     public int getRenderId()
/*  70:    */     {
/*  71: 71 */       return RedPowerCore.customBlockModel;
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public int getItemIcon(up it, int dmg)
/*  76:    */   {
/*  77: 77 */     return it.b(dmg);
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void setupRenderers()
/*  81:    */   {
/*  82: 82 */     RedPowerCore.customBlockModel = RenderingRegistry.getNextAvailableRenderId();
/*  83: 83 */     RedPowerCore.nullBlockModel = RenderingRegistry.getNextAvailableRenderId();
/*  84: 84 */     RenderingRegistry.registerBlockHandler(RedPowerCore.customBlockModel, new RenderHandler());
/*  85:    */     
/*  86:    */ 
/*  87:    */ 
/*  88: 88 */     MinecraftForge.EVENT_BUS.register(new RenderHighlight());
/*  89:    */   }
/*  90:    */   
/*  91:    */   protected void pxySendPacketToServer(ef pkt)
/*  92:    */   {
/*  93: 93 */     ayh nch = Minecraft.x().g.a;
/*  94: 94 */     nch.c(pkt);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void processPacket211(Packet211TileDesc pkt, eg nh)
/*  98:    */   {
/*  99: 99 */     if ((nh instanceof ayh))
/* 100:    */     {
/* 101:100 */       ayh nch = (ayh)nh;
/* 102:101 */       yc world = nch.getPlayer().p;
/* 103:103 */       if (world.f(pkt.xCoord, pkt.yCoord, pkt.zCoord))
/* 104:    */       {
/* 105:104 */         any tile = world.q(pkt.xCoord, pkt.yCoord, pkt.zCoord);
/* 106:106 */         if ((tile instanceof IHandlePackets))
/* 107:    */         {
/* 108:107 */           ((IHandlePackets)tile).handlePacket(pkt);
/* 109:108 */           return;
/* 110:    */         }
/* 111:    */       }
/* 112:111 */       return;
/* 113:    */     }
/* 114:113 */     super.processPacket211(pkt, nh);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void processPacket212(Packet212GuiEvent pkt, eg nh)
/* 118:    */   {
/* 119:118 */     if ((nh instanceof ayh))
/* 120:    */     {
/* 121:119 */       ayh nch = (ayh)nh;
/* 122:120 */       qx pl = nch.getPlayer();
/* 123:122 */       if ((pl.bL == null) || (pl.bL.d != pkt.windowId)) {
/* 124:124 */         return;
/* 125:    */       }
/* 126:125 */       if (!(pl.bL instanceof IHandleGuiEvent)) {
/* 127:126 */         return;
/* 128:    */       }
/* 129:127 */       IHandleGuiEvent ihge = (IHandleGuiEvent)pl.bL;
/* 130:128 */       ihge.handleGuiEvent(pkt);
/* 131:129 */       return;
/* 132:    */     }
/* 133:131 */     super.processPacket212(pkt, nh);
/* 134:    */   }
/* 135:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CoreProxyClient
 * JD-Core Version:    0.7.0.1
 */