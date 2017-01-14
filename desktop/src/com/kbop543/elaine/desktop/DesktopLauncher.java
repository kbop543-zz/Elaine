package com.kbop543.elaine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kbop543.elaine.Elaine;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Elaine.WIDTH;
		config.height = Elaine.HEIGHT;
		config.title = Elaine.TITLE;
		new LwjglApplication(new Elaine(), config);
	}
}
