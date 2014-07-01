/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import aoh;
/*  5:   */ import com.eloraam.redpower.core.CoreLib;
/*  6:   */ import com.eloraam.redpower.core.IRotatable;
/*  7:   */ import lq;
/*  8:   */ import md;
/*  9:   */ import qx;
/* 10:   */ import tj;
/* 11:   */ import up;
/* 12:   */ import ur;
/* 13:   */ import yc;
/* 14:   */ 
/* 15:   */ public class ItemScrewdriver
/* 16:   */   extends up
/* 17:   */ {
/* 18:   */   public ItemScrewdriver(int i)
/* 19:   */   {
/* 20:18 */     super(i);
/* 21:19 */     e(200);
/* 22:20 */     d(1);
/* 23:21 */     a(tj.i);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean a(ur itemstack, md entityliving, md player)
/* 27:   */   {
/* 28:26 */     itemstack.a(8, player);
/* 29:27 */     return true;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public boolean onItemUseFirst(ur ist, qx player, yc world, int i, int j, int k, int l, float xp, float yp, float zp)
/* 33:   */   {
/* 34:35 */     if (CoreLib.isClient(world)) {
/* 35:35 */       return false;
/* 36:   */     }
/* 37:36 */     boolean sec = false;
/* 38:37 */     if ((player != null) && (player.ah())) {
/* 39:37 */       sec = true;
/* 40:   */     }
/* 41:39 */     int bid = world.a(i, j, k);
/* 42:40 */     int md = world.h(i, j, k);
/* 43:41 */     if ((bid == amq.bk.cm) || (bid == amq.bl.cm))
/* 44:   */     {
/* 45:43 */       world.c(i, j, k, md & 0xC | md + 1 & 0x3);
/* 46:   */       
/* 47:45 */       ist.a(1, player);
/* 48:46 */       return true;
/* 49:   */     }
/* 50:48 */     if (bid == amq.S.cm)
/* 51:   */     {
/* 52:49 */       md = md & 0x3 ^ md >> 2;
/* 53:50 */       md += 2;
/* 54:51 */       world.c(i, j, k, md);
/* 55:52 */       ist.a(1, player);
/* 56:53 */       return true;
/* 57:   */     }
/* 58:55 */     if ((bid == amq.ac.cm) || (bid == amq.Y.cm))
/* 59:   */     {
/* 60:57 */       md++;
/* 61:57 */       if (md > 5) {
/* 62:57 */         md = 0;
/* 63:   */       }
/* 64:58 */       world.c(i, j, k, md);
/* 65:59 */       ist.a(1, player);
/* 66:60 */       return true;
/* 67:   */     }
/* 68:62 */     IRotatable ir = (IRotatable)CoreLib.getTileEntity(world, i, j, k, IRotatable.class);
/* 69:64 */     if (ir == null) {
/* 70:64 */       return false;
/* 71:   */     }
/* 72:66 */     aoh mop = CoreLib.retraceBlock(world, player, i, j, k);
/* 73:68 */     if (mop == null) {
/* 74:68 */       return false;
/* 75:   */     }
/* 76:69 */     int rm = ir.getPartMaxRotation(mop.subHit, sec);
/* 77:70 */     if (rm == 0) {
/* 78:70 */       return false;
/* 79:   */     }
/* 80:72 */     int r = ir.getPartRotation(mop.subHit, sec);
/* 81:73 */     r++;
/* 82:73 */     if (r > rm) {
/* 83:73 */       r = 0;
/* 84:   */     }
/* 85:74 */     ir.setPartRotation(mop.subHit, sec, r);
/* 86:75 */     ist.a(1, player);
/* 87:76 */     return true;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public int a(lq entity)
/* 91:   */   {
/* 92:80 */     return 6;
/* 93:   */   }
/* 94:   */   
/* 95:   */   public String getTextureFile()
/* 96:   */   {
/* 97:84 */     return "/eloraam/base/items1.png";
/* 98:   */   }
/* 99:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ItemScrewdriver
 * JD-Core Version:    0.7.0.1
 */