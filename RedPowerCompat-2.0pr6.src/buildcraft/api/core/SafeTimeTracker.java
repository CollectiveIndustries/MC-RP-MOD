/*  1:   */ package buildcraft.api.core;
/*  2:   */ 
/*  3:   */ import yc;
/*  4:   */ 
/*  5:   */ public class SafeTimeTracker
/*  6:   */ {
/*  7:16 */   private long lastMark = 0L;
/*  8:   */   
/*  9:   */   public boolean markTimeIfDelay(yc world, long delay)
/* 10:   */   {
/* 11:22 */     if (world == null) {
/* 12:23 */       return false;
/* 13:   */     }
/* 14:25 */     long currentTime = world.G();
/* 15:27 */     if (currentTime < this.lastMark)
/* 16:   */     {
/* 17:28 */       this.lastMark = currentTime;
/* 18:29 */       return false;
/* 19:   */     }
/* 20:30 */     if (this.lastMark + delay <= currentTime)
/* 21:   */     {
/* 22:31 */       this.lastMark = world.G();
/* 23:32 */       return true;
/* 24:   */     }
/* 25:34 */     return false;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void markTime(yc world)
/* 29:   */   {
/* 30:39 */     this.lastMark = world.G();
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.core.SafeTimeTracker
 * JD-Core Version:    0.7.0.1
 */