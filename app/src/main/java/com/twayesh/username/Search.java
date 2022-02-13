package com.twayesh.username;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Search extends AppCompatActivity {
    Button back, searchButton;
    DBConnection db;
    TextView viewuser;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        back = findViewById(R.id.back_button);
        searchButton= findViewById(R.id.search_button);
        db = new DBConnection(this);
        viewuser= findViewById(R.id.editTextTextPersonName);
        userName = findViewById(R.id.userName);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String searchuserName = db.searchData(username);
                viewuser.setText(searchuserName);

            }
        });

   }
}