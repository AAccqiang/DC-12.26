package aa.edu.com.dc.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aa.edu.com.dc.DB.DBmanager;
import aa.edu.com.dc.R;
import aa.edu.com.dc.adapter.DishMenuAdapter;
import aa.edu.com.dc.bean.Dish;
import aa.edu.com.dc.bean.TableBean;
import aa.edu.com.dc.utils.SharedPreferUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class ChoosedishActivity extends FragmentActivity {

    private Spinner btnTableNum;
    private Spinner btnMenuDish;

    private DBmanager dBmanager;

    private ExpandableListView expandableListView;
    private DishMenuAdapter adapter;

//adsa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosedish);
        ButterKnife.bind(this);
        //初始化二级菜单
        expandableListView = (ExpandableListView) findViewById(R.id.edListView);
        //初始化数据库辅助类
        dBmanager = new DBmanager(this);
        //判断是否第一次，进行插入数据库表数据
        SharedPreferences sp = getSharedPreferences("first", Context.MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst) {
            initTable();
        }

        sprinnerInit();
        initMenu();
    }

    //插入数据库表数据
    private void initTable() {
        SharedPreferUtil.getIsFirst(this);
        dBmanager = new DBmanager(this);
        dBmanager.initTableDb(this);
    }


    //两按钮的实现
    private void sprinnerInit() {
        btnTableNum = (Spinner) findViewById(R.id.btn_table_num);
        btnTableNum.setPrompt("请选择桌号:");

        btnMenuDish = (Spinner) findViewById(R.id.btnMenuDish);
        btnMenuDish.setPrompt("请选择菜类:");
        btnMenuDish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: 2016/12/21 0021 按钮指定热菜

                String Menus = (String) btnMenuDish.getItemAtPosition(i);
                if (i != 0) {
                    showBtnClass(Menus);
                } else {
                    initMenu();
                }

                Toast.makeText(ChoosedishActivity.this, Menus, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    List<String> menuClassList = new ArrayList<>();
    List<List<Dish>> childredDish = new ArrayList<>();

    //添加菜类集合，并获取
    public List<String> getMenuClassList() {

        menuClassList.add("热菜");
        menuClassList.add("凉菜");
        menuClassList.add("汤类");
        menuClassList.add("饮料");
        menuClassList.add("主食");
        menuClassList.add("其他");

        return menuClassList;
    }

    List<Dish> six;

    //获取菜集合，并获取,且显示
    private void initMenu() {
        //一级菜单集合
        menuClassList.clear();
        childredDish.clear();
        menuClassList = getMenuClassList();
        childredDish.clear();
        //第二级菜单的集合，发现要占用6个位置才可以想里面添加数据

        //数据库里所有的Dish数据集合
        List<Dish> list = dBmanager.getDishList();

        //给二级菜单占用位置，才可以添加？里的数据：例如数组的array[6][?];
        for (int i = 0; i < menuClassList.size(); i++) {
            six = new ArrayList<>();
            childredDish.add(six);
        }
//        //判断数据是否和菜类相等，若相等则添加
        for (Dish dish : list) {
            if (dish.getDishClass().equals(menuClassList.get(0))) {
                childredDish.get(0).add(dish);
            } else if (dish.getDishClass().equals(menuClassList.get(1))) {
                childredDish.get(1).add(dish);
            } else if (dish.getDishClass().equals(menuClassList.get(2))) {
                childredDish.get(2).add(dish);
            } else if (dish.getDishClass().equals(menuClassList.get(3))) {
                childredDish.get(3).add(dish);
            } else if (dish.getDishClass().equals(menuClassList.get(4))) {
                childredDish.get(4).add(dish);
            } else if (dish.getDishClass().equals(menuClassList.get(5))) {
                childredDish.get(5).add(dish);
            }
        }

        //expandableListView二级菜单的实现
        adapter = new DishMenuAdapter(this, menuClassList, childredDish);
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null);
        itemOnTouch();
    }


    // TODO: 2016/12/21 0021  右按钮难点攻克

    private void showBtnClass(String menus) {
        if (!menus.equals("全部")) {
            menuClassList.clear();
            //       childredDish.clear();
            menuClassList = getMenuClassList();

            for (int i = 0; i < menuClassList.size(); i++) {
                if (menuClassList.get(i).equals(menus)) {
                    menuClassList.clear();
                    menuClassList.add(menus);
                }
            }

            List<Dish> list = dBmanager.getDishList();
            adapter.getChildredDish().clear();
            for (Dish dish : list) {
                if (dish.getDishClass().equals(menuClassList.get(0))) {

                    for (int i = 0; i < 6; i++) {
                        six = new ArrayList<>();
                        childredDish.add(six);
                    }
                    adapter.getChildredDish().get(0).add(dish);
                }
            }

            List<List<Dish>> i = adapter.getChildredDish();
            Log.e("menuClassList", menuClassList.size() + "");
            Log.e("childredDish", childredDish.size() + "");
            Log.e("adapter", i.size() + "");


            adapter = new DishMenuAdapter(this, menuClassList, adapter.getChildredDish());
            expandableListView.setAdapter(adapter);
            expandableListView.setGroupIndicator(null);
            adapter.notifyDataSetChanged();
            itemOnTouch();
        }
    }

    private void itemOnTouch(){
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                Toast.makeText(ChoosedishActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }



}
