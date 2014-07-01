/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import by;
/*   6:    */ import java.util.Collection;
/*   7:    */ import java.util.LinkedList;
/*   8:    */ import ur;
/*   9:    */ 
/*  10:    */ public class TubeBuffer
/*  11:    */ {
/*  12:    */   public boolean isEmpty()
/*  13:    */   {
/*  14: 15 */     if (this.buffer == null) {
/*  15: 16 */       return true;
/*  16:    */     }
/*  17: 17 */     return this.buffer.size() == 0;
/*  18:    */   }
/*  19:    */   
/*  20:    */   public TubeItem getLast()
/*  21:    */   {
/*  22: 21 */     if (this.buffer == null) {
/*  23: 21 */       return null;
/*  24:    */     }
/*  25: 22 */     return (TubeItem)this.buffer.getLast();
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void add(TubeItem ti)
/*  29:    */   {
/*  30: 26 */     if (this.buffer == null) {
/*  31: 27 */       this.buffer = new LinkedList();
/*  32:    */     }
/*  33: 28 */     this.buffer.addFirst(ti);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void addNew(ur ist)
/*  37:    */   {
/*  38: 32 */     if (this.buffer == null) {
/*  39: 33 */       this.buffer = new LinkedList();
/*  40:    */     }
/*  41: 34 */     this.buffer.addFirst(new TubeItem(0, ist));
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void addNewColor(ur ist, int col)
/*  45:    */   {
/*  46: 38 */     if (this.buffer == null) {
/*  47: 39 */       this.buffer = new LinkedList();
/*  48:    */     }
/*  49: 40 */     TubeItem ti = new TubeItem(0, ist);
/*  50: 41 */     ti.color = ((byte)col);
/*  51: 42 */     this.buffer.addFirst(ti);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void addAll(Collection col)
/*  55:    */   {
/*  56: 46 */     if (this.buffer == null) {
/*  57: 47 */       this.buffer = new LinkedList();
/*  58:    */     }
/*  59: 48 */     for (ur ist : col) {
/*  60: 49 */       this.buffer.add(new TubeItem(0, ist));
/*  61:    */     }
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void addBounce(TubeItem ti)
/*  65:    */   {
/*  66: 54 */     if (this.buffer == null) {
/*  67: 55 */       this.buffer = new LinkedList();
/*  68:    */     }
/*  69: 56 */     this.buffer.addLast(ti);
/*  70: 57 */     this.plugged = true;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void pop()
/*  74:    */   {
/*  75: 61 */     this.buffer.removeLast();
/*  76: 62 */     if (this.buffer.size() == 0) {
/*  77: 63 */       this.plugged = false;
/*  78:    */     }
/*  79:    */   }
/*  80:    */   
/*  81:    */   public int size()
/*  82:    */   {
/*  83: 67 */     if (this.buffer == null) {
/*  84: 67 */       return 0;
/*  85:    */     }
/*  86: 68 */     return this.buffer.size();
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void onRemove(any te)
/*  90:    */   {
/*  91: 72 */     if (this.buffer == null) {
/*  92: 72 */       return;
/*  93:    */     }
/*  94: 73 */     for (TubeItem ti : this.buffer) {
/*  95: 74 */       if ((ti != null) && (ti.item.a > 0)) {
/*  96: 75 */         CoreLib.dropItem(te.k, te.l, te.m, te.n, ti.item);
/*  97:    */       }
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void readFromNBT(bq nbttagcompound)
/* 102:    */   {
/* 103: 82 */     by items = nbttagcompound.m("Buffer");
/* 104: 83 */     if (items.c() > 0)
/* 105:    */     {
/* 106: 84 */       this.buffer = new LinkedList();
/* 107: 85 */       for (int i = 0; i < items.c(); i++)
/* 108:    */       {
/* 109: 86 */         bq item = (bq)items.b(i);
/* 110: 87 */         this.buffer.add(TubeItem.newFromNBT(item));
/* 111:    */       }
/* 112:    */     }
/* 113: 90 */     int b = nbttagcompound.c("Plug");
/* 114: 91 */     this.plugged = (b > 0);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void writeToNBT(bq nbttagcompound)
/* 118:    */   {
/* 119: 95 */     by items = new by();
/* 120: 96 */     if (this.buffer != null) {
/* 121: 97 */       for (TubeItem ti : this.buffer)
/* 122:    */       {
/* 123: 98 */         bq item = new bq();
/* 124: 99 */         ti.writeToNBT(item);
/* 125:100 */         items.a(item);
/* 126:    */       }
/* 127:    */     }
/* 128:103 */     nbttagcompound.a("Buffer", items);
/* 129:104 */     nbttagcompound.a("Plug", (byte)(this.plugged ? 1 : 0));
/* 130:    */   }
/* 131:    */   
/* 132:106 */   LinkedList buffer = null;
/* 133:107 */   public boolean plugged = false;
/* 134:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TubeBuffer
 * JD-Core Version:    0.7.0.1
 */