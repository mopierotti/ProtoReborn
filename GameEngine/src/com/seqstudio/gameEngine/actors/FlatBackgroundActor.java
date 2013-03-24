package com.seqstudio.gameEngine.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FlatBackgroundActor extends Actor {
	TextureRegion region;
	Texture texture;
	float r,g,b,a;
	private ShapeRenderer renderer;

	public FlatBackgroundActor(float r, float g, float b, float a) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		renderer = new ShapeRenderer();
	}
	
    public void draw (SpriteBatch batch, float parentAlpha) {
        batch.end();

        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.translate(getX(), getY(), 0);

        renderer.setColor(r,g,b,a*parentAlpha);
        renderer.begin(ShapeType.FilledRectangle);
        renderer.filledRect(0, 0, getWidth(), getHeight());
        renderer.end();

        batch.begin();
    }
}
