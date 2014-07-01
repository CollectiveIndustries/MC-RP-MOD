/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import net.minecraftforge.event.ForgeSubscribe;
/*  4:   */ import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
/*  5:   */ import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
/*  6:   */ import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;
/*  7:   */ import qw;
/*  8:   */ import qx;
/*  9:   */ import ur;
/* 10:   */ 
/* 11:   */ public class CoreEvents
/* 12:   */ {
/* 13:   */   @ForgeSubscribe
/* 14:   */   public void toolDestroyed(PlayerDestroyItemEvent ev)
/* 15:   */   {
/* 16:15 */     qx player = ev.entityPlayer;
/* 17:16 */     ur orig = ev.original;
/* 18:   */     
/* 19:18 */     int ci = player.bJ.c;
/* 20:   */     
/* 21:   */ 
/* 22:   */ 
/* 23:22 */     int oid = orig.c;
/* 24:23 */     int odmg = orig.j();
/* 25:   */     
/* 26:25 */     ur in2 = player.bJ.a(ci + 27);
/* 27:26 */     ur ist = player.bJ.a(ci);
/* 28:27 */     if ((ist == null) || (ist.a > 0)) {
/* 29:27 */       return;
/* 30:   */     }
/* 31:29 */     if (in2 == null) {
/* 32:29 */       return;
/* 33:   */     }
/* 34:30 */     if (in2.c != oid) {
/* 35:30 */       return;
/* 36:   */     }
/* 37:31 */     if ((in2.g()) && (in2.j() != odmg)) {
/* 38:32 */       return;
/* 39:   */     }
/* 40:34 */     player.bJ.a(ci, in2);
/* 41:   */     
/* 42:36 */     player.bJ.a(ci + 27, null);
/* 43:38 */     for (int i = 2; i > 0; i--)
/* 44:   */     {
/* 45:39 */       ist = player.bJ.a(ci + 9 * i);
/* 46:40 */       if (ist == null) {
/* 47:40 */         return;
/* 48:   */       }
/* 49:41 */       if (ist.c != oid) {
/* 50:41 */         return;
/* 51:   */       }
/* 52:42 */       if ((ist.g()) && (ist.j() != odmg)) {
/* 53:43 */         return;
/* 54:   */       }
/* 55:44 */       player.bJ.a(ci + 9 * i + 9, ist);
/* 56:   */       
/* 57:46 */       player.bJ.a(ci + 9 * i, null);
/* 58:   */     }
/* 59:   */   }
/* 60:   */   
/* 61:   */   @ForgeSubscribe
/* 62:   */   public void oreRegister(OreDictionary.OreRegisterEvent ev)
/* 63:   */   {
/* 64:53 */     CoreLib.registerOre(ev.Name, ev.Ore);
/* 65:   */   }
/* 66:   */   
/* 67:   */   @ForgeSubscribe
/* 68:   */   public void liquidRegister(LiquidDictionary.LiquidRegisterEvent ev)
/* 69:   */   {
/* 70:58 */     PipeLib.registerForgeFluid(ev.Name, ev.Liquid);
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.CoreEvents
 * JD-Core Version:    0.7.0.1
 */