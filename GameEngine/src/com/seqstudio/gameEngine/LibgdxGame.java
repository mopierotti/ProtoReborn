package com.seqstudio.gameEngine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.seqstudio.gameEngine.actors.FlatBackgroundActor;
import com.seqstudio.gameEngine.actors.FrameRateDisplayActor;

public class LibgdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	
	private int stageWidth = 800;
	private int stageHeight = 480;
	private Stage stage;
	
	@Override
	public void create() {
		Actor textActor = new FrameRateDisplayActor();
		Group primaryGroup = new Group();
		primaryGroup.addActor(textActor);
		
		Actor background = new FlatBackgroundActor(1, 1, 1, 1);
		background.setBounds(0, 0, stageWidth, stageHeight);
		primaryGroup.addActorAt(0, background);
		stage = new Stage();
		stage.addActor(primaryGroup);
		
        Gdx.input.setInputProcessor(stage);
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, stageWidth, stageHeight);
		batch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		stage.dispose();

		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {	
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
        stage.setViewport(stageWidth, stageHeight, true);
        stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
