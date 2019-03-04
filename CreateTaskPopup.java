package com.example.payto.taskquest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Created by payto on 8/24/2017.
 */

public class CreateTaskPopup extends Activity {

    Game game = new Game();

    @Override
    public void onCreate(Bundle stats) {
        super.onCreate(stats);
        setContentView(R.layout.task_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width), (int)(height));

        //Set up items that are inside of the view
        final Spinner frequency = (Spinner)findViewById(R.id.frequencySpinner);
        ArrayAdapter<CharSequence> adapter;
        final Button create = (Button)findViewById(R.id.create);
        final EditText name = (EditText)findViewById(R.id.taskName);
        final SeekBar difficulty = (SeekBar)findViewById(R.id.difficulty);
        final CheckBox remind = (CheckBox)findViewById(R.id.doRemind);

        adapter = ArrayAdapter.createFromResource(this, R.array.frequency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //frequency.setAdapter(adapter);

        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean doRemind = remind.isChecked();
                String taskName = name.getText().toString();
                String freq = String.valueOf(frequency.getSelectedItem());
                int dif = difficulty.getProgress();
                //Need an equation to calculate for how much exp and gold will be given depending on how difficult the task is
                int expReward = dif * 10;
                int goldReward = dif * 5;

                Task customTask = new Task(taskName, 0, 30, expReward, goldReward, freq);
                //Now need to add the task to the list of tasks in game
                game.addTask(customTask);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    public void addTaskToGame(Game game, Task task) {
        game.addTask(task);
    }
}
