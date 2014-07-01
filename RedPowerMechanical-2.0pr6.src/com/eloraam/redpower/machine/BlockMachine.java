/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import agi;
/*  4:   */ import com.eloraam.redpower.core.BlockExtended;
/*  5:   */ import com.eloraam.redpower.core.CoreLib;
/*  6:   */ import com.eloraam.redpower.core.CreativeExtraTabs;
/*  7:   */ import net.minecraftforge.common.ForgeDirection;
/*  8:   */ import yc;
/*  9:   */ import ym;
/* 10:   */ 
/* 11:   */ public class BlockMachine
/* 12:   */   extends BlockExtended
/* 13:   */ {
/* 14:   */   public BlockMachine(int i)
/* 15:   */   {
/* 16:19 */     super(i, agi.e);
/* 17:20 */     c(2.0F);
/* 18:21 */     a(CreativeExtraTabs.tabMachine);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean c()
/* 22:   */   {
/* 23:24 */     return true;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public boolean isACube()
/* 27:   */   {
/* 28:25 */     return true;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public boolean b()
/* 32:   */   {
/* 33:26 */     return true;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public boolean isBlockNormalCube(yc world, int i, int j, int k)
/* 37:   */   {
/* 38:29 */     return false;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public boolean isBlockSolidOnSide(yc world, int i, int j, int k, ForgeDirection side)
/* 42:   */   {
/* 43:35 */     return true;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public int b(int i)
/* 47:   */   {
/* 48:40 */     return i;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public boolean i()
/* 52:   */   {
/* 53:43 */     return true;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public boolean b(ym iba, int i, int j, int k, int l)
/* 57:   */   {
/* 58:50 */     TileMachine tm = (TileMachine)CoreLib.getTileEntity(iba, i, j, k, TileMachine.class);
/* 59:52 */     if (tm == null) {
/* 60:52 */       return false;
/* 61:   */     }
/* 62:53 */     return tm.isPoweringTo(l);
/* 63:   */   }
/* 64:   */   
/* 65:   */   public boolean isFireSource(yc world, int x, int y, int z, int md, int face)
/* 66:   */   {
/* 67:58 */     if (md != 12) {
/* 68:58 */       return false;
/* 69:   */     }
/* 70:60 */     TileIgniter tig = (TileIgniter)CoreLib.getTileEntity(world, x, y, z, TileIgniter.class);
/* 71:62 */     if (tig == null) {
/* 72:62 */       return false;
/* 73:   */     }
/* 74:64 */     return tig.isOnFire(face);
/* 75:   */   }
/* 76:   */   
/* 77:   */   public String getTextureFile()
/* 78:   */   {
/* 79:68 */     return "/eloraam/machine/machine1.png";
/* 80:   */   }
/* 81:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.BlockMachine
 * JD-Core Version:    0.7.0.1
 */