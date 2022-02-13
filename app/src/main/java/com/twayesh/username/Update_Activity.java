package com.twayesh.username;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update_Activity extends AppCompatActivity {
    Button back,update;
    EditText new_name, old_name;
    DBConnection database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        back = findViewById(R.id.back_button);
        new_name=findViewById(R.id.updatename);
        old_name= findViewById(R.id.OlduserName);
        update= findViewById(R.id.update_button);
        database= new DBConnection(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

      /*update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldUserName = old_name.getText().toString();
                String newUSerName = new_name.getText().toString();
                database.UpdateData(oldUserName,newUSerName);
            }
        });*/
        // lambda expression
        update.setOnClickListener((View view) -> {
            String oldUserName = old_name.getText().toString();
            String newUSerName = new_name.getText().toString();
            database.UpdateData(oldUserName,newUSerName);
        });

    }
}