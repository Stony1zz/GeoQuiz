package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE=
            "com.bignerdranch.android.geoquiz.answer_is_ture";
    private boolean mAnswerIsTrue;
    private static final String TRUE_ANSWER="true_answer";
    private static final String TAG="CheatActivity";
    private static final String KEY_INDEX="index";
    private static final String CHAEAT_INDEX="cheat_index";
    private String text;
    private TextView mShowWarning;
    private TextView mAnswerTextView;
    private Button mShowAnswerButoon;
    private int is_cheat;
    private boolean KEY;
    public static Intent newIntent(Context packageContext,boolean answerIsTrue){
        Intent intent=new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return intent;
    }
    public static int wasCheatShown(Intent intent){
        return intent.getIntExtra(CHAEAT_INDEX,0);
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
        if (savedInstanceState !=null){
            is_cheat=savedInstanceState.getInt(CHAEAT_INDEX,1);
            KEY=savedInstanceState.getBoolean(TRUE_ANSWER,true);
          setAnswerShowResult(KEY,1);
        }
        mShowAnswerButoon= findViewById(R.id.show_answer_button);
        mShowAnswerButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                KEY=true;
                setAnswerShowResult(true,1);
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInsranceState");
        outState.putBoolean(KEY_INDEX,true);
       outState.putBoolean(TRUE_ANSWER,KEY);
       outState.putInt(CHAEAT_INDEX,is_cheat);
    }


    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
    private void setAnswerShowResult(boolean isAnswerShown,int is_cheat){
        Intent data=new Intent();
        data.putExtra(CHAEAT_INDEX,is_cheat);
        data.putExtra(EXTRA_ANSWER_IS_TRUE,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}
