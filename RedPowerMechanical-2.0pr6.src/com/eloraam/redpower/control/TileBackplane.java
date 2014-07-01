/*   1:    */ package com.eloraam.redpower.control;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerControl;
/*   5:    */ import com.eloraam.redpower.core.BlockExtended;
/*   6:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.IFrameSupport;
/*   9:    */ import com.eloraam.redpower.core.IHandlePackets;
/*  10:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*  11:    */ import com.eloraam.redpower.core.TileMultipart;
/*  12:    */ import com.eloraam.redpower.core.WorldCoord;
/*  13:    */ import ef;
/*  14:    */ import java.io.ByteArrayOutputStream;
/*  15:    */ import java.io.IOException;
/*  16:    */ import java.util.ArrayList;
/*  17:    */ import net.minecraftforge.common.ForgeDirection;
/*  18:    */ import qx;
/*  19:    */ import ur;
/*  20:    */ import yc;
/*  21:    */ import ym;
/*  22:    */ 
/*  23:    */ public class TileBackplane
/*  24:    */   extends TileMultipart
/*  25:    */   implements IHandlePackets, IFrameSupport
/*  26:    */ {
/*  27:    */   public int readBackplane(int addr)
/*  28:    */   {
/*  29: 26 */     return 255;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void writeBackplane(int addr, int val) {}
/*  33:    */   
/*  34:    */   public int getBlockID()
/*  35:    */   {
/*  36: 35 */     return RedPowerControl.blockBackplane.cm;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public int getExtendedID()
/*  40:    */   {
/*  41: 39 */     return 0;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void onBlockNeighborChange(int l)
/*  45:    */   {
/*  46: 43 */     if (!this.k.isBlockSolidOnSide(this.l, this.m - 1, this.n, ForgeDirection.UP))
/*  47:    */     {
/*  48: 45 */       breakBlock();
/*  49: 46 */       return;
/*  50:    */     }
/*  51: 48 */     WorldCoord wc = new WorldCoord(this);
/*  52: 49 */     wc.step(CoreLib.rotToSide(this.Rotation) ^ 0x1);
/*  53:    */     
/*  54: 51 */     int bid = this.k.a(wc.x, wc.y, wc.z);
/*  55: 52 */     int md = this.k.h(wc.x, wc.y, wc.z);
/*  56: 54 */     if (bid == RedPowerControl.blockBackplane.cm) {
/*  57: 54 */       return;
/*  58:    */     }
/*  59: 55 */     if ((bid == RedPowerControl.blockPeripheral.cm) && (md == 1)) {
/*  60: 56 */       return;
/*  61:    */     }
/*  62: 57 */     breakBlock();
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void addHarvestContents(ArrayList ist)
/*  66:    */   {
/*  67: 63 */     super.addHarvestContents(ist);
/*  68: 64 */     ist.add(new ur(RedPowerControl.blockBackplane, 1, 0));
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void onHarvestPart(qx player, int part)
/*  72:    */   {
/*  73: 68 */     breakBlock();
/*  74:    */   }
/*  75:    */   
/*  76:    */   public float getPartStrength(qx player, int part)
/*  77:    */   {
/*  78: 72 */     return 0.1F;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public boolean blockEmpty()
/*  82:    */   {
/*  83: 75 */     return false;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void setPartBounds(BlockMultipart bl, int part)
/*  87:    */   {
/*  88: 78 */     bl.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*  89:    */   }
/*  90:    */   
/*  91:    */   public int getSolidPartsMask()
/*  92:    */   {
/*  93: 81 */     return 1;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public int getPartsMask()
/*  97:    */   {
/*  98: 82 */     return 1;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public byte[] getFramePacket()
/* 102:    */   {
/* 103: 87 */     Packet211TileDesc pkt = new Packet211TileDesc();
/* 104: 88 */     pkt.subId = 7;
/* 105: 89 */     writeToPacket(pkt);
/* 106: 90 */     pkt.headout.write(pkt.subId);
/* 107: 91 */     return pkt.toByteArray();
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void handleFramePacket(byte[] ba)
/* 111:    */     throws IOException
/* 112:    */   {
/* 113: 95 */     Packet211TileDesc pkt = new Packet211TileDesc(ba);
/* 114: 96 */     pkt.subId = pkt.getByte();
/* 115: 97 */     readFromPacket(pkt);
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void onFrameRefresh(ym iba) {}
/* 119:    */   
/* 120:    */   public void onFramePickup(ym iba) {}
/* 121:    */   
/* 122:    */   public void onFrameDrop() {}
/* 123:    */   
/* 124:    */   public void a(bq nbttagcompound)
/* 125:    */   {
/* 126:107 */     super.a(nbttagcompound);
/* 127:108 */     this.Rotation = nbttagcompound.c("rot");
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void b(bq nbttagcompound)
/* 131:    */   {
/* 132:112 */     super.b(nbttagcompound);
/* 133:113 */     nbttagcompound.a("rot", (byte)this.Rotation);
/* 134:    */   }
/* 135:    */   
/* 136:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 137:    */     throws IOException
/* 138:    */   {
/* 139:118 */     this.Rotation = pkt.getByte();
/* 140:    */   }
/* 141:    */   
/* 142:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 143:    */   {
/* 144:122 */     pkt.addByte(this.Rotation);
/* 145:    */   }
/* 146:    */   
/* 147:    */   public ef l()
/* 148:    */   {
/* 149:126 */     Packet211TileDesc packet = new Packet211TileDesc();
/* 150:127 */     packet.subId = 7;
/* 151:128 */     packet.xCoord = this.l;packet.yCoord = this.m;
/* 152:129 */     packet.zCoord = this.n;
/* 153:130 */     writeToPacket(packet);
/* 154:131 */     packet.encode();
/* 155:132 */     return packet;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void handlePacket(Packet211TileDesc packet)
/* 159:    */   {
/* 160:    */     try
/* 161:    */     {
/* 162:137 */       if (packet.subId != 7) {
/* 163:137 */         return;
/* 164:    */       }
/* 165:138 */       readFromPacket(packet);
/* 166:    */     }
/* 167:    */     catch (IOException e) {}
/* 168:140 */     this.k.i(this.l, this.m, this.n);
/* 169:    */   }
/* 170:    */   
/* 171:143 */   public int Rotation = 0;
/* 172:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.TileBackplane
 * JD-Core Version:    0.7.0.1
 */