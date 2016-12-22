package aa.edu.com.dc.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.apache.commons.io.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import aa.edu.com.dc.bean.Dish;
import aa.edu.com.dc.bean.TableBean;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class DBmanager {

    private DBhelper dBhelper;
    Context context;

    public DBmanager(Context context) {
        dBhelper = new DBhelper(context);
        this.context = context;
    }

    //插入数据
    public void initTableDb(Context context) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        try {
            InputStreamReader ir = new InputStreamReader(context.getAssets().open("db/dish_db.sql"));
            BufferedReader br = new BufferedReader(ir);
            String len = null;
            while ((len = br.readLine()) != null) {
                if (len.length() != 0) {
                    db.execSQL(len);
                    System.out.println(len);
                }
            }
            br.close();
            ir.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<TableBean> getTableNum() {

        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from table_list", null);
        TableBean tableBean = null;
        List<TableBean> list = new ArrayList<>();
        list.clear();
        while (cursor.moveToNext()) {
            int tableNum = cursor.getInt(cursor.getColumnIndex("table_num"));
            String tableName = cursor.getString(cursor.getColumnIndex("table_name"));
            int tableSize = cursor.getInt(cursor.getColumnIndex("table_size"));
            Log.e("aaaaaaaaaaaaa", tableNum + "");
            Log.e("bbbbbbbbbbbbb", tableName);
            Log.e("ccccccccccccc", tableSize + "");
            list.add(new TableBean(tableNum, tableName, tableSize));
        }
        cursor.close();
        cursor = null;
        return list;
    }
    //获取Dish数据集合
    public List<Dish> getDishList() {

        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from dish_list", null);
        List<Dish> list = new ArrayList<>();
        list.clear();
        while (cursor.moveToNext()) {
            int dishId = cursor.getInt(cursor.getColumnIndex("dish_id"));
            String dishName = cursor.getString(cursor.getColumnIndex("dish_name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
            String dishClass = cursor.getString(cursor.getColumnIndex("dish_class"));
            String imgDownload = cursor.getString(cursor.getColumnIndex("img_download"));
            String imgPath = cursor.getString(cursor.getColumnIndex("img_path"));
            list.add(new Dish(dishName, price, introduction, dishClass, imgDownload, imgPath));
        }
        cursor.close();
        cursor = null;
        return list;
    }


}
