/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerMachine;
/*   6:    */ import com.eloraam.redpower.base.TileAppliance;
/*   7:    */ import com.eloraam.redpower.core.CoreLib;
/*   8:    */ import com.eloraam.redpower.core.IRotatable;
/*   9:    */ import la;
/*  10:    */ import md;
/*  11:    */ import net.minecraftforge.common.ForgeDirection;
/*  12:    */ import net.minecraftforge.common.ISidedInventory;
/*  13:    */ import qx;
/*  14:    */ import ur;
/*  15:    */ import yc;
/*  16:    */ 
/*  17:    */ public class TileBufferChest
/*  18:    */   extends TileAppliance
/*  19:    */   implements la, ISidedInventory, IRotatable
/*  20:    */ {
/*  21:    */   private ur[] contents;
/*  22:    */   
/*  23:    */   public TileBufferChest()
/*  24:    */   {
/*  25: 28 */     this.contents = new ur[20];
/*  26:    */   }
/*  27:    */   
/*  28:    */   public int getExtendedID()
/*  29:    */   {
/*  30: 34 */     return 2;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public boolean canUpdate()
/*  34:    */   {
/*  35: 39 */     return false;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public boolean onBlockActivated(qx player)
/*  39:    */   {
/*  40: 45 */     if (player.ah()) {
/*  41: 45 */       return false;
/*  42:    */     }
/*  43: 46 */     if (CoreLib.isClient(this.k)) {
/*  44: 47 */       return true;
/*  45:    */     }
/*  46: 48 */     player.openGui(RedPowerMachine.instance, 4, this.k, this.l, this.m, this.n);
/*  47:    */     
/*  48: 50 */     return true;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public int getFacing(md ent)
/*  52:    */   {
/*  53: 54 */     int yawrx = (int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  54: 55 */     if ((Math.abs(ent.t - this.l) < 2.0D) && (Math.abs(ent.v - this.n) < 2.0D))
/*  55:    */     {
/*  56: 57 */       double p = ent.u + 1.82D - ent.M - this.m;
/*  57: 58 */       if (p > 2.0D) {
/*  58: 58 */         return 0;
/*  59:    */       }
/*  60: 59 */       if (p < 0.0D) {
/*  61: 59 */         return 1;
/*  62:    */       }
/*  63:    */     }
/*  64: 61 */     switch (yawrx)
/*  65:    */     {
/*  66:    */     case 0: 
/*  67: 62 */       return 3;
/*  68:    */     case 1: 
/*  69: 63 */       return 4;
/*  70:    */     case 2: 
/*  71: 64 */       return 2;
/*  72:    */     }
/*  73: 65 */     return 5;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void onBlockPlaced(ur ist, int side, md ent)
/*  77:    */   {
/*  78: 71 */     this.Rotation = getFacing(ent);
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void onBlockRemoval()
/*  82:    */   {
/*  83: 75 */     for (int i = 0; i < 20; i++)
/*  84:    */     {
/*  85: 76 */       ur ist = this.contents[i];
/*  86: 77 */       if ((ist != null) && (ist.a > 0)) {
/*  87: 78 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/*  88:    */       }
/*  89:    */     }
/*  90:    */   }
/*  91:    */   
/*  92:    */   public int getPartMaxRotation(int part, boolean sec)
/*  93:    */   {
/*  94: 87 */     if (sec) {
/*  95: 87 */       return 0;
/*  96:    */     }
/*  97: 88 */     return 5;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public int getPartRotation(int part, boolean sec)
/* 101:    */   {
/* 102: 92 */     if (sec) {
/* 103: 92 */       return 0;
/* 104:    */     }
/* 105: 93 */     return this.Rotation;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void setPartRotation(int part, boolean sec, int rot)
/* 109:    */   {
/* 110: 97 */     if (sec) {
/* 111: 97 */       return;
/* 112:    */     }
/* 113: 98 */     this.Rotation = rot;
/* 114: 99 */     updateBlockChange();
/* 115:    */   }
/* 116:    */   
/* 117:    */   public int k_()
/* 118:    */   {
/* 119:105 */     return 20;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public ur a(int i)
/* 123:    */   {
/* 124:109 */     return this.contents[i];
/* 125:    */   }
/* 126:    */   
/* 127:    */   public ur a(int i, int j)
/* 128:    */   {
/* 129:114 */     if (this.contents[i] == null) {
/* 130:114 */       return null;
/* 131:    */     }
/* 132:116 */     if (this.contents[i].a <= j)
/* 133:    */     {
/* 134:117 */       ur tr = this.contents[i];
/* 135:118 */       this.contents[i] = null;
/* 136:119 */       d();
/* 137:120 */       return tr;
/* 138:    */     }
/* 139:122 */     ur tr = this.contents[i].a(j);
/* 140:123 */     if (this.contents[i].a == 0) {
/* 141:124 */       this.contents[i] = null;
/* 142:    */     }
/* 143:125 */     d();
/* 144:126 */     return tr;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public ur a_(int i)
/* 148:    */   {
/* 149:130 */     if (this.contents[i] == null) {
/* 150:130 */       return null;
/* 151:    */     }
/* 152:131 */     ur ist = this.contents[i];
/* 153:132 */     this.contents[i] = null;
/* 154:133 */     return ist;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void a(int i, ur ist)
/* 158:    */   {
/* 159:137 */     this.contents[i] = ist;
/* 160:138 */     if ((ist != null) && (ist.a > c())) {
/* 161:139 */       ist.a = c();
/* 162:    */     }
/* 163:140 */     d();
/* 164:    */   }
/* 165:    */   
/* 166:    */   public String b()
/* 167:    */   {
/* 168:144 */     return "Buffer";
/* 169:    */   }
/* 170:    */   
/* 171:    */   public int c()
/* 172:    */   {
/* 173:148 */     return 64;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public boolean a_(qx player)
/* 177:    */   {
/* 178:152 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 179:153 */       return false;
/* 180:    */     }
/* 181:154 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void f() {}
/* 185:    */   
/* 186:    */   public void l_() {}
/* 187:    */   
/* 188:    */   public int getStartInventorySide(ForgeDirection fd)
/* 189:    */   {
/* 190:165 */     int side = fd.ordinal();
/* 191:166 */     if ((side ^ 0x1) == this.Rotation) {
/* 192:166 */       return 0;
/* 193:    */     }
/* 194:167 */     return 4 * ((5 + (side ^ 0x1) - this.Rotation) % 6);
/* 195:    */   }
/* 196:    */   
/* 197:    */   public int getSizeInventorySide(ForgeDirection fd)
/* 198:    */   {
/* 199:171 */     int side = fd.ordinal();
/* 200:172 */     if ((side ^ 0x1) == this.Rotation) {
/* 201:172 */       return 20;
/* 202:    */     }
/* 203:173 */     return 4;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public void a(bq nbttagcompound)
/* 207:    */   {
/* 208:179 */     super.a(nbttagcompound);
/* 209:    */     
/* 210:181 */     by items = nbttagcompound.m("Items");
/* 211:182 */     this.contents = new ur[k_()];
/* 212:183 */     for (int i = 0; i < items.c(); i++)
/* 213:    */     {
/* 214:184 */       bq item = (bq)items.b(i);
/* 215:    */       
/* 216:186 */       int j = item.c("Slot") & 0xFF;
/* 217:187 */       if ((j >= 0) && (j < this.contents.length)) {
/* 218:188 */         this.contents[j] = ur.a(item);
/* 219:    */       }
/* 220:    */     }
/* 221:    */   }
/* 222:    */   
/* 223:    */   public void b(bq nbttagcompound)
/* 224:    */   {
/* 225:194 */     super.b(nbttagcompound);
/* 226:    */     
/* 227:196 */     by items = new by();
/* 228:197 */     for (int i = 0; i < this.contents.length; i++) {
/* 229:198 */       if (this.contents[i] != null)
/* 230:    */       {
/* 231:199 */         bq item = new bq();
/* 232:200 */         item.a("Slot", (byte)i);
/* 233:201 */         this.contents[i].b(item);
/* 234:202 */         items.a(item);
/* 235:    */       }
/* 236:    */     }
/* 237:205 */     nbttagcompound.a("Items", items);
/* 238:    */   }
/* 239:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileBufferChest
 * JD-Core Version:    0.7.0.1
 */