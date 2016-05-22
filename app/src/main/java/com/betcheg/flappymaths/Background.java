package com.betcheg.flappymaths;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by root on 22/05/16.
 */
public class Background implements Sprite {


    Context c;
    int width;
    int height;

    public Background(Context c, int w, int h){
        this.c = c;
        this.width = w;
        this.height = h;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Drawable background = c.getResources().getDrawable(R.drawable.bg_general_day);
        background.setBounds(0,0, width, height);
        background.draw(canvas);

    }
}
