/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import by;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.Iterator;
/*   8:    */ import java.util.LinkedList;
/*   9:    */ import ur;
/*  10:    */ import yc;
/*  11:    */ 
/*  12:    */ public abstract class TubeFlow
/*  13:    */ {
/*  14:    */   public abstract boolean schedule(TubeItem paramTubeItem, TubeScheduleContext paramTubeScheduleContext);
/*  15:    */   
/*  16:    */   public static class TubeScheduleContext
/*  17:    */   {
/*  18:    */     public yc world;
/*  19:    */     public WorldCoord wc;
/*  20:    */     public int cons;
/*  21:    */     
/*  22:    */     public TubeScheduleContext(any te)
/*  23:    */     {
/*  24: 17 */       this.world = te.k;
/*  25: 18 */       this.wc = new WorldCoord(te);
/*  26: 19 */       this.cons = TubeLib.getConnections(this.world, this.wc.x, this.wc.y, this.wc.z);
/*  27:    */     }
/*  28:    */     
/*  29: 25 */     public ArrayList tir = new ArrayList();
/*  30:    */     public Iterator tii;
/*  31: 27 */     public WorldCoord dest = null;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public boolean handleItem(TubeItem ti, TubeScheduleContext tsc)
/*  35:    */   {
/*  36: 33 */     return false;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public abstract any getParent();
/*  40:    */   
/*  41:    */   public boolean update()
/*  42:    */   {
/*  43: 38 */     boolean hasChanged = false;
/*  44: 39 */     if (this.contents.size() == 0) {
/*  45: 39 */       return false;
/*  46:    */     }
/*  47: 41 */     TubeScheduleContext tsc = new TubeScheduleContext(getParent());
/*  48:    */     
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55:    */ 
/*  56: 50 */     tsc.tii = this.contents.iterator();
/*  57: 51 */     while (tsc.tii.hasNext())
/*  58:    */     {
/*  59: 52 */       TubeItem ti = (TubeItem)tsc.tii.next(); TubeItem 
/*  60:    */       
/*  61: 54 */         tmp63_62 = ti;tmp63_62.progress = ((short)(tmp63_62.progress + (ti.power + 16)));
/*  62: 55 */       if (ti.progress >= 128)
/*  63:    */       {
/*  64: 56 */         if (ti.power > 0)
/*  65:    */         {
/*  66: 56 */           TubeItem tmp100_99 = ti;tmp100_99.power = ((short)(tmp100_99.power - 1));
/*  67:    */         }
/*  68: 58 */         hasChanged = true;
/*  69: 59 */         if (!ti.scheduled)
/*  70:    */         {
/*  71: 60 */           if (!schedule(ti, tsc)) {
/*  72: 61 */             tsc.tii.remove();
/*  73:    */           }
/*  74:    */         }
/*  75:    */         else
/*  76:    */         {
/*  77: 64 */           tsc.tii.remove();
/*  78: 65 */           if (!CoreLib.isClient(tsc.world)) {
/*  79: 66 */             tsc.tir.add(ti);
/*  80:    */           }
/*  81:    */         }
/*  82:    */       }
/*  83:    */     }
/*  84: 68 */     if (CoreLib.isClient(tsc.world)) {
/*  85: 68 */       return hasChanged;
/*  86:    */     }
/*  87: 69 */     for (TubeItem ti : tsc.tir) {
/*  88: 70 */       if ((ti.side < 0) || ((tsc.cons & 1 << ti.side) == 0))
/*  89:    */       {
/*  90: 71 */         if (tsc.cons == 0)
/*  91:    */         {
/*  92: 72 */           MachineLib.ejectItem(tsc.world, tsc.wc, ti.item, 1);
/*  93:    */         }
/*  94:    */         else
/*  95:    */         {
/*  96: 76 */           ti.side = ((byte)Integer.numberOfTrailingZeros(tsc.cons));
/*  97:    */           
/*  98: 78 */           ti.progress = 128;
/*  99: 79 */           ti.scheduled = false;
/* 100: 80 */           this.contents.add(ti);
/* 101: 81 */           hasChanged = true;
/* 102:    */         }
/* 103:    */       }
/* 104:    */       else
/* 105:    */       {
/* 106: 84 */         tsc.dest = tsc.wc.copy();tsc.dest.step(ti.side);
/* 107: 85 */         ITubeConnectable itc = (ITubeConnectable)CoreLib.getTileEntity(tsc.world, tsc.dest, ITubeConnectable.class);
/* 108: 87 */         if ((itc instanceof ITubeFlow))
/* 109:    */         {
/* 110: 88 */           ITubeFlow itf = (ITubeFlow)itc;
/* 111: 89 */           itf.addTubeItem(ti);
/* 112:    */         }
/* 113: 93 */         else if (((itc == null) || 
/* 114: 94 */           (!itc.tubeItemEnter((ti.side ^ 0x1) & 0x3F, ti.mode, ti))) && 
/* 115:    */           
/* 116:    */ 
/* 117:    */ 
/* 118:    */ 
/* 119: 99 */           (!handleItem(ti, tsc)))
/* 120:    */         {
/* 121:101 */           ti.progress = 0;
/* 122:102 */           ti.scheduled = false;
/* 123:103 */           ti.mode = 2;
/* 124:104 */           this.contents.add(ti);
/* 125:    */         }
/* 126:    */       }
/* 127:    */     }
/* 128:106 */     return hasChanged;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public void add(TubeItem ti)
/* 132:    */   {
/* 133:110 */     ti.progress = 0;
/* 134:111 */     ti.scheduled = false;
/* 135:112 */     this.contents.add(ti);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public void onRemove()
/* 139:    */   {
/* 140:116 */     any te = getParent();
/* 141:117 */     for (TubeItem ti : this.contents) {
/* 142:118 */       if ((ti != null) && (ti.item.a > 0)) {
/* 143:119 */         CoreLib.dropItem(te.k, te.l, te.m, te.n, ti.item);
/* 144:    */       }
/* 145:    */     }
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void readFromNBT(bq tag)
/* 149:    */   {
/* 150:126 */     by items = tag.m("Items");
/* 151:127 */     if (items.c() > 0)
/* 152:    */     {
/* 153:128 */       this.contents = new LinkedList();
/* 154:129 */       for (int i = 0; i < items.c(); i++)
/* 155:    */       {
/* 156:130 */         bq item = (bq)items.b(i);
/* 157:131 */         this.contents.add(TubeItem.newFromNBT(item));
/* 158:    */       }
/* 159:    */     }
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void writeToNBT(bq tag)
/* 163:    */   {
/* 164:137 */     by items = new by();
/* 165:138 */     if (this.contents != null) {
/* 166:139 */       for (TubeItem ti : this.contents)
/* 167:    */       {
/* 168:140 */         bq item = new bq();
/* 169:141 */         ti.writeToNBT(item);
/* 170:142 */         items.a(item);
/* 171:    */       }
/* 172:    */     }
/* 173:145 */     tag.a("Items", items);
/* 174:    */   }
/* 175:    */   
/* 176:148 */   public LinkedList contents = new LinkedList();
/* 177:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TubeFlow
 * JD-Core Version:    0.7.0.1
 */