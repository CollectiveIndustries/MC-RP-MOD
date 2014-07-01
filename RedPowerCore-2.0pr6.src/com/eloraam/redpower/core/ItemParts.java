/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.HashMap;
/*  7:   */ import java.util.List;
/*  8:   */ import tj;
/*  9:   */ import up;
/* 10:   */ import ur;
/* 11:   */ 
/* 12:   */ public class ItemParts
/* 13:   */   extends up
/* 14:   */ {
/* 15:   */   String textureFile;
/* 16:   */   
/* 17:   */   public ItemParts(int i, String txf)
/* 18:   */   {
/* 19:14 */     super(i);
/* 20:15 */     e(0);
/* 21:16 */     a(true);
/* 22:17 */     this.textureFile = txf;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void addItem(int dmg, int icon, String name)
/* 26:   */   {
/* 27:21 */     this.icons.put(Integer.valueOf(dmg), Integer.valueOf(icon));
/* 28:22 */     this.names.put(Integer.valueOf(dmg), name);
/* 29:23 */     this.valid.add(Integer.valueOf(dmg));
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String d(ur ist)
/* 33:   */   {
/* 34:27 */     String tr = (String)this.names.get(Integer.valueOf(ist.j()));
/* 35:28 */     if (tr == null) {
/* 36:28 */       throw new IndexOutOfBoundsException();
/* 37:   */     }
/* 38:29 */     return tr;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public int b(int i)
/* 42:   */   {
/* 43:33 */     Integer tr = (Integer)this.icons.get(Integer.valueOf(i));
/* 44:34 */     if (tr == null) {
/* 45:34 */       throw new IndexOutOfBoundsException();
/* 46:   */     }
/* 47:35 */     return tr.intValue();
/* 48:   */   }
/* 49:   */   
/* 50:   */   @SideOnly(Side.CLIENT)
/* 51:   */   public void a(int id, tj tab, List list)
/* 52:   */   {
/* 53:41 */     for (Integer i : this.valid) {
/* 54:42 */       list.add(new ur(this, 1, i.intValue()));
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   public String getTextureFile()
/* 59:   */   {
/* 60:47 */     return this.textureFile;
/* 61:   */   }
/* 62:   */   
/* 63:50 */   HashMap names = new HashMap();
/* 64:51 */   HashMap icons = new HashMap();
/* 65:52 */   ArrayList valid = new ArrayList();
/* 66:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.ItemParts
 * JD-Core Version:    0.7.0.1
 */