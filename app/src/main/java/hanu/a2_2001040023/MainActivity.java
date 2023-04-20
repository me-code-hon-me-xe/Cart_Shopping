package hanu.a2_2001040023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hanu.a2_2001040023.Apdaters.MyAdapter;
import hanu.a2_2001040023.Apdaters.MyAdapter2;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.Services.ProductService;
import hanu.a2_2001040023.database.MyCartCRUD;
import hanu.a2_2001040023.database.MyCartDatabaseHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public ImageView cart_icon;
    public MyCartCRUD myCartCRUD;
    public MyAdapter2 adapter2;
    // Database:
    private MyCartDatabaseHelper dbHelper;
    public MyAdapter adapter;
    public List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter2 = new MyAdapter2(MainActivity.this);
        dbHelper = new MyCartDatabaseHelper(this);
        cart_icon = findViewById(R.id.cart_icon);
        cart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCartCRUD = new MyCartCRUD(MainActivity.this);
                productList = myCartCRUD.getAllProducts();
                int totalPrice = 0;
                for(Product product : myCartCRUD.getAllProducts()){
                    totalPrice += product.getProduct_sum_price();
                }
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        });
        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hanu-congnv.github.io/mpr-cart-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a ProductService instance
        ProductService productService = retrofit.create(ProductService.class);

        // make the HTTP request to get all products
        Call<List<Product>> call = productService.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                // get the list of products from the response body
                List<Product> productList = response.body();

                // create and set the adapter for the recycler view
                adapter = new MyAdapter(productList,MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // handle the failure case
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(adapter != null){
            adapter.release();
        }
    }

//    public void updateTotalPrice(){
//        int total = 0;
//        for(Product product : myCartCRUD.getAllProducts()){
//            total += product.getProduct_sum_price();
//        }
//        CartActivity cartActivity = ;
//        cartActivity.updateTotalPrice(total);
//    }
}
