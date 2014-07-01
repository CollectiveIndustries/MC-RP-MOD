/*   1:    */ package buildcraft.api.recipes;
/*   2:    */ 
/*   3:    */ import java.util.Collections;
/*   4:    */ import java.util.SortedSet;
/*   5:    */ import java.util.TreeSet;
/*   6:    */ import net.minecraftforge.liquids.LiquidStack;
/*   7:    */ 
/*   8:    */ public class RefineryRecipe
/*   9:    */   implements Comparable
/*  10:    */ {
/*  11: 20 */   private static SortedSet recipes = new TreeSet();
/*  12:    */   public final LiquidStack ingredient1;
/*  13:    */   public final LiquidStack ingredient2;
/*  14:    */   public final LiquidStack result;
/*  15:    */   public final int energy;
/*  16:    */   public final int delay;
/*  17:    */   
/*  18:    */   public static void registerRefineryRecipe(RefineryRecipe recipe)
/*  19:    */   {
/*  20: 23 */     if (!recipes.contains(recipe)) {
/*  21: 24 */       recipes.add(recipe);
/*  22:    */     }
/*  23:    */   }
/*  24:    */   
/*  25:    */   public static SortedSet getRecipes()
/*  26:    */   {
/*  27: 29 */     return Collections.unmodifiableSortedSet(recipes);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public static RefineryRecipe findRefineryRecipe(LiquidStack liquid1, LiquidStack liquid2)
/*  31:    */   {
/*  32: 33 */     for (RefineryRecipe recipe : recipes) {
/*  33: 34 */       if (recipe.matches(liquid1, liquid2)) {
/*  34: 35 */         return recipe;
/*  35:    */       }
/*  36:    */     }
/*  37: 37 */     return null;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public RefineryRecipe(int ingredientId1, int ingredientQty1, int ingredientId2, int ingredientQty2, int resultId, int resultQty, int energy, int delay)
/*  41:    */   {
/*  42: 48 */     this(new LiquidStack(ingredientId1, ingredientQty1, 0), new LiquidStack(ingredientId2, ingredientQty2, 0), new LiquidStack(resultId, resultQty, 0), energy, delay);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public RefineryRecipe(LiquidStack ingredient1, LiquidStack ingredient2, LiquidStack result, int energy, int delay)
/*  46:    */   {
/*  47: 55 */     if ((ingredient1 != null) && (ingredient2 != null))
/*  48:    */     {
/*  49: 56 */       if ((ingredient1.itemID > ingredient2.itemID) || ((ingredient1.itemID == ingredient2.itemID) && (ingredient1.itemMeta > ingredient2.itemMeta)))
/*  50:    */       {
/*  51: 57 */         this.ingredient1 = ingredient2;
/*  52: 58 */         this.ingredient2 = ingredient1;
/*  53:    */       }
/*  54:    */       else
/*  55:    */       {
/*  56: 60 */         this.ingredient1 = ingredient1;
/*  57: 61 */         this.ingredient2 = ingredient2;
/*  58:    */       }
/*  59:    */     }
/*  60: 63 */     else if (ingredient2 != null)
/*  61:    */     {
/*  62: 64 */       this.ingredient1 = ingredient2;
/*  63: 65 */       this.ingredient2 = ingredient1;
/*  64:    */     }
/*  65:    */     else
/*  66:    */     {
/*  67: 67 */       this.ingredient1 = ingredient1;
/*  68: 68 */       this.ingredient2 = ingredient2;
/*  69:    */     }
/*  70: 71 */     this.result = result;
/*  71: 72 */     this.energy = energy;
/*  72: 73 */     this.delay = delay;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public boolean matches(LiquidStack liquid1, LiquidStack liquid2)
/*  76:    */   {
/*  77: 79 */     if ((liquid1 == null) && (liquid2 == null)) {
/*  78: 80 */       return false;
/*  79:    */     }
/*  80: 83 */     if ((this.ingredient1 != null) && (this.ingredient2 != null) && ((liquid1 == null) || (liquid2 == null))) {
/*  81: 84 */       return false;
/*  82:    */     }
/*  83: 86 */     if (this.ingredient1 != null)
/*  84:    */     {
/*  85: 88 */       if (this.ingredient2 == null) {
/*  86: 89 */         return (this.ingredient1.isLiquidEqual(liquid1)) || (this.ingredient1.isLiquidEqual(liquid2));
/*  87:    */       }
/*  88: 91 */       return ((this.ingredient1.isLiquidEqual(liquid1)) && (this.ingredient2.isLiquidEqual(liquid2))) || ((this.ingredient2.isLiquidEqual(liquid1)) && (this.ingredient1.isLiquidEqual(liquid2)));
/*  89:    */     }
/*  90: 94 */     if (this.ingredient2 != null) {
/*  91: 95 */       return (this.ingredient2.isLiquidEqual(liquid1)) || (this.ingredient2.isLiquidEqual(liquid2));
/*  92:    */     }
/*  93: 97 */     return false;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public int compareTo(RefineryRecipe other)
/*  97:    */   {
/*  98:107 */     if (other == null) {
/*  99:108 */       return -1;
/* 100:    */     }
/* 101:109 */     if (this.ingredient1 == null)
/* 102:    */     {
/* 103:110 */       if (other.ingredient1 == null) {
/* 104:111 */         return 0;
/* 105:    */       }
/* 106:113 */       return 1;
/* 107:    */     }
/* 108:114 */     if (other.ingredient1 == null) {
/* 109:115 */       return -1;
/* 110:    */     }
/* 111:116 */     if (this.ingredient1.itemID != other.ingredient1.itemID) {
/* 112:117 */       return this.ingredient1.itemID - other.ingredient1.itemID;
/* 113:    */     }
/* 114:118 */     if (this.ingredient1.itemMeta != other.ingredient1.itemMeta) {
/* 115:119 */       return this.ingredient1.itemMeta - other.ingredient1.itemMeta;
/* 116:    */     }
/* 117:120 */     if (this.ingredient2 == null)
/* 118:    */     {
/* 119:121 */       if (other.ingredient2 == null) {
/* 120:122 */         return 0;
/* 121:    */       }
/* 122:124 */       return 1;
/* 123:    */     }
/* 124:125 */     if (other.ingredient2 == null) {
/* 125:126 */       return -1;
/* 126:    */     }
/* 127:127 */     if (this.ingredient2.itemID != other.ingredient2.itemID) {
/* 128:128 */       return this.ingredient2.itemID - other.ingredient2.itemID;
/* 129:    */     }
/* 130:129 */     if (this.ingredient2.itemMeta != other.ingredient2.itemMeta) {
/* 131:130 */       return this.ingredient2.itemMeta - other.ingredient2.itemMeta;
/* 132:    */     }
/* 133:132 */     return 0;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public boolean equals(Object obj)
/* 137:    */   {
/* 138:138 */     if ((obj != null) && ((obj instanceof RefineryRecipe))) {
/* 139:139 */       return compareTo((RefineryRecipe)obj) == 0;
/* 140:    */     }
/* 141:140 */     return false;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public int hashCode()
/* 145:    */   {
/* 146:146 */     if (this.ingredient1 == null) {
/* 147:147 */       return 0;
/* 148:    */     }
/* 149:148 */     if (this.ingredient2 == null) {
/* 150:149 */       return this.ingredient1.itemID ^ this.ingredient1.itemMeta;
/* 151:    */     }
/* 152:151 */     return this.ingredient1.itemID ^ this.ingredient1.itemMeta ^ this.ingredient2.itemID ^ this.ingredient2.itemMeta;
/* 153:    */   }
/* 154:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.recipes.RefineryRecipe
 * JD-Core Version:    0.7.0.1
 */