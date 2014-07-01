/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import la;
/*  7:   */ import yc;
/*  8:   */ 
/*  9:   */ public class BptBlockInventory
/* 10:   */   extends BptBlock
/* 11:   */ {
/* 12:   */   public BptBlockInventory(int blockId)
/* 13:   */   {
/* 14:11 */     super(blockId);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void buildBlock(BptSlotInfo slot, IBptContext context)
/* 18:   */   {
/* 19:17 */     super.buildBlock(slot, context);
/* 20:   */     
/* 21:19 */     la inv = (la)context.world().q(slot.x, slot.y, slot.z);
/* 22:21 */     for (int i = 0; i < inv.k_(); i++) {
/* 23:22 */       inv.a(i, null);
/* 24:   */     }
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockInventory
 * JD-Core Version:    0.7.0.1
 */