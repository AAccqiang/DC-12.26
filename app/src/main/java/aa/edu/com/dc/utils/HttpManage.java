package aa.edu.com.dc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 86409 on 2016/12/1.
 */

public class HttpManage {

    public static boolean isNetConnected(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nk = cm.getActiveNetworkInfo();
        if(nk != null){
            return nk.isAvailable();
        }
        return false;

    }

}
