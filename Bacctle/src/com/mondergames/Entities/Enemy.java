package com.mondergames.Entities;

import com.mondergames.Location;
import com.mondergames.Main;

public class Enemy extends Entity {

	public Enemy(Location l0, EntityType entitytype) {
		super(l0, entitytype);
		this.setSpeed((int) (this.getSizeX()*0.033));
	}

	public void doAction() {
		if (this.getLocation().getX() < 0 - this.getSizeX()) {
			this.die();
			Main.JGame.getEntities().remove(this);
		}
	}
}
