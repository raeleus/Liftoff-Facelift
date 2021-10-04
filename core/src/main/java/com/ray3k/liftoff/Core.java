package com.ray3k.liftoff;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Core extends ApplicationAdapter {
    public static Stage stage;
    public static Skin skin;
    public static ShapeDrawer shapeDrawer;
    
    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("ui/skin.json"));
        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        shapeDrawer = new ShapeDrawer(stage.getBatch(), skin.getRegion("white-pixel"));
        
        IntroTable introTable = new IntroTable();
        stage.addActor(introTable);
    }
    
    @Override
    public void resize(int width, int height) {
        if (width > 0 && height > 0)
            stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        
        stage.act();
        stage.draw();
    }
    
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}