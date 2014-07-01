/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import java.util.Formatter;
/*  4:   */ import java.util.Locale;
/*  5:   */ 
/*  6:   */ public class Vector3
/*  7:   */ {
/*  8:   */   public double x;
/*  9:   */   public double y;
/* 10:   */   public double z;
/* 11:   */   
/* 12:   */   public Vector3() {}
/* 13:   */   
/* 14:   */   public Vector3(double xi, double yi, double zi)
/* 15:   */   {
/* 16: 9 */     this.x = xi;this.y = yi;this.z = zi;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public Vector3(Vector3 v)
/* 20:   */   {
/* 21:13 */     this.x = v.x;this.y = v.y;this.z = v.z;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public Object clone()
/* 25:   */   {
/* 26:17 */     return new Vector3(this);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void set(double xi, double yi, double zi)
/* 30:   */   {
/* 31:21 */     this.x = xi;this.y = yi;this.z = zi;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void set(Vector3 v)
/* 35:   */   {
/* 36:25 */     this.x = v.x;this.y = v.y;this.z = v.z;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public double dotProduct(Vector3 v)
/* 40:   */   {
/* 41:29 */     return v.x * this.x + v.y * this.y + v.z * this.z;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public double dotProduct(double xi, double yi, double zi)
/* 45:   */   {
/* 46:33 */     return xi * this.x + yi * this.y + zi * this.z;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void crossProduct(Vector3 v)
/* 50:   */   {
/* 51:39 */     double tx = this.y * v.z - this.z * v.y;
/* 52:40 */     double ty = this.z * v.x - this.x * v.z;
/* 53:41 */     double tz = this.x * v.y - this.y * v.x;
/* 54:   */     
/* 55:43 */     this.x = tx;this.y = ty;this.z = tz;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void add(double xi, double yi, double zi)
/* 59:   */   {
/* 60:47 */     this.x += xi;this.y += yi;this.z += zi;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void add(Vector3 v)
/* 64:   */   {
/* 65:51 */     this.x += v.x;this.y += v.y;this.z += v.z;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void subtract(Vector3 v)
/* 69:   */   {
/* 70:55 */     this.x -= v.x;this.y -= v.y;this.z -= v.z;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public void multiply(double d)
/* 74:   */   {
/* 75:59 */     this.x *= d;this.y *= d;this.z *= d;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public double mag()
/* 79:   */   {
/* 80:63 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/* 81:   */   }
/* 82:   */   
/* 83:   */   public double magSquared()
/* 84:   */   {
/* 85:67 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public void normalize()
/* 89:   */   {
/* 90:71 */     double d = mag();
/* 91:72 */     if (d == 0.0D) {
/* 92:72 */       return;
/* 93:   */     }
/* 94:73 */     multiply(1.0D / d);
/* 95:   */   }
/* 96:   */   
/* 97:   */   public String toString()
/* 98:   */   {
/* 99:77 */     StringBuilder sb = new StringBuilder();
/* :0:78 */     Formatter fmt = new Formatter(sb, Locale.US);
/* :1:   */     
/* :2:80 */     fmt.format("Vector:\n", new Object[0]);
/* :3:81 */     fmt.format("  < %f %f %f >\n", new Object[] { Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z) });
/* :4:82 */     return sb.toString();
/* :5:   */   }
/* :6:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.Vector3
 * JD-Core Version:    0.7.0.1
 */