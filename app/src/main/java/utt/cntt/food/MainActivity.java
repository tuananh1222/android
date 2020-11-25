package utt.cntt.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
        Food_Adaper foodAdaper;
        ArrayList<Food> foods;
        RecyclerView recyclerView;

        private DrawerLayout drawerMain;
        private BottomNavigationView bottom_nav_main;
        private NavigationView navMain;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initActionBar();
        initView();
    }
        private void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }
        private void initView(){
        drawerMain = findViewById(R.id.drawerMain);
        bottom_nav_main = findViewById(R.id.btt_nav);
        bottom_nav_main.setOnNavigationItemSelectedListener(navListener);
        navMain = findViewById(R.id.navMain);
        navMain.setNavigationItemSelectedListener(MainActivity.this);
    }
        private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment fragment = new Home_Fragement();
                        switch (menuItem.getItemId()){
                            case R.id.mnu_home:
                                fragment = new Home_Fragement();
                                break;
                            case R.id.mnu_list:
                                fragment = new List_Fragement();
                                break;
                            case R.id.mnu_favor:
                                fragment = new Favor_Fragement();
                                break;
                            case R.id.mnu_acc:
                                fragment = new Account_Fragement();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
                        return true;
                    }
                };
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_toolbar, menu);
            MenuItem searchItem = menu.findItem(R.id.search_food);
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    foodAdaper.getFilter().filter(newText);
                    return false;
                }
            });
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerMain.isDrawerOpen(GravityCompat.START)) {
                    drawerMain.closeDrawer(GravityCompat.START);
                } else {
                    drawerMain.openDrawer(GravityCompat.START);
                }
                break;
        }
             return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_setting:
                Toast.makeText(this, "123123", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_home:
                Toast.makeText(this, "1234123", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    private  void initRecyclerView(){
        recyclerView = findViewById(R.id.foods_list);
        foods = new ArrayList<>();
        for(int i = 0 ; i < 50; i++){
            foods.add(new Food(R.drawable.china, "china"));
        }
        foodAdaper = new Food_Adaper( foods, MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}