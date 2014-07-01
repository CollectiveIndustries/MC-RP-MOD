/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.WorldCoord;
/*   8:    */ import la;
/*   9:    */ import net.minecraftforge.common.ForgeDirection;
/*  10:    */ import net.minecraftforge.common.ISidedInventory;
/*  11:    */ import qw;
/*  12:    */ import qx;
/*  13:    */ import ur;
/*  14:    */ import yc;
/*  15:    */ 
/*  16:    */ public class TileDeploy
/*  17:    */   extends TileDeployBase
/*  18:    */   implements la, ISidedInventory
/*  19:    */ {
/*  20:    */   private ur[] contents;
/*  21:    */   
/*  22:    */   public TileDeploy()
/*  23:    */   {
/*  24: 28 */     this.contents = new ur[9];
/*  25:    */   }
/*  26:    */   
/*  27:    */   public boolean onBlockActivated(qx player)
/*  28:    */   {
/*  29: 34 */     if (player.ah()) {
/*  30: 34 */       return false;
/*  31:    */     }
/*  32: 35 */     if (CoreLib.isClient(this.k)) {
/*  33: 36 */       return true;
/*  34:    */     }
/*  35: 37 */     player.openGui(RedPowerMachine.instance, 1, this.k, this.l, this.m, this.n);
/*  36:    */     
/*  37: 39 */     return true;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void onBlockRemoval()
/*  41:    */   {
/*  42: 43 */     for (int i = 0; i < 9; i++)
/*  43:    */     {
/*  44: 44 */       ur ist = this.contents[i];
/*  45: 45 */       if ((ist != null) && (ist.a > 0)) {
/*  46: 46 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/*  47:    */       }
/*  48:    */     }
/*  49:    */   }
/*  50:    */   
/*  51:    */   protected void packInv(ur[] bkup)
/*  52:    */   {
/*  53: 53 */     for (int i = 0; i < 9; i++)
/*  54:    */     {
/*  55: 54 */       bkup[i] = fakePlayer.bJ.a(i);
/*  56: 55 */       fakePlayer.bJ.a(i, this.contents[i]);
/*  57:    */     }
/*  58:    */   }
/*  59:    */   
/*  60:    */   protected void unpackInv(ur[] bkup)
/*  61:    */   {
/*  62: 61 */     for (int i = 0; i < 9; i++)
/*  63:    */     {
/*  64: 62 */       this.contents[i] = fakePlayer.bJ.a(i);
/*  65: 63 */       fakePlayer.bJ.a(i, bkup[i]);
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void enableTowards(WorldCoord wc)
/*  70:    */   {
/*  71: 70 */     ur[] bkup = new ur[9];
/*  72: 71 */     initPlayer();
/*  73: 72 */     packInv(bkup);
/*  74: 75 */     for (int i = 0; i < 9; i++)
/*  75:    */     {
/*  76: 76 */       ur ist = this.contents[i];
/*  77: 77 */       if ((ist != null) && (ist.a > 0) && 
/*  78: 78 */         (tryUseItemStack(ist, wc.x, wc.y, wc.z, i)))
/*  79:    */       {
/*  80: 79 */         if (fakePlayer.bM()) {
/*  81: 80 */           fakePlayer.bO();
/*  82:    */         }
/*  83: 81 */         unpackInv(bkup);
/*  84: 82 */         if (this.contents[i].a == 0) {
/*  85: 83 */           this.contents[i] = null;
/*  86:    */         }
/*  87: 84 */         d();
/*  88: 85 */         return;
/*  89:    */       }
/*  90:    */     }
/*  91: 89 */     unpackInv(bkup);
/*  92:    */   }
/*  93:    */   
/*  94:    */   public int k_()
/*  95:    */   {
/*  96: 95 */     return 9;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public ur a(int i)
/* 100:    */   {
/* 101: 99 */     return this.contents[i];
/* 102:    */   }
/* 103:    */   
/* 104:    */   public ur a(int i, int j)
/* 105:    */   {
/* 106:104 */     if (this.contents[i] == null) {
/* 107:104 */       return null;
/* 108:    */     }
/* 109:106 */     if (this.contents[i].a <= j)
/* 110:    */     {
/* 111:107 */       ur tr = this.contents[i];
/* 112:108 */       this.contents[i] = null;
/* 113:109 */       d();
/* 114:110 */       return tr;
/* 115:    */     }
/* 116:112 */     ur tr = this.contents[i].a(j);
/* 117:113 */     if (this.contents[i].a == 0) {
/* 118:114 */       this.contents[i] = null;
/* 119:    */     }
/* 120:115 */     d();
/* 121:116 */     return tr;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public ur a_(int i)
/* 125:    */   {
/* 126:120 */     if (this.contents[i] == null) {
/* 127:120 */       return null;
/* 128:    */     }
/* 129:121 */     ur ist = this.contents[i];
/* 130:122 */     this.contents[i] = null;
/* 131:123 */     return ist;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void a(int i, ur ist)
/* 135:    */   {
/* 136:127 */     this.contents[i] = ist;
/* 137:128 */     if ((ist != null) && (ist.a > c())) {
/* 138:129 */       ist.a = c();
/* 139:    */     }
/* 140:130 */     d();
/* 141:    */   }
/* 142:    */   
/* 143:    */   public String b()
/* 144:    */   {
/* 145:134 */     return "Deployer";
/* 146:    */   }
/* 147:    */   
/* 148:    */   public int c()
/* 149:    */   {
/* 150:138 */     return 64;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public boolean a_(qx player)
/* 154:    */   {
/* 155:142 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 156:143 */       return false;
/* 157:    */     }
/* 158:144 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void f() {}
/* 162:    */   
/* 163:    */   public void l_() {}
/* 164:    */   
/* 165:    */   public int getStartInventorySide(ForgeDirection fd)
/* 166:    */   {
/* 167:155 */     int side = fd.ordinal();
/* 168:156 */     if ((side ^ 0x1) == this.Rotation) {
/* 169:156 */       return 0;
/* 170:    */     }
/* 171:157 */     return 0;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 175:    */   {
/* 176:161 */     int side = fd.ordinal();
/* 177:162 */     if ((side ^ 0x1) == this.Rotation) {
/* 178:162 */       return 0;
/* 179:    */     }
/* 180:163 */     return 9;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public void a(bq nbttagcompound)
/* 184:    */   {
/* 185:169 */     super.a(nbttagcompound);
/* 186:    */     
/* 187:171 */     by items = nbttagcompound.m("Items");
/* 188:172 */     this.contents = new ur[k_()];
/* 189:173 */     for (int i = 0; i < items.c(); i++)
/* 190:    */     {
/* 191:174 */       bq item = (bq)items.b(i);
/* 192:    */       
/* 193:176 */       int j = item.c("Slot") & 0xFF;
/* 194:177 */       if ((j >= 0) && (j < this.contents.length)) {
/* 195:178 */         this.contents[j] = ur.a(item);
/* 196:    */       }
/* 197:    */     }
/* 198:    */   }
/* 199:    */   
/* 200:    */   public void b(bq nbttagcompound)
/* 201:    */   {
/* 202:184 */     super.b(nbttagcompound);
/* 203:    */     
/* 204:186 */     by items = new by();
/* 205:187 */     for (int i = 0; i < this.contents.length; i++) {
/* 206:188 */       if (this.contents[i] != null)
/* 207:    */       {
/* 208:189 */         bq item = new bq();
/* 209:190 */         item.a("Slot", (byte)i);
/* 210:191 */         this.contents[i].b(item);
/* 211:192 */         items.a(item);
/* 212:    */       }
/* 213:    */     }
/* 214:195 */     nbttagcompound.a("Items", items);
/* 215:    */   }
/* 216:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileDeploy
 * JD-Core Version:    0.7.0.1
 */