package com.mondergames.Windows;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.Timer;
import com.mondergames.KeyBoard;
import com.mondergames.Location;
import com.mondergames.Main;
import com.mondergames.Bullets.BulletType;
import com.mondergames.Bullets.Shot;
import com.mondergames.Drops.Drops;
import com.mondergames.Entities.Enemy;
import com.mondergames.Entities.Entity;
import com.mondergames.Entities.Macrofago;
import com.mondergames.Entities.Macrofago2;
import com.mondergames.Entities.Macrofago3;
import com.mondergames.Entities.Macrofago4;
import com.mondergames.Entities.Macrofago5;
import com.mondergames.Entities.Macrofago6;
import com.mondergames.Files.Save;
import com.mondergames.Player.Player;
import com.mondergames.Sounds.Sound;
import com.mondergames.Utilities.Blood;
import com.mondergames.Utilities.Pulse;
import com.mondergames.Utilities.Side;
import com.mondergames.SpriteSheet;

public class Game extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static Player player;
	private static int amountX = 15;
	private static int amountY = 9;
	private static int sizeX = (Main.Width / amountX);
	private static int sizeY = (Main.Height / amountY);
	private static Set<Integer> keys = KeyBoard.keys;
	private static List<Entity> entities;
	private static Graphics g2d;
	public Timer timer0, timer1;
	int frametimer1;
	int frametimer2;
	int frametimer3;
	private boolean pause;
	public static Blood blood;
	public ArrayList<Shot> shots;
	public URL bacneyString = this.getClass().getClassLoader().getResource("Resources_Menu\\bacney.gif");
	public SpriteSheet bacneySprite;
	public Sound musicSound;

	public Game() {
		// PLAYER 1
		player = Main.player;
		blood = new Blood();
		shots = new ArrayList<Shot>();
		entities = new ArrayList<Entity>();
		Drops.setDropsLive(new ArrayList<Drops>());// DO TUDO ASSIM
		Location l0 = new Location(getSizeX(), (getSizeY() * amountY) / 2);
		player.setLocation(l0);
		player.setSide(Side.RIGHT);
		player.setSpeed((int) (player.getSizeX() * Main.speed.getValue()));
		// ADICIONANDO ENTITIES
		entities.add(player);
		// BACNEY IMPORT
		bacneySprite = new SpriteSheet(bacneyString, 96, 96, Side.RIGHT);
		URL music = this.getClass().getClassLoader().getResource("Resources_Sounds\\music.wav");
		musicSound = new Sound(music, -30);
		timer0 = new Timer(1000 / 60, new ActionListener() {// 60 FPS
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				if (pause) {
					keyboard();
				} else {
					keyboard();
					if (!Drops.getDropsLive().isEmpty()) {
						for (int i = 0; i < Drops.getDropsLive().size(); i++) {
							if (Drops.getDropsLive().get(i) != null) {
								Drops.getDropsLive().get(i).doAction();
							}
						}
					}

					if (entities != null && !entities.isEmpty()) {
						for (int i = 0; i < entities.size(); i++) {
							if (entities.get(i) != null && !entities.get(i).isDead()) {
								if (entities.get(i) instanceof Enemy && !entities.get(i).isDead()) {
									((Enemy) entities.get(i)).doAction();
								}
							}
						}
					}
				}
				if (!musicSound.running())
					musicSound.play();
			}
		});
		timer1 = new Timer(1000 / 12, new ActionListener() {// 12 FPS
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pause)
					return;
				if (frametimer1 == 0) {// 1 SEGUNDO
					Random random = new Random();
					for (int iay = 0; iay < amountY; iay++) {
						if (random.nextInt(10) == 0) {
							Location loce = new Location(Main.Width, iay * getSizeY());
							int rEnemy = (random.nextInt(12) / 2) + 1;
							Entity rEntity = null;
							switch (rEnemy) {
							case 1:
								rEntity = new Macrofago(loce);
								break;
							case 2:
								rEntity = new Macrofago2(loce);
								break;
							case 3:
								rEntity = new Macrofago3(loce);
								break;
							case 4:
								rEntity = new Macrofago4(loce);
								break;
							case 5:
								rEntity = new Macrofago5(loce);
								break;
							case 6:
								rEntity = new Macrofago6(loce);
								break;
							}
							rEntity.setSpeed(12);
							entities.add(rEntity);
						}
					}
					frametimer1++;
				} else {
					frametimer1++;
					// FRAMETIMER1 CONTROL
					if (frametimer1 == 12)
						frametimer1 = 0;
				}

				if (frametimer2 == 0) {
					player.setBacney(player.getBacney() + 1);
					Save.setValue("bacney", player.getBacney() + "");
					frametimer2++;
				} else {
					frametimer2++;
					// FRAMETIMER2 CONTROL
					if (frametimer2 == 60)
						frametimer2 = 0;
				}

				if (frametimer3 == 0) {
					if (player.getAmmunition() < 4) {
						player.setAmmunition(player.getAmmunition() + 1);
					}
					frametimer3++;
				} else {
					frametimer3++;
					// FRAMETIMER3 CONTROL
					if (frametimer3 == ((int) Main.delayAttack.getValue()))
						frametimer3 = 0;
				}

				if (entities != null && !entities.isEmpty()) {
					for (int i = 0; i < entities.size(); i++) {
						if (entities.get(i) != null && !entities.get(i).isDead()) {
							if (entities.get(i) instanceof Enemy && !entities.get(i).isDead()) {
								entities.get(i).playSprite();
							}
						}
					}
				}
				player.playSprite();

				if (!Drops.getDropsLive().isEmpty()) {
					for (int i = 0; i < Drops.getDropsLive().size(); i++) {
						if (Drops.getDropsLive().get(i) != null) {
							Drops.getDropsLive().get(i).playSprite();
						}
					}
				}

				if (shots != null && !shots.isEmpty()) {
					for (int i = 0; i < shots.size(); i++) {
						if (!shots.get(i).isRemove())
							shots.get(i).doAction();
					}
				}
				blood.update();
				playerColision();
				if (player.isDead()) {
					close();
				}
			}
		});
		if (player.getBacney() > 0) {
			player.setBacney(player.getBacney() - 1);
		}
		this.start();

	}

	@Override
	public void paint(Graphics g) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			bs = getBufferStrategy();
		}
		g2d = bs.getDrawGraphics();
		blood.draw(g2d);

		if (!Drops.getDropsLive().isEmpty()) {
			for (int i = 0; i < Drops.getDropsLive().size(); i++) {
				if (Drops.getDropsLive().get(i) != null) {
					Drops.getDropsLive().get(i).draw(g2d);
				}
			}
		}

		for (Entity e : entities) {
			if (!e.isDead()) {
				e.draw(g2d);
			}
		}
		if (shots != null && !shots.isEmpty()) {
			for (Shot s0 : shots)
				s0.draw(g2d);
		}

		// BULLETS
		SpriteSheet sshot = BulletType.toSpriteSheet(BulletType.Normal);
		if (player.getAmmunition() != 0) {
			g2d.drawImage(sshot.getSprite(1), (int) -(sizeX * 0.78), (int) -(sizeX * 0.78), (int) (sizeX * 2.5),
					(int) (sizeX * 2.5), null);
		} else {
			g2d.drawImage(sshot.getSprite(0), (int) -(sizeX * 0.78), (int) -(sizeX * 0.78), (int) (sizeX * 2.5),
					(int) (sizeX * 2.5), null);
		}
		g2d.setColor(Menu.fore);
		g2d.setFont(new Font("TimesRoman", Font.BOLD, sizeX / 4));
		g2d.drawString("" + player.getAmmunition(), (int) (sizeX / 4 * 1.6), (int) (sizeX / 4 * 2.3));
		// BACNEY
		g2d.drawImage(bacneySprite.getSprite(0), (int) ((int) (sizeX * 0.78) * 0.9), (int) (96 * 0.15),
				(int) (sizeX * 0.78), (int) (sizeX * 0.78), null);
		g2d.setColor(Menu.fore);
		g2d.setFont(new Font("TimesRoman", Font.BOLD, sizeX / 4));
		g2d.drawString("" + player.getBacney(), (int) ((int) (sizeX / 4 * 1.6) * 2.5), (int) (sizeX / 4 * 2.3));

		bs.show();
	}

	private void keyboard() {
		int speed = (int) player.getSpeed();
		if (keys.contains(KeyEvent.VK_ESCAPE)) {
			keys.remove(KeyEvent.VK_ESCAPE);
			if (pause) {
				this.start();
			} else {
				this.close();
			}
			return;
		}
		if (!pause) {
			if ((keys.contains(KeyEvent.VK_D) || keys.contains(KeyEvent.VK_RIGHT))
					&& player.getLocation().getX() + player.getSizeX() + speed < Main.Width) {
				player.setLocation(new Location(player.getLocation().getX() + speed, player.getLocation().getY()));
				player.setSide(Side.RIGHT);
				blood.pulses.add(new Pulse(player.getLocation().getX(), (int) (Main.Height * 5.4)));
			}
			if ((keys.contains(KeyEvent.VK_A) || keys.contains(KeyEvent.VK_LEFT))
					&& player.getLocation().getX() - speed > 0) {
				player.setLocation(new Location(player.getLocation().getX() - speed, player.getLocation().getY()));
				player.setSide(Side.LEFT);
				blood.pulses.add(new Pulse(player.getLocation().getX(), (int) (Main.Height * 5.4)));
			}
			if ((keys.contains(KeyEvent.VK_W) || keys.contains(KeyEvent.VK_UP))
					&& player.getLocation().getY() - speed > 0 && player.getLocation().getY() - speed > 0) {
				player.setLocation(new Location(player.getLocation().getX(), player.getLocation().getY() - speed));
			}
			if ((keys.contains(KeyEvent.VK_S) || keys.contains(KeyEvent.VK_DOWN))
					&& player.getLocation().getY() + player.getSizeY() + speed < Main.Height) {
				player.setLocation(new Location(player.getLocation().getX(), player.getLocation().getY() + speed));
			}
			if (keys.contains(KeyEvent.VK_CONTROL) || keys.contains(KeyEvent.VK_ALT)) {
				keys.remove(KeyEvent.VK_SPACE);
				keys.remove(KeyEvent.VK_W);
				keys.remove(KeyEvent.VK_A);
				keys.remove(KeyEvent.VK_S);
				keys.remove(KeyEvent.VK_D);
				keys.remove(KeyEvent.VK_CONTROL);
				keys.remove(KeyEvent.VK_ALT);
			}
			if (keys.contains(KeyEvent.VK_SPACE) && player.getAmmunition() > 0) {
				player.setAmmunition(player.getAmmunition() - 1);
				shots.add(new Shot(player, BulletType.Normal));
				Main.sounds.shotSound();
				keys.remove(KeyEvent.VK_SPACE);
			}
		}
	}

	public static int getSizeY() {
		return sizeY;
	}

	public static void setSizeY(int sizeY) {
		Game.sizeY = sizeY;
	}

	public static int getSizeX() {
		return sizeX;
	}

	public static void setSizeX(int sizeX) {
		Game.sizeX = sizeX;
	}

	public synchronized List<Entity> getEntities() {
		ArrayList<Entity> entities2 = new ArrayList<>();
		if (!entities.isEmpty() && !pause) {
			for (Entity e : entities) {
				if (e != null && !e.isDead()) {
					entities2.add(e);
				}
			}
		}
		entities = entities2;
		return entities2;
	}

	public synchronized void setEntities(ArrayList<Entity> entities2) {
		Game.entities = entities2;
	}

	public synchronized void removeEntities(Entity entities2) {
		Game.entities.remove(entities2);
	}

	public synchronized void addEntities(Entity entity2) {
		Game.entities.add(entity2);
	}

	public List<Shot> getShots() {
		ArrayList<Shot> shots2 = new ArrayList<>();
		if (!shots.isEmpty() && !pause) {
			for (Shot s : shots) {
				if (s != null && !s.isRemove()) {
					shots2.add(s);
				}
			}
		}
		shots = shots2;
		return shots2;
	}

	public void pause() {
		this.timer0.stop();
		pause = true;
	}

	public void start() {
		if (timer1.isRunning()) {
			this.timer1.stop();
		}
		this.timer0.start();
		this.timer1.start();
		pause = false;
	}

	public void close() {
		Save.setValue("bacney", player.getBacney() + "");
		musicSound.stop();
		Main.sounds.pageSound();
		pause();
		Main.main.Menu();
		Main.JGame.dispose();
		Main.JGame = null;
	}

	public void playerColision() {
		Entity ents = player.hasCrashed();
		if (ents != null) {
			String entc = ents.getEntityType().name().toString().toUpperCase();
			switch (entc) {
			case ("MACROFAGO"):
				close();
				break;
			case ("MACROFAGO2"):

				break;
			case ("MACROFAGO3"):
				close();
				break;
			case ("MACROFAGO4"):

				break;
			case ("MACROFAGO5"):
				close();
				break;
			case ("MACROFAGO6"):
				close();
				break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		keys.remove(KeyEvent.VK_SPACE);
		keys.remove(KeyEvent.VK_W);
		keys.remove(KeyEvent.VK_A);
		keys.remove(KeyEvent.VK_S);
		keys.remove(KeyEvent.VK_D);
		keys.remove(KeyEvent.VK_CONTROL);
		keys.remove(KeyEvent.VK_ALT);
	}
}
/*
 * Tipos de Oponentes: Macrofago: Apenas passa andando reto.
 * 
 * Macrofago2: Apenas passa andando reto caso colida te deixa mais lento.
 * 
 * Macrofago3: Segue apenas se estiver muito perto.
 * 
 * Macrofago4: Desvia de voce mas caso acerte ira aumentar o grau de
 * dificuldade.
 * 
 * Macrofago5: Segue até ser morta.
 * 
 * Macrofago6: A partir de uma certa distancia ira apenas se lançar em linha
 * reta em sua direção.
 * 
 */