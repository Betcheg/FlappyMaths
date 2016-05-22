package com.betcheg.flappymaths;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by root on 22/05/16.
 */
public class Bird implements Sprite {
    int x = 30;
    int height_bird;
    int width_bird;
    int current_height;
    float velocity = 1;
    Context c;
    float i;
    Drawable bird;

    protected int[] birds = new int[]{ R.drawable.img_bird_red_1, R.drawable.img_bird_red_2,
            R.drawable.img_bird_red_3 };
    protected Drawable drawable_bird;

    public Bird(Context c, int w, int l){
        this.c = c;
        current_height = 70;

        Resources res = c.getResources();
        drawable_bird = res.getDrawable(birds[(int)i]);
        width_bird = res.getDrawable(birds[(int)i]).getIntrinsicWidth();
        height_bird = res.getDrawable(birds[(int)i]).getIntrinsicHeight();
        i = 0;
    }

    @Override
    public void onDraw(Canvas canvas) {
        bird = c.getResources().getDrawable(birds[(int)i]);
        bird.setBounds(x, current_height, x + width_bird, current_height + height_bird);
        bird.draw(canvas);

        if (velocity < 0)
            current_height += (velocity*velocity);
        else
            current_height -= (velocity*velocity);

        velocity -= 0.085;

        i+=0.2; // Trick so the animation isn't too fast, it is 5 times slower than it should
        i=i%3;
    }

    public void setCurrentHeight(int h){
        this.current_height = h;
    }

    public int getX() {
        return x;
    }
    public int getWidth() {
        return width_bird;
    }
    public int getY() {
        return current_height;
    }

    public void jump() {
        this.velocity= (float) 2.3;
    }
}
