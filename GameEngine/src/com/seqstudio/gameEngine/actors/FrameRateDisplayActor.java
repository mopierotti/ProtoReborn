package com.seqstudio.gameEngine.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FrameRateDisplayActor extends Actor {
	private BitmapFont arial;

	public FrameRateDisplayActor() {
		arial = new BitmapFont(Gdx.files.internal("data/testFont.fnt"),Gdx.files.internal("data/testFont_0.png"),false);
	}

    public void draw (SpriteBatch batch, float parentAlpha) {
		arial.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		String text = Float.toString(1/Gdx.graphics.getDeltaTime());
		arial.draw(batch, text, 30, 30);
    }
}
