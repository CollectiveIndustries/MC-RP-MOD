/*  1:   */ package com.eloraam.redpower.machine;
/*  2:   */ 
/*  3:   */ import aaq;
/*  4:   */ import any;
/*  5:   */ import bq;
/*  6:   */ import com.eloraam.redpower.core.BlockMultipart;
/*  7:   */ import com.eloraam.redpower.core.BluePowerConductor;
/*  8:   */ import com.eloraam.redpower.core.CoreLib;
/*  9:   */ import com.eloraam.redpower.core.IBluePowerConnectable;
/* 10:   */ import com.eloraam.redpower.core.RedPowerLib;
/* 11:   */ import net.minecraftforge.common.ForgeDirection;
/* 12:   */ import yc;
/* 13:   */ 
/* 14:   */ public class TileSolarPanel
/* 15:   */   extends TileMachinePanel
/* 16:   */   implements IBluePowerConnectable
/* 17:   */ {
/* 18:   */   public void onBlockNeighborChange(int l)
/* 19:   */   {
/* 20:16 */     this.ConMask = -1;
/* 21:17 */     if (this.k.isBlockSolidOnSide(this.l, this.m - 1, this.n, ForgeDirection.UP)) {
/* 22:19 */       return;
/* 23:   */     }
/* 24:20 */     breakBlock();
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setPartBounds(BlockMultipart bl, int part)
/* 28:   */   {
/* 29:26 */     bl.a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getConnectableMask()
/* 33:   */   {
/* 34:33 */     return 16777231;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public int getConnectClass(int side)
/* 38:   */   {
/* 39:37 */     return 64;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public int getCornerPowerMode()
/* 43:   */   {
/* 44:40 */     return 0;
/* 45:   */   }
/* 46:   */   
/* 47:44 */   BluePowerConductor cond = new BluePowerConductor()
/* 48:   */   {
/* 49:   */     public any getParent()
/* 50:   */     {
/* 51:46 */       return TileSolarPanel.this;
/* 52:   */     }
/* 53:   */     
/* 54:   */     public double getInvCap()
/* 55:   */     {
/* 56:50 */       return 4.0D;
/* 57:   */     }
/* 58:   */   };
/* 59:   */   
/* 60:   */   public BluePowerConductor getBlueConductor(int side)
/* 61:   */   {
/* 62:55 */     return this.cond;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void g()
/* 66:   */   {
/* 67:59 */     if (CoreLib.isClient(this.k)) {
/* 68:59 */       return;
/* 69:   */     }
/* 70:61 */     if (this.ConMask < 0)
/* 71:   */     {
/* 72:62 */       this.ConMask = RedPowerLib.getConnections(this.k, this, this.l, this.m, this.n);
/* 73:   */       
/* 74:64 */       this.cond.recache(this.ConMask, 0);
/* 75:   */     }
/* 76:66 */     this.cond.iterate();
/* 77:67 */     dirtyBlock();
/* 78:69 */     if (this.cond.getVoltage() > 100.0D) {
/* 79:69 */       return;
/* 80:   */     }
/* 81:70 */     if (!this.k.k(this.l, this.m, this.n)) {
/* 82:71 */       return;
/* 83:   */     }
/* 84:72 */     if (!this.k.u()) {
/* 85:72 */       return;
/* 86:   */     }
/* 87:73 */     if (this.k.u.f) {
/* 88:73 */       return;
/* 89:   */     }
/* 90:74 */     this.cond.applyDirect(2.0D);
/* 91:   */   }
/* 92:   */   
/* 93:   */   public void a(bq nbttagcompound)
/* 94:   */   {
/* 95:82 */     super.a(nbttagcompound);
/* 96:83 */     this.cond.readFromNBT(nbttagcompound);
/* 97:   */   }
/* 98:   */   
/* 99:   */   public void b(bq nbttagcompound)
/* :0:   */   {
/* :1:87 */     super.b(nbttagcompound);
/* :2:88 */     this.cond.writeToNBT(nbttagcompound);
/* :3:   */   }
/* :4:   */   
/* :5:92 */   public int ConMask = -1;
/* :6:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileSolarPanel
 * JD-Core Version:    0.7.0.1
 */