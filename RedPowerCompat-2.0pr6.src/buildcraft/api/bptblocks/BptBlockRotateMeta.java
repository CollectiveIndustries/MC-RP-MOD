/*  1:   */ package buildcraft.api.bptblocks;
/*  2:   */ 
/*  3:   */ import buildcraft.api.blueprints.BptBlock;
/*  4:   */ import buildcraft.api.blueprints.BptSlotInfo;
/*  5:   */ import buildcraft.api.blueprints.IBptContext;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ import ur;
/*  8:   */ import yc;
/*  9:   */ 
/* 10:   */ public class BptBlockRotateMeta
/* 11:   */   extends BptBlock
/* 12:   */ {
/* 13:   */   int[] rot;
/* 14:   */   boolean rotateForward;
/* 15:24 */   int infoMask = 0;
/* 16:   */   
/* 17:   */   public BptBlockRotateMeta(int blockId, int[] rotations, boolean rotateForward)
/* 18:   */   {
/* 19:27 */     super(blockId);
/* 20:   */     
/* 21:29 */     this.rot = rotations;
/* 22:31 */     for (int i = 0; i < this.rot.length; i++) {
/* 23:32 */       if (this.rot[i] < 4) {
/* 24:33 */         this.infoMask = (this.infoMask < 3 ? 3 : this.infoMask);
/* 25:34 */       } else if (this.rot[i] < 8) {
/* 26:35 */         this.infoMask = (this.infoMask < 7 ? 7 : this.infoMask);
/* 27:36 */       } else if (this.rot[i] < 16) {
/* 28:37 */         this.infoMask = (this.infoMask < 15 ? 15 : this.infoMask);
/* 29:   */       }
/* 30:   */     }
/* 31:41 */     this.rotateForward = rotateForward;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/* 35:   */   {
/* 36:46 */     requirements.add(new ur(slot.blockId, 1, 0));
/* 37:   */   }
/* 38:   */   
/* 39:   */   public boolean isValid(BptSlotInfo slot, IBptContext context)
/* 40:   */   {
/* 41:51 */     return slot.blockId == context.world().a(slot.x, slot.y, slot.z);
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void rotateLeft(BptSlotInfo slot, IBptContext context)
/* 45:   */   {
/* 46:56 */     int pos = slot.meta & this.infoMask;
/* 47:57 */     int others = slot.meta - pos;
/* 48:59 */     if (this.rotateForward)
/* 49:   */     {
/* 50:60 */       if (pos == this.rot[0]) {
/* 51:61 */         pos = this.rot[1];
/* 52:62 */       } else if (pos == this.rot[1]) {
/* 53:63 */         pos = this.rot[2];
/* 54:64 */       } else if (pos == this.rot[2]) {
/* 55:65 */         pos = this.rot[3];
/* 56:66 */       } else if (pos == this.rot[3]) {
/* 57:67 */         pos = this.rot[0];
/* 58:   */       }
/* 59:   */     }
/* 60:70 */     else if (pos == this.rot[0]) {
/* 61:71 */       pos = this.rot[3];
/* 62:72 */     } else if (pos == this.rot[1]) {
/* 63:73 */       pos = this.rot[2];
/* 64:74 */     } else if (pos == this.rot[2]) {
/* 65:75 */       pos = this.rot[0];
/* 66:76 */     } else if (pos == this.rot[3]) {
/* 67:77 */       pos = this.rot[1];
/* 68:   */     }
/* 69:81 */     slot.meta = (pos + others);
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.bptblocks.BptBlockRotateMeta
 * JD-Core Version:    0.7.0.1
 */