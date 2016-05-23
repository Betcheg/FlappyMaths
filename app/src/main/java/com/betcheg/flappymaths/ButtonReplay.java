package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;


/**
 * Created by betcheg on 23/05/16.
 */
public class ButtonReplay implements Sprite{

    int REPLAY = 0;
    int SHOP = 1;
    int HIGHSCORE = 2;

    Context c;
    int screen_width;
    int screen_height;
    double speed;
    boolean animate;
    boolean endAnimation = true;

    int width[] = new int[3];
    int height[] = new int[3];
    int y[] = new int[3];
    int x[] = new int[3];
    Drawable buttons[] = new Drawable[3];

    public ButtonReplay(Context c, int w, int h) {

        this.screen_width = w;
        this.screen_height = h;

        this.width[REPLAY] = Math.min(160, w/2);
        this.height[REPLAY] = width[REPLAY]/2;
        this.x[REPLAY] = screen_width/2 - width[REPLAY]/2;
        this.y[REPLAY] = screen_height+50;

        this.width[SHOP] = Math.min(120, (int) (w/2.5));
        this.height[SHOP] = width[SHOP]/2;
        this.x[SHOP] = screen_width/2 - width[SHOP] -10;
        this.y[SHOP] = y[REPLAY] + height[REPLAY] +20;

        this.width[HIGHSCORE] = Math.min(120, (int) (w/2.5));
        this.height[HIGHSCORE] = width[HIGHSCORE]/2;
        this.x[HIGHSCORE] = screen_width/2 +10;
        this.y[HIGHSCORE] = y[REPLAY] + height[REPLAY] +20;

        this.speed = (0.018)*screen_height;
        this.animate = false;
        buttons[REPLAY] = c.getResources().getDrawable(R.drawable.restart);
        buttons[SHOP] = c.getResources().getDrawable(R.drawable.shop);
        buttons[HIGHSCORE] = c.getResources().getDrawable(R.drawable.best);
    }

    @Override
    public void onDraw(Canvas canvas) {

        if(this.y[REPLAY] > screen_height/2 - height[REPLAY]/2 && animate) {
            y[REPLAY] -= speed;
            y[SHOP] -= speed;
            y[HIGHSCORE] -= speed;
        }
        else {
            endAnimation = true;
        }

        for (int i=0; i<3; i++){

            if (this.y[i] < screen_height) {
                buttons[i].setBounds(x[i], y[i], x[i] + width[i], y[i] + height[i]);
                buttons[i].draw(canvas);
            }
        }


    }

    public void animate() {
        this.endAnimation=false;
        this.animate = true;
    }
    public void reset() {
        this.endAnimation=false;
        this.animate = false;
        y[REPLAY] = screen_height+50;
        this.y[SHOP] = y[REPLAY] + height[REPLAY] +20;
        this.y[HIGHSCORE] = y[REPLAY] + height[REPLAY] +20;
    }

    public boolean isAnimated(){
        return this.animate;
    }
    public boolean isAnimationFinished(){
        return this.endAnimation;
    }
    public boolean isReplayTouched(MotionEvent event){
        if(event.getRawX() >= x[REPLAY] && event.getRawX() <= x[REPLAY]+width[REPLAY] && event.getRawY() >= y[REPLAY] && event.getRawY() <= y[REPLAY] +height[REPLAY])
            return true;
        else
            return false;
    }
}
