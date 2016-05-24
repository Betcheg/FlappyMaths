package com.betcheg.flappymaths;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.preference.PreferenceManager;

/**
 * Created by betcheg on 23/05/16.
 */
public class ButtonReplay implements Sprite{

    int BACKGROUND = 0;
    int SHOP = 1;
    int HIGHSCORE = 2;
    int REPLAY = 3;
    int MEDAL = 4;

    Context c;
    int screen_width;
    int screen_height;
    double speed;
    boolean animate;
    boolean endAnimation = true;
    int score = 0;
    Paint sharedPaint;
    int highscore;

    int width[] = new int[5];
    int height[] = new int[5];
    int y[] = new int[5];
    int x[] = new int[5];
    Drawable buttons[] = new Drawable[5];

    public ButtonReplay(Context c, int w, int h) {

        this.c = c;
        this.screen_width = w;
        this.screen_height = h;
        highscore= c.getSharedPreferences("betcheg", Context.MODE_PRIVATE).getInt("highscore",0);


        this.width[BACKGROUND] = 260;
        this.height[BACKGROUND] = width[BACKGROUND]/2;
        this.x[BACKGROUND] = screen_width/2 - width[BACKGROUND]/2;
        this.y[BACKGROUND] = screen_height+50;

        this.height[MEDAL] = height[BACKGROUND]/2;
        this.width[MEDAL] = (int) (height[MEDAL]* 0.58);
        this.x[MEDAL] = x[BACKGROUND] + width[BACKGROUND]/5;
        this.y[MEDAL] = y[BACKGROUND] + height[BACKGROUND]/2 - height[MEDAL]/2;

        this.width[REPLAY] = Math.min(160, w/2);
        this.height[REPLAY] = width[REPLAY]/2;
        this.x[REPLAY] = screen_width/2 - width[REPLAY]/2;
        this.y[REPLAY] = y[BACKGROUND] + height[BACKGROUND] +30;

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

        buttons[BACKGROUND] = c.getResources().getDrawable(R.drawable.backgroundmenu);
        buttons[REPLAY] = c.getResources().getDrawable(R.drawable.restart);
        buttons[SHOP] = c.getResources().getDrawable(R.drawable.shop);
        buttons[HIGHSCORE] = c.getResources().getDrawable(R.drawable.best);
        buttons[MEDAL] = c.getResources().getDrawable(R.drawable.bronze);
    }

    @Override
    public void onDraw(Canvas canvas) {

        if(this.y[BACKGROUND] > screen_height/3 - height[BACKGROUND]/2 && animate) {
            y[REPLAY] -= speed;
            y[BACKGROUND] -= speed;
            y[SHOP] -= speed;
            y[HIGHSCORE] -= speed;
            y[MEDAL] -= speed;
        }
        else {
            endAnimation = true;
        }

        for (int i=0; i<5; i++){

            if (this.y[i] < screen_height) {
                buttons[i].setBounds(x[i], y[i], x[i] + width[i], y[i] + height[i]);
                buttons[i].draw(canvas);
            }
        }


        sharedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sharedPaint.setTextAlign(Paint.Align.CENTER);
        sharedPaint.setTextSize(24);
        sharedPaint.setColor(0xFFFFFFFF);
        sharedPaint.setShadowLayer(2, -1, -1, 0xFF000000);
        canvas.drawText("Score: " + Integer.toString(score), screen_width / 2 + width[BACKGROUND]/4 , y[BACKGROUND] + 50,
                sharedPaint);
        canvas.drawText("Best: " + Integer.toString(highscore), screen_width / 2 + width[BACKGROUND]/4, y[BACKGROUND] + 95,
                sharedPaint);
        sharedPaint.setShadowLayer(2, 1, 1, 0xFF000000);
        canvas.drawText("Score: " + Integer.toString(score),screen_width / 2 + width[BACKGROUND]/4, y[BACKGROUND] + 50,
                sharedPaint);
        canvas.drawText("Best: "+Integer.toString(highscore) , screen_width / 2 + width[BACKGROUND]/4 , y[BACKGROUND]+95,
                sharedPaint);


    }

    public void animate() {
        this.endAnimation=false;
        this.animate = true;
    }
    public void reset() {
        this.endAnimation=false;
        this.animate = false;

        this.y[BACKGROUND] = screen_height+50;
        this.y[MEDAL] = y[BACKGROUND] + height[BACKGROUND]/2 - height[MEDAL]/2;
        this.y[REPLAY] = y[BACKGROUND] + height[BACKGROUND] +30;
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

    public boolean isLeaderBoardTouched(MotionEvent event){
        if(event.getRawX() >= x[HIGHSCORE] && event.getRawX() <= x[HIGHSCORE]+width[HIGHSCORE] && event.getRawY() >= y[HIGHSCORE] && event.getRawY() <= y[HIGHSCORE] +height[HIGHSCORE ])
            return true;
        else
            return false;
    }

    public boolean isShopTouched(MotionEvent event){
        if(event.getRawX() >= x[SHOP] && event.getRawX() <= x[SHOP]+width[SHOP] && event.getRawY() >= y[SHOP] && event.getRawY() <= y[SHOP] +height[SHOP ])
            return true;
        else
            return false;
    }


    public void setScore(int s){
        this.score = s;
        if(score>highscore) {
            Log.i("on", "change");
            c.getSharedPreferences("betcheg", Context.MODE_PRIVATE).edit().putInt("highscore", score).apply();;
            highscore=c.getSharedPreferences("betcheg", Context.MODE_PRIVATE).getInt("highscore", 0);
            Log.i("highscore vaut", Integer.toString(highscore));
        }

        if(s <= 5) buttons[MEDAL] = c.getResources().getDrawable(R.drawable.bronze);
        else if(s > 5 && s <= 15) buttons[MEDAL] = c.getResources().getDrawable(R.drawable.silver);
        else if(s > 15 && s <= 25) buttons[MEDAL] = c.getResources().getDrawable(R.drawable.gold);
        else if(s > 25 && s <= 35) buttons[MEDAL] = c.getResources().getDrawable(R.drawable.diamond);
        else if(s > 35) buttons[MEDAL] = c.getResources().getDrawable(R.drawable.master);
    }
}
