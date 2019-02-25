package com.mondergames.Bullets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.mondergames.Location;
import com.mondergames.Main;
import com.mondergames.SpriteSheet;
import com.mondergames.Drops.Bacney;
import com.mondergames.Drops.Drops;
import com.mondergames.Entities.Enemy;
import com.mondergames.Entities.Entity;
import com.mondergames.Utilities.Side;
import com.mondergames.Windows.Game;

public class Shot {
	private BufferedImage currentFrame;
	private SpriteSheet spritesheet;
	private int frame;
	private Location location;
	private int sizeX;
	private int sizeY;
	private boolean remove;
	private boolean visible;
	private Side side;
	private int speed;
	private BulletType bullettype;

	public Shot(Entity entity, BulletType bulletType) {
		this.setEntityType(bulletType);
		this.setVisible(true);
		this.setLocation(entity.getLocation().clone());
		this.setSizeX(Game.getSizeX() / 2);
		this.setSizeY(Game.getSizeY() / 2);
		this.setSpeed((int) (this.getSizeX() - entity.getSpeed()));
		this.spritesheet = BulletType.toSpriteSheet(bulletType);
		this.setCurrentFrame(this.spritesheet.getSprite(1));
		this.setSide(entity.getSide());
		this.playSprite();
	}

	public Shot(Location ls0, BulletType bulletType) {
		this.setEntityType(bulletType);
		this.setVisible(true);
		this.setLocation(ls0);
		this.setSizeX(Game.getSizeX() * 2);
		this.setSizeY(Game.getSizeY() * 2);
		this.spritesheet = BulletType.toSpriteSheet(bulletType);
		this.setCurrentFrame(this.spritesheet.getSprite(1));
		this.setSide(Side.RIGHT);
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

	public Entity hasCrashed() {
		for (Entity e : Main.JGame.getEntities()) {
			if (e != null && !e.isDead()) {

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

	public BulletType getBulletType() {
		return this.bullettype;
	}

	public void setEntityType(BulletType bulletType) {
		this.bullettype = bulletType;
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

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void remove() {
		this.setRemove(true);
		this.setVisible(false);
		Main.JGame.shots.remove(this);
	}

	/**
	 * @return the remove
	 */
	public boolean isRemove() {
		return remove;
	}

	/**
	 * @param remove
	 *            the remove to set
	 */
	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public void doAction() {
		if (this.getSide().equals(Side.LEFT)) {
			this.getLocation().addX(-this.getSpeed());
		} else {
			this.getLocation().addX(this.getSpeed());
		}
		Entity ec = this.hasCrashed();
		if (ec != null && ec instanceof Enemy) {
			Drops.addDropsLive(new Bacney(ec.getLocation()));
			ec.die();
			this.remove();
		}
	}
}
