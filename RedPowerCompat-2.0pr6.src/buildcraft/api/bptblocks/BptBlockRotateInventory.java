/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  4:   */ import buildcraft.api.blueprints.IBptContext;
/*  5:   */ import la;
/*  6:   */ import yc;
/*  7:   */ 
/*  8:   */ public class BptBlockRotateInventory
/*  9:   */   extends BptBlockRotateMeta
/* 10:   */ {
/* 11:   */   public BptBlockRotateInventory(int blockId, int[] rotations, boolean rotateForward)
/* 12:   */   {
/* 13:10 */     super(blockId, rotations, rotateForward);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void buildBlock(BptSlotInfo slot, IBptContext context)
/* 17:   */   {
/* 18:16 */     super.buildBlock(slot, context);
/* 19:   */     
/* 20:18 */     la inv = (la)context.world().q(slot.x, slot.y, slot.z);
/* 21:20 */     for (int i = 0; i < inv.k_(); i++) {
/* 22:21 */       inv.a(i, null);
/* 23:   */     }
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockRotateInventory
 * JD-Core Version:    0.7.0.1
 */