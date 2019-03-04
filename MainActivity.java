package com.example.payto.taskquest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater;

    //Basic variables that are needed for the player
    int[] expReq = {100, 500, 1000, 1750, 2500, 5000, 10000, 13500, 17500, 25000};

    int gold, level, exp, energy;
    String playerClass, name;

    private Game game = new Game();

    CreateTaskPopup newTaskPopup;

    final int CLASS_SELECT_CODE = 1;
    final int TASK_CREATED_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        name = "Payton Fisher"; //Temp for testing purposes

        final ImageButton addTask = (ImageButton)findViewById(R.id.addTask);
        final RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        final ScrollView tasks = (ScrollView)findViewById(R.id.taskScroller);
        final ProgressBar expBar = (ProgressBar)findViewById(R.id.levelProgress);
        final TextView expText = (TextView)findViewById(R.id.exp);
        final TextView nameText = (TextView)findViewById(R.id.title);

        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        //Check to see if this is the players first time playing the game
        boolean firstTime = sharedPref.getBoolean("FirstTime", true);
        if(firstTime) {
            //We will set the content view to a view of the class selection and game walkthrough
            //startActivityForResult(new Intent(MainActivity.this, SelectClass.class), CLASS_SELECT_CODE);
        }else{
             //Set content view to homeScreen and load in the string of the players name and class and such

        }
        exp = sharedPref.getInt("Exp", 0);
        level = sharedPref.getInt("Level", 1);
        name = sharedPref.getString("Name", "No Name");

        expText.setText(exp + " / " + expReq[level - 1]);
        expBar.setProgress(exp/expReq[level-1]);
        nameText.setText(name);

        addTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get the layout inflater
                //builder.setView(inflater.inflate(R.layout.task_popup, null));

                startActivityForResult(new Intent(MainActivity.this, CreateTaskPopup.class), TASK_CREATED_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TASK_CREATED_CODE) {
            if (resultCode == RESULT_OK) {
               setUpTasksList();
            }
        }
        if(requestCode == CLASS_SELECT_CODE) {
            if (resultCode == RESULT_OK) {
                playerClass = data.getStringExtra("Class");
            }
        }
    }


    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //Add in any data that needs added and save it.
        editor.putInt("Exp", exp);
        editor.putInt("Level", level);
        editor.putString("Name", name);
        editor.apply();
    }

    public void setUpTasksList() {
        LinearLayout tasksGroup = (LinearLayout)findViewById(R.id.tasksHolder);

        if(tasksGroup.getChildCount() > 0) {
            tasksGroup.removeAllViews();
        }

        for(int i = 0; i < game.tasks.size(); i++) {
            Task temp = game.tasks.get(i);
            //Button newTask = new Button(this);

            RelativeLayout newTask = (RelativeLayout) inflater.inflate(R.layout.task_selection, null);
            TextView title = (TextView)newTask.findViewById(R.id.taskTitle);
            title.setText(temp.name);
            TextView info1 = (TextView)newTask.findViewById(R.id.Info1);
            info1.setText(temp.getDuration());
            TextView info2 = (TextView)newTask.findViewById(R.id.Info2);
            info2.setText(temp.frequency + " Activity");
            TextView info3 = (TextView)newTask.findViewById(R.id.Info3);
            info3.setText(temp.points + " Points");
            tasksGroup.addView(newTask);
            newTask.bringToFront();

            ImageButton task = (ImageButton)newTask.findViewById(R.id.box);

            task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, TaskMenu.class));
                }
            });

            //newTask.setText(temp.name);
            //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            //tasksGroup.addView(newTask, lp);
        }
    }
}
