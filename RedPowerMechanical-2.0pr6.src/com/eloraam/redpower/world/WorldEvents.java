/*   1:    */ package com.eloraam.redpower.world;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.RedPowerWorld;
/*   5:    */ import com.eloraam.redpower.core.CoreLib;
/*   6:    */ import com.eloraam.redpower.core.MachineLib;
/*   7:    */ import la;
/*   8:    */ import li;
/*   9:    */ import lq;
/*  10:    */ import md;
/*  11:    */ import net.minecraftforge.event.Event.Result;
/*  12:    */ import net.minecraftforge.event.ForgeSubscribe;
/*  13:    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*  14:    */ import net.minecraftforge.event.entity.player.BonemealEvent;
/*  15:    */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*  16:    */ import px;
/*  17:    */ import qc;
/*  18:    */ import qn;
/*  19:    */ import qr;
/*  20:    */ import qw;
/*  21:    */ import qx;
/*  22:    */ import up;
/*  23:    */ import ur;
/*  24:    */ import xc;
/*  25:    */ import xe;
/*  26:    */ import yc;
/*  27:    */ 
/*  28:    */ public class WorldEvents
/*  29:    */ {
/*  30:    */   @ForgeSubscribe
/*  31:    */   public void onBonemeal(BonemealEvent ev)
/*  32:    */   {
/*  33: 28 */     if (ev.ID == RedPowerWorld.blockCrops.cm)
/*  34:    */     {
/*  35: 29 */       int md = ev.world.h(ev.X, ev.Y, ev.Z);
/*  36: 30 */       if ((md == 4) || (md == 5)) {
/*  37: 30 */         return;
/*  38:    */       }
/*  39: 31 */       if (CoreLib.isClient(ev.world))
/*  40:    */       {
/*  41: 32 */         ev.setResult(Event.Result.ALLOW);
/*  42: 33 */         return;
/*  43:    */       }
/*  44: 35 */       if (RedPowerWorld.blockCrops.fertilize(ev.world, ev.X, ev.Y, ev.Z)) {
/*  45: 37 */         ev.setResult(Event.Result.ALLOW);
/*  46:    */       }
/*  47:    */     }
/*  48:    */   }
/*  49:    */   
/*  50:    */   @ForgeSubscribe
/*  51:    */   public void onDeath(LivingDeathEvent ev)
/*  52:    */   {
/*  53: 43 */     if (!(ev.source instanceof li)) {
/*  54: 44 */       return;
/*  55:    */     }
/*  56: 45 */     li eds = (li)ev.source;
/*  57:    */     
/*  58: 47 */     lq ent = eds.g();
/*  59: 48 */     if (!(ent instanceof qx)) {
/*  60: 48 */       return;
/*  61:    */     }
/*  62: 50 */     qx epl = (qx)ent;
/*  63:    */     
/*  64: 52 */     ur wpn = epl.bS();
/*  65: 53 */     if (xe.a(RedPowerWorld.enchantVorpal.z, wpn) == 0) {
/*  66: 56 */       return;
/*  67:    */     }
/*  68: 58 */     if (ev.entityLiving.aU() > -20) {
/*  69: 58 */       return;
/*  70:    */     }
/*  71: 60 */     if ((ev.entityLiving instanceof qn))
/*  72:    */     {
/*  73: 61 */       qn esk = (qn)ev.entityLiving;
/*  74: 62 */       if (esk.o() == 1) {
/*  75: 62 */         return;
/*  76:    */       }
/*  77: 64 */       ev.entityLiving.a(new ur(up.bQ.cj, 1, 0), 0.0F);
/*  78:    */     }
/*  79: 66 */     else if ((ev.entityLiving instanceof qr))
/*  80:    */     {
/*  81: 67 */       ev.entityLiving.a(new ur(up.bQ.cj, 1, 2), 0.0F);
/*  82:    */     }
/*  83: 69 */     else if ((ev.entityLiving instanceof qx))
/*  84:    */     {
/*  85: 70 */       ur ist = new ur(up.bQ.cj, 1, 3);
/*  86:    */       
/*  87:    */ 
/*  88: 73 */       ist.d(new bq());
/*  89: 74 */       ist.p().a("SkullOwner", ev.entityLiving.an());
/*  90:    */       
/*  91:    */ 
/*  92: 77 */       ev.entityLiving.a(ist, 0.0F);
/*  93:    */     }
/*  94: 78 */     else if ((ev.entityLiving instanceof qc))
/*  95:    */     {
/*  96: 79 */       ev.entityLiving.a(new ur(up.bQ.cj, 1, 4), 0.0F);
/*  97:    */     }
/*  98:    */   }
/*  99:    */   
/* 100:    */   @ForgeSubscribe
/* 101:    */   public void onPickupItem(EntityItemPickupEvent ev)
/* 102:    */   {
/* 103: 87 */     for (int i = 0; i < 9; i++)
/* 104:    */     {
/* 105: 88 */       ur ist = ev.entityPlayer.bJ.a(i);
/* 106: 89 */       if ((ist != null) && 
/* 107: 90 */         ((ist.b() instanceof ItemSeedBag)))
/* 108:    */       {
/* 109: 93 */         la inv = ItemSeedBag.getBagInventory(ist);
/* 110: 94 */         if ((inv != null) && 
/* 111: 95 */           (ItemSeedBag.getPlant(inv) != null))
/* 112:    */         {
/* 113: 97 */           ur tpi = ev.item.d();
/* 114: 98 */           if (ItemSeedBag.canAdd(inv, tpi)) {
/* 115:100 */             if (MachineLib.addToInventoryCore(inv, tpi, 0, inv.k_(), true))
/* 116:    */             {
/* 117:102 */               ev.item.x();
/* 118:103 */               ev.setResult(Event.Result.ALLOW);
/* 119:104 */               return;
/* 120:    */             }
/* 121:    */           }
/* 122:    */         }
/* 123:    */       }
/* 124:    */     }
/* 125:    */   }
/* 126:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.world.WorldEvents
 * JD-Core Version:    0.7.0.1
 */