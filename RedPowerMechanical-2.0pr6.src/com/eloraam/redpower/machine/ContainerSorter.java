/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.core.BluePowerEndpoint;
/*   4:    */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*   5:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*   6:    */ import java.io.IOException;
/*   7:    */ import java.util.List;
/*   8:    */ import la;
/*   9:    */ import qx;
/*  10:    */ import rq;
/*  11:    */ import rw;
/*  12:    */ import sr;
/*  13:    */ import ur;
/*  14:    */ 
/*  15:    */ public class ContainerSorter
/*  16:    */   extends rq
/*  17:    */   implements IHandleGuiEvent
/*  18:    */ {
/*  19:    */   public ContainerSorter(la inv, TileSorter tf)
/*  20:    */   {
/*  21: 17 */     this.tileSorter = tf;
/*  22: 19 */     for (int i = 0; i < 5; i++) {
/*  23: 19 */       for (int j = 0; j < 8; j++) {
/*  24: 20 */         a(new sr(tf, j + i * 8, 26 + 18 * j, 18 + 18 * i));
/*  25:    */       }
/*  26:    */     }
/*  27: 22 */     for (i = 0; i < 3; i++) {
/*  28: 22 */       for (int j = 0; j < 9; j++) {
/*  29: 23 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
/*  30:    */       }
/*  31:    */     }
/*  32: 25 */     for (i = 0; i < 9; i++) {
/*  33: 26 */       a(new sr(inv, i, 8 + i * 18, 198));
/*  34:    */     }
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean a(qx player)
/*  38:    */   {
/*  39: 31 */     return this.tileSorter.a_(player);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public ur b(qx player, int i)
/*  43:    */   {
/*  44: 36 */     ur itemstack = null;
/*  45: 37 */     sr slot = (sr)this.c.get(i);
/*  46: 38 */     if ((slot != null) && (slot.d()))
/*  47:    */     {
/*  48: 39 */       ur itemstack1 = slot.c();
/*  49: 40 */       itemstack = itemstack1.l();
/*  50: 41 */       if (i < 40)
/*  51:    */       {
/*  52: 42 */         if (!a(itemstack1, 40, 76, true)) {
/*  53: 43 */           return null;
/*  54:    */         }
/*  55:    */       }
/*  56: 45 */       else if (!a(itemstack1, 0, 40, false)) {
/*  57: 46 */         return null;
/*  58:    */       }
/*  59: 48 */       if (itemstack1.a == 0) {
/*  60: 49 */         slot.c(null);
/*  61:    */       } else {
/*  62: 51 */         slot.e();
/*  63:    */       }
/*  64: 53 */       if (itemstack1.a != itemstack.a) {
/*  65: 54 */         slot.a(player, itemstack1);
/*  66:    */       } else {
/*  67: 56 */         return null;
/*  68:    */       }
/*  69:    */     }
/*  70: 59 */     return itemstack;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void b()
/*  74:    */   {
/*  75: 63 */     super.b();
/*  76: 65 */     for (int i = 0; i < this.e.size(); i++)
/*  77:    */     {
/*  78: 66 */       rw ic = (rw)this.e.get(i);
/*  79: 68 */       for (int j = 0; j < 8; j++) {
/*  80: 69 */         if (this.colors[j] != this.tileSorter.colors[j]) {
/*  81: 70 */           ic.a(this, j, this.tileSorter.colors[j]);
/*  82:    */         }
/*  83:    */       }
/*  84: 73 */       if (this.column != this.tileSorter.column) {
/*  85: 74 */         ic.a(this, 8, this.tileSorter.column);
/*  86:    */       }
/*  87: 76 */       if (this.charge != this.tileSorter.cond.Charge) {
/*  88: 77 */         ic.a(this, 9, this.tileSorter.cond.Charge);
/*  89:    */       }
/*  90: 79 */       if (this.flow != this.tileSorter.cond.Flow) {
/*  91: 80 */         ic.a(this, 10, this.tileSorter.cond.Flow);
/*  92:    */       }
/*  93: 82 */       if (this.mode != this.tileSorter.mode) {
/*  94: 83 */         ic.a(this, 11, this.tileSorter.mode);
/*  95:    */       }
/*  96: 85 */       if (this.defcolor != this.tileSorter.defcolor) {
/*  97: 86 */         ic.a(this, 12, this.tileSorter.defcolor);
/*  98:    */       }
/*  99: 88 */       if (this.automode != this.tileSorter.automode) {
/* 100: 89 */         ic.a(this, 13, this.tileSorter.automode);
/* 101:    */       }
/* 102:    */     }
/* 103: 92 */     for (int j = 0; j < 8; j++) {
/* 104: 93 */       this.colors[j] = this.tileSorter.colors[j];
/* 105:    */     }
/* 106: 94 */     this.column = this.tileSorter.column;
/* 107: 95 */     this.charge = this.tileSorter.cond.Charge;
/* 108: 96 */     this.flow = this.tileSorter.cond.Flow;
/* 109: 97 */     this.mode = this.tileSorter.mode;
/* 110: 98 */     this.defcolor = this.tileSorter.defcolor;
/* 111: 99 */     this.automode = this.tileSorter.automode;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void func_20112_a(int i, int j)
/* 115:    */   {
/* 116:104 */     b(i, j);
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void b(int i, int j)
/* 120:    */   {
/* 121:108 */     if (i < 8) {
/* 122:108 */       this.tileSorter.colors[i] = ((byte)j);
/* 123:    */     }
/* 124:109 */     switch (i)
/* 125:    */     {
/* 126:    */     case 8: 
/* 127:110 */       this.tileSorter.column = ((byte)j); break;
/* 128:    */     case 9: 
/* 129:111 */       this.tileSorter.cond.Charge = j; break;
/* 130:    */     case 10: 
/* 131:112 */       this.tileSorter.cond.Flow = j; break;
/* 132:    */     case 11: 
/* 133:113 */       this.tileSorter.mode = ((byte)j); break;
/* 134:    */     case 12: 
/* 135:114 */       this.tileSorter.defcolor = ((byte)j); break;
/* 136:    */     case 13: 
/* 137:115 */       this.tileSorter.automode = ((byte)j);
/* 138:    */     }
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 142:    */   {
/* 143:    */     try
/* 144:    */     {
/* 145:121 */       switch (packet.eventId)
/* 146:    */       {
/* 147:    */       case 1: 
/* 148:123 */         this.tileSorter.mode = ((byte)packet.getByte());
/* 149:124 */         this.tileSorter.dirtyBlock();
/* 150:125 */         break;
/* 151:    */       case 2: 
/* 152:127 */         int i = (byte)packet.getByte();
/* 153:128 */         if ((i >= 0) && (i <= 8))
/* 154:    */         {
/* 155:129 */           this.tileSorter.colors[i] = ((byte)packet.getByte());
/* 156:130 */           this.tileSorter.dirtyBlock();
/* 157:    */         }
/* 158:131 */         break;
/* 159:    */       case 3: 
/* 160:133 */         this.tileSorter.defcolor = ((byte)packet.getByte());
/* 161:134 */         this.tileSorter.dirtyBlock();
/* 162:135 */         break;
/* 163:    */       case 4: 
/* 164:137 */         this.tileSorter.automode = ((byte)packet.getByte());
/* 165:138 */         this.tileSorter.pulses = 0;
/* 166:139 */         this.tileSorter.dirtyBlock();
/* 167:    */       }
/* 168:    */     }
/* 169:    */     catch (IOException e) {}
/* 170:    */   }
/* 171:    */   
/* 172:144 */   public byte[] colors = new byte[8];
/* 173:    */   public int column;
/* 174:146 */   public int charge = 0;
/* 175:146 */   public int flow = 0;
/* 176:147 */   public int mode = 0;
/* 177:147 */   public int defcolor = 0;
/* 178:147 */   public int automode = 0;
/* 179:    */   private TileSorter tileSorter;
/* 180:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerSorter
 * JD-Core Version:    0.7.0.1
 */