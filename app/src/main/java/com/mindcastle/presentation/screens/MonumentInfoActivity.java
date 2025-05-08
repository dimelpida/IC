package com.mindcastle.presentation.screens;

import android.widget.ImageButton;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.mindcastle.R;
import com.mindcastle.backend.Monument;
import com.mindcastle.backend.QuestionDataProvider;
import com.mindcastle.backend.UserStateManager;

public class MonumentInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monument_info);

        TextView titleText = findViewById(R.id.monumentTitle);
        TextView descriptionText = findViewById(R.id.monumentDescription);
        ImageView image = findViewById(R.id.monumentImage);
        Button continueButton = findViewById(R.id.continueButton);
        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> finish());

        Intent intent = getIntent();
        String monumentId = intent.getStringExtra("monumentId");

        // Φόρτωση δεδομένων σε background thread
        new Thread(() -> {
            final Monument monument = QuestionDataProvider.getInstance().getMonumentById(monumentId != null ? monumentId : "");

            runOnUiThread(() -> {
                if (monument != null) {
                    titleText.setText(monument.getName());
                    descriptionText.setText(monument.getDescription());

                    int resId = getResources().getIdentifier(monument.getImageUrl(), "drawable", getPackageName());
                    if (resId != 0) {
                        image.setImageResource(resId);
                    } else {
                        image.setImageResource(R.drawable.placeholder);
                    }

                    continueButton.setOnClickListener(v -> {
                        UserStateManager.getInstance().setCurrentMonumentId(monument.getId());
                        Intent quizIntent = new Intent(MonumentInfoActivity.this, QuestionActivity.class);
                        quizIntent.putExtra("monumentId", monument.getId());
                        startActivity(quizIntent);
                    });
                } else {
                    titleText.setText("Monument not found");
                    descriptionText.setText("Could not load data.");
                    image.setImageResource(R.drawable.placeholder);
                    continueButton.setEnabled(false);
                }
            });
        }).start();
    }
}
