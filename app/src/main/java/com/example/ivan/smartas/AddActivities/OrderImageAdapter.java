package com.example.ivan.smartas.AddActivities;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ivan.smartas.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 25.10.2017.
 */

public class OrderImageAdapter extends RecyclerView.Adapter<OrderImageAdapter.ImageViewHolder> {

    Context context;
    ArrayList<OrderImage> arrayListImage = new ArrayList<>();
    ArrayList<String> names = null;

    public OrderImageAdapter(Context context, ArrayList<OrderImage> arrayListImage) {
        this.context = context;
        this.arrayListImage = arrayListImage;
    }

    public void setNames(ArrayList<String> names){
        this.names = names;
    }

    public ArrayList<String> getNames(){
        return this.names;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, null, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        OrderImage orderImage = arrayListImage.get(position);
        holder.imageView.setImageURI(Uri.parse(orderImage.getPath()));
        holder.buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.photo_item_btn_del){
                    arrayListImage.remove(position);
                    //notifyItemRemoved(position);
                    notifyDataSetChanged();
                    names.remove(position);
                    Toast.makeText(v.getContext(), "size == " + arrayListImage.size() + "/" + names.size(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public ArrayList<OrderImage> getArrayListImage(){
        return arrayListImage;
    }

    @Override
    public int getItemCount() {
        return arrayListImage.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public ImageButton buttonClose;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.photo_item);
            buttonClose = (ImageButton) itemView.findViewById(R.id.photo_item_btn_del);
        }
    }
}
