/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ import bq;
/*  5:   */ import java.io.IOException;
/*  6:   */ import yc;
/*  7:   */ 
/*  8:   */ public abstract class FluidBuffer
/*  9:   */ {
/* 10:   */   public abstract any getParent();
/* 11:   */   
/* 12:   */   public abstract void onChange();
/* 13:   */   
/* 14:   */   public int getMaxLevel()
/* 15:   */   {
/* 16:12 */     return 1000;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int getLevel()
/* 20:   */   {
/* 21:15 */     long lt = getParent().k.G();
/* 22:16 */     if ((lt & 0xFFFF) == this.lastTick) {
/* 23:16 */       return this.Level;
/* 24:   */     }
/* 25:17 */     this.lastTick = ((int)(lt & 0xFFFF));
/* 26:18 */     this.Level += this.Delta;this.Delta = 0;
/* 27:19 */     if (this.Level == 0) {
/* 28:19 */       this.Type = 0;
/* 29:   */     }
/* 30:20 */     return this.Level;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void addLevel(int type, int lvl)
/* 34:   */   {
/* 35:24 */     this.Type = type;
/* 36:25 */     this.Delta += lvl;
/* 37:26 */     onChange();
/* 38:   */   }
/* 39:   */   
/* 40:   */   public FluidClass getFluidClass()
/* 41:   */   {
/* 42:30 */     if ((this.Type == 0) || (this.Level == 0)) {
/* 43:30 */       return null;
/* 44:   */     }
/* 45:31 */     return PipeLib.getLiquidClass(this.Type);
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void readFromNBT(bq tag, String name)
/* 49:   */   {
/* 50:35 */     bq t2 = tag.l(name);
/* 51:   */     
/* 52:37 */     this.Type = t2.e("type");
/* 53:38 */     this.Level = t2.e("lvl");
/* 54:39 */     this.Delta = t2.e("del");
/* 55:40 */     this.lastTick = t2.e("ltk");
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void writeToNBT(bq tag, String name)
/* 59:   */   {
/* 60:44 */     bq t2 = new bq();
/* 61:   */     
/* 62:46 */     t2.a("type", (short)this.Type);
/* 63:47 */     t2.a("lvl", (short)this.Level);
/* 64:48 */     t2.a("del", (short)this.Delta);
/* 65:49 */     t2.a("lck", (short)this.lastTick);
/* 66:   */     
/* 67:51 */     tag.a(name, t2);
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void writeToPacket(Packet211TileDesc pkt)
/* 71:   */   {
/* 72:55 */     pkt.addUVLC(this.Type);
/* 73:56 */     pkt.addUVLC(this.Level);
/* 74:   */   }
/* 75:   */   
/* 76:   */   public void readFromPacket(Packet211TileDesc pkt)
/* 77:   */     throws IOException
/* 78:   */   {
/* 79:60 */     this.Type = ((int)pkt.getUVLC());
/* 80:61 */     this.Level = ((int)pkt.getUVLC());
/* 81:   */   }
/* 82:   */   
/* 83:64 */   public int Level = 0;
/* 84:64 */   public int Delta = 0;
/* 85:64 */   public int Type = 0;
/* 86:65 */   private int lastTick = 0;
/* 87:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.FluidBuffer
 * JD-Core Version:    0.7.0.1
 */