package com.example.yam.projectdata;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    ListView lv;
    HelperDB hlp;
    SQLiteDatabase db;
    Cursor c;
    Spinner s1;
    ArrayAdapter<String> adp;
    ArrayList<String> tb1 = new ArrayList<>();
    ArrayList<String> tb2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        s1 = (Spinner) findViewById(R.id.spinner2);
        lv = (ListView) findViewById(R.id.lv1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tables, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(this);

        lv.setOnItemClickListener(this);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            Intent t = new Intent(this, MainActivity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        if (text.equals("Personal Info")){
            Toast.makeText(this, "Personal Info", Toast.LENGTH_LONG).show();
            adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tb1);
            lv.setAdapter(adp);
        }

        if(text.equals("Grades")){
            Toast.makeText(this, "Grades", Toast.LENGTH_LONG).show();
            adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tb2);
            lv.setAdapter(adp);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void enterData(SQLiteDatabase db){
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        c = db.query(Personal.TABLE_PERSONAL, null, null, null, null, null, null);
        int col01 = c.getColumnIndex("_id");
        int col02 = c.getColumnIndex("Name");
        int col03 = c.getColumnIndex("IDNUMBER");
        c.moveToFirst();

        while (!c.isAfterLast()) {
            int Pid = c.getInt(col01);
            String name = c.getString(col02);
            String ID_NUMBER = c.getString(col03);

            String temp = name + "," + ID_NUMBER;
            tb2.add(temp);
            c.moveToNext();
        }
        c.close();
        db.close();


        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        c = db.query(Student.TABLE_STUDENT, null, null, null, null, null, null);
        int col1 = c.getColumnIndex("_id");
        int col2 = c.getColumnIndex("Age");
        int col3 = c.getColumnIndex("Class");
        int col4 = c.getColumnIndex("Teacher name");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int Pid = c.getInt(col1);
            int Age1 = c.getInt(col2);
            String Class1 = c.getString(col3);
            String Teachname = c.getString(col4);
            String temp = + Age1 + "," + Class1 + " , Teachname";
            tb1.add(temp);
            c.moveToNext();
        }
        c.close();
        db.close();
    }
}