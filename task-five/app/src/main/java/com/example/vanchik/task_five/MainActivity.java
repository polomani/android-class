package com.example.vanchik.task_five;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ArrayList<Finger> fingers = new ArrayList<Finger>();        // Все пальцы, находящиеся на экране

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int id = event.getPointerId(event.getActionIndex());    // Идентификатор пальца
        int action = event.getActionMasked(); // Действие
        if(action  == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN)
            fingers.add(event.getActionIndex(), new Finger(id, (int)event.getX(), (int)event.getY()));
        else if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
            fingers.remove(fingers.get(event.getActionIndex()));    // Удаляем палец, который был отпущен
        else if(action == MotionEvent.ACTION_MOVE){
            for(int n = 0; n < fingers.size(); n++) {            // Обновляем положение всех пальцев
                fingers.get(n).setNow((int) event.getX(n), (int) event.getY(n));
            }
        }
        TextView textView = (TextView)findViewById(R.id.tfield);
        if (fingers.size()==1) {
            textView.setText(fingers.get(0).Now.toString());
        } else if (fingers.size()==2) {
            textView.setText(String.valueOf(Math.round(Finger.checkDistance(fingers.get(0).Now, fingers.get(1).Now))));
        } else if (fingers.size()>0) {
            Polygon polygon = new Polygon ();
            for (Finger f:fingers)
                polygon.add(new Point(f.Now));
            textView.setText(String.valueOf(Math.abs(Math.round(polygon.area()))));
        } else {
            textView.setText("touch me");
        }
        return true;
    }

}
