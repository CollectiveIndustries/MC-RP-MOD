/*  1:   */ package buildcraft.api.transport;
/*  2:   */ 
/*  3:   */ import java.lang.reflect.Method;
/*  4:   */ import ur;
/*  5:   */ 
/*  6:   */ public class FacadeManager
/*  7:   */ {
/*  8:   */   private static Method addFacade;
/*  9:   */   
/* 10:   */   public static void addFacade(ur is)
/* 11:   */   {
/* 12:   */     try
/* 13:   */     {
/* 14:12 */       if (addFacade == null)
/* 15:   */       {
/* 16:13 */         Class facade = Class.forName("buildcraft.transport.ItemFacade");
/* 17:14 */         addFacade = facade.getMethod("addFacade", new Class[] { ur.class });
/* 18:   */       }
/* 19:16 */       addFacade.invoke(null, new Object[] { is });
/* 20:   */     }
/* 21:   */     catch (Exception ex) {}
/* 22:   */   }
/* 23:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.transport.FacadeManager
 * JD-Core Version:    0.7.0.1
 */