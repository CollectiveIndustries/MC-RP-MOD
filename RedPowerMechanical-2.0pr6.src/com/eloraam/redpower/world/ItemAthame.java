/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import com.eloraam.redpower.RedPowerBase;
/*  5:   */ import lq;
/*  6:   */ import pp;
/*  7:   */ import qd;
/*  8:   */ import tj;
/*  9:   */ import uq;
/* 10:   */ import ur;
/* 11:   */ import vu;
/* 12:   */ 
/* 13:   */ public class ItemAthame
/* 14:   */   extends vu
/* 15:   */ {
/* 16:   */   public ItemAthame(int i)
/* 17:   */   {
/* 18:18 */     super(i, uq.d);
/* 19:19 */     e(100);
/* 20:20 */     b(0, 7);
/* 21:21 */     a(tj.j);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public float a(ur itemstack, amq block)
/* 25:   */   {
/* 26:25 */     return 1.0F;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public int a(lq entity)
/* 30:   */   {
/* 31:29 */     if (((entity instanceof qd)) || ((entity instanceof pp))) {
/* 32:31 */       return 25;
/* 33:   */     }
/* 34:32 */     return 1;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public String getTextureFile()
/* 38:   */   {
/* 39:36 */     return "/eloraam/world/worlditems1.png";
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean a(ur ist1, ur ist2)
/* 43:   */   {
/* 44:42 */     if (ist2.a(RedPowerBase.itemIngotSilver)) {
/* 45:43 */       return true;
/* 46:   */     }
/* 47:44 */     return false;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public int c()
/* 51:   */   {
/* 52:48 */     return 30;
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemAthame
 * JD-Core Version:    0.7.0.1
 */