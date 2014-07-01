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
/*  16:    */ public class TileFrameTube
/*  17:    */   extends TileTube
/*  18:    */   implements IFrameLink
/*  19:    */ {
/*  20:    */   public boolean isFrameMoving()
/*  21:    */   {
/*  22: 23 */     return false;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public boolean canFrameConnectIn(int dir)
/*  26:    */   {
/*  27: 27 */     return (this.StickySides & 1 << dir) > 0;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public boolean canFrameConnectOut(int dir)
/*  31:    */   {
/*  32: 31 */     return (this.StickySides & 1 << dir) > 0;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public WorldCoord getFrameLinkset()
/*  36:    */   {
/*  37: 34 */     return null;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getExtendedID()
/*  41:    */   {
/*  42: 39 */     return 2;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public int getBlockID()
/*  46:    */   {
/*  47: 43 */     return RedPowerMachine.blockFrame.cm;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void onHarvestPart(qx player, int part)
/*  51:    */   {
/*  52: 49 */     boolean change = false;
/*  53: 50 */     if (part == 29)
/*  54:    */     {
/*  55: 51 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(getBlockID(), 1, 2));
/*  56:    */       
/*  57: 53 */       this.flow.onRemove();
/*  58: 54 */       if (this.CoverSides > 0)
/*  59:    */       {
/*  60: 55 */         replaceWithCovers();
/*  61: 56 */         updateBlockChange();
/*  62:    */       }
/*  63:    */       else
/*  64:    */       {
/*  65: 57 */         deleteBlock();
/*  66:    */       }
/*  67:    */     }
/*  68:    */     else
/*  69:    */     {
/*  70: 59 */       super.onHarvestPart(player, part);
/*  71: 60 */       return;
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void addHarvestContents(ArrayList ist)
/*  76:    */   {
/*  77: 65 */     addCoverableHarvestContents(ist);
/*  78: 66 */     ist.add(new ur(getBlockID(), 1, 2));
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void setPartBounds(BlockMultipart bl, int part)
/*  82:    */   {
/*  83: 70 */     if (part == 29) {
/*  84: 71 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  85:    */     } else {
/*  86: 73 */       super.setPartBounds(bl, part);
/*  87:    */     }
/*  88:    */   }
/*  89:    */   
/*  90:    */   public boolean canAddCover(int side, int cover)
/*  91:    */   {
/*  92: 80 */     if (side > 5) {
/*  93: 80 */       return false;
/*  94:    */     }
/*  95: 81 */     int n = cover >> 8;
/*  96: 82 */     if ((n != 0) && (n != 1) && (n != 3) && (n != 4)) {
/*  97: 82 */       return false;
/*  98:    */     }
/*  99: 83 */     if ((this.CoverSides & 1 << side) > 0) {
/* 100: 83 */       return false;
/* 101:    */     }
/* 102: 84 */     return true;
/* 103:    */   }
/* 104:    */   
/* 105:    */   void rebuildSticky()
/* 106:    */   {
/* 107: 88 */     int ss = 0;
/* 108: 89 */     for (int i = 0; i < 6; i++)
/* 109:    */     {
/* 110: 90 */       int m = 1 << i;
/* 111: 91 */       if ((this.CoverSides & m) == 0)
/* 112:    */       {
/* 113: 92 */         ss |= m;
/* 114:    */       }
/* 115:    */       else
/* 116:    */       {
/* 117: 94 */         int n = this.Covers[i] >> 8;
/* 118: 95 */         if ((n == 1) || (n == 4)) {
/* 119: 95 */           ss |= m;
/* 120:    */         }
/* 121:    */       }
/* 122:    */     }
/* 123: 98 */     this.StickySides = ss;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public boolean tryAddCover(int side, int cover)
/* 127:    */   {
/* 128:102 */     if (!super.tryAddCover(side, cover)) {
/* 129:102 */       return false;
/* 130:    */     }
/* 131:103 */     rebuildSticky();
/* 132:104 */     return true;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public int tryRemoveCover(int side)
/* 136:    */   {
/* 137:108 */     int tr = super.tryRemoveCover(side);
/* 138:109 */     if (tr < 0) {
/* 139:109 */       return tr;
/* 140:    */     }
/* 141:110 */     rebuildSticky();
/* 142:111 */     return tr;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void a(bq tag)
/* 146:    */   {
/* 147:117 */     super.a(tag);
/* 148:118 */     rebuildSticky();
/* 149:    */   }
/* 150:    */   
/* 151:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 152:    */     throws IOException
/* 153:    */   {
/* 154:123 */     super.readFromPacket(pkt);
/* 155:124 */     rebuildSticky();
/* 156:    */   }
/* 157:    */   
/* 158:127 */   public int StickySides = 63;
/* 159:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileFrameTube
 * JD-Core Version:    0.7.0.1
 */