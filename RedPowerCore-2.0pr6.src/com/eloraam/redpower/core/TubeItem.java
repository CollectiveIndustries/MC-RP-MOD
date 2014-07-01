/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import bq;
/*  4:   */ import java.io.IOException;
/*  5:   */ import ur;
/*  6:   */ 
/*  7:   */ public class TubeItem
/*  8:   */ {
/*  9:   */   public TubeItem() {}
/* 10:   */   
/* 11:   */   public TubeItem(int s, ur stk)
/* 12:   */   {
/* 13:13 */     this.item = stk;
/* 14:14 */     this.side = ((byte)s);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void readFromNBT(bq nbt)
/* 18:   */   {
/* 19:18 */     this.item = ur.a(nbt);
/* 20:19 */     this.side = nbt.c("side");
/* 21:20 */     this.progress = nbt.d("pos");
/* 22:21 */     this.mode = nbt.c("mode");
/* 23:22 */     this.color = nbt.c("col");
/* 24:23 */     this.priority = nbt.d("prio");
/* 25:24 */     if (this.progress < 0)
/* 26:   */     {
/* 27:25 */       this.scheduled = true;this.progress = ((short)(-this.progress - 1));
/* 28:   */     }
/* 29:27 */     this.power = ((short)(nbt.c("pow") & 0xFF));
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void writeToNBT(bq nbt)
/* 33:   */   {
/* 34:31 */     this.item.b(nbt);
/* 35:32 */     nbt.a("side", this.side);
/* 36:33 */     nbt.a("pos", (short)(this.scheduled ? -this.progress - 1 : this.progress));
/* 37:   */     
/* 38:35 */     nbt.a("mode", this.mode);
/* 39:36 */     nbt.a("col", this.color);
/* 40:37 */     nbt.a("pow", (byte)this.power);
/* 41:38 */     nbt.a("prio", this.priority);
/* 42:   */   }
/* 43:   */   
/* 44:   */   public static TubeItem newFromNBT(bq nbt)
/* 45:   */   {
/* 46:42 */     TubeItem ti = new TubeItem();
/* 47:43 */     ti.readFromNBT(nbt);
/* 48:44 */     return ti;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void readFromPacket(Packet211TileDesc pkt)
/* 52:   */     throws IOException
/* 53:   */   {
/* 54:49 */     this.side = ((byte)pkt.getByte());
/* 55:50 */     this.progress = ((short)(int)pkt.getVLC());
/* 56:51 */     if (this.progress < 0)
/* 57:   */     {
/* 58:52 */       this.scheduled = true;this.progress = ((short)(-this.progress - 1));
/* 59:   */     }
/* 60:54 */     this.color = ((byte)pkt.getByte());
/* 61:55 */     this.power = ((short)(byte)pkt.getByte());
/* 62:   */     
/* 63:   */ 
/* 64:   */ 
/* 65:59 */     int n = pkt.getByte();
/* 66:60 */     int dmg = (int)pkt.getUVLC();
/* 67:61 */     int iid = (int)pkt.getUVLC();
/* 68:62 */     this.item = new ur(up.e[iid], n, dmg);
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void writeToPacket(Packet211TileDesc pkt)
/* 72:   */   {
/* 73:66 */     pkt.addByte(this.side);
/* 74:67 */     int prg = this.scheduled ? -this.progress - 1 : this.progress;
/* 75:68 */     pkt.addVLC(this.scheduled ? -this.progress - 1 : this.progress);
/* 76:69 */     pkt.addByte(this.color);
/* 77:70 */     pkt.addByte(this.power);
/* 78:71 */     pkt.addByte(this.item.a);
/* 79:72 */     pkt.addUVLC(this.item.j());
/* 80:73 */     pkt.addUVLC(this.item.c);
/* 81:   */   }
/* 82:   */   
/* 83:   */   public static TubeItem newFromPacket(Packet211TileDesc pkt)
/* 84:   */     throws IOException
/* 85:   */   {
/* 86:78 */     TubeItem ti = new TubeItem();
/* 87:79 */     ti.readFromPacket(pkt);
/* 88:80 */     return ti;
/* 89:   */   }
/* 90:   */   
/* 91:83 */   public short progress = 0;
/* 92:84 */   public byte mode = 1;
/* 93:   */   public byte side;
/* 94:84 */   public byte color = 0;
/* 95:85 */   public short power = 0;
/* 96:86 */   public boolean scheduled = false;
/* 97:87 */   public short priority = 0;
/* 98:   */   public ur item;
/* 99:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TubeItem
 * JD-Core Version:    0.7.0.1
 */