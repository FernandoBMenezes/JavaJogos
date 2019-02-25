package com.mondergames.Utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import com.mondergames.Main;

public class Blood {
	private final static Color back = new Color(145, 60, 100);
	private final static Color fore = new Color(145, 60, 100);
	private final static Color color1 = new Color(160, 70, 145);
	private final static Color color2 = new Color(215, 90, 155);
	private static int num_springs = 100;
	public int y_offset = Main.Height / 100;
	private static Spring[] springs = new Spring[num_springs];
	private final float spread = 0.05f;
	public List<Pulse> pulses = new ArrayList<Pulse>();
	public static int WidthBack = (int) (Main.Width+(Main.Width*0.1));

	public Blood() {
		for (int n = 0; n < springs.length; n++) {
			float t = (float) n / (float) springs.length;
			springs[n] = new Spring(t * WidthBack, y_offset);
		}
	}

	public void update() {
		for (int i = 0; i < springs.length; i++)
			springs[i].update();
		float[] leftDeltas = new float[springs.length];
		float[] rightDeltas = new float[springs.length];
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < springs.length; i++) {
				if (i > 0) {
					leftDeltas[i] = spread * (springs[i].posy - springs[i - 1].posy);
					springs[i - 1].speed += leftDeltas[i];
				}
				if (i < springs.length - 1) {
					rightDeltas[i] = spread * (springs[i].posy - springs[i + 1].posy);
					springs[i + 1].speed += rightDeltas[i];
				}
			}
			for (int i = 0; i < springs.length; i++) {
				if (i > 0)
					springs[i - 1].posy += leftDeltas[i];
				if (i < springs.length - 1)
					springs[i + 1].posy += rightDeltas[i];
			}
		}
		for (int i = 0; i < pulses.size(); i++)
			pulses.get(i).update();
	}

	public void splash(float x, float speed) {
		float bestDistanceSoFar = WidthBack;
		int index = 0;
		for (int i = 0; i < springs.length; i++) {
			float distance = Math.abs(springs[i].posx - x);
			if (distance < bestDistanceSoFar) {
				bestDistanceSoFar = distance;
				index = i;
			}
		}
		springs[index].speed = speed * 5;
	}

	public List<Pulse> getPulses() {
		return pulses;
	}

	public void draw(Graphics g) {
		g.setColor(back);
		g.fillRect(0, 0, WidthBack, Main.Height);
		g.setColor(fore);
		int a1 = 50;
		int a2 = (int) (a1 * 0.8);
		for (int i = 0; i < springs.length - 1; i++) {
			int[] xPoints2 = new int[] { (int) springs[i].posx, (int) springs[i + 1].posx, (int) springs[i + 1].posx,
					(int) springs[i].posx };
			int[] yPoints2 = new int[] { (int) springs[i].posy + a1, (int) springs[i + 1].posy + a1, -1, 0 };
			g.setColor(color2);
			g.fillPolygon(xPoints2, yPoints2, 4);
			//
			int[] xPoints = new int[] { (int) springs[i].posx, (int) springs[i + 1].posx, (int) springs[i + 1].posx,
					(int) springs[i].posx };
			int[] yPoints = new int[] { (int) springs[i].posy + a2, (int) springs[i + 1].posy + a2, -1, 0 };
			g.setColor(color1);
			g.fillPolygon(xPoints, yPoints, 4);
			//
			int[] xPoints4 = new int[] { (int) springs[i].posx, (int) springs[i + 1].posx, (int) springs[i + 1].posx,
					(int) springs[i].posx };
			int[] yPoints4 = new int[] { (int) -springs[i].posy + Main.Height - a1,
					(int) -springs[i + 1].posy + Main.Height - a1, Main.Height + 1, Main.Height };
			//
			g.setColor(color2);
			g.fillPolygon(xPoints4, yPoints4, 4);
			int[] xPoints3 = new int[] { (int) springs[i].posx, (int) springs[i + 1].posx, (int) springs[i + 1].posx,
					(int) springs[i].posx };
			int[] yPoints3 = new int[] { (int) -springs[i].posy + Main.Height - a2,
					(int) -springs[i + 1].posy + Main.Height - a2, Main.Height + 1, Main.Height };
			g.setColor(color1);
			g.fillPolygon(xPoints3, yPoints3, 4);
			//
		}
	}
}
