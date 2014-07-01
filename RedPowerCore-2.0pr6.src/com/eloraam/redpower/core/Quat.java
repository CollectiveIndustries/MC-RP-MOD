/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import java.util.Formatter;
/*  4:   */ import java.util.Locale;
/*  5:   */ 
/*  6:   */ public class Quat
/*  7:   */ {
/*  8:   */   public double x;
/*  9:   */   public double y;
/* 10:   */   public double z;
/* 11:   */   public double s;
/* 12:   */   
/* 13:   */   public Quat()
/* 14:   */   {
/* 15: 6 */     this.s = 1.0D;this.x = 0.0D;this.y = 0.0D;this.z = 0.0D;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public Quat(Quat q)
/* 19:   */   {
/* 20:10 */     this.x = q.x;this.y = q.y;this.z = q.z;this.s = q.s;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public Quat(double si, double xi, double yi, double zi)
/* 24:   */   {
/* 25:14 */     this.x = xi;this.y = yi;this.z = zi;this.s = si;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void set(Quat q)
/* 29:   */   {
/* 30:18 */     this.x = q.x;this.y = q.y;this.z = q.z;this.s = q.s;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public static Quat aroundAxis(double xi, double yi, double zi, double a)
/* 34:   */   {
/* 35:23 */     a *= 0.5D;
/* 36:24 */     double sn = Math.sin(a);
/* 37:25 */     return new Quat(Math.cos(a), xi * sn, yi * sn, zi * sn);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void multiply(Quat q)
/* 41:   */   {
/* 42:30 */     double ts = this.s * q.s - this.x * q.x - this.y * q.y - this.z * q.z;
/* 43:31 */     double tx = this.s * q.x + this.x * q.s - this.y * q.z + this.z * q.y;
/* 44:32 */     double ty = this.s * q.y + this.x * q.z + this.y * q.s - this.z * q.x;
/* 45:33 */     double tz = this.s * q.z - this.x * q.y + this.y * q.x + this.z * q.s;
/* 46:   */     
/* 47:35 */     this.s = ts;this.x = tx;this.y = ty;this.z = tz;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void rightMultiply(Quat q)
/* 51:   */   {
/* 52:40 */     double ts = this.s * q.s - this.x * q.x - this.y * q.y - this.z * q.z;
/* 53:41 */     double tx = this.s * q.x + this.x * q.s + this.y * q.z - this.z * q.y;
/* 54:42 */     double ty = this.s * q.y - this.x * q.z + this.y * q.s + this.z * q.x;
/* 55:43 */     double tz = this.s * q.z + this.x * q.y - this.y * q.x + this.z * q.s;
/* 56:   */     
/* 57:45 */     this.s = ts;this.x = tx;this.y = ty;this.z = tz;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public double mag()
/* 61:   */   {
/* 62:49 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.s * this.s);
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void normalize()
/* 66:   */   {
/* 67:53 */     double d = mag();
/* 68:54 */     if (d == 0.0D) {
/* 69:54 */       return;
/* 70:   */     }
/* 71:55 */     d = 1.0D / d;
/* 72:56 */     this.x *= d;this.y *= d;this.z *= d;this.s *= d;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public void rotate(Vector3 v)
/* 76:   */   {
/* 77:62 */     double ts = -this.x * v.x - this.y * v.y - this.z * v.z;
/* 78:63 */     double tx = this.s * v.x + this.y * v.z - this.z * v.y;
/* 79:64 */     double ty = this.s * v.y - this.x * v.z + this.z * v.x;
/* 80:65 */     double tz = this.s * v.z + this.x * v.y - this.y * v.x;
/* 81:   */     
/* 82:67 */     v.x = (tx * this.s - ts * this.x - ty * this.z + tz * this.y);
/* 83:68 */     v.y = (ty * this.s - ts * this.y + tx * this.z - tz * this.x);
/* 84:69 */     v.z = (tz * this.s - ts * this.z - tx * this.y + ty * this.x);
/* 85:   */   }
/* 86:   */   
/* 87:   */   public String toString()
/* 88:   */   {
/* 89:74 */     StringBuilder sb = new StringBuilder();
/* 90:75 */     Formatter fmt = new Formatter(sb, Locale.US);
/* 91:   */     
/* 92:77 */     fmt.format("Quaternion:\n", new Object[0]);
/* 93:78 */     fmt.format("  < %f %f %f %f >\n", new Object[] { Double.valueOf(this.s), Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z) });
/* 94:79 */     return sb.toString();
/* 95:   */   }
/* 96:   */   
/* 97:83 */   public static final double SQRT2 = Math.sqrt(2.0D);
/* 98:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.Quat
 * JD-Core Version:    0.7.0.1
 */