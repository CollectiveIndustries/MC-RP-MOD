/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import agi;
/*   4:    */ import akb;
/*   5:    */ import amq;
/*   6:    */ import any;
/*   7:    */ import aoe;
/*   8:    */ import com.eloraam.redpower.RedPowerCore;
/*   9:    */ import cpw.mods.fml.relauncher.Side;
/*  10:    */ import cpw.mods.fml.relauncher.SideOnly;
/*  11:    */ import java.lang.reflect.Constructor;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import java.util.Random;
/*  14:    */ import lq;
/*  15:    */ import md;
/*  16:    */ import qv;
/*  17:    */ import qx;
/*  18:    */ import up;
/*  19:    */ import ur;
/*  20:    */ import xe;
/*  21:    */ import yc;
/*  22:    */ import ym;
/*  23:    */ 
/*  24:    */ public class BlockExtended
/*  25:    */   extends akb
/*  26:    */ {
/*  27:    */   public BlockExtended(int i, agi m)
/*  28:    */   {
/*  29: 24 */     super(i, m);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public boolean c()
/*  33:    */   {
/*  34: 27 */     return false;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean b()
/*  38:    */   {
/*  39: 28 */     return false;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public boolean isACube()
/*  43:    */   {
/*  44: 29 */     return false;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public int b(int i)
/*  48:    */   {
/*  49: 32 */     return i;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public float getHardness()
/*  53:    */   {
/*  54: 36 */     return this.cn;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public ArrayList getBlockDropped(yc world, int i, int j, int k, int md, int fortune)
/*  58:    */   {
/*  59: 44 */     ArrayList ist = new ArrayList();
/*  60:    */     
/*  61: 46 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(world, i, j, k, TileExtended.class);
/*  62: 48 */     if (tl == null) {
/*  63: 48 */       return ist;
/*  64:    */     }
/*  65: 49 */     tl.addHarvestContents(ist);
/*  66: 50 */     return ist;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public int a(int i, Random random, int j)
/*  70:    */   {
/*  71: 55 */     return 0;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void a(yc world, qx player, int i, int j, int k, int l) {}
/*  75:    */   
/*  76:    */   public boolean removeBlockByPlayer(yc world, qx player, int i, int j, int k)
/*  77:    */   {
/*  78: 67 */     if (CoreLib.isClient(world)) {
/*  79: 67 */       return true;
/*  80:    */     }
/*  81: 69 */     int bid = world.a(i, j, k);
/*  82: 70 */     int md = world.h(i, j, k);
/*  83: 71 */     amq bl = amq.p[bid];
/*  84: 72 */     if (bl == null) {
/*  85: 72 */       return false;
/*  86:    */     }
/*  87: 73 */     if ((bl.canHarvestBlock(player, md)) && (!player.cd.d))
/*  88:    */     {
/*  89: 74 */       ArrayList il = getBlockDropped(world, i, j, k, md, xe.f(player));
/*  90: 76 */       for (ur it : il) {
/*  91: 77 */         CoreLib.dropItem(world, i, j, k, it);
/*  92:    */       }
/*  93:    */     }
/*  94: 80 */     world.e(i, j, k, 0);
/*  95: 81 */     return true;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void a(yc world, int i, int j, int k, int l)
/*  99:    */   {
/* 100: 88 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(world, i, j, k, TileExtended.class);
/* 101: 90 */     if (tl == null)
/* 102:    */     {
/* 103: 91 */       world.e(i, j, k, 0);
/* 104: 92 */       return;
/* 105:    */     }
/* 106: 94 */     tl.onBlockNeighborChange(l);
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void onBlockPlacedUseful(yc world, int i, int j, int k, int side, md ent, ur ist)
/* 110:    */   {
/* 111:119 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(world, i, j, k, TileExtended.class);
/* 112:121 */     if (tl == null) {
/* 113:121 */       return;
/* 114:    */     }
/* 115:122 */     tl.onBlockPlaced(ist, side, ent);
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void a(yc world, int i, int j, int k, int id, int md)
/* 119:    */   {
/* 120:128 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(world, i, j, k, TileExtended.class);
/* 121:130 */     if (tl == null) {
/* 122:130 */       return;
/* 123:    */     }
/* 124:131 */     tl.onBlockRemoval();
/* 125:132 */     super.a(world, i, j, k, id, md);
/* 126:    */   }
/* 127:    */   
/* 128:    */   public boolean c(ym iba, int i, int j, int k, int l)
/* 129:    */   {
/* 130:138 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(iba, i, j, k, TileExtended.class);
/* 131:140 */     if (tl == null) {
/* 132:140 */       return false;
/* 133:    */     }
/* 134:141 */     return tl.isBlockStrongPoweringTo(l);
/* 135:    */   }
/* 136:    */   
/* 137:    */   public boolean b(ym iba, int i, int j, int k, int l)
/* 138:    */   {
/* 139:147 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(iba, i, j, k, TileExtended.class);
/* 140:149 */     if (tl == null) {
/* 141:149 */       return false;
/* 142:    */     }
/* 143:150 */     return tl.isBlockWeakPoweringTo(l);
/* 144:    */   }
/* 145:    */   
/* 146:    */   public boolean a(yc world, int i, int j, int k, qx player, int side, float xp, float yp, float zp)
/* 147:    */   {
/* 148:157 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(world, i, j, k, TileExtended.class);
/* 149:159 */     if (tl == null) {
/* 150:159 */       return false;
/* 151:    */     }
/* 152:160 */     return tl.onBlockActivated(player);
/* 153:    */   }
/* 154:    */   
/* 155:    */   public void a(yc world, int i, int j, int k, lq entity)
/* 156:    */   {
/* 157:166 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(world, i, j, k, TileExtended.class);
/* 158:168 */     if (tl == null) {
/* 159:168 */       return;
/* 160:    */     }
/* 161:169 */     tl.onEntityCollidedWithBlock(entity);
/* 162:    */   }
/* 163:    */   
/* 164:    */   public aoe e(yc world, int i, int j, int k)
/* 165:    */   {
/* 166:175 */     TileExtended tl = (TileExtended)CoreLib.getTileEntity(world, i, j, k, TileExtended.class);
/* 167:177 */     if (tl != null)
/* 168:    */     {
/* 169:178 */       aoe bb = tl.getCollisionBoundingBox();
/* 170:179 */       if (bb != null) {
/* 171:179 */         return bb;
/* 172:    */       }
/* 173:    */     }
/* 174:181 */     return super.e(world, i, j, k);
/* 175:    */   }
/* 176:    */   
/* 177:    */   public int d()
/* 178:    */   {
/* 179:187 */     return RedPowerCore.customBlockModel;
/* 180:    */   }
/* 181:    */   
/* 182:    */   @SideOnly(Side.CLIENT)
/* 183:    */   public void a(yc world, int i, int j, int k, Random random)
/* 184:    */   {
/* 185:194 */     int md = world.h(i, j, k);
/* 186:195 */     RenderCustomBlock rend = RenderLib.getRenderer(this.cm, md);
/* 187:196 */     if (rend != null) {
/* 188:196 */       rend.randomDisplayTick(world, i, j, k, random);
/* 189:    */     }
/* 190:    */   }
/* 191:    */   
/* 192:    */   public any getBlockEntity()
/* 193:    */   {
/* 194:202 */     return null;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public void addTileEntityMapping(int md, Class cl)
/* 198:    */   {
/* 199:217 */     this.tileEntityMap[md] = cl;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public void setItemName(int md, String name)
/* 203:    */   {
/* 204:221 */     up item = up.e[this.cm];
/* 205:222 */     ((ItemExtended)item).setMetaName(md, "tile." + name);
/* 206:    */   }
/* 207:    */   
/* 208:    */   public any a(yc world)
/* 209:    */   {
/* 210:226 */     return null;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public any createTileEntity(yc world, int md)
/* 214:    */   {
/* 215:    */     try
/* 216:    */     {
/* 217:233 */       return (any)this.tileEntityMap[md].getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/* 218:    */     }
/* 219:    */     catch (Exception e) {}
/* 220:237 */     return null;
/* 221:    */   }
/* 222:    */   
/* 223:241 */   private Class[] tileEntityMap = new Class[16];
/* 224:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.BlockExtended
 * JD-Core Version:    0.7.0.1
 */