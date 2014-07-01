/*  1:   */ package buildcraft.api.recipes;
/*  2:   */ 
/*  3:   */ import java.util.LinkedList;
/*  4:   */ import ur;
/*  5:   */ 
/*  6:   */ public class AssemblyRecipe
/*  7:   */ {
/*  8: 9 */   public static LinkedList assemblyRecipes = new LinkedList();
/*  9:   */   public final ur[] input;
/* 10:   */   public final ur output;
/* 11:   */   public final float energy;
/* 12:   */   
/* 13:   */   public AssemblyRecipe(ur[] input, int energy, ur output)
/* 14:   */   {
/* 15:16 */     this.input = input;
/* 16:17 */     this.output = output;
/* 17:18 */     this.energy = energy;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public boolean canBeDone(ur[] items)
/* 21:   */   {
/* 22:23 */     for (ur in : this.input) {
/* 23:25 */       if (in != null)
/* 24:   */       {
/* 25:29 */         int found = 0;
/* 26:31 */         for (ur item : items) {
/* 27:32 */           if (item != null) {
/* 28:36 */             if (item.a(in)) {
/* 29:37 */               found += item.a;
/* 30:   */             }
/* 31:   */           }
/* 32:   */         }
/* 33:42 */         if (found < in.a) {
/* 34:43 */           return false;
/* 35:   */         }
/* 36:   */       }
/* 37:   */     }
/* 38:47 */     return true;
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.recipes.AssemblyRecipe
 * JD-Core Version:    0.7.0.1
 */