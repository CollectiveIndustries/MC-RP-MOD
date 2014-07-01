/*  1:   */ package com.eloraam.redpower.core;
/*  2:   */ 
/*  3:   */ import bba;
/*  4:   */ import bem;
/*  5:   */ import ben;
/*  6:   */ import cpw.mods.fml.client.FMLTextureFX;
/*  7:   */ import java.awt.image.BufferedImage;
/*  8:   */ import java.io.InputStream;
/*  9:   */ import javax.imageio.ImageIO;
/* 10:   */ import net.minecraft.client.Minecraft;
/* 11:   */ import org.lwjgl.opengl.GL11;
/* 12:   */ 
/* 13:   */ public class TextureAnimFX
/* 14:   */   extends FMLTextureFX
/* 15:   */ {
/* 16:   */   public byte[][] textureFrames;
/* 17:18 */   private int index = 0;
/* 18:18 */   private int tick = 0;
/* 19:   */   private int tickRate;
/* 20:   */   private String textureFile;
/* 21:   */   
/* 22:   */   private byte[] convertRGB(int[] rgb)
/* 23:   */   {
/* 24:22 */     byte[] tr = new byte[rgb.length * 4];
/* 25:24 */     for (int i = 0; i < rgb.length; i++)
/* 26:   */     {
/* 27:25 */       tr[(i * 4)] = ((byte)(rgb[i] >> 16));
/* 28:26 */       tr[(i * 4 + 1)] = ((byte)(rgb[i] >> 8));
/* 29:27 */       tr[(i * 4 + 2)] = ((byte)rgb[i]);
/* 30:28 */       tr[(i * 4 + 3)] = ((byte)(rgb[i] >> 24));
/* 31:   */     }
/* 32:30 */     return tr;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public TextureAnimFX(int idx, String tex, int ticks, int[] frame)
/* 36:   */   {
/* 37:34 */     super(idx);
/* 38:35 */     this.textureFile = tex;
/* 39:36 */     this.tickRate = ticks;
/* 40:37 */     this.textureFrames = new byte[frame.length][];
/* 41:   */     try
/* 42:   */     {
/* 43:40 */       InputStream is = Minecraft.x().C.e().a(tex);
/* 44:43 */       if (is == null) {
/* 45:44 */         throw new Exception("Image not found: " + tex);
/* 46:   */       }
/* 47:46 */       BufferedImage img = ImageIO.read(is);
/* 48:   */       
/* 49:48 */       int[] tmp = new int[this.tileSizeBase * this.tileSizeBase];
/* 50:49 */       for (int i = 0; i < frame.length; i++)
/* 51:   */       {
/* 52:50 */         int fx = (frame[i] & 0xF) * this.tileSizeBase;
/* 53:51 */         int fy = (frame[i] >> 4) * this.tileSizeBase;
/* 54:52 */         img.getRGB(fx, fy, this.tileSizeBase, this.tileSizeBase, tmp, 0, this.tileSizeBase);
/* 55:   */         
/* 56:54 */         this.textureFrames[i] = convertRGB(tmp);
/* 57:   */       }
/* 58:   */     }
/* 59:   */     catch (Exception e)
/* 60:   */     {
/* 61:57 */       e.printStackTrace();
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void a()
/* 66:   */   {
/* 67:63 */     this.tick += 1;
/* 68:64 */     if (this.tick < this.tickRate) {
/* 69:64 */       return;
/* 70:   */     }
/* 71:65 */     this.tick = 0;
/* 72:   */     
/* 73:67 */     this.index += 1;
/* 74:68 */     if (this.index >= this.textureFrames.length) {
/* 75:68 */       this.index = 0;
/* 76:   */     }
/* 77:70 */     this.d = this.textureFrames[this.index];
/* 78:   */   }
/* 79:   */   
/* 80:   */   public void a(bba re)
/* 81:   */   {
/* 82:75 */     GL11.glBindTexture(3553, re.b(this.textureFile));
/* 83:   */   }
/* 84:   */ }


/* Location:           C:\Users\Admiral\Desktop\REDPOWER CODE\RedPowerCore-2.0pr6.zip
 * Qualified Name:     com.eloraam.redpower.core.TextureAnimFX
 * JD-Core Version:    0.7.0.1
 */