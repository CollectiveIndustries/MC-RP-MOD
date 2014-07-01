/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import com.eloraam.redpower.RedPowerWorld;
/*  5:   */ import uq;
/*  6:   */ import ur;
/*  7:   */ import vu;
/*  8:   */ 
/*  9:   */ public class ItemCustomSword
/* 10:   */   extends vu
/* 11:   */ {
/* 12:   */   protected uq toolMaterial2;
/* 13:   */   
/* 14:   */   public ItemCustomSword(int i, uq mat)
/* 15:   */   {
/* 16:14 */     super(i, mat);
/* 17:15 */     this.toolMaterial2 = mat;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public boolean a(ur ist1, ur ist2)
/* 21:   */   {
/* 22:20 */     if ((this.toolMaterial2 == RedPowerWorld.toolMaterialRuby) && (ist2.a(RedPowerBase.itemRuby))) {
/* 23:22 */       return true;
/* 24:   */     }
/* 25:23 */     if ((this.toolMaterial2 == RedPowerWorld.toolMaterialSapphire) && (ist2.a(RedPowerBase.itemSapphire))) {
/* 26:25 */       return true;
/* 27:   */     }
/* 28:26 */     if ((this.toolMaterial2 == RedPowerWorld.toolMaterialGreenSapphire) && (ist2.a(RedPowerBase.itemGreenSapphire))) {
/* 29:28 */       return true;
/* 30:   */     }
/* 31:29 */     return super.a(ist1, ist2);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getTextureFile()
/* 35:   */   {
/* 36:33 */     return "/eloraam/world/worlditems1.png";
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemCustomSword
 * JD-Core Version:    0.7.0.1
 */