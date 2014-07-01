/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ 
/*  8:   */ public class BptBlockDelegate
/*  9:   */   extends BptBlock
/* 10:   */ {
/* 11:   */   final int delegateTo;
/* 12:   */   
/* 13:   */   public BptBlockDelegate(int blockId, int delegateTo)
/* 14:   */   {
/* 15:25 */     super(blockId);
/* 16:   */     
/* 17:27 */     this.delegateTo = delegateTo;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 21:   */   {
/* 22:32 */     BptSlotInfo newSlot = slot.clone();
/* 23:33 */     slot.blockId = this.delegateTo;
/* 24:35 */     if (buildcraft.api.blueprints.BlueprintManager.blockBptProps[this.delegateTo] != null) {
/* 25:36 */       buildcraft.api.blueprints.BlueprintManager.blockBptProps[this.delegateTo].addRequirements(newSlot, context, requirements);
/* 26:   */     } else {
/* 27:38 */       super.addRequirements(newSlot, context, requirements);
/* 28:   */     }
/* 29:   */   }
/* 30:   */   
/* 31:   */   public boolean isValid(BptSlotInfo slot, IBptContext context)
/* 32:   */   {
/* 33:44 */     BptSlotInfo newSlot = slot.clone();
/* 34:45 */     slot.blockId = this.delegateTo;
/* 35:47 */     if (buildcraft.api.blueprints.BlueprintManager.blockBptProps[this.delegateTo] != null) {
/* 36:48 */       return buildcraft.api.blueprints.BlueprintManager.blockBptProps[this.delegateTo].isValid(newSlot, context);
/* 37:   */     }
/* 38:50 */     return super.isValid(newSlot, context);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 42:   */   {
/* 43:55 */     BptSlotInfo newSlot = slot.clone();
/* 44:56 */     slot.blockId = this.delegateTo;
/* 45:58 */     if (buildcraft.api.blueprints.BlueprintManager.blockBptProps[this.delegateTo] != null) {
/* 46:59 */       buildcraft.api.blueprints.BlueprintManager.blockBptProps[this.delegateTo].rotateLeft(newSlot, context);
/* 47:   */     } else {
/* 48:61 */       super.rotateLeft(newSlot, context);
/* 49:   */     }
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockDelegate
 * JD-Core Version:    0.7.0.1
 */