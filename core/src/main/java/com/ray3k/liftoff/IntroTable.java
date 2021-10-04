package com.ray3k.liftoff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import space.earlygrey.shapedrawer.ShapeDrawer;
import space.earlygrey.shapedrawer.scene2d.ShapeDrawerDrawable;

import static com.ray3k.liftoff.Core.*;

public class IntroTable extends Table {
    public IntroTable() {
        setFillParent(true);
        
        AnimationDrawable drawable = new AnimationDrawable();
        Image image = new Image(drawable);
        add(image).grow();
    
        addAction(Actions.sequence(new TemporalAction(5f) {
            @Override
            protected void update(float percent) {
                drawable.percent = percent;
            }
        }));
    }
    
    private static class AnimationDrawable extends ShapeDrawerDrawable {
        public float percent;
    
        public AnimationDrawable() {
            super(Core.shapeDrawer);
        }
    
        @Override
        public void drawShapes(ShapeDrawer shapeDrawer, float x, float y, float width, float height) {
            shapeDrawer.setColor(Color.RED);
            shapeDrawer.filledRectangle(x, y, width, height);
        }
    }
}
