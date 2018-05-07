package com.bignerdranch.android.geoquiz;

/**
 * Created by li619 on 2018/4/28.
 */

public class Cheat {
    private int mTextResId;
    public int mIndex;
    public Cheat(int textResId,int Index){
        mTextResId=textResId;
        mIndex=Index;
    }

    public int getTestResId(){
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int Index) {
        mIndex = Index;
    }
}
