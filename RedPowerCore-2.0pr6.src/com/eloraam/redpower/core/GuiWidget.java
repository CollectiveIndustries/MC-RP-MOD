/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import ats;
/*  4:   */ 
/*  5:   */ public class GuiWidget
/*  6:   */   extends ats
/*  7:   */ {
/*  8:   */   public int width;
/*  9:   */   public int height;
/* 10:   */   public int top;
/* 11:   */   public int left;
/* 12:   */   
/* 13:   */   public GuiWidget(int x, int y, int w, int h)
/* 14:   */   {
/* 15: 9 */     this.left = x;this.top = y;this.width = w;this.height = h;
/* 16:   */   }
/* 17:   */   
/* 18:   */   protected void drawRelRect(int x, int y, int w, int h, int c)
/* 19:   */   {
/* 20:13 */     a(x, y, x + w, y + h, c | 0xF000);
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.GuiWidget
 * JD-Core Version:    0.7.0.1
 */