/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import java.util.Collections;
/*   5:    */ import java.util.HashSet;
/*   6:    */ import java.util.LinkedList;
/*   7:    */ import yc;
/*   8:    */ 
/*   9:    */ public class FrameLib
/*  10:    */ {
/*  11:    */   public static class FrameSolver
/*  12:    */   {
/*  13:    */     HashSet scanmap;
/*  14:    */     LinkedList scanpos;
/*  15:    */     HashSet framemap;
/*  16:    */     LinkedList frameset;
/*  17:    */     LinkedList clearset;
/*  18:    */     int movedir;
/*  19:    */     WorldCoord motorpos;
/*  20:    */     
/*  21:    */     public FrameSolver(yc world, WorldCoord wc, WorldCoord motorp, int movdir)
/*  22:    */     {
/*  23: 11 */       this.movedir = movdir;this.motorpos = motorp;
/*  24: 12 */       this.worldObj = world;
/*  25:    */       
/*  26: 14 */       this.scanmap = new HashSet();
/*  27: 15 */       this.scanpos = new LinkedList();
/*  28: 16 */       this.framemap = new HashSet();
/*  29: 17 */       this.frameset = new LinkedList();
/*  30: 18 */       this.clearset = new LinkedList();
/*  31:    */       
/*  32: 20 */       this.scanmap.add(motorp);
/*  33: 21 */       this.scanmap.add(wc);
/*  34: 22 */       this.scanpos.addLast(wc);
/*  35:    */     }
/*  36:    */     
/*  37:    */     boolean step()
/*  38:    */     {
/*  39: 26 */       WorldCoord wc = (WorldCoord)this.scanpos.removeFirst();
/*  40: 27 */       if ((wc.y < 0) || (wc.y >= this.worldObj.O() - 1)) {
/*  41: 28 */         return false;
/*  42:    */       }
/*  43: 31 */       int bid = this.worldObj.a(wc.x, wc.y, wc.z);
/*  44: 32 */       if ((this.movedir >= 0) && (!this.worldObj.f(wc.x, wc.y, wc.z)))
/*  45:    */       {
/*  46: 34 */         this.valid = false;
/*  47: 35 */         return false;
/*  48:    */       }
/*  49: 37 */       if (bid == 0) {
/*  50: 37 */         return false;
/*  51:    */       }
/*  52: 39 */       if ((this.movedir >= 0) && (amq.p[bid].m(this.worldObj, wc.x, wc.y, wc.z) < 0.0F))
/*  53:    */       {
/*  54: 42 */         this.valid = false;
/*  55: 43 */         return false;
/*  56:    */       }
/*  57: 45 */       this.framemap.add(wc);
/*  58: 46 */       this.frameset.addLast(wc);
/*  59:    */       
/*  60: 48 */       IFrameLink ifl = (IFrameLink)CoreLib.getTileEntity(this.worldObj, wc, IFrameLink.class);
/*  61: 50 */       if (ifl == null) {
/*  62: 50 */         return true;
/*  63:    */       }
/*  64: 52 */       if ((ifl.isFrameMoving()) && (this.movedir >= 0))
/*  65:    */       {
/*  66: 53 */         this.valid = false;
/*  67: 54 */         return true;
/*  68:    */       }
/*  69: 57 */       for (int i = 0; i < 6; i++) {
/*  70: 58 */         if (ifl.canFrameConnectOut(i))
/*  71:    */         {
/*  72: 61 */           WorldCoord sp = wc.coordStep(i);
/*  73: 62 */           if (!this.scanmap.contains(sp))
/*  74:    */           {
/*  75: 64 */             IFrameLink if2 = (IFrameLink)CoreLib.getTileEntity(this.worldObj, sp, IFrameLink.class);
/*  76: 66 */             if (if2 != null)
/*  77:    */             {
/*  78: 67 */               if (!if2.canFrameConnectIn((i ^ 0x1) & 0xFF)) {
/*  79:    */                 continue;
/*  80:    */               }
/*  81: 69 */               if (this.movedir < 0)
/*  82:    */               {
/*  83: 70 */                 WorldCoord wcls = if2.getFrameLinkset();
/*  84: 72 */                 if ((wcls == null) || 
/*  85: 73 */                   (!wcls.equals(this.motorpos))) {
/*  86:    */                   continue;
/*  87:    */                 }
/*  88:    */               }
/*  89:    */             }
/*  90: 77 */             this.scanmap.add(sp);
/*  91: 78 */             this.scanpos.addLast(sp);
/*  92:    */           }
/*  93:    */         }
/*  94:    */       }
/*  95: 80 */       return true;
/*  96:    */     }
/*  97:    */     
/*  98:    */     public boolean solve()
/*  99:    */     {
/* 100: 84 */       while ((this.valid) && (this.scanpos.size() > 0)) {
/* 101: 85 */         step();
/* 102:    */       }
/* 103: 86 */       return this.valid;
/* 104:    */     }
/* 105:    */     
/* 106:    */     public boolean solveLimit(int limit)
/* 107:    */     {
/* 108: 90 */       while ((this.valid) && (this.scanpos.size() > 0))
/* 109:    */       {
/* 110: 91 */         if (step()) {
/* 111: 91 */           limit--;
/* 112:    */         }
/* 113: 92 */         if (limit == 0) {
/* 114: 92 */           return false;
/* 115:    */         }
/* 116:    */       }
/* 117: 94 */       return this.valid;
/* 118:    */     }
/* 119:    */     
/* 120:    */     public boolean addMoved()
/* 121:    */     {
/* 122: 98 */       LinkedList fsstp = (LinkedList)this.frameset.clone();
/* 123:100 */       for (WorldCoord wc : fsstp)
/* 124:    */       {
/* 125:101 */         WorldCoord sp = wc.coordStep(this.movedir);
/* 126:    */         
/* 127:    */ 
/* 128:    */ 
/* 129:    */ 
/* 130:106 */         int bid = this.worldObj.a(sp.x, sp.y, sp.z);
/* 131:107 */         if (!this.worldObj.f(sp.x, sp.y, sp.z))
/* 132:    */         {
/* 133:108 */           this.valid = false;return false;
/* 134:    */         }
/* 135:110 */         if (!this.framemap.contains(sp))
/* 136:    */         {
/* 137:111 */           if (bid != 0)
/* 138:    */           {
/* 139:112 */             if (!this.worldObj.a(amq.w.cm, sp.x, sp.y, sp.z, true, 0, null))
/* 140:    */             {
/* 141:116 */               this.valid = false;
/* 142:117 */               return false;
/* 143:    */             }
/* 144:119 */             this.clearset.add(sp);
/* 145:    */           }
/* 146:121 */           this.framemap.add(sp);
/* 147:122 */           this.frameset.addLast(sp);
/* 148:    */         }
/* 149:    */       }
/* 150:124 */       return this.valid;
/* 151:    */     }
/* 152:    */     
/* 153:    */     public void sort(int dir)
/* 154:    */     {
/* 155:128 */       Collections.sort(this.frameset, WorldCoord.getCompareDir(dir));
/* 156:    */     }
/* 157:    */     
/* 158:    */     public LinkedList getFrameSet()
/* 159:    */     {
/* 160:133 */       return this.frameset;
/* 161:    */     }
/* 162:    */     
/* 163:    */     public LinkedList getClearSet()
/* 164:    */     {
/* 165:137 */       return this.clearset;
/* 166:    */     }
/* 167:    */     
/* 168:150 */     public boolean valid = true;
/* 169:    */     yc worldObj;
/* 170:    */   }
/* 171:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.FrameLib
 * JD-Core Version:    0.7.0.1
 */