/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import java.lang.reflect.Field;
/*  4:   */ import java.lang.reflect.InvocationTargetException;
/*  5:   */ import java.lang.reflect.Method;
/*  6:   */ 
/*  7:   */ public class ReflectLib
/*  8:   */ {
/*  9:   */   public static void callClassMethod(String className, String method, Class[] def, Object... params)
/* 10:   */   {
/* 11:   */     Class cl;
/* 12:   */     try
/* 13:   */     {
/* 14:12 */       cl = Class.forName(className);
/* 15:   */     }
/* 16:   */     catch (ClassNotFoundException e)
/* 17:   */     {
/* 18:   */       return;
/* 19:   */     }
/* 20:   */     Method mth;
/* 21:   */     try
/* 22:   */     {
/* 23:17 */       mth = cl.getDeclaredMethod(method, def);
/* 24:   */     }
/* 25:   */     catch (NoSuchMethodException e)
/* 26:   */     {
/* 27:19 */       return;
/* 28:   */     }
/* 29:   */     try
/* 30:   */     {
/* 31:22 */       mth.invoke(null, params);
/* 32:   */     }
/* 33:   */     catch (IllegalAccessException e) {}catch (InvocationTargetException e) {}
/* 34:   */   }
/* 35:   */   
/* 36:   */   public static Object getStaticField(String classname, String var, Class varcl)
/* 37:   */   {
/* 38:   */     Class cl;
/* 39:   */     try
/* 40:   */     {
/* 41:34 */       cl = Class.forName(classname);
/* 42:   */     }
/* 43:   */     catch (ClassNotFoundException e)
/* 44:   */     {
/* 45:36 */       return null;
/* 46:   */     }
/* 47:   */     Field fld;
/* 48:   */     try
/* 49:   */     {
/* 50:41 */       fld = cl.getDeclaredField(var);
/* 51:   */     }
/* 52:   */     catch (NoSuchFieldException e)
/* 53:   */     {
/* 54:43 */       return null;
/* 55:   */     }
/* 56:   */     Object ob;
/* 57:   */     try
/* 58:   */     {
/* 59:48 */       ob = fld.get(null);
/* 60:   */     }
/* 61:   */     catch (IllegalAccessException e)
/* 62:   */     {
/* 63:50 */       return null;
/* 64:   */     }
/* 65:   */     catch (NullPointerException e)
/* 66:   */     {
/* 67:52 */       return null;
/* 68:   */     }
/* 69:55 */     if (!varcl.isInstance(ob)) {
/* 70:55 */       return null;
/* 71:   */     }
/* 72:56 */     return ob;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public static Object getField(Object ob, String var, Class varcl)
/* 76:   */   {
/* 77:61 */     Class cl = ob.getClass();
/* 78:   */     Field fld;
/* 79:   */     try
/* 80:   */     {
/* 81:65 */       fld = cl.getDeclaredField(var);
/* 82:   */     }
/* 83:   */     catch (NoSuchFieldException e)
/* 84:   */     {
/* 85:67 */       return null;
/* 86:   */     }
/* 87:   */     Object ob2;
/* 88:   */     try
/* 89:   */     {
/* 90:72 */       ob2 = fld.get(ob);
/* 91:   */     }
/* 92:   */     catch (IllegalAccessException e)
/* 93:   */     {
/* 94:74 */       return null;
/* 95:   */     }
/* 96:   */     catch (NullPointerException e)
/* 97:   */     {
/* 98:76 */       return null;
/* 99:   */     }
/* :0:79 */     if (!varcl.isInstance(ob2)) {
/* :1:79 */       return null;
/* :2:   */     }
/* :3:80 */     return ob2;
/* :4:   */   }
/* :5:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.ReflectLib
 * JD-Core Version:    0.7.0.1
 */