/*  1:   */ package com.eloraam.redpower.logic;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ import baz;
/*  5:   */ import bdx;
/*  6:   */ import com.eloraam.redpower.core.MathLib;
/*  7:   */ import com.eloraam.redpower.core.Quat;
/*  8:   */ import com.eloraam.redpower.core.RenderLib;
/*  9:   */ import com.eloraam.redpower.core.Vector3;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class TilePointerRenderer
/* 14:   */   extends bdx
/* 15:   */ {
/* 16:   */   public void a(any te, double x, double y, double z, float f)
/* 17:   */   {
/* 18:21 */     baz tessellator = baz.a;
/* 19:22 */     if (!(te instanceof TileLogicPointer)) {
/* 20:22 */       return;
/* 21:   */     }
/* 22:23 */     TileLogicPointer tlp = (TileLogicPointer)te;
/* 23:   */     
/* 24:25 */     a("/terrain.png");
/* 25:   */     
/* 26:27 */     int lv = te.k.i(te.l, te.m, te.n, 0);
/* 27:   */     
/* 28:29 */     tessellator.c(lv);
/* 29:   */     
/* 30:31 */     GL11.glDisable(2896);
/* 31:32 */     tessellator.b();
/* 32:   */     
/* 33:34 */     float ptrdir = tlp.getPointerDirection(f) + 0.25F;
/* 34:35 */     Quat q = MathLib.orientQuat(tlp.Rotation >> 2, tlp.Rotation & 0x3);
/* 35:   */     
/* 36:37 */     Vector3 v = tlp.getPointerOrigin();
/* 37:38 */     q.rotate(v);
/* 38:39 */     v.add(x + 0.5D, y + 0.5D, z + 0.5D);
/* 39:   */     
/* 40:41 */     q.rightMultiply(Quat.aroundAxis(0.0D, 1.0D, 0.0D, -6.283185307179586D * ptrdir));
/* 41:42 */     RenderLib.renderPointer(v, q);
/* 42:43 */     tessellator.a();
/* 43:44 */     GL11.glEnable(2896);
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.TilePointerRenderer
 * JD-Core Version:    0.7.0.1
 */