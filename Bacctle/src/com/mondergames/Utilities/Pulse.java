package com.mondergames.Utilities;

import com.mondergames.Main;
import com.mondergames.Windows.Game;

public class Pulse {

	public float x, y;
	public float speed;
	private final float gravity = 0.5f;
	private final int y_offset = Main.Height / 5;
	private boolean splash = false;

	public Pulse(int cx, int cy) {
		x = cx;
		y = cy;
		speed = 0;
	}

	public void update() {

		speed += gravity;

		y += speed;

		if (y > y_offset - 20 && !splash) {
			Game.blood.splash(x, speed);
			splash = true;
		}

		if (y > y_offset + 20)
			speed /= 2;

		if (y > Main.Height) {
			Game.blood.getPulses().remove(this);
		}
	}
}
