package com.mondergames.Entities;

import com.mondergames.Location;
import com.mondergames.Main;
import com.mondergames.Player.Player;

public class Macrofago4 extends Enemy {
	boolean hasSafety = false;

	public Macrofago4(Location l0) {
		super(l0, EntityType.Macrofago4);
	}

	@Override
	public void doAction() {
		super.doAction();
		Entity ec = this.hasCrashed();

		if (ec != null && ec.getEntityType() == EntityType.Bacilo) {
			this.getLocation().addX(
					(int) (((((this.getLocation().getX() - ec.getLocation().getX()) * 0.25) + this.getSpeed()) * 0.25))
							/ 4);
			this.getLocation().addY(
					(int) (((((this.getLocation().getY() - ec.getLocation().getY()) * 0.25) + this.getSpeed()) * 0.25))
							/ 4);
			if (!hasSafety) {
				Macrofago5 safety = new Macrofago5(
						this.getLocation().clone().addXandReturn(-this.getSizeX() - this.getLocation().getX()));
				Main.JGame.addEntities(safety);
				safety.setPlayer((Player) ec);
				hasSafety = true;
			}
		} else {
			this.getLocation().addX(((int) -this.getSpeed()) / 4);
		}
	}

	@Override
	public Entity hasCrashed() {
		for (Entity e : Main.JGame.getEntities()) {
			if (e != this && !e.isDead()) {

				// Entity XY MIN
				Location lexy = e.getLocation().clone();
				lexy.addX((int) -(e.getSizeX() * 0.50));
				lexy.addY((int) -(e.getSizeY() * 0.50));

				// Entity X+1Y
				Location lexxy = e.getLocation().clone();
				lexxy.addX((int) (e.getSizeX() * 1.50));
				lexxy.addY((int) -(e.getSizeY() * 0.50));

				// Entity XY+1
				Location lexyy = e.getLocation().clone();
				lexyy.addX((int) -(e.getSizeX() * 0.50));
				lexyy.addY((int) (e.getSizeY() * 1.50));

				// Entity X+1Y+1 MAX
				Location lexxyy = e.getLocation().clone();
				lexxyy.addX((int) (e.getSizeX() * 1.50));
				lexxyy.addY((int) (e.getSizeY() * 1.50));

				// This XY
				Location ltxy = this.getLocation().clone();
				ltxy.addX((int) -(this.getSizeX() * 0.50));
				ltxy.addY((int) -(this.getSizeY() * 0.50));

				// This X+1Y+1
				Location ltxxyy = this.getLocation().clone();
				ltxxyy.addX((int) (this.getSizeX() * 1.50));
				ltxxyy.addY((int) (this.getSizeY() * 1.50));

				if (this.hasInside(lexy) || this.hasInside(lexxy) || this.hasInside(lexyy) || this.hasInside(lexxyy)) {
					return e;
				}
			}
		}
		return null;
	}
}