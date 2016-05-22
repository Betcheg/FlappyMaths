package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
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
    private Pipe pipe;
    private Block block;
    private int random;
    private int random2;
    private int random3;
    private boolean screenPressed = false;

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
        pipe = new Pipe(c, (int) SCREEN_WIDTH , (int) SCREEN_HEIGHT);
        sprites.add(pipe);
        block = new Block(c, (int) SCREEN_WIDTH , (int) SCREEN_HEIGHT, (int) SCREEN_HEIGHT);
        sprites.add(block);

        setWillNotDraw(false); // Will call onDraw();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN && screenPressed == false)
        {
            screenPressed = true;
            bird.jump();
        }

        else if(event.getAction() == MotionEvent.ACTION_UP) {
            this.screenPressed = false;
        }

        return true;
    }


    protected void onDraw(Canvas canvas)
    {
        if (block.getNewLevel()){
            // Assigner couleur, nombre etc..
            // Assigner taille
            random = (int) (Math.random()* (SCREEN_HEIGHT*0.25));
            pipe.setSize(random, (int) (SCREEN_HEIGHT * 0.25 - random));
            block.setHeight((int) (SCREEN_HEIGHT - (SCREEN_HEIGHT * 0.25)) / block.getNumbers());
            block.setStartY(random);
            block.setNewLevel(false);
            random = (int) (Math.random()* 7);
            random2 = (int) (Math.random()* 7);
            while(random2 == random){
                random2 = (int) (Math.random()* 7);
            }
            random3 = (int) (Math.random()* 7);
            while(random3 == random || random3 == random2){
                random3 = (int) (Math.random()* 7);
            }
            block.setColors(random, random2, random3);
        }


        Iterator<Sprite> iterator = sprites.iterator();
        while (iterator.hasNext()) {
            Sprite sprite = iterator.next();
            sprite.onDraw(canvas);
        }



        invalidate(); // Will loop onDraw
    }

}
