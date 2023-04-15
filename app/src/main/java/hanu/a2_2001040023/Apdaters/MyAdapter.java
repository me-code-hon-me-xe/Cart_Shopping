package hanu.a2_2001040023.Apdaters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hanu.a2_2001040023.CartActivity;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.R;
import retrofit2.Callback;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Product> productList;
    private Context mContext;

    public MyAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_pl;
        public ImageView cartAddTextView;
        public TextView nameTextView_pl;
        public TextView priceTextView_pl;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView_pl = itemView.findViewById(R.id.imageView_pl);
            nameTextView_pl = itemView.findViewById(R.id.nameTextView_pl);
            priceTextView_pl = itemView.findViewById(R.id.priceTextView_pl);
            cartAddTextView = itemView.findViewById(R.id.cartAddTextView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Product product = productList.get(position);
        holder.nameTextView_pl.setText(product.getName());
        holder.priceTextView_pl.setText(String.valueOf(product.getUnitPrice()));
        Picasso.get().load(product.getThumbnail()).into(holder.imageView_pl);
        holder.itemView.findViewById(R.id.cartAddTextView);

        holder.cartAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// get the data for this item

                String name = productList.get(position).getName();
                int price = productList.get(position).getUnitPrice();
                String thumbnail = String.valueOf(productList.get(position).getThumbnail());
                Product product = new Product(name, price, thumbnail);

                // create an Intent to start the activity_cart
                Intent intent = new Intent(mContext, CartActivity.class);
                // pass the data to the activity_cart
                intent.putExtra("product", product);
//                intent.putExtra("name", name);
//                intent.putExtra("price", price);
//                intent.putExtra("thumbnail", thumbnail);
//
//                Toast.makeText(mContext, name + ", " + price + ", " + thumbnail, Toast.LENGTH_LONG).show();

                // start the activity_cart
                mContext.startActivity(intent);
            }
        });

    }
    public void release(){
        mContext = null;
    }




    @Override
    public int getItemCount() {
        return productList.size();
    }

}


