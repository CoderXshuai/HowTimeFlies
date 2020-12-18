package com.example.howtimeflies.activity.ui.time;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimeViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public TimeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is new fragment");//根据自己喜欢放界面的测试文字
    }

    public LiveData<String> getText() {
        return mText;
    }
}
