package com.example.payto.taskquest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by payto on 9/15/2017.
 */

public class SelectClass extends Activity {
    @Override
    public void onCreate(Bundle stats) {
        super.onCreate(stats);
        setContentView(R.layout.class_selection);
        final Intent returnVal = new Intent();

        ImageButton warrior = (ImageButton)findViewById(R.id.warriorPick);
        ImageButton sorcerer = (ImageButton)findViewById(R.id.sorcererPick);
        ImageButton rogue = (ImageButton)findViewById(R.id.roguePick);
        //Set onClickListeners
        warrior.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnVal.putExtra("Class", "Warrior");
                setResult(Activity.RESULT_OK, returnVal);
            }
        });

        sorcerer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnVal.putExtra("Class", "Sorcerer");
                setResult(Activity.RESULT_OK, returnVal);
                finish();
            }
        });

        rogue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnVal.putExtra("Class", "Rogue");
                setResult(Activity.RESULT_OK, returnVal);
            }
        });
    }
}
