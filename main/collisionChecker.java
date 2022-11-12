package main;

public class collisionChecker {
	//Variables
	boolean collision;
	int damage;
	GamePanel gp;

	public collisionChecker(GamePanel gp) { //Constructor
		this.gp = gp;
	}

	public void checkCollision() {

		collision = false;
		damage = 0;

		for (int i = 0; i < gp.enemies.size(); i++) { //Check for collision with every enemy with player
			Enemy e = gp.enemies.get(i);
			if (gp.player.x < e.x + e.size && gp.player.x + gp.player.size > e.x && gp.player.y < e.y + e.size
					&& gp.player.y + gp.player.size > e.y) {
				collision = true;
				damage = e.damage;
			}

		}

	}
}
