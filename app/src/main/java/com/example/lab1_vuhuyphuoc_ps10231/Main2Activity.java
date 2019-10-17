package com.example.lab1_vuhuyphuoc_ps10231;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import fragment.HoaDonFragment;
import fragment.NguoiDungFragment;
import fragment.SachBanChayFragment;
import fragment.SachFragment;
import fragment.TheLoaiFragment;
import fragment.ThongKeFragment;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    // giao diên chính
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
//        MỞ FRAGMENT MẶC ĐỊNH LÀ FRAGMENT THỐNG KÊ
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new ThongKeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
       int id = menuItem.getItemId();
       if (id == R.id.nguoidung){
           fragmentTransaction.replace(R.id.fragment_container,new NguoiDungFragment()).commit();
       }
        if (id == R.id.theloai){
            fragmentTransaction.replace(R.id.fragment_container,new TheLoaiFragment()).commit();
        }
        if (id == R.id.sach){
            fragmentTransaction.replace(R.id.fragment_container,new SachFragment()).commit();
        }
        if (id == R.id.sachbanchay){
            fragmentTransaction.replace(R.id.fragment_container,new SachBanChayFragment()).commit();
        }
        if (id == R.id.hoadon){
            fragmentTransaction.replace(R.id.fragment_container,new HoaDonFragment()).commit();
        }
        if (id == R.id.thongke){
            fragmentTransaction.replace(R.id.fragment_container,new ThongKeFragment()).commit();
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;



    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
