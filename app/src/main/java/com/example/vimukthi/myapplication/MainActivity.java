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
    Menu menu;
    MenuItem undo_btn;
    boolean b = false;
    MenuItem redo_btn;


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
            undo_btn.setVisible(false);
         //   redo_btn.setVisible(false);
            return false;
        } else {
            undoBtn.setEnabled(true);
            b=true;
            undo_btn.setVisible(b);
            return true;

        }
    }


    private void performRedo() {
        helper.redo();
    }

    private void performUndo() {
        helper.undo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_file_view, menu);
        undo_btn = (MenuItem) menu.findItem(R.id.action_undo_btn);
        redo_btn = (MenuItem) menu.findItem(R.id.action_redo_btn);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        int id=item.getItemId();
        switch (id){
            case  R.id.action_undo_btn:
                if (textInType() == true) {

                    performUndo();
                }
                redo_btn.setVisible(true);
                return true;
            case  R.id.action_redo_btn:
                performRedo();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
       // undo_btn.setEnabled(b);
        redo_btn.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }



}

