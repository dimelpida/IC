package com.mindcastle.presentation.screens;
//
//import static androidx.core.content.ContextCompat.startActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//import com.mindcastle.R;
//import com.mindcastle.backend.Monument;
//import com.mindcastle.backend.QuestionDataProvider;
//import com.mindcastle.backend.UserStateManager;
//
//import java.util.List;
//
//public class MapActivity extends AppCompatActivity {
//
//    private TextView userGreetingTextView;
//    private TextView scoreTextView;
//    private TextView levelTextView;
//    private ImageView mapImageView;
//    private FrameLayout mapContainer;
//    private CardView infoCardView;
//    private TextView monumentNameTextView;
//    private TextView monumentDescriptionTextView;
//    private Button startQuizButton;
//
//    private List<Monument> monuments;
//    private Monument selectedMonument;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//        // Αρχικοποίηση των στοιχείων του UI
//        userGreetingTextView = findViewById(R.id.userGreetingTextView);
//        scoreTextView = findViewById(R.id.scoreTextView);
//        levelTextView = findViewById(R.id.levelTextView);
//        mapImageView = findViewById(R.id.mapImageView);
//        mapContainer = findViewById(R.id.mapContainer);
//        infoCardView = findViewById(R.id.infoCardView);
//        monumentNameTextView = findViewById(R.id.monumentNameTextView);
//        monumentDescriptionTextView = findViewById(R.id.monumentDescriptionTextView);
//        startQuizButton = findViewById(R.id.startQuizButton);
//
//        // Ενημέρωση των στοιχείων του UI με τις τρέχουσες πληροφορίες
//        updateUI();
//
//        // Προσθήκη των κουμπιών των μνημείων στο χάρτη
//        addMonumentButtons();
//
//        // Event listener για το κουμπί έναρξης κουίζ
//        startQuizButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (selectedMonument != null) {
//                    // Αποθήκευση του τρέχοντος μνημείου και μετάβαση στην οθόνη ερωτήσεων
//                    UserStateManager.getInstance().setCurrentMonumentId(selectedMonument.getId());
//                    UserStateManager.getInstance().addVisitedMonument(selectedMonument.getId());
//
//                    Intent intent = new Intent(MapActivity.this, QuestionActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // Ενημέρωση του UI κάθε φορά που επιστρέφουμε στην οθόνη του χάρτη
//        updateUI();
//
//        // Απόκρυψη της κάρτας πληροφοριών
//        infoCardView.setVisibility(View.GONE);
//    }
//
//    private void updateUI() {
//        UserStateManager userState = UserStateManager.getInstance();
//
//        // Ενημέρωση των στοιχείων του UI
//        userGreetingTextView.setText("Γεια σου, " + userState.getUsername() + "!");
//        scoreTextView.setText("Σκορ: " + userState.getTotalScore());
//        levelTextView.setText("Επίπεδο: " + userState.getCurrentLevel());
//    }
//
//    private void addMonumentButtons() {
//        monuments = QuestionDataProvider.getInstance().getMonuments();
//
//        for (Monument monument : monuments) {
//            // Δημιουργία νέου κουμπιού για κάθε μνημείο
//            Button monumentButton = new Button(this);
//            monumentButton.setText(monument.getName());
//
//            // Ορισμός των παραμέτρων της θέσης του κουμπιού βάσει των συντεταγμένων του μνημείου
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                    FrameLayout.LayoutParams.WRAP_CONTENT,
//                    FrameLayout.LayoutParams.WRAP_CONTENT
//            );
//
//            // Μετατροπή των γεωγραφικών συντεταγμένων σε κανονικοποιημένες συντεταγμένες οθόνης
//            // (απλοποιημένη προσέγγιση - σε πραγματική εφαρμογή θα χρειαζόταν πιο ακριβής υπολογισμός)
//            float normalizedX = (float) ((monument.getLongitude() - 20.85) * 1000); // Προσαρμόστε ανάλογα με το χάρτη
//            float normalizedY = (float) ((39.67 - monument.getLatitude()) * 1000); // Προσαρμόστε ανάλογα με το χάρτη
//
//            params.leftMargin = (int) normalizedX;
//            params.topMargin = (int) normalizedY;
//
//            monumentButton.setLayoutParams(params);
//
//            // Προσαρμογή της εμφάνισης του κουμπιού ανάλογα με το αν έχει επισκεφτεί το μνημείο ο χρήστης
//            if (monument.isVisited()) {
//                monumentButton.setBackgroundResource(R.drawable.button_visited);
//            } else {
//                monumentButton.setBackgroundResource(R.drawable.button_normal);
//            }
//
//            // Event listener για το κουμπί του μνημείου
//            monumentButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedMonument = monument;
//                    showMonumentInfo(monument);
//                }
//            });
//
//            // Προσθήκη του κουμπιού στο container του χάρτη
//            mapContainer.addView(monumentButton);
//        }
//    }
//
//    private void showMonumentInfo(Monument monument) {
//        // Ενημέρωση των στοιχείων της κάρτας πληροφοριών
//        monumentNameTextView.setText(monument.getName());
//        monumentDescriptionTextView.setText(monument.getDescription());
//
//        // Εμφάνιση της κάρτας πληροφοριών
//        infoCardView.setVisibility(View.VISIBLE);
//    }
//}

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

        monuments = QuestionDataProvider.getInstance().getMonuments();

        startQuizButton.setOnClickListener(v -> {
            if (selectedMonument != null) {
                Intent intent = new Intent(MapActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        LatLng ioanninaCastle = new LatLng(39.6685, 20.8544);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ioanninaCastle, 18));

        addMonumentMarkers();
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
            String monumentId = (String) marker.getTag();
            selectedMonument = QuestionDataProvider.getInstance().getMonumentById(monumentId);

            if (selectedMonument != null) {
                showMonumentInfo(selectedMonument);
            } else {
                Log.e("MapActivity", "Μνημείο δεν βρέθηκε για ID: " + monumentId);
            }
        });
    }

    private void showMonumentInfo(Monument monument) {
        // Εμφάνιση της κάρτας πληροφοριών
        infoCardView.setVisibility(View.VISIBLE);
        monumentNameTextView.setText(monument.getName());
        monumentDescriptionTextView.setText(monument.getDescription());
        UserStateManager.getInstance().setCurrentMonumentId(monument.getId());
    }
}