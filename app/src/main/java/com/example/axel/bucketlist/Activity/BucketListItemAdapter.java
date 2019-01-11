package com.example.axel.bucketlist.Activity;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.axel.bucketlist.Database.Entity.BucketListItem;
import com.example.axel.bucketlist.R;
import java.util.List;

public class BucketListItemAdapter extends RecyclerView.Adapter<BucketListItemAdapter.ViewHolder> {

    private Context context;
    private List<BucketListItem> bucketListItems;
    private BucketListItemOnClickListener mBucketListItemOnClickListener;

    public BucketListItemAdapter(Context context, List<BucketListItem> bucketListItems, BucketListItemOnClickListener mBucketListItemOnClickListener) {
        this.context = context;
        this.bucketListItems = bucketListItems;
        this.mBucketListItemOnClickListener = mBucketListItemOnClickListener;
    }

    @NonNull
    @Override
    public BucketListItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_grid_bucketlist, viewGroup, false);
        return new ViewHolder(itemView, mBucketListItemOnClickListener);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BucketListItem item = bucketListItems.get(i);
        viewHolder.itemName.setText(item.getName());
        viewHolder.itemDescription.setText(item.getDescription());
        viewHolder.checkBoxItem.setChecked(item.getChecked());
        if (item.getChecked()) {
            viewHolder.itemName.setPaintFlags(viewHolder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.itemDescription.setPaintFlags(viewHolder.itemDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return bucketListItems.size();
    }

    public void setCheckListItems(List<BucketListItem> items) {
        this.bucketListItems = items;
        notifyDataSetChanged();
    }

    public interface BucketListItemOnClickListener {
        void itemOnClick (View view, int i);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CheckBox checkBoxItem;
        public TextView itemName;
        public TextView itemDescription;
        private BucketListItemOnClickListener mBucketListItemOnClickListener;

        public ViewHolder(View itemView, BucketListItemOnClickListener listener) {
            super(itemView);
            checkBoxItem = itemView.findViewById(R.id.checkBox);
            itemName = itemView.findViewById(R.id.item_name);
            itemDescription = itemView.findViewById(R.id.item_description);
            mBucketListItemOnClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mBucketListItemOnClickListener.itemOnClick(view, clickedPosition);
        }
    }
}
