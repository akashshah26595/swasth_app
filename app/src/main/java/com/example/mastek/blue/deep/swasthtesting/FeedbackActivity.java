package com.example.mastek.blue.deep.swasthtesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private int pos = 0;
    private boolean flag = false;
    private LinearLayout feedbackLinearLayout;
    private LinearLayout answerLinearLayout;
    private QuestionsAdapter questionsAdapter;
    private AnswersAdapter answersAdapter;
    private RadioGroup optionsRadioGroup;
    private Map<String, String> hashMap;
    private ScrollView mainScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Button nextButton = (Button) findViewById(R.id.nextButton);
        Button previousButton = (Button) findViewById(R.id.previousButton);
        mainScrollView = (ScrollView) findViewById(R.id.mainScrollView);

        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);

        try {
            InputStream inputStream1 = getAssets().open("feedback.json");
            String response1 = IOUtils.toString(inputStream1);

            InputStream inputStream2 = getAssets().open("feedbackanswers.json");
            String response2 = IOUtils.toString(inputStream2);

            Gson gson1 = new GsonBuilder().create();
            Questions feedbackCollection = gson1.fromJson(response1, Questions.class);

            Answers answersCollection = gson1.fromJson(response2, Answers.class);

//            Toast.makeText(this, feedbackCollection.questions[0].question, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(answersCollection.answers.length), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, answersCollection.answers[0].option1, Toast.LENGTH_SHORT).show();

            feedbackLinearLayout = (LinearLayout) findViewById(R.id.feedbackLinearLayout);
            questionsAdapter = new QuestionsAdapter(this, feedbackCollection);

            answerLinearLayout = (LinearLayout) findViewById(R.id.answerLinearLayout);
            answersAdapter = new AnswersAdapter(this, answersCollection);
            feedbackLinearLayout.addView(questionsAdapter.getView(pos, null, feedbackLinearLayout));
            answerLinearLayout.addView(answersAdapter.getView(pos, null, answerLinearLayout));

            hashMap = new HashMap<>(feedbackCollection.questions.length);
            optionsRadioGroup = (RadioGroup) findViewById(R.id.optionsRadioGroup);
            optionsRadioGroup.setOnCheckedChangeListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        //mainScrollView.smoothScrollTo(0, 0);
        //mainScrollView.pageScroll(View.FOCUS_UP);

        // Wait until my scrollView is ready
        mainScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ready, move up
                mainScrollView.fullScroll(View.FOCUS_UP);
                mainScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        switch (v.getId()) {
            case R.id.nextButton:
                nextQuestion();
                break;
            case R.id.previousButton:
                previousQuestion();
                break;
        }
    }

    private void nextQuestion() {
        if (flag) {
            pos++;
            if (pos >= questionsAdapter.getCount()) {
                Toast.makeText(this, "Feedback finished!", Toast.LENGTH_SHORT).show();
                Map<String, String> sortedMap = new TreeMap<>(hashMap);
                Toast.makeText(this, "Map is: " + sortedMap, Toast.LENGTH_LONG).show();
                pos = questionsAdapter.getCount() - 1;
//
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.putExtra("map", sortedMap);
//                startActivity(intent);
//                finish();
            } else {
                feedbackLinearLayout.removeViewAt(0);
                answerLinearLayout.removeViewAt(0);
                feedbackLinearLayout.addView(questionsAdapter.getView(pos, null, feedbackLinearLayout));
                answerLinearLayout.addView(answersAdapter.getView(pos, null, answerLinearLayout));
                saveRadioState();
                if (!hashMap.containsKey(Integer.toString(pos + 1))) {
                    flag = false;
                }
            }
        } else {
            Toast.makeText(this, "Select a choice!", Toast.LENGTH_SHORT).show();
        }
    }

    private void previousQuestion() {
        pos--;
        if (pos < 0) {
            Toast.makeText(this, "This is the first question!", Toast.LENGTH_SHORT).show();
            pos = 0;
        } else {
            feedbackLinearLayout.removeViewAt(0);
            answerLinearLayout.removeViewAt(0);
            feedbackLinearLayout.addView(questionsAdapter.getView(pos, null, feedbackLinearLayout));
            answerLinearLayout.addView(answersAdapter.getView(pos, null, answerLinearLayout));

            saveRadioState();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        flag = true;
        switch (checkedId) {
            case R.id.option1:
                hashMap.put(Integer.toString(pos + 1), "1");
                //Toast.makeText(this, "Hashmap: " + hashMap, Toast.LENGTH_LONG).show();
                break;
            case R.id.option2:
                //Toast.makeText(this, "Opt 2", Toast.LENGTH_SHORT).show();
                hashMap.put(Integer.toString(pos + 1), "2");
                break;
            case R.id.option3:
                //Toast.makeText(this, "Opt 3", Toast.LENGTH_SHORT).show();
                hashMap.put(Integer.toString(pos + 1), "3");
                break;
            case R.id.option4:
                //Toast.makeText(this, "Opt 4", Toast.LENGTH_SHORT).show();
                hashMap.put(Integer.toString(pos + 1), "4");
                break;
            case R.id.option5:
                //Toast.makeText(this, "Opt 5", Toast.LENGTH_SHORT).show();
                hashMap.put(Integer.toString(pos + 1), "5");
                break;
        }
    }

    private void saveRadioState() {
        //loads saved value of radio button and makes it accept change in radio value
        optionsRadioGroup = (RadioGroup) findViewById(R.id.optionsRadioGroup);

        String val = hashMap.get(Integer.toString(pos + 1));
        if (val != null) {
            if (Integer.parseInt(val) == 1)
                optionsRadioGroup.check(R.id.option1);
            else if (Integer.parseInt(val) == 2)
                optionsRadioGroup.check(R.id.option2);
            else if (Integer.parseInt(val) == 3)
                optionsRadioGroup.check(R.id.option3);
            else if (Integer.parseInt(val) == 4)
                optionsRadioGroup.check(R.id.option4);
            else
                optionsRadioGroup.check(R.id.option5);
        }
        optionsRadioGroup.setOnCheckedChangeListener(this);
    }
}
