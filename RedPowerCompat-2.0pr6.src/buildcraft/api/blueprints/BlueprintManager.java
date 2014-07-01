/*  1:   */ package buildcraft.api.blueprints;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import up;
/*  5:   */ import ur;
/*  6:   */ 
/*  7:   */ public class BlueprintManager
/*  8:   */ {
/*  9:10 */   public static BptBlock[] blockBptProps = new BptBlock[amq.p.length];
/* 10:   */   
/* 11:   */   public static ItemSignature getItemSignature(up item)
/* 12:   */   {
/* 13:13 */     ItemSignature sig = new ItemSignature();
/* 14:15 */     if (item.cj >= amq.p.length + 126) {
/* 15:16 */       sig.itemClassName = item.getClass().getSimpleName();
/* 16:   */     }
/* 17:19 */     sig.itemName = item.d(new ur(item));
/* 18:   */     
/* 19:21 */     return sig;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static BlockSignature getBlockSignature(amq block)
/* 23:   */   {
/* 24:25 */     return blockBptProps[0].getSignature(block);
/* 25:   */   }
/* 26:   */   
/* 27:   */   static
/* 28:   */   {
/* 29:30 */     for (int i = 0; i < blockBptProps.length; i++) {
/* 30:31 */       new BptBlock(i);
/* 31:   */     }
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.blueprints.BlueprintManager
 * JD-Core Version:    0.7.0.1
 */