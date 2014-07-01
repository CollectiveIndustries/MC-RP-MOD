/*  1:   */ package com.eloraam.redpower.base;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import by;
/*  5:   */ import cpw.mods.fml.relauncher.Side;
/*  6:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  7:   */ import java.util.Arrays;
/*  8:   */ import java.util.HashMap;
/*  9:   */ import java.util.List;
/* 10:   */ import java.util.Map.Entry;
/* 11:   */ import qx;
/* 12:   */ import up;
/* 13:   */ import ur;
/* 14:   */ import vb;
/* 15:   */ 
/* 16:   */ public class ItemPlan
/* 17:   */   extends up
/* 18:   */ {
/* 19:   */   public ItemPlan(int i)
/* 20:   */   {
/* 21:17 */     super(i);
/* 22:18 */     e(0);
/* 23:19 */     a(true);
/* 24:20 */     b("planFull");
/* 25:21 */     d(1);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int b(int i)
/* 29:   */   {
/* 30:25 */     return 82;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String getTextureFile()
/* 34:   */   {
/* 35:29 */     return "/eloraam/base/items1.png";
/* 36:   */   }
/* 37:   */   
/* 38:   */   @SideOnly(Side.CLIENT)
/* 39:   */   public String l(ur ist)
/* 40:   */   {
/* 41:34 */     if (ist.d == null) {
/* 42:35 */       return super.l(ist);
/* 43:   */     }
/* 44:37 */     if (!ist.d.b("result")) {
/* 45:38 */       return super.l(ist);
/* 46:   */     }
/* 47:40 */     bq res = ist.d.l("result");
/* 48:41 */     ur result = ur.a(res);
/* 49:   */     
/* 50:43 */     return result.b().l(result) + " Plan";
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void a(ur ist, qx player, List lines, boolean par4)
/* 54:   */   {
/* 55:49 */     if (ist.d == null) {
/* 56:49 */       return;
/* 57:   */     }
/* 58:50 */     by require = ist.d.m("requires");
/* 59:51 */     if (require == null) {
/* 60:51 */       return;
/* 61:   */     }
/* 62:53 */     HashMap counts = new HashMap();
/* 63:56 */     for (int i = 0; i < require.c(); i++)
/* 64:   */     {
/* 65:57 */       bq item = (bq)require.b(i);
/* 66:58 */       ur is2 = ur.a(item);
/* 67:   */       
/* 68:60 */       List l1 = Arrays.asList(new Integer[] { Integer.valueOf(is2.c), Integer.valueOf(is2.j()) });
/* 69:61 */       Integer lc = (Integer)counts.get(l1);
/* 70:62 */       if (lc == null) {
/* 71:62 */         lc = Integer.valueOf(0);
/* 72:   */       }
/* 73:63 */       counts.put(l1, Integer.valueOf(lc.intValue() + 1));
/* 74:   */     }
/* 75:66 */     for (Map.Entry kv : counts.entrySet())
/* 76:   */     {
/* 77:67 */       List li = (List)kv.getKey();
/* 78:68 */       ur i2d = new ur(((Integer)li.get(0)).intValue(), 1, ((Integer)li.get(1)).intValue());
/* 79:   */       
/* 80:70 */       lines.add(kv.getValue() + " x " + i2d.b().l(i2d));
/* 81:   */     }
/* 82:   */   }
/* 83:   */   
/* 84:   */   @SideOnly(Side.CLIENT)
/* 85:   */   public vb f(ur ist)
/* 86:   */   {
/* 87:76 */     return vb.c;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public boolean q()
/* 91:   */   {
/* 92:81 */     return true;
/* 93:   */   }
/* 94:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.base.ItemPlan
 * JD-Core Version:    0.7.0.1
 */