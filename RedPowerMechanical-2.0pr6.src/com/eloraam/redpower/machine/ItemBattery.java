/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.RedPowerMachine;
/*  4:   */ import com.eloraam.redpower.core.IChargeable;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import qw;
/*  7:   */ import qx;
/*  8:   */ import tj;
/*  9:   */ import up;
/* 10:   */ import ur;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class ItemBattery
/* 14:   */   extends up
/* 15:   */ {
/* 16:   */   public ItemBattery(int i)
/* 17:   */   {
/* 18:16 */     super(i);
/* 19:17 */     c(25);
/* 20:18 */     d(1);
/* 21:19 */     setNoRepair();
/* 22:20 */     e(1500);
/* 23:21 */     a(tj.d);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public ur a(ur ist, yc world, qx player)
/* 27:   */   {
/* 28:26 */     for (int i = 0; i < 9; i++)
/* 29:   */     {
/* 30:27 */       ur i1 = player.bJ.a(i);
/* 31:28 */       if ((i1 != null) && 
/* 32:29 */         ((i1.b() instanceof IChargeable))) {
/* 33:31 */         if (i1.j() > 1)
/* 34:   */         {
/* 35:32 */           int d = Math.min(i1.j() - 1, ist.k() - ist.j());
/* 36:   */           
/* 37:34 */           d = Math.min(d, 25);
/* 38:   */           
/* 39:36 */           ist.b(ist.j() + d);
/* 40:37 */           i1.b(i1.j() - d);
/* 41:38 */           player.bJ.d();
/* 42:40 */           if (ist.j() != ist.k()) {
/* 43:   */             break;
/* 44:   */           }
/* 45:41 */           return new ur(RedPowerMachine.itemBatteryEmpty, 1);
/* 46:   */         }
/* 47:   */       }
/* 48:   */     }
/* 49:45 */     return ist;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void addCreativeItems(ArrayList itemList) {}
/* 53:   */   
/* 54:   */   public String getTextureFile()
/* 55:   */   {
/* 56:52 */     return "/eloraam/base/items1.png";
/* 57:   */   }
/* 58:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ItemBattery
 * JD-Core Version:    0.7.0.1
 */