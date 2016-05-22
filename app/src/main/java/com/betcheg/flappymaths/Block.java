package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by root on 22/05/16.
 */
public class Block implements Sprite{

    Context c;
    int width;
    int height;
    int x;
    int screen_width;
    int screen_height;
    int space;
    int verticalSpace;
    int minimumSpace;
    int startY;
    int values[] = new int[] {0,0,0};
    int goodValue;
    protected int[] drawable_numbers = new int[]{ R.drawable.zero , R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine};


    Drawable v1;
    Drawable v2;
    Drawable v3;

    int numbers = 3;
    boolean outofCamera = true;
    boolean fixedValue = false;
    boolean newLevel = true;

    protected int[] blocks = new int[]{ R.drawable.blockblue , R.drawable.blockgreen,
            R.drawable.blockorange, R.drawable.blockpurple, R.drawable.blockred, R.drawable.blockwhite,
    R.drawable.blockyellow};

    protected Drawable[] drawable_blocks;

    public Block(Context c, int w, int h, int verticalSpace){
        this.c = c;
        this.width = 80;
        this.height = verticalSpace/numbers;
        this.verticalSpace = verticalSpace;
        this.minimumSpace = h/4;
        this.space = w/4;
        x = w + space;
        screen_width = w;
        screen_height = h;

        drawable_blocks = new Drawable[]{c.getResources().getDrawable(blocks[0]),
                c.getResources().getDrawable(blocks[1]),
                c.getResources().getDrawable(blocks[2])};
    }

    @Override
    public void onDraw(Canvas canvas) {

        v1= c.getResources().getDrawable(drawable_numbers[values[0]]);
        v2= c.getResources().getDrawable(drawable_numbers[values[1]]);
        if(numbers ==3) v3= c.getResources().getDrawable(drawable_numbers[values[2]]);

        drawable_blocks[0].setBounds(x, startY, x + width, startY + height);
        drawable_blocks[1].setBounds(x, startY + height, x + width, startY + 2 * height);
        if(numbers == 3) drawable_blocks[2].setBounds(x, startY + 2*height, x + width, startY + 3*height);

        v1.setBounds(x + width / 2 - 15, startY + (height / 2) - 15, x + width / 2 + 15, startY + (height/2) + 15);
        v2.setBounds(x + width / 2 - 15, startY + (height / 2) - 15 + height, x + width / 2 + 15, startY + (height/2) + height+ 15);
        if(numbers == 3) v3.setBounds(x + width / 2 - 15, startY + (height / 2) - 15 + 2*height, x + width / 2 + 15, startY + (height/2) + 2*height+ 15);


        drawable_blocks[0].draw(canvas);
        drawable_blocks[1].draw(canvas);
        if (numbers == 3)  drawable_blocks[2].draw(canvas);
        v1.draw(canvas);
        v2.draw(canvas);
        if (numbers == 3) v3.draw(canvas);

            x -= (0.005)*screen_width;
        if(x<-width) {
            x = screen_width + space;
            newLevel = true;
        }
    }

    public int getX() {
        return x;
    }

    public int getNumbers() {
        return numbers;
    }

    public int getMinimumSpace() {
        return minimumSpace;
    }

    public boolean getNewLevel() {
        return newLevel;
    }

    public boolean getFixedValue() {
        return fixedValue;
    }

    public void setHeight(int h){
        this.height = h;
    }

    public void setStartY(int y){
        this.startY = y;
    }
    public void setNewLevel(boolean n){
        this.newLevel = n;
    }

    public void setColors(int c1, int c2, int c3){
        drawable_blocks = new Drawable[]{c.getResources().getDrawable(blocks[c1]),
                c.getResources().getDrawable(blocks[c2]),
                c.getResources().getDrawable(blocks[c3])};
    }

    public void setValues(int v1, int v2, int v3){
        this.values[0]=v1;
        this.values[1]=v2;
        this.values[2]=v3;
    }

    public void setGoodValue(int g){
        this.goodValue=g;
    }
}
