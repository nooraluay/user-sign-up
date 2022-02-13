package com.twayesh.username;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    DBConnection database;
    EditText FullName, UserName, PassWord;
    Button save, viewData , searchButton, updateButton, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FullName = findViewById(R.id.full_name);
        UserName = findViewById(R.id.user_name);
        PassWord = findViewById(R.id.password);
        save = findViewById(R.id.Save_button);
        viewData = findViewById(R.id.view_data);
        searchButton = findViewById(R.id.search_name);
        updateButton= findViewById(R.id.update_data);
        delete= findViewById(R.id.delete_data);
        database = new DBConnection(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_Name = FullName.getText().toString();
                String user_Name = UserName.getText().toString();
                String Password_user = PassWord.getText().toString();
                long userid = database.dataInsert(full_Name, user_Name, Password_user);
                if(userid <0 ){
                    //Toast.makeText(context,"Error! Not inserted", Toast.LENGTH_LONG).show();
                    Toast.makeText(view.getContext(),"error not inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "succedfully inserted", Toast.LENGTH_LONG).show();
                }
            }

        });
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(this,viewDataActivity.class);
                Intent intent = new Intent(view.getContext(),viewDataActivity.class);
                startActivity(intent);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Search.class);
                startActivity(intent);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Update_Activity.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Delete.class);
                startActivity(intent);
            }
        });
    }
}