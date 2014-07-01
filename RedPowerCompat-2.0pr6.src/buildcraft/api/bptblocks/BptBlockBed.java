/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import up;
/*  8:   */ import ur;
/*  9:   */ import yc;
/* 10:   */ 
/* 11:   */ public class BptBlockBed
/* 12:   */   extends BptBlock
/* 13:   */ {
/* 14:   */   public BptBlockBed(int blockId)
/* 15:   */   {
/* 16:23 */     super(blockId);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 20:   */   {
/* 21:28 */     if ((slot.meta & 0x8) == 0) {
/* 22:29 */       requirements.add(new ur(up.ba));
/* 23:   */     }
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 27:   */   {
/* 28:35 */     int orientation = slot.meta & 0x7;
/* 29:36 */     int others = slot.meta - orientation;
/* 30:38 */     switch (orientation)
/* 31:   */     {
/* 32:   */     case 0: 
/* 33:40 */       slot.meta = (1 + others);
/* 34:41 */       break;
/* 35:   */     case 1: 
/* 36:43 */       slot.meta = (2 + others);
/* 37:44 */       break;
/* 38:   */     case 2: 
/* 39:46 */       slot.meta = (3 + others);
/* 40:47 */       break;
/* 41:   */     case 3: 
/* 42:49 */       slot.meta = (0 + others);
/* 43:   */     }
/* 44:   */   }
/* 45:   */   
/* 46:   */   public void buildBlock(BptSlotInfo slot, IBptContext context)
/* 47:   */   {
/* 48:56 */     if ((slot.meta & 0x8) != 0) {
/* 49:57 */       return;
/* 50:   */     }
/* 51:59 */     context.world().d(slot.x, slot.y, slot.z, slot.blockId, slot.meta);
/* 52:   */     
/* 53:61 */     int x2 = slot.x;
/* 54:62 */     int z2 = slot.z;
/* 55:64 */     switch (slot.meta)
/* 56:   */     {
/* 57:   */     case 0: 
/* 58:66 */       z2++;
/* 59:67 */       break;
/* 60:   */     case 1: 
/* 61:69 */       x2--;
/* 62:70 */       break;
/* 63:   */     case 2: 
/* 64:72 */       z2--;
/* 65:73 */       break;
/* 66:   */     case 3: 
/* 67:75 */       x2++;
/* 68:   */     }
/* 69:79 */     context.world().d(x2, slot.y, z2, slot.blockId, slot.meta + 8);
/* 70:   */   }
/* 71:   */   
/* 72:   */   public boolean ignoreBuilding(BptSlotInfo slot)
/* 73:   */   {
/* 74:84 */     return (slot.meta & 0x8) != 0;
/* 75:   */   }
/* 76:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockBed
 * JD-Core Version:    0.7.0.1
 */