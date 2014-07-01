/*  1:   */ package buildcraft.api.fuels;
/*  2:   */ 
/*  3:   */ import java.util.LinkedList;
/*  4:   */ import net.minecraftforge.liquids.LiquidStack;
/*  5:   */ 
/*  6:   */ public class IronEngineFuel
/*  7:   */ {
/*  8:19 */   public static LinkedList fuels = new LinkedList();
/*  9:   */   public final LiquidStack liquid;
/* 10:   */   public final float powerPerCycle;
/* 11:   */   public final int totalBurningTime;
/* 12:   */   
/* 13:   */   public static IronEngineFuel getFuelForLiquid(LiquidStack liquid)
/* 14:   */   {
/* 15:22 */     if (liquid == null) {
/* 16:23 */       return null;
/* 17:   */     }
/* 18:24 */     if (liquid.itemID <= 0) {
/* 19:25 */       return null;
/* 20:   */     }
/* 21:27 */     for (IronEngineFuel fuel : fuels) {
/* 22:28 */       if (fuel.liquid.isLiquidEqual(liquid)) {
/* 23:29 */         return fuel;
/* 24:   */       }
/* 25:   */     }
/* 26:31 */     return null;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public IronEngineFuel(int liquidId, float powerPerCycle, int totalBurningTime)
/* 30:   */   {
/* 31:39 */     this(new LiquidStack(liquidId, 1000, 0), powerPerCycle, totalBurningTime);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public IronEngineFuel(LiquidStack liquid, float powerPerCycle, int totalBurningTime)
/* 35:   */   {
/* 36:43 */     this.liquid = liquid;
/* 37:44 */     this.powerPerCycle = powerPerCycle;
/* 38:45 */     this.totalBurningTime = totalBurningTime;
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.fuels.IronEngineFuel
 * JD-Core Version:    0.7.0.1
 */