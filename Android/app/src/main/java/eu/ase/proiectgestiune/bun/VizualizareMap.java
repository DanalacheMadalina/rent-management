package eu.ase.proiectgestiune.bun;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import eu.ase.proiectgestiune.R;

import static eu.ase.proiectgestiune.bun.ItemDetailsLV_ap_Libere.KEY_FOR_LOCATION;
import static eu.ase.proiectgestiune.bun.Lista_Ap_Libere.KEY_SHOW_ITEM;

public class VizualizareMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<CoordonateApartament> listaAp = new ArrayList<>();
    CoordonateApartament coordap;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        intent = getIntent();
        if (intent.hasExtra(KEY_FOR_LOCATION)) {
            coordap = intent.getParcelableExtra(KEY_FOR_LOCATION);
            listaAp.add(coordap);
            LatLng positionLoc = new LatLng(coordap.getLatitude(), coordap.getLongitude());
            options.position(positionLoc);
            options.title("Bucuresti");
            options.snippet(coordap.getStrada());
            googleMap.addMarker(options);


        }
    }
}
