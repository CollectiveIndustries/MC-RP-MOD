/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import ur;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class BptBlockLiquid
/* 11:   */   extends BptBlock
/* 12:   */ {
/* 13:   */   private final ur bucketStack;
/* 14:   */   
/* 15:   */   public BptBlockLiquid(int blockId, ur bucketStack)
/* 16:   */   {
/* 17:24 */     super(blockId);
/* 18:   */     
/* 19:26 */     this.bucketStack = bucketStack;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 23:   */   {
/* 24:31 */     if (slot.meta == 0) {
/* 25:32 */       requirements.add(this.bucketStack.l());
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public boolean isValid(BptSlotInfo slot, IBptContext context)
/* 30:   */   {
/* 31:38 */     if (slot.meta == 0) {
/* 32:39 */       return (slot.blockId == context.world().a(slot.x, slot.y, slot.z)) && (context.world().h(slot.x, slot.y, slot.z) == 0);
/* 33:   */     }
/* 34:41 */     return true;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context) {}
/* 38:   */   
/* 39:   */   public boolean ignoreBuilding(BptSlotInfo slot)
/* 40:   */   {
/* 41:51 */     return slot.meta != 0;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void buildBlock(BptSlotInfo slot, IBptContext context)
/* 45:   */   {
/* 46:56 */     if (slot.meta == 0) {
/* 47:57 */       context.world().d(slot.x, slot.y, slot.z, slot.blockId, 0);
/* 48:   */     }
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockLiquid
 * JD-Core Version:    0.7.0.1
 */