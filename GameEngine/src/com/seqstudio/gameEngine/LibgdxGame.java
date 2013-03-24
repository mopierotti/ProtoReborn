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
	private int realWidth = 800;
	private int realHeight = 480;
	private Stage stage;
	
	private SpriteBatch gutterBatch;
	private Texture gutterTexture;
	
	@Override
	public void create() {
		gutterTexture = new Texture(Gdx.files.internal("data/gutterTexture.png"));
		gutterBatch = new SpriteBatch();
		
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
		drawPatternInGutters(stage);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		realWidth = width;
		realHeight = height;
        stage.setViewport(stageWidth, stageHeight, true);
        stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
        camera.setToOrtho(false, width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	private void drawPatternInGutters(Stage stage){
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		float gutterWidth = stage.getGutterWidth();
		float gutterHeight = stage.getGutterHeight();
		int textureWidth = gutterTexture.getWidth();
		int textureHeight = gutterTexture.getHeight();
		if(Math.abs(gutterWidth)>0.001){
			//draw left and right gutters
			for (int i = 0; i<gutterWidth*2;){
				for (int j = 0; j<realHeight;){
					batch.draw(gutterTexture,i,j,textureWidth,textureHeight);
					batch.draw(gutterTexture,realWidth-gutterWidth*2+i,j,textureWidth,textureHeight);
					j+=textureHeight;
				}
				i+=textureWidth;
			}
		}else{
			//draw top and bottom gutters
			for (int i = 0; i<gutterHeight*2;){
				for (int j = 0; j<realWidth;){
					batch.draw(gutterTexture,j,i,textureWidth,textureHeight);
					batch.draw(gutterTexture,j,realHeight-gutterHeight*2+i,textureWidth,textureHeight);
					j+=textureWidth;
				}
				i+=textureHeight;
			}
		}
		batch.end();
	}
}
