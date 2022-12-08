package main;

import java.awt.Graphics2D;

public class ShooterEnemy extends Enemy {
	long shootingTimer = System.nanoTime();
	long shootingDelay = randomDelay();

	public ShooterEnemy(GamePanel gp, int power) {
		super(gp, power);
	}

	public ShooterEnemy(GamePanel gp, int power, int x, int y) {
		super(gp, power, x, y);
	}

	@Override
	public void update() {
		super.update();

		if (gp.player.health > 0) {
			long currentTime = System.nanoTime();
			long differenceTime = currentTime - shootingTimer;
			differenceTime /= 1000000;
			if (differenceTime >= shootingDelay) {
				shoot();
				shootingDelay = randomDelay();
				shootingTimer = System.nanoTime();
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);

	}

	public long randomDelay() {
		shootingDelay = 5000;
		int rand = (int) (Math.random() * 5000);
		long newDelay = shootingDelay += rand;
		return newDelay;
	}

	public void shoot() {
		Player p = gp.player;
		int dx = (x - p.getX());
		int dy = (y - p.getY());
		//int dist = (int) Math.sqrt(dx * dx + dy * dy);
		double angle = 0;
		if (dx != 0) {
			angle = Math.atan2(dy, dx);
		}
		angle = Math.toDegrees(angle) + 180;
		gp.bullets.add(new BadBullet(gp, (int) angle, x, y));

	}

}
