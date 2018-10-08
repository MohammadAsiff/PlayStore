package com.example.sys.playstore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
 String[] titles={"item 6","item 7","item 8","item 9","item 10","item 11"};
 int[] images={R.drawable.android,R.drawable.android,R.drawable.android,R.drawable.android,R.drawable.android,R.drawable.android};
 ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        lv = findViewById( R.id.listView );
        MyAdapter adapter = new MyAdapter( Main2Activity.this, titles, images );
        lv.setAdapter( adapter );

    }
    }

     class MyAdapter extends ArrayAdapter {
        int[] imageArray;
        String[] titleArray;
        public MyAdapter(Context context,String[]titles1,int[]image1)
        {
            super(context,R.layout.example_listview, R.id.tv, titles1 );
            this.imageArray=image1;
            this.titleArray=titles1;
        }
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate( R.layout.example_listview,parent,false );
            ImageView myImage=row.findViewById( R.id.image );
            TextView myTitle=row.findViewById( R.id.tv );
            myImage.setImageResource( imageArray[position] );
            myTitle.setText( titleArray[position] );
            myTitle.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"You Clicked : "+myTitle.getText(),Toast.LENGTH_SHORT).show();
                }
            } );
            return row;
        }
    }
