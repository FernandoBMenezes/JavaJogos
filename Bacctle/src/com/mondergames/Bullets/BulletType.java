package com.mondergames.Bullets;

import java.net.URL;

import com.mondergames.Main;
import com.mondergames.SpriteSheet;
import com.mondergames.Utilities.Side;

public enum BulletType {
	Normal, Infection;

	public static URL normalFile = Main.main.getClass().getClassLoader().getResource("Resources_Game\\Normal.gif");
	public URL infectionFile = Main.main.getClass().getClassLoader().getResource("Resources_Game\\Infection.gif");
	public static SpriteSheet normalSprite;
	public SpriteSheet infectionSprite;
	

	public static SpriteSheet toSpriteSheet(BulletType e) {
		if (e.equals(BulletType.Normal)) {
			if (normalSprite == null) {
				normalSprite = new SpriteSheet(normalFile, 32, 32, Side.RIGHT);
				return normalSprite;
			} else {
				return normalSprite;
			}
		}
		return null;
	}
}
