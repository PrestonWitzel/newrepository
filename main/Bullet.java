package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Entity {
	//Variables
	private int x;
	private int y;
	private int speed;
	private GamePanel gp;
	private boolean collision;
	private boolean deleted;
	private Color color;
	private int r;

	public Bullet(GamePanel gp) { //Constructor
		this.gp = gp;
		setDefaultValues();
	}

	public void setDefaultValues() { //Initialize variables
		color = Color.blue;
		r = 5;
		x = (gp.player.x + (gp.player.size/2) - (r/2));
		y = gp.player.y;
		speed = 1;
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
				setDeleted(true);
			}
		}
		
		
		if (y < 0) { //Bullet only moves up
			setDeleted(true);
		}
		
		y -= speed;

	}

	public void draw(Graphics2D g2) {
		//Draws this bullet
		g2.setColor(color);
		g2.fillOval(x - r, y - r, r*2, r*2);
	}
	

}
