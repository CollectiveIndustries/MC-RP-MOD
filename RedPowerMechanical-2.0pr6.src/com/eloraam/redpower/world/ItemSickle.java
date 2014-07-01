/*   1:    */ package com.eloraam.redpower.world;
/*   2:    */ 
/*   3:    */ import aje;
/*   4:    */ import akt;
/*   5:    */ import amq;
/*   6:    */ import com.eloraam.redpower.RedPowerBase;
/*   7:    */ import com.eloraam.redpower.RedPowerWorld;
/*   8:    */ import md;
/*   9:    */ import qx;
/*  10:    */ import tw;
/*  11:    */ import uq;
/*  12:    */ import ur;
/*  13:    */ import yc;
/*  14:    */ 
/*  15:    */ public class ItemSickle
/*  16:    */   extends tw
/*  17:    */ {
/*  18:    */   public ItemSickle(int i, uq mat)
/*  19:    */   {
/*  20: 20 */     super(i, 3, mat, new amq[0]);
/*  21:    */     
/*  22: 22 */     this.ck = 1;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public float a(ur ist, amq bl)
/*  26:    */   {
/*  27: 27 */     if ((bl instanceof akt)) {
/*  28: 28 */       return this.a;
/*  29:    */     }
/*  30: 30 */     return super.a(ist, bl);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public boolean a(ur ist, yc world, int ibid, int i, int j, int k, md entity)
/*  34:    */   {
/*  35: 38 */     boolean used = false;
/*  36: 41 */     if (!(entity instanceof qx)) {
/*  37: 41 */       return false;
/*  38:    */     }
/*  39: 42 */     qx player = (qx)entity;
/*  40:    */     
/*  41: 44 */     amq bl = amq.p[ibid];
/*  42: 45 */     if ((bl != null) && (bl.isLeaves(world, i, j, k)))
/*  43:    */     {
/*  44: 46 */       for (int q = -this.leafRadius; q <= this.leafRadius; q++) {
/*  45: 47 */         for (int r = -this.leafRadius; r <= this.leafRadius; r++) {
/*  46: 48 */           for (int s = -this.leafRadius; s <= this.leafRadius; s++)
/*  47:    */           {
/*  48: 49 */             int bid = world.a(i + q, j + r, k + s);
/*  49: 50 */             int md = world.h(i + q, j + r, k + s);
/*  50: 51 */             bl = amq.p[bid];
/*  51: 52 */             if ((bl != null) && 
/*  52: 53 */               (bl.isLeaves(world, i + q, j + r, k + s)))
/*  53:    */             {
/*  54: 54 */               if (bl.canHarvestBlock(player, md)) {
/*  55: 55 */                 bl.a(world, player, i + q, j + r, k + s, md);
/*  56:    */               }
/*  57: 58 */               world.e(i + q, j + r, k + s, 0);
/*  58: 59 */               used = true;
/*  59:    */             }
/*  60:    */           }
/*  61:    */         }
/*  62:    */       }
/*  63: 63 */       if (used) {
/*  64: 63 */         ist.a(1, entity);
/*  65:    */       }
/*  66: 64 */       return used;
/*  67:    */     }
/*  68: 67 */     for (int q = -this.cropRadius; q <= this.cropRadius; q++) {
/*  69: 68 */       for (int r = -this.cropRadius; r <= this.cropRadius; r++)
/*  70:    */       {
/*  71: 69 */         int bid = world.a(i + q, j, k + r);
/*  72: 70 */         int md = world.h(i + q, j, k + r);
/*  73: 71 */         if (bid != 0)
/*  74:    */         {
/*  75: 72 */           bl = amq.p[bid];
/*  76: 73 */           if ((bl.cm != amq.bC.cm) && 
/*  77: 74 */             ((bl instanceof aje)))
/*  78:    */           {
/*  79: 75 */             if (bl.canHarvestBlock(player, md)) {
/*  80: 76 */               bl.a(world, player, i + q, j, k + r, md);
/*  81:    */             }
/*  82: 79 */             world.e(i + q, j, k + r, 0);
/*  83: 80 */             used = true;
/*  84:    */           }
/*  85:    */         }
/*  86:    */       }
/*  87:    */     }
/*  88: 84 */     if (used) {
/*  89: 84 */       ist.a(1, entity);
/*  90:    */     }
/*  91: 85 */     return used;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean a(ur ist1, ur ist2)
/*  95:    */   {
/*  96: 90 */     if ((this.b == RedPowerWorld.toolMaterialRuby) && (ist2.a(RedPowerBase.itemRuby))) {
/*  97: 92 */       return true;
/*  98:    */     }
/*  99: 93 */     if ((this.b == RedPowerWorld.toolMaterialSapphire) && (ist2.a(RedPowerBase.itemSapphire))) {
/* 100: 95 */       return true;
/* 101:    */     }
/* 102: 96 */     if ((this.b == RedPowerWorld.toolMaterialGreenSapphire) && (ist2.a(RedPowerBase.itemGreenSapphire))) {
/* 103: 98 */       return true;
/* 104:    */     }
/* 105: 99 */     return super.a(ist1, ist2);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public String getTextureFile()
/* 109:    */   {
/* 110:103 */     return "/eloraam/world/worlditems1.png";
/* 111:    */   }
/* 112:    */   
/* 113:    */   public int c()
/* 114:    */   {
/* 115:108 */     return 20;
/* 116:    */   }
/* 117:    */   
/* 118:112 */   public int cropRadius = 2;
/* 119:113 */   public int leafRadius = 1;
/* 120:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemSickle
 * JD-Core Version:    0.7.0.1
 */