/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.core.IConnectable;
/*   6:    */ import com.eloraam.redpower.core.IFrameLink;
/*   7:    */ import com.eloraam.redpower.core.ITubeConnectable;
/*   8:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   9:    */ import com.eloraam.redpower.core.TubeBuffer;
/*  10:    */ import com.eloraam.redpower.core.TubeItem;
/*  11:    */ import com.eloraam.redpower.core.WorldCoord;
/*  12:    */ import yc;
/*  13:    */ 
/*  14:    */ public class TileBreaker
/*  15:    */   extends TileMachine
/*  16:    */   implements ITubeConnectable, IFrameLink, IConnectable
/*  17:    */ {
/*  18:    */   public boolean isFrameMoving()
/*  19:    */   {
/*  20: 25 */     return false;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public boolean canFrameConnectIn(int dir)
/*  24:    */   {
/*  25: 29 */     if (dir == (this.Rotation ^ 0x1)) {
/*  26: 29 */       return false;
/*  27:    */     }
/*  28: 30 */     return true;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public boolean canFrameConnectOut(int dir)
/*  32:    */   {
/*  33: 34 */     return false;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public WorldCoord getFrameLinkset()
/*  37:    */   {
/*  38: 44 */     return null;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getConnectableMask()
/*  42:    */   {
/*  43: 49 */     return 0x3FFFFFFF ^ RedPowerLib.getConDirMask(this.Rotation ^ 0x1);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public int getConnectClass(int side)
/*  47:    */   {
/*  48: 53 */     return 0;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public int getCornerPowerMode()
/*  52:    */   {
/*  53: 57 */     return 0;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public int getTubeConnectableSides()
/*  57:    */   {
/*  58: 63 */     return 1 << this.Rotation;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public int getTubeConClass()
/*  62:    */   {
/*  63: 67 */     return 0;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public boolean canRouteItems()
/*  67:    */   {
/*  68: 71 */     return false;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public boolean tubeItemEnter(int side, int state, TubeItem ti)
/*  72:    */   {
/*  73: 75 */     if ((side == this.Rotation) && (state == 2))
/*  74:    */     {
/*  75: 76 */       this.buffer.addBounce(ti);
/*  76: 77 */       this.Active = true;
/*  77: 78 */       scheduleTick(5);
/*  78: 79 */       return true;
/*  79:    */     }
/*  80: 81 */     return false;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public boolean tubeItemCanEnter(int side, int state, TubeItem ti)
/*  84:    */   {
/*  85: 85 */     if ((side == this.Rotation) && (state == 2)) {
/*  86: 86 */       return true;
/*  87:    */     }
/*  88: 87 */     return false;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public int tubeWeight(int side, int state)
/*  92:    */   {
/*  93: 91 */     if ((side == this.Rotation) && (state == 2)) {
/*  94: 92 */       return this.buffer.size();
/*  95:    */     }
/*  96: 93 */     return 0;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public void onBlockNeighborChange(int l)
/* 100:    */   {
/* 101: 99 */     int cm = getConnectableMask();
/* 102:100 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, cm, cm >> 24))
/* 103:    */     {
/* 104:102 */       if (this.Powered) {
/* 105:102 */         return;
/* 106:    */       }
/* 107:103 */       this.Powered = true;
/* 108:    */     }
/* 109:    */     else
/* 110:    */     {
/* 111:105 */       if ((this.Active) && (!isTickScheduled())) {
/* 112:106 */         scheduleTick(5);
/* 113:    */       }
/* 114:107 */       if (!this.Powered) {
/* 115:107 */         return;
/* 116:    */       }
/* 117:108 */       this.Powered = false;
/* 118:109 */       return;
/* 119:    */     }
/* 120:111 */     dirtyBlock();
/* 121:112 */     if (this.Active) {
/* 122:112 */       return;
/* 123:    */     }
/* 124:114 */     WorldCoord wc = new WorldCoord(this.l, this.m, this.n);
/* 125:115 */     wc.step(this.Rotation ^ 0x1);
/* 126:    */     
/* 127:117 */     int bid = this.k.a(wc.x, wc.y, wc.z);
/* 128:118 */     if ((bid == 0) || (bid == amq.C.cm)) {
/* 129:118 */       return;
/* 130:    */     }
/* 131:120 */     amq bl = amq.p[bid];
/* 132:121 */     if (bl.m(this.k, wc.x, wc.y, wc.z) < 0.0F) {
/* 133:121 */       return;
/* 134:    */     }
/* 135:123 */     this.Active = true;
/* 136:124 */     updateBlock();
/* 137:    */     
/* 138:126 */     int md = this.k.h(wc.x, wc.y, wc.z);
/* 139:127 */     this.buffer.addAll(bl.getBlockDropped(this.k, wc.x, wc.y, wc.z, md, 0));
/* 140:128 */     this.k.e(wc.x, wc.y, wc.z, 0);
/* 141:    */     
/* 142:130 */     drainBuffer();
/* 143:131 */     if (!this.buffer.isEmpty()) {
/* 144:132 */       scheduleTick(5);
/* 145:    */     }
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void drainBuffer()
/* 149:    */   {
/* 150:136 */     while (!this.buffer.isEmpty())
/* 151:    */     {
/* 152:137 */       TubeItem ti = this.buffer.getLast();
/* 153:138 */       if (!handleItem(ti))
/* 154:    */       {
/* 155:139 */         this.buffer.plugged = true;
/* 156:140 */         return;
/* 157:    */       }
/* 158:142 */       this.buffer.pop();
/* 159:143 */       if (this.buffer.plugged) {
/* 160:143 */         return;
/* 161:    */       }
/* 162:    */     }
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void onBlockRemoval()
/* 166:    */   {
/* 167:148 */     this.buffer.onRemove(this);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void onTileTick()
/* 171:    */   {
/* 172:152 */     if (!this.buffer.isEmpty())
/* 173:    */     {
/* 174:153 */       drainBuffer();
/* 175:154 */       if (!this.buffer.isEmpty()) {
/* 176:154 */         scheduleTick(10);
/* 177:    */       } else {
/* 178:155 */         scheduleTick(5);
/* 179:    */       }
/* 180:156 */       return;
/* 181:    */     }
/* 182:158 */     if (!this.Powered)
/* 183:    */     {
/* 184:159 */       this.Active = false;
/* 185:160 */       updateBlock();
/* 186:    */     }
/* 187:    */   }
/* 188:    */   
/* 189:    */   public int getExtendedID()
/* 190:    */   {
/* 191:165 */     return 1;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void a(bq nbttagcompound)
/* 195:    */   {
/* 196:171 */     super.a(nbttagcompound);
/* 197:172 */     this.buffer.readFromNBT(nbttagcompound);
/* 198:    */   }
/* 199:    */   
/* 200:    */   public void b(bq nbttagcompound)
/* 201:    */   {
/* 202:176 */     super.b(nbttagcompound);
/* 203:177 */     this.buffer.writeToNBT(nbttagcompound);
/* 204:    */   }
/* 205:    */   
/* 206:180 */   TubeBuffer buffer = new TubeBuffer();
/* 207:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileBreaker
 * JD-Core Version:    0.7.0.1
 */