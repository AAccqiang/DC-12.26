package aa.edu.com.dc.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class SharedPreferUtil {

    public static void getIsFirst(Context context){
        SharedPreferences sp = context.getSharedPreferences("first",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isFirst",false);
        editor.commit();
    }
}
