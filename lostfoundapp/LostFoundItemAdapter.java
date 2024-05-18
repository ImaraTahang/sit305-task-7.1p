package com.example.lostfoundapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LostFoundItemAdapter extends RecyclerView.Adapter<LostFoundItemAdapter.ViewHolder> {
    private List<LostFoundItem> lostFoundItemList;
    private OnItemRemovedListener onItemRemovedListener;

    public LostFoundItemAdapter(List<LostFoundItem> lostFoundItemList, OnItemRemovedListener onItemRemovedListener){
        this.lostFoundItemList=lostFoundItemList;
        this.onItemRemovedListener=onItemRemovedListener;
    }
    public interface OnItemRemovedListener {
        void onItemRemoved(LostFoundItem item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lost_found, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        LostFoundItem item = lostFoundItemList.get(position);
        holder.textViewPostType.setText("Post Type: " + item.getPostType());
        holder.textViewName.setText("Name: " + item.getName());
        holder.textViewPhone.setText("Phone: " + item.getPhone());
        holder.textViewDescription.setText("Description: " + item.getDescription());
        holder.textViewDate.setText("Date: " + item.getDate());
        holder.textViewLocation.setText("Location: " + item.getLocation());

        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                LostFoundItem item = lostFoundItemList.get(position);
                lostFoundItemList.remove(position);
                notifyItemRemoved(position);
                if (onItemRemovedListener != null) {
                    onItemRemovedListener.onItemRemoved(item);
                }
            }
        });
    }

    public int getItemCount(){
        return lostFoundItemList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPostType;
        public TextView textViewName;
        public TextView textViewPhone;
        public TextView textViewDescription;
        public TextView textViewDate;
        public TextView textViewLocation;
        Button buttonRemove;

        public ViewHolder(View view) {
            super(view);
            textViewPostType = view.findViewById(R.id.textViewPostType);
            textViewName = view.findViewById(R.id.textViewName);
            textViewPhone = view.findViewById(R.id.textViewPhone);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            textViewDate = view.findViewById(R.id.textViewDate);
            textViewLocation = view.findViewById(R.id.textViewLocation);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }
}
