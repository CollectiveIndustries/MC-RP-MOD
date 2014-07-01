/*  1:   */ package buildcraft.api.blueprints;
/*  2:   */ 
/*  3:   */ public class BlockSignature
/*  4:   */ {
/*  5:   */   public String blockClassName;
/*  6:   */   public String tileClassName;
/*  7:   */   public String blockName;
/*  8:   */   public String mod;
/*  9:   */   public String modVersion;
/* 10:   */   public String customField;
/* 11:   */   
/* 12:   */   public BlockSignature(String str)
/* 13:   */   {
/* 14:22 */     String[] values = str.split("/");
/* 15:   */     
/* 16:24 */     int i = 0;
/* 17:26 */     if (values[0].equals("#B")) {
/* 18:27 */       i++;
/* 19:   */     }
/* 20:30 */     this.blockClassName = values[i];
/* 21:31 */     this.tileClassName = values[(i + 1)];
/* 22:32 */     this.blockName = values[(i + 2)];
/* 23:33 */     this.mod = values[(i + 3)];
/* 24:34 */     this.modVersion = values[(i + 4)];
/* 25:35 */     this.customField = values[(i + 5)];
/* 26:   */     
/* 27:37 */     replaceNullWithStar();
/* 28:   */   }
/* 29:   */   
/* 30:   */   public BlockSignature()
/* 31:   */   {
/* 32:42 */     replaceNullWithStar();
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String toString()
/* 36:   */   {
/* 37:47 */     replaceNullWithStar();
/* 38:   */     
/* 39:49 */     return "#B/" + this.blockClassName + "/" + this.tileClassName + "/" + this.blockName + "/" + this.mod + "/" + this.modVersion + "/" + this.customField;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void replaceNullWithStar()
/* 43:   */   {
/* 44:53 */     if (this.blockClassName == null) {
/* 45:54 */       this.blockClassName = "*";
/* 46:   */     }
/* 47:57 */     if (this.tileClassName == null) {
/* 48:58 */       this.tileClassName = "*";
/* 49:   */     }
/* 50:61 */     if (this.blockName == null) {
/* 51:62 */       this.blockName = "*";
/* 52:   */     }
/* 53:65 */     if (this.mod == null) {
/* 54:66 */       this.mod = "*";
/* 55:   */     }
/* 56:69 */     if (this.modVersion == null) {
/* 57:70 */       this.modVersion = "*";
/* 58:   */     }
/* 59:73 */     if (this.customField == null) {
/* 60:74 */       this.customField = "*";
/* 61:   */     }
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.blueprints.BlockSignature
 * JD-Core Version:    0.7.0.1
 */