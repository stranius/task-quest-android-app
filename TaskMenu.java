package com.example.payto.taskquest;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by payto on 9/19/2017.
 */

public class TaskMenu extends Activity {
    Game game = new Game();

    @Override
    public void onCreate(Bundle stats) {
        super.onCreate(stats);
        setContentView(R.layout.task_menu);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels - 20;
        int height = dm.heightPixels - 20;

        getWindow().setLayout((int)(width), (int)(height));

    }
}
