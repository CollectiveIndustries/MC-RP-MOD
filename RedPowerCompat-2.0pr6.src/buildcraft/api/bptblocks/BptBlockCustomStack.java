/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import ur;
/*  8:   */ 
/*  9:   */ public class BptBlockCustomStack
/* 10:   */   extends BptBlock
/* 11:   */ {
/* 12:   */   final ur customStack;
/* 13:   */   
/* 14:   */   public BptBlockCustomStack(int blockId, ur customStack)
/* 15:   */   {
/* 16:24 */     super(blockId);
/* 17:   */     
/* 18:26 */     this.customStack = customStack;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 22:   */   {
/* 23:31 */     requirements.add(this.customStack.l());
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockCustomStack
 * JD-Core Version:    0.7.0.1
 */