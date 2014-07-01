/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import java.util.Formatter;
/*  4:   */ import java.util.Locale;
/*  5:   */ 
/*  6:   */ public class Matrix3
/*  7:   */ {
/*  8:   */   public double xx;
/*  9:   */   public double xy;
/* 10:   */   public double xz;
/* 11:   */   public double yx;
/* 12:   */   public double yy;
/* 13:   */   public double yz;
/* 14:   */   public double zx;
/* 15:   */   public double zy;
/* 16:   */   public double zz;
/* 17:   */   
/* 18:   */   public Matrix3() {}
/* 19:   */   
/* 20:   */   public Matrix3(Quat q)
/* 21:   */   {
/* 22: 9 */     set(q);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void set(Quat q)
/* 26:   */   {
/* 27:13 */     this.xx = (q.s * q.s + q.x * q.x - q.z * q.z - q.y * q.y);
/* 28:14 */     this.xy = (2.0D * (q.s * q.z + q.x * q.y));
/* 29:15 */     this.xz = (2.0D * (q.x * q.z - q.s * q.y));
/* 30:   */     
/* 31:17 */     this.yx = (2.0D * (q.x * q.y - q.s * q.z));
/* 32:18 */     this.yy = (q.s * q.s + q.y * q.y - q.z * q.z - q.x * q.x);
/* 33:19 */     this.yz = (2.0D * (q.s * q.x + q.y * q.z));
/* 34:   */     
/* 35:21 */     this.zx = (2.0D * (q.s * q.y + q.x * q.z));
/* 36:22 */     this.zy = (2.0D * (q.y * q.z - q.s * q.x));
/* 37:23 */     this.zz = (q.s * q.s + q.z * q.z - q.y * q.y - q.x * q.x);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void set(Matrix3 m)
/* 41:   */   {
/* 42:27 */     this.xx = m.xx;this.xy = m.xy;this.xz = m.xz;
/* 43:28 */     this.yx = m.yx;this.yy = m.yy;this.yz = m.yz;
/* 44:29 */     this.zx = m.zx;this.zy = m.zy;this.zz = m.zz;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public Matrix3 multiply(Matrix3 m)
/* 48:   */   {
/* 49:33 */     Matrix3 tr = new Matrix3();
/* 50:   */     
/* 51:35 */     tr.xx = (this.xx * m.xx + this.xy * m.yx + this.xz * m.zx);
/* 52:36 */     tr.xy = (this.xx * m.xy + this.xy * m.yy + this.xz * m.zy);
/* 53:37 */     tr.xz = (this.xx * m.xz + this.xy * m.yz + this.xz * m.zz);
/* 54:   */     
/* 55:39 */     tr.yx = (this.yx * m.xx + this.yy * m.yx + this.yz * m.zx);
/* 56:40 */     tr.yy = (this.yx * m.xy + this.yy * m.yy + this.yz * m.zy);
/* 57:41 */     tr.yz = (this.yx * m.xz + this.yy * m.yz + this.yz * m.zz);
/* 58:   */     
/* 59:43 */     tr.zx = (this.zx * m.xx + this.zy * m.yx + this.zz * m.zx);
/* 60:44 */     tr.zy = (this.zx * m.xy + this.zy * m.yy + this.zz * m.zy);
/* 61:45 */     tr.zz = (this.zx * m.xz + this.zy * m.yz + this.zz * m.zz);
/* 62:   */     
/* 63:47 */     return tr;
/* 64:   */   }
/* 65:   */   
/* 66:   */   public static Matrix3 getRotY(double angle)
/* 67:   */   {
/* 68:51 */     double c = Math.cos(angle);
/* 69:52 */     double s = Math.sin(angle);
/* 70:   */     
/* 71:54 */     Matrix3 tr = new Matrix3();
/* 72:   */     
/* 73:56 */     tr.xx = c;tr.xy = 0.0D;tr.xz = s;
/* 74:57 */     tr.yx = 0.0D;tr.yy = 1.0D;tr.yz = 0.0D;
/* 75:58 */     tr.zx = (-s);tr.zy = 0.0D;tr.zz = c;
/* 76:59 */     return tr;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public Vector3 getBasisVector(int n)
/* 80:   */   {
/* 81:63 */     if (n == 0) {
/* 82:63 */       return new Vector3(this.xx, this.xy, this.xz);
/* 83:   */     }
/* 84:64 */     if (n == 1) {
/* 85:64 */       return new Vector3(this.yx, this.yy, this.yz);
/* 86:   */     }
/* 87:65 */     return new Vector3(this.zx, this.zy, this.zz);
/* 88:   */   }
/* 89:   */   
/* 90:   */   public double det()
/* 91:   */   {
/* 92:69 */     return this.xx * (this.yy * this.zz - this.yz * this.zy) - this.xy * (this.yx * this.zz - this.yz * this.zx) + this.xz * (this.yx * this.zy - this.yy * this.zx);
/* 93:   */   }
/* 94:   */   
/* 95:   */   public void rotate(Vector3 v)
/* 96:   */   {
/* 97:75 */     double tx = this.xx * v.x + this.yx * v.y + this.zx * v.z;
/* 98:76 */     double ty = this.xy * v.x + this.yy * v.y + this.zy * v.z;
/* 99:77 */     double tz = this.xz * v.x + this.yz * v.y + this.zz * v.z;
/* :0:   */     
/* :1:79 */     v.x = tx;v.y = ty;v.z = tz;
/* :2:   */   }
/* :3:   */   
/* :4:   */   public String toString()
/* :5:   */   {
/* :6:83 */     StringBuilder sb = new StringBuilder();
/* :7:84 */     Formatter fmt = new Formatter(sb, Locale.US);
/* :8:   */     
/* :9:86 */     fmt.format("Matrix:\n", new Object[0]);
/* ;0:87 */     fmt.format("  < %f %f %f >\n", new Object[] { Double.valueOf(this.xx), Double.valueOf(this.xy), Double.valueOf(this.xz) });
/* ;1:88 */     fmt.format("  < %f %f %f >\n", new Object[] { Double.valueOf(this.yx), Double.valueOf(this.yy), Double.valueOf(this.yz) });
/* ;2:89 */     fmt.format("  < %f %f %f >\n", new Object[] { Double.valueOf(this.zx), Double.valueOf(this.zy), Double.valueOf(this.zz) });
/* ;3:90 */     return sb.toString();
/* ;4:   */   }
/* ;5:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.Matrix3
 * JD-Core Version:    0.7.0.1
 */