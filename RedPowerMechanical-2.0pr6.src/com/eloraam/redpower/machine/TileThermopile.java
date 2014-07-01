/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import aaq;
/*   4:    */ import akf;
/*   5:    */ import amq;
/*   6:    */ import any;
/*   7:    */ import bq;
/*   8:    */ import com.eloraam.redpower.RedPowerMachine;
/*   9:    */ import com.eloraam.redpower.RedPowerWorld;
/*  10:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*  11:    */ import com.eloraam.redpower.core.CoreLib;
/*  12:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*  13:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  14:    */ import com.eloraam.redpower.core.TileExtended;
/*  15:    */ import com.eloraam.redpower.core.WorldCoord;
/*  16:    */ import com.eloraam.redpower.world.BlockCustomStone;
/*  17:    */ import java.util.Random;
/*  18:    */ import yc;
/*  19:    */ 
/*  20:    */ public class TileThermopile
/*  21:    */   extends TileExtended
/*  22:    */   implements IBluePowerConnectable
/*  23:    */ {
/*  24:    */   public int getConnectableMask()
/*  25:    */   {
/*  26: 29 */     return 1073741823;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public int getConnectClass(int side)
/*  30:    */   {
/*  31: 33 */     return 64;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public int getCornerPowerMode()
/*  35:    */   {
/*  36: 36 */     return 0;
/*  37:    */   }
/*  38:    */   
/*  39: 40 */   BluePowerConductor cond = new BluePowerConductor()
/*  40:    */   {
/*  41:    */     public any getParent()
/*  42:    */     {
/*  43: 42 */       return TileThermopile.this;
/*  44:    */     }
/*  45:    */     
/*  46:    */     public double getInvCap()
/*  47:    */     {
/*  48: 46 */       return 4.0D;
/*  49:    */     }
/*  50:    */   };
/*  51:    */   
/*  52:    */   public BluePowerConductor getBlueConductor(int side)
/*  53:    */   {
/*  54: 51 */     return this.cond;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public int getExtendedID()
/*  58:    */   {
/*  59: 57 */     return 11;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public int getBlockID()
/*  63:    */   {
/*  64: 61 */     return RedPowerMachine.blockMachine.cm;
/*  65:    */   }
/*  66:    */   
/*  67:    */   private void updateTemps()
/*  68:    */   {
/*  69: 65 */     int up = 0;int down = 0;
/*  70: 67 */     for (int i = 0; i < 6; i++)
/*  71:    */     {
/*  72: 68 */       WorldCoord wc = new WorldCoord(this);
/*  73: 69 */       wc.step(i);
/*  74: 70 */       int bid = this.k.a(wc.x, wc.y, wc.z);
/*  75: 72 */       if (this.k.c(wc.x, wc.y, wc.z))
/*  76:    */       {
/*  77: 73 */         if (this.k.u.e) {
/*  78: 74 */           up++;
/*  79:    */         } else {
/*  80: 76 */           down++;
/*  81:    */         }
/*  82:    */       }
/*  83: 78 */       else if (bid == amq.aX.cm) {
/*  84: 79 */         down += 100;
/*  85: 80 */       } else if (bid == amq.aW.cm) {
/*  86: 81 */         down += 100;
/*  87: 82 */       } else if (bid == amq.aV.cm) {
/*  88: 83 */         down += 50;
/*  89: 84 */       } else if (bid == amq.at.cm) {
/*  90: 85 */         up += 5;
/*  91: 86 */       } else if (bid == amq.bi.cm) {
/*  92: 87 */         up += 3;
/*  93: 88 */       } else if ((bid == amq.D.cm) || (bid == amq.E.cm)) {
/*  94: 90 */         down += 25;
/*  95: 91 */       } else if ((bid == amq.F.cm) || (bid == amq.G.cm)) {
/*  96: 93 */         up += 100;
/*  97: 94 */       } else if (bid == amq.au.cm) {
/*  98: 95 */         up += 25;
/*  99:    */       }
/* 100:    */     }
/* 101: 98 */     if ((this.tempHot >= 100) && (this.tempCold >= 200)) {
/* 102: 99 */       for (int i = 0; i < 6; i++)
/* 103:    */       {
/* 104:100 */         WorldCoord wc = new WorldCoord(this);
/* 105:101 */         wc.step(i);
/* 106:102 */         int bid = this.k.a(wc.x, wc.y, wc.z);
/* 107:103 */         if ((bid == amq.F.cm) || (bid == amq.G.cm)) {
/* 108:106 */           if (this.k.t.nextInt(300) == 0)
/* 109:    */           {
/* 110:109 */             int md = this.k.h(wc.x, wc.y, wc.z);
/* 111:110 */             this.k.d(wc.x, wc.y, wc.z, md == 0 ? amq.as.cm : RedPowerWorld.blockStone.cm, md > 0 ? 1 : 0);
/* 112:    */             
/* 113:    */ 
/* 114:    */ 
/* 115:    */ 
/* 116:115 */             break;
/* 117:    */           }
/* 118:    */         }
/* 119:    */       }
/* 120:    */     }
/* 121:118 */     if (this.tempHot >= 100) {
/* 122:119 */       for (int i = 0; i < 6; i++) {
/* 123:120 */         if (this.k.t.nextInt(300) == 0)
/* 124:    */         {
/* 125:122 */           WorldCoord wc = new WorldCoord(this);
/* 126:123 */           wc.step(i);
/* 127:124 */           int bid = this.k.a(wc.x, wc.y, wc.z);
/* 128:125 */           if (bid == amq.aV.cm)
/* 129:    */           {
/* 130:126 */             this.k.e(wc.x, wc.y, wc.z, 0);
/* 131:    */             
/* 132:128 */             break;
/* 133:    */           }
/* 134:130 */           if ((bid == amq.aW.cm) || (bid == amq.aX.cm))
/* 135:    */           {
/* 136:132 */             this.k.e(wc.x, wc.y, wc.z, this.k.u.e ? 0 : amq.D.cm);
/* 137:    */             
/* 138:    */ 
/* 139:    */ 
/* 140:    */ 
/* 141:137 */             break;
/* 142:    */           }
/* 143:    */         }
/* 144:    */       }
/* 145:    */     }
/* 146:141 */     this.tempHot = up;this.tempCold = down;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void g()
/* 150:    */   {
/* 151:145 */     super.g();
/* 152:146 */     if (CoreLib.isClient(this.k)) {
/* 153:146 */       return;
/* 154:    */     }
/* 155:148 */     if (this.ConMask < 0)
/* 156:    */     {
/* 157:149 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 158:    */       
/* 159:151 */       this.cond.recache(this.ConMask, 0);
/* 160:    */     }
/* 161:153 */     this.cond.iterate();
/* 162:154 */     dirtyBlock();
/* 163:156 */     if (this.cond.getVoltage() > 100.0D) {
/* 164:156 */       return;
/* 165:    */     }
/* 166:157 */     this.ticks += 1;
/* 167:158 */     if (this.ticks > 20)
/* 168:    */     {
/* 169:159 */       this.ticks = 0;
/* 170:160 */       updateTemps();
/* 171:    */     }
/* 172:163 */     int t = Math.min(this.tempHot, this.tempCold);
/* 173:164 */     this.cond.applyDirect(0.005D * t);
/* 174:    */   }
/* 175:    */   
/* 176:    */   public void onBlockNeighborChange(int l)
/* 177:    */   {
/* 178:170 */     this.ConMask = -1;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void a(bq tag)
/* 182:    */   {
/* 183:176 */     super.a(tag);
/* 184:177 */     this.cond.readFromNBT(tag);
/* 185:178 */     this.tempHot = tag.d("hot");
/* 186:179 */     this.tempCold = tag.d("cold");
/* 187:180 */     this.ticks = tag.c("ticks");
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void b(bq tag)
/* 191:    */   {
/* 192:184 */     super.b(tag);
/* 193:185 */     this.cond.writeToNBT(tag);
/* 194:186 */     tag.a("hot", (short)this.tempHot);
/* 195:187 */     tag.a("cold", (short)this.tempCold);
/* 196:188 */     tag.a("ticks", (byte)this.ticks);
/* 197:    */   }
/* 198:    */   
/* 199:191 */   public int tempHot = 0;
/* 200:191 */   public int tempCold = 0;
/* 201:191 */   public int ticks = 0;
/* 202:192 */   public int ConMask = -1;
/* 203:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileThermopile
 * JD-Core Version:    0.7.0.1
 */