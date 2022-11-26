package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	Thread gameThread;
	KeyHandler kh = new KeyHandler();
	Player player = new Player(this, kh);
	WaveManager wm = new WaveManager(this);
	public static int screenWidth = 1500;
	public static int screenHeight = 900;

	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static ArrayList<Explosion> explosions = new ArrayList<Explosion>();

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setFocusable(true);
		this.addKeyListener(kh);

		startGame();
	}

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {
		while (gameThread != null) {

			repaint();
			update();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, screenWidth, screenHeight);

		for (Explosion ex : explosions) {
			ex.draw(g2);
		}
		for (Bullet b : bullets) {
			b.draw(g2);
		}
		if (player.getHealth() > 0) {
			player.draw(g2);
		}
		for (Enemy e : enemies) {
			e.draw(g2);
		}

		wm.draw(g2);

		g2.dispose();
	}

	public void update() {

		wm.update();
		if (player.getHealth() > 0) {
			player.update();
		}
		for (Bullet b : bullets) {
			b.update();
		}
		for (Enemy e : enemies) {
			e.update();
		}
		for (Explosion ex : explosions) {
			ex.update();
		}

		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			int x = e.getX();
			int y = e.getY();
			int size = e.getSize();
			int newPower = e.getPower() - 1;

			if (e.getDeleted() == true) {
				if (e.getPower() > 0) {

					enemies.add(new Enemy(this, newPower, x, y));
					enemies.add(new Enemy(this, newPower, x, y));
				}

				enemies.remove(i);
				explosions.add(new Explosion(this, size * 2, x, y));
				i--;
			}
		}
		bullets.removeIf(b -> b.getDeleted());
		explosions.removeIf(ex -> ex.getDeleted());
	}
}
