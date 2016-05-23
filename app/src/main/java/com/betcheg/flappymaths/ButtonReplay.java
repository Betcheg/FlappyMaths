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

    int width;
    int height;
    int y;
    int x;
    double speed;
    boolean animate;
    boolean endAnimation = true;
    Drawable background;

    public ButtonReplay(Context c, int w, int h) {

        this.screen_width = w;
        this.screen_height = h;
        this.width = Math.min(160, w/2);
        this.height = Math.min(80, h/4);
        this.x = screen_width/2 - width/2;
        this.y = screen_height+50;
        this.speed = (0.018)*screen_height;
        this.animate = false;
        background = c.getResources().getDrawable(R.drawable.restart);
    }

    @Override
    public void onDraw(Canvas canvas) {

        if(this.y > screen_height/2 - height/2 && animate) {
            y -= speed;
        }
        else {
            endAnimation = true;
        }

        if(this.y < screen_height){
            background.setBounds(x, y, x + width, y + height);
            background.draw(canvas);
        }


    }

    public void animate() {
        this.endAnimation=false;
        this.animate = true;
    }
    public void reset() {
        this.endAnimation=false;
        this.animate = false;
        y = screen_height+50;
    }

    public boolean isAnimated(){
        return this.animate;
    }
    public boolean isAnimationFinished(){
        return this.endAnimation;
    }
    public boolean isTouched(MotionEvent event){
        if(event.getRawX() >= x && event.getRawX() <= x+width && event.getRawY() >= y && event.getRawY() <= y +height)
            return true;
       else
            return false;
    }
}
