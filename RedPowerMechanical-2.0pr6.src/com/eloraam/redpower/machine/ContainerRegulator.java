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
/*  14:    */ public class ContainerRegulator
/*  15:    */   extends rq
/*  16:    */   implements IHandleGuiEvent
/*  17:    */ {
/*  18:    */   public ContainerRegulator(la inv, TileRegulator tf)
/*  19:    */   {
/*  20: 18 */     this.tileRegulator = tf;
/*  21: 20 */     for (int k = 0; k < 3; k++) {
/*  22: 20 */       for (int i = 0; i < 3; i++) {
/*  23: 20 */         for (int j = 0; j < 3; j++) {
/*  24: 21 */           a(new sr(tf, j + i * 3 + k * 9, 8 + j * 18 + k * 72, 18 + i * 18));
/*  25:    */         }
/*  26:    */       }
/*  27:    */     }
/*  28: 24 */     for (int i = 0; i < 3; i++) {
/*  29: 24 */       for (int j = 0; j < 9; j++) {
/*  30: 25 */         a(new sr(inv, j + i * 9 + 9, 26 + j * 18, 86 + i * 18));
/*  31:    */       }
/*  32:    */     }
/*  33: 27 */     for (i = 0; i < 9; i++) {
/*  34: 28 */       a(new sr(inv, i, 26 + i * 18, 144));
/*  35:    */     }
/*  36:    */   }
/*  37:    */   
/*  38:    */   public boolean a(qx player)
/*  39:    */   {
/*  40: 33 */     return this.tileRegulator.a_(player);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public ur b(qx player, int i)
/*  44:    */   {
/*  45: 38 */     ur itemstack = null;
/*  46: 39 */     sr slot = (sr)this.c.get(i);
/*  47: 40 */     if ((slot != null) && (slot.d()))
/*  48:    */     {
/*  49: 41 */       ur itemstack1 = slot.c();
/*  50: 42 */       itemstack = itemstack1.l();
/*  51: 43 */       if (i < 27)
/*  52:    */       {
/*  53: 44 */         if (!a(itemstack1, 27, 63, true)) {
/*  54: 45 */           return null;
/*  55:    */         }
/*  56:    */       }
/*  57: 47 */       else if (!a(itemstack1, 9, 18, false)) {
/*  58: 48 */         return null;
/*  59:    */       }
/*  60: 50 */       if (itemstack1.a == 0) {
/*  61: 51 */         slot.c(null);
/*  62:    */       } else {
/*  63: 53 */         slot.e();
/*  64:    */       }
/*  65: 55 */       if (itemstack1.a != itemstack.a) {
/*  66: 56 */         slot.a(player, itemstack1);
/*  67:    */       } else {
/*  68: 58 */         return null;
/*  69:    */       }
/*  70:    */     }
/*  71: 61 */     return itemstack;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void b()
/*  75:    */   {
/*  76: 65 */     super.b();
/*  77: 67 */     for (int i = 0; i < this.e.size(); i++)
/*  78:    */     {
/*  79: 68 */       rw ic = (rw)this.e.get(i);
/*  80: 69 */       if (this.color != this.tileRegulator.color) {
/*  81: 70 */         ic.a(this, 0, this.tileRegulator.color);
/*  82:    */       }
/*  83: 72 */       if (this.mode != this.tileRegulator.mode) {
/*  84: 73 */         ic.a(this, 1, this.tileRegulator.mode);
/*  85:    */       }
/*  86:    */     }
/*  87: 76 */     this.color = this.tileRegulator.color;
/*  88: 77 */     this.mode = this.tileRegulator.mode;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void b(int i, int j)
/*  92:    */   {
/*  93: 81 */     switch (i)
/*  94:    */     {
/*  95:    */     case 0: 
/*  96: 82 */       this.tileRegulator.color = ((byte)j); break;
/*  97:    */     case 1: 
/*  98: 83 */       this.tileRegulator.mode = ((byte)j);
/*  99:    */     }
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 103:    */   {
/* 104:    */     try
/* 105:    */     {
/* 106: 90 */       switch (packet.eventId)
/* 107:    */       {
/* 108:    */       case 1: 
/* 109: 92 */         this.tileRegulator.color = ((byte)packet.getByte());
/* 110: 93 */         this.tileRegulator.dirtyBlock();
/* 111: 94 */         break;
/* 112:    */       case 2: 
/* 113: 96 */         this.tileRegulator.mode = ((byte)packet.getByte());
/* 114: 97 */         this.tileRegulator.dirtyBlock();
/* 115: 98 */         break;
/* 116:    */       }
/* 117:    */     }
/* 118:    */     catch (IOException e) {}
/* 119:    */   }
/* 120:    */   
/* 121:105 */   public int mode = 0;
/* 122:105 */   public int color = 0;
/* 123:    */   private TileRegulator tileRegulator;
/* 124:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ContainerRegulator
 * JD-Core Version:    0.7.0.1
 */