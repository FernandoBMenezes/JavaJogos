package com.mondergames.Drops;

import com.mondergames.Location;
import com.mondergames.Player.Player;

public class Bacney extends Drops{

	public Bacney(Location l0) {
		super(l0, DropsType.Bacney);
	}
	
	@Override
	public void doAction() {
		super.doAction();
		Player p = (Player) this.hasCrashed();
		if(p != null) {
			Drops.removeDropsLive(this);
			p.setBacney(p.getBacney()+1);
		}
	}
}