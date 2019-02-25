package com.mondergames.Entities;

import com.mondergames.Location;

public class Macrofago extends Enemy {

	public Macrofago(Location l0) {
		super(l0, EntityType.Macrofago);
	}

	@Override
	public void doAction() {
		super.doAction();
		this.getLocation().addX(((int) -this.getSpeed()) / 4);
	}
}
