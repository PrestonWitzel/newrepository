package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean esc;
	public boolean shooting, paused;

	@Override
	public void keyTyped(KeyEvent e) {

	}	

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		handleKeys(code, true);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (!paused) {
				paused = true;
			} else {
				paused = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		handleKeys(code, false);
	}

	private void handleKeys(int code, boolean isPressed) {
		if (code == KeyEvent.VK_W) {
			up = isPressed;
		}
		if (code == KeyEvent.VK_S) {
			down = isPressed;
		}
		if (code == KeyEvent.VK_A) {
			left = isPressed;
		}
		if (code == KeyEvent.VK_D) {
			right = isPressed;
		}
		if (code == KeyEvent.VK_SPACE) {
			shooting = isPressed;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			esc = isPressed;
		}

	}

}
