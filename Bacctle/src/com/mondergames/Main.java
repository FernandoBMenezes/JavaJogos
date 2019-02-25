//TAREFAS
//DO: NÃO DEIXAR ESCAPAR DA TELA
//DO: BALAS LIMITADAS
//DO: TEMPO DE RECARGA DAS BALAS
//DO: MOSTRAR NUMEROS DE BALAS
//DO: MOSTRAR BACNEY GAME
//DO: INTERATIVIDADE MAIOR BOTÃO UPGRADES
//DO: GANHO DE DINHEIRO POR TEMPO
//DO: GANHO DE DINHEIRO AO MATAR
//DO: INTERATIVIDADE MAIOR COM BOTÃO JOGAR
//DO: UPGRADES
//DO: MOSTRAR BACNEY MENU
//DO: AJUSTAR TAMANHO DOS HUB POR TELA
//DO: ERRO DE VARIOS START
//DO: OPTIMIZAR DIMINUINDO A UTILIZAÇÃO DOS GETS USANDO O FOR COM INT++
//DO: CRIAR UM SISTEMA DE ARQUIVOS
//DO: CRIAR SITE BASICO NA GAMEJOLT PARA O JOGO
//DO: CRIAR UM SISTEMA DE SONS
//DO: COLOCAR EFEITOS SONOROS
//DO: ANTI CHEAT ENGINE BASICO
//DO: CRIAR SISTEMA DE CRIPTOGRAFIA DE PARAMETROS PARA NUMEROS E STRING
//DO: IMPLEMENTAR O ANTI CHEAT ENGINE
//DO: SISTEMA DE SAVE
//DO: BUSCAR NO SAVE
//DO: ERRO COM ALT OU CTRL
//DO: TER QUE PEGAR NO CHÃO O DINHEIRO

//TODO: CRIPTOGRAFIA NO SAVE
//TODO: PASSAR OS ARRAYS PARA CLASSE COMO !STATIC!
//TODO: SISTEMA DE INIMIGOS POR FASE
//TODO: ERRO DO VERMELHOR(MACROFAGO5)
//TODO: COLOCAR TRILHA SONORA
//TODO: MELHORIA NOS EFEITOS SONOROS/MUSICAS DE FUNDO
//TODO: CONFIGS DE QUALIDADE DE JOGO/TECLAS
//TODO: IMPLEMENTAR CONFIGS DE QUALIDADE DE MUSICAS
//TODO: VOLUME DAS MUSICAS/EFEITOS
//TODO: QUANDO ALTERAR TAMANHO DA TELA EM EXECUÇÃO RESOLVER

//CURRENTPATH [1.3.5.1] HIGH.MEDIUM.LOW.HOTFIX
package com.mondergames;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

import com.mondergames.Encrypts.VDouble;
import com.mondergames.Entities.EntityType;
import com.mondergames.Files.Save;
import com.mondergames.Player.Player;
import com.mondergames.Sounds.Sounds;
import com.mondergames.Windows.Game;
import com.mondergames.Windows.Menu;

public class Main {
	public static Toolkit tk = Toolkit.getDefaultToolkit();
	public static Dimension d = tk.getScreenSize();
	public static int Height;
	public static int Width;
	public static JFrame frame;
	public static Game JGame;
	public static JFrame JMenu;
	public static KeyBoard keyboard;
	public static Player player;
	public static Main main;
	public static Sounds sounds;
	public static VDouble speed;
	public static VDouble delayAttack;
	public static VDouble phase;

	public static void main(String[] args) {
		Save.loadFolders();
		main = new Main();
		sounds = new Sounds();
	}

	public Main() {
		Height = d.height;
		Width = d.width;
		delayAttack = new VDouble(Double.parseDouble(Save.getValue("delayAttack")[1]));
		speed = new VDouble(Double.parseDouble(Save.getValue("speed")[1]));
		phase = new VDouble(Double.parseDouble(Save.getValue("phase")[1]));
		player = new Player(new Location(0, 0), EntityType.Bacilo);
		player.setBacney((int) Double.parseDouble(Save.getValue("bacney")[1]));
		keyboard = new KeyBoard();
		Menu();
	}

	public void mouse() {
		Image cursorImg = null;
		cursorImg = tk.createImage(this.getClass().getClassLoader().getResource("Resources_Menu\\icon.png"));
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "cursor");
		frame.getContentPane().setCursor(cursor);
	}

	public void Game() {
		JGame = new Game();
		frame = JGame;
		// DEFAULT FOR GAME
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setTitle("Bacttle");
				frame.setSize(Width, Height);
				Thread t = Thread.currentThread();
				t.setName("Bacttle-Thread");
				frame.setLayout(null);
				frame.setDefaultCloseOperation(2);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setUndecorated(true);
				mouse();
				frame.setVisible(true);
				frame.addKeyListener(keyboard);
			}
		});
	}

	public void Default() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setTitle("Bacttle");
				frame.setSize((int) (Width / 1.5), (int) (Height / 1.5));
				Thread t = Thread.currentThread();
				t.setName("Bacttle-Thread");
				frame.setLayout(null);
				frame.setDefaultCloseOperation(2);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setUndecorated(true);
				mouse();
				frame.setVisible(true);
				frame.addKeyListener(keyboard);
			}
		});
	}

	public void Menu() {
		frame = new Menu();
		JMenu = frame;
		Default();
	}
}