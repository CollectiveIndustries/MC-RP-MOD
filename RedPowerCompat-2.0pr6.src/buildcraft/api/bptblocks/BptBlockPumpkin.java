/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import ur;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class BptBlockPumpkin
/* 11:   */   extends BptBlock
/* 12:   */ {
/* 13:   */   public BptBlockPumpkin(int blockId)
/* 14:   */   {
/* 15:22 */     super(blockId);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 19:   */   {
/* 20:27 */     requirements.add(new ur(slot.blockId, 1, 0));
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean isValid(BptSlotInfo slot, IBptContext context)
/* 24:   */   {
/* 25:32 */     return slot.blockId == context.world().a(slot.x, slot.y, slot.z);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 29:   */   {
/* 30:37 */     switch (slot.meta)
/* 31:   */     {
/* 32:   */     case 0: 
/* 33:39 */       slot.meta = 1;
/* 34:40 */       break;
/* 35:   */     case 1: 
/* 36:42 */       slot.meta = 2;
/* 37:43 */       break;
/* 38:   */     case 2: 
/* 39:45 */       slot.meta = 3;
/* 40:46 */       break;
/* 41:   */     case 3: 
/* 42:48 */       slot.meta = 0;
/* 43:   */     }
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockPumpkin
 * JD-Core Version:    0.7.0.1
 */