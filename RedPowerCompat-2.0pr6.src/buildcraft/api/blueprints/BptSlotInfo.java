/*  1:   */ package buildcraft.api.blueprints;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import java.util.LinkedList;
/*  5:   */ 
/*  6:   */ public class BptSlotInfo
/*  7:   */ {
/*  8:22 */   public int blockId = 0;
/*  9:23 */   public int meta = 0;
/* 10:   */   public int x;
/* 11:   */   public int y;
/* 12:   */   public int z;
/* 13:31 */   public LinkedList storedRequirements = new LinkedList();
/* 14:37 */   public bq cpt = new bq();
/* 15:   */   
/* 16:   */   public BptSlotInfo clone()
/* 17:   */   {
/* 18:41 */     BptSlotInfo obj = new BptSlotInfo();
/* 19:   */     
/* 20:43 */     obj.x = this.x;
/* 21:44 */     obj.y = this.y;
/* 22:45 */     obj.z = this.z;
/* 23:46 */     obj.blockId = this.blockId;
/* 24:47 */     obj.meta = this.meta;
/* 25:48 */     obj.cpt = ((bq)this.cpt.b());
/* 26:   */     
/* 27:50 */     return obj;
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.blueprints.BptSlotInfo
 * JD-Core Version:    0.7.0.1
 */