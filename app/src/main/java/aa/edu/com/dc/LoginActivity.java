package aa.edu.com.dc;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 86409 on 2016/11/25.
 */

public class LoginActivity extends Activity {

    @BindView(R.id.et_Username)
    EditText et_username;
    @BindView(R.id.et_Password)
    EditText et_password;
    private RequestQueue requestQueue ;
    private static  final String url = "http://10.0.1.13:8080/GourmetOrderServer/loginServlet";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        System.out.println("ssssssssssssssssssssss11ssssssssssssssss");
        ButterKnife.bind(this);
        requestQueue = Volley.newRequestQueue(this);

    }


    public void login(View v){
        System.out.print("ssssssssssssssssssssssssssssssssssssss");
         String username = et_username.getText().toString();
         String password = et_password.getText().toString();
         String path = url  + "?category=user&name="+username+"&paw="+password;

         JsonObjectRequest request = new JsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 try {
                     String rt = response.getString("rt");
                     String rtmsg = response.getString("rtmsg");
                     if("200".equals(rt)){
                         Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT);
                     }else {
                         Toast.makeText(LoginActivity.this,"rtmsg",Toast.LENGTH_SHORT);
                     }

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(LoginActivity.this,"访问异常",Toast.LENGTH_SHORT).show();
             }
         });

         requestQueue.add(request);
    }

}
