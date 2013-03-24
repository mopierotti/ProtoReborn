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
	
	private int stageWidth = 800;
	private int stageHeight = 480;
	private int realWidth = stageWidth;
	private int realHeight = stageHeight;
	private Stage stage;
	
	private Texture gutterTexture;
	private SpriteBatch gutterBatch;
	
	@Override
	public void create() {
		gutterTexture = new Texture(Gdx.files.internal("data/gutterTexture.png"));
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Actor textActor = new FrameRateDisplayActor();
		Group primaryGroup = new Group();
		primaryGroup.addActor(textActor);
		Actor background = new FlatBackgroundActor(1, 1, 1, 1);
		background.setBounds(0, 0, stageWidth, stageHeight);
		primaryGroup.addActorAt(0, background);
		stage.addActor(primaryGroup);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, stageWidth, stageHeight);
		gutterBatch = new SpriteBatch();
	}

	@Override
	public void dispose() {
		stage.dispose();
		gutterBatch.dispose();
	}
	
	//Timestep logic variables
	private float deltaTime;
	private int stepsToTake;
	private float timeAccumulator = 0;
	private float timeStep = 0.008333333f;

	@Override
	public void render() {	
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		drawPatternInGutters(stage); //Draw gutter pattern first so it's under the stage
		
		deltaTime = Gdx.graphics.getDeltaTime();
		timeAccumulator += deltaTime;
		stepsToTake = (int)(timeAccumulator / timeStep);
		timeAccumulator = timeAccumulator % timeStep;
		for (int i = 0;i<stepsToTake;i++){
			stage.act(timeStep);
		}
		
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		//Important values for drawing gutter pattern
		realWidth = width;
		realHeight = height;
		camera.setToOrtho(false, width, height);
		
        stage.setViewport(stageWidth, stageHeight, true);
        stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	private void drawPatternInGutters(Stage stage){
		gutterBatch.setProjectionMatrix(camera.combined);
		gutterBatch.begin();
		float gutterWidth = stage.getGutterWidth();
		float gutterHeight = stage.getGutterHeight();
		int textureWidth = gutterTexture.getWidth();
		int textureHeight = gutterTexture.getHeight();
		if(Math.abs(gutterWidth)>0.001){
			//draw left and right gutters
			for (int i = 0; i<gutterWidth*2;){
				for (int j = 0; j<realHeight;){
					gutterBatch.draw(gutterTexture,i,j,textureWidth,textureHeight);
					gutterBatch.draw(gutterTexture,realWidth-gutterWidth*2+i,j,textureWidth,textureHeight);
					j+=textureHeight;
				}
				i+=textureWidth;
			}
		}else{
			//draw top and bottom gutters
			for (int i = 0; i<gutterHeight*2;){
				for (int j = 0; j<realWidth;){
					gutterBatch.draw(gutterTexture,j,i,textureWidth,textureHeight);
					gutterBatch.draw(gutterTexture,j,realHeight-gutterHeight*2+i,textureWidth,textureHeight);
					j+=textureWidth;
				}
				i+=textureHeight;
			}
		}
		gutterBatch.end();
	}
}
