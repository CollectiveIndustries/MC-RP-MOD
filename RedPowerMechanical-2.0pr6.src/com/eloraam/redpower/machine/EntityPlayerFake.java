/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import ke;
/*  4:   */ import lq;
/*  5:   */ import qw;
/*  6:   */ import qx;
/*  7:   */ import s;
/*  8:   */ import up;
/*  9:   */ import ur;
/* 10:   */ import yc;
/* 11:   */ 
/* 12:   */ public class EntityPlayerFake
/* 13:   */   extends qx
/* 14:   */ {
/* 15:   */   public EntityPlayerFake(yc world)
/* 16:   */   {
/* 17:12 */     super(world);
/* 18:   */     
/* 19:14 */     this.bR = "";
/* 20:15 */     for (int i = 9; i < 36; i++) {
/* 21:16 */       this.bJ.a(i, new ur(up.D));
/* 22:   */     }
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void a(lq entity) {}
/* 26:   */   
/* 27:   */   public boolean a(int p, String str)
/* 28:   */   {
/* 29:26 */     return false;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void a(String var1) {}
/* 33:   */   
/* 34:   */   public s b()
/* 35:   */   {
/* 36:33 */     return new s(ke.c(this.t), ke.c(this.u + 0.5D), ke.c(this.v));
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.EntityPlayerFake
 * JD-Core Version:    0.7.0.1
 */