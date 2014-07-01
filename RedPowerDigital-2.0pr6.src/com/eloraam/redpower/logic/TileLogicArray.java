/*   1:    */ package com.eloraam.redpower.logic;
/*   2:    */ 
/*   3:    */ import bq;
/*   4:    */ import com.eloraam.redpower.core.BlockMultipart;
/*   5:    */ import com.eloraam.redpower.core.CoreLib;
/*   6:    */ import com.eloraam.redpower.core.IRedPowerWiring;
/*   7:    */ import com.eloraam.redpower.core.Packet211TileDesc;
/*   8:    */ import com.eloraam.redpower.core.RedPowerLib;
/*   9:    */ import java.io.IOException;
/*  10:    */ 
/*  11:    */ public class TileLogicArray
/*  12:    */   extends TileLogic
/*  13:    */   implements IRedPowerWiring
/*  14:    */ {
/*  15:    */   public int getPoweringMask(int ch)
/*  16:    */   {
/*  17: 18 */     if (ch != 0) {
/*  18: 18 */       return 0;
/*  19:    */     }
/*  20: 19 */     int tr = 0;
/*  21: 20 */     if (this.PowerVal1 > 0) {
/*  22: 21 */       tr |= RedPowerLib.mapRotToCon(10, this.Rotation);
/*  23:    */     }
/*  24: 22 */     if (this.PowerVal2 > 0) {
/*  25: 23 */       tr |= RedPowerLib.mapRotToCon(5, this.Rotation);
/*  26:    */     }
/*  27: 24 */     return tr;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public void updateCurrentStrength()
/*  31:    */   {
/*  32: 30 */     this.PowerVal2 = ((short)RedPowerLib.updateBlockCurrentStrength(this.k, this, this.l, this.m, this.n, RedPowerLib.mapRotToCon(5, this.Rotation), 1));
/*  33:    */     
/*  34:    */ 
/*  35: 33 */     this.PowerVal1 = ((short)RedPowerLib.updateBlockCurrentStrength(this.k, this, this.l, this.m, this.n, RedPowerLib.mapRotToCon(10, this.Rotation), 1));
/*  36:    */     
/*  37:    */ 
/*  38: 36 */     CoreLib.markBlockDirty(this.k, this.l, this.m, this.n);
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getCurrentStrength(int cons, int ch)
/*  42:    */   {
/*  43: 40 */     if (ch != 0) {
/*  44: 40 */       return -1;
/*  45:    */     }
/*  46: 42 */     if ((RedPowerLib.mapRotToCon(5, this.Rotation) & cons) > 0) {
/*  47: 43 */       return this.PowerVal2;
/*  48:    */     }
/*  49: 44 */     if ((RedPowerLib.mapRotToCon(10, this.Rotation) & cons) > 0) {
/*  50: 45 */       return this.PowerVal1;
/*  51:    */     }
/*  52: 46 */     return -1;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public int scanPoweringStrength(int cons, int ch)
/*  56:    */   {
/*  57: 50 */     if (ch != 0) {
/*  58: 50 */       return 0;
/*  59:    */     }
/*  60: 51 */     int r1 = RedPowerLib.mapRotToCon(5, this.Rotation);
/*  61: 52 */     int r2 = RedPowerLib.mapRotToCon(10, this.Rotation);
/*  62: 53 */     if ((r1 & cons) > 0)
/*  63:    */     {
/*  64: 54 */       if (this.Powered) {
/*  65: 54 */         return 255;
/*  66:    */       }
/*  67: 55 */       if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, r1 & cons, 0)) {
/*  68: 57 */         return 255;
/*  69:    */       }
/*  70: 58 */       return 0;
/*  71:    */     }
/*  72: 60 */     if ((r2 & cons) > 0)
/*  73:    */     {
/*  74: 61 */       if (RedPowerLib.isPowered(this.k, this.l, this.m, this.n, r2 & cons, 0)) {
/*  75: 63 */         return 255;
/*  76:    */       }
/*  77: 64 */       return 0;
/*  78:    */     }
/*  79: 66 */     return 0;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public int getConnectionMask()
/*  83:    */   {
/*  84: 72 */     return RedPowerLib.mapRotToCon(15, this.Rotation);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public int getExtConnectionMask()
/*  88:    */   {
/*  89: 76 */     return 0;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public int getTopwireMask()
/*  93:    */   {
/*  94: 82 */     return RedPowerLib.mapRotToCon(5, this.Rotation);
/*  95:    */   }
/*  96:    */   
/*  97:    */   private boolean cellWantsPower()
/*  98:    */   {
/*  99: 86 */     if (this.SubId == 1) {
/* 100: 87 */       return this.PowerState == 0;
/* 101:    */     }
/* 102: 89 */     return this.PowerState != 0;
/* 103:    */   }
/* 104:    */   
/* 105:    */   private void updatePowerState()
/* 106:    */   {
/* 107: 94 */     this.PowerState = (this.PowerVal1 > 0 ? 1 : 0);
/* 108: 95 */     if (cellWantsPower() != this.Powered) {
/* 109: 96 */       scheduleTick(2);
/* 110:    */     }
/* 111:    */   }
/* 112:    */   
/* 113:    */   public int getExtendedID()
/* 114:    */   {
/* 115:102 */     return 2;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void onBlockNeighborChange(int l)
/* 119:    */   {
/* 120:106 */     if (tryDropBlock()) {
/* 121:106 */       return;
/* 122:    */     }
/* 123:107 */     RedPowerLib.updateCurrent(this.k, this.l, this.m, this.n);
/* 124:109 */     if (this.SubId == 0) {
/* 125:109 */       return;
/* 126:    */     }
/* 127:110 */     if (isTickRunnable()) {
/* 128:110 */       return;
/* 129:    */     }
/* 130:111 */     updatePowerState();
/* 131:    */   }
/* 132:    */   
/* 133:    */   public boolean isBlockStrongPoweringTo(int l)
/* 134:    */   {
/* 135:115 */     if (RedPowerLib.isSearching()) {
/* 136:115 */       return false;
/* 137:    */     }
/* 138:116 */     return (getPoweringMask(0) & RedPowerLib.getConDirMask(l ^ 0x1)) > 0;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public boolean isBlockWeakPoweringTo(int l)
/* 142:    */   {
/* 143:121 */     if (RedPowerLib.isSearching()) {
/* 144:121 */       return false;
/* 145:    */     }
/* 146:122 */     return (getPoweringMask(0) & RedPowerLib.getConDirMask(l ^ 0x1)) > 0;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void onTileTick()
/* 150:    */   {
/* 151:127 */     if (this.Powered != cellWantsPower())
/* 152:    */     {
/* 153:128 */       this.Powered = (!this.Powered);
/* 154:129 */       updateBlockChange();
/* 155:130 */       updatePowerState();
/* 156:    */     }
/* 157:    */   }
/* 158:    */   
/* 159:    */   public void setPartBounds(BlockMultipart bl, int part)
/* 160:    */   {
/* 161:137 */     if (part != this.Rotation >> 2)
/* 162:    */     {
/* 163:138 */       super.setPartBounds(bl, part);
/* 164:139 */       return;
/* 165:    */     }
/* 166:141 */     switch (part)
/* 167:    */     {
/* 168:    */     case 0: 
/* 169:143 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
/* 170:144 */       break;
/* 171:    */     case 1: 
/* 172:146 */       bl.a(0.0F, 0.15F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 173:147 */       break;
/* 174:    */     case 2: 
/* 175:149 */       bl.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
/* 176:150 */       break;
/* 177:    */     case 3: 
/* 178:152 */       bl.a(0.0F, 0.0F, 0.15F, 1.0F, 1.0F, 1.0F);
/* 179:153 */       break;
/* 180:    */     case 4: 
/* 181:155 */       bl.a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
/* 182:156 */       break;
/* 183:    */     case 5: 
/* 184:158 */       bl.a(0.15F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 185:    */     }
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void a(bq tag)
/* 189:    */   {
/* 190:166 */     super.a(tag);
/* 191:167 */     this.PowerVal1 = ((short)(tag.c("pv1") & 0xFF));
/* 192:168 */     this.PowerVal2 = ((short)(tag.c("pv2") & 0xFF));
/* 193:    */   }
/* 194:    */   
/* 195:    */   public void b(bq tag)
/* 196:    */   {
/* 197:172 */     super.b(tag);
/* 198:173 */     tag.a("pv1", (byte)this.PowerVal1);
/* 199:174 */     tag.a("pv2", (byte)this.PowerVal2);
/* 200:    */   }
/* 201:    */   
/* 202:    */   protected void readFromPacket(Packet211TileDesc pkt)
/* 203:    */     throws IOException
/* 204:    */   {
/* 205:179 */     super.readFromPacket(pkt);
/* 206:180 */     if (pkt.subId != 6) {
/* 207:180 */       return;
/* 208:    */     }
/* 209:181 */     this.PowerVal1 = ((short)pkt.getByte());
/* 210:182 */     this.PowerVal2 = ((short)pkt.getByte());
/* 211:    */   }
/* 212:    */   
/* 213:    */   protected void writeToPacket(Packet211TileDesc pkt)
/* 214:    */   {
/* 215:186 */     super.writeToPacket(pkt);
/* 216:187 */     pkt.subId = 6;
/* 217:188 */     pkt.addByte(this.PowerVal1);
/* 218:189 */     pkt.addByte(this.PowerVal2);
/* 219:    */   }
/* 220:    */   
/* 221:192 */   public short PowerVal2 = 0;
/* 222:192 */   public short PowerVal1 = 0;
/* 223:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.logic.TileLogicArray
 * JD-Core Version:    0.7.0.1
 */