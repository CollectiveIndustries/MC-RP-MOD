/*  1:   */ package buildcraft.api.gates;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ 
/*  5:   */ public abstract class Trigger
/*  6:   */   implements ITrigger
/*  7:   */ {
/*  8:   */   protected int id;
/*  9:   */   
/* 10:   */   public Trigger(int id)
/* 11:   */   {
/* 12:26 */     this.id = id;
/* 13:27 */     ActionManager.triggers[id] = this;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getId()
/* 17:   */   {
/* 18:37 */     return this.id;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public abstract String getTextureFile();
/* 22:   */   
/* 23:   */   public int getIndexInTexture()
/* 24:   */   {
/* 25:55 */     return 0;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public boolean hasParameter()
/* 29:   */   {
/* 30:65 */     return false;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String getDescription()
/* 34:   */   {
/* 35:75 */     return "";
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean isTriggerActive(any tile, ITriggerParameter parameter)
/* 39:   */   {
/* 40:86 */     return false;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public final ITriggerParameter createParameter()
/* 44:   */   {
/* 45:96 */     return new TriggerParameter();
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.Trigger
 * JD-Core Version:    0.7.0.1
 */