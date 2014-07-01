/*   1:    */ package buildcraft.api.core;
/*   2:    */ 
/*   3:    */ import any;
/*   4:    */ import bq;
/*   5:    */ import net.minecraftforge.common.ForgeDirection;
/*   6:    */ 
/*   7:    */ public class Position
/*   8:    */ {
/*   9:    */   public double x;
/*  10:    */   public double y;
/*  11:    */   public double z;
/*  12:    */   public ForgeDirection orientation;
/*  13:    */   
/*  14:    */   public Position(double ci, double cj, double ck)
/*  15:    */   {
/*  16: 22 */     this.x = ci;
/*  17: 23 */     this.y = cj;
/*  18: 24 */     this.z = ck;
/*  19: 25 */     this.orientation = ForgeDirection.UNKNOWN;
/*  20:    */   }
/*  21:    */   
/*  22:    */   public Position(double ci, double cj, double ck, ForgeDirection corientation)
/*  23:    */   {
/*  24: 29 */     this.x = ci;
/*  25: 30 */     this.y = cj;
/*  26: 31 */     this.z = ck;
/*  27: 32 */     this.orientation = corientation;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public Position(Position p)
/*  31:    */   {
/*  32: 36 */     this.x = p.x;
/*  33: 37 */     this.y = p.y;
/*  34: 38 */     this.z = p.z;
/*  35: 39 */     this.orientation = p.orientation;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public Position(bq nbttagcompound)
/*  39:    */   {
/*  40: 43 */     this.x = nbttagcompound.h("i");
/*  41: 44 */     this.y = nbttagcompound.h("j");
/*  42: 45 */     this.z = nbttagcompound.h("k");
/*  43:    */     
/*  44: 47 */     this.orientation = ForgeDirection.UNKNOWN;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public Position(any tile)
/*  48:    */   {
/*  49: 51 */     this.x = tile.l;
/*  50: 52 */     this.y = tile.m;
/*  51: 53 */     this.z = tile.n;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void moveRight(double step)
/*  55:    */   {
/*  56: 57 */     switch (1.$SwitchMap$net$minecraftforge$common$ForgeDirection[this.orientation.ordinal()])
/*  57:    */     {
/*  58:    */     case 1: 
/*  59: 59 */       this.x -= step;
/*  60: 60 */       break;
/*  61:    */     case 2: 
/*  62: 62 */       this.x += step;
/*  63: 63 */       break;
/*  64:    */     case 3: 
/*  65: 65 */       this.z += step;
/*  66: 66 */       break;
/*  67:    */     case 4: 
/*  68: 68 */       this.z -= step;
/*  69: 69 */       break;
/*  70:    */     }
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void moveLeft(double step)
/*  74:    */   {
/*  75: 75 */     moveRight(-step);
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void moveForwards(double step)
/*  79:    */   {
/*  80: 79 */     switch (1.$SwitchMap$net$minecraftforge$common$ForgeDirection[this.orientation.ordinal()])
/*  81:    */     {
/*  82:    */     case 5: 
/*  83: 81 */       this.y += step;
/*  84: 82 */       break;
/*  85:    */     case 6: 
/*  86: 84 */       this.y -= step;
/*  87: 85 */       break;
/*  88:    */     case 1: 
/*  89: 87 */       this.z += step;
/*  90: 88 */       break;
/*  91:    */     case 2: 
/*  92: 90 */       this.z -= step;
/*  93: 91 */       break;
/*  94:    */     case 3: 
/*  95: 93 */       this.x += step;
/*  96: 94 */       break;
/*  97:    */     case 4: 
/*  98: 96 */       this.x -= step;
/*  99: 97 */       break;
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void moveBackwards(double step)
/* 104:    */   {
/* 105:103 */     moveForwards(-step);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void moveUp(double step)
/* 109:    */   {
/* 110:107 */     switch (1.$SwitchMap$net$minecraftforge$common$ForgeDirection[this.orientation.ordinal()])
/* 111:    */     {
/* 112:    */     case 1: 
/* 113:    */     case 2: 
/* 114:    */     case 3: 
/* 115:    */     case 4: 
/* 116:112 */       this.y += step;
/* 117:113 */       break;
/* 118:    */     }
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void moveDown(double step)
/* 122:    */   {
/* 123:120 */     moveUp(-step);
/* 124:    */   }
/* 125:    */   
/* 126:    */   public void writeToNBT(bq nbttagcompound)
/* 127:    */   {
/* 128:124 */     nbttagcompound.a("i", this.x);
/* 129:125 */     nbttagcompound.a("j", this.y);
/* 130:126 */     nbttagcompound.a("k", this.z);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public String toString()
/* 134:    */   {
/* 135:131 */     return "{" + this.x + ", " + this.y + ", " + this.z + "}";
/* 136:    */   }
/* 137:    */   
/* 138:    */   public Position min(Position p)
/* 139:    */   {
/* 140:135 */     return new Position(p.x > this.x ? this.x : p.x, p.y > this.y ? this.y : p.y, p.z > this.z ? this.z : p.z);
/* 141:    */   }
/* 142:    */   
/* 143:    */   public Position max(Position p)
/* 144:    */   {
/* 145:139 */     return new Position(p.x < this.x ? this.x : p.x, p.y < this.y ? this.y : p.y, p.z < this.z ? this.z : p.z);
/* 146:    */   }
/* 147:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.core.Position
 * JD-Core Version:    0.7.0.1
 */