/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ import baz;
/*  5:   */ import bbu;
/*  6:   */ import bce;
/*  7:   */ import bdx;
/*  8:   */ import com.eloraam.redpower.core.ITubeFlow;
/*  9:   */ import com.eloraam.redpower.core.RenderContext;
/* 10:   */ import com.eloraam.redpower.core.TubeFlow;
/* 11:   */ import com.eloraam.redpower.core.TubeItem;
/* 12:   */ import com.eloraam.redpower.core.WorldCoord;
/* 13:   */ import px;
/* 14:   */ import ur;
/* 15:   */ import yc;
/* 16:   */ 
/* 17:   */ public class TileTubeRenderer
/* 18:   */   extends bdx
/* 19:   */ {
/* 20:   */   public void a(any te, double x, double y, double z, float f)
/* 21:   */   {
/* 22:30 */     baz tessellator = baz.a;
/* 23:   */     
/* 24:   */ 
/* 25:   */ 
/* 26:   */ 
/* 27:   */ 
/* 28:   */ 
/* 29:   */ 
/* 30:   */ 
/* 31:   */ 
/* 32:40 */     int lv = te.k.i(te.l, te.m, te.n, 0);
/* 33:   */     
/* 34:42 */     tessellator.c(lv);
/* 35:   */     
/* 36:44 */     this.entityitem.p = te.k;
/* 37:45 */     this.entityitem.b(te.l + 0.5D, te.m + 0.5D, te.n + 0.5D);
/* 38:   */     
/* 39:   */ 
/* 40:48 */     bce renderitem = (bce)bbu.a.a(px.class);
/* 41:   */     
/* 42:50 */     this.entityitem.a = 0;
/* 43:51 */     this.entityitem.c = 0.0F;
/* 44:   */     
/* 45:53 */     WorldCoord offset = new WorldCoord(0, 0, 0);
/* 46:54 */     ITubeFlow itf = (ITubeFlow)te;
/* 47:55 */     TubeFlow tf = itf.getTubeFlow();
/* 48:56 */     for (TubeItem item : tf.contents)
/* 49:   */     {
/* 50:57 */       this.entityitem.a(item.item);
/* 51:   */       
/* 52:59 */       offset.x = 0;offset.y = 0;offset.z = 0;
/* 53:60 */       offset.step(item.side);
/* 54:61 */       double d = item.progress / 128.0D * 0.5D;
/* 55:62 */       if (!item.scheduled) {
/* 56:62 */         d = 0.5D - d;
/* 57:   */       }
/* 58:63 */       double yo = 0.0D;
/* 59:64 */       if (item.item.c >= 256) {
/* 60:64 */         yo += 0.1D;
/* 61:   */       }
/* 62:65 */       renderitem.a(this.entityitem, x + 0.5D + offset.x * d, y + 0.5D - this.entityitem.M - yo + offset.y * d, z + 0.5D + offset.z * d, 0.0F, 0.0F);
/* 63:70 */       if (item.color > 0)
/* 64:   */       {
/* 65:71 */         a("/eloraam/machine/machine1.png");
/* 66:72 */         tessellator.b();
/* 67:73 */         this.context.useNormal = true;
/* 68:74 */         this.context.setDefaults();
/* 69:75 */         this.context.setBrightness(lv);
/* 70:76 */         this.context.setPos(x + offset.x * d, y + offset.y * d, z + offset.z * d);
/* 71:77 */         this.context.setTintHex(this.paintColors[(item.color - 1)]);
/* 72:   */         
/* 73:79 */         this.context.setTex(3);
/* 74:80 */         this.context.renderBox(63, 0.2599999904632568D, 0.2599999904632568D, 0.2599999904632568D, 0.7400000095367432D, 0.7400000095367432D, 0.7400000095367432D);
/* 75:81 */         tessellator.a();
/* 76:   */       }
/* 77:   */     }
/* 78:   */   }
/* 79:   */   
/* 80:92 */   int[] paintColors = { 16777215, 16744448, 16711935, 7110911, 16776960, 65280, 16737408, 5460819, 9671571, 65535, 8388863, 255, 5187328, 32768, 16711680, 2039583 };
/* 81:98 */   RenderContext context = new RenderContext();
/* 82:99 */   px entityitem = new px(null);
/* 83:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileTubeRenderer
 * JD-Core Version:    0.7.0.1
 */