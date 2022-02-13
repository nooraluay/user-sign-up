package com.twayesh.username;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {
    Button back,delete;
    EditText name;
    DBConnection database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        back = findViewById(R.id.back_button);
        name = findViewById(R.id.deleteuserName);
        delete = findViewById(R.id.delete_button);
        database= new DBConnection(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener((View view) -> {
            String str = name.getText().toString();
            int count = database.deleteData(str);
            Toast.makeText(view.getContext(), count + " deleted", Toast.LENGTH_LONG).show();
        });

    }
}