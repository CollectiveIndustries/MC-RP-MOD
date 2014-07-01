/*  1:   */ package buildcraft.api.blueprints;
/*  2:   */ 
/*  3:   */ public class ItemSignature
/*  4:   */ {
/*  5:   */   public String itemClassName;
/*  6:   */   public String itemName;
/*  7:   */   
/*  8:   */   public ItemSignature(String str)
/*  9:   */   {
/* 10:18 */     String[] values = str.split("/");
/* 11:   */     
/* 12:20 */     this.itemClassName = values[1];
/* 13:21 */     this.itemName = values[2];
/* 14:   */     
/* 15:23 */     replaceNullWithStar();
/* 16:   */   }
/* 17:   */   
/* 18:   */   public ItemSignature()
/* 19:   */   {
/* 20:28 */     replaceNullWithStar();
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String toString()
/* 24:   */   {
/* 25:33 */     replaceNullWithStar();
/* 26:   */     
/* 27:35 */     return "#I/" + this.itemClassName + "/" + this.itemName;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void replaceNullWithStar()
/* 31:   */   {
/* 32:39 */     if (this.itemClassName == null) {
/* 33:40 */       this.itemClassName = "*";
/* 34:   */     }
/* 35:43 */     if (this.itemName == null) {
/* 36:44 */       this.itemName = "*";
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.blueprints.ItemSignature
 * JD-Core Version:    0.7.0.1
 */