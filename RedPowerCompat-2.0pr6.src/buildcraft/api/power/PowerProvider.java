/*   1:    */ package buildcraft.api.power;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import buildcraft.api.core.SafeTimeTracker;
/*   6:    */ import net.minecraftforge.common.ForgeDirection;
/*   7:    */ 
/*   8:    */ public abstract class PowerProvider
/*   9:    */   implements IPowerProvider
/*  10:    */ {
/*  11:    */   protected int latency;
/*  12:    */   protected int minEnergyReceived;
/*  13:    */   protected int maxEnergyReceived;
/*  14:    */   protected int maxEnergyStored;
/*  15:    */   protected int minActivationEnergy;
/*  16: 24 */   protected float energyStored = 0.0F;
/*  17: 26 */   protected int powerLoss = 1;
/*  18: 27 */   protected int powerLossRegularity = 100;
/*  19: 29 */   public SafeTimeTracker timeTracker = new SafeTimeTracker();
/*  20: 30 */   public SafeTimeTracker energyLossTracker = new SafeTimeTracker();
/*  21: 32 */   public int[] powerSources = { 0, 0, 0, 0, 0, 0 };
/*  22:    */   
/*  23:    */   public SafeTimeTracker getTimeTracker()
/*  24:    */   {
/*  25: 36 */     return this.timeTracker;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public int getLatency()
/*  29:    */   {
/*  30: 41 */     return this.latency;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public int getMinEnergyReceived()
/*  34:    */   {
/*  35: 46 */     return this.minEnergyReceived;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public int getMaxEnergyReceived()
/*  39:    */   {
/*  40: 51 */     return this.maxEnergyReceived;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public int getMaxEnergyStored()
/*  44:    */   {
/*  45: 56 */     return this.maxEnergyStored;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public int getActivationEnergy()
/*  49:    */   {
/*  50: 61 */     return this.minActivationEnergy;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public float getEnergyStored()
/*  54:    */   {
/*  55: 66 */     return this.energyStored;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void configure(int latency, int minEnergyReceived, int maxEnergyReceived, int minActivationEnergy, int maxStoredEnergy)
/*  59:    */   {
/*  60: 71 */     this.latency = latency;
/*  61: 72 */     this.minEnergyReceived = minEnergyReceived;
/*  62: 73 */     this.maxEnergyReceived = maxEnergyReceived;
/*  63: 74 */     this.maxEnergyStored = maxStoredEnergy;
/*  64: 75 */     this.minActivationEnergy = minActivationEnergy;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void configurePowerPerdition(int powerLoss, int powerLossRegularity)
/*  68:    */   {
/*  69: 80 */     this.powerLoss = powerLoss;
/*  70: 81 */     this.powerLossRegularity = powerLossRegularity;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public boolean update(IPowerReceptor receptor)
/*  74:    */   {
/*  75: 86 */     if (!preConditions(receptor)) {
/*  76: 87 */       return false;
/*  77:    */     }
/*  78: 89 */     any tile = (any)receptor;
/*  79: 90 */     boolean result = false;
/*  80: 92 */     if (this.energyStored >= this.minActivationEnergy) {
/*  81: 93 */       if (this.latency == 0)
/*  82:    */       {
/*  83: 94 */         receptor.doWork();
/*  84: 95 */         result = true;
/*  85:    */       }
/*  86: 97 */       else if (this.timeTracker.markTimeIfDelay(tile.k, this.latency))
/*  87:    */       {
/*  88: 98 */         receptor.doWork();
/*  89: 99 */         result = true;
/*  90:    */       }
/*  91:    */     }
/*  92:104 */     if ((this.powerLoss > 0) && (this.energyLossTracker.markTimeIfDelay(tile.k, this.powerLossRegularity)))
/*  93:    */     {
/*  94:106 */       this.energyStored -= this.powerLoss;
/*  95:107 */       if (this.energyStored < 0.0F) {
/*  96:108 */         this.energyStored = 0.0F;
/*  97:    */       }
/*  98:    */     }
/*  99:112 */     for (int i = 0; i < 6; i++) {
/* 100:113 */       if (this.powerSources[i] > 0) {
/* 101:114 */         this.powerSources[i] -= 1;
/* 102:    */       }
/* 103:    */     }
/* 104:118 */     return result;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public boolean preConditions(IPowerReceptor receptor)
/* 108:    */   {
/* 109:123 */     return true;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public float useEnergy(float min, float max, boolean doUse)
/* 113:    */   {
/* 114:128 */     float result = 0.0F;
/* 115:130 */     if (this.energyStored >= min) {
/* 116:131 */       if (this.energyStored <= max)
/* 117:    */       {
/* 118:132 */         result = this.energyStored;
/* 119:133 */         if (doUse) {
/* 120:134 */           this.energyStored = 0.0F;
/* 121:    */         }
/* 122:    */       }
/* 123:    */       else
/* 124:    */       {
/* 125:137 */         result = max;
/* 126:138 */         if (doUse) {
/* 127:139 */           this.energyStored -= max;
/* 128:    */         }
/* 129:    */       }
/* 130:    */     }
/* 131:144 */     return result;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void readFromNBT(bq nbttagcompound)
/* 135:    */   {
/* 136:149 */     this.latency = nbttagcompound.e("latency");
/* 137:150 */     this.minEnergyReceived = nbttagcompound.e("minEnergyReceived");
/* 138:151 */     this.maxEnergyReceived = nbttagcompound.e("maxEnergyReceived");
/* 139:152 */     this.maxEnergyStored = nbttagcompound.e("maxStoreEnergy");
/* 140:153 */     this.minActivationEnergy = nbttagcompound.e("minActivationEnergy");
/* 141:    */     try
/* 142:    */     {
/* 143:156 */       this.energyStored = nbttagcompound.g("storedEnergy");
/* 144:    */     }
/* 145:    */     catch (Throwable c)
/* 146:    */     {
/* 147:158 */       this.energyStored = 0.0F;
/* 148:    */     }
/* 149:    */   }
/* 150:    */   
/* 151:    */   public void writeToNBT(bq nbttagcompound)
/* 152:    */   {
/* 153:164 */     nbttagcompound.a("latency", this.latency);
/* 154:165 */     nbttagcompound.a("minEnergyReceived", this.minEnergyReceived);
/* 155:166 */     nbttagcompound.a("maxEnergyReceived", this.maxEnergyReceived);
/* 156:167 */     nbttagcompound.a("maxStoreEnergy", this.maxEnergyStored);
/* 157:168 */     nbttagcompound.a("minActivationEnergy", this.minActivationEnergy);
/* 158:169 */     nbttagcompound.a("storedEnergy", this.energyStored);
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void receiveEnergy(float quantity, ForgeDirection from)
/* 162:    */   {
/* 163:174 */     this.powerSources[from.ordinal()] = 2;
/* 164:    */     
/* 165:176 */     this.energyStored += quantity;
/* 166:178 */     if (this.energyStored > this.maxEnergyStored) {
/* 167:179 */       this.energyStored = this.maxEnergyStored;
/* 168:    */     }
/* 169:    */   }
/* 170:    */   
/* 171:    */   public boolean isPowerSource(ForgeDirection from)
/* 172:    */   {
/* 173:185 */     return this.powerSources[from.ordinal()] != 0;
/* 174:    */   }
/* 175:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.power.PowerProvider
 * JD-Core Version:    0.7.0.1
 */