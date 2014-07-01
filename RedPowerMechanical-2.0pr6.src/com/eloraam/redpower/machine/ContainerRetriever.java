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
/*  15:    */ public class ContainerRetriever
/*  16:    */   extends rq
/*  17:    */   implements IHandleGuiEvent
/*  18:    */ {
/*  19:    */   public ContainerRetriever(la inv, TileRetriever tr)
/*  20:    */   {
/*  21: 17 */     this.tileRetriever = tr;
/*  22: 19 */     for (int i = 0; i < 3; i++) {
/*  23: 19 */       for (int j = 0; j < 3; j++) {
/*  24: 20 */         a(new sr(tr, j + i * 3, 62 + j * 18, 17 + i * 18));
/*  25:    */       }
/*  26:    */     }
/*  27: 22 */     for (i = 0; i < 3; i++) {
/*  28: 22 */       for (int j = 0; j < 9; j++) {
/*  29: 23 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*  30:    */       }
/*  31:    */     }
/*  32: 25 */     for (i = 0; i < 9; i++) {
/*  33: 26 */       a(new sr(inv, i, 8 + i * 18, 142));
/*  34:    */     }
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean a(qx player)
/*  38:    */   {
/*  39: 31 */     return this.tileRetriever.a_(player);
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
/*  50: 41 */       if (i < 9)
/*  51:    */       {
/*  52: 42 */         if (!a(itemstack1, 9, 45, true)) {
/*  53: 43 */           return null;
/*  54:    */         }
/*  55:    */       }
/*  56: 45 */       else if (!a(itemstack1, 0, 9, false)) {
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
/*  79: 68 */       if (this.charge != this.tileRetriever.cond.Charge) {
/*  80: 69 */         ic.a(this, 0, this.tileRetriever.cond.Charge);
/*  81:    */       }
/*  82: 71 */       if (this.flow != this.tileRetriever.cond.Flow) {
/*  83: 72 */         ic.a(this, 1, this.tileRetriever.cond.Flow);
/*  84:    */       }
/*  85: 74 */       if (this.color != this.tileRetriever.color) {
/*  86: 75 */         ic.a(this, 2, this.tileRetriever.color);
/*  87:    */       }
/*  88: 77 */       if (this.select != this.tileRetriever.select) {
/*  89: 78 */         ic.a(this, 3, this.tileRetriever.select);
/*  90:    */       }
/*  91: 80 */       if (this.mode != this.tileRetriever.mode) {
/*  92: 81 */         ic.a(this, 4, this.tileRetriever.mode);
/*  93:    */       }
/*  94:    */     }
/*  95: 84 */     this.flow = this.tileRetriever.cond.Flow;
/*  96: 85 */     this.charge = this.tileRetriever.cond.Charge;
/*  97: 86 */     this.color = this.tileRetriever.color;
/*  98: 87 */     this.select = this.tileRetriever.select;
/*  99: 88 */     this.mode = this.tileRetriever.mode;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void b(int i, int j)
/* 103:    */   {
/* 104: 92 */     switch (i)
/* 105:    */     {
/* 106:    */     case 0: 
/* 107: 93 */       this.tileRetriever.cond.Charge = j; break;
/* 108:    */     case 1: 
/* 109: 94 */       this.tileRetriever.cond.Flow = j; break;
/* 110:    */     case 2: 
/* 111: 95 */       this.tileRetriever.color = ((byte)j); break;
/* 112:    */     case 3: 
/* 113: 96 */       this.tileRetriever.select = ((byte)j); break;
/* 114:    */     case 4: 
/* 115: 97 */       this.tileRetriever.mode = ((byte)j);
/* 116:    */     }
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 120:    */   {
/* 121:    */     try
/* 122:    */     {
/* 123:103 */       switch (packet.eventId)
/* 124:    */       {
/* 125:    */       case 1: 
/* 126:105 */         this.tileRetriever.color = ((byte)packet.getByte());
/* 127:106 */         this.tileRetriever.dirtyBlock();
/* 128:107 */         break;
/* 129:    */       case 2: 
/* 130:109 */         this.tileRetriever.mode = ((byte)packet.getByte());
/* 131:110 */         this.tileRetriever.dirtyBlock();
/* 132:    */       }
/* 133:    */     }
/* 134:    */     catch (IOException e) {}
/* 135:    */   }
/* 136:    */   
/* 137:116 */   public int charge = 0;
/* 138:116 */   public int flow = 0;
/* 139:116 */   public int color = 0;
/* 140:117 */   public int select = 0;
/* 141:117 */   public int mode = 0;
/* 142:    */   private TileRetriever tileRetriever;
/* 143:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerRetriever
 * JD-Core Version:    0.7.0.1
 */