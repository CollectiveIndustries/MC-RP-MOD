/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import java.util.List;
/*  8:   */ import qx;
/*  9:   */ import tj;
/* 10:   */ import ur;
/* 11:   */ import vq;
/* 12:   */ import yc;
/* 13:   */ 
/* 14:   */ public class ItemExtended
/* 15:   */   extends vq
/* 16:   */ {
/* 17:   */   public ItemExtended(int i)
/* 18:   */   {
/* 19:17 */     super(i);
/* 20:18 */     e(0);
/* 21:19 */     a(true);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public int a(int i)
/* 25:   */   {
/* 26:23 */     return i;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setMetaName(int dmg, String name)
/* 30:   */   {
/* 31:27 */     this.names.put(Integer.valueOf(dmg), name);
/* 32:28 */     this.valid.add(Integer.valueOf(dmg));
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String d(ur ist)
/* 36:   */   {
/* 37:32 */     String tr = (String)this.names.get(Integer.valueOf(ist.j()));
/* 38:33 */     if (tr == null) {
/* 39:33 */       throw new IndexOutOfBoundsException();
/* 40:   */     }
/* 41:34 */     return tr;
/* 42:   */   }
/* 43:   */   
/* 44:   */   @SideOnly(Side.CLIENT)
/* 45:   */   public void a(int id, tj tab, List list)
/* 46:   */   {
/* 47:40 */     for (Integer i : this.valid) {
/* 48:41 */       list.add(new ur(this, 1, i.intValue()));
/* 49:   */     }
/* 50:   */   }
/* 51:   */   
/* 52:   */   public boolean placeBlockAt(ur stack, qx player, yc world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/* 53:   */   {
/* 54:50 */     if (!world.d(x, y, z, this.cj, metadata)) {
/* 55:52 */       return false;
/* 56:   */     }
/* 57:54 */     if (world.a(x, y, z) == this.cj)
/* 58:   */     {
/* 59:55 */       BlockExtended bex = (BlockExtended)amq.p[this.cj];
/* 60:56 */       bex.onBlockPlacedUseful(world, x, y, z, side, player, stack);
/* 61:   */     }
/* 62:58 */     return true;
/* 63:   */   }
/* 64:   */   
/* 65:61 */   HashMap names = new HashMap();
/* 66:62 */   ArrayList valid = new ArrayList();
/* 67:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.ItemExtended
 * JD-Core Version:    0.7.0.1
 */