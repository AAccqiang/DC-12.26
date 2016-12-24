package aa.edu.com.dc.HttpApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public interface HttpApi {

    @GET("{imgPath}")
    Call<String> loadImg(@Path("imgPath") String imgPath);
}
