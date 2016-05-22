package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.LinearLayout;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by root on 22/05/16.
 */

public class Game extends LinearLayout {


    protected double SCREEN_WIDTH = 0;
    protected double SCREEN_HEIGHT = 0;
    private LinkedList<Sprite> sprites;
    private Bird bird;
    private Background background;
    Context c;



    public Game(Context c, int h, int w){

        super(c);
        sprites = new LinkedList<Sprite>();
        this.c = (MainActivity) c;
        SCREEN_WIDTH = w;
        SCREEN_HEIGHT = h;

        background = new Background(c, (int) SCREEN_WIDTH , (int) SCREEN_HEIGHT);
        sprites.add(background);
        bird = new Bird(c, (int) SCREEN_WIDTH , (int) SCREEN_HEIGHT);
        sprites.add(bird);

        setWillNotDraw(false); // Will call onDraw();

    }


    protected void onDraw(Canvas canvas)
    {
        Iterator<Sprite> iterator = sprites.iterator();
        while (iterator.hasNext()) {
            Sprite sprite = iterator.next();
            sprite.onDraw(canvas);
        }

        invalidate(); // Will loop onDraw
    }

}
