/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import ur;
/*  8:   */ 
/*  9:   */ public class BptBlockWallSide
/* 10:   */   extends BptBlock
/* 11:   */ {
/* 12:   */   public BptBlockWallSide(int blockId)
/* 13:   */   {
/* 14:22 */     super(blockId);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 18:   */   {
/* 19:27 */     requirements.add(new ur(slot.blockId, 1, 0));
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 23:   */   {
/* 24:32 */     int XPos = 2;
/* 25:33 */     int XNeg = 1;
/* 26:34 */     int ZPos = 4;
/* 27:35 */     int ZNeg = 3;
/* 28:37 */     switch (slot.meta)
/* 29:   */     {
/* 30:   */     case 2: 
/* 31:39 */       slot.meta = 4;
/* 32:40 */       break;
/* 33:   */     case 3: 
/* 34:42 */       slot.meta = 2;
/* 35:43 */       break;
/* 36:   */     case 1: 
/* 37:45 */       slot.meta = 3;
/* 38:46 */       break;
/* 39:   */     case 4: 
/* 40:48 */       slot.meta = 1;
/* 41:   */     }
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockWallSide
 * JD-Core Version:    0.7.0.1
 */