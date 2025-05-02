package com.mindcastle.presentation.screens

import android.widget.ImageButton
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mindcastle.R
import com.mindcastle.backend.QuestionDataProvider
import com.mindcastle.backend.UserStateManager


class MonumentInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monument_info)

        val titleText = findViewById<TextView>(R.id.monumentTitle)
        val descriptionText = findViewById<TextView>(R.id.monumentDescription)
        val image = findViewById<ImageView>(R.id.monumentImage)
        val continueButton = findViewById<Button>(R.id.continueButton)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()  // Απλή επιστροφή στο προηγούμενο activity
        }

        val monumentId = intent.getStringExtra("monumentId")

        // Φόρτωση δεδομένων σε background thread
        Thread {
            val monument = QuestionDataProvider.getInstance().getMonumentById(monumentId ?: "")

            runOnUiThread {
                if (monument != null) {
                    titleText.text = monument.name
                    descriptionText.text = monument.description

                    val resId = resources.getIdentifier(monument.imageUrl, "drawable", packageName)
                    if (resId != 0) {
                        image.setImageResource(resId)
                    } else {
                        image.setImageResource(R.drawable.placeholder)
                    }

                    continueButton.setOnClickListener {
                        UserStateManager.getInstance().setCurrentMonumentId(monument.id)
                        val intent = Intent(this, QuestionActivity::class.java)
                        intent.putExtra("monumentId", monument.id)
                        startActivity(intent)
                    }
                } else {
                    titleText.text = "Μνημείο δεν βρέθηκε"
                    descriptionText.text = "Δεν ήταν δυνατή η φόρτωση των στοιχείων."
                    image.setImageResource(R.drawable.placeholder)
                    continueButton.isEnabled = false
                }
            }
        }.start()
    }
}
