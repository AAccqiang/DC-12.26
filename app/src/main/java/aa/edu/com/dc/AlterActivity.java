package aa.edu.com.dc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aa.edu.com.dc.bean.Password;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 86409 on 2016/11/27.
 */

public class AlterActivity extends Activity {

    @BindView(R.id.et_oldPassword)
    EditText et_oldPassword;
    @BindView(R.id.et_newPassword)
    EditText et_newPassword;
    @BindView(R.id.et_confirmPassword)
    EditText et_ComfirmPassword;

    private RequestQueue requestQueue;
    private static   final String url = "http://10.0.1.13:8080/GourmetOrderServer/loginServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);
        requestQueue = Volley.newRequestQueue(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_confirm)
    public void confirm(){
        System.out.println("1233333333333333333333333333333333");

        final String oldPassword = et_oldPassword.getText().toString();
            final String newPassword = et_newPassword.getText().toString();
            final String confirmPassword = et_ComfirmPassword.getText().toString();
            String path = "http://10.0.1.13:8080/users/user";

        if(oldPassword == null || oldPassword.equals("")){
            Toast.makeText(this, "修改失败,原密码为空", Toast.LENGTH_SHORT).show();
            return;
        }


        if(oldPassword.equals(newPassword)){
            Toast.makeText(this, "修改失败,密码相同", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!newPassword.equals(confirmPassword)){
            Toast.makeText(this, "修改失败,确认密码不相同", Toast.LENGTH_SHORT).show();
            return;
        }
        final Map<String, String> map =new HashMap<String, String>();
        map.put("name","login03");
        map.put("opaw",oldPassword);
        map.put("npaw",newPassword);


        StringRequest request = new StringRequest(Request.Method.POST, url,listener,errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;
            }
        };
        requestQueue.add(request);
    }

    private Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i(response , "sssssssssssssss");
            Toast.makeText(AlterActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(AlterActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
        }
    };




}
