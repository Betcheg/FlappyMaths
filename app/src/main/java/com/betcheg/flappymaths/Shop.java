package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by betcheg on 24/05/16.
 */
public class Shop implements Sprite {


    int BACKGROUND = 0;
    int CROSS = 1;
    int FLAPPY_ORIGINAL = 2;
    int FLAPPY_GAY = 3;
    int FLAPPY_DARK = 4;
    int FLAPPY_REICH = 5;
    int FLAPPY_GOLD = 6;
    int LAST_FLAPPY = FLAPPY_GOLD;

    int numberOfElement = LAST_FLAPPY;

    int flappys[] = new int[] {R.drawable.img_bird_red_1, R.drawable.flappygay, R.drawable.darkflappy, R.drawable.flappyreich, R.drawable.flappygold};
    Drawable flappys_drawable[] = new Drawable[LAST_FLAPPY - FLAPPY_ORIGINAL];
    int indiceFlappy = FLAPPY_ORIGINAL;

    Drawable elements[] = new Drawable[numberOfElement+1];
    Paint sharedPaint;

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

    public Shop(Context c, int w, int h) {
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

        for(int i=FLAPPY_ORIGINAL; i<=LAST_FLAPPY; i++){
            elements[i] = c.getResources().getDrawable(flappys[i-FLAPPY_ORIGINAL]);
        }

        for(int i=0; i < 3; i++){

            for(int j=0; j < 3; j++){
                if(indiceFlappy < LAST_FLAPPY) {
                    this.y[indiceFlappy] = y[BACKGROUND] + 100 + 50 * i;
                    this.width[indiceFlappy] = c.getResources().getDrawable(flappys[indiceFlappy-FLAPPY_ORIGINAL]).getIntrinsicWidth();
                    this.height[indiceFlappy] = c.getResources().getDrawable(flappys[indiceFlappy-FLAPPY_ORIGINAL]).getIntrinsicHeight();
                    this.x[indiceFlappy] = (int) (screen_width / 2 + (j - 1) * this.width[indiceFlappy] + (j - 1) * 50 - this.width[indiceFlappy] / 2);
                    indiceFlappy++;
                }
            }

        }
    }

    @Override
    public void onDraw(Canvas canvas) {

        if (this.y[BACKGROUND] > (screen_height / 2 - height[BACKGROUND]/2) && animate) {
            for(int i = 0; i<numberOfElement; i++) {
                y[i] -= speed;
            }
        } else {
            endAnimation = true;
        }



        for (int i=0; i<numberOfElement; i++) {
            elements[i].setBounds(x[i], y[i], x[i] + width[i], y[i] + height[i]);
            elements[i].draw(canvas);

        }


        /* Draw Shop */
        sharedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sharedPaint.setTextAlign(Paint.Align.CENTER);
        sharedPaint.setTextSize(26);
        sharedPaint.setColor(0xFFFFFFFF);
        sharedPaint.setShadowLayer(2, -1, -1, 0xFF000000);
        canvas.drawText("SHOP", screen_width / 2 , y[BACKGROUND] + 50,
                sharedPaint);
        sharedPaint.setShadowLayer(2, 1, 1, 0xFF000000);
        canvas.drawText("SHOP", screen_width / 2, y[BACKGROUND] + 50,
                sharedPaint);

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

