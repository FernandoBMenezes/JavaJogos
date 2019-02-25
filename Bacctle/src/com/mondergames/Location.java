package com.mondergames;

public class Location implements Cloneable {
	private int[] location;

	public Location(int X, int Y) {
		int[] l0 = new int[2];
		l0[0] = X;
		l0[1] = Y;
		this.location = l0;
	}

	public int[] getLocation() {
		return this.location;
	}

	public int getX() {
		return this.location[0];
	}

	public int getY() {
		return this.location[1];
	}

	public void addX(int x) {
		this.location[0] = this.getX() + x;
	}

	public void addY(int y) {
		this.location[1] = this.getY() + y;
	}

	public Location addXandReturn(int x) {
		this.location[0] = this.getX() + x;
		return this;
	}

	public Location addYandReturn(int y) {
		this.location[1] = this.getY() + y;
		return this;
	}

	public void setarCordenadas(int[] cords) {
		this.location = cords;
	}

	public Location clone() {
		Location lc = new Location(this.getX(), this.getY());
		return lc;
	}
}
