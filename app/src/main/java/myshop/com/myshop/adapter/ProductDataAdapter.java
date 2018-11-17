package myshop.com.myshop.adapter;

/**
 * Created by Kajal on 9/19/2018.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import myshop.com.myshop.R;
import myshop.com.myshop.model.Item;

public class ProductDataAdapter extends RecyclerView.Adapter<ProductDataAdapter.ViewHolder> {
    private ArrayList<Item> mItemList;
    private Context context;

    public ProductDataAdapter(Context context, ArrayList<Item> items) {
        this.mItemList = items;
        this.context = context;
    }

    @Override
    public ProductDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductDataAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.item_id.setText(mItemList.get(i).getId());
        loadImage(mItemList.get(i).getImage(),viewHolder.item_img);
      viewHolder.item_desc.setText(mItemList.get(i).getItemDesc());

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_id;
        private ImageView item_img;
        private TextView item_desc;
        public ViewHolder(View view) {
            super(view);

            item_id = (TextView)view.findViewById(R.id.item_id);
            item_img = (ImageView) view.findViewById(R.id.item_img);
            item_desc = (TextView)view.findViewById(R.id.item_desc);
        }
    }

    private void loadImage(String url, final ImageView iv){
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // log exception
                        Log.e("TAG", "Error loading image", e);
                        iv.setImageDrawable(context.getResources().getDrawable(R.drawable.sample_product));
                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(iv);
    }

}