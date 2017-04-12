package com.mygdx.game.UI_Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.games.Game;
import com.mygdx.game.entities.games.Simple2DJavaGameSingleplayer;

/**
 * Launch screen has become a valid Game screen to be drawn from MyGdxGame
 * The buttons on the screen will switch between Single and Multiplayer
 * 
 * Scene2D uses stages as the "canvas" to draw components, known as
 * "actors" like buttons and checkboxes, etc,.
 * @author adamfarmelo
 *
 */
public class Launch_Screen extends Game{

	private Stage stage;
	private Table table;
	private Skin skin;
	private TextButton singlePlay, multiPlay;

	public Launch_Screen() {
		MyGdxGame.currentGame = this;
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(new ScreenViewport());
		table = new Table();
		table.setWidth(stage.getWidth());
		table.align(Align.center | Align.top);
		
		table.setPosition(0,Gdx.graphics.getHeight());
		singlePlay = new TextButton("Singleplayer", skin, "default");
		singlePlay.setWidth(200);
		singlePlay.setHeight(50);
		singlePlay.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2+50, Align.center);
		stage.addActor(singlePlay);
		singlePlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.dispose();
				skin.dispose();
				new Simple2DJavaGameSingleplayer();
			}
		});
		multiPlay = new TextButton("Multiplayer", skin, "default");
		multiPlay.setWidth(200);
		multiPlay.setHeight(50);
		multiPlay.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2-50, Align.center);
		stage.addActor(multiPlay);
		
		Gdx.input.setInputProcessor(stage);
		// Add widgets to the table here.
	}
	
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void draw(SpriteBatch batch) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
}