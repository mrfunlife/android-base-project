package combackandroid.mk.group.baseproject.Views.Activitys;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import combackandroid.mk.group.baseproject.Views.Fragments.DashboardFragment;
import combackandroid.mk.group.baseproject.Views.Fragments.HomeFragment;
import combackandroid.mk.group.baseproject.Views.Fragments.NotificationFragment;
import combackandroid.mk.group.baseproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.fragment_container)
    LinearLayout fragmentContainer;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private NotificationFragment notificationFragment;
    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadHomeFragment();
                    return true;
                case R.id.navigation_dashboard:
                    loadDashboardFragment();
                    return true;
                case R.id.navigation_notifications:
                    loadNotificationFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        initFragment();
    }

    private void initFragment(){
        homeFragment = new HomeFragment();
        dashboardFragment = new DashboardFragment();
        notificationFragment = new NotificationFragment();
        fragmentManager = getFragmentManager();
        loadHomeFragment();
    }

    private void replaceFragment(Fragment fragment)  {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void loadHomeFragment(){
        replaceFragment(homeFragment);
    }
    private void loadDashboardFragment(){
        replaceFragment(dashboardFragment);
    }
    private void loadNotificationFragment(){
        replaceFragment(notificationFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnNext:
                startActivity(new Intent(this,MainActivity.class));
                break;
                default:
                    break;
        }
    }
}
