package com.neyrisbh.shopfast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neyrisbh.shopfast.models.Product;

import java.util.List;

public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.ProductViewHolder> {

    private final List<Product> productList;

    public AdapterProducts(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("Precio: $" + product.getPrice());
        holder.tvQuantity.setText("Cantidad: " + product.getQuantity());

        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_image)
                .into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQuantity;
        ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtProductName);
            tvPrice = itemView.findViewById(R.id.txtProductPrice);
            tvQuantity = itemView.findViewById(R.id.txtProductStock);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}