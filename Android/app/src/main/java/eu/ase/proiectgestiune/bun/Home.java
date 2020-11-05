package eu.ase.proiectgestiune.bun;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.fragment.Cheltuieli;
import eu.ase.proiectgestiune.bun.fragment.DetaliiClienti;
import eu.ase.proiectgestiune.bun.fragment.ListaApartamente;
import eu.ase.proiectgestiune.bun.fragment.ListaClienti;
import eu.ase.proiectgestiune.bun.fragment.Profit;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        configNavigation();
        NavigationView navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState ==null) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ListaApartamente()).commit();
        navigationView.setCheckedItem(R.id.listaAp);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId())
        {
            case R.id.listaAp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ListaApartamente()).commit();
                break;
            case R.id.detaliiClienti:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DetaliiClienti()).commit();
                break;
            case R.id.tranzactii_clienti:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ListaClienti()).commit();
                break;
            case R.id.profit:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Profit()).commit();
                break;
            case R.id.cheltuieli:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Cheltuieli()).commit();
                break;

            case R.id.logOut:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void configNavigation() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle  actionbar= new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open, R.string.open);
        drawer.addDrawerListener(actionbar);
        actionbar.syncState();

    }
    public void onBackPressed()
    {   if(drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
    }
    else {
        super.onBackPressed(); }
    }
}
