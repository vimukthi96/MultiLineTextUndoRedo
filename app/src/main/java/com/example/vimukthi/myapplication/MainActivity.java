package com.example.vimukthi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button redoBtn = (Button) findViewById(R.id.redo);
        Button undoBtn = (Button) findViewById(R.id.undo);
        MultiAutoCompleteTextView editText = (MultiAutoCompleteTextView) findViewById(R.id.edittext);

        // pass edittext object to TextViewUndoRedo class
        final TextViewUndoRedo helper = new TextViewUndoRedo(editText);

        // call the method from TextViewUndoRedo class
        redoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.redo(); // perform redo
            }
        });
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.undo();
            }
        });


    }
}
