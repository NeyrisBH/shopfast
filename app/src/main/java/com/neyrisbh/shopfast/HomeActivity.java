package com.neyrisbh.shopfast;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleMenuItemClick(item.getItemId());
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Error al cargar el mapa", Toast.LENGTH_SHORT).show();
        }

        Button exploreButton = findViewById(R.id.btn_explore);
        exploreButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
            startActivity(intent);
        });
    }

    private void handleMenuItemClick(int itemId) {
        switch (itemId) {
            case R.id.nav_home:
                Toast.makeText(this, "Inicio seleccionado", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_products:
                Intent productsIntent = new Intent(this, ProductsActivity.class);
                startActivity(productsIntent);
                break;

            //case R.id.nav_cart:
            //    Intent cartIntent = new Intent(this, CartActivity.class);
            //    startActivity(cartIntent);
            //    break;

            //case R.id.nav_profile:
            //    Intent profileIntent = new Intent(this, ProfileActivity.class);
            //    startActivity(profileIntent);
            //    break;

            case R.id.nav_logout:
                handleLogout();
                break;

            default:
                Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void handleLogout() {
        Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show();
        Intent logoutIntent = new Intent(this, LoginActivity.class);
        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logoutIntent);
        finish();
    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        com.google.android.gms.maps.model.LatLng storeLocation = new com.google.android.gms.maps.model.LatLng(3.4372, -76.5226);
        googleMap.addMarker(new com.google.android.gms.maps.model.MarkerOptions()
                .position(storeLocation)
                .title("Tienda ShopFast"));
        googleMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(storeLocation, 15));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}