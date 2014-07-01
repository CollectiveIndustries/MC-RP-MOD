/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Arrays;
/*   5:    */ import java.util.HashMap;
/*   6:    */ import java.util.HashSet;
/*   7:    */ import java.util.Iterator;
/*   8:    */ import java.util.List;
/*   9:    */ import net.minecraftforge.oredict.OreDictionary;
/*  10:    */ import net.minecraftforge.oredict.ShapedOreRecipe;
/*  11:    */ import net.minecraftforge.oredict.ShapelessOreRecipe;
/*  12:    */ import up;
/*  13:    */ import ur;
/*  14:    */ import wn;
/*  15:    */ 
/*  16:    */ public class CraftLib
/*  17:    */ {
/*  18:    */   public static void addAlloyResult(ur output, Object... input)
/*  19:    */   {
/*  20: 16 */     alloyRecipes.add(Arrays.asList(new Object[] { input, output }));
/*  21:    */   }
/*  22:    */   
/*  23:    */   public static void addOreRecipe(ur output, Object... input)
/*  24:    */   {
/*  25: 20 */     wn.a().b().add(new ShapedOreRecipe(output, new Object[] { Boolean.valueOf(true), input }));
/*  26:    */   }
/*  27:    */   
/*  28:    */   public static void addShapelessOreRecipe(ur output, Object... input)
/*  29:    */   {
/*  30: 27 */     wn.a().b().add(new ShapelessOreRecipe(output, input));
/*  31:    */   }
/*  32:    */   
/*  33:    */   public static boolean isOreClass(ur ist, String ore)
/*  34:    */   {
/*  35: 32 */     ArrayList ores = OreDictionary.getOres(ore);
/*  36: 34 */     for (ur ois : ores) {
/*  37: 35 */       if (ois.a(ist)) {
/*  38: 35 */         return true;
/*  39:    */       }
/*  40:    */     }
/*  41: 37 */     return false;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public static ur getAlloyResult(ur[] input, int ofs, int len, boolean decr)
/*  45:    */   {
/*  46: 43 */     Iterator i$ = alloyRecipes.iterator();
/*  47: 43 */     if (i$.hasNext())
/*  48:    */     {
/*  49: 43 */       List l = (List)i$.next();
/*  50: 44 */       Object[] ob = l.toArray();
/*  51: 45 */       Object[] ipt = (Object[])ob[0];
/*  52: 46 */       Object[] arr$ = ipt;int len$ = arr$.length;
/*  53: 46 */       for (int i$ = 0;; i$++)
/*  54:    */       {
/*  55: 46 */         if (i$ >= len$) {
/*  56:    */           break label265;
/*  57:    */         }
/*  58: 46 */         Object iso1 = arr$[i$];
/*  59: 47 */         if ((iso1 instanceof ur))
/*  60:    */         {
/*  61: 48 */           ur is1 = (ur)iso1;
/*  62: 49 */           int rc = is1.a;
/*  63: 50 */           for (int i = ofs; i < len; i++) {
/*  64: 51 */             if (input[i] != null)
/*  65:    */             {
/*  66: 52 */               if (input[i].a(is1)) {
/*  67: 53 */                 rc -= input[i].a;
/*  68:    */               }
/*  69: 54 */               if (rc <= 0) {
/*  70:    */                 break;
/*  71:    */               }
/*  72:    */             }
/*  73:    */           }
/*  74: 56 */           if (rc > 0) {
/*  75:    */             break;
/*  76:    */           }
/*  77: 57 */           continue;
/*  78:    */         }
/*  79: 57 */         if ((iso1 instanceof OreStack))
/*  80:    */         {
/*  81: 58 */           OreStack os1 = (OreStack)iso1;
/*  82: 59 */           int rc = os1.quantity;
/*  83: 60 */           for (int i = ofs; i < len; i++) {
/*  84: 61 */             if (input[i] != null)
/*  85:    */             {
/*  86: 62 */               if (isOreClass(input[i], os1.material)) {
/*  87: 63 */                 rc -= input[i].a;
/*  88:    */               }
/*  89: 64 */               if (rc <= 0) {
/*  90:    */                 break;
/*  91:    */               }
/*  92:    */             }
/*  93:    */           }
/*  94: 66 */           if (rc > 0) {
/*  95:    */             break;
/*  96:    */           }
/*  97:    */         }
/*  98:    */       }
/*  99:    */       label265:
/* 100: 69 */       if (decr) {
/* 101: 69 */         for (Object iso1 : ipt) {
/* 102: 70 */           if ((iso1 instanceof ur))
/* 103:    */           {
/* 104: 71 */             ur is1 = (ur)iso1;
/* 105: 72 */             int rc = is1.a;
/* 106: 73 */             for (int i = ofs; i < len; i++) {
/* 107: 74 */               if ((input[i] != null) && 
/* 108: 75 */                 (input[i].a(is1)))
/* 109:    */               {
/* 110: 77 */                 rc -= input[i].a;
/* 111: 78 */                 if (rc < 0) {
/* 112: 79 */                   input[i].a = (-rc);
/* 113: 80 */                 } else if (input[i].b().s()) {
/* 114: 82 */                   input[i] = new ur(input[i].b().r());
/* 115:    */                 } else {
/* 116: 83 */                   input[i] = null;
/* 117:    */                 }
/* 118: 85 */                 if (rc <= 0) {
/* 119:    */                   break;
/* 120:    */                 }
/* 121:    */               }
/* 122:    */             }
/* 123:    */           }
/* 124: 87 */           else if ((iso1 instanceof OreStack))
/* 125:    */           {
/* 126: 88 */             OreStack os1 = (OreStack)iso1;
/* 127: 89 */             int rc = os1.quantity;
/* 128: 90 */             for (int i = ofs; i < len; i++) {
/* 129: 91 */               if ((input[i] != null) && 
/* 130: 92 */                 (isOreClass(input[i], os1.material)))
/* 131:    */               {
/* 132: 94 */                 rc -= input[i].a;
/* 133: 95 */                 if (rc < 0) {
/* 134: 96 */                   input[i].a = (-rc);
/* 135:    */                 } else {
/* 136: 97 */                   input[i] = null;
/* 137:    */                 }
/* 138: 99 */                 if (rc <= 0) {
/* 139:    */                   break;
/* 140:    */                 }
/* 141:    */               }
/* 142:    */             }
/* 143:    */           }
/* 144:    */         }
/* 145:    */       }
/* 146:103 */       return (ur)ob[1];
/* 147:    */     }
/* 148:105 */     return null;
/* 149:    */   }
/* 150:    */   
/* 151:119 */   static List alloyRecipes = new ArrayList();
/* 152:120 */   public static HashSet damageOnCraft = new HashSet();
/* 153:121 */   public static HashMap damageContainer = new HashMap();
/* 154:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CraftLib
 * JD-Core Version:    0.7.0.1
 */