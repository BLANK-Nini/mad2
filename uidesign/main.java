package com.example.sigup;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText name, email, pass;
    Button signup, dobBtn;
    Spinner courseSpinner;
    ListView listview;
    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        signup = findViewById(R.id.signup);
        dobBtn = findViewById(R.id.dobBtn);

        courseSpinner = findViewById(R.id.courseSpinner);
        listview = findViewById(R.id.listview);
        gridview = findViewById(R.id.gridview);

        // Course Spinner
        String[] courses = {"Software Developer", "Machine Learning", "Cyber"};
        ArrayAdapter<String> courseAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        // Date Picker
        dobBtn.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            DatePickerDialog dp = new DatePickerDialog(
                    MainActivity.this,
                    (view, year, month, day) ->
                            dobBtn.setText(day + "/" + (month + 1) + "/" + year),
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            );
            dp.show();
        });

        // ListView
        String[] listData = {"Android", "Java", "Flutter"};
        ArrayAdapter<String> listAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listview.setAdapter(listAdapter);

        // GridView
        String[] gridData = {"Home", "Profile", "Settings"};
        ArrayAdapter<String> gridAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gridData);
        gridview.setAdapter(gridAdapter);

        // Sign Up
        signup.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,
                        "Signup Successful",
                        Toast.LENGTH_LONG).show());
    }
}
