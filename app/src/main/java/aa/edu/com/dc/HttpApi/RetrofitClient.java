package aa.edu.com.dc.HttpApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public class RetrofitClient {
    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private HttpApi httpApil;

    public static RetrofitClient getInstance(){
        if(retrofitClient == null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    private RetrofitClient(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)//添加日志拦截器
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://10.0.2.2:8080/GourmetOrderServer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public HttpApi httpApi(){
        if(httpApil == null){
            httpApil = retrofit.create(HttpApi.class);
        }
        return httpApil;
    }
}
