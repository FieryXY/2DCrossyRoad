package com.fieryxy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	//Timers. There is one to draw every frame.
	Timer frameDraw;
	Timer roadSpawn;
	
	//The States
	public final int MENU = 0;
	public final int ENDLESS = 1;
	public final int TUTORIAL = 2;
	public final int SHOP = 3;
	
	//Object Managers. The member variable eom represents the EndlessObjectManager which creates, manages, and destroys GameObjects.
	EndlessObjectManager eom;
	
	//Color Integers
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int MAGENTA = 3;
	
	public int currentState = MENU;
	
	
	int highScore = 0;
	int lastScore = 0;
	
	Font menuMainFont = new Font("Arial", Font.PLAIN, 20);
	
	GamePanel() {
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState == MENU) {
			drawMenuState(g);
		}
		else if(currentState == ENDLESS) {
			drawEndlessState(g);
		}
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ColorDash.WIDTH, ColorDash.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(menuMainFont);
		g.drawString("Press E To Play and G for Instructions", 300, 100);
		g.setColor(Color.GRAY);
		g.drawString("High Score: "+highScore, 300, 300);
		g.drawString("Last Score: "+lastScore, 300, 400);
	}
	void updateMenuState() {
		
	}
	void drawEndlessState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ColorDash.WIDTH, ColorDash.HEIGHT);
		eom.draw(g);
	}
	void updateEndlessState() {
		eom.update();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(currentState == MENU) {
			if(e.getKeyCode() == KeyEvent.VK_E) {
				System.out.println("hi");
				currentState = ENDLESS;
				 eom = new EndlessObjectManager(new Soldier(ColorDash.WIDTH/2-30, ColorDash.HEIGHT-120), this);
				 startEndless();
			}
			else if(e.getKeyCode() == KeyEvent.VK_G) {
				JOptionPane.showMessageDialog(null, "Use the arrow keys to move. You can click space to stop your character. Touch vehicles of "
						+ "similar color to get points and avoid other colors.");
			}
		}
		else if(currentState == ENDLESS) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				eom.player.left();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				eom.player.right();
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				eom.isScrolling = !(eom.isScrolling);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentState == MENU) {
			updateMenuState();
		}
		else if(currentState == ENDLESS) {
			updateEndlessState();
		}
		this.repaint();
	}

	void startEndless() {
		 roadSpawn = new Timer(1000, eom);
		roadSpawn.start();
	}
	void endlessToMenu() {
		if(eom.score > highScore) {
			highScore = eom.score;
		}
		lastScore = eom.score;
		currentState = MENU;
		eom = null;
	}
	
}
