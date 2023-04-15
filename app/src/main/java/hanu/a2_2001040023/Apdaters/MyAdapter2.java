package hanu.a2_2001040023.Apdaters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hanu.a2_2001040023.MainActivity;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.R;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2> {



    private List<Product> productList = new ArrayList<>();

    public void addData(Product product) {
        productList.add(product);
        System.out.println(product.getName());
        System.out.println(productList.size());
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        public ImageView imageView_pl2;
        public TextView nameTextView_pl2;
        public TextView priceTextView_pl2;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            imageView_pl2 = itemView.findViewById(R.id.imageView_pl2);
            nameTextView_pl2 = itemView.findViewById(R.id.nameTextView_pl2);
            priceTextView_pl2 = itemView.findViewById(R.id.priceTextView_pl2);
        }
//        public void bind(Product product) {
//            Picasso.get().load(product.getThumbnail()).into(imageView_pl2);
//            nameTextView_pl2.setText(product.getName());
//            priceTextView_pl2.setText(product.getUnitPrice());
//        }
    }


    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cart_item, parent, false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        Product productModel = productList.get(position);
        holder.nameTextView_pl2.setText(productModel.getName());
        holder.priceTextView_pl2.setText(String.valueOf(productModel.getUnitPrice()));
        Picasso.get().load(productModel.getThumbnail()).into(holder.imageView_pl2);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
