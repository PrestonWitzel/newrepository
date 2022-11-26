package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	// Variables
	Thread gameThread;
	KeyHandler kh = new KeyHandler();
	Player player = new Player(this, kh);
	long currentTime;
	long waveTimer;
	long waveDelay = 5000;
	public static int screenWidth = 700;
	public static int screenHeight = 500;
	int wave = 0;
	int waves = 7;
	boolean addEnemies;

	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>(); // Bullets
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>(); // Enemies
	public static ArrayList<Explosion> explosions = new ArrayList<Explosion>(); // Explosions

	public GamePanel() { // Constructor
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setFocusable(true);
		this.addKeyListener(kh);

		startGame(); // Initializes gameThread
	}

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
		currentTime = System.nanoTime();

		// while loop in run starts
	}

	@Override
	public void run() {
		while (gameThread != null) { // Game loop

			repaint();
			update();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, screenWidth, screenHeight);

		// Draw objects
		
		for (Explosion ex : explosions) {
			ex.draw(g2);
		}
		for (Bullet b : bullets) {
			b.draw(g2);
		}
		player.draw(g2);
		for (Enemy e : enemies) {
			e.draw(g2);
		}

		//Game status
		g2.setColor(Color.white);
		if (player.health > 0 && wave <= waves && enemies.size() > 0) {
			g2.drawString("Health: " + player.getHealth(), 10, 20);

			g2.drawString("Wave: " + wave, 10, 40);

		} else if (player.health > 0 && wave == waves && enemies.size() == 0) {
			g2.drawString("You won!", 10, 20);
		} else if (player.health <= 0) {
			g2.drawString("You died", 10, 20);
		}

		g2.dispose();
	}

	public void update() {

		// Wave System
		waveTimer = System.nanoTime();
		long differenceTime = waveTimer - currentTime;
		differenceTime /= 1000000;
		if (differenceTime >= waveDelay && enemies.size() == 0) {
			if (wave != waves) {
				wave++;
				addEnemies = true;
			}

			waveTimer = System.nanoTime();
		}

		if (addEnemies) {
			if (wave == 1) {
				for (int i = 0; i < 2; i++) {
					enemies.add(new Enemy(this, 0));
				}
			}

			if (wave == 2) {
				for (int i = 0; i < 2; i++) {
					int power = (int) (Math.random() * 2);
					enemies.add(new Enemy(this, power));

				}
			}

			if (wave == 3) {
				for (int i = 0; i < 3; i++) {
					int power = (int) (Math.random() * 3);
					enemies.add(new Enemy(this, power));

				}
			}

			if (wave == 4) {
				for (int i = 0; i < 4; i++) {
					int power = (int) (Math.random() * 6);
					enemies.add(new Enemy(this, power));

				}
			}

			if (wave == 5) {
				for (int i = 0; i < 5; i++) {
					int power = (int) (Math.random() * 7);
					enemies.add(new Enemy(this, power));

				}
			}

			if (wave == 6) {
				for (int i = 0; i < 5; i++) {
					int power = (int) (Math.random() * 7);
					enemies.add(new Enemy(this, power));

				}
				enemies.add(new Enemy(this, 7));
			}
			
			if (wave == 7) {
				
				enemies.add(new Enemy(this, 8));
			}

			addEnemies = false;
		}

		// Update objects
		player.update();
		for (Bullet b : bullets) {
			b.update();
		}
		for (Enemy e : enemies) {
			e.update();
		}
		for (Explosion ex : explosions) {
			ex.update();
		}

		// Check enemies and bullets for removal
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			int x = e.getX();
			int y = e.getY();
			int size = e.getSize();
			int newPower = e.getPower() - 1;

			if (e.getDeleted() == true) {
				if(e.getPower() > 0) {

					enemies.add(new Enemy(this, newPower, x, y));
					enemies.add(new Enemy(this, newPower, x, y));
				}
		
				enemies.remove(i); explosions.add(new Explosion(this, size * 2, x, y));
				i--;
			}
		}
		bullets.removeIf(b -> b.getDeleted());
		explosions.removeIf(ex -> ex.getDeleted());
	}
}
