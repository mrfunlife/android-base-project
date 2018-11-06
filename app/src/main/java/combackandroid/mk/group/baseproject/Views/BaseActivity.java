package combackandroid.mk.group.baseproject.Views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.CallSuper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import combackandroid.mk.group.baseproject.R;
import nucleus.view.NucleusAppCompatActivity;
import rx.Subscription;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity<P extends BasePresenter> extends NucleusAppCompatActivity<P> {

    public final static int ID_FRAGMENT = R.id.fragment_container;

    private Subscription subcriptipon;

    protected abstract Fragment createRootFragment();

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(ID_FRAGMENT);
        if (fragment == null) {
            fragment = createRootFragment();
            fm.beginTransaction().replace(ID_FRAGMENT, fragment).commit();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onBackPressed() {
        // Otherwise, it may return to the previous fragment stack
          FragmentManager fragmentManager = getFragmentManager();
          if (fragmentManager.getBackStackEntryCount() > 0) {
              fragmentManager.popBackStack();
          }else{
              super.onBackPressed();
          }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subcriptipon != null && !subcriptipon.isUnsubscribed())
            subcriptipon.unsubscribe();
    }
}