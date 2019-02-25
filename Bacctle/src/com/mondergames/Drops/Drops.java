package com.mondergames.Drops;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import com.mondergames.Location;
import com.mondergames.Main;
import com.mondergames.SpriteSheet;
import com.mondergames.Player.Player;
import com.mondergames.Windows.Game;

public class Drops {
	private BufferedImage currentFrame;
	private SpriteSheet spritesheet;
	private int frame;
	private Location location;
	private int sizeX;
	private int sizeY;
	private DropsType dropstype;
	private static ArrayList<Drops> dropsLive;

	public Drops(Location l0, DropsType dropstype2) {
		this.setDropsType(dropstype2);
		this.setLocation(l0);
		this.setSizeX(Game.getSizeX());
		this.setSizeY(Game.getSizeY());
		this.spritesheet = dropstype2.toSpriteSheet(dropstype2);
		this.playSprite();
		Random random = new Random();
		this.setFrame(random.nextInt(spritesheet.getFrameCount()));
	}

	public void playSprite() {
		this.setCurrentFrame(this.spritesheet.getSprite(this.getFrame() % this.spritesheet.getFrameCount()));
		this.setFrame(this.getFrame() + 1);
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public BufferedImage getCurrentFrame() {
		return this.currentFrame;
	}

	public void setCurrentFrame(BufferedImage currentFrame) {
		this.currentFrame = currentFrame;
	}

	public int getFrame() {
		return this.frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public boolean hasInside(Location l2) {
		boolean xv = (l2.getX() >= this.getLocation().getX()
				&& l2.getX() <= this.getLocation().getX() + this.getSizeX());
		boolean yv = (l2.getY() >= this.getLocation().getY()
				&& l2.getY() <= this.getLocation().getY() + this.getSizeY());
		return (xv && yv);
	}

	public Player hasCrashed() {
		Player e = Main.player;
		if (!e.isDead()) {

			// Entity XY MIN
			Location lexy = e.getLocation().clone();
			lexy.addX((int) (e.getSizeX() * 0.25));
			lexy.addY((int) (e.getSizeY() * 0.25));

			// Entity X+1Y
			Location lexxy = e.getLocation().clone();
			lexxy.addX((int) (e.getSizeX() * 0.75));
			lexxy.addY((int) (e.getSizeY() * 0.25));

			// Entity XY+1
			Location lexyy = e.getLocation().clone();
			lexyy.addX((int) (e.getSizeX() * 0.25));
			lexyy.addY((int) (e.getSizeY() * 0.75));

			// Entity X+1Y+1 MAX
			Location lexxyy = e.getLocation().clone();
			lexxyy.addX((int) (e.getSizeX() * 0.75));
			lexxyy.addY((int) (e.getSizeY() * 0.75));

			// This XY
			Location ltxy = this.getLocation().clone();
			ltxy.addX((int) (this.getSizeX() * 0.25));
			ltxy.addY((int) (this.getSizeY() * 0.25));

			// This X+1Y+1
			Location ltxxyy = this.getLocation().clone();
			ltxxyy.addX((int) (this.getSizeX() * 0.75));
			ltxxyy.addY((int) (this.getSizeY() * 0.75));

			if (this.hasInside(lexy) || this.hasInside(lexxy) || this.hasInside(lexyy) || this.hasInside(lexxyy)) {
				return e;
			}
		}
		return null;
	}

	public Rectangle getBounds() {
		Location l = this.getLocation();
		return new Rectangle(l.getX(), l.getY(), this.getSizeX() / 2, this.getSizeY() / 2);
	}

	public int getSizeY() {
		return this.sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getSizeX() {
		return this.sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public void draw(Graphics g2d) {
		g2d.drawImage(this.getCurrentFrame(), (int) ((this.getLocation().getX()) * 1.025),
				(int) ((this.getLocation().getY()) * 1.025), this.getSizeX() / 2, this.getSizeY() / 2, Main.JGame);
	}

	public DropsType getDropsType() {
		return this.dropstype;
	}

	public void setDropsType(DropsType entitytype2) {
		this.dropstype = entitytype2;
	}

	public void doAction() {
		// TODO
	}

	/**
	 * @return the dropsLive
	 */
	public static synchronized ArrayList<Drops> getDropsLive() {
		return dropsLive;
	}

	/**
	 * @param dropsLive
	 *            the dropsLive to set
	 */
	public static synchronized void setDropsLive(ArrayList<Drops> dropsLive) {
		Drops.dropsLive = dropsLive;
	}

	public static synchronized void addDropsLive(Drops drop) {
		dropsLive.add(drop);
	}

	public static synchronized void removeDropsLive(Drops dropRemove) {

		dropsLive.remove(dropRemove);
	}
}