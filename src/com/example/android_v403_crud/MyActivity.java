package com.example.android_v403_crud;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.example.android_v403_crud.common.MySQLiteOpenHelper;

/**
 * Main Activity Class
 *  Android V4.0.3 CRUD Example
 *   SQLite - Create/Read/Updata/Delete
 */
public class MyActivity extends Activity implements OnClickListener{

    private SQLiteDatabase mydb;
    private Cursor cursor;
    private SimpleCursorAdapter myadapter;

    private ListView listView;

    /**
     * Activity Create
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //SQLite DB Setting
        MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(getApplicationContext());
        mydb = hlpr.getWritableDatabase();

        /** ListView DB Data **/
        cursor = mydb.query(
                "mytable",
                new String[] {"_id", "data"}, null, null, null, null, "_id DESC");
        String[] from = new String[] {"_id", "data"};
        int[] to = new int[] {R.id._id, R.id.data01};
        myadapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);
        //ListView Setting
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(myadapter);

        /** Button01 [CREATE] **/
        View btn01 = findViewById(R.id.button1);
        btn01.setOnClickListener(this);
        /** Button02 [READ] **/
        View btn02 = findViewById(R.id.button2);
        btn02.setOnClickListener(this);
        /** Button03 [UPDATE] **/
        View btn03 = findViewById(R.id.button3);
        btn03.setOnClickListener(this);
        /** Button04 [DELETE] **/
        View btn04 = findViewById(R.id.button4);
        btn04.setOnClickListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        cursor.close();
        mydb.close();
    }

    /**
     * Click Event Listener
     *  @param v :  View
     */
    public void onClick(View v){
        /* Button01 Create */
        if(v.getId() == R.id.button1){
            //Go to Next Activity
            Intent intent = new Intent(MyActivity.this, CreateActivity.class);
            startActivity(intent);
        /* Button02 Read */
        }else if(v.getId() == R.id.button2){
            finish();
            startActivity(getIntent());
            Toast.makeText(this, "Updata List!!",Toast.LENGTH_LONG).show();
        /* Button03 Update */
        }else if(v.getId() == R.id.button3){
            //Go to Next Activity(Update Page)
            Intent intent = new Intent(MyActivity.this, UpdateActiviy.class);
            startActivity(intent);
        /* Button04 Delete */
        }else if(v.getId() == R.id.button4){
            Intent intent = new Intent(MyActivity.this, DeleteActivity.class);
            startActivity(intent);
        }
    }
}
