package hanu.a2_2001040023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hanu.a2_2001040023.Apdaters.MyAdapter;
import hanu.a2_2001040023.Apdaters.MyAdapter2;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.database.MyCartCRUD;
import hanu.a2_2001040023.database.MyCartDatabaseHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public ImageView cart_icon;
    public MyCartCRUD myCartCRUD;
    public MyAdapter2 adapter2;
    // Database:
    private MyCartDatabaseHelper dbHelper;
    public MyAdapter adapter;
    public List<Product> productList;

    public SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        adapter = new MyAdapter(new ArrayList<>(), this);
        dbHelper = new MyCartDatabaseHelper(this);

        search = findViewById(R.id.search);
        search.setOnQueryTextListener(this);


        cart_icon = findViewById(R.id.cart_icon);
        cart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCartCRUD = new MyCartCRUD(MainActivity.this);
                productList = myCartCRUD.getAllProducts();
                int totalPrice = 0;
                for (Product product : myCartCRUD.getAllProducts()) {
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://hanu-congnv.github.io/mpr-cart-api/products.json");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();

                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    String jsonString = sb.toString();

                    Gson gson = new Gson();
                    Type productListType = new TypeToken<List<Product>>(){}.getType();
                    List<Product> productList = gson.fromJson(jsonString, productListType);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // create and set the adapter for the recycler view
                            adapter = new MyAdapter(productList, MainActivity.this);
                            recyclerView.setAdapter(adapter);
                        }
                    });

                    reader.close();
                    inputStream.close();
                    conn.disconnect();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.release();
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        search.clearFocus();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (adapter != null) {
            adapter.getFilter().filter(newText);
        }
        return false;
    }

}
