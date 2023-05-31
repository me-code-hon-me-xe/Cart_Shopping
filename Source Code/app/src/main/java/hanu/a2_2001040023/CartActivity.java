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

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import hanu.a2_2001040023.Apdaters.MyAdapter;
import hanu.a2_2001040023.Apdaters.MyAdapter2;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.database.MyCartCRUD;

public class CartActivity extends AppCompatActivity {
    public MyCartCRUD myCartCRUD;

    private DecimalFormat formatter;
    private RecyclerView recyclerView;
    public List<Product> products;
    public MyAdapter2 adapter;
    public ImageView cart_icon1;
    public Intent intent;
    public TextView totalPriceTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        formatter = new DecimalFormat("#,###");
        recyclerView = findViewById(R.id.recyclerView2);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        intent = getIntent();
        int total = intent.getIntExtra("totalPrice",0);
        totalPriceTextView.setText(formatter.format(total));
        myCartCRUD = new MyCartCRUD(this);
        adapter = new MyAdapter2(CartActivity.this);
        products = myCartCRUD.getAllProducts();
        for (Product product : products){
            adapter.addData(product);
        }
        recyclerView.setAdapter(adapter);

    }


    public void updateTotalPrice(int total) {
        formatter = new DecimalFormat("#,###");
        totalPriceTextView.setText(formatter.format(total));
    }
}