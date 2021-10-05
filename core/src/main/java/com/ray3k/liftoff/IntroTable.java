package com.ray3k.liftoff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import space.earlygrey.shapedrawer.ShapeDrawer;
import space.earlygrey.shapedrawer.scene2d.ShapeDrawerDrawable;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.ray3k.liftoff.Core.*;

public class IntroTable extends Table {
    private final static Color color = new Color(204f/255f, 0, 0, 1);
    private final static Vector2 temp = new Vector2();
    private final static Vector2 temp2 = new Vector2();
    private final static Polygon shape1 = new Polygon();
    private final static Polygon shape2 = new Polygon();
    
    public IntroTable() {
        setFillParent(true);
        
        AnimationDrawable drawable = new AnimationDrawable();
        Image image = new Image(drawable);
        add(image).grow();
        
        final Image imageGDX = new Image(skin, "intro-gdx");
        final Image imageLiftoff = new Image(skin, "intro-liftoff");
        float longest = Math.max(stage.getWidth() / 2, stage.getHeight() / 2);
        temp.set(longest, longest).setAngleDeg(225).add(stage.getWidth() / 2, stage.getHeight() / 2);
        float[] verts1 = new float[6];
        verts1[0] = temp.x; verts1[1] = temp.y;
        temp.set(longest, longest).setAngleDeg(45).add(stage.getWidth() / 2, stage.getHeight() / 2);
        verts1[2] = verts1[0]; verts1[3] = temp.y;
        verts1[4] = temp.x; verts1[5] = temp.y;
        shape1.setVertices(verts1);
        shape1.setOrigin(stage.getWidth() / 2, stage.getHeight() / 2);
        
        temp.set(longest, longest).setAngleDeg(225).add(stage.getWidth() / 2, stage.getHeight() / 2);
        float[] verts2 = new float[6];
        verts2[0] = temp.x; verts2[1] = temp.y;
        temp.set(longest, longest).setAngleDeg(45).add(stage.getWidth() / 2, stage.getHeight() / 2);
        verts2[2] = temp.x; verts2[3] = temp.y;
        verts2[4] = temp.x; verts2[5] = verts2[1];
        shape2.setVertices(verts2);
        shape2.setOrigin(stage.getWidth() / 2, stage.getHeight() / 2);
    
        addActor(imageGDX);
        temp.set(35, 0);
        temp.rotateDeg(135);
        temp.add(stage.getWidth() / 2, stage.getHeight() / 2);
        temp2.set(longest + imageGDX.getWidth() / 2, longest + imageGDX.getHeight() / 2);
        temp2.setAngleDeg(225);
        temp.add(temp2);
        imageGDX.setPosition(temp.x, temp.y, Align.center);
    
        addActor(imageLiftoff);
        temp.set(43, 0);
        temp.rotateDeg(315);
        temp.add(stage.getWidth() / 2, stage.getHeight() / 2);
        temp2.set(longest + imageLiftoff.getWidth() / 2, longest + imageLiftoff.getHeight() / 2);
        temp2.setAngleDeg(45);
        temp.add(temp2);
        imageLiftoff.setPosition(temp.x, temp.y, Align.center);
    
        temp.set(longest + imageGDX.getWidth() / 2, longest + imageGDX.getHeight() / 2);
        temp.setAngleDeg(45);
        temp.add(imageGDX.getX(Align.center), imageGDX.getY(Align.center));
        float gdxTarget1x = temp.x;
        float gdxTarget1y = temp.y;
        
        temp.set(longest + imageLiftoff.getWidth() / 2, longest + imageLiftoff.getHeight() / 2);
        temp.setAngleDeg(225);
        temp.add(imageLiftoff.getX(Align.center), imageLiftoff.getY(Align.center));
        float liftoffTarget1x = temp.x;
        float liftoffTarget1y = temp.y;
        
        temp.set(longest, longest).setAngleDeg(135);
        temp.add(gdxTarget1x, gdxTarget1y);
        float gdxTarget2x = temp.x;
        float gdxTarget2y = temp.y;
    
        temp.set(longest, longest).setAngleDeg(315);
        temp.add(liftoffTarget1x, liftoffTarget1y);
        float liftoffTarget2x = temp.x;
        float liftoffTarget2y = temp.y;
    
        addAction(sequence(delay(.5f), parallel(
                        targeting(imageGDX, moveToAligned(gdxTarget1x, gdxTarget1y, Align.center, 1f, Interpolation.pow4Out)),
                        targeting(imageLiftoff,
                                moveToAligned(liftoffTarget1x, liftoffTarget1y, Align.center, 1f, Interpolation.pow4Out))),
                parallel(new TemporalAction(1f) {
                             @Override
                             protected void update(float percent) {
                                 drawable.percent = percent;
                             }
                         }, targeting(imageGDX,
                                moveToAligned(gdxTarget2x, gdxTarget2y, Align.center, 1f, Interpolation.pow3In)),
                        targeting(imageLiftoff,
                                moveToAligned(liftoffTarget2x, liftoffTarget2y, Align.center, 1f,
                                        Interpolation.pow3In)))));
    }
    
    private static class AnimationDrawable extends ShapeDrawerDrawable {
        public float percent;
    
        public AnimationDrawable() {
            super(Core.shapeDrawer);
        }
    
        @Override
        public void drawShapes(ShapeDrawer shapeDrawer, float x, float y, float width, float height) {
            temp.set(0, 0);
            float longest = Math.max(stage.getWidth() / 2, stage.getHeight() / 2);
            temp2.set(longest, longest).setAngleDeg(135);
            temp.interpolate(temp2, percent, Interpolation.pow3In);
            shape1.setPosition(temp.x, temp.y);
    
            temp.set(0, 0);
            temp2.setAngleDeg(315);
            temp.interpolate(temp2, percent, Interpolation.pow3In);
            shape2.setPosition(temp.x, temp.y);
            
            if (MathUtils.isZero(percent)) {
                shapeDrawer.setColor(color);
                shapeDrawer.filledRectangle(x, y, width, height);
            } else {
                shapeDrawer.setColor(color);
                shapeDrawer.filledPolygon(shape1);
                shapeDrawer.filledPolygon(shape2);
            }
        }
    }
}
