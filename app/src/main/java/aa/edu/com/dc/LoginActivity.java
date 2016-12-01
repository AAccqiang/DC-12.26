package aa.edu.com.dc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
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

import aa.edu.com.dc.utils.HttpManage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Thread.sleep;

/**
 * Created by 86409 on 2016/11/25.
 */

public class LoginActivity extends Activity {

    private  ProgressDialog progressDialog;

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
        ButterKnife.bind(this);
        requestQueue = Volley.newRequestQueue(this);

    }


    @OnClick(R.id.btn_lg)
    public void login(View v){
        boolean isconnect = HttpManage.isNetConnected(this);
        if(!isconnect){
            Toast.makeText(this,"你的网络不可用！",Toast.LENGTH_SHORT).show();
            return;
        }
        String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        String path = url  + "?category=user&name="+username+"&paw="+password;

        if(username == null || username.equals("")){
            Toast.makeText(this, "登陆失败,账号为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password == null || password.equals("")){
            Toast.makeText(this, "登陆失败,密码为空", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgress();

         JsonObjectRequest request = new JsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 try {
                     String rt = response.getString("rt");
                     String rtmsg = response.getString("rtmsg");
                     if("200".equals(rt)){
                         Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();

                             Intent intent = new Intent(LoginActivity.this,AlterActivity.class);
                             startActivity(intent);
                             finish();

                     }else {
                         Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                         dismiss();
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
    public void showProgress(){
        progressDialog = ProgressDialog.show(this,null,"正在登陆");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public void dismiss(){
        progressDialog.dismiss();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismiss();
    }
}
