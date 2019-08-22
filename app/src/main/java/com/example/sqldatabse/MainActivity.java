package com.example.sqldatabse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqldatabse.database.DatabaseQueries;

public class MainActivity extends AppCompatActivity {
    EditText name ,email, password ;
    Button submit,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        submit = findViewById(R.id.submit);
        show=findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ShowActivity.class);
                startActivity(i);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1= name.getText().toString();
                String email1= email.getText().toString();
                String password1= password.getText().toString();

                DatabaseQueries db=new DatabaseQueries(MainActivity.this );
                long res =db.addData(name1, email1, password1);
                if(res>0)
                {
                    Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }
}
