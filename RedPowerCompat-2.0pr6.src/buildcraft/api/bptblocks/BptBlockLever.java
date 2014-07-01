/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  4:   */ import buildcraft.api.blueprints.IBptContext;
/*  5:   */ import java.util.LinkedList;
/*  6:   */ import ur;
/*  7:   */ 
/*  8:   */ public class BptBlockLever
/*  9:   */   extends BptBlockWallSide
/* 10:   */ {
/* 11:   */   public BptBlockLever(int blockId)
/* 12:   */   {
/* 13:21 */     super(blockId);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 17:   */   {
/* 18:26 */     requirements.add(new ur(slot.blockId, 1, 0));
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 22:   */   {
/* 23:31 */     int status = slot.meta - (slot.meta & 0x7);
/* 24:   */     
/* 25:33 */     slot.meta -= status;
/* 26:34 */     super.rotateLeft(slot, context);
/* 27:35 */     slot.meta += status;
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockLever
 * JD-Core Version:    0.7.0.1
 */