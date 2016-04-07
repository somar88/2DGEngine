package com.exlife.core;

// the game will start and go to render and then if nothing or a close is essued the game will not return to update and it will stop 

public abstract class AbstractGame {
	
	public abstract void update(GameContainer gc, float dt);
	public abstract void render(GameContainer gc, Renderer r);
	
}
