package hanu.a2_2001040023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import hanu.a2_2001040023.Apdaters.MyAdapter;
import hanu.a2_2001040023.Apdaters.MyAdapter2;
import hanu.a2_2001040023.Models.Product;
import hanu.a2_2001040023.database.MyCartCRUD;

public class CartActivity extends AppCompatActivity {
    public MyCartCRUD myCartCRUD;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        myCartCRUD = new MyCartCRUD(this);
        MyAdapter2 adapter = new MyAdapter2(CartActivity.this);
        System.out.println(myCartCRUD.getAllProducts());
        List<Product> products = myCartCRUD.getAllProducts();
        for (Product product : products){
            adapter.addData(product);
        }
        recyclerView.setAdapter(adapter);


//        Product product = (Product) getIntent().getSerializableExtra("product");
//        adapter.addData(product);
    }
}