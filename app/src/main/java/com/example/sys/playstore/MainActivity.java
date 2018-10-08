package com.example.sys.playstore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    ArrayList<SectionDataModel> allSampleData;
//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        allSampleData = new ArrayList<SectionDataModel>();

        createDummyData();
        initViews();
        FragmentTransaction buildBeginTransaction = getSupportFragmentManager().beginTransaction();
        buildBeginTransaction.replace(R.id.container_home, homeFragment);
        buildBeginTransaction.commit();

        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.recyclerView);
        my_recycler_view.setHasFixedSize(true);
        RecyclerAdapter adapter = new RecyclerAdapter(this, allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }

    private void createDummyData() {
        for (int i = 1; i <= 5; i++) {
            SectionDataModel dm = new SectionDataModel();
            dm.setHeaderTitle("Section " + i);
            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }
            dm.setAllItemsInSection(singleItem);
            allSampleData.add(dm);
        }
    }

    private void initViews() {
        setContentView(R.layout.activity_main);
        homeFragment = HomeFragment.newInstance();
    }


}
