/*  1:   */ package buildcraft.api.transport;
/*  2:   */ 
/*  3:   */ import any;
/*  4:   */ 
/*  5:   */ public abstract interface IPipe
/*  6:   */ {
/*  7:   */   public abstract boolean isWired(WireColor paramWireColor);
/*  8:   */   
/*  9:   */   public abstract boolean hasInterface();
/* 10:   */   
/* 11:   */   public abstract any getContainer();
/* 12:   */   
/* 13:   */   public abstract boolean isWireConnectedTo(any paramany, WireColor paramWireColor);
/* 14:   */   
/* 15:   */   public static enum DrawingState
/* 16:   */   {
/* 17:17 */     DrawingPipe,  DrawingRedWire,  DrawingBlueWire,  DrawingGreenWire,  DrawingYellowWire,  DrawingGate;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static enum WireColor
/* 21:   */   {
/* 22:21 */     Red,  Blue,  Green,  Yellow;
/* 23:   */     
/* 24:   */     public WireColor reverse()
/* 25:   */     {
/* 26:24 */       switch (IPipe.1.$SwitchMap$buildcraft$api$transport$IPipe$WireColor[ordinal()])
/* 27:   */       {
/* 28:   */       case 1: 
/* 29:26 */         return Yellow;
/* 30:   */       case 2: 
/* 31:28 */         return Green;
/* 32:   */       case 3: 
/* 33:30 */         return Blue;
/* 34:   */       }
/* 35:32 */       return Red;
/* 36:   */     }
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.transport.IPipe
 * JD-Core Version:    0.7.0.1
 */