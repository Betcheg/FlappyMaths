package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by root on 22/05/16.
 */
public class Pipe implements Sprite {

    Context c;
    int width;
    int p1height;
    int p2height;
    int x;
    int screen_width;
    int screen_height;
    int space = 100;
    Drawable pipe;
    Drawable pipe2;
    boolean newLevel = false;

    public Pipe(Context c, int w, int h){
        this.c = c;
        this.width = 80;
        this.p1height = h/5;
        this.p2height = h/5;
        x = w + space;
        screen_width = w;
        screen_height = h;
    }

    @Override
    public void onDraw(Canvas canvas) {
        pipe = c.getResources().getDrawable(R.drawable.stone);
        pipe2 = c.getResources().getDrawable(R.drawable.stone);
        pipe.setBounds(x,-3, x+width, p1height);
        pipe2.setBounds(x, screen_height-p2height, x + width, screen_height+3);
        pipe.draw(canvas);
        pipe2.draw(canvas);
        x -= (0.005)*screen_width;
        if(x<-width) {
            x = screen_width + space;
            newLevel = true;
        }
    }

    public int getX() {
        return x + width;
    }

    public void setSize(int p1, int p2) {
        this.p1height = p1;
        this.p2height = p2;
    }
}
