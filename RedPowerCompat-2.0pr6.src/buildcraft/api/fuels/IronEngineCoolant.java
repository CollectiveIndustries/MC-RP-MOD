/*  1:   */ package buildcraft.api.fuels;
/*  2:   */ 
/*  3:   */ import java.util.LinkedList;
/*  4:   */ import net.minecraftforge.liquids.LiquidStack;
/*  5:   */ 
/*  6:   */ public class IronEngineCoolant
/*  7:   */ {
/*  8: 9 */   public static LinkedList coolants = new LinkedList();
/*  9:   */   public final LiquidStack liquid;
/* 10:   */   public final float coolingPerUnit;
/* 11:   */   
/* 12:   */   public static IronEngineCoolant getCoolantForLiquid(LiquidStack liquid)
/* 13:   */   {
/* 14:12 */     if (liquid == null) {
/* 15:13 */       return null;
/* 16:   */     }
/* 17:14 */     if (liquid.itemID <= 0) {
/* 18:15 */       return null;
/* 19:   */     }
/* 20:17 */     for (IronEngineCoolant coolant : coolants) {
/* 21:18 */       if (coolant.liquid.isLiquidEqual(liquid)) {
/* 22:19 */         return coolant;
/* 23:   */       }
/* 24:   */     }
/* 25:21 */     return null;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public IronEngineCoolant(LiquidStack liquid, float coolingPerUnit)
/* 29:   */   {
/* 30:28 */     this.liquid = liquid;
/* 31:29 */     this.coolingPerUnit = coolingPerUnit;
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.fuels.IronEngineCoolant
 * JD-Core Version:    0.7.0.1
 */