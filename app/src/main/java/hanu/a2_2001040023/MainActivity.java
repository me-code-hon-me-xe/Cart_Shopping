package hanu.a2_2001040023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hanu.a2_2001040023.Apdaters.MyAdapter;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.Services.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}
