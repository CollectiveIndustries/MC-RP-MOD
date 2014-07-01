/*  1:   */ package buildcraft.api.power;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ 
/*  5:   */ public abstract class PowerFramework
/*  6:   */ {
/*  7:16 */   private static String baseNBTName = "com.eloraam.redpower.buildcarft.Power";
/*  8:   */   public static PowerFramework currentFramework;
/*  9:   */   
/* 10:   */   public abstract IPowerProvider createPowerProvider();
/* 11:   */   
/* 12:   */   public void loadPowerProvider(IPowerReceptor receptor, bq compound)
/* 13:   */   {
/* 14:24 */     IPowerProvider provider = createPowerProvider();
/* 15:26 */     if (compound.b(baseNBTName))
/* 16:   */     {
/* 17:27 */       bq cpt = compound.l(baseNBTName);
/* 18:28 */       if (cpt.i("class").equals(getClass().getName())) {
/* 19:29 */         provider.readFromNBT(cpt.l("contents"));
/* 20:   */       }
/* 21:   */     }
/* 22:33 */     receptor.setPowerProvider(provider);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void savePowerProvider(IPowerReceptor receptor, bq compound)
/* 26:   */   {
/* 27:38 */     IPowerProvider provider = receptor.getPowerProvider();
/* 28:40 */     if (provider == null) {
/* 29:41 */       return;
/* 30:   */     }
/* 31:43 */     bq cpt = new bq();
/* 32:   */     
/* 33:45 */     cpt.a("class", getClass().getName());
/* 34:   */     
/* 35:47 */     bq contents = new bq();
/* 36:   */     
/* 37:49 */     provider.writeToNBT(contents);
/* 38:   */     
/* 39:51 */     cpt.a("contents", contents);
/* 40:52 */     compound.a(baseNBTName, cpt);
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.power.PowerFramework
 * JD-Core Version:    0.7.0.1
 */