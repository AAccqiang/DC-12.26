package aa.edu.com.dc;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {
    EditText etUsername;
    EditText etPassword;
    private RequestQueue requestQueue ;
    private static  final String url = "http://10.0.1.13:8080/GourmetOrderServer/loginServlet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword= (EditText) findViewById(R.id.et_password);
      //  Button btnLogin = (Button) findViewById(R.id.btn_login);
    //    btnLogin.setOnClickListener(listener);
        requestQueue = Volley.newRequestQueue(this);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_login)
    public void sss(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String path = url + "?category=user&name="+username+"&paw="+password;

        JsonObjectRequest request = new JsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String rs = response.getString("rt");
                    String remessage = response.getString("rtmsg");
                    if("200".equals(rs)){
                        Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,remessage,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"访问异常",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String path = url + "?category=user&name="+username+"&paw="+password;

            JsonObjectRequest request = new JsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String rs = response.getString("rt");
                        String remessage = response.getString("rtmsg");
                        if("200".equals(rs)){
                            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,remessage,Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,"访问异常",Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(request);
        }
    };


    private View.OnClickListener listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String path = url + "?category=user&name="+username+"&paw="+password;
            StringRequest request = new StringRequest(path, new Response.Listener<String>() {
                @Override


                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String resultCode = jsonObject.getString("rt");
                        String resultMessage = jsonObject.getString("rtmsg");
                        if("200".equals(resultCode)){
                            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,resultMessage,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,"访问异常",Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(request);

        }
    };
}
