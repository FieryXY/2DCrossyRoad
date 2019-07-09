package com.fieryxy;

import javax.swing.JFrame;

//The main class of the game. If you want to play the game via a jar file, there is one in this repository which you can download.
//Mind the graphics; they aren't very good.
public class ColorDash {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	
	JFrame frame;
	GamePanel gPanel;
	
	ColorDash(JFrame frame, GamePanel gPanel) {
		this.frame = frame;
		this.gPanel = gPanel;
	}
	
	
	public static void main(String[] args) {
		ColorDash colorDash = new ColorDash(new JFrame(), new GamePanel());
		colorDash.setup();
	}
	
	void setup() {
		//Sets up the basic window
		//Adds a GamePanel, which extends JPanel. The GamePanel repaints itself every frame to simulate a game.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(gPanel);
		frame.addKeyListener(gPanel);
		frame.setVisible(true);
	}
	
}
