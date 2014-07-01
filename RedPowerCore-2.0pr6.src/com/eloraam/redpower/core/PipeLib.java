/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import any;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.util.Arrays;
/*   7:    */ import java.util.HashMap;
/*   8:    */ import java.util.Map;
/*   9:    */ import java.util.Map.Entry;
/*  10:    */ import net.minecraftforge.common.ForgeDirection;
/*  11:    */ import net.minecraftforge.liquids.ILiquidTank;
/*  12:    */ import net.minecraftforge.liquids.ITankContainer;
/*  13:    */ import net.minecraftforge.liquids.LiquidDictionary;
/*  14:    */ import net.minecraftforge.liquids.LiquidStack;
/*  15:    */ import up;
/*  16:    */ import yc;
/*  17:    */ import ym;
/*  18:    */ 
/*  19:    */ public class PipeLib
/*  20:    */ {
/*  21:    */   private static boolean isConSide(ym iba, int i, int j, int k, int side)
/*  22:    */   {
/*  23: 25 */     any te = iba.q(i, j, k);
/*  24: 26 */     if ((te instanceof IPipeConnectable))
/*  25:    */     {
/*  26: 27 */       IPipeConnectable itc = (IPipeConnectable)te;
/*  27: 28 */       int s = itc.getPipeConnectableSides();
/*  28: 29 */       return (s & 1 << side) > 0;
/*  29:    */     }
/*  30: 31 */     if ((te instanceof ITankContainer))
/*  31:    */     {
/*  32: 32 */       ITankContainer itc = (ITankContainer)te;
/*  33: 33 */       ILiquidTank ilt = itc.getTank(ForgeDirection.getOrientation(side), null);
/*  34:    */       
/*  35: 35 */       return ilt != null;
/*  36:    */     }
/*  37: 37 */     return false;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public static int getConnections(ym iba, int i, int j, int k)
/*  41:    */   {
/*  42: 41 */     IPipeConnectable itc = (IPipeConnectable)CoreLib.getTileEntity(iba, i, j, k, IPipeConnectable.class);
/*  43: 43 */     if (itc == null) {
/*  44: 43 */       return 0;
/*  45:    */     }
/*  46: 45 */     int trs = 0;
/*  47: 46 */     int sides = itc.getPipeConnectableSides();
/*  48: 47 */     if (((sides & 0x1) > 0) && (isConSide(iba, i, j - 1, k, 1))) {
/*  49: 47 */       trs |= 0x1;
/*  50:    */     }
/*  51: 48 */     if (((sides & 0x2) > 0) && (isConSide(iba, i, j + 1, k, 0))) {
/*  52: 48 */       trs |= 0x2;
/*  53:    */     }
/*  54: 49 */     if (((sides & 0x4) > 0) && (isConSide(iba, i, j, k - 1, 3))) {
/*  55: 49 */       trs |= 0x4;
/*  56:    */     }
/*  57: 50 */     if (((sides & 0x8) > 0) && (isConSide(iba, i, j, k + 1, 2))) {
/*  58: 50 */       trs |= 0x8;
/*  59:    */     }
/*  60: 51 */     if (((sides & 0x10) > 0) && (isConSide(iba, i - 1, j, k, 5))) {
/*  61: 51 */       trs |= 0x10;
/*  62:    */     }
/*  63: 52 */     if (((sides & 0x20) > 0) && (isConSide(iba, i + 1, j, k, 4))) {
/*  64: 52 */       trs |= 0x20;
/*  65:    */     }
/*  66: 53 */     return trs;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public static int getFlanges(ym iba, WorldCoord wci, int sides)
/*  70:    */   {
/*  71: 58 */     int tr = 0;
/*  72: 59 */     for (int i = 0; i < 6; i++) {
/*  73: 60 */       if ((sides & 1 << i) != 0)
/*  74:    */       {
/*  75: 62 */         WorldCoord wc = wci.copy();
/*  76: 63 */         wc.step(i);
/*  77:    */         
/*  78: 65 */         any te = iba.q(wc.x, wc.y, wc.z);
/*  79: 66 */         if (te != null)
/*  80:    */         {
/*  81: 67 */           if ((te instanceof IPipeConnectable))
/*  82:    */           {
/*  83: 68 */             IPipeConnectable ipc = (IPipeConnectable)te;
/*  84: 69 */             if ((ipc.getPipeFlangeSides() & 1 << (i ^ 0x1)) > 0) {
/*  85: 70 */               tr |= 1 << i;
/*  86:    */             }
/*  87:    */           }
/*  88: 72 */           if ((te instanceof ITankContainer))
/*  89:    */           {
/*  90: 73 */             ITankContainer itc = (ITankContainer)te;
/*  91: 74 */             ILiquidTank ilt = itc.getTank(ForgeDirection.getOrientation(i ^ 0x1), null);
/*  92: 77 */             if (ilt != null) {
/*  93: 77 */               tr |= 1 << i;
/*  94:    */             }
/*  95:    */           }
/*  96:    */         }
/*  97:    */       }
/*  98:    */     }
/*  99: 80 */     return tr;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public static Integer getPressure(yc world, WorldCoord wc, int dir)
/* 103:    */   {
/* 104: 84 */     any te = world.q(wc.x, wc.y, wc.z);
/* 105: 85 */     if (te == null) {
/* 106: 85 */       return null;
/* 107:    */     }
/* 108: 86 */     if ((te instanceof IPipeConnectable))
/* 109:    */     {
/* 110: 87 */       IPipeConnectable ipc = (IPipeConnectable)te;
/* 111: 88 */       return Integer.valueOf(ipc.getPipePressure(dir));
/* 112:    */     }
/* 113: 90 */     if ((te instanceof ITankContainer))
/* 114:    */     {
/* 115: 91 */       ITankContainer itc = (ITankContainer)te;
/* 116: 92 */       ILiquidTank ilt = itc.getTank(ForgeDirection.getOrientation(dir), null);
/* 117: 94 */       if (ilt == null) {
/* 118: 94 */         return null;
/* 119:    */       }
/* 120: 95 */       int p = ilt.getTankPressure();
/* 121: 96 */       if (p > 0) {
/* 122: 96 */         return Integer.valueOf(100);
/* 123:    */       }
/* 124: 97 */       if (p < 0) {
/* 125: 97 */         return Integer.valueOf(-100);
/* 126:    */       }
/* 127: 98 */       return Integer.valueOf(0);
/* 128:    */     }
/* 129:100 */     return null;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public static void registerVanillaFluid(int blockStill, int blockMoving)
/* 133:    */   {
/* 134:107 */     amq bl = amq.p[blockStill];
/* 135:108 */     if (bl == null) {
/* 136:108 */       return;
/* 137:    */     }
/* 138:110 */     FluidClassVanilla fluid = new FluidClassVanilla(blockStill, blockStill, blockMoving, bl.getTextureFile(), bl.a(0));
/* 139:    */     
/* 140:    */ 
/* 141:    */ 
/* 142:114 */     fluidByItem.put(Arrays.asList(new Integer[] { Integer.valueOf(blockStill), Integer.valueOf(0) }), fluid);
/* 143:115 */     fluidByBlock.put(Integer.valueOf(blockStill), fluid);
/* 144:116 */     fluidByBlock.put(Integer.valueOf(blockMoving), fluid);
/* 145:117 */     fluidByID.put(Integer.valueOf(blockStill), fluid);
/* 146:    */   }
/* 147:    */   
/* 148:    */   public static void registerForgeFluid(String name, LiquidStack stack)
/* 149:    */   {
/* 150:121 */     System.out.printf("Fluid registration: %s\n", new Object[] { name });
/* 151:    */     
/* 152:123 */     up it = up.e[stack.itemID];
/* 153:124 */     if (it == null) {
/* 154:124 */       return;
/* 155:    */     }
/* 156:126 */     int id = stack.itemID + (stack.itemMeta << 16);
/* 157:127 */     FluidClassItem fluid = new FluidClassItem(id, stack.itemID, stack.itemMeta, it.getTextureFile(), CoreProxy.instance.getItemIcon(it, stack.itemMeta));
/* 158:    */     
/* 159:    */ 
/* 160:    */ 
/* 161:131 */     fluidByID.put(Integer.valueOf(id), fluid);
/* 162:132 */     fluidByItem.put(Arrays.asList(new Integer[] { Integer.valueOf(stack.itemID), Integer.valueOf(stack.itemMeta) }), fluid);
/* 163:    */   }
/* 164:    */   
/* 165:    */   public static void registerFluids()
/* 166:    */   {
/* 167:136 */     LiquidDictionary hack = new LiquidDictionary() {};
/* 168:139 */     for (Map.Entry entry : LiquidDictionary.getLiquids().entrySet()) {
/* 169:140 */       registerForgeFluid((String)entry.getKey(), (LiquidStack)entry.getValue());
/* 170:    */     }
/* 171:    */   }
/* 172:    */   
/* 173:143 */   private static HashMap fluidByItem = new HashMap();
/* 174:145 */   private static HashMap fluidByBlock = new HashMap();
/* 175:147 */   private static HashMap fluidByID = new HashMap();
/* 176:    */   
/* 177:    */   public static int getLiquidId(yc world, WorldCoord wc)
/* 178:    */   {
/* 179:151 */     int bid = world.a(wc.x, wc.y, wc.z);
/* 180:    */     
/* 181:153 */     FluidClass fcl = (FluidClass)fluidByBlock.get(Integer.valueOf(bid));
/* 182:154 */     if (fcl == null) {
/* 183:154 */       return 0;
/* 184:    */     }
/* 185:156 */     return fcl.getFluidId(world, wc);
/* 186:    */   }
/* 187:    */   
/* 188:    */   public static FluidClass getLiquidClass(int liquidID)
/* 189:    */   {
/* 190:160 */     return (FluidClass)fluidByID.get(Integer.valueOf(liquidID));
/* 191:    */   }
/* 192:    */   
/* 193:    */   public static FluidClass getLiquidClass(LiquidStack ls)
/* 194:    */   {
/* 195:164 */     return (FluidClass)fluidByItem.get(Arrays.asList(new Integer[] { Integer.valueOf(ls.itemID), Integer.valueOf(ls.itemMeta) }));
/* 196:    */   }
/* 197:    */   
/* 198:    */   public static void movePipeLiquid(yc world, IPipeConnectable src, WorldCoord wsrc, int sides)
/* 199:    */   {
/* 200:173 */     for (int i = 0; i < 6; i++) {
/* 201:174 */       if ((sides & 1 << i) != 0)
/* 202:    */       {
/* 203:176 */         WorldCoord wc = wsrc.coordStep(i);
/* 204:    */         
/* 205:178 */         any te = world.q(wc.x, wc.y, wc.z);
/* 206:179 */         if (te != null) {
/* 207:181 */           if ((te instanceof IPipeConnectable))
/* 208:    */           {
/* 209:182 */             IPipeConnectable ipc = (IPipeConnectable)te;
/* 210:    */             
/* 211:184 */             int p1 = src.getPipePressure(i);
/* 212:185 */             int p2 = ipc.getPipePressure(i ^ 0x1);
/* 213:187 */             if (p1 >= p2)
/* 214:    */             {
/* 215:189 */               FluidBuffer f1 = src.getPipeBuffer(i);
/* 216:190 */               if (f1 != null)
/* 217:    */               {
/* 218:191 */                 int l1 = f1.getLevel();l1 += f1.Delta;
/* 219:192 */                 if ((f1.Type != 0) && (l1 > 0))
/* 220:    */                 {
/* 221:194 */                   FluidBuffer f2 = ipc.getPipeBuffer(i ^ 0x1);
/* 222:195 */                   if (f2 != null)
/* 223:    */                   {
/* 224:196 */                     int l2 = f2.getLevel();
/* 225:197 */                     if ((f2.Type == 0) || (f2.Type == f1.Type))
/* 226:    */                     {
/* 227:199 */                       int qty = Math.max(p1 > p2 ? 25 : 0, (l1 - l2) / 2);
/* 228:200 */                       qty = Math.min(Math.min(qty, f2.getMaxLevel() - l2), l1);
/* 229:201 */                       if (qty > 0)
/* 230:    */                       {
/* 231:202 */                         f1.addLevel(f1.Type, -qty);
/* 232:203 */                         f2.addLevel(f1.Type, qty);
/* 233:    */                       }
/* 234:    */                     }
/* 235:    */                   }
/* 236:    */                 }
/* 237:    */               }
/* 238:    */             }
/* 239:    */           }
/* 240:205 */           else if ((te instanceof ITankContainer))
/* 241:    */           {
/* 242:206 */             ITankContainer itc = (ITankContainer)te;
/* 243:207 */             ILiquidTank ilt = itc.getTank(ForgeDirection.getOrientation(i ^ 0x1), null);
/* 244:210 */             if (ilt != null)
/* 245:    */             {
/* 246:211 */               int p2 = ilt.getTankPressure();
/* 247:212 */               p2 = p2 < 0 ? -100 : p2 > 0 ? 100 : 0;
/* 248:213 */               int p1 = src.getPipePressure(i);
/* 249:    */               
/* 250:215 */               FluidBuffer f1 = src.getPipeBuffer(i);
/* 251:216 */               if (f1 != null)
/* 252:    */               {
/* 253:217 */                 int l1 = f1.getLevel();l1 += f1.Delta;
/* 254:218 */                 int l2 = 0;
/* 255:219 */                 LiquidStack ls = ilt.getLiquid();
/* 256:220 */                 FluidClass fc = null;
/* 257:221 */                 if ((ls != null) && (ls.amount > 0))
/* 258:    */                 {
/* 259:222 */                   fc = getLiquidClass(ls);
/* 260:223 */                   if (fc != null)
/* 261:    */                   {
/* 262:224 */                     l2 = ilt.getLiquid().amount;
/* 263:225 */                     if ((f1.Type != 0) && (f1.Type != fc.getFluidId())) {}
/* 264:    */                   }
/* 265:    */                 }
/* 266:230 */                 else if ((p1 < p2) && (l2 > 0))
/* 267:    */                 {
/* 268:231 */                   int qty = Math.max(25, (l2 - l1) / 2);
/* 269:232 */                   qty = Math.min(Math.min(qty, f1.getMaxLevel() - l1), l2);
/* 270:235 */                   if (qty > 0)
/* 271:    */                   {
/* 272:236 */                     LiquidStack ls2 = ilt.drain(qty, true);
/* 273:237 */                     f1.addLevel(fc.getFluidId(), ls2.amount);
/* 274:    */                   }
/* 275:    */                 }
/* 276:238 */                 else if ((p1 > p2) && (f1.Type != 0) && (l1 > 0))
/* 277:    */                 {
/* 278:239 */                   int qty = Math.max(25, (l1 - l2) / 2);
/* 279:240 */                   qty = Math.min(Math.min(qty, ilt.getCapacity() - l2), l1);
/* 280:242 */                   if (qty > 0)
/* 281:    */                   {
/* 282:244 */                     fc = getLiquidClass(f1.Type);
/* 283:245 */                     qty = ilt.fill(fc.getLiquidStack(qty), true);
/* 284:    */                     
/* 285:247 */                     f1.addLevel(f1.Type, -qty);
/* 286:    */                   }
/* 287:    */                 }
/* 288:    */               }
/* 289:    */             }
/* 290:    */           }
/* 291:    */         }
/* 292:    */       }
/* 293:    */     }
/* 294:    */   }
/* 295:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.PipeLib
 * JD-Core Version:    0.7.0.1
 */