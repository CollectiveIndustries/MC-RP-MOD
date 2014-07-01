/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import md;
/*  5:   */ import xc;
/*  6:   */ import xd;
/*  7:   */ import yc;
/*  8:   */ 
/*  9:   */ public class EnchantmentVorpal
/* 10:   */   extends xc
/* 11:   */ {
/* 12:   */   public EnchantmentVorpal(int i, int j)
/* 13:   */   {
/* 14:13 */     super(i, j, xd.g);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public int a(int i)
/* 18:   */   {
/* 19:17 */     return 20 + 10 * (i - 1);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int b(int i)
/* 23:   */   {
/* 24:21 */     return a(i) + 50;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int b()
/* 28:   */   {
/* 29:25 */     return 4;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int a(int i, md ent)
/* 33:   */   {
/* 34:29 */     if (ent.p.t.nextInt(100) < 2 * i * i) {
/* 35:30 */       return 100;
/* 36:   */     }
/* 37:31 */     return 0;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public String a()
/* 41:   */   {
/* 42:35 */     return "enchantment.damage.vorpal";
/* 43:   */   }
/* 44:   */   
/* 45:   */   public boolean a(xc enchantment)
/* 46:   */   {
/* 47:39 */     if (enchantment == this) {
/* 48:39 */       return false;
/* 49:   */     }
/* 50:40 */     return true;
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.EnchantmentVorpal
 * JD-Core Version:    0.7.0.1
 */