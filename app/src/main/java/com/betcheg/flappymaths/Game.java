package com.betcheg.flappymaths;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
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
    private TextCalculus texteCalculus;
    private int random;
    private int random2;
    private int random3;
    private int result;

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
        pipe = new Pipe(c, (int) SCREEN_WIDTH , (int) SCREEN_HEIGHT);
        sprites.add(pipe);
        block = new Block(c, (int) SCREEN_WIDTH , (int) SCREEN_HEIGHT, (int) SCREEN_HEIGHT);
        sprites.add(block);
        bird = new Bird(c, (int) SCREEN_WIDTH , (int) SCREEN_HEIGHT);
        sprites.add(bird);
        texteCalculus = new TextCalculus(c, (int) SCREEN_WIDTH , (int) SCREEN_HEIGHT);
        sprites.add(texteCalculus);

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
            texteCalculus.setFini(false);

            random = (int) (Math.random()* 5);
            random2 = (int) (Math.random()* 5);
            texteCalculus.setExpression(random, "+", random2);
            result = random + random2;
            random = (int) (Math.random()* 2);
            random2 = (int) (Math.random()* 7);

            while(random2 == result){
                random2 = (int) (Math.random()* 7);
            }

            if (random%2 == 0 )
                block.setValues(result, random2, 0);
            else
                block.setValues(random2, result, 0);

            block.setGoodValue(result);

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

        if(block.getX() <= SCREEN_WIDTH * 0.80) texteCalculus.setFini(true);



        Iterator<Sprite> iterator = sprites.iterator();
        while (iterator.hasNext()) {
            Sprite sprite = iterator.next();
            sprite.onDraw(canvas);
        }


        invalidate(); // Will loop onDraw
    }

}
