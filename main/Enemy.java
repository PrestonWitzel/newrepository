package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Entity {
	// Variables
	private GamePanel gp;
	int x;
	int y;
	double dx;
	double dy;
	int speed;
	int xVelocity;
	int yVelocity;
	int health;
	int size;
	boolean deleted;
	int power;
	int damage;
	Color color;
	int r;

	public Enemy(GamePanel gp, int power) { // Constructor
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

	public void setDefaultValues() { // Initialize variables


		y = 100;

		double degrees = (Math.random() * 360);
		double angle = Math.toRadians(degrees);

		// Update variables based on 'power'
		if (power == 0) {
			damage = 3;
			health = 1;
			speed = 3;
			size = 20;
			color = new Color(100, 150, 50, 100);
		} else if (power == 1) {
			damage = 5;
			health = 2;
			speed = 3;
			size = 20;
			color = new Color(50, 250, 70, 100);
		} else if (power == 2) {
			damage = 6;
			health = 3;
			speed = 3;
			size = 23;
			color = new Color(110, 20, 90, 100);
		} else if (power == 3) {
			damage = 9;
			health = 5;
			speed = 2;
			size = 25;
			color = new Color(0, 200, 250, 100);
		} else if (power == 4) {
			damage = 9;
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
		
		speed = 1;

		// Randomize x, y, and angle
		dx = (Math.cos(angle) * speed);
		dy = (Math.sin(angle) * speed);

		x = (int) (Math.random() * gp.screenWidth - (size*3) + (size + 10));

		
		r = size/2;
	}

	public void draw(Graphics2D g2) {
		// Draw enemy
		g2.setColor(color);
		g2.fillOval(x - r, y - r, r * 2, r * 2);

		// Draw outline
		g2.setStroke(new BasicStroke(3));
		g2.setColor(color.darker());
		g2.drawOval(x - r, y - r, r * 2, r * 2);
		g2.setStroke(new BasicStroke(1));

	}

	public void update() {
		if (health > 0) {
			// Bouncing function that only runs when the enemy is alive

			if (x < 0) {
				x = 0;
				dx = -dx;
			}

			if((x + r / 2) > gp.screenWidth) {
				x = gp.screenWidth - r;
				dx = -dx;
			}

			if (y < 0) {
				
				y = 0;
				dy = -dy;
			}
			
			if((y + r / 2) > gp.screenHeight) {
				y = gp.screenHeight - r;
				dy = -dy;
			}

			
		} else {
			
			deleted = true;
		}
		
		x += dx;
		y += dy;

	}
	
	public void getHit(int damage) {
		health-= damage;
	}
}
