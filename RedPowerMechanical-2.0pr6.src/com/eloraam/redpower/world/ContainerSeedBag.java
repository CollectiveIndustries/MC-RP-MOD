/*  1:   */ package com.eloraam.redpower.world;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import la;
/*  5:   */ import qw;
/*  6:   */ import qx;
/*  7:   */ import rq;
/*  8:   */ import sr;
/*  9:   */ import ur;
/* 10:   */ 
/* 11:   */ public class ContainerSeedBag
/* 12:   */   extends rq
/* 13:   */ {
/* 14:   */   ur itemBag;
/* 15:   */   la baginv;
/* 16:   */   
/* 17:   */   public static class SlotSeeds
/* 18:   */     extends sr
/* 19:   */   {
/* 20:   */     public SlotSeeds(la inv, int par2, int par3, int par4)
/* 21:   */     {
/* 22:18 */       super(par2, par3, par4);
/* 23:   */     }
/* 24:   */     
/* 25:   */     public boolean a(ur ist)
/* 26:   */     {
/* 27:22 */       return ItemSeedBag.canAdd(this.f, ist);
/* 28:   */     }
/* 29:   */   }
/* 30:   */   
/* 31:   */   public ContainerSeedBag(qw inv, la bag, ur ist)
/* 32:   */   {
/* 33:29 */     this.baginv = bag;
/* 34:30 */     for (int i = 0; i < 3; i++) {
/* 35:30 */       for (int j = 0; j < 3; j++) {
/* 36:31 */         a(new SlotSeeds(bag, j + i * 3, 62 + j * 18, 17 + i * 18));
/* 37:   */       }
/* 38:   */     }
/* 39:34 */     for (i = 0; i < 3; i++) {
/* 40:34 */       for (int j = 0; j < 9; j++) {
/* 41:35 */         a(new sr(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/* 42:   */       }
/* 43:   */     }
/* 44:37 */     for (i = 0; i < 9; i++) {
/* 45:38 */       a(new sr(inv, i, 8 + i * 18, 142));
/* 46:   */     }
/* 47:40 */     this.itemBag = ist;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public boolean a(qx player)
/* 51:   */   {
/* 52:44 */     return player.bJ.g() == this.itemBag;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public ur b(qx player, int i)
/* 56:   */   {
/* 57:49 */     ur itemstack = null;
/* 58:50 */     sr slot = (sr)this.c.get(i);
/* 59:51 */     if ((slot != null) && (slot.d()))
/* 60:   */     {
/* 61:52 */       ur itemstack1 = slot.c();
/* 62:53 */       if (!ItemSeedBag.canAdd(this.baginv, itemstack1)) {
/* 63:54 */         return null;
/* 64:   */       }
/* 65:55 */       itemstack = itemstack1.l();
/* 66:56 */       if (i < 9)
/* 67:   */       {
/* 68:57 */         if (!a(itemstack1, 9, 45, true)) {
/* 69:58 */           return null;
/* 70:   */         }
/* 71:   */       }
/* 72:60 */       else if (!a(itemstack1, 0, 9, false)) {
/* 73:61 */         return null;
/* 74:   */       }
/* 75:63 */       if (itemstack1.a == 0) {
/* 76:64 */         slot.c(null);
/* 77:   */       } else {
/* 78:66 */         slot.e();
/* 79:   */       }
/* 80:68 */       if (itemstack1.a != itemstack.a) {
/* 81:69 */         slot.a(player, itemstack1);
/* 82:   */       } else {
/* 83:71 */         return null;
/* 84:   */       }
/* 85:   */     }
/* 86:74 */     return itemstack;
/* 87:   */   }
/* 88:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.ContainerSeedBag
 * JD-Core Version:    0.7.0.1
 */