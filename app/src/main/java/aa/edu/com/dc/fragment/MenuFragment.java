package aa.edu.com.dc.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import aa.edu.com.dc.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class MenuFragment extends Fragment {

    @BindView(R.id.iv_fm_menuImg)
    ImageView imageView;

    @BindView(R.id.tv_fm_menuName)
    TextView tvFmMenuName;

    @BindView(R.id.tv_fm_menuPirce)
    TextView tvFmMenuPirce;

    @BindView(R.id.tv_fm_introduction)
    TextView tvFmIntroduction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fish,container,false);
        ButterKnife.bind(this,view);
        return view;
    }



}
