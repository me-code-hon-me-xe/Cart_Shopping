package hanu.a2_2001040023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hanu.a2_2001040023.Apdaters.MyAdapter;
import hanu.a2_2001040023.Apdaters.MyAdapter2;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.database.MyCartCRUD;

public class CartActivity extends AppCompatActivity {
    public MyCartCRUD myCartCRUD;
    private RecyclerView recyclerView;
    public List<Product> products;
    public MyAdapter2 adapter;
    public ImageView cart_icon1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        myCartCRUD = new MyCartCRUD(this);
        adapter = new MyAdapter2(CartActivity.this);
        products = myCartCRUD.getAllProducts();
        System.out.println(products);
        for (Product product : products){
            adapter.addData(product);
        }
        recyclerView.setAdapter(adapter);
        cart_icon1 = findViewById(R.id.cart_icon1);
        cart_icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotalPrice();
            }
        });
    }


    private void updateTotalPrice() {
        int totalPrice = 0;
        for (Product product : products) {
            System.out.println(product);
            totalPrice += product.getProduct_sum_price();
        }
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(Integer.toString(totalPrice));
    }
}