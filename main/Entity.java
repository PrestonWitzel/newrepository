package main;

import java.awt.Color;

public class Entity {
	protected GamePanel gp;
	protected KeyHandler kh;
	int x;
	int y;
	double dx, dy;
	int health;
	Color color;
	Color outline;
	int size;
	int r;
	int speed;
	boolean deleted = false;
	int xVelocity;
	int yVelocity;
	int power;
	boolean collision = false;

	public boolean getDeleted() {
		return deleted;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getR() {
		return r;
	}

	public int getHealth() {
		return health;
	}

	public Color getColor() {
		return color;
	}

	public Color getOutline() {
		return outline;
	}

	public void setOutline(Color outline) {
		this.outline = outline;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setRadius(int r) {
		this.r = r;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setDeleted(boolean isDeleted) {
		deleted = isDeleted;
	}

	public int getSize() {
		return size;
	}

	public void checkCollision(Entity e) {

		int ex = e.getX();
		int ey = e.getY();
		int er = e.getR();

		int dx = x - ex;
		int dy = y - ey;

		double dist = Math.sqrt(dx * dx + dy * dy);

		if (dist <= er + r) {
			collision = true;
		} else {
			collision = false;
		}
	}

}
