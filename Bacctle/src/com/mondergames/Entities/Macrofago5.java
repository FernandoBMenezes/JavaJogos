package com.mondergames.Entities;

import com.mondergames.Location;
import com.mondergames.Main;
import com.mondergames.Player.Player;

public class Macrofago5 extends Enemy {
	private Player player;

	public Macrofago5(Location l0) {
		super(l0, EntityType.Macrofago5);
	}

	@Override
	public void doAction() {
		super.doAction();

		if (getPlayer() != null) {
			this.getLocation().addX(
					((int) (-(((this.getLocation().getX() - getPlayer().getLocation().getX()) * 0.25) + this.getSpeed())
							* 0.50)) / 4);
			this.getLocation().addY(
					((int) (-(((this.getLocation().getY() - getPlayer().getLocation().getY()) * 0.25) + this.getSpeed())
							* 0.50)) / 4);
		} else {
			Entity ec = this.hasCrashed();
			if (ec != null && ec.getEntityType() == EntityType.Bacilo) {
				setPlayer((Player) ec);
			}
			this.getLocation().addX(((int) -this.getSpeed()) / 4);
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

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
