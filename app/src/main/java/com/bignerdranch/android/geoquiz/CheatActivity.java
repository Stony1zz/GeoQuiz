package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE=
            "com.bignerdranch.android.geoquiz.answer_is_ture";
    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private TextView mShowWarning;
    private Button mShowAnswerButoon;
    public static Intent newIntent(Context packageContext,boolean answerIsTrue){
        Intent intent=new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return intent;
    }
    public static boolean wasAnswerShown(Intent intent){
        return intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mShowWarning= findViewById(R.id.warning);
        mAnswerTextView= findViewById(R.id.answer_text_view);
        mShowAnswerButoon= findViewById(R.id.show_answer_button);
        mShowAnswerButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShowResult(true);
            }
        });

    }
    private void setAnswerShowResult(boolean isAnswerShown){
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_IS_TRUE,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}