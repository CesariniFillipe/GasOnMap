package com.toninho.gasonmap;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.text.Text;
import com.toninho.gasonmap.database.DBController;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
}


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(

                new LatLng(-21.444383, -45.950427), 18));

        mMap.setInfoWindowAdapter(new InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);

                TextView posto = (TextView) v.findViewById(R.id.posto);
                TextView precos = (TextView) v.findViewById(R.id.precos);

                posto.setText(marker.getTitle());
                precos.setText(marker.getSnippet());


                return v;
            }
        });
        addAllPostos();
        /*
        mMap.addMarker(new MarkerOptions()

                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                .anchor(0.0f, 1.0f)
                .title("Posto Tamandaré")
                .position(new LatLng(-21.444383, -45.950427)));


        mMap.addMarker(new MarkerOptions()

                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                .anchor(0.0f, 1.0f)
                .title("Posto Terra")
                .position(new LatLng(-21.425763, -45.959480)));

        mMap.addMarker(new MarkerOptions()

                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                .anchor(0.0f, 1.0f)
                .title("Posto Souza Ltda.")
                .position(new LatLng(-21.401540, -45.947648)));

        mMap.addMarker(new MarkerOptions()

                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                .anchor(0.0f, 1.0f)
                .title("Auto Posto BR 2000")
                .position(new LatLng(-21.416311, -45.947327)));

        mMap.addMarker(new MarkerOptions()

                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                .anchor(0.0f, 1.0f)
                .title("Alves e Cia Ltda.")
                .position(new LatLng(-21.436166, -45.948260)));

        mMap.addMarker(new MarkerOptions()

                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                .anchor(0.0f, 1.0f)
                .title("Auto Posto Alfenas")
                .position(new LatLng(-21.430267, -45.949411)));

        mMap.addMarker(new MarkerOptions()

                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                .anchor(0.0f, 1.0f)
                .title("Jaguar Auto Posto")
                .position(new LatLng(-21.429491, -45.943442)));

        mMap.addMarker(new MarkerOptions()

                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                .anchor(0.0f, 1.0f)
                .title("Central Posto")
                .position(new LatLng(-21.423428, -45.948346)));*/
    }

    public void addAllPostos(){
        DBController db = new DBController(getBaseContext());
        Cursor postos = db.getAllPostos();

        for(int i = 0; i < postos.getCount(); i++){
            Posto p = new Posto(postos.getString(0), postos.getString(1), postos.getString(2),
                    postos.getString(3), postos.getString(4), postos.getString(5), postos.getString(6));
            mMap.addMarker(new MarkerOptions()

                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.gas))
                    .anchor(0.0f, 1.0f)
                    .title(p.getNome())
                    .snippet("\nGasolina      R$ "+p.getGas()+
                              "\nAlcool          R$ "+p.getAlc()+
                              "\nDiesel          R$ "+p.getDie())
                    .position(new LatLng(Double.valueOf(p.getLat()), Double.valueOf(p.getLng()))));
        }
    }
}