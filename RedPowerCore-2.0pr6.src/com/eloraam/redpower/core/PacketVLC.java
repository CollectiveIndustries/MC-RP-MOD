/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import ef;
/*   4:    */ import java.io.ByteArrayInputStream;
/*   5:    */ import java.io.ByteArrayOutputStream;
/*   6:    */ import java.io.DataOutputStream;
/*   7:    */ import java.io.IOException;
/*   8:    */ import java.io.InputStream;
/*   9:    */ 
/*  10:    */ public abstract class PacketVLC
/*  11:    */   extends ef
/*  12:    */ {
/*  13:    */   public PacketVLC()
/*  14:    */   {
/*  15:  9 */     this.headout = new ByteArrayOutputStream();
/*  16: 10 */     this.bodyout = new ByteArrayOutputStream();
/*  17:    */   }
/*  18:    */   
/*  19:    */   public PacketVLC(byte[] data)
/*  20:    */   {
/*  21: 14 */     this.bodyin = new ByteArrayInputStream(data);
/*  22:    */   }
/*  23:    */   
/*  24:    */   public byte[] toByteArray()
/*  25:    */   {
/*  26:    */     try
/*  27:    */     {
/*  28: 19 */       this.bodyout.writeTo(this.headout);
/*  29:    */     }
/*  30:    */     catch (IOException e) {}
/*  31: 21 */     return this.headout.toByteArray();
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void a(DataOutputStream out)
/*  35:    */     throws IOException
/*  36:    */   {
/*  37: 26 */     this.headout.writeTo(out);
/*  38: 27 */     this.bodyout.writeTo(out);
/*  39:    */   }
/*  40:    */   
/*  41:    */   public void addByte(int b)
/*  42:    */   {
/*  43: 31 */     this.bodyout.write(b);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void addUVLC(long l)
/*  47:    */   {
/*  48: 35 */     writeUVLC(this.bodyout, l);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void addVLC(long l)
/*  52:    */   {
/*  53: 39 */     writeVLC(this.bodyout, l);
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void addByteArray(byte[] ba)
/*  57:    */   {
/*  58: 43 */     addUVLC(ba.length);
/*  59: 44 */     this.bodyout.write(ba, 0, ba.length);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public int getByte()
/*  63:    */     throws IOException
/*  64:    */   {
/*  65: 48 */     int i = this.bodyin.read();
/*  66: 49 */     if (i < 0) {
/*  67: 49 */       throw new IOException("Not enough data");
/*  68:    */     }
/*  69: 50 */     return i;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public long getUVLC()
/*  73:    */     throws IOException
/*  74:    */   {
/*  75: 54 */     return readUVLC(this.bodyin);
/*  76:    */   }
/*  77:    */   
/*  78:    */   public long getVLC()
/*  79:    */     throws IOException
/*  80:    */   {
/*  81: 58 */     return readVLC(this.bodyin);
/*  82:    */   }
/*  83:    */   
/*  84:    */   public byte[] getByteArray()
/*  85:    */     throws IOException
/*  86:    */   {
/*  87: 62 */     int ln = (int)getUVLC();
/*  88: 63 */     byte[] tr = new byte[ln];
/*  89: 64 */     this.bodyin.read(tr, 0, ln);
/*  90: 65 */     return tr;
/*  91:    */   }
/*  92:    */   
/*  93:    */   protected static void writeVLC(ByteArrayOutputStream os, long l)
/*  94:    */   {
/*  95: 69 */     if (l >= 0L) {
/*  96: 69 */       l <<= 1;
/*  97:    */     } else {
/*  98: 70 */       l = -l << 1 | 1L;
/*  99:    */     }
/* 100: 71 */     writeUVLC(os, l);
/* 101:    */   }
/* 102:    */   
/* 103:    */   protected static void writeUVLC(ByteArrayOutputStream os, long l)
/* 104:    */   {
/* 105:    */     do
/* 106:    */     {
/* 107: 77 */       int i = (int)(l & 0x7F);l >>>= 7;
/* 108: 78 */       if (l != 0L) {
/* 109: 78 */         i |= 0x80;
/* 110:    */       }
/* 111: 79 */       os.write(i);
/* 112: 80 */     } while (l != 0L);
/* 113:    */   }
/* 114:    */   
/* 115:    */   protected long readUVLC(InputStream in)
/* 116:    */     throws IOException
/* 117:    */   {
/* 118: 84 */     long tr = 0L;
/* 119: 85 */     int sc = 0;
/* 120:    */     do
/* 121:    */     {
/* 122: 87 */       int i = in.read();
/* 123: 88 */       if (i < 0) {
/* 124: 88 */         throw new IOException("Not enough data");
/* 125:    */       }
/* 126: 89 */       this.cnt1 += 1;
/* 127: 90 */       tr |= (i & 0x7F) << sc;
/* 128: 91 */       if ((i & 0x80) == 0) {
/* 129:    */         break;
/* 130:    */       }
/* 131: 92 */       sc += 7;
/* 132: 93 */     } while (sc <= 64);
/* 133: 93 */     throw new IOException("Bad VLC");
/* 134:    */     
/* 135: 95 */     return tr;
/* 136:    */   }
/* 137:    */   
/* 138:    */   protected long readVLC(InputStream in)
/* 139:    */     throws IOException
/* 140:    */   {
/* 141: 99 */     long tr = readUVLC(in);
/* 142:100 */     if ((tr & 1L) == 0L) {
/* 143:100 */       tr >>>= 1;
/* 144:    */     } else {
/* 145:101 */       tr = -(tr >>> 1);
/* 146:    */     }
/* 147:102 */     return tr;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public int a()
/* 151:    */   {
/* 152:106 */     return this.size;
/* 153:    */   }
/* 154:    */   
/* 155:    */   protected void fixLocalPacket()
/* 156:    */   {
/* 157:110 */     this.bodyin = new ByteArrayInputStream(this.bodyout.toByteArray());
/* 158:    */   }
/* 159:    */   
/* 160:114 */   protected int size = 0;
/* 161:114 */   protected int cnt1 = 0;
/* 162:116 */   public ByteArrayOutputStream bodyout = null;
/* 163:116 */   public ByteArrayOutputStream headout = null;
/* 164:117 */   public ByteArrayInputStream bodyin = null;
/* 165:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.PacketVLC
 * JD-Core Version:    0.7.0.1
 */