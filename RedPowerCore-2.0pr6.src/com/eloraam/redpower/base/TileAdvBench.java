/*   1:    */ package com.eloraam.redpower.base;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerBase;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import la;
/*   8:    */ import md;
/*   9:    */ import net.minecraftforge.common.ForgeDirection;
/*  10:    */ import net.minecraftforge.common.ISidedInventory;
/*  11:    */ import qx;
/*  12:    */ import ur;
/*  13:    */ import yc;
/*  14:    */ 
/*  15:    */ public class TileAdvBench
/*  16:    */   extends TileAppliance
/*  17:    */   implements la, ISidedInventory
/*  18:    */ {
/*  19:    */   private ur[] contents;
/*  20:    */   
/*  21:    */   public TileAdvBench()
/*  22:    */   {
/*  23: 26 */     this.contents = new ur[28];
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getExtendedID()
/*  27:    */   {
/*  28: 32 */     return 3;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public boolean canUpdate()
/*  32:    */   {
/*  33: 37 */     return false;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public boolean onBlockActivated(qx player)
/*  37:    */   {
/*  38: 43 */     if (player.ah()) {
/*  39: 43 */       return false;
/*  40:    */     }
/*  41: 44 */     if (CoreLib.isClient(this.k)) {
/*  42: 45 */       return true;
/*  43:    */     }
/*  44: 46 */     player.openGui(RedPowerBase.instance, 2, this.k, this.l, this.m, this.n);
/*  45:    */     
/*  46: 48 */     return true;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void onBlockPlaced(ur ist, int side, md ent)
/*  50:    */   {
/*  51: 53 */     this.Rotation = ((int)Math.floor(ent.z * 4.0F / 360.0F + 0.5D) & 0x3);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void onBlockRemoval()
/*  55:    */   {
/*  56: 57 */     for (int i = 0; i < 27; i++)
/*  57:    */     {
/*  58: 58 */       ur ist = this.contents[i];
/*  59: 59 */       if ((ist != null) && (ist.a > 0)) {
/*  60: 60 */         CoreLib.dropItem(this.k, this.l, this.m, this.n, ist);
/*  61:    */       }
/*  62:    */     }
/*  63:    */   }
/*  64:    */   
/*  65:    */   public int getStartInventorySide(ForgeDirection side)
/*  66:    */   {
/*  67: 69 */     return 10;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public int getSizeInventorySide(ForgeDirection side)
/*  71:    */   {
/*  72: 73 */     return 18;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public int k_()
/*  76:    */   {
/*  77: 79 */     return 28;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public ur a(int i)
/*  81:    */   {
/*  82: 83 */     return this.contents[i];
/*  83:    */   }
/*  84:    */   
/*  85:    */   public ur a(int i, int j)
/*  86:    */   {
/*  87: 88 */     if (this.contents[i] == null) {
/*  88: 88 */       return null;
/*  89:    */     }
/*  90: 90 */     if (this.contents[i].a <= j)
/*  91:    */     {
/*  92: 91 */       ur tr = this.contents[i];
/*  93: 92 */       this.contents[i] = null;
/*  94: 93 */       d();
/*  95: 94 */       return tr;
/*  96:    */     }
/*  97: 96 */     ur tr = this.contents[i].a(j);
/*  98: 97 */     if (this.contents[i].a == 0) {
/*  99: 98 */       this.contents[i] = null;
/* 100:    */     }
/* 101: 99 */     d();
/* 102:100 */     return tr;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public ur a_(int i)
/* 106:    */   {
/* 107:104 */     if (this.contents[i] == null) {
/* 108:104 */       return null;
/* 109:    */     }
/* 110:105 */     ur ist = this.contents[i];
/* 111:106 */     this.contents[i] = null;
/* 112:107 */     return ist;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void a(int i, ur ist)
/* 116:    */   {
/* 117:111 */     this.contents[i] = ist;
/* 118:112 */     if ((ist != null) && (ist.a > c())) {
/* 119:113 */       ist.a = c();
/* 120:    */     }
/* 121:114 */     d();
/* 122:    */   }
/* 123:    */   
/* 124:    */   public String b()
/* 125:    */   {
/* 126:118 */     return "Project Table";
/* 127:    */   }
/* 128:    */   
/* 129:    */   public int c()
/* 130:    */   {
/* 131:122 */     return 64;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public boolean a_(qx player)
/* 135:    */   {
/* 136:126 */     if (this.k.q(this.l, this.m, this.n) != this) {
/* 137:127 */       return false;
/* 138:    */     }
/* 139:128 */     return player.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void f() {}
/* 143:    */   
/* 144:    */   public void l_() {}
/* 145:    */   
/* 146:    */   public void a(bq nbttagcompound)
/* 147:    */   {
/* 148:139 */     super.a(nbttagcompound);
/* 149:    */     
/* 150:141 */     by items = nbttagcompound.m("Items");
/* 151:142 */     this.contents = new ur[k_()];
/* 152:143 */     for (int i = 0; i < items.c(); i++)
/* 153:    */     {
/* 154:144 */       bq item = (bq)items.b(i);
/* 155:    */       
/* 156:146 */       int j = item.c("Slot") & 0xFF;
/* 157:147 */       if ((j >= 0) && (j < this.contents.length)) {
/* 158:148 */         this.contents[j] = ur.a(item);
/* 159:    */       }
/* 160:    */     }
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void b(bq nbttagcompound)
/* 164:    */   {
/* 165:154 */     super.b(nbttagcompound);
/* 166:    */     
/* 167:156 */     by items = new by();
/* 168:157 */     for (int i = 0; i < this.contents.length; i++) {
/* 169:158 */       if (this.contents[i] != null)
/* 170:    */       {
/* 171:159 */         bq item = new bq();
/* 172:160 */         item.a("Slot", (byte)i);
/* 173:161 */         this.contents[i].b(item);
/* 174:162 */         items.a(item);
/* 175:    */       }
/* 176:    */     }
/* 177:165 */     nbttagcompound.a("Items", items);
/* 178:    */   }
/* 179:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.TileAdvBench
 * JD-Core Version:    0.7.0.1
 */