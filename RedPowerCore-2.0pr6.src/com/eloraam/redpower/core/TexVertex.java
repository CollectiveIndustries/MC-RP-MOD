/*  1:   */ 
/*  2:   */ 
/*  3:   */ TexVertex
/*  4:   */ 
/*  5:   */   vtx
/*  6:   */   u
/*  7:   */   v
/*  8:   */   r
/*  9:   */   g
/* 10:   */   b
/* 11:   */   brtex
/* 12:   */   
/* 13:   */   TexVertex {}
/* 14:   */   
/* 15:   */   TexVertex, , , 
/* 16:   */   
/* 17: 6 */     vtx = vti;
/* 18: 7 */     this.u = ((tn & 0xF) * 0.0625D + ui * 0.0625D);
/* 19: 8 */     this.v = ((tn >> 4) * 0.0625D + vi * 0.0625D);
/* 20:   */     
/* 21:10 */     this.r = 1.0F;this.g = 1.0F;this.b = 1.0F;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public TexVertex(int vti, double ui, double vi)
/* 25:   */   {
/* 26:13 */     this.vtx = vti;
/* 27:14 */     this.u = ui;this.v = vi;
/* 28:15 */     this.r = 1.0F;this.g = 1.0F;this.b = 1.0F;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setUV(double ui, double vi)
/* 32:   */   {
/* 33:18 */     this.u = ui;this.v = vi;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public TexVertex copy()
/* 37:   */   {
/* 38:22 */     TexVertex tr = new TexVertex(this.vtx, this.u, this.v);
/* 39:23 */     tr.r = this.r;tr.g = this.g;tr.b = this.b;
/* 40:24 */     tr.brtex = this.brtex;
/* 41:25 */     return tr;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TexVertex
 * JD-Core Version:    0.7.0.1
 */