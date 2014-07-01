/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import com.eloraam.redpower.core.BlockExtended;
/*  5:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  6:   */ import net.minecraftforge.common.ForgeDirection;
/*  7:   */ import yc;
/*  8:   */ 
/*  9:   */ public class BlockPeripheral
/* 10:   */   extends BlockExtended
/* 11:   */ {
/* 12:   */   public BlockPeripheral(int i)
/* 13:   */   {
/* 14:17 */     super(i, agi.e);
/* 15:18 */     c(2.0F);
/* 16:19 */     a(CreativeExtraTabs.tabMachine);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public boolean c()
/* 20:   */   {
/* 21:22 */     return true;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean isACube()
/* 25:   */   {
/* 26:23 */     return true;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public boolean b()
/* 30:   */   {
/* 31:24 */     return true;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean isBlockNormalCube(yc world, int i, int j, int k)
/* 35:   */   {
/* 36:27 */     return false;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public boolean isBlockSolidOnSide(yc world, int i, int j, int k, ForgeDirection side)
/* 40:   */   {
/* 41:33 */     return true;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public int b(int i)
/* 45:   */   {
/* 46:38 */     return i;
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.BlockPeripheral
 * JD-Core Version:    0.7.0.1
 */