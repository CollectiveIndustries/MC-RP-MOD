/*  1:   */ package buildcraft.api.gates;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import ur;
/*  5:   */ 
/*  6:   */ public class TriggerParameter
/*  7:   */   implements ITriggerParameter
/*  8:   */ {
/*  9:   */   protected ur stack;
/* 10:   */   
/* 11:   */   public ur getItemStack()
/* 12:   */   {
/* 13:26 */     return this.stack;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void set(ur stack)
/* 17:   */   {
/* 18:36 */     if (stack != null)
/* 19:   */     {
/* 20:37 */       this.stack = stack.l();
/* 21:38 */       this.stack.a = 1;
/* 22:   */     }
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void writeToNBT(bq compound)
/* 26:   */   {
/* 27:49 */     if (this.stack != null)
/* 28:   */     {
/* 29:50 */       compound.a("itemID", this.stack.c);
/* 30:51 */       compound.a("itemDMG", this.stack.j());
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void readFromNBT(bq compound)
/* 35:   */   {
/* 36:62 */     int itemID = compound.e("itemID");
/* 37:64 */     if (itemID != 0) {
/* 38:65 */       this.stack = new ur(itemID, 1, compound.e("itemDMG"));
/* 39:   */     }
/* 40:   */   }
/* 41:   */   
/* 42:   */   public ur getItem()
/* 43:   */   {
/* 44:76 */     return this.stack;
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.TriggerParameter
 * JD-Core Version:    0.7.0.1
 */