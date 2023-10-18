package pl.edu.pb.wi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswerCorrectness(true);
                    }
                }
        );

        falseButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswerCorrectness(false);
                    }
                }
        );

        nextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentIndex = (currentIndex + 1)%questions.length;
                        setNextQuestion();
                    }
                }
        );
        setNextQuestion();

    }

    private Question[] questions = new Question[] {
            new Question(R.string.q_worldCup, true),
            new Question(R.string.q_lewy, true),
            new Question(R.string.q_match, false),
            new Question(R.string.q_manu, false),
            new Question(R.string.q_messi, true)

    };
    private int currentIndex = 0;
    private int correctAnswerCount = 0;

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer)
        {
            correctAnswerCount += 1;
            resultMessageId = R.string.correctAnswer;
        }
        else resultMessageId = R.string.incorrectAnswer;

        if(currentIndex == questions.length - 1)
        {
            String finalResultMessage = getString(R.string.q_score, correctAnswerCount, questions.length);
            Toast.makeText(this, finalResultMessage, Toast.LENGTH_LONG).show();
            correctAnswerCount = 0;
        }
        else
        {
            Toast.makeText(this,resultMessageId, Toast.LENGTH_SHORT).show();
        }
    }

    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }



}