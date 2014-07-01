/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerMachine;
/*   5:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.IFrameLink;
/*   8:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   9:    */ import com.eloraam.redpower.core.TubeFlow;
/*  10:    */ import com.eloraam.redpower.core.WorldCoord;
/*  11:    */ import java.io.IOException;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import qx;
/*  14:    */ import ur;
/*  15:    */ 
/*  16:    */ public class TileFrameRedstoneTube
/*  17:    */   extends TileRedstoneTube
/*  18:    */   implements IFrameLink
/*  19:    */ {
/*  20:    */   public boolean isFrameMoving()
/*  21:    */   {
/*  22: 24 */     return false;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public boolean canFrameConnectIn(int dir)
/*  26:    */   {
/*  27: 28 */     return (this.StickySides & 1 << dir) > 0;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public boolean canFrameConnectOut(int dir)
/*  31:    */   {
/*  32: 32 */     return (this.StickySides & 1 << dir) > 0;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public WorldCoord getFrameLinkset()
/*  36:    */   {
/*  37: 35 */     return null;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getExtendedID()
/*  41:    */   {
/*  42: 40 */     return 3;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public int getBlockID()
/*  46:    */   {
/*  47: 44 */     return RedPowerMachine.blockFrame.cm;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void onHarvestPart(qx player, int part)
/*  51:    */   {
/*  52: 50 */     boolean change = false;
/*  53: 51 */     if (part == 29)
/*  54:    */     {
/*  55: 52 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(getBlockID(), 1, 3));
/*  56:    */       
/*  57: 54 */       this.flow.onRemove();
/*  58: 55 */       if (this.CoverSides > 0)
/*  59:    */       {
/*  60: 56 */         replaceWithCovers();
/*  61: 57 */         updateBlockChange();
/*  62:    */       }
/*  63:    */       else
/*  64:    */       {
/*  65: 58 */         deleteBlock();
/*  66:    */       }
/*  67:    */     }
/*  68:    */     else
/*  69:    */     {
/*  70: 60 */       super.onHarvestPart(player, part);
/*  71: 61 */       return;
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void addHarvestContents(ArrayList ist)
/*  76:    */   {
/*  77: 66 */     addCoverableHarvestContents(ist);
/*  78: 67 */     ist.add(new ur(getBlockID(), 1, 3));
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void setPartBounds(BlockMultipart bl, int part)
/*  82:    */   {
/*  83: 71 */     if (part == 29) {
/*  84: 72 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  85:    */     } else {
/*  86: 74 */       super.setPartBounds(bl, part);
/*  87:    */     }
/*  88:    */   }
/*  89:    */   
/*  90:    */   public boolean canAddCover(int side, int cover)
/*  91:    */   {
/*  92: 81 */     if (side > 5) {
/*  93: 81 */       return false;
/*  94:    */     }
/*  95: 82 */     int n = cover >> 8;
/*  96: 83 */     if ((n != 0) && (n != 1) && (n != 3) && (n != 4)) {
/*  97: 83 */       return false;
/*  98:    */     }
/*  99: 84 */     if ((this.CoverSides & 1 << side) > 0) {
/* 100: 84 */       return false;
/* 101:    */     }
/* 102: 85 */     return true;
/* 103:    */   }
/* 104:    */   
/* 105:    */   void rebuildSticky()
/* 106:    */   {
/* 107: 89 */     int ss = 0;
/* 108: 90 */     for (int i = 0; i < 6; i++)
/* 109:    */     {
/* 110: 91 */       int m = 1 << i;
/* 111: 92 */       if ((this.CoverSides & m) == 0)
/* 112:    */       {
/* 113: 93 */         ss |= m;
/* 114:    */       }
/* 115:    */       else
/* 116:    */       {
/* 117: 95 */         int n = this.Covers[i] >> 8;
/* 118: 96 */         if ((n == 1) || (n == 4)) {
/* 119: 96 */           ss |= m;
/* 120:    */         }
/* 121:    */       }
/* 122:    */     }
/* 123: 99 */     this.StickySides = ss;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public boolean tryAddCover(int side, int cover)
/* 127:    */   {
/* 128:103 */     if (!super.tryAddCover(side, cover)) {
/* 129:103 */       return false;
/* 130:    */     }
/* 131:104 */     rebuildSticky();
/* 132:105 */     return true;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public int tryRemoveCover(int side)
/* 136:    */   {
/* 137:109 */     int tr = super.tryRemoveCover(side);
/* 138:110 */     if (tr < 0) {
/* 139:110 */       return tr;
/* 140:    */     }
/* 141:111 */     rebuildSticky();
/* 142:112 */     return tr;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void a(bq tag)
/* 146:    */   {
/* 147:118 */     super.a(tag);
/* 148:119 */     rebuildSticky();
/* 149:    */   }
/* 150:    */   
/* 151:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 152:    */     throws IOException
/* 153:    */   {
/* 154:124 */     super.readFromPacket(pkt);
/* 155:125 */     rebuildSticky();
/* 156:    */   }
/* 157:    */   
/* 158:128 */   public int StickySides = 63;
/* 159:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileFrameRedstoneTube
 * JD-Core Version:    0.7.0.1
 */