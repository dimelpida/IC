package com.mindcastle.presentation.screens;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.mindcastle.R;
import com.mindcastle.backend.Monument;
import com.mindcastle.backend.QuestionDataProvider;
import com.mindcastle.backend.UserStateManager;
import java.util.List;

import android.widget.Button;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CardView infoCardView;
    private TextView monumentNameTextView;
    private TextView monumentDescriptionTextView;
    private Button startQuizButton;
    private List<Monument> monuments;
    private Monument selectedMonument;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        infoCardView = findViewById(R.id.infoCardView);
        monumentNameTextView = findViewById(R.id.monumentNameTextView);
        monumentDescriptionTextView = findViewById(R.id.monumentDescriptionTextView);
        startQuizButton = findViewById(R.id.startQuizButton);

        startQuizButton.setOnClickListener(v -> {
            if (selectedMonument != null) {
                Intent intent = new Intent(MapActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

        // εβαλα το getMonuments σε background thread
        new Thread(() -> {
            List<Monument> loadedMonuments = QuestionDataProvider.getInstance().getMonuments();
            runOnUiThread(() -> {
                this.monuments = loadedMonuments;

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.mapFragment);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(this);
                }
            });
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Ξαναφόρτωσε monuments αν χρειάζεται
        monuments = QuestionDataProvider.getInstance().getMonuments();
        if (mMap != null) {
            mMap.clear(); // Καθαρίζει τα markers
            addMonumentMarkers(); // Τα ξαναβάζει
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        LatLng ioanninaCastle = new LatLng(39.671119626595534, 20.86253291614562);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ioanninaCastle, 17));

        // Δεν κάνουμε εδώ addMonumentMarkers εκτός αν τα δεδομένα είναι ήδη έτοιμα
        if (monuments != null && !monuments.isEmpty()) {
            addMonumentMarkers();
        }
    }

    private void addMonumentMarkers() {
        for (Monument monument : monuments) {
            LatLng monumentLocation = new LatLng(monument.getLatitude(), monument.getLongitude());
            mMap.addMarker(new MarkerOptions()
                            .position(monumentLocation)
                            .title(monument.getName())
                            .snippet(monument.getDescription()))
                    .setTag(monument.getId());
        }

        mMap.setOnInfoWindowClickListener(marker -> {
            Object tag = marker.getTag();
            if (tag != null && tag instanceof String) {
                String monumentId = (String) tag;
                Monument monument = QuestionDataProvider.getInstance().getMonumentById(monumentId);

                if (monument != null) {
                    Intent intent = new Intent(MapActivity.this, MonumentInfoActivity.class);
                    intent.putExtra("monumentId", monument.getId());
                    startActivity(intent);
                } else {
                    Log.e("MapActivity", "Monument with ID: " + monumentId+" Not found");
                }
            } else {
                Log.e("MapActivity", "tag  is null or wrong type");
            }
        });


    }
}
