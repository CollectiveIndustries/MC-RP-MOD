/*  1:   */ package buildcraft.api.gates;
/*  2:   */ 
/*  3:   */ public abstract class Action
/*  4:   */   implements IAction
/*  5:   */ {
/*  6:   */   protected int id;
/*  7:   */   
/*  8:   */   public Action(int id)
/*  9:   */   {
/* 10:17 */     this.id = id;
/* 11:18 */     ActionManager.actions[id] = this;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public int getId()
/* 15:   */   {
/* 16:23 */     return this.id;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public abstract String getTexture();
/* 20:   */   
/* 21:   */   public int getIndexInTexture()
/* 22:   */   {
/* 23:31 */     return 0;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean hasParameter()
/* 27:   */   {
/* 28:36 */     return false;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public String getDescription()
/* 32:   */   {
/* 33:41 */     return "";
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.Action
 * JD-Core Version:    0.7.0.1
 */