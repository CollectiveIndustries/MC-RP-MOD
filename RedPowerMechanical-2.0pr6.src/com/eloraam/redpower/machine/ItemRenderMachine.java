/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import baz;
/*  4:   */ import bq;
/*  5:   */ import com.eloraam.redpower.RedPowerMachine;
/*  6:   */ import com.eloraam.redpower.core.RenderContext;
/*  7:   */ import com.eloraam.redpower.core.RenderLib;
/*  8:   */ import net.minecraftforge.client.IItemRenderer;
/*  9:   */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/* 10:   */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/* 11:   */ import ur;
/* 12:   */ 
/* 13:   */ public class ItemRenderMachine
/* 14:   */   implements IItemRenderer
/* 15:   */ {
/* 16:   */   protected RenderContext context;
/* 17:   */   
/* 18:   */   public ItemRenderMachine()
/* 19:   */   {
/* 20:21 */     this.context = new RenderContext();
/* 21:   */   }
/* 22:   */   
/* 23:   */   public boolean handleRenderType(ur item, IItemRenderer.ItemRenderType type)
/* 24:   */   {
/* 25:25 */     if ((item.c == RedPowerMachine.blockMachine.cm) && 
/* 26:26 */       (item.j() == 6))
/* 27:   */     {
/* 28:27 */       if (item.d != null) {
/* 29:28 */         return true;
/* 30:   */       }
/* 31:30 */       return false;
/* 32:   */     }
/* 33:33 */     return false;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ur item, IItemRenderer.ItemRendererHelper helper)
/* 37:   */   {
/* 38:38 */     switch (1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[helper.ordinal()])
/* 39:   */     {
/* 40:   */     case 1: 
/* 41:   */     case 2: 
/* 42:   */     case 3: 
/* 43:   */     case 4: 
/* 44:   */     case 5: 
/* 45:44 */       return true;
/* 46:   */     }
/* 47:46 */     return false;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void renderItem(IItemRenderer.ItemRenderType type, ur item, Object... data)
/* 51:   */   {
/* 52:52 */     if (item.j() == 6)
/* 53:   */     {
/* 54:53 */       this.context.setDefaults();
/* 55:54 */       this.context.setPos(-0.5D, -0.5D, -0.5D);
/* 56:55 */       this.context.useNormal = true;
/* 57:   */       
/* 58:57 */       RenderLib.bindTexture("/eloraam/machine/machine1.png");
/* 59:58 */       baz tessellator = baz.a;
/* 60:59 */       tessellator.b();
/* 61:   */       
/* 62:61 */       int bat = 0;
/* 63:62 */       if (item.d != null) {
/* 64:63 */         bat = item.d.d("batLevel");
/* 65:   */       }
/* 66:65 */       int tx = 129 + bat * 8 / 6000;
/* 67:66 */       this.context.setTex(84, 128, tx, tx, tx, tx);
/* 68:67 */       this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 69:   */       
/* 70:69 */       tessellator.a();
/* 71:70 */       RenderLib.unbindTexture();
/* 72:   */       
/* 73:72 */       this.context.useNormal = false;
/* 74:   */     }
/* 75:   */   }
/* 76:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.ItemRenderMachine
 * JD-Core Version:    0.7.0.1
 */