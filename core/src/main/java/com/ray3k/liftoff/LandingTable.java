package com.ray3k.liftoff;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import static com.ray3k.liftoff.Core.skin;

public class LandingTable extends Table {
    
    public LandingTable() {
        setFillParent(true);
        pad(20);
    
        Image image = new Image(skin, "title");
        image.setScaling(Scaling.fit);
        add(image);
    
        row();
        Label label = new Label("A modern setup tool for libGDX Gradle projects", skin);
        add(label).spaceTop(55);
        
        row();
        Table table = new Table();
        add(table).spaceTop(25);
        
        label = new Label("Version ", skin, "subtitle");
        table.add(label);
        
        label = new Label("1.10.0.6 \"Strawberry Jam Highlighter\"", skin, "subtitle-highlight");
        table.add(label);
    
        row();
        TextButton textButton = new TextButton("CREATE NEW PROJECT", skin);
        add(textButton).spaceTop(75);
        textButton.setTransform(true);
        textButton.setOrigin(Align.center);
        textButton.addListener(new ClickListener() {
            boolean inside;
            
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if (pointer == -1) {
                    if (!inside) {
                        textButton.addAction(
                                Actions.forever(
                                        Actions.sequence(Actions.scaleTo(.95f, .95f, 1f, Interpolation.smoother),
                                                Actions.scaleTo(1f, 1f, 1f, Interpolation.smoother))));
                        textButton.getLabel().addAction(Actions.forever(
                                Actions.sequence(Actions.scaleTo(1f, 1f, 1f, Interpolation.smoother),
                                        Actions.scaleTo(.95f, 95f, 1f, Interpolation.smoother))));
                        inside = true;
                    }
                }
            }
    
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                if (pointer == -1) {
                    inside = false;
                    textButton.clearActions();
                    textButton.addAction(Actions.scaleTo(1f, 1f, .5f));
                }
            }
    
            @Override
            public void clicked(InputEvent event, float x, float y) {
                textButton.clearActions();
                textButton.addAction(Actions.sequence(Actions.moveBy(0, -4f, .1f), Actions.moveBy(0, 4f, .1f), Actions.delay(.1f), Actions.scaleTo(1f, 1f, .5f)));
            }
        });
        
        setColor(1, 1, 1, 0);
        addAction(Actions.fadeIn(1f));
    }
}
