/*   1:    */ package com.eloraam.redpower;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.core.Config;
/*   5:    */ import com.eloraam.redpower.core.CoreLib;
/*   6:    */ import com.eloraam.redpower.core.ItemParts;
/*   7:    */ import com.eloraam.redpower.logic.BlockLogic;
/*   8:    */ import com.eloraam.redpower.logic.ItemLogic;
/*   9:    */ import com.eloraam.redpower.logic.LogicProxy;
/*  10:    */ import com.eloraam.redpower.logic.TileLogicAdv;
/*  11:    */ import com.eloraam.redpower.logic.TileLogicArray;
/*  12:    */ import com.eloraam.redpower.logic.TileLogicPointer;
/*  13:    */ import com.eloraam.redpower.logic.TileLogicSimple;
/*  14:    */ import com.eloraam.redpower.logic.TileLogicStorage;
/*  15:    */ import cpw.mods.fml.common.Mod;
/*  16:    */ import cpw.mods.fml.common.Mod.Init;
/*  17:    */ import cpw.mods.fml.common.Mod.Instance;
/*  18:    */ import cpw.mods.fml.common.Mod.PostInit;
/*  19:    */ import cpw.mods.fml.common.Mod.PreInit;
/*  20:    */ import cpw.mods.fml.common.SidedProxy;
/*  21:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  22:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  23:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  24:    */ import cpw.mods.fml.common.network.NetworkMod;
/*  25:    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*  26:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  27:    */ import up;
/*  28:    */ import ur;
/*  29:    */ import wj;
/*  30:    */ 
/*  31:    */ @Mod(modid="RedPowerLogic", name="RedPower Logic", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b", dependencies="required-after:RedPowerBase")
/*  32:    */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/*  33:    */ public class RedPowerLogic
/*  34:    */ {
/*  35:    */   @Mod.Instance("RedPowerLogic")
/*  36:    */   public static RedPowerLogic instance;
/*  37:    */   @SidedProxy(clientSide="com.eloraam.redpower.logic.LogicProxyClient", serverSide="com.eloraam.redpower.logic.LogicProxy")
/*  38:    */   public static LogicProxy proxy;
/*  39:    */   public static BlockLogic blockLogic;
/*  40:    */   public static ItemParts itemParts;
/*  41:    */   public static ur itemAnode;
/*  42:    */   public static ur itemCathode;
/*  43:    */   public static ur itemWire;
/*  44:    */   public static ur itemWafer;
/*  45:    */   public static ur itemPointer;
/*  46:    */   public static ur itemPlate;
/*  47:    */   public static ur itemWaferRedwire;
/*  48:    */   public static ur itemChip;
/*  49:    */   public static ur itemTaintedChip;
/*  50:    */   public static ur itemWaferBundle;
/*  51:    */   public static boolean EnableSounds;
/*  52:    */   
/*  53:    */   @Mod.PreInit
/*  54:    */   public void preInit(FMLPreInitializationEvent event) {}
/*  55:    */   
/*  56:    */   @Mod.Init
/*  57:    */   public void load(FMLInitializationEvent event)
/*  58:    */   {
/*  59: 57 */     EnableSounds = Config.getInt("settings.logic.enableSounds") > 0;
/*  60: 58 */     setupLogic();
/*  61: 59 */     proxy.registerRenderers();
/*  62: 60 */     NetworkRegistry.instance().registerGuiHandler(instance, proxy);
/*  63:    */   }
/*  64:    */   
/*  65:    */   @Mod.PostInit
/*  66:    */   public void postInit(FMLPostInitializationEvent event) {}
/*  67:    */   
/*  68:    */   private static void setupLogic()
/*  69:    */   {
/*  70: 69 */     GameRegistry.registerTileEntity(TileLogicSimple.class, "RPLgSmp");
/*  71: 70 */     GameRegistry.registerTileEntity(TileLogicArray.class, "RPLgAr");
/*  72: 71 */     GameRegistry.registerTileEntity(TileLogicStorage.class, "RPLgStor");
/*  73: 72 */     GameRegistry.registerTileEntity(TileLogicAdv.class, "RPLgAdv");
/*  74: 73 */     GameRegistry.registerTileEntity(TileLogicPointer.class, "RPLgPtr");
/*  75:    */     
/*  76:    */ 
/*  77: 76 */     itemParts = new ItemParts(Config.getItemID("items.logic.parts.id"), "/eloraam/base/items1.png");
/*  78:    */     
/*  79:    */ 
/*  80:    */ 
/*  81: 80 */     itemParts.addItem(0, 0, "item.irwafer");
/*  82: 81 */     itemParts.addItem(1, 1, "item.irwire");
/*  83: 82 */     itemParts.addItem(2, 2, "item.iranode");
/*  84: 83 */     itemParts.addItem(3, 3, "item.ircathode");
/*  85: 84 */     itemParts.addItem(4, 4, "item.irpointer");
/*  86: 85 */     itemParts.addItem(5, 5, "item.irredwire");
/*  87: 86 */     itemParts.addItem(6, 6, "item.irplate");
/*  88: 87 */     itemParts.addItem(7, 7, "item.irchip");
/*  89: 88 */     itemParts.addItem(8, 8, "item.irtchip");
/*  90: 89 */     itemParts.addItem(9, 9, "item.irbundle");
/*  91:    */     
/*  92: 91 */     itemWafer = new ur(itemParts, 1, 0);
/*  93: 92 */     itemWire = new ur(itemParts, 1, 1);
/*  94: 93 */     itemAnode = new ur(itemParts, 1, 2);
/*  95: 94 */     itemCathode = new ur(itemParts, 1, 3);
/*  96: 95 */     itemPointer = new ur(itemParts, 1, 4);
/*  97: 96 */     itemWaferRedwire = new ur(itemParts, 1, 5);
/*  98: 97 */     itemPlate = new ur(itemParts, 1, 6);
/*  99: 98 */     itemChip = new ur(itemParts, 1, 7);
/* 100: 99 */     itemTaintedChip = new ur(itemParts, 1, 8);
/* 101:100 */     itemWaferBundle = new ur(itemParts, 1, 9);
/* 102:    */     
/* 103:    */ 
/* 104:103 */     wj.a().addSmelting(amq.w.cm, 0, new ur(itemParts, 2, 0), 0.1F);
/* 105:    */     
/* 106:    */ 
/* 107:    */ 
/* 108:107 */     GameRegistry.addRecipe(itemWire, new Object[] { "R", "B", Character.valueOf('B'), itemWafer, Character.valueOf('R'), up.aC });
/* 109:    */     
/* 110:    */ 
/* 111:    */ 
/* 112:    */ 
/* 113:    */ 
/* 114:113 */     GameRegistry.addRecipe(new ur(itemParts, 3, 2), new Object[] { " R ", "RRR", "BBB", Character.valueOf('B'), itemWafer, Character.valueOf('R'), up.aC });
/* 115:    */     
/* 116:    */ 
/* 117:    */ 
/* 118:    */ 
/* 119:    */ 
/* 120:119 */     GameRegistry.addRecipe(itemCathode, new Object[] { "T", "B", Character.valueOf('B'), itemWafer, Character.valueOf('T'), amq.aT });
/* 121:    */     
/* 122:    */ 
/* 123:    */ 
/* 124:    */ 
/* 125:    */ 
/* 126:125 */     GameRegistry.addRecipe(itemPointer, new Object[] { "S", "T", "B", Character.valueOf('B'), itemWafer, Character.valueOf('S'), amq.w, Character.valueOf('T'), amq.aT });
/* 127:    */     
/* 128:    */ 
/* 129:    */ 
/* 130:    */ 
/* 131:    */ 
/* 132:    */ 
/* 133:132 */     GameRegistry.addRecipe(itemWaferRedwire, new Object[] { "W", "B", Character.valueOf('B'), itemWafer, Character.valueOf('W'), new ur(RedPowerBase.blockMicro, 1, 256) });
/* 134:    */     
/* 135:    */ 
/* 136:    */ 
/* 137:    */ 
/* 138:    */ 
/* 139:    */ 
/* 140:139 */     GameRegistry.addRecipe(itemPlate, new Object[] { " B ", "SRS", "BCB", Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('R'), RedPowerBase.itemIngotRed, Character.valueOf('S'), up.D });
/* 141:    */     
/* 142:    */ 
/* 143:    */ 
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:147 */     GameRegistry.addRecipe(CoreLib.copyStack(itemChip, 3), new Object[] { " R ", "BBB", Character.valueOf('B'), itemWafer, Character.valueOf('R'), RedPowerBase.itemWaferRed });
/* 149:    */     
/* 150:    */ 
/* 151:    */ 
/* 152:    */ 
/* 153:    */ 
/* 154:153 */     GameRegistry.addShapelessRecipe(CoreLib.copyStack(itemTaintedChip, 1), new Object[] { itemChip, up.aT });
/* 155:    */     
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:158 */     GameRegistry.addRecipe(itemWaferBundle, new Object[] { "W", "B", Character.valueOf('B'), itemWafer, Character.valueOf('W'), new ur(RedPowerBase.blockMicro, 1, 768) });
/* 160:    */     
/* 161:    */ 
/* 162:    */ 
/* 163:    */ 
/* 164:    */ 
/* 165:    */ 
/* 166:165 */     blockLogic = new BlockLogic(Config.getBlockID("blocks.logic.logic.id"));
/* 167:    */     
/* 168:167 */     GameRegistry.registerBlock(blockLogic, ItemLogic.class, "logic");
/* 169:    */     
/* 170:169 */     blockLogic.addTileEntityMapping(0, TileLogicPointer.class);
/* 171:170 */     blockLogic.addTileEntityMapping(1, TileLogicSimple.class);
/* 172:171 */     blockLogic.addTileEntityMapping(2, TileLogicArray.class);
/* 173:172 */     blockLogic.addTileEntityMapping(3, TileLogicStorage.class);
/* 174:173 */     blockLogic.addTileEntityMapping(4, TileLogicAdv.class);
/* 175:    */     
/* 176:175 */     blockLogic.setItemName(0, "irtimer");
/* 177:176 */     blockLogic.setItemName(1, "irseq");
/* 178:177 */     blockLogic.setItemName(2, "irstate");
/* 179:    */     
/* 180:179 */     blockLogic.setItemName(256, "irlatch");
/* 181:180 */     blockLogic.setItemName(257, "irnor");
/* 182:181 */     blockLogic.setItemName(258, "iror");
/* 183:182 */     blockLogic.setItemName(259, "irnand");
/* 184:183 */     blockLogic.setItemName(260, "irand");
/* 185:184 */     blockLogic.setItemName(261, "irxnor");
/* 186:185 */     blockLogic.setItemName(262, "irxor");
/* 187:186 */     blockLogic.setItemName(263, "irpulse");
/* 188:187 */     blockLogic.setItemName(264, "irtoggle");
/* 189:188 */     blockLogic.setItemName(265, "irnot");
/* 190:189 */     blockLogic.setItemName(266, "irbuf");
/* 191:190 */     blockLogic.setItemName(267, "irmux");
/* 192:191 */     blockLogic.setItemName(268, "irrepeater");
/* 193:192 */     blockLogic.setItemName(269, "irsync");
/* 194:193 */     blockLogic.setItemName(270, "irrand");
/* 195:194 */     blockLogic.setItemName(271, "irdlatch");
/* 196:    */     
/* 197:196 */     blockLogic.setItemName(272, "rplightsensor");
/* 198:    */     
/* 199:198 */     blockLogic.setItemName(512, "rpanc");
/* 200:199 */     blockLogic.setItemName(513, "rpainv");
/* 201:200 */     blockLogic.setItemName(514, "rpaninv");
/* 202:    */     
/* 203:202 */     blockLogic.setItemName(768, "ircounter");
/* 204:    */     
/* 205:204 */     blockLogic.setItemName(1024, "irbusxcvr");
/* 206:    */     
/* 207:    */ 
/* 208:207 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 0), new Object[] { "BWB", "WPW", "ACA", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode, Character.valueOf('P'), itemPointer });
/* 209:    */     
/* 210:    */ 
/* 211:    */ 
/* 212:    */ 
/* 213:    */ 
/* 214:    */ 
/* 215:    */ 
/* 216:    */ 
/* 217:216 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 1), new Object[] { "BCB", "CPC", "BCB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode, Character.valueOf('P'), itemPointer });
/* 218:    */     
/* 219:    */ 
/* 220:    */ 
/* 221:    */ 
/* 222:    */ 
/* 223:    */ 
/* 224:    */ 
/* 225:    */ 
/* 226:225 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 2), new Object[] { "BAC", "WSP", "BWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode, Character.valueOf('P'), itemPointer, Character.valueOf('S'), itemChip });
/* 227:    */     
/* 228:    */ 
/* 229:    */ 
/* 230:    */ 
/* 231:    */ 
/* 232:    */ 
/* 233:    */ 
/* 234:    */ 
/* 235:    */ 
/* 236:235 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 256), new Object[] { "WWA", "CBC", "AWW", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 237:    */     
/* 238:    */ 
/* 239:    */ 
/* 240:    */ 
/* 241:    */ 
/* 242:    */ 
/* 243:    */ 
/* 244:243 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 257), new Object[] { "BAB", "WCW", "BWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 245:    */     
/* 246:    */ 
/* 247:    */ 
/* 248:    */ 
/* 249:    */ 
/* 250:    */ 
/* 251:    */ 
/* 252:251 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 258), new Object[] { "BCB", "WCW", "BWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode });
/* 253:    */     
/* 254:    */ 
/* 255:    */ 
/* 256:    */ 
/* 257:    */ 
/* 258:    */ 
/* 259:258 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 259), new Object[] { "AAA", "CCC", "BWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 260:    */     
/* 261:    */ 
/* 262:    */ 
/* 263:    */ 
/* 264:    */ 
/* 265:    */ 
/* 266:    */ 
/* 267:266 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 260), new Object[] { "ACA", "CCC", "BWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 268:    */     
/* 269:    */ 
/* 270:    */ 
/* 271:    */ 
/* 272:    */ 
/* 273:    */ 
/* 274:    */ 
/* 275:274 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 261), new Object[] { "ACA", "CAC", "WCW", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 276:    */     
/* 277:    */ 
/* 278:    */ 
/* 279:    */ 
/* 280:    */ 
/* 281:    */ 
/* 282:    */ 
/* 283:282 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 262), new Object[] { "AWA", "CAC", "WCW", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 284:    */     
/* 285:    */ 
/* 286:    */ 
/* 287:    */ 
/* 288:    */ 
/* 289:    */ 
/* 290:    */ 
/* 291:290 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 263), new Object[] { "ACA", "CAC", "WWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 292:    */     
/* 293:    */ 
/* 294:    */ 
/* 295:    */ 
/* 296:    */ 
/* 297:    */ 
/* 298:    */ 
/* 299:298 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 264), new Object[] { "BCB", "WLW", "BCB", Character.valueOf('L'), amq.aM, Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode });
/* 300:    */     
/* 301:    */ 
/* 302:    */ 
/* 303:    */ 
/* 304:    */ 
/* 305:    */ 
/* 306:    */ 
/* 307:306 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 265), new Object[] { "BAB", "ACA", "BWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 308:    */     
/* 309:    */ 
/* 310:    */ 
/* 311:    */ 
/* 312:    */ 
/* 313:    */ 
/* 314:    */ 
/* 315:314 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 266), new Object[] { "ACA", "WCW", "BWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 316:    */     
/* 317:    */ 
/* 318:    */ 
/* 319:    */ 
/* 320:    */ 
/* 321:    */ 
/* 322:    */ 
/* 323:322 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 267), new Object[] { "ACA", "CBC", "ACW", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 324:    */     
/* 325:    */ 
/* 326:    */ 
/* 327:    */ 
/* 328:    */ 
/* 329:    */ 
/* 330:    */ 
/* 331:330 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 268), new Object[] { "BCW", "BAW", "BWC", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('A'), itemAnode, Character.valueOf('C'), itemCathode });
/* 332:    */     
/* 333:    */ 
/* 334:    */ 
/* 335:    */ 
/* 336:    */ 
/* 337:    */ 
/* 338:    */ 
/* 339:338 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 269), new Object[] { "WCW", "SAS", "WWW", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('A'), itemAnode, Character.valueOf('C'), itemCathode, Character.valueOf('S'), itemChip });
/* 340:    */     
/* 341:    */ 
/* 342:    */ 
/* 343:    */ 
/* 344:    */ 
/* 345:    */ 
/* 346:    */ 
/* 347:    */ 
/* 348:347 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 270), new Object[] { "BSB", "WWW", "SWS", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('S'), itemTaintedChip });
/* 349:    */     
/* 350:    */ 
/* 351:    */ 
/* 352:    */ 
/* 353:    */ 
/* 354:    */ 
/* 355:354 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 271), new Object[] { "ACW", "CCC", "CWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('A'), itemAnode });
/* 356:    */     
/* 357:    */ 
/* 358:    */ 
/* 359:    */ 
/* 360:    */ 
/* 361:    */ 
/* 362:    */ 
/* 363:362 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 272), new Object[] { "BWB", "BSB", "BBB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('S'), RedPowerBase.itemWaferBlue });
/* 364:    */     
/* 365:    */ 
/* 366:    */ 
/* 367:    */ 
/* 368:    */ 
/* 369:    */ 
/* 370:369 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 768), new Object[] { "BWB", "CPC", "BWB", Character.valueOf('W'), itemWire, Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('P'), itemPointer });
/* 371:    */     
/* 372:    */ 
/* 373:    */ 
/* 374:    */ 
/* 375:    */ 
/* 376:    */ 
/* 377:    */ 
/* 378:377 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 512), new Object[] { "BRB", "RRR", "BRB", Character.valueOf('B'), itemWafer, Character.valueOf('R'), itemWaferRedwire });
/* 379:    */     
/* 380:    */ 
/* 381:    */ 
/* 382:    */ 
/* 383:    */ 
/* 384:383 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 513), new Object[] { "BRB", "RPR", "BRB", Character.valueOf('B'), itemWafer, Character.valueOf('R'), itemWaferRedwire, Character.valueOf('P'), itemPlate });
/* 385:    */     
/* 386:    */ 
/* 387:    */ 
/* 388:    */ 
/* 389:    */ 
/* 390:    */ 
/* 391:390 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 514), new Object[] { "BRB", "RPR", "BRC", Character.valueOf('B'), itemWafer, Character.valueOf('C'), itemCathode, Character.valueOf('R'), itemWaferRedwire, Character.valueOf('P'), itemPlate });
/* 392:    */     
/* 393:    */ 
/* 394:    */ 
/* 395:    */ 
/* 396:    */ 
/* 397:    */ 
/* 398:    */ 
/* 399:398 */     GameRegistry.addRecipe(new ur(blockLogic, 1, 1024), new Object[] { "CCC", "WBW", "CCC", Character.valueOf('B'), itemWafer, Character.valueOf('W'), RedPowerBase.itemWaferRed, Character.valueOf('C'), itemWaferBundle });
/* 400:    */   }
/* 401:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerDigital-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerLogic
 * JD-Core Version:    0.7.0.1
 */