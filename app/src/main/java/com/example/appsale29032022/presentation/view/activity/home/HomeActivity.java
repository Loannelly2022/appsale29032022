package com.example.appsale29032022.presentation.view.activity.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.appsale29032022.R;
import com.example.appsale29032022.presentation.view.activity.store.StoreFragment;
import com.example.appsale29032022.presentation.view.activity.user.UserFragment;

public class HomeActivity extends AppCompatActivity {
    private final int ID_HOME = 1;
    private final int ID_STORE = 2;
    private final int ID_ABOUT = 3;
    TextView txtLable;
    MeowBottomNavigation naviBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControls();
        setUpNavi();
        events();

    }

    private void events() {
        naviBottom.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case ID_HOME:
                        replaceFragment(new HomeFragment());
                        break;
                    case ID_STORE:
                        replaceFragment(new StoreFragment());
                        break;
                    case ID_ABOUT:
                        replaceFragment(new UserFragment());
                        break;
                }
            }
        });
        naviBottom.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                String name = "";
                switch (item.getId()){
                    case ID_HOME:
                        name = "Home";
                        break;
                    case ID_STORE:
                        name = "Store";
                        break;
                    case ID_ABOUT:
                        name = "About";
                        break;
                }
                txtLable.setText(name);
            }
        });
        naviBottom.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case ID_HOME:
                        replaceFragment(new HomeFragment());
                        break;
                    case ID_STORE:
                        replaceFragment(new StoreFragment());
                        break;
                    case ID_ABOUT:
                        replaceFragment(new UserFragment());
                        break;
                }
            }
        });

        // Choose fragment first show
        naviBottom.show(ID_HOME,true);
        replaceFragment(new HomeFragment());
    }

    private void setUpNavi() {
        naviBottom.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.ic_home));
        naviBottom.add(new MeowBottomNavigation.Model(ID_STORE,R.drawable.ic_store));
        naviBottom.add(new MeowBottomNavigation.Model(ID_ABOUT,R.drawable.ic_acc));
    }

    private void addControls() {
        txtLable = findViewById(R.id.textViewLabel);
        naviBottom = findViewById(R.id.navigationBottom);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}