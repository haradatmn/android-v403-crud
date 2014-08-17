package com.example.android_v403_crud;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import com.example.android_v403_crud.common.MySQLiteOpenHelper;

/**
 * Update Activity
 */
public class UpdateActiviy extends Activity implements OnClickListener{
    private SQLiteDatabase mydb;
    private Cursor cursor;
    private SimpleCursorAdapter myadapter;
    private ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        //SQLite DB Setting
        MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(getApplicationContext());
        mydb = hlpr.getWritableDatabase();

        /** ListView DB Data **/
        cursor = mydb.query(
                "mytable",
                new String[] {"_id", "data"}, null, null, null, null, "_id DESC");
        String[] from = new String[] {"_id", "data"};
        int[] to = new int[] {R.id._id, R.id.data01};
        myadapter = new SimpleCursorAdapter(this, R.layout.update_row, cursor, from, to);
        //ListView Setting
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(myadapter);

        /** Button01 [update] **/
        View btn01 = findViewById(R.id.button1);
        btn01.setOnClickListener(this);
    }


    @Override
    public void onPause(){
        super.onPause();
        cursor.close();
        mydb.close();
    }

    public void onClick(View v){
        /* button01 update */
        if(v.getId() == R.id.button1){
            /** ListViewの子ViewであるEditTextの値を取得する**/
            //ListView(親クラス)を取得
            ListView listView = (ListView)findViewById(R.id.listview);
            for( int i = 0; i< listView.getChildCount();i++) {
                Log.v("update", "child1=" + listView.getChildAt(i).getClass());
                RelativeLayout layout = (RelativeLayout)listView.getChildAt(i);
                String id = null;
                String data01 = null;

                //EditText（子クラス）を取得する
                for( int j = 0; j< layout.getChildCount();j++) {
                    Log.v("update", "child2=" + layout.getChildAt(j).getClass());

                    /** 注意：EditTextはTextViewの小クラスのため、判定順番が重要**/
                    //DATA01 ※EditTextの場合
                    if( layout.getChildAt(j) instanceof EditText ){
                        EditText editText = (EditText)layout.getChildAt(j);
                        data01 = editText.getText().toString();
                        Log.v("update", "data01=" + data01);
                    //ID ※TextViewの場合
                    }else if( layout.getChildAt(j) instanceof TextView ){
                        TextView textView = (TextView)layout.getChildAt(j);
                        id = textView.getText().toString();
                        Log.v("update", "id=" + id);
                    }

                }

                //ID、DATA01をUPDATE
                ContentValues values = new ContentValues();
                values.put("data", data01);
                mydb.update("mytable", values, "_id="+id, null);
                Log.v("update","id="+id+", data=" + data01);
            }

            //Back Activity
            finish();
        }
    }


}