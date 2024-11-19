package com.neyrisbh.shopfast;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neyrisbh.shopfast.adapters.ProductAdapter;
import com.neyrisbh.shopfast.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        productRecyclerView = findViewById(R.id.productRecyclerView);


        productList = new ArrayList<>();
        //productList.add(new Product("Laptop X", 1500.0, R.drawable.laptop_x));
        productList.add(new Product("Auriculares Z", 250.0, R.drawable.product_1));
        productList.add(new Product("SmartWash Y", 1200.0, R.drawable.product_3));
        productList.add(new Product("Tarjeta SD", 800.0, R.drawable.product_2));

        productAdapter = new ProductAdapter(this, productList);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setAdapter(productAdapter);
    }
}
