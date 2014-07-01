/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerBase;
/*  4:   */ import com.eloraam.redpower.RedPowerWorld;
/*  5:   */ import uq;
/*  6:   */ import ur;
/*  7:   */ import vj;
/*  8:   */ 
/*  9:   */ public class ItemCustomShovel
/* 10:   */   extends vj
/* 11:   */ {
/* 12:   */   public ItemCustomShovel(int i, uq mat)
/* 13:   */   {
/* 14:12 */     super(i, mat);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public boolean a(ur ist1, ur ist2)
/* 18:   */   {
/* 19:17 */     if ((this.b == RedPowerWorld.toolMaterialRuby) && (ist2.a(RedPowerBase.itemRuby))) {
/* 20:19 */       return true;
/* 21:   */     }
/* 22:20 */     if ((this.b == RedPowerWorld.toolMaterialSapphire) && (ist2.a(RedPowerBase.itemSapphire))) {
/* 23:22 */       return true;
/* 24:   */     }
/* 25:23 */     if ((this.b == RedPowerWorld.toolMaterialGreenSapphire) && (ist2.a(RedPowerBase.itemGreenSapphire))) {
/* 26:25 */       return true;
/* 27:   */     }
/* 28:26 */     return super.a(ist1, ist2);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public String getTextureFile()
/* 32:   */   {
/* 33:30 */     return "/eloraam/world/worlditems1.png";
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ItemCustomShovel
 * JD-Core Version:    0.7.0.1
 */