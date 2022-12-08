package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Entity {

	protected GamePanel gp;
	int angle;

	public Bullet(GamePanel gp) {
		this.gp = gp;
		setDefaultValues();
	}

	public Bullet(GamePanel gp, int angle) {
		this.gp = gp;
		setDefaultValues();

		this.angle = angle;

	}

	public Bullet(GamePanel gp, int angle, int x, int y) {
		this.gp = gp;
		setDefaultValues();
		this.x = x; this.y = y;
		this.angle = angle;

	}

	public void setDefaultValues() {
		color = Color.blue;
		r = 5;
		x = gp.player.x;
		y = gp.player.y;
		speed = 10;
		collision = false;
		deleted = false;

	}

	public void update() {
		double rad = Math.toRadians(angle);
		dx = (int) (Math.cos(rad) * speed);
		dy = (int) (Math.sin(rad) * speed);

		for (int i = 0; i < GamePanel.enemies.size(); i++) {
			Enemy e = GamePanel.enemies.get(i);

			checkCollision(e);

			if (collision) {
				e.getHit(1);
				deleted = true;
			}
		}

		if (y < 0 || y > gp.screenHeight || x < 0 || x > gp.screenWidth) {
			deleted = true;
		}

		y += dy;
		x += dx;

	}

	public void draw(Graphics2D g2) {

		g2.setColor(color);
		g2.fillOval(x - r, y - r, r * 2, r * 2);
	}

}
