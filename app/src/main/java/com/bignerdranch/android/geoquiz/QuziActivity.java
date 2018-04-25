package com.bignerdranch.android.geoquiz;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

public class QuziActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestions=new Question[]{
      new Question(R.string.question_africa,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_australia,false),
            new Question(R.string.question_anericas,true),
            new Question(R.string.question_asia,true)

    };
    private int mCurrentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quzi);
        mQuestionTextView =(TextView)findViewById(R.id.question_text_view);
//        int question=mQuestions[mCurrentIndex].getTestResId();
//        mQuestionTextView.setText(question);
        mNextButton=(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex =(mCurrentIndex+1)% mQuestions.length;
//                int question =mQuestions[mCurrentIndex].getTestResId();
//                mQuestionTextView.setText(question);点击调用
                 updateQuestion();
            }
        });
        updateQuestion();//？为什么要有一次updaraQuestion的调用？？
        //思考过后感觉应该是初始化屏幕变量在未点击按钮的时候第一次显示的东西是经过这次调用显示的
        mTrueButton=(Button)findViewById(R.id.ture_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast toast=Toast.makeText(QuziActivity.this,
//                        R.string.correct_toast,Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
//                   将Toast位置设置到屏幕顶部，在设置过程中需要先将Toast先进行实例化，
//                   然后才能进行setGravity方法的调用，此方法不属于类方法。
//                toast.show();
                    checkAnswer(true);
            }
        });
        mFalseButton=(Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast toast=Toast.makeText(QuziActivity.this,R.string.incorrect_toast,
//                        Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
//                toast.show();
                    checkAnswer(false);
            }
        });

    }
    private void updateQuestion(){
        int question =mQuestions[mCurrentIndex].getTestResId();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue =mQuestions[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if(userPressedTrue == answerIsTrue){
            messageResId =R.string.correct_toast;
        }else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }
}
