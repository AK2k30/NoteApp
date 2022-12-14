package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noteapp.NoteApp.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteTakerActivity extends AppCompatActivity {
    EditText editText_title, editText_notes;
    ImageView imageView_save;
    Notes notes;
    boolean isOldNotes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taker);

        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            editText_title.setText(notes.getTitle());
            editText_notes.setText(notes.getNotes());
            isOldNotes = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString();
                String description = editText_notes.getText().toString();
                
                if (description.isEmpty())
                {
                    Toast.makeText(NoteTakerActivity.this, "Please enter notes", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH: mm a");
                Date date = new Date();

                if(!isOldNotes){
                    notes = new Notes();
                }
                notes = new Notes();

                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("notes", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}