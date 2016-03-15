package com.example.vanchik.task_five;

/**
 * Created by Vanchik on 15.03.2016.
 */
import android.graphics.Point;
import android.util.FloatMath;

public class Finger {
    public int ID;
    public Point Now;
    public Point Before;
    public long wasDown;

    boolean enabled = false;
    public boolean enabledLongTouch = true;
    Point startPoint;

    public Finger(int id, int x, int y){
        wasDown = System.currentTimeMillis();
        ID = id;
        Now = Before = startPoint = new Point(x, y);
    }

    public void setNow(int x, int y){
        if(!enabled){
            enabled = true;
            Now = Before = startPoint = new Point(x, y);
        }else{
            Before = Now;
            Now = new Point(x, y);
        }
    }

    static double checkDistance(Point p1, Point p2){	// Функция вычисления расстояния между двумя точками
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
}

