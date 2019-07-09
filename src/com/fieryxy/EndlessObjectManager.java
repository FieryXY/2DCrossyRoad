package com.fieryxy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import com.fieryxy.Street.StreetType;


//Manages the player, roads, cars, and all other objects in the game.
public class EndlessObjectManager implements ActionListener {
	
	Soldier player;
	ArrayList<Street> streetArr = new ArrayList<Street>();
	Random random = new Random();
	public boolean isScrolling = true;
	public long forwardMotion = 0;
	GamePanel gp;
	
	public int score = 0;
	
	Font menuMainFont = new Font("Arial", Font.PLAIN, 40);
	
	
	
	EndlessObjectManager(Soldier player, GamePanel gp) {
		this.player = player;
		this.gp = gp;
	}
	
	
	
	//Calls the draw method in every object in order to draw it onto the GamePanel
	void draw(Graphics g) {
		//Draw Streets
		for(Street s : streetArr) {
			s.draw(g);
		}
		//Draw Player
		player.draw(g);
		
		//Draws the score number on the top left of the screen
		g.setColor(Color.WHITE);
		g.setFont(menuMainFont);
		g.drawString(String.valueOf(score), 30, 30);
		
	}
	
	//Calls the update method to update positions etc.
	void update() {
		for(Street s : streetArr) {
			s.update();
		}
		
		//Removes streets once they are off the screen.
		for(int j = 0; j < streetArr.size(); j++) {
			if(streetArr.get(j).y > ColorDash.HEIGHT) {
				streetArr.set(j, null);
				streetArr.remove(streetArr.get(j));
				//score++;
			}
		}
		
		player.update();
		checkCollisions();
	}


	//This is called whenever the street timer goes off. This generates a new street randomly from a set of options.
	@Override
	public void actionPerformed(ActionEvent e) {
		//Chooses a random number and uses it to determine which option to use. Some streets may be double-lane, if it's StreetType is TWO_WAY.
		int decision = random.nextInt(7);
		
		if(streetArr.size() == 0) {
			streetArr.add(new Street(0,-250, 1, StreetType.ONE_WAY, this));
		}
		
		else if(decision == 0) {
			streetArr.add(new Street(0, streetArr.get(streetArr.size()-1).y-200, 1, StreetType.TWO_WAY, this));
		}
		else if(decision == 1) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200,random.nextInt(2)+1, StreetType.ONE_WAY, this));
		}
		else if(decision == 2) {
			//This option creates no street
			
			//streetArr.add(new Street(0,-100,100, this));	
			}
		else if(decision == 3) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200, 1, StreetType.ONE_WAY, this));
		}
		else if(decision == 4) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200, 1, StreetType.TWO_WAY, this));
		}
		else if(decision == 5) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200, 1, StreetType.TWO_WAY, this));
		}
		else if(decision == 6) {
			streetArr.add(new Street(0,streetArr.get(streetArr.size()-1).y-200, 1, StreetType.TWO_WAY, this));
		}
	
		
	}
	
	//Checks collisions between the player and obstacles, determining when to add points and when to end the game.
	//Every GameObject has a collisionBox, which is an invisible Rectangle used to determine collisions.
	void checkCollisions() {
		for(Street s : streetArr) {
			for(Street.LaneTraffic l : s.laneArr) {
				for(Obstacle o : l.obstacles) {
					if(player.collisionBox.intersects(o.collisionBox)) {
						if(o.obstacleColor != player.color) {
							//Ends Game
							gp.endlessToMenu();
						}
						else {
							//Scores points based on the type of obstacle hit.
							switch(o.type) {
							case SOLDIER:
								score++;
								break;
							case TRUCK:
								score += 2;
								break;
							case TANK:
								score += 3;
								break;
							default:
								score++;
								break;
							}
							
							//Sends the obstacle off the screen so that its update function, which checks if the obstacle is off the screen, can remove it.
							o.x = ColorDash.WIDTH+50;
						}
						
					}
					
				}
			}
		}
	}
}