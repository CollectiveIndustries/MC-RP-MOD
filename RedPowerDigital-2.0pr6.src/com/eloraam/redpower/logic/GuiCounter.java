/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import atb;
/*   4:    */ import atq;
/*   5:    */ import avf;
/*   6:    */ import ayp;
/*   7:    */ import bba;
/*   8:    */ import com.eloraam.redpower.core.CoreProxy;
/*   9:    */ import com.eloraam.redpower.core.Packet212GuiEvent;
/*  10:    */ import java.util.List;
/*  11:    */ import net.minecraft.client.Minecraft;
/*  12:    */ import org.lwjgl.opengl.GL11;
/*  13:    */ import qw;
/*  14:    */ import rq;
/*  15:    */ 
/*  16:    */ public class GuiCounter
/*  17:    */   extends avf
/*  18:    */ {
/*  19:    */   private TileLogicStorage tileLogic;
/*  20: 18 */   private atb[] buttons = new atb[18];
/*  21:    */   
/*  22:    */   public GuiCounter(qw pli, TileLogicStorage te)
/*  23:    */   {
/*  24: 21 */     super(new ContainerCounter(pli, te));
/*  25: 22 */     this.b = 228;
/*  26: 23 */     this.c = 117;
/*  27:    */     
/*  28: 25 */     this.tileLogic = te;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public GuiCounter(rq cn)
/*  32:    */   {
/*  33: 29 */     super(cn);
/*  34:    */     
/*  35: 31 */     this.b = 228;
/*  36: 32 */     this.c = 117;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void A_()
/*  40:    */   {
/*  41: 36 */     super.A_();
/*  42:    */     
/*  43: 38 */     int bw = this.b - 20;
/*  44: 39 */     int l = (this.g - this.b) / 2;
/*  45: 40 */     int m = (this.h - this.c) / 2;
/*  46:    */     
/*  47: 42 */     this.i.add(this.buttons[0] =  = new atb(1, l + 10, m + 20, bw / 6, 20, "-25"));
/*  48: 43 */     this.i.add(this.buttons[1] =  = new atb(2, l + 10 + bw / 6, m + 20, bw / 6, 20, "-5"));
/*  49: 44 */     this.i.add(this.buttons[2] =  = new atb(3, l + 10 + bw * 2 / 6, m + 20, bw / 6, 20, "-1"));
/*  50: 45 */     this.i.add(this.buttons[3] =  = new atb(4, l + 10 + bw * 3 / 6, m + 20, bw / 6, 20, "+1"));
/*  51: 46 */     this.i.add(this.buttons[4] =  = new atb(5, l + 10 + bw * 4 / 6, m + 20, bw / 6, 20, "+5"));
/*  52: 47 */     this.i.add(this.buttons[5] =  = new atb(6, l + 10 + bw * 5 / 6, m + 20, bw / 6, 20, "+25"));
/*  53:    */     
/*  54: 49 */     this.i.add(this.buttons[6] =  = new atb(7, l + 10, m + 55, bw / 6, 20, "-25"));
/*  55: 50 */     this.i.add(this.buttons[7] =  = new atb(8, l + 10 + bw / 6, m + 55, bw / 6, 20, "-5"));
/*  56: 51 */     this.i.add(this.buttons[8] =  = new atb(9, l + 10 + bw * 2 / 6, m + 55, bw / 6, 20, "-1"));
/*  57: 52 */     this.i.add(this.buttons[9] =  = new atb(10, l + 10 + bw * 3 / 6, m + 55, bw / 6, 20, "+1"));
/*  58: 53 */     this.i.add(this.buttons[10] =  = new atb(11, l + 10 + bw * 4 / 6, m + 55, bw / 6, 20, "+5"));
/*  59: 54 */     this.i.add(this.buttons[11] =  = new atb(12, l + 10 + bw * 5 / 6, m + 55, bw / 6, 20, "+25"));
/*  60:    */     
/*  61: 56 */     this.i.add(this.buttons[12] =  = new atb(13, l + 10, m + 90, bw / 6, 20, "-25"));
/*  62: 57 */     this.i.add(this.buttons[13] =  = new atb(14, l + 10 + bw / 6, m + 90, bw / 6, 20, "-5"));
/*  63: 58 */     this.i.add(this.buttons[14] =  = new atb(15, l + 10 + bw * 2 / 6, m + 90, bw / 6, 20, "-1"));
/*  64: 59 */     this.i.add(this.buttons[15] =  = new atb(16, l + 10 + bw * 3 / 6, m + 90, bw / 6, 20, "+1"));
/*  65: 60 */     this.i.add(this.buttons[16] =  = new atb(17, l + 10 + bw * 4 / 6, m + 90, bw / 6, 20, "+5"));
/*  66: 61 */     this.i.add(this.buttons[17] =  = new atb(18, l + 10 + bw * 5 / 6, m + 90, bw / 6, 20, "+25"));
/*  67:    */   }
/*  68:    */   
/*  69:    */   protected void b(int p1, int p2) {}
/*  70:    */   
/*  71:    */   protected void a(float f, int p1, int p2)
/*  72:    */   {
/*  73: 89 */     atq fontrenderer = this.f.p;
/*  74:    */     
/*  75:    */ 
/*  76: 92 */     TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)this.tileLogic.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/*  77:    */     
/*  78:    */ 
/*  79:    */ 
/*  80: 96 */     int k = this.f.o.b("/eloraam/logic/countergui.png");
/*  81:    */     
/*  82: 98 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  83: 99 */     this.f.o.b(k);
/*  84:100 */     int l = (this.g - this.b) / 2;
/*  85:101 */     int m = (this.h - this.c) / 2;
/*  86:102 */     b(l, m, 0, 0, this.b, this.c);
/*  87:    */     
/*  88:104 */     String str = String.format("Maximum Count: %d", new Object[] { Integer.valueOf(lsc.CountMax) });
/*  89:105 */     a(fontrenderer, str, this.g / 2, m + 10, -1);
/*  90:    */     
/*  91:107 */     str = String.format("Increment: %d", new Object[] { Integer.valueOf(lsc.Inc) });
/*  92:108 */     a(fontrenderer, str, this.g / 2, m + 45, -1);
/*  93:    */     
/*  94:110 */     str = String.format("Decrement: %d", new Object[] { Integer.valueOf(lsc.Dec) });
/*  95:111 */     a(fontrenderer, str, this.g / 2, m + 80, -1);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void changeCountMax(int cc)
/*  99:    */   {
/* 100:122 */     TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)this.tileLogic.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/* 101:    */     
/* 102:    */ 
/* 103:    */ 
/* 104:126 */     lsc.CountMax += cc;
/* 105:127 */     if (lsc.CountMax < 1) {
/* 106:127 */       lsc.CountMax = 1;
/* 107:    */     }
/* 108:129 */     if (!this.f.e.I)
/* 109:    */     {
/* 110:130 */       this.tileLogic.updateBlock();
/* 111:131 */       return;
/* 112:    */     }
/* 113:133 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 114:134 */     pkt.eventId = 1;
/* 115:135 */     pkt.windowId = this.d.d;
/* 116:136 */     pkt.addUVLC(lsc.CountMax);
/* 117:137 */     pkt.encode();
/* 118:138 */     CoreProxy.sendPacketToServer(pkt);
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void changeInc(int cc)
/* 122:    */   {
/* 123:142 */     TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)this.tileLogic.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/* 124:    */     
/* 125:    */ 
/* 126:    */ 
/* 127:146 */     lsc.Inc += cc;
/* 128:147 */     if (lsc.Inc < 1) {
/* 129:147 */       lsc.Inc = 1;
/* 130:    */     }
/* 131:149 */     if (!this.f.e.I)
/* 132:    */     {
/* 133:150 */       this.tileLogic.updateBlock();
/* 134:151 */       return;
/* 135:    */     }
/* 136:153 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 137:154 */     pkt.eventId = 2;
/* 138:155 */     pkt.windowId = this.d.d;
/* 139:156 */     pkt.addUVLC(lsc.Inc);
/* 140:157 */     pkt.encode();
/* 141:158 */     CoreProxy.sendPacketToServer(pkt);
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void changeDec(int cc)
/* 145:    */   {
/* 146:162 */     TileLogicStorage.LogicStorageCounter lsc = (TileLogicStorage.LogicStorageCounter)this.tileLogic.getLogicStorage(TileLogicStorage.LogicStorageCounter.class);
/* 147:    */     
/* 148:    */ 
/* 149:    */ 
/* 150:166 */     lsc.Dec += cc;
/* 151:167 */     if (lsc.Dec < 1) {
/* 152:167 */       lsc.Dec = 1;
/* 153:    */     }
/* 154:169 */     if (!this.f.e.I)
/* 155:    */     {
/* 156:170 */       this.tileLogic.updateBlock();
/* 157:171 */       return;
/* 158:    */     }
/* 159:173 */     Packet212GuiEvent pkt = new Packet212GuiEvent();
/* 160:174 */     pkt.eventId = 3;
/* 161:175 */     pkt.windowId = this.d.d;
/* 162:176 */     pkt.addUVLC(lsc.Dec);
/* 163:177 */     pkt.encode();
/* 164:178 */     CoreProxy.sendPacketToServer(pkt);
/* 165:    */   }
/* 166:    */   
/* 167:    */   protected void a(atb guibutton)
/* 168:    */   {
/* 169:182 */     if (!guibutton.g) {
/* 170:182 */       return;
/* 171:    */     }
/* 172:183 */     switch (guibutton.f)
/* 173:    */     {
/* 174:    */     case 1: 
/* 175:184 */       changeCountMax(-25); break;
/* 176:    */     case 2: 
/* 177:185 */       changeCountMax(-5); break;
/* 178:    */     case 3: 
/* 179:186 */       changeCountMax(-1); break;
/* 180:    */     case 4: 
/* 181:187 */       changeCountMax(1); break;
/* 182:    */     case 5: 
/* 183:188 */       changeCountMax(5); break;
/* 184:    */     case 6: 
/* 185:189 */       changeCountMax(25); break;
/* 186:    */     case 7: 
/* 187:191 */       changeInc(-25); break;
/* 188:    */     case 8: 
/* 189:192 */       changeInc(-5); break;
/* 190:    */     case 9: 
/* 191:193 */       changeInc(-1); break;
/* 192:    */     case 10: 
/* 193:194 */       changeInc(1); break;
/* 194:    */     case 11: 
/* 195:195 */       changeInc(5); break;
/* 196:    */     case 12: 
/* 197:196 */       changeInc(25); break;
/* 198:    */     case 13: 
/* 199:198 */       changeDec(-25); break;
/* 200:    */     case 14: 
/* 201:199 */       changeDec(-5); break;
/* 202:    */     case 15: 
/* 203:200 */       changeDec(-1); break;
/* 204:    */     case 16: 
/* 205:201 */       changeDec(1); break;
/* 206:    */     case 17: 
/* 207:202 */       changeDec(5); break;
/* 208:    */     case 18: 
/* 209:203 */       changeDec(25);
/* 210:    */     }
/* 211:    */   }
/* 212:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.GuiCounter
 * JD-Core Version:    0.7.0.1
 */