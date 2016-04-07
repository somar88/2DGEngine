package com.exlife.core;

// a class that is going to hold/contain the game loop
// which will statrt the game and do the loop and thats including the update and render than go back to update

public class GameContainer implements Runnable {

	private Thread thread;
	private AbstractGame game;
	private Window window;
	private Renderer renderer;
	private Input input;

	private int width = 320, height = 240;
	private float scale = 2.0f;
	private String title = "PixEngine v1.0 by Somar Abraham";
	private double frameCap = 1.0d / 60.0d;
	private boolean isRunnung = false;

	public GameContainer(AbstractGame game) {
		this.game = game;
	}

	public void start() {
		if (isRunnung) {
			return;
		}

		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);

		// Initialize engine components
		thread = new Thread(this);
		thread.run();
	}

	public void stop() {
		if (!isRunnung) {
			return;
		}
		isRunnung = false;
	}

	public void run() {

		// We need to controll our fram rate and how often we want to update
		isRunnung = true;

		double firstTime = 0.0;
		// this will convert our time into seconds
		double lastTime = System.nanoTime() / 1000_000_000.0;
		double passedTime = 0.0;
		double unprocessedTime = 0.0;
		double frameTime = 0;
		int frames = 0;
		int fps = 0;

		boolean render;
		while (isRunnung) {
			render = true;
			firstTime = System.nanoTime() / 1000_000_000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;
			frameTime += passedTime;

			while (unprocessedTime >= frameCap) {
				game.update(this, (float) frameCap);
				input.update();
				unprocessedTime -= frameCap;
				render = true;

				if (frameTime >= 1) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}
			}
			if (render) {

				renderer.clear();
				game.render(this, renderer);
				renderer.renderSting("fps : " + fps, 0xffffffff, 5, 5);
				window.update();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		cleanUp();
	}

	private void cleanUp() {
		window.cleanUp();
	}

	// Getters and Setters
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Window getWindow() {
		return window;
	}

}
