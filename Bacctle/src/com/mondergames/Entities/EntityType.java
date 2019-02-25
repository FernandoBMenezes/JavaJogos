package com.mondergames.Entities;

import java.net.URL;

import com.mondergames.SpriteSheet;
import com.mondergames.Utilities.Side;

public enum EntityType {
	Bacilo, Macrofago, Macrofago2, Macrofago3, Macrofago4, Macrofago5, Macrofago6;

	URL macrofagoFile = this.getClass().getClassLoader().getResource("Resources_Game\\macrofago.gif");
	URL macrofagoFile2 = this.getClass().getClassLoader().getResource("Resources_Game\\macrofago2.gif");
	URL macrofagoFile3 = this.getClass().getClassLoader().getResource("Resources_Game\\macrofago3.gif");
	URL macrofagoFile4 = this.getClass().getClassLoader().getResource("Resources_Game\\macrofago4.gif");
	URL macrofagoFile5 = this.getClass().getClassLoader().getResource("Resources_Game\\macrofago5.gif");
	URL macrofagoFile6 = this.getClass().getClassLoader().getResource("Resources_Game\\macrofago6.gif");
	URL baciloFile = this.getClass().getClassLoader().getResource("Resources_Game\\bacilo.gif");
	SpriteSheet baciloSprite;
	SpriteSheet macrofagoSprite;
	SpriteSheet macrofagoSprite2;
	SpriteSheet macrofagoSprite3;
	SpriteSheet macrofagoSprite4;
	SpriteSheet macrofagoSprite5;
	SpriteSheet macrofagoSprite6;

	public SpriteSheet toSpriteSheet(EntityType e) {
		if (e.equals(EntityType.Macrofago)) {
			if (macrofagoSprite == null) {
				macrofagoSprite = new SpriteSheet(macrofagoFile, 256, 256, Side.RIGHT);
				return macrofagoSprite;
			} else {
				return macrofagoSprite;
			}
		}
		if (e.equals(EntityType.Macrofago2)) {
			if (macrofagoSprite2 == null) {
				macrofagoSprite2 = new SpriteSheet(macrofagoFile2, 256, 256, Side.RIGHT);
				return macrofagoSprite2;
			} else {
				return macrofagoSprite2;
			}
		}
		if (e.equals(EntityType.Macrofago3)) {
			if (macrofagoSprite3 == null) {
				macrofagoSprite3 = new SpriteSheet(macrofagoFile3, 256, 256, Side.RIGHT);
				return macrofagoSprite3;
			} else {
				return macrofagoSprite3;
			}
		}
		if (e.equals(EntityType.Macrofago4)) {
			if (macrofagoSprite4 == null) {
				macrofagoSprite4 = new SpriteSheet(macrofagoFile4, 256, 256, Side.RIGHT);
				return macrofagoSprite4;
			} else {
				return macrofagoSprite4;
			}
		}
		if (e.equals(EntityType.Macrofago5)) {
			if (macrofagoSprite5 == null) {
				macrofagoSprite5 = new SpriteSheet(macrofagoFile5, 256, 256, Side.RIGHT);
				return macrofagoSprite5;
			} else {
				return macrofagoSprite5;
			}
		}
		if (e.equals(EntityType.Macrofago6)) {
			if (macrofagoSprite6 == null) {
				macrofagoSprite6 = new SpriteSheet(macrofagoFile6, 256, 256, Side.RIGHT);
				return macrofagoSprite6;
			} else {
				return macrofagoSprite6;
			}
		}
		if (e.equals(EntityType.Bacilo)) {
			if (baciloSprite == null) {
				baciloSprite = new SpriteSheet(baciloFile, 96, 96, Side.LEFT);
				return baciloSprite;
			} else {
				return baciloSprite;
			}
		}
		return null;
	}
	public static EntityType IntToEntityType(int i) {
		if (i == 1) {
			return EntityType.Macrofago;
		}
		if (i == 2) {
			return EntityType.Macrofago2;
		}
		if (i == 3) {
			return EntityType.Macrofago3;
		}
		if (i == 4) {
			return EntityType.Macrofago4;
		}
		if (i == 5) {
			return EntityType.Macrofago5;
		}
		if (i == 6) {
			return EntityType.Macrofago6;
		}
		if (i == 0) {
			return EntityType.Bacilo;
		}
		return null;
	}
	public static String IntToString(int i) {
		if (i == 1) {
			return EntityType.Macrofago.toString();
		}
		if (i == 2) {
			return EntityType.Macrofago2.toString();
		}
		if (i == 3) {
			return EntityType.Macrofago3.toString();
		}
		if (i == 4) {
			return EntityType.Macrofago4.toString();
		}
		if (i == 5) {
			return EntityType.Macrofago5.toString();
		}
		if (i == 6) {
			return EntityType.Macrofago6.toString();
		}
		if (i == 0) {
			return EntityType.Bacilo.toString();
		}
		return null;
	}
	public String toString() {
		return this.toString();
	}
}
