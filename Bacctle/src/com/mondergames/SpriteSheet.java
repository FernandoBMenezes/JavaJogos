package com.mondergames;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import com.mondergames.Utilities.Collider;
import com.mondergames.Utilities.Side;

public class SpriteSheet {

	private URL path;
	private int frameWidth;
	private int frameHeight;
	private BufferedImage sheet;
	private BufferedImage[] frameImages;
	private Side sideCurrent;
	private Side side;

	public SpriteSheet(URL macrofagoFile, int width, int height, Side side) {
		this.path = macrofagoFile;
		this.frameWidth = width;
		this.frameHeight = height;
		this.sideCurrent = side;
		this.side = side;
		try {
			if (side.equals(Side.LEFT)) {
				sheet = ImageIO.read(path);
			} else {
				BufferedImage readBuff = ImageIO.read(path);
				sheet = Collider.rotateHorizontal(readBuff, this.frameWidth, this.frameHeight);
			}
			frameImages = getAllSprites();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BufferedImage getSprite(int frame) {
		if (sideCurrent != side) {
			BufferedImage readBuff = null;
			try {
				readBuff = ImageIO.read(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (side == Side.LEFT) {
				sheet = Collider.rotateHorizontal(readBuff, this.frameWidth, this.frameHeight);
			} else {
				try {
					sheet = ImageIO.read(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			frameImages = getAllSprites();
			sideCurrent = side;
			return frameImages[frame];
		}
		return frameImages[frame];
	}

	public int getHeight() {
		return frameHeight;
	}

	public int getWidth() {
		return frameWidth;
	}

	public int getColumnCount() {
		return sheet.getWidth() / getWidth();
	}

	public int getRowCount() {
		return sheet.getHeight() / getHeight();
	}

	public int getFrameCount() {
		int cols = getColumnCount();
		int rows = getRowCount();
		return cols * rows;
	}

	private BufferedImage getSprite(int x, int y, int w, int h) {
		BufferedImage sprite = sheet.getSubimage(x, y, w, h);
		return sprite;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public BufferedImage[] getAllSprites() {
		int frameCount = getFrameCount();
		BufferedImage[] sprites = new BufferedImage[frameCount];
		int index = 0;
		for (int row = 0; row < getRowCount(); row++) {
			for (int col = 0; col < getColumnCount(); col++) {
				int x = col * getWidth();
				int y = row * getHeight();
				BufferedImage currentSprite = getSprite(x, y, getWidth(), getHeight());
				sprites[index] = currentSprite;
				index++;
			}
		}
		return sprites;
	}
	
}