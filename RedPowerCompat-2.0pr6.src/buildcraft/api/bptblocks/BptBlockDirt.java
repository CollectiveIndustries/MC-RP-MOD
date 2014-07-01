/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import akj;
/*  4:   */ import amq;
/*  5:   */ import buildcraft.api.blueprints.BptBlock;
/*  6:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  7:   */ import buildcraft.api.blueprints.IBptContext;
/*  8:   */ import java.util.LinkedList;
/*  9:   */ import ur;
/* 10:   */ import yc;
/* 11:   */ 
/* 12:   */ public class BptBlockDirt
/* 13:   */   extends BptBlock
/* 14:   */ {
/* 15:   */   public BptBlockDirt(int blockId)
/* 16:   */   {
/* 17:23 */     super(blockId);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 21:   */   {
/* 22:28 */     requirements.add(new ur(amq.y));
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void buildBlock(BptSlotInfo slot, IBptContext context)
/* 26:   */   {
/* 27:33 */     context.world().d(slot.x, slot.y, slot.z, amq.y.cm, slot.meta);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean isValid(BptSlotInfo slot, IBptContext context)
/* 31:   */   {
/* 32:38 */     int id = context.world().a(slot.x, slot.y, slot.z);
/* 33:   */     
/* 34:40 */     return (id == amq.y.cm) || (id == amq.x.cm) || (id == amq.aD.cm);
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockDirt
 * JD-Core Version:    0.7.0.1
 */