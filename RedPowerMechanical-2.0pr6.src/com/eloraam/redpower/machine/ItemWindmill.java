/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import java.util.List;
/*  7:   */ import tj;
/*  8:   */ import up;
/*  9:   */ import ur;
/* 10:   */ 
/* 11:   */ public class ItemWindmill
/* 12:   */   extends up
/* 13:   */ {
/* 14:   */   public int windmillType;
/* 15:   */   
/* 16:   */   public ItemWindmill(int i, int tp)
/* 17:   */   {
/* 18:22 */     super(i);
/* 19:23 */     c(177);
/* 20:24 */     d(1);
/* 21:25 */     e(1000);
/* 22:26 */     b("windTurbineWood");
/* 23:27 */     this.windmillType = tp;
/* 24:28 */     a(tj.f);
/* 25:   */   }
/* 26:   */   
/* 27:   */   @SideOnly(Side.CLIENT)
/* 28:   */   public void a(int id, tj tab, List list)
/* 29:   */   {
/* 30:34 */     list.add(new ur(this, 1, 0));
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean canFaceDirection(int dir)
/* 34:   */   {
/* 35:38 */     switch (this.windmillType)
/* 36:   */     {
/* 37:   */     case 1: 
/* 38:39 */       return dir == 0;
/* 39:   */     case 2: 
/* 40:40 */       return dir > 1;
/* 41:   */     }
/* 42:42 */     return false;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public ur getBrokenItem()
/* 46:   */   {
/* 47:46 */     switch (this.windmillType)
/* 48:   */     {
/* 49:   */     case 1: 
/* 50:48 */       return new ur(RedPowerBase.blockMicro, 3, 5905);
/* 51:   */     case 2: 
/* 52:51 */       return new ur(RedPowerBase.blockMicro, 1, 5905);
/* 53:   */     }
/* 54:54 */     return null;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public String getTextureFile()
/* 58:   */   {
/* 59:58 */     return "/eloraam/base/items1.png";
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ItemWindmill
 * JD-Core Version:    0.7.0.1
 */