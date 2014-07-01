/*  1:   */ package buildcraft.api.gates;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import any;
/*  5:   */ import buildcraft.api.transport.IPipe;
/*  6:   */ import java.util.LinkedList;
/*  7:   */ 
/*  8:   */ public class ActionManager
/*  9:   */ {
/* 10:11 */   public static Trigger[] triggers = new Trigger[1024];
/* 11:12 */   public static Action[] actions = new Action[1024];
/* 12:14 */   private static LinkedList triggerProviders = new LinkedList();
/* 13:15 */   private static LinkedList actionProviders = new LinkedList();
/* 14:   */   
/* 15:   */   public static void registerTriggerProvider(ITriggerProvider provider)
/* 16:   */   {
/* 17:18 */     if ((provider != null) && (!triggerProviders.contains(provider))) {
/* 18:19 */       triggerProviders.add(provider);
/* 19:   */     }
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static LinkedList getNeighborTriggers(amq block, any entity)
/* 23:   */   {
/* 24:24 */     LinkedList triggers = new LinkedList();
/* 25:26 */     for (ITriggerProvider provider : triggerProviders)
/* 26:   */     {
/* 27:27 */       LinkedList toAdd = provider.getNeighborTriggers(block, entity);
/* 28:29 */       if (toAdd != null) {
/* 29:30 */         for (ITrigger t : toAdd) {
/* 30:31 */           if (!triggers.contains(t)) {
/* 31:32 */             triggers.add(t);
/* 32:   */           }
/* 33:   */         }
/* 34:   */       }
/* 35:   */     }
/* 36:38 */     return triggers;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public static void registerActionProvider(IActionProvider provider)
/* 40:   */   {
/* 41:42 */     if ((provider != null) && (!actionProviders.contains(provider))) {
/* 42:43 */       actionProviders.add(provider);
/* 43:   */     }
/* 44:   */   }
/* 45:   */   
/* 46:   */   public static LinkedList getNeighborActions(amq block, any entity)
/* 47:   */   {
/* 48:48 */     LinkedList actions = new LinkedList();
/* 49:50 */     for (IActionProvider provider : actionProviders)
/* 50:   */     {
/* 51:51 */       LinkedList toAdd = provider.getNeighborActions(block, entity);
/* 52:53 */       if (toAdd != null) {
/* 53:54 */         for (IAction t : toAdd) {
/* 54:55 */           if (!actions.contains(t)) {
/* 55:56 */             actions.add(t);
/* 56:   */           }
/* 57:   */         }
/* 58:   */       }
/* 59:   */     }
/* 60:62 */     return actions;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public static LinkedList getPipeTriggers(IPipe pipe)
/* 64:   */   {
/* 65:66 */     LinkedList triggers = new LinkedList();
/* 66:68 */     for (ITriggerProvider provider : triggerProviders)
/* 67:   */     {
/* 68:69 */       LinkedList toAdd = provider.getPipeTriggers(pipe);
/* 69:71 */       if (toAdd != null) {
/* 70:72 */         for (ITrigger t : toAdd) {
/* 71:73 */           if (!triggers.contains(t)) {
/* 72:74 */             triggers.add(t);
/* 73:   */           }
/* 74:   */         }
/* 75:   */       }
/* 76:   */     }
/* 77:80 */     return triggers;
/* 78:   */   }
/* 79:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.gates.ActionManager
 * JD-Core Version:    0.7.0.1
 */