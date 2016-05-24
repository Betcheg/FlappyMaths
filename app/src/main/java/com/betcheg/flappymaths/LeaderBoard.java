package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

/**
 * Created by betcheg on 24/05/16.
 */
public class LeaderBoard implements Sprite {

    int BACKGROUND = 0;
    int CROSS = 1;

    int numberOfElement = 2;

    Drawable elements[] = new Drawable[numberOfElement];

    Context c;
    int screen_width;
    int screen_height;
    double speed;
    int x[] = new int[numberOfElement];
    int y[] = new int[numberOfElement];
    int height[] = new int[numberOfElement];
    int width[] = new int[numberOfElement];
    boolean animate;
    boolean endAnimation;

    public LeaderBoard(Context c, int w, int h) {
        this.c = c;
        this.screen_width = w;
        this.screen_height = h;
        this.speed = (0.025) * screen_height;
        this.animate = false;
        this.endAnimation = true;


        this.y[BACKGROUND] = h;
        this.width[BACKGROUND] = (int) (w * 0.95);
        this.height[BACKGROUND] = (int) (h * 0.95);
        this.x[BACKGROUND] = (int) (screen_width/2-width[BACKGROUND]/2);

        this.y[CROSS] = h+10;
        this.width[CROSS] = 30;
        this.height[CROSS] = 30;
        this.x[CROSS] = (int) (x[BACKGROUND] + width[BACKGROUND] - width[CROSS] - 10);

        elements[BACKGROUND] = c.getResources().getDrawable(R.drawable.backgroundlb);
        elements[CROSS] = c.getResources().getDrawable(R.drawable.cross);
    }

    @Override
    public void onDraw(Canvas canvas) {

        if (this.y[BACKGROUND] > (screen_height / 2 - height[BACKGROUND]/2) && animate) {
            y[BACKGROUND] -= speed;
            y[CROSS] -= speed;
        } else {
            endAnimation = true;
        }

        for (int i=0; i<numberOfElement; i++) {
            elements[i].setBounds(x[i], y[i], x[i] + width[i], y[i] + height[i]);
            elements[i].draw(canvas);
        }
    }

    public void start(){
        this.animate = true;
        this.endAnimation = false;
    }

    public void reset() {
        this.y[BACKGROUND] = screen_height;
        this.y[CROSS] = screen_height+10;
        this.animate = false;
        this.endAnimation = true;
    }

    public boolean isAnimationFinished(){
       return this.endAnimation;
    }

    public void setAnimated(boolean b){
        this.animate = true;
    }

    public boolean isCloseTouched(MotionEvent event){
        if(event.getRawX() >= x[CROSS] && event.getRawX() <= x[CROSS]+width[CROSS] && event.getRawY() >= y[CROSS] && event.getRawY() <= y[CROSS] +height[CROSS])
            return true;
        else
            return false;
    }

}

