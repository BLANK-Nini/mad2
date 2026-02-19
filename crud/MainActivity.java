package com.example.sqdb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.database.Cursor;
import android.content.ContentValues;

public class MainActivity extends AppCompatActivity {

    EditText rollno, name, dept;
    Button insert, update, delete, view;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollno = findViewById(R.id.rollno);
        name = findViewById(R.id.name);
        dept = findViewById(R.id.dept);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        db = new DBHelper(this);

        // INSERT
        insert.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("rollno", rollno.getText().toString());
            values.put("name", name.getText().toString());
            values.put("dept", dept.getText().toString());

            boolean result = db.insertData(values);

            if(result)
                Toast.makeText(this,"Inserted",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show();
        });

        // UPDATE
        update.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("name", name.getText().toString());
            values.put("dept", dept.getText().toString());

            int result = db.updateData(values, rollno.getText().toString());

            if(result > 0)
                Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show();
        });

        // DELETE
        delete.setOnClickListener(v -> {
            int result = db.deleteData(rollno.getText().toString());

            if(result > 0)
                Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show();
        });

        // VIEW
        view.setOnClickListener(v -> {
            Cursor cursor = db.readData();
            StringBuilder data = new StringBuilder();

            while(cursor.moveToNext()){
                data.append("Roll: " + cursor.getString(0));
                data.append("Name: " + cursor.getString(1));
                data.append("Dept: " + cursor.getString(2));
            }

            Toast.makeText(this,data.toString(),Toast.LENGTH_LONG).show();
        });
    }
}