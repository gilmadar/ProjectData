package com.example.yam.projectdata;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    EditText etName,etNumber,etAge,etTeacher,etClass;
    ContentValues cv;
    int Age;
    String Name,Class,Teachername,IDNUMBER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        etNumber = (EditText)findViewById(R.id.etNumber);
        etAge= (EditText)findViewById(R.id.etAge);
        etClass= (EditText)findViewById(R.id.etClass);
        etTeacher= (EditText)findViewById(R.id.etTeachear);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = item.getTitle().toString();
        if(st.equals("Credits")){
            Toast.makeText(this, "App was created by Gil Madar", Toast.LENGTH_SHORT).show();
        }
        if (st.equals("Change Activity")){
            Intent t = new Intent(this, Main2Activity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }


    public void getDataStudent(View view) {
        if(etAge.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter the Age", Toast.LENGTH_SHORT).show();
        }
        else{
            if(etClass.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter the Class", Toast.LENGTH_SHORT).show();
            }
            else{
                if(etTeacher.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter the Teacher name", Toast.LENGTH_SHORT).show();
                }
                else{

                    cv = new ContentValues();
                    Age = Integer.parseInt(etAge.getText().toString());
                    Class = etClass.getText().toString();
                    Teachername = etTeacher.getText().toString();

                    cv.put(Student.AGE, Age);
                    cv.put(Student.CLASS, Class);
                    cv.put(Student.TEACHERNAME, Teachername);


                    db = hlp.getWritableDatabase();
                    db.insert(Student.TABLE_STUDENT, null, cv);
                    db.close();
                }
            }
        }

    }
    public void getDataPersonal(View view) {
        if(etName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter the Name", Toast.LENGTH_SHORT).show();
        }
        else{
            if(etNumber.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter the ID Number", Toast.LENGTH_SHORT).show();
            }
            else{


                    cv = new ContentValues();
                    Name = etName.getText().toString();
                    IDNUMBER = etNumber.getText().toString();

                    cv.put(Personal.NAME, Name);
                    cv.put(Personal.IDNUMBER, IDNUMBER);


                    db = hlp.getWritableDatabase();
                    db.insert(Personal.TABLE_PERSONAL, null, cv);
                    db.close();

            }
        }
    }


}
