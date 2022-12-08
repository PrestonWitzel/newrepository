package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class BadBullet extends Bullet {
	public BadBullet(GamePanel gp) {
		super(gp);
	}
	
	public BadBullet(GamePanel gp, int angle) {
		super(gp, angle);
	}
	public BadBullet(GamePanel gp, int angle, int x, int y) {
		super(gp, angle, x, y);
	}
	
	public void update() {
		speed = 10;
		double rad = Math.toRadians(angle);
		dx = (int) (Math.cos(rad) * speed);
		dy = (int) (Math.sin(rad) * speed);
		y += dy;
		x += dx;
		
		checkCollision(gp.player);
		if(collision) {
			gp.player.health -= 1;
			deleted = true;
		}
		if(x < 0 || x > gp.screenWidth || y < 0 || y > gp.screenHeight) {
			deleted = true;
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillOval(x - r, y -r, r * 2, r * 2);
	}
}
