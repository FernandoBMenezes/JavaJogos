package com.mondergames.Player;

import java.awt.Rectangle;

import com.mondergames.Location;
import com.mondergames.Encrypts.VDouble;
import com.mondergames.Entities.Entity;
import com.mondergames.Entities.EntityType;

public class Player extends Entity {
	private VDouble bacney;
	private int ammunition;

	public Player(Location l0, EntityType entityType) {
		super(l0, entityType);
		bacney = new VDouble(150);
	}

	/**
	 * @return the bacney
	 */
	public int getBacney() {
		return this.bacney.toInt();
	}

	/**
	 * @param bacney
	 *            the bacney to set
	 */
	public void setBacney(int bacney) {
		this.bacney = new VDouble(bacney);
	}

	/**
	 * @return the ammunition
	 */
	public int getAmmunition() {
		return this.ammunition;
	}
	
	public Rectangle getBounds() {
		Location l = this.getLocation();
		return new Rectangle(l.getX(), l.getY(), this.getSizeX(), this.getSizeY());
	}

	/**
	 * @param ammunition
	 *            the ammunition to set
	 */
	public void setAmmunition(int ammunition) {
		this.ammunition = ammunition;
	}
}
