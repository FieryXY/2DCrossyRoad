package com.fieryxy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Obstacle {
	int x;
	int y;
	int[] widthValues = {30, 50, 40};
	int[] heightValues = {30, 40, 30};
	int width;
	int height;
	
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;
	
	Rectangle collisionBox = new Rectangle();
	
	public enum ObstacleType {
		SOLDIER, TANK, TRUCK;
	}
	public enum Direction {
		LEFT, RIGHT;
	}
	
	Direction direction;
	ObstacleType type;
	Color obstacleColor;
	
	Obstacle(int x, int y, ObstacleType type, Direction direction, Color obstacleColor) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.obstacleColor = obstacleColor;
		this.direction = direction;
		getValues();
	}
	

	
	
	//This function determines exactly what graphic to use based on the color, type, and orientation of the obstacle.
	void getValues() {
		if(type == ObstacleType.SOLDIER) {
			width = widthValues[0];
			height = heightValues[0];
			if(obstacleColor == Color.RED) {
				if(needImage) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("SoldierRed.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
			}
			else if(obstacleColor == Color.GREEN) {
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("SoldierGreen.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}

			}
			else if(obstacleColor == Color.BLUE) {
				//Blue Soldier
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("SoldierBlue.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}

			}
			else if(obstacleColor == Color.MAGENTA) {
				//Magenta or Purple Soldier
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("SoldierMagenta.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
		}
		else if(type == ObstacleType.TANK) {
			width = widthValues[1];
			height = heightValues[1];
			if(obstacleColor == Color.RED) {
				//Red Tank
				if(direction == Direction.RIGHT) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("TankRedRight.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
				else if(direction == Direction.LEFT) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("TankRedLeft.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
			}
			else if(obstacleColor == Color.GREEN) {
				//Green Tank
				if(direction == Direction.RIGHT) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("TankGreenRight.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
				else if(direction == Direction.LEFT) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("TankGreenLeft.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
			}
			else if(obstacleColor == Color.BLUE) {
				//Blue Tank
				if(direction == Direction.RIGHT) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("TankBlueRight.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
				else if(direction == Direction.LEFT) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("TankBlueLeft.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
			}
			else if(obstacleColor == Color.MAGENTA) {
				//Magenta or Purple Tank
				if(direction == Direction.RIGHT) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("TankMagentaRight.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
				else if(direction == Direction.LEFT) {
					try {
						image = ImageIO.read(this.getClass().getResourceAsStream("TankMagentaLeft.png"));
						gotImage = true;
					}
					catch(Exception e) {
						
					}
				}
			}
		}
		else if(type == ObstacleType.TRUCK) {
			width = widthValues[2];
			height = heightValues[2];
			if(obstacleColor == Color.RED) {
				//Red Truck
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("TruckRed.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
			else if(obstacleColor == Color.GREEN) {
				//Green Truck
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("TruckGreen.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
			else if(obstacleColor == Color.BLUE) {
				//Blue Truck
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("TruckBlue.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
			else if(obstacleColor == Color.MAGENTA) {
				//Magenta or Purple Truck
				try {
					image = ImageIO.read(this.getClass().getResourceAsStream("TruckMagenta.png"));
					gotImage = true;
				}
				catch(Exception e) {
					
				}
			}
		}
		needImage = false;
	}
	
	void draw(Graphics g) {
		
		//Draws the image. If the image couldn't be loaded for some reason, it will draw a plain rectangle.
		if(gotImage) {
			g.drawImage(image, x, y, width, height, null);
		}
		else {
			g.setColor(obstacleColor);
			g.fillRect(x, y, width, height);
		}
		collisionBox.setBounds(x, y, width, height);
	
	}
	
	
	

}
