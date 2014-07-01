/*  1:   */ package com.eloraam.redpower.logic;
/*  2:   */ 
/*  3:   */ import com.eloraam.redpower.core.IHandleGuiEvent;
/*  4:   */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.util.List;
/*  7:   */ import la;
/*  8:   */ import qx;
/*  9:   */ import rq;
/* 10:   */ import rw;
/* 11:   */ import ur;
/* 12:   */ 
/* 13:   */ public class ContainerCounter
/* 14:   */   extends rq
/* 15:   */   implements IHandleGuiEvent
/* 16:   */ {
/* 17:   */   private TileLogicStorage tileLogic;
/* 18:   */   
/* 19:   */   public ContainerCounter(la inv, TileLogicStorage tf)
/* 20:   */   {
/* 21:17 */     this.tileLogic = tf;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean a(qx player)
/* 25:   */   {
/* 26:21 */     return this.tileLogic.isUseableByPlayer(player);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public ur b(qx player, int i)
/* 30:   */   {
/* 31:26 */     return null;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void b()
/* 35:   */   {
/* 36:30 */     super.b();
/* 37:   */     
/* 38:32 */     TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)this.tileLogic.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/* 39:36 */     for (int i = 0; i < this.e.size(); i++)
/* 40:   */     {
/* 41:37 */       rw ic = (rw)this.e.get(i);
/* 42:39 */       if (this.Count != lsc.Count) {
/* 43:40 */         ic.a(this, 0, lsc.Count);
/* 44:   */       }
/* 45:42 */       if (this.CountMax != lsc.CountMax) {
/* 46:43 */         ic.a(this, 1, lsc.CountMax);
/* 47:   */       }
/* 48:45 */       if (this.Inc != lsc.Inc) {
/* 49:46 */         ic.a(this, 2, lsc.Inc);
/* 50:   */       }
/* 51:48 */       if (this.Dec != lsc.Dec) {
/* 52:49 */         ic.a(this, 3, lsc.Dec);
/* 53:   */       }
/* 54:   */     }
/* 55:52 */     this.Count = lsc.Count;
/* 56:53 */     this.CountMax = lsc.CountMax;
/* 57:54 */     this.Inc = lsc.Inc;
/* 58:55 */     this.Dec = lsc.Dec;
/* 59:   */   }
/* 60:   */   
/* 61:   */   public void b(int i, int j)
/* 62:   */   {
/* 63:59 */     TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)this.tileLogic.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/* 64:62 */     switch (i)
/* 65:   */     {
/* 66:   */     case 0: 
/* 67:63 */       lsc.Count = j; break;
/* 68:   */     case 1: 
/* 69:64 */       lsc.CountMax = j; break;
/* 70:   */     case 2: 
/* 71:65 */       lsc.Inc = j; break;
/* 72:   */     case 3: 
/* 73:66 */       lsc.Dec = j;
/* 74:   */     }
/* 75:   */   }
/* 76:   */   
/* 77:   */   public void handleGuiEvent(Packet212GuiEvent packet)
/* 78:   */   {
/* 79:72 */     TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)this.tileLogic.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/* 80:   */     try
/* 81:   */     {
/* 82:76 */       switch (packet.eventId)
/* 83:   */       {
/* 84:   */       case 0: 
/* 85:77 */         lsc.Count = ((int)packet.getUVLC());
/* 86:78 */         this.tileLogic.updateBlock();
/* 87:79 */         break;
/* 88:   */       case 1: 
/* 89:80 */         lsc.CountMax = ((int)packet.getUVLC());
/* 90:81 */         this.tileLogic.updateBlock();
/* 91:82 */         break;
/* 92:   */       case 2: 
/* 93:83 */         lsc.Inc = ((int)packet.getUVLC());
/* 94:84 */         this.tileLogic.dirtyBlock();
/* 95:85 */         break;
/* 96:   */       case 3: 
/* 97:86 */         lsc.Dec = ((int)packet.getUVLC());
/* 98:87 */         this.tileLogic.dirtyBlock();
/* 99:   */       }
/* :0:   */     }
/* :1:   */     catch (IOException e) {}
/* :2:   */   }
/* :3:   */   
/* :4:93 */   int Dec = 0;
/* :5:93 */   int Inc = 0;
/* :6:93 */   int CountMax = 0;
/* :7:93 */   int Count = 0;
/* :8:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.ContainerCounter
 * JD-Core Version:    0.7.0.1
 */