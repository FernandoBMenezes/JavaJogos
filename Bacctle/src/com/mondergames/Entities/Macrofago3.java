package com.mondergames.Entities;

import com.mondergames.Location;
import com.mondergames.Main;

public class Macrofago3 extends Enemy {

	public Macrofago3(Location l0) {
		super(l0, EntityType.Macrofago3);
	}

	@Override
	public void doAction() {
		super.doAction();
		Entity ec = this.hasCrashed();

		if (ec != null && ec.getEntityType() == EntityType.Bacilo) {
			this.getLocation().addX(
					((int) -(((this.getLocation().getX() - ec.getLocation().getX()) + this.getSpeed()) * 0.10)) / 4);
			// 8 -20
			this.getLocation().addY(
					((int) -(((this.getLocation().getY() - ec.getLocation().getY()) + this.getSpeed()) * 0.10)) / 4);
		} else {
			this.getLocation().addX(((int) -this.getSpeed()) / 4);
		}
	}

	@Override
	public Entity hasCrashed() {
		double distance = 0.95;
		for (Entity e : Main.JGame.getEntities()) {
			if (e != this && !e.isDead()) {
				// Entity XY MIN
				Location lexy = e.getLocation().clone();
				lexy.addX((int) -(e.getSizeX() * distance));
				lexy.addY((int) -(e.getSizeY() * distance));

				// Entity X+1Y
				Location lexxy = e.getLocation().clone();
				lexxy.addX((int) (e.getSizeX() * (1 + distance)));
				lexxy.addY((int) -(e.getSizeY() * distance));

				// Entity XY+1
				Location lexyy = e.getLocation().clone();
				lexyy.addX((int) -(e.getSizeX() * distance));
				lexyy.addY((int) (e.getSizeY() * (1 + distance)));

				// Entity X+1Y+1 MAX
				Location lexxyy = e.getLocation().clone();
				lexxyy.addX((int) (e.getSizeX() * (1 + distance)));
				lexxyy.addY((int) (e.getSizeY() * (1 + distance)));

				if (this.hasInside(lexy) || this.hasInside(lexxy) || this.hasInside(lexyy) || this.hasInside(lexxyy)) {
					return e;
				}
			}
		}
		return null;
	}
}
