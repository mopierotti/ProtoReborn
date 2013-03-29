package com.seqstudio.gameEngine;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "GameEngine";
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 768;
		
		new LwjglApplication(new LibgdxGame(), cfg);
	}
}
