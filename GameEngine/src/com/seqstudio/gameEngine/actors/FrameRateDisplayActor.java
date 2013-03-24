package com.seqstudio.gameEngine.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FrameRateDisplayActor extends Actor {
	private BitmapFont arial;
	private int timeStepCounter = 0;

	public FrameRateDisplayActor() {
		arial = new BitmapFont(Gdx.files.internal("data/testFont.fnt"),Gdx.files.internal("data/testFont_0.png"),false);
	}
	
	@Override
	public void act (float delta) {
		super.act(delta);
		timeStepCounter++;
	}

    public void draw (SpriteBatch batch, float parentAlpha) {
		arial.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		arial.draw(batch, Float.toString(1/Gdx.graphics.getDeltaTime()), 30, 30);
		arial.draw(batch, ("steps/frame: "+timeStepCounter), 170, 30);
		timeStepCounter=0;
    }
}
