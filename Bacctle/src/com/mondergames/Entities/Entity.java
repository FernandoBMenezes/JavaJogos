package com.mondergames.Entities;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.mondergames.Location;
import com.mondergames.Main;
import com.mondergames.SpriteSheet;
import com.mondergames.Effects.Effects;
import com.mondergames.Effects.EffectsType;
import com.mondergames.Utilities.Side;
import com.mondergames.Windows.Game;

public class Entity {
	private BufferedImage currentFrame;
	private SpriteSheet spritesheet;
	private int frame;
	private Location location;
	private int[][] spaceCollider;
	private int sizeX;
	private int sizeY;
	private boolean dead;
	private boolean visible;
	private Side side;
	private double speed;
	private EntityType entitytype;
	private Effects effects;

	public Entity(Location l0, EntityType entitytype2) {
		this.setDead(false);
		this.setEntityType(entitytype2);
		this.setVisible(true);
		this.setLocation(l0);
		this.setSizeX(Game.getSizeX());
		this.setSizeY(Game.getSizeY());
		this.spritesheet = entitytype2.toSpriteSheet(entitytype2);
		this.playSprite();
		Random random = new Random();
		this.setFrame(random.nextInt(spritesheet.getFrameCount()));
		this.setSide(Side.LEFT);
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

	public int[][] getSpaceCollider() {
		return this.spaceCollider;
	}

	public boolean hasInside(Location l2) {
		boolean xv = (l2.getX() >= this.getLocation().getX()
				&& l2.getX() <= this.getLocation().getX() + this.getSizeX());
		boolean yv = (l2.getY() >= this.getLocation().getY()
				&& l2.getY() <= this.getLocation().getY() + this.getSizeY());
		return (xv && yv);
	}

	public Entity hasCrashed() {//FIXME
		for (Entity e : Main.JGame.getEntities()) {
			if (e != this && !e.isDead()) {

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
		}
		return null;
	}

	public void setSpaceCollider(int[][] spaceCollider) {
		if (this.spaceCollider == null) {
			this.spaceCollider = spaceCollider;
		}
		this.spaceCollider = spaceCollider;
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
		if (this.isVisible() == true && this.getSide() == Side.RIGHT) {
			g2d.drawImage(this.getCurrentFrame(), this.getLocation().getX(), this.getLocation().getY(), this.getSizeX(),
					this.getSizeY(), Main.JGame);
		} else {
			g2d.drawImage(this.getCurrentFrame(), this.getLocation().getX(), this.getLocation().getY(), this.getSizeX(),
					this.getSizeY(), Main.JGame);
		}
	}

	public EntityType getEntityType() {
		return this.entitytype;
	}

	public void setEntityType(EntityType entitytype2) {
		this.entitytype = entitytype2;
	}

	public boolean isDead() {
		return this.dead;
	}

	public void setDead(boolean state) {
		this.dead = state;
	}

	public void die() {
		this.setDead(true);
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setVisible(boolean state) {
		this.visible = state;
	}

	public Side getSide() {
		return this.side;
	}

	public void setSide(Side side) {
		this.side = side;
		spritesheet.setSide(side);
	}

	public double getSpeed() {
		if (this.getEffects() != null && this.getEffects().getEffecttype().equals(EffectsType.Slow)) {
			return (this.speed) / 4;
		}
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Effects getEffects() {
		return this.effects;
	}

	public void setEffects(Effects effects) {
		this.effects = effects;
	}

	public void addEffect(Effects effects) {
		this.setEffects(effects);
	}

	public void removeEffect(ActionListener actionListener) {
		this.setEffects(null);
	}
}
