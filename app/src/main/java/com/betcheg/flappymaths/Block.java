package com.betcheg.flappymaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
    int blockWithGoodValue;

    Paint sharedPaint;

    protected int[] drawable_numbers = new int[]{ R.drawable.zero , R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine};

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

        // Draw blocks
        drawable_blocks[0].setBounds(x, startY, x + width, startY + height);
        drawable_blocks[1].setBounds(x, startY + height, x + width, startY + 2 * height);
        if(numbers == 3) drawable_blocks[2].setBounds(x, startY + 2 * height, x + width, startY + 3 * height);


        drawable_blocks[0].draw(canvas);
        drawable_blocks[1].draw(canvas);
        if (numbers == 3)  drawable_blocks[2].draw(canvas);

            x -= (0.005)*screen_width;
        if(x<-width) {
            x = screen_width + space;
            newLevel = true;
        }

        // Draw text
        for (int i=0; i<3; i++) {
            sharedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            sharedPaint.setTextAlign(Paint.Align.CENTER);
            sharedPaint.setTextSize(30);
            sharedPaint.setColor(0xFFFFFFFF);
            sharedPaint.setShadowLayer(2, -1, -1, 0xFF000000);
            canvas.drawText(Integer.toString(values[i]), x + width / 2, startY + i * height + height / 2,
                    sharedPaint);
            sharedPaint.setShadowLayer(2, 1, 1, 0xFF000000);
            canvas.drawText(Integer.toString(values[i]), x+width/2, startY + i*height + height/2,
                    sharedPaint);
        }

    }

    public int getX() {
        return x;
    }
    public int getWidth() {
        return width;
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

    public int getHeight() {
        return this.height;
    }

    public void resetX() {
        this.x = screen_width + space;
    }

    public int getYnoCollision(){
         return startY + blockWithGoodValue * height;
    }
    public void setGoodValue(int g){
        this.goodValue=g;
        if(this.values[0] == g) blockWithGoodValue = 0;
        if(this.values[1] == g) blockWithGoodValue = 1;
        if(this.values[2] == g) blockWithGoodValue = 2;
    }
}
