package com.sz.jjj.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sz.jjj.R;
import com.sz.jjj.view.event.TestEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jjj on 2018/1/2.
 *
 * @description:
 */

public class TestFragment extends Fragment {
    @BindView(R.id.card_title_tv)
    TextView cardTitleTv;
    Unbinder unbinder;
    private String mTitle;

    public static TestFragment getInstance(String title) {
        TestFragment sf = new TestFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_silding_tab_fragment, null);
        unbinder = ButterKnife.bind(this, v);
        cardTitleTv.setText(mTitle);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.card_title_tv)
    public void onViewClicked() {
        EventBus.getDefault().post(new TestEvent());
    }
}
