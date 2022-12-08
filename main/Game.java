package main;

import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("Game");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GamePanel gp = new GamePanel();

		frame.add(gp);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
