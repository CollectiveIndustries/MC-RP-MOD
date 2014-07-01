/*  1:   */ package com.eloraam.redpower.control;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import com.eloraam.redpower.RedPowerControl;
/*  5:   */ import com.eloraam.redpower.core.BlockExtended;
/*  6:   */ import com.eloraam.redpower.core.BlockMultipart;
/*  7:   */ import com.eloraam.redpower.core.CoreLib;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ import qx;
/* 10:   */ import ur;
/* 11:   */ import yc;
/* 12:   */ 
/* 13:   */ public class TileRAM
/* 14:   */   extends TileBackplane
/* 15:   */ {
/* 16:   */   public int readBackplane(int addr)
/* 17:   */   {
/* 18:20 */     return this.memory[addr] & 0xFF;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void writeBackplane(int addr, int val)
/* 22:   */   {
/* 23:24 */     this.memory[addr] = ((byte)val);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public int getBlockID()
/* 27:   */   {
/* 28:30 */     return RedPowerControl.blockBackplane.cm;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public int getExtendedID()
/* 32:   */   {
/* 33:34 */     return 1;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void addHarvestContents(ArrayList ist)
/* 37:   */   {
/* 38:40 */     super.addHarvestContents(ist);
/* 39:41 */     ist.add(new ur(RedPowerControl.blockBackplane, 1, 1));
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void onHarvestPart(qx player, int part)
/* 43:   */   {
/* 44:45 */     CoreLib.dropItem(this.k, this.l, this.m, this.n, new ur(RedPowerControl.blockBackplane, 1, 1));
/* 45:   */     
/* 46:47 */     BlockMultipart.removeMultipart(this.k, this.l, this.m, this.n);
/* 47:   */     
/* 48:49 */     this.k.b(this.l, this.m, this.n, RedPowerControl.blockBackplane.cm);
/* 49:   */     
/* 50:51 */     TileBackplane tb = (TileBackplane)CoreLib.getTileEntity(this.k, this.l, this.m, this.n, TileBackplane.class);
/* 51:53 */     if (tb != null) {
/* 52:53 */       tb.Rotation = this.Rotation;
/* 53:   */     }
/* 54:54 */     updateBlockChange();
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setPartBounds(BlockMultipart bl, int part)
/* 58:   */   {
/* 59:58 */     if (part == 0) {
/* 60:58 */       super.setPartBounds(bl, part);
/* 61:   */     } else {
/* 62:59 */       bl.a(0.0F, 0.125F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 63:   */     }
/* 64:   */   }
/* 65:   */   
/* 66:   */   public int getSolidPartsMask()
/* 67:   */   {
/* 68:62 */     return 3;
/* 69:   */   }
/* 70:   */   
/* 71:   */   public int getPartsMask()
/* 72:   */   {
/* 73:63 */     return 3;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public void a(bq nbttagcompound)
/* 77:   */   {
/* 78:68 */     super.a(nbttagcompound);
/* 79:69 */     this.memory = nbttagcompound.j("ram");
/* 80:70 */     if (this.memory.length != 8192) {
/* 81:71 */       this.memory = new byte[8192];
/* 82:   */     }
/* 83:   */   }
/* 84:   */   
/* 85:   */   public void b(bq nbttagcompound)
/* 86:   */   {
/* 87:75 */     super.b(nbttagcompound);
/* 88:76 */     nbttagcompound.a("ram", this.memory);
/* 89:   */   }
/* 90:   */   
/* 91:79 */   public byte[] memory = new byte[8192];
/* 92:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.control.TileRAM
 * JD-Core Version:    0.7.0.1
 */