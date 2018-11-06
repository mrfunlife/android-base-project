package combackandroid.mk.group.baseproject.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import combackandroid.mk.group.baseproject.BaseProject.API.Models.ResponseDemo;
import combackandroid.mk.group.baseproject.R;
import combackandroid.mk.group.baseproject.Views.BaseFragment;
import combackandroid.mk.group.baseproject.Views.Presenters.HomePresenter;
import nucleus.factory.RequiresPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresPresenter(HomePresenter.class)
public class HomeFragment extends BaseFragment<HomePresenter> implements View.OnClickListener{
    @BindView(R.id.btnGetAPIDemo)
    Button btnGetAPIDemo;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGetAPIDemo.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    public void onFetchAPIDemoSuccess(ResponseDemo response) {
        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onFetchAPIDemoError(Throwable throwable) {
        Toast.makeText(getActivity(), throwable.getMessage() , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetAPIDemo:
                showLoading(true);
                getPresenter().fetchDataApiDemo();
                break;
                default:
                    break;
        }
    }
}
