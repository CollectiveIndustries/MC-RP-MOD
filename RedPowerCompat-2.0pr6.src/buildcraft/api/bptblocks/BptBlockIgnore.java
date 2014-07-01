/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import ur;
/*  8:   */ 
/*  9:   */ public class BptBlockIgnore
/* 10:   */   extends BptBlock
/* 11:   */ {
/* 12:   */   public BptBlockIgnore(int blockId)
/* 13:   */   {
/* 14:22 */     super(blockId);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 18:   */   {
/* 19:27 */     requirements.add(new ur(slot.blockId, 0, 0));
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean isValid(BptSlotInfo slot, IBptContext context)
/* 23:   */   {
/* 24:32 */     return true;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context) {}
/* 28:   */   
/* 29:   */   public boolean ignoreBuilding(BptSlotInfo slot)
/* 30:   */   {
/* 31:42 */     return true;
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockIgnore
 * JD-Core Version:    0.7.0.1
 */