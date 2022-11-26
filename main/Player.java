package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {

	long shootingTimer = System.nanoTime();
	long collisionTimer = System.nanoTime();
	long shootingDelay = 200;
	long collisionDelay = 2000;

	int rot = 0;

	public Player(GamePanel gp, KeyHandler kh) {
		this.gp = gp;
		this.kh = kh;
		setDefaultValues();
	}

	public void setDefaultValues() {
		size = 50;
		x = gp.screenWidth / 2;
		y = gp.screenHeight - 90;
		speed = 3;
		health = 100;
		power = 0;
		color = Color.WHITE;
		outline = Color.WHITE;
		r = size / 2;
	}

	public void draw(Graphics2D g2) {

		g2.setColor(color);
		g2.fillOval(x - r, y - r, r * 2, r * 2);

		g2.setStroke(new BasicStroke(3));
		g2.setColor(outline.darker());
		g2.drawOval(x - r, y - r, r * 2, r * 2);
		g2.setStroke(new BasicStroke(1));
	}

	public void update() {
		if (health > 0) {

			for (int i = 0; i < GamePanel.enemies.size(); i++) {
				Enemy e = GamePanel.enemies.get(i);
				checkCollision(e);

				if (collision == true) {

					long currentTime = System.nanoTime();
					long differenceTime = currentTime - collisionTimer;
					differenceTime /= 1000000;
					if (differenceTime >= collisionDelay) {
						health -= e.getDamage();

						collisionTimer = System.nanoTime();
					}
				}

			}

			if (kh.down == true && y + r <= gp.screenHeight) {
				y += speed;
			}
			if (kh.left == true && x - r >= 0) {
				x -= speed;
			}
			if (kh.right == true && x + r <= gp.screenWidth) {
				x += speed;
			}
			if (kh.up == true && y - r >= 0) {
				y -= speed;
			}

			if (kh.shooting) {
				long currentTime = System.nanoTime();
				long differenceTime = currentTime - shootingTimer;
				differenceTime /= 1000000;
				if (differenceTime >= shootingDelay) {
					if (power < 100) {
						GamePanel.bullets.add(new Bullet(gp, 270));
					} else if (power >= 100 && power < 250) {
						GamePanel.bullets.add(new Bullet(gp, 270));
						GamePanel.bullets.add(new Bullet(gp, 285));
						GamePanel.bullets.add(new Bullet(gp, 255));
					} else if (power >= 250 && power < 1000) {
						GamePanel.bullets.add(new Bullet(gp, 270));
						GamePanel.bullets.add(new Bullet(gp, 285));
						GamePanel.bullets.add(new Bullet(gp, 255));
						shootingDelay = 140;

					} else if (power >= 1000 && power < 2000) {

						GamePanel.bullets.add(new Bullet(gp, 270));
						GamePanel.bullets.add(new Bullet(gp, 285));
						GamePanel.bullets.add(new Bullet(gp, 255));
						for (int i = 0; i < 3; i++) {
							GamePanel.bullets.add(new Bullet(gp, rot));
							rot += 50;
						}
						shootingDelay = 120;

					} else if (power >= 2000) {

						GamePanel.bullets.add(new Bullet(gp, 270));
						GamePanel.bullets.add(new Bullet(gp, 285));
						GamePanel.bullets.add(new Bullet(gp, 255));
						for (int i = 0; i < 5; i++) {
							GamePanel.bullets.add(new Bullet(gp, rot));
							rot += 50;
						}
						shootingDelay = 90;

					}
					shootingTimer = System.nanoTime();
				}
			}

		}

		if (health <= 0) {
			GamePanel.explosions.add(new Explosion(gp, size * 2, x, y));
			
		}

	}

}
