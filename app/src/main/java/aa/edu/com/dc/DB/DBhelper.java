package aa.edu.com.dc.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "Dc.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists table_list(_id integer primary key autoIncrement,table_num text,table_name text,table_add text,table_size text,staff_num text)");
        db.execSQL("create table if not exists dish_list(_id integer primary key autoIncrement,dish_id text,dish_name text,price text,introduction text,dish_class text,dish_unit text,img_path text,load_position text default 0,img_download boolean default 0)");
        db.execSQL("create table if not exists temp_orders(_id integer primary key autoIncrement,table_num text,dish_id text,count text default 1,remark text default '')");
        db.execSQL("create table if not exists orders(_id integer primary key autoIncrement,order_id text,table_num text,checkout boolean default 0)");
     }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
