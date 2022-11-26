package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class WaveManager {
	GamePanel gp;
	boolean addEnemies = false;
	long waveTimer = System.nanoTime();;
	long currentTime = System.nanoTime();
	long waveDelay = 5000;
	int wave = 0;

	public WaveManager(GamePanel gp) {
		this.gp = gp;

	}

	public void update() {
		waveTimer = System.nanoTime();
		long differenceTime = waveTimer - currentTime;
		differenceTime /= 1000000;
		if (differenceTime >= waveDelay && gp.enemies.size() == 0) {

			wave++;
			addEnemies = true;

			waveTimer = System.nanoTime();
		}

		if (addEnemies) {
			for (int i = 0; i < wave * 2; i++) {
				int power = 0;
				
				if(wave <= 9) {
					power = (int) (Math.random() * wave);
				} else if(wave > 9) {
					power = (int) (Math.random() * 9);
				}
				
				gp.enemies.add(new Enemy(gp, power));
			}
			addEnemies = false;
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.white);
		if (gp.player.health > 0) {
			g2.drawString("Health: " + gp.player.getHealth(), 10, 20);

			g2.drawString("Wave: " + wave, 10, 40);
			
			g2.drawString("Score: " + gp.player.getScore(), 10, 60);

			g2.drawString("Power: " + gp.player.power, 10, gp.screenHeight - 20);

		} else if (gp.player.health <= 0) {
			g2.drawString("You died! Score: " + gp.player.getScore(), 10, 20);
		}

	}

}