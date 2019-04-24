package com.work.androidarchitecturecomponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    EditText etTitle, etDescription;

    NumberPicker npPriority;


    Button btnCancel, btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel);
        setTitle("Add Note");

        initView();
    }


    private void initView() {

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        npPriority = findViewById(R.id.npPriority);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        npPriority.setMinValue(1);
        npPriority.setMaxValue(10);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleString = etTitle.getText().toString().trim();
                String descriptionString = etDescription.getText().toString().trim();

                if (TextUtils.isEmpty(titleString) || TextUtils.isEmpty(descriptionString)) {
                    Toast.makeText(AddNoteActivity.this, "Invalid inputs", Toast.LENGTH_SHORT).show();
                }

                Intent data = new Intent();
                data.putExtra("title", titleString);
                data.putExtra("description", descriptionString);
                data.putExtra("priority", npPriority.getValue());

                setResult(RESULT_OK, data);
                finish();

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
