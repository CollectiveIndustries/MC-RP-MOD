/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  4:   */ import buildcraft.api.blueprints.IBptContext;
/*  5:   */ import yc;
/*  6:   */ 
/*  7:   */ public class BptBlockPiston
/*  8:   */   extends BptBlockRotateMeta
/*  9:   */ {
/* 10:   */   public BptBlockPiston(int blockId)
/* 11:   */   {
/* 12:18 */     super(blockId, new int[] { 2, 5, 3, 4 }, true);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void buildBlock(BptSlotInfo slot, IBptContext context)
/* 16:   */   {
/* 17:23 */     int meta = slot.meta & 0x7;
/* 18:   */     
/* 19:25 */     context.world().d(slot.x, slot.y, slot.z, slot.blockId, meta);
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockPiston
 * JD-Core Version:    0.7.0.1
 */