package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {
	//Variables
	long shootingTimer = System.nanoTime();
	long collisionTimer = System.nanoTime();
	long shootingDelay = 200;
	long collisionDelay = 2000;;
	Color color;
	Color outline;
	
	collisionChecker cc;
	
	public Player(GamePanel gp, KeyHandler kh) { //Constructor
		this.gp = gp;
		this.kh = kh;
		setDefaultValues();
	}
	
	public void setDefaultValues( ) { //Initialize variables
		setSize(50);
		setX((gp.screenWidth/2) - (size/2));
		setY(gp.screenHeight - 90);
		setSpeed(3);
		health = 100;
		cc = new collisionChecker(gp);
		setPower(0);
		color = Color.WHITE;
		outline = Color.WHITE;
		r = size/2;
	}
	
	public void draw(Graphics2D g2) {
		
		
		//Draw player
		g2.setColor(color);
		g2.fillOval(x - r, y - r, r*2, r*2);

		// Draw outline
		g2.setStroke(new BasicStroke(3));
		g2.setColor(outline.darker());
		g2.drawOval(x - r, y - r, r*2, r*2);
		g2.setStroke(new BasicStroke(1));
	}
	
	public void update() {
		if(health > 0) {
			
			for(int i = 0; i < GamePanel.enemies.size(); i++) {
				Enemy e = GamePanel.enemies.get(i);
				checkCollision(e); //Check for collision with enemies
			}

				
				
			if(kh.down == true && y + r/2 <= (gp.screenHeight - r*2)) {
				y += speed;
			}
			if(kh.left == true && x + r/2 >= 0) {
				x -= speed;
			}
			if(kh.right == true && x + r/2 <= (gp.screenWidth - r*2)) {
				x += speed;
			}
			if(kh.up == true && y + r/2 >= 0) {
				y -= speed;
			}
		
			//Shooting function
			if(kh.shooting) {
				long currentTime = System.nanoTime();
				long differenceTime = currentTime - shootingTimer;
				differenceTime /= 1000000;
				if(differenceTime >= shootingDelay) {
					GamePanel.bullets.add(new Bullet(gp));
					shootingTimer = System.nanoTime();
				}
			}
		
			//Collision/Health function
			if(collision == true) {
				outline = Color.red;
				long currentTime = System.nanoTime();
				long differenceTime = currentTime - collisionTimer;
				differenceTime /= 1000000;
				if(differenceTime >= collisionDelay) {
					health -= 1;
					
					collisionTimer = System.nanoTime();
				}
			}
			
			if(!collision) {
				outline = Color.white;
			}
		}
		
		if(health <= 0) {
			x = 999;
			y = 999;
		}
		
	}
	

}
