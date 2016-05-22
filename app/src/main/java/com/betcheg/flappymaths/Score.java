package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by root on 22/05/16.
 */
public class Score implements Sprite {

    int score = 0;
    Paint sharedPaint;
    int screen_height;
    int screen_width;

    public Score(Context c, int w, int h) {

        this.score = 0;
        screen_height = h;
        screen_width = w;

    }

    public void onDraw(Canvas canvas) {
        sharedPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
        sharedPaint.setTextAlign(Paint.Align.CENTER);
        sharedPaint.setTextSize(40);
        sharedPaint.setColor(0xFFFFFFFF);
        sharedPaint.setShadowLayer(2, -1, -1, 0xFF000000);
        canvas.drawText(Integer.toString(score), screen_width - 50, 50,
                sharedPaint);
        sharedPaint.setShadowLayer(2,1,1, 0xFF000000);
        canvas.drawText(Integer.toString(score) , screen_width - 50 , 50,
                sharedPaint);
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int s){
        this.score = s;
    }

}
