/*   1:    */ package com.eloraam.redpower;
/*   2:    */ 
/*   3:    */ import amq;
/*   4:    */ import com.eloraam.redpower.base.BaseProxy;
/*   5:    */ import com.eloraam.redpower.base.BlockAppliance;
/*   6:    */ import com.eloraam.redpower.base.BlockMicro;
/*   7:    */ import com.eloraam.redpower.base.ItemBag;
/*   8:    */ import com.eloraam.redpower.base.ItemDrawplate;
/*   9:    */ import com.eloraam.redpower.base.ItemDyeIndigo;
/*  10:    */ import com.eloraam.redpower.base.ItemHandsaw;
/*  11:    */ import com.eloraam.redpower.base.ItemMicro;
/*  12:    */ import com.eloraam.redpower.base.ItemPlan;
/*  13:    */ import com.eloraam.redpower.base.ItemScrewdriver;
/*  14:    */ import com.eloraam.redpower.base.RecipeBag;
/*  15:    */ import com.eloraam.redpower.base.TileAdvBench;
/*  16:    */ import com.eloraam.redpower.base.TileAlloyFurnace;
/*  17:    */ import com.eloraam.redpower.core.AchieveLib;
/*  18:    */ import com.eloraam.redpower.core.BlockMultiblock;
/*  19:    */ import com.eloraam.redpower.core.Config;
/*  20:    */ import com.eloraam.redpower.core.CoreLib;
/*  21:    */ import com.eloraam.redpower.core.CoverLib;
/*  22:    */ import com.eloraam.redpower.core.CraftLib;
/*  23:    */ import com.eloraam.redpower.core.ItemExtended;
/*  24:    */ import com.eloraam.redpower.core.ItemParts;
/*  25:    */ import com.eloraam.redpower.core.ItemTextured;
/*  26:    */ import com.eloraam.redpower.core.OreStack;
/*  27:    */ import com.eloraam.redpower.core.PipeLib;
/*  28:    */ import com.eloraam.redpower.core.TileCovered;
/*  29:    */ import com.eloraam.redpower.core.TileMultiblock;
/*  30:    */ import cpw.mods.fml.common.Mod;
/*  31:    */ import cpw.mods.fml.common.Mod.Init;
/*  32:    */ import cpw.mods.fml.common.Mod.Instance;
/*  33:    */ import cpw.mods.fml.common.Mod.PostInit;
/*  34:    */ import cpw.mods.fml.common.Mod.PreInit;
/*  35:    */ import cpw.mods.fml.common.SidedProxy;
/*  36:    */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  37:    */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  38:    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  39:    */ import cpw.mods.fml.common.network.NetworkMod;
/*  40:    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*  41:    */ import cpw.mods.fml.common.registry.GameRegistry;
/*  42:    */ import java.util.List;
/*  43:    */ import jh;
/*  44:    */ import net.minecraftforge.common.AchievementPage;
/*  45:    */ import net.minecraftforge.oredict.OreDictionary;
/*  46:    */ import tj;
/*  47:    */ import up;
/*  48:    */ import ur;
/*  49:    */ import wn;
/*  50:    */ 
/*  51:    */ @Mod(modid="RedPowerBase", name="RedPower Base", version="2.0pr6", certificateFingerprint="28f7f8a775e597088f3a418ea29290b6a1d23c7b", dependencies="required-after:RedPowerCore")
/*  52:    */ @NetworkMod(clientSideRequired=true, serverSideRequired=false)
/*  53:    */ public class RedPowerBase
/*  54:    */ {
/*  55:    */   @Mod.Instance("RedPowerBase")
/*  56:    */   public static RedPowerBase instance;
/*  57:    */   @SidedProxy(clientSide="com.eloraam.redpower.base.BaseProxyClient", serverSide="com.eloraam.redpower.base.BaseProxy")
/*  58:    */   public static BaseProxy proxy;
/*  59:    */   public static BlockAppliance blockAppliance;
/*  60:    */   public static up itemHandsawIron;
/*  61:    */   public static up itemHandsawDiamond;
/*  62:    */   public static ItemParts itemLumar;
/*  63:    */   public static ItemParts itemResource;
/*  64:    */   public static ur itemRuby;
/*  65:    */   public static ur itemGreenSapphire;
/*  66:    */   public static ur itemSapphire;
/*  67:    */   public static ur itemIngotSilver;
/*  68:    */   public static ur itemIngotTin;
/*  69:    */   public static ur itemIngotCopper;
/*  70:    */   public static ur itemNikolite;
/*  71:    */   public static ItemParts itemAlloy;
/*  72:    */   public static ur itemIngotRed;
/*  73:    */   public static ur itemIngotBlue;
/*  74:    */   public static ur itemIngotBrass;
/*  75:    */   public static ur itemBouleSilicon;
/*  76:    */   public static ur itemWaferSilicon;
/*  77:    */   public static ur itemWaferBlue;
/*  78:    */   public static ur itemWaferRed;
/*  79:    */   public static ur itemTinplate;
/*  80:    */   public static ur itemFineCopper;
/*  81:    */   public static ur itemFineIron;
/*  82:    */   public static ur itemCopperCoil;
/*  83:    */   public static ur itemMotor;
/*  84:    */   public static ur itemCanvas;
/*  85:    */   public static ItemParts itemNugget;
/*  86:    */   public static ur itemNuggetIron;
/*  87:    */   public static ur itemNuggetSilver;
/*  88:    */   public static ur itemNuggetTin;
/*  89:    */   public static ur itemNuggetCopper;
/*  90:    */   public static up itemDyeIndigo;
/*  91:    */   public static BlockMicro blockMicro;
/*  92:    */   public static BlockMultiblock blockMultiblock;
/*  93:    */   public static ItemScrewdriver itemScrewdriver;
/*  94:    */   public static up itemDrawplateDiamond;
/*  95:    */   public static up itemPlanBlank;
/*  96:    */   public static up itemPlanFull;
/*  97:    */   public static up itemBag;
/*  98:    */   
/*  99:    */   @Mod.PreInit
/* 100:    */   public void preInit(FMLPreInitializationEvent event) {}
/* 101:    */   
/* 102:    */   @Mod.Init
/* 103:    */   public void load(FMLInitializationEvent event)
/* 104:    */   {
/* 105:115 */     initBaseItems();
/* 106:116 */     initAlloys();
/* 107:117 */     initIndigo();
/* 108:118 */     initMicroblocks();
/* 109:119 */     initCoverMaterials();
/* 110:120 */     initFluids();
/* 111:121 */     initBlocks();
/* 112:122 */     initAchievements();
/* 113:    */     
/* 114:124 */     wn.a().b().add(new RecipeBag());
/* 115:    */     
/* 116:    */ 
/* 117:127 */     proxy.registerRenderers();
/* 118:128 */     NetworkRegistry.instance().registerGuiHandler(instance, proxy);
/* 119:    */   }
/* 120:    */   
/* 121:    */   @Mod.PostInit
/* 122:    */   public void postInit(FMLPostInitializationEvent event) {}
/* 123:    */   
/* 124:    */   public static void initBaseItems()
/* 125:    */   {
/* 126:137 */     itemLumar = new ItemParts(Config.getItemID("items.base.lumar.id"), "/eloraam/base/items1.png");
/* 127:    */     
/* 128:    */ 
/* 129:140 */     itemLumar.a(tj.l);
/* 130:143 */     for (int i = 0; i < 16; i++)
/* 131:    */     {
/* 132:144 */       itemLumar.addItem(i, 32 + i, "item.rplumar." + CoreLib.rawColorNames[i]);
/* 133:    */       
/* 134:146 */       Config.addName("item.rplumar." + CoreLib.rawColorNames[i] + ".name", CoreLib.enColorNames[i] + " Lumar");
/* 135:    */       
/* 136:    */ 
/* 137:149 */       ur dye = new ur(up.aW, 1, 15 - i);
/* 138:150 */       GameRegistry.addShapelessRecipe(new ur(itemLumar, 2, i), new Object[] { up.aC, dye, dye, up.aT });
/* 139:    */     }
/* 140:156 */     itemResource = new ItemParts(Config.getItemID("items.base.resource.id"), "/eloraam/base/items1.png");
/* 141:    */     
/* 142:    */ 
/* 143:159 */     itemAlloy = new ItemParts(Config.getItemID("items.base.alloy.id"), "/eloraam/base/items1.png");
/* 144:    */     
/* 145:    */ 
/* 146:162 */     itemResource.a(tj.l);
/* 147:163 */     itemAlloy.a(tj.l);
/* 148:164 */     itemResource.addItem(0, 48, "item.ruby");
/* 149:165 */     itemResource.addItem(1, 49, "item.greenSapphire");
/* 150:166 */     itemResource.addItem(2, 50, "item.sapphire");
/* 151:167 */     itemResource.addItem(3, 51, "item.ingotSilver");
/* 152:168 */     itemResource.addItem(4, 52, "item.ingotTin");
/* 153:169 */     itemResource.addItem(5, 53, "item.ingotCopper");
/* 154:170 */     itemResource.addItem(6, 54, "item.nikolite");
/* 155:    */     
/* 156:172 */     itemAlloy.addItem(0, 64, "item.ingotRed");
/* 157:173 */     itemAlloy.addItem(1, 65, "item.ingotBlue");
/* 158:174 */     itemAlloy.addItem(2, 66, "item.ingotBrass");
/* 159:175 */     itemAlloy.addItem(3, 67, "item.bouleSilicon");
/* 160:176 */     itemAlloy.addItem(4, 68, "item.waferSilicon");
/* 161:177 */     itemAlloy.addItem(5, 69, "item.waferBlue");
/* 162:178 */     itemAlloy.addItem(6, 70, "item.waferRed");
/* 163:179 */     itemAlloy.addItem(7, 71, "item.tinplate");
/* 164:180 */     itemAlloy.addItem(8, 72, "item.finecopper");
/* 165:181 */     itemAlloy.addItem(9, 73, "item.fineiron");
/* 166:182 */     itemAlloy.addItem(10, 74, "item.coppercoil");
/* 167:183 */     itemAlloy.addItem(11, 75, "item.btmotor");
/* 168:184 */     itemAlloy.addItem(12, 76, "item.rpcanvas");
/* 169:    */     
/* 170:186 */     itemRuby = new ur(itemResource, 1, 0);
/* 171:187 */     itemGreenSapphire = new ur(itemResource, 1, 1);
/* 172:188 */     itemSapphire = new ur(itemResource, 1, 2);
/* 173:    */     
/* 174:190 */     itemIngotSilver = new ur(itemResource, 1, 3);
/* 175:191 */     itemIngotTin = new ur(itemResource, 1, 4);
/* 176:192 */     itemIngotCopper = new ur(itemResource, 1, 5);
/* 177:    */     
/* 178:194 */     itemNikolite = new ur(itemResource, 1, 6);
/* 179:    */     
/* 180:196 */     itemIngotRed = new ur(itemAlloy, 1, 0);
/* 181:197 */     itemIngotBlue = new ur(itemAlloy, 1, 1);
/* 182:198 */     itemIngotBrass = new ur(itemAlloy, 1, 2);
/* 183:199 */     itemBouleSilicon = new ur(itemAlloy, 1, 3);
/* 184:200 */     itemWaferSilicon = new ur(itemAlloy, 1, 4);
/* 185:201 */     itemWaferBlue = new ur(itemAlloy, 1, 5);
/* 186:202 */     itemWaferRed = new ur(itemAlloy, 1, 6);
/* 187:203 */     itemTinplate = new ur(itemAlloy, 1, 7);
/* 188:204 */     itemFineCopper = new ur(itemAlloy, 1, 8);
/* 189:205 */     itemFineIron = new ur(itemAlloy, 1, 9);
/* 190:206 */     itemCopperCoil = new ur(itemAlloy, 1, 10);
/* 191:207 */     itemMotor = new ur(itemAlloy, 1, 11);
/* 192:208 */     itemCanvas = new ur(itemAlloy, 1, 12);
/* 193:    */     
/* 194:210 */     OreDictionary.registerOre("gemRuby", itemRuby);
/* 195:211 */     OreDictionary.registerOre("gemGreenSapphire", itemGreenSapphire);
/* 196:212 */     OreDictionary.registerOre("gemSapphire", itemSapphire);
/* 197:    */     
/* 198:214 */     OreDictionary.registerOre("ingotTin", itemIngotTin);
/* 199:215 */     OreDictionary.registerOre("ingotCopper", itemIngotCopper);
/* 200:216 */     OreDictionary.registerOre("ingotSilver", itemIngotSilver);
/* 201:217 */     OreDictionary.registerOre("ingotBrass", itemIngotBrass);
/* 202:218 */     OreDictionary.registerOre("dustNikolite", itemNikolite);
/* 203:    */     
/* 204:    */ 
/* 205:221 */     itemNugget = new ItemParts(Config.getItemID("items.base.nuggets.id"), "/eloraam/base/items1.png");
/* 206:    */     
/* 207:    */ 
/* 208:224 */     itemNugget.a(tj.l);
/* 209:225 */     itemNugget.addItem(0, 160, "item.nuggetIron");
/* 210:226 */     itemNugget.addItem(1, 161, "item.nuggetSilver");
/* 211:227 */     itemNugget.addItem(2, 162, "item.nuggetTin");
/* 212:228 */     itemNugget.addItem(3, 163, "item.nuggetCopper");
/* 213:    */     
/* 214:230 */     itemNuggetIron = new ur(itemNugget, 1, 0);
/* 215:231 */     itemNuggetSilver = new ur(itemNugget, 1, 1);
/* 216:232 */     itemNuggetTin = new ur(itemNugget, 1, 2);
/* 217:233 */     itemNuggetCopper = new ur(itemNugget, 1, 3);
/* 218:    */     
/* 219:235 */     OreDictionary.registerOre("nuggetIron", itemNuggetIron);
/* 220:236 */     OreDictionary.registerOre("nuggetSilver", itemNuggetSilver);
/* 221:237 */     OreDictionary.registerOre("nuggetTin", itemNuggetTin);
/* 222:238 */     OreDictionary.registerOre("nuggetCopper", itemNuggetCopper);
/* 223:    */     
/* 224:    */ 
/* 225:241 */     itemDrawplateDiamond = new ItemDrawplate(Config.getItemID("items.base.drawplateDiamond.id"));
/* 226:    */     
/* 227:243 */     itemDrawplateDiamond.b("drawplateDiamond").c(27).e(255);
/* 228:    */     
/* 229:    */ 
/* 230:    */ 
/* 231:247 */     itemBag = new ItemBag(Config.getItemID("items.base.bag.id"));
/* 232:    */     
/* 233:249 */     GameRegistry.addRecipe(new ur(itemBag, 1, 0), new Object[] { "CCC", "C C", "CCC", Character.valueOf('C'), itemCanvas });
/* 234:252 */     for (int i = 1; i < 16; i++) {
/* 235:253 */       GameRegistry.addRecipe(new ur(itemBag, 1, i), new Object[] { "CCC", "CDC", "CCC", Character.valueOf('C'), itemCanvas, Character.valueOf('D'), new ur(up.aW, 1, 15 - i) });
/* 236:    */     }
/* 237:    */   }
/* 238:    */   
/* 239:    */   public static void initIndigo()
/* 240:    */   {
/* 241:263 */     itemDyeIndigo = new ItemDyeIndigo(Config.getItemID("items.base.dyeIndigo.id"));
/* 242:    */     
/* 243:265 */     OreDictionary.registerOre("dyeBlue", new ur(itemDyeIndigo));
/* 244:    */     
/* 245:    */ 
/* 246:    */ 
/* 247:269 */     GameRegistry.addShapelessRecipe(new ur(amq.ae.cm, 1, 11), new Object[] { itemDyeIndigo, amq.ae });
/* 248:    */     
/* 249:    */ 
/* 250:    */ 
/* 251:    */ 
/* 252:274 */     GameRegistry.addShapelessRecipe(new ur(up.aW, 2, 12), new Object[] { itemDyeIndigo, new ur(up.aW, 1, 15) });
/* 253:    */     
/* 254:    */ 
/* 255:277 */     GameRegistry.addShapelessRecipe(new ur(up.aW, 2, 6), new Object[] { itemDyeIndigo, new ur(up.aW, 1, 2) });
/* 256:    */     
/* 257:    */ 
/* 258:    */ 
/* 259:281 */     GameRegistry.addShapelessRecipe(new ur(up.aW, 2, 5), new Object[] { itemDyeIndigo, new ur(up.aW, 1, 1) });
/* 260:    */     
/* 261:    */ 
/* 262:    */ 
/* 263:285 */     GameRegistry.addShapelessRecipe(new ur(up.aW, 3, 13), new Object[] { itemDyeIndigo, new ur(up.aW, 1, 1), new ur(up.aW, 1, 9) });
/* 264:    */     
/* 265:    */ 
/* 266:    */ 
/* 267:    */ 
/* 268:290 */     GameRegistry.addShapelessRecipe(new ur(up.aW, 4, 13), new Object[] { itemDyeIndigo, new ur(up.aW, 1, 1), new ur(up.aW, 1, 1), new ur(up.aW, 1, 15) });
/* 269:    */     
/* 270:    */ 
/* 271:    */ 
/* 272:    */ 
/* 273:    */ 
/* 274:    */ 
/* 275:    */ 
/* 276:298 */     CraftLib.addShapelessOreRecipe(new ur(itemLumar, 2, 11), new Object[] { up.aC, "dyeBlue", "dyeBlue", up.aT });
/* 277:    */     
/* 278:    */ 
/* 279:    */ 
/* 280:302 */     CraftLib.addOreRecipe(new ur(itemBag, 1, 11), new Object[] { "CCC", "CDC", "CCC", Character.valueOf('C'), itemCanvas, Character.valueOf('D'), "dyeBlue" });
/* 281:    */     
/* 282:    */ 
/* 283:    */ 
/* 284:    */ 
/* 285:    */ 
/* 286:308 */     itemPlanBlank = new ItemTextured(Config.getItemID("items.base.planBlank.id"), 81, "/eloraam/base/items1.png");
/* 287:    */     
/* 288:    */ 
/* 289:311 */     itemPlanBlank.b("planBlank");
/* 290:312 */     itemPlanBlank.a(tj.f);
/* 291:313 */     GameRegistry.addShapelessRecipe(new ur(itemPlanBlank), new Object[] { up.aK, itemDyeIndigo });
/* 292:    */     
/* 293:    */ 
/* 294:316 */     itemPlanFull = new ItemPlan(Config.getItemID("items.base.planFull.id"));
/* 295:    */   }
/* 296:    */   
/* 297:    */   public static void initAlloys()
/* 298:    */   {
/* 299:322 */     CraftLib.addAlloyResult(itemIngotRed, new Object[] { new ur(up.aC, 4), new ur(up.o, 1) });
/* 300:    */     
/* 301:    */ 
/* 302:325 */     CraftLib.addAlloyResult(itemIngotRed, new Object[] { new ur(up.aC, 4), new OreStack("ingotCopper") });
/* 303:    */     
/* 304:    */ 
/* 305:328 */     CraftLib.addAlloyResult(CoreLib.copyStack(itemIngotBrass, 4), new Object[] { new OreStack("ingotTin"), new OreStack("ingotCopper", 3) });
/* 306:    */     
/* 307:    */ 
/* 308:    */ 
/* 309:332 */     CraftLib.addAlloyResult(CoreLib.copyStack(itemTinplate, 4), new Object[] { new OreStack("ingotTin"), new ur(up.o, 2) });
/* 310:    */     
/* 311:    */ 
/* 312:    */ 
/* 313:336 */     CraftLib.addAlloyResult(itemIngotBlue, new Object[] { new OreStack("ingotSilver"), new OreStack("dustNikolite", 4) });
/* 314:    */     
/* 315:    */ 
/* 316:    */ 
/* 317:    */ 
/* 318:341 */     CraftLib.addAlloyResult(new ur(up.o, 3), new Object[] { new ur(amq.aJ, 8) });
/* 319:    */     
/* 320:343 */     CraftLib.addAlloyResult(new ur(up.o, 3), new Object[] { new ur(up.aw, 1) });
/* 321:    */     
/* 322:345 */     CraftLib.addAlloyResult(new ur(up.o, 5), new Object[] { new ur(up.az, 1) });
/* 323:    */     
/* 324:347 */     CraftLib.addAlloyResult(new ur(up.o, 6), new Object[] { new ur(up.aB, 1) });
/* 325:    */     
/* 326:349 */     CraftLib.addAlloyResult(new ur(up.o, 3), new Object[] { new ur(amq.bs, 8) });
/* 327:    */     
/* 328:    */ 
/* 329:352 */     CraftLib.addAlloyResult(new ur(up.o, 31), new Object[] { new ur(amq.ck, 1, 0) });
/* 330:    */     
/* 331:354 */     CraftLib.addAlloyResult(new ur(up.o, 31), new Object[] { new ur(amq.ck, 1, 1) });
/* 332:    */     
/* 333:356 */     CraftLib.addAlloyResult(new ur(up.o, 31), new Object[] { new ur(amq.ck, 1, 2) });
/* 334:    */     
/* 335:    */ 
/* 336:    */ 
/* 337:360 */     CraftLib.addAlloyResult(new ur(up.o, 2), new Object[] { new ur(up.q, 1) });
/* 338:    */     
/* 339:362 */     CraftLib.addAlloyResult(new ur(up.o, 3), new Object[] { new ur(up.g, 1) });
/* 340:    */     
/* 341:364 */     CraftLib.addAlloyResult(new ur(up.o, 3), new Object[] { new ur(up.h, 1) });
/* 342:    */     
/* 343:366 */     CraftLib.addAlloyResult(new ur(up.o, 1), new Object[] { new ur(up.f, 1) });
/* 344:    */     
/* 345:368 */     CraftLib.addAlloyResult(new ur(up.o, 2), new Object[] { new ur(up.P, 1) });
/* 346:    */     
/* 347:    */ 
/* 348:371 */     CraftLib.addAlloyResult(new ur(up.p, 2), new Object[] { new ur(up.G, 1) });
/* 349:    */     
/* 350:373 */     CraftLib.addAlloyResult(new ur(up.p, 3), new Object[] { new ur(up.I, 1) });
/* 351:    */     
/* 352:375 */     CraftLib.addAlloyResult(new ur(up.p, 3), new Object[] { new ur(up.J, 1) });
/* 353:    */     
/* 354:377 */     CraftLib.addAlloyResult(new ur(up.p, 1), new Object[] { new ur(up.H, 1) });
/* 355:    */     
/* 356:379 */     CraftLib.addAlloyResult(new ur(up.p, 2), new Object[] { new ur(up.R, 1) });
/* 357:    */     
/* 358:    */ 
/* 359:    */ 
/* 360:383 */     CraftLib.addAlloyResult(new ur(up.o, 5), new Object[] { new ur(up.ad, 1) });
/* 361:    */     
/* 362:385 */     CraftLib.addAlloyResult(new ur(up.o, 8), new Object[] { new ur(up.ae, 1) });
/* 363:    */     
/* 364:387 */     CraftLib.addAlloyResult(new ur(up.o, 7), new Object[] { new ur(up.af, 1) });
/* 365:    */     
/* 366:389 */     CraftLib.addAlloyResult(new ur(up.o, 4), new Object[] { new ur(up.ag, 1) });
/* 367:    */     
/* 368:    */ 
/* 369:392 */     CraftLib.addAlloyResult(new ur(up.p, 5), new Object[] { new ur(up.al, 1) });
/* 370:    */     
/* 371:394 */     CraftLib.addAlloyResult(new ur(up.p, 8), new Object[] { new ur(up.am, 1) });
/* 372:    */     
/* 373:396 */     CraftLib.addAlloyResult(new ur(up.p, 7), new Object[] { new ur(up.an, 1) });
/* 374:    */     
/* 375:398 */     CraftLib.addAlloyResult(new ur(up.p, 4), new Object[] { new ur(up.ao, 1) });
/* 376:    */     
/* 377:    */ 
/* 378:    */ 
/* 379:402 */     CraftLib.addAlloyResult(new ur(up.p, 1), new Object[] { new ur(up.bq, 9) });
/* 380:    */     
/* 381:    */ 
/* 382:405 */     CraftLib.addAlloyResult(new ur(up.o, 1), new Object[] { CoreLib.copyStack(itemNuggetIron, 9) });
/* 383:    */     
/* 384:407 */     CraftLib.addAlloyResult(itemIngotSilver, new Object[] { CoreLib.copyStack(itemNuggetSilver, 9) });
/* 385:    */     
/* 386:409 */     CraftLib.addAlloyResult(itemIngotCopper, new Object[] { CoreLib.copyStack(itemNuggetCopper, 9) });
/* 387:    */     
/* 388:411 */     CraftLib.addAlloyResult(itemIngotTin, new Object[] { CoreLib.copyStack(itemNuggetTin, 9) });
/* 389:    */     
/* 390:    */ 
/* 391:    */ 
/* 392:415 */     CraftLib.addAlloyResult(itemIngotCopper, new Object[] { itemFineCopper });
/* 393:416 */     CraftLib.addAlloyResult(new ur(up.o, 1), new Object[] { itemFineIron });
/* 394:    */     
/* 395:    */ 
/* 396:    */ 
/* 397:420 */     CraftLib.addAlloyResult(itemBouleSilicon, new Object[] { new ur(up.m, 8, 0), new ur(amq.H, 8) });
/* 398:    */     
/* 399:    */ 
/* 400:423 */     CraftLib.addAlloyResult(itemBouleSilicon, new Object[] { new ur(up.m, 8, 1), new ur(amq.H, 8) });
/* 401:    */     
/* 402:    */ 
/* 403:426 */     CraftLib.addAlloyResult(itemWaferBlue, new Object[] { CoreLib.copyStack(itemWaferSilicon, 1), new OreStack("dustNikolite", 4) });
/* 404:    */     
/* 405:    */ 
/* 406:429 */     CraftLib.addAlloyResult(itemWaferRed, new Object[] { CoreLib.copyStack(itemWaferSilicon, 1), new ur(up.aC, 4) });
/* 407:    */   }
/* 408:    */   
/* 409:    */   public static void initMicroblocks()
/* 410:    */   {
/* 411:435 */     blockMicro = new BlockMicro(Config.getBlockID("blocks.base.microblock.id"));
/* 412:    */     
/* 413:437 */     blockMicro.b("rpwire");
/* 414:    */     
/* 415:439 */     GameRegistry.registerBlock(blockMicro, ItemMicro.class, "micro");
/* 416:440 */     blockMicro.addTileEntityMapping(0, TileCovered.class);
/* 417:    */     
/* 418:442 */     CoverLib.blockCoverPlate = blockMicro;
/* 419:    */   }
/* 420:    */   
/* 421:    */   public static void initFluids()
/* 422:    */   {
/* 423:454 */     PipeLib.registerFluids();
/* 424:    */     
/* 425:456 */     PipeLib.registerVanillaFluid(amq.E.cm, amq.D.cm);
/* 426:    */     
/* 427:    */ 
/* 428:459 */     PipeLib.registerVanillaFluid(amq.G.cm, amq.F.cm);
/* 429:    */   }
/* 430:    */   
/* 431:    */   public static void initCoverMaterials()
/* 432:    */   {
/* 433:485 */     CoverLib.addMaterial(0, 1, amq.z, "cobble", "Cobblestone");
/* 434:    */     
/* 435:487 */     CoverLib.addMaterial(1, 1, amq.w, "stone", "Stone");
/* 436:    */     
/* 437:489 */     CoverLib.addMaterial(2, 0, amq.A, "planks", "Wooden Plank");
/* 438:    */     
/* 439:491 */     CoverLib.addMaterial(3, 1, amq.T, "sandstone", "Sandstone");
/* 440:    */     
/* 441:493 */     CoverLib.addMaterial(4, 1, amq.ar, "moss", "Moss Stone");
/* 442:    */     
/* 443:495 */     CoverLib.addMaterial(5, 1, amq.ao, "brick", "Brick");
/* 444:496 */     CoverLib.addMaterial(6, 2, amq.as, "obsidian", "Obsidian");
/* 445:    */     
/* 446:498 */     CoverLib.addMaterial(7, 1, true, amq.P, "glass", "Glass");
/* 447:499 */     CoverLib.addMaterial(8, 0, amq.y, "dirt", "Dirt");
/* 448:500 */     CoverLib.addMaterial(9, 0, amq.aZ, "clay", "Clay");
/* 449:501 */     CoverLib.addMaterial(10, 0, amq.aq, "books", "Bookshelf");
/* 450:    */     
/* 451:503 */     CoverLib.addMaterial(11, 0, amq.p[87], "netherrack", "Netherrack");
/* 452:    */     
/* 453:505 */     CoverLib.addMaterial(12, 0, amq.M, 0, "wood", "Oak Wood");
/* 454:506 */     CoverLib.addMaterial(13, 0, amq.M, 1, "wood1", "Spruce Wood");
/* 455:507 */     CoverLib.addMaterial(14, 0, amq.M, 2, "wood2", "Birch Wood");
/* 456:508 */     CoverLib.addMaterial(15, 0, amq.bf, "soul", "Soul Sand");
/* 457:    */     
/* 458:510 */     CoverLib.addMaterial(16, 1, amq.an, "slab", "Polished Stone");
/* 459:    */     
/* 460:512 */     CoverLib.addMaterial(17, 1, amq.al, "iron", "Iron");
/* 461:513 */     CoverLib.addMaterial(18, 1, amq.ak, "gold", "Gold");
/* 462:514 */     CoverLib.addMaterial(19, 2, amq.aA, "diamond", "Diamond");
/* 463:    */     
/* 464:516 */     CoverLib.addMaterial(20, 1, amq.R, "lapis", "Lapis Lazuli");
/* 465:    */     
/* 466:518 */     CoverLib.addMaterial(21, 0, amq.aX, "snow", "Snow");
/* 467:519 */     CoverLib.addMaterial(22, 0, amq.bd, "pumpkin", "Pumpkin");
/* 468:520 */     CoverLib.addMaterial(23, 1, amq.bp, 0, "stonebrick", "Stone Brick");
/* 469:    */     
/* 470:522 */     CoverLib.addMaterial(24, 1, amq.bp, 1, "stonebrick1", "Stone Brick");
/* 471:    */     
/* 472:524 */     CoverLib.addMaterial(25, 1, amq.bp, 2, "stonebrick2", "Stone Brick");
/* 473:    */     
/* 474:526 */     CoverLib.addMaterial(26, 1, amq.bD, "netherbrick", "Nether Brick");
/* 475:    */     
/* 476:    */ 
/* 477:529 */     CoverLib.addMaterial(27, 1, amq.bp, 3, "stonebrick3", "Stone Brick");
/* 478:    */     
/* 479:531 */     CoverLib.addMaterial(28, 0, amq.A, 1, "planks1", "Wooden Plank");
/* 480:    */     
/* 481:533 */     CoverLib.addMaterial(29, 0, amq.A, 2, "planks2", "Wooden Plank");
/* 482:    */     
/* 483:535 */     CoverLib.addMaterial(30, 0, amq.A, 3, "planks3", "Wooden Plank");
/* 484:    */     
/* 485:    */ 
/* 486:538 */     CoverLib.addMaterial(31, 1, amq.T, 1, "sandstone1", "Sandstone");
/* 487:    */     
/* 488:540 */     CoverLib.addMaterial(64, 1, amq.T, 2, "sandstone2", "Sandstone");
/* 489:    */     
/* 490:542 */     CoverLib.addMaterial(65, 0, amq.M, 3, "wood3", "Jungle Wood");
/* 491:545 */     for (int i = 0; i < 16; i++) {
/* 492:546 */       CoverLib.addMaterial(32 + i, 0, amq.ae, i, "wool." + CoreLib.rawColorNames[i], CoreLib.enColorNames[i] + " Wool");
/* 493:    */     }
/* 494:    */   }
/* 495:    */   
/* 496:    */   public static void initAchievements()
/* 497:    */   {
/* 498:554 */     AchieveLib.registerAchievement(117027, "rpMakeAlloy", 0, 0, new ur(blockAppliance, 1, 0), jh.j);
/* 499:    */     
/* 500:    */ 
/* 501:557 */     AchieveLib.registerAchievement(117028, "rpMakeSaw", 4, 0, new ur(itemHandsawDiamond), jh.w);
/* 502:    */     
/* 503:    */ 
/* 504:    */ 
/* 505:561 */     AchieveLib.registerAchievement(117029, "rpIngotRed", 2, 2, itemIngotRed, "rpMakeAlloy");
/* 506:    */     
/* 507:563 */     AchieveLib.registerAchievement(117030, "rpIngotBlue", 2, 4, itemIngotBlue, "rpMakeAlloy");
/* 508:    */     
/* 509:565 */     AchieveLib.registerAchievement(117031, "rpIngotBrass", 2, 6, itemIngotBrass, "rpMakeAlloy");
/* 510:    */     
/* 511:    */ 
/* 512:568 */     AchieveLib.registerAchievement(117032, "rpAdvBench", -2, 0, new ur(blockAppliance, 1, 3), jh.h);
/* 513:    */     
/* 514:    */ 
/* 515:    */ 
/* 516:572 */     AchieveLib.addCraftingAchievement(new ur(blockAppliance, 1, 0), "rpMakeAlloy");
/* 517:    */     
/* 518:574 */     AchieveLib.addCraftingAchievement(new ur(blockAppliance, 1, 3), "rpAdvBench");
/* 519:    */     
/* 520:576 */     AchieveLib.addCraftingAchievement(new ur(itemHandsawDiamond), "rpMakeSaw");
/* 521:    */     
/* 522:    */ 
/* 523:    */ 
/* 524:580 */     AchieveLib.addAlloyAchievement(itemIngotRed, "rpIngotRed");
/* 525:581 */     AchieveLib.addAlloyAchievement(itemIngotBlue, "rpIngotBlue");
/* 526:582 */     AchieveLib.addAlloyAchievement(itemIngotBrass, "rpIngotBrass");
/* 527:    */     
/* 528:584 */     AchievementPage.registerAchievementPage(AchieveLib.achievepage);
/* 529:    */   }
/* 530:    */   
/* 531:    */   public static void initBlocks()
/* 532:    */   {
/* 533:589 */     blockMultiblock = new BlockMultiblock(Config.getBlockID("blocks.base.multiblock.id"));
/* 534:    */     
/* 535:591 */     GameRegistry.registerBlock(blockMultiblock, "multi");
/* 536:592 */     GameRegistry.registerTileEntity(TileMultiblock.class, "RPMulti");
/* 537:    */     
/* 538:    */ 
/* 539:595 */     blockAppliance = new BlockAppliance(Config.getBlockID("blocks.base.appliance.id"));
/* 540:    */     
/* 541:597 */     GameRegistry.registerBlock(blockAppliance, ItemExtended.class, "appliance");
/* 542:    */     
/* 543:    */ 
/* 544:    */ 
/* 545:601 */     GameRegistry.registerTileEntity(TileAlloyFurnace.class, "RPAFurnace");
/* 546:602 */     blockAppliance.addTileEntityMapping(0, TileAlloyFurnace.class);
/* 547:603 */     blockAppliance.setItemName(0, "rpafurnace");
/* 548:604 */     GameRegistry.addRecipe(new ur(blockAppliance, 1, 0), new Object[] { "BBB", "B B", "BBB", Character.valueOf('B'), amq.ao });
/* 549:    */     
/* 550:    */ 
/* 551:    */ 
/* 552:    */ 
/* 553:609 */     GameRegistry.registerTileEntity(TileAdvBench.class, "RPAdvBench");
/* 554:610 */     blockAppliance.addTileEntityMapping(3, TileAdvBench.class);
/* 555:611 */     blockAppliance.setItemName(3, "rpabench");
/* 556:612 */     CraftLib.addOreRecipe(new ur(blockAppliance, 1, 3), new Object[] { "SSS", "WTW", "WCW", Character.valueOf('S'), amq.w, Character.valueOf('W'), "plankWood", Character.valueOf('T'), amq.aB, Character.valueOf('C'), amq.ax });
/* 557:    */     
/* 558:    */ 
/* 559:    */ 
/* 560:    */ 
/* 561:    */ 
/* 562:    */ 
/* 563:    */ 
/* 564:620 */     itemHandsawIron = new ItemHandsaw(Config.getItemID("items.base.handsawIron.id"), 0);
/* 565:    */     
/* 566:622 */     itemHandsawDiamond = new ItemHandsaw(Config.getItemID("items.base.handsawDiamond.id"), 2);
/* 567:    */     
/* 568:624 */     itemHandsawIron.b("handsawIron").c(17);
/* 569:625 */     itemHandsawIron.e(320);
/* 570:626 */     itemHandsawDiamond.b("handsawDiamond").c(18);
/* 571:627 */     itemHandsawDiamond.e(1280);
/* 572:    */     
/* 573:629 */     GameRegistry.addRecipe(new ur(itemHandsawIron, 1), new Object[] { "WWW", " II", " II", Character.valueOf('I'), up.o, Character.valueOf('W'), up.D });
/* 574:    */     
/* 575:    */ 
/* 576:    */ 
/* 577:    */ 
/* 578:634 */     GameRegistry.addRecipe(new ur(itemHandsawDiamond, 1), new Object[] { "WWW", " II", " DD", Character.valueOf('I'), up.o, Character.valueOf('D'), up.n, Character.valueOf('W'), up.D });
/* 579:    */     
/* 580:    */ 
/* 581:    */ 
/* 582:    */ 
/* 583:    */ 
/* 584:    */ 
/* 585:641 */     GameRegistry.addShapelessRecipe(CoreLib.copyStack(itemWaferSilicon, 16), new Object[] { itemBouleSilicon, new ur(itemHandsawDiamond, 1, -1) });
/* 586:    */     
/* 587:    */ 
/* 588:    */ 
/* 589:    */ 
/* 590:    */ 
/* 591:    */ 
/* 592:648 */     itemScrewdriver = new ItemScrewdriver(Config.getItemID("items.base.screwdriver.id"));
/* 593:    */     
/* 594:650 */     itemScrewdriver.b("screwdriver").c(16);
/* 595:    */     
/* 596:652 */     GameRegistry.addRecipe(new ur(itemScrewdriver, 1), new Object[] { "I ", " W", Character.valueOf('I'), up.o, Character.valueOf('W'), up.D });
/* 597:    */     
/* 598:    */ 
/* 599:    */ 
/* 600:    */ 
/* 601:    */ 
/* 602:    */ 
/* 603:659 */     GameRegistry.addRecipe(new ur(itemDrawplateDiamond, 1), new Object[] { " I ", "IDI", " I ", Character.valueOf('I'), new ur(blockMicro, 1, 5649), Character.valueOf('D'), new ur(blockMicro, 1, 4115) });
/* 604:    */     
/* 605:    */ 
/* 606:    */ 
/* 607:    */ 
/* 608:    */ 
/* 609:665 */     GameRegistry.addShapelessRecipe(itemFineIron, new Object[] { up.o, new ur(itemDrawplateDiamond, 1, -1) });
/* 610:    */     
/* 611:    */ 
/* 612:    */ 
/* 613:669 */     CraftLib.addShapelessOreRecipe(itemFineCopper, new Object[] { "ingotCopper", new ur(itemDrawplateDiamond, 1, -1) });
/* 614:    */     
/* 615:    */ 
/* 616:    */ 
/* 617:    */ 
/* 618:674 */     GameRegistry.addRecipe(CoreLib.copyStack(itemNuggetIron, 9), new Object[] { "I", Character.valueOf('I'), up.o });
/* 619:    */     
/* 620:676 */     CraftLib.addOreRecipe(CoreLib.copyStack(itemNuggetCopper, 9), new Object[] { "I", Character.valueOf('I'), "ingotCopper" });
/* 621:    */     
/* 622:678 */     CraftLib.addOreRecipe(CoreLib.copyStack(itemNuggetTin, 9), new Object[] { "I", Character.valueOf('I'), "ingotTin" });
/* 623:    */     
/* 624:680 */     CraftLib.addOreRecipe(CoreLib.copyStack(itemNuggetSilver, 9), new Object[] { "I", Character.valueOf('I'), "ingotSilver" });
/* 625:    */     
/* 626:    */ 
/* 627:683 */     GameRegistry.addRecipe(new ur(up.o, 1, 0), new Object[] { "III", "III", "III", Character.valueOf('I'), itemNuggetIron });
/* 628:    */     
/* 629:685 */     GameRegistry.addRecipe(itemIngotSilver, new Object[] { "III", "III", "III", Character.valueOf('I'), itemNuggetSilver });
/* 630:    */     
/* 631:687 */     GameRegistry.addRecipe(itemIngotTin, new Object[] { "III", "III", "III", Character.valueOf('I'), itemNuggetTin });
/* 632:    */     
/* 633:689 */     GameRegistry.addRecipe(itemIngotCopper, new Object[] { "III", "III", "III", Character.valueOf('I'), itemNuggetCopper });
/* 634:    */     
/* 635:    */ 
/* 636:    */ 
/* 637:693 */     GameRegistry.addRecipe(itemCanvas, new Object[] { "SSS", "SWS", "SSS", Character.valueOf('S'), up.K, Character.valueOf('W'), up.D });
/* 638:    */     
/* 639:    */ 
/* 640:    */ 
/* 641:    */ 
/* 642:    */ 
/* 643:    */ 
/* 644:700 */     GameRegistry.addRecipe(new ur(up.n, 2), new Object[] { "D", Character.valueOf('D'), new ur(blockMicro, 1, 4115) });
/* 645:    */     
/* 646:702 */     GameRegistry.addRecipe(new ur(up.n, 1), new Object[] { "D", Character.valueOf('D'), new ur(blockMicro, 1, 19) });
/* 647:    */     
/* 648:704 */     GameRegistry.addRecipe(new ur(up.o, 2), new Object[] { "I", Character.valueOf('I'), new ur(blockMicro, 1, 4113) });
/* 649:    */     
/* 650:706 */     GameRegistry.addRecipe(new ur(up.o, 1), new Object[] { "I", Character.valueOf('I'), new ur(blockMicro, 1, 17) });
/* 651:    */   }
/* 652:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.RedPowerBase
 * JD-Core Version:    0.7.0.1
 */