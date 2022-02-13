package com.twayesh.username;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class viewDataActivity extends AppCompatActivity {
    DBConnection dataBase;
    Button back;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        dataBase = new DBConnection(this);
        Toast.makeText(this, "acitivty two", Toast.LENGTH_LONG).show();
        TextView viewData = findViewById(R.id.editTextTextPersonName);
        back= findViewById(R.id.back_button);
        String data = dataBase.viewData();
        viewData.setText(data);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

}