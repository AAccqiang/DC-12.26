package aa.edu.com.dc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import aa.edu.com.dc.R;
import aa.edu.com.dc.adapter.VpageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class HomeActivity extends Activity {

    private List<View> arrayList = new ArrayList<>();
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private int[] arrayImage = {
      R.mipmap.gallery1,R.mipmap.gallery2,R.mipmap.gallery3
            ,R.mipmap.gallery4,R.mipmap.gallery5,R.mipmap.gallery6
    };


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             int curr = viewPager.getCurrentItem();

            viewPager.setCurrentItem(curr + 1);
            Message message = handler.obtainMessage();
            handler.sendMessageDelayed(message,2000);
        }
    };

    private VpageAdapter vpageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initVP();
    }

    private void initVP(){

        for (int i = 0;i < arrayImage.length;i++){
            ImageView imageView = (ImageView) getLayoutInflater().from(this).inflate(R.layout.viewpager_item,null);
            imageView.setImageResource(arrayImage[i]);
            arrayList.add(imageView);
        }
        vpageAdapter = new VpageAdapter(arrayList);
        viewPager.setAdapter(vpageAdapter);

        Message msg = handler.obtainMessage();
        handler.sendMessageDelayed(msg,2000);
    }

}
