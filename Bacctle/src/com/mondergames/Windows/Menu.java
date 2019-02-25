package com.mondergames.Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.mondergames.KeyBoard;
import com.mondergames.Main;
import com.mondergames.Files.Save;
import com.mondergames.Player.Player;
import com.mondergames.Utilities.Collider;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Set<Integer> keys = KeyBoard.keys;
	private static Timer timer;
	private static int Height = (int) (Main.Height / 1.5);
	private static int Width = (int) (Main.Width / 1.5);
	public static Color back = new Color(145, 60, 100);
	public static Color fore = new Color(205, 85, 155);
	private JLabel imgPlay;
	private JLabel imgSpeed;
	private JLabel imgAttack;
	private JLabel imgBacney;
	private String deta = "<HTML><CENTER>PASSE O MOUSE ACIMA PARA SABER MAIS!</CENTER></HTML>";
	private JLabel jtf;
	private BufferedImage speedImg = null;
	private BufferedImage attackImg = null;
	private BufferedImage playImg = null;
	private BufferedImage bacneyImg = null;
	private int btnSize = (Width / 4);
	private Player player = Main.player;
	private JLabel txtBacney;

	public Menu() {
		this.setLayout(null);
		this.setBackground(back);
		JPanel jBack = new JPanel(null);
		jBack.setBackground(back);
		jBack.setBounds(0, 0, Width, Height);

		try {
			playImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\play_0.png")), btnSize, btnSize / 4);
			speedImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\speed_0.png")), btnSize / 2, btnSize / 2);
			attackImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\attack_0.png")), btnSize / 2, btnSize / 2);
			bacneyImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\bacney.gif")), btnSize / 3, btnSize / 3);
		} catch (IOException e) {
			e.printStackTrace();
		}

		imgPlay = new JLabel();
		imgPlay.setIcon(new ImageIcon(playImg));
		imgPlay.setBounds(Width / 2 - (btnSize / 2), (btnSize / 10), btnSize, btnSize / 2);

		imgBacney = new JLabel();
		imgBacney.setIcon(new ImageIcon(bacneyImg));
		imgBacney.setBounds((Width - btnSize / 3), 0, btnSize / 3, btnSize / 3);

		txtBacney = new JLabel();
		String aBacney = "<HTML><CENTER><H1> " + player.getBacney() + " <H1></CENTER></HTML>";
		txtBacney.setText(aBacney);
		txtBacney.setBorder(null);
		txtBacney.setFocusable(false);
		txtBacney.setBackground(new Color(0, 0, 0, 0));
		txtBacney.setOpaque(true);
		txtBacney.setForeground(fore);
		txtBacney.setBounds((int) ((Width - btnSize / 3) * 1.03), 0, btnSize / 3, btnSize / 3);
		this.add(txtBacney);

		JPanel jUpgrades = new JPanel();
		jUpgrades.setBackground(back);
		jUpgrades.setBounds(0, (int) (btnSize * 1.2), Width, btnSize - (btnSize / 3));
		jtf = new JLabel(deta);
		jtf.setForeground(fore);
		jtf.setBackground(back);
		jtf.setFont(new Font("TimesRoman", Font.BOLD, btnSize / 12));
		jtf.setFocusable(false);
		jUpgrades.add(jtf);

		JPanel selection = new JPanel();
		selection.setVisible(true);
		selection.setBackground(back);
		selection.setBounds(0, (int) (btnSize * 0.6), Width, (int) (btnSize * 0.6));

		// SPEED
		imgSpeed = new JLabel();
		imgSpeed.setIcon(new ImageIcon(speedImg));
		imgSpeed.setBounds(0, 0, btnSize / 2, btnSize / 2);
		imgSpeed.setPreferredSize(new Dimension(btnSize / 2, btnSize / 2));
		// ATTACK
		imgAttack = new JLabel();
		imgAttack.setIcon(new ImageIcon(attackImg));
		imgAttack.setBounds(0, 0, btnSize / 2, btnSize / 2);
		imgAttack.setPreferredSize(new Dimension(btnSize / 2, btnSize / 2));

		this.add(imgPlay);
		this.add(imgBacney);
		this.add(selection);
		selection.add(imgSpeed);
		selection.add(imgAttack);
		this.add(jUpgrades);
		this.add(jBack);
		buttons();
		timer = new Timer(1000 / 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyboard();
				repaint();
			}
		});
		timer.start();
	}

	private void buttons() {
		imgPlay.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Main.sounds.entredSound();
				try {
					playImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\play_0.png")), btnSize, btnSize / 4);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgPlay.setIcon(new ImageIcon(playImg));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Main.sounds.entredSound();
				try {
					playImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\play_1.png")), btnSize, btnSize / 4);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgPlay.setIcon(new ImageIcon(playImg));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Main.sounds.pageSound();
				startGame();
			}
		});

		imgSpeed.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Main.sounds.entredSound();
				try {
					speedImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\speed_0.png")), btnSize / 2,
							btnSize / 2);
					imgSpeed.setIcon(new ImageIcon(speedImg));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				deta = "<HTML><CENTER>PASSE O MOUSE ACIMA PARA SABER MAIS!</CENTER></HTML>";
				jtf.setText(deta);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Main.sounds.entredSound();
				try {
					speedImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\speed_1.png")), btnSize / 2,
							btnSize / 2);
					imgSpeed.setIcon(new ImageIcon(speedImg));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				int vSpeed = 20 + (int) (1000 * Main.speed.getValue());
				deta = "<HTML><BR><CENTER>Com esta evolução, Sem duvidas a Bacteria estará mais rapida doque nunca!</CENTER><BR><DIV align=\"right\">Valor da evolução "
						+ vSpeed + " Bacney</DIV></HTML>";
				jtf.setText(deta);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Main.sounds.pageSound();
				// UPGRADE
				int vSpeed = 20 + (int) (1000 * Main.speed.getValue());
				if ((player.getBacney() >= vSpeed) && (Main.speed.getValue() <= 0.05)) {
					Main.speed.setValue(Main.speed.getValue() + 0.005);
					Save.setValue("speed", (Main.speed.getValue() + 0.005)+"");
					player.setBacney(player.getBacney() - vSpeed);
					Save.setValue("bacney", player.getBacney()+"");
					deta = "<HTML><BR><CENTER>Upgrade de Speed efetuado!</DIV></HTML>";
					jtf.setText(deta);
					String aBacney = "<HTML><CENTER><H1> " + player.getBacney() + " <H1></CENTER></HTML>";
					txtBacney.setText(aBacney);
				} else {
					if (Main.speed.getValue() >= 0.05) {
						deta = "<HTML><BR><CENTER>Ja fez o maximo de Upgrade!</DIV></HTML>";
						jtf.setText(deta);
					} else {
						deta = "<HTML><BR><CENTER>Sem Bacney necessario para isso!</DIV></HTML>";
						jtf.setText(deta);
					}
				}
			}
		});

		imgAttack.addMouseListener(new MouseListener() {// 2
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Main.sounds.entredSound();
				try {
					attackImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\attack_0.png")), btnSize / 2,
							btnSize / 2);
					imgAttack.setIcon(new ImageIcon(attackImg));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				deta = "<HTML><CENTER>PASSE O MOUSE ACIMA PARA SABER MAIS!</CENTER></HTML>";
				jtf.setText(deta);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Main.sounds.entredSound();
				try {
					attackImg = Collider.resize(ImageIO.read(this.getClass().getClassLoader().getResource("Resources_Menu\\attack_1.png")), btnSize / 2,
							btnSize / 2);
					imgAttack.setIcon(new ImageIcon(attackImg));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				int vAttack = (int) ((100 - Main.delayAttack.getValue()) * 1.25);
				deta = "<HTML><BR><CENTER>Com esta evolução, Sem duvidas a Bacteria estará causando mais dano doque nunca!</CENTER><BR><DIV align=\"right\">Valor da evolução "
						+ vAttack + " Bacney</DIV></HTML>";
				jtf.setText(deta);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Main.sounds.pageSound();
				// UPGRADE
				int vAttack = (int) ((100 - Main.delayAttack.getValue()) * 1.25);
				if (player.getBacney() >= vAttack && Main.delayAttack.getValue() > 8) {
					Main.delayAttack.setValue(Main.delayAttack.getValue() * 0.98);
					Save.setValue("delayAttack", (Main.delayAttack.getValue() * 0.98)+"");
					player.setBacney(player.getBacney() - vAttack);
					Save.setValue("bacney", player.getBacney()+"");
					deta = "<HTML><BR><CENTER>Upgrade de Attack efetuado!</DIV></HTML>";
					jtf.setText(deta);
					String aBacney = "<HTML><CENTER><H1> " + player.getBacney() + " <H1></CENTER></HTML>";
					txtBacney.setText(aBacney);
				} else {
					if (Main.delayAttack.getValue() <= 8) {
						deta = "<HTML><BR><CENTER>Ja fez o maximo de Upgrade!</DIV></HTML>";
						jtf.setText(deta);
					} else
						deta = "<HTML><BR><CENTER>Sem Bacney necessario para isso!</DIV></HTML>";
					jtf.setText(deta);
				}
			}
		});
	}

	private static void keyboard() {
		if (keys.contains(KeyEvent.VK_ENTER)) {
			keys.remove(KeyEvent.VK_ENTER);
			timer.stop();
			startGame();
		}
		if (keys.contains(KeyEvent.VK_ESCAPE)) {
			keys.remove(KeyEvent.VK_ESCAPE);
			System.exit(2);
		}
	}

	private static void startGame() {
		Main.main.Game();
		Main.JMenu.dispose();
		Main.JMenu = null;
	}
}