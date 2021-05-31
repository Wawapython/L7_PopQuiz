package com.example.l7_20demo1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class LiveData extends AndroidViewModel {
    SavedStateHandle handle;
    private static final String KEY_HIGH_SCORE = "key_high_score";
    private static final String KEY_LEFT_NUM = "key_left_num";
    private static final String KEY_RIGHT_NUM = "key_right_num";
    private static final String KEY_OPERATION = "key_operation";
    private static final String KEY_ANS = "key_ans";
    private static final String KEY_CURRENT = "key_current";
    private static final String SAVE_SHP = "save_sharedper";
    boolean winFlag = false;

    // 可以透過@Nonnull讓 application不為null。
    public LiveData(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        if (!handle.contains(KEY_HIGH_SCORE)){
            SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP, Context.MODE_PRIVATE);
            handle.set(KEY_HIGH_SCORE,shp.getInt(KEY_HIGH_SCORE, 0) );
            handle.set(KEY_LEFT_NUM,0);
            handle.set(KEY_RIGHT_NUM,0);
            handle.set(KEY_OPERATION,0);
            handle.set(KEY_ANS,0);
            handle.set(KEY_CURRENT, 0);
            //Log.d("msg", String.valueOf(handle.get(KEY_HIGH_SCORE)));

        }
        this.handle = handle;
    }
    public MutableLiveData<Integer>getLeftNum(){
        return handle.getLiveData(KEY_LEFT_NUM);
    }
    public MutableLiveData<Integer>getRightNum(){
        return handle.getLiveData(KEY_RIGHT_NUM);
    }
    public MutableLiveData<String>getOperator(){
        return handle.getLiveData(KEY_OPERATION);
    }
    public MutableLiveData<Integer>getCurrent(){
        return handle.getLiveData(KEY_CURRENT);
    }
    public MutableLiveData<Integer>getHighScore(){
        return handle.getLiveData(KEY_HIGH_SCORE);
    }
    public MutableLiveData<Integer>getAns(){
        return handle.getLiveData(KEY_ANS);
    }

    void generator(){
        Random random = new Random();
        int x,y;
        x = random.nextInt(20) +1 ;
        y = random.nextInt(20) +1 ;
        if(x%2==0){
            getOperator().setValue("+");
            if(x>y){
                getAns().setValue(x);
                getLeftNum().setValue(y);
                getRightNum().setValue(x - y);
            }else {
                getAns().setValue(y);
                getLeftNum().setValue(x);
                getRightNum().setValue(y-x);
            }
        }else{
            getOperator().setValue("-");
            if(x>y){
                getAns().setValue(x-y);
                getLeftNum().setValue(x);
                getRightNum().setValue(y);
            }else {
                getAns().setValue(y-x);
                getLeftNum().setValue(y);
                getRightNum().setValue(x);
            }
        }
    }

    void save(){
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =shp.edit();
        editor.putInt(KEY_HIGH_SCORE, getHighScore().getValue());
        editor.apply();
    }

    void answerCorr(){
        getCurrent().setValue(getCurrent().getValue()+1);
        if (getCurrent().getValue()>getHighScore().getValue()){
            getHighScore().setValue(getCurrent().getValue());
            winFlag = true;
        }
        generator();
    }
}
