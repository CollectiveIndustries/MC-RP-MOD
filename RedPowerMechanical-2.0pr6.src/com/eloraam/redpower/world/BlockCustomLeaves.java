/*   1:    */ package com.eloraam.redpower.world;
/*   2:    */ 
/*   3:    */ import akt;
/*   4:    */ import amq;
/*   5:    */ import com.eloraam.redpower.RedPowerWorld;
/*   6:    */ import com.eloraam.redpower.core.CoreLib;
/*   7:    */ import com.eloraam.redpower.core.WorldCoord;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import java.util.HashMap;
/*  10:    */ import java.util.LinkedList;
/*  11:    */ import java.util.Random;
/*  12:    */ import ur;
/*  13:    */ import yc;
/*  14:    */ import ym;
/*  15:    */ 
/*  16:    */ public class BlockCustomLeaves
/*  17:    */   extends akt
/*  18:    */ {
/*  19:    */   public BlockCustomLeaves(int i)
/*  20:    */   {
/*  21: 18 */     super(i, 64);
/*  22: 19 */     b(true);
/*  23: 20 */     c(0.2F);
/*  24: 21 */     a(amq.g);
/*  25: 22 */     h(1);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public boolean c()
/*  29:    */   {
/*  30: 26 */     this.c = (!amq.N.c());
/*  31: 27 */     return !this.c;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public boolean a(ym iblockaccess, int i, int j, int k, int l)
/*  35:    */   {
/*  36: 32 */     this.c = (!amq.N.c());
/*  37: 33 */     return super.a(iblockaccess, i, j, k, l);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int a(int i, int j)
/*  41:    */   {
/*  42: 38 */     this.c = (!amq.N.c());
/*  43: 39 */     return 48 + (this.c ? 0 : 1);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void onBlockRemoval(yc world, int i, int j, int k)
/*  47:    */   {
/*  48: 43 */     updateLeaves(world, i, j, k, 1);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public static void updateLeaves(yc world, int i, int j, int k, int r)
/*  52:    */   {
/*  53: 48 */     if (!world.d(i - r - 1, j - r - 1, k - r - 1, i + r + 1, j + r + 1, k + r + 1)) {
/*  54: 50 */       return;
/*  55:    */     }
/*  56: 51 */     for (int x = -r; x <= r; x++) {
/*  57: 51 */       for (int y = -r; y <= r; y++) {
/*  58: 52 */         for (int z = -r; z <= r; z++) {
/*  59: 53 */           if (world.a(i + x, j + y, k + z) == RedPowerWorld.blockLeaves.cm)
/*  60:    */           {
/*  61: 56 */             int md = world.h(i + x, j + y, k + z);
/*  62: 57 */             world.d(i + x, j + y, k + z, md | 0x8);
/*  63:    */           }
/*  64:    */         }
/*  65:    */       }
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void b(yc world, int i, int j, int k, Random random)
/*  70:    */   {
/*  71: 63 */     if (CoreLib.isClient(world)) {
/*  72: 63 */       return;
/*  73:    */     }
/*  74: 64 */     int md = world.h(i, j, k);
/*  75: 65 */     if (((md & 0x8) == 0) || ((md & 0x4) > 0)) {
/*  76: 65 */       return;
/*  77:    */     }
/*  78: 67 */     HashMap wch = new HashMap();
/*  79: 68 */     LinkedList fifo = new LinkedList();
/*  80: 69 */     WorldCoord wc = new WorldCoord(i, j, k);
/*  81: 70 */     WorldCoord wcp = wc.copy();
/*  82: 71 */     fifo.addLast(wc);wch.put(wc, Integer.valueOf(4));
/*  83: 73 */     while (fifo.size() > 0)
/*  84:    */     {
/*  85: 74 */       wc = (WorldCoord)fifo.removeFirst();
/*  86: 75 */       Integer stp = (Integer)wch.get(wc);
/*  87: 76 */       if (stp != null) {
/*  88: 77 */         for (int n = 0; n < 6; n++)
/*  89:    */         {
/*  90: 78 */           wcp.set(wc);
/*  91: 79 */           wcp.step(n);
/*  92: 80 */           if (!wch.containsKey(wcp))
/*  93:    */           {
/*  94: 82 */             int bid = world.a(wcp.x, wcp.y, wcp.z);
/*  95: 83 */             if (bid == RedPowerWorld.blockLogs.cm)
/*  96:    */             {
/*  97: 84 */               world.d(i, j, k, md & 0xFFFFFFF7);
/*  98: 85 */               return;
/*  99:    */             }
/* 100: 87 */             if ((stp.intValue() != 0) && 
/* 101: 88 */               (bid == this.cm))
/* 102:    */             {
/* 103: 89 */               wch.put(wcp, Integer.valueOf(stp.intValue() - 1));
/* 104: 90 */               fifo.addLast(wcp);
/* 105:    */             }
/* 106:    */           }
/* 107:    */         }
/* 108:    */       }
/* 109:    */     }
/* 110: 93 */     c(world, i, j, k, md, 0);
/* 111: 94 */     world.e(i, j, k, 0);
/* 112:    */   }
/* 113:    */   
/* 114:    */   public int a(int i, Random random, int j)
/* 115:    */   {
/* 116:113 */     return RedPowerWorld.blockPlants.cm;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public int quantityDropped(int i, int fortune, Random random)
/* 120:    */   {
/* 121:117 */     return random.nextInt(20) != 0 ? 0 : 1;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public int b(int i)
/* 125:    */   {
/* 126:122 */     return 1;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public boolean isLeaves(yc world, int x, int y, int z)
/* 130:    */   {
/* 131:127 */     return true;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void addCreativeItems(ArrayList itemList)
/* 135:    */   {
/* 136:131 */     itemList.add(new ur(this, 1, 0));
/* 137:    */   }
/* 138:    */   
/* 139:    */   public String getTextureFile()
/* 140:    */   {
/* 141:135 */     return "/eloraam/world/world1.png";
/* 142:    */   }
/* 143:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockCustomLeaves
 * JD-Core Version:    0.7.0.1
 */