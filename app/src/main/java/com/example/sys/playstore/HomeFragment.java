package com.example.sys.playstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    private Context context;
    private ViewPager mViewPager;
    private ArrayList<String> offerList;
    private LinearLayout dotsLayout;
    private Timer timer;
    private int count = 0;



    public HomeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private void initViews(View view) {
        mViewPager= view.findViewById(R.id.view_pager_home);
        dotsLayout = view.findViewById(R.id.layoutDots_home);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        setUpViewPager();


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //timer for auto Sliding
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(() -> {
                    if (count <= 6) {
                        mViewPager.setCurrentItem(count);
                        count++;
                    } else {
                        count = 0;
                        mViewPager.setCurrentItem(count);

                    }
                });
            }
        }, 500, 2000);


        return view;
    }

    private void setUpViewPager() {
        offerList = new ArrayList<>();
        offerList.add("Item 0");
        offerList.add("Item 1");
        offerList.add("Item 2");
        offerList.add("Item 3");
        offerList.add("Item 4");
        offerList.add("Item 5");
        HomeViewPagerAdapter viewPagerAdapter = new HomeViewPagerAdapter(context, offerList);
        mViewPager.setAdapter(viewPagerAdapter);
        addBottomDots(0);
    }

    private void addBottomDots(int currentPage) {
        TextView[] mTxvDotsArray = new TextView[offerList.size()];

        dotsLayout.removeAllViews();
        for (int i = 0; i < mTxvDotsArray.length; i++) {
            mTxvDotsArray[i] = new TextView(context);
            mTxvDotsArray[i].setText(Html.fromHtml("&#8226;"));
            mTxvDotsArray[i].setTextSize(35);
            mTxvDotsArray[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
            dotsLayout.addView(mTxvDotsArray[i]);
        }

        if (mTxvDotsArray.length > 0)
            mTxvDotsArray[currentPage].setTextColor(getResources().getColor(R.color.grey_400));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        timer.cancel();
    }

}

