package com.neyrisbh.shopfast;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neyrisbh.shopfast.models.Product;

import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecyclerView rvProducts;
    private AdapterProducts productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        databaseHelper = new DatabaseHelper(this);
        rvProducts = findViewById(R.id.rvProducts);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));

        Button btnAddProduct = findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(v -> {
            // Ejemplo: Agregar producto
            databaseHelper.addProduct(new Product(0, "Producto X", 50.0, 10));
            loadProducts();
        });

        loadProducts();
    }

    private void loadProducts() {
        List<Product> products = databaseHelper.getAllProducts();
        productAdapter = new AdapterProducts(products);
        rvProducts.setAdapter(productAdapter);
    }
}
