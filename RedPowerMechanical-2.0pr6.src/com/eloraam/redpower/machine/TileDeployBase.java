/*   1:    */ package com.eloraam.redpower.machine;
/*   2:    */ 
/*   3:    */ import aoe;
/*   4:    */ import aoh;
/*   5:    */ import aoj;
/*   6:    */ import com.eloraam.redpower.RedPowerMachine;
/*   7:    */ import com.eloraam.redpower.core.IConnectable;
/*   8:    */ import com.eloraam.redpower.core.IFrameLink;
/*   9:    */ import com.eloraam.redpower.core.RedPowerLib;
/*  10:    */ import com.eloraam.redpower.core.WorldCoord;
/*  11:    */ import java.util.List;
/*  12:    */ import lq;
/*  13:    */ import md;
/*  14:    */ import qw;
/*  15:    */ import qx;
/*  16:    */ import up;
/*  17:    */ import ur;
/*  18:    */ import yc;
/*  19:    */ 
/*  20:    */ public abstract class TileDeployBase
/*  21:    */   extends TileMachine
/*  22:    */   implements IFrameLink, IConnectable
/*  23:    */ {
/*  24:    */   public boolean isFrameMoving()
/*  25:    */   {
/*  26: 34 */     return false;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public boolean canFrameConnectIn(int dir)
/*  30:    */   {
/*  31: 38 */     if (dir == (this.Rotation ^ 0x1)) {
/*  32: 38 */       return false;
/*  33:    */     }
/*  34: 39 */     return true;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public boolean canFrameConnectOut(int dir)
/*  38:    */   {
/*  39: 43 */     return false;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public WorldCoord getFrameLinkset()
/*  43:    */   {
/*  44: 46 */     return null;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public int getConnectableMask()
/*  48:    */   {
/*  49: 51 */     return 0x3FFFFFFF ^ RedPowerLib.getConDirMask(this.Rotation ^ 0x1);
/*  50:    */   }
/*  51:    */   
/*  52:    */   public int getConnectClass(int side)
/*  53:    */   {
/*  54: 55 */     return 0;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public int getCornerPowerMode()
/*  58:    */   {
/*  59: 59 */     return 0;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public int getBlockID()
/*  63:    */   {
/*  64: 65 */     return RedPowerMachine.blockMachine.cm;
/*  65:    */   }
/*  66:    */   
/*  67:    */   protected void initPlayer()
/*  68:    */   {
/*  69: 69 */     if (fakePlayer == null) {
/*  70: 70 */       fakePlayer = new EntityPlayerFake(this.k);
/*  71:    */     }
/*  72: 73 */     double x = this.l + 0.5D;double y = this.m - 1.1D;double z = this.n + 0.5D;
/*  73:    */     float pitch;
/*  74:    */     float yaw;
/*  75: 74 */     switch (this.Rotation)
/*  76:    */     {
/*  77:    */     case 0: 
/*  78: 75 */       pitch = -90.0F;yaw = 0.0F;y += 0.51D; break;
/*  79:    */     case 1: 
/*  80: 76 */       pitch = 90.0F;yaw = 0.0F;y -= 0.51D; break;
/*  81:    */     case 2: 
/*  82: 77 */       pitch = 0.0F;yaw = 0.0F;z += 0.51D; break;
/*  83:    */     case 3: 
/*  84: 78 */       pitch = 0.0F;yaw = 180.0F;z -= 0.51D; break;
/*  85:    */     case 4: 
/*  86: 79 */       pitch = 0.0F;yaw = 270.0F;x += 0.51D; break;
/*  87:    */     default: 
/*  88: 80 */       pitch = 0.0F;yaw = 90.0F;x -= 0.51D;
/*  89:    */     }
/*  90: 82 */     fakePlayer.p = this.k;
/*  91: 83 */     fakePlayer.b(x, y, z, yaw, pitch);
/*  92:    */   }
/*  93:    */   
/*  94:    */   protected static lq traceEntities(yc world, lq exclude, aoj vs, aoj vlook)
/*  95:    */   {
/*  96: 89 */     aoe aabb = aoe.a(vs.c, vs.d, vs.e, vs.c, vs.d, vs.e);
/*  97:    */     
/*  98:    */ 
/*  99: 92 */     List elist = world.b(exclude, aabb.a(vlook.c, vlook.d, vlook.e).b(1.0D, 1.0D, 1.0D));
/* 100:    */     
/* 101:    */ 
/* 102: 95 */     aoj v2 = vs.c(vlook.c, vlook.d, vlook.e);
/* 103:    */     
/* 104: 97 */     lq entHit = null;
/* 105: 98 */     double edis = 0.0D;
/* 106:100 */     for (int i = 0; i < elist.size(); i++)
/* 107:    */     {
/* 108:101 */       lq ent = (lq)elist.get(i);
/* 109:102 */       if (ent.L())
/* 110:    */       {
/* 111:104 */         float cbs = ent.Y();
/* 112:105 */         aoe ab2 = ent.D.b(cbs, cbs, cbs);
/* 113:107 */         if (ab2.a(vs))
/* 114:    */         {
/* 115:108 */           entHit = ent;edis = 0.0D;
/* 116:109 */           break;
/* 117:    */         }
/* 118:112 */         aoh mop = ab2.a(vs, v2);
/* 119:113 */         if (mop != null)
/* 120:    */         {
/* 121:115 */           double d = vs.d(mop.f);
/* 122:116 */           if ((d < edis) || (edis == 0.0D))
/* 123:    */           {
/* 124:117 */             entHit = ent;edis = d;
/* 125:    */           }
/* 126:    */         }
/* 127:    */       }
/* 128:    */     }
/* 129:120 */     return entHit;
/* 130:    */   }
/* 131:    */   
/* 132:    */   protected boolean useOnEntity(lq ent)
/* 133:    */   {
/* 134:124 */     if (ent.a(fakePlayer)) {
/* 135:125 */       return true;
/* 136:    */     }
/* 137:127 */     ur ist = fakePlayer.bS();
/* 138:128 */     if ((ist != null) && ((ent instanceof md)))
/* 139:    */     {
/* 140:130 */       int iss = ist.a;
/* 141:131 */       ist.a((md)ent);
/* 142:132 */       if (ist.a != iss) {
/* 143:133 */         return true;
/* 144:    */       }
/* 145:    */     }
/* 146:135 */     return false;
/* 147:    */   }
/* 148:    */   
/* 149:    */   protected boolean tryUseItemStack(ur ist, int x, int y, int z, int slot)
/* 150:    */   {
/* 151:140 */     fakePlayer.bJ.c = slot;
/* 152:141 */     if ((ist.c == up.aW.cj) || (ist.c == up.az.cj) || (ist.c == up.aO.cj) || (ist.c == up.aN.cj))
/* 153:    */     {
/* 154:145 */       if (ist.b().a(ist, fakePlayer, this.k, x, y, z, 1, 0.5F, 0.5F, 0.5F)) {
/* 155:147 */         return true;
/* 156:    */       }
/* 157:    */     }
/* 158:    */     else
/* 159:    */     {
/* 160:149 */       if (ist.b().onItemUseFirst(ist, fakePlayer, this.k, x, y, z, 1, 0.5F, 0.5F, 0.5F)) {
/* 161:151 */         return true;
/* 162:    */       }
/* 163:152 */       if (ist.b().a(ist, fakePlayer, this.k, x, y - 1, z, 1, 0.5F, 0.5F, 0.5F)) {
/* 164:155 */         return true;
/* 165:    */       }
/* 166:    */     }
/* 167:158 */     int iss = ist.a;
/* 168:159 */     ur ost = ist.a(this.k, fakePlayer);
/* 169:160 */     if ((ost != ist) || ((ost != null) && (ost.a != iss)))
/* 170:    */     {
/* 171:161 */       ist = ost;
/* 172:162 */       fakePlayer.bJ.a(slot, ost);
/* 173:    */       
/* 174:164 */       return true;
/* 175:    */     }
/* 176:167 */     aoj lv = fakePlayer.i(1.0F);
/* 177:168 */     lv.c *= 2.5D;lv.d *= 2.5D;
/* 178:169 */     lv.e *= 2.5D;
/* 179:170 */     aoj sv = aoj.a(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D);
/* 180:    */     
/* 181:172 */     lq ent = traceEntities(this.k, fakePlayer, sv, lv);
/* 182:173 */     if ((ent != null) && 
/* 183:174 */       (useOnEntity(ent))) {
/* 184:175 */       return true;
/* 185:    */     }
/* 186:177 */     return false;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public abstract void enableTowards(WorldCoord paramWorldCoord);
/* 190:    */   
/* 191:    */   public void onBlockNeighborChange(int l)
/* 192:    */   {
/* 193:183 */     int cm = getConnectableMask();
/* 194:184 */     if (!RedPowerLib.isPowered(this.k, this.l, this.m, this.n, cm, cm >> 24))
/* 195:    */     {
/* 196:186 */       if (!this.Active) {
/* 197:186 */         return;
/* 198:    */       }
/* 199:187 */       scheduleTick(5);
/* 200:188 */       return;
/* 201:    */     }
/* 202:190 */     if (this.Active) {
/* 203:190 */       return;
/* 204:    */     }
/* 205:191 */     this.Active = true;
/* 206:192 */     updateBlock();
/* 207:    */     
/* 208:194 */     WorldCoord wc = new WorldCoord(this);
/* 209:195 */     wc.step(this.Rotation ^ 0x1);
/* 210:    */     
/* 211:197 */     enableTowards(wc);
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void onTileTick()
/* 215:    */   {
/* 216:201 */     this.Active = false;
/* 217:202 */     updateBlock();
/* 218:    */   }
/* 219:    */   
/* 220:205 */   protected static qx fakePlayer = null;
/* 221:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerMechanical-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.machine.TileDeployBase
 * JD-Core Version:    0.7.0.1
 */