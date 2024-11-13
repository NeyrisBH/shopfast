package com.neyrisbh.shopfast;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    displayWelcomeMessage();
                    break;
                case R.id.nav_logout:
                    logout();
                    break;

            }
            drawerLayout.closeDrawers();
            return true;
        });

        // Inicio
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        TextView welcomeText = findViewById(R.id.welcomeTextView);
        if (welcomeText != null) {
            welcomeText.setText("Bienvenido a My Store App");
        }
    }

    private void logout() {
        // Cierra la sesión y redirige al LoginActivity
        // Aquí puedes limpiar la información de la sesión si es necesario
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(findViewById(R.id.navigation_view))) {
            drawerLayout.closeDrawer(findViewById(R.id.navigation_view));
        } else {
            super.onBackPressed();
        }
    }
}
