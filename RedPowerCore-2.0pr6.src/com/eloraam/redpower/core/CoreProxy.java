/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ import cpw.mods.fml.common.SidedProxy;
/*  5:   */ import ef;
/*  6:   */ import eg;
/*  7:   */ import ik;
/*  8:   */ import in;
/*  9:   */ import iq;
/* 10:   */ import iv;
/* 11:   */ import java.util.List;
/* 12:   */ import rq;
/* 13:   */ import rw;
/* 14:   */ import up;
/* 15:   */ import yc;
/* 16:   */ 
/* 17:   */ public class CoreProxy
/* 18:   */ {
/* 19:   */   @SidedProxy(clientSide="com.eloraam.redpower.core.CoreProxyClient", serverSide="com.eloraam.redpower.core.CoreProxy")
/* 20:   */   public static CoreProxy instance;
/* 21:   */   
/* 22:   */   public static void sendPacketToServer(ef pkt)
/* 23:   */   {
/* 24:30 */     instance.pxySendPacketToServer(pkt);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int getItemIcon(up it, int dmg)
/* 28:   */   {
/* 29:34 */     return 0;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public static void sendPacketToPosition(yc world, ef pkt, int x, int z)
/* 33:   */   {
/* 34:39 */     if (world.I) {
/* 35:39 */       return;
/* 36:   */     }
/* 37:40 */     in ws = (in)world;
/* 38:41 */     ik plm = ws.r();
/* 39:   */     
/* 40:43 */     List players = world.h;
/* 41:44 */     for (int i = 0; i < players.size(); i++)
/* 42:   */     {
/* 43:45 */       iq player = (iq)players.get(i);
/* 44:48 */       if (plm.a(player, x >> 4, z >> 4)) {
/* 45:49 */         player.a.b(pkt);
/* 46:   */       }
/* 47:   */     }
/* 48:   */   }
/* 49:   */   
/* 50:   */   public static void sendPacketToCrafting(rw icr, ef pkt)
/* 51:   */   {
/* 52:54 */     if (!(icr instanceof iq)) {
/* 53:55 */       return;
/* 54:   */     }
/* 55:56 */     iq player = (iq)icr;
/* 56:57 */     player.a.b(pkt);
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void setupRenderers() {}
/* 60:   */   
/* 61:   */   protected void pxySendPacketToServer(ef pkt) {}
/* 62:   */   
/* 63:   */   public void processPacket211(Packet211TileDesc pkt, eg nh)
/* 64:   */   {
/* 65:67 */     if (!(nh instanceof iv)) {
/* 66:67 */       return;
/* 67:   */     }
/* 68:69 */     iv nsh = (iv)nh;
/* 69:70 */     iq player = nsh.getPlayer();
/* 70:   */     
/* 71:72 */     yc world = player.p;
/* 72:73 */     if (world.f(pkt.xCoord, pkt.yCoord, pkt.zCoord))
/* 73:   */     {
/* 74:74 */       any tile = world.q(pkt.xCoord, pkt.yCoord, pkt.zCoord);
/* 75:76 */       if ((tile instanceof IHandlePackets))
/* 76:   */       {
/* 77:77 */         ((IHandlePackets)tile).handlePacket(pkt);
/* 78:78 */         return;
/* 79:   */       }
/* 80:   */     }
/* 81:   */   }
/* 82:   */   
/* 83:   */   public void processPacket212(Packet212GuiEvent pkt, eg nh)
/* 84:   */   {
/* 85:84 */     if (!(nh instanceof iv)) {
/* 86:84 */       return;
/* 87:   */     }
/* 88:86 */     iv nsh = (iv)nh;
/* 89:87 */     iq pl = nsh.getPlayer();
/* 90:89 */     if ((pl.bL == null) || (pl.bL.d != pkt.windowId)) {
/* 91:91 */       return;
/* 92:   */     }
/* 93:92 */     if (!(pl.bL instanceof IHandleGuiEvent)) {
/* 94:93 */       return;
/* 95:   */     }
/* 96:94 */     IHandleGuiEvent ihge = (IHandleGuiEvent)pl.bL;
/* 97:95 */     ihge.handleGuiEvent(pkt);
/* 98:   */   }
/* 99:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CoreProxy
 * JD-Core Version:    0.7.0.1
 */