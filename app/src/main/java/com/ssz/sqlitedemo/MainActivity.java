package com.ssz.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    //使用https://github.com/JakeWharton/butterknife
    @BindView(R.id.btn_create_db)
    Button createDBbtn;
    @BindView(R.id.btn_update_db)
    Button btnUpdateDb;
    @BindView(R.id.btn_insert_data)
    Button btnInsertData;
    @BindView(R.id.btn_delete_db)
    Button btnDeleteDb;
    @BindView(R.id.btn_update_data)
    Button btnUpdateData;
    @BindView(R.id.btn_query_data)
    Button btnQueryData;
    @BindView(R.id.btn_delete_data)
    Button btnDeleteData;
    private SQLiteDatabase mWritableDatabase;
    private MySqliteOpenHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_create_db, R.id.btn_update_db, R.id.btn_insert_data, R.id.btn_delete_db, R.id.btn_update_data, R.id.btn_query_data, R.id.btn_delete_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_db:
                createDb();
                break;

            case R.id.btn_update_db:
                updateDb();
                break;

            case R.id.btn_insert_data:
                insertData();
                break;

            case R.id.btn_update_data:
                updateData();
                break;

            case R.id.btn_query_data:
                queryData();
                break;

            case R.id.btn_delete_data:
                deleteData();
                break;

            case R.id.btn_delete_db:
                deleteDb();
                break;
        }
    }

    private void createDb() {
        mDbHelper = new MySqliteOpenHelper(this, "test.db",null,1);
        mWritableDatabase = mDbHelper.getWritableDatabase();
    }

    private void updateDb() {
        mDbHelper = new MySqliteOpenHelper(this, "test.db",null,2);
        mWritableDatabase = mDbHelper.getWritableDatabase();
    }

    private void insertData() {
        ContentValues values = new ContentValues();
        values.put("id",1);
        values.put("name","ssz");
        mWritableDatabase.insert("student",null,values);
        mWritableDatabase.close();
    }

    private void updateData() {
        mWritableDatabase = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","ssz1");
        mWritableDatabase.update("student",values,"id=?",new String[]{"1"});
        mWritableDatabase.close();
    }
    private void queryData() {
        mWritableDatabase = mDbHelper.getWritableDatabase();
        String id;
        String name;
        Cursor cursor = mWritableDatabase.query("student", new String[]{"id", "name"}, "id=?",
                new String[]{"1"}, null, null, null);
        while (cursor.moveToNext()){
            id = cursor.getString(cursor.getColumnIndex("id"));
            name = cursor.getString(cursor.getColumnIndex("name"));
            Log.e("查询到的数据是：","id=" + id +"    "+ "name=" + name);
        }
        mWritableDatabase.close();
    }

    private void deleteData() {
        mWritableDatabase = mDbHelper.getWritableDatabase();
        mWritableDatabase.delete("student","id=?", new String[]{"1"});
        mWritableDatabase.close();
    }
    private void deleteDb() {
        deleteDatabase("test.db");
    }
}
