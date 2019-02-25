package com.mondergames.Drops;

import java.net.URL;

import com.mondergames.SpriteSheet;
import com.mondergames.Utilities.Side;

public enum DropsType {
	Bacney;
	URL bacneyFile = this.getClass().getClassLoader().getResource("Resources_Game\\bacneyDrop.gif");
	SpriteSheet bacneySprite;

	public SpriteSheet toSpriteSheet(DropsType dropstype2) {
		if (dropstype2.equals(DropsType.Bacney)) {
			if (bacneySprite == null) {
				bacneySprite = new SpriteSheet(bacneyFile, 96, 96, Side.RIGHT);
				return bacneySprite;
			} else {
				return bacneySprite;
			}
		}
		return null;
	}
}