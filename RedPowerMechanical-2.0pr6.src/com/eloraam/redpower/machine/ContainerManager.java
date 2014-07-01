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
/*  15:    */ public class ContainerManager
/*  16:    */   extends rq
/*  17:    */   implements IHandleGuiEvent
/*  18:    */ {
/*  19:    */   public ContainerManager(la inv, TileManager tf)
/*  20:    */   {
/*  21: 17 */     this.tileManager = tf;
/*  22: 19 */     for (int i = 0; i < 4; i++) {
/*  23: 19 */       for (int j = 0; j < 6; j++) {
/*  24: 20 */         a(new sr(tf, j + i * 6, 44 + 18 * j, 18 + 18 * i));
/*  25:    */       }
/*  26:    */     }
/*  27: 22 */     for (i = 0; i < 3; i++) {
/*  28: 22 */       for (int j = 0; j < 9; j++) {
/*  29: 23 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 104 + i * 18));
/*  30:    */       }
/*  31:    */     }
/*  32: 25 */     for (i = 0; i < 9; i++) {
/*  33: 26 */       a(new sr(inv, i, 8 + i * 18, 162));
/*  34:    */     }
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean a(qx player)
/*  38:    */   {
/*  39: 31 */     return this.tileManager.a_(player);
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
/*  50: 41 */       if (i < 24)
/*  51:    */       {
/*  52: 42 */         if (!a(itemstack1, 24, 60, true)) {
/*  53: 43 */           return null;
/*  54:    */         }
/*  55:    */       }
/*  56: 45 */       else if (!a(itemstack1, 0, 24, false)) {
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
/*  79: 67 */       if (this.charge != this.tileManager.cond.Charge) {
/*  80: 68 */         ic.a(this, 0, this.tileManager.cond.Charge);
/*  81:    */       }
/*  82: 70 */       if (this.flow != this.tileManager.cond.Flow) {
/*  83: 71 */         ic.a(this, 1, this.tileManager.cond.Flow);
/*  84:    */       }
/*  85: 73 */       if (this.mode != this.tileManager.mode) {
/*  86: 74 */         ic.a(this, 2, this.tileManager.mode);
/*  87:    */       }
/*  88: 76 */       if (this.color != this.tileManager.color) {
/*  89: 77 */         ic.a(this, 3, this.tileManager.color);
/*  90:    */       }
/*  91: 79 */       if (this.priority != this.tileManager.priority) {
/*  92: 80 */         ic.a(this, 4, this.tileManager.priority);
/*  93:    */       }
/*  94:    */     }
/*  95: 83 */     this.charge = this.tileManager.cond.Charge;
/*  96: 84 */     this.flow = this.tileManager.cond.Flow;
/*  97: 85 */     this.mode = this.tileManager.mode;
/*  98: 86 */     this.color = this.tileManager.color;
/*  99: 87 */     this.priority = this.tileManager.priority;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void b(int i, int j)
/* 103:    */   {
/* 104: 91 */     switch (i)
/* 105:    */     {
/* 106:    */     case 0: 
/* 107: 92 */       this.tileManager.cond.Charge = j; break;
/* 108:    */     case 1: 
/* 109: 93 */       this.tileManager.cond.Flow = j; break;
/* 110:    */     case 2: 
/* 111: 94 */       this.tileManager.mode = ((byte)j); break;
/* 112:    */     case 3: 
/* 113: 95 */       this.tileManager.color = ((byte)j); break;
/* 114:    */     case 4: 
/* 115: 96 */       this.tileManager.priority = j;
/* 116:    */     }
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 120:    */   {
/* 121:    */     try
/* 122:    */     {
/* 123:102 */       switch (packet.eventId)
/* 124:    */       {
/* 125:    */       case 1: 
/* 126:104 */         this.tileManager.mode = ((byte)packet.getByte());
/* 127:105 */         this.tileManager.dirtyBlock();
/* 128:106 */         break;
/* 129:    */       case 2: 
/* 130:108 */         this.tileManager.color = ((byte)packet.getByte());
/* 131:109 */         this.tileManager.dirtyBlock();
/* 132:110 */         break;
/* 133:    */       case 3: 
/* 134:112 */         this.tileManager.priority = ((int)packet.getUVLC());
/* 135:113 */         this.tileManager.dirtyBlock();
/* 136:    */       }
/* 137:    */     }
/* 138:    */     catch (IOException e) {}
/* 139:    */   }
/* 140:    */   
/* 141:118 */   public int flow = 0;
/* 142:118 */   public int charge = 0;
/* 143:119 */   public int mode = 0;
/* 144:119 */   public int priority = 0;
/* 145:119 */   public int color = 0;
/* 146:    */   private TileManager tileManager;
/* 147:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerManager
 * JD-Core Version:    0.7.0.1
 */