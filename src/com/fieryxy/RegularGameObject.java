package com.fieryxy;


//Determines the basic properties for all objects which are Regular GameObjects.
public abstract class RegularGameObject {
	int x;
	int y;
	
	RegularGameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
}