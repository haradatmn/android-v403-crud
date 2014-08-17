package com.example.android_v403_crud;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.example.android_v403_crud.common.MySQLiteOpenHelper;

/**
 * Delete Activity
 */
public class DeleteActivity extends Activity{
    //
    private SQLiteDatabase mydb;
    private Cursor cursor;
    private SimpleCursorAdapter myadapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        //SQLite DB Setting
        MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(getApplicationContext());
        mydb = hlpr.getWritableDatabase();

        /** ListView DB Data **/
        cursor = mydb.query(
                "mytable",
                new String[] {"_id", "data"}, null, null, null, null, "_id DESC");
        String[] from = new String[] {"_id", "data"};
        int[] to = new int[] {R.id._id, R.id.data01};
        myadapter = new SimpleCursorAdapter(this, R.layout.delete_row, cursor, from, to);
        //ListView Setting
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(myadapter);
    }
}