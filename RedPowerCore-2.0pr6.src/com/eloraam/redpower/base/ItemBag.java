/*   1:    */ package com.eloraam.redpower.base;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import by;
/*   5:    */ import com.eloraam.redpower.RedPowerBase;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import la;
/*   8:    */ import qx;
/*   9:    */ import tj;
/*  10:    */ import up;
/*  11:    */ import ur;
/*  12:    */ import yc;
/*  13:    */ 
/*  14:    */ public class ItemBag
/*  15:    */   extends up
/*  16:    */ {
/*  17:    */   public ItemBag(int i)
/*  18:    */   {
/*  19: 16 */     super(i);
/*  20: 17 */     d(1);
/*  21: 18 */     a(true);
/*  22: 19 */     setTextureFile("/eloraam/base/items1.png");
/*  23: 20 */     b("rpBag");
/*  24: 21 */     a(tj.f);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public static class InventoryBag
/*  28:    */     implements la
/*  29:    */   {
/*  30:    */     ur bagitem;
/*  31:    */     ur[] items;
/*  32:    */     
/*  33:    */     InventoryBag(ur ist)
/*  34:    */     {
/*  35: 29 */       this.bagitem = ist;
/*  36: 30 */       unpackInventory();
/*  37:    */     }
/*  38:    */     
/*  39:    */     void unpackInventory()
/*  40:    */     {
/*  41: 34 */       this.items = new ur[27];
/*  42: 35 */       if (this.bagitem.d == null) {
/*  43: 36 */         return;
/*  44:    */       }
/*  45: 37 */       by list = this.bagitem.d.m("contents");
/*  46: 39 */       for (int i = 0; i < list.c(); i++)
/*  47:    */       {
/*  48: 40 */         bq item = (bq)list.b(i);
/*  49:    */         
/*  50: 42 */         int slt = item.c("Slot");
/*  51: 43 */         if (slt < 27) {
/*  52: 44 */           this.items[slt] = ur.a(item);
/*  53:    */         }
/*  54:    */       }
/*  55:    */     }
/*  56:    */     
/*  57:    */     void packInventory()
/*  58:    */     {
/*  59: 49 */       if (this.bagitem.d == null) {
/*  60: 50 */         this.bagitem.d(new bq());
/*  61:    */       }
/*  62: 53 */       by contents = new by();
/*  63: 54 */       for (int i = 0; i < 27; i++) {
/*  64: 55 */         if (this.items[i] != null)
/*  65:    */         {
/*  66: 56 */           bq cpd = new bq();
/*  67: 57 */           this.items[i].b(cpd);
/*  68: 58 */           cpd.a("Slot", (byte)i);
/*  69: 59 */           contents.a(cpd);
/*  70:    */         }
/*  71:    */       }
/*  72: 61 */       this.bagitem.d.a("contents", contents);
/*  73:    */     }
/*  74:    */     
/*  75:    */     public int k_()
/*  76:    */     {
/*  77: 64 */       return 27;
/*  78:    */     }
/*  79:    */     
/*  80:    */     public ur a(int slot)
/*  81:    */     {
/*  82: 67 */       return this.items[slot];
/*  83:    */     }
/*  84:    */     
/*  85:    */     public ur a(int slot, int num)
/*  86:    */     {
/*  87: 72 */       if (this.items[slot] == null) {
/*  88: 72 */         return null;
/*  89:    */       }
/*  90: 73 */       if (this.items[slot].a <= num)
/*  91:    */       {
/*  92: 74 */         ur tr = this.items[slot];this.items[slot] = null;
/*  93: 75 */         d();
/*  94: 76 */         return tr;
/*  95:    */       }
/*  96: 78 */       ur tr = this.items[slot].a(num);
/*  97: 79 */       if (this.items[slot].a == 0) {
/*  98: 80 */         this.items[slot] = null;
/*  99:    */       }
/* 100: 81 */       d();
/* 101: 82 */       return tr;
/* 102:    */     }
/* 103:    */     
/* 104:    */     public ur a_(int slot)
/* 105:    */     {
/* 106: 86 */       if (this.items[slot] == null) {
/* 107: 86 */         return null;
/* 108:    */       }
/* 109: 87 */       ur tr = this.items[slot];
/* 110: 88 */       this.items[slot] = null;
/* 111: 89 */       return tr;
/* 112:    */     }
/* 113:    */     
/* 114:    */     public void a(int slot, ur ist)
/* 115:    */     {
/* 116: 93 */       this.items[slot] = ist;
/* 117: 94 */       if ((ist != null) && (ist.a > c())) {
/* 118: 95 */         ist.a = c();
/* 119:    */       }
/* 120: 96 */       d();
/* 121:    */     }
/* 122:    */     
/* 123:    */     public String b()
/* 124:    */     {
/* 125: 98 */       return "Canvas Bag";
/* 126:    */     }
/* 127:    */     
/* 128:    */     public int c()
/* 129:    */     {
/* 130: 99 */       return 64;
/* 131:    */     }
/* 132:    */     
/* 133:    */     public void d()
/* 134:    */     {
/* 135:101 */       packInventory();
/* 136:    */     }
/* 137:    */     
/* 138:    */     public boolean a_(qx pl)
/* 139:    */     {
/* 140:103 */       return true;
/* 141:    */     }
/* 142:    */     
/* 143:    */     public void l_() {}
/* 144:    */     
/* 145:    */     public void f() {}
/* 146:    */   }
/* 147:    */   
/* 148:    */   public static la getBagInventory(ur ist)
/* 149:    */   {
/* 150:110 */     if (!(ist.b() instanceof ItemBag)) {
/* 151:111 */       return null;
/* 152:    */     }
/* 153:112 */     return new InventoryBag(ist);
/* 154:    */   }
/* 155:    */   
/* 156:    */   public int c_(ur par1ItemStack)
/* 157:    */   {
/* 158:119 */     return 1;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public int b(int i)
/* 162:    */   {
/* 163:124 */     return 192 + i;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public ur a(ur ist, yc world, qx player)
/* 167:    */   {
/* 168:130 */     if (CoreLib.isClient(world)) {
/* 169:130 */       return ist;
/* 170:    */     }
/* 171:131 */     if (player.ah()) {
/* 172:131 */       return ist;
/* 173:    */     }
/* 174:133 */     player.openGui(RedPowerBase.instance, 4, world, 0, 0, 0);
/* 175:134 */     return ist;
/* 176:    */   }
/* 177:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ItemBag
 * JD-Core Version:    0.7.0.1
 */