package hanu.a2_2001040023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import hanu.a2_2001040023.Apdaters.MyAdapter;
import hanu.a2_2001040023.Apdaters.MyAdapter2;
import hanu.a2_2001040023.Models.Product;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        MyAdapter2 adapter = new MyAdapter2();
        recyclerView.setAdapter(adapter);

        Product product = (Product) getIntent().getSerializableExtra("product");
        adapter.addData(product);
    }
}