package com.example.sys.playstore;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<SectionDataModel> dataList;


    public RecyclerAdapter(Context context, ArrayList<SectionDataModel> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_list, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final String sectionName = dataList.get(i).getHeaderTitle();

        ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();

        myViewHolder.itemTitle.setText(sectionName);
        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems);
       myViewHolder.recycler_view_list.setHasFixedSize(true);
       myViewHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
       myViewHolder.recycler_view_list.setAdapter(itemListDataAdapter);

    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView itemTitle;

        protected RecyclerView recycler_view_list;

        protected Button btnMore,btn;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            this.itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) itemView.findViewById(R.id.recycler_view_list);
            this.btnMore= (Button) itemView.findViewById(R.id.btnMore);
          //  this.btn=itemView.findViewById( R.id.btn );
            btnMore.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                      mContext. startActivity(new Intent( mContext,Main2Activity.class ));

                }
            } );
            /*btn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext. startActivity(new Intent( mContext,Main3Activity.class ));
                }
            } );*/
        }
    }
}
