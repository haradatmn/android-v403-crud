package com.example.android_v403_crud;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android_v403_crud.common.MySQLiteOpenHelper;

/**
 * Create Activity
 */
public class CreateActivity extends Activity {
    //
    private SQLiteDatabase mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        //SQLite DB Setting
        MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(getApplicationContext());
        mydb = hlpr.getWritableDatabase();

        /** Button01 new create **/
        View btn01 = findViewById(R.id.button1);
        View.OnClickListener listener01 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText Data01
                EditText editText01 = (EditText)findViewById(R.id.edittext01);
                String data01 = editText01.getText().toString();
                //text empty
                if( data01.isEmpty() ){
                    Toast.makeText(CreateActivity.this, "Please input Data.", Toast.LENGTH_LONG).show();
                }else{
                    //Data01 Insert
                    ContentValues values = new ContentValues();
                    values.put("data", data01);
                    mydb.insert("mytable", null, values);

                    //Activity Finish (Next Top Page)
                    finish();
                }
            }
        };
        btn01.setOnClickListener(listener01);
    }
}