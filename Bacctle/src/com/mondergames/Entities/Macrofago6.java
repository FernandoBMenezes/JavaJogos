package com.mondergames.Entities;

import com.mondergames.Location;
import com.mondergames.Main;
import com.mondergames.Player.Player;

public class Macrofago6 extends Enemy {
	Player player;
	int x, y;

	public Macrofago6(Location l0) {
		super(l0, EntityType.Macrofago6);
	}

	@Override
	public void doAction() {
		super.doAction();
		if (player == null) {
			Entity ec = this.hasCrashed();
			this.getLocation().addX((int) -this.getSpeed()/4);

			if (ec != null && ec instanceof Player) {
				player = (Player) ec;
				x = ((int) (-(((this.getLocation().getX()-player.getLocation().getX())*0.25)+this.getSpeed())*0.50))/4;
				y = ((int) (-(((this.getLocation().getY() - player.getLocation().getY()) * 0.25) + this.getSpeed())
						* 0.50)) / 4;
			}
		} else {
			this.getLocation().addX(x);
			this.getLocation().addY(y);
		}
	}

	@Override
	public Entity hasCrashed() {
		for (Entity e : Main.JGame.getEntities()) {
			if (e != this && !e.isDead()) {

				// Entity XY MIN
				Location lexy = e.getLocation().clone();
				lexy.addX((int) -(e.getSizeX() * 0.75));
				lexy.addY((int) -(e.getSizeY() * 0.75));

				// Entity X+1Y
				Location lexxy = e.getLocation().clone();
				lexxy.addX((int) (e.getSizeX() * 1.75));
				lexxy.addY((int) -(e.getSizeY() * 0.75));

				// Entity XY+1
				Location lexyy = e.getLocation().clone();
				lexyy.addX((int) -(e.getSizeX() * 0.75));
				lexyy.addY((int) (e.getSizeY() * 1.75));

				// Entity X+1Y+1 MAX
				Location lexxyy = e.getLocation().clone();
				lexxyy.addX((int) (e.getSizeX() * 1.75));
				lexxyy.addY((int) (e.getSizeY() * 1.75));

				// This XY
				Location ltxy = this.getLocation().clone();
				ltxy.addX((int) -(this.getSizeX() * 0.75));
				ltxy.addY((int) -(this.getSizeY() * 0.75));

				// This X+1Y+1
				Location ltxxyy = this.getLocation().clone();
				ltxxyy.addX((int) (this.getSizeX() * 1.75));
				ltxxyy.addY((int) (this.getSizeY() * 1.75));

				if (this.hasInside(lexy) || this.hasInside(lexxy) || this.hasInside(lexyy) || this.hasInside(lexxyy)) {
					return e;
				}
			}
		}
		return null;
	}
}