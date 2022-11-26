package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Entity {

	private GamePanel gp;

	int damage;

	public Enemy(GamePanel gp, int power) {
		this.gp = gp;
		this.power = power;
		setDefaultValues();
	}

	public Enemy(GamePanel gp, int power, int x, int y) {
		this.gp = gp;
		this.power = power;
		setDefaultValues();
		this.x = x;
		this.y = y;
	}

	public void setDefaultValues() {

		y = 100;

		double degrees = (Math.random() * 360);
		double angle = Math.toRadians(degrees);

		if (power == 0) {
			damage = 1;
			health = 1;
			speed = 3;
			size = 20;
			color = new Color(100, 150, 50, 100);
		} else if (power == 1) {
			damage = 3;
			health = 2;
			speed = 3;
			size = 20;
			color = new Color(50, 250, 70, 100);
		} else if (power == 2) {
			damage = 4;
			health = 3;
			speed = 3;
			size = 23;
			color = new Color(110, 20, 90, 100);
		} else if (power == 3) {
			damage = 4;
			health = 5;
			speed = 2;
			size = 25;
			color = new Color(0, 200, 250, 100);
		} else if (power == 4) {
			damage = 5;
			health = 5;
			speed = 2;
			size = 26;
			color = new Color(50, 200, 200, 100);
		} else if (power == 5) {
			damage = 10;
			health = 7;
			speed = 2;
			size = 32;
			color = new Color(250, 50, 110, 100);
		} else if (power == 6) {
			damage = 13;
			health = 13;
			speed = 2;
			size = 43;
			color = new Color(0, 250, 20, 100);
		} else if (power == 7) {
			damage = 15;
			health = 20;
			speed = 2;
			size = 80;
			color = new Color(255, 255, 255, 100);
		} else if (power == 8) {
			damage = 17;
			health = 32;
			speed = 2;
			size = 120;
			color = new Color(255, 255, 0, 100);
		}

		dx = (Math.cos(angle) * speed);
		dy = (Math.sin(angle) * speed);

		x = (int) (Math.random() * gp.screenWidth - (size * 3) + (size + 10));

		r = size / 2;
	}

	public void draw(Graphics2D g2) {

		g2.setColor(color);
		g2.fillOval(x - r, y - r, r * 2, r * 2);

		g2.setStroke(new BasicStroke(3));
		g2.setColor(color.darker());
		g2.drawOval(x - r, y - r, r * 2, r * 2);
		g2.setStroke(new BasicStroke(1));

	}

	public void update() {
		if (health > 0) {

			if (x - r < 0) {
				x = 0 + r;
				dx = -dx;
			}

			if (x + r > gp.screenWidth) {
				x = gp.screenWidth - r;
				dx = -dx;
			}

			if (y - r < 0) {

				y = 0 + r;
				dy = -dy;
			}

			if (y + r > gp.screenHeight) {
				y = gp.screenHeight - r;
				dy = -dy;
			}

		} else {
			gp.player.setPower(gp.player.getPower() + (power + 1)); gp.player.setScore(gp.player.getScore() + (1 + (power * 3)));
			deleted = true;
		}

		x += dx;
		y += dy;

	}

	public void getHit(int damage) {
		health -= damage;
	}

	public int getDamage() {
		return damage;
	}
}
