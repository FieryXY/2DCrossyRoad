package com.fieryxy;

import java.awt.Color;
import java.util.Random;

//Defines the basic values for a GameObject which changes properties based on a Color member variable.
public abstract class ColoredGameObject {
	int x;
	int y;
	Color color;
	
	ColoredGameObject(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	//Special Constructor which randomizes the color of the GameObject
	ColoredGameObject(int x, int y) {
		Random rand = new Random();
		int randInt = rand.nextInt(4);
		Color c = Color.RED;
		switch(randInt) {
		case 0:
			c = Color.RED;
			break;
		case 1:
			c = Color.BLUE;
			break;
		case 2:
			c = Color.GREEN;
			break;
		case 3:
			c = Color.MAGENTA;
			break;
		}
		this.x = x;
		this.y = y;
		this.color = c;
	}
}
