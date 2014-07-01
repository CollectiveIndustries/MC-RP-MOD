/*   1:    */ 
/*   2:    */ 
/*   3:    */ any
/*   4:    */ bq
/*   5:    */ buildcraft.api.power.IPowerProvider
/*   6:    */ buildcraft.api.power.IPowerReceptor
/*   7:    */ buildcraft.api.power.PowerProvider
/*   8:    */ com.eloraam.redpower.core.BluePowerConductor
/*   9:    */ com.eloraam.redpower.core.BluePowerEndpoint
/*  10:    */ com.eloraam.redpower.core.CoreLib
/*  11:    */ com.eloraam.redpower.core.IBluePowerConnectable
/*  12:    */ com.eloraam.redpower.core.Packet211TileDesc
/*  13:    */ com.eloraam.redpower.core.RedPowerLib
/*  14:    */ com.eloraam.redpower.core.WorldCoord
/*  15:    */ java.io.IOException
/*  16:    */ net.minecraftforge.common.ForgeDirection
/*  17:    */ 
/*  18:    */ TileBlueEngine
/*  19:    */   
/*  20:    */   
/*  21:    */ 
/*  22:    */   getConnectableMask
/*  23:    */   
/*  24: 32 */      = getConDirMaskRotation ^ 0x1) | 15 << ((this.Rotation ^ 0x1) << 2);
/*  25:    */     
/*  26: 34 */     return 0xFFFFFF & (wm ^ 0xFFFFFFFF) | 16777216 << this.Rotation;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public int getConnectClass(int side)
/*  30:    */   {
/*  31: 38 */     return 65;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public int getCornerPowerMode()
/*  35:    */   {
/*  36: 41 */     return 0;
/*  37:    */   }
/*  38:    */   
/*  39: 45 */   BluePowerEndpoint cond = new BluePowerEndpoint()
/*  40:    */   {
/*  41:    */     public any getParent()
/*  42:    */     {
/*  43: 47 */       return TileBlueEngine.this;
/*  44:    */     }
/*  45:    */   };
/*  46:    */   
/*  47:    */   public BluePowerConductor getBlueConductor(int side)
/*  48:    */   {
/*  49: 52 */     return this.cond;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void onBlockNeighborChange(int l)
/*  53:    */   {
/*  54: 58 */     this.ConMask = -1;
/*  55:    */     
/*  56: 60 */     int cm = getConnectableMask();
/*  57: 61 */     if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, cm, cm >> 24))
/*  58:    */     {
/*  59: 63 */       if (this.Powered) {
/*  60: 63 */         return;
/*  61:    */       }
/*  62: 64 */       this.Powered = true;
/*  63: 65 */       updateBlock();
/*  64:    */     }
/*  65:    */     else
/*  66:    */     {
/*  67: 67 */       this.Powered = false;
/*  68: 68 */       updateBlock();
/*  69: 69 */       return;
/*  70:    */     }
/*  71:    */   }
/*  72:    */   
/*  73:    */   protected void deliverPower()
/*  74:    */   {
/*  75: 76 */     WorldCoord pos = new WorldCoord(this);
/*  76: 77 */     pos.step(this.Rotation ^ 0x1);
/*  77:    */     
/*  78: 79 */     IPowerReceptor ipr = (IPowerReceptor)CoreLib.getTileEntity(this.k, pos, IPowerReceptor.class);
/*  79: 81 */     if (ipr == null) {
/*  80: 81 */       return;
/*  81:    */     }
/*  82: 82 */     IPowerProvider ipp = ipr.getPowerProvider();
/*  83: 83 */     if ((ipp == null) || (!(ipp instanceof PowerProvider))) {
/*  84: 83 */       return;
/*  85:    */     }
/*  86: 85 */     double todraw = Math.min(ipp.getMaxEnergyStored() - ipp.getEnergyStored(), ipp.getMaxEnergyReceived());
/*  87:    */     
/*  88:    */ 
/*  89: 88 */     todraw = Math.min(todraw, this.Flywheel);
/*  90: 89 */     if (todraw < ipp.getMinEnergyReceived()) {
/*  91: 90 */       return;
/*  92:    */     }
/*  93: 92 */     this.Flywheel -= todraw;
/*  94: 93 */     ipp.receiveEnergy((float)todraw, ForgeDirection.getOrientation(this.Rotation));
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void onTileTick() {}
/*  98:    */   
/*  99:    */   public void g()
/* 100:    */   {
/* 101:102 */     super.g();
/* 102:103 */     if (CoreLib.isClient(this.k))
/* 103:    */     {
/* 104:104 */       if (this.Active)
/* 105:    */       {
/* 106:105 */         this.PumpTick = ((byte)(this.PumpTick + 1));
/* 107:106 */         if (this.PumpTick >= this.PumpSpeed * 2)
/* 108:    */         {
/* 109:107 */           this.PumpTick = 0;
/* 110:108 */           if (this.PumpSpeed > 4) {
/* 111:108 */             this.PumpSpeed = ((byte)(this.PumpSpeed - 1));
/* 112:    */           }
/* 113:    */         }
/* 114:    */       }
/* 115:    */       else
/* 116:    */       {
/* 117:110 */         this.PumpTick = 0;
/* 118:    */       }
/* 119:111 */       return;
/* 120:    */     }
/* 121:114 */     if (this.ConMask < 0)
/* 122:    */     {
/* 123:115 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 124:    */       
/* 125:117 */       this.cond.recache(this.ConMask, 0);
/* 126:    */     }
/* 127:119 */     this.cond.iterate();
/* 128:120 */     dirtyBlock();
/* 129:    */     
/* 130:122 */     boolean act = this.Active;
/* 131:123 */     if (this.Active)
/* 132:    */     {
/* 133:124 */       this.PumpTick = ((byte)(this.PumpTick + 1));
/* 134:125 */       int sp = this.PumpTick;
/* 135:126 */       if (sp == this.PumpSpeed) {
/* 136:127 */         deliverPower();
/* 137:    */       }
/* 138:129 */       if (sp >= this.PumpSpeed * 2)
/* 139:    */       {
/* 140:130 */         this.PumpTick = 0;
/* 141:131 */         if (this.PumpSpeed > 4) {
/* 142:131 */           this.PumpSpeed = ((byte)(this.PumpSpeed - 1));
/* 143:    */         }
/* 144:132 */         this.Active = false;
/* 145:    */       }
/* 146:134 */       if ((this.Powered) && (this.Flywheel < 512.0D))
/* 147:    */       {
/* 148:135 */         double draw = Math.min(Math.min(512.0D - this.Flywheel, 32.0D), 0.002D * this.cond.getEnergy(60.0D));
/* 149:    */         
/* 150:137 */         this.cond.drawPower(1000.0D * draw);
/* 151:138 */         this.Flywheel += draw;
/* 152:    */       }
/* 153:140 */       this.cond.drawPower(50.0D);
/* 154:    */     }
/* 155:142 */     if (this.cond.getVoltage() < 60.0D)
/* 156:    */     {
/* 157:143 */       if ((this.Charged) && (this.cond.Flow == 0))
/* 158:    */       {
/* 159:144 */         this.Charged = false;
/* 160:145 */         updateBlock();
/* 161:    */       }
/* 162:147 */       return;
/* 163:    */     }
/* 164:148 */     if (!this.Charged)
/* 165:    */     {
/* 166:149 */       this.Charged = true;
/* 167:150 */       updateBlock();
/* 168:    */     }
/* 169:152 */     if ((this.Charged) && (this.Powered)) {
/* 170:152 */       this.Active = true;
/* 171:    */     }
/* 172:153 */     if (this.Active != act)
/* 173:    */     {
/* 174:154 */       if (this.Active) {
/* 175:154 */         this.PumpSpeed = 16;
/* 176:    */       }
/* 177:155 */       updateBlock();
/* 178:    */     }
/* 179:    */   }
/* 180:    */   
/* 181:    */   public int getExtendedID()
/* 182:    */   {
/* 183:160 */     return 0;
/* 184:    */   }
/* 185:    */   
/* 186:    */   public void a(bq tag)
/* 187:    */   {
/* 188:166 */     super.a(tag);
/* 189:167 */     this.cond.readFromNBT(tag);
/* 190:168 */     this.PumpTick = tag.c("ptk");
/* 191:169 */     this.PumpSpeed = tag.c("spd");
/* 192:170 */     this.Flywheel = tag.h("flyw");
/* 193:    */   }
/* 194:    */   
/* 195:    */   public void b(bq tag)
/* 196:    */   {
/* 197:174 */     super.b(tag);
/* 198:175 */     this.cond.writeToNBT(tag);
/* 199:176 */     tag.a("ptk", this.PumpTick);
/* 200:177 */     tag.a("spd", this.PumpSpeed);
/* 201:178 */     tag.a("flyw", this.Flywheel);
/* 202:    */   }
/* 203:    */   
/* 204:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 205:    */     throws IOException
/* 206:    */   {
/* 207:184 */     super.readFromPacket(pkt);
/* 208:185 */     this.PumpSpeed = ((byte)pkt.getByte());
/* 209:    */   }
/* 210:    */   
/* 211:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 212:    */   {
/* 213:190 */     super.writeToPacket(pkt);
/* 214:191 */     pkt.addByte(this.PumpSpeed);
/* 215:    */   }
/* 216:    */   
/* 217:194 */   public int ConMask = -1;
/* 218:195 */   public byte PumpTick = 0;
/* 219:196 */   public byte PumpSpeed = 16;
/* 220:197 */   private double Flywheel = 0.0D;
/* 221:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.compat.TileBlueEngine
 * JD-Core Version:    0.7.0.1
 */