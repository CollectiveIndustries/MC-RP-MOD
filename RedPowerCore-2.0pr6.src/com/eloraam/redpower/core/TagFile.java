/*   1:    */ package com.eloraam.redpower.core;
/*   2:    */ 
/*   3:    */ import java.io.BufferedReader;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.FileInputStream;
/*   6:    */ import java.io.FileOutputStream;
/*   7:    */ import java.io.IOException;
/*   8:    */ import java.io.InputStream;
/*   9:    */ import java.io.InputStreamReader;
/*  10:    */ import java.io.PrintStream;
/*  11:    */ import java.io.StreamTokenizer;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import java.util.Iterator;
/*  14:    */ import java.util.Set;
/*  15:    */ import java.util.TreeMap;
/*  16:    */ 
/*  17:    */ public class TagFile
/*  18:    */ {
/*  19: 10 */   TreeMap contents = new TreeMap();
/*  20: 11 */   TreeMap comments = new TreeMap();
/*  21: 12 */   String filecomment = "";
/*  22:    */   
/*  23:    */   public void addTag(String name, Object tag)
/*  24:    */   {
/*  25: 15 */     int idx = 0;
/*  26:    */     
/*  27: 17 */     TreeMap sub = this.contents;
/*  28:    */     for (;;)
/*  29:    */     {
/*  30: 21 */       int nid = name.indexOf('.', idx);
/*  31: 22 */       if (nid < 0)
/*  32:    */       {
/*  33: 23 */         String p = name.substring(idx);
/*  34: 24 */         if (p.equals("")) {
/*  35: 25 */           throw new IllegalArgumentException("Empty key name");
/*  36:    */         }
/*  37: 28 */         sub.put(p, tag);
/*  38: 29 */         return;
/*  39:    */       }
/*  40: 31 */       String p = name.substring(idx, nid);
/*  41: 32 */       idx = nid + 1;
/*  42: 33 */       if (p.equals("")) {
/*  43: 34 */         throw new IllegalArgumentException("Empty key name");
/*  44:    */       }
/*  45: 37 */       Object ob = sub.get(p);
/*  46: 38 */       if (ob == null)
/*  47:    */       {
/*  48: 39 */         TreeMap tmp = new TreeMap();
/*  49: 40 */         sub.put(p, tmp);
/*  50: 41 */         sub = tmp;
/*  51:    */       }
/*  52:    */       else
/*  53:    */       {
/*  54: 44 */         if (!(ob instanceof TreeMap)) {
/*  55: 45 */           throw new IllegalArgumentException("Key not a dictionary");
/*  56:    */         }
/*  57: 48 */         sub = (TreeMap)ob;
/*  58:    */       }
/*  59:    */     }
/*  60:    */   }
/*  61:    */   
/*  62:    */   public Object getTag(String name)
/*  63:    */   {
/*  64: 53 */     int idx = 0;
/*  65:    */     
/*  66: 55 */     TreeMap sub = this.contents;
/*  67:    */     for (;;)
/*  68:    */     {
/*  69: 58 */       int nid = name.indexOf('.', idx);
/*  70: 59 */       if (nid < 0)
/*  71:    */       {
/*  72: 60 */         String p = name.substring(idx);
/*  73: 61 */         return sub.get(p);
/*  74:    */       }
/*  75: 63 */       String p = name.substring(idx, nid);
/*  76: 64 */       idx = nid + 1;
/*  77:    */       
/*  78: 66 */       Object ob = sub.get(p);
/*  79: 67 */       if (!(ob instanceof TreeMap)) {
/*  80: 68 */         return null;
/*  81:    */       }
/*  82: 69 */       sub = (TreeMap)ob;
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   public Object removeTag(String name)
/*  87:    */   {
/*  88: 74 */     int idx = 0;
/*  89:    */     
/*  90: 76 */     TreeMap sub = this.contents;
/*  91:    */     for (;;)
/*  92:    */     {
/*  93: 79 */       int nid = name.indexOf('.', idx);
/*  94: 80 */       if (nid < 0)
/*  95:    */       {
/*  96: 81 */         String p = name.substring(idx);
/*  97: 82 */         return sub.remove(p);
/*  98:    */       }
/*  99: 84 */       String p = name.substring(idx, nid);
/* 100: 85 */       idx = nid + 1;
/* 101:    */       
/* 102: 87 */       Object ob = sub.get(p);
/* 103: 88 */       if (!(ob instanceof TreeMap)) {
/* 104: 89 */         return null;
/* 105:    */       }
/* 106: 90 */       sub = (TreeMap)ob;
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void commentTag(String k, String v)
/* 111:    */   {
/* 112: 95 */     this.comments.put(k, v);
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void commentFile(String cmt)
/* 116:    */   {
/* 117: 99 */     this.filecomment = cmt;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void addString(String name, String value)
/* 121:    */   {
/* 122:103 */     addTag(name, value);
/* 123:    */   }
/* 124:    */   
/* 125:    */   public void addInt(String name, int value)
/* 126:    */   {
/* 127:107 */     addTag(name, Integer.valueOf(value));
/* 128:    */   }
/* 129:    */   
/* 130:    */   public String getString(String name)
/* 131:    */   {
/* 132:111 */     Object ob = getTag(name);
/* 133:112 */     if (!(ob instanceof String)) {
/* 134:112 */       return null;
/* 135:    */     }
/* 136:113 */     return (String)ob;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public String getString(String name, String def)
/* 140:    */   {
/* 141:117 */     Object ob = getTag(name);
/* 142:118 */     if (!(ob instanceof String)) {
/* 143:118 */       return def;
/* 144:    */     }
/* 145:119 */     return (String)ob;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public int getInt(String name)
/* 149:    */   {
/* 150:123 */     Object ob = getTag(name);
/* 151:124 */     if (!(ob instanceof Integer)) {
/* 152:124 */       return 0;
/* 153:    */     }
/* 154:125 */     return ((Integer)ob).intValue();
/* 155:    */   }
/* 156:    */   
/* 157:    */   public int getInt(String name, int def)
/* 158:    */   {
/* 159:129 */     Object ob = getTag(name);
/* 160:130 */     if (!(ob instanceof Integer)) {
/* 161:130 */       return def;
/* 162:    */     }
/* 163:131 */     return ((Integer)ob).intValue();
/* 164:    */   }
/* 165:    */   
/* 166:    */   private void writecomment(PrintStream ps, String indent, String cmt)
/* 167:    */   {
/* 168:135 */     if (cmt == null) {
/* 169:135 */       return;
/* 170:    */     }
/* 171:136 */     for (String s : cmt.split("\n")) {
/* 172:137 */       ps.printf("%s# %s\n", new Object[] { indent, s });
/* 173:    */     }
/* 174:    */   }
/* 175:    */   
/* 176:    */   private String collapsedtag(TreeMap tag, String key, String ft)
/* 177:    */   {
/* 178:144 */     String cn = key;
/* 179:145 */     Object ob = tag.get(key);
/* 180:    */     for (;;)
/* 181:    */     {
/* 182:148 */       if (this.comments.get(ft) != null) {
/* 183:148 */         return null;
/* 184:    */       }
/* 185:149 */       if ((ob instanceof String)) {
/* 186:150 */         return cn + "=\"" + ((String)ob).replace("\"", "\\\"") + "\"";
/* 187:    */       }
/* 188:152 */       if ((ob instanceof Integer)) {
/* 189:153 */         return cn + "=" + (Integer)ob;
/* 190:    */       }
/* 191:155 */       tag = (TreeMap)ob;
/* 192:156 */       if (tag.size() != 1) {
/* 193:156 */         return null;
/* 194:    */       }
/* 195:157 */       String k = (String)tag.firstKey();
/* 196:158 */       cn = cn + "." + k;
/* 197:159 */       ob = tag.get(k);
/* 198:160 */       ft = ft + "." + k;
/* 199:    */     }
/* 200:    */   }
/* 201:    */   
/* 202:    */   private void savetag(PrintStream ps, TreeMap tag, String name, String indent)
/* 203:    */     throws IOException
/* 204:    */   {
/* 205:167 */     for (String k : tag.keySet())
/* 206:    */     {
/* 207:168 */       String ft = name != null ? name + "." + k : k;
/* 208:169 */       writecomment(ps, indent, (String)this.comments.get(ft));
/* 209:    */       
/* 210:171 */       Object ob = tag.get(k);
/* 211:172 */       if ((ob instanceof String))
/* 212:    */       {
/* 213:173 */         ps.printf("%s%s=\"%s\"\n", new Object[] { indent, k, ((String)ob).replace("\"", "\\\"") });
/* 214:    */       }
/* 215:175 */       else if ((ob instanceof Integer))
/* 216:    */       {
/* 217:176 */         ps.printf("%s%s=%d\n", new Object[] { indent, k, (Integer)ob });
/* 218:    */       }
/* 219:177 */       else if ((ob instanceof TreeMap))
/* 220:    */       {
/* 221:178 */         String ct = collapsedtag(tag, k, ft);
/* 222:179 */         if (ct != null)
/* 223:    */         {
/* 224:180 */           ps.printf("%s%s\n", new Object[] { indent, ct });
/* 225:    */         }
/* 226:    */         else
/* 227:    */         {
/* 228:183 */           ps.printf("%s%s {\n", new Object[] { indent, k });
/* 229:184 */           savetag(ps, (TreeMap)ob, ft, indent + "    ");
/* 230:    */           
/* 231:186 */           ps.printf("%s}\n\n", new Object[] { indent });
/* 232:    */         }
/* 233:    */       }
/* 234:    */     }
/* 235:    */   }
/* 236:    */   
/* 237:    */   public void saveFile(File file)
/* 238:    */   {
/* 239:    */     try
/* 240:    */     {
/* 241:193 */       FileOutputStream os = new FileOutputStream(file);
/* 242:194 */       PrintStream ps = new PrintStream(os);
/* 243:    */       
/* 244:196 */       writecomment(ps, "", this.filecomment);
/* 245:197 */       savetag(ps, this.contents, null, "");
/* 246:    */       
/* 247:199 */       ps.close();
/* 248:    */     }
/* 249:    */     catch (IOException e)
/* 250:    */     {
/* 251:201 */       e.printStackTrace();
/* 252:    */     }
/* 253:    */   }
/* 254:    */   
/* 255:    */   private static void readtag(TreeMap tag, StreamTokenizer tok)
/* 256:    */     throws IOException
/* 257:    */   {
/* 258:207 */     while ((tok.nextToken() != -1) && 
/* 259:208 */       (tok.ttype != 125)) {
/* 260:209 */       if (tok.ttype != 10)
/* 261:    */       {
/* 262:210 */         if (tok.ttype != -3) {
/* 263:211 */           throw new IllegalArgumentException("Parse error");
/* 264:    */         }
/* 265:214 */         String key = tok.sval;
/* 266:    */         
/* 267:216 */         TreeMap ltag = tag;
/* 268:    */         Object obtag;
/* 269:    */         for (;;)
/* 270:    */         {
/* 271:219 */           switch (tok.nextToken())
/* 272:    */           {
/* 273:    */           case 46: 
/* 274:221 */             obtag = ltag.get(key);
/* 275:222 */             if (!(obtag instanceof TreeMap))
/* 276:    */             {
/* 277:223 */               TreeMap ttag = new TreeMap();
/* 278:224 */               ltag.put(key, ttag);
/* 279:225 */               ltag = ttag;
/* 280:    */             }
/* 281:    */             else
/* 282:    */             {
/* 283:227 */               ltag = (TreeMap)obtag;
/* 284:    */             }
/* 285:229 */             tok.nextToken();
/* 286:230 */             if (tok.ttype != -3) {
/* 287:231 */               throw new IllegalArgumentException("Parse error");
/* 288:    */             }
/* 289:234 */             key = tok.sval;
/* 290:    */           }
/* 291:    */         }
/* 292:237 */         tok.nextToken();
/* 293:238 */         if (tok.ttype == -2) {
/* 294:239 */           ltag.put(key, Integer.valueOf((int)tok.nval));
/* 295:241 */         } else if (tok.ttype == 34) {
/* 296:242 */           ltag.put(key, tok.sval);
/* 297:    */         } else {
/* 298:244 */           throw new IllegalArgumentException("Parse error");
/* 299:    */         }
/* 300:247 */         tok.nextToken();
/* 301:248 */         if (tok.ttype != 10)
/* 302:    */         {
/* 303:249 */           throw new IllegalArgumentException("Parse error");
/* 304:    */           
/* 305:    */ 
/* 306:    */ 
/* 307:253 */           obtag = ltag.get(key);
/* 308:254 */           if (!(obtag instanceof TreeMap))
/* 309:    */           {
/* 310:255 */             TreeMap ttag = new TreeMap();
/* 311:256 */             ltag.put(key, ttag);
/* 312:257 */             ltag = ttag;
/* 313:    */           }
/* 314:    */           else
/* 315:    */           {
/* 316:259 */             ltag = (TreeMap)obtag;
/* 317:    */           }
/* 318:261 */           readtag(ltag, tok);
/* 319:262 */           tok.nextToken();
/* 320:263 */           if (tok.ttype != 10)
/* 321:    */           {
/* 322:264 */             throw new IllegalArgumentException("Parse error");
/* 323:    */             
/* 324:    */ 
/* 325:    */ 
/* 326:268 */             throw new IllegalArgumentException("Parse error");
/* 327:    */           }
/* 328:    */         }
/* 329:    */       }
/* 330:    */     }
/* 331:    */   }
/* 332:    */   
/* 333:    */   public static TagFile loadFile(File file)
/* 334:    */   {
/* 335:275 */     TagFile tr = new TagFile();
/* 336:    */     try
/* 337:    */     {
/* 338:277 */       FileInputStream fis = new FileInputStream(file);
/* 339:278 */       tr.readStream(fis);
/* 340:    */     }
/* 341:    */     catch (IOException e)
/* 342:    */     {
/* 343:280 */       e.printStackTrace();
/* 344:    */     }
/* 345:282 */     return tr;
/* 346:    */   }
/* 347:    */   
/* 348:    */   public void readFile(File file)
/* 349:    */   {
/* 350:    */     try
/* 351:    */     {
/* 352:287 */       FileInputStream fis = new FileInputStream(file);
/* 353:288 */       readStream(fis);
/* 354:    */     }
/* 355:    */     catch (IOException e)
/* 356:    */     {
/* 357:290 */       e.printStackTrace();
/* 358:    */     }
/* 359:    */   }
/* 360:    */   
/* 361:    */   public void readStream(InputStream fis)
/* 362:    */   {
/* 363:    */     try
/* 364:    */     {
/* 365:296 */       BufferedReader r = new BufferedReader(new InputStreamReader(fis));
/* 366:    */       
/* 367:298 */       StreamTokenizer tok = new StreamTokenizer(r);
/* 368:299 */       tok.commentChar(35);
/* 369:300 */       tok.eolIsSignificant(true);
/* 370:301 */       tok.lowerCaseMode(false);
/* 371:302 */       tok.parseNumbers();
/* 372:303 */       tok.quoteChar(34);
/* 373:304 */       tok.ordinaryChar(61);
/* 374:305 */       tok.ordinaryChar(123);
/* 375:306 */       tok.ordinaryChar(125);
/* 376:307 */       tok.ordinaryChar(46);
/* 377:    */       
/* 378:309 */       readtag(this.contents, tok);
/* 379:    */       
/* 380:311 */       fis.close();
/* 381:    */     }
/* 382:    */     catch (IOException e)
/* 383:    */     {
/* 384:313 */       e.printStackTrace();
/* 385:    */     }
/* 386:    */   }
/* 387:    */   
/* 388:    */   public class Query
/* 389:    */     implements Iterable
/* 390:    */   {
/* 391:    */     String[] pattern;
/* 392:    */     
/* 393:    */     public class QueryIterator
/* 394:    */       implements Iterator
/* 395:    */     {
/* 396:    */       ArrayList path;
/* 397:    */       String lastentry;
/* 398:    */       
/* 399:    */       private QueryIterator()
/* 400:    */       {
/* 401:331 */         this.path = new ArrayList();
/* 402:332 */         TreeMap p = TagFile.this.contents;
/* 403:333 */         String path = null;
/* 404:335 */         if (!step0(0, TagFile.this.contents, "")) {
/* 405:335 */           step();
/* 406:    */         }
/* 407:    */       }
/* 408:    */       
/* 409:    */       private void step()
/* 410:    */       {
/* 411:339 */         while (this.path != null) {
/* 412:340 */           if (step1()) {}
/* 413:    */         }
/* 414:    */       }
/* 415:    */       
/* 416:    */       private boolean step1()
/* 417:    */       {
/* 418:345 */         TagFile.QueryEntry qe = (TagFile.QueryEntry)this.path.get(this.path.size() - 1);
/* 419:346 */         if (!qe.iter.hasNext())
/* 420:    */         {
/* 421:347 */           this.path.remove(this.path.size() - 1);
/* 422:348 */           if (this.path.size() == 0) {
/* 423:348 */             this.path = null;
/* 424:    */           }
/* 425:349 */           return false;
/* 426:    */         }
/* 427:351 */         String str = (String)qe.iter.next();
/* 428:352 */         String sp = qe.path + "." + str;
/* 429:354 */         if (qe.lvl == TagFile.Query.this.pattern.length - 1)
/* 430:    */         {
/* 431:355 */           this.lastentry = sp;
/* 432:356 */           return true;
/* 433:    */         }
/* 434:358 */         Object ob = qe.tag.get(str);
/* 435:359 */         if (!(ob instanceof TreeMap)) {
/* 436:360 */           return false;
/* 437:    */         }
/* 438:361 */         return step0(qe.lvl + 1, (TreeMap)ob, sp);
/* 439:    */       }
/* 440:    */       
/* 441:    */       private boolean step0(int lvl0, TreeMap p, String sp)
/* 442:    */       {
/* 443:368 */         for (int lvl = lvl0; lvl < TagFile.Query.this.pattern.length; lvl++)
/* 444:    */         {
/* 445:369 */           if (TagFile.Query.this.pattern[lvl].equals("%"))
/* 446:    */           {
/* 447:370 */             TagFile.QueryEntry qe = new TagFile.QueryEntry(null);
/* 448:371 */             qe.path = sp;
/* 449:372 */             qe.tag = p;
/* 450:373 */             qe.lvl = lvl;
/* 451:374 */             qe.iter = p.keySet().iterator();
/* 452:375 */             this.path.add(qe);
/* 453:376 */             return false;
/* 454:    */           }
/* 455:378 */           Object ob = p.get(TagFile.Query.this.pattern[lvl]);
/* 456:380 */           if (sp.equals("")) {
/* 457:381 */             sp = TagFile.Query.this.pattern[lvl];
/* 458:    */           } else {
/* 459:382 */             sp = sp + "." + TagFile.Query.this.pattern[lvl];
/* 460:    */           }
/* 461:383 */           if (!(ob instanceof TreeMap))
/* 462:    */           {
/* 463:384 */             if (lvl != TagFile.Query.this.pattern.length - 1) {
/* 464:    */               break;
/* 465:    */             }
/* 466:385 */             this.lastentry = sp;
/* 467:386 */             return true;
/* 468:    */           }
/* 469:390 */           p = (TreeMap)ob;
/* 470:    */         }
/* 471:393 */         this.path.remove(this.path.size() - 1);
/* 472:394 */         if (this.path.size() == 0) {
/* 473:394 */           this.path = null;
/* 474:    */         }
/* 475:395 */         return false;
/* 476:    */       }
/* 477:    */       
/* 478:    */       public boolean hasNext()
/* 479:    */       {
/* 480:399 */         return this.path != null;
/* 481:    */       }
/* 482:    */       
/* 483:    */       public String next()
/* 484:    */       {
/* 485:403 */         String tr = this.lastentry;
/* 486:404 */         step();
/* 487:405 */         return tr;
/* 488:    */       }
/* 489:    */       
/* 490:    */       public void remove() {}
/* 491:    */     }
/* 492:    */     
/* 493:    */     private Query(String pat)
/* 494:    */     {
/* 495:412 */       this.pattern = pat.split("\\.");
/* 496:    */     }
/* 497:    */     
/* 498:    */     public Iterator iterator()
/* 499:    */     {
/* 500:416 */       return new QueryIterator(null);
/* 501:    */     }
/* 502:    */   }
/* 503:    */   
/* 504:    */   Query query(String pattern)
/* 505:    */   {
/* 506:421 */     return new Query(pattern, null);
/* 507:    */   }
/* 508:    */   
/* 509:    */   private static class QueryEntry
/* 510:    */   {
/* 511:    */     public TreeMap tag;
/* 512:    */     public Iterator iter;
/* 513:    */     public String path;
/* 514:    */     int lvl;
/* 515:    */   }
/* 516:    */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TagFile
 * JD-Core Version:    0.7.0.1
 */