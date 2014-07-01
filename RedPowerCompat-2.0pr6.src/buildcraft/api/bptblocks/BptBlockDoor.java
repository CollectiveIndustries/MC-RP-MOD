/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import ur;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class BptBlockDoor
/* 11:   */   extends BptBlock
/* 12:   */ {
/* 13:   */   final ur stack;
/* 14:   */   
/* 15:   */   public BptBlockDoor(int blockId, ur stack)
/* 16:   */   {
/* 17:24 */     super(blockId);
/* 18:   */     
/* 19:26 */     this.stack = stack;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 23:   */   {
/* 24:31 */     if ((slot.meta & 0x8) == 0) {
/* 25:32 */       requirements.add(this.stack.l());
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 30:   */   {
/* 31:38 */     int orientation = slot.meta & 0x3;
/* 32:39 */     int others = slot.meta - orientation;
/* 33:41 */     switch (orientation)
/* 34:   */     {
/* 35:   */     case 0: 
/* 36:43 */       slot.meta = (1 + others);
/* 37:44 */       break;
/* 38:   */     case 1: 
/* 39:46 */       slot.meta = (2 + others);
/* 40:47 */       break;
/* 41:   */     case 2: 
/* 42:49 */       slot.meta = (3 + others);
/* 43:50 */       break;
/* 44:   */     case 3: 
/* 45:52 */       slot.meta = (0 + others);
/* 46:   */     }
/* 47:   */   }
/* 48:   */   
/* 49:   */   public boolean ignoreBuilding(BptSlotInfo slot)
/* 50:   */   {
/* 51:59 */     return (slot.meta & 0x8) != 0;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void buildBlock(BptSlotInfo slot, IBptContext context)
/* 55:   */   {
/* 56:64 */     context.world().d(slot.x, slot.y, slot.z, slot.blockId, slot.meta);
/* 57:65 */     context.world().d(slot.x, slot.y + 1, slot.z, slot.blockId, slot.meta + 8);
/* 58:   */     
/* 59:67 */     context.world().c(slot.x, slot.y + 1, slot.z, slot.meta + 8);
/* 60:68 */     context.world().c(slot.x, slot.y, slot.z, slot.meta);
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockDoor
 * JD-Core Version:    0.7.0.1
 */