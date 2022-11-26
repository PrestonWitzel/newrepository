package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Explosion {

	GamePanel gp;
	int opacity;
	Color color;
	boolean deleted = false;
	int x, y;
	int size;
	int r;

	public Explosion(GamePanel gp, int size, int x, int y) { //Constructor
			this.gp = gp;
			this.size = size;
			this.x = x; this.y = y;
			setDefaultValues();
	}

	public void setDefaultValues() { // Initialize variables
		opacity = 150;
		color = new Color(255, 255, 255, opacity);
		r = size/2;
	}

	public void update() {
		opacity -= 5;
		size += 2;
		r = size/2;
		
		if(opacity <= 0) {
			color = new Color(255, 255, 255, 0);
			deleted = true;
		} else {
			color = new Color(255, 255, 255, opacity);
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fillOval(x - r, y - r, size, size);
	}
	
	public boolean getDeleted() {
		return deleted;
	}

}
