/*  1:   */ package com.eloraam.redpower.lighting;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerLighting;
/*  4:   */ import cpw.mods.fml.relauncher.Side;
/*  5:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  6:   */ import java.util.List;
/*  7:   */ import tj;
/*  8:   */ import ur;
/*  9:   */ import vq;
/* 10:   */ 
/* 11:   */ public class ItemLamp
/* 12:   */   extends vq
/* 13:   */ {
/* 14:   */   public ItemLamp(int i)
/* 15:   */   {
/* 16:24 */     super(i);
/* 17:25 */     e(0);
/* 18:26 */     a(true);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public int a(int i)
/* 22:   */   {
/* 23:30 */     return i;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public int getPlacedBlockMetadata(int i)
/* 27:   */   {
/* 28:34 */     return i;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public String d(ur ist)
/* 32:   */   {
/* 33:38 */     if (ist.c == RedPowerLighting.blockInvLampOn.cm) {
/* 34:39 */       return "tile.rpilamp." + com.eloraam.redpower.core.CoreLib.rawColorNames[ist.j()];
/* 35:   */     }
/* 36:41 */     return "tile.rplamp." + com.eloraam.redpower.core.CoreLib.rawColorNames[ist.j()];
/* 37:   */   }
/* 38:   */   
/* 39:   */   @SideOnly(Side.CLIENT)
/* 40:   */   public void a(int id, tj tab, List list)
/* 41:   */   {
/* 42:48 */     for (int i = 0; i <= 15; i++) {
/* 43:49 */       list.add(new ur(this, 1, i));
/* 44:   */     }
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.lighting.ItemLamp
 * JD-Core Version:    0.7.0.1
 */