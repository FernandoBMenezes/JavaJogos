package com.mondergames.Utilities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Collider {
	private int Width;
	private int Height;
	private int[][] spaceCollider;

	public Collider(int Width, int Height) {
		this.Width = Width;
		this.Height = Height;
		spaceCollider = new int[this.Width][this.Height];
	}

	public int printPixelARGB(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		if (alpha == 0 || red + green + blue == 255 * 3) {
			return 0;
		} else {
			return 1;
		}
	}

	public int[][] marchThroughImage(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		int[][] space = new int[w][h];
		for (int iy = 0; iy < h; iy++) {
			for (int ix = 0; ix < w; ix++) {
				int pixel = image.getRGB(ix, iy);
				space[ix][iy] = printPixelARGB(pixel);
			}
		}
		return space;
	}
	
	public static BufferedImage rotateHorizontal(BufferedImage imgtorotate, int w, int h) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-imgtorotate.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imgtorotate = op.filter(imgtorotate, null);
		return imgtorotate;
	}

	public static BufferedImage resize(BufferedImage src, int w, int h) {
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		int x, y;
		int ww = src.getWidth();
		int hh = src.getHeight();
		int[] ys = new int[h];
		for (y = 0; y < h; y++)
			ys[y] = y * hh / h;
		for (x = 0; x < w; x++) {
			int newX = x * ww / w;
			for (y = 0; y < h; y++) {
				int col = src.getRGB(newX, ys[y]);
				img.setRGB(x, y, col);
			}
		}
		return img;
	}

	public BufferedImage toBufferedImage(Image img) {
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics bg = bimage.getGraphics();
		bg.drawImage(img, 0, 0, null);
		bg.dispose();
		return bimage;
	}

	public void addCollider(int x, int y, int xSize, int ySize, int[][] imgPixel) {
		for (int iy = 0; iy < ySize; iy++) {
			for (int ix = 0; ix < xSize; ix++) {
				int xx = x + ix;
				int yy = y + iy;
				if ((x > 0 && y > 0) && (x < xSize && y < ySize)) {
					int imgPixelQ = imgPixel[ix][iy];
					this.getSpaceCollider()[xx][yy] = imgPixelQ;
				}
			}
		}
	}

	public boolean verifyCollider(int x, int y, int xSize, int ySize, int[][] imgPixel) {
		for (int iy = 0; iy < xSize; iy++) {
			for (int ix = 0; ix < ySize; ix++) {
				int xx = x + ix;
				int yy = y + iy;
				if ((x > 0 && y > 0) && (x < xSize && y < ySize) && this.getSpaceCollider()[xx][yy] != 0
						&& imgPixel[ix][iy] != 1) {
					System.out.println(xx + ":" + yy);
				}
			}
		}
		return false;
	}

	public void clearCollider() {
		this.spaceCollider = new int[this.Width][this.Height];
	}

	public int[][] getSpaceCollider() {
		return this.spaceCollider;
	}
}
