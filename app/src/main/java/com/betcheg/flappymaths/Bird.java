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
    int velocity;
    Context c;
    float i;

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
        Drawable bird = c.getResources().getDrawable(birds[(int)i]);
        bird.setBounds(x, current_height, x + width_bird, current_height + height_bird);
        bird.draw(canvas);

        i+=0.2; // Trick so the animation isn't too fast, it is 5 times slower than it should
        i=i%3;
    }
}
