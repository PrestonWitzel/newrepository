package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Entity {
	//Variables

	private GamePanel gp;


	public Bullet(GamePanel gp) { //Constructor
		this.gp = gp;
		setDefaultValues();
	}

	public void setDefaultValues() { //Initialize variables
		color = Color.blue;
		r = 5;
		x = gp.player.x;
		y = gp.player.y;
		speed = 10;
		collision = false;
		deleted = false;
	}



	public void update() {

		//Checks collision with every enemy
		for (int i = 0; i < GamePanel.enemies.size(); i++) {
			Enemy e = GamePanel.enemies.get(i);
			

			checkCollision(e);
			
			if(collision) {
				e.getHit(1);
				deleted = true;
			}
		}
		
		
		if (y < 0) { //Bullet only moves up
			deleted = true;
		}
		
		y -= speed;

	}

	public void draw(Graphics2D g2) {
		//Draws this bullet
		g2.setColor(color);
		g2.fillOval(x - r, y - r, r*2, r*2);
	}
	

}
