/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import tj;
/*  5:   */ import ur;
/*  6:   */ 
/*  7:   */ public class CreativeExtraTabs
/*  8:   */ {
/*  9: 9 */   public static tj tabWires = new tj(tj.getNextID(), "RPWires")
/* 10:   */   {
/* 11:   */     public ur getIconItemStack()
/* 12:   */     {
/* 13:12 */       return new ur(RedPowerBase.blockMicro, 1, 768);
/* 14:   */     }
/* 15:   */   };
/* 16:16 */   public static tj tabMicros = new tj(tj.getNextID(), "RPMicroblocks")
/* 17:   */   {
/* 18:   */     public ur getIconItemStack()
/* 19:   */     {
/* 20:19 */       return new ur(RedPowerBase.blockMicro, 1, 23);
/* 21:   */     }
/* 22:   */   };
/* 23:23 */   public static tj tabMachine = new tj(tj.getNextID(), "RPMachines")
/* 24:   */   {
/* 25:   */     public ur getIconItemStack()
/* 26:   */     {
/* 27:26 */       return new ur(RedPowerBase.blockAppliance, 1, 3);
/* 28:   */     }
/* 29:   */   };
/* 30:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CreativeExtraTabs
 * JD-Core Version:    0.7.0.1
 */