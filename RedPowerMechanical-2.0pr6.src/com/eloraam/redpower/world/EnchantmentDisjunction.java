/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerWorld;
/*  4:   */ import md;
/*  5:   */ import pp;
/*  6:   */ import qd;
/*  7:   */ import ur;
/*  8:   */ import wz;
/*  9:   */ import xc;
/* 10:   */ import xd;
/* 11:   */ 
/* 12:   */ public class EnchantmentDisjunction
/* 13:   */   extends xc
/* 14:   */ {
/* 15:   */   public EnchantmentDisjunction(int i, int j)
/* 16:   */   {
/* 17:15 */     super(i, j, xd.g);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public int a(int i)
/* 21:   */   {
/* 22:19 */     return 5 + 8 * i;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public int b(int i)
/* 26:   */   {
/* 27:23 */     return a(i) + 20;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int b()
/* 31:   */   {
/* 32:27 */     return 5;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public int a(int i, md ent)
/* 36:   */   {
/* 37:31 */     if (((ent instanceof qd)) || ((ent instanceof pp))) {
/* 38:32 */       return i * 6;
/* 39:   */     }
/* 40:33 */     return 0;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public String a()
/* 44:   */   {
/* 45:37 */     return "enchantment.damage.disjunction";
/* 46:   */   }
/* 47:   */   
/* 48:   */   public boolean canEnchantItem(ur ist)
/* 49:   */   {
/* 50:41 */     return ist.c == RedPowerWorld.itemAthame.cj;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public boolean a(xc enchantment)
/* 54:   */   {
/* 55:45 */     if (enchantment == this) {
/* 56:45 */       return false;
/* 57:   */     }
/* 58:46 */     return !(enchantment instanceof wz);
/* 59:   */   }
/* 60:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.EnchantmentDisjunction
 * JD-Core Version:    0.7.0.1
 */