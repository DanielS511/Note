package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

public class NoteEditor extends AppCompatActivity {
    int noteID;

    EditText titleEditText;
    EditText noteEditText;
    /*
    Initialize the Title and Note according to the note id
     */
    protected void initializeTextView(){
        noteID = getIntent().getIntExtra("NoteID", -1);

        if (noteID != -1 && noteID < MainActivity.title.size()){
            //if we access a note we have written
            titleEditText.setText(MainActivity.title.get(noteID));
            noteEditText.setText(MainActivity.note.get(noteID));
        }else if (noteID != -1){
            //if we create a new node -- note id will be the size of title so we need to increment title and note list
            MainActivity.title.add("");
            MainActivity.arrayAdapter.notifyDataSetChanged();
            MainActivity.note.add("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        noteEditText = (EditText) findViewById(R.id.noteEditText);
        initializeTextView();

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.title.set(noteID,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.note.set(noteID,String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}