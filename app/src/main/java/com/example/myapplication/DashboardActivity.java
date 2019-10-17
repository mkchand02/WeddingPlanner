package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.fragment.AllFiveFragment;
import com.example.myapplication.util.SharedPrefHelper;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;

    ExpandableListView expandableListDrawer;
    //ExpandableListAdapter expandableListAdapter;
    FrameLayout frameLayout;
    FirebaseAuth auth;
    FirebaseUser user;
    Bundle args;

    long lastBackPressed = 0;


    final HashMap<String, Integer> colors = new HashMap<>();
    final HashMap<String, String> departmentNames = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = new Bundle();
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
        com.example.myapplication.HomeFragment homeFragment = new com.example.myapplication.HomeFragment();
        homeFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();

//        auth = FirebaseAuth.getInstance();
//        if(auth.getCurrentUser() != null){
//
//
//            updateUi();
//
//            //set photo and name on header
//            setPhotoAndName();
//
//
//        }


    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }



    public void updateUi() {




        SharedPrefHelper sph = new SharedPrefHelper(this);
        String type = sph.getUserData("type");
        if(type.equals("Bride") || type.equals("Groom")) {
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }

    private void setPhotoAndName() {


        NavigationView navView = findViewById(R.id.nav_view);
        ImageView profilepic = navView.getHeaderView(0).findViewById(R.id.nav_iv);
        TextView profilename = navView.getHeaderView(0).findViewById(R.id.nav_tv1);

        SharedPrefHelper sph = new SharedPrefHelper(this);
        profilename.setText(sph.getUserData("name"));
        //Picasso.get().load(sph.getUserData("photo")).into(profilepic);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        AllFiveFragment allFiveFragment = new AllFiveFragment();
        switch (menuItem.getItemId()){
            case R.id.nav_dashboard:
                allFiveFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new com.example.myapplication.HomeFragment()).commit();
                break;
            case R.id.nav_engagement:
                args.putString("event","engagement");
                allFiveFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
            case R.id.nav_mehendi:
                args.putString("event","mehendi");
                allFiveFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
            case R.id.nav_sangeet:
                args.putString("event","sangeet");
                allFiveFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
            case R.id.nav_wedding:
                args.putString("event","wedding");
                allFiveFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
            case R.id.nav_reception:
                args.putString("event","reception");
                allFiveFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
        }
        return false;
    }

        /*private void addColors() {


        colors.put("courses", R.color.colorPrimary);
        colors.put("comps", R.color.comps);
        colors.put("it", R.color.it);
        colors.put("extc", R.color.extc);
        colors.put("chem", R.color.chem);
        colors.put("biomed", R.color.biomed);
        colors.put("biotech", R.color.biotech);
        colors.put("fe", R.color.fe);
    }

    private void addDepartmentNames() {

        departmentNames.put("courses", "TSEC App");
        departmentNames.put("comps", "Computer Science");
        departmentNames.put("it", "Information Technology");
        departmentNames.put("extc", "Electronics And Telecommunication");
        departmentNames.put("chem", "Chemical Engineering");
        departmentNames.put("biomed", "Biomedical Engineering");
        departmentNames.put("biotech", "Bio Technology");
        departmentNames.put("fe", "First Year Engineering");

    }
    public void recreateHomeActivity(){

        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(getIntent());
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }*/

}
