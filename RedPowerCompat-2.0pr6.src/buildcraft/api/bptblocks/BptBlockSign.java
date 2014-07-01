/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import buildcraft.api.blueprints.BlockSignature;
/*  5:   */ import buildcraft.api.blueprints.BptBlock;
/*  6:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  7:   */ import buildcraft.api.blueprints.IBptContext;
/*  8:   */ import java.util.LinkedList;
/*  9:   */ import up;
/* 10:   */ import ur;
/* 11:   */ 
/* 12:   */ public class BptBlockSign
/* 13:   */   extends BptBlock
/* 14:   */ {
/* 15:   */   boolean isWall;
/* 16:   */   
/* 17:   */   public BptBlockSign(int blockId, boolean isWall)
/* 18:   */   {
/* 19:27 */     super(blockId);
/* 20:   */     
/* 21:29 */     this.isWall = isWall;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 25:   */   {
/* 26:34 */     requirements.add(new ur(up.au));
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 30:   */   {
/* 31:39 */     if (!this.isWall)
/* 32:   */     {
/* 33:40 */       double angle = slot.meta * 360.0D / 16.0D;
/* 34:41 */       angle += 90.0D;
/* 35:42 */       if (angle >= 360.0D) {
/* 36:43 */         angle -= 360.0D;
/* 37:   */       }
/* 38:45 */       slot.meta = ((int)(angle / 360.0D * 16.0D));
/* 39:   */     }
/* 40:   */   }
/* 41:   */   
/* 42:   */   public BlockSignature getSignature(amq block)
/* 43:   */   {
/* 44:53 */     BlockSignature sig = super.getSignature(block);
/* 45:55 */     if (this.isWall) {
/* 46:56 */       sig.customField = "wall";
/* 47:   */     } else {
/* 48:58 */       sig.customField = "floor";
/* 49:   */     }
/* 50:61 */     return sig;
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockSign
 * JD-Core Version:    0.7.0.1
 */