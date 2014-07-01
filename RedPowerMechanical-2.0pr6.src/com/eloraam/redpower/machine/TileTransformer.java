/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   6:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*   9:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  10:    */ import md;
/*  11:    */ import ur;
/*  12:    */ 
/*  13:    */ public class TileTransformer
/*  14:    */   extends TileMachinePanel
/*  15:    */   implements IBluePowerConnectable
/*  16:    */ {
/*  17:    */   public int getPartMaxRotation(int part, boolean sec)
/*  18:    */   {
/*  19: 18 */     if (sec) {
/*  20: 18 */       return 0;
/*  21:    */     }
/*  22: 19 */     return 3;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public int getPartRotation(int part, boolean sec)
/*  26:    */   {
/*  27: 23 */     if (sec) {
/*  28: 23 */       return 0;
/*  29:    */     }
/*  30: 24 */     return this.Rotation & 0x3;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void setPartRotation(int part, boolean sec, int rot)
/*  34:    */   {
/*  35: 28 */     if (sec) {
/*  36: 28 */       return;
/*  37:    */     }
/*  38: 29 */     this.Rotation = (rot & 0x3 | this.Rotation & 0xFFFFFFFC);
/*  39: 30 */     updateBlockChange();
/*  40:    */   }
/*  41:    */   
/*  42:    */   public int getConnectableMask()
/*  43:    */   {
/*  44: 36 */     return RedPowerLib.mapRotToCon(5, this.Rotation);
/*  45:    */   }
/*  46:    */   
/*  47:    */   public int getConnectClass(int side)
/*  48:    */   {
/*  49: 40 */     int s = RedPowerLib.mapRotToCon(1, this.Rotation);
/*  50: 41 */     if ((s & RedPowerLib.getConDirMask(side)) > 0) {
/*  51: 42 */       return 64;
/*  52:    */     }
/*  53: 43 */     return 68;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public int getCornerPowerMode()
/*  57:    */   {
/*  58: 46 */     return 0;
/*  59:    */   }
/*  60:    */   
/*  61: 50 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  62:    */   {
/*  63:    */     public any getParent()
/*  64:    */     {
/*  65: 52 */       return TileTransformer.this;
/*  66:    */     }
/*  67:    */   };
/*  68: 55 */   BluePowerEndpoint cond2 = new BluePowerEndpoint()
/*  69:    */   {
/*  70:    */     public any getParent()
/*  71:    */     {
/*  72: 57 */       return TileTransformer.this;
/*  73:    */     }
/*  74:    */     
/*  75:    */     public double getResistance()
/*  76:    */     {
/*  77: 60 */       return 1.0D;
/*  78:    */     }
/*  79:    */     
/*  80:    */     public double getIndScale()
/*  81:    */     {
/*  82: 61 */       return 0.0007D;
/*  83:    */     }
/*  84:    */     
/*  85:    */     public double getCondParallel()
/*  86:    */     {
/*  87: 62 */       return 0.005D;
/*  88:    */     }
/*  89:    */     
/*  90:    */     public double getInvCap()
/*  91:    */     {
/*  92: 63 */       return 25.0D;
/*  93:    */     }
/*  94:    */     
/*  95:    */     protected void computeVoltage()
/*  96:    */     {
/*  97: 67 */       this.Vcap = (TileTransformer.this.cond.getVoltage() * 100.0D);
/*  98: 68 */       this.Itot = (TileTransformer.this.cond.Itot * 0.01D);
/*  99: 69 */       this.It1 = 0.0D;this.Icap = 0.0D;
/* 100:    */     }
/* 101:    */     
/* 102:    */     public void applyCurrent(double Iin)
/* 103:    */     {
/* 104: 74 */       TileTransformer.this.cond.applyCurrent(Iin * 100.0D);
/* 105:    */     }
/* 106:    */   };
/* 107:    */   
/* 108:    */   public BluePowerConductor getBlueConductor(int side)
/* 109:    */   {
/* 110: 79 */     if ((RedPowerLib.mapRotToCon(1, this.Rotation) & RedPowerLib.getConDirMask(side)) > 0) {
/* 111: 81 */       return this.cond;
/* 112:    */     }
/* 113: 82 */     return this.cond2;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public int getExtendedID()
/* 117:    */   {
/* 118: 89 */     return 4;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void onBlockPlaced(ur ist, int side, md ent)
/* 122:    */   {
/* 123: 96 */     this.Rotation = ((side ^ 0x1) << 2);
/* 124:    */     
/* 125: 98 */     int yaw = (int)Math.floor(ent.z / 90.0F + 0.5F);
/* 126: 99 */     int pitch = (int)Math.floor(ent.A / 90.0F + 0.5F);
/* 127:100 */     yaw &= 0x3;
/* 128:101 */     int down = this.Rotation >> 2;
/* 129:    */     int rot;
/* 130:102 */     switch (down)
/* 131:    */     {
/* 132:    */     case 0: 
/* 133:103 */       rot = yaw; break;
/* 134:    */     case 1: 
/* 135:104 */       rot = yaw ^ (yaw & 0x1) << 1; break;
/* 136:    */     case 2: 
/* 137:105 */       rot = (yaw & 0x1) > 0 ? 0 : pitch > 0 ? 2 : 1 - yaw & 0x3;
/* 138:106 */       break;
/* 139:    */     case 3: 
/* 140:107 */       rot = (yaw & 0x1) > 0 ? 0 : pitch > 0 ? 2 : yaw - 1 & 0x3;
/* 141:108 */       break;
/* 142:    */     case 4: 
/* 143:109 */       rot = (yaw & 0x1) == 0 ? 0 : pitch > 0 ? 2 : yaw - 2 & 0x3;
/* 144:110 */       break;
/* 145:    */     case 5: 
/* 146:111 */       rot = (yaw & 0x1) == 0 ? 0 : pitch > 0 ? 2 : 2 - yaw & 0x3;
/* 147:112 */       break;
/* 148:    */     default: 
/* 149:113 */       rot = 0;
/* 150:    */     }
/* 151:115 */     this.Rotation = (down << 2 | rot);
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void onBlockNeighborChange(int l)
/* 155:    */   {
/* 156:119 */     this.ConMask1 = -1;
/* 157:120 */     this.ConMask2 = -1;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public void g()
/* 161:    */   {
/* 162:124 */     super.g();
/* 163:125 */     if (CoreLib.isClient(this.k)) {
/* 164:125 */       return;
/* 165:    */     }
/* 166:127 */     if (this.ConMask1 < 0)
/* 167:    */     {
/* 168:128 */       int cm1 = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 169:    */       
/* 170:130 */       this.ConMask1 = (cm1 & RedPowerLib.mapRotToCon(1, this.Rotation));
/* 171:131 */       this.ConMask2 = (cm1 & RedPowerLib.mapRotToCon(4, this.Rotation));
/* 172:132 */       this.cond.recache(this.ConMask1, 0);
/* 173:133 */       this.cond2.recache(this.ConMask2, 0);
/* 174:    */     }
/* 175:135 */     this.cond.iterate();
/* 176:    */     
/* 177:137 */     this.cond2.iterate();
/* 178:138 */     dirtyBlock();
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void a(bq tag)
/* 182:    */   {
/* 183:144 */     super.a(tag);
/* 184:145 */     bq c1 = tag.l("c1");
/* 185:146 */     this.cond.readFromNBT(c1);
/* 186:147 */     bq c2 = tag.l("c2");
/* 187:148 */     this.cond2.readFromNBT(c2);
/* 188:    */   }
/* 189:    */   
/* 190:    */   public void b(bq tag)
/* 191:    */   {
/* 192:152 */     super.b(tag);
/* 193:153 */     bq c1 = new bq();
/* 194:154 */     this.cond.writeToNBT(c1);
/* 195:155 */     bq c2 = new bq();
/* 196:156 */     this.cond2.writeToNBT(c2);
/* 197:157 */     tag.a("c1", c1);
/* 198:158 */     tag.a("c2", c2);
/* 199:    */   }
/* 200:    */   
/* 201:160 */   public int ConMask1 = -1;
/* 202:160 */   public int ConMask2 = -1;
/* 203:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileTransformer
 * JD-Core Version:    0.7.0.1
 */