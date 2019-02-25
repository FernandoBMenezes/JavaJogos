package com.mondergames.Entities;

import com.mondergames.Location;
import com.mondergames.Effects.Effects;
import com.mondergames.Effects.EffectsType;

public class Macrofago2 extends Enemy {

	public Macrofago2(Location l0) {
		super(l0, EntityType.Macrofago2);
	}

	@Override
	public void doAction() {
		super.doAction();
		this.getLocation().addX(((int) -this.getSpeed()) / 4);
		// -12
		Entity ec = this.hasCrashed();
		if (ec != null && ec.getEntityType() == EntityType.Bacilo) {
			Effects ef = new Effects(EffectsType.Slow);
			ef.doAction(ec);
			this.die();
		}
	}
}
