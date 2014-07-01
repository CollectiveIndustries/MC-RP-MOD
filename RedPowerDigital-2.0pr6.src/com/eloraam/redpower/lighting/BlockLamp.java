/*  1:   */ package com.eloraam.redpower.lighting;
/*  2:   */ 
/*  3:   */ import amq;
/*  4:   */ import com.eloraam.redpower.RedPowerCore;
/*  5:   */ import com.eloraam.redpower.RedPowerLighting;
/*  6:   */ import com.eloraam.redpower.core.CoreLib;
/*  7:   */ import com.eloraam.redpower.core.RedPowerLib;
/*  8:   */ import java.util.List;
/*  9:   */ import java.util.Random;
/* 10:   */ import tj;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class BlockLamp
/* 14:   */   extends amq
/* 15:   */ {
/* 16:   */   public boolean lit;
/* 17:   */   public boolean powered;
/* 18:   */   public int onID;
/* 19:   */   public int offID;
/* 20:   */   
/* 21:   */   public BlockLamp(int i, boolean l, boolean p)
/* 22:   */   {
/* 23:21 */     super(i, CoreLib.materialRedpower);
/* 24:22 */     a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 25:23 */     c(0.5F);
/* 26:24 */     a(RedPowerLighting.tabLamp);
/* 27:25 */     this.lit = l;this.powered = p;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public boolean canRenderInPass(int n)
/* 31:   */   {
/* 32:28 */     return true;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean c()
/* 36:   */   {
/* 37:30 */     return true;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean b()
/* 41:   */   {
/* 42:31 */     return true;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public boolean isACube()
/* 46:   */   {
/* 47:32 */     return true;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public boolean i()
/* 51:   */   {
/* 52:34 */     return true;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public int n()
/* 56:   */   {
/* 57:37 */     return 1;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public int b(int i)
/* 61:   */   {
/* 62:42 */     return i;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public int a(int i, Random random, int j)
/* 66:   */   {
/* 67:46 */     return this.offID;
/* 68:   */   }
/* 69:   */   
/* 70:   */   private void checkPowerState(yc world, int i, int j, int k)
/* 71:   */   {
/* 72:51 */     if ((!this.powered) && (RedPowerLib.isPowered(world, i, j, k, 16777215, 63)))
/* 73:   */     {
/* 74:52 */       int md = world.h(i, j, k);
/* 75:53 */       world.d(i, j, k, this.onID, md);
/* 76:54 */       world.i(i, j, k);
/* 77:   */     }
/* 78:55 */     else if ((this.powered) && (!RedPowerLib.isPowered(world, i, j, k, 16777215, 63)))
/* 79:   */     {
/* 80:56 */       int md = world.h(i, j, k);
/* 81:57 */       world.d(i, j, k, this.offID, md);
/* 82:58 */       world.i(i, j, k);
/* 83:   */     }
/* 84:   */   }
/* 85:   */   
/* 86:   */   public void a(yc world, int i, int j, int k, int l)
/* 87:   */   {
/* 88:64 */     checkPowerState(world, i, j, k);
/* 89:   */   }
/* 90:   */   
/* 91:   */   public void g(yc world, int i, int j, int k)
/* 92:   */   {
/* 93:68 */     checkPowerState(world, i, j, k);
/* 94:   */   }
/* 95:   */   
/* 96:   */   public int d()
/* 97:   */   {
/* 98:73 */     return RedPowerCore.customBlockModel;
/* 99:   */   }
/* :0:   */   
/* :1:   */   public void a(int par1, tj par2CreativeTabs, List par3List) {}
/* :2:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.lighting.BlockLamp
 * JD-Core Version:    0.7.0.1
 */