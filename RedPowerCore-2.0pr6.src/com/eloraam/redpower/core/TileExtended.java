/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import aoe;
/*   5:    */ import bq;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import lq;
/*   8:    */ import md;
/*   9:    */ import qx;
/*  10:    */ import ur;
/*  11:    */ import yc;
/*  12:    */ 
/*  13:    */ public abstract class TileExtended
/*  14:    */   extends any
/*  15:    */ {
/*  16:    */   public void onBlockNeighborChange(int l) {}
/*  17:    */   
/*  18:    */   public void onBlockPlaced(ur ist, int side, md ent) {}
/*  19:    */   
/*  20:    */   public void onBlockRemoval() {}
/*  21:    */   
/*  22:    */   public boolean isBlockStrongPoweringTo(int side)
/*  23:    */   {
/*  24: 29 */     return false;
/*  25:    */   }
/*  26:    */   
/*  27:    */   public boolean isBlockWeakPoweringTo(int side)
/*  28:    */   {
/*  29: 33 */     return isBlockStrongPoweringTo(side);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public boolean onBlockActivated(qx player)
/*  33:    */   {
/*  34: 37 */     return false;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void onEntityCollidedWithBlock(lq ent) {}
/*  38:    */   
/*  39:    */   public aoe getCollisionBoundingBox()
/*  40:    */   {
/*  41: 43 */     return null;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void onTileTick() {}
/*  45:    */   
/*  46:    */   public int getExtendedID()
/*  47:    */   {
/*  48: 51 */     return 0;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public abstract int getBlockID();
/*  52:    */   
/*  53:    */   public int getExtendedMetadata()
/*  54:    */   {
/*  55: 56 */     return 0;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void setExtendedMetadata(int md) {}
/*  59:    */   
/*  60:    */   public void addHarvestContents(ArrayList ist)
/*  61:    */   {
/*  62: 60 */     ist.add(new ur(getBlockID(), 1, getExtendedID()));
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void scheduleTick(int time)
/*  66:    */   {
/*  67: 66 */     long tn = this.k.G() + time;
/*  68: 67 */     if ((this.timeSched > 0L) && (this.timeSched < tn)) {
/*  69: 67 */       return;
/*  70:    */     }
/*  71: 68 */     this.timeSched = tn;
/*  72: 69 */     dirtyBlock();
/*  73:    */   }
/*  74:    */   
/*  75:    */   public boolean isTickRunnable()
/*  76:    */   {
/*  77: 76 */     return (this.timeSched >= 0L) && (this.timeSched <= this.k.G());
/*  78:    */   }
/*  79:    */   
/*  80:    */   public boolean isTickScheduled()
/*  81:    */   {
/*  82: 80 */     return this.timeSched >= 0L;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void updateBlockChange()
/*  86:    */   {
/*  87: 86 */     RedPowerLib.updateIndirectNeighbors(this.k, this.l, this.m, this.n, getBlockID());
/*  88:    */     
/*  89: 88 */     this.k.i(this.l, this.m, this.n);
/*  90: 89 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void updateBlock()
/*  94:    */   {
/*  95: 93 */     int md = this.k.h(this.l, this.m, this.n);
/*  96: 94 */     this.k.i(this.l, this.m, this.n);
/*  97: 95 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void dirtyBlock()
/* 101:    */   {
/* 102: 99 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void breakBlock()
/* 106:    */   {
/* 107:105 */     ArrayList il = new ArrayList();
/* 108:106 */     addHarvestContents(il);
/* 109:107 */     for (ur it : il) {
/* 110:108 */       CoreLib.dropItem(this.k, this.l, this.m, this.n, it);
/* 111:    */     }
/* 112:110 */     this.k.e(this.l, this.m, this.n, 0);
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void g()
/* 116:    */   {
/* 117:116 */     if (CoreLib.isClient(this.k)) {
/* 118:116 */       return;
/* 119:    */     }
/* 120:117 */     if (this.timeSched < 0L) {
/* 121:117 */       return;
/* 122:    */     }
/* 123:118 */     long wtime = this.k.G();
/* 124:119 */     if (this.timeSched > wtime + 1200L)
/* 125:    */     {
/* 126:120 */       this.timeSched = (wtime + 1200L);
/* 127:    */     }
/* 128:121 */     else if (this.timeSched <= wtime)
/* 129:    */     {
/* 130:122 */       this.timeSched = -1L;
/* 131:123 */       onTileTick();
/* 132:124 */       dirtyBlock();
/* 133:    */     }
/* 134:    */   }
/* 135:    */   
/* 136:    */   public void a(bq nbttagcompound)
/* 137:    */   {
/* 138:129 */     super.a(nbttagcompound);
/* 139:130 */     this.timeSched = nbttagcompound.f("sched");
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void b(bq nbttagcompound)
/* 143:    */   {
/* 144:135 */     super.b(nbttagcompound);
/* 145:136 */     nbttagcompound.a("sched", this.timeSched);
/* 146:    */   }
/* 147:    */   
/* 148:141 */   protected long timeSched = -1L;
/* 149:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TileExtended
 * JD-Core Version:    0.7.0.1
 */