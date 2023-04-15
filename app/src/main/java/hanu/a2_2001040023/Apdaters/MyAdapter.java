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

import java.util.ArrayList;
import java.util.List;

import hanu.a2_2001040023.CartActivity;
import hanu.a2_2001040023.MainActivity;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.R;
import hanu.a2_2001040023.database.MyCartCRUD;
import hanu.a2_2001040023.database.MyCartDatabaseHelper;
import retrofit2.Callback;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private MyCartDatabaseHelper dbHelper;

    private List<Product> productList;
    private Product product;
    private List<Product> productListInMyCart;
    private Context mContext;
    private int COLUMN_PRODUCT_QUANTITY;
    public MyCartCRUD myCartCRUD;
    public MyAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
        myCartCRUD = new MyCartCRUD(mContext);
        productListInMyCart = new ArrayList<>();
        COLUMN_PRODUCT_QUANTITY = 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_pl;
        public ImageView cartAddImageView;
        public TextView nameTextView_pl;
        public TextView priceTextView_pl;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView_pl = itemView.findViewById(R.id.imageView_pl);
            nameTextView_pl = itemView.findViewById(R.id.nameTextView_pl);
            priceTextView_pl = itemView.findViewById(R.id.priceTextView_pl);
            cartAddImageView = itemView.findViewById(R.id.cartAddImageView);
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

        product = productList.get(position);
        holder.nameTextView_pl.setText(product.getName());
        holder.priceTextView_pl.setText(String.valueOf(product.getUnitPrice()));
        Picasso.get().load(product.getThumbnail()).into(holder.imageView_pl);
        holder.itemView.findViewById(R.id.cartAddImageView);

        holder.cartAddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productListInMyCart.contains(product)) {
                    COLUMN_PRODUCT_QUANTITY = COLUMN_PRODUCT_QUANTITY + 1;
                } else {
                    COLUMN_PRODUCT_QUANTITY = 1;
                }
                // get the data for this item
                String COLUMN_PRODUCT_NAME = productList.get(position).getName();
                int COLUMN_PRODUCT_PRICE = productList.get(position).getUnitPrice();
                int COLUMN_PRODUCT_SUM_PRICE = COLUMN_PRODUCT_PRICE * COLUMN_PRODUCT_QUANTITY;
                String COLUMN_PRODUCT_THUMBNAIL = String.valueOf(productList.get(position).getThumbnail());
                Product productCart = new Product(COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_PRICE, COLUMN_PRODUCT_THUMBNAIL, COLUMN_PRODUCT_QUANTITY, COLUMN_PRODUCT_SUM_PRICE);

                // Check if product exit or not
                if (productListInMyCart.contains(productCart)) {
                    //
                } else {
                    productListInMyCart.add(productCart);
                    myCartCRUD.insert(productCart);
                }
                System.out.println("Size of productListInMyCart: " + productListInMyCart.size());

                // create an Intent to start the activity_cart
                Intent intent = new Intent(mContext, CartActivity.class);
                // pass the data to the activity_cart
                intent.putExtra("product", productCart);
                mContext.startActivity(intent);
            }
        });

    }

    public void release() {
        mContext = null;
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

}


