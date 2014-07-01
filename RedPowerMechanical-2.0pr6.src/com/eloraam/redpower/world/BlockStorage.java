/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import amq;
/*  5:   */ import cpw.mods.fml.relauncher.Side;
/*  6:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  7:   */ import java.util.List;
/*  8:   */ import tj;
/*  9:   */ import ur;
/* 10:   */ 
/* 11:   */ public class BlockStorage
/* 12:   */   extends amq
/* 13:   */ {
/* 14:   */   public BlockStorage(int i)
/* 15:   */   {
/* 16:19 */     super(i, agi.f);
/* 17:20 */     c(5.0F);b(10.0F);
/* 18:21 */     a(amq.i);
/* 19:22 */     a(tj.b);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public int a(int i, int j)
/* 23:   */   {
/* 24:26 */     return 80 + j;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int b(int i)
/* 28:   */   {
/* 29:31 */     return i;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String getTextureFile()
/* 33:   */   {
/* 34:35 */     return "/eloraam/world/world1.png";
/* 35:   */   }
/* 36:   */   
/* 37:   */   @SideOnly(Side.CLIENT)
/* 38:   */   public void a(int id, tj tab, List list)
/* 39:   */   {
/* 40:41 */     for (int i = 0; i <= 5; i++) {
/* 41:42 */       list.add(new ur(this, 1, i));
/* 42:   */     }
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.BlockStorage
 * JD-Core Version:    0.7.0.1
 */