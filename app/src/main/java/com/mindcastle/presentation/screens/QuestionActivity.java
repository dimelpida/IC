package com.mindcastle.presentation.screens;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mindcastle.R;
import com.mindcastle.backend.Monument;
import com.mindcastle.backend.Question;
import com.mindcastle.backend.QuestionDataProvider;
import com.mindcastle.backend.UserStateManager;
import com.mindcastle.presentation.MainActivity;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private ImageView backgroundImageView;
    private TextView questionNumberTextView;
    private TextView scoreTextView;
    private TextView questionTextView;
    private RadioGroup answersRadioGroup;
    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private RadioButton option4RadioButton;
    private Button submitAnswerButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private ProgressBar progressBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        String intentMonumentId = getIntent().getStringExtra("monumentId");
        if (intentMonumentId != null) {
            UserStateManager.getInstance().setCurrentMonumentId(intentMonumentId);
        }
        setContentView(R.layout.activity_question);


        // Αρχικοποίηση των στοιχείων του UI

        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        answersRadioGroup = findViewById(R.id.answersRadioGroup);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        option4RadioButton = findViewById(R.id.option4RadioButton);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);
        progressBar = findViewById(R.id.progressBar);


        new Thread(() -> {
            String currentMonumentId = UserStateManager.getInstance().getCurrentMonumentId();
            questions = QuestionDataProvider.getInstance().getQuestionsForMonument(currentMonumentId);

            runOnUiThread(() -> {
                if (questions == null || questions.isEmpty()) {
                    Toast.makeText(QuestionActivity.this, "No questions found for this monument", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    progressBar.setMax(questions.size()); //mpara pou
                    progressBar.setProgress(currentQuestionIndex + 1);
                    updateUI();
                }
            });
        }).start();



        submitAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Έλεγχος αν έχει επιλεχθεί κάποια απάντηση
                if (answersRadioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(QuestionActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedIndex = -1;
                if (option1RadioButton.isChecked()) selectedIndex = 0;
                else if (option2RadioButton.isChecked()) selectedIndex = 1;
                else if (option3RadioButton.isChecked()) selectedIndex = 2;
                else if (option4RadioButton.isChecked()) selectedIndex = 3;

                Question currentQuestion = questions.get(currentQuestionIndex);
                boolean isCorrect = currentQuestion.checkAnswer(selectedIndex);

                String explanation = currentQuestion.getExplanation();

                showResultDialog(isCorrect, explanation);
            }
        });
    }

    private void updateUI() {
        UserStateManager userState = UserStateManager.getInstance();
        Question currentQuestion = questions.get(currentQuestionIndex);

        questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.size());
        scoreTextView.setText("Score: " + userState.getTotalScore());
        questionTextView.setText(currentQuestion.getQuestion());


        List<String> options = currentQuestion.getOptions();
        option1RadioButton.setText(options.get(0));
        option2RadioButton.setText(options.get(1));
        option3RadioButton.setText(options.get(2));
        option4RadioButton.setText(options.get(3));

        answersRadioGroup.clearCheck();

        int resId = getResources().getIdentifier(
                currentQuestion.getImageUrl(),
                "drawable", // oxi null εδώ
                getPackageName()
        );


        if (resId != 0) {
            backgroundImageView.setImageResource(resId);
        }

        progressBar.setProgress(currentQuestionIndex + 1);

    }


    private void showResultDialog(boolean isCorrect, String explanation) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_question_result);
        dialog.setCancelable(false);

        TextView resultTitleTextView = dialog.findViewById(R.id.resultTitleTextView);
        TextView resultMessageTextView = dialog.findViewById(R.id.resultMessageTextView);
        TextView explanationTextView = dialog.findViewById(R.id.explanationTextView);
        Button nextQuestionButton = dialog.findViewById(R.id.nextQuestionButton);

        TextView scoreUpdateTextView = dialog.findViewById(R.id.scoreUpdateTextView);

        if (isCorrect) {
            correctAnswers++;
            UserStateManager.getInstance().addPoints(10);
            scoreTextView.setText("Score: "+ UserStateManager.getInstance().getTotalScore());

            resultTitleTextView.setText("Correct!");
            resultMessageTextView.setText("Congratulations! Your answer is correct!");
            explanationTextView.setVisibility(View.GONE);
        } else {
            resultTitleTextView.setText("Incorrect!");
            resultMessageTextView.setText("Your answer is incorrect. See more information:");

            explanationTextView.setVisibility(View.VISIBLE);
            explanationTextView.setText(explanation);
            scoreUpdateTextView.setVisibility(View.GONE); //  Hide +10 points
        }

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    updateUI();
                } else {
                    showQuizCompletedDialog();
                }

            }
        });

        dialog.show();
    }

    private void showQuizCompletedDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_quiz_completed);
        dialog.setCancelable(false);

        TextView completedTitleTextView = dialog.findViewById(R.id.completedTitleTextView);
        TextView monumentNameTextView = dialog.findViewById(R.id.monumentNameTextView);
        TextView scoreTextView = dialog.findViewById(R.id.scoreTextView);
        TextView levelUpTextView = dialog.findViewById(R.id.levelUpTextView);
        Button continueButton = dialog.findViewById(R.id.continueButton);
        Button exitButton = dialog.findViewById(R.id.exitButton);

        String monumentId = UserStateManager.getInstance().getCurrentMonumentId();
        Monument monument = QuestionDataProvider.getInstance().getMonumentById(monumentId);

        completedTitleTextView.setText("Quiz Completed!");
        monumentNameTextView.setText("Monument: " + monument.getName());
        scoreTextView.setText("Total Score: " + UserStateManager.getInstance().getTotalScore());

        if (correctAnswers >= 3) {
            UserStateManager.getInstance().incrementLevel();
            levelUpTextView.setText("Congratulations! You've leveled up!");
        } else {
            levelUpTextView.setText("You need at least 3 correct answers to level up.");
        }

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // Πήγαινε στον χάρτη μετά το quiz
                Intent intent = new Intent(QuestionActivity.this, MapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
    }
}