package hanu.a2_2001040023.Apdaters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hanu.a2_2001040023.CartActivity;
import hanu.a2_2001040023.MainActivity;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.R;
import hanu.a2_2001040023.database.MyCartCRUD;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2> {

    public CartActivity cartActivity;
    public int COLUMN_PRODUCT_QUANTITY;

    public MyCartCRUD myCartCRUD;
    private Context mContext;
    private List<Product> productList;
    private DecimalFormat formatter;

    public MyAdapter2(Context mContext) {
        cartActivity = new CartActivity();
        productList = new ArrayList<>();
        this.mContext = mContext;
        myCartCRUD = new MyCartCRUD(mContext);
    }

    public void addData(Product product) {
        productList.add(product);
        System.out.println(product.getName());
        System.out.println(productList.size());
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        public ImageView imageView_pl2;
        public TextView nameTextView_pl2;
        public TextView priceTextView_pl2;
        public TextView textViewQuantity;
        public TextView sumPriceTextView;
        public ImageView imageViewRemoveQuantity;
        public ImageView imageViewAddQuantity;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            imageView_pl2 = itemView.findViewById(R.id.imageView_pl2);
            nameTextView_pl2 = itemView.findViewById(R.id.nameTextView_pl2);
            priceTextView_pl2 = itemView.findViewById(R.id.priceTextView_pl2);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            sumPriceTextView = itemView.findViewById(R.id.sumPriceTextView);
            imageViewRemoveQuantity = itemView.findViewById(R.id.imageViewRemoveQuantity);
            imageViewAddQuantity = itemView.findViewById(R.id.imageViewAddQuantity);

        }
    }


    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cart_item, parent, false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, @SuppressLint("RecyclerView") int position) {
        Product productModel = productList.get(position);

        formatter = new DecimalFormat("#,###");
        holder.nameTextView_pl2.setText(productModel.getName());
        holder.priceTextView_pl2.setText(String.valueOf(formatter.format(productModel.getUnitPrice())));
        holder.textViewQuantity.setText(String.valueOf(productModel.getQuantity()));
        holder.sumPriceTextView.setText(String.valueOf(formatter.format(productModel.getProduct_sum_price())));
        Picasso.get().load(productModel.getThumbnail()).into(holder.imageView_pl2);
        holder.imageViewAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                COLUMN_PRODUCT_QUANTITY = myCartCRUD.getProductById(productModel.getId()).getQuantity();
                int COLUMN_PRODUCT_ID = productModel.getId();
                COLUMN_PRODUCT_QUANTITY = COLUMN_PRODUCT_QUANTITY + 1;
                String COLUMN_PRODUCT_NAME = productModel.getName();
                int COLUMN_PRODUCT_PRICE = productModel.getUnitPrice();
                int COLUMN_PRODUCT_SUM_PRICE = COLUMN_PRODUCT_PRICE * COLUMN_PRODUCT_QUANTITY;
                String COLUMN_PRODUCT_THUMBNAIL = String.valueOf(productModel.getThumbnail());
                Product productCart = new Product(COLUMN_PRODUCT_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_PRICE, COLUMN_PRODUCT_THUMBNAIL, COLUMN_PRODUCT_QUANTITY, COLUMN_PRODUCT_SUM_PRICE);
                if (myCartCRUD.isProductExists(productCart.getId()) == true) {
                    System.out.println("exit");
                } else {
                    System.out.println("not exit");
                }
//                System.out.println(myCartCRUD.getAllProducts());
                myCartCRUD.update(productCart);
//                System.out.println(myCartCRUD.getAllProducts());
                holder.textViewQuantity.setText(String.valueOf(productCart.getQuantity()));
                holder.sumPriceTextView.setText(String.valueOf(formatter.format(productCart.getProduct_sum_price())));
            }
        });

        holder.imageViewRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                COLUMN_PRODUCT_QUANTITY = myCartCRUD.getProductById(productModel.getId()).getQuantity();
                System.out.println(productModel);
                int COLUMN_PRODUCT_ID = productModel.getId();
                COLUMN_PRODUCT_QUANTITY = COLUMN_PRODUCT_QUANTITY - 1;
                String COLUMN_PRODUCT_NAME = productModel.getName();
                int COLUMN_PRODUCT_PRICE = productModel.getUnitPrice();
                int COLUMN_PRODUCT_SUM_PRICE = COLUMN_PRODUCT_PRICE * COLUMN_PRODUCT_QUANTITY;
                String COLUMN_PRODUCT_THUMBNAIL = String.valueOf(productModel.getThumbnail());
                Product productCart = new Product(COLUMN_PRODUCT_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_PRICE, COLUMN_PRODUCT_THUMBNAIL, COLUMN_PRODUCT_QUANTITY, COLUMN_PRODUCT_SUM_PRICE);
                if (productCart.getQuantity() == 0) {
                    productList.remove(position);
                    myCartCRUD.delete(productCart);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, productList.size());
                }else{
                    myCartCRUD.update(productCart);
                    System.out.println(myCartCRUD.getAllProducts());
                    holder.textViewQuantity.setText(String.valueOf(productCart.getQuantity()));
                    holder.sumPriceTextView.setText(String.valueOf(formatter.format(productCart.getProduct_sum_price())));
                }
                }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
