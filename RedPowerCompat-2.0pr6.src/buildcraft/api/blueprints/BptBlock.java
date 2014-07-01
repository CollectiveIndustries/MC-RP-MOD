/*   1:    */ package buildcraft.api.blueprints;
/*   2:    */ 
/*   3:    */ import akb;
/*   4:    */ import amq;
/*   5:    */ import any;
/*   6:    */ import bq;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import java.util.LinkedList;
/*   9:    */ import up;
/*  10:    */ import ur;
/*  11:    */ import yc;
/*  12:    */ 
/*  13:    */ public class BptBlock
/*  14:    */ {
/*  15:    */   public final int blockId;
/*  16:    */   
/*  17:    */   public BptBlock(int blockId)
/*  18:    */   {
/*  19: 49 */     this.blockId = blockId;
/*  20:    */     
/*  21: 51 */     BlueprintManager.blockBptProps[blockId] = this;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void addRequirements(BptSlotInfo slot, IBptContext context, LinkedList requirements)
/*  25:    */   {
/*  26: 59 */     if (slot.blockId != 0) {
/*  27: 60 */       if (slot.storedRequirements.size() != 0) {
/*  28: 61 */         requirements.addAll(slot.storedRequirements);
/*  29:    */       } else {
/*  30: 63 */         requirements.add(new ur(slot.blockId, 1, slot.meta));
/*  31:    */       }
/*  32:    */     }
/*  33:    */   }
/*  34:    */   
/*  35:    */   public ur useItem(BptSlotInfo slot, IBptContext context, ur req, ur stack)
/*  36:    */   {
/*  37: 80 */     ur result = stack.l();
/*  38: 81 */     if (stack.f())
/*  39:    */     {
/*  40: 82 */       if (req.j() + stack.j() <= stack.k())
/*  41:    */       {
/*  42: 83 */         stack.b(req.j() + stack.j());
/*  43: 84 */         result.b(req.j());
/*  44: 85 */         req.a = 0;
/*  45:    */       }
/*  46: 88 */       if (stack.j() >= stack.k()) {
/*  47: 89 */         stack.a = 0;
/*  48:    */       }
/*  49:    */     }
/*  50: 92 */     else if (stack.a >= req.a)
/*  51:    */     {
/*  52: 93 */       result.a = req.a;
/*  53: 94 */       stack.a -= req.a;
/*  54: 95 */       req.a = 0;
/*  55:    */     }
/*  56:    */     else
/*  57:    */     {
/*  58: 97 */       req.a -= stack.a;
/*  59: 98 */       stack.a = 0;
/*  60:    */     }
/*  61:102 */     if ((stack.a == 0) && (stack.b().r() != null))
/*  62:    */     {
/*  63:103 */       up container = stack.b().r();
/*  64:    */       
/*  65:105 */       stack.c = container.cj;
/*  66:106 */       stack.a = 1;
/*  67:107 */       stack.b(0);
/*  68:    */     }
/*  69:109 */     return result;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean isValid(BptSlotInfo slot, IBptContext context)
/*  73:    */   {
/*  74:119 */     return (slot.blockId == context.world().a(slot.x, slot.y, slot.z)) && (slot.meta == context.world().h(slot.x, slot.y, slot.z));
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void rotateLeft(BptSlotInfo slot, IBptContext context) {}
/*  78:    */   
/*  79:    */   public void buildBlock(BptSlotInfo slot, IBptContext context)
/*  80:    */   {
/*  81:134 */     context.world().d(slot.x, slot.y, slot.z, slot.blockId, slot.meta);
/*  82:135 */     context.world().c(slot.x, slot.y, slot.z, slot.meta);
/*  83:137 */     if ((amq.p[slot.blockId] instanceof akb))
/*  84:    */     {
/*  85:138 */       any tile = context.world().q(slot.x, slot.y, slot.z);
/*  86:    */       
/*  87:140 */       slot.cpt.a("x", slot.x);
/*  88:141 */       slot.cpt.a("y", slot.y);
/*  89:142 */       slot.cpt.a("z", slot.z);
/*  90:144 */       if (tile != null) {
/*  91:145 */         tile.a(slot.cpt);
/*  92:    */       }
/*  93:    */     }
/*  94:    */   }
/*  95:    */   
/*  96:    */   public boolean ignoreBuilding(BptSlotInfo slot)
/*  97:    */   {
/*  98:154 */     return false;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void initializeFromWorld(BptSlotInfo slot, IBptContext context, int x, int y, int z)
/* 102:    */   {
/* 103:164 */     if ((amq.p[slot.blockId] instanceof akb))
/* 104:    */     {
/* 105:165 */       any tile = context.world().q(x, y, z);
/* 106:167 */       if (tile != null) {
/* 107:168 */         tile.b(slot.cpt);
/* 108:    */       }
/* 109:    */     }
/* 110:172 */     if (amq.p[slot.blockId] != null)
/* 111:    */     {
/* 112:173 */       ArrayList req = amq.p[slot.blockId].getBlockDropped(context.world(), x, y, z, context.world().h(x, y, z), 0);
/* 113:175 */       if (req != null) {
/* 114:176 */         slot.storedRequirements.addAll(req);
/* 115:    */       }
/* 116:    */     }
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void postProcessing(BptSlotInfo slot, IBptContext context) {}
/* 120:    */   
/* 121:    */   public BlockSignature getSignature(amq block)
/* 122:    */   {
/* 123:194 */     BlockSignature sig = new BlockSignature();
/* 124:196 */     if (block.cm > 122)
/* 125:    */     {
/* 126:197 */       sig.blockClassName = block.getClass().getSimpleName();
/* 127:199 */       if ((block instanceof akb))
/* 128:    */       {
/* 129:201 */         any tile = ((akb)block).a(null);
/* 130:203 */         if (tile != null) {
/* 131:204 */           sig.tileClassName = tile.getClass().getSimpleName();
/* 132:    */         }
/* 133:    */       }
/* 134:    */     }
/* 135:209 */     sig.blockName = block.a();
/* 136:210 */     sig.replaceNullWithStar();
/* 137:    */     
/* 138:212 */     return sig;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public boolean match(amq block, BlockSignature sig)
/* 142:    */   {
/* 143:221 */     if (block == null) {
/* 144:222 */       return false;
/* 145:    */     }
/* 146:224 */     BlockSignature inst = BlueprintManager.getBlockSignature(block);
/* 147:    */     
/* 148:226 */     return (starMatch(sig.blockName, inst.blockName)) && (starMatch(sig.blockClassName, inst.blockClassName)) && (starMatch(sig.tileClassName, inst.tileClassName)) && (starMatch(sig.customField, inst.customField)) && (starMatch(sig.mod, inst.mod));
/* 149:    */   }
/* 150:    */   
/* 151:    */   private boolean starMatch(String s1, String s2)
/* 152:    */   {
/* 153:231 */     return (s1.equals("*")) || (s2.equals("*")) || (s1.equals(s2));
/* 154:    */   }
/* 155:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCompat-2.0pr6.zip
 * Qualified Name:     buildcraft.api.blueprints.BptBlock
 * JD-Core Version:    0.7.0.1
 */