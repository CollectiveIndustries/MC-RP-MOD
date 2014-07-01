/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import avf;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import rq;
/*  6:   */ 
/*  7:   */ public abstract class GuiExtended
/*  8:   */   extends avf
/*  9:   */ {
/* 10:   */   ArrayList widgetList;
/* 11:   */   
/* 12:   */   public GuiExtended(rq cn)
/* 13:   */   {
/* 14:15 */     super(cn);
/* 15:16 */     this.widgetList = new ArrayList();
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.GuiExtended
 * JD-Core Version:    0.7.0.1
 */