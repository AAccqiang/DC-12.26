package aa.edu.com.dc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aa.edu.com.dc.R;
import aa.edu.com.dc.bean.Dish;

/**
 * Created by Administrator on 2016/12/20 0020.
 */

public class DishMenuAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groupMenu = new ArrayList<>();
    private List<List<Dish>> childredDish = new ArrayList<>();
    private LayoutInflater inflater;

    public DishMenuAdapter(Context context,List<String> groupMenu,List<List<Dish>> childredDish) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.groupMenu = groupMenu;
        this.childredDish = childredDish;
    }

    @Override
    public int getGroupCount() {
        return groupMenu.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childredDish.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupMenu.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childredDish.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public List<List<Dish>> getChildredDish(){
        return childredDish;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_group_menu,null);
        TextView tvMenuName = (TextView) view.findViewById(R.id.tv_menu_name);

        tvMenuName.setText(groupMenu.get(i).toString());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_group_childer,null);
        TextView tvChildName = (TextView) view.findViewById(R.id.tv_childer_name);


        CheckBox cbChild = (CheckBox) view.findViewById(R.id.cb_childer_name);
        Dish dish = childredDish.get(i).get(i1);
        tvChildName.setText(dish.getDishName());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
