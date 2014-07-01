/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import com.eloraam.redpower.core.BlockMultipart;
/*  5:   */ import com.eloraam.redpower.core.CoreLib;
/*  6:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  7:   */ import ym;
/*  8:   */ 
/*  9:   */ public class BlockMachinePanel
/* 10:   */   extends BlockMultipart
/* 11:   */ {
/* 12:   */   public BlockMachinePanel(int i)
/* 13:   */   {
/* 14:20 */     super(i, agi.e);
/* 15:21 */     c(2.0F);
/* 16:22 */     a(CreativeExtraTabs.tabMachine);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int getLightValue(ym iba, int i, int j, int k)
/* 20:   */   {
/* 21:26 */     TileMachinePanel tmp = (TileMachinePanel)CoreLib.getTileEntity(iba, i, j, k, TileMachinePanel.class);
/* 22:28 */     if (tmp == null) {
/* 23:28 */       return 0;
/* 24:   */     }
/* 25:29 */     return tmp.getLightValue();
/* 26:   */   }
/* 27:   */   
/* 28:   */   public boolean c()
/* 29:   */   {
/* 30:32 */     return false;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean isACube()
/* 34:   */   {
/* 35:33 */     return false;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean b()
/* 39:   */   {
/* 40:34 */     return false;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public int b(int i)
/* 44:   */   {
/* 45:38 */     return i;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public String getTextureFile()
/* 49:   */   {
/* 50:52 */     return "/eloraam/machine/machine1.png";
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.BlockMachinePanel
 * JD-Core Version:    0.7.0.1
 */