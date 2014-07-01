/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import up;
/*  8:   */ import ur;
/*  9:   */ 
/* 10:   */ public class BptBlockRedstoneRepeater
/* 11:   */   extends BptBlock
/* 12:   */ {
/* 13:   */   public BptBlockRedstoneRepeater(int blockId)
/* 14:   */   {
/* 15:23 */     super(blockId);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 19:   */   {
/* 20:28 */     requirements.add(new ur(up.bb));
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 24:   */   {
/* 25:33 */     int step = slot.meta - (slot.meta & 0x3);
/* 26:35 */     switch (slot.meta - step)
/* 27:   */     {
/* 28:   */     case 0: 
/* 29:37 */       slot.meta = (1 + step);
/* 30:38 */       break;
/* 31:   */     case 1: 
/* 32:40 */       slot.meta = (2 + step);
/* 33:41 */       break;
/* 34:   */     case 2: 
/* 35:43 */       slot.meta = (3 + step);
/* 36:44 */       break;
/* 37:   */     case 3: 
/* 38:46 */       slot.meta = (0 + step);
/* 39:   */     }
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockRedstoneRepeater
 * JD-Core Version:    0.7.0.1
 */