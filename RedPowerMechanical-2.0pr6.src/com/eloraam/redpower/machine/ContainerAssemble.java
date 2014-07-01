/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*   4:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.util.List;
/*   7:    */ import la;
/*   8:    */ import qx;
/*   9:    */ import rq;
/*  10:    */ import rw;
/*  11:    */ import sr;
/*  12:    */ import ur;
/*  13:    */ 
/*  14:    */ public class ContainerAssemble
/*  15:    */   extends rq
/*  16:    */   implements IHandleGuiEvent
/*  17:    */ {
/*  18:    */   public ContainerAssemble(la inv, TileAssemble tf)
/*  19:    */   {
/*  20: 17 */     this.tileAssemble = tf;
/*  21: 19 */     for (int i = 0; i < 2; i++) {
/*  22: 19 */       for (int j = 0; j < 8; j++) {
/*  23: 20 */         a(new sr(tf, j + i * 8, 8 + j * 18, 18 + i * 18));
/*  24:    */       }
/*  25:    */     }
/*  26: 22 */     for (i = 0; i < 2; i++) {
/*  27: 22 */       for (int j = 0; j < 9; j++) {
/*  28: 23 */         a(new sr(tf, j + i * 9 + 16, 8 + j * 18, 63 + i * 18));
/*  29:    */       }
/*  30:    */     }
/*  31: 25 */     for (i = 0; i < 3; i++) {
/*  32: 25 */       for (int j = 0; j < 9; j++) {
/*  33: 26 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 113 + i * 18));
/*  34:    */       }
/*  35:    */     }
/*  36: 28 */     for (i = 0; i < 9; i++) {
/*  37: 29 */       a(new sr(inv, i, 8 + i * 18, 171));
/*  38:    */     }
/*  39:    */   }
/*  40:    */   
/*  41:    */   public boolean a(qx player)
/*  42:    */   {
/*  43: 34 */     return this.tileAssemble.a_(player);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public ur b(qx player, int i)
/*  47:    */   {
/*  48: 39 */     ur itemstack = null;
/*  49: 40 */     sr slot = (sr)this.c.get(i);
/*  50: 41 */     if ((slot != null) && (slot.d()))
/*  51:    */     {
/*  52: 42 */       ur itemstack1 = slot.c();
/*  53: 43 */       itemstack = itemstack1.l();
/*  54: 44 */       if (i < 34)
/*  55:    */       {
/*  56: 45 */         if (!a(itemstack1, 34, 70, true)) {
/*  57: 46 */           return null;
/*  58:    */         }
/*  59:    */       }
/*  60: 48 */       else if (!a(itemstack1, 16, 34, false)) {
/*  61: 49 */         return null;
/*  62:    */       }
/*  63: 51 */       if (itemstack1.a == 0) {
/*  64: 52 */         slot.c(null);
/*  65:    */       } else {
/*  66: 54 */         slot.e();
/*  67:    */       }
/*  68: 56 */       if (itemstack1.a != itemstack.a) {
/*  69: 57 */         slot.a(player, itemstack1);
/*  70:    */       } else {
/*  71: 59 */         return null;
/*  72:    */       }
/*  73:    */     }
/*  74: 62 */     return itemstack;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void b()
/*  78:    */   {
/*  79: 66 */     super.b();
/*  80: 68 */     for (int i = 0; i < this.e.size(); i++)
/*  81:    */     {
/*  82: 69 */       rw ic = (rw)this.e.get(i);
/*  83: 70 */       if (this.mode != this.tileAssemble.mode) {
/*  84: 71 */         ic.a(this, 0, this.tileAssemble.mode);
/*  85:    */       }
/*  86: 73 */       if (this.select != this.tileAssemble.select) {
/*  87: 74 */         ic.a(this, 1, this.tileAssemble.select);
/*  88:    */       }
/*  89: 76 */       if (this.skipSlots != this.tileAssemble.skipSlots) {
/*  90: 77 */         ic.a(this, 2, this.tileAssemble.skipSlots);
/*  91:    */       }
/*  92:    */     }
/*  93: 80 */     this.mode = this.tileAssemble.mode;
/*  94: 81 */     this.select = this.tileAssemble.select;
/*  95: 82 */     this.skipSlots = this.tileAssemble.skipSlots;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void b(int i, int j)
/*  99:    */   {
/* 100: 86 */     switch (i)
/* 101:    */     {
/* 102:    */     case 0: 
/* 103: 87 */       this.tileAssemble.mode = ((byte)j); break;
/* 104:    */     case 1: 
/* 105: 88 */       this.tileAssemble.select = ((byte)j); break;
/* 106:    */     case 2: 
/* 107: 89 */       this.tileAssemble.skipSlots = (j & 0xFFFF);
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 112:    */   {
/* 113:    */     try
/* 114:    */     {
/* 115: 96 */       switch (packet.eventId)
/* 116:    */       {
/* 117:    */       case 1: 
/* 118: 98 */         this.tileAssemble.mode = ((byte)packet.getByte());
/* 119: 99 */         this.tileAssemble.updateBlockChange();
/* 120:100 */         break;
/* 121:    */       case 2: 
/* 122:102 */         this.tileAssemble.skipSlots = ((int)packet.getUVLC());
/* 123:103 */         this.tileAssemble.dirtyBlock();
/* 124:    */       }
/* 125:    */     }
/* 126:    */     catch (IOException e) {}
/* 127:    */   }
/* 128:    */   
/* 129:110 */   public int skipSlots = 0;
/* 130:110 */   public int select = 0;
/* 131:110 */   public int mode = 0;
/* 132:    */   private TileAssemble tileAssemble;
/* 133:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerAssemble
 * JD-Core Version:    0.7.0.1
 */