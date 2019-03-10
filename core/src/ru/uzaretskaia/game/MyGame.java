package ru.uzaretskaia.game;

import com.badlogic.gdx.Game;
import ru.uzaretskaia.screen.MenuScreen;

public class MyGame extends Game {
	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
}
