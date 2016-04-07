package test;

import java.awt.event.KeyEvent;

import com.exlife.core.AbstractGame;
import com.exlife.core.GameContainer;
import com.exlife.core.Input;
import com.exlife.core.Renderer;
import com.exlife.core.gfx.Image;

public class Game extends AbstractGame {

	private Image image = new Image("/test.png");

	public static void main(String[] args) {

		GameContainer gc = new GameContainer(new Game());
		gc.setWidth(340);
		gc.setHeight(340 / 4 * 3);
		gc.setScale(3);
		gc.start();
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if (Input.isKeyReleased(KeyEvent.VK_A)) {
			System.out.println("A");
		}

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.renderImage(image, 50, 50);
	}

}
