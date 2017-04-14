package io.github.ianawp.multishrink.common;

/**
 * Created by IanAWP on 14/04/2017.
 */

public class Resolution {

    final int MIN=120;
    private int width;
    private int height;

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Resolution(int width, int height){
        width = width>MIN?width:MIN;
        height = width>MIN?height:MIN;
        this.width=width;
        this.height=height;
    }
}
