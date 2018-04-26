package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class QuziActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mLastButton;
    private TextView mQuestionTextView;
    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX="index";
    private static final String TRUE_INDEX="true_index";
    private Question[] mQuestions=new Question[]{
      new Question(R.string.question_africa,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_australia,false),
            new Question(R.string.question_anericas,true),
            new Question(R.string.question_asia,true)

    };
    private Answer[] mAnswers=new Answer[]{
            new Answer(R.string.question_africa,0),
            new Answer(R.string.question_oceans,0),
            new Answer(R.string.question_mideast,0),
            new Answer(R.string.question_australia,0),
            new Answer(R.string.question_anericas,0),
            new Answer(R.string.question_asia,0)
    };
    public  float i;
    private int mCurrentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle)called");
        setContentView(R.layout.activity_quzi);
        if (savedInstanceState !=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            i=savedInstanceState.getFloat(TRUE_INDEX,0);
        }
        mQuestionTextView = findViewById(R.id.question_text_view);
//        int question=mQuestions[mCurrentIndex].getTestResId();
//        mQuestionTextView.setText(question);
        mNextButton= findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex =(mCurrentIndex+1)% mQuestions.length;
//                int question =mQuestions[mCurrentIndex].getTestResId();
//                mQuestionTextView.setText(question);点击调用
                 updateQuestion();
            }
        });
        mLastButton= findViewById(R.id.last_button);
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex == 0){
                mCurrentIndex=mQuestions.length-1;
                updateQuestion();
                }else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestions.length;
                    updateQuestion();
                }
            }
        });
        updateQuestion();//？为什么要有一次updaraQuestion的调用？？
        //思考过后感觉应该是初始化屏幕变量在未点击按钮的时候第一次显示的东西是经过这次调用显示的
        mTrueButton= findViewById(R.id.ture_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast toast=Toast.makeText(QuziActivity.this,
//                        R.string.correct_toast,Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
//                   将Toast位置设置到屏幕顶部，在设置过程中需要先将Toast先进行实例化，
//                   然后才能进行setGravity方法的调用，此方法不属于类方法。
//                toast.show();
                if (mAnswers[mCurrentIndex].getIndex()==1){
                    Toast.makeText(QuziActivity.this,"此题已经作答",
                            Toast.LENGTH_SHORT).show();
                }else {
                    checkAnswer(true);
                    mAnswers[mCurrentIndex].setIndex(1);
                }
            }
        });
        mFalseButton= findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast toast=Toast.makeText(QuziActivity.this,R.string.incorrect_toast,
//                        Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
//                toast.show();
                if (mAnswers[mCurrentIndex].getIndex()==1){
                    Toast.makeText(QuziActivity.this,"此题已经作答",
                            Toast.LENGTH_SHORT).show();
                }else {
                    checkAnswer(false);
                    mAnswers[mCurrentIndex].setIndex(1);
                }
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
        outState.putInt(KEY_INDEX,mCurrentIndex);
        outState.putFloat(TRUE_INDEX,i);
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
    private void updateQuestion(){
        int question =mQuestions[mCurrentIndex].getTestResId();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue =mQuestions[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        float m=0;
        float x=mCurrentIndex+1;
        NumberFormat mf=NumberFormat.getNumberInstance();
        mf.setMaximumIntegerDigits(3);
        if(userPressedTrue == answerIsTrue){
            messageResId =R.string.correct_toast;
            i++;

        }else {
            messageResId = R.string.incorrect_toast;
        }
            m = (i / x) * 100;
        Toast.makeText(this, messageResId ,Toast.LENGTH_LONG).show();
        Toast.makeText(this,  "正确率为："+mf.format(m)+"%"+i+mCurrentIndex,Toast.LENGTH_LONG).show();
    }
}
