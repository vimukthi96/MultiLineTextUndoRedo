package com.example.vimukthi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MultiAutoCompleteTextView editText;
    Button redoBtn;
    Button undoBtn;
    TextViewUndoRedo helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redoBtn = (Button) findViewById(R.id.redo);
        undoBtn = (Button) findViewById(R.id.undo);
        undoBtn.setEnabled(false);
        redoBtn.setEnabled(false);
        editText = (MultiAutoCompleteTextView) findViewById(R.id.edittext);
        helper = new TextViewUndoRedo(editText);

        redoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRedo();
            }
        });
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textInType() == true) {

                    performUndo();
                }
                redoBtn.setEnabled(true);
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInType();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public boolean textInType() {
        if (editText.getText().length() == 0) {
            undoBtn.setEnabled(false);
            redoBtn.setEnabled(false);
            return false;
        } else {
            undoBtn.setEnabled(true);
            return true;

        }
    }


    private void performRedo() {
        helper.redo();
    }

    private void performUndo() {
        helper.undo();
    }

    ImageButton locButton;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_file_view, menu);
        locButton = (ImageButton) menu.findItem(R.id.action_undo_btn).getActionView();
        locButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this,"edjebdre",Toast.LENGTH_SHORT).show();
              //  performUndo();
           }
    });
        return true;
    }




}

