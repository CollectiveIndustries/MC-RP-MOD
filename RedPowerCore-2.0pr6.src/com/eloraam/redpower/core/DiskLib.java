/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerCore;
/*  4:   */ import java.io.File;
/*  5:   */ import java.util.Random;
/*  6:   */ import yc;
/*  7:   */ 
/*  8:   */ public class DiskLib
/*  9:   */ {
/* 10:   */   public static File getSaveDir(yc world)
/* 11:   */   {
/* 12:10 */     File tr = new File(RedPowerCore.getSaveDir(world), "redpower");
/* 13:11 */     tr.mkdirs();
/* 14:12 */     return tr;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static String generateSerialNumber(yc world)
/* 18:   */   {
/* 19:16 */     String tr = "";
/* 20:18 */     for (int i = 0; i < 16; i++) {
/* 21:19 */       tr = tr + String.format("%01x", new Object[] { Integer.valueOf(world.t.nextInt(16)) });
/* 22:   */     }
/* 23:21 */     return tr;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public static File getDiskFile(File dir, String serno)
/* 27:   */   {
/* 28:25 */     return new File(dir, String.format("disk_%s.img", new Object[] { serno }));
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.DiskLib
 * JD-Core Version:    0.7.0.1
 */