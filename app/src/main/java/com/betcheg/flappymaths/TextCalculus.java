package com.betcheg.flappymaths;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by root on 22/05/16.
 */
public class TextCalculus implements Sprite{

    Context c;
    int width;
    int height;
    int screen_width;
    int screen_height;
    Drawable c1;
    Drawable e1;
    Drawable c2;
    boolean fini = false;
    Paint sharedPaint;

    /*
    protected int[] drawable_numbers = new int[]{ R.drawable.zero , R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine};

    protected int[] drawable_symbols = new int[]{ R.drawable.plus};

    protected int[] expression = new int[] {0,0,0}; */
    protected String[] symbols = new String[] {"+"};
    protected String expression;

    public TextCalculus (Context c, int w, int h){
        this.c = c;
        Resources res = c.getResources();
        this.width = res.getDrawable(R.drawable.three).getIntrinsicWidth();
        this.height = res.getDrawable(R.drawable.three).getIntrinsicHeight();
        screen_height = h;
        screen_width = w;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (!fini) {
          /*
            c1 = c.getResources().getDrawable(drawable_numbers[expression[0]]);
            e1 = c.getResources().getDrawable(drawable_symbols[expression[1]]);
            c2 = c.getResources().getDrawable(drawable_numbers[expression[2]]);

            c1.setBounds((int) (screen_width / 2 - 1.5 * width), screen_height / 2 - height / 2, (int) (screen_width / 2 - 0.5 * width), screen_height / 2 + height / 2);
            e1.setBounds((int) (screen_width / 2 - 0.5 * width), screen_height / 2 - height / 2, (int) (screen_width / 2 + 0.5 * width), screen_height / 2 + height / 2);
            c2.setBounds((int) (screen_width / 2 + 0.5 * width), screen_height / 2 - height / 2, (int) (screen_width / 2 + 1.5 * width), screen_height / 2 + height / 2);

            c1.draw(canvas);
            e1.draw(canvas);
            c2.draw(canvas); */

            // sharedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            sharedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            sharedPaint.setTextAlign(Paint.Align.CENTER);
            sharedPaint.setTextSize(width + 40);
            sharedPaint.setColor(0xFFFFFFFF);
            sharedPaint.setShadowLayer(2, -1, -1, 0xFF000000);
            canvas.drawText(expression, screen_width / 2, screen_height / 2,
                    sharedPaint);
            sharedPaint.setShadowLayer(2, 1, 1, 0xFF000000);
            canvas.drawText(expression, screen_width / 2, screen_height / 2,
                    sharedPaint);

        }
    }

    public int setExpression(int c1, int e1, int c2){
        expression = "";
        expression += Integer.toString(c1);
        expression += symbols[e1];
        expression += Integer.toString(c2);

        if(symbols[e1].equals("+"))
            return c1+c2;
        else
            return 0;
    }

    public void setFini(boolean b){
        this.fini = b;
    }
}
