package cc.dreamlike.second_task;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Path;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

class OctavePath {
    private float xx[];
    private float yy[];
    OctavePath(int indentX, int indentY) {
        int max = 500;
        int a = 200;
        xx = new float[max];
        yy = new float[max];
        for (int k = 0; k < max; k++) {
            double t = k*Math.PI/250;
            xx[k] = indentX + (float)(a*Math.sin(t));
            yy[k] = indentY + (float)(a*Math.sin(t)*Math.cos(t));
        }
    }

    public float[] getXValues(int indent) {
        return shiftArr(xx, indent);
    }

    public float[] getYValues(int indent) {
        return shiftArr(yy, indent);
    }

    public static float[] shiftArr(float[] inArr,int shift)
    {
        if ((inArr == null)|| (inArr.length == 0 ) || (shift<0)) { throw new java.lang.IllegalArgumentException(); }
        while(shift>0)
        {
            float lastVar = inArr[inArr.length-1];
            for(int counter = 0;counter<inArr.length;counter++)
            {
                float curVal = inArr[counter];
                inArr[counter] = lastVar;
                lastVar = curVal;
            }
            shift--;
        }
        return inArr;
    }
}

public class Octave extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_octave);

        TextView[] textViews= {
                (TextView) findViewById(R.id.o),
                (TextView) findViewById(R.id.l2),
                (TextView) findViewById(R.id.l1),
                (TextView) findViewById(R.id.e),
                (TextView) findViewById(R.id.h)
        };

        for (int i = 0; i < textViews.length;++i) {
            TextView textView = textViews[i];
            OctavePath octavePath = new OctavePath(getApplicationContext().getResources().getDisplayMetrics().widthPixels/2 ,getApplicationContext().getResources().getDisplayMetrics().heightPixels/2);
            ObjectAnimator anim = ObjectAnimator.ofFloat(textView, "x", octavePath.getXValues(i*15));
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(textView, "y", octavePath.getYValues(i*15));
            anim.setInterpolator(new LinearInterpolator());
            anim2.setInterpolator(new LinearInterpolator());
            anim.setDuration(5000);
            anim2.setDuration(5000);
            anim.setRepeatCount(Animation.INFINITE);
            anim2.setRepeatCount(Animation.INFINITE);
            anim.start();
            anim2.start();
        }
    }

}
