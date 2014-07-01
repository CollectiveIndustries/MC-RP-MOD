/*   1:    */ package com.eloraam.redpower.wiring;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import com.eloraam.redpower.core.BluePowerConductor;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.IBluePowerConnectable;
/*   8:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   9:    */ 
/*  10:    */ public class TileBluewire
/*  11:    */   extends TileWiring
/*  12:    */   implements IBluePowerConnectable
/*  13:    */ {
/*  14:    */   public float getWireHeight()
/*  15:    */   {
/*  16: 31 */     switch (this.Metadata)
/*  17:    */     {
/*  18:    */     case 0: 
/*  19: 32 */       return 0.188F;
/*  20:    */     case 1: 
/*  21: 33 */       return 0.25F;
/*  22:    */     case 2: 
/*  23: 34 */       return 0.3125F;
/*  24:    */     }
/*  25: 35 */     return 0.25F;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public int getExtendedID()
/*  29:    */   {
/*  30: 42 */     return 5;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public int getConnectClass(int side)
/*  34:    */   {
/*  35: 48 */     switch (this.Metadata)
/*  36:    */     {
/*  37:    */     case 0: 
/*  38: 49 */       return 64;
/*  39:    */     case 1: 
/*  40: 50 */       return 68;
/*  41:    */     }
/*  42: 51 */     return 69;
/*  43:    */   }
/*  44:    */   
/*  45: 57 */   BluePowerConductor cond = new BluePowerConductor()
/*  46:    */   {
/*  47:    */     public any getParent()
/*  48:    */     {
/*  49: 59 */       return TileBluewire.this;
/*  50:    */     }
/*  51:    */     
/*  52:    */     public double getInvCap()
/*  53:    */     {
/*  54: 65 */       switch (TileBluewire.this.Metadata)
/*  55:    */       {
/*  56:    */       case 0: 
/*  57: 66 */         return 8.0D;
/*  58:    */       }
/*  59: 67 */       return 800.0D;
/*  60:    */     }
/*  61:    */     
/*  62:    */     public double getResistance()
/*  63:    */     {
/*  64: 71 */       switch (TileBluewire.this.Metadata)
/*  65:    */       {
/*  66:    */       case 0: 
/*  67: 72 */         return 0.01D;
/*  68:    */       }
/*  69: 73 */       return 1.0D;
/*  70:    */     }
/*  71:    */     
/*  72:    */     public double getIndScale()
/*  73:    */     {
/*  74: 77 */       switch (TileBluewire.this.Metadata)
/*  75:    */       {
/*  76:    */       case 0: 
/*  77: 78 */         return 0.07000000000000001D;
/*  78:    */       }
/*  79: 79 */       return 0.0007D;
/*  80:    */     }
/*  81:    */     
/*  82:    */     public double getCondParallel()
/*  83:    */     {
/*  84: 83 */       switch (TileBluewire.this.Metadata)
/*  85:    */       {
/*  86:    */       case 0: 
/*  87: 84 */         return 0.5D;
/*  88:    */       }
/*  89: 85 */       return 0.005D;
/*  90:    */     }
/*  91:    */   };
/*  92:    */   
/*  93:    */   public BluePowerConductor getBlueConductor(int side)
/*  94:    */   {
/*  95: 91 */     return this.cond;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public int getConnectionMask()
/*  99:    */   {
/* 100: 97 */     if (this.ConMask >= 0) {
/* 101: 97 */       return this.ConMask;
/* 102:    */     }
/* 103: 98 */     this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 104:100 */     if (this.EConMask < 0)
/* 105:    */     {
/* 106:101 */       this.EConMask = RedPowerLib.getExtConnections(this.k, this, this.l, this.m, this.n);
/* 107:    */       
/* 108:103 */       this.EConEMask = RedPowerLib.getExtConnectionExtras(this.k, this, this.l, this.m, this.n);
/* 109:    */     }
/* 110:106 */     if (CoreLib.isClient(this.k)) {
/* 111:106 */       return this.ConMask;
/* 112:    */     }
/* 113:107 */     this.cond.recache(this.ConMask, this.EConMask);
/* 114:108 */     return this.ConMask;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public int getExtConnectionMask()
/* 118:    */   {
/* 119:112 */     if (this.EConMask >= 0) {
/* 120:112 */       return this.EConMask;
/* 121:    */     }
/* 122:113 */     this.EConMask = RedPowerLib.getExtConnections(this.k, this, this.l, this.m, this.n);
/* 123:    */     
/* 124:115 */     this.EConEMask = RedPowerLib.getExtConnectionExtras(this.k, this, this.l, this.m, this.n);
/* 125:117 */     if (this.ConMask < 0) {
/* 126:118 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 127:    */     }
/* 128:121 */     if (CoreLib.isClient(this.k)) {
/* 129:121 */       return this.EConMask;
/* 130:    */     }
/* 131:122 */     this.cond.recache(this.ConMask, this.EConMask);
/* 132:123 */     return this.EConMask;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public boolean canUpdate()
/* 136:    */   {
/* 137:128 */     return true;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void g()
/* 141:    */   {
/* 142:130 */     if (CoreLib.isClient(this.k)) {
/* 143:130 */       return;
/* 144:    */     }
/* 145:132 */     if ((this.ConMask < 0) || (this.EConMask < 0))
/* 146:    */     {
/* 147:133 */       if (this.ConMask < 0) {
/* 148:133 */         this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 149:    */       }
/* 150:135 */       if (this.EConMask < 0)
/* 151:    */       {
/* 152:136 */         this.EConMask = RedPowerLib.getExtConnections(this.k, this, this.l, this.m, this.n);
/* 153:    */         
/* 154:138 */         this.EConEMask = RedPowerLib.getExtConnectionExtras(this.k, this, this.l, this.m, this.n);
/* 155:    */       }
/* 156:141 */       this.cond.recache(this.ConMask, this.EConMask);
/* 157:    */     }
/* 158:143 */     this.cond.iterate();
/* 159:144 */     dirtyBlock();
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void a(bq nbttagcompound)
/* 163:    */   {
/* 164:150 */     super.a(nbttagcompound);
/* 165:151 */     this.cond.readFromNBT(nbttagcompound);
/* 166:    */   }
/* 167:    */   
/* 168:    */   public void b(bq nbttagcompound)
/* 169:    */   {
/* 170:156 */     super.b(nbttagcompound);
/* 171:157 */     this.cond.writeToNBT(nbttagcompound);
/* 172:    */   }
/* 173:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.wiring.TileBluewire
 * JD-Core Version:    0.7.0.1
 */