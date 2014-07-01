/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import com.eloraam.redpower.RedPowerCore;
/*   4:    */ import java.io.BufferedReader;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.InputStream;
/*   7:    */ import java.io.InputStreamReader;
/*   8:    */ import java.io.StreamTokenizer;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ 
/*  11:    */ public class RenderModel
/*  12:    */ {
/*  13:    */   public Vector3[] vertexList;
/*  14:    */   public TexVertex[][] texList;
/*  15:    */   int[][][] groups;
/*  16:    */   
/*  17:    */   public static class ModelReader
/*  18:    */   {
/*  19:    */     public ArrayList vertex;
/*  20:    */     public ArrayList faceno;
/*  21:    */     public ArrayList texvert;
/*  22:    */     public ArrayList groups;
/*  23:    */     public ArrayList grcnt;
/*  24:    */     
/*  25:    */     public ModelReader()
/*  26:    */     {
/*  27: 13 */       this.vertex = new ArrayList();
/*  28: 14 */       this.texvert = new ArrayList();
/*  29: 15 */       this.faceno = new ArrayList();
/*  30: 16 */       this.groups = new ArrayList();
/*  31: 17 */       this.grcnt = new ArrayList();
/*  32:    */     }
/*  33:    */     
/*  34:    */     private void eatline(StreamTokenizer tok)
/*  35:    */       throws IOException
/*  36:    */     {
/*  37: 21 */       while (tok.nextToken() != -1) {
/*  38: 22 */         if (tok.ttype == 10) {}
/*  39:    */       }
/*  40:    */     }
/*  41:    */     
/*  42:    */     private void endline(StreamTokenizer tok)
/*  43:    */       throws IOException
/*  44:    */     {
/*  45: 28 */       if (tok.nextToken() != 10) {
/*  46: 29 */         throw new IllegalArgumentException("Parse error");
/*  47:    */       }
/*  48:    */     }
/*  49:    */     
/*  50:    */     private double getfloat(StreamTokenizer tok)
/*  51:    */       throws IOException
/*  52:    */     {
/*  53: 35 */       if (tok.nextToken() != -2) {
/*  54: 36 */         throw new IllegalArgumentException("Parse error");
/*  55:    */       }
/*  56: 38 */       return tok.nval;
/*  57:    */     }
/*  58:    */     
/*  59:    */     private int getint(StreamTokenizer tok)
/*  60:    */       throws IOException
/*  61:    */     {
/*  62: 43 */       if (tok.nextToken() != -2) {
/*  63: 44 */         throw new IllegalArgumentException("Parse error");
/*  64:    */       }
/*  65: 46 */       return (int)tok.nval;
/*  66:    */     }
/*  67:    */     
/*  68:    */     private void parseface(StreamTokenizer tok)
/*  69:    */       throws IOException
/*  70:    */     {
/*  71:    */       for (;;)
/*  72:    */       {
/*  73: 52 */         tok.nextToken();
/*  74: 53 */         if ((tok.ttype == -1) || (tok.ttype == 10)) {
/*  75:    */           break;
/*  76:    */         }
/*  77: 56 */         if (tok.ttype != -2) {
/*  78: 57 */           throw new IllegalArgumentException("Parse error");
/*  79:    */         }
/*  80: 59 */         int n1 = (int)tok.nval;
/*  81: 60 */         if (tok.nextToken() != 47) {
/*  82: 61 */           throw new IllegalArgumentException("Parse error");
/*  83:    */         }
/*  84: 63 */         int n2 = getint(tok);
/*  85: 64 */         this.faceno.add(Integer.valueOf(n1));
/*  86: 65 */         this.faceno.add(Integer.valueOf(n2));
/*  87:    */       }
/*  88: 67 */       this.faceno.add(Integer.valueOf(-1));
/*  89: 68 */       this.fno += 1;
/*  90:    */     }
/*  91:    */     
/*  92:    */     private void setgroup(int gr, int sub)
/*  93:    */     {
/*  94: 72 */       this.groups.add(Integer.valueOf(gr));
/*  95: 73 */       this.groups.add(Integer.valueOf(sub));
/*  96: 74 */       this.groups.add(Integer.valueOf(this.fno));
/*  97: 76 */       if (this.grcnt.size() < gr) {
/*  98: 77 */         throw new IllegalArgumentException("Parse error");
/*  99:    */       }
/* 100: 79 */       if (this.grcnt.size() == gr) {
/* 101: 80 */         this.grcnt.add(Integer.valueOf(0));
/* 102:    */       }
/* 103: 82 */       this.grcnt.set(gr, Integer.valueOf(Math.max(((Integer)this.grcnt.get(gr)).intValue(), sub + 1)));
/* 104:    */     }
/* 105:    */     
/* 106:    */     private void parsegroup(StreamTokenizer tok)
/* 107:    */       throws IOException
/* 108:    */     {
/* 109: 87 */       int n1 = getint(tok);
/* 110: 88 */       int n2 = 0;
/* 111:    */       
/* 112: 90 */       tok.nextToken();
/* 113: 91 */       if (tok.ttype == 95)
/* 114:    */       {
/* 115: 92 */         n2 = getint(tok);
/* 116: 93 */         tok.nextToken();
/* 117:    */       }
/* 118: 95 */       setgroup(n1, n2);
/* 119: 96 */       if (tok.ttype != 10) {
/* 120: 97 */         throw new IllegalArgumentException("Parse error");
/* 121:    */       }
/* 122:    */     }
/* 123:    */     
/* 124:    */     public void readModel(InputStream fis)
/* 125:    */       throws IOException
/* 126:    */     {
/* 127:103 */       BufferedReader r = new BufferedReader(new InputStreamReader(fis));
/* 128:    */       
/* 129:105 */       StreamTokenizer tok = new StreamTokenizer(r);
/* 130:106 */       tok.commentChar(35);
/* 131:107 */       tok.eolIsSignificant(true);
/* 132:108 */       tok.lowerCaseMode(false);
/* 133:109 */       tok.parseNumbers();
/* 134:110 */       tok.quoteChar(34);
/* 135:111 */       tok.ordinaryChar(47);
/* 136:113 */       while (tok.nextToken() != -1) {
/* 137:114 */         if (tok.ttype != 10)
/* 138:    */         {
/* 139:116 */           if (tok.ttype != -3) {
/* 140:117 */             throw new IllegalArgumentException("Parse error");
/* 141:    */           }
/* 142:120 */           if (tok.sval.equals("v"))
/* 143:    */           {
/* 144:121 */             Vector3 nv = new Vector3();
/* 145:122 */             nv.x = getfloat(tok);
/* 146:123 */             nv.y = getfloat(tok);
/* 147:124 */             nv.z = getfloat(tok);
/* 148:125 */             this.vertex.add(nv);
/* 149:126 */             endline(tok);
/* 150:    */           }
/* 151:127 */           else if (tok.sval.equals("vt"))
/* 152:    */           {
/* 153:129 */             double f1 = getfloat(tok);
/* 154:130 */             double f2 = getfloat(tok);
/* 155:131 */             this.texvert.add(new TexVertex(0, f1, f2));
/* 156:132 */             endline(tok);
/* 157:    */           }
/* 158:133 */           else if (tok.sval.equals("vtc"))
/* 159:    */           {
/* 160:135 */             double f1 = getfloat(tok);
/* 161:136 */             double f2 = getfloat(tok);
/* 162:137 */             TexVertex tv = new TexVertex(0, f1, f2);
/* 163:138 */             tv.r = ((float)getfloat(tok));
/* 164:139 */             tv.g = ((float)getfloat(tok));
/* 165:140 */             tv.b = ((float)getfloat(tok));
/* 166:141 */             this.texvert.add(tv);
/* 167:142 */             endline(tok);
/* 168:    */           }
/* 169:143 */           else if (tok.sval.equals("f"))
/* 170:    */           {
/* 171:144 */             parseface(tok);
/* 172:    */           }
/* 173:145 */           else if (tok.sval.equals("g"))
/* 174:    */           {
/* 175:146 */             parsegroup(tok);
/* 176:    */           }
/* 177:    */           else
/* 178:    */           {
/* 179:147 */             eatline(tok);
/* 180:    */           }
/* 181:    */         }
/* 182:    */       }
/* 183:150 */       fis.close();
/* 184:    */     }
/* 185:    */     
/* 186:158 */     int fno = 0;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public static RenderModel loadModel(String name)
/* 190:    */   {
/* 191:162 */     InputStream is = RedPowerCore.class.getResourceAsStream(name);
/* 192:    */     
/* 193:164 */     ModelReader ml = new ModelReader();
/* 194:    */     try
/* 195:    */     {
/* 196:166 */       ml.readModel(is);
/* 197:    */     }
/* 198:    */     catch (IOException e)
/* 199:    */     {
/* 200:168 */       return null;
/* 201:    */     }
/* 202:170 */     ArrayList vtl = new ArrayList();
/* 203:    */     
/* 204:    */ 
/* 205:173 */     int i = 0;
/* 206:174 */     while (i < ml.faceno.size())
/* 207:    */     {
/* 208:175 */       TexVertex[] tv = new TexVertex[4];
/* 209:176 */       for (int j = 0; j < 4; j++)
/* 210:    */       {
/* 211:177 */         int n = ((Integer)ml.faceno.get(i)).intValue();i++;
/* 212:178 */         if (n < 0) {
/* 213:179 */           throw new IllegalArgumentException("Non-Quad Face");
/* 214:    */         }
/* 215:182 */         int m = ((Integer)ml.faceno.get(i)).intValue();i++;
/* 216:    */         
/* 217:    */ 
/* 218:    */ 
/* 219:    */ 
/* 220:    */ 
/* 221:    */ 
/* 222:189 */         TexVertex t = ((TexVertex)ml.texvert.get(m - 1)).copy();
/* 223:190 */         t.vtx = (n - 1);
/* 224:191 */         t.v = (1.0D - t.v);
/* 225:192 */         tv[j] = t;
/* 226:    */       }
/* 227:194 */       int n = ((Integer)ml.faceno.get(i)).intValue();i++;
/* 228:195 */       if (n >= 0) {
/* 229:196 */         throw new IllegalArgumentException("Non-Quad Face");
/* 230:    */       }
/* 231:199 */       vtl.add(tv);
/* 232:    */     }
/* 233:202 */     RenderModel tr = new RenderModel();
/* 234:203 */     tr.vertexList = ((Vector3[])ml.vertex.toArray(new Vector3[0]));
/* 235:204 */     tr.texList = ((TexVertex[][])vtl.toArray(new TexVertex[0][]));
/* 236:    */     
/* 237:206 */     tr.groups = new int[ml.grcnt.size()][][];
/* 238:207 */     for (i = 0; i < ml.grcnt.size(); i++)
/* 239:    */     {
/* 240:208 */       int l = ((Integer)ml.grcnt.get(i)).intValue();
/* 241:209 */       tr.groups[i] = new int[l][];
/* 242:210 */       for (int j = 0; j < ((Integer)ml.grcnt.get(i)).intValue(); j++) {
/* 243:211 */         tr.groups[i][j] = new int[2];
/* 244:    */       }
/* 245:    */     }
/* 246:215 */     i = 0;
/* 247:216 */     int lgs = -1;int lgmn = -1;int lgsn = -1;
/* 248:217 */     while (i < ml.groups.size())
/* 249:    */     {
/* 250:218 */       if (lgs >= 0)
/* 251:    */       {
/* 252:219 */         tr.groups[lgmn][lgsn][0] = lgs;
/* 253:220 */         tr.groups[lgmn][lgsn][1] = ((Integer)ml.groups.get(i + 2)).intValue();
/* 254:    */       }
/* 255:222 */       lgmn = ((Integer)ml.groups.get(i)).intValue();
/* 256:223 */       lgsn = ((Integer)ml.groups.get(i + 1)).intValue();
/* 257:224 */       lgs = ((Integer)ml.groups.get(i + 2)).intValue();
/* 258:225 */       i += 3;
/* 259:    */     }
/* 260:227 */     if (lgs >= 0)
/* 261:    */     {
/* 262:228 */       tr.groups[lgmn][lgsn][0] = lgs;
/* 263:229 */       tr.groups[lgmn][lgsn][1] = ml.fno;
/* 264:    */     }
/* 265:231 */     return tr;
/* 266:    */   }
/* 267:    */   
/* 268:    */   public void scale(double sf)
/* 269:    */   {
/* 270:235 */     for (int i = 0; i < this.vertexList.length; i++) {
/* 271:236 */       this.vertexList[i].multiply(sf);
/* 272:    */     }
/* 273:    */   }
/* 274:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.RenderModel
 * JD-Core Version:    0.7.0.1
 */