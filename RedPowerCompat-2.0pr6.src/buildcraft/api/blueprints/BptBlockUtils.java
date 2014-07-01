/*  1:   */ package buildcraft.api.blueprints;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import by;
/*  5:   */ import java.util.LinkedList;
/*  6:   */ import la;
/*  7:   */ import ur;
/*  8:   */ 
/*  9:   */ public class BptBlockUtils
/* 10:   */ {
/* 11:   */   public static void requestInventoryContents(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 12:   */   {
/* 13:22 */     ur[] stacks = getItemStacks(slot, context);
/* 14:24 */     for (ur stack : stacks) {
/* 15:25 */       if (stack != null) {
/* 16:26 */         requirements.add(stack);
/* 17:   */       }
/* 18:   */     }
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static void initializeInventoryContents(BptSlotInfo slot, IBptContext context, la inventory)
/* 22:   */   {
/* 23:32 */     ur[] stacks = new ur[inventory.k_()];
/* 24:34 */     for (int i = 0; i < inventory.k_(); i++) {
/* 25:35 */       stacks[i] = inventory.a(i);
/* 26:   */     }
/* 27:38 */     setItemStacks(slot, context, stacks);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public static void buildInventoryContents(BptSlotInfo slot, IBptContext context, la inventory)
/* 31:   */   {
/* 32:42 */     ur[] stacks = getItemStacks(slot, context);
/* 33:44 */     for (int i = 0; i < stacks.length; i++) {
/* 34:45 */       inventory.a(i, stacks[i]);
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public static ur[] getItemStacks(BptSlotInfo slot, IBptContext context)
/* 39:   */   {
/* 40:50 */     by list = (by)slot.cpt.a("inv");
/* 41:52 */     if (list == null) {
/* 42:53 */       return new ur[0];
/* 43:   */     }
/* 44:55 */     ur[] stacks = new ur[list.c()];
/* 45:57 */     for (int i = 0; i < list.c(); i++)
/* 46:   */     {
/* 47:58 */       ur stack = ur.a((bq)list.b(i));
/* 48:60 */       if ((stack != null) && (stack.c != 0) && (stack.a > 0)) {
/* 49:61 */         stacks[i] = context.mapItemStack(stack);
/* 50:   */       }
/* 51:   */     }
/* 52:65 */     return stacks;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public static void setItemStacks(BptSlotInfo slot, IBptContext context, ur[] stacks)
/* 56:   */   {
/* 57:69 */     by nbttaglist = new by();
/* 58:71 */     for (int i = 0; i < stacks.length; i++)
/* 59:   */     {
/* 60:72 */       bq cpt = new bq();
/* 61:73 */       nbttaglist.a(cpt);
/* 62:74 */       ur stack = stacks[i];
/* 63:76 */       if ((stack != null) && (stack.a != 0))
/* 64:   */       {
/* 65:77 */         stack.b(cpt);
/* 66:78 */         context.storeId(stack.c);
/* 67:   */       }
/* 68:   */     }
/* 69:82 */     slot.cpt.a("inv", nbttaglist);
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.blueprints.BptBlockUtils
 * JD-Core Version:    0.7.0.1
 */